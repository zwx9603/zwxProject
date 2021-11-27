package com.dscomm.iecs.accept.service;

import java.util.List;

/**
 * 描述: 超时 服务
 */
public interface OverTimeTaskService {



    /**
     * 处警单位 到达超时提醒  websocket 提醒
     */
    void  handleNoticeOrganizationOverTimeTask() ;


    /**
     * 处警单位 到达超时提醒  websocket 提醒
     */
    List<String>  findNoticeOverTimeHandleIds (  Long currentTime  , Long overTime ) ;


    /**
     * 处警单位 人工签收超时提醒  websocket 提醒
     */
    void  handleOrganizationOverTimeTask() ;

    /**
     * 处警单位 人工签收超时提醒  websocket 提醒
     */
    List<String>  findOverTimeHandleIds (  Long currentTime  , Long overTime ) ;



}
