package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.IncidentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述： 统计查询服务
 */
@Repository
public interface StatisticsRepository extends JpaRepository<IncidentEntity, String> {

    /**
     * 根据条件 统计车辆状态数
     * @param dictionaryTypeCode   机构id /机构查询码
     * @param scopeSquadronId   机构id /机构查询码
     * @param organizationIds   机构ids
     * @param keyword 关键字
     * @param vehicleTypeCodes 车辆类型集合
     * @param vehicleStatusCodes 车辆状态
     * @return 统计结果
     */
    List<Object[]> findStatisticsVehicleStatus( String dictionaryTypeCode   , String scopeSquadronId ,String  keyword  , List<String> organizationIds ,
                                                          List<String> vehicleTypeCodes , List<String> vehicleStatusCodes  , String natureLike );

    /**
     * 根据条件 统计车辆类型数
     * @param dictionaryTypeCode   字典分类
     * @param scopeSquadronId   机构id /机构查询码
     * @param organizationIds   机构ids
     * @param keyword 关键字
     * @param vehicleTypeCodes 车辆类型集合
     * @param vehicleStatusCodes 车辆状态
     * @return
     */
    List<Object[] > findVehicleTypeStatistics ( String dictionaryTypeCode , String scopeSquadronId ,String  keyword  , List<String> organizationIds ,
                                                       List<String> vehicleTypeCodes , List<String> vehicleStatusCodes , String natureLike );

    /**
     * 根据条件 统计机构车辆数
     * @param scopeSquadronId   机构id /机构查询码
     * @param organizationIds   机构ids
     * @param keyword 关键字
     * @param vehicleTypeCodes 车辆类型集合
     * @param vehicleStatusCodes 车辆状态
     * @return
     */
    List<Object[] > findVehicleOrganizationStatistics (String scopeSquadronId ,String  keyword  , List<String> organizationIds ,
                                               List<String> vehicleTypeCodes , List<String> vehicleStatusCodes , String natureLike  );


    /**
     * 根据时间段查询各个警情类型数量
     *
     * @param startTime      开始时间
     * @param endTime        结束时间
     * @param districtCode   行政区域代码
     * @param organizationId 辖区机构id
     * @return 符合条件的警情数量
     */
    List<Object[]> findIncidentTypeGroup(Long startTime, Long endTime, String districtCode, String organizationId);


    /**
     * 根据时间段查询警情类型统计信息
     *
     * @return 符合条件的警情数量
     */
    List<Object[]> findStatisticsIncidentType(String scopeSquadronId, List<String> orgIds, String keyword, List<String> incidentTypeCodes,
                                              List<String> incidentLevelCodes, List<String> incidentStateCodes,
                                              Boolean whetherKeyUnit, Boolean whetherImport,
                                              Long startTime, Long endTime , List<String> districtCodes);







    /**
     * 根据辖区ID集合 统计案件同环比（案件类型分类）
     *
     * @param startTime  开始时间
     * @param endTime    结束时间
     * @return 返回统计结果
     */
    @Query(value = "SELECT incidentEntity.incidentTypeCode,count(incidentEntity.incidentTypeCode) FROM IncidentEntity incidentEntity where incidentEntity.valid = 1" +
            " and incidentEntity.registerIncidentTime >= ?1 and incidentEntity.registerIncidentTime < ?2 GROUP BY incidentEntity.incidentTypeCode")
    List<Object[]> findIncidentTypeContrastStatistics(Long startTime, Long endTime );

    /**
     * 根据辖区ID集合 统计案件同环比（案件类型分类）
     *
     * @param startTime  开始时间
     * @param endTime    结束时间
     * @param searchPath 辖区机构查询码
     * @return 返回统计结果
     */
    @Query(value = "SELECT incidentEntity.incidentTypeCode,count(incidentEntity.incidentTypeCode) FROM IncidentEntity incidentEntity where incidentEntity.valid = 1" +
            " and incidentEntity.squadronOrganizationId in (   select t.id  from OrganizationEntity t  where  t.valid = 1 and t.searchPath like ?3 ) " +
            " and incidentEntity.registerIncidentTime >= ?1 and incidentEntity.registerIncidentTime < ?2 GROUP BY incidentEntity.incidentTypeCode")
    List<Object[]> findIncidentTypeContrastStatisticsBySearchPath(Long startTime, Long endTime, String searchPath);




    /**
     * 统计各个辖区 案件类型数量
     * @return map集合
     * */
    @Query(" SELECT incidentEntity.districtCode , incidentEntity.incidentTypeCode,count( incidentEntity.id ) FROM IncidentEntity incidentEntity where incidentEntity.valid = 1 " +
            " and incidentEntity.registerIncidentTime >=?1 and incidentEntity.registerIncidentTime <  ?2  " +
            " GROUP BY incidentEntity.districtCode  , incidentEntity.incidentTypeCode  " +
            " order by incidentEntity.districtCode asc , incidentEntity.incidentTypeCode asc   ")
    List<Object[] > findIncidentDistrictStatistics(  Long startTime  ,  Long endTime  );


    /**
     * 统计各个等级重点单位数量
     * @return map集合
     * */
    @Query(" SELECT incidentEntity.districtCode , incidentEntity.incidentTypeCode,count( incidentEntity.id ) FROM IncidentEntity incidentEntity where incidentEntity.valid = 1 " +
            " and incidentEntity.registerIncidentTime >=?1 and incidentEntity.registerIncidentTime <  ?2  and incidentEntity.districtCode = ?3  " +
            " GROUP BY incidentEntity.districtCode  , incidentEntity.incidentTypeCode   " +
            " order by incidentEntity.districtCode asc , incidentEntity.incidentTypeCode asc   ")
    List<Object[] > findIncidentDistrictStatistics(  Long startTime  ,  Long endTime , String districtCode );

    List<Object[]> findInjuriesAndDeaths(Long lastUpdataTime, Long endTime, String districtCode, String squadronSearch);


}
