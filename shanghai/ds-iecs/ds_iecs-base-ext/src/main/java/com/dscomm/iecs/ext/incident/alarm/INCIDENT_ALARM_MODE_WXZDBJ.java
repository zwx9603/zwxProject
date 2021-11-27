package com.dscomm.iecs.ext.incident.alarm;

import com.dscomm.iecs.base.bo.BaseStatusBean;

public class INCIDENT_ALARM_MODE_WXZDBJ {

    private static  BaseStatusBean tz ;

    static {
        tz = new  BaseStatusBean( "0402","无线自动报警") ;
    }

    public static String getCode() {
        return tz.getCode() ;
    }

    public static String getName() {
        return tz.getName() ;
    }

}
