package com.dscomm.ecs.gateway.dsnetcomm.exception;

import org.mx.error.UserInterfaceError;
import org.mx.error.UserInterfaceException;
import org.mx.spring.utils.I18nMessageUtils;

/**
 * 描述:21异常定义类
 *
 * @author YangFuxi
 * Date Time 2020/9/17 10:07
 */
public class DsNotifyException extends UserInterfaceException {
    public DsNotifyException(int errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    public DsNotifyException(DsNetErrors error) {
        this(error.getErrorCode(),error.getErrorMessage());
    }

    public DsNotifyException() {
        this(DsNetErrors.OTHER_FAIL);
    }

    public enum DsNetErrors implements UserInterfaceError {
        SEND_21_MESSAGE_FAIL("发送21消息失败"),
        IVS_GET_DOMAINROUTE_FAIL("获取ivs系统域路由列表失败"),
        IVS_GET_DEVICELIST_FAIL("获取ivs子设备列表失败"),
        LOG_IN_FAIL("用户登录失败"),
        IVS_LOGOUT_FAIL("用户注销失败"),
        IVS_REGEDIT_CALLBACK_FAIL("注册回调信息失败"),
        IVS_GET_USERID_FAIL("获取当前用户ID失败"),
        OTHER_FAIL("未知异常"),
        DATA_NULL("必填字段为空"),
        IVS_SUBSCRIBE_ALARM_FAIL("ivs订阅告警服务失败"),
        MODIFY_IVS_CACHE_FAIL("修改ivs缓存失败"),
        GET_IVS_CACHE_FAIL("获取ivs缓存失败"),
        GET_COOKIE_FAIL("获取cookie失败"),
        IVS_KEEP_ALIVE_FAIL("ivs服务保持连接失败"),
        REMOTE_CALL_IVS_FAIL("ivs调用失败"),
        ;
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

        DsNetErrors(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }
}
