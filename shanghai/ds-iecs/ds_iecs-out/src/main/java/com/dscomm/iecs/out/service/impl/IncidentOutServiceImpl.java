package com.dscomm.iecs.out.service.impl;

import com.dscomm.iecs.accept.dal.po.IncidentEntity;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.out.dal.po.QueryAuditEntity;
import com.dscomm.iecs.out.enums.InterfaceTypeEnum;
import com.dscomm.iecs.out.exception.OutException;
import com.dscomm.iecs.out.graphql.typebean.IncidentOutBean;
import com.dscomm.iecs.out.service.IncidentOutService;
import com.dscomm.iecs.out.utils.GetQueryAuditUtil;
import com.dscomm.iecs.out.utils.TransformUtil;
import org.mx.dal.service.GeneralAccessor;
import org.mx.spring.session.SessionDataStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/4/19 19:46
 * @Describe
 */
@Component
public class IncidentOutServiceImpl implements IncidentOutService {

    private static final Logger logger = LoggerFactory.getLogger(IncidentOutServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private DictionaryService dictionaryService;
    private OrganizationService organizationService;
    private SessionDataStore dataStore;

    private List<String> dics;


    @Autowired
    public IncidentOutServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor,
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
     * 根据时间获取案件列表
     *
     * @param startTime
     * @param endTime
     * @return
     */

    @Override
    public List<IncidentOutBean> getIncidentListByTime(Long startTime, Long endTime, String username) {
        QueryAuditEntity queryAuditEntity = new QueryAuditEntity();
        try {
            logService.infoLog(logger, "service", "getIncidentListByTime", "service is started...");
            Long logStart = System.currentTimeMillis();
            GetQueryAuditUtil.getBeforeQueryAudit(username, InterfaceTypeEnum.JQXX.getCode()
                    , "JCJ_AJXX", accessor, queryAuditEntity, logStart);

            List<IncidentOutBean> res = getIncidentListByTime(startTime, endTime);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "getIncidentListByTime", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            GetQueryAuditUtil.getAfterQueryAudit("success", res.size(), queryAuditEntity, logEnd);
            return res;

        } catch (Exception ex) {
            GetQueryAuditUtil.getAfterQueryAudit("fail", 0, queryAuditEntity, System.currentTimeMillis());
            logService.erorLog(logger, "service", "getIncidentListByTime", "execution error", ex);
            throw new OutException(OutException.AccetpErrors.FIND_INCIDENT_LIST_FAIL);
        } finally {
            saveAuditLog(queryAuditEntity);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveAuditLog(QueryAuditEntity queryAuditEntity) {
        accessor.save(queryAuditEntity);

    }


    @Transactional(readOnly = true)
    public List<IncidentOutBean> getIncidentListByTime(Long startTime, Long endTime) {
        if (startTime == null || endTime == null) {
            logService.infoLog(logger, "service", "getIncidentListByTime", "query time is Blank.");
            throw new OutException(OutException.AccetpErrors.DATA_NULL);
        }
        try {

            logService.infoLog(logger, "service", "getIncidentListByTime", "service is started...");
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

            List<IncidentEntity> entityList = accessor.find(conditionGroup, IncidentEntity.class);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "getIncidentListByTime", String.format("repository is finished,execute time is :%sms", end - start));

            // 查询出所有需要用到的字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
            // 查询机构id-名称缓存map
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();
            String deptCode= (String) dataStore.get().get("deptCode");
            List<IncidentOutBean> res = new ArrayList<>();
            if (entityList != null && entityList.size() > 0) {
                for (IncidentEntity entity : entityList
                ) {
                    IncidentOutBean bean = TransformUtil.transform(entity, dicsMap, organizationNameMap, logService, logger);
                    bean.setDeptCode(deptCode);
                    res.add(bean);
                }
            }
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "getIncidentListByTime", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;

        } catch (Exception ex) {
            logService.erorLog(logger, "service", "getIncidentListByTime", "execution error", ex);
            throw new OutException(OutException.AccetpErrors.FIND_INCIDENT_LIST_FAIL);
        }
    }
}
