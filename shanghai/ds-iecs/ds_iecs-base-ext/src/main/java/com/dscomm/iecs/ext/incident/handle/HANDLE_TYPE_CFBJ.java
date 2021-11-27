package com.dscomm.iecs.ext.incident.handle;

import com.dscomm.iecs.base.bo.BaseStatusBean;

public class HANDLE_TYPE_CFBJ {

    private static  BaseStatusBean tz ;

    static {
        tz = new  BaseStatusBean( "3","重复报警" ) ;
    }

    public static String getCode() {
        return tz.getCode() ;
    }

    public static String getName() {
        return tz.getName() ;
    }

}
