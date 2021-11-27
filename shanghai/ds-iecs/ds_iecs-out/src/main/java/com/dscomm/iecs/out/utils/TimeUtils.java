package com.dscomm.iecs.out.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
    public  static  Long transformaStamp ( String str ) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(str);
        long ts = date.getTime();
        return ts;
    }
    public  static  String transformaStrTime( Long timeStamp ) throws ParseException {
        if (null == timeStamp) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date(timeStamp));
        return date;
    }



}
