package com.dscomm.iecs.basedata.service;

import com.dscomm.iecs.base.graphql.typebean.PaginationBean;
import com.dscomm.iecs.basedata.graphql.inputbean.UnitJointServiceQueryInputinfo;
import com.dscomm.iecs.basedata.graphql.typebean.UnitJointServiceBean;

/**
 * 描述：联勤保障单位 服务
 */
public interface UnitJointServiceService {
    PaginationBean<UnitJointServiceBean> findUnitJointPage(UnitJointServiceQueryInputinfo queryBean);
}
