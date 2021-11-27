package com.dscomm.iecs.basedata.dal.repository;

import com.dscomm.iecs.basedata.dal.po.VehiclePersonsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 描述： 车辆的数据底层服务
 */
@Repository
public interface EquipmentVehiclePersonRepository extends JpaRepository<VehiclePersonsEntity, String> {


    /**
     * 根据车辆ids 获得 车载人员信息
     *
     * @return 返回结果
     */
    @Query(" select  t, m  " +
            "  from VehiclePersonsEntity t " +
            "  left join PersonMailEntity m on t.personId = m.id   " +
            "   where t.valid = 1  and  t.vehicleId  in ( ?1 )  " )
    List<Object[] > findVehiclePersonsMailByVehicleIds(List<String> vehicleIds);


    /**
     * 根据车辆ids 获得 车载人员信息
     *
     * @return 返回结果
     */
    @Query(" select  vehicle , person " +
            "  from EquipmentVehicleEntity vehicle  " +
            "  left join VehiclePersonsEntity person  on vehicle.id = person.vehicleId and person.valid = 1    " +
            "   where vehicle.valid = 1  and   vehicle.id  in ( ?1 )  " )
    List<Object[] > findVehiclePersonsByVehicleIds(List<String> vehicleIds);


    /**
     * 根据车辆ids 获得 车载人员信息
     *
     * @return 返回结果
     */
    @Query(" select  vehicle , person " +
            "  from EquipmentVehicleEntity vehicle  " +
            "  left join VehiclePersonsEntity person  on vehicle.id = person.vehicleId and person.valid = 1    " +
            "   where vehicle.valid = 1  and  vehicle.organizationId  = ?1 order by vehicle.vehicleOrderNum asc   " )
    List<Object[] > findVehiclePersonsByOrganizationId( String organizationId );


    /**
     * 根据车辆ids 获得 车载人员信息
     *
     * @return 返回结果
     */
    @Query(" select  t   from VehiclePersonsEntity t  where t.valid = 1  and  t.vehicleId  in ( ?1 )   " )
    List<VehiclePersonsEntity> findVehiclePersonsByVehicleIdList (  List<String> vehicleIds   );

    @Query("select t.vehicleId,t.personId,t.personName,duty.organizationId,duty.contactNumber from VehiclePersonsEntity t left join OnDutySummaryEntity duty on duty.onDutyPersonId=t.personId where t.vehicleId in ?1 and duty.onDutyTime>?2 and duty.onDutyTime<?3")
    List<Object[]> findVehiclePersonByDutyTimeAndVhicleIds(List<String> vehicleIds,Long start,Long endTime);

    @Query("select t.vehicleId,t.personId,t.personName,vehicle.organizationId,'' as lxdh from VehiclePersonsEntity t left join EquipmentVehicleEntity vehicle on vehicle.id=t.vehicleId where t.vehicleId in ?1 ")
    List<Object[]> findVehiclePersonByVhicleIds(List<String> vehicleIds);
}
