package com.dscomm.iecs.accept.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dscomm.iecs.accept.dal.po.SmartRecommendationRecordEntity;
import com.dscomm.iecs.accept.dal.repository.AcceptNativeQueryRepository;
import com.dscomm.iecs.accept.dal.repository.SmartRecommendationRecordRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.FindSmartRecommendationInfo;
import com.dscomm.iecs.accept.graphql.typebean.FindSmartRecommendationBean;
import com.dscomm.iecs.accept.graphql.typebean.HierarchicalDispatchVehicleBean;
import com.dscomm.iecs.accept.service.SmartRecommendationService;
import com.dscomm.iecs.accept.service.bean.*;
import com.dscomm.iecs.agent.service.UserService;
import com.dscomm.iecs.base.graphql.typebean.PaginationBean;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.dal.repository.EquipmentVehicleRepository;
import com.dscomm.iecs.basedata.graphql.typebean.OrganizationBean;
import com.dscomm.iecs.basedata.graphql.typebean.SystemConfigurationBean;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.basedata.service.SystemConfigurationService;
import com.dscomm.iecs.intelligent.exception.IntellligentException;
import com.dscomm.iecs.keydata.service.ServletService;
import org.mx.StringUtils;
import org.mx.dal.service.GeneralAccessor;
import org.mx.error.UserInterfaceException;
import org.mx.service.client.rest.RestClientInvoke;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 智能推荐服务
 */
@Component("SmartRecommendationServiceImpl")
public class SmartRecommendationServiceImpl implements SmartRecommendationService {

    private static final Logger logger = LoggerFactory.getLogger(SmartRecommendationServiceImpl.class);
    private LogService logService;
    private UserService userService;
    private Environment env;
    private SmartRecommendationRecordRepository smartRecommendationRecordRepository;
    private SystemConfigurationService systemConfigurationService;
    private AcceptNativeQueryRepository acceptNativeQueryRepository;
    private EquipmentVehicleRepository equipmentVehicleRepository;
    private OrganizationService organizationService;
    private DictionaryService dictionaryService;
    private GeneralAccessor accessor;
    private ServletService servletService;

    @Value("${gradeJudgeUrl:}")
    private String gradeJudgeUrl;
    @Value("${powerTransferUrl:}")
    private String powerTransferUrl;
    @Value("${enableSmartRecommand:false}")
    private Boolean enableSmartRecommand;


    @Autowired

    public SmartRecommendationServiceImpl(LogService logService, Environment env,
                                          SmartRecommendationRecordRepository smartRecommendationRecordRepository,
                                          UserService userService,
                                          SystemConfigurationService systemConfigurationService, AcceptNativeQueryRepository acceptNativeQueryRepository, EquipmentVehicleRepository equipmentVehicleRepository, OrganizationService organizationService, DictionaryService dictionaryService, @Qualifier("generalAccessor") GeneralAccessor accessor,
                                          ServletService servletService) {
        this.logService = logService;
        this.env = env;
        this.systemConfigurationService = systemConfigurationService;
        this.acceptNativeQueryRepository = acceptNativeQueryRepository;
        this.equipmentVehicleRepository = equipmentVehicleRepository;
        this.organizationService = organizationService;
        this.dictionaryService = dictionaryService;
        this.accessor = accessor;
        this.servletService = servletService;
        this.smartRecommendationRecordRepository = smartRecommendationRecordRepository;
        this.userService = userService;

    }

    /**
     * {@inheritDoc}
     *
     * @param inputInfo 参数
     * @return 返回结果
     */

