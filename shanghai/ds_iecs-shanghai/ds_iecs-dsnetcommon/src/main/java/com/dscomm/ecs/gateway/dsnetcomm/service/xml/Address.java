/**
 * 
 */
package com.dscomm.ecs.gateway.dsnetcomm.service.xml;

import org.dom4j.Element;

import java.io.Serializable;

/**
 * DS21通信协议XML中的源/目标地址定义
 * 
 * @author Josh Peng
 * 
 */
public class Address implements Serializable, IXmlSupportedBean {
	private static final long serialVersionUID = -2837815463059796742L;
	private String addr = "", type = "", id = ""; // 地址，类型，ID（台号）

	/**
	 * 默认的构造函数
	 */
	public Address() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dscomm.dsnetcomm.bean.IXmlSupportedBean#fromXml(org.dom4j.Element)
	 */
	public void fromXml(Element element) {
		setAddr(element.attributeValue("Addr", ""));
		setType(element.attributeValue("Type", ""));
		setId(element.attributeValue("Id", ""));
	}

	/**
	 * 地址
	 * 
	 * @return 地址
	 */
	public String getAddr() {
		return addr;
	}

	/**
	 * 地址ID
	 * 
	 * @return ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * 获取地址类型/超类型号
	 * 
	 * @return 超类型
	 */
	public String getType() {
		return type;
	}

	/**
	 * 地址
	 * 
	 * @param addr
	 *            真正的地址内容，对于DS21和DS11而言，此为空。
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}

	/**
	 * 地址ID，一般是台号。
	 * 
	 * @param id
	 *            ID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 设置地址类型，这里是我们定义的节点超类型，分别可以根据DS21或者DS11进行映射。
	 * 
	 * @param type
	 *            节点超类型
	 */
	public void setType(String type) {
		this.type = type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dscomm.dsnetcomm.bean.IXmlSupportedBean#toXml(org.dom4j.Element)
	 */
	public Element toXml(Element element) {
		element.addAttribute("Addr", getAddr());
		element.addAttribute("Type", getType());
		element.addAttribute("Id", getId());
		return element;
	}

}
