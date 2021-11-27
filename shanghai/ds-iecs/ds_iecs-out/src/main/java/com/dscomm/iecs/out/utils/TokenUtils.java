package com.dscomm.iecs.out.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.dscomm.iecs.out.exception.OutException;
import com.dscomm.iecs.out.graphql.typebean.TokenBean;
import com.dscomm.iecs.out.graphql.typebean.TokenDate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/4/21 20:18
 * @Describe token生成工具类
 */
public class TokenUtils {

    //设置过期时间
    private static final long EXPIRE_DATE = 30 * 60 * 100000;
    //token秘钥
    private static final String TOKEN_SECRET = "ZCEQIUBFKSJBFJH2020BQWE";

    public static  TokenDate token(String username, String password) {

        //TokenBean tokenBean = new TokenBean();
        TokenDate data = new TokenDate();
        String token = "";
        try {
            //过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_DATE);
            //秘钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            //设置头部信息
            Map<String, Object> header = new HashMap<>();
            header.put("typ", "JWT");
            header.put("alg", "HS256");
            //携带username，password信息，生成签名
            token = JWT.create()
                    .withHeader(header)
                    .withClaim("username", username)
                    .withClaim("password", password).withExpiresAt(date)
                    .sign(algorithm);
            data.setAccess_token(token);
            data.setToken_type("bearer");
            data.setUsername(username);

//            tokenBean.setData(data);
//            tokenBean.setCode("200");
//            tokenBean.setMsg("授权成功");

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return data;
    }


    public static boolean verify(String token) {
        /**
         * @desc 验证token，通过返回true
         * @params [token]需要校验的串
         **/
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            String username = jwt.getClaim("username").asString();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 获取用户名
     *
     * @param token
     * @return
     */
    public static String getUserName(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            String username = jwt.getClaim("username").asString();
            return username;
        } catch (Exception e) {
            e.printStackTrace();
            throw new OutException(OutException.AccetpErrors.DATA_NULL);
        }
    }


}
