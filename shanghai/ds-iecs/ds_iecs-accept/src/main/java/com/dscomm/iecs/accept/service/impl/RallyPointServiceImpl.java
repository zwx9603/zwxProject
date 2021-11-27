package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.RallyItemEntity;
import com.dscomm.iecs.accept.dal.po.RallyItemFeedbackEntity;
import com.dscomm.iecs.accept.dal.po.RallyPointEntity;
import com.dscomm.iecs.accept.dal.repository.RallyPointRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.*;
import com.dscomm.iecs.accept.graphql.typebean.RallyItemBean;
import com.dscomm.iecs.accept.graphql.typebean.RallyItemFeedbackBean;
import com.dscomm.iecs.accept.graphql.typebean.RallyPointBean;
import com.dscomm.iecs.accept.service.DocumentService;
import com.dscomm.iecs.accept.service.InstructionService;
import com.dscomm.iecs.accept.service.RallyPointService;
import com.dscomm.iecs.accept.utils.EnumUtils;
import com.dscomm.iecs.accept.utils.transform.FireTransformUtil;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.ext.RallyPointTypeBakEnum;
import com.dscomm.iecs.ext.rallypointstatus.SAVED;
import com.dscomm.iecs.ext.rallypointstatus.SENT;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述： 保存指挥实现
 *
 * @author AIguibin Date time 2018/7/2 10:44
 */
@Component("rallyPointServiceImpl")
public class RallyPointServiceImpl implements RallyPointService {
    private static final Logger logger = LoggerFactory.getLogger(RallyPointServiceImpl.class);
    private GeneralAccessor accessor;
    private RallyPointRepository rallyPointRepository ;
    private DocumentService documentService ;
    private LogService logService;
    private InstructionService instructionService;

