package com.dscomm.iecs.basedata.service;


import com.dscomm.iecs.basedata.graphql.inputbean.PlanSaveInputInfo;
import com.dscomm.iecs.basedata.graphql.typebean.PlanBean;

import java.util.List;

/**
 * 描述：预案 服务
 */
public interface PlanService {

    /**
     * 保存  重点单位预案
     * @param inputInfo
     * @return
     */
    PlanBean saveKeyUnitPlan(PlanSaveInputInfo inputInfo);


    /**
     * 根据预案id  获得预案信息
     *
     * @return
     */
    PlanBean findPlanById(String planId);


    /**
     * 根据重点单位id 获得预案列表
     *
     * @return
     */
    List<PlanBean> findPlanByKeyUnitId(String keyUnitId);


    /**删除重点单位预案*/
    Boolean deletePlan(List<String> ids);





}
