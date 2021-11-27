package com.dscomm.iecs.ext.incident.autoUpdate;

import com.dscomm.iecs.base.bo.BaseStatusBean;

public class TXLX_TYPE_RSMJ {

    private static  BaseStatusBean tz ;

    static {
        tz = new  BaseStatusBean( "RSMJ","燃烧面积" ) ;
    }

    public static String getCode() {
        return tz.getCode() ;
    }

    public static String getName() {
        return tz.getName() ;
    }

}
