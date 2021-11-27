package com.dscomm.iecs.agent.service;


import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;

import java.util.List;

/**
 * 描述:用户信息
 *
 * @author YangFuxi
 * Date Time 2019/8/30 13:55
 */
public interface UserService {



    /**
     * 获取当前已登录用户信息
     *
     * @return 返回用户信息
     */
    UserInfo getUserInfo();

    /**
     * 获取已登录用户账号
     *
     * @return 返回用户信息
     */
    String  getAccount();

    /**
     * 登录服务
     *
     * @param token 加密token
     * @param ip 客户端ip
     * @return 返回用户信息
     * @param loginFlag 是否是登录接口调用的 true的时候检查是否重复登录 false不检查
     */
    UserInfo login(String token,String ip , String sessionAccount ,boolean loginFlag);


    /**
     * 心跳服务
     * @param token token
     * @return 返回调用结果
     */
    Boolean heart(String token);

     /**
     * 注销登录
     *
     * @param token 访问Ip
     * @return 返回操作结果
     */
    Boolean logout(String token);

    /**
     * 设置用户语言
     *
     * @param language 语言
     * @return 返回用户语言
     */
    String setUserLanguage(String language);

    /**
     * 获取用户语言
     *
     * @param systemName 用户账号
     * @return 返回用户语言
     */
    String getUserLanguage(String systemName);

    /**
     * 心跳检测退出机制
     */
    void checkHeart();

    /**
     * 查数据库 非接警角色 用户信息
     *
     * @return 成功标志
     */
    Boolean initUserInfoCache();


    /**
     * 获取全部用户信息
     *
     * @return 返回用户信息
     */
    List<UserInfo>  findAllOnlineUserInfo();

    /**
     * 获取全部用户信息 ( 去掉坐席登录用户信息 )
     *
     * @return 返回用户信息
     */
    List<UserInfo>  getAllUserInfoByAuthorization();

    /**
     * 分权分域获取所有消防站在线用户( 去掉坐席登录用户信息 )
     *
     * @return 返回用户信息
     */
    List<UserInfo> findAllOnlineUserInfoByAuthorization();




}
