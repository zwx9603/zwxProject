package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.FireSafetyMonitoringEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:出入火场 数据库持久层服务
 */
@Repository
public interface FireSafetyMonitoringRepository extends JpaRepository<FireSafetyMonitoringEntity, String> {


    /**
     * 根据 警情id(案件id)获得 出入火场记录
     *
     * @param incidentId 警情id(案件id)
     * @return 返回结果
     */
    @Query(" select t from FireSafetyMonitoringEntity t  where  t.valid = 1 and t.incidentId = ?1 order by  t.updatedTime desc     ")
    List<FireSafetyMonitoringEntity> findFireSafetyMonitoringByIncidentId(String incidentId);



    /**
     * 根据 警情id(案件id)获得 进入火场
     *
     * @param incidentId 警情id(案件id)
     * @return 返回结果
     */
    @Query(" select t from FireSafetyMonitoringEntity t  where  t.valid = 1 and t.incidentId = ?1   " +
            " and t.withdrawFireTime is null  order by  t.updatedTime desc     ")
    List<FireSafetyMonitoringEntity> findEnterFireSafetyByIncidentId(String incidentId  );



    /**
     * 根据 警情id(案件id) 人员id 获得 最新 进入火场 并未 撤出 的  记录信息
     *
     * @param incidentId 警情id(案件id)
     * @return 返回结果
     */
    @Query(" select t from FireSafetyMonitoringEntity t  where  t.valid = 1 and t.incidentId = ?1 and t.personId in ( ?2 ) " +
            " and t.withdrawFireTime is null  order by  t.updatedTime desc     ")
    List<FireSafetyMonitoringEntity> findEnterFireSafetyByIncidentId(  String incidentId , List<String> personIds );

    /**
     * 根据 警情id(案件id)获得 进入火场 NEW
     *
     * @param incidentId 警情id(案件id)
     * @return 返回结果
     */
    @Query(value = " select t.* from JCJ_HCAQJKJL t INNER JOIN (select j.ajid as jajid,j.ryid as jryid ,max(j.jrhcsj) as jt  from JCJ_HCAQJKJL j GROUP BY j.ajid,j.ryid) jj "+
    		" on t.jrhcsj = jj.jt and t.ajid = jj.jajid and t.ryid = jj.jryid where t.yxx=1 and t.ajid = ?1 and t.ryid in (?2)  "+
    		" order by  t.gxsj desc ", nativeQuery = true)
    List<FireSafetyMonitoringEntity> findEnterFireSafetyNewByIncidentId(String incidentId , List<String> personIds );
    
    /**
     * 根据 车辆 id 获得 进入火场 
     *
     * @param incidentId 警情id(案件id)
     * @return 返回结果
     */
    @Query(value = " select t.* from JCJ_HCAQJKJL t INNER JOIN (select j.ajid as jajid,j.ryid as jryid ,max(j.jrhcsj) as jt  from JCJ_HCAQJKJL j GROUP BY j.ajid,j.ryid) jj "+
    		" on t.jrhcsj = jj.jt and t.ajid = jj.jajid and t.ryid = jj.jryid "+
    		" where t.yxx=1 and t.ajid IN (select distinct cz.ajid from jcj_czry cz where cz.clbh = ?1 and cz.ajid = ?2 ) "+
    		" UNION "+
    		" select '' as id,0 as cjsj,'' as czz,0 as gxsj,1 as yxx,0 as jrhcsj,cz.ajid as ajid,cz.xfjgbh as xfjgbh,'' as xfjgmc,cz.rybh as ryid,cz.rymc,0 as ycchcsj,'' as bz,0 as cchcsj "+
    		" from jcj_czry cz "+
    		" where cz.ajid = ?2 and cz.clbh = ?1 and cz.rybh NOT IN (select jj.ryid from JCJ_HCAQJKJL jj where jj.ajid= ?2 ) "
    		,
    		nativeQuery= true)
    List<FireSafetyMonitoringEntity> findEnterFireSafetyByVehicle(String vehicleId,String incidentId  );

    /**
     * 根据 警情id(案件id)获得 最新 进入火场 撤场记录 记录
     *
     * @param incidentId 警情id(案件id)
     * @return 返回结果
     */
    @Query(" select t from FireSafetyMonitoringEntity t  where  t.valid = 1 and t.incidentId = ?1 and t.personId in ( ?2 )   " +
            "  order by  t.updatedTime desc     ")
    List<FireSafetyMonitoringEntity> findLastFireSafetyMonitoring(  String incidentId , List<String> personIds );




}
