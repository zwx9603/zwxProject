package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.IncidentImportantRuleEntity;
import com.dscomm.iecs.accept.dal.repository.IncidentImportantRuleRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.fireinputbean.IncidentImportantRuleSaveInputInfo;
import com.dscomm.iecs.accept.graphql.firetypebean.IncidentImportantRuleBean;
import com.dscomm.iecs.accept.graphql.typebean.IncidentBean;
import com.dscomm.iecs.accept.service.AttentionService;
import com.dscomm.iecs.accept.service.HandleService;
import com.dscomm.iecs.accept.service.IncidentImportantService;
import com.dscomm.iecs.accept.service.IncidentService;
import com.dscomm.iecs.accept.utils.transform.IncidentTransformUtil;
import com.dscomm.iecs.base.enums.EnableEnum;
import com.dscomm.iecs.base.websocket.WebsocketCodeEnum;
import com.dscomm.iecs.base.service.NotifyActionService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.graphql.typebean.SystemConfigurationBean;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.basedata.service.SystemConfigurationService;
import com.dscomm.iecs.ext.incident.status.INCIDENT_STATUS_FD;
import com.dscomm.iecs.keydata.service.ServletService;
import org.apache.logging.log4j.util.Strings;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 重要警情规则 服务实现类
 */

@Component("incidentImportantServiceImpl")
public class IncidentImportantServiceImpl implements IncidentImportantService {
    private static final Logger logger = LoggerFactory.getLogger(HandleServiceImpl.class);
    private DictionaryService dictionaryService;
    private GeneralAccessor accessor;
    private LogService logService;
    private IncidentImportantRuleRepository incidentImportantRuleRepository;
    private Environment env;
    private IncidentService incidentService ;
    private HandleService handleService ;
    private ServletService servletService ;
    private SystemConfigurationService systemConfigurationService ;
    private AttentionService attentionService ;
    private OrganizationService organizationService;
    private NotifyActionService notifyActionService ;

    private List<String> dics;


    @Autowired
    public IncidentImportantServiceImpl(@Qualifier("generalAccessor") GeneralAccessor accessor,IncidentImportantRuleRepository incidentImportantRuleRepository ,
                                        LogService logService,DictionaryService dictionaryService,  Environment env , IncidentService incidentService ,
                                        HandleService handleService , ServletService servletService , SystemConfigurationService systemConfigurationService ,
                                        AttentionService attentionService , OrganizationService organizationService ,  NotifyActionService notifyActionService

    ) {
        this.accessor = accessor;
        this.logService = logService;
        this.dictionaryService = dictionaryService;
        this.incidentImportantRuleRepository = incidentImportantRuleRepository;
        this.env = env ;
        this.incidentService = incidentService ;
        this.handleService = handleService ;
        this.servletService = servletService ;
        this.systemConfigurationService = systemConfigurationService ;
        this.attentionService = attentionService ;
        this.organizationService = organizationService ;
        this.notifyActionService = notifyActionService ;

        dics = new ArrayList<>(Arrays.asList(  "AJLX", "AJLXZX", "AJDJ",   "CZDX"   ));

    }


