package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.ParticipantFeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:参战人员反馈 数据库持久层服务
 */
@Repository
public interface ParticipanFeedbackRepository extends JpaRepository<ParticipantFeedbackEntity, String> {


    /**
     * 根据 警情id(案件id)获得 参战人员反馈
     *
     * @param incidentId 警情id(案件id)
     * @return  返回结果
     */
    @Query(" select t from ParticipantFeedbackEntity t  where  t.valid = 1 and t.incidentId = ?1 order by  t.checkTime desc , t.feedbackCheckTime desc    ")
    List<ParticipantFeedbackEntity> findParticipantFeedbackByIncidentId( String incidentId );


    /**
     * 根据 原始警情id(案件id)获得 参战人员反馈
     *
     * @param incidentId 警情id(案件id)
     * @return  返回结果
     */
    @Query(" select t from ParticipantFeedbackEntity t  where  t.valid = 1 and t.originalIncidentNumber = ?1 order by  t.checkTime desc , t.feedbackCheckTime desc    ")
    List<ParticipantFeedbackEntity> findParticipantFeedbackByOriginalIncidentId( String incidentId );

    /**
     * 根据 警情id(案件id)获得 参战人员反馈
     *
     * @param incidentId 警情id(案件id)
     * @return  返回结果
     */
    @Query(" select t from ParticipantFeedbackEntity t  where  t.valid = 1 and t.incidentId = ?1 " +
            " and  t.vehicleId in ( ?2 ) " +
            "order by  t.checkTime desc , t.feedbackCheckTime desc    ")
    List<ParticipantFeedbackEntity> findParticipantFeedbackByIncidentId( String incidentId   , List<String> vehicleIds );


    /**
     * 根据 警情id(案件id)获得 参战人员反馈
     *
     * @param incidentId 警情id(案件id)
     * @return  返回结果
     */
    @Query(" select t from ParticipantFeedbackEntity t  where  t.valid = 1 and t.incidentId = ?1 " +
            " and t.handleId = ?2  and  t.vehicleId in ( ?3 ) " +
            "order by  t.checkTime desc , t.feedbackCheckTime desc    ")
    List<ParticipantFeedbackEntity> findParticipantFeedbackByIncidentId( String incidentId , String handleId  , List<String> vehicleIds );


    /**
     * 根据id集合获取参战人员信息
     * @param valid 有效性（1 为有效）
     * @param ids id集合
     * @return   参战人员信息
     * */
    List<ParticipantFeedbackEntity> findByValidAndIdIn (Integer valid,List<String> ids);

    /**
     * 根据警情，处警，车辆id，是否返回获取参战人员信息
     * @param valid 有效性（1为有效）
     * @param vehicleId 车辆id
     * @param incidentId 警情id
     * @param handleId 处警id
     * @param whetherFeedBack 是否返回（0 否 1 是）
     * @return   参战人员信息
     * */
    List<ParticipantFeedbackEntity> findByValidAndIncidentIdAndHandleIdAndVehicleIdAndWhetherFeedback (
            Integer valid,String incidentId,String handleId,String vehicleId,Integer whetherFeedBack);

    /**
     * 根据案件id和参战人员id集合获取参战人员信息
     * @param incidentId 案件id
     * @param personIds 人员id集合
     * @return 参战人员集合
     */
    @Query("select t from ParticipantFeedbackEntity t where t.incidentId=?1 and t.personId in ?2 and t.valid=1")
    List<ParticipantFeedbackEntity> findParticipantFeedbackEntitiesByIncidentIdAndPersonIds(String incidentId,List<String> personIds);



}
