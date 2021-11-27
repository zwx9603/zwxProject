package com.dscomm.iecs.ext.warn;

import com.dscomm.iecs.base.bo.BaseStatusBean;

public class WARN_TYPE_ZDDW {

    private static  BaseStatusBean tz ;

    static {
        tz = new  BaseStatusBean( "ZDDW","重点单位" ) ;
    }

    public static String getCode() {
        return tz.getCode() ;
    }

    public static String getName() {
        return tz.getName() ;
    }

}
