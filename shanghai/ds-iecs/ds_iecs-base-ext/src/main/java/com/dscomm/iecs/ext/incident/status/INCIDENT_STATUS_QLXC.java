package com.dscomm.iecs.ext.incident.status;

import com.dscomm.iecs.base.bo.BaseStatusBean;

public class INCIDENT_STATUS_QLXC {

    private static  BaseStatusBean tz ;

    static {
        tz = new  BaseStatusBean( "09","清理现场") ;
    }

    public static String getCode() {
        return tz.getCode() ;
    }

    public static String getName() {
        return tz.getName() ;
    }

}
