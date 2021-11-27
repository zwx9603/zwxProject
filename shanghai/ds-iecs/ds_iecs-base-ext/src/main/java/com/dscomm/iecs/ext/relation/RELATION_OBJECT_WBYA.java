package com.dscomm.iecs.ext.relation;

import com.dscomm.iecs.base.bo.BaseStatusBean;

public class RELATION_OBJECT_WBYA {

    private static  BaseStatusBean tz ;

    static {
        tz = new  BaseStatusBean( "WBYA","文本预案") ;
    }

    public static String getCode() {
        return tz.getCode() ;
    }

    public static String getName() {
        return tz.getName() ;
    }

}
