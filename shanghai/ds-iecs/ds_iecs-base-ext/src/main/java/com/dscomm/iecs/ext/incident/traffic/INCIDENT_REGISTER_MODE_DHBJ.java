package com.dscomm.iecs.ext.incident.traffic;

import com.dscomm.iecs.base.bo.BaseStatusBean;

public class INCIDENT_REGISTER_MODE_DHBJ {

    private static  BaseStatusBean tz ;

    static {
        tz = new  BaseStatusBean( "110","110" ) ;
    }

    public static String getCode() {
        return tz.getCode() ;
    }

    public static String getName() {
        return tz.getName() ;
    }

}
