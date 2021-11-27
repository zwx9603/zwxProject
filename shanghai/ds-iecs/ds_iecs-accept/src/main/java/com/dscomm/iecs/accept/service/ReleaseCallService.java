package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.dal.po.ReleaseCallEntity;
import com.dscomm.iecs.accept.graphql.inputbean.ReleaseCallQueryInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.ReleaseCallSaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.ReleaseCallBean;

import java.util.List;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/5/10 13:50
 * @Describe 排队早释服务
 */
public interface ReleaseCallService {


    /**
     * 保存排队早释记录
     * @param info
     * @return
     */
    Boolean  saveReleaseCall(ReleaseCallSaveInputInfo info);


    /**
     * 获取排队早释记录
     * @param queryInputInfo
     * @return
     */
    List<ReleaseCallEntity> findReleaseCalls(ReleaseCallQueryInputInfo queryInputInfo);
    /**
     * 保存排队记录
     * @param param 排队电话参数
     * @return 返回操作结果
     */
    Boolean saveQueueCalls(ReleaseCallSaveInputInfo param);

    /**
     * 删除排队记录
     * @param callNum 电话
     * @return 返回操作结果
     */
    Boolean removeQuenceCall(String callNum);

    /**
     * 查询排队电话列表
     * @return 排队电话
     */
    List<ReleaseCallBean> findQueueList();
}
