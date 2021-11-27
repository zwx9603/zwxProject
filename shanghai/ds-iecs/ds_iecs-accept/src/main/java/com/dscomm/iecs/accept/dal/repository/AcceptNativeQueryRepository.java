package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * 描述: 原生SQL 数据库持久层服务
 */
@Repository
public interface AcceptNativeQueryRepository extends JpaRepository<BaseEntity, String> {


    /**
     * 根据案件id  获得 案件出动力量结构
     *
     * @param incidentId 警情id
     * @return 返回
     */
    @Query(value = "  select " +
            "  ( select  count( distinct c.CJPC )  from  JCJ_CJJL c where c.yxx = 1 and c.JQ_TYWYSBM = ?1  ) as cpcs ," +
            "  ( select  count( distinct c.JSDW_TYWYSBM  )  from  JCJ_CJXX c where c.yxx = 1 and c.AJID = ?1  ) as cddws ," +
            "  ( select  count( distinct c.XFCL_TYWYSBM )  from  JCJ_ZZCLXX c where c.yxx = 1 and c.AJID = ?1  ) as cdcls ," +
            "  ( select  count( distinct c.RYBH )  from  JCJ_CZRY c where c.yxx = 1 and c.AJID = ?1  ) as cdrys   " +
            "  from  JCJ_AJXX t where t.id = ?1  ", nativeQuery = true)
    List<Long[]> findHandlePowerStatistics(String incidentId);


    /**
     * 根据时间段、机构id 统计处警量信息
     *
     * @param scopeSquadronId 机构id
     * @param startTime       开始时间
     * @param endTime         截止时间
     * @return 处警量统计信息
     */
    @Query(value = " select  lft.id ,  COALESCE(  lrt.num   ,0 )  as num  from  " +
            "  (  select t.id  from jgxx_xfjg t  where  t.yxx = 1 and XFJYJGXZDM like ?4 and  t.cxmlj like ?1 ) lft " +
            " left join " +
            "  (   select  JSDW_TYWYSBM , count( distinct AJID ) as num from JCJ_CJXX a , JCJ_AJXX b " +
            "  where  1 = 1  and a.ajid = b.id and b.yxx = 1   " +
            "  and a.JSDW_TYWYSBM in (   select t.id  from jgxx_xfjg t  where  t.yxx = 1 and XFJYJGXZDM like ?4 and  t.cxmlj like ?1 ) " +
            "  and a.yxx=1 and a.TZSJ >= ?2 and a.TZSJ < ?3  group by  JSDW_TYWYSBM " +
            "  ) lrt  " +
            " on  lft.id = lrt.JSDW_TYWYSBM " +
            " order by num desc  ", nativeQuery = true)
    List<Object[]> findHandleStatisticsBySearchPath(String scopeSquadronId, Long startTime, Long endTime , String  natureLike);

    /**
     * 根据辖区ID集合 统计处警量信息
     *
     * @param startTime 开始时间
     * @param endTime   截止时间
     * @return 处警量统计信息
     */
    @Query(value = " select  lft.id ,  COALESCE(  lrt.num   ,0 )  as num   from  " +
            "  (  select t.id  from jgxx_xfjg t  where  t.yxx = 1 and XFJYJGXZDM like ?3  ) lft " +
            " left join " +
            "  (   select  JSDW_TYWYSBM , count( distinct AJID ) as num from JCJ_CJXX a , JCJ_AJXX b " +
            "  where   1 = 1  and a.ajid = b.id and b.yxx = 1  " +
            " and a.JSDW_TYWYSBM in (   select t.id  from jgxx_xfjg t  where  t.yxx = 1 and XFJYJGXZDM like ?3   ) " +
            " and a.yxx=1 and a.TZSJ >= ?1 and a.TZSJ < ?2  group by  jsdw_tywysbm " +
            "  ) lrt  " +
            " on  lft.id = lrt.JSDW_TYWYSBM " +
            " order by num desc  ", nativeQuery = true)
    List<Object[]> findHandleStatistics(Long startTime, Long endTime , String  natureLike);





