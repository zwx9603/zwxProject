package com.dscomm.iecs.ext.incident.nature;

import com.dscomm.iecs.base.bo.BaseStatusBean;

public class INCIDENT_NATURE_JJ {

    private static  BaseStatusBean tz ;

    static {
        tz = new  BaseStatusBean( "1", "假警" ) ;
    }

    public static String getCode() {
        return tz.getCode() ;
    }

    public static String getName() {
        return tz.getName() ;
    }

}
