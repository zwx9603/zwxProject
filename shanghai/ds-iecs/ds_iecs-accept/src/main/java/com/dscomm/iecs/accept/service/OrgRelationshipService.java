package com.dscomm.iecs.accept.service;


import com.dscomm.iecs.basedata.graphql.inputbean.OrgRelationshipSaveInputInfo;
import com.dscomm.iecs.basedata.graphql.typebean.OrgRelationshipBean;

import java.util.List;

/**
 * 描述：机构转警及其他机构关系 服务
 */
public interface OrgRelationshipService {

    /**
     * 保存 机构转警及其他机构关系
     * @param orgRelationshipVo
     * @return
     */
    OrgRelationshipBean saveOrgRelationship(OrgRelationshipSaveInputInfo orgRelationshipVo);

    /**
     * 根据 机构id 查询机构转警及其他机构关系
     * @param fireDeptId
     * @return
     */
    List<OrgRelationshipBean> findOrgRelationshipList( String fireDeptId );

    /**
     * 根据 机构id  转警类型   查询机构转警及其他机构关系
     * @param fireDeptId
     * @param transferType
     * @return
     */
    OrgRelationshipBean  findOrgRelationship( String fireDeptId  , String   transferType );




    /**
     * 根据
     * @param orgRelationshipList
     * @return
     */
    boolean deleteOrgRelationship( List<String>  orgRelationshipList );

}
