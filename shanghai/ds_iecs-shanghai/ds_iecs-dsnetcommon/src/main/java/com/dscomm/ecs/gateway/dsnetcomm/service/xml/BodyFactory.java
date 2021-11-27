/*
 * @(#)BodyFactory.java
 *
 * Copyright 2015, 迪爱斯通信设备有限公司保留.
 */
package com.dscomm.ecs.gateway.dsnetcomm.service.xml;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mx.StringUtils;
import org.springframework.context.ApplicationContext;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 创建具体命令消息体的工厂类
 *
 *
 * <b>History:</b>
 * <table border="1">
 * <tr>
 * <th>Date</th>
 * <th>Operator</th>
 * <th>Memo</th>
 * </tr>
 * <tr>
 * <td>2015-8-3</td>
 * <td>Bezaleel</td>
 * <td>Modify:1.5.3,修正了bodyFactory的配置无法向前兼容的问题</td>
 * </tr>
 * </table>
 *
 * @author Bezaleel
 * @version 1.5.3
 * @since 1.5.3
 */
public class BodyFactory implements Serializable {
    private static final long serialVersionUID = 1869086436114712585L;
    private static final Log logger = LogFactory.getLog(BodyFactory.class);
    private Map<String, Class<Body>> bodies = null;
    private ApplicationContext context;


    /**
     * 默认的构造函数
     */
    public BodyFactory(ApplicationContext context) {
        this.context = context;
    }

    /**
     * //     * @see OnContextLoadedListener#getOrder()
     * //
     */
////	@Override
//    public int getOrder() {
//        return DsNetCommonManger.PRIORITY_ORDER - 1;
//    }

//    /**
////     * @see OnContextLoadedListener#onContextLoaded(GlobalBeanContext)
//     */
//	@Override
    public void init() {
        // modify 20150803 bezaleel 修正了bodyfactory注入时仅读取BodyRegister而无法向前兼容的问题
        if (bodies == null || bodies.isEmpty()) {
            bodies = new HashMap<String, Class<Body>>();
            Map<String, BodyRegister> beanMap = context.getBeansOfType(BodyRegister.class);
            if (beanMap != null && !beanMap.isEmpty()) {
                beanMap.keySet().forEach(name -> {
                    BodyRegister bodyRegister = beanMap.get(name);
                    if (!StringUtils.isBlank(bodyRegister.getCode()) && !bodies.containsKey(bodyRegister.getCode())) {
                        bodies.put(bodyRegister.getCode(), bodyRegister.getBody());
                    }
                });
            }
        }
    }

    /**
     * 创建一个具体命令消息体的工厂方法。
     *
     * @param head 消息头定义信息
     * @return 实例化的命令消息体对象
     */
    public Body createBody(Head head) {
        Body body = null;
        if (bodies != null) {
            Class<Body> clazz = bodies.get(head.getMsg().getCode());
            if (clazz != null) {
                try {
                    body = clazz.newInstance();
                } catch (Exception ex) {
                    if (logger.isErrorEnabled()) {
                        logger.error(
                                "Create the message body instance fail. class: "
                                        + clazz.getName(), ex);
                    }
                }
            }
        }
        if (body == null) {
            body = new Body();
        }
        return body;
    }

    /**
     * 设置系统中的命令消息体映射信息类
     *
     * @param bodies 命令消息体映射信息类集合
     */
    public void setBodies(Map<String, Class<Body>> bodies) {
        this.bodies = bodies;
    }

}
