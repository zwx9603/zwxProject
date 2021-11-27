package com.dscomm.iecs.out.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dscomm.iecs.accept.dal.po.IncidentEntity;
import com.dscomm.iecs.accept.dal.po.SoundRecordEntity;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.out.dal.po.QueryAuditEntity;
import com.dscomm.iecs.out.dal.repository.SoundRecordOutRepository;
import com.dscomm.iecs.out.enums.InterfaceTypeEnum;
import com.dscomm.iecs.out.exception.OutException;
import com.dscomm.iecs.out.graphql.typebean.SoundRecordOutBean;
import com.dscomm.iecs.out.service.SoundRecordOutService;
import com.dscomm.iecs.out.utils.GetQueryAuditUtil;
import com.dscomm.iecs.out.utils.TimeUtils;
import com.dscomm.iecs.out.utils.TransformUtil;
import org.apache.commons.lang3.StringUtils;
import org.mx.dal.service.GeneralAccessor;
import org.mx.spring.session.SessionDataStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/4/20 19:04
 * @Describe
 */
@Component
public class SoundRecordOutServiceImpl implements SoundRecordOutService {

    private static final Logger logger = LoggerFactory.getLogger(SoundRecordOutServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private SessionDataStore dataStore;
    private SoundRecordOutRepository soundRecordOutRepository;

    @Autowired
    public SoundRecordOutServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor,

                                     SessionDataStore dataStore, SoundRecordOutRepository soundRecordOutRepository) {
        this.logService = logService;
        this.accessor = accessor;

        this.dataStore = dataStore;
        this.soundRecordOutRepository = soundRecordOutRepository;
    }


    /**
     * 根据时间获取录音列表
     *
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public List<SoundRecordOutBean> getSoundRecordListByTime(Long startTime, Long endTime, String username) {

        QueryAuditEntity queryAuditEntity = new QueryAuditEntity();
        try {

            logService.infoLog(logger, "service", "getAlarmJqlyListByTime", "service is started...");
            Long logStart = System.currentTimeMillis();
            GetQueryAuditUtil.getBeforeQueryAudit(username, InterfaceTypeEnum.JQLYList.getCode()
                    , "JCJ_LYJL", accessor, queryAuditEntity, logStart);
            List<SoundRecordOutBean> res = getSoundRecordListByTime(startTime, endTime);
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "getAlarmJqlyListByTime", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            GetQueryAuditUtil.getAfterQueryAudit("success", res.size(), queryAuditEntity, logEnd);
            return res;
        } catch (Exception ex) {
            GetQueryAuditUtil.getAfterQueryAudit("fail", 0, queryAuditEntity, System.currentTimeMillis());
            logService.erorLog(logger, "service", "getAlarmJqlyListByTime", "execution error", ex);
            throw new OutException(OutException.AccetpErrors.FIND_SOUND_RECORD_LIST_FAIL);
        } finally {
            saveAuditLog(queryAuditEntity);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveAuditLog(QueryAuditEntity queryAuditEntity) {
        accessor.save(queryAuditEntity);

    }

    @Transactional(readOnly = true)
    public List<SoundRecordOutBean> getSoundRecordListByTime(Long startTime, Long endTime) {
        if (startTime == null || endTime == null) {
            logService.infoLog(logger, "service", "getAlarmJqlyListByTime", "query time is Blank.");
            throw new OutException(OutException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "getAlarmJqlyListByTime", "service is started...");
            Long logStart = System.currentTimeMillis();

//            //创建一个list 存放ConditionGroup
//            List<GeneralAccessor.ConditionGroup> conditionGroupList = new ArrayList<>();
//            //创建一个大于等于条件  创建时间
//            conditionGroupList.add(GeneralAccessor.ConditionTuple.gte("createdTime", startTime));//查询开始时间
//
//            //创建一个小于等于条件  创建时间
//            conditionGroupList.add(GeneralAccessor.ConditionTuple.lte("createdTime", endTime));//查询结束时间
//            conditionGroupList.add(GeneralAccessor.ConditionTuple.eq("valid", 1));
//            GeneralAccessor.ConditionGroup conditionGroup = GeneralAccessor.ConditionGroup.and(conditionGroupList);
            Long start = System.currentTimeMillis();
//
//            List<SoundRecordEntity> entityList = accessor.find(conditionGroup, SoundRecordEntity.class);
            List<Object[]> result = soundRecordOutRepository.findSoundRecordEntitiesByTime(startTime, endTime);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "getAlarmJqlyListByTime", String.format("repository is finished,execute time is :%sms", end - start));
            String deptCode= (String) dataStore.get().get("deptCode");
            List<SoundRecordOutBean> res = new ArrayList<>();
            if (result != null && result.size() > 0) {
                for (Object[] objects : result
                ) {
                    SoundRecordEntity entity= (SoundRecordEntity) objects[0];
                    Long lasj= (Long) objects[1];
                    SoundRecordOutBean bean = TransformUtil.transform(entity);
                    bean.setDeptCode(deptCode);
                    bean.setLasj(TimeUtils.transformaStrTime(lasj));
                    res.add(bean);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "getAlarmJqlyListByTime", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {

            logService.erorLog(logger, "service", "getAlarmJqlyListByTime", "execution error", ex);
            throw new OutException(OutException.AccetpErrors.FIND_SOUND_RECORD_LIST_FAIL);
        }
    }
}
