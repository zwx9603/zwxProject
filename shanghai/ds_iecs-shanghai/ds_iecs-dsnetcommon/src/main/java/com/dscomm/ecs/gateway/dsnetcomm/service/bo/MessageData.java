package com.dscomm.ecs.gateway.dsnetcomm.service.bo;

import java.io.Serializable;

/**
 * 接收到的消息对象，包括底层消息传输头。
 * 
 * @author Josh Peng
 * 
 */
public final class MessageData implements Serializable {

	private static final long serialVersionUID = -7125462131095103544L;

	protected String type = "21"; // 接收到消息的类型，一般对应DS11、DS21，……
	protected DsMsgHeader header; // 接收到消息的底层传输头
	protected byte[] data = null; // 数据内容

	/**
	 * 默认的构造函数
	 */
	public MessageData() {
		super();
	}

	/**
	 * 返回数据内容
	 * 
	 * @return 数据内容
	 */
	public byte[] getData() {
		return data;
	}

	/**
	 * 返回消息类型，对应于DS11, DS21,……
	 * 
	 * @return 消息类型
	 */
	public String getType() {
		return type;
	}

	/**
	 * 返回通信头数据
	 * 
	 * @return 通信头数据
	 */
	public DsMsgHeader getHeader() {
		return header;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setHeader(DsMsgHeader header) {
		this.header = header;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("communication type(").append(type).append(")");
		sb.append(",").append(header.toString());
		return sb.toString();
	}

}