    /**
     * 获取范围内未归档案件
     *
     * @return
     */
    @Query(value = "  select  incident.id  from jcj_ajxx incident  where incident.yxx = 1" +
            " and  incident.lasj >=?1  and incident.lasj <?2 " +
            " and  cast( incident.dqjd AS FLOAT) >=?3 and   cast( incident.dqjd AS FLOAT ) <= ?4 " +
            " and  cast( incident.dqwd AS FLOAT) >=?5 and   cast( incident.dqwd AS FLOAT ) <= ?6  " +
            " and incident.JQZT in ( ?7 )  " +
            " and incident.dqjd is not null and  incident.dqwd is not null  " +
            " and incident.dqjd !='' and  incident.dqwd !='' ", nativeQuery = true)
    List<String> findScopeIncident( long startTime , long endTime ,  double minLng , double maxLng  , double minLat  ,  double maxLat ,  List<String> incidentStatusCodes );



    /**
     * 根据主管机构id 车辆类型 车辆状态  列表查询车辆
     *
     * @return 车辆列表
     */
    @Query(value = " select   e.ID    from (  select distinct  t.id , t.XFZBLXDM from WL_CLXX t where t.yxx = 1 " +
            " and t.XFJYJG_TYWYSBM = ?1 and t.XFZBLXDM = ?2  and t.CLQWZTLBDM in (?3) order by XFZBLXDM asc   ) e   " , nativeQuery =  true )
    List<String> findEquipmentVehicle(String organizationId , String vehicleTypeCode , List<String> vehicleStatusCodes  );

    /**
     * 根据主管机构id 车辆类型 车辆状态  查询数量 列表查询车辆
     *
     * @return 车辆列表
     */
    @Query(value = " select   e.ID    from (  select distinct  t.id , t.XFZBLXDM from WL_CLXX t where t.yxx = 1 " +
            " and t.XFJYJG_TYWYSBM = ?1 and t.XFZBLXDM = ?2  and t.CLQWZTLBDM in (?3) order by XFZBLXDM asc   LIMIT ?4 OFFSET 0    ) e   " , nativeQuery =  true )
    List<String> findEquipmentVehicle(String organizationId , String vehicleTypeCode , List<String> vehicleStatusCodes , int  rowNum  );

    /**
     * 根据主管机构id 车辆类型 车辆状态  查询数量 列表查询车辆
     *
     * @return 车辆列表
     */
    @Query(value = " select   e.ID    from (  select distinct t.id , t.XFZBLXDM  from WL_CLXX t where t.yxx = 1 " +
            " and t.XFJYJG_TYWYSBM = ?1 and t.XFZBLXDM in ( ?2 )   and t.CLQWZTLBDM in (?3) order by XFZBLXDM asc   LIMIT ?4 OFFSET 0    )  e    " , nativeQuery =  true )
    List<String> findEquipmentVehicle(String organizationId , List<String> vehicleTypeCodes , List<String> vehicleStatusCodes , int  rowNum  );

    /**
     * 根据主管机构id 车辆类型 车辆状态  查询数量 列表查询车辆
     *
     * @return 车辆列表
     */
    @Query(value = " select   e.ID    from (  select distinct t.id , t.XFZBLXDM from WL_CLXX t where t.yxx = 1 " +
            " and t.XFJYJG_TYWYSBM = ?1  and t.CLQWZTLBDM in (?2) order by t.XFZBLXDM asc     LIMIT ?3 OFFSET 0    )  e    " , nativeQuery =  true )
    List<String> findEquipmentVehicle(String organizationId , List<String> vehicleStatusCodes , int  rowNum  );



