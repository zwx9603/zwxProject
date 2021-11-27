package com.dscomm.iecs.basedata.service.impl;

import com.alibaba.fastjson.JSON;
import com.dscomm.iecs.base.service.SubAuditService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.dal.po.AttachmentEntity;
import com.dscomm.iecs.basedata.dal.po.PlanDispatchEntity;
import com.dscomm.iecs.basedata.dal.po.PlanEntity;
import com.dscomm.iecs.basedata.dal.repository.AttachmentRepository;
import com.dscomm.iecs.basedata.dal.repository.PlanRepository;
import com.dscomm.iecs.basedata.exception.BasedataException;
import com.dscomm.iecs.basedata.graphql.inputbean.AttachmentSaveInputInfo;
import com.dscomm.iecs.basedata.graphql.inputbean.PlanSaveInputInfo;
import com.dscomm.iecs.basedata.graphql.typebean.AttachmentBean;
import com.dscomm.iecs.basedata.graphql.typebean.EquipmentVehicleBean;
import com.dscomm.iecs.basedata.graphql.typebean.PlanBean;
import com.dscomm.iecs.basedata.graphql.typebean.PlanDispatchBean;
import com.dscomm.iecs.basedata.service.*;
import com.dscomm.iecs.basedata.service.enums.AttachmentRelationEnum;
import com.dscomm.iecs.basedata.utils.KeyUnitTransformUtil;
import com.dscomm.iecs.keydata.service.AuditLogService;
import com.dscomm.iecs.keydata.service.ServletService;
import org.apache.logging.log4j.util.Strings;
import org.mx.StringUtils;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 描述：预案 服务类实现
 */
@Component("planServiceImpl")
public class PlanServiceImpl implements PlanService {
    private static final Logger logger = LoggerFactory.getLogger(PlanServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private Environment env;
    private DictionaryService dictionaryService;
    private OrganizationService organizationService;
    private PlanRepository planRepository;
    private AttachmentRepository attachmentRepository;
    private AttachmentService attachmentService;
    private ServletService servletService ;
    private VehicleService vehicleService ;


    private List<String> dics;

    /**
     * 默认的构造函数
     */
    @Autowired
    public PlanServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor,
                           Environment env, DictionaryService dictionaryService, OrganizationService organizationService,
                           PlanRepository planRepository, SystemConfigurationService systemConfigurationService,
                           AuditLogService auditLogService, SubAuditService subAuditService, AttachmentService attachmentService,
                           AttachmentRepository attachmentRepository, ServletService servletService , VehicleService vehicleService

    ) {
        this.logService = logService;
        this.accessor = accessor;
        this.env = env;
        this.dictionaryService = dictionaryService;
        this.organizationService = organizationService;
        this.planRepository = planRepository;

        this.attachmentService = attachmentService;
        this.attachmentRepository = attachmentRepository;
        this.servletService = servletService;
        this.vehicleService = vehicleService ;

        dics = new ArrayList<>(Arrays.asList("WLCLLX", "WLCLDJ", "WLZZGN","YAZL", "YALX", "YAZT" ,"YADXLX"));
    }

