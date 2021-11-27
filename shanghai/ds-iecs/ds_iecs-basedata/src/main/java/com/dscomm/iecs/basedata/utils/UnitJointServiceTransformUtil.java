package com.dscomm.iecs.basedata.utils;

import com.dscomm.iecs.basedata.dal.po.UnitJointServiceEntity;
import com.dscomm.iecs.basedata.graphql.typebean.UnitJointServiceBean;
import org.springframework.beans.BeanUtils;

import java.util.Map;

public class UnitJointServiceTransformUtil {
    /**
     * 描述：联动单位信息 转换
     *
     * @param source  机构实体PO
     * @param dicsMap 字典map
     * @return 机构BO
     */
    public static UnitJointServiceBean transform(UnitJointServiceEntity source, Map<String, Map<String, String>> dicsMap , Map<String, String> organizationIdCodeMap   ) {
        UnitJointServiceBean target = new UnitJointServiceBean();
        BeanUtils.copyProperties(source,target);
        return target;
    }

}
