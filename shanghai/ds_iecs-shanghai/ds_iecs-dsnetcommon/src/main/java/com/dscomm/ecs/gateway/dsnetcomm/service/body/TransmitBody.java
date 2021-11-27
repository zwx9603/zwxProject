package com.dscomm.ecs.gateway.dsnetcomm.service.body;

import com.dscomm.ecs.gateway.dsnetcomm.service.xml.Body;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述:转发消息的实体
 *
 * @author YangFuxi
 * Date Time 2020/9/29 21:38
 */
public class TransmitBody extends Body {
    private static final Logger logger=LoggerFactory.getLogger(TransmitBody.class);
    private String xmlMessage;
    public void fromXml(String xml){
        try {
            Document document = DocumentHelper.parseText(xml.substring(0,
                    xml.indexOf("</Body>"))
                    + "</Body>");
            Element elRoot = document.getRootElement();
            super.fromXml(elRoot);
        } catch (DocumentException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void fromXml(Element element) {
        String xml = element.getText();
        this.xmlMessage=xml;
        super.fromXml(element);
    }

    @Override
    public Element toXml(Element element) {
        element.setText(this.xmlMessage);
        return super.toXml(element);
    }

    public String getXmlMessage() {
        return xmlMessage;
    }

    public void setXmlMessage(String xmlMessage) {
        this.xmlMessage = xmlMessage;
    }
}
