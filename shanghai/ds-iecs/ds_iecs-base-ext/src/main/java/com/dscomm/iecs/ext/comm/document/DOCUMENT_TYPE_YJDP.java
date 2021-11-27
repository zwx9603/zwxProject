package com.dscomm.iecs.ext.comm.document;

import com.dscomm.iecs.base.bo.BaseStatusBean;

public class DOCUMENT_TYPE_YJDP {

    private static  BaseStatusBean tz ;

    static {
        tz = new  BaseStatusBean( "02","一键调派" ) ;
    }

    public static String getCode() {
        return tz.getCode() ;
    }

    public static String getName() {
        return tz.getName() ;
    }

}
