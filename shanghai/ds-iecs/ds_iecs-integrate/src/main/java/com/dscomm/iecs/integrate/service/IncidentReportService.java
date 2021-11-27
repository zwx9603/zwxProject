package com.dscomm.iecs.integrate.service;

import com.dscomm.iecs.integrate.restful.vo.CaseInfoDTO;
import com.dscomm.iecs.integrate.restful.vo.CaseMergeDTO;
import com.dscomm.iecs.integrate.restful.vo.CaseStatDTO;

/**
 * 警情上报一体化服务
 * */

public interface IncidentReportService {

    /**
     * 新增警情上报
     * */
    Boolean newCase(CaseInfoDTO caseInfoDTO);

    /**
     * 修改警情上报
     * */
    Boolean modifyCase(CaseInfoDTO caseInfoDTO);

    /**
     * 案件状态修改上报
     * */
    Boolean caseStat(CaseStatDTO caseStatDTO);


    /**
     * 案件合并上报
     * */
    Boolean caseMerger(CaseMergeDTO caseMergeDTO);

}
