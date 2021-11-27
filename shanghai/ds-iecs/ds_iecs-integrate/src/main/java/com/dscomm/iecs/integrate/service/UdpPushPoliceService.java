package com.dscomm.iecs.integrate.service;

import com.dscomm.iecs.integrate.service.udpBean.*;

/**
 * 描述： UDP 向公安推送消息
 *
 * @author YangFuXi Date Time 2018/11/29 16:04
 */
public interface UdpPushPoliceService {

    /**
     * 119接入服务器向市局警情互联互通服务器发送三字段
     *
     * */
    void sendPhone( UdpPhoneContentVO  phone );

    /**
     * 119接入服务器向市局警情互联互通服务器发送警情
     *
     * */
    void sendIncident( UdpIncidentContentVO incident   );

    /**
     * 119接入服务器向市局警情互联互通服务器发送处警信息
     *
     * */
    void sendHandle( UdpHandle119ContentVO handle );

    /**
     * 119接入服务器向市局警情互联互通服务器发送反馈信息
     *
     * */
    void sendFeedBack( UdpFeedBackContentVO feedback );

    /**
     * 119接入服务器向市局警情互联互通服务器发送协作请求
     *
     * */
    void sendHelp( UdpHelpContentVO help );

    /**
     * 119接入服务器向市局警情互联互通服务器发送警情接收状态消息
     *
     * */
    void sendIncidentReceiveState( UdpIncidentReceiveStateContentVO  incidentReceiveState );

    /**
     * 119接入服务器向市局警情互联互通服务器发送心跳应答消息
     *
     * */
    void sendHeart( UdpHeartContentVO  heart );


}
