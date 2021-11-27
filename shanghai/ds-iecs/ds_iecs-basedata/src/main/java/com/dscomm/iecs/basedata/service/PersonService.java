package com.dscomm.iecs.basedata.service;


import com.dscomm.iecs.basedata.graphql.typebean.PersonBean;
import com.dscomm.iecs.basedata.graphql.typebean.PersonMailBean;

import java.util.List;

/**
 * 人员 服务类
 */
public interface PersonService {

    /**
     * 描述：根据人员id查询指定 人员通讯录
     *
     * @param personId 人员id
     * @return 人员通讯详情
     */
    PersonMailBean findPersonMail(String personId);


    /**
     * 描述：根据 机构 人员名称模糊匹配 获得人员通信录
     *
     * @param organizationId 人员id
     * @param keyword 人员姓名
     * @return 人员通讯详情
     */
    List<PersonMailBean> findPersonMailCondition (String organizationId , String keyword );



    /**
     * 描述：根据 机构 人员名称模糊匹配 获得人员信息
     *
     * @param organizationId 人员id
     * @param keyword 人员姓名
     * @return 人员通讯详情
     */
    List<PersonBean> findPersonCondition (String organizationId , String keyword );


    /**
     * 描述：根据人员id查询 人员详情
     *
     * @param personId 人员id
     * @return 人员通讯详情
     */
    PersonBean findPerson(String personId);

}
