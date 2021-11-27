package com.dscomm.iecs.out.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.dscomm.iecs.accept.dal.po.IncidentEntity;
import com.dscomm.iecs.accept.graphql.inputbean.HierarchicalDispatchQueryInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.HierarchicalDispatchBean;
import com.dscomm.iecs.accept.service.HierarchicalDispatchService;
import com.dscomm.iecs.accept.service.PlanDispatchService;
import com.dscomm.iecs.base.graphql.typebean.PaginationBean;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.graphql.typebean.PlanDispatchBean;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.out.dal.po.QueryAuditEntity;
import com.dscomm.iecs.out.enums.InterfaceTypeEnum;
import com.dscomm.iecs.out.exception.OutException;
import com.dscomm.iecs.out.graphql.typebean.*;
import com.dscomm.iecs.out.service.AlarmJqcjDpTjfaService;
import com.dscomm.iecs.out.utils.GetQueryAuditUtil;
import org.apache.logging.log4j.util.Strings;
import org.mx.dal.service.GeneralAccessor;
import org.mx.spring.session.SessionDataStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author fanghuilin
 * @version 1.00
 * @Time 2021/4/27
 * @Describe
 */
@Component
public class AlarmJqcjDpTjfaServiceImpl implements AlarmJqcjDpTjfaService {
    private static final Logger logger = LoggerFactory.getLogger(AlarmJqcjDpTjfaServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private SessionDataStore dataStore;
    private DictionaryService dictionaryService;
    private OrganizationService organizationService;
    private PlanDispatchService planDispatchService;
    private HierarchicalDispatchService hierarchicalDispatchService;

    private List<String> dics;


    @Autowired
    public AlarmJqcjDpTjfaServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor,
                                      SessionDataStore dataStore,
                                      DictionaryService dictionaryService,
                                      OrganizationService organizationService,
                                      PlanDispatchService planDispatchService,
                                      HierarchicalDispatchService hierarchicalDispatchService) {
        this.logService = logService;
        this.accessor = accessor;

        this.dataStore = dataStore;
        this.organizationService = organizationService;
        this.dictionaryService = dictionaryService;
        this.planDispatchService = planDispatchService;
        this.hierarchicalDispatchService = hierarchicalDispatchService;

        dics = new ArrayList<>(Arrays.asList("AJLX", "CZDX", "AJDJ" ,"WLCLLX"  ));
    }

    @Override
    public List<AlarmJqcjDpTjfaBean> getAlarmJqcjDpTjfaListByTime(Long startTime, Long endTime, String username) {
        QueryAuditEntity queryAuditEntity = new QueryAuditEntity();
        try {
            logService.infoLog(logger, "service", "getAlarmJqwsListByTime", "service is started...");
            Long logStart = System.currentTimeMillis();
            GetQueryAuditUtil.getBeforeQueryAudit(username, InterfaceTypeEnum.JQWSXX.getCode()
                    , "JCJ_WSXX", accessor, queryAuditEntity, logStart);
            List<AlarmJqcjDpTjfaBean> res = getJqcjDpTjfaListByTime(startTime, endTime);
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "getAlarmJqwsListByTime", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            GetQueryAuditUtil.getAfterQueryAudit("success", res.size(), queryAuditEntity, logEnd);
            return res;

        } catch (Exception ex) {
            GetQueryAuditUtil.getAfterQueryAudit("fail", 0, queryAuditEntity, System.currentTimeMillis());
            logService.erorLog(logger, "service", "getAlarmJqwsListByTime", "execution error", ex);
            throw new OutException(OutException.AccetpErrors.FIND_DOC_RECORD_FAIL);
        } finally {
            saveAuditLog(queryAuditEntity);
        }

    }
    @Transactional(rollbackFor = Exception.class)
    public void saveAuditLog(QueryAuditEntity queryAuditEntity) {
        accessor.save(queryAuditEntity);

    }

