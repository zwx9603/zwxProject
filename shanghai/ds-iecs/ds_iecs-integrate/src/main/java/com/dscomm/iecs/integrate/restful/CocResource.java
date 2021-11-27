package com.dscomm.iecs.integrate.restful;

import com.dscomm.iecs.integrate.exception.IntegrateException;
import com.dscomm.iecs.integrate.restful.vo.IncidentCocVO;
import com.dscomm.iecs.integrate.service.CocService;
import org.mx.StringUtils;
import org.mx.service.rest.vo.DataVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**coc通知restful*/

@Path("rest/COC/FireAccept")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CocResource {

    private static final Logger logger = LoggerFactory.getLogger(CocResource.class);
    private CocService cocService;

    @Autowired
    public CocResource(CocService cocService) {
        this.cocService = cocService;
    }

    @Path("NewCase")
    @POST
    public DataVO<Boolean> newCase(IncidentCocVO vo){
        if (  null == vo || StringUtils.isBlank(vo.getIncidentId())) {
            throw new IntegrateException(IntegrateException.IntegrateErrors.DATA_NULL);
        }
        Boolean res = cocService.newCase(vo);
        return new DataVO<>(res);
    }

    @Path("ModifyCase")
    @POST
    public DataVO<Boolean> modifyCase(IncidentCocVO vo){
        if (  null == vo || StringUtils.isBlank(vo.getIncidentId())) {
            throw new IntegrateException(IntegrateException.IntegrateErrors.DATA_NULL);
        }
        Boolean res = cocService.modifyCase(vo);
        return new DataVO<>(res);
    }
}
