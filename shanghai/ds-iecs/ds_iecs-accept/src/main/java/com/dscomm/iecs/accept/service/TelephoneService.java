package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.inputbean.CallBackSaveInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.MonitotTakeOverIncidentInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.TelephoneQueryInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.VoiceTranscribeSaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.PhoneLibraryBean;
import com.dscomm.iecs.accept.graphql.typebean.TelephoneBean;
import com.dscomm.iecs.accept.graphql.typebean.TelephoneInformationBean;
import com.dscomm.iecs.accept.graphql.typebean.VoiceTranscribeBean;
import com.dscomm.iecs.base.graphql.typebean.PaginationBean;

import java.util.List;

/**
 * 描述：电话报警记录 服务
 */
public interface TelephoneService {


    /**
     *  非话务 生成警情id  受理id 信息
     * @param alarmPhone
     * @return
     */
    TelephoneBean  buildIdsNoCalling ( String alarmPhone );


    /**
     * 振铃时生成通话信息
     * @param incidentId  案件id
     * @param callingTelephone 主叫号码
     * @param calledTelephone  被叫号码
     * @return 警单基类信息
     */
    TelephoneBean ringSaveTelephoneRecord( String callingTelephone, String calledTelephone ,   Boolean isCallback ,
                                           String  incidentId  );


    /**
     * 回拨服务(没有振铃，直接摘机，用于接警过程中/警情详情回拨)
     *
     * @param inputInfo 参数
     * @return 返回呼叫编号
     */
    TelephoneBean callBackSaveCallRecord(CallBackSaveInputInfo inputInfo);


    /**
     * 摘机更新通话信息
     *
     * @param telephoneId 电话呼入记录id
     * @return 电话呼入记录id
     */
    TelephoneBean pickUpUpdateTelephone(String telephoneId);

    /**
     * 上传录音号更新通话信息
     *
     * @param telephoneId       电话呼入记录id
     * @param agentCallRecordNum 坐席录音号
     * @return 电话呼入记录id
     */
    TelephoneBean upLoadSoundRecordUpdateTelephone(String telephoneId, String agentCallRecordNum);

    /**
     * 挂机更新通话信息
     *
     * @param telephoneId 电话呼入记录id
     * @return 电话呼入记录id
     */
    TelephoneBean hangUpUpdateTelephone(String telephoneId) ;


    /**
     * 更新 电话报警记录的报警经纬度信息
     *
     * @param telephoneId 电话呼入记录id
     * @return 电话呼入记录id
     */
    TelephoneBean updateTelephoneCoordinates(String telephoneId , String longitude , String latitude , String height ) ;

    /**
     * 根据警情id(案件id)获取案件电话报警记录ids
     *
     * @param incidentId 案件id
     * @return 案件接警信息（电话报警记录） 列表
     */
    List<String> findTelephoneIds (String incidentId);


    /**
     * 根据警情id(案件id)获取案件接警信息(电话报警记录)
     *
     * @param incidentId 案件id
     * @return 案件接警信息（电话报警记录） 列表
     */
    TelephoneInformationBean findTelephone(String incidentId);


    /**
     * 变更电话报警记录关联的案件id
     *
     * @param sourceIncidentId 原案件id
     * @param targetIncidentId 目标案件id
     */
    void changeTelephoneIncidentId(String sourceIncidentId, String targetIncidentId);

    /**
     * 恢复电话报警记录关联的案件id
     *
     * @param incidentId 案件id
     */
    void recoverTelephoneIncidentId(String incidentId);

    /**
     * 根据报警电话 获取固定电话信息
     *
     * @param phoneNumber 电话号
     * @return 固定电话信息列表
     */
    List<PhoneLibraryBean> findPhoneLibraryByPhoneNumber(String phoneNumber);

    /**
     * 根据报警电话 查询报警次数（报警记录个数）
     * @param callingNumber 报警电话
     * @return 报警次数
     */
    Long findAlarmCount(String callingNumber);

    /**
     * 主动接管
     *
     * @param agentNum 被接管坐席号
     * @return 是否成功
     */
    Boolean monitorInsert(Integer agentNum);

    /**
     * 班长插话
     *
     * @param agentNum 被插话坐席号
     * @return 是否成功
     */
    Boolean monitorBarge(Integer agentNum);

    /**
     * 接警席向班长席推送被接管的警情
     *
     * @param InfoBaseBO 推送的警情
     * @param agentNum   班长席坐席号
     * @return 是否成功
     */
    Boolean getmonitorInsertToPushCaseinfo(MonitotTakeOverIncidentInputInfo InfoBaseBO, Integer agentNum);

    /**
     * 班长席接管成功后向接警席回推被接管的警情，接警席删除警情缓存
     *
     * @param caseInfoBO 推送的警情
     * @param agentNum   被接管坐席号
     * @return 是否成功
     */
    Boolean getmonitorInsertToPushCaseinfoResult(MonitotTakeOverIncidentInputInfo caseInfoBO, Integer agentNum);




    /**
     *  保存语音转写记录
     * @param inputInfo
     * @return
     */
    VoiceTranscribeBean saveVoiceTranscribe(VoiceTranscribeSaveInputInfo inputInfo );


    /**
     * 查询 电话报警记录信息 分页
     * 可根据 警情id  坐席号 员工号
     */
    PaginationBean<TelephoneBean> findTelephonePagination(  TelephoneQueryInputInfo queryBean  );


}