    /**
     * {@inheritDoc}
     *
     * @see PlanService#saveKeyUnitPlan(PlanSaveInputInfo)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PlanBean saveKeyUnitPlan(PlanSaveInputInfo inputInfo) {
        if (inputInfo == null ||  Strings.isBlank( inputInfo.getKeyUnitId() )) {
            logService.infoLog(logger, "service", "saveKeyUnitPlan", "PlanSaveInputInfo or keyUnitId is null.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveKeyUnitPlan", "service is started...");
            Long logStart = System.currentTimeMillis();

            PlanBean res =  null ;
            List<PlanDispatchBean> planDispatchBeanList = new ArrayList<>();
            List<AttachmentBean> attachmentBeans = new ArrayList<>();
            Long makeTime = servletService.getSystemTime();

            decodePlanSaveInputInfo(inputInfo); //预案保存 URL转码

            //保存预案
            PlanEntity planEntity = KeyUnitTransformUtil.transform(inputInfo,makeTime);
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();



            if (planEntity != null) {
                logService.infoLog(logger, "repository", "save(dbPlanEntity)", "repository is started...");
                Long start = System.currentTimeMillis();

                accessor.save(planEntity);

                Long end = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbPlanEntity)", String.format("repository is finished,execute time is :%sms", end - start));

                //预案id
                String  planId = planEntity.getId() ;

                //删除预案历史调派车辆信息
                List<PlanDispatchEntity> oldPlanDispatchEntityList = planRepository.findPlanDispatchByPlanId( planId ) ;
                if( oldPlanDispatchEntityList != null  && oldPlanDispatchEntityList.size() > 0 ){
                    logService.infoLog(logger, "repository", "remove(dbPlanDispatchEntityList)", "repository is started...");
                    Long startPlanDispatch = System.currentTimeMillis();

                    accessor.remove( oldPlanDispatchEntityList , true ) ; //逻辑删除 预案历史调派数据

                    Long endPlanDispatch = System.currentTimeMillis();
                    logService.infoLog(logger, "repository", "remove(dbPlanDispatchEntityList)", String.format("repository is finished,execute time is :%sms", endPlanDispatch - startPlanDispatch));
                }

                //保存预案调派
                List<PlanDispatchEntity> planDispatchEntityList = KeyUnitTransformUtil.transform(inputInfo ,planId  );

                if (planDispatchEntityList != null && planDispatchEntityList.size() > 0) {
                    logService.infoLog(logger, "repository", "save(dbPlanDispatchEntityList)", "repository is started...");
                    Long startPlanDispatch = System.currentTimeMillis();

                    accessor.save(planDispatchEntityList);

                    Long endPlanDispatch = System.currentTimeMillis();
                    logService.infoLog(logger, "repository", "save(dbPlanDispatchEntityList)", String.format("repository is finished,execute time is :%sms", endPlanDispatch - startPlanDispatch));

                    for (PlanDispatchEntity planDispatchEntity : planDispatchEntityList) {
                        EquipmentVehicleBean vehicleBean = vehicleService.findVehicleCache( planDispatchEntity.getVehicleId() ) ;
                        PlanDispatchBean bean = KeyUnitTransformUtil.transform(planDispatchEntity, dicsMap, organizationNameMap , vehicleBean );
                        planDispatchBeanList.add(bean);
                    }
                }

                List<AttachmentEntity> attachmentEntityList = attachmentRepository.findAttachmentByRelationId(planId);
                if (attachmentEntityList != null && attachmentEntityList.size()>0){
                    accessor.remove(attachmentEntityList,true);
                }

                //保存附件
                if (inputInfo.getAttachmentSaveInputInfo() != null && inputInfo.getAttachmentSaveInputInfo().size()>0){
                    List<AttachmentSaveInputInfo> attachmentSaveInputInfos = new ArrayList<>();
                    for (AttachmentSaveInputInfo attachmentSaveInputInfo:inputInfo.getAttachmentSaveInputInfo()
                         ) {
                        attachmentSaveInputInfo.setRelationId(planId);
                        attachmentSaveInputInfo.setRelationObject(AttachmentRelationEnum.PLAN.getCode());
                        attachmentSaveInputInfos.add(attachmentSaveInputInfo);
                    }
                    attachmentBeans = attachmentService.saveAttachmentList(attachmentSaveInputInfos);

                }
            }
            //装配返回结果
            res = KeyUnitTransformUtil.transform(planEntity, dicsMap, organizationNameMap);
            if( res != null ){
                res.setPlanDispatch(planDispatchBeanList);
                res.setAttachmentBean(attachmentBeans);
            }



            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveKeyUnitPlan", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveKeyUnitPlan", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.SAVE_PLAN_FAIL);
        }

    }


    /**
     * {@inheritDoc}
     *
     * @see PlanService#findPlanById(String)
     */
    @Transactional(readOnly = true)
    @Override
    public PlanBean findPlanById(String planId) {
        if (Strings.isBlank(planId)) {
            logService.infoLog(logger, "service", "findPlanById", "planId is null.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findPlanById", "service is started...");
            Long logStart = System.currentTimeMillis();

            PlanBean res =  null ;

            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();

            PlanEntity planEntity = accessor.getById(planId, PlanEntity.class);
            if (null != planEntity) {
                res = KeyUnitTransformUtil.transform(planEntity, dicsMap, organizationNameMap);
            }

            logService.infoLog(logger, "repository", "findPlanDispatchByPlanId( planId )", "repository is started...");
            Long start = System.currentTimeMillis();

            //根据预案id 获得 预案调派力量
            List<PlanDispatchEntity> planDispatchEntityList = planRepository.findPlanDispatchByPlanId(planId);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findPlanDispatchByPlanId( planId )", String.format("repository is finished,execute time is :%sms", end - start));

            if (null != planDispatchEntityList && planDispatchEntityList.size() > 0) {
                List<PlanDispatchBean> planDispatch = new ArrayList<>();
                for (PlanDispatchEntity planDispatchEntity : planDispatchEntityList) {
                    EquipmentVehicleBean vehicleBean = vehicleService.findVehicleCache( planDispatchEntity.getVehicleId() ) ;
                    PlanDispatchBean bean = KeyUnitTransformUtil.transform(planDispatchEntity, dicsMap, organizationNameMap , vehicleBean);
                    planDispatch.add(bean);
                }
                res.setPlanDispatch(planDispatch);
            }

            //获取附件
            List<AttachmentBean> attachmentBeans = attachmentService.findAttachment(null,planId);
            if (attachmentBeans != null && attachmentBeans.size()>0){
                res.setAttachmentBean(attachmentBeans);
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findPlanById", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findPlanById", String.format(" find plan fail by id : %s.", JSON.toJSONString(planId)), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_PLAN_FAIL);
        }

    }

