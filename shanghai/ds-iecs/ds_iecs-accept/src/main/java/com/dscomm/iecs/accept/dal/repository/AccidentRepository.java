package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.AccidentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:事故情况 数据库持久层服务
 */
@Repository
public interface AccidentRepository extends JpaRepository<AccidentEntity, String> {

    /**
     * 根据 警情id(案件id)获得 最新事故情况
     *
     * @param incidentId 警情id(案件id)
     * @return 返回结果
     */
    @Query("  select t from AccidentEntity t  where  t.valid = 1 and t.incidentId = ?1  order by t.updatedTime desc      ")
    List<AccidentEntity> findAccidentByIncidentId(String incidentId);

}
