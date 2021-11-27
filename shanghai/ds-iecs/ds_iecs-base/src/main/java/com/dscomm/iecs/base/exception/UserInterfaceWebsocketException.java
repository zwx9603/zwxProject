package com.dscomm.iecs.base.exception;

import org.mx.error.UserInterfaceError;
import org.mx.error.UserInterfaceException;
import org.mx.spring.utils.I18nMessageUtils;

/**
 * 描述:接警受理模块异常类定义
 *
 * @author YangFuxi
 * Date Time 2019/7/21 14:50
 */
public class UserInterfaceWebsocketException extends UserInterfaceException {

    public UserInterfaceWebsocketException(WebsocketErrors error) {
        super(error.getErrorCode(), error.getErrorMessage());
    }

    public UserInterfaceWebsocketException() {
        this(WebsocketErrors.OTHER_FAIL);
    }

    public enum WebsocketErrors implements UserInterfaceError {
        OTHER_FAIL,
        WEBSOCKET_CONNECT_FAIL("websocket连接失败"),
        WEBSOCKET_CONNECT_URL_NULL("websocket连接ip为空"),
        WEBSOCKET_MESSAGE_SEND_FAIL("websocket消息发送失败.");
        private String errorMessage;
        public static final int BASE_ORDINAL = 4000;

        WebsocketErrors() {
        }

        WebsocketErrors(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        @Override
        public int getErrorCode() {
            return BASE_ORDINAL + this.ordinal();
        }

        @Override
        public String getErrorMessage() {
            return I18nMessageUtils.getI18nMessage(super.name(), this.errorMessage);
        }


    }
}
