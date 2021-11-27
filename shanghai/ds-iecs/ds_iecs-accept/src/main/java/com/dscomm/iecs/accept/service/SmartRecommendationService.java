package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.inputbean.FindSmartRecommendationInfo;
import com.dscomm.iecs.accept.graphql.typebean.FindSmartRecommendationBean;
import com.dscomm.iecs.accept.service.bean.*;
import com.dscomm.iecs.base.graphql.typebean.PaginationBean;

import java.util.List;

/**
 * 智能推荐服务
 */
public interface SmartRecommendationService {

    /**
     *等级判定接口调用
     * @param paramBean
     * @return
     */
    GradeJudgeResultBean transformGradeJudge(GradeJudgeQueryParamBean paramBean);

    /**
     *推荐力量接口调用
     * @param param
     * @return
     */
    PowerTransferResultBean transformPowerTransfer(PowerTransferQueryParam param);

    /**
     * 智能推荐调派力量
     * @param param 参数
     * @return 返回值
     */
    SmartRecommendVehicleBean smartRecommendVehicle(PowerTransferQueryParam param);

    /**
     * 确认推荐结果或更新差异原因
     * @param param 参数
     * @return 调用结果
     */
    Boolean confirmSmartRecommend(ConfirmSmartRecommendBean param);

    /**
     * 查询智能辅助调用记录
     * @param param 参数
     * @return 返回值
     */
    PaginationBean<FindSmartRecommendationBean> findSmartRecommendationRecord(FindSmartRecommendationInfo param);

}
