package com.dscomm.iecs.ext.incident.status;

import com.dscomm.iecs.base.bo.BaseStatusBean;

public class INCIDENT_STATUS_TZ {

    private static  BaseStatusBean tz ;

    static {
        tz = new  BaseStatusBean( "02","通知" ) ;
    }

    public static String getCode() {
        return tz.getCode() ;
    }

    public static String getName() {
        return tz.getName() ;
    }

}
