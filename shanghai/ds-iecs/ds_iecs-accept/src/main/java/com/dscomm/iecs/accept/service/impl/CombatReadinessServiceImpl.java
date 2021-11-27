package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.CombatReadinessEntity;
import com.dscomm.iecs.accept.dal.repository.CombatReadinessRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.fireinputbean.CombatReadinessInputInfo;
import com.dscomm.iecs.accept.graphql.firetypebean.CombatReadinessBean;
import com.dscomm.iecs.accept.service.CombatReadinessService;
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

@Component("combatReadinessServiceImpl")
public class CombatReadinessServiceImpl implements CombatReadinessService {
    private static final Logger logger = LoggerFactory.getLogger(AcceptanceServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private CombatReadinessRepository combatReadinessRepository;

    /**
     * 默认的构造函数
     */
    @Autowired
    public CombatReadinessServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor,
                                      CombatReadinessRepository combatReadinessRepository
    ) {
        this.logService = logService;
        this.accessor = accessor;
        this.combatReadinessRepository=combatReadinessRepository;
    }

    @Transactional(  readOnly =  true )
    @Override
    public List<CombatReadinessBean> findCombatReadiness(String type, Long showStartTime, Long showEndTime) {
        //参数判断
        if(null==type || null==showStartTime ||  null==showEndTime){
            logService.infoLog(logger, "service", "findCombatReadiness", "type,showStartTime,showEndTime is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findCombatReadiness", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<CombatReadinessBean> res = new ArrayList<>();

            logService.infoLog(logger, "repository", "find(findCombatReadiness)", "repository is started...");
            Long startHandleBatch = System.currentTimeMillis();

            List<CombatReadinessEntity> combatReadiness = combatReadinessRepository.findCombatReadiness(type, showStartTime, showEndTime);

            Long endHandleBatch = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "find(findCombatReadiness)", String.format("repository is finished,execute time is :%sms", endHandleBatch - startHandleBatch));

            for (CombatReadinessEntity combatReadinessEntity :  combatReadiness){
                CombatReadinessBean combatReadinessBean = FireTransformUtil.transform(  combatReadinessEntity );
                res.add( combatReadinessBean );
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findCombatReadiness", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res ;
        }catch (Exception ex){
            logService.erorLog(logger, "service", "findCombatReadiness", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_COMBATREADINESS_FALL);
        }


    }

    @Transactional(  readOnly =  true )
    @Override
    public CombatReadinessBean findCombatReadinessById(String id) {

        if(StringUtils.isBlank(id)){
            logService.infoLog(logger, "service", "findCombatReadinessById", "id is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        try {
            logService.infoLog(logger, "service", "findCombatReadinessById", "service is started...");
            Long logStart = System.currentTimeMillis();

            CombatReadinessBean res = null ;

            logService.infoLog(logger, "repository", "find(CombatReadinessEntity)", "repository is started...");
            Long startHandleBatch = System.currentTimeMillis();

            CombatReadinessEntity combatReadinessEntity = accessor.getById(id, CombatReadinessEntity.class);

            Long endHandleBatch = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "find(CombatReadinessEntity)", String.format("repository is finished,execute time is :%sms", endHandleBatch - startHandleBatch));

            if ( null != combatReadinessEntity && combatReadinessEntity.isValid()  ){
                 res = FireTransformUtil.transform(  combatReadinessEntity );
            }else{
                logService.infoLog(logger, "service", "findCombatReadinessById", "data is null.");
                throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_NULL);
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveCombatReadiness", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return  res ;

        }catch (Exception ex){
            logService.erorLog(logger, "service", "findCombatReadinessById", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_COMBATREADINESS_FALL);
        }

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CombatReadinessBean saveCombatReadiness(CombatReadinessInputInfo queryBean) {
        if ( null == queryBean ) {
            logService.infoLog(logger, "service", "saveCombatReadiness", "CombatReadinessInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {

            logService.infoLog(logger, "service", "saveCombatReadiness", "service is started...");
            Long logStart = System.currentTimeMillis();

            CombatReadinessBean res = null ;

            CombatReadinessEntity combatReadinessEntity = FireTransformUtil.transform(queryBean);
            if (null != combatReadinessEntity ){

                logService.infoLog(logger, "repository", "save(CombatReadinessEntity)", "repository is started...");
                Long startHandleBatch = System.currentTimeMillis();

                accessor.save( combatReadinessEntity ) ;

                Long endHandleBatch = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(CombatReadinessEntity)", String.format("repository is finished,execute time is :%sms", endHandleBatch - startHandleBatch));

                res=FireTransformUtil.transform(combatReadinessEntity );
            }
        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "saveCombatReadiness", String.format("service is finished,execute time is :%sms", logEnd - logStart));

        return  res;

        }catch (Exception ex){
            logService.erorLog(logger, "service", "saveCombatReadiness", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_COMBATREADINESS_FALL);
        }

    }



    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean removeCombatReadiness(String id) {
        if(StringUtils.isBlank(id)){
            logService.infoLog(logger, "service", "removeCombatReadiness", "incidentId is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "removeCombatReadiness", "service is started...");
            Long logStart = System.currentTimeMillis();

            logService.infoLog(logger, "repository", "remove(CombatReadinessEntity)", "repository is started...");
            Long startHandleBatch = System.currentTimeMillis();

            CombatReadinessEntity remove =   accessor.getById(id ,  CombatReadinessEntity.class);
            if( remove != null ){
                remove.setValid( Boolean.FALSE );
                accessor.save( remove ) ;
            }

            Long endHandleBatch = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "remove(CombatReadinessEntity)", String.format("repository is finished,execute time is :%sms", endHandleBatch - startHandleBatch));


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "removeCombatReadiness", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return  Boolean.TRUE;
        }catch (Exception ex){
            logService.erorLog(logger, "service", "removeCombatReadiness", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.REMOVE_COMBATREADINESS_FALL);
        }

    }
}
