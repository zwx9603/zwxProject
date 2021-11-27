package com.dscomm.iecs.ext.incident.handle;

import com.dscomm.iecs.base.bo.BaseStatusBean;

public class HANDLE_TYPE_ZDGJ {

    private static  BaseStatusBean tz ;

    static {
        tz = new  BaseStatusBean( "5","主动挂机" ) ;
    }

    public static String getCode() {
        return tz.getCode() ;
    }

    public static String getName() {
        return tz.getName() ;
    }

}
