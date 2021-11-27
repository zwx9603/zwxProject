package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.IncidentEntity;
import com.dscomm.iecs.accept.dal.po.IncidentStatusChangeEntity;
import com.dscomm.iecs.accept.dal.po.RallyPointEntity;
import com.dscomm.iecs.accept.dal.repository.IncidentFireRepository;
import com.dscomm.iecs.accept.dal.repository.RallyPointRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.firetypebean.ActionSummaryBean;
import com.dscomm.iecs.accept.graphql.firetypebean.CommanderBean;
import com.dscomm.iecs.accept.graphql.firetypebean.IncidentDossierDrawingBean;
import com.dscomm.iecs.accept.graphql.firetypebean.IncidentSummaryBean;
import com.dscomm.iecs.accept.graphql.inputbean.InstructionQueryInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.*;
import com.dscomm.iecs.accept.service.*;
import com.dscomm.iecs.accept.utils.transform.FireTransformUtil;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.graphql.typebean.AttachmentBean;
import com.dscomm.iecs.basedata.graphql.typebean.KeyUnitBean;
import com.dscomm.iecs.basedata.service.AttachmentService;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.KeyUnitService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import org.apache.logging.log4j.util.Strings;
import org.mx.StringUtils;
import org.mx.dal.service.GeneralAccessor;
import org.mx.error.UserInterfaceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 描述：警情 服务类实现
 */
@Component("incidentFireServiceImpl")
public class IncidentFireServiceImpl implements IncidentFireService {
    private static final Logger logger = LoggerFactory.getLogger(IncidentFireServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private Environment env;
    private IncidentFireRepository incidentFireRepository ;
    private DictionaryService dictionaryService;
    private OrganizationService organizationService;
    private KeyUnitService keyUnitService;
    private ActionSummaryService actionSummaryService;
    private RallyPointRepository rallyPointRepository;
    private IncidentService incidentService;
    private HandleService handleService ;
    private CommanderService commanderService;


    private AcceptanceService acceptanceService;
    private InstructionService instructionService;
    private SecurityHintsService securityHintsService ;
    private RallyPointService rallyPointService;
    private AttachmentService attachmentService;
    private DocumentService documentService;
    private TelephoneService telephoneService;


    private List<String> dics;
    /**
     * 默认的构造函数
     */
    @Autowired
    @Lazy(true)
    public IncidentFireServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor, OrganizationService organizationService,

                                   Environment env, DictionaryService dictionaryService, IncidentFireRepository incidentFireRepository , KeyUnitService keyUnitService,
                                   ActionSummaryService actionSummaryService, RallyPointRepository rallyPointRepository ,
                                   IncidentService incidentService, HandleService handleService,CommanderService commanderService,


                                   AcceptanceService acceptanceService , SecurityHintsService securityHintsService ,
                                   RallyPointService rallyPointService, AttachmentService attachmentService,InstructionService instructionService,
                                   DocumentService documentService,
                                   TelephoneService telephoneService
    ) {
        this.logService = logService;
        this.accessor = accessor;
        this.env = env;
        this.incidentFireRepository = incidentFireRepository;
        this.dictionaryService = dictionaryService;
        this.keyUnitService = keyUnitService;
        this.organizationService = organizationService;
        this.actionSummaryService = actionSummaryService;
        this.rallyPointRepository = rallyPointRepository;
        this.incidentService=incidentService;
        this.handleService = handleService ;
        this.commanderService=commanderService;

        this.acceptanceService=acceptanceService;
        this.instructionService=instructionService;
        this.securityHintsService=securityHintsService;
        this.rallyPointService=rallyPointService;
        this.attachmentService=attachmentService;
        this.documentService=documentService;
        this.telephoneService=telephoneService;

        dics = new ArrayList<>(Arrays.asList("XB","XZQX", "BJFS", "LAFS", "AJLX", "AJLXZX", "AJDJ", "AJZT", "AJXZ", "CZDX", "ZDDWLX" ,"JZJG" ,"YWQK", "ZHCS"   ));

    }



