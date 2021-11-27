package com.dscomm.iecs.accept.dal.repository;


import com.dscomm.iecs.accept.dal.po.IncidentEntity;
import com.dscomm.iecs.accept.dal.po.TelephoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 案件底层服务
 */
@Repository
public interface IncidentRepository extends JpaRepository<IncidentEntity, String> {


    /**
     * 根据辖区ID集合、警情地址模糊匹配、警情类型、警情等级、警情状态、警情时间段查询警情信息(分页)
     *
     * @return
     */
    List<Object[]> findIncidentCondition(  String scopeSquadronId , Boolean  whetherHandleIncident , List<String> orgIds, String keyword, List<String> incidentTypeCodes,
                                               List<String> incidentLevelCodes, List<String> incidentStatusCodes,
                                               Boolean whetherKeyUnit, Boolean whetherImport,
                                               Long startTime, Long endTime, Boolean whetherPage,
                                               int page, int size ,  String account   ,
                                               Boolean whetherAttention ,  Integer type  , String receiverObjectId  , List<String> filterIncidentIds   );

    /**
     * 根据辖区ID集合、警情地址模糊匹配、警情类型、警情等级、警情状态、警情时间段查询警情总数
     *
     * @return
     */
    Integer findIncidentConditionTotal(  String scopeSquadronId , Boolean  whetherHandleIncident , List<String> orgIds, String keyword, List<String> incidentTypeCodes,
                                       List<String> incidentLevelCodes, List<String> incidentStatusCodes,
                                       Boolean whetherKeyUnit, Boolean whetherImport,
                                       Long startTime, Long endTime ,  String account   ,
                                       Boolean whetherAttention ,  Integer type  , String receiverObjectId , List<String> filterIncidentIds   );



    /**
     * 根据辖区ID集合、警情地址模糊匹配、警情类型、警情等级查询未完成警情信息
     *
     * @return
     */
    List<Object[]> findIncidentDetailed( String incidentId , String mergeByMainIncidentId ,  List<String> incidentIdList ,   String account    );



    /**
     * 根据警情id 获得警情列表信息
     * @param incidentId 警情id
     * @return
     */
    @Query("select  t  from IncidentEntity t " +
            " where  t.relationIncidentNumber = ?1   ")
    List<IncidentEntity> findRelationIncidentNumberById (   String  incidentId  );


    /**
     * 根据警情id 获得警情列表信息
     * @param incidentId 警情id
     * @param splitIncidentId 拆分警情id
     * @return
     */
    @Query("select  count(t)  from IncidentEntity t " +
            " where  t.relationIncidentNumber = ?1  and  t.retentionIncidentNumber <>?1  and  t.retentionIncidentNumber <>?2 ")
   Integer findRelationNumByIncidentIdAndSpliptIncidentId  (   String  incidentId , String splitIncidentId );

    /**
     * 根据警情id 获得警情id 以及 保留警情id
     * @param incidentIds 警情id
     * @return
     */
    @Query("select  t.relationIncidentNumber , t.retentionIncidentNumber  from IncidentEntity t " +
            " where  t.relationIncidentNumber in ( ?1 ) ")
    List<Object[]> findRelationIncidentNumberByIds( List<String> incidentIds );


    /**
     * 根据 警情id(案件id)获得 电话报警记录
     *
     * @param incidentId 警情id(案件id)
     * @return 电话报警记录 列表
     */
    @Query(" select t from TelephoneEntity t  where  t.valid = 1 and t.originalIncidentNumber = ?1    ")
    List<TelephoneEntity> findTelephoneByOriginalIncidentNumber(String incidentId);

    /**
     * 统计警情车辆携带的水总量和泡沫总量
     * @param incidentId 案件id
     * @return 返回统计结果
     */
    @Query("select sum(t.carrierBubble),sum(t.waterCarrier) from EquipmentVehicleEntity t left join HandleOrganizationVehicleEntity h on h.vehicleId=t.id where t.valid=1 and h.incidentId=?1")
    List<Object[]> countIncidentVechileMedicament(String incidentId);

    /**
     * 统计多个警情的出动车辆携带的药剂
     * @param incidentIds 案件id集合
     * @return 返回结果
     */
    @Query("select h.incidentId ,sum(t.carrierBubble),sum(t.waterCarrier) from EquipmentVehicleEntity t left join HandleOrganizationVehicleEntity h on h.vehicleId=t.id where t.valid=1 and h.incidentId in ?1 group by h.incidentId")
    List<Object[]> countIncidentsVechileMedicament(List<String> incidentIds);

}
