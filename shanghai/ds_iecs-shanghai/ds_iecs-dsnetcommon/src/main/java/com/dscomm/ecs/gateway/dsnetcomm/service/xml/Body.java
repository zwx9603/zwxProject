/**
 * 
 */
package com.dscomm.ecs.gateway.dsnetcomm.service.xml;

import org.dom4j.Element;

import java.io.Serializable;

/**
 * DS21通讯协议XML中的详细指令内容信息定义。<br>
 * 这是一个超类，所有具体的命令消息体都应该从此继承实现。
 * 
 * @author Josh Peng
 * 
 */

/**
 * @author Josh Peng
 * 
 */
public class Body implements Serializable, IXmlSupportedBean {

	private static final long serialVersionUID = 8711815684749238184L;

	/**
	 * 默认的构造函数
	 */
	public Body() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dscomm.dsnetcomm.bean.IXmlSupportedBean#fromXml(org.dom4j.Element)
	 */
	public void fromXml(Element element) {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dscomm.dsnetcomm.bean.IXmlSupportedBean#toXml(org.dom4j.Element)
	 */
	public Element toXml(Element element) {
		return element;
	}

}
