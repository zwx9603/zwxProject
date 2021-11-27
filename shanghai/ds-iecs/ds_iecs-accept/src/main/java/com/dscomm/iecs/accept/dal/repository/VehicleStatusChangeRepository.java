package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.VehicleStatusChangeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/5/1 13:49
 * @Describe 车辆状态变动查询
 */
@Repository
public interface VehicleStatusChangeRepository extends JpaRepository<VehicleStatusChangeEntity,String> {


    /**
     * 查询车辆状态变动记录
     * @param brigadeOrganizationId 所属大队
     * @param organizationId 所属机构
     * @param vehicleState 车辆状态
     * @param whetherPage 是否分页
     * @param page 第几页
     * @param size 每页大小
     * @return
     */
    List<Object[]> findVehicleChangeList(List<String> states,String brigadeOrganizationId,
                                                           String organizationId, String vehicleState, Boolean whetherPage, int page, int size);


    /**
     * 总数
     * @param brigadeOrganizationId
     * @param organizationId
     * @param vehicleState
     * @return
     */
    Integer findVehicleChangeTotal(List<String> states,String brigadeOrganizationId, String organizationId,
                                        String vehicleState);
}
