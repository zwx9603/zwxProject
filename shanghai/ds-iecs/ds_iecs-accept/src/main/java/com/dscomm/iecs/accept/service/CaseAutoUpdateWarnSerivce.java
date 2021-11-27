package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.inputbean.CaseAutoUpdateWarnInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.CaseAutoUpdateWarnQueryInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.CaseAutoUpdateWarnBean;

import java.util.List;

/**
 * 案件等级自动升级提醒
 */
public interface CaseAutoUpdateWarnSerivce {

    CaseAutoUpdateWarnBean saveCaseAutoUpdateWarn(CaseAutoUpdateWarnInputInfo inputInfo);

    Boolean removeCaseAutoUpdateWarn(String id);

    CaseAutoUpdateWarnBean findCaseAutoUpdateWarnById(String id);

    List<CaseAutoUpdateWarnBean> findCaseAutoUpdateWarn();

    List<CaseAutoUpdateWarnBean> findCaseAutoUpdateWarnCondition(  CaseAutoUpdateWarnQueryInputInfo queryBean );


    /**
     * 根据案件类型  提醒类型 获得最新提醒信息
     */
    CaseAutoUpdateWarnBean findCaseAutoUpdateWarnByCaseType( String  caseType ,  String warnType ) ;


}
