package com.dscomm.iecs.out.service.impl;

import com.dscomm.iecs.accept.dal.po.SoundRecordEntity;
import com.dscomm.iecs.accept.dal.po.TelephoneEntity;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.out.dal.po.QueryAuditEntity;
import com.dscomm.iecs.out.dal.repository.SoundRecordOutRepository;
import com.dscomm.iecs.out.enums.InterfaceTypeEnum;
import com.dscomm.iecs.out.exception.OutException;
import com.dscomm.iecs.out.graphql.typebean.TelephoneOutBean;
import com.dscomm.iecs.out.service.TelephoneOutService;
import com.dscomm.iecs.out.utils.GetQueryAuditUtil;
import com.dscomm.iecs.out.utils.TimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.mx.dal.service.GeneralAccessor;
import org.mx.spring.session.SessionDataStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class TelephoneOutServiceImpl implements TelephoneOutService {
    private static final Logger logger = LoggerFactory.getLogger(TelephoneOutServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private DictionaryService dictionaryService;
    private OrganizationService organizationService;
    private SessionDataStore sessionDataStore;
    private SoundRecordOutRepository soundRecordOutRepository;

    private List<String> dics;


    @Autowired
    public TelephoneOutServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor,
                                   DictionaryService dictionaryService,
                                   OrganizationService organizationService, SessionDataStore sessionDataStore,
                                   SoundRecordOutRepository soundRecordOutRepository) {
        this.logService = logService;
        this.accessor = accessor;
        this.dictionaryService = dictionaryService;
        this.organizationService = organizationService;
        this.sessionDataStore = sessionDataStore;
        this.soundRecordOutRepository = soundRecordOutRepository;
        dics = new ArrayList<>(Arrays.asList("XB", "XZQX", "BJFS", "LAFS", "AJLX", "AJLXZX", "AJDJ", "AJZT", "AJXZ", "CZDX", "ZDDWLX", "JZJG", "YWQK", "ZHCS", "JQDX"));

    }


    /**
     * 通话记录
     *
     * @param startTime
     * @param endTime
     * @return
     */

    @Override
    public List<TelephoneOutBean> getAlarmThjlListByTime(Long startTime, Long endTime, String username) {
        QueryAuditEntity queryAuditEntity = new QueryAuditEntity();
        if (startTime == null || endTime == null) {
            logService.infoLog(logger, "service", "getAlarmThjlListByTime", "query time is Blank.");
            throw new OutException(OutException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "getAlarmThjlListByTime", "service is started...");
            Long logStart = System.currentTimeMillis();
            GetQueryAuditUtil.getBeforeQueryAudit(username, InterfaceTypeEnum.THJLList.getCode()
                    , "JCJ_DHBJXX", accessor, queryAuditEntity, logStart);
            List<TelephoneOutBean> res = getTelephoneListByTime(startTime, endTime);
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "getAlarmThjlListByTime", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            GetQueryAuditUtil.getAfterQueryAudit("success", res.size(), queryAuditEntity, logEnd);
            return res;

        } catch (Exception ex) {
            GetQueryAuditUtil.getAfterQueryAudit("fail", 0, queryAuditEntity, System.currentTimeMillis());
            logService.erorLog(logger, "service", "getAlarmThjlListByTime", "execution error", ex);
            throw new OutException(OutException.AccetpErrors.FIND_TEL_RECORD_FAIL);
        } finally {
            saveAuditLog(queryAuditEntity);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveAuditLog(QueryAuditEntity queryAuditEntity) {
        accessor.save(queryAuditEntity);

    }

    @Transactional(readOnly = true)
    public List<TelephoneOutBean> getTelephoneListByTime(Long startTime, Long endTime) {
        if (startTime == null || endTime == null) {
            logService.infoLog(logger, "service", "getAlarmThjlListByTime", "query time is Blank.");
            throw new OutException(OutException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "getAlarmThjlListByTime", "service is started...");
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

            List<TelephoneEntity> entityList = accessor.find(conditionGroup, TelephoneEntity.class);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "getAlarmThjlListByTime", String.format("repository is finished,execute time is :%sms", end - start));

            List<TelephoneOutBean> res = new ArrayList<>();
            String deptCode= (String) sessionDataStore.get().get("deptCode");
            if (entityList != null && entityList.size() > 0) {
                for (TelephoneEntity entity : entityList
                ) {
                    TelephoneOutBean bean = new TelephoneOutBean();
                    BeanUtils.copyProperties(entity, bean);
                    bean.setUid(entity.getId());
                    bean.setCreateTime(TimeUtils.transformaStrTime(entity.getCreatedTime()));
                    bean.setAnswerTime(TimeUtils.transformaStrTime(entity.getAnswerTime()));
                    bean.setEndTime(TimeUtils.transformaStrTime(entity.getEndTime()));
                    bean.setRingingTime(TimeUtils.transformaStrTime(entity.getRingingTime()));

                    String relayRecordNumber = entity.getRelayRecordNumber();//录音号
                    if (Strings.isNotBlank(relayRecordNumber)){
                        List<SoundRecordEntity> soundRecordEntities =
                                soundRecordOutRepository.findSoundRecordByRecordNo(relayRecordNumber);
                        if (soundRecordEntities!=null&&soundRecordEntities.size()>0){
                            SoundRecordEntity soundRecordEntity  =soundRecordEntities.get(0);
                            bean.setRelayRecordName(soundRecordEntity.getFilename());
                            bean.setRelayRecordUrl(soundRecordEntity.getRecordUrl());
                            bean.setRelayRecorCode("9");//其他
                        }
                    }
                    if (StringUtils.isBlank(entity.getIdCode())) {
                        bean.setIdCode(entity.getId());
                    }
                    bean.setDeptCode(deptCode);
                    res.add(bean);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "getAlarmThjlListByTime", String.format("service is finished,execute time is :%sms", logEnd - logStart));


            return res;

        } catch (Exception ex) {

            logService.erorLog(logger, "service", "getAlarmThjlListByTime", "execution error", ex);
            throw new OutException(OutException.AccetpErrors.FIND_TEL_RECORD_FAIL);
        }
    }
}
