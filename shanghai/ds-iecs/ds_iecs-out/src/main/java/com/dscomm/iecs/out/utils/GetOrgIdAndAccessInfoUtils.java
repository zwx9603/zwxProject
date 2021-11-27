package com.dscomm.iecs.out.utils;

import com.dscomm.iecs.basedata.dal.po.OrganizationEntity;
import com.dscomm.iecs.basedata.dal.po.SystemUserEntity;
import com.dscomm.iecs.out.dal.po.QueryAuditEntity;
import com.dscomm.iecs.out.enums.InterfaceTypeEnum;
import com.dscomm.iecs.out.exception.OutException;
import com.dscomm.iecs.out.graphql.typebean.AccessInfoVo;
import com.dscomm.iecs.out.graphql.typebean.IncidentOutBean;
import com.dscomm.iecs.out.graphql.typebean.ResultVo;
import org.mx.dal.service.GeneralAccessor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GetOrgIdAndAccessInfoUtils {
    /**
     * 获取组织
     * @param deptCode
     * @param accessor
     * @return
     */
    public  static  OrganizationEntity getOrganization( String deptCode ,GeneralAccessor accessor) {
        //根据机构code获取机构id
        List<GeneralAccessor.ConditionGroup> conditionOrgGroupList = new ArrayList<>();
        conditionOrgGroupList.add(GeneralAccessor.ConditionTuple.eq("organizationCode", deptCode));
        GeneralAccessor.ConditionGroup conditionOrgGroup = GeneralAccessor.ConditionGroup.and(conditionOrgGroupList);
        OrganizationEntity organizationEntity = accessor.findOne(conditionOrgGroup,
                OrganizationEntity.class);
        if (organizationEntity == null) {
            throw new OutException(OutException.AccetpErrors.ORG_NULL);
        }
        return organizationEntity;
    }

    /**
     * 获取用户
     * @param deptCode
     * @param username
     * @param accessor
     * @return
     */
    public  static  AccessInfoVo getAccessInfo( String deptCode ,String username ,GeneralAccessor accessor) {
        //数据接入信息
        AccessInfoVo accessInfoVo = new AccessInfoVo();
        List<GeneralAccessor.ConditionGroup> conditionGroupList = new ArrayList<>();
        conditionGroupList.add(GeneralAccessor.ConditionTuple.eq("userCode", username));
        GeneralAccessor.ConditionGroup conditionGroup = GeneralAccessor.ConditionGroup.and(conditionGroupList);
        SystemUserEntity agentAccountEntity = accessor.findOne(conditionGroup,
                SystemUserEntity.class);
        if (agentAccountEntity == null) {
            throw new OutException(OutException.AccetpErrors.LOGIN_FAIL);
        }
        accessInfoVo.setAccessPerson(agentAccountEntity.getName());//人员姓名
        accessInfoVo.setAccessPhone(null);
        accessInfoVo.setAccessVersion("V1");
        accessInfoVo.setDeptCode(deptCode);


        //获取用户所属机构信息
        List<GeneralAccessor.ConditionGroup> conditionOrgGroupList = new ArrayList<>();
        conditionOrgGroupList.add(GeneralAccessor.ConditionTuple.eq("idCode", agentAccountEntity.getOrganizationCode()));
        GeneralAccessor.ConditionGroup conditionOrgGroup = GeneralAccessor.ConditionGroup.and(conditionOrgGroupList);
        OrganizationEntity organizationEntity = accessor.findOne(conditionOrgGroup,
                OrganizationEntity.class);
        if (organizationEntity != null) {
            accessInfoVo.setAccessOrg(organizationEntity.getOrganizationName());
        }



        return accessInfoVo;
    }

    /**
     * 组装结果数据
     * @param resVo
     * @param accessInfoVo
     * @param dataList
     * @param interfaceType
     * @param <T>
     */
    public  static <T> void  getAfterQueryAudit(ResultVo resVo,AccessInfoVo accessInfoVo
            , List<T> dataList
            , String interfaceType
            ) {
        resVo.setAccessInfoVo(accessInfoVo);
        resVo.setDataList(dataList);
        resVo.setInterfaceType(interfaceType);
        resVo.setQueryParam(null);
    }

}
