package com.dscomm.iecs.ext.incident.source;

import com.dscomm.iecs.base.bo.BaseStatusBean;

public class INCIDENT_SOURCE_FAS {

    private static  BaseStatusBean tz ;

    static {
        tz = new  BaseStatusBean( "300","FAS" ) ;
    }

    public static String getCode() {
        return tz.getCode() ;
    }

    public static String getName() {
        return tz.getName() ;
    }

}
