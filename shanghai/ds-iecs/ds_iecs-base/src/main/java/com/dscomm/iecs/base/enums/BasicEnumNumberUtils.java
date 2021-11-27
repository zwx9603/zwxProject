package com.dscomm.iecs.base.enums;


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
public class BasicEnumNumberUtils {
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
     * 获取指定枚举类(须实现com.dscomm.ecs.agent.service.bo.enums.BasicEnumBase接口)的全部元素值集合
     *
     * @param clazz 枚举类
     * @return 枚举类(须实现)的全部元素值集合
     */
    public static Map<String, Map<Integer, String>> allValueMap(Class clazz) {
        Map<String, Map<Integer, String>> values = new HashMap<>();
        if (clazz.isEnum() && BasicEnumNumber.class.isAssignableFrom(clazz)) {
            Enum[] enums = (Enum[]) clazz.getEnumConstants();
            for (Enum anEnum : enums) {
                BasicEnumNumber baseBasicEnum = (BasicEnumNumber) anEnum;
                Map<Integer, String> map = new HashMap();
                map.put(baseBasicEnum.getCode(), baseBasicEnum.getName());
                values.put(baseBasicEnum.getType(), map);
            }
        }
        return values;
    }

    /**
     * 根据枚举类和类型获取指定枚举类(须实现com.dscomm.ecs.agent.service.bo.enums.BasicEnumBase接口)内容bo
     *
     * @param clazz 枚举类
     * @param type  类型
     * @return 枚举内容bo
     */
    public static BasicEnumNumberBean getBasicEnumBean(Class clazz, String type) {
        BasicEnumNumberBean bo = null;
        Map<String, Map<Integer, String>> map = allValueMap(clazz);
        if (map != null && map.size() > 0) {
            Map<Integer, String> value = map.get(type);
            bo = mapToBasicEnumBean(bo, type, value);
        }
        return bo;
    }

    /**
     * 获取指定枚举类的所有枚举bo
     *
     * @param clazz 枚举类
     * @return 返回枚举bo集合
     */
    public static List<BasicEnumNumberBean> getAllEnumBean(Class clazz) {
        List<BasicEnumNumberBean> list = new ArrayList<>();
        Map<String, Map<Integer, String>> map = allValueMap(clazz);
        if (map != null && map.size() > 0) {
            map.keySet().forEach(type -> {
                BasicEnumNumberBean bo = null;
                Map<Integer, String> value = map.get(type);
                bo=mapToBasicEnumBean(bo, type, value);
                if (bo!=null){
                    list.add(bo);
                }
            });
        }
        return list;
    }

    /**
     * 根据枚举类和类型获取指定枚举类(须实现com.dscomm.ecs.agent.service.bo.enums.BasicEnumBase接口)内容bo
     *
     * @param clazz 枚举类
     * @param code  类型
     * @return 枚举内容bo
     */
    public static BasicEnumNumberBean getBasicEnumBeanByCode(Class clazz, Integer code) {
        BasicEnumNumberBean bo = null;
        Map<String, Map<Integer, String>> map = allValueMap(clazz);
        if (map != null && map.size() > 0) {
            for (String type : map.keySet()) {
                Map<Integer, String> value = map.get(type);
                Integer key = (Integer) value.keySet().toArray()[0];
                if (code != null && code.equals(key)) {
                    bo = mapToBasicEnumBean(bo, type, value);
                    return bo;
                }

            }
        }
        return bo;
    }

    /**
     * 根据枚举类和类型获取指定枚举类(须实现com.dscomm.ecs.agent.service.bo.enums.BasicEnumBase接口)name值
     *
     * @param clazz 枚举类
     * @param type  类型
     * @return 名称
     */
    public static String getNameByType(Class clazz, String  type ) {
        BasicEnumNumberBean bo = null;
        Map<String, Map<Integer, String>> map = allValueMap(clazz);
        if (map != null && map.size() > 0) {
            Map<Integer, String> value = map.get(type);
            bo = mapToBasicEnumBean(bo, type, value);
        }
        if( bo != null ){
            return  bo.getName() ;
        }
        return null;
    }

    /**
     * 根据枚举类和类型获取指定枚举类(须实现com.dscomm.ecs.agent.service.bo.enums.BasicEnumBase接口)name值
     *
     * @param clazz 枚举类
     * @param code  编码
     * @return 名称
     */
    public static String getNameByCode(Class clazz, Integer code) {
        BasicEnumNumberBean bo = getBasicEnumBeanByCode(clazz, code);
        if (bo != null) {
            return bo.getName();
        }
        return null;
    }

    /**
     * 转换工具
     *
     * @param bo    枚举bo
     * @param type  类型
     * @param value 枚举内容
     * @return 枚举bo
     */
    private static BasicEnumNumberBean mapToBasicEnumBean(BasicEnumNumberBean bo, String type, Map<Integer, String> value) {
        if (value != null && value.size() > 0) {
            bo = new BasicEnumNumberBean();
            bo.setType(type);
            Integer key = (Integer) value.keySet().toArray()[0];
            bo.setCode(key);
            bo.setName(value.get(key));
        }
        return bo;
    }

}
