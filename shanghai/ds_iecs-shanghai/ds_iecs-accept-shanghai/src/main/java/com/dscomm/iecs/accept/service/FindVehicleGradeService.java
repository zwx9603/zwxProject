package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.inputbean.IncidentgradeputInfo;
import com.dscomm.iecs.accept.graphql.typebean.IncidentGradeBean;

import java.util.List;

/*
* 根据案件信息做等级调派派车
* */
public interface FindVehicleGradeService {
    List<IncidentGradeBean> findVehicleGrade(IncidentgradeputInfo info);
}
