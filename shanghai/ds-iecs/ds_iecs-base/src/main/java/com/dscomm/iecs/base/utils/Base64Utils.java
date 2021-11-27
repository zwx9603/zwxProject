package com.dscomm.iecs.base.utils;

import org.mx.StringUtils;

import java.util.Base64;

/**
 * 描述:工具类
 *
 * @author YangFuxi
 * Date Time 2020/3/26 10:27
 */
public class Base64Utils {
    private static final String key="dsEdmund110";
    /**
     * base64加密
     *
     * @param str 明文
     * @return 返回加密后的二进制数组
     */
    public static byte[] Base64Encrypt(String str) {
        if (!StringUtils.isBlank(str)) {
            return Base64.getEncoder().encode(str.getBytes());
        } else {
            return null;
        }
    }

    public static byte bitXOR(byte source, byte key) {
        return (byte) (source ^ key);
    }

    public static String transformStr(String source){
        if (StringUtils.isBlank(source)){
            return source;
        }else {
            try {
                byte[] keys=key.getBytes("UTF-8");
                byte[] sourceKey=source.getBytes("UTF-8");
                byte[] target=new byte[sourceKey.length];
                int keyIdx = 0;
                for (int i = 0; i < sourceKey.length; i++) {
                    if (keyIdx==keys.length){
                        keyIdx=0;
                    }
                    target[i]=bitXOR(sourceKey[i],keys[keyIdx]);
                }
                return AES256Helper.encrypt(Base64.getEncoder().encodeToString(target));
            } catch (Exception ex) {
                return null;
            }
        }
    }
    public static String transformStr2(String source){
        if (StringUtils.isBlank(source)){
            return source;
        }else {
            try {
                byte[] keys=key.getBytes("UTF-8");
                byte[] sourceKey=Base64.getDecoder().decode(AES256Helper.decrypt(source));
                byte[] target=new byte[sourceKey.length];
                int keyIdx = 0;
                for (int i = 0; i < sourceKey.length; i++) {
                    if (keyIdx==keys.length){
                        keyIdx=0;
                    }
                    target[i]=bitXOR(sourceKey[i],keys[keyIdx]);
                }
                return new String(target,"UTF-8");
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public static void main(String[] args) {
        String ss="123edrto";
        String ee=transformStr(ss);
        String dd=transformStr2(ee);
        System.out.println(String.format("ss:%s,ee:%s,dd:%s",ss,ee,dd));
    }
}
