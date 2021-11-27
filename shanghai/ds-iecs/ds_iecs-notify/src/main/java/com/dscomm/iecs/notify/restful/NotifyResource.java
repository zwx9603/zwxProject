package com.dscomm.iecs.notify.restful;

import com.dscomm.iecs.notify.restful.vo.NotifyMessageVO;
import com.dscomm.iecs.notify.service.DeviceNotifyService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mx.error.UserInterfaceException;
import org.mx.service.error.UserInterfaceServiceErrorException;
import org.mx.service.rest.vo.DataVO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 描述： 警情实时推送Rest服务资源
 *
 * @author john peng
 * Date time 2018/7/15 上午9:54
 */

@Path("rest/iecs/v1.0/notify")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NotifyResource {

    private static final Log logger = LogFactory.getLog(NotifyResource.class);
    private DeviceNotifyService deviceNotifyService;

    @Autowired
    public NotifyResource(DeviceNotifyService deviceNotifyService) {
        super();
        this.deviceNotifyService = deviceNotifyService;
    }

    @Path("message")
    @POST
    public DataVO<Boolean> notifyMessage(NotifyMessageVO vo) {
        try {
            deviceNotifyService.pushNotifyMessage( vo.getCode() ,  vo.getBody()  );
            return new DataVO<>(true);
        } catch (UserInterfaceException ex) {
            return new DataVO<>(ex);
        } catch (Exception ex) {
            logger.error( "message fail ", ex );
            return new DataVO<>(new UserInterfaceServiceErrorException(
                    UserInterfaceServiceErrorException.ServiceErrors.SERVICE_OTHER_FAIL));
        }
    }

    @Path("messageFilter")
    @POST
    public DataVO<Boolean> pushNotifyMessageFilter(NotifyMessageVO vo) {
        try {
            deviceNotifyService.pushNotifyMessageFilter( vo.getFilterType() ,  vo.getFilterKeys()  , vo.getCode() ,  vo.getBody()  );
            return new DataVO<>(true);
        } catch (UserInterfaceException ex) {
            return new DataVO<>(ex);
        } catch (Exception ex) {
            logger.error( "message fail ", ex );
            return new DataVO<>(new UserInterfaceServiceErrorException(
                    UserInterfaceServiceErrorException.ServiceErrors.SERVICE_OTHER_FAIL));
        }
    }

}
