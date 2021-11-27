package com.dscomm.iecs.basedata.service.impl;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.dal.po.VehicleGarageMappingEntity;
import com.dscomm.iecs.basedata.dal.repository.VehicleGarageMappingRepository;
import com.dscomm.iecs.basedata.exception.BasedataException;
import com.dscomm.iecs.basedata.graphql.inputbean.VehicleGarageMappingQueryInputInfo;
import com.dscomm.iecs.basedata.graphql.inputbean.VehicleGarageMappingSaveInputInfo;
import com.dscomm.iecs.basedata.graphql.inputbean.VehicleGarageMappingSaveListInputInfo;
import com.dscomm.iecs.basedata.graphql.typebean.VehicleGarageMappingBean;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.basedata.service.VehicleGarageMappingService;
import com.dscomm.iecs.basedata.utils.EquipmentTransformUtil;
import org.mx.dal.service.GeneralAccessor;
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
 * 描述：中队车辆与车库门对应关系 服务类实现
 */
@Component("vehicleGarageMappingServiceImpl")
public class VehicleGarageMappingServiceImpl implements VehicleGarageMappingService {
    private static final Logger logger = LoggerFactory.getLogger(VehicleGarageMappingServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private OrganizationService organizationService;
    private VehicleGarageMappingRepository vehicleGarageMappingRepository;
    private DictionaryService dictionaryService;

    private List<String> dics;

    /**
     * 默认的构造函数
     */
    @Autowired
    public VehicleGarageMappingServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor,
                                           VehicleGarageMappingRepository vehicleGarageMappingRepository, OrganizationService organizationService ,
                                           DictionaryService dictionaryService
    ) {
        this.accessor = accessor;
        this.logService = logService;
        this.organizationService = organizationService;
        this.vehicleGarageMappingRepository = vehicleGarageMappingRepository;
        this.dictionaryService = dictionaryService ;

        dics = new ArrayList<>(Arrays.asList("CLCKGXLX", "CKMH" ));

    }

    /**
     * {@inheritDoc}
     *
     * @see  #findVehicleGarageMappingCondition(VehicleGarageMappingQueryInputInfo)
     */
    @Transactional(readOnly = true)
    @Override
    public List<VehicleGarageMappingBean> findVehicleGarageMappingCondition(VehicleGarageMappingQueryInputInfo queryInputInfo) {
        if (queryInputInfo == null) {
            logService.infoLog(logger, "service", "findVehicleGarageMappingCondition", "VehicleGarageMappingQueryInputInfo is null.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findVehicleGarageMappingCondition", "service is started...");
            Long logStart = System.currentTimeMillis();
            List<VehicleGarageMappingBean> res = new ArrayList<>();

            logService.infoLog(logger, "repository", "findVehicleGarageMappingCondition", "repository is started...");
            Long start = System.currentTimeMillis();

            List<VehicleGarageMappingEntity> vehicleGarageMappingEntityList =
                    vehicleGarageMappingRepository.findVehicleGarageMappingCondition(
                            queryInputInfo.getOrganizationIds(), queryInputInfo.getMappingTypes(),
                            queryInputInfo.getMappingObjectIds(), queryInputInfo.getMappingGroupIds() );

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findVehicleGarageMappingCondition", String.format("repository is finished,execute time is :%sms", end - start));

            if (vehicleGarageMappingEntityList != null && vehicleGarageMappingEntityList.size() > 0) {
                //获取机构id与名字对应缓存
                Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();
                // 查询出所有需要用到的字典
                Map<String, Map<String, String>> dicsMap  = dictionaryService.findDictionaryMap(dics) ;
                List<String> vehicleIds = new ArrayList<>() ;
                for (VehicleGarageMappingEntity entity : vehicleGarageMappingEntityList) {
                    VehicleGarageMappingBean bean = EquipmentTransformUtil.transform(entity, dicsMap , organizationNameMap);
                    vehicleIds.add( entity.getMappingObjectId()  ) ;
                    res.add(bean);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findVehicleGarageMappingCondition", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findVehicleGarageMappingCondition", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_VEHICLE_GARAGE_MAPPING_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see VehicleGarageMappingService#saveVehicleGarageMapping(VehicleGarageMappingSaveListInputInfo)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<VehicleGarageMappingBean> saveVehicleGarageMapping(VehicleGarageMappingSaveListInputInfo inputInfo) {
        if (inputInfo == null) {
            logService.infoLog(logger, "service", "saveVehicleGarageMapping", "VehicleGarageMappingSaveListInputInfo is null.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveVehicleGarageMapping", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<VehicleGarageMappingBean> res = new ArrayList<>();
            //获取机构id与名字对应缓存
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();


            List<VehicleGarageMappingEntity> vehicleGarageMappingEntityList = new ArrayList<>() ;
            for(VehicleGarageMappingSaveInputInfo vehicleGarageMappingSaveInputInfo  : inputInfo.getVehicleGarageMappingSaveInputInfos() ){
                VehicleGarageMappingEntity vehicleGarageMappingEntity = EquipmentTransformUtil.transform(vehicleGarageMappingSaveInputInfo);
                vehicleGarageMappingEntityList.add( vehicleGarageMappingEntity ) ;
            }


            logService.infoLog(logger, "repository", "save(dbVehicleGarageMappingEntity)", "repository is started...");
            Long start = System.currentTimeMillis();

            accessor.save(vehicleGarageMappingEntityList);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbVehicleGarageMappingEntity)", String.format("repository is finished,execute time is :%sms", end - start));



            if (vehicleGarageMappingEntityList != null && vehicleGarageMappingEntityList.size() > 0) {
                // 查询出所有需要用到的字典
                Map<String, Map<String, String>> dicsMap  = dictionaryService.findDictionaryMap(dics) ;
                List<String> vehicleIds = new ArrayList<>() ;
                for (VehicleGarageMappingEntity entity : vehicleGarageMappingEntityList) {
                    VehicleGarageMappingBean bean = EquipmentTransformUtil.transform(entity, dicsMap , organizationNameMap);
                    vehicleIds.add( entity.getMappingObjectId()  ) ;
                    res.add(bean);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveVehicleGarageMapping", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveVehicleGarageMapping", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.SAVE_VEHICLE_GARAGE_MAPPING_FAIL);
        }
    }

}
