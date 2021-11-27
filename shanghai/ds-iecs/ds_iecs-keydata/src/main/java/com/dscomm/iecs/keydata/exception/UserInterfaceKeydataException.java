package com.dscomm.iecs.keydata.exception;

import org.mx.error.UserInterfaceError;
import org.mx.error.UserInterfaceException;
import org.mx.spring.utils.I18nMessageUtils;

/**
 * 描述:接警受理模块异常类定义
 *
 * @author YangFuxi
 * Date Time 2019/7/21 14:50
 */
public class UserInterfaceKeydataException extends UserInterfaceException {

    public UserInterfaceKeydataException(KeydataErrors error) {
        super(error.getErrorCode(), error.getErrorMessage());
    }

    public UserInterfaceKeydataException() {
        this(KeydataErrors.OTHER_FAIL);
    }

    public enum KeydataErrors implements UserInterfaceError {

        DATA_NULL("必填字段为空"),
        SAVE_KEYDATACHANGERECORD_FAIL("保存关键数据变更记录失败"),
        TIMELINE_SAVE_FAIL("时间轴保存失败."),
        GET_SYSTEMTIME_FAIL("获取当前系统时间失败"),
        GET_HEXSRING_KEY_FAIL("获取加密key失败"),
        FIND_AUDITLOG_FAIL("获取审计日志失败"),
        OTHER_FAIL("未知异常") ;

        private String errorMessage;
        public static final int BASE_ORDINAL = 2000;

        @Override
        public int getErrorCode() {
            return BASE_ORDINAL + this.ordinal();
        }

        @Override
        public String getErrorMessage() {
            return I18nMessageUtils.getI18nMessage(super.name(), this.errorMessage);
        }

        KeydataErrors() {
        }

        KeydataErrors(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }
}
