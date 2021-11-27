package com.dscomm.iecs.ext.incident.nature;

import com.dscomm.iecs.base.bo.BaseStatusBean;

public class INCIDENT_NATURE_CWJJ {

    private static  BaseStatusBean tz ;

    static {
        tz = new  BaseStatusBean( "3", "错位接警" ) ;
    }

    public static String getCode() {
        return tz.getCode() ;
    }

    public static String getName() {
        return tz.getName() ;
    }

}
