package com.dscomm.iecs.basedata.service;

import com.dscomm.iecs.basedata.graphql.typebean.ContactNumberBean;

/**
 * 描述：通讯录 服务
 */
public interface ContactsService {

    /**
     * 根据单位或人员编号获取单位或人员的联系方式(获取联系信息)
     * @param contactObjectId 单位或人员编号
     * @param contactType 类别（单位/人员）
     * @return 联系信息
     */
    ContactNumberBean findContactNumber(String contactObjectId, String contactType);

}