    /**
     * {@inheritDoc}
     *
     * @see #saveIncidentImportant(IncidentImportantRuleSaveInputInfo)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public IncidentImportantRuleBean saveIncidentImportant(IncidentImportantRuleSaveInputInfo incidentImportantSaveInputInfo) {
        if (null == incidentImportantSaveInputInfo) {
            logService.infoLog(logger, "service", "saveIncidentImportant", "IncidentImportantSaveInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            Long logStart = System.currentTimeMillis();
            IncidentImportantRuleBean res = new IncidentImportantRuleBean();

            IncidentImportantRuleEntity incidentImportantRuleEntity = IncidentTransformUtil.transform(incidentImportantSaveInputInfo);


            logService.infoLog(logger, "repository", "save(dbIncidentImportantEntity)", "repository is started...");
            Long startIncidentHandle = System.currentTimeMillis();

            accessor.save( incidentImportantRuleEntity ) ;

            Long endIncidentHandle = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbIncidentImportantEntity)", String.format("repository is finished,execute time is :%sms", endIncidentHandle - startIncidentHandle));

            res = IncidentTransformUtil.transform( incidentImportantRuleEntity ) ;

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveIncidentImportant", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return  res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveIncidentImportant", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_INCIDENT_IMPORTANT_FAIL);
        }

    }





    /**
     * {@inheritDoc}
     *
     * @see #removeIncidentImportantRule(String)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean removeIncidentImportantRule(String id) {
        if (Strings.isBlank(id)) {
            logService.infoLog(logger, "service", "removeIncidentImportant", "id is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            Long logStart = System.currentTimeMillis();

            IncidentImportantRuleEntity incidentImportantEntity = accessor.getById(id, IncidentImportantRuleEntity.class);

            if( incidentImportantEntity != null ){
                incidentImportantEntity.setValid(Boolean.FALSE);

                logService.infoLog(logger, "repository", "save(dbIncidentImportantEntity)", "repository is started...");
                Long startIncidentHandle = System.currentTimeMillis();

                accessor.save( incidentImportantEntity ) ;

                Long endIncidentHandle = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbIncidentImportantEntity)", String.format("repository is finished,execute time is :%sms", endIncidentHandle - startIncidentHandle));

                Long logEnd = System.currentTimeMillis();
                logService.infoLog(logger, "service", "removeIncidentImportant", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            }

            return  Boolean.TRUE;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "removeIncidentImportant", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.REMOVE_INCIDENT_IMPORTANT_FAIL);
        }

    }


    /**
     * {@inheritDoc}
     *
     */
    @Transactional(   rollbackFor =  Exception.class  )
    @Override
    public Boolean  enabledIncidentImportantRule ( String  incidentImportantRuleId    ) {
        try {
            Boolean res = true ;
            IncidentImportantRuleEntity incidentImportantRuleEntity = accessor.getById( incidentImportantRuleId , IncidentImportantRuleEntity.class ) ;
            if( incidentImportantRuleEntity != null ){
                String incidentTypeCode = incidentImportantRuleEntity.getIncidentTypeCode() ;
                Boolean incidentImportantTypeEnabled = Boolean.parseBoolean(env.getProperty("incidentImportantTypeEnabled")) ;
                //判断是否案件类型分类 获得启用重要警情规则
                List<IncidentImportantRuleEntity> importantRuleEntityList = null ;
                if( incidentImportantTypeEnabled ){
                    importantRuleEntityList = incidentImportantRuleRepository.findIncidentImportantEnable( incidentTypeCode ) ;
                }else{
                    importantRuleEntityList = incidentImportantRuleRepository.findIncidentImportantEnable() ;
                }
                if( importantRuleEntityList != null && importantRuleEntityList.size() > 0  ){
                    for( IncidentImportantRuleEntity incidentImportantRule : importantRuleEntityList){
                        incidentImportantRule.setWhetherEnable(  EnableEnum.ENABLE_FALSE.getCode() );
                    }
                    accessor.save( importantRuleEntityList  ) ;
                }
                //设置启用状态
                incidentImportantRuleEntity.setWhetherEnable(  EnableEnum.ENABLE_TRUE.getCode() );
                accessor.save( incidentImportantRuleEntity ) ;
            }
            return  res ;
        } catch (Exception ex) {
            if (logger.isErrorEnabled()) {
                logger.error(String.format(" enabledRule error:"), ex);
            }
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_INCIDENT_IMPORTANT_FAIL );
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see #findIncidentImportantRuleCondition( )
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<IncidentImportantRuleBean> findIncidentImportantRuleCondition (   ) {

        try {
            Long logStart = System.currentTimeMillis();
            List<IncidentImportantRuleBean> res = new ArrayList<>() ;


            logService.infoLog(logger, "repository", "findIncidentImportant(incidentType)", "repository is started...");
            Long startIncidentHandle = System.currentTimeMillis();

            List<IncidentImportantRuleEntity> queryResult = incidentImportantRuleRepository.findIncidentImportant();

            Long endIncidentHandle = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findIncidentImportant(incidentType)", String.format("repository is finished,execute time is :%sms", endIncidentHandle - startIncidentHandle));


            //获得全部有效的警情规则
            List<IncidentImportantRuleEntity> importantRuleEntityList = incidentImportantRuleRepository.findIncidentImportant() ;
            if( importantRuleEntityList != null && importantRuleEntityList.size() > 0 ){
                for ( IncidentImportantRuleEntity incidentImportantRuleEntity : importantRuleEntityList  ){
                    IncidentImportantRuleBean bean = IncidentTransformUtil.transform( incidentImportantRuleEntity  ) ;
                    res.add( bean ) ;
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveIncidentImportant", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return  res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findIncidentImportantByType", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_INCIDENT_IMPORTANT_FAIL);
        }

    }




    /**
     * {@inheritDoc}
     *
     * @see #judgeIncidentImportant( String )
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean judgeIncidentImportant( String incidentId ) {
        if (  Strings.isBlank( incidentId )  ) {
            logService.infoLog(logger, "service", "judgeIncidentImportant", "HandleSaveInputInfo or incidentId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            Long logStart = System.currentTimeMillis();

            Boolean res = false ;

            //根据案件id获得 案件信息
            IncidentBean incidentBean = incidentService.findIncident(incidentId , false) ;
            if( incidentBean == null ){
                throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_NULL );
            }
            //判断此警情是否已存在 重要警情 存在 结束
            Boolean isExist = attentionService.existImportantAttention(incidentId);
            if( isExist ){
                return  true ;
            }

            //获得重要警情规则信息
            Boolean incidentImportantTypeEnabled = Boolean.parseBoolean(env.getProperty("incidentImportantTypeEnabled")) ;
            List<IncidentImportantRuleEntity>  incidentImportantRuleEntityList = null ;
            if( incidentImportantTypeEnabled ){
                incidentImportantRuleEntityList = incidentImportantRuleRepository.findIncidentImportantEnable( incidentBean.getIncidentTypeCode() ) ;
            }else{
                incidentImportantRuleEntityList = incidentImportantRuleRepository.findIncidentImportantEnable() ;
            }
            if( incidentImportantRuleEntityList == null || incidentImportantRuleEntityList.size()< 1 ){
                res = true ;
                return  res ;
            }
            IncidentImportantRuleEntity incidentImportantRuleEntity = incidentImportantRuleEntityList.get(0) ;

            //字典信息
            Map<String,Map<String,String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;
            String importReason = buildImportantIncidentReason( incidentImportantRuleEntity , incidentBean , dicsMap ) ;
            //判断是否为重要警情
            if( Strings.isNotBlank( importReason ) ){
                res = true ;
                //保存重要警情关注信息 type1 警情关注 2 重要警情关注 attentionType关注类型 1 普通关注（个人）；2班长关注 （ 全系统 ）attentionWay关注方式  1 系统关注  2 人工关注
                attentionService.saveAttention( incidentBean.getId()  , 2  , 2  , 1 , importReason ) ;
                //发送websocket

                //消息通知案件参与单位
                Set<String> orgSet = new HashSet<>() ;
                List<String> orgIds = incidentService.findIncidentParticipantOrganizationId( incidentId  );
                orgSet.addAll( orgIds ) ;
                List<String> orgCodes =  organizationService.findOrganizationCodesByIds(new ArrayList<>(orgSet));
                orgSet.addAll(orgCodes);
                notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.BUILD_IMPORTANT_INCIDENT.getCode(), res, orgSet);

            }
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveIncidentImportant", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return  res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findIncidentImportantByType", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_INCIDENT_IMPORTANT_FAIL);
        }

    }


    /**
     * 根据重要警情规则 判断是否为重要警情  如果为重要警情 原因不为空  不为重要警情 原因为空
     */
    private String   buildImportantIncidentReason (  IncidentImportantRuleEntity incidentImportantRuleEntity , IncidentBean incidentBean  ,  Map<String,Map<String,String> > dicsMap   ){
        //重要警情原因
        String importReason =  "" ;
        //判断案件等级是否为重要警情
        String incidentImportantRuleLevelCode   = incidentImportantRuleEntity.getIncidentLevelCode() ;
        String incidentLevelCode =   incidentBean.getIncidentLevelCode()      ;
        if( incidentImportantRuleLevelCode.contains( incidentLevelCode ) ){
            importReason = importReason + "等级" + dicsMap.get("AJDJ").get( incidentLevelCode  ) + ";" ;
        }
        // 警情 受伤 死亡 被困人数 判断是否为重要警情
        //被困人数判断
        Integer trappedEnable =  incidentImportantRuleEntity.getTrappedEnable() ;
        Integer trappedNumber = incidentImportantRuleEntity.getTrappedNumber() ;
        if( trappedEnable == 1 ){
            String incidentTrappedNumber = incidentBean.getTrappedCount() ;
            Boolean isNumber = isNumeric(incidentTrappedNumber) ;
            if( isNumber ){
                if(  Integer.parseInt(  incidentTrappedNumber ) >= trappedNumber    ){
                    importReason = importReason + "被困" + incidentTrappedNumber  +"人" + ";" ;
                }
            }
        }
        //受伤人数判断
        Integer injuredEnable =  incidentImportantRuleEntity.getInjuredEnable() ;
        Integer injuredNumber = incidentImportantRuleEntity.getInjuredNumber() ;
        if( injuredEnable == 1 ){
            String incidentInjuredNumber = incidentBean.getInjuredCount() ;
            Boolean isNumber = isNumeric(incidentInjuredNumber) ;
            if( isNumber ){
                if(  Integer.parseInt(  incidentInjuredNumber ) >= injuredNumber    ){
                    importReason = importReason + "受伤" + incidentInjuredNumber +"人" + ";" ;
                }
            }
        }
        //死亡人数判断
        Integer deathsEnable =  incidentImportantRuleEntity.getDeathsEnable() ;
        Integer deathsNumber = incidentImportantRuleEntity.getDeathsNumber() ;
        if( deathsEnable == 1 ){
            String incidentDeathNumber = incidentBean.getDeathCount() ;
            Boolean isNumber = isNumeric(incidentDeathNumber) ;
            if( isNumber ){
                if(  Integer.parseInt(  incidentDeathNumber ) >= deathsNumber    ){
                    importReason = importReason + "死亡" + incidentDeathNumber +"人" + ";" ;
                }
            }
        }
        //出动车辆 数量 判断是否为重要警情
        Integer combatVehicleNum = incidentImportantRuleEntity.getCombatVehicleNum() ;
        List<String> combatVehicleIds =  handleService.findIncidentHandleOrganizationVehicleId( incidentBean.getId() ) ;
        if( combatVehicleIds.size() >= combatVehicleNum  ){
            importReason = importReason + "出动" + combatVehicleIds.size() + "车" + ";" ;
        }
        //关键字判断 主要在  补充信息 注意事项 案件描述 查找 判断是否为重要警情 关键字为多个用逗号分隔
        String keyword = incidentImportantRuleEntity.getKeyword() ;
        if( Strings.isNotBlank( keyword  )){

            String  supplementInfo = incidentBean.getSupplementInfo() ;// 补充信息
            String  attentions = incidentBean.getAttentions() ;// 注意事项
            String  incidentDescribe = incidentBean.getIncidentDescribe()  ;//案件描述
            //处置对象
            String  disposalObjectCode = incidentBean.getDisposalObjectCode() ;
            String  disposalObjectName = dicsMap.get("CZDX").get( disposalObjectCode  ) ;

            List<String>  keywords = Arrays.asList( keyword.split(",") ) ;
            for ( String keywordLike : keywords ) {
                //补充信息判断是否为重要警情
                if( Strings.isNotBlank( supplementInfo ) && supplementInfo.contains( keywordLike)  ){
                    importReason = importReason + "补充信息包含：" + keywordLike + ";" ;
                    break ;
                }
                //注意事项判断是否为重要警情
                if( Strings.isNotBlank( attentions ) && attentions.contains( keywordLike)  ){
                    importReason = importReason + "注意事项包含：" + keywordLike + ";" ;
                    break ;
                }
                //案件描述判断是否为重要警情
                if( Strings.isNotBlank( incidentDescribe ) && incidentDescribe.contains( keywordLike)  ){
                    importReason = importReason + "案件描述包含：" + keywordLike + ";" ;
                    break ;
                }
                //处置对象判断是否为重要警情

                if( Strings.isNotBlank( disposalObjectName ) && disposalObjectName.contains( keywordLike) ){
                    importReason = importReason + "处置对象包含：" + keywordLike   + ";" ;
                    break ;
                }
            }

        }
        //超时处置时间 判断是否为重要警情
        SystemConfigurationBean systemConfigurationBean = systemConfigurationService.getSystemConfigByConfigType( "incidentReturnStatusCode" ) ;
        String returnStatusCode = systemConfigurationBean == null   ?  INCIDENT_STATUS_FD.getCode() : systemConfigurationBean.getConfigValue() ;
        String incidentStatusCode  =  incidentBean.getIncidentStatusCode()  ;
        Long nowTime  =  servletService.getSystemTime() ;
        Long handleTime  = nowTime - incidentBean.getRegisterIncidentTime() ;
        Long overTime =  incidentImportantRuleEntity.getOvertime() ;
        if( Integer.parseInt( incidentStatusCode ) < Integer.parseInt( returnStatusCode ) && handleTime > overTime  ){
            long mins = Math.round( handleTime / ( 60*1000) ) ;
            importReason = importReason + "处置" +   mins + "分钟"  + ";" ;
        }


        return  importReason ;

    }


    /**
     * 判断字符串是否为数据
     */
    public static boolean isNumeric(String str) {
        try{
            Integer.parseInt(str);
            return true;
        }catch(NumberFormatException e) {
             return false;
        }
    }

}

