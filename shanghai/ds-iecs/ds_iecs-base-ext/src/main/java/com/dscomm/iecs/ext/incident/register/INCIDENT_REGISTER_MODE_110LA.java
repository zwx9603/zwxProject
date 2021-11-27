package com.dscomm.iecs.ext.incident.register;

import com.dscomm.iecs.base.bo.BaseStatusBean;

public class INCIDENT_REGISTER_MODE_110LA {

    private static  BaseStatusBean tz ;

    static {
        tz = new  BaseStatusBean( "2","110立案" ) ;
    }

    public static String getCode() {
        return tz.getCode() ;
    }

    public static String getName() {
        return tz.getName() ;
    }

}
