package com.dscomm.iecs.accept.restful;


import com.dscomm.iecs.accept.graphql.typebean.HandleBean;
import com.dscomm.iecs.accept.graphql.typebean.IncidentBean;
import com.dscomm.iecs.accept.hangzhou.service.TTSSpeechService;
import com.dscomm.iecs.accept.service.HandleService;
import com.dscomm.iecs.accept.service.IncidentService;
import com.dscomm.iecs.agent.service.UserService;
import com.dscomm.iecs.base.service.NotifyActionService;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.KeyUnitService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import org.apache.logging.log4j.util.Strings;
import org.mx.service.rest.vo.DataVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashSet;
import java.util.Set;

@Path("iecs/v1.0/ts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WebsocketOrOtherTestResource {

    private static final Logger logger = LoggerFactory.getLogger(WebsocketOrOtherTestResource.class);
    private NotifyActionService notifyActionService ;
    private IncidentService incidentService ;
    private HandleService handleService ;
    private TTSSpeechService ttsSpeechService ;



    @Autowired
    public WebsocketOrOtherTestResource(  NotifyActionService notifyActionService ,
                                           IncidentService incidentService ,
                                           HandleService handleService ,
                                           TTSSpeechService ttsSpeechService
    ) {
        this.notifyActionService = notifyActionService ;
        this.incidentService = incidentService ;
        this.handleService = handleService ;
        this.ttsSpeechService = ttsSpeechService ;

    }

    /**
     * websokcet 测试接口
     *
     * @return 返回更新结果
     */
    @Path("TestQuery")
    @GET
    public DataVO<Object> buildIncident( ) {

        String  incidentId = "6ABE5AB3C2E5AC4880C8AFCC8B304D944422";
        String  handleId = "ddc06c4c977648d38e0bdaed7482b4cb"  ;
        IncidentBean incidentBean = incidentService.findIncident( incidentId , false ) ;
        HandleBean handleBean = handleService.findHandleByHandleId( handleId ) ;

        ttsSpeechService.pushIncidentHandleTTS( incidentBean , handleBean ) ;

        String speakToFileUrl = ttsSpeechService.buildSpeakToFile( incidentBean , handleBean ) ;

        return new DataVO<>(speakToFileUrl);
    }


    /**
     * websokcet 测试接口
     *
     * @return 返回更新结果
     */
    @Path("webscoket")
    @GET
    public DataVO<Boolean> notifyCurrentIncidentInfoCache(
            @QueryParam("code") String code  ,
            @QueryParam("body") String body ,
            @QueryParam("orgs") String orgs ,
            @QueryParam("roles") String roles ,
            @QueryParam("system") String system
            ) {
        Set<String> orgset = new HashSet<>() ;
        if(Strings.isNotBlank(orgs)){
           String [] o =  orgs.split(",") ;
           for( int i = 0 ; i < o.length ; i++ ){
               orgset.add( o[i]) ;
           }
        }
        Set<String> roleset = new HashSet<>() ;
        if(Strings.isNotBlank(roles)){
            String [] o =  roles.split(",") ;
            for( int i = 0 ; i < o.length ; i++ ){
                roleset.add( o[i]) ;
            }
        }
        notifyActionService.pushMessageToDefaultSystemBusinessOrg(  code,  body ,orgset   );
        notifyActionService.pushMessageToBusinessOrg(  code,  body ,orgset  , system  );
        notifyActionService.pushMessageToDefaultSystemBusinessOrgRole(  code,  body ,orgset  ,roleset     );
        notifyActionService.pushMessageToBusinessOrgRole(  code,  body ,orgset  ,roleset , system  );

        return new DataVO<>(true);
    }




}
