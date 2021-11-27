package com.dscomm.ecs.gateway.dsnetcomm.service.xml;

/**
 * Body 注册器
 * 
 * @author wangxb
 * @since 1.5.2
 */
public class BodyRegister {

	/**
	 * 21消息Code
	 */
	private String code;

	/**
	 * 21消息Body
	 */
	private Class<Body> body;

	/**
	 * 21消息Code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 21消息Code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 21消息Body
	 */
	public Class<Body> getBody() {
		return body;
	}

	/**
	 * 21消息Body
	 */
	public void setBody(Class<Body> body) {
		this.body = body;
	}
}
