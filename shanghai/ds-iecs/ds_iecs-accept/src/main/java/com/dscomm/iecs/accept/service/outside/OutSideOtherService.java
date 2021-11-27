package com.dscomm.iecs.accept.service.outside;

import com.dscomm.iecs.accept.restful.vo.UdpVO.UdpHelpVO;
import com.dscomm.iecs.accept.restful.vo.UdpVO.UdpMessageTypeVO;

public interface OutSideOtherService {


    /**
     *  接收udp 消息
     */
    Boolean analyse( UdpMessageTypeVO udpMessage  ) ;


    /**
     *  公安请求协助
     */
    Boolean  udpHelp(    UdpHelpVO udpHelp    ) ;

}
