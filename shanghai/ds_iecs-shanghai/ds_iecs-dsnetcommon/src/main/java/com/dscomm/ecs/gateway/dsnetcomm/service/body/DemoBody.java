package com.dscomm.ecs.gateway.dsnetcomm.service.body;

import com.dscomm.ecs.gateway.dsnetcomm.exception.XmlGenerateFail;
import com.dscomm.ecs.gateway.dsnetcomm.service.xml.Body;
import com.dscomm.ecs.gateway.dsnetcomm.service.xml.MainMsg;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 描述:
 *
 * @author YangFuxi
 * Date Time 2020/9/23 23:41
 */
public class DemoBody extends Body {
    private String userName;
    private String userPhone;

    public void fromXml(String xml) {
        try {
            Document document = DocumentHelper.parseText(xml.substring(0,
                    xml.indexOf("</Body>"))
                    + "</Body>");
            Element elRoot = document.getRootElement();
            fromXml(elRoot);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void fromXml(Element element) {
        Element testDemo = element.element("TestDemo");
        Element uE = testDemo.element("UserName");
        if (uE!=null){
            this.userName=uE.getText();
        }
        Element pE = testDemo.element("UserPhone");
        if (pE!=null){
            this.userPhone=pE.getText();
        }
        super.fromXml(element);
    }

    @Override
    public Element toXml(Element element) {
        Element testDemo = element.addElement("TestDemo");
        Element uElement = testDemo.addElement("UserName");
        uElement.setText(userName);
        Element pElement = testDemo.addElement("UserPhone");
        pElement.setText(userPhone);
        return super.toXml(element);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public static void main(String[] args){
        DemoBody body=new DemoBody();
        body.setUserName("张三");
        body.setUserPhone("1513716991");
//        Document document = DocumentHelper.createDocument();
//        Element elRoot = document.addElement("Body");
//        Element element = body.toXml(elRoot);
//        OutputFormat format = true ? OutputFormat.createCompactFormat()
//                : OutputFormat.createPrettyPrint();
//        format.setEncoding("UTF-8");
//        format.setOmitEncoding(false);
//        StringWriter writer = new StringWriter();
//        XMLWriter xmlWriter = new XMLWriter(writer, format);
//        String xmlString = null;
//        try {
//            xmlWriter.write(document);
//            xmlWriter.flush();
//            writer.flush();
//            xmlString = writer.toString();
//            xmlWriter.close();
//            writer.close();
//        } catch (IOException ex) {
//            System.out.println("出错了");
//        }
//        System.out.println(xmlString);
//        DemoBody body2=new DemoBody();
//        body2.fromXml(xmlString);
//        System.out.println(JSONObject.toJSONString(body2));

        MainMsg mainMsg=new MainMsg();
        mainMsg.setBody(body);
        mainMsg.getHead().getDst().setId("3");
        mainMsg.getHead().getDst().setType("0x8700".substring(2));
        mainMsg.getHead().getMsg().setCode("0xAA00".substring(2));
        try {
            String s = mainMsg.toXmlString();
            System.out.println(s);
        } catch (XmlGenerateFail xmlGenerateFail) {
            xmlGenerateFail.printStackTrace();
        }
    }
}
