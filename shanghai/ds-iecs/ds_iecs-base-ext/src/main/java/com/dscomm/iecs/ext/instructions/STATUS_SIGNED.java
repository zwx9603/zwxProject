package com.dscomm.iecs.ext.instructions;

import com.dscomm.iecs.base.bo.BaseStatusBean;

public class STATUS_SIGNED {

    private static  BaseStatusBean tz ;

    static {
        tz = new  BaseStatusBean( "STATUS_SIGNED","已签收" ) ;
    }

    public static String getCode() {
        return tz.getCode() ;
    }

    public static String getName() {
        return tz.getName() ;
    }

}
