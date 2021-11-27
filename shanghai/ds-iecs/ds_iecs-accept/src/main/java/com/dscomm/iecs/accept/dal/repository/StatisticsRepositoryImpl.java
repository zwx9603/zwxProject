package com.dscomm.iecs.accept.dal.repository;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class StatisticsRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;



    @Transactional(readOnly = true)
    public List<Object[]> findStatisticsVehicleStatus( String dictionaryTypeCode  , String scopeSquadronId , String  keyword  , List<String> organizationIds ,
                                                       List<String> vehicleTypeCodes , List<String> vehicleStatusCodes  , String natureLike )  {

        String leftsql = " select dictionary.bm as code , dictionary.mc as name  , dictionary.sjbm  as parentcode from " +
                "  jcj_dm dictionary " +
                " where dictionary.yxx = 1  and  dictionary.zdlx = :ZDLX  " ;
        if (vehicleStatusCodes != null && vehicleStatusCodes.size() > 0) {
            leftsql = leftsql + " and dictionary.bm in (:CLZT )  ";
        }


        leftsql = leftsql + " order by dictionary.sx asc " ;

        String headsql = " select  vehicle.CLQWZTLBDM as code , count(1) as num    FROM wl_clxx vehicle " +
                "  where vehicle.yxx = 1  ";
        String conditionsql = "";
        if (Strings.isNotBlank(scopeSquadronId)) {
            conditionsql = conditionsql + " and  vehicle.XFJYJG_TYWYSBM in (   select t.id   from jgxx_xfjg t  where  t.yxx = 1 and t.cxmlj like :GJGCXM )   ";
        }
        if (organizationIds != null && organizationIds.size() > 0) {
            conditionsql = conditionsql + " and vehicle.XFJYJG_TYWYSBM in (:CLJG )  ";
        }
        if (Strings.isNotBlank(keyword)) {
            conditionsql = conditionsql + " and (  vehicle.zbmc  like  :GJZ " +
                    " or vehicle.cljc  like  :GJZ or vehicle.JDCHPHM  like  :GJZ  )  ";
        }
        if (vehicleTypeCodes != null && vehicleTypeCodes.size() > 0) {
            conditionsql = conditionsql + " and vehicle.XFZBLXDM in (:CLLX )  ";
        }
        if (vehicleStatusCodes != null && vehicleStatusCodes.size() > 0) {
            conditionsql = conditionsql + " and vehicle.CLQWZTLBDM in (:CLZT )  ";
        }
        if( Strings.isNotBlank( natureLike )){
            conditionsql = conditionsql + " and  vehicle.XFJYJG_TYWYSBM in (   select t.id   from jgxx_xfjg t  where  t.yxx = 1 and t.XFJYJGXZDM like :JGXZ )   ";
        }
        String endsql = "  group by  vehicle.CLQWZTLBDM     ";
        String ordersql = " order by num desc       ";

        String rightsql = headsql + conditionsql + endsql + ordersql;

        String sql =  " select lft.code , lft.name , lft.parentcode , rft.num  from (  " +  leftsql  + " ) as lft left join    ( "  +   rightsql   + " ) as rft " +
                " on  lft.code = rft.code "   ;
        Query query = entityManager.createNativeQuery(sql);

        query.setParameter("ZDLX", dictionaryTypeCode );
        if (Strings.isNotBlank(scopeSquadronId)) {
            query.setParameter("GJGCXM", scopeSquadronId + "%");
        }
        if (organizationIds != null && organizationIds.size() > 0) {
            query.setParameter("CLJG", organizationIds);
        }
        if (Strings.isNotBlank(keyword)) {
            query.setParameter("GJZ", "%" + keyword + "%");
        }
        if (vehicleTypeCodes != null && vehicleTypeCodes.size() > 0) {
            query.setParameter("CLLX", vehicleTypeCodes);
        }
        if (vehicleStatusCodes != null && vehicleStatusCodes.size() > 0) {
            query.setParameter("CLZT", vehicleStatusCodes);
        }
        if( Strings.isNotBlank( natureLike )){
            query.setParameter("JGXZ", natureLike );
        }
        List<Object[]> list = query.getResultList();
        return list;
    }





    @Transactional(readOnly = true)
    public List<Object[]> findVehicleTypeStatistics ( String dictionaryTypeCode , String scopeSquadronId ,String  keyword  , List<String> organizationIds ,
                                                      List<String> vehicleTypeCodes , List<String> vehicleStatusCodes  , String natureLike )  {

        String leftsql = " select dictionary.bm as code , dictionary.mc as name  , dictionary.sjbm  as parentcode from " +
                "  jcj_dm dictionary " +
                " where dictionary.yxx = 1  and  dictionary.zdlx = :ZDLX" ;
        if (vehicleTypeCodes != null && vehicleTypeCodes.size() > 0) {
            leftsql = leftsql + " and dictionary.bm in (:CLLX )  ";
        }
        leftsql = leftsql + " order by dictionary.sx asc " ;

        String headsql = " select  vehicle.XFZBLXDM as code , count(1) as num    FROM wl_clxx vehicle " +
                "  where vehicle.yxx = 1  ";
        String conditionsql = "";
        if (Strings.isNotBlank(scopeSquadronId)) {
            conditionsql = conditionsql + " and  vehicle.XFJYJG_TYWYSBM in (   select t.id   from jgxx_xfjg t  where  t.yxx = 1 and t.cxmlj like :GJGCXM )   ";
        }
        if (organizationIds != null && organizationIds.size() > 0) {
            conditionsql = conditionsql + " and vehicle.XFJYJG_TYWYSBM in (:CLJG )  ";
        }
        if (Strings.isNotBlank(keyword)) {
            conditionsql = conditionsql + " and (  vehicle.zbmc  like  :GJZ " +
                    " or vehicle.cljc  like  :GJZ or vehicle.JDCHPHM  like  :GJZ  )  ";
        }
        if (vehicleTypeCodes != null && vehicleTypeCodes.size() > 0) {
            conditionsql = conditionsql + " and vehicle.XFZBLXDM in (:CLLX )  ";
        }
        if (vehicleStatusCodes != null && vehicleStatusCodes.size() > 0) {
            conditionsql = conditionsql + " and vehicle.XFJYJG_TYWYSBM in (:CLZT )  ";
        }
        if( Strings.isNotBlank( natureLike )){
            conditionsql = conditionsql + " and  vehicle.XFJYJG_TYWYSBM in (   select t.id   from jgxx_xfjg t  where  t.yxx = 1 and t.XFJYJGXZDM like :JGXZ )   ";
        }
        String endsql = "  group by  vehicle.XFZBLXDM     ";
        String ordersql = " order by num desc       ";

        String rightsql = headsql + conditionsql + endsql + ordersql;

        String sql =  " select rft.code ,  rft.name , rft .parentcode , lft.num  from (  " +  rightsql    + " ) as lft left join    ( "  +   leftsql   + " ) as rft " +
                " on  lft.code = rft.code "   ;
        Query query = entityManager.createNativeQuery(sql);

        query.setParameter("ZDLX", dictionaryTypeCode );

        if (Strings.isNotBlank(scopeSquadronId)) {
            query.setParameter("GJGCXM", scopeSquadronId + "%");
        }
        if (organizationIds != null && organizationIds.size() > 0) {
            query.setParameter("CLJG", organizationIds);
        }
        if (Strings.isNotBlank(keyword)) {
            query.setParameter("GJZ", "%" + keyword + "%");
        }
        if (vehicleTypeCodes != null && vehicleTypeCodes.size() > 0) {
            query.setParameter("CLLX", vehicleTypeCodes);
        }
        if (vehicleStatusCodes != null && vehicleStatusCodes.size() > 0) {
            query.setParameter("CLZT", vehicleStatusCodes);
        }
        if( Strings.isNotBlank( natureLike )){
            query.setParameter("JGXZ", natureLike );
        }
        List<Object[]> list = query.getResultList();
        return list;
    }

    @Transactional(readOnly = true)
    public List<Object[]> findVehicleOrganizationStatistics ( String scopeSquadronId ,String  keyword  , List<String> organizationIds ,
                                                              List<String> vehicleTypeCodes , List<String> vehicleStatusCodes  , String natureLike )  {

        String leftsql = " select organization.id as code , organization.DWMC as name  , organization.sjjgid  as parentcode from " +
                "  jgxx_xfjg organization " +
                " where organization.yxx = 1 " ;
        if (organizationIds != null && organizationIds.size() > 0) {
            leftsql = leftsql + " and organization.id in (:CLJG )  ";
        }

        String headsql = " select  vehicle.XFJYJG_TYWYSBM as code , count(1) as num    FROM wl_clxx vehicle " +
                "  where vehicle.yxx = 1  ";
        String conditionsql = "";
        if (Strings.isNotBlank(scopeSquadronId)) {
            leftsql = leftsql + " and organization.cxmlj like :GJGCXM ";
            conditionsql = conditionsql + " and  vehicle.XFJYJG_TYWYSBM in (   select t.id   from jgxx_xfjg t  where  t.yxx = 1 and t.cxmlj like :GJGCXM )   ";
        }
        if (organizationIds != null && organizationIds.size() > 0) {
            conditionsql = conditionsql + " and vehicle.XFJYJG_TYWYSBM in (:CLJG )  ";
        }
        if (Strings.isNotBlank(keyword)) {
            conditionsql = conditionsql + " and (  vehicle.zbmc  like  :GJZ " +
                    " or vehicle.cljc  like  :GJZ or vehicle.JDCHPHM  like  :GJZ  )  ";
        }
        if (vehicleTypeCodes != null && vehicleTypeCodes.size() > 0) {
            conditionsql = conditionsql + " and vehicle.XFZBLXDM in (:CLLX )  ";
        }
        if (vehicleStatusCodes != null && vehicleStatusCodes.size() > 0) {
            conditionsql = conditionsql + " and vehicle.CLQWZTLBDM in (:CLZT )  ";
        }
        if( Strings.isNotBlank( natureLike )){
            leftsql = leftsql + " and organization.XFJYJGXZDM like :JGXZ  ";
            conditionsql = conditionsql + " and  vehicle.XFJYJG_TYWYSBM in (   select t.id   from jgxx_xfjg t  where  t.yxx = 1 and t.XFJYJGXZDM like :JGXZ )   ";
        }

        leftsql = leftsql + " order by organization.CXMLJ asc " ;

        String endsql = "  group by  vehicle.XFJYJG_TYWYSBM     ";
        String ordersql = " order by num desc       ";

        String rightsql = headsql + conditionsql + endsql + ordersql;

        String sql =  " select rft.code , rft.name , rft.parentcode , lft.num  from (  " +  rightsql   + " ) as lft left join    ( "  +   leftsql   + " ) as rft " +
                " on  lft.code = rft.code "   ;
        Query query = entityManager.createNativeQuery(sql);
        if (Strings.isNotBlank(scopeSquadronId)) {
            query.setParameter("GJGCXM", scopeSquadronId + "%");
        }
        if (organizationIds != null && organizationIds.size() > 0) {
            query.setParameter("CLJG", organizationIds);
        }
        if (Strings.isNotBlank(keyword)) {
            query.setParameter("GJZ", "%" + keyword + "%");
        }
        if (vehicleTypeCodes != null && vehicleTypeCodes.size() > 0) {
            query.setParameter("CLLX", vehicleTypeCodes);
        }
        if (vehicleStatusCodes != null && vehicleStatusCodes.size() > 0) {
            query.setParameter("CLZT", vehicleStatusCodes);
        }
        if( Strings.isNotBlank( natureLike )){
            query.setParameter("JGXZ", natureLike );
        }
        List<Object[]> list = query.getResultList();
        return list;
    }









    @Transactional(readOnly = true)
    public List<Object[]> findIncidentTypeGroup (Long startTime, Long endTime, String districtCode, String fireSquadronId) {
        String headsql = " select  incident.incidentTypeCode as typeCode , count(1) as typeNum    FROM IncidentEntity incident " +
                "  where incident.valid = 1  ";
        String conditionsql = "";
        if (startTime != null) {
            conditionsql = conditionsql + " and incident.registerIncidentTime >=:KSSJ  ";
        }
        if (endTime != null) {
            conditionsql = conditionsql + " and incident.registerIncidentTime <:JSSJ  ";
        }
        if (Strings.isNotBlank(districtCode)) {
            conditionsql = conditionsql + " and incident.districtCode =:XZQDM  ";
        }
        if (Strings.isNotBlank(fireSquadronId)) {
            conditionsql = conditionsql + "  and incident.squadronOrganizationId in (   select t.id  from OrganizationEntity t  where  t.valid = 1 and t.searchPath like :JGCXM )  ";
        }
        String endsql = "  group by  incident.incidentTypeCode     ";
        String ordersql = " order by typeNum desc       ";
        String sql = headsql + conditionsql + endsql + ordersql;
        Query query = entityManager.createQuery(sql);
        if (startTime != null) {
            query.setParameter("KSSJ", startTime);
        }
        if (endTime != null) {
            query.setParameter("JSSJ", endTime);
        }
        if (Strings.isNotBlank(districtCode)) {
            query.setParameter("XZQDM", districtCode);
        }
        if (Strings.isNotBlank(fireSquadronId)) {
            query.setParameter("JGCXM", fireSquadronId + "%");
        }
        List<Object[]> list = query.getResultList();
        return list;
    }


    @Transactional(readOnly = true)
    public List<Object[]> findStatisticsIncidentType(String scopeSquadronId, List<String> orgIds, String keyword, List<String> incidentTypeCodes,
                                                     List<String> incidentLevelCodes, List<String> incidentStateCodes,
                                                     Boolean whetherKeyUnit, Boolean whetherImport,
                                                     Long startTime, Long endTime ,List<String> districtCodes ) {

        String headsql = "  select  incident.incidentTypeCode as typeCode , count(1) as typeNum   from IncidentEntity incident  where incident.valid = 1  ";
        String conditionsql = "";
        if (Strings.isNotBlank(scopeSquadronId)) {
            conditionsql = conditionsql + " and incident.squadronOrganizationId in (   select t.id  from OrganizationEntity t  where  t.valid = 1 and t.searchPath like :GJGCXM )  ";
        }
        if (orgIds != null && orgIds.size() > 0) {
            conditionsql = conditionsql + " and   incident.squadronOrganizationId in (:JGID ) ";
        }
        if (Strings.isNotBlank(keyword)) {
            conditionsql = conditionsql + " and incident.crimeAddress  like  :GJZ ";
        }
        if (incidentTypeCodes != null && incidentTypeCodes.size() > 0) {
            conditionsql = conditionsql + " and incident.incidentTypeCode in (:AJLX )  ";
        }
        if (incidentLevelCodes != null && incidentLevelCodes.size() > 0) {
            conditionsql = conditionsql + " and incident.incidentLevelCode in (:AJDJ )  ";
        }
        if (incidentStateCodes != null && incidentStateCodes.size() > 0) {
            conditionsql = conditionsql + " and incident.incidentStatusCode in (:AJZT )  ";
        }
        if (whetherKeyUnit) {
            conditionsql = conditionsql + " and incident.keyUnitId  is not null    ";
        }
        if (whetherImport) {
            conditionsql = conditionsql + " and incident.importSign  = 1 ";
        }
        if (startTime != null) {
            conditionsql = conditionsql + " and incident.registerIncidentTime >=:KSSJ ";
        }
        if (endTime != null) {
            conditionsql = conditionsql + " and incident.registerIncidentTime <:JSSJ ";
        }
        if ( districtCodes != null && districtCodes.size() > 0 ) {
            conditionsql = conditionsql + " and incident.districtCode in ( :XZQDM  )  ";
        }
        String endsql = "  group by  incident.incidentTypeCode     ";
        String ordersql = " order by typeNum desc       ";
        String sql = headsql + conditionsql + endsql + ordersql;
        Query query = entityManager.createQuery(sql);
        if (Strings.isNotBlank(scopeSquadronId)) {
            query.setParameter("GJGCXM", scopeSquadronId + "%");
        }
        if (orgIds != null && orgIds.size() > 0) {
            query.setParameter("JGID", orgIds);
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
        if (incidentStateCodes != null && incidentStateCodes.size() > 0) {
            query.setParameter("AJZT", incidentStateCodes);
        }
        if (startTime != null) {
            query.setParameter("KSSJ", startTime);
        }
        if (endTime != null) {
            query.setParameter("JSSJ", endTime);
        }
        if ( districtCodes != null && districtCodes.size() > 0 ) {
            query.setParameter("XZQDM", districtCodes);
        }
        List<Object[]> list = query.getResultList();
        return list;

    }

    public List<Object[]> findInjuriesAndDeaths(Long startTime, Long endTime, String districtCode, String fireSquadronId){
        String headsql = " select  sum( CAST( CASE incident.injuredCount WHEN '' THEN '0' ELSE incident.injuredCount END AS integer )  ) AS injuredCountNum, " +
                "sum( CAST( CASE incident.deathCount WHEN '' THEN '0' ELSE incident.deathCount END AS integer )  ) AS deathCountNum    " +
                "FROM IncidentEntity incident " +
                "  where incident.valid = 1  ";
        String conditionsql = "";
        if (startTime != null) {
            conditionsql = conditionsql + " and incident.registerIncidentTime >=:KSSJ  ";
        }
        if (endTime != null) {
            conditionsql = conditionsql + " and incident.registerIncidentTime <:JSSJ  ";
        }
        if (Strings.isNotBlank(districtCode)) {
            conditionsql = conditionsql + " and incident.districtCode =:XZQDM  ";
        }
        if (Strings.isNotBlank(fireSquadronId)) {
            conditionsql = conditionsql + "  and incident.squadronOrganizationId in (   select t.id  from OrganizationEntity t  where  t.valid = 1 and t.searchPath like :JGCXM )  ";
        }
        String endsql = " ";//" group by  incident.incidentTypeCode     ";
        String ordersql = "";//" order by typeNum desc       ";
        String sql = headsql + conditionsql + endsql + ordersql;
        Query query = entityManager.createQuery(sql);
        if (startTime != null) {
            query.setParameter("KSSJ", startTime);
        }
        if (endTime != null) {
            query.setParameter("JSSJ", endTime);
        }
        if (Strings.isNotBlank(districtCode)) {
            query.setParameter("XZQDM", districtCode);
        }
        if (Strings.isNotBlank(fireSquadronId)) {
            query.setParameter("JGCXM", fireSquadronId + "%");
        }
        List<Object[]> list = query.getResultList();
        return list;
    }

}
