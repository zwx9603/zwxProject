package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.CommonPhraseEntity;
import com.dscomm.iecs.accept.dal.repository.CommonPhraseRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.CommonPhraseSaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.CommonPhraseBean;
import com.dscomm.iecs.accept.service.CommonPhraseService;
import com.dscomm.iecs.accept.utils.transform.FireTransformUtil;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import org.apache.logging.log4j.util.Strings;
import org.mx.StringUtils;
import org.mx.dal.service.GeneralAccessor;
import org.mx.error.UserInterfaceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 */
@Component("commonPhraseServiceImpl")
public class CommonPhraseServiceImpl implements CommonPhraseService {

    private static final Logger logger = LoggerFactory.getLogger(CommonPhraseServiceImpl.class);

    private LogService logService;
    private GeneralAccessor accessor;
    private CommonPhraseRepository commonPhraseRepository ;
    private OrganizationService organizationService ;

    @Autowired
    public CommonPhraseServiceImpl( LogService logService,  @Qualifier("generalAccessor") GeneralAccessor accessor ,
                                    CommonPhraseRepository commonPhraseRepository ,OrganizationService organizationService
    ) {
        this.accessor = accessor;
        this.logService = logService;
        this.commonPhraseRepository = commonPhraseRepository ;
        this.organizationService = organizationService ;
    }

    /**
     * 获取常用短语
     *
     * @param phraseType   短语类型
     * @param incidentType 事件类型
     * @return 常用短语列表
     */
    @Transactional( readOnly = true  )
    @Override
    public List<CommonPhraseBean> findCommonPhrase(String phraseType, String incidentType) {
        if(  Strings.isBlank( phraseType )  ){
            logService.infoLog(logger, "service", "findCommonPhrase", "phraseType is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findCommonPhrase", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<CommonPhraseBean>  res =  new ArrayList<>() ;


            List<CommonPhraseEntity> commonPhraseEntityList = new ArrayList<>() ;


            logService.infoLog(logger, "repository", "saveCommander", "repository is started...");
            Long startincidentCircleSave = System.currentTimeMillis();

            if ( Strings.isBlank( incidentType)  ) {
                commonPhraseEntityList = commonPhraseRepository.findCommonPhrase(phraseType, incidentType);
            } else {
                commonPhraseEntityList =  commonPhraseRepository.findCommonPhrase(phraseType);
            }

            Long endincidentCircleSave = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "saveCommander", String.format("repository is finished,execute time is :%sms", endincidentCircleSave - startincidentCircleSave));


            if( commonPhraseEntityList != null && commonPhraseEntityList.size() > 0  ){
                Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap() ;
                for ( CommonPhraseEntity commonPhraseEntity : commonPhraseEntityList ) {
                    CommonPhraseBean commonPhraseBean = FireTransformUtil.transform( commonPhraseEntity , organizationNameMap  ) ;
                    res.add( commonPhraseBean );
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findCommonPhrase", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res ;

        } catch (UserInterfaceException ex) {
            if (logger.isErrorEnabled()) {
                logger.error(String.format("findCommonPhrase() error:"), ex);
            }
            throw ex;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveCommander", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_FAIL );
        }
    }


    /**
     * 保存常用短语
     * @param queryBean
     * @return
     */
    @Transactional( rollbackFor = Exception.class)
    @Override
    public  CommonPhraseBean saveCommonPhrase( CommonPhraseSaveInputInfo queryBean){
        if(  null==queryBean   ){
            logService.infoLog(logger, "service", "saveCommonPhrase", "queryBean is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveCommonPhrase", "service is started...");
            Long logStart = System.currentTimeMillis();

            CommonPhraseBean res = null ;

            CommonPhraseEntity commonPhraseEntity = FireTransformUtil.transform(queryBean);

            logService.infoLog(logger, "repository", "saveCommonPhrase", "repository is started...");
            Long startincidentCircleSave = System.currentTimeMillis();

            accessor.save(commonPhraseEntity);

            Long endincidentCircleSave = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "saveCommonPhrase", String.format("repository is finished,execute time is :%sms", endincidentCircleSave - startincidentCircleSave));

            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap() ;
            res = FireTransformUtil.transform(commonPhraseEntity , organizationNameMap );

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveCommonPhrase", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;

        }catch (Exception ex){
            logService.erorLog(logger, "service", "saveCommonPhrase", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DATA_FAIL);
        }


    }


    @Transactional( rollbackFor = Exception.class)
    @Override
    public Boolean  removeCommonPhrase(String commonPhraseId )  {
        if(StringUtils.isBlank(commonPhraseId)){
            logService.infoLog(logger, "service", "removeCommonPhrase", "commonPhraseId is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "removeCommonPhrase", "service is started...");
            Long logStart = System.currentTimeMillis();

            Boolean res = true  ;

            logService.infoLog(logger, "repository", "find(CommanderEntity)", "repository is started...");
            Long startHandleBatch = System.currentTimeMillis();

            CommonPhraseEntity remove = accessor.getById(  commonPhraseId , CommonPhraseEntity.class);

            Long endHandleBatch = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "find(CommanderEntity)", String.format("repository is finished,execute time is :%sms", endHandleBatch - startHandleBatch));

            if( remove != null ){

                remove.setValid( false );

                logService.infoLog(logger, "repository", "remove(dbCommonPhraseEntity)", "repository is started...");
                Long startCommander = System.currentTimeMillis();

                accessor.save( remove ) ;

                Long endCommander = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "remove(dbCommonPhraseEntity)", String.format("repository is finished,execute time is :%sms", endCommander - startCommander));

            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "removeCommonPhrase", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return  res ;

        }catch (Exception ex){
            logService.erorLog(logger, "service", "removeCommonPhrase", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.REMOVE_COMMANDER_FALL);
        }

    }



}
