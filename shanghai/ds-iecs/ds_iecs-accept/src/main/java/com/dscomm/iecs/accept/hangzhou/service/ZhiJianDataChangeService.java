package com.dscomm.iecs.accept.hangzhou.service;

import com.dscomm.iecs.accept.graphql.typebean.HandleBean;
import com.dscomm.iecs.accept.graphql.typebean.IncidentBean;

import java.util.List;

/**
 *  接处警 推送 其他系统方法
 */
public interface ZhiJianDataChangeService {

    /**
     * 新增警情
     * */
    Boolean newCase(IncidentBean incidentBean);


    /**
     * 修改警情
     * */
    Boolean modifyCase(IncidentBean incidentBean);


    /**
     * 处警单
     * */
    Boolean dispatchVehicle(IncidentBean incidentBean, List<HandleBean> handleBeans);


}
