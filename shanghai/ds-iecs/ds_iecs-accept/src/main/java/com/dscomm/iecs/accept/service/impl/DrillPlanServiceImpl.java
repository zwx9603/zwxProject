package com.dscomm.iecs.accept.service.impl;

import com.alibaba.fastjson.JSON;
import com.dscomm.iecs.accept.dal.po.DrillPlanDispatchEntity;
import com.dscomm.iecs.accept.dal.po.DrillPlanEntity;
import com.dscomm.iecs.accept.dal.repository.DrillPlanRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.DrillPlanQueryInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.DrillPlanSaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.DrillPlanBean;
import com.dscomm.iecs.accept.graphql.typebean.DrillPlanDispatchBean;
import com.dscomm.iecs.accept.service.DispatchPlanUsageRecordService;
import com.dscomm.iecs.accept.service.DrillPlanService;
import com.dscomm.iecs.accept.utils.transform.HandleDispatchTransformUtil;
import com.dscomm.iecs.agent.service.UserService;
import com.dscomm.iecs.base.graphql.typebean.PaginationBean;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.keydata.service.ServletService;
import org.apache.logging.log4j.util.Strings;
import org.mx.StringUtils;
import org.mx.dal.Pagination;
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
 * 描述：集合演练方案 服务类实现
 */
@Component("drillPlanServiceImpl")
public class DrillPlanServiceImpl implements DrillPlanService {
    private static final Logger logger = LoggerFactory.getLogger(DrillPlanServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private Environment env;
    private DictionaryService dictionaryService;
    private OrganizationService organizationService;
    private DrillPlanRepository drillPlanRepository;
    private UserService userService ;
    private ServletService servletService ;
    private DispatchPlanUsageRecordService dispatchPlanUsageRecordService;


    private List<String> dics;

    /**
     * 默认的构造函数
     */
    @Autowired
    public DrillPlanServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor,
                                Environment env, DictionaryService dictionaryService, OrganizationService organizationService,
                                DrillPlanRepository drillPlanRepository, UserService userService, ServletService servletService,

                                DispatchPlanUsageRecordService dispatchPlanUsageRecordService) {
        this.logService = logService;
        this.accessor = accessor;
        this.env = env;
        this.dictionaryService = dictionaryService;
        this.organizationService = organizationService;
        this.drillPlanRepository = drillPlanRepository;
        this.userService = userService ;
        this.servletService = servletService ;
        this.dispatchPlanUsageRecordService = dispatchPlanUsageRecordService;

        dics = new ArrayList<>(Arrays.asList("AJLX", "WLCLLX"    ));

    }


