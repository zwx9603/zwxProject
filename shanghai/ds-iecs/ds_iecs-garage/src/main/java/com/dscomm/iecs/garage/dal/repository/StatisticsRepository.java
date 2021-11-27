package com.dscomm.iecs.garage.dal.repository;

import com.dscomm.iecs.garage.dal.po.FictitiousEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 描述： 统计查询服务
 */
@Repository
public interface StatisticsRepository extends JpaRepository<FictitiousEntity, String> {

    /**
     * 统计车辆状态数
     * @param scopeSquadronId 机构id/查询码
     * @param vehicleStatusCodes 车辆状态代码
     * @param whetherOnlySquadron 是否只查询本机构，  true：只查询本机构、false：查询机构id及其辖区
     * @return 统计结果
     */
    List<Object[]> findStatisticsVehicleStatus(String scopeSquadronId, List<String>  vehicleStatusCodes, Boolean whetherOnlySquadron);

    /**
     * 获取机构查询码
     * @param organizationId 机构id
     * @return 机构查询码
     */

    @Query(value = "  select t.JGTREE from  JGXX_XFJG t WITH ( NOLOCK )  where t.JLZT=1 and t.id = ?1  ", nativeQuery = true)
    String findOrganizationTree(String organizationId);

    /**
     * 根据机构id获取车辆详情
     * @param organizationId 机构id
     * @return 车辆详情列表
     */
    @Query(value = "  select cl.ID as ID,cl.ZBBM as ZBBM,cl.ZBMC as ZBMC,cl.ZBLXDM as ZBLXDM," +
            " cllx.NAME as CLLXMC,cl.CLZTDM_MH as CLZTDM_MH,clzt.ZTMC as ZTMC,cl.CPHM as CPHM,cl.CKMBH as CKMBH," +
            " cl.EICDBH as EICDBH,cl.FJBH as FJBH,cl.CKMZT as CKMZT , cl.CLZK as CLZK  from  WL_CLXX cl WITH ( NOLOCK )  " +
            " left join V_CLLX cllx WITH ( NOLOCK ) on cl.ZBLXDM = cllx.ID " +
            " left join JCJ_CLZTDY clzt WITH ( NOLOCK ) on cl.CLZTDM_MH = clzt.ZTBM " +
            " where cl.JLZT=1 and cl.ZBLXDM like '21%' and cl.SSXFJGID = ?1  " +
            " order by ( CAST(cl.CKMBH AS FLOAT ) ) ASC ", nativeQuery = true)
    List<Object[]> findVehicle(String organizationId);


    /**
     * 根据机构id 获得辖区中队 车辆数 出动车辆数
     * @param organizationId 机构id
     * @return
     */
    @Query(value = "  select    " +
            "  lft.JGTREE as JGTREE ,  " +
            "  lft.id as jgid ,  " +
            "  lft.name as jgmc ,  " +
            "  lft.RESERVE1 as ddid ,  " +
            "  lft.RESERVE2 as ddmc ,  " +
            "  lft.zdid as zdid ,  " +
            "  lft.zdmc as zdmc ,  " +
            "  ( select count(1) from wl_clxx a where a.JLZT=1 and a.ZBLXDM like '21%' and a.SSXFJGID = lft.id and a.CLZTDM_MH in ( ?2  ) ) as clcds ,  " +
            "  ( select count(1) from wl_clxx a where a.JLZT=1 and a.ZBLXDM like '21%' and a.SSXFJGID = lft.id ) as clzs ,  " +
            "  lft.lxdh as lxdh," +
            "  lft.jgjc as jgjc   " +
            " from (  " +
            "   " +
            "  SELECT A.ID, A.JGMCSX NAME, A.SJJGID RESERVE1, B.JGJC RESERVE2 , B.SJJGID zdid , ( select JGJC from JGXX_XFJG d with (nolock) where d.JLZT=1 and  d.id = b.SJJGID     ) as zdmc ,  " +
            "      a.JGTREE, a.LXDH as lxdh ,a.jgjc jgjc   " +
            "        from  " +
            "        (  " +
            "        select a.id, a.pym, a.JGMCSX, a.jgjc, a.jgtree, a.JGXZDM, case when a.JGXZDM like '09%' then a.SJJGID else a.ID end sjjgid  , a.LXDH " +
            "        from JGXX_XFJG a with (nolock)  " +
            "        WHERE a.JLZT=1 and  (JGXZDM like '09%'  ) and ZZJG=1  " +
            "        and JGTREE like (select c.JGTREE+'%' from JGXX_XFJG c with (nolock) where  c.JLZT=1 and c.id = ?1 ) " +
            "        ) a, JGXX_XFJG b with (nolock)  " +
            "        where  a.sjjgid=b.ID     " +
            "         " +
            " ) lft   ORDER BY clcds desc,clzs desc,JGTREE asc ", nativeQuery = true)
    List<Object[]> findStatisticsVehicleNum(String organizationId , List<String>  vehicleStatusCodes  );



