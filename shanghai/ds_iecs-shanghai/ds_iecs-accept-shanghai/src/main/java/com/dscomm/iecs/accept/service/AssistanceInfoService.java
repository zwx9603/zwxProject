package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.dal.po.AssistanceEntity;
import com.dscomm.iecs.accept.graphql.inputbean.AssistanceInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.AssistanceBean;

import java.util.List;

public interface AssistanceInfoService {
    /**
     * 保存友邻的消防队的信息
     * @param info
     * @return
     */
    Boolean  saveAssistanceInfo(AssistanceInputInfo info);

    /**
     * 根据id查询友邻援助的信息
     * @param zgdwdmId
     * @return
     */
    AssistanceBean queryInfo (String zgdwdmId);
}
