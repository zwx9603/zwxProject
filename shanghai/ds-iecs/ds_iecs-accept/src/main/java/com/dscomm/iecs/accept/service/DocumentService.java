package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.inputbean.DocumentSaveInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.IncidentGuidanceInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.DocumentBean;
import com.dscomm.iecs.basedata.graphql.typebean.OrganizationBean;

import java.util.List;

/**
 * 描述：警情文书 服务
 */
public interface DocumentService {

    /**
     * 根据案件id(警情id)获得文书信息
     * @param incidentId  警情id
     * @return 文书信息列表
     */
    List<DocumentBean> findDocument(String incidentId , String organizationId , String type );

    /**
     * 保存/修改警情文书
     *
     * @param inputInfo 文书信息参数
     * @return 保存结果
     */
    DocumentBean saveDocument(DocumentSaveInputInfo inputInfo);



    /**
     * 根据案件id 获得 文书参与单位信息
     * @param incidentId  警情id
     * @return 文书信息列表
     */
    List<OrganizationBean> findDocumentOrganization(String incidentId  );


    /**
     * 保存警情引导文书
     * @param inputInfo
     * @return
     */
    DocumentBean saveIncidentGuidance ( IncidentGuidanceInputInfo inputInfo);
}
