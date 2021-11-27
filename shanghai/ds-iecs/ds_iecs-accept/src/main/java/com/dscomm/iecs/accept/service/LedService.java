package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.fireinputbean.LedQueryInputInfo;
import com.dscomm.iecs.accept.graphql.fireinputbean.LedSaveInputInfo;
import com.dscomm.iecs.accept.service.bean.LedBean;
import com.dscomm.iecs.base.graphql.typebean.PaginationBean;


public interface LedService {

    boolean saveLed(LedSaveInputInfo info);

    PaginationBean<LedBean> findLedList(LedQueryInputInfo queryBean);
}
