package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.service.bean.RecommendedVehicleBean;

import java.util.List;

/**
 * 描述: 出动力量计算方法服务
 * author:YangFuXi
 * Date:2021/6/11 Time:10:35
 **/
public interface DispatchAlgorithmService {
    /**
     * 根据条件推荐出动力量
     * @param incidentId 案件编号
     * @param param 扩展字段，方便后面定制化传条件
     * @return
     */
    List<RecommendedVehicleBean> recommendedVehicle(String incidentId,Object param);
}
