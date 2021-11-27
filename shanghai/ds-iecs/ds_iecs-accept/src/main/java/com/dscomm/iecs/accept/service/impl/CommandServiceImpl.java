package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.CommandEntity;
import com.dscomm.iecs.accept.dal.repository.CommandRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.fireinputbean.CommandSaveInputInfo;
import com.dscomm.iecs.accept.graphql.firetypebean.CommandBean;
import com.dscomm.iecs.accept.service.CommandService;
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


@Component("commandServiceImpl")
public class CommandServiceImpl implements CommandService {
    private static final Logger logger = LoggerFactory.getLogger(HandleServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private CommandRepository commandRepository;


    @Autowired
    public CommandServiceImpl(@Qualifier("generalAccessor") GeneralAccessor accessor, LogService logService,CommandRepository commandRepository) {
        this.accessor = accessor;
        this.logService = logService;
        this.commandRepository=commandRepository;
    }

    @Transactional( rollbackFor = Exception.class)
    @Override
    public CommandBean saveCommand(CommandSaveInputInfo queryBean) {
        if(null==queryBean || Strings.isBlank( queryBean.getIncidentId() )  ){
            logService.infoLog(logger, "service", "saveCommand", "CommandSaveInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {

            logService.infoLog(logger, "service", "saveCommand", "service is started...");
            Long logStart = System.currentTimeMillis();

            CommandBean res = null ;

            logService.infoLog(logger, "repository", "findCommand", "repository is started...");
            Long startincidentCircle = System.currentTimeMillis();

            List<CommandEntity> commandEntityList = commandRepository.findCommandEntitiesByIncidentId(queryBean.getIncidentId());

            Long endincidentCircle = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "saveCommand", String.format("repository is finished,execute time is :%sms", endincidentCircle - startincidentCircle));


            CommandEntity commandEntity = null ;

            if( commandEntityList != null && commandEntityList.size() > 0 ){
                commandEntity = commandEntityList.get(0) ;
            }

            if ( null == commandEntity     ){

                commandEntity= FireTransformUtil.transform(queryBean);

                logService.infoLog(logger, "repository", "saveCommand", "repository is started...");
                Long startincidentCircleSave = System.currentTimeMillis();

                commandEntity = accessor.save(commandEntity);

                Long endincidentCircleSave = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "saveCommand", String.format("repository is finished,execute time is :%sms", endincidentCircleSave - startincidentCircleSave));

            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveCommand", String.format("service is finished,execute time is :%sms", logEnd - logStart));


            res = FireTransformUtil.transform(commandEntity);

            return res;
        }catch (Exception ex){
            logService.erorLog(logger, "service", "saveCommand", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_COMMAND_FALL);
        }

    }
}
