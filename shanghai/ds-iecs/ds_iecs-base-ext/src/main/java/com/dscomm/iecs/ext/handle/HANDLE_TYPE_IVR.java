package com.dscomm.iecs.ext.handle;

import com.dscomm.iecs.base.bo.BaseStatusBean;

public class HANDLE_TYPE_IVR {

    private static  BaseStatusBean tz ;

    static {
        tz = new  BaseStatusBean( "03","IVR调派" ) ;
    }

    public static String getCode() {
        return tz.getCode() ;
    }

    public static String getName() {
        return tz.getName() ;
    }

}
