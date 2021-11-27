package com.dscomm.iecs.integrate.restful;

import com.dscomm.iecs.integrate.exception.IntegrateException;
import com.dscomm.iecs.integrate.restful.vo.OutsideVO;
import com.dscomm.iecs.integrate.restful.vo.ReceiveUnTrafficAlarmVO;
import com.dscomm.iecs.integrate.service.OutsideService;
import org.apache.logging.log4j.util.Strings;
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
 * 描述： 对接外部 restful 接口
 */
@Path("rest/iecs/v1.0/outside")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OutsideResource {


    private static final Logger logger = LoggerFactory.getLogger(OutsideResource.class);
    private OutsideService outsideService ;

    @Autowired
    public OutsideResource( OutsideService outsideService  ) {
        this.outsideService = outsideService ;
    }


    /**
     *  转警
     *
     * @return 返回更新结果
     */
    @Path("transferOutIncident")
    @POST
    public DataVO<Boolean> transferOutIncident(OutsideVO vo  ) {
        //参数判断
        if (null == vo || Strings.isBlank(  vo.getIncidentId() ) ) {
            throw new IntegrateException(IntegrateException.IntegrateErrors.DATA_NULL);
        }
        Boolean  res = outsideService.transferOutIncident( vo )  ;
        return new DataVO<>(res);
    }


    /**
     * 错位接警
     *
     * @return 返回更新结果
     */
    @Path("dislocationIncident")
    @POST
    public DataVO<Boolean> dislocationIncident(OutsideVO vo  ) {
        //参数判断
        if (null == vo || Strings.isBlank(  vo.getIncidentId() ) ) {
            throw new IntegrateException(IntegrateException.IntegrateErrors.DATA_NULL);
        }
        Boolean  res = outsideService.dislocationIncident( vo )  ;
        return new DataVO<>(res);
    }



    /**
     *  请求协助
     *
     * @return 返回更新结果
     */
    @Path("assistIncident")
    @POST
    public DataVO<Boolean> assistIncident(OutsideVO vo  ) {
        //参数判断
        if (null == vo || Strings.isBlank(  vo.getIncidentId() ) ) {
            throw new IntegrateException(IntegrateException.IntegrateErrors.DATA_NULL);
        }
        Boolean  res = outsideService.assistIncident( vo )  ;
        return new DataVO<>(res);
    }


    /**
     *  非话务报警
     *
     * @return 返回更新结果
     */
    @Path("unTrafficAlarm")
    @POST
    public DataVO<String> unTrafficAlarm(ReceiveUnTrafficAlarmVO vo  ) {
        //参数判断
        if (null == vo    ) {
            throw new IntegrateException(IntegrateException.IntegrateErrors.DATA_NULL);
        }
        String  res = outsideService.unTrafficAlarm( vo )  ;
        return new DataVO<>(res);
    }



}
