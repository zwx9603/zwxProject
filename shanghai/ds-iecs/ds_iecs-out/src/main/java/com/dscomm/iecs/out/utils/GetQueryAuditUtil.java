package com.dscomm.iecs.out.utils;

import com.dscomm.iecs.basedata.dal.po.OrganizationEntity;
import com.dscomm.iecs.basedata.dal.po.SystemUserEntity;
import com.dscomm.iecs.out.dal.po.QueryAuditEntity;
import com.dscomm.iecs.out.enums.InterfaceTypeEnum;
import com.dscomm.iecs.out.exception.OutException;
import org.mx.dal.service.GeneralAccessor;

import java.util.ArrayList;
import java.util.List;

public class GetQueryAuditUtil {
    /**
     * 查询数据之前的组装
     * @param username
     * @param interfaceName
     * @param tableName
     * @param accessor
     * @param queryAuditEntity
     * @param logStart
     */
    public  static void  getBeforeQueryAudit(String username , String interfaceName
            , String tableName
            , GeneralAccessor accessor, QueryAuditEntity queryAuditEntity
            , Long logStart) {
        //获取用户信息
        List<GeneralAccessor.ConditionGroup> conditionUserGroupList = new ArrayList<>();
        conditionUserGroupList.add(GeneralAccessor.ConditionTuple.eq("userCode", username));
        GeneralAccessor.ConditionGroup conditionUserGroup =
                GeneralAccessor.ConditionGroup.and(conditionUserGroupList);
        SystemUserEntity agentAccountEntity = accessor.findOne(conditionUserGroup,
                SystemUserEntity.class);

        //queryAuditEntity.setIp(ip);
        queryAuditEntity.setInterfaceName(interfaceName);
        queryAuditEntity.setTableName(tableName);
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
        queryAuditEntity.setQueryStartTime(logStart);
    }

    /**
     * 查询数据之后或错误时组装
     * @param queryResult
     * @param dataNum
     * @param queryAuditEntity
     * @param logEnd
     */
    public  static void  getAfterQueryAudit(String queryResult
            , Integer dataNum
            , QueryAuditEntity queryAuditEntity
            , Long logEnd) {
        queryAuditEntity.setQueryResult(queryResult);//查询结果
        queryAuditEntity.setQueryEndTime(logEnd);//查询结束时间
        queryAuditEntity.setDataNum(dataNum);//查询数据量
        //查询耗时
        queryAuditEntity.setQueryTime(queryAuditEntity.getQueryEndTime() - queryAuditEntity.getQueryStartTime());
    }
}
