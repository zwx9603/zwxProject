package com.dscomm.iecs.out.service;

import com.dscomm.iecs.out.dal.po.QueryAuditEntity;
import com.dscomm.iecs.out.graphql.inputinfo.QueryInputInfo;
import com.dscomm.iecs.out.graphql.typebean.CountBean;

import java.util.List;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/4/22 16:18
 * @Describe 审计日志服务
 */
public interface AuditLogOutService {


    /**
     * 条件查询接口调用情况
     * @param inputInfo
     * @return 列表
     */
    List<QueryAuditEntity> findInterfaceAuditByParams(QueryInputInfo inputInfo);


    /**
     * 统计情况
     * @param inputInfo
     * @return
     */
    CountBean countPostByParams(QueryInputInfo inputInfo);
}
