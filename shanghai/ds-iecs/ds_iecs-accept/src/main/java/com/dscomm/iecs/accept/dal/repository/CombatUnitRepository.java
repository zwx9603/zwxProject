package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.CombatUnitItemEntity;
import com.dscomm.iecs.accept.dal.po.CombatUnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 * author:YangFuXi
 * Date:2021/6/8 Time:17:08
 **/
@Repository
public interface CombatUnitRepository extends JpaRepository<CombatUnitEntity,String> {
    /**
     * 查询作战单元明细
     * @param combatUnitId 作战单元id
     * @return 返回结果
     */
    @Query("select t from CombatUnitItemEntity t where t.combatUnitId=?1 and t.valid=1")
    List<CombatUnitItemEntity> findCombatUnitDetailsEntities(String combatUnitId);

    /**
     * 查询作战单元明细
     * @param combatUnitId 车辆id
     * @return 返回作战单元明细
     */
    @Query("select t,v from CombatUnitItemEntity t left join EquipmentVehicleEntity v on v.id=t.vehicleId where t.combatUnitId=?1 and t.valid=1")
    List<Object[]> findCombatUnitItemEntityByCombatUnitId(String combatUnitId);

    @Query("select t,v from CombatUnitItemEntity t left join EquipmentVehicleEntity v on v.id=t.vehicleId where t.combatUnitId in ?1 and t.valid=1")
    List<Object[]> findCombatUnitItemEntityByCombatUnitIds(List<String> combatIds);
}
