package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.fireinputbean.MajorIncidentRuleInputInfo;
import com.dscomm.iecs.accept.graphql.fireinputbean.MajorIncidentRuleQueryInputInfo;
import com.dscomm.iecs.accept.graphql.firetypebean.MajorIncidentRuleBean;
import com.dscomm.iecs.base.graphql.typebean.PaginationBean;

import java.util.List;

/**
 * 描述：重大案件判定规则
 */
public interface MajorIncidentRuleService {
    MajorIncidentRuleBean saveMajorIncidentRule(MajorIncidentRuleInputInfo inputInfo);

    MajorIncidentRuleBean updateMajorIncidentRule(MajorIncidentRuleInputInfo inputInfo);

    Boolean deleteMajorIncidentRule(List<String> ids);

    PaginationBean<MajorIncidentRuleBean> findMajorIncidentRule(MajorIncidentRuleQueryInputInfo queryBean);
}