    @Transactional(readOnly = true)
    public List<AlarmJqcjDpTjfaBean> getJqcjDpTjfaListByTime(Long startTime, Long endTime) {
        if (startTime == null || endTime == null) {
            logService.infoLog(logger, "service", "getAlarmThjlListByTime", "query time is Blank.");
            throw new OutException(OutException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "getAlarmThjlListByTime", "service is started...");
            Long logStart = System.currentTimeMillis();
            List<AlarmJqcjDpTjfaBean> res = new ArrayList<>();
            String deptCode= (String) dataStore.get().get("deptCode");
            /**
             * 获取警情列表
             */
            //创建一个list 存放ConditionGroup
            List<GeneralAccessor.ConditionGroup> conditionGroupList = new ArrayList<>();
            //创建一个大于等于条件  创建时间
            conditionGroupList.add(GeneralAccessor.ConditionTuple.gte("createdTime", startTime));//查询开始时间

            //创建一个小于等于条件  创建时间
            conditionGroupList.add(GeneralAccessor.ConditionTuple.lte("createdTime", endTime));//查询结束时间
            conditionGroupList.add(GeneralAccessor.ConditionTuple.eq("valid", 1));
            GeneralAccessor.ConditionGroup conditionGroup = GeneralAccessor.ConditionGroup.and(conditionGroupList);


            // 查询出所有需要用到的字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;

            List<IncidentEntity> entityList = accessor.find(conditionGroup, IncidentEntity.class);
            for (IncidentEntity entity:entityList
                 ) {
                AlarmJqcjDpTjfaBean bean = new AlarmJqcjDpTjfaBean();
                //调派依据
                DPTJFAYJBean dptjfayjBean = new DPTJFAYJBean();
                dptjfayjBean.setIncidentTypeCode(entity.getIncidentTypeCode());
                dptjfayjBean.setIncidentTypeName(dicsMap.get("AJLX").get(entity.getIncidentTypeCode()));
                dptjfayjBean.setDisposalObjectCode(entity.getDisposalObjectCode());
                dptjfayjBean.setDisposalObjectName(dicsMap.get("CZDX").get(entity.getDisposalObjectCode()));
                dptjfayjBean.setIncidentLevelCode(entity.getIncidentLevelCode());
                dptjfayjBean.setIncidentLevelName(dicsMap.get("AJDJ").get(entity.getIncidentLevelCode()));
                Date dt = new Date();
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                bean.setCreateTime(sdf.format(entity.getCreatedTime()));
                bean.setDeptCode(deptCode);
                bean.setUid(entity.getId());
                bean.setJq_tywysbm(entity.getId());
                bean.setJqcjdptjfa_tywysbm(entity.getId());
                //警情调派 推荐
                // 方案  依据
                //判断是否是重点单位
                if (Strings.isNotBlank(entity.getKeyUnit())){
                    dptjfayjBean.setWhetherKeyUnit(true);
                    List<PlanDispatchBean> dispatchBeanList =
                            planDispatchService.findPlanDispatchByKeyUnitId(entity.getKeyUnit());
                    if (dispatchBeanList!=null&&dispatchBeanList.size()>0) {
                        bean.setJqcjdptjfa(dispatchBeanList.toString());
                    }

                }else {
                    //等级调度
                    HierarchicalDispatchQueryInputInfo queryInputInfo = new HierarchicalDispatchQueryInputInfo();
                    queryInputInfo.setSquadronOrganizationId(entity.getSquadronOrganizationId());
                    queryInputInfo.setDisposalObjectCode(entity.getDisposalObjectCode());
                    queryInputInfo.setIncidentLevelCode(entity.getIncidentLevelCode());
                    queryInputInfo.setIncidentTypeCode(entity.getIncidentTypeCode());
                    PaginationBean<HierarchicalDispatchBean> paginationBean =
                            hierarchicalDispatchService.findHierarchicalDispatchCondition(queryInputInfo);
                    if (paginationBean.getList()!=null&&paginationBean.getList().size()>0){
                        bean.setJqcjdptjfa(paginationBean.getList().toString());
                    }

                }
                res.add(bean);
            }
//            for (int i = 0; i < 5; i++) {
//                AlarmJqcjDpTjfaBean alarmJqcjDpTjfaBean = new AlarmJqcjDpTjfaBean();
//
//                String uuid0 = UUID.randomUUID().toString();
//                alarmJqcjDpTjfaBean.setUid(uuid0);
//
//
//                String oneHoursAgoTime="";
//                Date dt = new Date();
//                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                Calendar rightNow = Calendar.getInstance();
//                rightNow.setTime(dt);
//                rightNow.add(Calendar.HOUR, -i+1);
//                Date dt1=rightNow.getTime();
//                oneHoursAgoTime = sdf.format(dt1);
//                alarmJqcjDpTjfaBean.setCreateTime(oneHoursAgoTime);
//
//
//
//                String uuid1 = UUID.randomUUID().toString();
//                alarmJqcjDpTjfaBean.setJqcjdptjfaTywysbm(uuid1);
//                String uuid2 = UUID.randomUUID().toString();
//                alarmJqcjDpTjfaBean.setJqTywysbm(uuid2);
//
//                AlarmJqcjDpTjfaInfoInput alarmJqcjDpTjfaInfoInput = new AlarmJqcjDpTjfaInfoInput();
//                String uuid3 = UUID.randomUUID().toString();
//                alarmJqcjDpTjfaInfoInput.setXfjyjgTywysbm(uuid3);
//                alarmJqcjDpTjfaInfoInput.setXfjyjgjl((i+1)*100 + "");
//                alarmJqcjDpTjfaInfoInput.setXfjyjgtjhldsl(i+10 +"");
//
//                alarmJqcjDpTjfaInfoInput.setXfjyjgyjdcsj(oneHoursAgoTime);
//                List<DpryBean> dpryList = new ArrayList<>();
//
//                for(int j=0;j<3;j++){
//                    DpryBean dpryBean = new DpryBean();
//                    String uuid4 = UUID.randomUUID().toString();
//                    dpryBean.setXfjyryTywysbm(uuid4);
//                    dpryBean.setJqcjdpryztlbdm(UUID.randomUUID().toString());
//                    dpryBean.setJqcjdpryZtbm(UUID.randomUUID().toString());
//                    dpryBean.setJqcjdpryGgyj(UUID.randomUUID().toString());
//                    dpryList.add(dpryBean);
//                }
//                alarmJqcjDpTjfaInfoInput.setDpryList(dpryList);
//                if(i == 0){
//                    alarmJqcjDpTjfaBean.setJqcjdpTjfayj("火警救援预案");
//                }
//                if(i == 1){
//                    alarmJqcjDpTjfaBean.setJqcjdpTjfayj("抢险救援预案");
//                }
//                if(i == 2){
//                    alarmJqcjDpTjfaBean.setJqcjdpTjfayj("台风救援预案");
//                }
//                if(i == 3){
//                    alarmJqcjDpTjfaBean.setJqcjdpTjfayj("地震救援预案");
//                }
//                if(i == 4){
//                    alarmJqcjDpTjfaBean.setJqcjdpTjfayj("海啸救援预案");
//                }
//                List<AlarmJqcjDpTjfaInfoInput> AlarmJqcjDpTjfaList = new ArrayList<>();
//                AlarmJqcjDpTjfaList.add(alarmJqcjDpTjfaInfoInput);
//                Object obj = JSONArray.toJSON(AlarmJqcjDpTjfaList);
//                //String aaa = "" + obj ;
//                String json = obj.toString();
//                alarmJqcjDpTjfaBean.setJqcjdptjfa(json);
//                alarmJqcjDpTjfaBean.setDeptCode(deptCode);
//                res.add(alarmJqcjDpTjfaBean);
//            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "getAlarmThjlListByTime", String.format("service is finished,execute time is :%sms", logEnd - logStart));


            return res;

        } catch (Exception ex) {

            logService.erorLog(logger, "service", "getAlarmThjlListByTime", "execution error", ex);
            throw new OutException(OutException.AccetpErrors.FIND_TEL_RECORD_FAIL);
        }
    }
}
