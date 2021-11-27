package com.dscomm.iecs.ext.incident.alarm;

import com.dscomm.iecs.base.bo.BaseStatusBean;

public class INCIDENT_ALARM_MODE_YCJKBJ {

    private static  BaseStatusBean tz ;

    static {
        tz = new  BaseStatusBean( "0600","城市消防远程监控中心报警" ) ;
    }

    public static String getCode() {
        return tz.getCode() ;
    }

    public static String getName() {
        return tz.getName() ;
    }

}
