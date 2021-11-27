package com.dscomm.iecs.accept.service;


import com.dscomm.iecs.accept.graphql.inputbean.RallyItemFeedbackQueryInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.RallyItemFeedbackSaveInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.RallyPointDeleteTargetInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.RallyPointSaveTargetInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.RallyItemBean;
import com.dscomm.iecs.accept.graphql.typebean.RallyItemFeedbackBean;
import com.dscomm.iecs.accept.graphql.typebean.RallyPointBean;

import java.util.List;

/**
 * 描述： 指挥信息
 *
 */
public interface RallyPointService {
    /**
     * 描述：保存 修改任务部署
     */
    List<RallyPointBean> saveOrUpdateRallyPoint(RallyPointSaveTargetInputInfo queryBean);


    /**
     * 描述:删除集结点 以及该集结点下的集结项,必经点
     *
     */
    Boolean removeRallyPoint(RallyPointDeleteTargetInputInfo queryBean);


    /**
     * 根据事件ID获取集结点列表
     *
     */
    List<RallyPointBean> findRallyPoint(String incidentId);


    /**
     * 根据事件id 集结力量id 获取集结点详情
     */
    List<RallyItemBean> findRallyItem  (String incidentId , String rallyPowerId );


    /**
     * 根据集结点ID获取集结点详情
     */
    RallyPointBean findRallyPointDetail  (String rallyPointId);


    /**
     *  保存 集结项反馈（集结力量反馈）
     */
    Boolean saveRallyItemFeedback(RallyItemFeedbackSaveInputInfo queryBean);


    /**
     * 根据条件 查询集结项列表（集结力量反馈）
     */
    List<RallyItemFeedbackBean> findRallyItemFeedbackCondition(RallyItemFeedbackQueryInputInfo queryBean);



}