    /**
     * 根据主管机构id毗邻信息查询  车辆类型 车辆状态   列表查询车辆
     *
     * @return 车辆列表
     */
    @Query(value = " select   e.ID    from (  select distinct t.id , t.XFZBLXDM ,d.jglxdm , d.yxj from " +
            " WL_CLXX t , " +
            " (  select a.pldwdm , b.jglxdm , a.yxj  from ZBZB_XFJGPLYXJ a , jgxx_xfjg b where  a.yxx = 1 and b.yxx = 1 and a.pldwdm = b.id  " +
            " and  a.zgdwdm = ?1   order by jglxdm desc  ) d " +
            " where t.yxx = 1 and t.XFJYJG_TYWYSBM = d.pldwdm " +
            "  and t.XFZBLXDM = ?2  and t.CLQWZTLBDM in (?3) order by d.jglxdm desc ,  d.yxj asc , t.XFZBLXDM asc    )   e    " , nativeQuery =  true )
    List<String> findEquipmentVehicleByAdjacentPriority(String organizationId  , String vehicleTypeCode , List<String> vehicleStatusCodes    );


    /**
     * 根据主管机构id毗邻信息查询  车辆类型 车辆状态  查询数量 列表查询车辆
     *
     * @return 车辆列表
     */
    @Query(value = " select   e.ID    from (  select distinct t.id , t.XFZBLXDM ,d.jglxdm , d.yxj from " +
            " WL_CLXX t , " +
            " (  select a.pldwdm , b.jglxdm , a.yxj  from ZBZB_XFJGPLYXJ a , jgxx_xfjg b where  a.yxx = 1 and b.yxx = 1 and a.pldwdm = b.id  " +
            " and  a.zgdwdm = ?1   order by jglxdm desc  ) d " +
            " where t.yxx = 1 and t.XFJYJG_TYWYSBM = d.pldwdm " +
            "  and t.XFZBLXDM = ?2  and t.CLQWZTLBDM in (?3) order by d.jglxdm desc ,  d.yxj asc , t.XFZBLXDM asc  LIMIT ?4 OFFSET 0  )   e    " , nativeQuery =  true )
    List<String> findEquipmentVehicleByAdjacentPriority(String organizationId  , String vehicleTypeCode , List<String> vehicleStatusCodes , int  rowNum  );


    /**
     * 根据主管机构id毗邻信息查询  车辆类型 车辆状态  查询数量 列表查询车辆
     *
     * @return 车辆列表
     */
    @Query(value = " select   e.ID    from (  select distinct t.id , t.XFZBLXDM  , d.yxj from " +
            " WL_CLXX t , " +
            " (  select a.pldwdm , b.jglxdm , a.yxj  from ZBZB_XFJGPLYXJ a , jgxx_xfjg b where  a.yxx = 1 and b.yxx = 1 and a.pldwdm = b.id  " +
            " and  a.zgdwdm = ?1     ) d " +
            " where t.yxx = 1 and t.XFJYJG_TYWYSBM = d.pldwdm " +
            "  and t.XFZBLXDM in ( ?2 ) and t.CLQWZTLBDM in (?3) order by   d.yxj asc  , t.XFZBLXDM asc  LIMIT ?4 OFFSET 0    )  e   " , nativeQuery =  true )
    List<String> findEquipmentVehicleByAdjacentPriority(String organizationId  , List<String> vehicleTypeCodes , List<String> vehicleStatusCodes , int  rowNum  );


    /**
     * 根据主管机构id毗邻信息查询  车辆类型 车辆状态    列表查询车辆
     *
     * @return 车辆列表
     */
    @Query(value = " select   e.ID    from (  select distinct t.id , t.XFZBLXDM   , d.yxj  from " +
            "WL_CLXX t , " +
            " (  select a.pldwdm , b.jglxdm , a.yxj  from ZBZB_XFJGPLYXJ a , jgxx_xfjg b where  a.yxx = 1 and b.yxx = 1 and a.pldwdm = b.id  " +
            " and  a.zgdwdm = ?1 and  b.cxmlj like ?2    ) d " +
            " where t.yxx = 1 and t.XFJYJG_TYWYSBM = d.pldwdm " +
            "  and t.XFZBLXDM = ?3  and t.CLQWZTLBDM in (?4) order by   d.yxj asc  , t.XFZBLXDM asc      )   e   " , nativeQuery =  true )
    List<String> findEquipmentVehicleByAdjacentPriority(String organizationId , String searchPath , String vehicleTypeCode ,
                                                        List<String> vehicleStatusCodes    );


