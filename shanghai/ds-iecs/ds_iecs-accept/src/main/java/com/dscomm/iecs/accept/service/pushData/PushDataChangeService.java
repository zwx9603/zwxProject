package com.dscomm.iecs.accept.service.pushData;

import com.dscomm.iecs.accept.graphql.typebean.*;

import java.util.List;
import java.util.Map;

/**
 *  接处警 推送 其他系统方法
 */
public interface PushDataChangeService {


    /**
     *  电话报警信息
     */
    Boolean pushTelephone (TelephoneBean telephoneBean  , Map<String, String > otherParams );

    /**
     * 新增警情
     * */
    Boolean pushNewIncident (IncidentBean incidentBean , Map<String, String > otherParams );

    /**
     * 修改警情
     * */
    Boolean pushUpdateIncident ( IncidentBean incidentBean    , Map<String, String > otherParams );

    /**
     * 警情状态变动记录
     * */
    Boolean pushIncidentStatusChange (  IncidentBean incidentBean  ,
                                        IncidentStatusChangeBean incidentStatusChangeBean , Map<String, String > otherParams );

    /**
     * 警情合并
     * */
    Boolean pushIncidentMerger ( IncidentMergeBean incidentMergeBean , Map<String, String > otherParams );

    /**
     * 处警单
     * */
    Boolean pushIncidentHandle( IncidentBean incidentBean   ,  HandleBean  handleBean   , Map<String, String > otherParams );

    /**
     *  指令单
     * */
    Boolean pushIncidentInstruction ( String incidentId , List<String> receivingObjectIds ,
                                      InstructionBean instructionBean  , Map<String, String > otherParams )  ;


    /**
     * 车辆状态 变更
     * */
    Boolean pushVehicleStatusChange( String incidentId , String handleId ,
                                     String  vehicleId  , String vehicleStatusCode   , Map<String, String > otherParams );

    /**
     * 车辆状态 变更
     * */
    Boolean pushVehicleStatusChange( String incidentId , String handleId ,
                                     List<String>  vehicleIds  , String vehicleStatusCode   , Map<String, String > otherParams );

    /**
     * 录音号 （ 电话报警录音 电话调度 ）
     * */
    Boolean pushSoundRecord( SoundRecordBean soundRecordBean , Map<String, String > otherParams );

    /**
     * 火场文书
     * */
    Boolean pushIncidentDocument( DocumentBean documentBean  , Map<String, String > otherParams );

    /**
     * 现场信息
     * */
    Boolean pushIncidentLocale(LocaleBean localeBean , Map<String, String > otherParams );

    /**
     * 结案反馈
     * */
    Boolean pushIncidentAccident (AccidentBean accidentBean , Map<String, String > otherParams );





}
