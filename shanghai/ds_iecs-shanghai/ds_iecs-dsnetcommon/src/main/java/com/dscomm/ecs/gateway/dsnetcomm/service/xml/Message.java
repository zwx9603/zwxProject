/**
 * 
 */
package com.dscomm.ecs.gateway.dsnetcomm.service.xml;

import org.dom4j.Element;

import java.io.Serializable;

/**
 * DS21通信协议XML中消息头信息定义
 * 
 * @author Josh Peng
 * 
 */
public class Message implements Serializable, IXmlSupportedBean {
	private static final long serialVersionUID = -1166278947074153021L;
	private String code = "", sendTime = "", ver = "1.0"; // 消息号，发送时间，版本号。
	private int transType = 0; // 通讯方式，默认为点对点通讯。

	/**
	 * 默认的构造函数
	 */
	public Message() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dscomm.dsnetcomm.bean.IXmlSupportedBean#fromXml(org.dom4j.Element)
	 */
	public void fromXml(Element element) {
		setCode(element.attributeValue("Code", ""));
		setSendTime(element.attributeValue("SendTime", ""));
		setVer(element.attributeValue("Ver", "1.0"));
		int transType = Integer.parseInt(element.attributeValue("TransType",
				"0"));
		setTransType(transType);
	}

	/**
	 * 获取消息号
	 * 
	 * @return 消息号
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 获取发送时间
	 * 
	 * @return 发送时间
	 */
	public String getSendTime() {
		return sendTime;
	}

	/**
	 * 获取传输模式
	 * 
	 * @return 传输模式
	 */
	public int getTransType() {
		return transType;
	}

	/**
	 * 获取版本号
	 * 
	 * @return 版本号
	 */
	public String getVer() {
		return ver;
	}

	/**
	 * 设置消息号
	 * 
	 * @param code
	 *            消息号
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 设置版本号
	 * 
	 * @param ver
	 *            版本号
	 */
	public void setVer(String ver) {
		this.ver = ver;
	}

	/**
	 * 设置发送时间
	 * 
	 * @param sendTime
	 *            发送时间
	 */
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	/**
	 * 设置传输方式：点对点（0）、广播（1 | 2）等。
	 * 
	 * @param transType
	 *            传输方式
	 */
	public void setTransType(int transType) {
		this.transType = transType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dscomm.dsnetcomm.bean.IXmlSupportedBean#toXml(org.dom4j.Element)
	 */
	public Element toXml(Element element) {
		element.addAttribute("Code", getCode());
		element.addAttribute("SendTime", getSendTime());
		element.addAttribute("Ver", getVer());
		element.addAttribute("TransType", String.valueOf(getTransType()));
		return element;
	}

}
