package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.LocationRecordEntity;
import com.dscomm.iecs.accept.dal.repository.LocationRecordRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.LocationRecordInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.LocationRecordBean;
import com.dscomm.iecs.accept.service.LocationRecordService;
import com.dscomm.iecs.accept.utils.transform.HandleDispatchTransformUtil;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.keydata.service.ServletService;
import org.mx.StringUtils;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class LocationRecordServiceImpl implements LocationRecordService {

    private static final Logger logger = LoggerFactory.getLogger(LocaleServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private LocationRecordRepository locationRecordRepository;
    private ServletService servletService;

    @Autowired
    public LocationRecordServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor, LocationRecordRepository locationRecordRepository, ServletService servletService) {
        this.logService = logService;
        this.accessor = accessor;
        this.locationRecordRepository = locationRecordRepository;
        this.servletService = servletService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public LocationRecordBean saveLocationRecord(LocationRecordInputInfo inputInfo) {
        if (inputInfo == null || StringUtils.isBlank(inputInfo.getIncidentId())) {
            logService.infoLog(logger, "service", "saveLocationRecord", "LocationRecordInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveLocationRecord", "service is started...");
            Long logStart = System.currentTimeMillis();

            LocationRecordBean res = null;

            LocationRecordEntity entity = HandleDispatchTransformUtil.transform(inputInfo);
            if (null == entity.getSort()) {
                Integer nextSort = locationRecordRepository.findNextSort(inputInfo.getIncidentId());
                entity.setSort(nextSort);
            }
            if (entity.getLocationTime()==null||entity.getLocationTime()==0){
                entity.setLocationTime(servletService.getSystemTime());
            }

            accessor.save(entity);

            res = HandleDispatchTransformUtil.transform(entity);


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveLocationRecord", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveLocationRecord", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DATA_FAIL);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<LocationRecordBean> getLocationRecord(String incidentId) {
        if (StringUtils.isBlank(incidentId)) {
            logService.infoLog(logger, "service", "getLocationRecord", "LocationRecordInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "getLocationRecord", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<LocationRecordBean> res = new ArrayList<>();

            GeneralAccessor.ConditionGroup locationRecordConditionGroup = GeneralAccessor.ConditionGroup.and(
                    GeneralAccessor.ConditionTuple.eq("incidentId", incidentId),
                    GeneralAccessor.ConditionTuple.eq("valid", 1)
            );

            GeneralAccessor.RecordOrderGroup locationRecordRecordOrderGroup = GeneralAccessor.RecordOrderGroup.group(
                    GeneralAccessor.RecordOrder.desc("sort"),GeneralAccessor.RecordOrder.desc("locationTime")
            );

            List<LocationRecordEntity> entities = accessor.find(null, locationRecordConditionGroup, locationRecordRecordOrderGroup, LocationRecordEntity.class);

            if (entities != null && entities.size() > 0) {
                for (LocationRecordEntity entity : entities) {
                    LocationRecordBean locationRecordBean = HandleDispatchTransformUtil.transform(entity);
                    if (locationRecordBean != null) {
                        res.add(locationRecordBean);
                    }
                }
            }
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "getLocationRecord", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "getLocationRecord", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_FAIL);
        }
    }
}
