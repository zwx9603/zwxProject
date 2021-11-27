package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.AcceptanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:受理单（报警记录） 数据库持久层服务
 */
@Repository
public interface AcceptanceRepository extends JpaRepository<AcceptanceEntity, String> {

    /**
     * 根据 警情id(案件id)获得 受理单（报警记录）
     *
     * @param incidentId 警情id(案件id)
     * @return 受理单（报警记录）列表
     */
    @Query(" select t from AcceptanceEntity t  where  t.valid = 1 and t.incidentId = ?1 order by  t.registerIncidentTime desc   ")
    List<AcceptanceEntity> findAcceptanceByIncidentId(String incidentId);


    /**
     * 根据 原始警情id(案件id)获得 受理单（报警记录）
     *
     * @param incidentId 警情id(案件id)
     * @return 受理单（报警记录）列表
     */
    @Query(" select t from AcceptanceEntity t  where  t.valid = 1 and t.originalIncidentNumber = ?1 order by  t.registerIncidentTime desc   ")
    List<AcceptanceEntity> findAcceptanceByOriginalIncidentId(String incidentId);

}
