package com.dscomm.iecs.ext.incident.register;

import com.dscomm.iecs.base.bo.BaseStatusBean;

public class INCIDENT_REGISTER_MODE_XFHBLA {

    private static  BaseStatusBean tz ;

    static {
        tz = new  BaseStatusBean( "3","消防后补立案" ) ;
    }

    public static String getCode() {
        return tz.getCode() ;
    }

    public static String getName() {
        return tz.getName() ;
    }

}
