package com.dscomm.iecs.basedata.service;


import com.dscomm.iecs.basedata.graphql.typebean.WaterBaseBean;

/**
 * 水源service
 * */
public interface WaterService {

    /**
     * 根据 消火栓id  获取 消火栓
     * @param hydrantId  消火栓id
     * @return  WaterBaseBean
     * */
    WaterBaseBean findFireHydrantBeanById(String hydrantId );

    /**
     * 根据Id获取指定消防水池详情
     *
     * @param waterPoolId 水池ID
     * @return 返回水池详情
     */
    WaterBaseBean findWaterPoolDetailsById(String waterPoolId);
    /**
     * 根据Id获取指定水鹤详情
     *
     * @param waterCraneId 水鹤ID
     * @return 返回水鹤详情
     */
    WaterBaseBean findWaterCraneDetailsById(String waterCraneId);
    /**
     * 根据ID获取指定天然水源的详情
     *
     * @param naturalWaterSourceId 天然水源ID
     * @return 返回天然水源详情
     */
    WaterBaseBean findNaturalWaterSourceDetailsById(String naturalWaterSourceId);
    /**
     * 根据ID获取指定取水码头的详情
     *
     * @param waterIntakeWharfId 取水码头Id
     * @return 返回取水码头详情
     */
    WaterBaseBean findWaterIntakeWharfDetailsById(String waterIntakeWharfId);



}
