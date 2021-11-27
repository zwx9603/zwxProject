package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.SecurityHintsEntity;
import com.dscomm.iecs.accept.dal.po.SecurityHintsReceiverEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author: zs
 * @Date: 12:59 2020/7/23
 * desc: 特别警示
 */
public interface SecurityRepository extends JpaRepository<SecurityHintsEntity,String> {

    /**
     * 根据警情id
     * @param incidentId
     * @return
     */
    @Query("select s from SecurityHintsEntity s where s.valid=1 and  s.incidentId=?1   ")
    List<SecurityHintsEntity> findSecurityHints(String incidentId);


    /**
     * 根据警情id
     * @param securityHintsIds
     * @return
     */
    @Query("select s from SecurityHintsEntity s where s.valid=1 and  s.id in ( ?1 )   ")
    List<SecurityHintsEntity> findSecurityHints( List<String>   securityHintsIds);


    /**
     * 根据警情id  安全提示id  获得接收对象
     * @param incidentId
     * @return
     */
    @Query("select r from   SecurityHintsReceiverEntity r where " +
            " r.valid=1 and  r.incidentId=?1  and r.securityHintsId in ( ?2  )   ")
    List<SecurityHintsReceiverEntity> findSecurityHintsReceiver( String incidentId  , List<String> securityHintsId );



    /**
     * 根据警情id  安全提示id  获得接收对象
     * @param incidentId
     * @return
     */
    @Query("select r from   SecurityHintsReceiverEntity r where " +
            " r.valid=1 and  r.incidentId=?1  and r.receiverId = ?2   ")
    List<SecurityHintsReceiverEntity> findSecurityHintsReceiver( String incidentId  ,  String  receiverId );


    /**
     * 根据ids 获得安全提示对象信息
     * @param securityHintsReceiverIs
     * @return
     */
    @Query("select t from SecurityHintsReceiverEntity t where t.valid=1 and t.id in ( ?1 ) ")
    List<SecurityHintsReceiverEntity> findSecurityHintsReceiverByIds (List<String > securityHintsReceiverIs );

}
