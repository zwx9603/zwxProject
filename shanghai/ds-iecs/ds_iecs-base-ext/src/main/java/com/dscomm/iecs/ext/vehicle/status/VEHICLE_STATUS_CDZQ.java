package com.dscomm.iecs.ext.vehicle.status;

import com.dscomm.iecs.base.bo.BaseStatusBean;

public class VEHICLE_STATUS_CDZQ {

    private static  BaseStatusBean tz ;

    static {
        tz = new  BaseStatusBean( "0306", "执勤" ) ;
    }

    public static String getCode() {
        return tz.getCode() ;
    }

    public static String getName() {
        return tz.getName() ;
    }

}
