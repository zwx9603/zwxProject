package com.dscomm.iecs.schedule.exception;

import org.mx.error.UserInterfaceError;
import org.mx.error.UserInterfaceException;

/**
 * 描述:接警受理模块异常类
 *
 * @author YangFuxi
 * Date Time 2020/4/9 12:38
 */
public class ScheduleException extends UserInterfaceException {
    public ScheduleException(int errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    public ScheduleException() {
        this(ScheduleErrors.OTHER_FAIL);
    }

    public ScheduleException(ScheduleErrors error) {
        this(error.getErrorCode(), error.getErrorMessage());
    }

    public enum ScheduleErrors implements UserInterfaceError {
        SCHEDULE_DATA_FAIL("同步数据查询失败"),
        SAVE_DATA_FAIL("保存数据失败!"),
        OTHER_FAIL("未知异常");

        private String errorMessage;
        public static final int BASE_ORDINAL = 8000;

        @Override
        public int getErrorCode() {
            return BASE_ORDINAL + this.ordinal();
        }

        @Override
        public String getErrorMessage() {
            return this.errorMessage;
        }

        ScheduleErrors(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }
}
