package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.inputbean.HandleMiniatureStationFeedbackSaveInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.HandleSaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.HandleBean;
import com.dscomm.iecs.accept.graphql.typebean.HandleMiniatureStationBean;
import com.dscomm.iecs.accept.graphql.typebean.HandleMiniatureStationFeedbackBean;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;

import java.util.List;

/**
 *  微型消防站调派服务类
 */
public interface HandleMiniatureStationService {


    /**
     * 保存微站调派信息
     */
    HandleBean saveHandleMiniatureStation (HandleSaveInputInfo queryBean , UserInfo userInfo );


    /**
     * 查询微站调派信息
     */
     List<HandleMiniatureStationBean>   findHandleMiniatureStation( String incidentId , String handleId  ) ;

    /**
     *  保存 微站调派反馈
     * @param
     * @return
     */
    HandleMiniatureStationFeedbackBean saveHandleMiniatureStationFeedback(HandleMiniatureStationFeedbackSaveInputInfo inputInfo ) ;



    /**
     * 查询 微站反馈信息
     */
    List<HandleMiniatureStationFeedbackBean> findHandleMiniatureStationFeedback( String incidentId , String handleMiniatureStationId ) ;







}
