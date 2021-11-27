package com.dscomm.iecs.ext.rallypointstatus;

import com.dscomm.iecs.base.bo.BaseStatusBean;

public class SAVED {

    private static  BaseStatusBean tz ;

    static {
        tz = new  BaseStatusBean( "SAVED", "已保存" ) ;
    }

    public static String getCode() {
        return tz.getCode() ;
    }

    public static String getName() {
        return tz.getName() ;
    }

}
