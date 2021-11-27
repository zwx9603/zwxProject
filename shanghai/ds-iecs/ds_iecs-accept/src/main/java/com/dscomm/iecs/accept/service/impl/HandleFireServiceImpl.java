package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.ParticipantFeedbackEntity;
import com.dscomm.iecs.accept.dal.repository.HandleOrganizationRepository;
import com.dscomm.iecs.accept.dal.repository.HandleOrganizationVehicleRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.typebean.DispatchAgencyCarBean;
import com.dscomm.iecs.accept.service.HandleFireService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.graphql.typebean.EquipmentVehicleBean;
import com.dscomm.iecs.basedata.graphql.typebean.OrganizationBean;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.basedata.service.VehicleService;
import com.dscomm.iecs.ext.vehicle.status.VEHICLE_STATUS_CDDC;
import com.dscomm.iecs.ext.vehicle.status.VEHICLE_STATUS_CDFD;
import com.dscomm.iecs.ext.vehicle.status.VEHICLE_STATUS_CDTZ;
import org.apache.logging.log4j.util.Strings;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：处警记录（调派记录）实战指挥 服务类实现
 */
@Component("handleFireServiceImpl")
public class HandleFireServiceImpl implements HandleFireService {
    private static final Logger logger = LoggerFactory.getLogger(HandleFireServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private Environment env;
    private OrganizationService organizationService;
    private HandleOrganizationRepository handleOrganizationRepository;
    private HandleOrganizationVehicleRepository handleOrganizationVehicleRepository;
    private DictionaryService dictionaryService;
    private VehicleService vehicleService;

    /**
     * 默认的构造函数
     */
    @Autowired
    public HandleFireServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor, Environment env,
                                 OrganizationService organizationService,
                                 HandleOrganizationRepository handleOrganizationRepository,
                                 HandleOrganizationVehicleRepository handleOrganizationVehicleRepository,
                                   DictionaryService dictionaryService,
                                 VehicleService vehicleService


    ) {

        this.accessor = accessor;
        this.logService = logService;
        this.env = env;
        this.organizationService = organizationService;
        this.dictionaryService = dictionaryService;
        this.handleOrganizationRepository = handleOrganizationRepository;
        this.handleOrganizationVehicleRepository = handleOrganizationVehicleRepository;
        this.dictionaryService = dictionaryService;
        this.vehicleService = vehicleService;

    }






