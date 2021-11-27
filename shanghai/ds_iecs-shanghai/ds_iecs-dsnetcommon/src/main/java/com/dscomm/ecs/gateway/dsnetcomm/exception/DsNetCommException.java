/**
 * 
 */
package com.dscomm.ecs.gateway.dsnetcomm.exception;

/**
 * 进行底层通讯时发生异常。
 * 
 * @author Josh Peng
 * 
 */
@SuppressWarnings("serial")
public class DsNetCommException extends Exception {

	/**
	 * 默认的构造函数
	 */
	public DsNetCommException() {
		super();
	}

	/**
	 * 默认的构造函数
	 * 
	 * @param errorMessage
	 *            异常错误信息
	 */
	public DsNetCommException(String errorMessage) {
		super(errorMessage);
	}

	/**
	 * 默认的构造函数
	 * 
	 * @param errorMessage
	 *            异常错误信息
	 * @param errorCode
	 *            异常错误号
	 */
	public DsNetCommException(String errorMessage, int errorCode) {
		super(errorMessage + " Error code: " + errorCode);
	}

	/**
	 * 默认的构造函数
	 * 
	 * @param message
	 *            异常错误信息
	 * @param cause
	 *            导致异常的源
	 */
	public DsNetCommException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 默认的构造函数
	 * 
	 * @param cause
	 *            导致异常的源
	 */
	public DsNetCommException(Throwable cause) {
		super(cause);
	}

}
