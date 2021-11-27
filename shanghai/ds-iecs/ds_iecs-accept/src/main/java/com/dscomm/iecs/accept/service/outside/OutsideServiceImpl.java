package com.dscomm.iecs.accept.service.outside;

import com.dscomm.iecs.accept.graphql.inputbean.outside.OutsideInputInfo;
import com.dscomm.iecs.accept.restful.vo.ReceiveUnTrafficAlarmVO;
import com.dscomm.iecs.accept.service.bean.cad.IncidentDossierVO;
import com.dscomm.iecs.base.service.log.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


/**
 * 描述：外部接口 服务 实现类
 */
@Component("outsideServiceImpl")
public class OutsideServiceImpl implements OutsideService {

    private static final Logger logger = LoggerFactory.getLogger(OutsideServiceImpl.class);

    private LogService logService;
    private OutsideChangeService outsideChangeService  ;


    /**
     * 默认的构造函数
     */
    @Autowired
    public OutsideServiceImpl(LogService logService ,    @Qualifier("outsideChange")    OutsideChangeService outsideChangeService
    ) {
        this.logService = logService;
        this.outsideChangeService  =  outsideChangeService ;

    }

    /**
     * {@inheritDoc}
     *
     * @see #transferOutIncident(OutsideInputInfo)
     */
    @Transactional(rollbackFor = Exception.class )
    @Override
    public  Boolean transferOutIncident(  OutsideInputInfo queryBean ) {


        logService.infoLog(logger, "service", "transferOutIncident", "service is started...");
        Long logStart = System.currentTimeMillis();

        Boolean res = outsideChangeService.transferOutIncident( queryBean )  ;

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "transferOutIncident", String.format("service is finished,execute time is :%sms", logEnd - logStart));

        return res;

    }





    /**
     * 获取110警情卷宗
     * @param incidentId 事件id
     */
    @Override
    public IncidentDossierVO findIncidentDossier110(String incidentId) {

        logService.infoLog(logger, "service", "findIncidentDossier110", "service is started...");
        Long logStart = System.currentTimeMillis();

        IncidentDossierVO res =  outsideChangeService.findIncidentDossier110( incidentId )  ;

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "findIncidentDossier110", String.format("service is finished,execute time is :%sms", logEnd - logStart));

        return res;

    }




    /**
     * {@inheritDoc}
     *
     * @see #receiveTransferOutIncident(ReceiveUnTrafficAlarmVO )
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public   Boolean receiveTransferOutIncident(  ReceiveUnTrafficAlarmVO inputInfo ) {

        logService.infoLog(logger, "service", "receiveTransferOutIncident", "service is started...");
        Long logStart = System.currentTimeMillis();

        Boolean res =  outsideChangeService.receiveTransferOutIncident( inputInfo )  ;

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "receiveTransferOutIncident", String.format("service is finished,execute time is :%sms", logEnd - logStart));

        return res;
    }



    /**
     * {@inheritDoc}
     *
     * @see #dislocationIncident(OutsideInputInfo)
     */
    @Transactional( rollbackFor = Exception.class )
    @Override
    public  Boolean dislocationIncident(  OutsideInputInfo queryBean ) {

        logService.infoLog(logger, "service", "dislocationIncident", "service is started...");
        Long logStart = System.currentTimeMillis();

        Boolean res =  outsideChangeService.dislocationIncident( queryBean )  ;

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "dislocationIncident", String.format("service is finished,execute time is :%sms", logEnd - logStart));

        return res;

    }

    /**
     * {@inheritDoc}
     *
     * @see #dislocationIncident(OutsideInputInfo)
     */
    @Transactional( rollbackFor = Exception.class )
    @Override
    public Boolean  dislocationRelayRecordNumber( String dislocationId , String relayRecordNumber  ) {

        logService.infoLog(logger, "service", "dislocationRelayRecordNumber", "service is started...");
        Long logStart = System.currentTimeMillis();

        Boolean res =  outsideChangeService.dislocationRelayRecordNumber( dislocationId , relayRecordNumber )  ;

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "dislocationRelayRecordNumber", String.format("service is finished,execute time is :%sms", logEnd - logStart));

        return res;

    }

    /**
     * {@inheritDoc}
     *
     * @see #assistIncident(OutsideInputInfo)
     */
    @Transactional( rollbackFor = Exception.class )
    @Override
    public  Boolean assistIncident(  OutsideInputInfo queryBean ) {

        logService.infoLog(logger, "service", "assistIncident", "service is started...");
        Long logStart = System.currentTimeMillis();

        Boolean res =  outsideChangeService.assistIncident( queryBean )  ;

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "assistIncident", String.format("service is finished,execute time is :%sms", logEnd - logStart));

        return res;

    }




}