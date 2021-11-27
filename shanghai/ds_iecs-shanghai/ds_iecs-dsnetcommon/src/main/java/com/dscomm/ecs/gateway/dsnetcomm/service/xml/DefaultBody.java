/*
 * Description:
 * 		默认的消息体，在没有指定具体实现时使用。
 *
 * History：
 * ========================================
 * Date         Memo
 * 2012-09-20   Created by Mingy
 * ========================================
 *
 * Copyright 2010 , 迪爱斯通信设备有限公司保留。
 */
package com.dscomm.ecs.gateway.dsnetcomm.service.xml;

import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * 默认的消息体，在没有指定具体实现时使用。
 *
 * @author Mingy
 * @since 1.5.2
 */
public class DefaultBody extends Body {

    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = 751696220419015528L;

    /**
     * XML消息体的节点
     */
    private List<Element> elements;

    /**
     * @see #fromXml(Element)
     */
    @SuppressWarnings("unchecked")
    @Override
    public void fromXml(Element element) {
        super.fromXml(element);
        List<Element> list = ((Element) element.clone()).elements();
        elements = new ArrayList<Element>(list.size());
        for (Element e : list) {
            e.detach();
            elements.add(e);
        }
    }

    /**
     * @see #toXml(Element)
     */
    @Override
    public Element toXml(Element element) {
        for (Element e : elements) {
            element.add((Element) e.clone());
        }
        return super.toXml(element);
    }
}
