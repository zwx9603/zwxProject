package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.ActionSummaryEntity;
import com.dscomm.iecs.accept.dal.repository.ActionSummaryRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.fireinputbean.ActionSummarySaveInputInfo;
import com.dscomm.iecs.accept.graphql.firetypebean.ActionSummaryBean;
import com.dscomm.iecs.accept.service.ActionSummaryService;
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

@Component("actionSummaryServiceImpl")
public class ActionSummaryServiceImpl implements ActionSummaryService {
    private static final Logger logger = LoggerFactory.getLogger(ActionSummaryServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private ActionSummaryRepository actionSummaryRepository;

    /**
     * 默认的构造函数
     */
    @Autowired
    public ActionSummaryServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor,
                                    ActionSummaryRepository actionSummaryRepository
    ) {
        this.accessor = accessor;
        this.logService = logService;
        this.actionSummaryRepository = actionSummaryRepository;
    }

    @Transactional( readOnly = true )
    @Override
    public List<ActionSummaryBean> findActionSummary(String incidentId ) {

        try {
            logService.infoLog(logger, "repository", "findActionSummary", "repository is started...");
            Long startHandleBatch = System.currentTimeMillis();

            List<ActionSummaryEntity> actionSummaryEntityList = actionSummaryRepository.findActionSummary(incidentId);

            Long endHandleBatch = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findActionSummary", String.format("repository is finished,execute time is :%sms", endHandleBatch - startHandleBatch));

            //List<CommanderBean> commanderBeansList = FireTransformUtil.transform(commanderEntityList);

            List<ActionSummaryBean> actionSummaryBeanList = new ArrayList<>();

            for (ActionSummaryEntity ew : actionSummaryEntityList){
                ActionSummaryBean actionSummaryBean=new ActionSummaryBean();
                actionSummaryBean.setCommandId(ew.getCommandId());
                actionSummaryBean.setContent(ew.getContent());
                actionSummaryBean.setEvaluationTime(ew.getEvaluationTime());
                actionSummaryBean.setEvaluatorOrganizatioName(ew.getEvaluatorOrganizatioName());
                actionSummaryBean.setEvaluatorOrganizationId(ew.getEvaluatorOrganizationId());
                actionSummaryBean.setEvaluatorPersonId(ew.getEvaluatorPersonId());
                actionSummaryBean.setEvaluatorPersonIName(ew.getEvaluatorPersonIName());
                actionSummaryBean.setExperience(ew.getExperience());
                actionSummaryBean.setId(ew.getId());
                actionSummaryBean.setIncidentId(ew.getIncidentId());
                actionSummaryBean.setLabel(ew.getLabel());
                actionSummaryBean.setLesson(ew.getLesson());
                actionSummaryBean.setProcedure(ew.getProcedure());
                actionSummaryBean.setProcedureTime(ew.getProcedureTime());
                actionSummaryBean.setRemarks(ew.getRemarks());
                actionSummaryBean.setScore(ew.getScore());
                actionSummaryBeanList.add(actionSummaryBean);
            }

            return actionSummaryBeanList;
        }catch (Exception ex){
            logService.erorLog(logger, "service", "findActionSummary", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_ACTIONSUMMARY_FALL);
        }
    }

