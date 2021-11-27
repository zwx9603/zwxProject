package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.CommanderEntity;
import com.dscomm.iecs.accept.dal.repository.CommanderRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.fireinputbean.CommanderListSaveInputInfo;
import com.dscomm.iecs.accept.graphql.fireinputbean.CommanderSaveInputInfo;
import com.dscomm.iecs.accept.graphql.firetypebean.CommanderBean;
import com.dscomm.iecs.accept.service.CommanderService;
import com.dscomm.iecs.accept.service.IncidentService;
import com.dscomm.iecs.accept.utils.transform.FireTransformUtil;
import com.dscomm.iecs.base.service.NotifyActionService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.base.websocket.WebsocketCodeEnum;
import com.dscomm.iecs.basedata.graphql.typebean.EquipmentVehicleBean;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.basedata.service.VehicleService;
import org.apache.logging.log4j.util.Strings;
import org.mx.StringUtils;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component("commanderServiceImpl")
public class CommanderServiceImpl implements CommanderService {
    private static final Logger logger = LoggerFactory.getLogger(DrillPlanServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private CommanderRepository commanderRepository;
    private VehicleService vehicleService ;
    private IncidentService incidentService ;
    private OrganizationService organizationService;
    private NotifyActionService notifyActionService;
    /**
     * 默认的构造函数
     */
    @Autowired
    public CommanderServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor,
                                CommanderRepository commanderRepository ,VehicleService vehicleService ,
                                IncidentService incidentService ,NotifyActionService notifyActionService ,
                                OrganizationService organizationService
    ) {
        this.logService = logService;
        this.accessor = accessor;
        this.commanderRepository=commanderRepository;
        this.vehicleService = vehicleService ;
        this.incidentService = incidentService ;
        this.organizationService = organizationService ;
        this.notifyActionService = notifyActionService ;

    }

