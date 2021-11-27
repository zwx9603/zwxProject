package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.PhoneLibraryEntity;
import com.dscomm.iecs.accept.dal.po.TelephoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:电话报警记录 数据库持久层服务
 */
@Repository
public interface TelephoneRepository extends JpaRepository<TelephoneEntity, String> {

    /**
     * 根据 警情id(案件id)获得 电话报警记录
     *
     * @param incidentId 警情id(案件id)
     * @return 电话报警记录 列表
     */
    @Query(" select t from TelephoneEntity t  where  t.valid = 1 and t.incidentId = ?1 order by  t.ringingTime desc   ")
    List<TelephoneEntity> findTelephoneByIncidentId(String incidentId);



    /**
     * 根据 原始警情id(案件id)获得 电话报警记录
     *
     * @param incidentId 警情id(案件id)
     * @return 电话报警记录 列表
     */
    @Query(" select t from TelephoneEntity t  where  t.valid = 1 and t.originalIncidentNumber = ?1 order by  t.ringingTime desc   ")
    List<TelephoneEntity> findTelephoneByOriginalIncidentId(String incidentId);



    /**
     * 根据 电话获得  电话用户 与 装机地址
     *
     * @param phoneNumber 电话号码
     * @return 电话信息
     */
    @Query(" select t from PhoneLibraryEntity t  where  t.valid = 1 and t.phoneNumber = ?1     ")
    List<PhoneLibraryEntity> findPhoneLibraryByPhoneNumber(String phoneNumber );



    /**
     * 根据 电话获得  电话用户 与 装机地址
     *
     * @param phoneNumber 电话号码
     * @return 电话信息
     */
    @Query(" select count(1) from TelephoneEntity t  where  t.valid = 1 and t.callingNumber = ?1 and t.ringingTime >= ?2  and t.ringingTime < ?3 ")
    Long findAlarmCount(String phoneNumber,Long startTime,Long targetTime);




    /**
     * 根据 警情id(案件ids)获得 电话报警记录 录音信息
     *
     * @param incidentIdList 警情ids(案件ids)
     * @return 电话报警记录 列表
     */
    @Query(" select t from TelephoneEntity t  where  t.valid = 1 and t.incidentId in ( ?1 ) order by  t.ringingTime desc   ")
    List<TelephoneEntity> findSoundRecordMapByIncidentIdList ( List<String> incidentIdList );


    /**
     * 根据 根据条件查询电话记录
     *

     * @return 电话报警记录 列表
     */
    List<TelephoneEntity> findTelephoneCondition ( Long   startTime , Long   endTime  ,  String seatNumber ,  String personNumber ,
                                                   String  phoneKeyword ,String  nameKeyword  ,
                                              Boolean whetherPage , int page , int size  );

    /**
     * 根据 根据条件查询电话记录 总数
     *

     * @return 电话报警记录 列表
     */
    Integer findTelephoneConditionTotal ( Long   startTime , Long   endTime  ,  String seatNumber ,  String personNumber ,
                                          String  phoneKeyword ,String  nameKeyword    );
}
