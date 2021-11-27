package com.dscomm.iecs.basedata.utils;

import com.dscomm.iecs.basedata.dal.po.UnitEmergencyEntity;
import com.dscomm.iecs.basedata.graphql.typebean.UnitEmergencyBean;
import org.springframework.beans.BeanUtils;

import java.util.Map;

public class UnitEmergencyTransformUtil {
    /**
     * 描述：应急联动单位信息 转换
     *
     * @param source  机构实体PO
     * @param dicsMap 字典map
     * @return 机构BO
     */
    public static UnitEmergencyBean transform(UnitEmergencyEntity source, Map<String, Map<String, String>> dicsMap , Map<String, String> organizationIdCodeMap   ) {
        UnitEmergencyBean target = new UnitEmergencyBean();
        BeanUtils.copyProperties(source,target);
        return target;
    }
}
