package com.dscomm.iecs.accept.service.impl;


import com.dscomm.iecs.accept.dal.po.IncidentEntity;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.firetypebean.CommanderBean;
import com.dscomm.iecs.accept.graphql.inputbean.InstructionQueryInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.*;
import com.dscomm.iecs.accept.service.*;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.graphql.typebean.AttachmentBean;
import com.dscomm.iecs.basedata.graphql.typebean.VehiclePersonsBean;
import com.dscomm.iecs.basedata.service.AttachmentService;
import com.dscomm.iecs.basedata.service.VehiclePersonService;
import org.apache.logging.log4j.util.Strings;
import org.mx.DigestUtils;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述 : 警情详情service服务实现
 *
 */
@Component("incidentInfoServiceImpl")
public class IncidentInfoServiceImpl implements IncidentInfoService {
    private static final Logger logger = LoggerFactory.getLogger(IncidentInfoServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private AccidentService accidentService;
    private LocaleService localeService;
    private AcceptanceService acceptanceService;
    private HandleService handleService;
    private AttachmentService attachmentService;
    private IncidentService incidentService ;
    private InstructionService instructionService;
    private VehiclePersonService vehiclePersonService ;
    private CommanderService commanderService ;

    @Autowired
    public IncidentInfoServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor,   AccidentService accidentService,
                                   LocaleService localeService, AttachmentService attachmentService, HandleService handleService ,
                                   AcceptanceService acceptanceService, IncidentService incidentService ,InstructionService instructionService,
                                   VehiclePersonService vehiclePersonService ,
                                   CommanderService commanderService
    ) {
        this.logService = logService;
        this.accessor = accessor;
        this.accidentService = accidentService;
        this.localeService = localeService;
        this.attachmentService = attachmentService;
        this.acceptanceService = acceptanceService;
        this.handleService = handleService;
        this.incidentService = incidentService ;
        this.instructionService=instructionService;
        this.vehiclePersonService = vehiclePersonService ;
        this.commanderService= commanderService ;
    }

    /**
     * {@inheritDoc}
     *
     * @see IncidentInfoService#findIncidentDossier(String )
     */
    @Transactional(readOnly = true)
    @Override
    public IncidentDossierBean findIncidentDossier(String incidentId ) {
        if (Strings.isBlank(incidentId)) {
            logService.infoLog(logger, "service", "findIncidentDossier", "incidentId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findIncidentDossier", "service is started...");
            Long logStart = System.currentTimeMillis();

            IncidentDossierBean res = new IncidentDossierBean();


            //获取案件详情
//            IncidentEntity incidentEntity = accessor.getById(incidentId,IncidentEntity.class);
//            if(incidentEntity == null){
//                logService.infoLog(logger, "service", "findIncidentDossier", "incidentEntity is null.");
//                throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_NULL);
//            }
            IncidentMergeBean incidentMergeBean = incidentService.findIncidentMerge( incidentId );
            IncidentBean incidentBean = incidentMergeBean.getMainIncident() ;
            List<IncidentBean> relatedIncidents = incidentMergeBean.getMergeIncidents() ;

            AcceptanceInformationBean acceptanceInformationBean = acceptanceService.findAcceptance( incidentId ) ;

            List<HandleBean> handleBeanList = handleService.findHandle(incidentId,null);

            InstructionQueryInputInfo sceneInstructionQueryInputInfo = new InstructionQueryInputInfo() ;
            sceneInstructionQueryInputInfo.setIncidentId(incidentId);
            List<String> instructionsSource = new ArrayList<>();
            instructionsSource.add("1") ;// 指令来源  1 接处警 2 图上指挥  3 移动 可拓展
            sceneInstructionQueryInputInfo.setInstructionsSource( instructionsSource );
            List<InstructionBean> sceneInstructionBeanList = instructionService.findInstructionCondition(sceneInstructionQueryInputInfo) ;

            AccidentBean accidentBean = accidentService.findAccident(incidentId);
            List<LocaleBean> localeBeanList = localeService.findLocale(incidentId);


            List<AttachmentBean> attachmentBeanList = attachmentService.findAttachment(incidentId,null);

            res.setIncidentId(incidentId);
            res.setIncidentBean(incidentBean);
            res.setRelatedIncidentBeanList(relatedIncidents);
            res.setAcceptanceInformationBean( acceptanceInformationBean );

            res.setHandleBeanList(handleBeanList);
            res.setSceneInstructionBeanList( sceneInstructionBeanList );

            res.setAccidentBean(accidentBean);
            res.setLocaleBeanList(localeBeanList);
            res.setAttachmentBeanList(attachmentBeanList);

//            //电话报警信息( 仅仅 报警信息 )
//            TelephoneInformationBean telephoneInformationBean = telephoneService.findTelephone(incidentId);
//            res.setTelephoneInformationBean( telephoneInformationBean );
//            List<EarlyWarningBean> earlyWarningBeanList = earlyWarningService.findEarlyWarningByIncidentId(incidentId);
//            res.setEarlyWarningBeanList(earlyWarningBeanList);
//            List<DocumentBean> documentBeanList = documentService.findDocument(incidentId ,null , null );
//            res.setDocumentBeanList(documentBeanList);
//            List<ParticipantFeedbackBean> participantFeedbackBeanList = participantFeedbackService.findIncidentParticipant(incidentId);
//            List<FireSafetyMonitoringBean> fireSafetyMonitoringBeanList = fireSafetyMonitoringService.findIncidentFireSafetyMonitoring(incidentId);
//            res.setParticipantFeedbackBeanList(participantFeedbackBeanList);
//            res.setFireSafetyMonitoringBeanList(fireSafetyMonitoringBeanList);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findIncidentDossier", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findIncidentDossier", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_INCIDENT_DOSSIER_FAIL);
        }

    }


