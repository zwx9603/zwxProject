package com.dscomm.iecs.base.config.jwt;

import org.springframework.beans.factory.annotation.Value;

/**
 * 描述:jwt 认证配置类
 *
 * @author YangFuxi
 * Date Time 2020/1/14 16:48
 */

public class JwtConfigBean {

    @Value("${jwt.isOpenCas:false}")
    private String isOpenJwt ;

    //    秘钥 默认123456
    @Value("${jwt.client:iecs}")
    private String client;


    //    秘钥 默认123456
    @Value("${jwt.secret:123456}")
    private String secret;

    //    超时时间 默认300 秒
    @Value("${jwt.expire:300}")
    private Long expire;

    //  用于JWT加密的密匙 123456
    @Value("${jwt.datakey :123456}")
    private String datakey ;

    // 头信息 header名称
    @Value("${jwt.header:token}")
    private String header;

    public String getIsOpenJwt() {
        return isOpenJwt;
    }

    public void setIsOpenJwt(String isOpenJwt) {
        this.isOpenJwt = isOpenJwt;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Long getExpire() {
        return expire;
    }

    public void setExpire(Long expire) {
        this.expire = expire;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }


    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getDatakey() {
        return datakey;
    }

    public void setDatakey(String datakey) {
        this.datakey = datakey;
    }
}
