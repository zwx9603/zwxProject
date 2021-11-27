package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.inputbean.AccidentSaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.AccidentBean;


/**
 * 描述：事故情况 服务
 */
public interface AccidentService {


    /**
     * 事故情况 保存
     * @param queryBean 保存参数
     * @return 返回结果
     */
    AccidentBean saveAccident(AccidentSaveInputInfo queryBean);


    /**
     * 根据案件id查询 最新事故信息
     * @param incidentId 案件id
     * @return 返回结果
     */
    AccidentBean findAccident(String incidentId);

}
