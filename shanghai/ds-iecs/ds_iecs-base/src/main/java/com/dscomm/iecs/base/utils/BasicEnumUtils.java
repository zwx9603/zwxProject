package com.dscomm.iecs.base.utils;


import com.dscomm.iecs.base.enums.BasicEnum;
import com.dscomm.iecs.base.enums.EnumBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述:枚举工具类
 *
 * @author YangFuxi
 * Date Time 2019/8/14 9:52
 */
public class BasicEnumUtils {
    /**
     * 获取指定枚举类的全部类型集合
     *
     * @param clazz 枚举类
     * @return 返回全部类型集合
     */
    public static List<String> allTypes(Class clazz) {
        List types = new ArrayList();
        if (clazz.isEnum()) {
            Enum[] enums = (Enum[]) clazz.getEnumConstants();
            for (Enum anEnum : enums) {
                types.add(anEnum.name());
            }
        }
        return types;
    }

    /**
     * 获取指定枚举类(须实现com.dscomm.ecs.agent.service.bo.enums.BasicEnum接口)的全部元素值集合
     *
     * @param clazz 枚举类
     * @return 枚举类(须实现)的全部元素值集合
     */
    public static Map<String, Map<String, String>> allValueMap(Class clazz) {
        Map<String, Map<String, String>> values = new HashMap<>();
        if (clazz.isEnum() && BasicEnum.class.isAssignableFrom(clazz)) {
            Enum[] enums = (Enum[]) clazz.getEnumConstants();
            for (Enum anEnum : enums) {
                BasicEnum baseBasicEnum = (BasicEnum) anEnum;
                Map<String, String> map = new HashMap();
                map.put(baseBasicEnum.getCode(), baseBasicEnum.getName());
                values.put(baseBasicEnum.getType(), map);
            }
        }
        return values;
    }

    /**
     * 根据枚举类和类型获取指定枚举类(须实现com.dscomm.ecs.agent.service.bo.enums.BasicEnum接口)内容bo
     *
     * @param clazz 枚举类
     * @param type  类型
     * @return 枚举内容bo
     */
    public static EnumBean getEnumBean(Class clazz, String type) {
        EnumBean bo = null;
        Map<String, Map<String, String>> map = allValueMap(clazz);
        if (map != null && map.size() > 0) {
            Map<String, String> value = map.get(type);
            bo = mapToEnumBean(bo, value);
        }
        return bo;
    }

    /**
     * 获取指定枚举类的所有枚举bo
     *
     * @param clazz 枚举类
     * @return 返回枚举bo集合
     */
    public static List<EnumBean> getAllEnumBO(Class clazz) {
        List<EnumBean> list = new ArrayList<>();
        Map<String, Map<String, String>> map = allValueMap(clazz);
        if (map != null && map.size() > 0) {
            map.keySet().forEach(type -> {
                EnumBean bo = null;
                Map<String, String> value = map.get(type);
                bo=mapToEnumBean(bo, value);
                if (bo!=null){
                    list.add(bo);
                }
            });
        }
        return list;
    }

    /**
     * 根据枚举类和类型获取指定枚举类(须实现com.dscomm.ecs.agent.service.bo.enums.BasicEnum接口)内容bo
     *
     * @param clazz 枚举类
     * @param code  类型
     * @return 枚举内容bo
     */
    public static EnumBean getEnumBeanByCode(Class clazz, String code) {
        EnumBean bo = null;
        Map<String, Map<String, String>> map = allValueMap(clazz);
        if (map != null && map.size() > 0) {
            for (String type : map.keySet()) {
                Map<String, String> value = map.get(type);
                String key = (String) value.keySet().toArray()[0];
                if (code != null && code.equals(key)) {
                    bo = mapToEnumBean(bo, value);
                }

            }
        }
        return bo;
    }

    /**
     * 根据枚举类和类型获取指定枚举类(须实现com.dscomm.ecs.agent.service.bo.enums.BasicEnum接口)name值
     *
     * @param clazz 枚举类
     * @param code  编码
     * @return 名称
     */
    public static String getNameByCode(Class clazz, String code) {
        EnumBean bo = getEnumBeanByCode(clazz, code);
        if (bo != null) {
            return bo.getName();
        }
        return null;
    }

    /**
     * 转换工具
     *
     * @param bo    枚举bo
     * @param value 枚举内容
     * @return 枚举bo
     */
    private static EnumBean mapToEnumBean(EnumBean bo, Map<String, String> value) {
        bo = new EnumBean();
        if (value != null && value.size() > 0) {

            String key = (String) value.keySet().toArray()[0];
            bo.setCode(key);
            bo.setName(value.get(key));
        }
        return bo;
    }

}
