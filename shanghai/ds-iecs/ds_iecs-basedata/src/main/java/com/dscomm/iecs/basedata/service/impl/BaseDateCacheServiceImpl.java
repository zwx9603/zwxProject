package com.dscomm.iecs.basedata.service.impl;


import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("baseDateCacheServiceImpl")
public class BaseDateCacheServiceImpl implements BaseDateCacheService {

    private static final Logger logger = LoggerFactory.getLogger(ContactsServiceImpl.class);
    private LogService logService;
    private DictionaryService dictionaryService ;
    private OrganizationService organizationService ;
    private OrganizationOtherService organizationOtherService ;
    private EquipmentService equipmentService ;
    private NotifyNotesService notifyNotesService;
    private KeyUnitService keyUnitService ;
    private SystemConfigurationService  systemConfigurationService ;
    private VehicleService vehicleService ;
    private RegionService regionService ;
    /**
     * 默认的构造函数
     */
    @Autowired
    public BaseDateCacheServiceImpl (LogService logService , DictionaryService dictionaryService , OrganizationService organizationService ,
                                     OrganizationOtherService organizationOtherService ,  EquipmentService equipmentService  ,
                                     NotifyNotesService notifyNotesService , KeyUnitService keyUnitService ,
                                     SystemConfigurationService  systemConfigurationService , VehicleService vehicleService ,
                                     RegionService regionService
    ) {
        this.logService = logService ;
        this.dictionaryService = dictionaryService ;
        this.organizationService = organizationService ;
        this.organizationOtherService = organizationOtherService ;
        this.equipmentService = equipmentService ;
        this.notifyNotesService = notifyNotesService ;
        this.keyUnitService = keyUnitService ;
        this.systemConfigurationService = systemConfigurationService ;
        this.vehicleService  = vehicleService ;
        this.regionService = regionService ;
    }





    /**
     * {@inheritDoc}
     *
     * @see #updateBaseDateCache(   )
     */
    public void updateBaseDateCache(   ) {

        // 缓存信息强制更新
        try {
            logService.infoLog(logger, "service", "updateBaseDateCache", "start excute tasks");
            long start = System.currentTimeMillis();

            systemConfigurationService.forceUpdateCacheSystemConfiguration();

            dictionaryService.forceUpdateCacheDictionary();

            organizationService.forceUpdateCacheOrganization();

            vehicleService.forceUpdateCacheVehicle();

//            String path="rest/iecs/v1.0/notifyOtherNodes/baseDate/cache/cache";
//            notifyNotesService.notifyNodes(path,transmit ,null);

            long end = System.currentTimeMillis();
            logService.infoLog(logger, "service", "updateBaseDateCache", String.format("end excute tasks,totalTime:%sms", end - start));
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "updateBaseDateCache", "force update cache dictionary fail", ex);
        }

    }



    /**
     * {@inheritDoc}
     *
     * @see #updateBaseDateCache(   )
     */
    public void updateBaseDateNoKeyCache(      ) {

        // 缓存信息强制更新
        try {
            logService.infoLog(logger, "service", "updateBaseDateCache", "start excute tasks");
            long start = System.currentTimeMillis();

            regionService.forceUpdateCacheRegion();

            organizationOtherService.buildAdjacentOrganizationCache();

            keyUnitService.forceUpdateCacheKeyUnit();

            equipmentService.forceUpdateCacheEquipmentVehicleLoadCache();


//            String path="rest/iecs/v1.0/notifyOtherNodes/baseDate/cache/nokey";
//            notifyNotesService.notifyNodes(path,transmit ,null);

            long end = System.currentTimeMillis();
            logService.infoLog(logger, "service", "updateBaseDateCache", String.format("end excute tasks,totalTime:%sms", end - start));
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "updateBaseDateCache", "force update cache dictionary fail", ex);
        }

    }

}
