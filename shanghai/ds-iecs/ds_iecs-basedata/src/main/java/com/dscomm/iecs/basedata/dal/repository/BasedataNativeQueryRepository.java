package com.dscomm.iecs.basedata.dal.repository;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述: 原生SQL 数据库持久层服务
 */
@Repository
public interface BasedataNativeQueryRepository extends JpaRepository<BaseEntity, String> {

    /**
     * 根据经纬度范围查询 重点单位
     * @return
     */
    @Query(value = " select t.id from YAGL_MHDW t where t.yxx = 1  " +
            " and  cast( t.dqjd AS FLOAT) >=?1 and   cast( t.dqjd AS FLOAT ) <= ?2 " +
            " and  cast( t.dqwd AS FLOAT) >=?3 and   cast( t.dqwd AS FLOAT ) <= ?4  " +
            " and  t.dqjd is not null and  t.dqwd is not null " +
            " and  t.dqjd !='' and  t.dqwd !=''  ", nativeQuery = true)
    List<String> findKeyUnitIncidentRange(  double minLng , double maxLng , double minLat  ,  double maxLat  ) ;



    /**
     * 根据坐标查范围内应急联动单位
     *
     * @return
     */
    @Query(value = "  select incident.id  from JGXX_YJLDDW incident  where incident.yxx = 1" +
            " and  cast( incident.dqjd AS FLOAT) >=?1 and   cast( incident.dqjd AS FLOAT ) <= ?2 " +
            " and  cast( incident.dqwd AS FLOAT) >=?3 and   cast( incident.dqwd AS FLOAT ) <= ?4  " +
            " and incident.dqjd is not null and  incident.dqwd is not null  " +
            " and incident.dqjd !='' and  incident.dqwd !='' ", nativeQuery = true)
    List<String> findUnitEmergencyRange(   double minLng , double maxLng  , double minLat  , double maxLat );



    /**
     * 根据坐标查范围内联勤保障单位
     *
     * @return
     */
    @Query(value = "  select incident.id  from JGXX_LQBZDW incident  where incident.yxx = 1" +
            " and  cast( incident.dqjd AS FLOAT) >=?1 and   cast( incident.dqjd AS FLOAT ) <= ?2 " +
            " and  cast( incident.dqwd AS FLOAT) >=?3 and   cast( incident.dqwd AS FLOAT ) <= ?4  " +
            " and incident.dqjd is not null and  incident.dqwd is not null  " +
            " and incident.dqjd !='' and  incident.dqwd !='' ", nativeQuery = true)
    List<String> findUnitJointRange(   double minLng , double maxLng  , double minLat  , double maxLat );



    /**
     * 根据坐标查范围内的消防机构
     *
     * @return
     */
    @Query(value = "  select incident.id  from JGXX_XFJG   incident  where incident.yxx = 1" +
            " and  cast( incident.dqjd AS FLOAT) >=?1 and   cast( incident.dqjd AS FLOAT ) <= ?2 " +
            " and  cast( incident.dqwd AS FLOAT) >=?3 and   cast( incident.dqwd AS FLOAT ) <= ?4  " +
            " and incident.dqjd is not null and  incident.dqwd is not null  " +
            " and incident.dqjd !='' and  incident.dqwd !='' ", nativeQuery = true)
    List<String> findOrganizationRange(   double minLng , double maxLng  , double minLat  , double maxLat );



    /**
     * 根据坐标查范围内的微型消防站信息
     *
     * @return
     */
    @Query(value = "  select incident.id  from JGXX_WXXFZ   incident  where incident.yxx = 1" +
            " and  cast( incident.dqjd AS FLOAT) >=?1 and   cast( incident.dqjd AS FLOAT ) <= ?2 " +
            " and  cast( incident.dqwd AS FLOAT) >=?3 and   cast( incident.dqwd AS FLOAT ) <= ?4  " +
            " and incident.dqjd is not null and  incident.dqwd is not null  " +
            " and incident.dqjd !='' and  incident.dqwd !='' ", nativeQuery = true)
    List<String> findMiniatureStationRange(   double minLng , double maxLng  , double minLat  , double maxLat );



    /**
     * 车辆装载 装备缓存
     *
     * @return 装备列表
     */
    @Query(value = " select t.CLID  from V_CLXX_ZZZB t   where t.yxx = 1  and   " +
            " (  t.ZBQCMC like ?1  or  t.ZBLXDM in (  select bm   from   jcj_dm where zdlx = ?2 and mc like ?1 )     ) " +
            "  ", nativeQuery = true )
    List<String> findVehicleByKeyword(  String keyword , String dictionaryTypeCode );




}
