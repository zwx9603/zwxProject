package com.dscomm.iecs.out.service.impl;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.dal.po.EquipmentVehicleEntity;
import com.dscomm.iecs.basedata.graphql.typebean.SystemConfigurationBean;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.basedata.service.SystemConfigurationService;
import com.dscomm.iecs.out.dal.po.QueryAuditEntity;
import com.dscomm.iecs.out.enums.InterfaceTypeEnum;
import com.dscomm.iecs.out.exception.OutException;
import com.dscomm.iecs.out.graphql.typebean.VehicleOutBean;
import com.dscomm.iecs.out.service.VehicleOutService;
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
 * @Time 2021/4/20 11:47
 * @Describe
 */
@Component
public class VehicleOutServiceImpl implements VehicleOutService {

    private static final Logger logger = LoggerFactory.getLogger(VehicleOutServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private DictionaryService dictionaryService;
    private OrganizationService organizationService;
    private SessionDataStore dataStore;
    private SystemConfigurationService systemConfigurationService;

    private List<String> dics;

    @Autowired
    public VehicleOutServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor,
                                 DictionaryService dictionaryService,
                                 OrganizationService organizationService,
                                 SystemConfigurationService systemConfigurationService,
                                 SessionDataStore dataStore) {
        this.logService = logService;
        this.accessor = accessor;

        this.dictionaryService = dictionaryService;
        this.organizationService = organizationService;
        this.dataStore = dataStore;
        this.systemConfigurationService = systemConfigurationService;
        dics = new ArrayList<>(Arrays.asList("XB", "XZQX", "BJFS", "LAFS", "AJLX", "AJLXZX", "AJDJ", "AJZT", "AJXZ", "CZDX", "ZDDWLX", "JZJG", "YWQK", "ZHCS", "JQDX"));

    }


    /**
     * 根据时间获取车辆列表 (全量更新)
     *
     * @return
     */
    @Override
    public List<VehicleOutBean> getVehicleByTime(String username) {

        QueryAuditEntity queryAuditEntity = new QueryAuditEntity();
        try {
            logService.infoLog(logger, "service", "getAlarmJqclList", "service is started...");
            Long logStart = System.currentTimeMillis();
            GetQueryAuditUtil.getBeforeQueryAudit(username, InterfaceTypeEnum.JQXLList.getCode()
                    , "WL_CLXX", accessor, queryAuditEntity, logStart);
            List<VehicleOutBean> res = getVehicleListByTime();
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "getAlarmJqclList", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            GetQueryAuditUtil.getAfterQueryAudit("success", res.size(), queryAuditEntity, logEnd);
            return res;
        } catch (Exception ex) {
            GetQueryAuditUtil.getAfterQueryAudit("fail", 0, queryAuditEntity, System.currentTimeMillis());
            logService.erorLog(logger, "service", "getAlarmJqclList", "execution error", ex);
            throw new OutException(OutException.AccetpErrors.FIND_VEHICLE_LIST_FAIL);
        } finally {
            saveAuditLog(queryAuditEntity);
        }

    }

    @Transactional(rollbackFor = Exception.class)
    public void saveAuditLog(QueryAuditEntity queryAuditEntity) {
        accessor.save(queryAuditEntity);

    }

    @Transactional(readOnly = true)
    public List<VehicleOutBean> getVehicleListByTime() {
        try {
            logService.infoLog(logger, "service", "getAlarmJqclList", "service is started...");
            Long logStart = System.currentTimeMillis();

            //创建一个list 存放ConditionGroup
//            List<GeneralAccessor.ConditionGroup> conditionGroupList = new ArrayList<>();
//            //创建一个大于等于条件  创建时间
//            conditionGroupList.add(GeneralAccessor.ConditionTuple.gte("createdTime", startTime));//查询开始时间
//
//            //创建一个小于等于条件  创建时间
//            conditionGroupList.add(GeneralAccessor.ConditionTuple.lte("createdTime", endTime));//查询结束时间
//            GeneralAccessor.ConditionGroup conditionGroup = GeneralAccessor.ConditionGroup.and(conditionGroupList);
            Long start = System.currentTimeMillis();

            List<EquipmentVehicleEntity> entityList = accessor.find(null, EquipmentVehicleEntity.class);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "getAlarmJqclList", String.format("repository is finished,execute time is :%sms", end - start));

            // 查询出所有需要用到的字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
            // 查询机构id-名称缓存map
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();

            List<VehicleOutBean> res = new ArrayList<>();
            String deptCode= (String) dataStore.get().get("deptCode");
            //车辆所属省份 通过系统配置获取
            SystemConfigurationBean systemConfigurationBean = systemConfigurationService.getSystemConfigByConfigType(
                    "dsProxyNLPProv");
            if (entityList != null && entityList.size() > 0) {
                for (EquipmentVehicleEntity entity : entityList
                ) {
                    VehicleOutBean bean = TransformUtil.transform(entity, dicsMap, organizationNameMap, logService, logger);
                    bean.setDeptCode(deptCode);
                    if (systemConfigurationBean!=null) {
                        bean.setSssfmc(systemConfigurationBean.getConfigValue());
                    }
                    res.add(bean);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "getAlarmJqclList", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "getAlarmJqclList", "execution error", ex);
            throw new OutException(OutException.AccetpErrors.FIND_VEHICLE_LIST_FAIL);
        }
    }
}
