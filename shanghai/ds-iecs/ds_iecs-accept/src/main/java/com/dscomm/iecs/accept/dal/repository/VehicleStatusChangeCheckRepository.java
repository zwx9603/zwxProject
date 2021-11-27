package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.VehicleStatusChangeCheckEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VehicleStatusChangeCheckRepository extends JpaRepository<VehicleStatusChangeCheckEntity, String> {

    @Query("select t from VehicleStatusChangeCheckEntity t where  t.valid = 1 ")
    List<VehicleStatusChangeCheckEntity> findVehicleStatusChangeCheck();

    @Query("select t from VehicleStatusChangeCheckEntity t where  t.valid = 1 and t.id = ?1")
    VehicleStatusChangeCheckEntity findVehicleStatusChangeCheckById(String id);
}
