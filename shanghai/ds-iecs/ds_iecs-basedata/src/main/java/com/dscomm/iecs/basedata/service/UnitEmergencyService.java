package com.dscomm.iecs.basedata.service;

import com.dscomm.iecs.base.graphql.typebean.PaginationBean;
import com.dscomm.iecs.basedata.graphql.inputbean.UnitEmergencyQueryInputinfo;
import com.dscomm.iecs.basedata.graphql.typebean.UnitEmergencyBean;

/**
 * 描述：应急联动单位 服务
 */
public interface UnitEmergencyService {
    PaginationBean<UnitEmergencyBean> findUnitEmergencyPage(UnitEmergencyQueryInputinfo queryBean);
}
