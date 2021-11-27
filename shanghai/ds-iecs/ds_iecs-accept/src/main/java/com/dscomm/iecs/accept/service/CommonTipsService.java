package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.inputbean.CommonTipsInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.CommonTipsBean;

import java.util.List;

/**
 * 描述：接警处警提示信息 服务
 */
public interface CommonTipsService {

    /**
     * 根据案件等级和案件类型获取接警处警提示信息
     *
     * @param incidentTypeCode  案件类型
     * @param disposalObjectCode 案件处置对象
     * @return 接警处警提示信息列表
     */
    CommonTipsBean findCommonTips(String incidentTypeCode, String disposalObjectCode );

    CommonTipsBean saveCommonTips(CommonTipsInputInfo inputInfo);

    Boolean removeCommonTips( List<String> ids);

    CommonTipsBean findById(String id);

    List<CommonTipsBean> findAll();


}
