package com.dscomm.iecs.base.utils;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.util.*;

/**
 *  jpa 工具类  主要根据entity 获得数据库对应表名 列名
 *  实体类使用了persistence的注解
 */
public class JpaUtil {

    /**
     * 根据entit 获得表名
     * @param clazz
     * @return
     */
    public static String getTableName(Class clazz) {
        Table annotation = (Table) clazz.getAnnotation(Table.class);
        if (annotation != null) {
            return annotation.name();
        }
        return null;
    }

    /**
     * 根据entity  获得全部的 entity的属性 （本类 父类 ）的私有 公有 注解Column
     * @param clazz
     * @return
     */
    public static Map<String, Field > getColumnField( Class clazz ) throws  Exception {
        Map<String, Field > fieldMap = new HashMap<>() ;
        List<Field> fieldList = new ArrayList<>() ;
        while (clazz !=null && !clazz.getName().toLowerCase().equals("java.lang.object")) {
            fieldList.addAll(Arrays.asList(clazz .getDeclaredFields()));
            clazz = clazz.getSuperclass(); //得到父类,然后赋给自己
        }

        for (Field field : fieldList) {
            if (field.isAnnotationPresent(Column.class)) {
                fieldMap.put(field.getName() ,  field ) ;
            }
        }
        return fieldMap;
    }

    /**
     * 根据field获得 列名
     * @param field
     * @return
     */
    public static String getColumnName( Field field ) throws  Exception {
        if( null !=  field ){
            Column  column = field.getAnnotation(Column.class);
            return column.name();
        }
        return  null ;
    }

}