    @Transactional( readOnly = true )
    @Override
    public ActionSummaryBean findActionSummaryBeanById(String id) {

        if(StringUtils.isBlank(id)){
            logService.infoLog(logger, "service", "findActionSummaryBeanById", "id is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "repository", "findActionSummaryBeanById", "repository is started...");
            Long startHandleBatch = System.currentTimeMillis();

            ActionSummaryEntity actionSummaryEntity = actionSummaryRepository.findActionSummaryById(id);

            Long endHandleBatch = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findActionSummaryBeanById", String.format("repository is finished,execute time is :%sms", endHandleBatch - startHandleBatch));


            ActionSummaryBean actionSummaryBean = FireTransformUtil.transform(actionSummaryEntity);


            return actionSummaryBean;
        }catch (Exception ex){
            logService.erorLog(logger, "service", "findActionSummaryBeanById", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_ACTIONSUMMARY_FALL);
        }

    }

    @Transactional( rollbackFor = Exception.class)
    @Override
    public Boolean removeActionSummary(String id) {
        if(StringUtils.isBlank(id)){
            logService.infoLog(logger, "service", "removeEarlyWarningImportant", "id is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {

            Boolean res = false  ;

            logService.infoLog(logger, "repository", "find(ActionSummaryEntity)", "repository is started...");
            Long startHandleBatch = System.currentTimeMillis();

            ActionSummaryEntity remove = actionSummaryRepository.findActionSummaryById(id);

            Long endHandleBatch = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "find(ActionSummaryEntity)", String.format("repository is finished,execute time is :%sms", endHandleBatch - startHandleBatch));

            logService.infoLog(logger, "repository", "remove(ActionSummaryEntity)", "repository is started...");
            Long startCommander = System.currentTimeMillis();


            if( remove != null ){
                remove.setValid( false );
                accessor.save( remove ) ;
                res=true;
            }

            Long endCommander = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "remove(ActionSummaryEntity)", String.format("repository is finished,execute time is :%sms", endCommander - startCommander));

            return  res ;

        }catch (Exception ex){
            logService.erorLog(logger, "service", "removeCommander", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.REMOVE_ACTIONSUMMARY_FALL);
        }
    }

    @Transactional( rollbackFor = Exception.class)
    @Override
    public ActionSummaryBean saveActionSummary(ActionSummarySaveInputInfo queryBean) {
        if ( null == queryBean ) {
            logService.infoLog(logger, "service", "saveActionSummary", "CommanderSaveInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveActionSummary", "service is started...");
            Long logStart = System.currentTimeMillis();

            logService.infoLog(logger, "repository", "findActionSummary", "repository is started...");
            Long startincidentCircle = System.currentTimeMillis();

            ActionSummaryEntity actionSummaryEntity = accessor.getById(queryBean.getId(),ActionSummaryEntity.class);

            Long endincidentCircle = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findActionSummary", String.format("repository is finished,execute time is :%sms", endincidentCircle - startincidentCircle));


            logService.infoLog(logger, "repository", "saveActionSummary", "repository is started...");
            Long startincidentCircleSave = System.currentTimeMillis();

            if ( null != actionSummaryEntity ){
                actionSummaryEntity.setCommandId(queryBean.getCommandId());
                actionSummaryEntity.setContent(queryBean.getContent());
                actionSummaryEntity.setEvaluationTime(queryBean.getEvaluationTime());
                actionSummaryEntity.setEvaluatorOrganizatioName(queryBean.getEvaluatorOrganizatioName());
                actionSummaryEntity.setEvaluatorOrganizationId(queryBean.getEvaluatorOrganizationId());
                actionSummaryEntity.setEvaluatorPersonId(queryBean.getEvaluatorPersonId());
                actionSummaryEntity.setEvaluatorPersonIName(queryBean.getEvaluatorPersonIName());
                actionSummaryEntity.setExperience(queryBean.getExperience());
                actionSummaryEntity.setIncidentId(queryBean.getIncidentId());
                actionSummaryEntity.setLabel(queryBean.getLabel());
                actionSummaryEntity.setLesson(queryBean.getLesson());
                actionSummaryEntity.setProcedure(queryBean.getProcedure());
                actionSummaryEntity.setProcedureTime(queryBean.getProcedureTime());
                actionSummaryEntity.setRemarks(queryBean.getRemarks());
                actionSummaryEntity.setScore(queryBean.getScore());
                actionSummaryEntity.setCreatedTime(queryBean.getEvaluationTime());
                actionSummaryEntity.setId(queryBean.getId());
                actionSummaryEntity =accessor.save(actionSummaryEntity);
            }else{
                actionSummaryEntity= FireTransformUtil.transform(queryBean);
                actionSummaryEntity = accessor.save(actionSummaryEntity);
            }

            Long endincidentCircleSave = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "saveActionSummary", String.format("repository is finished,execute time is :%sms", endincidentCircleSave - startincidentCircleSave));

            ActionSummaryBean res=FireTransformUtil.transform(actionSummaryEntity);

            return res;

        }catch (Exception ex){
            logService.erorLog(logger, "service", "saveActionSummary", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_COMMANDER_FALL);
        }

    }
}
