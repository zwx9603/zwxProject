package com.dscomm.iecs.ext.incident.handle;

import com.dscomm.iecs.base.bo.BaseStatusBean;

public class HANDLE_TYPE_WS {

    private static  BaseStatusBean tz ;

    static {
        tz = new  BaseStatusBean( "6","咨询" ) ;
    }

    public static String getCode() {
        return tz.getCode() ;
    }

    public static String getName() {
        return tz.getName() ;
    }

}
