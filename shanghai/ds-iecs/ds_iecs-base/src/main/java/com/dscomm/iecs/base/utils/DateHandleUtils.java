package com.dscomm.iecs.base.utils;

import org.apache.logging.log4j.util.Strings;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 描述:时间处理工具类
 *
 * @author YangFuxi
 * Date Time 2019/10/30 11:14
 */
public class DateHandleUtils {


    public static TimeZone buildTimeZone( String timeZoneId ){
        timeZoneId = timeZoneId.replaceAll(" ","") ;
        if(Strings.isBlank( timeZoneId ) ) {
            timeZoneId = "GMT+8" ; //时区为空 默认 北京东八区时区
        }
        return  TimeZone.getTimeZone( timeZoneId );
    }

    /**
     * 获取时间
     *
     * @return 返回计算后的时间
     */
    public static Calendar buildCalendar( String timeZoneId   ) {
        return  Calendar.getInstance( buildTimeZone( timeZoneId)  );

    }


    /**
     * 根据配置项获取时间
     *
     * @param start  开始时间
     * @param type   相加时间单位类型
     * @param offset 相加时间单位长度
     * @return 返回计算后的时间
     */
    public static Date addTime(Date start, String type, int offset) {
        switch (type) {
            case "year":
                return org.mx.DateUtils.add(start, org.mx.DateUtils.FieldType.YEAR, offset);
            case "month":
                return org.mx.DateUtils.add(start, org.mx.DateUtils.FieldType.MONTH, offset);
            case "day":
                return org.mx.DateUtils.add(start, org.mx.DateUtils.FieldType.DAY, offset);
            case "hour":
                return org.mx.DateUtils.add(start, org.mx.DateUtils.FieldType.HOUR, offset);
            case "minute":
                return org.mx.DateUtils.add(start, org.mx.DateUtils.FieldType.MINUTE, offset);
            case "second":
                return org.mx.DateUtils.add(start, org.mx.DateUtils.FieldType.SECOND, offset);
            default:
                return start;
        }
    }

    /**
     * 通过当前时间戳 得到  年-月-日 时24-分-秒
     * 时间字符串格式  yyyy-MM-dd HH:mm:ss
     * @param time
     * @return
     * @throws ParseException
     */
    public static String  getDateyyyyMMddHHmmss ( String timeZoneId , long time) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"   );
        dateFormat.setTimeZone( buildTimeZone( timeZoneId ) );
        Calendar calendar =  buildCalendar( timeZoneId   ) ;
        dateFormat.format(calendar.getTime());
        return dateFormat.format(time) ;
    }

    /**
     * 通过当前时间戳 得到  年-月-日 时24-分-秒

     * @param time
     * @return
     * @throws ParseException
     */
    public static String  getDatePattern ( String timeZoneId , long time , String pattern ) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern   );
        dateFormat.setTimeZone( buildTimeZone( timeZoneId ) );
        Calendar calendar =  buildCalendar( timeZoneId   ) ;
        dateFormat.format(calendar.getTime());
        return dateFormat.format(time) ;
    }

}
