package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.EarlyWarningEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:预警信息 数据库持久层服务
 */
@Repository
public interface EarlyWarningRepository extends JpaRepository<EarlyWarningEntity, String> {

    @Query(" select t.receiveOrganizationId  from EarlyWarningEntity t  " +
            " where t.valid=1 and t.incidentId =?1 order by t.sendTime desc  ")
    List<String> findEarlyWarningOrganizationByIncidentId(String incidentId);


    @Query(" select t  from EarlyWarningEntity t  " +
            " where t.valid=1 and t.id  in ( ?1 )   order by t.sendTime desc  ")
    List<EarlyWarningEntity> findEarlyWarning( List<String> earlyWarningIds );

    /**
     * 根据案件id 查询 预警信息
     * @param incidentId 案件id
     * @return 预警信息
     */
    @Query(" select t  from EarlyWarningEntity t  " +
            " where t.valid=1 and t.incidentId  = ?1   order by t.sendTime desc  ")
    List<EarlyWarningEntity> findEarlyWarningIncidentId( String incidentId );

    /**
     * 根据案件id 查询 预警信息
     * @param incidentId 案件id
     * @return 预警信息
     */
    @Query(" select t  from EarlyWarningEntity t  " +
            " where t.valid=1 and t.incidentId  = ?1 and t.receiveOrganizationId = ?2 and t.earlyWarningType in ('0','1') order by t.sendTime desc  ")
    List<EarlyWarningEntity> findEarlyWarningByIncidentIdAndOrganizationId( String incidentId  , String organizationId  );



    List<EarlyWarningEntity> findEarlyWarning (String incidentId , String earlyWarningType , List<String> receiveOrganizationIds  );


}
