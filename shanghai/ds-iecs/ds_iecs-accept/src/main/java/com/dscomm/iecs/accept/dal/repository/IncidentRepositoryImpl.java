package com.dscomm.iecs.accept.dal.repository;

import org.apache.logging.log4j.util.Strings;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Repository
public class IncidentRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<Object[]> findIncidentCondition(  String scopeSquadronId   , Boolean  whetherHandleIncident , List<String> orgIds, String keyword, List<String> incidentTypeCodes,
                                                  List<String> incidentLevelCodes, List<String> incidentStatusCodes,
                                                  Boolean whetherKeyUnit, Boolean whetherImport,
                                                  Long startTime, Long endTime, Boolean whetherPage,
                                                  int page, int size ,  String account   ,
                                                  Boolean whetherAttention ,  Integer type  , String receiverObjectId , List<String> filterIncidentIds
    ) {

        String headsql = "  select " +
                "   incident.id , incident.incidentNumber , incident.squadronOrganizationId   , incident.brigadeOrganizationId  , incident.districtCode   ,   " +
                "   incident.registerModeCode ,  incident.registerIncidentTime   , incident.crimeAddress  , incident.longitude   ,  incident.latitude ,  " +
                "   incident.height , incident.incidentTypeCode   , incident.incidentTypeSubitemCode  , incident.incidentLevelCode   ,  incident.disposalObjectCode , " +
                "   incident.incidentStatusCode , incident.incidentNatureCode   , incident.whetherImportSign  , incident.mergeStatus   ,   incident.whetherMainMerge , " +
                "   incident.registerSeatNumber , incident.acceptancePersonNumber   , incident.acceptancePersonName  , incident.dispatchOrganization   , incident.dispatchVehicle ,  " +
                "  " +
                "   incident.incidentGroupId  ,   " +
                " ( select  count(attention.id)  from  AttentionEntity  attention where attention.valid = 1  and attention.type = 1  and  attention.incidentId = incident.id and attention.attentionPersonId =:YHZH ) as whetherFocusOn  ," +
                "  ( select count(attention.id)  from  AttentionEntity  attention where attention.valid = 1  and attention.type = 2  and  attention.incidentId = incident.id  ) as whetherImportant , " +
                " ( select  count( atachment.id ) from  AttachmentEntity atachment where atachment.valid = 1   and atachment.incidentId  in (    select innerIncident.id from IncidentEntity innerIncident where " +
                "   innerIncident.relationIncidentNumber =  incident.id  )    ) as attachmentNum , incident.alarmPhone  ,  " +
                "  " +
                "  incident.relayRecordNumber , incident.alarmModeCode , incident.alarmPersonPhone,incident.alarmPersonName  " +
                "  " +
                " from IncidentEntity incident    " +
                "  where  1 = 1  and incident.valid = 1  ";
        String conditionsql = "";
        if (Strings.isNotBlank(scopeSquadronId)) {

            if( whetherHandleIncident ){
                conditionsql = conditionsql + " and ( 1 != 1   " +
                        " or incident.id in (  select h.incidentId   from  HandleOrganizationEntity h ,  OrganizationEntity t  where h.valid = 1 and   t.valid = 1 " +
                        " and h.organizationId = t.id and t.searchPath like :GJGCXM   ) " +
                        "  ) ";
            }else{
                conditionsql = conditionsql + " and ( 1 != 1   " +
                        " or incident.registerOrganizationId in (   select t.id   from OrganizationEntity t  where  t.valid = 1 and t.searchPath like :GJGCXM ) " +
                        " or incident.squadronOrganizationId in (   select t.id   from OrganizationEntity t  where  t.valid = 1 and t.searchPath like :GJGCXM ) " +
                        " or incident.id in (  select h.incidentId   from  HandleOrganizationEntity h ,  OrganizationEntity t  where h.valid = 1 and   t.valid = 1 " +
                        " and h.organizationId = t.id and t.searchPath like :GJGCXM   ) " +
                        "  ) ";
            }

        }
        if (orgIds != null && orgIds.size() > 0) {
            conditionsql = conditionsql + " and (  incident.squadronOrganizationId = 'NA' ";
            for (int i = 0; i < orgIds.size(); i++) {
                conditionsql = conditionsql + " or  incident.squadronOrganizationId in (   select t.id   from OrganizationEntity t  where  t.valid = 1 and t.searchPath like :XQJG" + i + " )  ";

            }
            conditionsql = conditionsql + " ) ";
        }
        if (Strings.isNotBlank(keyword)) {
            conditionsql = conditionsql + " and (incident.crimeAddress  like  :GJZ  or incident.incidentDescribe  like  :GJZ or incident.alarmPhone  like  :GJZ)";
        }
        if (incidentTypeCodes != null && incidentTypeCodes.size() > 0) {
            conditionsql = conditionsql + " and incident.incidentTypeCode in (:AJLX )  ";
        }
        if (incidentLevelCodes != null && incidentLevelCodes.size() > 0) {
            conditionsql = conditionsql + " and incident.incidentLevelCode in (:AJDJ )  ";
        }
        if (incidentStatusCodes != null && incidentStatusCodes.size() > 0) {
            conditionsql = conditionsql + " and incident.incidentStatusCode in (:AJZT )  ";
        }
        if (whetherKeyUnit) {
            conditionsql = conditionsql + " and incident.keyUnitId  is not null    ";
        }
        if (whetherImport) {
            conditionsql = conditionsql + " and incident.whetherImportSign  = 1 ";
        }
        if (startTime != null) {
            conditionsql = conditionsql + " and incident.registerIncidentTime >=:KSSJ ";
        }
        if (endTime != null) {
            conditionsql = conditionsql + " and incident.registerIncidentTime < :JSSJ ";
        }


        if( whetherAttention ){
            //type  1 警情关注 2 重要警情关注
            if( 1 == type  ){
                conditionsql = conditionsql + " and incident.id  in (" +
                        "  select attention.incidentId from  AttentionEntity  attention where attention.valid = 1  " +
                        "  and attention.type = 1  and   attention.attentionPersonId =:YHZH" +
                        " ) ";
            }else if( 2 == type  ) {
                conditionsql = conditionsql + " and incident.id  in (" +
                        "  select attention.incidentId from  AttentionEntity  attention where attention.valid = 1  " +
                        "  and attention.type = 2 " +
                        " ) ";
            }
        }
        if( Strings.isNotBlank( receiverObjectId )){
            conditionsql = conditionsql + " and  (  incident.id in (  select v.incidentId   from  HandleOrganizationVehicleEntity v where v.vehicleId =:JSDXID ) " +
                    " or   incident.id in (  select i.incidentId   from  InstructionRecordEntity i where i.receivingObjectId =:JSDXID ) " +
                    "    )  ";
        }
        if( filterIncidentIds != null && filterIncidentIds.size() > 0 ){
            conditionsql = conditionsql + " and  incident.id not in ( :GLJQID ) " ;
        }
        String endsql = "  ";
        String ordersql = " order by  whetherFocusOn desc , incident.registerIncidentTime desc  ";
        String sql = headsql + conditionsql + endsql + ordersql;
        Query query = entityManager.createQuery(sql);
        query.setParameter("YHZH", account );
        if (Strings.isNotBlank(scopeSquadronId)) {
            query.setParameter("GJGCXM", scopeSquadronId + "%");
        }
        if (orgIds != null && orgIds.size() > 0) {
            for (int i = 0; i < orgIds.size(); i++) {
                query.setParameter("XQJG" + i, orgIds.get(i) + "%");
            }
        }
        if (Strings.isNotBlank(keyword)) {
            query.setParameter("GJZ", "%" + keyword + "%");
        }
        if (incidentTypeCodes != null && incidentTypeCodes.size() > 0) {
            query.setParameter("AJLX", incidentTypeCodes);
        }
        if (incidentLevelCodes != null && incidentLevelCodes.size() > 0) {
            query.setParameter("AJDJ", incidentLevelCodes);
        }
        if (incidentStatusCodes != null && incidentStatusCodes.size() > 0) {
            query.setParameter("AJZT", incidentStatusCodes);
        }

        if (startTime != null) {
            query.setParameter("KSSJ", startTime);
        }
        if (endTime != null) {
            query.setParameter("JSSJ", endTime);
        }
        if (whetherPage) {
            query.setFirstResult((page - 1) * size);
            query.setMaxResults(size);
        }
        if( Strings.isNotBlank( receiverObjectId )){
            query.setParameter("JSDXID", receiverObjectId );
        }
        if( filterIncidentIds != null && filterIncidentIds.size() > 0 ){
            query.setParameter("GLJQID", filterIncidentIds );
        }
        return query.getResultList();
    }


    @Transactional(readOnly = true)
    public Integer findIncidentConditionTotal(  String scopeSquadronId   , Boolean  whetherHandleIncident , List<String> orgIds, String keyword, List<String> incidentTypeCodes,
                                                List<String> incidentLevelCodes, List<String> incidentStatusCodes,
                                                Boolean whetherKeyUnit, Boolean whetherImport,
                                                Long startTime, Long endTime ,  String account   ,
                                                Boolean whetherAttention ,  Integer type  , String receiverObjectId , List<String> filterIncidentIds
    ) {

        String headsql = "  select count(incident.id) as  NUM     " +
                " from IncidentEntity incident     " +
                "  where  1 = 1   and incident.valid = 1 ";
        String conditionsql = "";
        if (Strings.isNotBlank(scopeSquadronId)) {
            if( whetherHandleIncident ){
                conditionsql = conditionsql + " and ( 1 != 1   " +
                        " or incident.id in (  select h.incidentId   from  HandleOrganizationEntity h ,  OrganizationEntity t  where h.valid = 1 and   t.valid = 1 " +
                        " and h.organizationId = t.id and t.searchPath like :GJGCXM   ) " +
                        "  ) ";
            }else{
                conditionsql = conditionsql + " and ( 1 != 1   " +
                        " or incident.registerOrganizationId in (   select t.id   from OrganizationEntity t  where  t.valid = 1 and t.searchPath like :GJGCXM ) " +
                        " or incident.squadronOrganizationId in (   select t.id   from OrganizationEntity t  where  t.valid = 1 and t.searchPath like :GJGCXM ) " +
                        " or incident.id in (  select h.incidentId   from  HandleOrganizationEntity h ,  OrganizationEntity t  where h.valid = 1 and   t.valid = 1 " +
                        " and h.organizationId = t.id and t.searchPath like :GJGCXM   ) " +
                        "  ) ";
            }
        }
        if (orgIds != null && orgIds.size() > 0) {
            conditionsql = conditionsql + " and (  incident.squadronOrganizationId = 'NA' ";
            for (int i = 0; i < orgIds.size(); i++) {
                conditionsql = conditionsql + " or  incident.squadronOrganizationId in (   select t.id   from OrganizationEntity t  where  t.valid = 1 and t.searchPath like :XQJG" + i + " )  ";

            }
            conditionsql = conditionsql + " ) ";
        }
        if (Strings.isNotBlank(keyword)) {
            conditionsql = conditionsql + " and (incident.crimeAddress  like  :GJZ  or incident.incidentDescribe  like  :GJZ or incident.alarmPhone  like  :GJZ) ";
        }
        if (incidentTypeCodes != null && incidentTypeCodes.size() > 0) {
            conditionsql = conditionsql + " and incident.incidentTypeCode in (:AJLX )  ";
        }
        if (incidentLevelCodes != null && incidentLevelCodes.size() > 0) {
            conditionsql = conditionsql + " and incident.incidentLevelCode in (:AJDJ )  ";
        }
        if (incidentStatusCodes != null && incidentStatusCodes.size() > 0) {
            conditionsql = conditionsql + " and incident.incidentStatusCode in (:AJZT )  ";
        }
        if (whetherKeyUnit) {
            conditionsql = conditionsql + " and incident.keyUnitId  is not null    ";
        }
        if (whetherImport) {
            conditionsql = conditionsql + " and incident.whetherImportSign  = 1 ";
        }
        if (startTime != null) {
            conditionsql = conditionsql + " and incident.registerIncidentTime >=:KSSJ ";
        }
        if (endTime != null) {
            conditionsql = conditionsql + " and incident.registerIncidentTime < :JSSJ ";
        }
        if( whetherAttention ){
            //type  1 警情关注 2 重要警情关注
            if( 1 == type  ){
                conditionsql = conditionsql + " and incident.id  in (" +
                        "  select attention.incidentId from  AttentionEntity  attention where attention.valid = 1  " +
                        "  and attention.type = 1  and   attention.attentionPersonId =:YHZH" +
                        " ) ";
            }else if( 2 == type  ) {
                conditionsql = conditionsql + " and incident.id  in (" +
                        "  select attention.incidentId from  AttentionEntity  attention where attention.valid = 1  " +
                        "  and attention.type = 2 " +
                        " ) ";
            }
        }
        if( Strings.isNotBlank( receiverObjectId )){
            conditionsql = conditionsql + " and  (  incident.id in (  select v.incidentId   from  HandleOrganizationVehicleEntity v where v.vehicleId =:JSDXID ) " +
                    " or   incident.id in (  select i.incidentId   from  InstructionRecordEntity i where i.receivingObjectId =:JSDXID ) " +
                    "    )  ";
        }
        if( filterIncidentIds != null && filterIncidentIds.size() > 0 ){
            conditionsql = conditionsql + " and  incident.id not in ( :GLJQID ) " ;
        }
        String endsql = "  ";
        String ordersql = "  ";
        String sql = headsql + conditionsql + endsql + ordersql;
        Query query = entityManager.createQuery(sql);
        if (Strings.isNotBlank(scopeSquadronId)) {
            query.setParameter("GJGCXM", scopeSquadronId + "%");
        }
        if (orgIds != null && orgIds.size() > 0) {
            for (int i = 0; i < orgIds.size(); i++) {
                query.setParameter("XQJG" + i, orgIds.get(i) + "%");
            }
        }
        if (Strings.isNotBlank(keyword)) {
            query.setParameter("GJZ", "%" + keyword + "%");
        }
        if (incidentTypeCodes != null && incidentTypeCodes.size() > 0) {
            query.setParameter("AJLX", incidentTypeCodes);
        }
        if (incidentLevelCodes != null && incidentLevelCodes.size() > 0) {
            query.setParameter("AJDJ", incidentLevelCodes);
        }
        if (incidentStatusCodes != null && incidentStatusCodes.size() > 0) {
            query.setParameter("AJZT", incidentStatusCodes);
        }
        if (startTime != null) {
            query.setParameter("KSSJ", startTime);
        }
        if (endTime != null) {
            query.setParameter("JSSJ", endTime);
        }
        if( whetherAttention ){
            //type  1 警情关注 2 重要警情关注
            if( 1 == type  ){
                query.setParameter("YHZH", account );
            }else if( 2 == type  ) {
                //无参数设置
            }
        }
        if( Strings.isNotBlank( receiverObjectId )){
            query.setParameter("JSDXID", receiverObjectId );
        }
        if( filterIncidentIds != null && filterIncidentIds.size() > 0 ){
            query.setParameter("GLJQID", filterIncidentIds );
        }
        List<Map<String, Long>> lists = query.unwrap(org.hibernate.Query.class)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
        if (lists != null && !lists.isEmpty()) {
            Long num = lists.get(0).get("NUM");
            return Integer.parseInt(num.toString());
        }
        return 0;
    }





    @Transactional(readOnly = true)
    public List<Object[]> findIncidentDetailed( String incidentId , String mergeByMainIncidentId ,  List<String> incidentIdList ,   String account    ) {

        String headsql = "  select " +
                " incident ,  " +
                " ( select count(attention.id) from  AttentionEntity  attention where attention.valid = 1  and attention.type = 1  and  attention.incidentId = incident.id and attention.attentionPersonId =:YHZH ) as whetherFocusOn  ," +
                " ( select count(attention.id) from  AttentionEntity  attention where attention.valid = 1  and attention.type = 2  and  attention.incidentId = incident.id  ) as whetherImportant , " +
                " ( select  count( distinct handle.handleBatch )  from  HandleEntity handle where handle.valid = 1 and ( handle.incidentId = incident.id  or handle.originalIncidentNumber = incident.id  )   ) as handleBatchNum , " +
                " ( select  count( distinct handleOrg.organizationId  )  from  HandleOrganizationEntity handleOrg  where handleOrg.valid = 1 and ( handleOrg.incidentId = incident.id  or handleOrg.originalIncidentNumber = incident.id  )  )  as handleOrganizationNum , " +
                " ( select  count( distinct handleVe.vehicleId )  from  HandleOrganizationVehicleEntity handleVe where handleVe.valid = 1  and ( handleVe.incidentId = incident.id  or handleVe.originalIncidentNumber = incident.id  )    )  as handleVehicleNum  , " +
                " ( select  count( distinct participant.personId )  from  ParticipantFeedbackEntity participant  where participant.valid = 1 and ( participant.incidentId = incident.id  or participant.originalIncidentNumber = incident.id  )  )  as handlePersonNum  ,  " +
                " ( select  untrafficAlarm.originalIncidentNumber  from  UnTrafficAlarmEntity  untrafficAlarm  where untrafficAlarm.valid = 1 and  untrafficAlarm.incidentId = incident.id   )  as originalIncidentNumber   , " +
                " ( select  coalesce( sum(    coalesce( commander.personNum , vehicle.passengersNum ) ) , '0' )   from " +
                "     EquipmentVehicleEntity vehicle left join  CommanderEntity commander  on " +
                "           commander.valid = 1 and  commander.vehicleId is not null  and  commander.vehicleId !='' " +
                "           and  commander.incidentId =  incident.id  and vehicle.id = commander.vehicleId " +
                "     where vehicle.id in (  select  handleVe.vehicleId from  HandleOrganizationVehicleEntity handleVe  where  " +
                "            handleVe.valid = 1  and ( handleVe.incidentId = incident.id  or handleVe.originalIncidentNumber = incident.id  )  )   " +
                "    )  as vehicleCommandPersonNum  , " +
                " ( select  coalesce( sum(    coalesce(  vehiclePerson.personNum , vehicle.passengersNum  )   )   , '0' )   from " +
                "      EquipmentVehicleEntity vehicle left join  VehiclePersonsEntity vehiclePerson on vehiclePerson.valid = 1 and  vehiclePerson.vehicleId  = vehicle.id " +
                "       where vehicle.id in (  select  handleVe.vehicleId from  HandleOrganizationVehicleEntity handleVe  where " +
                "        handleVe.valid = 1  and ( handleVe.incidentId = incident.id  or handleVe.originalIncidentNumber = incident.id  )  )  " +
                "    )  as vehiclePersonNum ,    " +
                " ( select  coalesce( sum(    coalesce(   vehicle.passengersNum , 0  )   )   , '0' )   from " +
                "      EquipmentVehicleEntity vehicle " +
                "       where vehicle.id in (  select  handleVe.vehicleId from  HandleOrganizationVehicleEntity handleVe  where " +
                "        handleVe.valid = 1  and ( handleVe.incidentId = incident.id  or handleVe.originalIncidentNumber = incident.id  )  )  " +
                "    )  as vehiclePassengersNum  , " +
                " ( select count( distinct vp.personId) from VehiclePersonsEntity vp left join  HandleOrganizationVehicleEntity hov " +
                "   on hov.vehicleId=vp.vehicleId and hov.valid=1 where hov.incidentId=incident.id) as vehiclePersonCountNum  "+
                " " +
                " from IncidentEntity incident    " +
                "  where  1 = 1   ";
        String conditionsql = "";
        if (Strings.isNotBlank(incidentId)) {
            conditionsql = conditionsql + " and incident.id  =:AJID ";
        }
        if (incidentIdList != null && incidentIdList.size() > 0) {
            conditionsql = conditionsql + " and incident.id in (:AJIDLB )  ";
        }
        if (Strings.isNotBlank(mergeByMainIncidentId)) {
            conditionsql = conditionsql + " and incident.relationIncidentNumber = :GLAJID and incident.retentionIncidentNumber <>:GLAJID   ";
        }
        String endsql = "  ";
        String ordersql = " order by  whetherFocusOn desc , incident.registerIncidentTime desc  ";
        String sql = headsql + conditionsql + endsql + ordersql;
        Query query = entityManager.createQuery(sql);
        query.setParameter("YHZH", account);
        if (Strings.isNotBlank(incidentId)) {
            query.setParameter("AJID", incidentId);
        }
        if (incidentIdList != null && incidentIdList.size() > 0) {
            query.setParameter("AJIDLB", incidentIdList);
        }
        if (Strings.isNotBlank(incidentId)) {
            query.setParameter("AJID", incidentId);
        }
        if (Strings.isNotBlank(mergeByMainIncidentId)) {
            query.setParameter("GLAJID", mergeByMainIncidentId);
        }
        return query.getResultList();


    }















}