    /**
     * {@inheritDoc}
     *
     * @see PlanService#findPlanByKeyUnitId(String)
     */
    @Transactional(readOnly = true)
    @Override
    public List<PlanBean> findPlanByKeyUnitId(String keyUnitId) {
        if (Strings.isBlank(keyUnitId)) {
            logService.infoLog(logger, "service", "findPlanByKeyUnitId", "keyUnitId is null.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findPlanByKeyUnitId", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<PlanBean> res = new ArrayList<>();

            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();

            logService.infoLog(logger, "repository", "findPlanByKeyUnitId( keyUnitId )", "repository is started...");
            Long start = System.currentTimeMillis();


            List<PlanEntity> planEntityList = planRepository.findPlanByKeyUnitId(keyUnitId);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findPlanByKeyUnitId( keyUnitId )", String.format("repository is finished,execute time is :%sms", end - start));


            if (null != planEntityList && planEntityList.size() > 0) {

                for (PlanEntity planEntity : planEntityList) {
                    PlanBean planBean = KeyUnitTransformUtil.transform(planEntity, dicsMap, organizationNameMap);
                    //根据预案id 获得 预案调派力量
                    logService.infoLog(logger, "repository", "findPlanDispatchByPlanId( planId )", "repository is started...");
                    Long startIn = System.currentTimeMillis();

                    List<PlanDispatchEntity> planDispatchEntityList = planRepository.findPlanDispatchByPlanId(planBean.getId());

                    Long endIn = System.currentTimeMillis();
                    logService.infoLog(logger, "repository", "findPlanDispatchByPlanId( planId )", String.format("repository is finished,execute time is :%sms", endIn - startIn));

                    List<PlanDispatchBean> planDispatch = new ArrayList<>();
                    if (null != planDispatchEntityList && planDispatchEntityList.size() > 0) {
                        for (PlanDispatchEntity planDispatchEntity : planDispatchEntityList) {
                            EquipmentVehicleBean vehicleBean = vehicleService.findVehicleCache( planDispatchEntity.getVehicleId() ) ;
                            PlanDispatchBean planDispatchBean = KeyUnitTransformUtil.transform(planDispatchEntity, dicsMap, organizationNameMap , vehicleBean );
                            planDispatch.add(planDispatchBean);
                        }
                        planBean.setPlanDispatch(planDispatch);
                    }
                    //获取附件
                    List<AttachmentBean> attachmentBeans = attachmentService.findAttachment(null,planBean.getId());
                    if (attachmentBeans != null && attachmentBeans.size()>0){
                        planBean.setAttachmentBean(attachmentBeans);
                    }


                    res.add(planBean);

                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findPlanByKeyUnitId", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findPlanByKeyUnitId", String.format(" find plan list  fail by keyUnitId : %s.", JSON.toJSONString(keyUnitId)), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_PLAN_FAIL);
        }

    }

    /**删除重点单位预案*/

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean deletePlan(List<String> ids) {
        if (ids == null || ids.size()<1){
            logService.infoLog(logger, "service", "deletePlan", "ids is null.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "deletePlan", "service is started...");
            Long logStart = System.currentTimeMillis();

            Boolean res = false;
            logService.infoLog(logger, "repository", "findPlanDispatchByPlanId( planId )", "repository is started...");
            Long startIn = System.currentTimeMillis();

            List<PlanEntity> planEntities =  new ArrayList<>( ) ;
            if( ids != null && ids.size() > 0 && ids.size() <= 900 ){
                planEntities = planRepository.findPlanIds( ids );
            }else if(  ids != null && ids.size()  > 900 ){
                int page = ( int ) Math.ceil( ids.size() / 900.0 ) ;
                for( int i = 0 ; i < page ; i++ ){
                    int startnum = i * 900 ;
                    int endnum = ( i + 1 ) * 900 ;
                    if( endnum > ids.size() ){
                        endnum = ids.size() ;
                    }
                    List<String>  batchIds = ids.subList( startnum , endnum ) ;
                    List<PlanEntity> bathEntityList  = planRepository.findPlanIds(  batchIds);
                    if (null != bathEntityList && bathEntityList.size() > 0) {
                        planEntities.addAll( bathEntityList ) ;
                    }
                }
            }

            Long endIn = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findPlanDispatchByPlanId( planId )", String.format("repository is finished,execute time is :%sms", endIn - startIn));

            if (planEntities != null && planEntities.size()>0){
                accessor.remove(planEntities,true);
                res = true;
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "deletePlan", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findPlanByKeyUnitId", " deletePlan  fail ", ex);
            throw new BasedataException(BasedataException.BasedataErrors.DELETE_PLAN_FAIL);
        }
    }

    /**
     * IncidentQueryInputInfo转码
     */
    private void decodePlanSaveInputInfo(PlanSaveInputInfo source) {
        if (source != null) {
            try {
                if (!StringUtils.isBlank(source.getPlanName())) {
                    source.setPlanName(URLDecoder.decode(source.getPlanName(), "UTF-8"));
                }
                if (!StringUtils.isBlank(source.getPlanDesc())) {
                    source.setPlanDesc(URLDecoder.decode(source.getPlanDesc(), "UTF-8"));
                }
                if (!StringUtils.isBlank(source.getRemarks())) {
                    source.setRemarks(URLDecoder.decode(source.getRemarks(), "UTF-8"));
                }
            } catch (Exception e) {
                throw new BasedataException(BasedataException.BasedataErrors.DECODE_FAIL);
            }
        }
    }


}
