package com.dscomm.iecs.basedata.dal.repository;

import com.dscomm.iecs.basedata.dal.po.PlanDispatchEntity;
import com.dscomm.iecs.basedata.dal.po.PlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:预案 数据库持久层服务
 *
 */
@Repository
public interface PlanRepository extends JpaRepository<PlanEntity, String> {

    /**
     * 根据预案id 获得 预案调派信息
     * @return
     */
    @Query(" select t from PlanDispatchEntity t   where  t.valid = 1   and t.planId = ?1  order by  t.dispatchOrderNum asc      " )
    List<PlanDispatchEntity> findPlanDispatchByPlanId(String planId ) ;


    /**
     * 根据重点单位id 获得预案列表
     * @return
     */
    @Query(" select t from PlanEntity t   where  t.valid = 1   and t.keyUnitId = ?1   order by  t.planOrderNum asc    " )
    List<PlanEntity> findPlanByKeyUnitId(String keyUnitId ) ;


    /**
     * 根据重点单位id列表 获得预案列表
     * @return
     */
    @Query(" select t from PlanEntity t   where  t.valid = 1   and t.keyUnitId in ?1   order by  t.planOrderNum asc    " )
    List<PlanEntity> findPlanByKeyUnitIds(List<String> keyUnitIds ) ;

    /**
     * 根据预案id 获得 预案调派信息
     * @return
     */
    @Query(" select t from PlanDispatchEntity t , PlanEntity p where  t.valid = 1  and p.valid = 1 and t.planId = p.id " +
            "   and p.keyUnitId = ?1  order by  p .planOrderNum asc  , t.planId asc ,  t.dispatchOrderNum asc        " )
    List<PlanDispatchEntity> findPlanDispatchByKeyUnitId( String keyUnitId  ) ;




    /**
     * 根据重点单位id的集合 合 查询 预案中原调派车辆信息
     *
     * @return
     */
    @Query(" select t.planId , t.vehicleId from PlanEntity p , PlanDispatchEntity t   " +
            " where p.valid = 1 and  t.valid = 1    " +
            " and p.id = t.planId " +
            " and p.keyUnitId = ?1  " +
            " order by  t.updatedTime desc , t.planId asc ,  t.dispatchOrderNum asc        ")
    List<Object []> findPlanDispatchVehicleKeyUnitId( String  keyUnitId  );


    /**
     * 根据重点单位id的集合、车辆状态集合 查询 预案中可调派车辆信息
     *
     * @return
     */
    @Query(" select  t.planId , t.vehicleId   from PlanEntity p , PlanDispatchEntity t ,EquipmentVehicleEntity v" +
            "  where  p.valid = 1 and  t.valid = 1  and v.valid = 1 " +
            "  and t.vehicleId = v.id and p.id = t.planId  and t.vehicleId = v.id " +
            "  and p.keyUnitId = ?1 and v.vehicleStatusCode in ?2  " +
            "  order by  t.updatedTime desc , t.planId asc ,  t.dispatchOrderNum asc        ")
    List<Object []> findPlanDispatchVehicleKeyUnitIdAndVehicleStatus( String  keyUnitId , List<String> vehicleStatusCodeLis  );


    /**
     * 根据ids获取预案列表
     * */
    @Query(" select p  from PlanEntity p   where  p.valid = 1 and  p.id in ( ?1 )       ")
    List<PlanEntity> findPlanIds( List<String> id );

}
