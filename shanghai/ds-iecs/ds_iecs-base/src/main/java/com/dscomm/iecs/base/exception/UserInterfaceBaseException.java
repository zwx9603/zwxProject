package com.dscomm.iecs.base.exception;

import org.mx.error.UserInterfaceError;
import org.mx.error.UserInterfaceException;
import org.mx.spring.utils.I18nMessageUtils;

/**
 * 描述: 基础 模块异常类定义
 *
 * @author YangFuxi
 * Date Time 2019/7/21 14:50
 */
public class UserInterfaceBaseException extends UserInterfaceException {

    public UserInterfaceBaseException(BaseErrors error) {
        super(error.getErrorCode(), error.getErrorMessage());
    }

    public UserInterfaceBaseException() {
        this(BaseErrors.OTHER_FAIL);
    }

    public enum BaseErrors implements UserInterfaceError {

        DATA_NULL("必填字段为空"),
        DECRYPT_FAIL("解密失败"),
        OTHER_FAIL("未知异常");

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

        BaseErrors() {
        }

        BaseErrors(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }
}
