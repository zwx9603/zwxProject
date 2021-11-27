package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.UnTrafficAlarmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述: 非话务报警 数据库持久层服务
 *
 */
@Repository
public interface UnTrafficAlarmRepository  extends JpaRepository<UnTrafficAlarmEntity, String> {

    /**
     * 根据 警情id(案件id)获得 非话务报警信息
     *
     * @param incidentId 警情id(案件id)
     * @return 非话务报警信息
     */
    @Query(" select t from UnTrafficAlarmEntity t  where  t.valid = 1 and t.incidentId = ?1 order by  t.alarmTime desc   ")
    List<UnTrafficAlarmEntity> findUnTrafficAlarmByIncidentId(String incidentId);


    /**
     * 根据  电话号码 最新非话务报警信息
     *
     * @param alarmPhone 电话号码
     * @return 非话务报警信息
     */
    @Query(" select t from UnTrafficAlarmEntity t  where  t.valid = 1 and t.alarmPhone = ?1 order by  t.alarmTime desc   ")
    List<UnTrafficAlarmEntity> findUnTrafficAlarmByAlarmPhone ( String alarmPhone);


    /**
     * 根据  转警系统 警情id  获得转警信息 id
     *
     * @param originalIncidentNumber 转警系统id
     * @return 非话务报警信息
     */
    @Query(" select t from UnTrafficAlarmEntity t  where  t.valid = 1 and t.originalIncidentNumber = ?1 order by  t.alarmTime desc   ")
     List<UnTrafficAlarmEntity>  findUnTrafficAlarmByOriginalIncidentNumber ( String originalIncidentNumber);


}