    @Transactional( rollbackFor = Exception.class)
    @Override
    public CommanderBean saveCommander(CommanderSaveInputInfo queryBean) {

        if(null==queryBean || Strings.isBlank( queryBean.getIncidentId() )){
            logService.infoLog(logger, "service", "saveCommander", "CommanderSaveInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveCommander", "service is started...");
            Long logStart = System.currentTimeMillis();

            CommanderBean res = null ;

            CommanderEntity commanderEntity = FireTransformUtil.transform(queryBean);

            logService.infoLog(logger, "repository", "saveCommander", "repository is started...");
            Long startincidentCircleSave = System.currentTimeMillis();

            accessor.save(commanderEntity);

            Long endincidentCircleSave = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "saveCommander", String.format("repository is finished,execute time is :%sms", endincidentCircleSave - startincidentCircleSave));

            if( commanderEntity != null ){
                EquipmentVehicleBean vehicleBean = null ;
                if( Strings.isNotBlank( commanderEntity.getVehicleId() ) ){
                    vehicleBean = vehicleService.findVehicleCache( commanderEntity.getVehicleId() ) ;
                }
                res = FireTransformUtil.transform(commanderEntity , vehicleBean );
            }

            //通知参与单位
            Set<String> orgSet = new HashSet<>();
            List<String> orgIds = incidentService.findIncidentParticipantOrganizationId(queryBean.getIncidentId());
            orgSet.addAll(orgIds);
            List<String> orgCodes = organizationService.findOrganizationCodesByIds(new ArrayList<>(orgSet));
            orgSet.addAll(orgCodes);
            notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.COMMANDER_UPDATE.getCode(), queryBean.getIncidentId() , orgSet);


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveCommander", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;

        }catch (Exception ex){
            logService.erorLog(logger, "service", "saveCommander", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_COMMANDER_FALL);
        }

    }


    @Transactional( rollbackFor = Exception.class)
    @Override
    public Boolean  saveCommanderList (CommanderListSaveInputInfo queryBean) {

        if(null==queryBean || queryBean.getCommanderList() == null || queryBean.getCommanderList().size() < 1 ){
            logService.infoLog(logger, "service", "saveCommanderList", "CommanderListSaveInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveCommanderList", "service is started...");
            Long logStart = System.currentTimeMillis();

            Boolean res = false ;

            if(  queryBean != null && queryBean.getCommanderList() != null && queryBean.getCommanderList().size() > 0 ){


                List<CommanderEntity> oldCommanderEntityList = commanderRepository.findCommanderByIncidentId( queryBean.getIncidentId() );

                accessor.remove( oldCommanderEntityList , true ) ;

                List<CommanderEntity> commanderEntityList = new ArrayList<>() ;
                for( CommanderSaveInputInfo inputInfo  : queryBean.getCommanderList() ){
                    CommanderEntity commanderEntity = FireTransformUtil.transform( inputInfo );
                    commanderEntityList.add( commanderEntity );
                }

                logService.infoLog(logger, "repository", "saveCommanderList", "repository is started...");
                Long startincidentCircleSave = System.currentTimeMillis();

                accessor.save( commanderEntityList );

                Long endincidentCircleSave = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "saveCommanderList", String.format("repository is finished,execute time is :%sms", endincidentCircleSave - startincidentCircleSave));


            }

            res  = true ;

            //通知参与单位
            Set<String> orgSet = new HashSet<>();
            List<String> orgIds = incidentService.findIncidentParticipantOrganizationId(queryBean.getIncidentId());
            orgSet.addAll(orgIds);
            List<String> orgCodes = organizationService.findOrganizationCodesByIds(new ArrayList<>(orgSet));
            orgSet.addAll(orgCodes);
            notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.COMMANDER_UPDATE.getCode(), queryBean.getIncidentId() , orgSet);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveCommanderList", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;

        }catch (Exception ex){
            logService.erorLog(logger, "service", "saveCommanderList", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_COMMANDER_FALL);
        }

    }

    @Transactional( rollbackFor = Exception.class)
    @Override
    public Boolean removeCommander(String id) {
        if(StringUtils.isBlank(id)){
           return  true ;
        }
        try {
            logService.infoLog(logger, "service", "saveCommander", "service is started...");
            Long logStart = System.currentTimeMillis();

            Boolean res = true  ;

            logService.infoLog(logger, "repository", "find(CommanderEntity)", "repository is started...");
            Long startHandleBatch = System.currentTimeMillis();

            CommanderEntity remove = accessor.getById(  id , CommanderEntity.class);

            Long endHandleBatch = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "find(CommanderEntity)", String.format("repository is finished,execute time is :%sms", endHandleBatch - startHandleBatch));

            if( remove != null ){

                remove.setValid( false );

                logService.infoLog(logger, "repository", "remove(CommanderEntity)", "repository is started...");
                Long startCommander = System.currentTimeMillis();
                accessor.save( remove ) ;

                Long endCommander = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "remove(CommanderEntity)", String.format("repository is finished,execute time is :%sms", endCommander - startCommander));

                //通知参与单位
                Set<String> orgSet = new HashSet<>();
                List<String> orgIds = incidentService.findIncidentParticipantOrganizationId(remove.getIncidentId());
                orgSet.addAll(orgIds);
                List<String> orgCodes = organizationService.findOrganizationCodesByIds(new ArrayList<>(orgSet));
                orgSet.addAll(orgCodes);
                notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.COMMANDER_UPDATE.getCode(), remove.getIncidentId() , orgSet);

            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveCommander", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return  res ;

        }catch (Exception ex){
            logService.erorLog(logger, "service", "removeCommander", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.REMOVE_COMMANDER_FALL);
        }

    }

    @Transactional( readOnly = true )
    @Override
    public List<CommanderBean> findCommander(String incidentId) {
        if(StringUtils.isBlank(incidentId)){
            logService.infoLog(logger, "service", "findCommander", "incidentId is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {

            logService.infoLog(logger, "service", "saveCommander", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<CommanderBean> res = new ArrayList<>() ;


            logService.infoLog(logger, "repository", "findCommander", "repository is started...");
            Long startHandleBatch = System.currentTimeMillis();

            List<CommanderEntity> commanderEntityList = commanderRepository.findCommanderByIncidentId(incidentId );

            Long endHandleBatch = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findCommander", String.format("repository is finished,execute time is :%sms", endHandleBatch - startHandleBatch));

           if( commanderEntityList != null && commanderEntityList.size() > 0 ){
               for (CommanderEntity commanderEntity : commanderEntityList){
                   EquipmentVehicleBean vehicleBean = null ;
                   if( Strings.isNotBlank( commanderEntity.getVehicleId() ) ){
                       vehicleBean = vehicleService.findVehicleCache( commanderEntity.getVehicleId() ) ;
                   }
                   CommanderBean commanderBean = FireTransformUtil.transform(commanderEntity , vehicleBean );
                   res.add( commanderBean ) ;
               }
           }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findCommander", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        }catch (Exception ex){
            logService.erorLog(logger, "service", "findCommander", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_COMMANDER_FALL);
        }
    }
}
