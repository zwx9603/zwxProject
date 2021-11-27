package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.DrillPlanDispatchEntity;
import com.dscomm.iecs.accept.dal.po.DrillPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:集合演练方案 数据库持久层服务
 *
 */
@Repository
public interface DrillPlanRepository extends JpaRepository<DrillPlanEntity, String> {

    /**
     * 根据条件获得 集合演练方案 列表(分页)
     * @return
     */
    List<DrillPlanEntity> findDrillPlanCondition( String organizationId , List<String> incidentTypeCodes, String keyword,
                                                  Boolean whetherEnableStatus , Boolean whetherEnableTime , Long currentTime ,Boolean whetherPage,
                                   int page, int size) ;


    /**
     * 根据条件获得 集合演练方案 总数
     * @return
     */
    Integer  findDrillPlanConditionTotal( String organizationId , List<String> incidentTypeCodes, String keyword ,
                                          Boolean whetherEnableStatus , Boolean whetherEnableTime , Long currentTime ) ;



    /**
     * 根据集合演练方案id 获得 集合演练方案调派信息
     * @return
     */
    @Query(" select t from DrillPlanDispatchEntity t   where  t.valid = 1   and t.drillPlanId = ?1  order by  t.orderNum asc      " )
    List<DrillPlanDispatchEntity> findDrillPlanDispatchByDrillPlanId(String drillPlanId) ;

    /**
     * 根据 集合演练方案id 移除 集合演练方案调派信息
     * @return
     */
    @Modifying(clearAutomatically = true)
    @Query("  update  DrillPlanDispatchEntity t  set t.valid = 0 where   t.drillPlanId = ?1      " )
    void removeDrillPlanDispatchByDrillPlanId( String drillPlanId) ;

}
