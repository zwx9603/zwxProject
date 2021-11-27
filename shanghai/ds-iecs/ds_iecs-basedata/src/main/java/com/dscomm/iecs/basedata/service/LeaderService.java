package com.dscomm.iecs.basedata.service;


import com.dscomm.iecs.basedata.graphql.typebean.LeaderBean;

import java.util.List;

/**
 * @Author: zs
 * @Date: 16:04 2020/7/24
 * desc:
 */
public interface LeaderService {

    /**
     * 查询领导列表
     * @param type 领导类型
     * @return
     */
    List<LeaderBean> LeaderList(String type);
}
