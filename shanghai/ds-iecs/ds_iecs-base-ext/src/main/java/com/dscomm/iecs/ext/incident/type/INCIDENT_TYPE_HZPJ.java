package com.dscomm.iecs.ext.incident.type;

import com.dscomm.iecs.base.bo.BaseStatusBean;

public class INCIDENT_TYPE_HZPJ {

    private static  BaseStatusBean tz ;

    static {
        tz = new  BaseStatusBean( "10000", "火灾扑救" ) ;
    }

    public static String getCode() {
        return tz.getCode() ;
    }

    public static String getName() {
        return tz.getName() ;
    }

}
