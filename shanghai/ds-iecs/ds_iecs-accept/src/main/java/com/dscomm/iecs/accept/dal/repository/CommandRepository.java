package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.CommandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Description:指挥服务
 *
 */
@Repository
public  interface CommandRepository extends JpaRepository<CommandEntity, String> {

    @Query("select t from CommandEntity t where t.incidentId=?1 and t.valid=1")
    List<CommandEntity> findCommandEntitiesByIncidentId(String incidentId);

}
