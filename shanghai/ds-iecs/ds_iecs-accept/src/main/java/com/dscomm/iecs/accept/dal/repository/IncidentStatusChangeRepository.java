package com.dscomm.iecs.accept.dal.repository;


import com.dscomm.iecs.accept.dal.po.IncidentStatusChangeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 案件状态 底层服务
 */
@Repository
public interface IncidentStatusChangeRepository extends JpaRepository<IncidentStatusChangeEntity, String> {


    /**
     * 根据警情id 获得 案件状态变更记录
     *
     * @param incidentId  警情id
     * @return 返回结果
     */
    @Query(" select t from IncidentStatusChangeEntity t   where  t.valid = 1   and t.incidentId = ?1  ")
    List<IncidentStatusChangeEntity> findIncidentStatusChange ( String incidentId );
}
