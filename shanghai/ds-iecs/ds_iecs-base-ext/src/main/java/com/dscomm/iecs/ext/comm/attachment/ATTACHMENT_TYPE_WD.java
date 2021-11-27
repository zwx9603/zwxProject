package com.dscomm.iecs.ext.comm.attachment;

import com.dscomm.iecs.base.bo.BaseStatusBean;

public class ATTACHMENT_TYPE_WD {

    private static  BaseStatusBean tz ;

    static {
        tz = new  BaseStatusBean( "3","文档" ) ;
    }

    public static String getCode() {
        return tz.getCode() ;
    }

    public static String getName() {
        return tz.getName() ;
    }

}
