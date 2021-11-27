package com.dscomm.iecs.accept.service.impl;


import com.dscomm.iecs.accept.dal.po.EarlyWarningEntity;
import com.dscomm.iecs.accept.dal.repository.EarlyWarningRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.EarlyWarningListSaveInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.EarlyWarningQueryInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.EarlyWarningSaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.EarlyWarningBean;
import com.dscomm.iecs.accept.hangzhou.service.TTSSpeechService;
import com.dscomm.iecs.accept.service.TTSCacheService;
import com.dscomm.iecs.accept.service.EarlyWarningService;
import com.dscomm.iecs.accept.utils.transform.IncidentTransformUtil;
import com.dscomm.iecs.base.enums.EnableEnum;
import com.dscomm.iecs.base.service.NotifyActionService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.base.utils.DistanceUtils;
import com.dscomm.iecs.base.websocket.WebsocketCodeEnum;
import com.dscomm.iecs.basedata.graphql.typebean.OrganizationBean;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.OrganizationService;
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
 * 描述：预警信息 服务实现类
 */
@Component("earlyWarningServiceImpl")
public class EarlyWarningServiceImpl implements EarlyWarningService {
    private static final Logger logger = LoggerFactory.getLogger(EarlyWarningServiceImpl.class);
    private Environment env;
    private LogService logService;
    private GeneralAccessor accessor;
    private DictionaryService dictionaryService;
    private OrganizationService organizationService;
    private EarlyWarningRepository earlyWarningRepository;
    private ServletService servletService;
    private NotifyActionService notifyActionService;

    private TTSSpeechService ttsSpeechService;
    private TTSCacheService TTSCacheService;
//    private Map<String,String> ttsSpeakToFileMap = new ConcurrentHashMap<>();

    private List<String> dics;

