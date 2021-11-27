package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.inputbean.HierarchicalDispatchQueryInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.HierarchicalDispatchSaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.HierarchicalDispatchBean;
import com.dscomm.iecs.base.graphql.typebean.PaginationBean;

import java.util.List;

/**
 * 描述：等级调派 服务
 */
public interface HierarchicalDispatchService {

    /**
     * 等级调派方案条件查询
     *
     * @param queryBean 查询参数
     * @return 等级调度列表
     */
    PaginationBean<HierarchicalDispatchBean> findHierarchicalDispatchCondition(HierarchicalDispatchQueryInputInfo queryBean);

    /**
     * 保存  等级调派方案
     *
     * @param queryBean 等级调派参数
     * @return 返回结果
     */
    HierarchicalDispatchBean saveHierarchicalDispatch(HierarchicalDispatchSaveInputInfo queryBean);


    /**
     *  移除 等级调派方案
     *
     * @param hierarchicalDispatchId 等级调派id
     * @return 返回结果
     */
    Boolean removeHierarchicalDispatch( String hierarchicalDispatchId);


    /**
     * 等级调派力量查询
     *
     * @param queryBean 等级调派力量查询参数
     * @return 返回结果
     */
    List<HierarchicalDispatchBean> findHierarchicalDispatchVehicle ( HierarchicalDispatchQueryInputInfo queryBean  );

}
