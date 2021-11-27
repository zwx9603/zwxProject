package com.dscomm.iecs.accept.utils.transform;

import com.dscomm.iecs.accept.dal.po.*;
import com.dscomm.iecs.accept.graphql.fireinputbean.*;
import com.dscomm.iecs.accept.graphql.firetypebean.*;
import com.dscomm.iecs.accept.graphql.inputbean.*;
import com.dscomm.iecs.accept.graphql.typebean.*;
import com.dscomm.iecs.accept.service.bean.LedBean;
import com.dscomm.iecs.base.utils.PinYinUtils;
import com.dscomm.iecs.basedata.dal.po.ExpertEntity;
import com.dscomm.iecs.basedata.graphql.typebean.EquipmentVehicleBean;
import com.dscomm.iecs.basedata.graphql.typebean.ExpertBean;
import org.apache.logging.log4j.util.Strings;
import org.mx.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FireTransformUtil {



    /**
     * 涉消舆情 转换
     * @param source 涉消舆情PO
     * @return 涉消舆情BO
     */
    public static ConsensusInformationBean transform(ConsensusInformationEntiy source) {
        if (null != source) {
            ConsensusInformationBean target = new ConsensusInformationBean();
            target.setContent(source.getContent());
            target.setId(source.getId());
            target.setPublishedTime(source.getPublishedTime());
            target.setPublishedUnit(source.getPublishedUnit());
            target.setPublisher(source.getPublisher());
            target.setRemarks(source.getRemarks());
            target.setTitle(source.getTitle());
            return target;
        }
        return null;
    }


    /**
     * 战评 转换
     * @param source 战评INPUT
     * @return 战评PO
     */
    public static EarlyWarningImportantEntity transform(EarlyWarningImportantSaveInputInfo source) {
        if (null != source) {
            EarlyWarningImportantEntity target = new EarlyWarningImportantEntity();
            target.setContent(source.getContent());
            target.setEffectiveTime(source.getEffectiveTime());
            target.setPublisher(source.getPublisher());
            target.setPublisherId(source.getPublisherId());
            target.setPublishTime(source.getPublishTime());
            target.setPublishUnit(source.getPublishUnit());
            target.setPublishUnitId(source.getPublishUnitId());
            target.setRemarks(source.getRemarks());
            target.setTitle(source.getTitle());
            target.setType(source.getType());
            target.setId(source.getId());
            return target;
        }
        return null;
    }


    /**
     *  重大灾害预警信息 转换
     * @param source 重大灾害预警信息PO
     * @return 重大灾害预警信息BO
     */
    public static EarlyWarningImportantBean transform(EarlyWarningImportantEntity source ) {

        if(source!=null){
            EarlyWarningImportantBean target=new EarlyWarningImportantBean();
            target.setId(source.getId());
            target.setType(source.getType());
            target.setContent(source.getContent());
            target.setEffectiveTime(source.getEffectiveTime());
            target.setPublisher(source.getPublisher());
            target.setPublisherId(source.getPublisherId());
            target.setPublishTime(source.getPublishTime());
            target.setPublishUnit(source.getPublishUnit());
            target.setPublishUnitId(source.getPublishUnitId());
            target.setRemarks(source.getRemarks());
            target.setTitle(source.getTitle());
            return target;
        }
        return null;
    }




    /**
     * 战评 转换
     * @param source 战评INPUT
     * @return 战评PO
     */
    public static ActionSummaryEntity transform(ActionSummarySaveInputInfo source) {
        if (null != source) {
            ActionSummaryEntity target = new ActionSummaryEntity();
            target.setCommandId(source.getCommandId());
            target.setContent(source.getContent());
            target.setEvaluationTime(source.getEvaluationTime());
            target.setEvaluatorOrganizatioName(source.getEvaluatorOrganizatioName());
            target.setEvaluatorOrganizationId(source.getEvaluatorOrganizationId());
            target.setEvaluatorPersonId(source.getEvaluatorPersonId());
            target.setEvaluatorPersonIName(source.getEvaluatorPersonIName());
            target.setExperience(source.getExperience());
            target.setIncidentId(source.getIncidentId());
            target.setLabel(source.getLabel());
            target.setLesson(source.getLesson());
            target.setProcedure(source.getProcedure());
            target.setProcedureTime(source.getProcedureTime());
            target.setRemarks(source.getRemarks());
            target.setScore(source.getScore());
            target.setCreatedTime(source.getEvaluationTime());
            target.setId(source.getId());
            return target;
        }
        return null;
    }


    /**
     *  战评 转换
     * @param source 战评信息PO
     * @return 战评信息BO
     */
    public static ActionSummaryBean transform(ActionSummaryEntity source ) {

        if(source!=null){
            ActionSummaryBean target=new ActionSummaryBean();
            target.setCommandId(source.getCommandId());
            target.setContent(source.getContent());
            target.setEvaluationTime(source.getEvaluationTime());
            target.setEvaluatorOrganizatioName(source.getEvaluatorOrganizatioName());
            target.setEvaluatorOrganizationId(source.getEvaluatorOrganizationId());
            target.setEvaluatorPersonId(source.getEvaluatorPersonId());
            target.setEvaluatorPersonIName(source.getEvaluatorPersonIName());
            target.setExperience(source.getExperience());
            target.setId(source.getId());
            target.setIncidentId(source.getIncidentId());
            target.setLabel(source.getLabel());
            target.setLesson(source.getLesson());
            target.setProcedure(source.getProcedure());
            target.setProcedureTime(source.getProcedureTime());
            target.setRemarks(source.getRemarks());
            target.setScore(source.getScore());
            return target;
        }
        return null;
    }







    /**
     *  现场指挥员 转换
     * @param source 现场指挥员List[PO]
     * @return 现场指挥员List[BO]
     */
    private static List<CommanderBean> transform( List<CommanderEntity> source ){
        List<CommanderBean> commanderBeansList = new ArrayList<>();
        for (CommanderEntity cde : source){
            CommanderBean commanderBean=new CommanderBean();
            commanderBean.setCommanderDuty(cde.getCommanderId());
            commanderBean.setCommanderId(cde.getCommandId());
            commanderBean.setCommanderName(cde.getCommanderName());
            commanderBean.setCommanderType(cde.getCommanderType());
            commanderBean.setCommandId(cde.getCommandId());
            commanderBean.setId(cde.getId());
            commanderBean.setIncidentId(cde.getIncidentId());
            commanderBean.setMobilePhone(cde.getMobilePhone());
            commanderBean.setOrganizationId(cde.getOrganizationId());
            commanderBean.setOrganizationName(cde.getOrganizationName());
            commanderBean.setTelephone(cde.getTelephone());
            commanderBeansList.add(commanderBean);
        }
        return commanderBeansList;
    }


    /**
     *  现场指挥员 转换
     * @param source 现场指挥员PO
     * @return 现场指挥员BO
     */
    public static CommanderBean transform(CommanderEntity source , EquipmentVehicleBean vehicleBean ) {

        if(source!=null){
            CommanderBean target=new CommanderBean();
            target.setId( source.getId() );
            target.setTelephone(source.getTelephone());
            target.setOrganizationName(source.getOrganizationName());
            target.setOrganizationId(source.getOrganizationId());
            target.setMobilePhone(source.getMobilePhone());
            target.setIncidentId(source.getIncidentId());
            if( vehicleBean != null ){
                target.setVehicleId( vehicleBean.getId()  );
                target.setVehicleName( vehicleBean.getVehicleName() );
                target.setVehicleNumber( vehicleBean.getVehicleNumber() );
                target.setPersonNum( source.getPersonNum() );

            }
            target.setCommandId(source.getCommandId());
            target.setCommanderType(source.getCommanderType());
            target.setCommanderName(source.getCommanderName());
            target.setCommanderId(source.getCommanderId());
            target.setCommanderDuty(source.getCommanderDuty());
            target.setCommanderDutyName( source.getCommanderDutyName() );

            target.setDriver( source.getDriver() );
            target.setCorrespondent( source.getCorrespondent() );

            target.setArriveTime( source.getArriveTime() );
            target.setBackTime( source.getBackTime() );
            return target;
        }
        return null;
    }




    /**
     * 现场指挥员 转换
     * @param source 现场指挥员INPUT
     * @return 现场指挥员PO
     */
    public static CommanderEntity transform(CommanderSaveInputInfo source) {
        if (null != source) {
            CommanderEntity target = new CommanderEntity();
            target.setId( source.getId() );
            target.setTelephone(source.getTelephone());
            target.setOrganizationName(source.getOrganizationName());
            target.setOrganizationId(source.getOrganizationId());
            target.setMobilePhone(source.getMobilePhone());
            target.setIncidentId(source.getIncidentId());
            target.setCommandId(source.getCommandId());
            target.setCommanderType(source.getCommanderType());
            target.setVehicleId( source.getVehicleId() );
            target.setPersonNum( source.getPersonNum() );
            target.setCommanderName(source.getCommanderName());
            target.setCommanderId(source.getCommanderId());
            target.setCommanderDuty(source.getCommanderDuty());
            target.setCommanderDutyName( source.getCommanderDutyName() );

            target.setDriver( source.getDriver() );
            target.setCorrespondent( source.getCorrespondent() );

            target.setArriveTime( source.getArriveTime() );
            target.setBackTime( source.getBackTime() );
            return target;
        }
        return null;
    }




        /**
         *  指挥设定 转换
         * @param source 现场指挥员PO
         * @return 现场指挥员BO
         */
    public static CommandBean transform(CommandEntity source ) {

        if(source!=null){
            CommandBean target=new CommandBean();
            target.setId( source.getId() );
            target.setTypicalIncident(source.getTypicalIncident());
            target.setIncidentId(source.getIncidentId());
            target.setStartTime(source.getStartTime());
            target.setStartOrganizationName(source.getStartOrganizationName());
            target.setStartOrganizationId(source.getStartOrganizationId());
            target.setInitiatorName(source.getInitiatorName());
            target.setInitiatorId(source.getInitiatorId());
            target.setEndTime(source.getEndTime());
            return target;
        }
        return null;
    }

    /**
     * 指挥设定 转换
     * @param source 指挥设定INPUT
     * @return 指挥设定PO
     */
    public static CommandEntity transform(CommandSaveInputInfo source) {
        if(null!=source){
            CommandEntity target=new CommandEntity();
            target.setTypicalIncident(source.getTypicalIncident());
            target.setIncidentId(source.getIncidentId());
            target.setStartTime(source.getStartTime());
            target.setStartOrganizationName(source.getStartOrganizationName());
            target.setStartOrganizationId(source.getStartOrganizationId());
            target.setInitiatorName(source.getInitiatorName());
            target.setInitiatorId(source.getInitiatorId());
            target.setEndTime(source.getEndTime());
            return target;
        }
        return null;
    }




    /**
     *  救援圈设定 转换
     * @param source 救援圈设定PO
     * @return 救援圈设定BO
     */
    public static IncidentCircleBean transform(IncidentCircleEntity source ) {

        if(source!=null){
            IncidentCircleBean incidentCircleBean=new IncidentCircleBean();
            incidentCircleBean.setId( source.getId() );
            incidentCircleBean.setIncidentId(source.getIncidentId());
            incidentCircleBean.setRadius(source.getRadius());
            return incidentCircleBean;
        }
        return null;
    }





    /**
     * 救援圈设定 转换
     * @param source 救援圈设定INPUT
     * @return 救援圈设定PO
     */
    public static IncidentCircleEntity transform(IncidentCircleSaveInputInfo source) {
        if(null!=source){
            IncidentCircleEntity target=new IncidentCircleEntity();
            target.setRadius(source.getRadius());
            target.setIncidentId(source.getIncidentId());
            return target;
        }
        return null;
    }




    /**
     * 文本预案 转换
     * @param source 文本预案INPUT
     * @return 文本预案PO
     */
     public static PlanWordEntity transform(PlanWordSaveInputInfo source) {
        if(null!=source){
            PlanWordEntity target=new PlanWordEntity();
            target.setId( source.getId() );
            target.setIncidentTypeCode(source.getIncidentTypeCode());
            target.setWhetherKeyUnit(source.getWhetherKeyUnit());
            target.setKeyUnitId(source.getKeyUnitId());
            target.setOrganizationCode(source.getOrganizationCode());
            target.setPlanName(source.getPlanName());
            target.setPlanTypeCode(source.getPlanTypeCode());
            target.setRemarks(source.getRemarks());
            return target;
        }
        return null;
    }


    /**
     *  文本预案 转换
     * @param source 文本预案PO
     * @return 文本预案BO
     */
    public static PlanWordBean transform(PlanWordEntity source ) {

        if(source!=null){
            PlanWordBean planWordBean=new PlanWordBean();
            planWordBean.setId( source.getId() );
            planWordBean.setPlanTypeCode( source.getPlanTypeCode() );
            planWordBean.setIncidentTypeCode(source.getIncidentTypeCode() );
            planWordBean.setPlanName( source.getPlanName() );
            planWordBean.setOrganizationCode(source.getOrganizationCode() );
            planWordBean.setWhetherKeyUnit(   source.getWhetherKeyUnit() );
            planWordBean.setKeyUnitId( source.getKeyUnitId() );
            planWordBean.setRemarks( source.getRemarks() );
            return planWordBean;
        }
        return null;
    }








    /**
     * 转换
     *
     *
     */
    public static CombatReadinessBean transform(CombatReadinessEntity source) {
        if (null != source) {
            CombatReadinessBean target = new CombatReadinessBean();
            target.setId(source.getId());
            target.setContent(source.getContent());
            target.setRemarks(source.getRemarks());
            target.setShowEndTime(source.getShowEndTime());
            target.setShowStartTime(source.getShowStartTime());
            target.setTitle(source.getTitle());
            target.setType(source.getType());
            target.setShowEndTime(source.getShowEndTime());
            target.setExpand1( source.getExpand1() );
            target.setExpand2(  source.getExpand2() );
            target.setExpand3( source.getExpand3() );
            return target;
        }
        return null;
    }
    /**
     * 转换
     *
     *
     */
    public static CombatReadinessEntity transform(CombatReadinessInputInfo source) {
        if(null!=source){
            CombatReadinessEntity target=new CombatReadinessEntity();
            target.setId(source.getId());
            target.setContent(source.getContent());
            target.setRemarks(source.getRemarks());
            target.setShowEndTime(source.getShowEndTime());
            target.setShowStartTime(source.getShowStartTime());
            target.setTitle(source.getTitle());
            target.setType(source.getType());
            target.setExpand1( source.getExpand1() );
            target.setExpand2(  source.getExpand2() );
            target.setExpand3( source.getExpand3() );

            return target;
        }
        return null;
    }

    /**
     * 集结项 queryBean 转换 entity
     *
     */
    public static  RallyItemFeedbackEntity transform  (RallyItemFeedbackSaveInputInfo queryBean ,
                                                        RallyItemFeedbackEntity entity      ) {
        if (queryBean!=null) {
            entity.setId(queryBean.getId());
            entity.setIncidentId(queryBean.getIncidentId());
            entity.setCommandId(queryBean.getCommandId());
            entity.setRallyPointId(queryBean.getRallyPointId());
            entity.setRallyItemId(queryBean.getRallyItemId());
            entity.setFeedbackObjectId(queryBean.getFeedbackObjectId());
            entity.setFeedbackObjectName(queryBean.getFeedbackObjectName());
            entity.setFeedbackOrganizationId(queryBean.getFeedbackOrganizationId());
            entity.setFeedbackObjectName(queryBean.getFeedbackObjectName());
            entity.setWriterName(queryBean.getWriterName());
            entity.setFeedbackContent(queryBean.getFeedbackContent());
            entity.setFeedbackTime(System.currentTimeMillis());
            entity.setCustomcredit1(queryBean.getCustomcredit1());
            entity.setCustomcredit2(queryBean.getCustomcredit2());
            entity.setRemarks(queryBean.getRemarks());
        }
        return entity;
    }

    /**
     * 集结项 entity 转换 bean
     *
     */
    public static  RallyItemFeedbackBean transform  (  RallyItemFeedbackEntity entity ,   RallyItemFeedbackBean bean     ) {
        if (entity!=null){
        bean.setId( entity.getId() );
        bean.setIncidentId( entity.getIncidentId() );
        bean.setCommandId( entity.getCommandId() );
        bean.setRallyPointId( entity.getRallyPointId() );
        bean.setRallyItemId( entity.getRallyItemId() );
        bean.setFeedbackObjectId( entity.getFeedbackObjectId() );
        bean.setFeedbackObjectName( entity.getFeedbackObjectName() );
        bean.setFeedbackOrganizationId( entity.getFeedbackOrganizationId() );
        bean.setFeedbackObjectName( entity.getFeedbackObjectName() );
        bean.setWriterName( entity.getWriterName() );
        bean.setFeedbackContent( entity.getFeedbackContent() );
        bean.setFeedbackTime( entity.getFeedbackTime() );
        bean.setCustomcredit1( entity.getCustomcredit1() );
        bean.setCustomcredit2( entity.getCustomcredit2() );
        bean.setRemarks( entity.getRemarks() );
    }
        return bean;
    }

    /**
     * 集结项 entity 转换 bean
     *
     */
    public static RallyItemBean  transform  (  RallyItemEntity entity , RallyItemBean bean      ) {
        bean.setId(  entity.getId() );
        bean.setIncidentId( entity.getIncidentId()  );
        bean.setCommandId( entity.getCommandId()  );
        bean.setRallyPointId( entity.getRallyPointId()  );
        bean.setRallyPowerType( entity.getRallyPowerType() );
        bean.setRallyPowerId( entity.getRallyPowerId() );
        bean.setRallyPowerName( entity.getRallyPowerName() );
        bean.setOrganizationId( entity.getOrganizationId() );
        bean.setOrganizationName( entity.getOrganizationName() );
        bean.setCustomcredit1( entity.getCustomcredit1() );
        bean.setCustomcredit2( entity.getCustomcredit2() );
        bean.setRemarks( entity.getRemarks() );
        return bean;
    }

    /**
     * 集结点 entity 转换 bean
     */
    public static  RallyPointBean transform (  RallyPointEntity entity  ,  RallyPointBean bean   ) {
        bean.setId( entity.getId() );
        bean.setIncidentId( entity.getIncidentId() );
        bean.setCommandId( entity.getCommandId() );
        bean.setRallyPointCode( entity.getRallyPointCode() );
        bean.setRallyPointType( entity.getRallyPointType() );
        bean.setRallyPointName( entity.getRallyPointName() );
        bean.setRallyPointDesc( entity.getRallyPointDesc() );
        bean.setRallyPointStatus( entity.getRallyPointStatus() );
        bean.setRallyPointTime( entity.getRallyPointTime() );
        bean.setRallyPointContacts( entity.getRallyPointContacts() );
        bean.setRallyPointPhone( entity.getRallyPointPhone() );
        bean.setLongitude( entity.getLongitude() );
        bean.setLatitude( entity.getLatitude() );
        bean.setCustomcredit1( entity.getCustomcredit1() );
        bean.setCustomcredit2( entity.getCustomcredit2() );
        bean.setRemarks( entity.getRemarks() );
        return bean;
    }

    /**
     * 集结项 saveInputInfo 转换 entity
     *
     */
    public static  RallyItemEntity  transform (RallyItemSaveInputInfo bean  , RallyItemEntity entity  , String incidentId , String commandId  , String rallyPointId     ) {
        entity.setId(  bean.getId() );
        entity.setIncidentId( incidentId );
        entity.setCommandId( commandId );
        entity.setRallyPointId( rallyPointId );
        entity.setRallyPowerType( bean.getRallyPowerType() );
        entity.setRallyPowerId( bean.getRallyPowerId() );
        entity.setRallyPowerName( bean.getRallyPowerName() );
        entity.setOrganizationId( bean.getOrganizationId() );
        entity.setOrganizationName( bean.getOrganizationName() );
        entity.setCustomcredit1( bean.getCustomcredit1() );
        entity.setCustomcredit2( bean.getCustomcredit2() );
        entity.setRemarks( bean.getRemarks() );
        return entity;
    }

    /**
     * 集结点 saveInputInfo 转换 entity
     *
     */
    public static RallyPointEntity  transform (RallyPointSaveInputInfo bean  , RallyPointEntity entity  , String incidentId , String commandId     ) {
        entity.setId(  bean.getId() );
        entity.setIncidentId( incidentId );
        entity.setCommandId( commandId );
        entity.setRallyPointCode( bean.getRallyPointCode() );
        entity.setRallyPointType( bean.getRallyPointType() );
        entity.setRallyPointName( bean.getRallyPointName() );
        entity.setRallyPointDesc( bean.getRallyPointDesc() );
        entity.setRallyPointStatus( bean.getRallyPointStatus() );
        entity.setRallyPointTime( bean.getRallyPointTime() );
        entity.setRallyPointContacts( bean.getRallyPointContacts() );
        entity.setRallyPointPhone( bean.getRallyPointPhone() );
        entity.setLongitude( bean.getLongitude() );
        entity.setLatitude( bean.getLatitude() );
        entity.setCustomcredit1( bean.getCustomcredit1() );
        entity.setCustomcredit2( bean.getCustomcredit2() );
        entity.setRemarks( bean.getRemarks() );
        return entity;
    }

    /**
     *  常用短语
     * @param source
     * @return
     */
    public static CommonPhraseEntity transform( CommonPhraseSaveInputInfo source ){
        if ( source !=null){
            CommonPhraseEntity target = new CommonPhraseEntity();
            target.setId( source.getId() );
            target.setPhraseType(source.getPhraseType());
            target.setIncidentType(source.getIncidentType());
            target.setPhraseContent(source.getPhraseContent());
            target.setPhraseTitle(source.getPhraseTitle());
            target.setOrganizationId(source.getOrganizationId());
            target.setRemarks(source.getRemarks());
            return  target ;
        }
        return null ;
    }

    /**
     * 常用短语
     * @return
     */
    public static  CommonPhraseBean transform(  CommonPhraseEntity source , Map<String, String> organizationNameMap ){
        if(organizationNameMap==null){
            organizationNameMap=new HashMap<>();
        }

        if ( source !=null){
            CommonPhraseBean target = new CommonPhraseBean();
            target.setId( source.getId() );
            target.setPhraseType(source.getPhraseType());
            target.setIncidentType(source.getIncidentType());
            target.setPhraseContent(source.getPhraseContent());
            target.setPhraseTitle(source.getPhraseTitle());
            target.setOrganizationId(source.getOrganizationId());
            if(!StringUtils.isBlank(target.getOrganizationId())){
                target.setOrganizationName( organizationNameMap.get( target.getOrganizationId() ));
            }
            target.setRemarks(source.getRemarks());
            return  target ;
        }
        return null ;
    }


    /**
     * 专家列表转换
     * @param expertEntities
     * @return
     */
    public static List<ExpertBean> transformList(List<ExpertEntity> expertEntities  , Map<String, Map<String , String> > dicsMap ,   Map<String, String> organizationNameMap ){
        List<ExpertBean> beanList = new ArrayList<>();
        if (expertEntities!=null&&expertEntities.size()>0){
            for (ExpertEntity entity :  expertEntities  ) {
                ExpertBean bean = transform( entity , dicsMap , organizationNameMap  ) ;
                beanList.add(bean);
            }
        }
        return beanList;
    }

    /**
     * ExpertEntity --->ExpertBean
     * @param entity
     * @return
     */
    public static ExpertBean transform(ExpertEntity entity , Map<String, Map<String , String> > dicsMap ,   Map<String, String> organizationNameMap  ){
        if(organizationNameMap==null){
            organizationNameMap=new HashMap<>();
        }

        ExpertBean bean = new ExpertBean();
        bean.setId(entity.getId());
        bean.setIDCard( entity.getIDCard()  );
        bean.setExpertName( entity.getExpertName() );
        bean.setExpertSex( entity.getExpertSex() );
        if(!StringUtils.isBlank(entity.getExpertSex())&&dicsMap.get("XB")!=null){
            bean.setExpertSexName( dicsMap.get("XB").get( entity.getExpertSex() ) );
        }

        bean.setOrganizationId( entity.getOrganizationId() );
        if(!StringUtils.isBlank(entity.getOrganizationId())){
            bean.setOrganizationName( organizationNameMap.get( entity.getOrganizationId() ) );
        }

        bean.setUnitName( entity.getUnitName() );
        bean.setExpertType( entity.getExpertType() );
        bean.setWhetherInnerExpert( entity.getWhetherInnerExpert() );
        bean.setExpertField( entity.getExpertField() );

        if(!StringUtils.isBlank(entity.getExpertField())&&dicsMap.get("ZYFL")!=null){
            bean.setExpertFieldName(  dicsMap.get("ZYFL").get( entity.getExpertField() )   );
        }

        bean.setNativePlace(  entity.getNativePlace()  );
        if(!StringUtils.isBlank(entity.getExpertField())&&dicsMap.get("JG")!=null){
            bean.setNativePlaceName( dicsMap.get("JG").get( entity.getExpertField() )  );
        }

        bean.setDateBirth( entity.getDateBirth() );
        bean.setDuties( entity.getDuties() );
        bean.setEducation( entity.getEducation() );
        if(!StringUtils.isBlank(entity.getExpertField())&&dicsMap.get("XL")!=null){
            bean.setEducationName( dicsMap.get("XL").get( entity.getExpertField() )  );
        }

        bean.setAddress( entity.getAddress() );
        bean.setPostalCode( entity.getPostalCode() );
        bean.setHomePhone( entity.getHomePhone() );
        bean.setMobilePhone( entity.getMobilePhone() );
        bean.setOfficePhone( entity.getOfficePhone() );
        bean.setNationCode( entity.getNationCode() );
        bean.setPolitical( entity.getPolitical() );
        bean.setPersonCategory( entity.getPersonCategory() );
        bean.setPersonReign( entity.getPersonReign() );
        bean.setQuarters ( entity.getQuarters() );
        if(!StringUtils.isBlank(entity.getQuarters())&&dicsMap.get("GW")!=null){
            bean.setQuartersName(  dicsMap.get("GW").get( entity.getQuarters() )   );
        }

        bean.setPersonStatus( entity.getPerson() );
        bean.setPicture( entity.getPicture() );
        bean.setRemarks( entity.getRemarks() );
        bean.setAddressName( entity.getAddressName() );
        bean.setDistrictCode( entity.getDistrictCode() );
        if(!StringUtils.isBlank(entity.getQuarters())&&dicsMap.get("XZQX")!=null){
            bean.setDistrictName( dicsMap.get("XZQX").get( entity.getQuarters() ) );
        }

        bean.setPerson( entity.getPerson() );
        return bean;
    }



//    /**
//     * 特别提示接收对象 转换
//     */
//    public static SecurityReceiverBean transform(SecurityWarningReceiverEntity entity){
//        SecurityReceiverBean bean = new SecurityReceiverBean();
//        bean.setNotificationTime(entity.getNotificationTime());
//        bean.setReceiverId(entity.getReceiverId());
//        bean.setReceiverType(entity.getReceiverType());
//        bean.setSecurityHintsId(entity.getSecurityHintsId());
//        return bean;
//    }

    /**
     * 作战信息卡 inputinfo转entity
     * */
    public static CombatInformationEntity transform (CombatInformationInputInfo inputInfo){
        CombatInformationEntity entity = new CombatInformationEntity();
        if (inputInfo != null){
            entity.setId(inputInfo.getId());
            entity.setOrganizationId(inputInfo.getOrganizationId());
            entity.setFileName(inputInfo.getFileName());
            entity.setFileURL(inputInfo.getFileURL());
            entity.setUnitAddress(inputInfo.getUnitAddress());
            entity.setUnitName(inputInfo.getUnitName());
            entity.setUploadTime(inputInfo.getUploadTime());
            entity.setAddressShortName(PinYinUtils.getHanziHeaderNumberInitials(inputInfo.getUnitAddress()));
            entity.setUnitShortName(PinYinUtils.getHanziHeaderNumberInitialsLower(inputInfo.getUnitName()));
        }
        return entity;
    }



    /**作战信息卡 entity转bean*/
    public static CombatInformationBean transform(CombatInformationEntity entity,Map<String,String> orgMap){
        CombatInformationBean bean = new CombatInformationBean();
        if (entity != null){
            bean.setId(entity.getId());
            bean.setAddressShortName(entity.getAddressShortName());
            bean.setFileName(entity.getFileName());
            bean.setFileURL(entity.getFileURL());
            bean.setOrganizationId(entity.getOrganizationId());
            if(!StringUtils.isBlank(entity.getOrganizationId())&&orgMap.get(entity.getOrganizationId())!=null){
                bean.setOrganizationName(orgMap.get(entity.getOrganizationId()));
            }

            bean.setUnitAddress(entity.getUnitAddress());
            bean.setUnitShortName(entity.getUnitShortName());
            bean.setUpdatedTime(entity.getUpdatedTime());
            bean.setUploadTime(entity.getCreatedTime());
            bean.setUnitName(entity.getUnitName());
        }
        return bean;
    }

    /**
     * 转换为string类
     *
     * @param obj 参数
     * @return 返回字符串
     */
    public static String toString(Object obj) {
        return (obj == null) ? "" : obj.toString();
    }

    // 联勤保障object to bean
    public static QueryUnitBean transfrom(Object[] obj, String distance) {
        QueryUnitBean queryUnitBean = new QueryUnitBean();
        queryUnitBean.setId(toString(obj[0]));
        queryUnitBean.setUnitName(toString(obj[1]));
        queryUnitBean.setUnitAddr(toString(obj[2]));
        queryUnitBean.setContacts(toString(obj[3]));
        queryUnitBean.setMobilePhone(toString(obj[4]));
        queryUnitBean.setUnitType(toString(obj[5]));
        queryUnitBean.setContext(toString(obj[6]));
        queryUnitBean.setSupportability(toString(obj[7]));
        queryUnitBean.setLatitude(toString(obj[8]));
        queryUnitBean.setLongitude(toString(obj[9]));
        queryUnitBean.setDistance(distance);
        return queryUnitBean;
    }
    /**
     * 获取环节时间列表下标
     *
     * @param list 环节列表
     * @param time 时间参数
     * @return 返回下标
     */
    public static int findStatus(List<IncidentProcessStepBean> list, long time) {
        if (list != null && list.size() > 0) {
            //案件是否已经结束
            Boolean isEnd = isEnd(list);
            for (int i = 0; i < list.size(); i++) {
                IncidentProcessStepBean bean = list.get(i);
                if (i != list.size() - 1) {
                    if (bean.getStartTime() <= time && bean.getEndTime() >= time) {
                        return i;
                    }
                } else {
                    if (isEnd) {
                        if (bean.getStartTime() <= time && bean.getEndTime() >= time) {
                            return i;
                        }
                    } else {
                        if (bean.getStartTime() <= time) {
                            return i;
                        } else {
                            return -1;
                        }
                    }
                }
            }
            return -1;
        } else {
            return -1;
        }
    }

    /**
     * 判断案件是否已经结束
     *
     * @param list 参数
     * @return 返回判断结果
     */
    private static Boolean isEnd(List<IncidentProcessStepBean> list) {
        if (list.get(list.size() - 1).getEndTime() != null && list.get(list.size() - 1).getEndTime() != 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取指挥序列下标
     *
     * @param list  指挥列表
     * @param start 环节开始时间
     * @param end   环节结束时间
     * @param isEnd 警情是否结束
     * @return 返回下标
     */
    public static int commandIndex(List<CommandSequenceBean> list, Long start, Long end, Boolean isEnd) {
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                CommandSequenceBean bean = list.get(i);
                if (i != list.size() - 1) {
                    if (bean.getStartCommandTime() <= start && bean.getEndCommandTime() >= end) {
                        return i;
                    }
                } else {
                    if (isEnd) {
                        if (bean.getStartCommandTime() <= start && bean.getEndCommandTime() >= end) {
                            return i;
                        }
                    } else {
                        if (bean.getStartCommandTime() <= start) {
                            return i;
                        }
                    }
                }
            }
            return -1;
        } else {
            return -1;
        }
    }






    /**
     *
     * 角度弧度计算公式 rad:(). <br/>
     * 360度=2π π=Math.PI
     * x度 = x*π/360 弧度
     * @return
     */
    private static double getRadian(double degree) {
        return degree * Math.PI / 180.0;
    }

    /**
     * 依据经纬度计算两点之间的距离 GetDistance:(). <br/>
     * @param lat1  1点的纬度
     * @param lng1  1点的经度
     * @param lat2  2点的纬度
     * @param lng2  2点的经度
     * @return 距离 单位 米
     */
    public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
        double radLat1 = getRadian(lat1);
        double radLat2 = getRadian(lat2);
        double a = radLat1 - radLat2;// 两点纬度差
        double b = getRadian(lng1) - getRadian(lng2);// 两点的经度差
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1)* Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        // 单位千米
        double EARTH_RADIUS = 6378.137;
        s = s * EARTH_RADIUS;
        return s * 1000;
    }

    public static LedVehicleEntity transforms(ledInputInfo infos, Long currentTime) {
        LedVehicleEntity entity=new LedVehicleEntity();
        entity.setId(infos.getId());
        entity.setOrder(infos.getOrder());
        entity.setCreatedTime(currentTime);
        entity.setIsDisPlay(infos.getIsDisPlay());
        entity.setUpdatedTime(currentTime);
        entity.setOldName(infos.getOldName());
        entity.setName(infos.getName());

        return entity;

    }

    public static LedOrganizationEntity transform(ledInputInfo infos, Long currentTime) {

        LedOrganizationEntity entity=new LedOrganizationEntity();
        entity.setId(infos.getId());
        entity.setOrder(infos.getOrder());
        entity.setCreatedTime(currentTime);
        entity.setIsDisPlay(infos.getIsDisPlay());
        entity.setUpdatedTime(currentTime);
        entity.setOldName(infos.getOldName());
        entity.setName(infos.getName());
        entity.setIsImportantCity(infos.getIsImportantCity());
        entity.setOrganizationParentId(infos.getOrganizationParentId());

        return entity;
    }


    public static LedBean transforms(Object[] obj, String type, Map<String, String> organization_name) {
        LedBean bean=new LedBean();
        bean.setId(toString(obj[0]));
        bean.setOrder(toString(obj[3]));
        bean.setOldName(toString(obj[2]));
        bean.setName(toString(obj[1]));
        if (Strings.isNotBlank(toString(obj[4]))){
            bean.setIsDisPlay(Integer.valueOf(toString(obj[4])));
        }else {
            bean.setIsDisPlay(0);
        }
        if (type.equals("organization")){
            bean.setOrganizationParentId(toString(obj[5]));
            if (Strings.isNotBlank(toString(obj[5]))){
                bean.setOrganizationParentName(organization_name.get(toString(obj[5])));
            }
            if (Strings.isNotBlank(toString(obj[6]))){
                bean.setIsImportantCity(Integer.valueOf(toString(obj[6])));
            }else {
                bean.setIsImportantCity(0);
            }
        }

        return bean;
    }
}
