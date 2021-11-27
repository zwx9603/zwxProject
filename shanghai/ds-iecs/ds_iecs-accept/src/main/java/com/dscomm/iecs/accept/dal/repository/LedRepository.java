package com.dscomm.iecs.accept.dal.repository;



import com.dscomm.iecs.accept.dal.po.LedVehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LedRepository extends JpaRepository<LedVehicleEntity, String> {

    List<Object[]> findLedVehicleList(String categoryCode, Integer isDisPlay, String name, String oldName, Boolean whetherPage, int page, int size);

    List<Object[]> findLedOrganizationList(String categoryCode, Integer isDisPlay, String name, String oldName, Boolean whetherPage, int page, int size);

    int findLedVehicleCount(String categoryCode, Integer isDisPlay, String name, String oldName);

    int findLedOrganizationCount(String categoryCode, Integer isDisPlay, String name, String oldName);

//    @Modifying(clearAutomatically = true)
//    @Query("update  OrganizationEntity t set t.isDisPlay=1 where t.id in ?1 and t.valid=1")
//    void updateOrganizationLedCode(List<String> ids);

//    @Modifying(clearAutomatically = true)
//    @Query("update EquipmentVehicleEntity t set t.isDisPlay=1 where t.id in ?1 and t.valid=1")
//    void updateVehicleLedCode(List<String> ids);
}
