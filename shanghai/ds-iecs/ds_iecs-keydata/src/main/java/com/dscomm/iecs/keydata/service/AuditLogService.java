package com.dscomm.iecs.keydata.service;


import com.dscomm.iecs.base.graphql.typebean.PaginationBean;
import com.dscomm.iecs.keydata.graphql.inputbean.AuditLogParam;
import com.dscomm.iecs.keydata.graphql.inputbean.AuditLogSaveInputInfo;
import com.dscomm.iecs.keydata.graphql.typebean.AuditLogBean;
import org.mx.dal.Pagination;

import java.util.List;

/**
 * 描述：系统操作日志 审计日志 服务
 */
public interface AuditLogService {

    /**
     * 保存系统操作日志 审计日志
     *
     * @param queryBean 系统操作日志保存参数
     * @return 返回结果
     */
    AuditLogBean saveAuditLog(AuditLogSaveInputInfo queryBean);

    PaginationBean<AuditLogBean> findAuditLogByCondition(AuditLogParam param, Pagination pagination);

}
