package com.dscomm.iecs.accept.restful;

import com.dscomm.iecs.accept.restful.vo.UdpVO.UdpHelpVO;
import com.dscomm.iecs.accept.restful.vo.UdpVO.UdpMessageTypeVO;
import com.dscomm.iecs.accept.service.outside.OutSideOtherService;
import org.mx.service.rest.vo.DataVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 描述：  接收公安警情信息
 */
@Path("iecs/v1.0/police/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReceivePoliceResource {

    private static final Logger logger = LoggerFactory.getLogger(ReceivePoliceResource.class);
    private OutSideOtherService outSideOtherService ;

    @Autowired
    public ReceivePoliceResource(  OutSideOtherService outSideOtherService  ) {
        this.outSideOtherService = outSideOtherService;
    }


    /**
     *  接收公安记录信息( 心跳不记录 )
     *
     * @return 返回更新结果
     */
    @Path("analyse")
    @POST
    public DataVO<Boolean> analyse( UdpMessageTypeVO udpMessage ) {

        Boolean res = outSideOtherService.analyse( udpMessage) ;

        return new DataVO<>(res );
    }


    /**
     * 公安请求协助
     *
     * @return 返回更新结果
     */
    @Path("udpHelp")
    @POST
    public DataVO<Boolean> udpHelp(    UdpHelpVO udpHelp    ) {

        Boolean res = outSideOtherService.udpHelp( udpHelp) ;

        return new DataVO<>(res );
    }


}
