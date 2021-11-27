package com.dscomm.iecs.accept.utils.transform;

import com.dscomm.iecs.accept.dal.po.MajorIncidentRuleEntity;
import com.dscomm.iecs.accept.enums.MajorIncidentRuleEnum;
import com.dscomm.iecs.accept.graphql.firetypebean.MajorIncidentRuleBean;
import org.mx.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.Map;

public class MajorIncidentRuleTransformUtil {
    public static MajorIncidentRuleBean transform(MajorIncidentRuleEntity source, Map<String, Map<String, String>> dicsMap , Map<String, String> organizationIdCodeMap   ) {
        MajorIncidentRuleBean target = new MajorIncidentRuleBean();
        BeanUtils.copyProperties(source,target);
        if(!StringUtils.isBlank(source.getIncidentType())&&dicsMap.get("AJLX")!=null){
            target.setIncidentTypeName(dicsMap.get("AJLX").get(source.getIncidentType()));
        }


        MajorIncidentRuleEnum[] majorIncidentRuleEnumList = MajorIncidentRuleEnum.values ();
        for (MajorIncidentRuleEnum majorIncidentRuleEnum:majorIncidentRuleEnumList
             ) {
            if (majorIncidentRuleEnum.getCode().equals(source.getRuleType())) {
                target.setRuleTypeName(majorIncidentRuleEnum.getName());
            }
        }

        return target;
    }
}
