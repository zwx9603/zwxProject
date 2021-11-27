package com.dscomm.iecs.out.service;

import com.dscomm.iecs.out.graphql.typebean.DocumentOutBean;

import java.util.List;

/**
 * @author fanghuilin
 * @version 1.00
 * @Time 2021/4/120
 * @Describe 获取文书信息服务
 */
public interface DocumentOutService {
    List<DocumentOutBean> getAlarmJqwsListByTime(Long st, Long et, String username);
}
