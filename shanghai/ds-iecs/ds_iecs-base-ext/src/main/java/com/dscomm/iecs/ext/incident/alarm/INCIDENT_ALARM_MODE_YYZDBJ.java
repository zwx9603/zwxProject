package com.dscomm.iecs.ext.incident.alarm;

import com.dscomm.iecs.base.bo.BaseStatusBean;

public class INCIDENT_ALARM_MODE_YYZDBJ {

    private static  BaseStatusBean tz ;

    static {
        tz = new  BaseStatusBean( "0900","应急中心转来的报警") ;
    }

    public static String getCode() {
        return tz.getCode() ;
    }

    public static String getName() {
        return tz.getName() ;
    }

}
