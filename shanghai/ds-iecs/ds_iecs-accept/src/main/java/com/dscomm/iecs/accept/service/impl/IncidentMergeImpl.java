package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.IncidentMergeEntity;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.IncidentMergeSaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.IncidentMergeBean;
import com.dscomm.iecs.accept.service.IncidentMergeService;
import com.dscomm.iecs.accept.service.IncidentService;
import com.dscomm.iecs.accept.service.pushData.PushDataService;
import com.dscomm.iecs.accept.utils.transform.IncidentTransformUtil;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.keydata.service.ServletService;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述：警情合并记录  服务类实现
 */
@Component("incidentMergeImpl")
public class IncidentMergeImpl implements IncidentMergeService {
    private static final Logger logger = LoggerFactory.getLogger(IncidentMergeImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private Environment env;
    private ServletService servletService ;
    private IncidentService incidentService;
    private PushDataService pushDataService ;

    /**
     * 默认的构造函数
     */
    @Autowired
    public IncidentMergeImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor, Environment env , ServletService servletService,
                             IncidentService incidentService, PushDataService pushDataService

    ) {
        this.logService = logService;
        this.accessor = accessor;
        this.env = env;
        this.servletService = servletService ;
        this.incidentService = incidentService;
        this.pushDataService = pushDataService ;

    }

    /**
     * {@inheritDoc}
     *
     * @see IncidentMergeService#saveIncidentMergeRecord(IncidentMergeSaveInputInfo)
     */
    @Transactional( rollbackFor =  Exception.class )
    @Override
    public  Boolean  saveIncidentMergeRecord( IncidentMergeSaveInputInfo inputInfo ){
        if ( null == inputInfo    ) {
            logService.infoLog(logger, "service", "saveIncidentMergeRecord", "IncidentMergeSaveInputInfo or incidentId is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveIncidentMergeRecord", "service is started...");
            Long logStart = System.currentTimeMillis();

            //保存案件合并记录
            IncidentMergeEntity incidentMergeEntity = IncidentTransformUtil.transform(inputInfo , servletService.getSystemTime() );

            logService.infoLog(logger, "repository", "save(dbIncidentMergeEntity)", "repository is started...");
            Long saveStart = System.currentTimeMillis();

            incidentMergeEntity = accessor.save(incidentMergeEntity);

            Long saveEnd = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbIncidentMergeEntity)", String.format("repository is finished,execute time is :%sms", saveEnd - saveStart));

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveIncidentMergeRecord", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            IncidentMergeBean  incidentMergeBean =  incidentService.findIncidentMerge(incidentMergeEntity.getMainIncidentId());
            //其他
            Map<String, String > otherParams = new HashMap<>( ) ;
            pushDataService.pushIncidentMerger( incidentMergeBean  , otherParams ) ;


            return true ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveIncidentMergeRecord", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DATA_FAIL);
        }

    }


}