    @Override
    public GradeJudgeResultBean transformGradeJudge(GradeJudgeQueryParamBean inputInfo) {
        RestClientInvoke restClientInvoke = new RestClientInvoke();
        try {
            if (enableSmartRecommand) {
                logService.infoLog(logger, "service", "transformGradeJudge", "service is started...");
                Long logStart = System.currentTimeMillis();
                Long systemTime = servletService.getSystemTime();
                if (inputInfo == null) {
                    throw new IntellligentException(IntellligentException.IntellligentErrors.DATA_NULL);
                } else if (StringUtils.isBlank(inputInfo.getAjxx_czdxdm()) ||
                        StringUtils.isBlank(inputInfo.getXcxx_czdxms()) ||
                        StringUtils.isBlank(inputInfo.getXcxx_rybk())) {
                    throw new IntellligentException(IntellligentException.IntellligentErrors.DATA_NULL);

                }

                Map<String, Object> headers = new HashMap<>();
                headers.put("Content-Type", "application/json;charset=UTF-8");
                Long callStart = System.currentTimeMillis();
                String result = restClientInvoke.post(gradeJudgeUrl, new SmartRecommendParam(inputInfo), String.class);
                Long callEnd = System.currentTimeMillis();
                GradeJudgeResultBean bean = JSONObject.parseObject(result, GradeJudgeResultBean.class);

                SmartRecommendationRecordEntity entity = new SmartRecommendationRecordEntity();
                UserInfo user = userService.getUserInfo();
                if (user != null) {

                    entity.setCallManualName(user.getPersonName());
                    entity.setCallManualNumber(user.getPersonCode());
                    entity.setCallUnitId(user.getOrgId());
                    entity.setCallUnitName(user.getOrgName());
                }
                entity.setIncidentId(inputInfo.getAjxx_id());
                entity.setSjc(System.currentTimeMillis());
                entity.setWasteTime(callEnd - callStart);
                entity.setType("DJTJ");
                entity.setRequestData(JSONObject.toJSONString(inputInfo));
                entity.setResult(JSONObject.toJSONString(bean));
                entity.setState(0);
                entity.setCallTime(systemTime);
                logService.infoLog(logger, "repository", "save", "repository is started...");
                Long startTime = System.currentTimeMillis();

                saveRecord(entity);
                bean.setId(entity.getId());

                Long endTime = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save", String.format("service is finished,execute time is :%sms", startTime - endTime));

                Long logEnd = System.currentTimeMillis();
                logService.infoLog(logger, "service", "transformGradeJudge", String.format("service is finished,execute time is :%sms", logEnd - logStart));
                return bean;
            } else {
                return new GradeJudgeResultBean();
            }

        } catch (Exception ex) {
            logService.erorLog(logger, "service", "transformGradeJudge", "the service fail ", ex);
            throw new AcceptException(AcceptException.AccetpErrors.TTANSFORM_GRADEJUDGE_FAILE);
        }finally {
            if (restClientInvoke!=null){
                restClientInvoke.close();
            }
        }

    }


