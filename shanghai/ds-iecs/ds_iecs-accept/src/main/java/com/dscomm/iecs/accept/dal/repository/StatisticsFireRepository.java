package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.IncidentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 描述： 统计查询服务
 */
@Repository
public interface StatisticsFireRepository extends JpaRepository<IncidentEntity, String> {


    /**
     * 统计各个等级重点单位数量
     * @return map集合
     * */
    @Query("select count(k.id) as count ,k.unitLevelCode as unitLevelCode  from KeyUnitEntity k where  k.valid = 1 group by k.unitLevelCode")
    List<Map<String,Object>> countKeyUnitLevel();


}
