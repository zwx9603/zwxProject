package com.dscomm.iecs.intelligent.exception;

import org.mx.error.UserInterfaceError;
import org.mx.error.UserInterfaceException;

public class IntellligentException extends UserInterfaceException {
    public IntellligentException(int errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    public IntellligentException() {
        this(IntellligentErrors.OTHER_FAIL);
    }

    public IntellligentException(IntellligentErrors errors) {
        this(errors.getErrorCode(), errors.getErrorMessage());
    }

    ;public enum IntellligentErrors implements UserInterfaceError {

        DATA_NULL("必填字段为空"),
        FIND_DATA_NULL("数据为空"),
        OTHER_FAIL("未知异常");


        private String errorMessage;
        public static final int BASE_ORDINAL = 1100;

        @Override
        public int getErrorCode() {
            return BASE_ORDINAL + this.ordinal();
        }

        @Override
        public String getErrorMessage() {
            return this.errorMessage;
        }

        IntellligentErrors(String errorMessage) {
            this.errorMessage = errorMessage;
        }

    }


}
