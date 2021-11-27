package com.dscomm.iecs.schedule.utils;

import org.mx.DateUtils.FieldType;

/**
 * 描述：Schedule 同步工具类
 *
 * @author YangFuXi Date Time 2018/9/21 14:47
 */
public class ScheduleUtil {
    /**
     * 获取同步时间类型
     * @param type 类型参数
     * @return 类型结果
     */
    public static FieldType transFormDateFieldType(int type) {
        switch (type) {
            case 0:
                return FieldType.YEAR;
            case 1:
                return FieldType.MONTH;
            case 2:
                return FieldType.DAY;
            case 3:
                return FieldType.HOUR;
            case 4:
                return FieldType.MINUTE;
            case 5:
                return FieldType.SECOND;
            default:
                return FieldType.HOUR;
        }
    }


}
