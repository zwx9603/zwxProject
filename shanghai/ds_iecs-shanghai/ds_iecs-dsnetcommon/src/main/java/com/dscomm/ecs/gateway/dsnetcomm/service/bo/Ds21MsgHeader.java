/**
 *
 */
package com.dscomm.ecs.gateway.dsnetcomm.service.bo;

import com.sun.jna.Memory;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;

/**
 * Ds21通信头定义
 *
 * @author Josh Peng
 */
public class Ds21MsgHeader extends DsMsgHeader {
    private static final long serialVersionUID = 2612022690525580316L;
    protected byte msgType; // 消息类型，业务
    protected int serialNo; // 序列号，业务
    protected int reserved; // 预留，业务

    /**
     * 默认的构造函数
     */
    public Ds21MsgHeader() {
        super();
        super.transType = 0; // DS21默认使用点对点传输
        super.headerLength = 22; // 消息头总长度为22字节
    }

    /*
     * (non-Javadoc)
     *
     * @see com.dscomm.dsnetcomm.DsMsgHeader#createMemoryBlock()
     */
    @Override
    public MemoryBlock.ByReference createMemoryBlock() {
        MemoryBlock.ByReference pBlk = super.createMemoryBlock();
        pBlk.size = new NativeLong(22);
        return pBlk;
    }

    /**
     * 根据当前DS21头信息，生成一个符合规范的通信数据包。
     *
     * @param data 待传输数据
     * @return 封包数据包，包括DS21头和传输数据。
     */
    public MemoryBlock.ByReference createMemoryBlock(byte[] data) {
        MemoryBlock.ByReference pBlk = super.createMemoryBlock();
        int dataLen = 0;
        if (data != null) {
            dataLen += data.length;
        }
        dataLength = dataLen + 3;
        int size = headerLength + dataLen + 1;
        Memory memory = new Memory(size);
        // 写入21通信头
        memory.write(0, new byte[]{(byte) transType, msgType}, 0, 2);
        memory.write(2, new short[]{(short) destType, (short) destID,
                (short) srcType, (short) srcID}, 0, 4);
        memory.write(10, new int[]{serialNo, reserved}, 0, 2);
        memory.write(18, new short[]{(short) dataLength, (short) msgID}, 0,
                2);
        // 写入数据，如果有的话
        if (data != null) {
            memory.write(headerLength, data, 0, data.length);
        }
        // 多写一个字节（'\0'），保证安全。
        memory.write(size - 1, new byte[]{(byte) 0}, 0, 1);

        pBlk.size = new NativeLong(size);
        pBlk.addr = memory;
        return pBlk;
    }

    /**
     * 从内存块中读出21通信头
     *
     * @param memory 内存对象
     * @return 读出的21通信头
     */
    public Ds21MsgHeader fromMemory(Pointer memory) {
        transType = memory.getByte(0);
        msgType = memory.getByte(1);
        destType = memory.getShort(2);
        destID = memory.getShort(4);
        srcType = memory.getShort(6);
        srcID = memory.getShort(8);
        serialNo = memory.getInt(10);
        reserved = memory.getInt(14);
        dataLength = memory.getShort(18);
        msgID = memory.getShort(20);
        return this;
    }

    /**
     * 返回消息类型
     *
     * @return 消息类型，业务解析。
     */
    public byte getMsgType() {
        return msgType;
    }

    /**
     * 返回DS21通信中的流水号
     *
     * @return 流水号
     */
    public int getSerialNo() {
        return serialNo;
    }

    /**
     * 返回DS21通信的保留字段
     *
     * @return 保留字段，业务解析。
     */
    public int getReserved() {
        return reserved;
    }

    /**
     * 设置DS21通信的消息类型，一般为0。
     *
     * @param msgType 消息类型，业务解析。
     */
    public void setMsgType(byte msgType) {
        this.msgType = msgType;
    }

    /**
     * 设置DS21通信的保留字段
     *
     * @param reserved 保留属性，业务解析。
     */
    public void setReserved(int reserved) {
        this.reserved = reserved;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.sun.jna.Structure#toString()
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(",msgType(").append(msgType).append(")");
        sb.append(",serialNo(").append(serialNo).append(")");
        sb.append(",reserved(").append(Integer.toHexString(reserved & 0xFFFF))
                .append(")");
        return sb.toString();
    }

    ;

}
