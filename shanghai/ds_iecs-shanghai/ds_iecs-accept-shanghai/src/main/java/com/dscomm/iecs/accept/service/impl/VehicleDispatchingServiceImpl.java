package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.AssistanceEntity;
import com.dscomm.iecs.accept.dal.po.VehicleNumEntity;
import com.dscomm.iecs.accept.dal.repository.AssistanceRepository;
import com.dscomm.iecs.accept.dal.repository.VehicleNumRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.FireDzInputBean;
import com.dscomm.iecs.accept.graphql.typebean.FireDzBean;
import com.dscomm.iecs.accept.service.VehicleDispatchingService;
import com.dscomm.iecs.base.service.log.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("vehicleDispatchingServiceImpl")
public class VehicleDispatchingServiceImpl implements VehicleDispatchingService {

    private static final Logger logger = LoggerFactory.getLogger(VehicleDispatchingServiceImpl.class);
    private LogService logService;
    private VehicleNumRepository vehicleNumRepository;
    private AssistanceRepository assistanceRepository;
    @Autowired
    public VehicleDispatchingServiceImpl(LogService logService,VehicleNumRepository vehicleNumRepository,
                                         AssistanceRepository assistanceRepository){
        this.logService = logService;
        this.vehicleNumRepository = vehicleNumRepository;
        this.assistanceRepository = assistanceRepository;
    }

