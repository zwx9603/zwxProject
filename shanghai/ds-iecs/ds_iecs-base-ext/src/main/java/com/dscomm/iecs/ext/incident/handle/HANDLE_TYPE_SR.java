package com.dscomm.iecs.ext.incident.handle;

import com.dscomm.iecs.base.bo.BaseStatusBean;

public class HANDLE_TYPE_SR {

    private static  BaseStatusBean tz ;

    static {
        tz = new  BaseStatusBean( "4","骚扰" ) ;
    }

    public static String getCode() {
        return tz.getCode() ;
    }

    public static String getName() {
        return tz.getName() ;
    }

}
