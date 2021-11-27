package com.dscomm.iecs.integrate.utils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * 描述：接收 UDP 消息 监控服务工具
 *
 * @author YangFuXi Date Time 2018/11/29 16:11
 */
public class UdpReceiveServer {

    DatagramSocket udpReceive;

    /**
     * 指定端口号的构造函数
     *
     * @param port 监听端口
     * @throws SocketException 抛出异常
     */
    public UdpReceiveServer(int port) throws SocketException {
        udpReceive = new DatagramSocket(port);
    }

    /**
     * 从端口读取报文并返回报文数据
     *
     * @param encoding 编码
     * @return 报文数据
     * @throws IOException 抛出异常
     */
    public  String read(String encoding) throws IOException {
        byte temp[] = new byte[2048];
        DatagramPacket receive_packet = new DatagramPacket(temp, temp.length);
        udpReceive.receive(receive_packet);
        String result = new String(receive_packet.getData(), 0, receive_packet.getLength(), encoding);
        return result;
    }


}
