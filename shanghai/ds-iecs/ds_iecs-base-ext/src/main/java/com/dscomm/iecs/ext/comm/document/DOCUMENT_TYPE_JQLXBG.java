package com.dscomm.iecs.ext.comm.document;

import com.dscomm.iecs.base.bo.BaseStatusBean;

public class DOCUMENT_TYPE_JQLXBG {

    private static  BaseStatusBean tz ;

    static {
        tz = new  BaseStatusBean( "13","案件类型变更") ;
    }

    public static String getCode() {
        return tz.getCode() ;
    }

    public static String getName() {
        return tz.getName() ;
    }

}
