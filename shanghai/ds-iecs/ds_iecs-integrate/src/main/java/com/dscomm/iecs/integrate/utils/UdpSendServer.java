package com.dscomm.iecs.integrate.utils;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 描述：发送UDP 消息监控服务工具
 *
 * @author YangFuXi Date Time 2018/11/29 16:11
 */
public class UdpSendServer {

    /**
     * 发送UDP到指定ip：port
     * @param encoding 编码
     * */
    public static  void send(String encoding,String message,String ip,int port  ) throws Exception{

        DatagramSocket udpSend  = new DatagramSocket() ;
        byte[] data =message.getBytes(encoding);
        InetAddress inet=InetAddress.getByName(ip);
        DatagramPacket dp=new DatagramPacket(data,data.length,inet,port);
        udpSend.send(dp);
        udpSend.close();
    }


    /**
     * 发送UDP到指定ip：port
     * @param encoding 编码
     * */
    public static  void send(String encoding,String message,String ip,int port , String udpEncoding) throws Exception{

        DatagramSocket udpSend  = new DatagramSocket() ;
        byte[] olddata =message.getBytes(encoding);
        String udpString  =  new String ( olddata , udpEncoding ) ;
        byte[] data =message.getBytes(udpEncoding);
        InetAddress inet=InetAddress.getByName(ip);
        DatagramPacket dp=new DatagramPacket(data,data.length,inet,port);
        udpSend.send(dp);
        udpSend.close();
    }



}
