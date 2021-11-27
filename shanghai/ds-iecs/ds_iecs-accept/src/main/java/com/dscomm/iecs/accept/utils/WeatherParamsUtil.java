package com.dscomm.iecs.accept.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class WeatherParamsUtil {

    /**
     * 风速获取 风力等级信息
     * @param windPowerValue
     * @return
     */
    public static List<String> windPower(String windPowerValue) {
        if (Objects.nonNull(windPowerValue)) {
            Float value = Float.valueOf(windPowerValue);
            if (value >= 0.0f && value <= 0.2f) {
                return Arrays.asList("01", "0");
            } else if (value >= 0.3f && value <= 1.5f) {
                return Arrays.asList("02", "1");
            } else if (value >= 1.6f && value <= 3.3f) {
                return Arrays.asList("03", "2");
            } else if (value >= 3.4f && value <= 5.4f) {
                return Arrays.asList("04", "3");
            } else if (value >= 5.5f && value <= 7.9f) {
                return Arrays.asList("05", "4");
            } else if (value >= 8.0f && value <= 10.7f) {
                return Arrays.asList("06", "5");
            } else if (value >= 10.8f && value <= 13.8f) {
                return Arrays.asList("07", "6");
            } else if (value >= 13.9f && value <= 17.1f) {
                return Arrays.asList("08", "7");
            } else if (value >= 17.2f && value <= 20.7f) {
                return Arrays.asList("09", "8");
            } else if (value >= 20.8f && value <= 24.4f) {
                return Arrays.asList("10", "9");
            } else if (value >= 24.5f && value <= 28.4f) {
                return Arrays.asList("11", "10");
            } else if (value >= 28.5f && value <= 32.6f) {
                return Arrays.asList("12", "11");
            } else if (value >= 32.7f && value <= 36.9f) {
                return Arrays.asList("13", "12");
            } else if (value >= 37.0f && value <= 41.4f) {
                return Arrays.asList("14", "13");
            } else if (value >= 41.5f && value <= 46.1f) {
                return Arrays.asList("15", "14");
            } else if (value >= 46.2f && value <= 50.9f) {
                return Arrays.asList("16", "15");
            } else if (value >= 51.0f && value <= 56.0f) {
                return Arrays.asList("17", "16");
            } else if (value >= 56.1f && value <= 61.2f) {
                return Arrays.asList("18", "17");
            } else if (value > 61.3f) {
                return Arrays.asList("19", "18");
            }
        }
        return null;
    }

    /**
     * 风向信息
     * @param value
     * @return
     */
    public  static  List<String> windDirection(String value){
        Float vf = Float.valueOf(value);
        if (vf >= 348.76f && vf <= 360f || vf >= 0f && vf <= 11.25f){
            return Arrays.asList("北","N","0");
        }else if (vf >= 11.26f && vf <= 33.75f){
            return Arrays.asList("北东北","NNE","22.5");
        }else if (vf >= 33.76f && vf <= 56.25f){
            return Arrays.asList("东北","NE","45");
        }else if (vf >= 56.26f && vf <= 78.75f){
            return Arrays.asList("东东北","NNE","67.5");
        }else if (vf >= 78.76f && vf <= 101.25f){
            return Arrays.asList("东","E","90");
        }else if (vf >= 101.26f && vf <= 123.75f){
            return Arrays.asList("东东南","ESE","112.5");
        }else if (vf >= 123.76f && vf <= 146.25f){
            return Arrays.asList("东南","SE","135");
        }else if (vf >= 146.26f && vf <= 168.75f){
            return Arrays.asList("南东南","SSE","157.5");
        }else if (vf >= 168.76f && vf <= 191.25f){
            return Arrays.asList("南","S","180");
        }else if (vf >= 191.26f && vf <= 213.75f){
            return Arrays.asList("南西南","SSW","202.5");
        }else if (vf >= 213.76f && vf <= 236.25f){
            return Arrays.asList("西南","SW","225");
        }else if (vf >= 236.26f && vf <= 258.75f){
            return Arrays.asList("西西南","WSW","247.5");
        }else if (vf >= 258.76f && vf <= 281.25f){
            return Arrays.asList("西","W","270");
        }else if (vf >= 281.26f && vf <= 303.75f){
            return Arrays.asList("西西北","WNW","295.5");
        }else if (vf >= 303.76f && vf <= 326.25f){
            return Arrays.asList("西北","NW","315");
        }else if (vf >= 326.26f && vf <= 348.75f){
            return Arrays.asList("北西北","NNW","337.5");
        }
        return null;
    }


}
