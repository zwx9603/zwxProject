/**
 *
 */
package com.dscomm.ecs.gateway.dsnetcomm.service.xml;

import com.dscomm.ecs.gateway.dsnetcomm.exception.XmlGenerateFail;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;

/**
 * 基于DS21通讯协议的XML载体
 *
 * @author Josh Peng
 */
public class MainMsg implements Serializable, IXmlSupportedBean {
    private static final long serialVersionUID = -967768762494283650L;
    private String vdr = "DS"; // 提供商
    private Head head = null; // 头信息
    private Body body = null; // 消息体信息
    private BodyFactory bodyFactory = null; // 消息体工厂

    /**
     * 默认的构造函数
     */
    public MainMsg() {
        super();
        head = new Head();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.dscomm.dsnetcomm.bean.IXmlSupportedBean#fromXml(org.dom4j.Element)
     */
    public void fromXml(Element elRoot) {
        setVdr(elRoot.attributeValue("Vdr", "DS"));
        Element elHead = elRoot.element("Head");
        getHead().fromXml(elHead);
        Body body = bodyFactory != null ? bodyFactory.createBody(getHead())
                : null;
        setBody(body != null ? body : new DefaultBody());
        if (getBody() != null) {
            Element elBody = elRoot.element("Body");
            getBody().fromXml(elBody);
        }
    }

    /**
     * 从XML字串中解析命令消息信息，并填充到本消息对象中。
     *
     * @param xml XML字串
     * @throws DocumentException
     */
    public void fromXml(String xml) throws DocumentException {
        // 处理了有些消息</MainMsg>后面会跟些乱码的情况
        Document document = DocumentHelper.parseText(xml.substring(0,
                xml.indexOf("</MainMsg>"))
                + "</MainMsg>");
        Element elRoot = document.getRootElement();
        fromXml(elRoot);
    }

    /**
     * 获取消息体
     *
     * @return 消息体
     */
    public Body getBody() {
        return body;
    }

    /**
     * 获取消息头
     *
     * @return 消息头
     */
    public Head getHead() {
        return head;
    }

    /**
     * 获取提供商
     *
     * @return 提供商
     */
    public String getVdr() {
        return vdr;
    }

    /**
     * 设置消息体
     *
     * @param body 消息体
     */
    public void setBody(Body body) {
        this.body = body;
    }

    /**
     * 设置消息体工厂
     *
     * @param factory 工厂
     */
    public void setBodyFactory(BodyFactory factory) {
        this.bodyFactory = factory;
    }

    /**
     * 设置提供商，默认为“DS”。
     *
     * @param vdr 提供商
     */
    public void setVdr(String vdr) {
        this.vdr = vdr;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.dscomm.dsnetcomm.bean.IXmlSupportedBean#toXml(org.dom4j.Element)
     */
    public Element toXml(Element element) {
        element.addAttribute("Vdr", getVdr());
        Element elHead = element.addElement("Head");
        getHead().toXml(elHead);
        Element elBody = element.addElement("Body");
        getBody().toXml(elBody);
        return element;
    }

    /**
     * 将本消息命令对象输出为紧凑格式的XML字串。
     *
     * @return XML字串
     * @throws XmlGenerateFail
     */
    public String toXmlString() throws XmlGenerateFail {
        return toXmlString("UTF-8", true);
    }

    /**
     * 将本消息命令对象输出为紧凑格式的XML字串。
     *
     * @return XML字串
     * @throws XmlGenerateFail
     */
    public String toXmlString(String encoding) throws XmlGenerateFail {
        return toXmlString(encoding, true);
    }

    /**
     * 将本消息命令对象输出为XML表示的字串。
     *
     * @param isCompact 设置为true表示输出为紧凑格式，否则为良好可读性格式。
     * @return XML字串
     * @throws XmlGenerateFail
     */
    public String toXmlString(String encoding, boolean isCompact)
            throws XmlGenerateFail {
        OutputFormat format = isCompact ? OutputFormat.createCompactFormat()
                : OutputFormat.createPrettyPrint();
        format.setEncoding(encoding);
        format.setOmitEncoding(false);
        StringWriter writer = new StringWriter();
        XMLWriter xmlWriter = new XMLWriter(writer, format);
        String xmlString = null;
        Document document = DocumentHelper.createDocument();
        Element elRoot = document.addElement("MainMsg");
        elRoot = toXml(elRoot);
        try {
            xmlWriter.write(document);
            xmlWriter.flush();
            writer.flush();
            xmlString = writer.toString();
            xmlWriter.close();
            writer.close();
        } catch (IOException ex) {
            throw new XmlGenerateFail(ex);
        }
        return xmlString;
    }

}
