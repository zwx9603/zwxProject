package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.basedata.graphql.typebean.ExpertBean;

import java.util.List;

/**
 * @Author: zs
 * @Date: 16:12 2020/7/23
 * desc: 专家服务
 */
public interface ExpertService {

    /**
     * 专家列表
     * @param expertField 领域
     * @param expertType 类型
     * @param expertName 姓名
     * @return
     */
    List<ExpertBean> findSmartExpertList(String expertField, String expertType, String expertName);

    /**
     * 专家详情
     * @param id
     * @return
     */
    ExpertBean findExpertDetailById(String id);


    /**
     * 智能检索专家列表
     */
    List<ExpertBean>findSmartExpertList(String keyword);
}
