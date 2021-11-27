package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.InteractiveRecordEntity;
import com.dscomm.iecs.accept.dal.repository.InteractiveRecordRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.InteractiveRecordSaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.InteractiveRecordBean;
import com.dscomm.iecs.accept.service.InteractiveRecordService;
import com.dscomm.iecs.agent.service.UserService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;
import com.dscomm.iecs.keydata.service.ServletService;
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
import java.util.List;

@Component("interactiveRecordServiceImpl")
public class InteractiveRecordServiceImpl implements InteractiveRecordService {
    private static final Logger logger = LoggerFactory.getLogger(InteractiveRecordServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private UserService userService;
    private ServletService servletService ;
    private InteractiveRecordRepository interactiveRecordRepository ;


    @Autowired
    public InteractiveRecordServiceImpl (LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor,
                                         UserService userService  ,
                                         ServletService servletService ,
                                         InteractiveRecordRepository interactiveRecordRepository

    ){
        this.logService = logService;
        this.accessor = accessor;
        this.userService = userService;
        this.servletService = servletService ;
        this.interactiveRecordRepository = interactiveRecordRepository ;

    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public InteractiveRecordBean saveInteractiveRecord (InteractiveRecordSaveInputInfo inputInfo , String alarmNumber ) {
        if (inputInfo == null || StringUtils.isBlank(inputInfo.getSenderPhoneNum())){
            logService.infoLog(logger, "service", "saveInteractiveRecord", "inputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveInteractiveRecord", "service is started...");
            Long logStart = System.currentTimeMillis();

            InteractiveRecordBean res = null ;

            InteractiveRecordEntity entity =  transform( inputInfo ,  servletService.getSystemTime() );

            if (inputInfo.getMessageType() != null && inputInfo.getMessageType() == 1) {
                UserInfo userInfo = userService.getUserInfo();
                entity.setSenderIdNum(userInfo.getAccount());
            }
            entity.setAlarmId( alarmNumber );


            logService.infoLog(logger, "repository", "save(dbInteractiveRecordEntity)", "repository is started...");
            Long countStart = System.currentTimeMillis();

            entity = accessor.save(entity);

            Long countEnd = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbInteractiveRecordEntity)", String.format("repository is finished,execute time is :%sms", countEnd - countStart));

            res  =  transform(entity);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveInteractiveRecord", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return  res ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveInteractiveRecord", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_UNCALLACCEPT_FAIL);
        }
    }



