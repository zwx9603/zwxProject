package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.inputbean.CtiCallQueryInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.CtiCallBean;
import com.dscomm.iecs.base.graphql.typebean.PaginationBean;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/5/25 14:24
 * @Describe
 */
public interface CtiCallService {

    PaginationBean<CtiCallBean> findCtiCallCondition(CtiCallQueryInputInfo queryBean);
}
