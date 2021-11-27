package com.dscomm.iecs.ext.relation;

import com.dscomm.iecs.base.bo.BaseStatusBean;

public class RELATION_OBJECT_XCTP {

    private static  BaseStatusBean tz ;

    static {
        tz = new  BaseStatusBean( "XCXX","现场图片" ) ;
    }

    public static String getCode() {
        return tz.getCode() ;
    }

    public static String getName() {
        return tz.getName() ;
    }

}
