package com.dscomm.iecs.ext.organization.naturehead;

import com.dscomm.iecs.base.bo.BaseStatusBean;

public class ORGANIZATION_NATURE_HEAD_XJZD {

    private static  BaseStatusBean tz ;

    static {
        tz = new  BaseStatusBean( "09", "县级中队" ) ;
    }

    public static String getCode() {
        return tz.getCode() ;
    }

    public static String getName() {
        return tz.getName() ;
    }

}