    /**
     * 根据主管机构id毗邻信息查询  车辆类型 车辆状态  查询数量 列表查询车辆
     *
     * @return 车辆列表
     */
    @Query(value = " select   e.ID    from (  select distinct t.id , t.XFZBLXDM   , d.yxj  from " +
            "WL_CLXX t , " +
            " (  select a.pldwdm , b.jglxdm , a.yxj  from ZBZB_XFJGPLYXJ a , jgxx_xfjg b where  a.yxx = 1 and b.yxx = 1 and a.pldwdm = b.id  " +
            " and  a.zgdwdm = ?1 and  b.cxmlj like ?2    ) d " +
            " where t.yxx = 1 and t.XFJYJG_TYWYSBM = d.pldwdm " +
            "  and t.XFZBLXDM = ?3  and t.CLQWZTLBDM in (?4) order by   d.yxj asc  , t.XFZBLXDM asc  LIMIT ?5 OFFSET 0   )   e   " , nativeQuery =  true )
    List<String> findEquipmentVehicleByAdjacentPriority(String organizationId , String searchPath , String vehicleTypeCode ,
                                                        List<String> vehicleStatusCodes , int  rowNum  );

    /**
     * 根据主管机构id毗邻信息查询  车辆类型 车辆状态  查询数量 列表查询车辆
     *
     * @return 车辆列表
     */
    @Query(value = " select   e.ID    from (  select distinct t.id , t.XFZBLXDM   , d.yxj  from " +
            "WL_CLXX t , " +
            " (  select a.pldwdm , b.jglxdm , a.yxj  from ZBZB_XFJGPLYXJ a , jgxx_xfjg b where  a.yxx = 1 and b.yxx = 1 and a.pldwdm = b.id  " +
            " and  a.zgdwdm = ?1 and  b.cxmlj like ?2    ) d " +
            " where t.yxx = 1 and t.XFJYJG_TYWYSBM = d.pldwdm " +
            "  and t.XFZBLXDM in ( ?3  )   and t.CLQWZTLBDM in (?4) order by   d.yxj asc  , t.XFZBLXDM asc  LIMIT ?5 OFFSET 0   )   e   " , nativeQuery =  true )
    List<String> findEquipmentVehicleByAdjacentPriority(String organizationId , String searchPath , List<String> vehicleTypeCodes , List<String> vehicleStatusCodes , int  rowNum  );


    @Query(value = " SELECT  a.*  FROM zbzb_qxxx a RIGHT JOIN   " +
            " (SELECT  max(ybjssj) ybjssj,max(id) id FROM zbzb_qxxx  " +
            "  where  YXX=1 and  XZQY in(?1) AND ybqssj <=?2  AND ybjssj > ?2   " +
            " GROUP BY xzqy) " +
            " b  ON a.id = b.id AND a.ybjssj = b.ybjssj ORDER BY a.xzqy   " , nativeQuery = true)
    List<Object[]> findWeather(List<String>  codes, Long currentTime);

    /**
     * 查询主管机构毗邻单位车辆
     * @param vehicleTypeCodes 车辆类型
     * @param vehicleStatus 车辆状态
     * @param orgid 主管单位id
     * @return 返回结果
     */
    @Query(value = "select wl.id,wl.xfzblxdm from wl_clxx wl,(select jg.id,jg.px,t.yxj from zbzb_xfjgplyxj t,jgxx_xfjg jg where t.yxx=1 and jg.yxx=1 and t.zgdwdm=?1 and t.pldwdm=jg.id ) d " +
            "  where wl.yxx=1 and wl.xfjyjg_tywysbm=d.id and wl.xfzblxdm  in ?2 and wl.clqwztlbdm in ?3 order by d.yxj asc,d.px asc",nativeQuery = true)
    List<Object[]> findVehicleByAdjacentPriority(String orgid, Set<String> vehicleTypeCodes, List<String> vehicleStatus);


}
