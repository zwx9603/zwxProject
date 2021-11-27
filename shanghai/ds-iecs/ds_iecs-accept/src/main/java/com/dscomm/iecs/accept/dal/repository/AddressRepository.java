package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 描述:辅助地址
 *
 * @author XuHao
 * Date Time 2020/2/21 13:54
 */
public interface AddressRepository extends JpaRepository<AddressEntity, String> {
    /**
     * 根据事件id和地址类型查找辅助地址
     *
     * @param incidentId     事件id
     * @param addressType 地址类型
     * @return 辅助地址列表
     */
    @Query("select a from AddressEntity a where a.incidentId = :incidentId and a.addressType = :addressType")
    List<AddressEntity> findAddressByIncidentIdAndAddressType(@Param("incidentId") String incidentId, @Param("addressType") Integer addressType);

    @Modifying
    @Query("update AddressEntity  set address =?1, longitude =?2, latitude =?3, saveTime =?4, sjc =?4 where incidentId =?5 and addressType =?6")
    void updateAddress(String address, String longitude, String latitude, Long saveTime, String incidentId, Integer addressType);

    /**
     * 根据事件id和地址类型删除辅助地址
     *
     */
    @Modifying
    @Query("delete  from AddressEntity t where t.incidentId=?1 and t.addressType=?2")
    void deleteAddressByIncidentIdAndAddressType(String incidentId, Integer addressType);

    /**
     * 根据事件id查找辅助地址
     *
     * @param incidentId     事件id
     * @return 辅助地址列表
     */
    @Query("select a from AddressEntity a where a.incidentId = :incidentId")
    List<AddressEntity> findAddressByIncidentId(@Param("incidentId") String incidentId);
}