    /*
    * 查询案发地点周围10个中队中可派车辆到案发点的距离
    * */
    @Override
    public Map<String,List<FireDzBean>> queryJL(
            List<FireDzInputBean> list
//            FireDzInputBean fireDzInputBean
    ) {
        if (list == null){
            logService.infoLog(logger, "service", "queryJL", "fireDzInputBean is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "queryJL", "service is started...");
            Long logStart = System.currentTimeMillis();
            // 执行逻辑
            /*Map<String,List<FireDzBean>> map = new HashMap<>();
            for (FireDzInputBean ele1:list) {  // 对传入的参数进行循环
                FireDzBean fireDzBean = new FireDzBean();
                String fireZbz = ele1.getFireZb(); // 获得火灾的坐标
                String zdzb = ele1.getZdzb(); // 获得中队的坐标
//              String zdName = ele1.getZdName(); // 获得中队的名字
                String zddm = ele1.getZddm(); // 获得中队的代码
                // 1.调用计算距离的接口获得 中队到案发点的距离
                FireDzBean fireDzBean1 =countJl(fireZb,zdzb,zddm,fireDzBean);
                // 2.计算中队中可派车辆 到案发点的距离
                List<FireDzBean> findkpcljl = findkpcljl(fireDzBean1);
                map.put(zddm,findkpcljl);
            }
            // 3.根据距离进行排序(主管中队放首位，其余的升序排序)
//                search(list2);*/
            // 1.计算中队到案发点的距离
            List<FireDzBean> fireDzBeans = new ArrayList<>();
            for (FireDzInputBean ele1:list) {  // 对传入的参数进行循环
                FireDzBean fireDzBean = new FireDzBean();
                String fireZb = ele1.getFireZb(); // 获得火灾的坐标
                String zdzb = ele1.getZdzb(); // 获得中队的坐标
//              String zdName = ele1.getZdName(); // 获得中队的名字
                String zddm = ele1.getZddm(); // 获得中队的代码
                // 1.调用计算距离的接口获得 中队到案发点的距离
                FireDzBean fireDzBean1 = countJl(fireZb, zdzb, zddm, fireDzBean);
                fireDzBeans.add(fireDzBean1);
            }
            // 2.根据距离进行排序(主管中队放首位，其余的升序排序)
            List<FireDzBean> list1 = new ArrayList<>();
            for (FireDzBean ele:fireDzBeans) {
                if(ele.getZddm() == "dsf"){ // 判断主管中队
                    list1.add(ele);
                    fireDzBeans.remove(ele);
                    break;
                }
            }
            // 对除了主管中队之外的中队进行排序
            List<FireDzBean> search = search(fireDzBeans);
            for (FireDzBean ele: search) {
                list1.add(ele);
            }
            // 3.计算中队中可派车辆到案发点的距离
            Map<String, List<FireDzBean>> stringListMap = findkpcljl2(search);
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "queryJL", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return stringListMap;
        }catch (Exception ex){
            logService.erorLog(logger, "service", "queryJL", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_FAIL);
        }
    }

    /*
    * 查询主管中队附近的十个中队
    * */
    public List<AssistanceEntity> findTenzdInfo(String zgzddm){
        List<AssistanceEntity> list = assistanceRepository.queryBlXfJl(zgzddm);
        return list;
    }
    /*
    * 1.计算中队中可派车辆到案发点的距离
    * */
    public List<FireDzBean>  findkpcljl(FireDzBean fireDzBean1){
        List<FireDzBean> list1 = new ArrayList<>();
        String zddm = fireDzBean1.getZddm();  // 获取中队代码
        List<VehicleNumEntity> list2 = vehicleNumRepository.queryCarInfo(zddm);
        for (VehicleNumEntity ele1:list2) {
            FireDzBean fireDzBean = new FireDzBean();
            fireDzBean = fireDzBean1; // 赋值
            String zblxdm = ele1.getZblxdm(); // 车辆类型代码
            fireDzBean.setCllx(zblxdm);
            fireDzBean.setCljl(fireDzBean1.getZdJl()); // 距离
            list1.add(fireDzBean);
        }
        return list1;
    }

    /*
     * 2.计算中队中可派车辆到案发点的距离
     * */
    public Map<String,List<FireDzBean>>  findkpcljl2(List<FireDzBean> fireDzBean2){
        Map<String,List<FireDzBean>> map = new HashMap<>();
        List<FireDzBean> list1 = new ArrayList<>();
        for(FireDzBean ele1:fireDzBean2){
            String zddm = ele1.getZddm();
            List<VehicleNumEntity> list2 = vehicleNumRepository.queryCarInfo(zddm);
            for (VehicleNumEntity ele2:list2) {
                FireDzBean fireDzBean = new FireDzBean();
                fireDzBean = ele1; // 赋值
                String zblxdm = ele2.getZblxdm(); // 车辆类型代码
                fireDzBean.setCllx(zblxdm);
                fireDzBean.setCljl(ele1.getZdJl()); // 距离
                list1.add(fireDzBean);
            }
            map.put(zddm,list1);
        }
        return map;
    }
    /*
    * 对中队到火灾的距离进行排序,升序
    * */
    public List<FireDzBean> search(List<FireDzBean> studentList){
        Collections.sort(studentList, new Comparator<FireDzBean>() {
            @Override
            public int compare(FireDzBean o1, FireDzBean o2) {
                if ((Integer.valueOf(o1.getZdJl()) >Integer.valueOf(o2.getZdJl()))){
                    return 1;
                }
                if ((Integer.valueOf(o1.getZdJl()) == Integer.valueOf(o2.getZdJl()))){
                    return 0;
                }
                return -1;
            }
        });
        return studentList;
    }

    /*
    * 计算案发地点周围的驻防点可派车辆到案发点的距离
    * */
    public List<FireDzBean> findZfdjl(List<FireDzInputBean> list){
        List<FireDzBean> list1 = new ArrayList<>();
        for (FireDzInputBean ele:list) {
            String zfdzb = ele.getZfdzb();
            String fireZb = ele.getFireZb();
            // 调用计算距离的接口
            FireDzBean fireDzBean = countJl(fireZb, zfdzb);
            list1.add(fireDzBean);
        }
        return list1;
    }

    /*
    * 计算案发点到中队的距离
    * */
    public FireDzBean countJl(String fireZb,String zdzb,String zddm,FireDzBean fireDzBean){
        FireDzBean fireDzBeans = new FireDzBean();
        return fireDzBeans;
    }

    /*
    * 计算驻防点到案发点的距离
    * */
    public FireDzBean countJl(String fireZb,String zfdzb){
        FireDzBean fireDzBeans = new FireDzBean();
        return fireDzBeans;
    }

}