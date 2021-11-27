package com.dscomm.iecs.accept.dal.repository;


import com.dscomm.iecs.basedata.dal.po.EquipmentVehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DistrictChartRepository extends JpaRepository<EquipmentVehicleEntity,String> {

//	/**
//	 * 辖区警力统计-大队版
//	 * @param orgId 机构ID
//	 * @param vehicleStatus 车辆状态代码
//	 * @return 返回车辆id
//	 */
//	@Query("select v.id from EquipmentVehicleEntity v left join  OrganizationEntity o on v.organizationId=o.id where "
//			+ "v.organizationId=?1 and v.valid=1" +
//			"and v.vehicleStatusCode = ?2")
//	public List<Object[]> findDistrictChart(String orgId,String vehicleStatus);
	
//	 /**
//     * 描述：查询指定机构信息
//     *
//     * @param rootOrgCode 机构ID
//     * @return 机构实体信息 Date 2018年4月27日 上午12:43:35
//     */
//    @Query("select a from OrganizationEntity a where a.id=?1 and a.valid=1")
//	OrganizationEntity getOrgEntity(String rootOrgCode);
	
//	/**
//	 * 根据机构id查询出动的车辆
//	 * @param id 机构id
//	 * @param vehicleStatus 车辆状态代码
//	 * @return 返回统计结果
//	 */
//	@Query("select count(t.organizationId),t.organizationId from EquipmentVehicleEntity t where t.organizationId =?1 and t.vehicleStatusCode = ?2 "
//			+ "and t.valid=1 group by t.organizationId")
//     List<Object[]> findVehicleNums(String id,String vehicleStatus);
  
  
//  /**
//	 * 出动的车辆和机构列表
//	 * @param orgId 机构id
//	 * @return 返回统计结果
//	 */
//	@Query("select t.id,j.id,j.organizationName,j.parent.id from EquipmentVehicleEntity t left join OrganizationEntity j on j.id=t.organizationId and t.valid=1 "
//			+ "where j.parent.id =?1 ")
//     List<Object[]> findVehicle(String orgId);
  
  /**
	 * 每个大队车辆的数量
	 * @param orgId 机构id
	 * @return 返回统计结果
	 */
	@Query("select count(t.id) from EquipmentVehicleEntity t left join OrganizationEntity j on t.organizationId=j.id where j.valid=1 and j.organizationParentId =?1 " +
			"and t.vehicleStatusCode = '0306' ")
    Integer findVehicleNum(String orgId);

	

}