    @Autowired
    public RallyPointServiceImpl(@Qualifier("generalAccessor") GeneralAccessor accessor  , RallyPointRepository rallyPointRepository  ,
                                 DocumentService documentService,LogService logService,InstructionService instructionService
    ) {
        this.accessor = accessor;
        this.rallyPointRepository = rallyPointRepository ;
        this.documentService = documentService ;
        this.logService = logService;
        this.instructionService = instructionService;

    }
    /**
     * {@inheritDoc}
     *
     */
    @Transactional( rollbackFor =  Exception.class )
    @Override
    public List<RallyPointBean> saveOrUpdateRallyPoint(RallyPointSaveTargetInputInfo queryBean) {
        try {
            logService.infoLog(logger, "service", "saveOrUpdateRallyPoint", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<RallyPointBean> rallyPointBeans = new ArrayList<>();
            String incidentId  = queryBean.getIncidentId() ;
            String commandId  = queryBean.getCommandId()  ;
            List<RallyPointSaveInputInfo> rallyPoints =  queryBean.getRallyPoints() ;
            //保存 集结点信息
            for ( RallyPointSaveInputInfo rallyPointSaveInputInfo : rallyPoints ) {
                 // 判断新增 or  修改
                if( Strings.isBlank(  rallyPointSaveInputInfo.getId()  ) ){
                    //新增
                    //查询该指挥下是否存在同名集结点
                    logService.infoLog(logger, "repository", "findRallyPoint", "repository is started...");
                    Long start = System.currentTimeMillis();
                    String rallyPointId = rallyPointRepository.findRallyPoint( commandId , rallyPointSaveInputInfo.getRallyPointName() );
                    Long end = System.currentTimeMillis();
                    logService.infoLog(logger, "repository", "findRallyPoint", String.format("repository is finished,execute time is :%sms", end - start));

                    if (StringUtils.isBlank(rallyPointId)) {
                        RallyPointBean rallyPointBean = saveOrUpdateRallyPoint( rallyPointSaveInputInfo , incidentId , commandId ) ;
                        rallyPointBeans.add( rallyPointBean ) ;

                        //保存文书 信息
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String content =  String.format(" %s %s部署名为:%s%s", rallyPointSaveInputInfo.getRallyPointContacts(),
                                simpleDateFormat.format( System.currentTimeMillis() ) ,  rallyPointBean.getRallyPointName(),
                                EnumUtils.getEnumTitle(rallyPointBean.getRallyPointType(), RallyPointTypeBakEnum.class))  ;
                        logService.infoLog(logger, "service", "saveDocument", "service is started...");
                        Long start1 = System.currentTimeMillis();

//                        this.saveDocument( incidentId , "任务部署" ,content , queryBean.getPersonName() , queryBean.getOrganizationId() , queryBean.getOrganizationName()  ) ;

                        Long end1 = System.currentTimeMillis();
                        logService.infoLog(logger, "service", "saveDocument", String.format("service is finished,execute time is :%sms", end1 - start1));

                        if (logger.isDebugEnabled()) {
                            logger.debug("已保存");
                        }
                    } else {
                        if (logger.isDebugEnabled()) {
                            logger.debug(String.format("保存指挥下的集结点时名称重复 :%s.", rallyPointSaveInputInfo.getRallyPointName() )  );
                        }
                        throw new AcceptException(AcceptException.AccetpErrors.RALLYPOINT_ERROR);
                    }

                }else {
                    //修改
                    logService.infoLog(logger, "service", "getById", "service is started...");
                    Long start = System.currentTimeMillis();
                    //验证该集结点是否存在
                    RallyPointEntity checkRallyPoint = accessor.getById(rallyPointSaveInputInfo.getId(), RallyPointEntity.class);

                    Long end = System.currentTimeMillis();
                    logService.infoLog(logger, "repository", "getById", String.format("repository is finished,execute time is :%sms", end - start));

                    if (checkRallyPoint != null && checkRallyPoint.isValid()) {
                        logService.infoLog(logger, "service", "findRallyPoint", "service is started...");
                        Long start1 = System.currentTimeMillis();
                        //查询该指挥下是否存在集结点ID不同的同名集结点SAVED
                        String pointEntityId = rallyPointRepository.findRallyPoint( rallyPointSaveInputInfo.getId(),
                                commandId , rallyPointSaveInputInfo.getRallyPointName() );
                        Long end1 = System.currentTimeMillis();
                        logService.infoLog(logger, "repository", "findRallyPoint", String.format("repository is finished,execute time is :%sms", end1 - start1));

                        //如果没有同名的集结点
                        if (StringUtils.isBlank(pointEntityId)) {
                            if ( rallyPointSaveInputInfo.getRallyPointStatus().equals( SAVED.getCode()) && checkRallyPoint.getRallyPointStatus().equals( SENT.getCode())) {
                                if (logger.isDebugEnabled()) {
                                    logger.debug(String.format("该指挥下的集结点时状态为已下达,不能更新为已保存 :%s.",
                                            checkRallyPoint.getRallyPointStatus()));
                                }
                                throw new AcceptException(AcceptException.AccetpErrors.RALLYPOINTSTATUS_IS_SENT);
                            } else {
                                //更新集结点
                                RallyPointBean rallyPointBean = saveOrUpdateRallyPoint( rallyPointSaveInputInfo , incidentId , commandId ) ;
                                rallyPointBeans.add( rallyPointBean ) ;

                                //保存文书 信息
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                String content =  String.format(" %s %s更新名为:%s%s", rallyPointSaveInputInfo.getRallyPointContacts(),
                                        simpleDateFormat.format( System.currentTimeMillis() ) ,  rallyPointBean.getRallyPointName(),
                                        EnumUtils.getEnumTitle(rallyPointBean.getRallyPointType(), RallyPointTypeBakEnum.class))  ;
                                logService.infoLog(logger, "service", "saveDocument", "service is started...");
                                Long start2 = System.currentTimeMillis();

//                                this.saveDocument( incidentId , "更新部署" ,content , queryBean.getPersonName() , queryBean.getOrganizationId() , queryBean.getOrganizationName()  ) ;

                                Long end2 = System.currentTimeMillis();
                                logService.infoLog(logger, "service", "saveDocument", String.format("service is finished,execute time is :%sms", end2 - start2));

                                if (logger.isDebugEnabled()) {
                                    logger.debug("已更新");
                                }
                            }
                        } else {
                            if (logger.isDebugEnabled()) {
                                logger.debug(String.format("更新该指挥下的集结点时名称重复 :%s.", rallyPointSaveInputInfo.getRallyPointName()));
                            }
                            throw new AcceptException(AcceptException.AccetpErrors.RALLYPOINT_ERROR);
                        }
                    } else {
                        if (logger.isDebugEnabled()) {
                            logger.debug(String.format("The aggregation point of ID for [%s] does not exist .", rallyPointSaveInputInfo.getId()));
                        }
                        throw new AcceptException(AcceptException.AccetpErrors.UNABLE_TO_MODIFY_DATA_THAT_DOES_NOT_EXIST);
                    }
                }
            }
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveOrUpdateRallyPoint", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            //新增 保存指令 TOTO
            InstructionSaveInputInfo instructionSaveInputInfo = new InstructionSaveInputInfo();
            instructionSaveInputInfo.setCommandId(queryBean.getCommandId());
            instructionSaveInputInfo.setIncidentId(queryBean.getIncidentId());
            instructionSaveInputInfo.setInstructionsType("INSTRUCTION");//指令类型
            instructionSaveInputInfo.setInstructionsOrganizationId(queryBean.getOrganizationId());
            instructionSaveInputInfo.setInstructionsOrganizationName(queryBean.getOrganizationName());
            instructionSaveInputInfo.setInstructionsPersonId(queryBean.getPersonId());
            instructionSaveInputInfo.setInstructionsPersonName(queryBean.getPersonName());
            instructionSaveInputInfo.setInstructionsSource("2");//指令来源  1 接处警 2 实战指挥  3 移动 可拓展
            instructionSaveInputInfo.setInstructionsMode("TEXT");// 指令下达方式
            /**
             *     TEXT("TEXT","文本"),
             *     SMS("SMS","短信"),
             *     VOICE("VOICE","语音"),
             *     MICRO_GROUP("MICRO_GROUP","微群"),
             */
            //遍历保存集结点目标里面的集结点
            for (RallyPointSaveInputInfo rallyPoint:queryBean.getRallyPoints()
            ) {
                List<InstructionRecordSaveInputInfo> instructionRecordSaveInputInfoList = new ArrayList<>();//指令记录列表
                for (RallyItemSaveInputInfo rallyItem:rallyPoint.getRallyItems()//集结项
                ) {
                    //指令记录
                    InstructionRecordSaveInputInfo instructionRecordSaveInputInfo= new InstructionRecordSaveInputInfo();
                    instructionRecordSaveInputInfo.setReceivingObjectId(rallyItem.getRallyPowerId());//  受令对象id/集结项id
                    // 集结项类型   VEHICLE( 车辆 ) PERSON( 人员 ) EQUIPMENT( 装备 ) ORGANIZATION( 机构 ) ;
                    switch (rallyItem.getRallyPowerType()){
                        /**
                         *      指令类型
                         *     VEHICLE("VEHICLE","车辆"),
                         *     PERSON("PERSON","人员"),
                         *     SAFEGUARDUNIT("SAFEGUARDUNIT","联勤单位"),
                         *     EMERGENCYUNIT("EMERGENCYUNIT","联动单位"),
                         *     TERMINAL_EQUIPMENT("TERMINAL_EQUIPMENT","终端设备"),
                         */
                        case "VEHICLE":instructionRecordSaveInputInfo.setReceivingObjectType("VEHICLE");//受令对象类型
                            break;
                        case "PERSON":instructionRecordSaveInputInfo.setReceivingObjectType("PERSON");
                            break;
                        case "SAFEGUARDUNIT":instructionRecordSaveInputInfo.setReceivingObjectType("ORGANIZATION");
                            break;
                        case "EMERGENCYUNIT":instructionRecordSaveInputInfo.setReceivingObjectType("ORGANIZATION");
                            break;
                        case "TERMINAL_EQUIPMENT":instructionRecordSaveInputInfo.setReceivingObjectType("EQUIPMENT");
                            break;
                        default:
                            break;
                    }
                    instructionRecordSaveInputInfo.setReceivingObjectName(rallyItem.getRallyPowerName());//受令对象名称
                    instructionRecordSaveInputInfoList.add(instructionRecordSaveInputInfo);
                }
                instructionSaveInputInfo.setInstructionRecordList(instructionRecordSaveInputInfoList);
                //保存指令记录
                instructionService.saveInstruction(instructionSaveInputInfo);
            }


            return  rallyPointBeans ;
        }  catch (Exception ex) {
            logService.erorLog(logger, "service", "saveOrUpdateRallyPoint", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.COMMAND_FAILE);
        }
    }

    /**
     * 集结点保存方法
     */
    private RallyPointBean  saveOrUpdateRallyPoint(RallyPointSaveInputInfo rallyPointSaveInputInfo, String incidentId , String commandId ) {
        logService.infoLog(logger, "service", "saveOrUpdateRallyPoint", "service is started...");
        Long logStart = System.currentTimeMillis();

        //保存集结点
        RallyPointEntity rallyPointEntity = new RallyPointEntity() ;
        FireTransformUtil.transform( rallyPointSaveInputInfo ,  rallyPointEntity , incidentId , commandId  ) ;
        logService.infoLog(logger, "repository", "save(rallyPointEntity)", "repository is started...");
        Long start = System.currentTimeMillis();
        String rallyPointEntityId = accessor.save( rallyPointEntity ).getId()  ;
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "repository", "save(rallyPointEntity)", String.format("repository is finished,execute time is :%sms", end - start));

        //保存集结项
        //删除集结点下集结项
        List<RallyItemEntity> itemsEntities = rallyPointRepository.findRallyItem(  rallyPointEntityId );
        if( itemsEntities != null && itemsEntities.size() > 0 ){
            for ( RallyItemEntity entity : itemsEntities) {
                if (entity != null && entity.isValid()) {
                    logService.infoLog(logger, "repository", "remove(RallyItemEntity)", "repository is started...");
                    Long start1 = System.currentTimeMillis();
                    accessor.remove(entity.getId(), RallyItemEntity.class);
                    Long end1 = System.currentTimeMillis();
                    logService.infoLog(logger, "repository", "remove(RallyItemEntity)", String.format("repository is finished,execute time is :%sms", end1 - start1));
                }
            }
        }
        List<RallyItemSaveInputInfo> rallyItems = rallyPointSaveInputInfo.getRallyItems() ;
        List<RallyItemEntity> rallyItemEntityList = null ;
        if(rallyItems != null && rallyItems.size() > 0  ){
            rallyItemEntityList = new ArrayList<>() ;
            for( RallyItemSaveInputInfo  rallyItemSaveInputInfo : rallyItems ){
                RallyItemEntity rallyItemEntity = new RallyItemEntity();
                FireTransformUtil.transform(rallyItemSaveInputInfo , rallyItemEntity , incidentId , commandId , rallyPointEntityId );
                rallyItemEntityList.add( rallyItemEntity ) ;
            }
            logService.infoLog(logger, "repository", "save(rallyItemEntityList)", "repository is started...");
            Long start2 = System.currentTimeMillis();
            accessor.save( rallyItemEntityList ) ;
            Long end2 = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(rallyItemEntityList)", String.format("repository is finished,execute time is :%sms", end2 - start2));

        }
        //返回结果
        RallyPointBean rallyPointBean = new RallyPointBean() ;
        FireTransformUtil.transform(  rallyPointEntity , rallyPointBean );
        if( rallyItemEntityList != null && rallyItemEntityList.size() > 0 ){
            List<RallyItemBean> rallyItemBeans = new ArrayList<>() ;
            for( RallyItemEntity rallyItemEntity : rallyItemEntityList ){
                RallyItemBean rallyItemBean = new RallyItemBean() ;
                FireTransformUtil.transform( rallyItemEntity , rallyItemBean );
                rallyItemBeans.add( rallyItemBean ) ;
            }
            rallyPointBean.setRallyItemBeans( rallyItemBeans );
        }

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "saveOrUpdateRallyPoint", String.format("service is finished,execute time is :%sms", logEnd - logStart));

