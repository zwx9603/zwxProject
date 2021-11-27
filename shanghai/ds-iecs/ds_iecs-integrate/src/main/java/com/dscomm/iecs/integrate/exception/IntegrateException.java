package com.dscomm.iecs.integrate.exception;

import org.mx.error.UserInterfaceError;
import org.mx.error.UserInterfaceException;

/**
 * 描述: 整合 模块异常类
 *
 * @author YangFuxi
 * Date Time 2020/4/9 12:38
 */
public class IntegrateException extends UserInterfaceException {
    public IntegrateException(int errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    public IntegrateException() {
        this(IntegrateErrors.OTHER_FAIL);
    }

    public IntegrateException(IntegrateErrors error) {
        this(error.getErrorCode(), error.getErrorMessage());
    }

    public enum  IntegrateErrors implements UserInterfaceError {
        DATA_NULL("必填字段为空"),
        FIND_DATA_NULL("数据为空"),
        REQUEST_OUT_SIDE_FAIL("请求外部接口失败"),
        REPORT_FAIL("上报失败"),
        TTS_SPEECH_FAIL("tts 通知失败"),
        OTHER_FAIL("未知异常");


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

        IntegrateErrors(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }
}
