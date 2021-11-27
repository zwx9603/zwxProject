package com.dscomm.iecs.ext.incident.type;

import com.dscomm.iecs.base.bo.BaseStatusBean;

public class INCIDENT_TYPE_QXJY {

    private static  BaseStatusBean tz ;

    static {
        tz = new  BaseStatusBean( "20000", "抢险救援" ) ;
    }

    public static String getCode() {
        return tz.getCode() ;
    }

    public static String getName() {
        return tz.getName() ;
    }

}
