package com.dscomm.iecs.ext.incident.status;

import com.dscomm.iecs.base.bo.BaseStatusBean;

public class INCIDENT_STATUS_KSZZ {

    private static  BaseStatusBean tz ;

    static {
        tz = new  BaseStatusBean( "06","出水（开始作战）") ;
    }

    public static String getCode() {
        return tz.getCode() ;
    }

    public static String getName() {
        return tz.getName() ;
    }

}
