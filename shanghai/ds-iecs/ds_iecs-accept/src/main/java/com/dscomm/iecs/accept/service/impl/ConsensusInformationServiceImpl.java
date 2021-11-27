package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.ConsensusInformationEntiy;
import com.dscomm.iecs.accept.dal.repository.ConsensusInformationRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.firetypebean.ConsensusInformationBean;
import com.dscomm.iecs.accept.service.ConsensusInformationService;
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
@Component("consensusInformationServiceImpl")
public class ConsensusInformationServiceImpl implements ConsensusInformationService {
    private static final Logger logger = LoggerFactory.getLogger(AttentionServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private ConsensusInformationRepository consensusInformationRepository;

    @Autowired
    public ConsensusInformationServiceImpl(@Qualifier("generalAccessor") GeneralAccessor accessor,LogService logService
            ,ConsensusInformationRepository consensusInformationRepository) {
        this.logService = logService;
        this.accessor = accessor;
        this.consensusInformationRepository=consensusInformationRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ConsensusInformationBean> findConsensusInformation( Long currentTimeStart, Long currentTimeEnd ) {
        if(null==currentTimeStart || null==currentTimeEnd ){
            logService.infoLog(logger, "service", "findConsensusInformationById", "currentTime is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "repository", "findEarlyWarningImportant", "repository is started...");
            Long startHandleBatch = System.currentTimeMillis();

            List<ConsensusInformationEntiy> consensusInformationEntiyList = consensusInformationRepository.findConsensusInformations(currentTimeStart,currentTimeEnd);

            Long endHandleBatch = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findEarlyWarningImportant", String.format("repository is finished,execute time is :%sms", endHandleBatch - startHandleBatch));

            List<ConsensusInformationBean> consensusInformationBeanList = new ArrayList<>();

            for (ConsensusInformationEntiy source : consensusInformationEntiyList){
                ConsensusInformationBean target=new ConsensusInformationBean();
                target.setTitle(source.getTitle());
                target.setRemarks(source.getRemarks());
                target.setPublisher(source.getPublisher());
                target.setPublishedUnit(source.getPublishedUnit());
                target.setPublishedTime(source.getPublishedTime());
                target.setId(source.getId());
                target.setContent(source.getContent());
                consensusInformationBeanList.add(target);
            }

            return consensusInformationBeanList;
        }catch (Exception ex){
            logService.erorLog(logger, "service", "findEarlyWarningImportant", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_EARLYWARNINGIMPORTANT_FALL);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public ConsensusInformationBean findConsensusInformationById(String id) {

        if(StringUtils.isBlank(id)){
            logService.infoLog(logger, "service", "findConsensusInformationById", "id is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "repository", "findConsensusInformationById", "repository is started...");
            Long startHandleBatch = System.currentTimeMillis();

            ConsensusInformationEntiy consensusInformationEntiy = consensusInformationRepository.findConsensusInformationsById(id);

            Long endHandleBatch = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findConsensusInformationById", String.format("repository is finished,execute time is :%sms", endHandleBatch - startHandleBatch));


            ConsensusInformationBean res = FireTransformUtil.transform(consensusInformationEntiy);
            return res;


        }catch (Exception ex){
            logService.erorLog(logger, "service", "findConsensusInformationById", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_CONSENSUSINFORMATIOM_FALL);
        }
    }
}
