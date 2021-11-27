package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.HandleOrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:调派信息（调派单位信息） 数据库持久层服务
 */
@Repository
public interface HandleOrganizationRepository extends JpaRepository<HandleOrganizationEntity, String> {

    /**
     * 根据案件ID获取 调派信息 （ 调派单位信息 ）
     *
     * @param incidentId 案件ID
     * @return 调派信息（调派单位信息）列表
     */
    @Query(" select handleOrganization,handle  from HandleOrganizationEntity handleOrganization " +
            " left join HandleEntity handle on handle.id = handleOrganization.handleId  and handle.valid = 1  " +
            " where handleOrganization.valid = 1   and handleOrganization.incidentId = ?1 " +
            " order by   handle.handleBatch  asc ,  handle.handleStartTime asc " )
    List<Object[]> findHandleOrganizationByIncidentId(String incidentId);

    /**
     * 根据案件ID获取 调派信息 （ 调派单位信息 ）
     *
     * @param incidentId 案件ID
     * @return 调派信息（调派单位信息）列表
     */
    @Query(" select handleOrganization,handle  from HandleOrganizationEntity handleOrganization " +
            " left join HandleEntity handle on handle.id = handleOrganization.handleId  and handle.valid = 1  " +
            " where handleOrganization.valid = 1   and handleOrganization.incidentId = ?1 and handleOrganization.organizationId = ?2  " +
            " order by   handle.handleBatch  asc ,  handle.handleStartTime asc " )
    List<Object[]> findHandleOrganizationByIncidentIdAndOrganizationId(String incidentId , String organizationId   );




    /**
     * 根据案件ID获取 出动单位id 列表
     *
     * @param incidentId 案件ID
     * @return 出动单位id 列表
     */
    @Query(" select distinct  t.organizationId  from HandleOrganizationEntity t  where  t.valid = 1 and t.incidentId = ?1  order by organizationId desc  ")
    List<String> findOrganizationIdByIncidentId(String incidentId);


    /**
     * 根据案件ID获取 出动单位id 列表
     *
     * @param incidentId 案件ID
     * @return 出动单位id 列表
     */
    @Query(" select distinct  t.organizationId  from HandleOrganizationEntity t  where  t.valid = 1 and t.incidentId = ?1 and t.organizationId = ?2  order by organizationId desc  ")
    List<String> findOrganizationIdByIncidentIdAndOrganizationId (String incidentId  , String organizationId  );

    /**
     * 2021-04-26 根据案件id 获取调派单位列表
     * @param incidentId
     * @return
     */
    @Query("select  distinct handleOrg.organizationId from  HandleOrganizationEntity handleOrg  " +
            "where handleOrg.valid= 1 and  (handleOrg.incidentId = ?1 or handleOrg.originalIncidentNumber = ?1) ")
    List<String> findOrganizationIdsByIncidentId(String incidentId);





    /**
     * 根据处警记录ID获取 调派信息 （ 调派单位信息 ）
     *
     * @param handleId 处警记录ID
     * @return 调派信息（调派单位信息）列表
     */
    @Query(" select t from HandleOrganizationEntity t  where  t.valid = 1 and t.handleId = ?1 order by t.updatedTime desc ")
    List<HandleOrganizationEntity> findHandleOrganizationByHandleId(String handleId);


    /**
     * 根据处警记录ID获取 调派信息 （ 调派单位信息 ）
     *
     * @param handleId 处警记录ID
     * @return 调派信息（调派单位信息）列表
     */
    @Query(" select t from HandleOrganizationEntity t  where  t.valid = 1 and t.handleId = ?1 and t.organizationId = ?2    ")
    HandleOrganizationEntity  findHandleOrganizationByHandleIdAndOrganizationId(String handleId , String organizationId );


    /**
     * 获得 到达收处警记录超时的id
     *
     * @param currentTime
     * @param overTime
     * @return 调派信息（调派单位信息）列表
     */
    @Query(" select distinct  t.handleId from HandleOrganizationEntity t  where  t.valid = 1 " +
            " and t.systemFeedbackTime is null    " +
            " and (  (  ?1 -  t.noticeTime    )  >= ?2  )   " +
            " and  t.overTimeHandleTime is  null  " +
            "   ")
    List<String>   findNoticeOverTimeHandleIds (   Long currentTime  , Long overTime  );


    /**
     * 获得超时未签收处警记录的id
     *
     * @param currentTime
     * @param overTime
     * @return 调派信息（调派单位信息）列表
     */
    @Query(" select distinct  t.handleId from HandleOrganizationEntity t  where  t.valid = 1 " +
            " and t.personFeedbackPersonTime is null    " +
            " and (  (  ?1 -  t.noticeTime    )  >= ?2  )   " +
            " and  t.overTimeHandleTime is  null  " +
            "   ")
    List<String>   findOverTimeHandleIds (   Long currentTime  , Long overTime  );


    /**
     * 修改 超时任务执行时间
     *
     * @param currentTime
     * @param handleIds
     * @return 调派信息（调派单位信息）列表
     */
    @Modifying(clearAutomatically = true)
    @Query("  update  HandleOrganizationEntity t  set t.overTimeHandleTime = ?1 where  t.valid = 1 and " +
            "  t.overTimeHandleTime is null   and  t.handleId in ( ?2 )  " )
    void   updateOverHandleTimeByIds (   Long currentTime  , List<String> handleIds   );


    /**
     * 根据处警记录IDs获取 调派信息 （ 调派单位信息 ）
     *
     * @param handleOrganizationIds 处警记录ID
     * @return 调派信息（调派单位信息）列表
     */
    @Query(" select t from HandleOrganizationEntity t  where  t.valid = 1 and t.id in ( ?1 )       ")
    List<HandleOrganizationEntity>  findHandleOrganizationByIds (  List<String> handleOrganizationIds );

}
