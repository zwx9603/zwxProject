package com.dscomm.iecs.base.utils;

import org.mx.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

public class AES256Helper {

    /**
     * 成员变量：加密算法
     */
    public static final String CIPHER_ALGORITHM = "AES/CBC/PKCS7Padding";

    /**
     * 成员变量：密钥
     */
    private static final String sKey = ")O[NB]6,YF}+efcaj{+oESb9d8>Z'e9M";

    /**
     * 成员变量：初始化向量
     */
    private static final String ivParameter = "L+\\~f4,Ir)b$=pkf";

    public static byte[] keyByte = null;

    static {
        try {
            keyByte = AES256Helper.generateKeyByte();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 加密
     *
     * @param data 明文
     * @param key  密钥
     * @return 密文
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM, "BC");
        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        return cipher.doFinal(data);
    }

    /**
     * 加密
     *
     * @param data 明文
     * @return 密文
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data) throws Exception {

        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM, "BC");
        SecretKeySpec skeySpec = new SecretKeySpec(AES256Helper.keyByte, "AES");
        IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        return cipher.doFinal(data);
    }

    /**
     * 加密
     *
     * @param data 明文
     * @return 返回加密后token
     * @throws Exception 异常类
     */
    public static String encrypt(String data) throws Exception {
        byte[] keys = data.getBytes();
        byte[] encrypt = encrypt(keys);
        String token = StringUtils.byte2Base64String(encrypt);
        return token;
    }

    /**
     * 解密
     *
     * @param data 密文
     * @param key  密钥
     * @return 明文
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM, "BC");
        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
        return cipher.doFinal(data);
    }

    /**
     * 解密
     *
     * @param data 密文
     * @return 明文
     * @throws Exception 异常类
     */
    public static byte[] decrypt(byte[] data) throws Exception {

        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM, "BC");
        SecretKeySpec skeySpec = new SecretKeySpec(AES256Helper.keyByte, "AES");
        IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
        return cipher.doFinal(data);
    }

    /**
     * 解密
     *
     * @param token token
     * @return 返回解密后的字符串
     * @throws Exception 异常类
     */
    public static String decrypt(String token) throws Exception {
        if (!StringUtils.isBlank(token)) {
            byte[] byteData = StringUtils.Base64String2Byte(token);
            String decryption = new String(decrypt(byteData));
            return decryption;
        }
        return null;
    }

    public static byte[] generateKeyByte() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {

        String rootDir = "/opt";
        String osName = System.getProperty("os.name");
        String fileSeparator = System.getProperty("file.separator");
        if (osName.startsWith("Windows")) {
            rootDir = "D:";
        }
        String dirName = rootDir + fileSeparator + "dssystem" + fileSeparator + "fileconfig" + fileSeparator;

        List<String> locations = new ArrayList<>();
        locations.add(dirName + "file1" + fileSeparator + "file1.conf");
        locations.add(dirName + "file2" + fileSeparator + "file2.conf");
        locations.add(dirName + "file3" + fileSeparator + "file3.conf");
        locations.add(dirName + "file4" + fileSeparator + "file4.conf");
        locations.add(dirName + "file5" + fileSeparator + "file5.conf");

        List<int[]> bytes = new ArrayList<>();
        //TODO:测试密钥
        for (String location : locations) {
//            FileInputStream fis = null;
//            InputStreamReader reader = null;
//            BufferedReader br = null;
//            try {
                String hexString = "1592545264799123456789";
//                fis = new FileInputStream(location);
//                reader = new InputStreamReader(fis, "utf-8");
//                br = new BufferedReader(reader);
//                String line = null;
//                while ((line = br.readLine()) != null) {
//                    hexString += line;
//                }
//                //去除utf-8文本中开头可能存在的BOM
//                if (hexString.startsWith("\uFEFF")) {
//                    hexString = hexString.substring(1, hexString.length());
//                }
                int[] vals = KeyUtil.hexStringToInt(hexString);
                bytes.add(vals);
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                reader.close();
//                fis.close();
//                br.close();
//            }
        }

        String keyChar = KeyUtil.xorInt(bytes);
        byte[] keyByte = KeyUtil.getPBKDF2(keyChar);

        return keyByte;
    }

    /*
     * public static byte[] generateDesKey(int length) throws Exception { //实例化
     * KeyGenerator kgen = null; kgen = KeyGenerator.getInstance("AES");
     * //设置密钥长度 kgen.getCategory(length); //生成密钥 SecretKey skey = kgen.generateKey();
     * //返回密钥的二进制编码 return skey.getEncoded(); }
     */

    //    public static void main(String[] args) throws Exception {
