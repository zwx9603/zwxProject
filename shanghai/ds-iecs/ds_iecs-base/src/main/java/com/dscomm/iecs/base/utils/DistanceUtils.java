package com.dscomm.iecs.base.utils;

import com.dscomm.iecs.base.service.bean.LocationRangeBean;

/**
 * 计算 经纬度 距离工具类
 */
public class DistanceUtils {



    /**
     * 地球半径,单位 km
     */
    private static final double EARTH_RADIUS = 6378.137d;


    /**
     * 根据经纬度，计算两点间的距离
     *
     * @param longitude1 第一个点的经度
     * @param latitude1  第一个点的纬度
     * @param longitude2 第二个点的经度
     * @param latitude2  第二个点的纬度
     * @return 返回距离 单位千米
     */
    public static double getDistance(double longitude1, double latitude1, double longitude2, double latitude2) {
        // 纬度
        double lat1 = Math.toRadians(latitude1);
        double lat2 = Math.toRadians(latitude2);
        // 经度
        double lng1 = Math.toRadians(longitude1);
        double lng2 = Math.toRadians(longitude2);
        // 纬度之差
        double a = lat1 - lat2;
        // 经度之差
        double b = lng1 - lng2;
        // 计算两点距离的公式
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(b / 2), 2)));
        // 弧长乘地球半径, 返回单位: 千米
        s =  s * EARTH_RADIUS;
        return s;
    }

    /**
     * 根据经纬度，计算两点间的距离 Haversine
     *
     * @param longitude1 第一个点的经度
     * @param latitude1  第一个点的纬度
     * @param longitude2 第二个点的经度
     * @param latitude2  第二个点的纬度
     * @return 返回距离 单位米( int )
     */
    public static Integer getDistanceRice(double longitude1, double latitude1,
                                      double longitude2, double latitude2) {

        double latDistance = Math.toRadians(longitude1 - longitude2);
        double lngDistance = Math.toRadians(latitude1 - latitude2);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(longitude1)) * Math.cos(Math.toRadians(longitude2))
                * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distanceRice =  c * EARTH_RADIUS * 1000 ;
        int distance = ( int ) Math.floor( distanceRice ) ;
        return  distance  ;
    }



    /**
     * 地球半径,单位 m
     */
    private static final double PI = 3.14159265358979323; //圆周率
    private static final double R = 6371229; //地球的半径

    /**
     * 根据经纬度 距离  计算大体访问
     *
     * @param longitude 经度
     * @param latitude  纬度
     * @param radius  访问
     * @return 返回
     */
    public static LocationRangeBean buildLocationRange(      double longitude , double latitude , Integer radius     ) {
        LocationRangeBean locationRangeBean = new LocationRangeBean() ;
        locationRangeBean.setLat( latitude );
        locationRangeBean.setLng( longitude);
        locationRangeBean.setRadius( radius );

        double  range =  ( 180 * radius ) / (  PI*R  );
        double  longRange = range / ( Math.cos(latitude*PI/180) ) ;

        locationRangeBean.setMaxLat( latitude +  range );
        locationRangeBean.setMinLat( latitude -  range);

        locationRangeBean.setMaxLng( longitude + longRange );
        locationRangeBean.setMinLng( longitude - longRange );

        return  locationRangeBean ;
    }












}
