package com.dscomm.iecs.ext.comm.document;

import com.dscomm.iecs.base.bo.BaseStatusBean;

public class DOCUMENT_TYPE_DPYJLDDW {

    private static  BaseStatusBean tz ;

    static {
        tz = new  BaseStatusBean( "06","调派应急联动单位" ) ;
    }

    public static String getCode() {
        return tz.getCode() ;
    }

    public static String getName() {
        return tz.getName() ;
    }

}