    /**
     * 根据机构id 获得辖区中队 车辆数 出动车辆数
     * @param organizationId 机构id
     * @return
     */
    @Query(value = "select  " +
            " count(CLZBQC_CLXX_ID)  from    " +
            "  JCJ_ZZCLXX  with (nolock)   where  JLZT=1 and  tzsj >= ?1 and tzsj <?2 and  JGXX_XFJG_ID  in (  " +
            "  select a.id   from JGXX_XFJG a with (nolock)   WHERE  a.JLZT=1 and  ZZJG=1  " +
            "  and JGTREE like (select c.JGTREE + '%' from JGXX_XFJG c with (nolock) where  c.JLZT=1 and c.id=?3 ) and JLZT=1 )  ", nativeQuery = true)
     Integer  findStatisticsVehicleOnDutyTrend(Date startTime , Date endTime , String organizationId    );


    /**
     * 根据机构id 获得辖区中队 平均出动时长
     * @param organizationId 机构id
     * @return
     */
    @Query(value = "select TOP (?4) " +
            "  lft.JGTREE as JGTREE ,  " +
            "  lft.id as jgid ,  " +
            "  lft.name as jgmc ,  " +
            "  lft.RESERVE1 as ddid ,  " +
            "  lft.RESERVE2 as ddmc ,  " +
            "  lft.zdid as zdid ,  " +
            "  lft.zdmc as zdmc ,  " +
            "  ISNULL(lrt.pjz,0) as pjz," +
            "  lft.jgjc as jgjc  " +
            "  from (   " +
            "  SELECT A.ID, A.JGMCSX NAME, A.SJJGID RESERVE1, B.JGJC RESERVE2 , B.SJJGID zdid , ( select JGJC from JGXX_XFJG d with (nolock) where  d.JLZT=1 and  d.id = b.SJJGID     ) as zdmc ,  " +
            "      a.JGTREE,a.jgjc jgjc  " +
            "        from  " +
            "        (  " +
            "        select a.id, a.pym, a.JGMCSX, a.jgjc, a.jgtree, a.JGXZDM, case when a.JGXZDM like '09%' then a.SJJGID else a.ID end sjjgid  " +
            "        from JGXX_XFJG a with (nolock)  " +
            "        WHERE (JGXZDM like '09%'  ) and ZZJG=1  " +
            "        and JGTREE like (select c.JGTREE+'%' from JGXX_XFJG c with (nolock) where  c.JLZT=1 and c.id=?3 ) and JLZT=1  " +
            "        ) a, JGXX_XFJG b with (nolock)  " +
            "        where a.sjjgid=b.ID     " +
            "    ) lft left   join  (  " +
            "   select  JGXX_XFJG_ID , AVG( cAST(0 AS FLOAT ) )  as pjz from  JCJ_ZZCLXX with (nolock) where  JLZT=1 and  tzsj >= ?1 and tzsj < ?2   " +
            "   " +
            "  GROUP BY    JGXX_XFJG_ID     " +
            "   " +
            " ) lrt on  lft.id = lrt.JGXX_XFJG_ID   order by  pjz asc  ", nativeQuery = true)
    List<Object[]>  findStatisticsOrganizationGoOutAvgTime( Date startTime , Date endTime , String organizationId  ,int top );



