package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.inputbean.InteractiveRecordSaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.InteractiveRecordBean;

import java.util.List;

/**
 * 短信报警交互记录
 */
public interface InteractiveRecordService {

    /**
     * 保存交互记录
     * @param inputInfo 交互记录
     * @return 返回保存后的交互记录
     * @return
     */
    InteractiveRecordBean saveInteractiveRecord (InteractiveRecordSaveInputInfo inputInfo , String alarmNumber );

    /**
     * 交互记录关联 警情id
     * @param alarmNumber
     * @return
     */
    Boolean  saveInteractiveRecordToIncident  ( String alarmNumber , String incidentId  );

    /**
     * 获取交互记录(电话号)
     * @param phoneNum
     * @return
     */
    List<InteractiveRecordBean> findAllInteractiveRecordByPhoneNum ( String phoneNum );

    /**
     * 获取交互记录( 流水号 )
     * @param alarmNumber
     * @return
     */
    List<InteractiveRecordBean> findAllInteractiveRecordByAlarmNumber  ( String alarmNumber );

    /**
     * 获取交互记录( 警情id )
     * @param incidentId
     * @return
     */
    List<InteractiveRecordBean> findAllInteractiveRecordByIncidentId  ( String incidentId );




}
