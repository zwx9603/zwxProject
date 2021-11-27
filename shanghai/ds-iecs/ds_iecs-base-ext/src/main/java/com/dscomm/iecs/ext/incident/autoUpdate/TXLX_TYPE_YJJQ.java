package com.dscomm.iecs.ext.incident.autoUpdate;

import com.dscomm.iecs.base.bo.BaseStatusBean;

public class TXLX_TYPE_YJJQ {

    private static  BaseStatusBean tz ;

    static {
        tz = new  BaseStatusBean( "YJJQ","夜间警情" ) ;
    }

    public static String getCode() {
        return tz.getCode() ;
    }

    public static String getName() {
        return tz.getName() ;
    }

}