    @Override
    public PowerTransferResultBean transformPowerTransfer(PowerTransferQueryParam inputInfo) {
        RestClientInvoke restClientInvoke = new RestClientInvoke();
        try {
            logService.infoLog(logger, "service", "transformPowerTransfer", "service is started...");
            Long logStart = System.currentTimeMillis();

            if (inputInfo == null) {
                throw new IntellligentException(IntellligentException.IntellligentErrors.DATA_NULL);
            } else if (StringUtils.isBlank(inputInfo.getAjxx_czdxdm()) || StringUtils.isBlank(inputInfo.getAjxx_lasj()) ||
                    StringUtils.isBlank(inputInfo.getAjxx_ajlxdm()) || StringUtils.isBlank(inputInfo.getAjxx_ajdjdm())) {
                throw new IntellligentException(IntellligentException.IntellligentErrors.DATA_NULL);

            }

            Map<String, Object> headers = new HashMap<>();
            headers.put("Content-Type", "application/json;charset=UTF-8");
            Long callStart = System.currentTimeMillis();
            String result = restClientInvoke.post(powerTransferUrl, new SmartRecommendParam(inputInfo), String.class);
            Long callEnd = System.currentTimeMillis();
            PowerTransferResultBean bean = JSONObject.parseObject(result, PowerTransferResultBean.class);
            logService.infoLog(logger, "service", "transformPowerTransfer", String.format("调用智能力量推荐服务耗费时间:%sms", callEnd - callStart));


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "transformPowerTransfer", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return bean;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "transformPowerTransfer", "theservice fail ", ex);
            throw new AcceptException(AcceptException.AccetpErrors.TTANSFORM_POWERTRANSFER_FAILE);
        }finally {
            if (restClientInvoke!=null){
                restClientInvoke.close();
            }
        }

    }

    /**
     * {@inheritDoc}
     *
     * @param param 参数
     * @return 返回值
     */
    @Transactional
    @Override
    public SmartRecommendVehicleBean smartRecommendVehicle(PowerTransferQueryParam param) {
        try {
            long start = System.currentTimeMillis();
            Long systemTime = servletService.getSystemTime();
            SmartRecommendVehicleBean bean = new SmartRecommendVehicleBean();
            if (enableSmartRecommand) {
                bean.setRecommendParam(param);
                Long callStart = System.currentTimeMillis();
                //调用智能推荐服务
                PowerTransferResultBean recommendRes = transformPowerTransfer(param);
                Long callEnd = System.currentTimeMillis();
                bean.setRecommendResult(recommendRes);
                String force_name_nums = recommendRes.getForce_name_nums();
                Map<String, HierarchicalDispatchVehicleBean> map = new HashMap<>();
                List<String> vehicleList = new ArrayList<>();
                if (!StringUtils.isBlank(force_name_nums)) {
                    Map<String, Integer> recommendMap = JSON.parseObject(force_name_nums, Map.class);
                    if (recommendMap != null && !recommendMap.isEmpty()) {
                        Map<String, String> wlcllx = dictionaryService.findDictionary("WLCLLX");
                        recommendMap.keySet().forEach(key -> {
                            if (!StringUtils.isBlank(key) && !map.containsKey(key)) {
                                HierarchicalDispatchVehicleBean dispatchVehicleBean = new HierarchicalDispatchVehicleBean();
                                dispatchVehicleBean.setVehicleTypeCode(key);
                                dispatchVehicleBean.setDispatchNum(recommendMap.get(key));
                                dispatchVehicleBean.setVehicleTypeName(wlcllx.get(key));
                                map.put(key, dispatchVehicleBean);
                            }
                        });
                        //推荐车辆
                        recommendVehicle(param, map, vehicleList);
                        //计算缺额
                        preareTips(bean, map, vehicleList, wlcllx);

                    }
                }

                SmartRecommendationRecordEntity entity = new SmartRecommendationRecordEntity();
                UserInfo user = userService.getUserInfo();
                if (user != null) {

                    entity.setCallManualName(user.getPersonName());
                    entity.setCallManualNumber(user.getPersonCode());
                    entity.setCallUnitId(user.getOrgId());
                    entity.setCallUnitName(user.getOrgName());
                }
                entity.setIncidentId(param.getAjxx_id());
                entity.setSjc(System.currentTimeMillis());
                entity.setWasteTime(callEnd - callStart);
                entity.setType("LLDP");
                entity.setRequestData(JSONObject.toJSONString(param));
//            entity.setResult(JSONObject.toJSONString(bean));
                JSONObject ret = new JSONObject();
                ret.put("tjfwfhjg", recommendRes);
                ret.put("sjtjjg", bean.getRecommendVehicleDetail());
                ret.put("sjtjclid", bean.getVehicleIds());
                ret.put("tips", bean.getTips());
                entity.setResult(ret.toJSONString());
                entity.setState(0);
                entity.setCallTime(systemTime);
                logService.infoLog(logger, "repository", "save", "repository is started...");
                Long startTime = System.currentTimeMillis();

                saveRecord(entity);

                Long endTime = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save", String.format("service is finished,execute time is :%sms", startTime - endTime));
                bean.setId(entity.getId());
            }

            long end = System.currentTimeMillis();
            logService.infoLog(logger, "service", "smartRecommendVehicle", String.format("fininshed,total time:%sms", end - start));
            return bean;
        } catch (UserInterfaceException ex) {
            logService.erorLog(logger, "service", "smartRecommendVehicle", "fail to smartRecommendVehicle", ex);
            throw ex;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "smartRecommendVehicle", "fail to smartRecommendVehicle", ex);
            throw new AcceptException(AcceptException.AccetpErrors.RECOMMEND_VEHICLE_FAIL);
        }
    }

    private void preareTips(SmartRecommendVehicleBean bean, Map<String, HierarchicalDispatchVehicleBean> map, List<String> vehicleList, Map<String, String> wlcllx) {
        StringBuffer sbd = new StringBuffer();
        if (!map.isEmpty()) {
            map.keySet().forEach(key -> {
                HierarchicalDispatchVehicleBean dispatchVehicleBean = map.get(key);
                int num = dispatchVehicleBean.getDispatchNum() - dispatchVehicleBean.getVehicleNum();
                if (num > 0) {
                    sbd.append("缺少 ").append(wlcllx.get(key) == null ? "" : wlcllx.get(key)).append("(").append(key).append(")").append(":").append(num).append("辆,");
                }
            });
            if (!StringUtils.isBlank(sbd.toString()) && sbd.length() > 1) {
                sbd.deleteCharAt(sbd.length() - 1);
            }
            bean.setRecommendVehicleDetail(new ArrayList<>(map.values()));
            bean.setTips(sbd.toString());
            bean.setVehicleIds(vehicleList);
        }
    }

    private void recommendVehicle(PowerTransferQueryParam param, Map<String, HierarchicalDispatchVehicleBean> map, List<String> vehicleList) {
        if (!map.isEmpty()) {
//                Map<String,Map<String,Set<String>>> temp=new HashMap<>();
            //获取可可调派车辆状态配置
            List<String> status = null;
            SystemConfigurationBean statusBean = systemConfigurationService.getSystemConfigByConfigType("vehicleDispatchStatus");
            if (statusBean != null && !StringUtils.isBlank(statusBean.getConfigValue())) {
                String[] split = statusBean.getConfigValue().split(",");
                if (split != null && split.length > 0) {
                    status = Arrays.asList(split);
                }
            }
            //查出主管机构符合条件的所有可调派车辆
            Map<String, OrganizationBean> orgs = organizationService.findOrganizationAll();
            if (StringUtils.isBlank(param.getAjxx_xfjgid())) {
                UserInfo userInfo = userService.getUserInfo();
                if (userInfo != null) {
                    param.setAjxx_xfjgid(userInfo.getOrgId());
                }
            }
            if (!StringUtils.isBlank(param.getAjxx_xfjgid()) && orgs.containsKey(param.getAjxx_xfjgid())) {
                OrganizationBean organizationBean = orgs.get(param.getAjxx_xfjgid());
                List<Object[]> ret = equipmentVehicleRepository.findAvailableVehicleByOrgSearchPathAndVehicleStatus(organizationBean.getSearchPath(), status, map.keySet());
                if (ret != null && !ret.isEmpty()) {
                    ret.forEach(objects -> {
                        String orgid = String.valueOf(objects[0]);
                        String typeCode = String.valueOf(objects[1]);
                        String vehicleId = String.valueOf(objects[2]);
//                            transform(temp, orgid, typeCode, vehicleId);
                        HierarchicalDispatchVehicleBean dispatchVehicleBean = map.get(typeCode);
                        List<String> list = dispatchVehicleBean.getVehicles();
                        if (list == null) {
                            list = new ArrayList<>();
                            dispatchVehicleBean.setVehicleNum(0);
                        }
                        if (!list.contains(vehicleId)) {
                            list.add(vehicleId);
                            dispatchVehicleBean.setVehicleNum(list.size());
                            vehicleList.add(vehicleId);
                        }
                    });

                }


                //如果不够，则继续查找毗邻单位车辆
                Map<String, Integer> vacancy = new HashMap<>();
                map.keySet().forEach(key -> {
                    HierarchicalDispatchVehicleBean dispatchVehicleBean = map.get(key);
                    int num = dispatchVehicleBean.getDispatchNum() - dispatchVehicleBean.getVehicleNum();
                    if (num > 0) {
                        vacancy.put(dispatchVehicleBean.getVehicleTypeCode(), num);
                    }
                });
                if (!vacancy.isEmpty()) {
                    List<String> lt = new ArrayList<>();
                    lt.addAll(vacancy.keySet());
                    //查找毗邻机构车辆(排序)
                    List<Object[]> result = acceptNativeQueryRepository.findVehicleByAdjacentPriority(param.getAjxx_xfjgid(), vacancy.keySet(), status);
                    if (result != null && !result.isEmpty()) {
                        for (Object[] objects : result) {
                            if (lt.isEmpty()) {
                                break;
                            }
                            String vehicleId = String.valueOf(objects[0]);
                            String type = String.valueOf(objects[1]);
                            HierarchicalDispatchVehicleBean dispatchVehicleBean = map.get(type);
                            List<String> vehicles = dispatchVehicleBean.getVehicles();
                            if (vehicles == null) {
                                vehicles = new ArrayList<>();
                                dispatchVehicleBean.setVehicleNum(0);
                            }
                            int num = dispatchVehicleBean.getDispatchNum() - dispatchVehicleBean.getVehicleNum();
                            if (num > 0) {
                                if (!vehicles.contains(vehicleId) && !vehicleList.contains(vehicleId)) {
                                    vehicles.add(vehicleId);
                                    vehicleList.add(vehicleId);
                                }
                            } else {
                                lt.remove(type);
                            }
                        }
                    }
                    result.clear();
                    result = null;
                }
            }
        }
    }

    private void transform(Map<String, Map<String, Set<String>>> temp, String orgid, String typeCode, String vehicleId) {
        Map<String, Set<String>> typeMap = temp.get(orgid);
        if (typeMap == null) {
            typeMap = new HashMap<>();
        }
        Set<String> vehicles = typeMap.get(typeCode);
        if (vehicles == null) {
            vehicles = new HashSet<>();
        }
        vehicles.add(vehicleId);
        typeMap.put(typeCode, vehicles);
        temp.put(orgid, typeMap);
    }

    private FindSmartRecommendationBean transform(Object[] obj){
        FindSmartRecommendationBean bean = new FindSmartRecommendationBean();

        if(obj==null){
            return null;
        }


        SmartRecommendationRecordEntity entity = (SmartRecommendationRecordEntity)obj[0];
        bean.setId(entity.getId());
        bean.setIncidentId(entity.getIncidentId());
        bean.setRequestData(entity.getRequestData());
        bean.setResult(entity.getResult());
        bean.setType(entity.getType());
        bean.setCallManualNumber(entity.getCallManualNumber());
        bean.setCallManualName(entity.getCallManualName());
        bean.setCallUnitId(entity.getCallUnitId());
        bean.setCallUnitName(entity.getCallUnitName());
        bean.setCallTime(entity.getCallTime());
        bean.setSjc(entity.getSjc());
        bean.setCreatedTime(entity.getCreatedTime());
        bean.setUpdatedTime(entity.getUpdatedTime());
        bean.setValid(entity.getValid());
        bean.setOperator(entity.getOperator());
        bean.setActualResults(entity.getActualResults());
        bean.setCauseOfDifference(entity.getCauseOfDifference());
        bean.setWasteTime(entity.getWasteTime());
        bean.setState(entity.getState());

        return bean;
    }

    @Transactional
    protected void saveRecord(SmartRecommendationRecordEntity entity) {
        try {
            accessor.save(entity);
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveRecord", "fail to save record", ex);
        }
    }

    /**
     * {@inheritDoc}
     * @param param 参数
     * @return 返回结果
     */
    @Transactional
    @Override
    public Boolean confirmSmartRecommend(ConfirmSmartRecommendBean param) {
        if (param==null||StringUtils.isBlank(param.getId())||(StringUtils.isBlank(param.getCauseOfDifference())&&(StringUtils.isBlank(param.getLevel())||param.getDispatchVehicles()==null))){
            logService.erorLog(logger,"service","confirmSmartRecommend",String.format("param is null,param:",JSONObject.toJSONString(param)),null);
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            SmartRecommendationRecordEntity entity = accessor.getById(param.getId(), SmartRecommendationRecordEntity.class);
            if (entity!=null){
                entity.setState(1);
                if (!StringUtils.isBlank(param.getCauseOfDifference())){
                    entity.setCauseOfDifference(param.getCauseOfDifference());
                }
                if ("DJTJ".equals(entity.getType())&&!StringUtils.isBlank(param.getLevel())){
                    entity.setActualResults(param.getLevel());
                }else if ("LLDP".equals(entity.getType())&&param.getDispatchVehicles()!=null&&!param.getDispatchVehicles().isEmpty()){
                    entity.setActualResults(JSONObject.toJSONString(param.getDispatchVehicles()));
                }
                accessor.save(entity);
                return true;
            }
            return false;
        }catch (Exception ex){
            logService.erorLog(logger,"service","confirmSmartRecommend",String.format("fail to confirmSmartRecommend,data:%s",JSONObject.toJSONString(param)),ex);
            return false;
        }
    }
    /**
     * 查询智能辅助记录
     * @param queryBean
     * @return 返回值描述
     */
    @Transactional(readOnly = true)
    @Override
    public PaginationBean<FindSmartRecommendationBean> findSmartRecommendationRecord(FindSmartRecommendationInfo queryBean){

        try {
            if (queryBean == null) {
                logService.infoLog(logger, "service", "findSmartRecommendationRecord", "queryBean is null.");
                throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
            }
            logService.infoLog(logger, "service", "findSmartRecommendationRecord", "service is started...");
            Long logStart = System.currentTimeMillis();
//准备工作

            logService.infoLog(logger, "repository",
                    "findSmartRecommendationRecordCondition", "repository is started...");
            Long start = System.currentTimeMillis();

            PaginationBean<FindSmartRecommendationBean> list = smartRecommendationRecordRepository.findSmartRecommendationRecordCondition(queryBean);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findSmartRecommendationRecordCondition",
                    String.format("repository is finished,execute time is :%sms", end - start));

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findSmartRecommendationRecord", String.format("service is finished,execute time is :%sms", logEnd - logStart));


            return list;

        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findSmartRecommendationRecord", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_SMARTRECOMMEND_FAIL);
        }
    }

}
