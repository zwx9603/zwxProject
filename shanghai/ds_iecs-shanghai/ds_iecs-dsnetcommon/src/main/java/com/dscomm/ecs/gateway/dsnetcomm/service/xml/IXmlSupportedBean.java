/**
 * 
 */
package com.dscomm.ecs.gateway.dsnetcomm.service.xml;

import org.dom4j.Element;

/**
 * 定义了支持XML序列化和反序列化的接口定义
 * 
 * @author Josh Peng
 * 
 */
public interface IXmlSupportedBean {

	/**
	 * 从XML元素中获取信息并填出到当前对象中。
	 * 
	 * @param element
	 *            指定的XML元素
	 */
	public void fromXml(Element element);

	/**
	 * 将本对象中的相关信息输出到指定的XML元素中。
	 * 
	 * @param element
	 *            指定的XML元素
	 * @return 合并好信息的XML元素
	 */
	public Element toXml(Element element);

}
