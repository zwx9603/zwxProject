package com.dscomm.iecs.out.service.impl;

import com.dscomm.iecs.accept.dal.po.DocumentEntity;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.out.dal.po.QueryAuditEntity;
import com.dscomm.iecs.out.enums.InterfaceTypeEnum;
import com.dscomm.iecs.out.exception.OutException;
import com.dscomm.iecs.out.graphql.typebean.DocumentOutBean;
import com.dscomm.iecs.out.service.DocumentOutService;
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

import java.util.ArrayList;
import java.util.List;

@Component
public class DocumentOutServiceImpl implements DocumentOutService {
    private static final Logger logger = LoggerFactory.getLogger(DocumentOutServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private SessionDataStore dataStore;


    @Autowired
    public DocumentOutServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor, SessionDataStore dataStore) {
        this.logService = logService;
        this.accessor = accessor;

        this.dataStore = dataStore;
    }

    /**
     * 根据时间获取文书记录列表
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    @Override
    public List<DocumentOutBean> getAlarmJqwsListByTime(Long startTime, Long endTime, String username) {
        QueryAuditEntity queryAuditEntity = new QueryAuditEntity();
        try {
            logService.infoLog(logger, "service", "getAlarmJqwsListByTime", "service is started...");
            Long logStart = System.currentTimeMillis();
            GetQueryAuditUtil.getBeforeQueryAudit(username, InterfaceTypeEnum.JQWSXX.getCode()
                    , "JCJ_WSXX", accessor, queryAuditEntity, logStart);
            List<DocumentOutBean> res = getDocumentListByTime(startTime, endTime);
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "getAlarmJqwsListByTime", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            GetQueryAuditUtil.getAfterQueryAudit("success", res.size(), queryAuditEntity, logEnd);
            return res;

        } catch (Exception ex) {
            GetQueryAuditUtil.getAfterQueryAudit("fail", 0, queryAuditEntity, System.currentTimeMillis());
            logService.erorLog(logger, "service", "getAlarmJqwsListByTime", "execution error", ex);
            throw new OutException(OutException.AccetpErrors.FIND_DOC_RECORD_FAIL);
        } finally {
            saveAuditLog(queryAuditEntity);
        }

    }

    @Transactional(rollbackFor = Exception.class)
    public void saveAuditLog(QueryAuditEntity queryAuditEntity) {
        accessor.save(queryAuditEntity);

    }

    @Transactional(readOnly = true)
    public List<DocumentOutBean> getDocumentListByTime(Long startTime, Long endTime) {
        if (startTime == null || endTime == null) {
            logService.infoLog(logger, "service", "getAlarmJqwsListByTime", "query time is Blank.");
            throw new OutException(OutException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "getAlarmJqwsListByTime", "service is started...");
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

            List<DocumentEntity> entityList = accessor.find(conditionGroup, DocumentEntity.class);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "getAlarmJqwsListByTime", String.format("repository is finished,execute time is :%sms", end - start));
            String deptCode= (String) dataStore.get().get("deptCode");
            List<DocumentOutBean> res = new ArrayList<>();

            if (entityList != null && entityList.size() > 0) {
                for (DocumentEntity entity : entityList
                ) {
                    DocumentOutBean bean = new DocumentOutBean();
                    BeanUtils.copyProperties(entity, bean);
                    bean.setUid(entity.getId());
                    bean.setCreateTime(TimeUtils.transformaStrTime(entity.getCreatedTime()));
                    bean.setFeedbackTime(TimeUtils.transformaStrTime(entity.getFeedbackTime()));
                    bean.setRecordTime(TimeUtils.transformaStrTime(entity.getFeedbackTime()));
                    bean.setRecordPerson(entity.getFeedbackPerson());
                    if (StringUtils.isBlank(entity.getIdCode())) {
                        bean.setIdCode(entity.getId());
                    }
                    bean.setDeptCode(deptCode);
                    res.add(bean);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "getAlarmJqwsListByTime", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;

        } catch (Exception ex) {

            logService.erorLog(logger, "service", "getAlarmJqwsListByTime", "execution error", ex);
            throw new OutException(OutException.AccetpErrors.FIND_DOC_RECORD_FAIL);
        }
    }
}
