package com.dscomm.iecs.accept.utils;


import com.dscomm.iecs.base.enums.BaseEnum;
import com.dscomm.iecs.base.enums.EnumBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：枚举类工具类
 *
 * @author YangFuXi Date Time 2018/6/12 16:32
 */
public class EnumUtils {
    /**
     * 获取枚举name集合
     *
     * @param clazz 枚举类型
     * @return 返回枚举集合
     */
    public static List<String> nameValues(Class clazz) {
        List<String> names = new ArrayList<>();
        if (clazz.isEnum()) {
            Enum[] values = (Enum[]) clazz.getEnumConstants();
            for (Enum e : values) {
                names.add(e.name());
            }
        }
        return names;
    }

    /**
     * 判断name在指定枚举中是否存在
     *
     * @param name  参数
     * @param clazz 枚举类型
     * @return 返回判断结果
     */
    public static Boolean contains(String name, Class clazz) {
        if (name != null && name != "" && clazz.isEnum()) {
            return nameValues(clazz).contains(name);
        } else {
            return false;
        }
    }

    /**
     * 根据枚举类和枚举名称获取EnumVO对象
     *
     * @param name  枚举名称
     * @param clazz 枚举类
     * @return 返回EnumVO对象
     */
    public static EnumBean transformToEnumVO(String name, Class clazz) {
        EnumBean enumBean = new EnumBean();
        if (contains(name, clazz)) {
            if (BaseEnum.class.isAssignableFrom(clazz)) {
                Enum e = Enum.valueOf(clazz, name);
                enumBean.setCode(e.name());
                enumBean.setName(((BaseEnum) e).getTitle());
            }
        }
        return enumBean;
    }

    /**
     * 获取指定枚举对应name的title
     *
     * @param name  参数 枚举name
     * @param clazz 参数 枚举类型
     * @return 返回title
     */
    public static String getEnumTitle(String name, Class clazz) {
        if (contains(name, clazz)) {
            if (BaseEnum.class.isAssignableFrom(clazz)) {
                Enum e = Enum.valueOf(clazz, name);
                return ((BaseEnum) e).getTitle();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
