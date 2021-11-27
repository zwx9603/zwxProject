package com.dscomm.iecs.agent.service;


import com.dscomm.iecs.agent.service.bean.ImeiPortalBean;
import com.dscomm.iecs.agent.service.bean.UserPortalBean;

/**
 * 从门户获得用户信息
 */
public interface UserStoreService {

    /**
     *
      * @param account 登录用户账号信息
     * @return 返回用户账号信息
     */
    UserPortalBean getUser(String account);


    /**
     *
     * @param imei 终端imei号
     * @return 返回终端信息
     */
    ImeiPortalBean getImei(String imei );

}
