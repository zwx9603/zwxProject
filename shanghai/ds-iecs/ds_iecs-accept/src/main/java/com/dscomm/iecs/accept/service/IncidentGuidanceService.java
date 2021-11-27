package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.typebean.DocumentBean;

import java.util.List;

/**
 * @author xushaohuang
 * @date 2021-05-20 13:23
 * @description 警情引导接口
 */
public interface IncidentGuidanceService {

    /**
     * 根据案件id获取文书和附件信息
     * @param incidentId
     * @return
     */
    List<DocumentBean> findIncidentGuidanceInfo (String incidentId) ;
}