    /**
     * {@inheritDoc}
     *
     * @see DrillPlanService#findDrillPlanCondition(DrillPlanQueryInputInfo)
     */
    @Transactional(readOnly = true)
    @Override
    public PaginationBean<DrillPlanBean> findDrillPlanCondition(DrillPlanQueryInputInfo inputInfo) {
        if (null == inputInfo) {
            logService.infoLog(logger, "service", "findDrillPlanCondition", "DrillPlanQueryInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findDrillPlanCondition", "service is started...");
            Long logStart = System.currentTimeMillis();

            PaginationBean<DrillPlanBean> res = new PaginationBean<>();
            List<DrillPlanBean> beans = new ArrayList<>();

            //当前登录用户
            UserInfo userInfo = userService.getUserInfo() ;
            String organizationId = null ;
//            if( userInfo != null ){
//                organizationId = userInfo.getOrgId() ;
//            }

            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();

            logService.infoLog(logger, "repository", "findDrillPlanCondition", "repository is started...");
            Long start = System.currentTimeMillis();

            List<DrillPlanEntity> drillPlanEntityList = drillPlanRepository.findDrillPlanCondition( organizationId , inputInfo.getIncidentTypeCodes(), inputInfo.getKeyword(),
                    inputInfo.getWhetherEnableStatus() , inputInfo.getWhetherEnableTime() , servletService.getSystemTime() ,
                    false, inputInfo.getPagination().getPage(), inputInfo.getPagination().getSize());

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findDrillPlanCondition", String.format("repository is finished,execute time is :%sms", end - start));
            //查询历史使用记录
            Map<String, Integer> map = dispatchPlanUsageRecordService.countDispatchPlanUsageRecord();
            if (drillPlanEntityList != null && drillPlanEntityList.size() > 0) {
                for (DrillPlanEntity entity : drillPlanEntityList) {
                    DrillPlanBean bean = HandleDispatchTransformUtil.transform(entity, dicsMap, organizationNameMap);
                    if (map.containsKey(bean.getId())){
                        bean.setUsageTimes(map.get(bean.getId()));
                    }
                    beans.add(bean);
                }
            }

            logService.infoLog(logger, "repository", "findDrillPlanConditionTotal", "repository is started...");
            Long countStart = System.currentTimeMillis();

            Integer total = drillPlanRepository.findDrillPlanConditionTotal(organizationId , inputInfo.getIncidentTypeCodes(), inputInfo.getKeyword(),
                    inputInfo.getWhetherEnableStatus() , inputInfo.getWhetherEnableTime() , servletService.getSystemTime() );

            Long countEnd = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findDrillPlanConditionTotal", String.format("repository is finished,execute time is :%sms", countEnd - countStart));

            Pagination pagination = new Pagination();
            pagination.setPage(inputInfo.getPagination().getPage());
            pagination.setSize(inputInfo.getPagination().getSize());
            pagination.setTotal(total);

            if (inputInfo.getPagination()!=null&&inputInfo.getWhetherPage()){
                int i=0,j=0;
                i=(inputInfo.getPagination().getPage()-1)*inputInfo.getPagination().getSize();
                j=inputInfo.getPagination().getPage()*inputInfo.getPagination().getSize();
                if(j>beans.size()){
                    j=beans.size();
                }
                beans=beans.subList(i,j);
            }
            res.setPagination(pagination);
            res.setList(beans);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findDrillPlanCondition", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findDrillPlanCondition", String.format(" execution error"), ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_DRILL_PLAN_FAIL);
        }

    }


    /**
     * {@inheritDoc}
     *
     * @see DrillPlanService#saveDrillPlan(DrillPlanSaveInputInfo)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public DrillPlanBean saveDrillPlan(DrillPlanSaveInputInfo inputInfo) {
        if (inputInfo == null) {
            logService.infoLog(logger, "service", "saveDrillPlan", "DrillPlanSaveInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveDrillPlan", "service is started...");
            Long logStart = System.currentTimeMillis();

            decodeDrillPlanSaveInputInfo(inputInfo);//URLDecoder inputInfo转码

            DrillPlanBean res = null;
            List<DrillPlanDispatchBean> drillPlanDispatchBeans = new ArrayList<>();


            //获得当前操作用户
            UserInfo userInfo = userService.getUserInfo() ;

            //保存 集合演练方案
            DrillPlanEntity drillPlanEntity = HandleDispatchTransformUtil.transform(inputInfo);
            if( userInfo != null ){
                if( Strings.isBlank( drillPlanEntity.getMakeOrganizationId() )){
                    drillPlanEntity.setMakeOrganizationId( userInfo.getOrgId() );
                }
                if( Strings.isBlank( drillPlanEntity.getMakePersonId()  )){
                    drillPlanEntity.setMakePersonId( userInfo.getAccount() );
                }
                if( Strings.isBlank( drillPlanEntity.getMakePersonName() )){
                    drillPlanEntity.setMakePersonName( userInfo.getPersonName() );
                }
            }
            if( drillPlanEntity.getMakeTime() == null  ){
                drillPlanEntity.setMakeTime( servletService.getSystemTime()  );
            }
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();

            if (drillPlanEntity != null) {
                logService.infoLog(logger, "repository", "save(dbDrillPlanEntity)", "repository is started...");
                Long start = System.currentTimeMillis();

                accessor.save(drillPlanEntity);

                Long end = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbDrillPlanEntity)", String.format("repository is finished,execute time is :%sms", end - start));

                //集合演练方案id
                String drillPlanId = drillPlanEntity.getId();

                //删除某个演练方案的历史数据
                logService.infoLog(logger, "repository", "remove(dbDrillPlanEntity)", "repository is started...");
                Long startDrillPlanId = System.currentTimeMillis();

                drillPlanRepository.removeDrillPlanDispatchByDrillPlanId( drillPlanId );

                Long endDrillPlanId = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "remove(dbDrillPlanEntity)", String.format("repository is finished,execute time is :%sms", endDrillPlanId - startDrillPlanId ));



                //保存集合演练方案调度
                List<DrillPlanDispatchEntity> drillPlanDispatchEntities = HandleDispatchTransformUtil.transform(inputInfo, drillPlanId);

                if (drillPlanDispatchEntities != null && drillPlanDispatchEntities.size() > 0) {
                    logService.infoLog(logger, "repository", "save(dbDrillPlanDispatchEntityList)", "repository is started...");
                    Long startPlanDispatch = System.currentTimeMillis();

                    accessor.save(drillPlanDispatchEntities);

                    Long endPlanDispatch = System.currentTimeMillis();
                    logService.infoLog(logger, "repository", "save(dbDrillPlanDispatchEntityList)", String.format("repository is finished,execute time is :%sms", endPlanDispatch - startPlanDispatch));

                    for (DrillPlanDispatchEntity drillPlanDispatchEntity : drillPlanDispatchEntities) {
                        DrillPlanDispatchBean bean = HandleDispatchTransformUtil.transform(drillPlanDispatchEntity, dicsMap, organizationNameMap);
                        drillPlanDispatchBeans.add(bean);
                    }
                }
            }
            //装配返回结果
            res = HandleDispatchTransformUtil.transform(drillPlanEntity, dicsMap, organizationNameMap);
            if (res != null) {
                res.setDrillPlanDispatchBeans(drillPlanDispatchBeans);
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveDrillPlan", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveDrillPlan", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DRILL_PLAN_FAIL);
        }

    }


    /**
     * {@inheritDoc}
     *
     * @see DrillPlanService#findDrillPlanById(String)
     */
    @Transactional(readOnly = true)
    @Override
    public DrillPlanBean findDrillPlanById(String id) {
        if (Strings.isBlank(id)) {
            logService.infoLog(logger, "service", "findDrillPlanById", "id is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findDrillPlanById", "service is started...");
            Long logStart = System.currentTimeMillis();

            DrillPlanBean res = null;

            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();

            DrillPlanEntity planEntity = accessor.getById(id, DrillPlanEntity.class);
            if (null != planEntity) {
                res = HandleDispatchTransformUtil.transform(planEntity, dicsMap, organizationNameMap);
            }

            logService.infoLog(logger, "repository", "findDrillPlanDispatchByDrillPlanId( drillPlanId )", "repository is started...");
            Long start = System.currentTimeMillis();

            //根据集合演练方案id 获得 集合演练方案调度
            List<DrillPlanDispatchEntity> drillPlanDispatchEntities = drillPlanRepository.findDrillPlanDispatchByDrillPlanId(id);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findDrillPlanDispatchByDrillPlanId( drillPlanId )", String.format("repository is finished,execute time is :%sms", end - start));

            if (null != drillPlanDispatchEntities && drillPlanDispatchEntities.size() > 0) {
                List<DrillPlanDispatchBean> drillPlanDispatchBeans = new ArrayList<>();
                for (DrillPlanDispatchEntity entity : drillPlanDispatchEntities) {
                    DrillPlanDispatchBean bean = HandleDispatchTransformUtil.transform(entity, dicsMap, organizationNameMap);
                    drillPlanDispatchBeans.add(bean);
                }
                res.setDrillPlanDispatchBeans(drillPlanDispatchBeans);
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findDrillPlanById", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findDrillPlanById", String.format(" find plan fail by id : %s.", JSON.toJSONString(id)), ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_DRILL_PLAN_FAIL);
        }

    }

    /**
     * {@inheritDoc}
     *
     * @see DrillPlanService#removeDrillPlan(String)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean removeDrillPlan(String id) {
        if (Strings.isBlank(id)) {
            logService.infoLog(logger, "service", "removeDrillPlan", "id is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "removeDrillPlan", "service is started...");
            Long logStart = System.currentTimeMillis();

            Boolean res = true;


            DrillPlanEntity drillPlanEntity = accessor.getById(id, DrillPlanEntity.class);

            if (null != drillPlanEntity) {

                drillPlanEntity.setValid(false);

                logService.infoLog(logger, "repository", "remove(dbDrillPlanEntity)", "repository is started...");
                Long start = System.currentTimeMillis();

                accessor.save(drillPlanEntity);
                drillPlanRepository.removeDrillPlanDispatchByDrillPlanId(id);

                Long end = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "remove(dbDrillPlanEntity)", String.format("repository is finished,execute time is :%sms", end - start));

            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "removeDrillPlan", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "removeDrillPlan", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.REMOVE_DRILL_PLAN_FAIL);
        }

    }

    /**DrillPlanSaveInputInfo转码*/
    private void decodeDrillPlanSaveInputInfo(DrillPlanSaveInputInfo inputInfo){
        try {
            if (!StringUtils.isBlank(inputInfo.getDrillPlanName())){
                inputInfo.setDrillPlanName(URLDecoder.decode(inputInfo.getDrillPlanName(),"UTF-8"));
            }
            if (!StringUtils.isBlank(inputInfo.getDrillPlanContent())){
                inputInfo.setDrillPlanContent(URLDecoder.decode(inputInfo.getDrillPlanContent(),"UTF-8"));
            }
        }catch (Exception e){
            logService.erorLog(logger, "service", "decodeDrillPlanSaveInputInfo", "execution error", e);
            throw new AcceptException(AcceptException.AccetpErrors.DECODE_FAIL);
        }
    }


}
