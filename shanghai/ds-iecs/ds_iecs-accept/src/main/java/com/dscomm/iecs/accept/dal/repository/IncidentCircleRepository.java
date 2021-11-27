package com.dscomm.iecs.accept.dal.repository;


import com.dscomm.iecs.accept.dal.po.IncidentCircleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 救援圈设定
 */
@Repository
public interface IncidentCircleRepository extends JpaRepository<IncidentCircleEntity, String> {

    /**
     * 根据 事件id
     * 获得救援圈设定
     * @param incidentId 案件id
     * @return 救援圈设定
     */
    @Query("select t  from IncidentCircleEntity t where t.incidentId=?1 and t.valid=1")
    List<IncidentCircleEntity> getIncidentCircleID(String incidentId);

}