        return  rallyPointBean ;

    }

//
//    /**
//     * 保存火场文书信息
//     * @param incidentId
//     * @param type
//     * @param content
//     * @param writer
//     * @param writerOrganizationId
//     * @param writerOrganizationName
//     */
//    private void  saveDocument(String incidentId , String type , String content , String writer ,
//                               String writerOrganizationId , String writerOrganizationName ){
//        try {
//            logService.infoLog(logger, "service", "saveDocument", "service is started...");
//            Long logStart = System.currentTimeMillis();
//            DocumentSaveInputInfo queryBea = new DocumentSaveInputInfo();
//            queryBea.setIncidentId( incidentId );
//            queryBea.setType( type );
//            queryBea.setContent(  content  );
//            queryBea.setFeedbackPerson( writer );
//            queryBea.setFeedbackOrganizationId( writerOrganizationId );
//            documentService.saveDocument(queryBea) ;
//            Long logEnd = System.currentTimeMillis();
//            logService.infoLog(logger, "service", "saveDocument", String.format("service is finished,execute time is :%sms", logEnd - logStart));
//        }catch ( Exception ex) {
//            if (logger.isErrorEnabled()) {
//                logger.error("保存文书失败", ex);
//            }
//        }
//
//    }


    /**
     * {@inheritDoc}
     *
     * @see RallyPointService#removeRallyPoint(RallyPointDeleteTargetInputInfo)
     */
    @Transactional( rollbackFor =  Exception.class )
    @Override
    public Boolean removeRallyPoint(RallyPointDeleteTargetInputInfo queryBean )  {
        try {
            logService.infoLog(logger, "service", "removeRallyPoint", "service is started...");
            Long logStart = System.currentTimeMillis();

            String pointId = queryBean.getRallyPointId() ;
            logService.infoLog(logger, "repository", "save(removeRallyPoint)", "repository is started...");
            Long start = System.currentTimeMillis();

            RallyPointEntity rallyPointEntity = accessor.getById(pointId, RallyPointEntity.class);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(removeRallyPoint)", String.format("repository is finished,execute time is :%sms", end - start));

            if (rallyPointEntity != null   ) {
                if ( rallyPointEntity.isValid() ) {
                    logService.infoLog(logger, "repository", "save(remove)", "repository is started...");
                    Long start1 = System.currentTimeMillis();
                    //删除集结点
                    RallyPointEntity deletePoint = accessor.remove(pointId, RallyPointEntity.class);

                    Long end1 = System.currentTimeMillis();
                    logService.infoLog(logger, "repository", "save(remove)", String.format("repository is finished,execute time is :%sms", end1 - start1));

                    logService.infoLog(logger, "repository", "findRallyItem", "repository is started...");
                    Long start2 = System.currentTimeMillis();

                    //删除集结项
                    List<RallyItemEntity> itemsEntities = rallyPointRepository.findRallyItem(pointId );

                    Long end2 = System.currentTimeMillis();
                    logService.infoLog(logger, "repository", "findRallyItem", String.format("repository is finished,execute time is :%sms", end2 - start2));

                    if( itemsEntities != null && itemsEntities.size() > 0 ){
                        for ( RallyItemEntity entity : itemsEntities) {
                            if (entity != null && entity.isValid()) {
                                entity.setValid( false );
                            }
                        }

                        logService.infoLog(logger, "repository", "save(remove)", "repository is started...");
                        Long start3 = System.currentTimeMillis();

                        accessor.save( itemsEntities ) ;

                        Long end3 = System.currentTimeMillis();
                        logService.infoLog(logger, "repository", "remove", String.format("repository is finished,execute time is :%sms", end3 - start3));

                    }
//                    //保存文书 信息
//                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                    String content =  String.format(" %s %s取消名为:%s%s", deletePoint.getRallyPointContacts(),
//                            simpleDateFormat.format( System.currentTimeMillis() ) ,  deletePoint.getRallyPointName(),
//                            EnumUtils.getEnumTitle(deletePoint.getRallyPointType(), RallyPointTypeEnum.class))  ;
//                    this.saveDocument( deletePoint.getIncidentId() , "取消部署" ,content , queryBean.getPersonName() , queryBean.getOrganizationId() , queryBean.getOrganizationName()  ) ;

                    return true ;
                } else {
                    return true ;
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "removeRallyPoint", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return true ;
        } catch (UserInterfaceException ex) {
            throw ex;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "removeRallyPoint", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.COMMAND_FAILE);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see RallyPointService#findRallyPoint(String)
     */
    @Transactional( readOnly =  true  )
    @Override
    public List<RallyPointBean> findRallyPoint(String incidentId) {
        try {
            logService.infoLog(logger, "service", "findRallyPoint", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<RallyPointBean> beans = new ArrayList<>( ) ;

            logService.infoLog(logger, "repository", "findRallyPoint", "repository is started...");
            Long start = System.currentTimeMillis();

            List<RallyPointEntity> rallyPointEntityList = rallyPointRepository.findRallyPoint( incidentId ) ;
            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findRallyPoint", String.format("repository is finished,execute time is :%sms", end - start));

            if( rallyPointEntityList != null && rallyPointEntityList.size() > 0 ){
                for( RallyPointEntity  rallyPointEntity  : rallyPointEntityList ){
                    RallyPointBean rallyPointBean = new RallyPointBean() ;
                    FireTransformUtil.transform( rallyPointEntity , rallyPointBean );
                    logService.infoLog(logger, "repository", "findRallyItem", "repository is started...");
                    Long start1 = System.currentTimeMillis();
                    //设置集结点下的集结项
                    List<RallyItemEntity> rallyItemEntityList = rallyPointRepository.findRallyItem( rallyPointEntity.getId() ) ;
                    Long end1 = System.currentTimeMillis();
                    logService.infoLog(logger, "repository", "findRallyItem", String.format("repository is finished,execute time is :%sms", end1 - start1));

                    if( rallyItemEntityList != null && rallyItemEntityList.size() > 0 ){
                        List<RallyItemBean> rallyItemBeans = new ArrayList<>() ;
                        for( RallyItemEntity rallyItemEntity : rallyItemEntityList ){
                            RallyItemBean rallyItemBean = new RallyItemBean() ;
                            FireTransformUtil.transform( rallyItemEntity , rallyItemBean );
                            rallyItemBeans.add( rallyItemBean ) ;
                        }
                        rallyPointBean.setRallyItemBeans( rallyItemBeans );
                    }
                    beans.add( rallyPointBean) ;
                }
            }
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findRallyPoint", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return  beans ;
        } catch (UserInterfaceException ex) {
            throw ex;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findPlanWord", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.COMMAND_FAILE);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see RallyPointService#findRallyPoint(String)
     */
    @Transactional( readOnly =  true  )
    @Override
    public  List<RallyItemBean> findRallyItem  (String incidentId , String rallyPowerId ) {
        try {
            logService.infoLog(logger, "service", "findRallyItem", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<RallyItemBean> beans = new ArrayList<>( ) ;

            logService.infoLog(logger, "repository", "findRallyItem", "repository is started...");
            Long start = System.currentTimeMillis();

            List<RallyItemEntity> rallyItemEntityList = rallyPointRepository.findRallyItem( incidentId , rallyPowerId  ) ;
            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findRallyItem", String.format("repository is finished,execute time is :%sms", end - start));

            if( rallyItemEntityList != null && rallyItemEntityList.size() > 0 ){
                for( RallyItemEntity  rallyItemEntity  : rallyItemEntityList ){

                    RallyItemBean rallyItemBean = new RallyItemBean() ;
                    FireTransformUtil.transform( rallyItemEntity , rallyItemBean );


                    RallyPointEntity rallyPointEntity = accessor.getById( rallyItemBean.getRallyPointId() , RallyPointEntity.class ) ;

                    RallyPointBean rallyPointBean = new RallyPointBean() ;
                    FireTransformUtil.transform( rallyPointEntity , rallyPointBean );

                    rallyItemBean.setRallyPointBean( rallyPointBean );
                    beans.add( rallyItemBean) ;
                }
            }
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findRallyItem", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return  beans ;
        } catch (UserInterfaceException ex) {
            throw ex;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findRallyItem", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.COMMAND_FAILE);
        }
    }



    /**
     * {@inheritDoc}
     *
     * @see RallyPointService#findRallyPointDetail(String)
     */
    @Transactional( readOnly =  true  )
    @Override
    public   RallyPointBean  findRallyPointDetail( String rallyPointId ) {
        try {
            logService.infoLog(logger, "service", "findRallyPointDetail", "service is started...");
            Long logStart = System.currentTimeMillis();

            RallyPointBean rallyPointBean = null ;
            logService.infoLog(logger, "repository", "getById", "repository is started...");
            Long start = System.currentTimeMillis();
            RallyPointEntity  rallyPointEntity = accessor.getById( rallyPointId , RallyPointEntity.class ) ;
            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "getById", String.format("repository is finished,execute time is :%sms", end - start));

            if( rallyPointEntity != null  &&rallyPointEntity.getValid()==1){
                rallyPointBean = new RallyPointBean() ;
                FireTransformUtil.transform( rallyPointEntity , rallyPointBean );
                logService.infoLog(logger, "repository", "findRallyItem", "repository is started...");
                Long start1 = System.currentTimeMillis();
                //设置集结点下的集结项
                List<RallyItemEntity> rallyItemEntityList = rallyPointRepository.findRallyItem( rallyPointEntity.getId() ) ;
                Long end1 = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "findRallyItem", String.format("repository is finished,execute time is :%sms", end1 - start1));

                if( rallyItemEntityList != null && rallyItemEntityList.size() > 0 ){
                    List<RallyItemBean> rallyItemBeans = new ArrayList<>() ;
                    for( RallyItemEntity rallyItemEntity : rallyItemEntityList ){
                        RallyItemBean rallyItemBean = new RallyItemBean() ;
                        FireTransformUtil.transform( rallyItemEntity , rallyItemBean );
                        rallyItemBeans.add( rallyItemBean ) ;
                    }
                    rallyPointBean.setRallyItemBeans( rallyItemBeans );
                }
            }
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findRallyPointDetail", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return  rallyPointBean ;
        } catch (UserInterfaceException ex) {
            throw ex;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findRallyPointDetail", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.COMMAND_FAILE);
        }
    }



    /**
     * {@inheritDoc}
     *
     * @see RallyPointService#saveRallyItemFeedback(RallyItemFeedbackSaveInputInfo)
     */
    @Transactional( rollbackFor =  Exception.class )
    @Override
    public Boolean saveRallyItemFeedback  (RallyItemFeedbackSaveInputInfo queryBean )  {
        try {
            logService.infoLog(logger, "service", "saveRallyItemFeedback", "service is started...");
            Long logStart = System.currentTimeMillis();
            RallyItemFeedbackEntity entity = new  RallyItemFeedbackEntity() ;
            FireTransformUtil.transform( queryBean , entity );
            if( entity != null  ){
                logService.infoLog(logger, "repository", "save", "repository is started...");
                Long start1 = System.currentTimeMillis();
                accessor.save( entity ) ;
                Long end1 = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save", String.format("repository is finished,execute time is :%sms", end1 - start1));

                //保存文书 信息
//                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                String content =  String.format("%s %s %s反馈为:%s ", queryBean.getFeedbackOrganizationName(), queryBean.getWriterName() ,
//                        simpleDateFormat.format( System.currentTimeMillis() ) ,  queryBean.getFeedbackContent() )  ;
//                this.saveDocument( queryBean.getIncidentId() , "集结反馈" ,content , queryBean.getWriterName() ,
//                        queryBean.getFeedbackOrganizationId() , queryBean.getFeedbackOrganizationName()  ) ;

                return  true ;
            }
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveRallyItemFeedback", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return  false ;
        } catch (UserInterfaceException ex) {
            throw ex;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveRallyItemFeedback", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.COMMAND_FAILE);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see RallyPointService#findRallyPointDetail(String)
     */
    @Transactional( readOnly =  true  )
    @Override
    public List<RallyItemFeedbackBean> findRallyItemFeedbackCondition  (RallyItemFeedbackQueryInputInfo queryBean ) {
        try {
            logService.infoLog(logger, "service", "findRallyItemFeedbackCondition", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<RallyItemFeedbackBean> beans  = new ArrayList<>( ) ;
            logService.infoLog(logger, "repository", "findRallyItemFeedbackCondition", "repository is started...");
            Long start1 = System.currentTimeMillis();
            List<RallyItemFeedbackEntity> rallyItemFeedbackEntityList = rallyPointRepository.findRallyItemFeedbackCondition( queryBean.getIncidentId() , queryBean.getCommandId() , queryBean.getRallyPointId() ,
                    queryBean.getRallyItemId() , queryBean.getFeedbackObjectId() , queryBean.getKeyword() ) ;
            Long end1 = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findRallyItemFeedbackCondition", String.format("repository is finished,execute time is :%sms", end1 - start1));

            if( rallyItemFeedbackEntityList != null && rallyItemFeedbackEntityList.size()> 0  ){
                for(RallyItemFeedbackEntity rallyItemFeedbackEntity : rallyItemFeedbackEntityList){
                    RallyItemFeedbackBean rallyItemFeedbackBean = new RallyItemFeedbackBean() ;
                    FireTransformUtil.transform( rallyItemFeedbackEntity ,  rallyItemFeedbackBean );
                    beans.add( rallyItemFeedbackBean ) ;
                }
            }
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findRallyItemFeedbackCondition", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return  beans ;
        }  catch (Exception ex) {
            logService.erorLog(logger, "service", "findRallyItemFeedbackCondition", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.COMMAND_FAILE);
        }
    }


}
