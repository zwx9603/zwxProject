package com.dscomm.iecs.garage.exception;

import org.mx.error.UserInterfaceError;
import org.mx.error.UserInterfaceException;

/**
 * 描述:坐席模块异常类
 *
 */
public class GarageException extends UserInterfaceException {
    public GarageException(int errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    public GarageException() {
        this(GarageErrors.OTHER_FAIL);
    }

    public GarageException(GarageErrors error) {
        this(error.getErrorCode(), error.getErrorMessage());
    }

    public enum GarageErrors implements UserInterfaceError {
        DATA_NULL("必填字段为空"),
        FIND_DATA_FAIL("数据查询失败"),
        GET_SYSTEMTIME_FAIL("获取当前系统时间失败"),
        STATISTICS_VEHICLE_STATUS_FAIL("车辆状态统计失败"),
        FIND_VEHICLE_FAIL("车辆查询失败"),
        FIND_ORGANIZATION_ON_DUTY_FAIL("值班信息查询失败"),
        FIND_ORGANIZATION_FAIL("机构查询失败"),
        FIND_INCIDENT_FAIL("警情查询失败"),
        FIND_ONDUTY_VEHICLE_TREND_FAIL("出动车辆时间趋势失败"),
        FIND_VEHICLE_DISPATCH_AVG_FAIL("机构出动平均时长查询失败"),
        FIND_STATISTICS_DISPATCH_VEHICLE_COUNT_FAIL("出动车次排行查询失败"),
        OTHER_FAIL("未知异常") ;


        private String errorMessage;
        public static final int BASE_ORDINAL = 1000;

        @Override
        public int getErrorCode() {
            return BASE_ORDINAL + this.ordinal();
        }

        @Override
        public String getErrorMessage() {
            return this.errorMessage;
        }

        GarageErrors(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }
}
