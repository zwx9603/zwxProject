package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.service.DispatchAlgorithmService;
import com.dscomm.iecs.accept.service.bean.RecommendedVehicleBean;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 描述:
 * author:YangFuXi
 * Date:2021/6/11 Time:14:24
 **/
@Component
public class DispatchAlgorithmServiceImpl implements DispatchAlgorithmService {
    @Override
    public List<RecommendedVehicleBean> recommendedVehicle(String incidentId, Object param) {
        return null;
    }
}
