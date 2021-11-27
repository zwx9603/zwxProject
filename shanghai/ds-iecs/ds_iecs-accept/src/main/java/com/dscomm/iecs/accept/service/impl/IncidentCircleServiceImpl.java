package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.IncidentCircleEntity;
import com.dscomm.iecs.accept.dal.repository.IncidentCircleRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.fireinputbean.IncidentCircleSaveInputInfo;
import com.dscomm.iecs.accept.graphql.firetypebean.IncidentCircleBean;
import com.dscomm.iecs.accept.service.IncidentCircleService;
import com.dscomm.iecs.accept.utils.transform.FireTransformUtil;
import com.dscomm.iecs.base.service.log.LogService;
import org.apache.logging.log4j.util.Strings;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("incidentCircleServiceImpl")
public class IncidentCircleServiceImpl implements IncidentCircleService {
    private static final Logger logger = LoggerFactory.getLogger(HandleServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private IncidentCircleRepository incidentCircleRepository;


    @Autowired
    public IncidentCircleServiceImpl(@Qualifier("generalAccessor") GeneralAccessor accessor, LogService logService,IncidentCircleRepository incidentCircleRepository) {
        this.accessor = accessor;
        this.logService = logService;
        this.incidentCircleRepository=incidentCircleRepository;
    }

    @Transactional( rollbackFor = Exception.class)
    @Override
    public IncidentCircleBean saveIncidentCircle(IncidentCircleSaveInputInfo queryBean) {

        if(null==queryBean  || Strings.isBlank( queryBean.getIncidentId() ) ){
            logService.infoLog(logger, "service", "saveIncidentCircle", "IncidentCircleSaveInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveIncidentCircle", "service is started...");
            Long logStart = System.currentTimeMillis();

            IncidentCircleBean res = null ;

            logService.infoLog(logger, "repository", "findIncidentCircle", "repository is started...");
            Long startincidentCircle = System.currentTimeMillis();

            List<IncidentCircleEntity> incidentCircleEntityList = incidentCircleRepository.getIncidentCircleID(queryBean.getIncidentId());

            Long endincidentCircle = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findIncidentCircle", String.format("repository is finished,execute time is :%sms", endincidentCircle - startincidentCircle));

            IncidentCircleEntity incidentCircleEntity = null ;
            if( incidentCircleEntityList != null && incidentCircleEntityList.size() > 0 ){
                incidentCircleEntity = incidentCircleEntityList.get(0) ;
            }

            logService.infoLog(logger, "repository", "findIncidentCircle", "repository is started...");
            Long startincidentCircleSave = System.currentTimeMillis();

            if ( null != incidentCircleEntity ){
                incidentCircleEntity.setRadius(queryBean.getRadius());
                accessor.save(incidentCircleEntity);
            }else{
                incidentCircleEntity= FireTransformUtil.transform(queryBean);
                 accessor.save(incidentCircleEntity);
            }

            Long endincidentCircleSave = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "saveIncidentCircle", String.format("repository is finished,execute time is :%sms", endincidentCircleSave - startincidentCircleSave));

            res=FireTransformUtil.transform(incidentCircleEntity);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findIncidentDossierDrawing", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;

        }catch (Exception ex){
            logService.erorLog(logger, "service", "saveIncidentCircle", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_INCIDENTCIRCLE_FALL);
        }
    }
}
