package com.dscomm.iecs.out.service.impl;

import com.dscomm.iecs.accept.dal.po.AcceptanceEntity;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.dal.po.OrganizationEntity;
import com.dscomm.iecs.basedata.dal.po.SystemUserEntity;
import com.dscomm.iecs.out.dal.po.QueryAuditEntity;
import com.dscomm.iecs.out.enums.InterfaceTypeEnum;
import com.dscomm.iecs.out.exception.OutException;
import com.dscomm.iecs.out.graphql.typebean.AlarmRecordBean;
import com.dscomm.iecs.out.service.AcceptanceOutService;
import com.dscomm.iecs.out.utils.GetQueryAuditUtil;
import com.dscomm.iecs.out.utils.TransformUtil;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/4/19 13:53
 * @Describe
 */
@Component
public class AcceptanceOutServiceImpl implements AcceptanceOutService {

    private static final Logger logger = LoggerFactory.getLogger(AcceptanceOutServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;


    @Autowired
    public AcceptanceOutServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor) {
        this.logService = logService;
        this.accessor = accessor;

    }


    /**
     * 根据时间获取报警记录列表
     * 查询立案单位registerOrganizationId 下的全部记录
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    @Override
    public List<AlarmRecordBean> getAlarmRecordListByTime(Long startTime, Long endTime, String username) {
        QueryAuditEntity queryAuditEntity = new QueryAuditEntity();
        try {
            logService.infoLog(logger, "service", "getAlarmRecordListByTime", "service is started...");
            Long logStart = System.currentTimeMillis();

           /* //获取用户信息
            List<GeneralAccessor.ConditionGroup> conditionUserGroupList = new ArrayList<>();
            conditionUserGroupList.add(GeneralAccessor.ConditionTuple.eq("userCode", username));
            GeneralAccessor.ConditionGroup conditionUserGroup =
                    GeneralAccessor.ConditionGroup.and(conditionUserGroupList);
            SystemUserEntity agentAccountEntity = accessor.findOne(conditionUserGroup,
                    SystemUserEntity.class);

            //queryAuditEntity.setIp(ip);
            queryAuditEntity.setInterfaceName(InterfaceTypeEnum.BJJL.getCode());
            queryAuditEntity.setTableName("JCJ_SLD");
            queryAuditEntity.setAccount(agentAccountEntity.getName());//账号
            //获取用户所属机构信息
            List<GeneralAccessor.ConditionGroup> conditionOrgGroupList = new ArrayList<>();
            conditionOrgGroupList.add(GeneralAccessor.ConditionTuple.eq("idCode", agentAccountEntity.getOrganizationCode()));
            GeneralAccessor.ConditionGroup conditionOrgGroup = GeneralAccessor.ConditionGroup.and(conditionOrgGroupList);
            OrganizationEntity organizationEntity = accessor.findOne(conditionOrgGroup,
                    OrganizationEntity.class);
            if (organizationEntity != null) {
                queryAuditEntity.setOrgName(organizationEntity.getOrganizationName());
            }

            queryAuditEntity.setQueryStartTime(logStart);*/

            GetQueryAuditUtil.getBeforeQueryAudit(username,InterfaceTypeEnum.BJJL.getCode()
            ,"JCJ_SLD",accessor,queryAuditEntity,logStart);

            List<AlarmRecordBean> res = getAlarmRecordListByTime(startTime,endTime);
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "getAlarmRecordListByTime", String.format("service is finished,execute time is :%sms", logEnd - logStart));

           /* queryAuditEntity.setQueryResult("success");//查询结果
            queryAuditEntity.setQueryEndTime(logEnd);//查询结束时间
            queryAuditEntity.setDataNum(res.size());//查询数据量
            //查询耗时
            queryAuditEntity.setQueryTime(queryAuditEntity.getQueryEndTime() - queryAuditEntity.getQueryStartTime());*/
            GetQueryAuditUtil.getAfterQueryAudit("success",res.size(),queryAuditEntity,logEnd);
            return res;

        } catch (Exception ex) {
            /*queryAuditEntity.setQueryResult("fail");
            queryAuditEntity.setDataNum(0);//查询数据量
            queryAuditEntity.setQueryEndTime(System.currentTimeMillis());
            queryAuditEntity.setQueryTime(queryAuditEntity.getQueryEndTime() - queryAuditEntity.getQueryStartTime());*/
            GetQueryAuditUtil.getAfterQueryAudit("fail",0,queryAuditEntity,System.currentTimeMillis());
            logService.erorLog(logger, "service", "getAlarmRecordListByTime", "execution error", ex);
            throw new OutException(OutException.AccetpErrors.FIND_ALARM_RECORD_FAIL);
        } finally {
            saveAuditLog(queryAuditEntity);
        }

    }


    @Transactional(rollbackFor = Exception.class)
    public void saveAuditLog(QueryAuditEntity queryAuditEntity ){
        accessor.save(queryAuditEntity);

    }


    @Transactional(readOnly = true)
    public List<AlarmRecordBean> getAlarmRecordListByTime(Long startTime, Long endTime){
        if (startTime == null || endTime == null) {
            logService.infoLog(logger, "service", "getAlarmRecordListByTime", "query time is Blank.");
            throw new OutException(OutException.AccetpErrors.DATA_NULL);
        }
        try {

            logService.infoLog(logger, "service", "getAlarmRecordListByTime", "service is started...");
            Long logStart = System.currentTimeMillis();

            //创建一个list 存放ConditionGroup
            List<GeneralAccessor.ConditionGroup> conditionGroupList = new ArrayList<>();
            //创建一个大于等于条件  创建时间
            conditionGroupList.add(GeneralAccessor.ConditionTuple.gte("createdTime", startTime));//查询开始时间

            //创建一个小于等于条件  创建时间
            conditionGroupList.add(GeneralAccessor.ConditionTuple.lte("createdTime", endTime));//查询结束时间

            conditionGroupList.add(GeneralAccessor.ConditionTuple.eq("valid", 1));

            //创建机构查询条件  立案机构
            //conditionGroupList.add(GeneralAccessor.ConditionTuple.eq("registerOrganizationId", orgId));//查询立案机构
            GeneralAccessor.ConditionGroup conditionGroup = GeneralAccessor.ConditionGroup.and(conditionGroupList);
            Long start = System.currentTimeMillis();

            List<AcceptanceEntity> entityList = accessor.find(conditionGroup, AcceptanceEntity.class);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "getAlarmRecordListByTime", String.format("repository is finished,execute time is :%sms", end - start));

            List<AlarmRecordBean> res = new ArrayList<>();

            if (entityList != null && entityList.size() > 0) {
                for (AcceptanceEntity entity : entityList
                ) {
                    AlarmRecordBean bean = TransformUtil.transform(entity, logService, logger);
                    res.add(bean);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "getAlarmRecordListByTime", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;

        } catch (Exception ex) {
            logService.erorLog(logger, "service", "getAlarmRecordListByTime", "execution error", ex);
            throw new OutException(OutException.AccetpErrors.FIND_ALARM_RECORD_FAIL);
        }
    }




}
