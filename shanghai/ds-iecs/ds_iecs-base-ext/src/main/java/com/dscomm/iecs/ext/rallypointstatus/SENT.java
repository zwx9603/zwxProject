package com.dscomm.iecs.ext.rallypointstatus;

import com.dscomm.iecs.base.bo.BaseStatusBean;

public class SENT {

    private static  BaseStatusBean tz ;

    static {
        tz = new  BaseStatusBean( "SENT", "已下达" ) ;
    }

    public static String getCode() {
        return tz.getCode() ;
    }

    public static String getName() {
        return tz.getName() ;
    }

}
