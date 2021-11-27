package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.fireinputbean.ActionSummarySaveInputInfo;
import com.dscomm.iecs.accept.graphql.firetypebean.ActionSummaryBean;

import java.util.List;

public interface ActionSummaryService {

    //根据案件id查询战评总结
    List<ActionSummaryBean> findActionSummary ( String incidentId  );

    //根据id查询战评总结
    ActionSummaryBean  findActionSummaryBeanById ( String id );

    //根据id删除战评总结
    Boolean   removeActionSummary(String id);

    //保存战评总结
    ActionSummaryBean  saveActionSummary(ActionSummarySaveInputInfo queryBean);
}
