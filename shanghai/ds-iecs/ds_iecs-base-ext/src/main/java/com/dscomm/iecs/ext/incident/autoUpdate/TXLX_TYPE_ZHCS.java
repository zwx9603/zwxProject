package com.dscomm.iecs.ext.incident.autoUpdate;

import com.dscomm.iecs.base.bo.BaseStatusBean;

public class TXLX_TYPE_ZHCS {

    private static  BaseStatusBean tz ;

    static {
        tz = new  BaseStatusBean( "ZHCS","火灾场所" ) ;
    }

    public static String getCode() {
        return tz.getCode() ;
    }

    public static String getName() {
        return tz.getName() ;
    }

}
