package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.GradeEntity;
import com.dscomm.iecs.accept.dal.repository.GradeRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.IncidentgradeputInfo;
import com.dscomm.iecs.accept.graphql.typebean.IncidentGradeBean;
import com.dscomm.iecs.accept.service.FindVehicleGradeService;
import com.dscomm.iecs.base.service.log.LogService;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component("findVehicleGradeServiceImp")
public class FindVehicleGradeServiceImp implements FindVehicleGradeService {
    private static final Logger logger = LoggerFactory.getLogger(FindVehicleGradeServiceImp.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private GradeRepository gradeRepository;
    @Autowired
    public FindVehicleGradeServiceImp(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor,
                                      GradeRepository gradeRepository

    ){
        this.logService = logService;
        this.accessor = accessor;
        this.gradeRepository = gradeRepository;
    }
    /*
    * 根据案件信息做等级调派派车
    * */
    @Override
    public List<IncidentGradeBean> findVehicleGrade(IncidentgradeputInfo info) {
        if(info == null){
            logService.infoLog(logger, "service", "findVehicleGrade", "data  is null.");
            throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findVehicleGrade", "service is started...");
            Long startHandleBatch = System.currentTimeMillis();
            List<IncidentGradeBean> list = new ArrayList<>();
            // 进行逻辑判
            // 1.判断案件是否处于春节期间
            Date registerIncidentTime = info.getRegisterIncidentTime(); //假设格式是 2021/1/1 15:30:32
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String format = sdf.format(registerIncidentTime);
            String[] split = format.split("");
            String[] split1 = split[0].split("/");
            String s = split[0];
            // 2.判断案件是否处于暴雨期间

            // 3.如果不是以上的模式，则根据案件的案件类型、处置对象和案件等级等条件，
            //   从预先设定的等级调派规则中计算出应派车辆的功能及数量
            IncidentGradeBean incidentGradeBean = new IncidentGradeBean();
            String incidentLevelCode = info.getIncidentLevelCode(); // 案件等级
            String incidentTypeCode = info.getIncidentTypeCode(); // 案件类型
            String disposalObjectCode = info.getDisposalObjectCode(); // 处置对象
            GradeEntity gradeEntity = gradeRepository.queryInfo(incidentLevelCode, incidentTypeCode, disposalObjectCode);
            incidentGradeBean.setClsl(gradeEntity.getSl());
            incidentGradeBean.setClmc(gradeEntity.getCllx());
            list.add(incidentGradeBean);
            // 4.判断当前时间是否重点时段——晚上22:00~第二点6:00（时间段可配置）
//            if(){
                IncidentGradeBean incidentGradeBean2= new IncidentGradeBean();
                incidentGradeBean.setClmc("照明车");
                incidentGradeBean.setClsl("1");
                list.add(incidentGradeBean);
//            }
            // 5.判断案发地址是否地下
            // （根据地址中是否含有“地下”字样判别），若是则增加一辆排烟车
            String crimeAddress = info.getCrimeAddress();
            if(crimeAddress.contains("地下")){
                IncidentGradeBean incidentGradeBean1 = new IncidentGradeBean();
                incidentGradeBean.setClmc("排烟车");
                incidentGradeBean.setClsl("1");
                list.add(incidentGradeBean);
            }
            // 调派车辆
            Long endHandleBatch = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findVehicleGrade", String.format("service is finished,execute time is :%sms", endHandleBatch - startHandleBatch));
            return list;
        } catch (Exception e) {
            logService.erorLog(logger, "service", "findVehicleGrade", "execution error", e);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_FAIL);
        }
    }
}
