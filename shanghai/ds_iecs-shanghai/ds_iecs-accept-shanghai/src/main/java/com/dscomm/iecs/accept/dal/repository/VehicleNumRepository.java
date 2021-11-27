package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.AssistanceEntity;
import com.dscomm.iecs.accept.dal.po.GradeEntity;
import com.dscomm.iecs.accept.dal.po.VehicleNumEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
* @author Zhao Wenxue
* */
@Repository
public interface VehicleNumRepository extends JpaRepository<VehicleNumEntity,String> {

    /*
    * 查询应派车辆、已派车辆、本次调派车辆的信息
    * */
    @Query("select count(t1.id) as num from VehicleNumEntity t1 " +
            "where t1.ajbh = (select t.incidentId from HandleEntity t where t.id = ?1 ) and " +
            "t1.clztdm_mh <= '0305' and t1.zblxdm = ?2")
    int queryVehicleNumInfo(String id,String cllx);

    /*
    * 查询可以派遣的车辆
    * */
    @Query("select t.zbmc ,t.zblxdm from VehicleNumEntity t where t.xfjyjg_tywysbm = ?1 and t.clztdm_mh ='0200'")
    List<VehicleNumEntity> queryCarInfo(String zddm);
}
