package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.inputbean.DrillPlanQueryInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.DrillPlanSaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.DrillPlanBean;
import com.dscomm.iecs.base.graphql.typebean.PaginationBean;

/**
 * 描述：集合演练方案 服务
 */
public interface DrillPlanService {

    /**
     * 条件查询 集合演练方案
     *
     * @return
     */
    PaginationBean<DrillPlanBean> findDrillPlanCondition(DrillPlanQueryInputInfo inputInfo);

    /**
     * 保存  集合演练方案
     * @param inputInfo
     * @return
     */
    DrillPlanBean saveDrillPlan(DrillPlanSaveInputInfo inputInfo);

    /**
     * 根据id  获得集合演练方案
     *
     * @return
     */
    DrillPlanBean findDrillPlanById(String id);

    /**
     * 根据id 移除 集合演练方案
     *
     * @return
     */
    Boolean  removeDrillPlan(String id);

}
