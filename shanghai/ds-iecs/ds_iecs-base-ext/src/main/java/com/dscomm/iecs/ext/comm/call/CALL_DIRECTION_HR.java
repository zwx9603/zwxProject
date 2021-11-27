package com.dscomm.iecs.ext.comm.call;

import com.dscomm.iecs.base.bo.BaseStatusBean;

public class CALL_DIRECTION_HR {

    private static  BaseStatusBean tz ;

    static {
        tz = new  BaseStatusBean( "1","呼入" ) ;
    }

    public static String getCode() {
        return tz.getCode() ;
    }

    public static String getName() {
        return tz.getName() ;
    }

}
