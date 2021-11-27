package com.dscomm.iecs.integrate.service;

/**
 * 描述： UDP 接收公安消息
 *
 * @author YangFuXi Date Time 2018/11/29 16:04
 */
public interface UdpReceivePoliceService {

    /**
     * 分析报文类型
     * */
    String analyse(String message);

    /**
     * 市局警情互联互通服务器向119接入服务器发送三字段
     *
     * */
    void transformPhone(String message);

    /**
     *市局警情互联互通服务器向119接入服务器发送警情
     *
     * */
    void transformIncident(String message);

    /**
     * 市局警情互联互通服务器向119接入服务器发送处警信息
     *
     * */
    void transformHandle(String message);

    /**
     * 市局警情互联互通服务器向119接入服务器发送协作请求
     *
     * */
    void transformHelp(String message);

    /**
     * 市局警情互联互通服务器向119接入服务器发送警情接收状态消息
     *
     * */
    void transformIncidentReceiveState(String message);

    /**
     *市局警情互联互通服务器向119接入服务器发送心跳信息
     *
     * */
    void transformHeart(String message);



}
