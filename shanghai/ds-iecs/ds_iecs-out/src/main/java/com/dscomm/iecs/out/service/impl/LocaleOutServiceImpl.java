package com.dscomm.iecs.out.service.impl;

import com.dscomm.iecs.accept.dal.po.LocaleEntity;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.out.dal.po.QueryAuditEntity;
import com.dscomm.iecs.out.enums.InterfaceTypeEnum;
import com.dscomm.iecs.out.exception.OutException;
import com.dscomm.iecs.out.graphql.typebean.LocaleOutBean;
import com.dscomm.iecs.out.graphql.typebean.SwryBean;
import com.dscomm.iecs.out.service.LocaleOutService;
import com.dscomm.iecs.out.utils.GetQueryAuditUtil;
import com.dscomm.iecs.out.utils.TimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.mx.dal.service.GeneralAccessor;
import org.mx.spring.session.SessionDataStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class LocaleOutServiceImpl implements LocaleOutService {
    private static final Logger logger = LoggerFactory.getLogger(LocaleOutServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private DictionaryService dictionaryService;
    private OrganizationService organizationService;
    private SessionDataStore dataStore;

    private List<String> dics;


    @Autowired
    public LocaleOutServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor,
                                DictionaryService dictionaryService,
                                OrganizationService organizationService, SessionDataStore dataStore) {
        this.logService = logService;
        this.accessor = accessor;
        this.dictionaryService = dictionaryService;
        this.organizationService = organizationService;
        this.dataStore = dataStore;
        dics = new ArrayList<>(Arrays.asList("XB", "XZQX", "BJFS", "LAFS", "AJLX", "AJLXZX", "AJDJ", "AJZT", "AJXZ", "CZDX", "ZDDWLX", "JZJG", "YWQK", "ZHCS", "JQDX"));

    }

    /**
     * 获取警情现场信息列表
     *
     * @param startTime
     * @param endTime
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<LocaleOutBean> getAlarmJqxcListByTime(Long startTime, Long endTime, String username) {
        QueryAuditEntity queryAuditEntity = new QueryAuditEntity();
        try {
            logService.infoLog(logger, "service", "getAlarmJqxcListByTime", "service is started...");
            Long logStart = System.currentTimeMillis();
            GetQueryAuditUtil.getBeforeQueryAudit(username, InterfaceTypeEnum.JQXCXX.getCode()
                    , "JCJ_XCXX", accessor, queryAuditEntity, logStart);
            List<LocaleOutBean> res = getLocaleListByTime(startTime, endTime);
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "getAlarmJqxcListByTime", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            GetQueryAuditUtil.getAfterQueryAudit("success", res.size(), queryAuditEntity, logEnd);
            return res;

        } catch (Exception ex) {
            GetQueryAuditUtil.getAfterQueryAudit("fail", 0, queryAuditEntity, System.currentTimeMillis());
            logService.erorLog(logger, "service", "getAlarmJqxcListByTime", "execution error", ex);
            throw new OutException(OutException.AccetpErrors.FIND_LOC_RECORD_FAIL);
        } finally {
            saveAuditLog(queryAuditEntity);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveAuditLog(QueryAuditEntity queryAuditEntity) {
        accessor.save(queryAuditEntity);

    }

    @Transactional(readOnly = true)
    public List<LocaleOutBean> getLocaleListByTime(Long startTime, Long endTime) {
        if (startTime == null || endTime == null) {
            logService.infoLog(logger, "service", "getAlarmJqxcListByTime", "query time is Blank.");
            throw new OutException(OutException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "getAlarmJqxcListByTime", "service is started...");
            Long logStart = System.currentTimeMillis();
            //创建一个list 存放ConditionGroup
            List<GeneralAccessor.ConditionGroup> conditionGroupList = new ArrayList<>();
            //创建一个大于等于条件  创建时间
            conditionGroupList.add(GeneralAccessor.ConditionTuple.gte("createdTime", startTime));//查询开始时间

            //创建一个小于等于条件  创建时间
            conditionGroupList.add(GeneralAccessor.ConditionTuple.lte("createdTime", endTime));//查询结束时间
            conditionGroupList.add(GeneralAccessor.ConditionTuple.eq("valid", 1));
            GeneralAccessor.ConditionGroup conditionGroup = GeneralAccessor.ConditionGroup.and(conditionGroupList);
            Long start = System.currentTimeMillis();

            List<LocaleEntity> entityList = accessor.find(conditionGroup, LocaleEntity.class);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "getAlarmJqxcListByTime", String.format("repository is finished,execute time is :%sms", end - start));

            List<LocaleOutBean> res = new ArrayList<>();
            String deptCode= (String) dataStore.get().get("deptCode");
            if (entityList != null && entityList.size() > 0) {
                for (LocaleEntity entity : entityList
                ) {
                    LocaleOutBean bean = new LocaleOutBean();
                    BeanUtils.copyProperties(entity, bean);
                    bean.setUid(entity.getId());
                    bean.setCreateTime(TimeUtils.transformaStrTime(entity.getCreatedTime()));
                    bean.setCollectionTime(TimeUtils.transformaStrTime(entity.getCollectionTime()));
                    bean.setRecordPerson(entity.getOperatorPerson());
                    if (StringUtils.isBlank(entity.getIdCode())) {
                        bean.setIdCode(entity.getId());
                    }
                    try {
                        if (StringUtils.isNotBlank(entity.getTrappedPersonNum())) {
                            bean.setTrappedPersonNum(Integer.valueOf(entity.getTrappedPersonNum()));
                        }
                    } catch (Exception ex) {
                        logService.infoLog(logger, "service", "getAlarmJqxcListByTime", String.format("the error id of table JCJ_XCXX is :", entity.getId()));
                        logService.infoLog(logger, "service", "getAlarmJqxcListByTime", String.format("the error column of table JCJ_XCXX is :", "BK_RS"));
                    }
                    try {
                        if (StringUtils.isNotBlank(entity.getAttendanceNum())) {
                            bean.setAttendanceNum(Integer.valueOf(entity.getAttendanceNum()));
                        }
                    } catch (Exception ex) {
                        logService.infoLog(logger, "service", "getAlarmJqxcListByTime", String.format("the error id of table JCJ_XCXX is :", entity.getId()));
                        logService.infoLog(logger, "service", "getAlarmJqxcListByTime", String.format("the error column of table JCJ_XCXX is :", "DC_RS"));
                    }
                    try {
                        if (StringUtils.isNotBlank(entity.getPressure())) {
                            bean.setPressure(Integer.valueOf(entity.getPressure()));
                        }
                    } catch (Exception ex) {
                        logService.infoLog(logger, "service", "getAlarmJqxcListByTime", String.format("the error id of table JCJ_XCXX is :", entity.getId()));
                        logService.infoLog(logger, "service", "getAlarmJqxcListByTime", String.format("the error column of table JCJ_XCXX is :", "DQ_YL"));
                    }
                    try {
                        if (StringUtils.isNotBlank(entity.getTroopsInjuredNum())) {
                            bean.setTroopsInjuredNum(Integer.valueOf(entity.getTroopsInjuredNum()));
                        }
                    } catch (Exception ex) {
                        logService.infoLog(logger, "service", "getAlarmJqxcListByTime", String.format("the error id of table JCJ_XCXX is :", entity.getId()));
                        logService.infoLog(logger, "service", "getAlarmJqxcListByTime", String.format("the error column of table JCJ_XCXX is :", "DWSS_RS"));
                    }
                    try {
                        if (StringUtils.isNotBlank(entity.getTroopsDeathNum())) {
                            bean.setTroopsDeathNum(Integer.valueOf(entity.getTroopsDeathNum()));
                        }
                    } catch (Exception ex) {
                        logService.infoLog(logger, "service", "getAlarmJqxcListByTime", String.format("the error id of table JCJ_XCXX is :", entity.getId()));
                        logService.infoLog(logger, "service", "getAlarmJqxcListByTime", String.format("the error column of table JCJ_XCXX is :", "DWSW_RS"));
                    }
                    try {
                        if (StringUtils.isNotBlank(entity.getTroopsOutContactNum())) {
                            bean.setTroopsOutContactNum(Integer.valueOf(entity.getTroopsOutContactNum()));
                        }
                    } catch (Exception ex) {
                        logService.infoLog(logger, "service", "getAlarmJqxcListByTime", String.format("the error id of table JCJ_XCXX is :", entity.getId()));
                        logService.infoLog(logger, "service", "getAlarmJqxcListByTime", String.format("the error column of table JCJ_XCXX is :", "DWSL_RS"));
                    }
                    try {
                        if (StringUtils.isNotBlank(entity.getMassOutContactNum())) {
                            bean.setMassOutContactNum(Integer.valueOf(entity.getMassOutContactNum()));
                        }
                    } catch (Exception ex) {
                        logService.infoLog(logger, "service", "getAlarmJqxcListByTime", String.format("the error id of table JCJ_XCXX is :", entity.getId()));
                        logService.infoLog(logger, "service", "getAlarmJqxcListByTime", String.format("the error column of table JCJ_XCXX is :", "QZSL_RS"));
                    }
                    try {
                        if (StringUtils.isNotBlank(entity.getMassDeathNum())) {
                            bean.setMassDeathNum(Integer.valueOf(entity.getMassDeathNum()));
                        }
                    } catch (Exception ex) {
                        logService.infoLog(logger, "service", "getAlarmJqxcListByTime", String.format("the error id of table JCJ_XCXX is :", entity.getId()));
                        logService.infoLog(logger, "service", "getAlarmJqxcListByTime", String.format("the error column of table JCJ_XCXX is :", "QZSW_RS"));
                    }
                    try {
                        if (StringUtils.isNotBlank(entity.getMassInjuredNum())) {
                            bean.setMassInjuredNum(Integer.valueOf(entity.getMassInjuredNum()));
                        }
                    } catch (Exception ex) {
                        logService.infoLog(logger, "service", "getAlarmJqxcListByTime", String.format("the error id of table JCJ_XCXX is :", entity.getId()));
                        logService.infoLog(logger, "service", "getAlarmJqxcListByTime", String.format("the error column of table JCJ_XCXX is :", "QZSS_RS"));
                    }
                    try {
                        if (StringUtils.isNotBlank(entity.getWindSpeed())) {
                            bean.setWindSpeed(NumberFormat.getInstance().parse(entity.getTrappedPersonNum()));
                        }
                    } catch (Exception ex) {
                        logService.infoLog(logger, "service", "getAlarmJqxcListByTime", String.format("the error id of table JCJ_XCXX is :", entity.getId()));
                        logService.infoLog(logger, "service", "getAlarmJqxcListByTime", String.format("the error column of table JCJ_XCXX is :", "F_SD"));
                    }
                    try {
                        if (StringUtils.isNotBlank(entity.getFireSitesTemperature())) {
                            bean.setFireSitesTemperature(NumberFormat.getInstance().parse(entity.getFireSitesTemperature()));
                        }
                    } catch (Exception ex) {
                        logService.infoLog(logger, "service", "getAlarmJqxcListByTime", String.format("the error id of table JCJ_XCXX is :", entity.getId()));
                        logService.infoLog(logger, "service", "getAlarmJqxcListByTime", String.format("the error column of table JCJ_XCXX is :", "HC_WD"));
                    }
                    try {
                        if (StringUtils.isNotBlank(entity.getWeatherTemperature())) {
                            bean.setWeatherTemperature(NumberFormat.getInstance().parse(entity.getWeatherTemperature()));
                        }
                    } catch (Exception ex) {
                        logService.infoLog(logger, "service", "getAlarmJqxcListByTime", String.format("the error id of table JCJ_XCXX is :", entity.getId()));
                        logService.infoLog(logger, "service", "getAlarmJqxcListByTime", String.format("the error column of table JCJ_XCXX is :", "KQ_WD"));
                    }
                    try {
                        if (StringUtils.isNotBlank(entity.getBurningArea())) {
                            bean.setBurningArea(NumberFormat.getInstance().parse(entity.getBurningArea()));
                        }
                    } catch (Exception ex) {
                        logService.infoLog(logger, "service", "getAlarmJqxcListByTime", String.format("the error id of table JCJ_XCXX is :", entity.getId()));
                        logService.infoLog(logger, "service", "getAlarmJqxcListByTime", String.format("the error column of table JCJ_XCXX is :", "RS_MJ"));
                    }
                    try {
                        if (StringUtils.isNotBlank(entity.getRelativeHumidity())) {
                            bean.setRelativeHumidity(NumberFormat.getInstance().parse(entity.getRelativeHumidity()));
                        }
                    } catch (Exception ex) {
                        logService.infoLog(logger, "service", "getAlarmJqxcListByTime", String.format("the error id of table JCJ_XCXX is :", entity.getId()));
                        logService.infoLog(logger, "service", "getAlarmJqxcListByTime", String.format("the error column of table JCJ_XCXX is :", "XDSD"));
                    }
                    List<SwryBean> swryList = new ArrayList<>();
                    bean.setSwryList(swryList);
                    bean.setDeptCode(deptCode);
                    res.add(bean);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "getAlarmJqxcListByTime", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;

        } catch (Exception ex) {
            logService.erorLog(logger, "service", "getAlarmJqxcListByTime", "execution error", ex);
            throw new OutException(OutException.AccetpErrors.FIND_LOC_RECORD_FAIL);
        }
    }
}
