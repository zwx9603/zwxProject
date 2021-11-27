package com.dscomm.iecs.agent.service;

import com.dscomm.iecs.basedata.graphql.typebean.user.SubToken;
import com.dscomm.iecs.basedata.graphql.typebean.user.TokenBean;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

/**
 * 描述:验证鉴权服务
 *
 * @author YangFuxi
 * Date Time 2019/9/2 18:53
 */
public interface AuthenticateService {

    /**
     * 访问前置方法
     *
     * @param headers 请求头
     */
    void beforeAccess(@Context HttpHeaders headers);

    /**
     * 访问后置方法
     *
     * @param headers 请求头
     */
    void afterAccess(HttpHeaders headers);

    /**
     * 登录时鉴权认证
     *
     * @param token oken(兼容加密和不加密)
     * @return 返回处理之后的token
     */
    TokenBean transformEncryptedToken(String token, String ip , String SessionAccount );

    /**
     * 认证
     *
     * @param token token(兼容加密和不加密)
     * @return 返回认证结果
     */
    Boolean authentication(String token);


    /**
     * 第三方系统访问认证
     * @param token token令牌
     * @return 返回token，无异常表示认证通过
     */
    SubToken subAuthenticate(String token);


}