//        String str = "dsportal";
//        String mykey = ")O[NB]6,YF}+ef90j{+oESb9d8>Z'e9M";
//
//        System.out.println(mykey.length());
//        // 打印原文
//        System.out.println("原文：" + str);
//        // 密钥
//        byte[] key;
//        try {
//            // 生成随机密钥
//            key = mykey.getBytes();
//            // 打印密钥
//            System.out.print("密钥：");
//            for (int i = 0; i < key.length; i++) {
//                System.out.printf("%x", key[i]);
//            }
//            System.out.print("\n");
//            // 加密
//            byte[] data = AES256Helper.encrypt(str.getBytes());
//            // byte[] data = AES256Helper.encrypt(str.getBytes(),key);
//            // 打印密文
//            System.out.print("加密后：");
//            // hXJvVNVWgpZe+68v+DCZnA==
//            //System.out.println(new BASE64Encoder().encode(data));
//            // 294f5b4e425d362c59467d2b656639306a7b2b6f4553623964383e5a2765394d
//            char[] testChar = Base64Helper.encode(data);
//            System.out.println(Base64Helper.encode(data));
//            // for (int i = 0; i < data.length; i++) {
//            // System.out.printf("%x", data[i]);
//            // }
//            // System.out.print("\n");
//
//            String test = String.valueOf(testChar);
//            // String test = "qLu6l0ZdQruoVX62LhHqvA==";
//            byte[] test_data = Base64Helper.decode(test.toCharArray());
//            // 解密密文
//            data = AES256Helper.decrypt(test_data);
//            // data = AES256Helper.decrypt(test_data, key);
//            // 打印原文
//            System.out.println("解密后：" + new String(data));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    public static void main(String[] args) {
        try {
            String token = System.currentTimeMillis() + "," + "192.168.7.122," + "11";
            System.out.println("加密前token:" + token);
            String encrypt = encrypt(token);
            System.out.println("加密后token:" + encrypt);
            String decrypt = decrypt(encrypt);
            System.out.println("解密后token:" + decrypt);
            String dsecs="dsecs_web";
            System.out.println("加密前dsecs:" + dsecs);
            String encrypt_dsecs = encrypt(dsecs);
            System.out.println("加密后dsecs:" + encrypt_dsecs);
            String decrypt_dsecs = decrypt(encrypt_dsecs);
            System.out.println("解密后dsecs:" + decrypt_dsecs);
            String dsicp="dsicp_web";
            System.out.println("加密前dsicp:" + dsicp);
            String encrypt_dsicp = encrypt(dsicp);
            System.out.println("加密后dsicp:" + encrypt_dsicp);
            String decrypt_dsicp = decrypt(encrypt_dsicp);
            System.out.println("解密后dsicp:" + decrypt_dsicp);
            String dsecsip="jdbc:postgresql://192.168.52.228:8110/dsdb?currentSchema=dsecs_web";
            System.out.println("解密前数据库连接:" +dsecsip+" 加密后：" +encrypt(dsecsip));
            String icpip="jdbc:postgresql://192.168.52.228:8110/dsdb?currentSchema=dsicp_web";
            System.out.println("解密前数据库连接:" +icpip+" 加密后：" +encrypt(icpip));
//            System.out.println("测试解密:"+"MlF76cnjpWAYKxlkjPmo+YcR7clBIi1S+Q+W/GvdAiI=   "+decrypt("MlF76cnjpWAYKxlkjPmo+YcR7clBIi1S+Q+W/GvdAiI="));
            System.out.println("测试密码加密 Edmund@110: "+encrypt("Edmund@110"));
            String redispass="Edmund@110";
            String encrypt_redispass = encrypt(redispass);
            String decrypt_redispass = decrypt(encrypt_redispass);
            System.out.println(String.format("%s:%s:%s",redispass,encrypt_redispass,decrypt_redispass));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
