package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.EarlyWarningImportantEntity;
import com.dscomm.iecs.accept.dal.repository.EarlyWarningImportantRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.fireinputbean.EarlyWarningImportantSaveInputInfo;
import com.dscomm.iecs.accept.graphql.firetypebean.EarlyWarningImportantBean;
import com.dscomm.iecs.accept.service.EarlyWarningImportantService;
import com.dscomm.iecs.accept.utils.transform.FireTransformUtil;
import com.dscomm.iecs.base.service.log.LogService;
import org.mx.StringUtils;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Component("earlyWarningImportantServiceImpl")
public class EarlyWarningImportantServiceImpl implements EarlyWarningImportantService {
    private static final Logger logger = LoggerFactory.getLogger(EarlyWarningImportantServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private EarlyWarningImportantRepository earlyWarningImportantRepository;

    /**
     * 默认的构造函数
     */
    @Autowired
    public EarlyWarningImportantServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor,EarlyWarningImportantRepository earlyWarningImportantRepository
    ) {
        this.accessor = accessor;
        this.logService = logService;
        this.earlyWarningImportantRepository = earlyWarningImportantRepository;
    }



    @Transactional( readOnly = true )
    @Override
    public List<EarlyWarningImportantBean> findEarlyWarningImportant( String currentTime) {
        if(StringUtils.isBlank(currentTime)){
            logService.infoLog(logger, "service", "findConsensusInformationById", "currentTime is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveDrillPlan", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<EarlyWarningImportantBean> res = new ArrayList<>() ;

            logService.infoLog(logger, "repository", "findEarlyWarningImportant", "repository is started...");
            Long startHandleBatch = System.currentTimeMillis();

            List<EarlyWarningImportantEntity> earlyWarningImportantList = earlyWarningImportantRepository.findEarlyWarningImportant(currentTime);

            Long endHandleBatch = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findEarlyWarningImportant", String.format("repository is finished,execute time is :%sms", endHandleBatch - startHandleBatch));

            if( earlyWarningImportantList != null && earlyWarningImportantList.size() > 0 ){
                for (EarlyWarningImportantEntity  earlyWarningImportantEntity  : earlyWarningImportantList){
                    EarlyWarningImportantBean earlyWarningImportantBean = FireTransformUtil.transform( earlyWarningImportantEntity );
                    res.add( earlyWarningImportantBean );
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveDrillPlan", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res  ;
        }catch (Exception ex){
            logService.erorLog(logger, "service", "findEarlyWarningImportant", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_EARLYWARNINGIMPORTANT_FALL);
        }
    }

    @Transactional( rollbackFor = Exception.class)
    @Override
    public Boolean removeEarlyWarningImportant(String id) {

        if(StringUtils.isBlank(id)){
            logService.infoLog(logger, "service", "removeEarlyWarningImportant", "id is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {

            logService.infoLog(logger, "service", "removeEarlyWarningImportant", "service is started...");
            Long logStart = System.currentTimeMillis();

            Boolean res = false  ;

            logService.infoLog(logger, "repository", "find(EarlyWarningImportantEntity)", "repository is started...");
            Long startHandleBatch = System.currentTimeMillis();

            EarlyWarningImportantEntity remove = accessor.getById(  id , EarlyWarningImportantEntity.class);

            Long endHandleBatch = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "find(EarlyWarningImportantEntity)", String.format("repository is finished,execute time is :%sms", endHandleBatch - startHandleBatch));

            if( remove != null ){

                remove.setValid( false );

                logService.infoLog(logger, "repository", "remove(EarlyWarningImportantEntity)", "repository is started...");
                Long startCommander = System.currentTimeMillis();

                accessor.save( remove ) ;

                Long endCommander = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "remove(EarlyWarningImportantEntity)", String.format("repository is finished,execute time is :%sms", endCommander - startCommander));

                res=true;
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "removeEarlyWarningImportant", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return  res ;

        }catch (Exception ex){
            logService.erorLog(logger, "service", "removeEarlyWarningImportant", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.REMOVE_EARLYWARNINGIMPORTANT_FALL);
        }
    }

    @Transactional( readOnly = true )
    @Override
    public EarlyWarningImportantBean findEarlyWarningImportantById(String id) {
        if(StringUtils.isBlank(id)){
            logService.infoLog(logger, "service", "findEarlyWarningImportantById", "id is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {

            logService.infoLog(logger, "service", "findEarlyWarningImportantById", "service is started...");
            Long logStart = System.currentTimeMillis();

            EarlyWarningImportantBean res = null ;

            logService.infoLog(logger, "repository", "findEarlyWarningImportantById", "repository is started...");
            Long startHandleBatch = System.currentTimeMillis();

            EarlyWarningImportantEntity earlyWarningImportantEntity = earlyWarningImportantRepository.findEarlyWarningImportantById(id);

            Long endHandleBatch = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findEarlyWarningImportantById", String.format("repository is finished,execute time is :%sms", endHandleBatch - startHandleBatch));

            if( earlyWarningImportantEntity != null && earlyWarningImportantEntity.isValid() ){
                res = FireTransformUtil.transform(earlyWarningImportantEntity);
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findEarlyWarningImportantById", String.format("service is finished,execute time is :%sms", logEnd - logStart));


            return res ;
        }catch (Exception ex){
            logService.erorLog(logger, "service", "findEarlyWarningImportantById ", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_EARLYWARNINGIMPORTANT_FALL);
        }

    }

    @Transactional( rollbackFor = Exception.class)
    @Override
    public EarlyWarningImportantBean saveEarlyWarningImportant(EarlyWarningImportantSaveInputInfo queryBean) {
        if ( null == queryBean ) {
            logService.infoLog(logger, "service", "saveEarlyWarningImportant", "EarlyWarningImportantSaveInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveEarlyWarningImportant", "service is started...");
            Long logStart = System.currentTimeMillis();

            EarlyWarningImportantBean res = null ;

            EarlyWarningImportantEntity earlyWarningImportantEntity = FireTransformUtil.transform(queryBean);

            if( null != earlyWarningImportantEntity ){

                logService.infoLog(logger, "repository", "saveEarlyWarningImportant", "repository is started...");
                Long startHandleBatch = System.currentTimeMillis();

                accessor.save(earlyWarningImportantEntity);

                Long endHandleBatch = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "saveEarlyWarningImportant", String.format("repository is finished,execute time is :%sms", endHandleBatch - startHandleBatch));

                res = FireTransformUtil.transform( earlyWarningImportantEntity );
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveEarlyWarningImportant", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return  res;

        }catch (Exception ex){
            logService.erorLog(logger, "service", "saveEarlyWarningImportant", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_EARLYWARNINGIMPORTANT_FALL);
        }

    }
}
