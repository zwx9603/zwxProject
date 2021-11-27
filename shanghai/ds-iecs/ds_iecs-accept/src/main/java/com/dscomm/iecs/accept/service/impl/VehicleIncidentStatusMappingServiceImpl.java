package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.VehicleIncidentStatusMappingEntity;
import com.dscomm.iecs.accept.dal.repository.VehicleIncidentStatusMappingRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.typebean.VehicleIncidentStatusMappingBean;
import com.dscomm.iecs.accept.service.AcceptanceService;
import com.dscomm.iecs.accept.service.VehicleIncidentStatusMappingService;
import com.dscomm.iecs.accept.utils.transform.HandleDispatchTransformUtil;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.service.DictionaryService;
import org.apache.logging.log4j.util.Strings;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 描述：车辆案件状态关系 服务类实现
 */
@Component("vehicleIncidentStatusMappingServiceImpl")
public class VehicleIncidentStatusMappingServiceImpl implements VehicleIncidentStatusMappingService {
    private static final Logger logger = LoggerFactory.getLogger(VehicleIncidentStatusMappingServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private Environment env;
    private DictionaryService dictionaryService  ;
    private VehicleIncidentStatusMappingRepository vehicleIncidentStatusMappingRepository ;

    private List<String> dics;
    /**
     * 默认的构造函数
     */
    @Autowired
    public VehicleIncidentStatusMappingServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor, Environment env,
                        DictionaryService dictionaryService , VehicleIncidentStatusMappingRepository vehicleIncidentStatusMappingRepository
    ) {
        this.logService = logService;
        this.accessor = accessor;
        this.env = env;
        this.dictionaryService = dictionaryService;
        this.vehicleIncidentStatusMappingRepository = vehicleIncidentStatusMappingRepository ;

        dics = new ArrayList<>(Arrays.asList("AJLX", "WLCLZT", "AJZT"    ));

    }

    /**
     * {@inheritDoc}
     *
     * @see AcceptanceService#findAcceptance(String)
     */
    @Transactional(readOnly = true)
    @Override
    public VehicleIncidentStatusMappingBean findVehicleIncidentStatusMapping (String incidentTypeCode , String vehicleStatusCode ) {
        if (Strings.isBlank(incidentTypeCode)) {
            logService.infoLog(logger, "service", "findVehicleIncidentStatusMappingByIncidentTypeCode", "incidentTypeCode is Blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findVehicleIncidentStatusMappingByIncidentTypeCode", "service is started...");
            Long logStart = System.currentTimeMillis();

             VehicleIncidentStatusMappingBean  res = null ;

            // 查询出所有需要用到的字典
            Map<String, Map<String, String>> dicsMap  = dictionaryService.findDictionaryMap(dics) ;

            logService.infoLog(logger, "repository", "findVehicleIncidentStatusMappingByIncidentTypeCode( incidentTypeCode )", "repository is started...");
            Long start = System.currentTimeMillis();

            List<VehicleIncidentStatusMappingEntity> vehicleIncidentStatusMappingEntityList = vehicleIncidentStatusMappingRepository.findVehicleIncidentStatusMapping( incidentTypeCode , vehicleStatusCode  ) ;
            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findVehicleIncidentStatusMappingByIncidentTypeCode( incidentTypeCode )", String.format("repository is finished,execute time is :%sms", end - start));

            if (vehicleIncidentStatusMappingEntityList != null && vehicleIncidentStatusMappingEntityList.size() > 0) {
                res = HandleDispatchTransformUtil.transform(vehicleIncidentStatusMappingEntityList.get(0 ), dicsMap );
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findVehicleIncidentStatusMappingByIncidentTypeCode", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findVehicleIncidentStatusMappingByIncidentTypeCode", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_VEHICLE_INCIDENT_MAPPING_FAIL);
        }

    }

}
