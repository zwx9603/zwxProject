package com.dscomm.iecs.accept.service;


import com.dscomm.iecs.accept.graphql.inputbean.ParticipantBackInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.ParticipantSaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.ParticipantFeedbackBean;
import com.dscomm.iecs.basedata.graphql.typebean.VehiclePersonsBean;

import java.util.List;

/**
 * 描述：参战人员反馈 服务
 */
public interface ParticipantFeedbackService {


    /**
     * 根据车辆ids 获得车载人员（值班 计划）
     * @return
     */
    List<VehiclePersonsBean>  findVehiclePersons( String incidentId , List<String>  vehicleIds   );


    /**
     *  保存 参战人员信息 登记
     * @param queryBean 参战人员反馈保存参数
     * @return 返回结果
     */
    List<ParticipantFeedbackBean> saveParticipant(ParticipantSaveInputInfo queryBean );

    /**
     *  保存 参战人员信息 登记
     * @param queryBean 参战人员反馈保存参数
     * @return 返回结果
     */
    List<ParticipantFeedbackBean> saveParticipant(List<ParticipantSaveInputInfo> queryBean );


    /**
     *  根据案件id 获得 参战人员信息
     * @param incidentId  案件id
     * @return 返回结果
     */
    List<ParticipantFeedbackBean> findIncidentParticipant( String  incidentId ,  List<String>  vehicleIds  );


    /**
     * 车辆实际返回人员保存
     * @param inputInfo 参战人员反馈id返回清点时间
     * */
   Boolean saveParticipantBack(ParticipantBackInputInfo inputInfo);

    /**
     * 更改参战人员状态
     * @param inputInfo 参战人员信息
     * @return 返回更改后的参战人员信息
     */
    List<ParticipantFeedbackBean> changeParticipantPersonState(ParticipantBackInputInfo inputInfo);


    /**
     *  案件合并 操作
     * @param sourceIncidentId 原案件id
     * @param targetIncidentId 目标案件id
     * @return 返回结果
     */
   void  saveIncidentParticipantMerge ( String sourceIncidentId, String targetIncidentId  );


    /**
     *  案件 拆分 操作
     * @param incidentId  案件id
     * @return 返回结果
     */
    void saveIncidentParticipantSplit( String  incidentId  );




}