    /**
     * 默认的构造函数
     */
    @Autowired
    public EarlyWarningServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor,
                                   OrganizationService organizationService,
                                   Environment env, DictionaryService dictionaryService,
                                   EarlyWarningRepository earlyWarningRepository,
                                   ServletService servletService,
                                   NotifyActionService notifyActionService,
                                   TTSSpeechService ttsSpeechService,
                                   TTSCacheService TTSCacheService) {
        this.env = env;
        this.accessor = accessor;
        this.logService = logService;
        this.dictionaryService = dictionaryService;
        this.earlyWarningRepository = earlyWarningRepository;
        this.organizationService = organizationService;
        this.servletService = servletService;
        this.notifyActionService = notifyActionService;

        this.ttsSpeechService = ttsSpeechService;
        this.TTSCacheService = TTSCacheService;

        dics = new ArrayList<>(Arrays.asList("XZQX", "AJLX", "AJLXZX", "AJDJ"));
    }

    /**
     * {@inheritDoc}
     *
     * @see EarlyWarningService#saveEarlyWarning(EarlyWarningListSaveInputInfo)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<EarlyWarningBean> saveEarlyWarning(EarlyWarningListSaveInputInfo inputInfo) {
        if (inputInfo == null) {
            logService.infoLog(logger, "service", "saveEarlyWarning", "EarlyWarningListSaveInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveEarlyWarning", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<EarlyWarningBean> res = new ArrayList<>();

            List<EarlyWarningEntity> earlyWarningEntityList = new ArrayList<>();

            if (inputInfo.getEarlyWarningList() != null && inputInfo.getEarlyWarningList().size() > 0) {
                for (EarlyWarningSaveInputInfo earlyWarningSaveInputInfo : inputInfo.getEarlyWarningList()) {
                    List<EarlyWarningEntity> earlyWarningList =null;
                    if (!"2".equals(earlyWarningSaveInputInfo.getEarlyWarningType())){
                        earlyWarningList =earlyWarningRepository.findEarlyWarningByIncidentIdAndOrganizationId(earlyWarningSaveInputInfo.getIncidentId(), earlyWarningSaveInputInfo.getReceiveOrganizationId());
                    }

                    if (earlyWarningList == null || earlyWarningList.size() < 1) {
                        EarlyWarningEntity earlyWarningEntity = IncidentTransformUtil.transform(earlyWarningSaveInputInfo);
                        earlyWarningEntity.setSendTime(servletService.getSystemTime());

                        earlyWarningEntityList.add(earlyWarningEntity);
                    }
                }
            }

            if (null != earlyWarningEntityList && earlyWarningEntityList.size() > 0) {
                logService.infoLog(logger, "repository", "save(dbEarlyWarningEntityList)", "repository is started...");
                Long start = System.currentTimeMillis();

                accessor.save(earlyWarningEntityList);

                Long end = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbEarlyWarningEntityList)", String.format("repository is finished,execute time is :%sms", end - start));

            }

            //字典信息
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
            if (earlyWarningEntityList != null && earlyWarningEntityList.size() > 0) {
                Set<String> orgs = new HashSet<>();
                for (EarlyWarningEntity earlyWarningEntity : earlyWarningEntityList) {
                    EarlyWarningBean earlyWarningBean = IncidentTransformUtil.transform(earlyWarningEntity, dicsMap, organizationService.findOrganizationNameMap());
                    OrganizationBean organizationBean = organizationService.findOrganizationByOrganizationId(earlyWarningBean.getReceiveOrganizationId());
                    if (organizationBean != null) {
                        earlyWarningBean.setReceiveOrganizationDispatchPhone(organizationBean.getDispatchPhone());
                        earlyWarningBean.setReceiveOrganizationMailCode(organizationBean.getMailCode());
                        earlyWarningBean.setReceiveOrganizationFaxNumber(organizationBean.getFaxNumber());
                        earlyWarningBean.setReceiveOrganizationContactPerson(organizationBean.getContactPerson());
                        earlyWarningBean.setReceiveOrganizationContactPhone(organizationBean.getContactPhone());

                        earlyWarningBean.setOrganizationLongitude(organizationBean.getLongitude());
                        earlyWarningBean.setOrganizationLatitude(organizationBean.getLatitude());
                        earlyWarningBean.setOrganizationHeight(organizationBean.getHeight());

                        double longitude1 = Strings.isBlank(earlyWarningBean.getLongitude()) ? 0.0d : Double.parseDouble(earlyWarningBean.getLongitude());
                        double latitude1 = Strings.isBlank(earlyWarningBean.getLatitude()) ? 0.0d : Double.parseDouble(earlyWarningBean.getLatitude());

                        double longitude2 = Strings.isBlank(organizationBean.getLongitude()) ? 0.0d : Double.parseDouble(organizationBean.getLongitude());
                        double latitude2 = Strings.isBlank(organizationBean.getLatitude()) ? 0.0d : Double.parseDouble(organizationBean.getLatitude());
                        double distance = DistanceUtils.getDistance(longitude1, latitude1, longitude2, latitude2);

                        earlyWarningBean.setDistance(distance);

                    }
                    res.add(earlyWarningBean);
                }
            }

            //预警信息 发送websocket 消息
            bathNotifyAction(res);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveEarlyWarning", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveEarlyWarning", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_EARLY_WARNING_FAIL);
        }
    }


    /**
     * 分批次推送预警警信息
     */
    private void bathNotifyAction(List<EarlyWarningBean> earlyWarningsBeanList) {
        if (earlyWarningsBeanList != null && earlyWarningsBeanList.size() > 0) {

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {

                    for (EarlyWarningBean earlyWarningBean : earlyWarningsBeanList) {
                        Set<String> orgs = new HashSet<>();
                        List<String> orgIds = new ArrayList<>();
                        orgIds.add(earlyWarningBean.getReceiveOrganizationId());
//                        List<String> parentOrganizationId = organizationService.findParentOrganizationIds(orgIds);
//                        orgs.addAll(parentOrganizationId);
//                        List<String> orgCodes = organizationService.findOrganizationCodesByIds(new ArrayList<>(parentOrganizationId));
                        List<String> orgCodes = organizationService.findOrganizationCodesByIds(new ArrayList<>(orgIds));
                        orgs.addAll(orgCodes);
                        orgs.addAll(orgIds);

                        //生成 tts播报路径
                        String speakToFileUrl = ttsSpeechService.buildSpeakToFile(earlyWarningBean);
                        earlyWarningBean.setSpeakToFileUrl(speakToFileUrl);

                        TTSCacheService.modifyEarlyWarningSpeakFileCache("put",earlyWarningBean.getId(), speakToFileUrl);

                        notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.SAVE_EARLY_WARNING.getCode(), earlyWarningBean, orgs);

                    }
                }
            });
            thread.start();
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see EarlyWarningService#changeEarlyWarningStatus(List)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean changeEarlyWarningStatus(List<String> earlyWarningIds) {
        if (null == earlyWarningIds || earlyWarningIds.size() < 1) {
            logService.infoLog(logger, "service", "changeEarlyWarningStatus", "earlyWarningIds  is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "changeEarlyWarningStatus", "service is started...");
            Long logStart = System.currentTimeMillis();

            logService.infoLog(logger, "repository", "findEarlyWarning(earlyWarningIds)", "repository is started...");
            Long startFind = System.currentTimeMillis();

            List<EarlyWarningEntity> entityList = earlyWarningRepository.findEarlyWarning(earlyWarningIds);

            Long endFind = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findEarlyWarning(earlyWarningIds)", String.format("repository is finished,execute time is :%sms", endFind - startFind));

            if (entityList != null) {
                for (EarlyWarningEntity entity : entityList) {
                    entity.setEarlyWarningStatus(String.valueOf(EnableEnum.ENABLE_TRUE.getCode()));
                }

                logService.infoLog(logger, "repository", "save(dbEarlyWarningEntityList)", "repository is started...");
                Long start = System.currentTimeMillis();

                accessor.save(entityList);

                Long end = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbEarlyWarningEntityList)", String.format("repository is finished,execute time is :%sms", end - start));

                //消息通知发送机构
                Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
                Set<String> orgs = new HashSet<>();
                for (EarlyWarningEntity entity : entityList) {
                    EarlyWarningBean earlyWarningBean = IncidentTransformUtil.transform(entity, dicsMap, organizationService.findOrganizationNameMap());
                    orgs.clear();

                    List<String> orgIds = new ArrayList<>();
                    orgIds.add(earlyWarningBean.getReceiveOrganizationId());
                    orgIds.add(earlyWarningBean.getSendOrganizationId());
                    List<String> parentOrganizationId = organizationService.findParentOrganizationIds(orgIds);
                    orgs.addAll(parentOrganizationId);

                    List<String> orgCodes = organizationService.findOrganizationCodesByIds(new ArrayList<>(parentOrganizationId));

                    orgs.addAll(orgCodes);
                    notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.CHANGE_EARLY_WARNING_STATUS.getCode(), earlyWarningBean, orgs);
                }
            }


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "changeEarlyWarningStatus", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return true;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "changeEarlyWarningStatus", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.CHANGE_EARLY_WARNING_STATUS_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see EarlyWarningService#findEarlyWarning(EarlyWarningQueryInputInfo)
     */
    @Transactional(readOnly = true)
    @Override
    public List<EarlyWarningBean> findEarlyWarning(EarlyWarningQueryInputInfo inputInfo) {
        if (inputInfo == null || Strings.isBlank(inputInfo.getIncidentId())) {
            logService.infoLog(logger, "service", "removeEarlyWarning", "EarlyWarningQueryInputInfo or incidentId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "removeEarlyWarning", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<EarlyWarningBean> res = new ArrayList<>();

            List<String> receiveOrganizationIds = new ArrayList<>();
            if (Strings.isNotBlank(inputInfo.getReceiveOrganizationId())) {
                receiveOrganizationIds.add(inputInfo.getReceiveOrganizationId());
            }


            logService.infoLog(logger, "repository", "findEarlyWarning", "repository is started...");
            Long startFind = System.currentTimeMillis();

            List<EarlyWarningEntity> earlyWarningEntityList = earlyWarningRepository.findEarlyWarning(inputInfo.getIncidentId(), inputInfo.getEarlyWarningType(), receiveOrganizationIds);

            Long endFind = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findEarlyWarning", String.format("repository is finished,execute time is :%sms", endFind - startFind));

            //字典信息
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
            if (earlyWarningEntityList != null && earlyWarningEntityList.size() > 0) {
                for (EarlyWarningEntity earlyWarningEntity : earlyWarningEntityList) {
                    EarlyWarningBean earlyWarningBean = IncidentTransformUtil.transform(earlyWarningEntity, dicsMap, organizationService.findOrganizationNameMap());
                    OrganizationBean organizationBean = organizationService.findOrganizationByOrganizationId(earlyWarningBean.getReceiveOrganizationId());
                    if (organizationBean != null) {
                        earlyWarningBean.setReceiveOrganizationDispatchPhone(organizationBean.getDispatchPhone());
                        earlyWarningBean.setReceiveOrganizationMailCode(organizationBean.getMailCode());
                        earlyWarningBean.setReceiveOrganizationFaxNumber(organizationBean.getFaxNumber());
                        earlyWarningBean.setReceiveOrganizationContactPerson(organizationBean.getContactPerson());
                        earlyWarningBean.setReceiveOrganizationContactPhone(organizationBean.getContactPhone());

                        double longitude1 = Strings.isBlank(earlyWarningBean.getLongitude()) ? 0.0d : Double.parseDouble(earlyWarningBean.getLongitude());
                        double latitude1 = Strings.isBlank(earlyWarningBean.getLatitude()) ? 0.0d : Double.parseDouble(earlyWarningBean.getLatitude());

                        double longitude2 = Strings.isBlank(organizationBean.getLongitude()) ? 0.0d : Double.parseDouble(organizationBean.getLongitude());
                        double latitude2 = Strings.isBlank(organizationBean.getLatitude()) ? 0.0d : Double.parseDouble(organizationBean.getLatitude());
                        double distance = DistanceUtils.getDistance(longitude1, latitude1, longitude2, latitude2);

                        earlyWarningBean.setDistance(distance);
                    }
                    res.add(earlyWarningBean);
                }
            }

            //对距离排序
            res.sort(new Comparator<EarlyWarningBean>() {
                @Override
                public int compare(EarlyWarningBean o1, EarlyWarningBean o2) {
                    Double d1 = o1.getDistance();
                    Double d2 = o2.getDistance();
                    return d1.compareTo(d2);
                }
            });

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "removeEarlyWarning", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "removeEarlyWarning", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.REMOVE_EARLY_WARNING_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see EarlyWarningService#removeEarlyWarning(String, String, List)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean removeEarlyWarning(String incidentId, String earlyWarningType, List<String> organizationIds) {
        if (Strings.isBlank(incidentId)) {
            logService.infoLog(logger, "service", "removeEarlyWarning", "incidentId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "removeEarlyWarning", "service is started...");
            Long logStart = System.currentTimeMillis();

            logService.infoLog(logger, "repository", "findEarlyWarning", "repository is started...");
            Long startFind = System.currentTimeMillis();

            List<EarlyWarningEntity> entityList = earlyWarningRepository.findEarlyWarning(incidentId, earlyWarningType, organizationIds);

            Long endFind = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findEarlyWarning", String.format("repository is finished,execute time is :%sms", endFind - startFind));

            if (entityList != null) {
                for (EarlyWarningEntity entity : entityList) {
                    entity.setRevokeTime(servletService.getSystemTime());
                    entity.setValid(false);
                }

                logService.infoLog(logger, "repository", "save(dbEarlyWarningEntityList)", "repository is started...");
                Long start = System.currentTimeMillis();

                accessor.save(entityList);

                Long end = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbEarlyWarningEntityList)", String.format("repository is finished,execute time is :%sms", end - start));


                //消息通知
                Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);

                for (EarlyWarningEntity entity : entityList) {
                    Set<String> orgs = new HashSet<>();
                    List<String> orgIds = new ArrayList<>();
                    EarlyWarningBean earlyWarningBean = IncidentTransformUtil.transform(entity, dicsMap, organizationService.findOrganizationNameMap());
                    orgIds.add(earlyWarningBean.getReceiveOrganizationId());

//                    List<String> parentOrganizationId = organizationService.findParentOrganizationIds(orgIds);
//                    orgs.addAll(parentOrganizationId);

                    List<String> orgCodes = organizationService.findOrganizationCodesByIds(new ArrayList<>(orgIds));
                    orgs.addAll(orgCodes);
                    notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.REMOVE_EARLY_WARNING.getCode(), earlyWarningBean, orgs);
//                    }
                }
            }


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "removeEarlyWarning", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return true;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "removeEarlyWarning", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.REMOVE_EARLY_WARNING_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see #findEarlyWarningOrganizationIds(String)
     */
    @Transactional(readOnly = true)
    @Override
    public List<String> findEarlyWarningOrganizationIds(String incidentId) {
        if (Strings.isBlank(incidentId)) {
            logService.infoLog(logger, "service", "findEarlyWarningOrganizationIds", "incidentId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findEarlyWarningOrganizationIds", "service is started...");
            Long logStart = System.currentTimeMillis();


            logService.infoLog(logger, "repository", "findEarlyWarningOrganizationByIncidentId", "repository is started...");
            Long start = System.currentTimeMillis();

            List<String> res = earlyWarningRepository.findEarlyWarningOrganizationByIncidentId(incidentId);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findEarlyWarningOrganizationByIncidentId", String.format("repository is finished,execute time is :%sms", end - start));


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findEarlyWarningOrganizationIds", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findEarlyWarningOrganizationIds", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_EARLY_WARNING_ORGANIZATION_FAIL);
        }

    }


    /**
     * {@inheritDoc}
     *
     * @see EarlyWarningService#findEarlyWarningByIncidentId(String)
     */
    @Transactional(readOnly = true)
    @Override
    public List<EarlyWarningBean> findEarlyWarningByIncidentId(String incidentId) {
        if (Strings.isBlank(incidentId)) {
            logService.infoLog(logger, "service", "findEarlyWarningByIncidentId", "EarlyWarningQueryInputInfo or incidentId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findEarlyWarningByIncidentId", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<EarlyWarningBean> res = new ArrayList<>();

            logService.infoLog(logger, "repository", "findEarlyWarningIncidentId", "repository is started...");
            Long startFind = System.currentTimeMillis();

            List<EarlyWarningEntity> earlyWarningEntityList = earlyWarningRepository.findEarlyWarningIncidentId(incidentId);

            Long endFind = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findEarlyWarningIncidentId", String.format("repository is finished,execute time is :%sms", endFind - startFind));

            //字典信息
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
            if (earlyWarningEntityList != null && earlyWarningEntityList.size() > 0) {
                for (EarlyWarningEntity earlyWarningEntity : earlyWarningEntityList) {
                    EarlyWarningBean earlyWarningBean = IncidentTransformUtil.transform(earlyWarningEntity, dicsMap, organizationService.findOrganizationNameMap());
                    OrganizationBean organizationBean = organizationService.findOrganizationByOrganizationId(earlyWarningBean.getReceiveOrganizationId());
                    if (organizationBean != null) {
                        earlyWarningBean.setReceiveOrganizationDispatchPhone(organizationBean.getDispatchPhone());
                        earlyWarningBean.setReceiveOrganizationMailCode(organizationBean.getMailCode());
                        earlyWarningBean.setReceiveOrganizationFaxNumber(organizationBean.getFaxNumber());
                        earlyWarningBean.setReceiveOrganizationContactPerson(organizationBean.getContactPerson());
                        earlyWarningBean.setReceiveOrganizationContactPhone(organizationBean.getContactPhone());

                        double longitude1 = Strings.isBlank(earlyWarningBean.getLongitude()) ? 0.0d : Double.parseDouble(earlyWarningBean.getLongitude());
                        double latitude1 = Strings.isBlank(earlyWarningBean.getLatitude()) ? 0.0d : Double.parseDouble(earlyWarningBean.getLatitude());

                        double longitude2 = Strings.isBlank(organizationBean.getLongitude()) ? 0.0d : Double.parseDouble(organizationBean.getLongitude());
                        double latitude2 = Strings.isBlank(organizationBean.getLatitude()) ? 0.0d : Double.parseDouble(organizationBean.getLatitude());
                        double distance = DistanceUtils.getDistance(longitude1, latitude1, longitude2, latitude2);

                        earlyWarningBean.setDistance(distance);
                    }
                    res.add(earlyWarningBean);
                }
            }
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findEarlyWarningByIncidentId", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findEarlyWarningByIncidentId", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.REMOVE_EARLY_WARNING_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see EarlyWarningService#findEarlyWarningByIncidentIdAndOrganizationId(String, String)
     */
    @Transactional(readOnly = true)
    @Override
    public List<EarlyWarningBean> findEarlyWarningByIncidentIdAndOrganizationId(String incidentId, String organizationId) {
        if (Strings.isBlank(incidentId) || Strings.isBlank(organizationId)) {
            logService.infoLog(logger, "service", "findEarlyWarningByIncidentIdAndOrganizationId", "EarlyWarningQueryInputInfo or incidentId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findEarlyWarningByIncidentIdAndOrganizationId", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<EarlyWarningBean> res = new ArrayList<>();

            logService.infoLog(logger, "repository", "findEarlyWarningIncidentId", "repository is started...");
            Long startFind = System.currentTimeMillis();

            List<EarlyWarningEntity> earlyWarningEntityList = earlyWarningRepository.findEarlyWarningByIncidentIdAndOrganizationId(incidentId, organizationId);

            Long endFind = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findEarlyWarningByIncidentIdAndOrganizationId", String.format("repository is finished,execute time is :%sms", endFind - startFind));

            //字典信息
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
            if (earlyWarningEntityList != null && earlyWarningEntityList.size() > 0) {
                for (EarlyWarningEntity earlyWarningEntity : earlyWarningEntityList) {
                    EarlyWarningBean earlyWarningBean = IncidentTransformUtil.transform(earlyWarningEntity, dicsMap, organizationService.findOrganizationNameMap());
                    OrganizationBean organizationBean = organizationService.findOrganizationByOrganizationId(earlyWarningBean.getReceiveOrganizationId());
                    if (organizationBean != null) {
                        earlyWarningBean.setReceiveOrganizationDispatchPhone(organizationBean.getDispatchPhone());
                        earlyWarningBean.setReceiveOrganizationMailCode(organizationBean.getMailCode());
                        earlyWarningBean.setReceiveOrganizationFaxNumber(organizationBean.getFaxNumber());
                        earlyWarningBean.setReceiveOrganizationContactPerson(organizationBean.getContactPerson());
                        earlyWarningBean.setReceiveOrganizationContactPhone(organizationBean.getContactPhone());

                        double longitude1 = Strings.isBlank(earlyWarningBean.getLongitude()) ? 0.0d : Double.parseDouble(earlyWarningBean.getLongitude());
                        double latitude1 = Strings.isBlank(earlyWarningBean.getLatitude()) ? 0.0d : Double.parseDouble(earlyWarningBean.getLatitude());

                        double longitude2 = Strings.isBlank(organizationBean.getLongitude()) ? 0.0d : Double.parseDouble(organizationBean.getLongitude());
                        double latitude2 = Strings.isBlank(organizationBean.getLatitude()) ? 0.0d : Double.parseDouble(organizationBean.getLatitude());
                        double distance = DistanceUtils.getDistance(longitude1, latitude1, longitude2, latitude2);

                        earlyWarningBean.setDistance(distance);
                    }
                    res.add(earlyWarningBean);
                }
            }
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findEarlyWarningByIncidentIdAndOrganizationId", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findEarlyWarningByIncidentIdAndOrganizationId", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.REMOVE_EARLY_WARNING_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateEarlyWarningSpeakFile() {

        try {
            logService.infoLog(logger, "service", "updateEarlyWarningSpeakFile", "service is started...");
            Long logStart = System.currentTimeMillis();
            Map<String, String> ttsSpeakToFileMap = TTSCacheService.findAllEarlyWarningSpeakFileCache();
            if (ttsSpeakToFileMap != null && ttsSpeakToFileMap.size() > 0) {
                Set<String> earlyWarningIdSet = ttsSpeakToFileMap.keySet();
                List<String> handleOrganizationIds = new ArrayList<>();
                handleOrganizationIds.addAll(earlyWarningIdSet);
                handleOrganizationIds.remove(null);
                List<EarlyWarningEntity> earlyWarningEntityList =
                        earlyWarningRepository.findEarlyWarning(handleOrganizationIds);
                for (EarlyWarningEntity earlyWarningEntity : earlyWarningEntityList) {
                    String fileUrl = ttsSpeakToFileMap.get(earlyWarningEntity.getId());
                    earlyWarningEntity.setSpeakToFileUrl(fileUrl);
                }

                accessor.save(earlyWarningEntityList);

                for (String key : ttsSpeakToFileMap.keySet()) {
                    TTSCacheService.modifyEarlyWarningSpeakFileCache("remove",key,null);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "updateEarlyWarningSpeakFile", String.format("service is finished,execute time is :%sms", logEnd - logStart));


        } catch (Exception ex) {
            logService.erorLog(logger, "service", "updateEarlyWarningSpeakFile", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_EARLY_WARNING_FAIL);
        }

    }

}
