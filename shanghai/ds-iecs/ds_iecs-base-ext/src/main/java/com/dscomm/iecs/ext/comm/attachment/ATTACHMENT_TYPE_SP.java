package com.dscomm.iecs.ext.comm.attachment;

import com.dscomm.iecs.base.bo.BaseStatusBean;

public class ATTACHMENT_TYPE_SP {

    private static  BaseStatusBean tz ;

    static {
        tz = new  BaseStatusBean( "1","视频" ) ;
    }

    public static String getCode() {
        return tz.getCode() ;
    }

    public static String getName() {
        return tz.getName() ;
    }

}
