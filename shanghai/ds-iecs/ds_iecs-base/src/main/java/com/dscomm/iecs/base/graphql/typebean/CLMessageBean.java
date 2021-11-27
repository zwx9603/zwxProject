package com.dscomm.iecs.base.graphql.typebean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 描述:websocket消息定义
 *
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "msg")
public class CLMessageBean implements java.io.Serializable {

	private static final long serialVersionUID = 749049885185736018L;
	/** 信息体 ：Object */
	private Object body;
	/** 信息头 */
	private CLMsgHeadBean head;

	/** 当前调用该参数所在方法的的账号 （可为空） */
	private String userCode;

	public CLMessageBean() {

	}

	/**
	 *
	 * @param head 消息头
	 * @param body 消息体
	 */
	public CLMessageBean(CLMsgHeadBean head, Object body) {
		this.head = head;
		this.body = body;
	}

	/**
	 *
	 * @param head 消息头
	 */
	public CLMessageBean(CLMsgHeadBean head ) {
		this.head = head;
	}


	public CLMessageBean(String code) {
		this.head = new CLMsgHeadBean(code);
	}

	/**
	 * @return the body
	 */
	@XmlElement(name = "body")
	public Object getBody() {
		return body;
	}

	/**
	 * @return the head
	 */
	@XmlElement(name = "head")
	public CLMsgHeadBean getHead() {
		return head;
	}

	/**
	 * 获取类成员userCode
	 *
	 * @return {@link #userCode}
	 */
	public String getUserCode() {
		return this.userCode;
	}

	/**
	 * @param body
	 *            the body to set
	 */
	public void setBody(Object body) {
		this.body = body;
	}

	/**
	 * @param head
	 *            the head to set
	 */
	public void setHead(CLMsgHeadBean head) {
		this.head = head;
	}

	/**
	 * 设定类成员userCode
	 *
	 * @param userCode
	 *            要设定的{@link #userCode}
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
}
