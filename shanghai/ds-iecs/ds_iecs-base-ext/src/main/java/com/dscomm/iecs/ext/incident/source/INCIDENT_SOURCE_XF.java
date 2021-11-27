package com.dscomm.iecs.ext.incident.source;

import com.dscomm.iecs.base.bo.BaseStatusBean;

public class INCIDENT_SOURCE_XF {

    private static  BaseStatusBean tz ;

    static {
        tz = new  BaseStatusBean( "100","消防" ) ;
    }

    public static String getCode() {
        return tz.getCode() ;
    }

    public static String getName() {
        return tz.getName() ;
    }

}
