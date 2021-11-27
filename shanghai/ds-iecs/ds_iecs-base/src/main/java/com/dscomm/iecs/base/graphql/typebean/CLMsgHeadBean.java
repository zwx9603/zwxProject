package com.dscomm.iecs.base.graphql.typebean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

/**
 * 描述:websocket消息头定义
 *
 * @author YangFuxi
 * Date Time 2019/7/19 17:27
 */
@XmlAccessorType(XmlAccessType.NONE)
public class CLMsgHeadBean implements java.io.Serializable {
	private static final long serialVersionUID = -2632391935324264411L;

	/** */
	private String code;

	/** 成员变量：发送者 */
	private ReceiverMessageBean from;

	/** 成员变量：接收者 toReceiver = "type,id,system"; */
	private List<ReceiverMessageBean> tos;

	/**
	 * 默认构造方法
	 */
	public CLMsgHeadBean() {
		super();
	}

	/**
	 * 默认构造方法
	 *
	 * @param tos 接收者
	 * @param code 接收系统代码
	 */
	public CLMsgHeadBean(List<ReceiverMessageBean> tos, String code) {
		super();
		this.tos = tos;
		this.code = code;
	}

	/**
	 * 默认构造方法
	 *
	 * @param code 代码
	 */
	public CLMsgHeadBean(String code) {
		super();
		this.code = code;
	}

	public CLMsgHeadBean(String code, ReceiverMessageBean from, List<ReceiverMessageBean> tos) {
		super();
		this.code = code;
		this.from = from;
		this.tos = tos;
	}

	/**
	 * 获取类成员code
	 *
	 * @return {@link #code}
	 */
	@XmlElement(name = "code")
	public String getCode() {
		return this.code;
	}

	/**
	 * 获取类成员from
	 *
	 * @return {@link #from}
	 */
	@XmlElement(name = "from")
	public ReceiverMessageBean getFrom() {
		return this.from;
	}

	/**
	 * 获取类成员toReceivers
	 *
	 * @return {@link #tos}
	 */
	@XmlElementWrapper(name = "tos")
	@XmlElement(name = "to")
	public List<ReceiverMessageBean> getTos() {
		return this.tos;
	}

	/**
	 * 设定类成员code
	 *
	 * @param code
	 *            要设定的{@link #code}
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 设定类成员from
	 *
	 * @param from
	 *            要设定的{@link #from}
	 */
	public void setFrom(ReceiverMessageBean from) {
		this.from = from;
	}

	/**
	 * 设定类成员toReceivers
	 *
	 * @param tos
	 *            要设定的{@link #tos}
	 */
	public void setTos(List<ReceiverMessageBean> tos) {
		this.tos = tos;
	}

}
