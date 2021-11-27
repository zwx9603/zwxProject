package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.inputbean.SecurityInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.SecurityBean;
import com.dscomm.iecs.accept.graphql.typebean.SecurityReceiverBean;

import java.util.List;

public interface SecurityHintsService {

    /**
     *下达特别警示( 下达安全提示 )
     * @param inputInfo
     * @return
     */
    Boolean  saveSecurityHints(SecurityInputInfo inputInfo);


    /**
     * 签收安全对接
     * @param securityIds
     * @return
     */
    Boolean  signSecurityHints(  List<String> securityIds  );


    /**
     * 获取当前对应警情的已下达安全提示列表
     * @param incidentId 参数
     * @return  返回查询结果列表
     */
     List<SecurityBean> findSecurityHintsList (String incidentId);



    /**
     * 根据警情id 接收对象id 获得 安全提示
     * @param incidentId 参数
     * @return  返回查询结果列表
     */
    List<SecurityReceiverBean> findSecurityReceiverList (String incidentId , String receiverId );



}
