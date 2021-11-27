/**
 * 
 */
package com.dscomm.ecs.gateway.dsnetcomm.service.bo;

import com.sun.jna.NativeLong;

import java.io.Serializable;

/**
 * DS通信底层的消息头抽象定义。
 * 
 * @author Josh Peng
 * 
 */
public class DsMsgHeader implements Serializable {
	private static final long serialVersionUID = 6466078813165519959L;
	// 通信模式
	protected int transType;
	// 消息ID,(业务数据+MsgID)长度,目标类型,目标ID,源类型,源ID
	protected int msgID, dataLength, destType, destID, srcType, srcID;

	protected int headerLength = 0; // 消息头长度，由子类设置

	/**
	 * 默认的构造函数
	 */
	public DsMsgHeader() {
		super();
	}

	/**
	 * 创建一个空的消息内存块
	 * 
	 * @return 消息内存块
	 */
	public MemoryBlock.ByReference createMemoryBlock() {
		MemoryBlock.ByReference memBlk = new MemoryBlock.ByReference();
		memBlk.size = new NativeLong(0);
		memBlk.addr = null;
		return memBlk;
	}

	/**
	 * 返回消息传输类型
	 * 
	 * @return 消息传输类型
	 */
	public int getTransType() {
		return transType;
	}

	/**
	 * 返回消息ID
	 * 
	 * @return 消息ID
	 */
	public int getMsgID() {
		return msgID;
	}

	/**
	 * 返回消息数据段长度，单位为字节。
	 * 
	 * @return 数据长度，为实际数据长度+2
	 */
	public int getDataLength() {
		return dataLength;
	}

	/**
	 * 返回消息头总长度，单位为字节。
	 * 
	 * @return 总长度
	 */
	public int getHeaderLength() {
		return headerLength;
	}

	/**
	 * 返回消息发送目标类型
	 * 
	 * @return 目标类型
	 */
	public int getDestType() {
		return destType;
	}

	/**
	 * 返回消息发送目标ID
	 * 
	 * @return 目标ID
	 */
	public int getDestID() {
		return destID;
	}

	/**
	 * 返回消息发送源类型
	 * 
	 * @return 源类型
	 */
	public int getSrcType() {
		return srcType;
	}

	/**
	 * 返回消息发送源ID
	 * 
	 * @return 源ID
	 */
	public int getSrcID() {
		return srcID;
	}

	/**
	 * 设置消息传输类型：点对点……
	 * 
	 * @param transType
	 *            传输类型
	 */
	public void setTransType(int transType) {
		this.transType = transType;
	}

	/**
	 * 设置消息ID，XML消息为0xAA00。
	 * 
	 * @param msgId
	 *            消息ID
	 */
	public void setMsgId(int msgId) {
		this.msgID = msgId;
	}

	/**
	 * 设置消息发送目标类型
	 * 
	 * @param destType
	 *            目标类型
	 */
	public void setDestType(int destType) {
		this.destType = destType;
	}

	/**
	 * 设置消息发生目标ID
	 * 
	 * @param destId
	 *            目标ID
	 */
	public void setDestId(int destId) {
		this.destID = destId;
	}

	/**
	 * 设置消息发送源类型
	 * 
	 * @param srcType
	 *            源类型
	 */
	public void setSrcType(int srcType) {
		this.srcType = srcType;
	}

	/**
	 * 设置消息发送源ID
	 * 
	 * @param srcId
	 *            源ID
	 */
	public void setSrcId(int srcId) {
		this.srcID = srcId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("headerLength(").append(headerLength).append(")");
		sb.append(",transType(").append(transType).append(")");
		sb.append(",msgId(0x").append(Integer.toHexString(msgID & 0xFFFF))
				.append(")");
		sb.append(",src(0x").append(Integer.toHexString(srcType & 0xFFFF))
				.append(":").append(srcID).append(")");
		sb.append(",dest(0x").append(Integer.toHexString(destType & 0xFFFF))
				.append(":").append(destID).append(")");
		sb.append(",dataLenth(").append(dataLength).append(")");
		return sb.toString();

	}

}
