package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.BlacklistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:黑名单 数据库持久层服务
 */
@Repository
public interface BlacklistRepository extends JpaRepository<BlacklistEntity, String> {


    List<BlacklistEntity> findBlacklistCondition( String keyword , String phoneKeyword , String personNameKeyword  ,  Long  startTime  ,  Long  endTime ,Boolean whetherPage,
                                                 int page, int size );


    Integer findBlacklistConditionTotal ( String keyword , String phoneKeyword , String personNameKeyword  , Long  startTime  ,  Long  endTime   );


    @Query(" select t from BlacklistEntity t   where   t.phoneNumber = ?1  ")
    List<BlacklistEntity> findBlacklistByPhoneNumber ( String phoneNumber   );


}
