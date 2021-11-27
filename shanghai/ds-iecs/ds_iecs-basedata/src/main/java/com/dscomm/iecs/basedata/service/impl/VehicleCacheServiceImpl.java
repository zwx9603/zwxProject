package com.dscomm.iecs.basedata.service.impl;

import com.dscomm.iecs.basedata.graphql.typebean.EquipmentVehicleBean;
import com.dscomm.iecs.basedata.service.VehicleCacheService;
import org.mx.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 描述:车辆装备缓存
 * author:YangFuXi
 * Date:2021/5/19 Time:18:20
 **/
@Component
@CacheConfig(cacheNames = "vehicle")
public class VehicleCacheServiceImpl implements VehicleCacheService  {
    private static final Logger logger=LoggerFactory.getLogger(VehicleCacheServiceImpl.class);
    private static Map<String, EquipmentVehicleBean> vehicleCache=new ConcurrentHashMap<>();
    @CachePut(key = "'vehicle'.concat(#id)")
    @Override
    public EquipmentVehicleBean modifyVehicleCache(String id, EquipmentVehicleBean bean) {
       try {
        if (!StringUtils.isBlank(id)){
            vehicleCache.put(id,bean);
        }
       }catch (Exception ex){
        logger.error("modify cache:vehicle fail",ex);
       }
       return bean;
    }

    @Cacheable(key = "'vehicle'.concat(#id)")
    @Override
    public EquipmentVehicleBean findVehicleCache(String id) {
        try {
            if (!StringUtils.isBlank(id)){
                return vehicleCache.get(id);
            }
        }catch (Exception ex){
            logger.error("modify cache:vehicle fail",ex);
        }
        return null;
    }


}