    /**
     * {@inheritDoc}
     *
     * @see  #findIncidentHandleOrganizationBean(String)
     */
    @Transactional(readOnly = true)
    @Override
    public List<OrganizationBean> findIncidentHandleOrganizationBean(String incidentId) {
        if (Strings.isBlank(incidentId)) {
            logService.infoLog(logger, "service", "findIncidentHandleOrganization", "incidentId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findIncidentHandleOrganization", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<OrganizationBean> res = new ArrayList<>();

            //根据案件id 从调派单位中 获取 单位id
            logService.infoLog(logger, "repository", "findOrganizationIdByIncidentId", "repository is started...");
            Long start = System.currentTimeMillis();

            List<String> organizationIdList = handleOrganizationRepository.findOrganizationIdByIncidentId(incidentId);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findOrganizationIdByIncidentId", String.format("repository is finished,execute time is :%sms", end - start));

            //根据单位id 查询具体单位
            if (organizationIdList != null && organizationIdList.size() > 0) {
                res = organizationService.findOrganizationsByIds(organizationIdList);
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findIncidentHandleOrganization", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findIncidentHandleOrganization", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_INCIDENT_HANDLE_ORGANIZATION_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see  #findIncidentHandleVehicle(String)
     */
    @Transactional(readOnly = true)
    @Override
    public List<DispatchAgencyCarBean> findIncidentHandleVehicle(String incidentId) {
        if (Strings.isBlank(incidentId)) {
            logService.infoLog(logger, "service", "findIncidentHandleVehicle", "incidentId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findIncidentHandleVehicle", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<EquipmentVehicleBean> res = new ArrayList<>();
            List<DispatchAgencyCarBean> dispatchAgencyCarBeans = new ArrayList<>();

            logService.infoLog(logger, "repository", "findVehicleIdByIncidentId", "repository is started...");
            Long start = System.currentTimeMillis();

            List<String>  vehicleIds = handleOrganizationVehicleRepository.findVehicleIdByIncidentId(incidentId);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findVehicleIdByIncidentId", String.format("repository is finished,execute time is :%sms", end - start));
            DispatchAgencyCarBean  dispatchAgencyCarBeanArrived = new DispatchAgencyCarBean();//到场车辆统计
            dispatchAgencyCarBeanArrived.setStateCode( VEHICLE_STATUS_CDDC.getCode());
            dispatchAgencyCarBeanArrived.setStateName("到场");
            DispatchAgencyCarBean  dispatchAgencyCarBeanOnWay = new DispatchAgencyCarBean();//途中车辆统计
            dispatchAgencyCarBeanOnWay.setStateCode( VEHICLE_STATUS_CDTZ.getCode());
            dispatchAgencyCarBeanOnWay.setStateName("途中");
            DispatchAgencyCarBean  dispatchAgencyCarBeanBack = new DispatchAgencyCarBean();//返回车辆统计
            dispatchAgencyCarBeanBack.setStateCode( VEHICLE_STATUS_CDFD.getCode());
            dispatchAgencyCarBeanBack.setStateName("返回");
            Integer arrived = 0;
            Integer onWay = 0;
            Integer back = 0;

            if (vehicleIds != null && vehicleIds.size() > 0) {
                //根据车辆id查询车辆详情列表
                res  = vehicleService.findVehicleCacheList(vehicleIds);
                Map<String, EquipmentVehicleBean> vehicleBeanMap = new HashMap<>() ;
                for(EquipmentVehicleBean vehicleBean : res ){
//                    vehicleBeanMap.put( vehicleBean.getId() , vehicleBean ) ;
//                    String vehicleState = dicsMap.get("WLCLZT").get( vehicleBean.getVehicleStatusCode() );
                    //只统计中途，到场和返回的车辆
                    if (dispatchAgencyCarBeanArrived.getStateCode().equals(vehicleBean.getVehicleStatusCode())){//到场
                        arrived++;
                    }else if (dispatchAgencyCarBeanOnWay.getStateCode().equals(vehicleBean.getVehicleStatusCode())){//途中
                        onWay++;
                    }else if (dispatchAgencyCarBeanBack.getStateCode().equals(vehicleBean.getVehicleStatusCode())){//反队
                        back++;
                    }
                }
                dispatchAgencyCarBeanArrived.setCount(arrived);
                dispatchAgencyCarBeanOnWay.setCount(onWay);
                dispatchAgencyCarBeanBack.setCount(back);
                dispatchAgencyCarBeans.add(dispatchAgencyCarBeanArrived);
                dispatchAgencyCarBeans.add(dispatchAgencyCarBeanOnWay);
                dispatchAgencyCarBeans.add(dispatchAgencyCarBeanBack);

            }


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findIncidentHandleVehicle", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return dispatchAgencyCarBeans;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findIncidentHandleVehicle", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_INCIDENT_HANDLE_VEHICLE_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see  #findIncidentParticipant(String)
     */
    @Transactional(readOnly = true)
    @Override
    public Integer findIncidentParticipant(String incidentId) {
        if (Strings.isBlank(incidentId)) {
            logService.infoLog(logger, "service", "findIncidentParticipant", "incidentId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findIncidentParticipant", "service is started...");
            Long logStart = System.currentTimeMillis();
            GeneralAccessor.ConditionGroup participantCondition = GeneralAccessor.ConditionGroup.and(
                    GeneralAccessor.ConditionTuple.eq("valid",1),
                    GeneralAccessor.ConditionTuple.eq("incidentId",incidentId)
            );
            Long participantCount = accessor.count(participantCondition,ParticipantFeedbackEntity.class);
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findIncidentParticipant", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return Integer.parseInt(participantCount.toString());
        }catch (Exception e){
            logService.erorLog(logger, "service", "findIncidentParticipant", "execution error", e);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_DISPATCHAGENCY_FAIL);
        }
    }





}
