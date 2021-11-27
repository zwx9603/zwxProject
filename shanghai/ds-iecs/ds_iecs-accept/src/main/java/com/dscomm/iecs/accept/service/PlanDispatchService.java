package com.dscomm.iecs.accept.service;


import com.dscomm.iecs.basedata.graphql.typebean.PlanBean;
import com.dscomm.iecs.basedata.graphql.typebean.PlanDispatchBean;

import java.util.List;

/**
 * 描述：预案调派  服务
 */
public interface PlanDispatchService {



    /**
      * 根据预案id 获得预案调派力量
      *
      * @return
      */
     List<PlanDispatchBean> findPlanDispatchByPlanId(String planId);


    /**
     * 根据重点单位 获得预案调派力量
     *
     * @return
     */
    List<PlanDispatchBean> findPlanDispatchByKeyUnitId(String keyUnitId);


    /**
     * 根据重点单位id的集合 查询 预案车辆信息 （ 包含补充信息 ）
     *
     * @return
     */
    List<PlanBean> findPlanDispatchSupplementKeyUnitId( String keyUnitId );


}
