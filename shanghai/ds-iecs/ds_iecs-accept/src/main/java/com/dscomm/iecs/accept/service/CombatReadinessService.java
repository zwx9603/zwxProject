package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.fireinputbean.CombatReadinessInputInfo;
import com.dscomm.iecs.accept.graphql.firetypebean.CombatReadinessBean;

import java.util.List;

/**
 * 描述：战备值守信息录入页面提供了会议通知、待办事项、领导批示、工作动态、值班文件等工作事项的录入、编辑、删除和查询等管理功能
 */
public interface CombatReadinessService {

        //根据事项类型，事项显示开始时间，事项显示结束时间查询战备信息
        List<CombatReadinessBean> findCombatReadiness(String type , Long showStartTime , Long showEndTime);

        //战备值守信息详情
        CombatReadinessBean findCombatReadinessById(String id);

        //保存战备值守信息
        CombatReadinessBean saveCombatReadiness(CombatReadinessInputInfo queryBean);

        //删除战备值守信息
        Boolean removeCombatReadiness(String id);
}
