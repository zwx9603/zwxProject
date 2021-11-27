package com.dscomm.iecs.basedata.dal.repository;

import com.dscomm.iecs.basedata.dal.po.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述 机构数据底层服务
 */
@Repository
public interface OrganizationOtherRepository extends JpaRepository<OrganizationEntity, String> {


    /**
     * 根据主管机构 id 获得 消防机构 地方专职队信息
     */
    @Query(" select t from OrganizationLocalFullTimeUnitEntity t  where   t.valid = 1 and  t.organizationId = ?1  order by t.updatedTime desc   ")
    List<OrganizationLocalFullTimeUnitEntity> findOrganizationLocalFullTimeUnitByOrganizationId(String organizationId );


    /**
     * 根据主管机构 id 获得 消防机构毗邻优先级
     */
    @Query(" select t from OrganizationAdjacentPriorityEntity t  where   t.valid = 1      ")
    List<OrganizationAdjacentPriorityEntity> findAdjacentOrganization (   );

    /**
     * 根据应急单位 ids 获得 应急单位信息
     */
    @Query(" select t from UnitEmergencyEntity t  where   t.valid = 1 and t.id in (?1)     ")
    List<UnitEmergencyEntity> findUnitEmergency (List<String> ids );

    /**
     * 根据联勤保障单位ids 获得 联勤单位信息
     */
    @Query(" select t from UnitJointServiceEntity t  where   t.valid = 1 and t.id in (?1)     ")
    List<UnitJointServiceEntity> findUnitJoint (List<String> ids );

    /**
     * 根据主管机构 ids 获得 消防单位信息
     */
    @Query(" select t from OrganizationEntity    t  where   t.valid = 1 and t.id in ( ?1 )     ")
    List<OrganizationEntity> findOrganization (List<String> ids );

    /**
     * 根据微型消防站 ids 获得 微型消防站信息
     */
    @Query(" select t from MiniatureStationEntity    t  where   t.valid = 1 and t.id in ( ?1 )     ")
    List<MiniatureStationEntity> findMiniatureOrganization (List<String> ids );


    @Query(" select max (t.updatedTime) from RegionEntity t      ")
    Long findDataLatestTime(  Long lastTime ) ;

    @Query(" select t  from RegionEntity t  where  t.updatedTime >?1 and t.updatedTime <=?2  ")
    List<RegionEntity>   findDataLatestTime( Long lastTime , Long  latestTime ) ;

    @Query(" select max (t.updatedTime) from OrganizationEntity t      ")
    Long findDataLatestTimeOrg( Long lastTime ) ;

    /**
     * 根据获得范围信息
     */
    @Query(" select t from RegionEntity    t  where   t.valid = 1     ")
    List<RegionEntity> findRegion (  );

    @Query(" select t from MiniatureStationEntity    t  where   t.valid = 1   and t.stationName = ?1   ")
    List<MiniatureStationEntity> findMiniatureStationByName(String organizationName);

    /** *
     *  根据条件 查询微站
     */
    List<MiniatureStationEntity> findMiniatureStationCondition( String keyword,  String  districtCode, String  squadronOrganizationSearchPath ,
                                                   Boolean whetherPage, int page, int size);

    /** *
     *  根据条件 查询微站
     */
    Integer findMiniatureStationTotal ( String keyword,  String  districtCode, String  squadronOrganizationSearchPath   );


    //todo 暂时不启用 查询可接警的单位信息
    @Query(" select t.id from OrganizationEntity   t         ")
    List<String> findReceiveOrganization( );

}
