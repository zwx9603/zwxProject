package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.typebean.KeyUnitLevelCountBean;

import java.util.List;

/**
 *  实战指挥 统计服务
 */
public interface StatisticsFireService {


    /**
     * 统计各个等级重点单位数量信息
     * @return KeyUnitLevelCountBean集合
     * */
    List<KeyUnitLevelCountBean> countKeyUnitLevel();



}
