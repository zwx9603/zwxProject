package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.dal.po.FunctionInfoEntity;
import com.dscomm.iecs.accept.graphql.inputbean.FunctionInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.FunctionInfoBean;

import java.util.List;

public interface FunctionInfoService {

    /*
     * 查询功能表的信息
     * */
    List<FunctionInfoBean> queryInfo();

    /*
     * 保存功能表的信息
     * */
    Boolean saveFunctionInfo(FunctionInputInfo info);
}
