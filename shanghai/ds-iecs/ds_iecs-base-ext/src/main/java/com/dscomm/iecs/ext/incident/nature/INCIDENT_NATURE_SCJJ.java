package com.dscomm.iecs.ext.incident.nature;

import com.dscomm.iecs.base.bo.BaseStatusBean;

public class INCIDENT_NATURE_SCJJ {

    private static  BaseStatusBean tz ;

    static {
        tz = new  BaseStatusBean( "9", "其他(删除警情)" ) ;
    }

    public static String getCode() {
        return tz.getCode() ;
    }

    public static String getName() {
        return tz.getName() ;
    }

}