    @Transactional(readOnly = true)
    @Override
    public IncidentSummaryBean findIncidentSummary(String incidentId) {
        if ( Strings.isBlank( incidentId) ){
            logService.infoLog(logger, "service", "findIncidentSummary", "incident is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            IncidentSummaryBean res=new IncidentSummaryBean();

            logService.infoLog(logger, "service", "findIncidentSummary", "service is started...");
            Long logStart = System.currentTimeMillis();

            IncidentBean incidentBean = incidentService.findIncident(incidentId , false );

            if( incidentBean == null ){
                logService.infoLog(logger, "service", "findIncidentSummary", "incident entity  is null.");
                throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_NULL);
            }

            List<HandleOrganizationVehicleBean> handleOrganizationVehicleBeans = handleService.findHandleOrganizationVehicle(incidentId, null , false  );

            List<CommanderBean> commanderBeanList =commanderService.findCommander(incidentId);

            res.setIncidentId( incidentId );
            res.setIncidentBean( incidentBean );
            if ( incidentBean.getKeyUnitBean() != null   ){
                res.setKeyUnitBean(  incidentBean.getKeyUnitBean() );
            }
            res.setCommanderBeanList(commanderBeanList);
            res.setHandleOrganizationVehicleBeanList( handleOrganizationVehicleBeans );

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findIncidentSummary", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        }catch (Exception ex){
            logService.erorLog(logger, "service", "findIncidentSummary", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_SUMMARY_FALL);
        }
    }




