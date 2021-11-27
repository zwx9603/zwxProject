package com.dscomm.iecs.ext.organization.naturehead;

import com.dscomm.iecs.base.bo.BaseStatusBean;

public class ORGANIZATION_NATURE_HEAD_XXDD {

    private static  BaseStatusBean tz ;

    static {
        tz = new  BaseStatusBean( "05", "区县大队" ) ;
    }

    public static String getCode() {
        return tz.getCode() ;
    }

    public static String getName() {
        return tz.getName() ;
    }

}
