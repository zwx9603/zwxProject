/**
 * 
 */
package com.dscomm.ecs.gateway.dsnetcomm.service.xml;

import org.dom4j.Element;

import java.io.Serializable;

/**
 * DS21通讯协议XML中的头信息定义
 * 
 * @author Josh Peng
 * 
 */
public class Head implements Serializable, IXmlSupportedBean {

	private static final long serialVersionUID = 4771971205475593478L;
	private String ver = "1.0"; // 版本号，不同版本的头信息中可能结构不同。
	private Address src = null, dst = null; // 命令发送源和目标信息
	private Message msg = null; // 消息头信息

	/**
	 * 默认的构造函数
	 */
	public Head() {
		super();
		src = new Address();
		dst = new Address();
		msg = new Message();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dscomm.dsnetcomm.bean.IXmlSupportedBean#fromXml(org.dom4j.Element)
	 */
	public void fromXml(Element element) {
		if (element == null) {
			return;
		}
		ver = element.attributeValue("Ver", "1.0");
		Element elSrc = element.element("Src");
		getSrc().fromXml(elSrc);
		Element elDst = element.element("Dst");
		getDst().fromXml(elDst);
		Element elMsg = element.element("Msg");
		getMsg().fromXml(elMsg);
	}

	/**
	 * 获取发送目标
	 * 
	 * @return 目标地址对象
	 */
	public Address getDst() {
		return dst;
	}

	/**
	 * 获取消息头
	 * 
	 * @return 消息头对象
	 */
	public Message getMsg() {
		return msg;
	}

	/**
	 * 获取发送源
	 * 
	 * @return 源地址对象
	 */
	public Address getSrc() {
		return src;
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
	 * 设置版本号
	 * 
	 * @param ver
	 *            版本号
	 */
	public void setVer(String ver) {
		this.ver = ver;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dscomm.dsnetcomm.bean.IXmlSupportedBean#toXml(org.dom4j.Element)
	 */
	public Element toXml(Element element) {
		element.addAttribute("Ver", getVer());
		Element elSrc = element.addElement("Src");
		getSrc().toXml(elSrc);
		Element elDst = element.addElement("Dst");
		getDst().toXml(elDst);
		Element elMsg = element.addElement("Msg");
		getMsg().toXml(elMsg);
		return element;
	}

}
