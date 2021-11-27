/**
 *
 */
package com.dscomm.ecs.gateway.dsnetcomm.exception;

/**
 * DS通信代理为空的异常，这将导致不能通信。
 *
 * @author Josh Peng
 */
@SuppressWarnings("serial")
public class DsCommProxyNullException extends DsNotifyException {
    private static final String errorMessage = "The DS communication proxy is null.";

    /**
     * 默认的构造函数
     */
    public DsCommProxyNullException() {

    }

    /**
     * 默认的构造函数
     *
     * @param cause 导致异常的源
     */
    public DsCommProxyNullException(Throwable cause) {
    }


}
