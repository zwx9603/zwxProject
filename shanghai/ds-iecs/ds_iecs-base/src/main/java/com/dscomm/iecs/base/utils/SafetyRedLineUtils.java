package com.dscomm.iecs.base.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.mx.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * 描述:安全红线工具类
 *
 * @author YangFuxi
 * Date Time 2020/2/12 17:07
 */
public class SafetyRedLineUtils {
    private static final Logger logger = LoggerFactory.getLogger(SafetyRedLineUtils.class);

    private static List<String> fields = Arrays.asList("address", "alarmPersonName",
            "alarmPersonContact", "alarmPersonAddress", "content", "alarmPersonIdentityNumber",
            "agentNum", "phone", "ip", "userId", "userCode", "account", "userAccount", "userName", "locateMark",
            "longitude", "latitude", "newContent", "carNumber", "alarmContent", "alarmPhone",
            "callingTelephone", "newContent", "incidentAddress", "alarmPersonAddress", "remotePhone", "reason");

    /**
     * 安全红线处理敏感信息
     *
     * @param msg 处理前的信息
     * @return 返回处理后的信息
     */
    public static String transform(String msg) {
        try {
            if (StringUtils.isBlank(msg)) {
                return msg;
            } else {
                int length = msg.length();
                if (length == 1) {
                    return "*";
                } else if (length == 2) {
                    return msg.substring(0, 1) + "*";
                } else if (length >= 3) {
                    return msg.substring(0, 1) + String.format("%" + (msg.length() - 2) + "s", "").replace(" ", "*") + msg.substring(msg.length() - 1, msg.length());
                }
                return "";
            }
        } catch (Exception ex) {
            logger.error("transform string message for redLine error:", ex);
            return "";
        }

    }


    /**
     * 安全红线处理对象信息
     *
     * @param obj 要处理的对象信息
     */
    public static Object transformObjectForSafety(Object obj) {
        Object result = JSONObject.toJSON(obj);
        if (result instanceof JSONArray) {
            JSONArray jsonArray = new JSONArray();
            ((JSONArray) result).forEach(o -> {
                jsonArray.add(transformObjectForSafety(o));
            });
            return jsonArray;
        } else if (result instanceof JSONObject) {
            transformJsonObject((JSONObject) result, fields);
            return result;
        }
        return null;
    }

    /**
     * 私有递归处理安全红线的方法（jsonobject）
     *
     * @param obj    要处理的对象
     * @param fields 敏感字段
     */
    private static void transformJsonObject(JSONObject obj, List<String> fields) {
        if (obj != null) {
            obj.keySet().forEach(key -> {
                Object value = obj.get(key);
                if (fields != null && fields.contains(key)) {
//                    System.out.println(String.format("contains key:%s,value:%s", key, value));
                    value = transform(String.valueOf(value));
                }else if (value instanceof JSONArray){
                    JSONArray jsonArray = new JSONArray();
                    ((JSONArray) value).forEach(o -> {
                        jsonArray.add(transformObjectForSafety(o));
                    });
                    value=jsonArray;
                }else if (value instanceof JSONObject) {
                    transformJsonObject((JSONObject) value, fields);
                }
                obj.put(key, value);
            });
        }
    }

    public static void main(String[] args) {
//        String s1=null;
//        String s2="1";
//        String s3="12";
//        String s4="123";
//        String s5="1234";
//        System.out.println(transform(s1));
//        System.out.println(transform(s2));
//        System.out.println(transform(s3));
//        System.out.println(transform(s4));
//        System.out.println(transform(s5));
        String ms1 = "123456";
        System.out.println(JSONObject.toJSONString(transformObjectForSafety(ms1)));

    }
}
