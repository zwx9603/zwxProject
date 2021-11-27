package com.dscomm.iecs.accept.service;


import com.dscomm.iecs.accept.graphql.typebean.UnTrafficAlarmBean;
import com.dscomm.iecs.accept.restful.vo.ReceiveUnTrafficAlarmVO;

import java.util.List;

/**
 * 描述：非话务报警信息
 */
public interface UnTrafficAlarmService {


    /**
     * 保存 非话务报警信息
     * @return 回写结果
     */
    String saveUnTrafficAlarmIncident( ReceiveUnTrafficAlarmVO inputInfo , Boolean whetherSaveIncident ) ;


    /**
     *  收其他系统警情回执操作
     * @param  alarmPhone  电话号码
     * @param  agentNumber 操作坐席号
     * @return 回写结果
     */
    Boolean receiveConfirmTrafficAlarm(   String  alarmPhone , String agentNumber     ) ;

    /**
     *  收其他系统警情回执操作
     * @param  receiveMessageId 转警记录id
     * @param  incidentId 消防警情id
     * @param  agentNumber 操作坐席号
     * @param  backResult 0 拒绝 1接收
     * @return 回写结果
     */
    Boolean receiveConfirmUnTrafficAlarm( String  receiveMessageId , String  incidentId , String agentNumber , Integer backResult   ) ;

    /**
     * 根据案件id 查询非话务报警记录
     * @param incidentId
     * @return
     */
    List<UnTrafficAlarmBean> findUnTrafficAlarm(String incidentId);


    /**
     * 回写案件id
     * @param unTrafficAlarmId 非话务报警信息id
     * @param incidentId 案件id
     * @return 回写结果
     */
    Boolean writeBackIncidentId(String unTrafficAlarmId,String incidentId) ;


    /**
     * 锁定非话务列表
     *
     * @param id  流水号
     * @return  返回接口
     */
    Boolean lockUncallAccept (String id   );


    /**
     * 删除非话务列表
     *
     * @param id  流水号
     * @return  返回接口
     */
    Boolean  uncallAcceptListDelete( String  id   );








}
