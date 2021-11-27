package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.inputbean.DocumentSaveInputInfoSH;
import com.dscomm.iecs.accept.graphql.inputbean.IncidentGuidanceInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.DocumentBeanSH;

import java.util.List;

/**
 * 描述：警情文书 服务
 */
public interface DocumentServiceSH {

    /**
     * 根据案件id(警情id)获得文书信息
     * @param incidentId  警情id
     * @return 文书信息列表
     */
    List<DocumentBeanSH> findDocumentSH(String incidentId, String organizationId, String type);

    /**
     * 保存/修改警情文书
     *
     * @param inputInfo 文书信息参数
     * @return 保存结果
     */
    DocumentBeanSH saveDocumentSH(DocumentSaveInputInfoSH inputInfo);



    /**
     * 保存警情引导文书
     * @param inputInfo
     * @return
     */
    DocumentBeanSH saveIncidentGuidance (IncidentGuidanceInputInfo inputInfo);


}
