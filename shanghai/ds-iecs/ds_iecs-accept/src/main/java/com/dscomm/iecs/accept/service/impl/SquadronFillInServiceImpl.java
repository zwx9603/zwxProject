package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.SquadronFillInEntity;
import com.dscomm.iecs.accept.dal.repository.SquadronFillInRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.SquadronFillInSaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.SquadronFillInBean;
import com.dscomm.iecs.accept.service.SquadronFillInService;
import com.dscomm.iecs.accept.utils.transform.HandleDispatchTransformUtil;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import org.apache.logging.log4j.util.Strings;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * 描述：中队填报 服务类实现
 */
@Component("squadronFillInServiceImpl")
public class SquadronFillInServiceImpl implements SquadronFillInService {
    private static final Logger logger = LoggerFactory.getLogger(SquadronFillInServiceImpl.class);
    private Environment env;
    private LogService logService;
    private GeneralAccessor accessor;
    private OrganizationService organizationService;
    private SquadronFillInRepository squadronFillInRepository ;
    /**
     * 默认的构造函数
     */
    @Autowired
    public SquadronFillInServiceImpl(  Environment env , @Qualifier("generalAccessor") GeneralAccessor accessor, LogService logService,
                                       OrganizationService organizationService , SquadronFillInRepository squadronFillInRepository

    ) {
        this.env = env;
        this.accessor = accessor;
        this.logService = logService;
        this.organizationService = organizationService;
        this.squadronFillInRepository = squadronFillInRepository ;
    }

    /**
     * {@inheritDoc}
     *
     * @see #findSquadronFillIn(String , String )
     */
    @Transactional(readOnly = true)
    @Override
    public List<SquadronFillInBean> findSquadronFillIn (String incidentId , String organizationId  )  {
        if ( Strings.isBlank(incidentId)) {
            logService.infoLog(logger, "service", "findSquadronFillIn", " incidentId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findSquadronFillIn", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<SquadronFillInBean> res = new ArrayList<>();


            List<SquadronFillInEntity> squadronFillInEntityList = null ;

            logService.infoLog(logger, "repository", "findSquadronFillIn", "repository is started...");
            Long start = System.currentTimeMillis();

            if( Strings.isBlank( organizationId  )){
                squadronFillInEntityList  = squadronFillInRepository.findSquadronFillIn(incidentId);
            }else{
                String  searchPath = organizationService.findSearchPathById(organizationId) ;
                squadronFillInEntityList = squadronFillInRepository.findSquadronFillIn(incidentId , searchPath + "%" );
            }

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findSquadronFillIn", String.format("repository is finished,execute time is :%sms", end - start));

            if( squadronFillInEntityList != null && squadronFillInEntityList.size() > 0 ){
                 for( SquadronFillInEntity squadronFillInEntity : squadronFillInEntityList ){
                     SquadronFillInBean squadronFillInBean  = HandleDispatchTransformUtil.transform( squadronFillInEntity , organizationService.findOrganizationNameMap() ) ;
                     res.add( squadronFillInBean ) ;
                 }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findSquadronFillIn", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findSquadronFillIn", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_SQUADRON_FILL_IN_FAIL);
        }

    }


    /**
     * {@inheritDoc}
     *
     * @see  #saveSquadronFillIn(SquadronFillInSaveInputInfo)
     */
    @Transactional( rollbackFor =  Exception.class )
    @Override
    public SquadronFillInBean  saveSquadronFillIn( SquadronFillInSaveInputInfo inputInfo ) {
        if ( null == inputInfo ) {
            logService.infoLog(logger, "service", "saveSquadronFillIn", " SquadronFillInSaveInputInfo is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveSquadronFillIn", "service is started...");
            Long logStart = System.currentTimeMillis();

            SquadronFillInBean res = null ;

            SquadronFillInEntity squadronFillInEntity = HandleDispatchTransformUtil.transform( inputInfo );
            if( squadronFillInEntity != null ){
                logService.infoLog(logger, "repository", "save(dbSquadronFillInEntity)", "repository is started...");
                Long start = System.currentTimeMillis();

                accessor.save( squadronFillInEntity ) ;

                Long end = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbSquadronFillInEntity)", String.format("repository is finished,execute time is :%sms", end - start));
            }

            res = HandleDispatchTransformUtil.transform( squadronFillInEntity , organizationService.findOrganizationNameMap() ) ;

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveSquadronFillIn", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveSquadronFillIn", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_SQUADRON_FILL_IN_FAIL);
        }

    }



}