    /**
     * 获取机构详情
     * @param organizationId 机构id
     * @return 机构详情
     */
    @Query(value = "  select  t.JGDM, t.JGMC, t.JGJC, t.JGDZ, t.JGMS, t.XZQDM , t.GIS_X, t.GIS_Y, " +
            " t.GIS_H, t.YZBM, t.CZHM, t.LXR, t.LXDH, t.JGTREE, t.BZ from  JGXX_XFJG t WITH ( NOLOCK )  " +
            "  where  t.JLZT=1 and t.ID = ?1  ", nativeQuery = true)
    List<Object[]> findOrganization(String organizationId);

    /**
     * 根据机构id获取案件详情列表
     * @param organizationId 机构id
     * @param limit 前limit条
     * @return 案件详情列表
     */
    @Query(value = "  select TOP (?2) incident.ID, incident.AJBH, incident.JJJL_ID,incident.LASJ, incident.AFDZ,incident.GIS_X,incident.GIS_Y, incident.AJZTDM, ajztdy.ZTMC from  JCJ_AJXX incident WITH ( NOLOCK )  " +
            " left join JCJ_AJZTDY ajztdy with (nolock) on incident.AJZTDM = ajztdy.ZTBM " +
            " where  incident.JLZT=1 and incident.ZGJGBH   in (    " +
            " select a.ID   from JGXX_XFJG a with (nolock)   WHERE a.JLZT=1    " +
            "  and JGTREE like ( select c.JGTREE + '%' from JGXX_XFJG c with (nolock) where c.JLZT=1 and c.ID=?1 ) ) " +
            " or incident.ID in (  select cjjl.JCJ_AJXX_ID   from  JCJ_CJJL cjjl WITH ( NOLOCK ) ,JCJ_CJXX cjxx WITH ( NOLOCK )  where cjjl.JLZT = 1 and  cjxx.JLZT = 1 " +
            " and cjxx.JCJ_CJJL_ID = cjjl.ID and cjxx.JGXX_XFJG_ID in ( " +
            " select b.ID  from JGXX_XFJG b with (nolock)   WHERE b.JLZT=1  " +
            "  and b.JGTREE like ( select d.JGTREE + '%' from JGXX_XFJG d with (nolock) where  d.JLZT=1 and d.ID=?1 ) ) )  " +
            " order by incident.LASJ desc ", nativeQuery = true)
    List<Object[]> findLatestIncident(String organizationId,Integer limit);


    /**
     * 根据 机构id 获得辖区中队 出动车辆排行
     * @param organizationId 机构id
     * @param startTime 起始时间
     * @param endTime 截止时间
     * @param limit 前limit
     * @return
     */
    @Query(value = "select  TOP (?4) " +
            "  count(zzcl.CLZBQC_CLXX_ID) as num,  " +
            "  jg.JGJC as jgmc from JCJ_ZZCLXX zzcl  with (nolock)  LEFT JOIN JGXX_XFJG jg with (nolock) on zzcl.JGXX_XFJG_ID = jg.ID  " +
            "  where  zzcl.JLZT=1 and  zzcl.TZSJ >= ?1 and zzcl.TZSJ <?2 and  zzcl.JGXX_XFJG_ID  in (  " +
            "  select a.id  from JGXX_XFJG a with (nolock)   WHERE  a.JLZT=1 and a.JGTREE like " +
            "   (select c.JGTREE + '%' from JGXX_XFJG c with (nolock) where  c.JLZT=1 and c.id=?3 ) and JLZT=1 )  " +
            " GROUP BY jg.JGJC ORDER BY num desc ", nativeQuery = true)
    List<Object[]>  findStatisticsDispatchVehicleCount(Date startTime , Date endTime , String organizationId ,Integer limit);

}