    @Transactional(readOnly = true)
    @Override
    public IncidentDossierDrawingBean findIncidentDossierDrawing(String incidentId) {
        if (Strings.isBlank(incidentId)) {
            logService.infoLog(logger, "service", "findIncidentDossierDrawing", "incidentId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            //TODO
            List<String> APPROVAL =new ArrayList<>(); //批示
            APPROVAL.add("APPROVAL");
            List<String> INSTRUCTION =new ArrayList<>(); //指令
            INSTRUCTION.add("INSTRUCTION");
            List<String> ASK_FOR_LEADER =new ArrayList<>(); //请示领导
            ASK_FOR_LEADER.add("ASK_FOR_LEADER");
            List<String> CONSULT_EXPERT =new ArrayList<>();//咨询专家
            CONSULT_EXPERT.add("CONSULT_EXPERT");
            List<String> NOTIFIED =new ArrayList<>(); // 通报
            NOTIFIED.add("NOTIFIED");
            List<String> CONTACT =new ArrayList<>();//联系
            CONTACT.add("CONTACT");
            InstructionQueryInputInfo instructionQueryInputInfo =new InstructionQueryInputInfo();
            logService.infoLog(logger, "service", "findIncidentDossierDrawing", "service is started...");
            Long logStart = System.currentTimeMillis();

            IncidentDossierDrawingBean res = new IncidentDossierDrawingBean();
            logService.infoLog(logger, "repository", "findIncidentDossierDrawing", "repository is started...");
            Long start = System.currentTimeMillis();

            //获取案件详情
            IncidentEntity incidentEntity = accessor.getById(incidentId,IncidentEntity.class);
            if(incidentEntity == null){
                logService.infoLog(logger, "service", "findIncidentDossierDrawing", "incidentEntity is null.");
                throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_NULL);
            }
            IncidentMergeBean incidentMergeBean = incidentService.findIncidentMerge( incidentId );
            IncidentBean incidentBean = incidentMergeBean.getMainIncident() ;

            //受理单记录
            AcceptanceInformationBean acceptanceInformationBean = acceptanceService.findAcceptance( incidentId ) ;

            //获取车辆
            List<HandleOrganizationVehicleBean> handleOrganizationVehicleBeans = handleService.findHandleOrganizationVehicle(incidentId , null , false  );

            //获取批示记录
            instructionQueryInputInfo.setCommandId(null);
            instructionQueryInputInfo.setIncidentId(incidentId);
            instructionQueryInputInfo.setInstructionType(APPROVAL);
            instructionQueryInputInfo.setKeyword(null);
            List<InstructionBean> APPROVALs = instructionService.findInstructionCondition(instructionQueryInputInfo);

            //获取指令记录
            instructionQueryInputInfo.setCommandId(null);
            instructionQueryInputInfo.setIncidentId(incidentId);
            instructionQueryInputInfo.setInstructionType(INSTRUCTION);
            instructionQueryInputInfo.setKeyword(null);
            List<InstructionBean> INSTRUCTIONs = instructionService.findInstructionCondition(instructionQueryInputInfo);

            //获取请示领导记录
            instructionQueryInputInfo.setCommandId(null);
            instructionQueryInputInfo.setIncidentId(incidentId);
            instructionQueryInputInfo.setInstructionType(ASK_FOR_LEADER);
            instructionQueryInputInfo.setKeyword(null);
            List<InstructionBean> ASK_FOR_LEADERs = instructionService.findInstructionCondition(instructionQueryInputInfo);

            //获取咨询专家记录
            instructionQueryInputInfo.setCommandId(null);
            instructionQueryInputInfo.setIncidentId(incidentId);
            instructionQueryInputInfo.setInstructionType(ASK_FOR_LEADER);
            instructionQueryInputInfo.setKeyword(null);
            List<InstructionBean> CONSULT_EXPERTs = instructionService.findInstructionCondition(instructionQueryInputInfo);

            //获取通报记录
            instructionQueryInputInfo.setCommandId(null);
            instructionQueryInputInfo.setIncidentId(incidentId);
            instructionQueryInputInfo.setInstructionType(ASK_FOR_LEADER);
            instructionQueryInputInfo.setKeyword(null);
            List<InstructionBean> NOTIFIEDs = instructionService.findInstructionCondition(instructionQueryInputInfo);

            //获取联系记录
            instructionQueryInputInfo.setCommandId(null);
            instructionQueryInputInfo.setIncidentId(incidentId);
            instructionQueryInputInfo.setInstructionType(ASK_FOR_LEADER);
            instructionQueryInputInfo.setKeyword(null);
            List<InstructionBean> CONTACTs = instructionService.findInstructionCondition(instructionQueryInputInfo);

            //获取特别警示记录

            List<SecurityBean> securityList = securityHintsService.findSecurityHintsList(incidentBean.getId());

            //获取任务部署记录
            List<RallyPointBean> rallyPoint = rallyPointService.findRallyPoint(incidentId);

            //附件
            List<AttachmentBean> attachmentBeanList = attachmentService.findAttachment(incidentId,null);

            //指挥员信息
            List<CommanderBean> commanderBeanList =commanderService.findCommander(incidentId);

            //重点单位信息
            if ( ! Strings.isBlank( incidentBean.getKeyUnitId() ) ){
                KeyUnitBean keyUnitBean = keyUnitService.findKeyUnitById(incidentBean.getKeyUnitId());
                res.setKeyUnitBean(keyUnitBean);
            }
            //火场文书
            List<DocumentBean> documentBeanList = documentService.findDocument(incidentId , null , null  );

            //电话报警信息
            TelephoneInformationBean telephoneInformationBean = telephoneService.findTelephone(incidentId);


            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findIncidentDossierDrawing", String.format("repository is finished,execute time is :%sms", end - start));

            res.setIncidentId(incidentBean.getId()); //案件id
            res.setIncidentBean(incidentBean);  //警情详情
            res.setTelephoneInformationBean(telephoneInformationBean);    //电话报警信息
            res.setHandleOrganizationVehicleBeanList(handleOrganizationVehicleBeans);//根据案件id获得车辆信息
            res.setRallyPoint(rallyPoint); //任务部署记录
            res.setSecurityHintsBeanList(securityList);    //特别警示
            res.setCommanderBeanList(commanderBeanList);  //指挥员信息
            res.setApprovals(APPROVALs); //批示
            res.setAskAndReportBeanList(CONSULT_EXPERTs);   //咨询专家
            res.setAskAndReportBeanList(ASK_FOR_LEADERs); //请示汇报
            res.setInstructionBeanList(INSTRUCTIONs); //下达指令
            res.setNotifiedBeanList(NOTIFIEDs); //通报
            res.setContactBeanList(CONTACTs);//联系
            res.setDocumentBeanList(documentBeanList); //火场文书
            res.setAttachmentBeanList(attachmentBeanList); //附件





            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findIncidentDossierDrawing", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findIncidentDossierDrawing", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_INCIDENT_DOSSIER_FAIL);
        }
    }



    /**
     * 警情回溯
     * @param incidentId 警情ID
     * @return
     */

    @Override
    public IncidentReplayBean incidentReplayReady(String incidentId) {
        try {
            //非空判断
            if (StringUtils.isBlank(incidentId)) {
                throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL );
            } else {
                //加载字典
//                String[] dics = {"AJLX", "AJDJ", "LAFS", "YWQK", "JZJG", "CZDX", "XZQX", "BJLX", "AJZT", "CLZT"};
//                Map<String, BaseDictTreeEntity> map_CZDX = dictionaryService.findTreeDictionary("CZDX");// 处置对象类型
//                Map<String, Map<String, String>> dicMaps = dictionary(dics);
                // 查询出所有需要用到的字典
                Map<String, Map<String, String>> dicsMap  = dictionaryService.findDictionaryMap(dics) ;
                // 查询机构id-名称缓存map
                Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();

                IncidentReplayBean replayBean = new IncidentReplayBean();
                String endStatusCode = "200";
                //读取配置文件的案件结束状态代码
                String configEndStatusCode = env.getProperty("incidentEndStatusCode");
                if (configEndStatusCode != null && configEndStatusCode != "") {
                    endStatusCode = configEndStatusCode;
                }
                //处理警情基本信息
                IncidentInfoBaseInfoBean baseInfoBean = new IncidentInfoBaseInfoBean();
                baseInfoBean.setIncidentId(incidentId);
                if (logger.isDebugEnabled()) {
                    logger.debug(String.format("开始查询警情 %s 的基本信息...", incidentId));
                }
                List<Object[]> baseInfoList = incidentFireRepository.incidentReplayBaseInfo(incidentId);
                if (logger.isDebugEnabled()) {
                    logger.debug(String.format("查询完毕。结果集长度：%s.", baseInfoList.size()));
                }
                if (baseInfoList != null && baseInfoList.size() > 0) {
                    Object[] objs = baseInfoList.get(0);
                    if (objs != null && objs.length > 0) {
                        //案件类型
                        baseInfoBean.setIncidentType(dicsMap.get("AJLX").get(FireTransformUtil.toString(objs[0])));
                        //立案时间
                        baseInfoBean.setRegisterEventTime((Long) objs[1]);
                        //案发地址
                        baseInfoBean.setAddress((String) objs[2]);
                        //警情等级
                        baseInfoBean.setIncidentLevel(dicsMap.get("AJDJ").get(FireTransformUtil.toString(objs[3])));
                        //立案方式
                        baseInfoBean.setIncidentSource(dicsMap.get("LAFS").get(FireTransformUtil.toString(objs[4])));
                        //灾害场所
                        baseInfoBean.setDisasterSites((String) objs[5]);
                        //烟雾情况
                        baseInfoBean.setSmogSituation(dicsMap.get("YWQK").get(FireTransformUtil.toString(objs[6])));
                        //建筑结构
                        baseInfoBean.setBuildingStructure(dicsMap.get("JZJG").get(FireTransformUtil.toString(objs[7])));
                        //被困人数
                        baseInfoBean.setTrappedPersonsNumber(FireTransformUtil.toString(objs[8]));
                        //燃烧面积
                        baseInfoBean.setBurningArea(FireTransformUtil.toString(objs[9]));
                        //燃烧楼层
                        baseInfoBean.setBurnFloor(FireTransformUtil.toString(objs[10]));
                        //处置对象
                        //String disposalObjectId = (objs[11]).toString();
                        baseInfoBean.setDisposalObject(dicsMap.get("CZDX").get(FireTransformUtil.toString(objs[11])));

                        //楼房层数
                        baseInfoBean.setStoreysOfBuildings(FireTransformUtil.toString(objs[12]));
                        //主管机构ID
                        baseInfoBean.setFireSquadronId(FireTransformUtil.toString(objs[13]));
                        //经度
                        baseInfoBean.setLongitude(FireTransformUtil.toString(objs[14]));
                        //纬度
                        baseInfoBean.setLatitude(FireTransformUtil.toString(objs[15]));
                        //行政区
                        baseInfoBean.setDistrict(dicsMap.get("XZQX").get(FireTransformUtil.toString(objs[16])));
                        //描述，补充信息
                        baseInfoBean.setDescription(FireTransformUtil.toString(objs[17]));
                        //案发时间，报警开始时间
                        baseInfoBean.setOccur((Long) objs[18]);
                        //报警方式
                        baseInfoBean.setAlarmMode(dicsMap.get("BJFS").get(FireTransformUtil.toString(objs[19])));
                        //报警人姓名
                        baseInfoBean.setAlarmPerson(FireTransformUtil.toString(objs[20]));
                        //报警人联系电话
                        baseInfoBean.setAlarmPersonPhone(FireTransformUtil.toString(objs[21]));
                        //电话报警录音号
                        baseInfoBean.setAlarmTapeNo(FireTransformUtil.toString(objs[22]));
                        //主管机构名称
                        baseInfoBean.setFireSquadronName(FireTransformUtil.toString(objs[23]));
                        //警情状态
                        baseInfoBean.setIncidentStatus(dicsMap.get("AJZT").get(FireTransformUtil.toString(objs[24])));
                        //反馈受伤人数
                       // Integer feedbackInjuredNumber = (Integer) objs[25];
                        //反馈死亡人数
                        //Integer feedbackDeathNumber = (Integer) objs[26];
                        baseInfoBean.setInjuredCount(FireTransformUtil.toString(objs[25]));
                        baseInfoBean.setDeathCount(FireTransformUtil.toString(objs[26]));
                        //事故主要情况
                        baseInfoBean.setMajorSituation(FireTransformUtil.toString(objs[27]));
                        //重点单位
                        KeyUnitBean unitBean=new KeyUnitBean();
                        //重点单位名称
                        unitBean.setUnitName(FireTransformUtil.toString(objs[28]));
                        //联系人，负责人
                        unitBean.setSecurityPerson(FireTransformUtil.toString(objs[29]));
                        //联系电话
                        unitBean.setSecurityPersonPhone(FireTransformUtil.toString(objs[30]));
                        //重点单位单位等级
                        unitBean.setUnitLevelCode(FireTransformUtil.toString(objs[31]));
                        baseInfoBean.setKeyUnitBean(unitBean);
                        //是否启动指挥
                        String commandId = FireTransformUtil.toString(objs[32]);
                        if (commandId != "") {
                            baseInfoBean.setStartCommand(true);
                            //指挥姓名
                            baseInfoBean.setCommanderName(FireTransformUtil.toString(objs[33]));
                            //指挥联系方式
                            baseInfoBean.setCommanderPhone(FireTransformUtil.toString(objs[34]));
                        } else {
                            baseInfoBean.setStartCommand(false);
                        }
                        //战评总结
                        if (logger.isDebugEnabled()) {
                            logger.debug(String.format("开始查询案件ID为:%s 的战评总结列表...", incidentId));
                        }
                        List<ActionSummaryBean> summaryBeans = actionSummaryService.findActionSummary(incidentId);
                        if (logger.isDebugEnabled()) {
                            logger.debug(String.format("查询战评总结列表完毕。"));
                        }
                        //为警情基本信息设置战评信息
                        baseInfoBean.setActionSummarys(summaryBeans);
                        //为回溯信息设置警情基本信息
                        replayBean.setBaseInformation(baseInfoBean);
                        if (logger.isDebugEnabled()) {
                            logger.debug(String.format("开始查询案件ID为:%s 的指挥列表...", incidentId));
                        }
                        List<Object[]> commands = incidentFireRepository.findCommandListByIncident(incidentId);
                        if (logger.isDebugEnabled()) {
                            logger.debug(String.format("查询指挥列表完毕，共查询到 %s 条数据。", commands.size()));
                        }
                        //TODO
                        //如果不存在指挥，模拟一条指挥，指挥启动时间就设为立案时间
                        if (commands == null || commands.size() == 0) {
                            if (commands == null) {
                                commands = new ArrayList<>();
                            }
                            commands.add(new Object[]{"command01", baseInfoBean.getRegisterEventTime()});
                        }
                        if (commands != null && commands.size() > 0) {
                            List<CommandSequenceBean> commandSequenceBeans = new ArrayList<>();
                            Map<String, CommandSequenceBean> sequenceBeanMap = new HashMap<>();
                            for (Object[] command : commands) {
                                CommandSequenceBean bean = new CommandSequenceBean();
                                //指挥ID
                                bean.setCommandId(FireTransformUtil.toString(command[0]));
                                //指挥启动时间
                                bean.setStartCommandTime((Long) command[1]);
                                //TODO 可以在此添加指挥开始时间非空验证
                                sequenceBeanMap.put(bean.getCommandId(), bean);
                                commandSequenceBeans.add(bean);
                            }
                            //按照指挥开始时间升序排序
                            Collections.sort(commandSequenceBeans, (a, b) -> a.getStartCommandTime().compareTo(b.getStartCommandTime()));
                            //如果有多次指挥，为指挥设置结束时间,最后一次指挥暂不设置结束时间
                            if (commandSequenceBeans.size() > 1) {
                                for (int i = 0; i < commandSequenceBeans.size() - 1; i++) {
                                    CommandSequenceBean bean = commandSequenceBeans.get(i);
                                    //将下一个指挥的开始时间设置为本次指挥的结束时间
                                    bean.setEndCommandTime(commandSequenceBeans.get(i + 1).getStartCommandTime());
                                    commandSequenceBeans.set(i, bean);
                                }
                            }
                            //判断第一次指挥，如果指挥启动时间为null或者启动时间大于立案时间，则把指挥开始时间调整为立案时间
                            CommandSequenceBean sequenceBean = commandSequenceBeans.get(0);
                            if (sequenceBean.getStartCommandTime() == null || sequenceBean.getStartCommandTime() > baseInfoBean.getRegisterEventTime()) {
                                sequenceBean.setStartCommandTime(baseInfoBean.getRegisterEventTime());
                                commandSequenceBeans.set(0, sequenceBean);
                            }
                            //获取警情状态变更
                            if (logger.isDebugEnabled()) {
                                logger.debug(String.format("开始查询案件ID为:%s 的案件状态变更列表...", incidentId));
                            }

                            List<IncidentStatusChangeEntity> statusChangeRecordEntities = incidentFireRepository.findAllStatusByIncidentId(incidentId, Integer.valueOf(endStatusCode));
                            if (logger.isDebugEnabled()) {
                                logger.debug(String.format("查询案件状态变更列表完毕，共查询到 %s 条数据。", statusChangeRecordEntities.size()));
                            }

                            List<IncidentProcessStepBean> incidentProcessStepBeans;
                            //对查询到的案件状态变更进行数据处理
                            if (statusChangeRecordEntities != null && statusChangeRecordEntities.size() > 0) {
                                //对查询到的数据进行升序排序
                                statusChangeRecordEntities.sort(Comparator.comparing(IncidentStatusChangeEntity::getChangeTime));
                                //判断案件是否已结束,如果已结束，给案件设置结束时间
                                IncidentStatusChangeEntity lastStatus = statusChangeRecordEntities.get(statusChangeRecordEntities.size() - 1);
                                if (lastStatus.getIncidentStatusCode().equals(endStatusCode) ) {
                                    //案件设置结束时间
                                    baseInfoBean.setEndTime(lastStatus.getChangeTime());
                                    //为最后一次指挥设置结束时间
                                    int last = commandSequenceBeans.size() - 1;
                                    CommandSequenceBean bean = commandSequenceBeans.get(last);
                                    bean.setEndCommandTime(lastStatus.getChangeTime());
                                    commandSequenceBeans.set(last, bean);
                                }
                                //设置案件环节
                                incidentProcessStepBeans = new ArrayList<>();
                                for (int i = 0; i < statusChangeRecordEntities.size() - 1; i++) {
                                    IncidentStatusChangeEntity entity = statusChangeRecordEntities.get(i);
                                    IncidentProcessStepBean stepBean = new IncidentProcessStepBean();
                                    //环节案件状态代码
                                    stepBean.setStatusCode(entity.getIncidentStatusCode());
                                    //环节案件状态名称
                                    stepBean.setStatusName(dicsMap.get("AJZT").get(FireTransformUtil.toString(entity.getIncidentStatusCode())) +
                                            "-" + dicsMap.get("AJZT").get(FireTransformUtil.toString(statusChangeRecordEntities.get(i + 1).getIncidentStatusCode())));
                                    //环节开始时间
                                    stepBean.setStartTime(entity.getChangeTime());
                                    //环节结束时间
                                    stepBean.setEndTime(statusChangeRecordEntities.get(i + 1).getChangeTime());
                                    //环节持续时间
                                    stepBean.setDuration(stepBean.getEndTime() - stepBean.getStartTime());
                                    //把环节添加进入总环节序列
                                    incidentProcessStepBeans.add(stepBean);
                                }
                                //案件是否结束
                                boolean isEnd;
                                isEnd = statusChangeRecordEntities.get(statusChangeRecordEntities.size() - 1).getIncidentStatusCode().equals(endStatusCode);
                                //如果案件没有结束，则做最后一个环节做处理
                                if (!isEnd) {
                                    IncidentStatusChangeEntity entity = statusChangeRecordEntities.get(statusChangeRecordEntities.size() - 1);
                                    IncidentProcessStepBean stepBean = new IncidentProcessStepBean();
                                    //环节案件状态代码
                                    stepBean.setStatusCode(entity.getIncidentStatusCode());
                                    //环节案件状态名称
                                    stepBean.setStatusName(dicsMap.get("AJZT").get(FireTransformUtil.toString(entity.getIncidentStatusCode())) + "-");
                                    //环节开始时间
                                    stepBean.setStartTime(entity.getChangeTime());
                                    //添加没有结束时间的环节
                                    incidentProcessStepBeans.add(stepBean);
                                }
                                //TODO
                                //时间节点
                                Map<Long, TimeNodeBean> mapNodes = new HashMap<>();
                                //包围圈
                                if (logger.isDebugEnabled()) {
                                    logger.debug(String.format("开始查询案件ID为：%s 的包围圈信息...", incidentId));
                                }
                                List<Object[]> surroundingCircles = incidentFireRepository.findAllSurroundingCircles(incidentId);
                                if (logger.isDebugEnabled()) {
                                    logger.debug(String.format("查询完毕，查询到 %s 条结果.", surroundingCircles.size()));
                                }
                                //设置包围圈
                                if (surroundingCircles != null && surroundingCircles.size() > 0) {
                                    for (Object[] circle : surroundingCircles) {
                                        Long circleTime = (Long) circle[0];
                                        TimeNodeBean nodeBean;
                                        if (mapNodes.containsKey(circleTime)) {
                                            nodeBean = mapNodes.get(circleTime);
                                        } else {
                                            nodeBean = new TimeNodeBean();
                                            //设置时间
                                            nodeBean.setTime(circleTime);
                                        }
                                        //设置包围圈半径
                                        nodeBean.setSurroundingCircleRadius((Double) circle[1]);
                                        //加入时间序列
                                        mapNodes.put(circleTime, nodeBean);
                                    }
                                }
                                //出动力量(车辆状态变动记录)
                                if (logger.isDebugEnabled()) {
                                    logger.debug(String.format("开始查询案件ID为：%s 的车辆变动记录...", incidentId));
                                }
                                List<Object[]> vehicles = incidentFireRepository.findVehicleStatusByIncident(incidentId);
                                if (logger.isDebugEnabled()) {
                                    logger.debug(String.format("查询完毕，共查询到 %s 数据", vehicles.size()));
                                }
                                //处理出动力量
                                if (vehicles != null && vehicles.size() > 0) {
                                    for (Object[] vehicle : vehicles) {
                                        VehiclePowerBean bean = new VehiclePowerBean();
                                        //车辆ID
                                        bean.setVehicleId((String) vehicle[0]);
                                        //车辆状态代码
                                        bean.setStatusCode((Integer) vehicle[1]);
                                        //车辆状态名称
                                        bean.setStatus(dicsMap.get("CLZT").get(FireTransformUtil.toString(vehicle[1])));
                                        //变动时间
                                        Long vehicleTime = (Long) vehicle[2];
                                        //车辆名称
                                        bean.setVehicleName((String) vehicle[3]);
                                        //车牌号
                                        bean.setVehicleNumber((String) vehicle[4]);
                                        //车辆点位编号
                                        bean.setLocationNumber((String) vehicle[5]);
                                        TimeNodeBean timeNodeBean;
                                        //判断是否已包含此节点，没有的话进行初始化
                                        if (mapNodes.containsKey(vehicleTime)) {
                                            timeNodeBean = mapNodes.get(vehicleTime);
                                        } else {
                                            timeNodeBean = new TimeNodeBean();
                                            //设置节点时间
                                            timeNodeBean.setTime(vehicleTime);
                                        }
                                        //初始化出动力量
                                        List<VehiclePowerBean> vehiclePowerBeans = timeNodeBean.getDispatchPower();
                                        if (vehiclePowerBeans == null) {
                                            vehiclePowerBeans = new ArrayList<>();
                                        }
                                        vehiclePowerBeans.add(bean);
                                        timeNodeBean.setDispatchPower(vehiclePowerBeans);
                                        //出动力量加入时间序列
                                        mapNodes.put(vehicleTime, timeNodeBean);
                                    }
                                }
                                //任务部署
                                if (logger.isDebugEnabled()) {
                                    logger.debug(String.format("开始查询案件ID为：%s 的任务部署记录...", incidentId));
                                }
                                List<RallyPointEntity> rallyPointEntities = rallyPointRepository.findRallyPoint( incidentId ) ;
                                if (logger.isDebugEnabled()) {
                                    logger.debug(String.format("查询完毕，查询到 %s 条结果.", rallyPointEntities.size()));
                                }
                                if (rallyPointEntities != null && rallyPointEntities.size() > 0) {
                                    //声明map，用于存放集结点
                                    Map<String, RallyPointBean> rallyPointBeanMap = new HashMap<>();
                                    for (RallyPointEntity entity : rallyPointEntities) {
                                        RallyPointBean bean = new RallyPointBean();
                                        //集结点ID
                                        bean.setRallyPointCode(entity.getId());
                                        //集结点名称
                                        bean.setRallyPointName(entity.getRallyPointName());
                                        //集结点类型
                                        bean.setRallyPointType(entity.getRallyPointType());
                                        //集结点描述
                                        bean.setRallyPointDesc(entity.getRallyPointDesc());
                                        //集结点经度
                                        bean.setLongitude(entity.getLongitude());
                                        //集结点纬度
                                        bean.setLatitude(entity.getLatitude());
                                        //集结点联系人
                                        bean.setRallyPointContacts(entity.getRallyPointContacts());
                                        //集结点联系人电话
                                        bean.setRallyPointPhone(entity.getRallyPointPhone());
                                        //设置集结点的时间
                                        bean.setRallyPointTime(entity.getCreatedTime());
                                        //集结点集结时间
                                        bean.setRallyPointTime(entity.getRallyPointTime());
                                        //加入集结点集合
                                        rallyPointBeanMap.put(bean.getRallyPointCode(), bean);
                                    }

                                    //把集结点加入时间节点
                                    for (String key : rallyPointBeanMap.keySet()) {
                                        //集结点
                                        RallyPointBean bean = rallyPointBeanMap.get(key);
                                        //时间节点
                                        TimeNodeBean timeNodeBean = mapNodes.get(bean.getRallyPointTime());
                                        if (timeNodeBean == null) {
                                            timeNodeBean = new TimeNodeBean();
                                            //设置时间
                                            timeNodeBean.setTime(bean.getRallyPointTime());
                                        }
                                        List<RallyPointBean> list = timeNodeBean.getTaskDeployment();
                                        if (list == null) {
                                            list = new ArrayList<>();
                                        }
                                        list.add(bean);
                                        //集结点设置进时间节点
                                        timeNodeBean.setTaskDeployment(list);
                                        //时间节点加入时间序列
                                        mapNodes.put(timeNodeBean.getTime(), timeNodeBean);
                                    }
                                }
                                //时间序列map转list
                                List<TimeNodeBean> timeNotes = new ArrayList<>(mapNodes.values());
                                //时间序列升序排序
                                timeNotes.sort(Comparator.comparing(TimeNodeBean::getTime));
                                //循环遍历把时间序列填充入案件环节中
                                for (TimeNodeBean note : timeNotes) {
                                    //取出环节下标
                                    int i = FireTransformUtil.findStatus(incidentProcessStepBeans, note.getTime());
                                    //如果该节点在环节范围中，把他设置进环节中
                                    if (i > -1) {
                                        IncidentProcessStepBean bean = incidentProcessStepBeans.get(i);
                                        List<TimeNodeBean> timeNodeBeans = bean.getTimeNode();
                                        if (timeNodeBeans == null) {
                                            timeNodeBeans = new ArrayList<>();
                                        }
                                        timeNodeBeans.add(note);
                                        bean.setTimeNode(timeNodeBeans);
                                        //更新环节
                                        incidentProcessStepBeans.set(i, bean);
                                    } else {
                                        if (logger.isDebugEnabled()) {
                                            logger.debug(String.format("时间节点异常,%s", note));
                                        }
                                    }
                                }

                                //把环节装配进指挥中
                                for (int i = 0; i < incidentProcessStepBeans.size(); i++) {
                                    IncidentProcessStepBean stepBean = incidentProcessStepBeans.get(i);
                                    int t=incidentProcessStepBeans.size()-1;
                                    if (i != incidentProcessStepBeans.size()-1) {
                                        int j = FireTransformUtil.commandIndex(commandSequenceBeans, stepBean.getStartTime(), stepBean.getEndTime(), isEnd);
                                        if (j != -1) {
                                            CommandSequenceBean command = commandSequenceBeans.get(j);
                                            //取出该指挥的环节列表
                                            List<IncidentProcessStepBean> stepBeans = command.getProcessSteps();
                                            if (stepBeans == null) {
                                                stepBeans = new ArrayList<>();
                                            }
                                            stepBeans.add(stepBean);
                                            command.setProcessSteps(stepBeans);
                                            //更新指挥
                                            commandSequenceBeans.set(j, command);
                                        }
                                    } else {
                                        //取出最后一个指挥
                                        CommandSequenceBean command = commandSequenceBeans.get(commandSequenceBeans.size() - 1);
                                        Boolean res;
                                        if (isEnd) {
                                            if (command.getStartCommandTime() <= stepBean.getStartTime() && command.getEndCommandTime() >= stepBean.getEndTime()) {
                                                res = true;
                                            } else {
                                                res = false;
                                            }
                                        } else {
                                            if (command.getStartCommandTime() <= stepBean.getStartTime()) {
                                                res = true;
                                            } else {
                                                res = false;
                                            }
                                        }
                                        if (res) {
                                            //取出该指挥的环节列表
                                            List<IncidentProcessStepBean> stepBeans = command.getProcessSteps();
                                            if (stepBeans == null) {
                                                stepBeans = new ArrayList<>();
                                            }
                                            stepBeans.add(stepBean);
                                            command.setProcessSteps(stepBeans);
                                            //更新指挥
                                            commandSequenceBeans.set(commandSequenceBeans.size() - 1, command);
                                        }
                                    }
                                }
                            }
                            //结束
                            replayBean.setCommands(commandSequenceBeans);
                            if (logger.isDebugEnabled()) {
                                logger.debug("回溯完毕");
                            }
                        }
                    } else {
                        if (logger.isDebugEnabled()) {
                            logger.debug(String.format("警情回溯失败，未查询到警情：%s 的信息.", incidentId));
                        }
                        throw new AcceptException(AcceptException.AccetpErrors.FIND_INCIDENTS_FAIL);
                    }
                }
                return replayBean;
            }
        } catch (UserInterfaceException ex) {
            if (logger.isErrorEnabled()) {
                logger.error(String.format("处置警情回溯出错，警情ID为:%s.", incidentId), ex);
            }
            throw ex;
        } catch (Exception ex) {
            if (logger.isErrorEnabled()) {
                logger.error(String.format("处置警情回溯出错，警情ID为:%s.", incidentId), ex);
            }
            throw new AcceptException(AcceptException.AccetpErrors.OTHER_FAIL);
        }
    }





}