    /**
     * {@inheritDoc}
     *
     * @see  #saveIncidentGroup(String  , String )
     */
    @Transactional( rollbackFor = Exception.class )
    @Override
    public  Boolean  saveIncidentGroup (String incidentId , String incidentGroupId ){
        if (Strings.isBlank(incidentId)) {
            logService.infoLog(logger, "service", "saveIncidentGroup", "incidentId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveIncidentGroup", "service is started...");
            Long logStart = System.currentTimeMillis();


            Boolean res = false ;

            //获取案件详情
            IncidentEntity incidentEntity = accessor.getById(incidentId,IncidentEntity.class);
            if(incidentEntity == null){
                logService.infoLog(logger, "service", "saveIncidentGroup", "incidentEntity is null.");
                throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_NULL);
            }

            incidentEntity.setIncidentGroupId( incidentGroupId );

            logService.infoLog(logger, "repository", "save(dbIncidentEntity)", "repository is started...");
            Long incidentStart = System.currentTimeMillis();

            accessor.save(incidentEntity);

            Long incidentEnd = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbIncidentEntity)", String.format("repository is finished,execute time is :%sms", incidentEnd - incidentStart));


            res = true ;

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveIncidentGroup", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveIncidentGroup", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.UPDATE_INCIDENT_FAIL);
        }

    }


    /**
     * {@inheritDoc}
     *
     * @see  #findVehiclePersonsByIncidentId(String  , Boolean   )
     */
    @Transactional( readOnly =  true )
    @Override
    public  List<CommanderBean> findVehiclePersonsByIncidentId (String incidentId , Boolean  whetherNotCommander ){
        if (Strings.isBlank(incidentId)) {
            logService.infoLog(logger, "service", "findVehiclePersonsByIncidentId", "incidentId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findVehiclePersonsByIncidentId", "service is started...");
            Long logStart = System.currentTimeMillis();


            List<CommanderBean> res =  new ArrayList<>() ;

            List<CommanderBean> temp =  new ArrayList<>() ;

            //根据案件id 获得 出动车辆信息
            List<String> vehicleIds = handleService.findIncidentHandleOrganizationVehicleId(incidentId) ;
            Map<String, VehiclePersonsBean> vehiclePersonsBeanMap = new HashMap<>() ;
            //获得车辆人员信息
            if( vehicleIds != null && vehicleIds.size() > 0 ){
                List<VehiclePersonsBean> vehiclePersonsBeanList  =  vehiclePersonService.findVehiclePersons( vehicleIds   ) ;
                for ( VehiclePersonsBean vehiclePersonsBean : vehiclePersonsBeanList ){
                    vehiclePersonsBeanMap.put( vehiclePersonsBean.getVehicleId() , vehiclePersonsBean ) ;
                }
            }

            //获得案件现有指挥员信息
            List<CommanderBean> commanderBeanList  = commanderService.findCommander( incidentId ) ;

            if(  whetherNotCommander ){
                temp.addAll( commanderBeanList ) ;
                List<String> commanderVehicleList = new ArrayList<>() ;
                for( CommanderBean commanderBean :  commanderBeanList ){
                    if( Strings.isNotBlank( commanderBean.getVehicleId() ) ){
                        commanderVehicleList.add( commanderBean.getVehicleId() ) ;
                    }
                }
                for( String key :  vehiclePersonsBeanMap.keySet() ){
                    if( !commanderVehicleList.contains( key ) ){
                        VehiclePersonsBean vehiclePersonsBean =  vehiclePersonsBeanMap.get( key ) ;
                        CommanderBean commanderBean = new CommanderBean();
                        //设置保存确定 设置临时id
                        commanderBean.setId( DigestUtils.uuid().replaceAll("-","") );

                        commanderBean.setVehicleId( vehiclePersonsBean.getVehicleId() );
                        commanderBean.setVehicleName( vehiclePersonsBean.getVehicleName() );
                        commanderBean.setVehicleNumber( vehiclePersonsBean.getVehicleNumber() );

                        commanderBean.setCommanderType("5"); //指挥员现场类型 (1:总指挥,2:副指挥,3:指挥长 4 指挥员 5车辆指挥员 )
                        commanderBean.setCommanderId( vehiclePersonsBean.getPersonId() );
                        commanderBean.setCommanderName( vehiclePersonsBean.getPersonName());
                        commanderBean.setCommanderDuty( vehiclePersonsBean.getPersonType());
                        commanderBean.setCommanderDutyName( vehiclePersonsBean.getPersonTypeName());
                        commanderBean.setOrganizationId( vehiclePersonsBean.getOrganizationId() );
                        commanderBean.setOrganizationName( vehiclePersonsBean.getOrganizationName() );

                        commanderBean.setDriver( vehiclePersonsBean.getDriver() );
                        commanderBean.setCorrespondent( vehiclePersonsBean.getCorrespondent() );

                        commanderBean.setPersonNum( vehiclePersonsBean.getPersonNum() );

                        temp.add( commanderBean ) ;
                    }
                }
            }else{
                temp.addAll( commanderBeanList ) ;
            }

            //对于车辆指挥信息 按照出动车辆顺序排序
            if( temp != null && temp.size() > 0 ){
                Map<String, CommanderBean> tempMap = new HashMap<>() ;
                Map<String, List<String> > tempIdMap = new HashMap<>() ;
                for(CommanderBean commander : temp){
                    tempMap.put( commander.getVehicleId() + "_" + commander.getId()  , commander ) ;
                    List<String>  tempIdList = tempIdMap.get( commander.getVehicleId() ) ;
                    if( tempIdList == null ){
                        tempIdList = new ArrayList<>() ;
                    }
                    tempIdList.add( commander.getVehicleId() + "_" + commander.getId()  ) ;
                    tempIdMap.put( commander.getVehicleId() , tempIdList  ) ;
                }
                List<HandleOrganizationVehicleBean> handleVehicle
                        = handleService.findHandleOrganizationVehicleGroup(incidentId, null , false );
               if( handleVehicle != null && handleVehicle.size() > 0 ){
                   List<String> doingVehicleIdList = new ArrayList<>() ;
                   for(HandleOrganizationVehicleBean vehicleBean :  handleVehicle ){
                        String vehicleId = vehicleBean.getVehicleId() ;
                        if( !doingVehicleIdList.contains( vehicleId ) ){
                            doingVehicleIdList.add( vehicleId ) ;

                            List<String> tempIdList = tempIdMap.get( vehicleId ) ;
                            if( tempIdList != null && tempIdList.size() > 0 ){
                                for( String tempId : tempIdList ){
                                    CommanderBean vehicleCommander =  tempMap.get( tempId ) ;
                                    if( vehicleCommander != null ){
                                        res.add( vehicleCommander ) ;
                                    }
                                }
                            }

                        }
                   }
               }

            }




            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findVehiclePersonsByIncidentId", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findVehiclePersonsByIncidentId", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DATA_FAIL);
        }

    }



}