    @Transactional(rollbackFor = Exception.class)
    @Override
    public  Boolean  saveInteractiveRecordToIncident  ( String alarmNumber , String incidentId  ) {
        if ( Strings.isBlank( alarmNumber) || Strings.isBlank( incidentId )){
            logService.infoLog(logger, "service", "saveInteractiveRecordToIncident", "inputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveInteractiveRecordToIncident", "service is started...");
            Long logStart = System.currentTimeMillis();

            Boolean res = false ;

            logService.infoLog(logger, "repository", "saveInteractiveRecordToIncident( alarmNumber , incidentId )", "repository is started...");
            Long countStart = System.currentTimeMillis();

            interactiveRecordRepository.saveInteractiveRecordToIncident( alarmNumber , incidentId ) ;

            Long countEnd = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "saveInteractiveRecordToIncident( alarmNumber , incidentId )", String.format("repository is finished,execute time is :%sms", countEnd - countStart));

            res = true ;

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveInteractiveRecordToIncident", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return  res ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveInteractiveRecordToIncident", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_UNCALLACCEPT_FAIL);
        }
    }



    @Transactional( readOnly = true )
    @Override
    public  List<InteractiveRecordBean> findAllInteractiveRecordByPhoneNum ( String phoneNum ) {
        if (Strings.isBlank( phoneNum )) {
            logService.infoLog(logger, "service", "findIncidentsCondition", "IncidentQueryInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findIncidentsCondition", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<InteractiveRecordBean> res = new ArrayList<>();

            List<GeneralAccessor.ConditionGroup> queryGroups = new ArrayList<>();

            queryGroups.add(GeneralAccessor.ConditionTuple.eq("valid",1));

            if ( Strings.isNotBlank(phoneNum  )){
                queryGroups.add(GeneralAccessor.ConditionTuple.eq("senderPhoneNum",phoneNum ) );
            }

            GeneralAccessor.RecordOrderGroup uncallAcceptOrder = GeneralAccessor.RecordOrderGroup.group(
              GeneralAccessor.RecordOrder.asc("sendTime")
            );

            logService.infoLog(logger, "repository", "find(dbInteractiveRecordEntity)", "repository is started...");
            Long countStart = System.currentTimeMillis();

            List<InteractiveRecordEntity> interactiveRecordEntityList = accessor.find(null, GeneralAccessor.ConditionGroup.and(queryGroups),uncallAcceptOrder,InteractiveRecordEntity.class);

            Long countEnd = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "find(dbInteractiveRecordEntity)", String.format("repository is finished,execute time is :%sms", countEnd - countStart));

            if( interactiveRecordEntityList != null && interactiveRecordEntityList.size() > 0  ){
                for( InteractiveRecordEntity interactiveRecordEntity :  interactiveRecordEntityList ){
                    InteractiveRecordBean interactiveRecordBean   =  transform( interactiveRecordEntity );
                    res.add( interactiveRecordBean ) ;
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findIncidentsCondition", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findIncidentsCondition", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_INCIDENTS_FAIL);
        }
    }


    @Transactional( readOnly = true )
    @Override
    public    List<InteractiveRecordBean> findAllInteractiveRecordByAlarmNumber  ( String alarmNumber ) {
        if (Strings.isBlank( alarmNumber )) {
            logService.infoLog(logger, "service", "findAllInteractiveRecordByAlarmNumber", "alarmNumber is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findAllInteractiveRecordByAlarmNumber", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<InteractiveRecordBean> res = new ArrayList<>();

            List<GeneralAccessor.ConditionGroup> queryGroups = new ArrayList<>();

            queryGroups.add(GeneralAccessor.ConditionTuple.eq("valid",1));

            if ( Strings.isNotBlank(alarmNumber  )){
                queryGroups.add(GeneralAccessor.ConditionTuple.eq("alarmId",alarmNumber ) );
            }

            GeneralAccessor.RecordOrderGroup uncallAcceptOrder = GeneralAccessor.RecordOrderGroup.group(
                    GeneralAccessor.RecordOrder.asc("sendTime")
            );

            logService.infoLog(logger, "repository", "find(dbInteractiveRecordEntity)", "repository is started...");
            Long countStart = System.currentTimeMillis();

            List<InteractiveRecordEntity> interactiveRecordEntityList = accessor.find(null, GeneralAccessor.ConditionGroup.and(queryGroups),uncallAcceptOrder,InteractiveRecordEntity.class);

            Long countEnd = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "find(dbInteractiveRecordEntity)", String.format("repository is finished,execute time is :%sms", countEnd - countStart));

            if( interactiveRecordEntityList != null && interactiveRecordEntityList.size() > 0  ){
                for( InteractiveRecordEntity interactiveRecordEntity :  interactiveRecordEntityList ){
                    InteractiveRecordBean interactiveRecordBean   =  transform( interactiveRecordEntity );
                    res.add( interactiveRecordBean ) ;
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findAllInteractiveRecordByAlarmNumber", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findAllInteractiveRecordByAlarmNumber", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_INCIDENTS_FAIL);
        }
    }


    @Transactional( readOnly = true )
    @Override
    public  List<InteractiveRecordBean> findAllInteractiveRecordByIncidentId ( String incidentId ) {
        if (Strings.isBlank( incidentId )) {
            logService.infoLog(logger, "service", "findAllInteractiveRecordByIncidentId", "incidentId is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findAllInteractiveRecordByIncidentId", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<InteractiveRecordBean> res = new ArrayList<>();

            List<GeneralAccessor.ConditionGroup> queryGroups = new ArrayList<>();

            queryGroups.add(GeneralAccessor.ConditionTuple.eq("valid",1));

            if ( Strings.isNotBlank(incidentId  )){
                queryGroups.add(GeneralAccessor.ConditionTuple.eq("incidentId",incidentId ) );
            }

            GeneralAccessor.RecordOrderGroup uncallAcceptOrder = GeneralAccessor.RecordOrderGroup.group(
                    GeneralAccessor.RecordOrder.asc("sendTime")
            );

            logService.infoLog(logger, "repository", "find(dbInteractiveRecordEntity)", "repository is started...");
            Long countStart = System.currentTimeMillis();

            List<InteractiveRecordEntity> interactiveRecordEntityList = accessor.find(null, GeneralAccessor.ConditionGroup.and(queryGroups),uncallAcceptOrder,InteractiveRecordEntity.class);

            Long countEnd = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "find(dbInteractiveRecordEntity)", String.format("repository is finished,execute time is :%sms", countEnd - countStart));

            if( interactiveRecordEntityList != null && interactiveRecordEntityList.size() > 0  ){
                for( InteractiveRecordEntity interactiveRecordEntity :  interactiveRecordEntityList ){
                    InteractiveRecordBean interactiveRecordBean   =  transform( interactiveRecordEntity );
                    res.add( interactiveRecordBean ) ;
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findAllInteractiveRecordByIncidentId", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findAllInteractiveRecordByIncidentId", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_INCIDENTS_FAIL);
        }
    }


    public  InteractiveRecordEntity transform (InteractiveRecordSaveInputInfo inputInfo , Long time){
        if (inputInfo != null) {
            InteractiveRecordEntity po = new InteractiveRecordEntity();
            po.setId(inputInfo.getId());
            po.setAlarmId(inputInfo.getAlarmId());
            po.setIncidentId( inputInfo.getIncidentId() );
            po.setSenderIdNum(inputInfo.getSenderIdNum());
            po.setSenderPhoneNum(inputInfo.getSenderPhoneNum());
            po.setSenderName(inputInfo.getSenderName());
            po.setSendTime(time);
            po.setMessageContent(inputInfo.getMessageContent());
            po.setMessageType(inputInfo.getMessageType());
            po.setMessageSource(inputInfo.getMessageSource());
            po.setResourceUrl(inputInfo.getResourceUrl());
            po.setRemark(inputInfo.getRemark());
            po.setState(inputInfo.getState());
            po.setXlabel(inputInfo.getXlabel());
            po.setYlabel(inputInfo.getYlabel());
            po.setReadMark(inputInfo.getReadMark());

            return  po ;
        }
        return  null ;
    }

    public  InteractiveRecordBean transform(  InteractiveRecordEntity entity) {

        InteractiveRecordBean bean = new InteractiveRecordBean();
        bean.setAlarmId(entity.getAlarmId());
        bean.setId(entity.getId());
        bean.setIncidentId( entity.getIncidentId() );
        bean.setMessageContent(entity.getMessageContent());
        bean.setRemark(entity.getRemark());
        bean.setResourceUrl(entity.getResourceUrl());
        bean.setSenderIdNum(entity.getSenderIdNum());
        bean.setSenderPhoneNum(entity.getSenderPhoneNum());
        bean.setSenderName(entity.getSenderName());
        bean.setSendTime(entity.getSendTime());
        bean.setXlabel(entity.getXlabel());
        bean.setYlabel(entity.getYlabel());
        bean.setMessageSource(entity.getMessageSource());
        bean.setMessageType(entity.getMessageType());
        bean.setReadMark(entity.getReadMark());
        bean.setState(entity.getState());

        return bean;
    }



}
