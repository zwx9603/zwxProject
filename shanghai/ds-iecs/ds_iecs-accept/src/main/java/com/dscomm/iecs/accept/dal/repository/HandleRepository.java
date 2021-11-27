package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.HandleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:处警记录（调派记录） 数据库持久层服务
 */
@Repository
public interface HandleRepository extends JpaRepository<HandleEntity, String> {


    /**
     * 根据 警情id(案件id)获得 最大处警批次
     *
     * @param incidentId 警情id(案件id)
     * @return 处警记录（调派记录）列表
     */
    @Query(" select count( distinct  t.handleBatch )  from HandleEntity t  where  t.valid = 1 and t.incidentId = ?1   " )
    Integer findHandleBatchByIncidentId(String incidentId);


    /**
     * 根据 警情id(案件id)获得 处警记录（调派记录）
     *
     * @param incidentId 警情id(案件id)
     * @return 处警记录（调派记录）列表
     */
    @Query(" select t from HandleEntity t  where  t.valid = 1 and t.incidentId = ?1 order by  t.handleStartTime desc   ")
    List<HandleEntity> findHandleByIncidentId(String incidentId);


    /**
     * 根据 警情id(案件id)获得 处警记录（调派记录）
     *
     * @param incidentId 警情id(案件id)
     * @return 处警记录（调派记录）列表
     */
    @Query(" select t from HandleEntity t , HandleOrganizationEntity e  where  t.valid = 1  and e.valid = 1 and t.id = e.handleId" +
            " and t.incidentId = ?1 and  e.organizationId = ?2 order by  t.handleStartTime desc   ")
    List<HandleEntity> findHandleByIncidentIdAndHandleOrganizationId(String incidentId , String organizationId );


    /**
     * 根据 警情id(案件id)获得 处警记录（调派记录）
     *
     * @param originalIncidentNumber 原始警情id(案件id)
     * @return 处警记录（调派记录）列表
     */
    @Query(" select t from HandleEntity t  where  t.valid = 1 and t.originalIncidentNumber = ?1 order by  t.handleStartTime desc   ")
    List<HandleEntity> findHandleByOriginalIncidentNumber(String originalIncidentNumber);

    /**
     * 查询处警记录id
     * @param incidentId 案件编号
     * @return 返回处警记录id
     */
    @Query("select  t.id from HandleEntity t  where  t.valid = 1 and t.incidentId=?1 order by t.createdTime desc")
    List<String> findHandleIdIdByIncidentId(String incidentId);








}
