package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.CommanderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommanderRepository extends JpaRepository<CommanderEntity, String> {

    /**
     * 根據案件id查詢现场指挥员列表
     *
     * @param incidentId 案件id
     * @return 场指挥员列表
     */
    @Query(" select t from CommanderEntity t  where  t.valid = 1 and t.incidentId = ?1  ")
    List<CommanderEntity> findCommanderByIncidentId(String incidentId);

}
