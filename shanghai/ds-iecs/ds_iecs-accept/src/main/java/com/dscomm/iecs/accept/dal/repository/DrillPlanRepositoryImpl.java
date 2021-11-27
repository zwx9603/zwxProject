package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.DrillPlanEntity;
import org.apache.logging.log4j.util.Strings;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

/**
 * 描述:集合演练方案 数据库持久层服务
 *
 */
@Repository
public class DrillPlanRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;


    @Transactional(readOnly = true)
    public List<DrillPlanEntity> findDrillPlanCondition(  String organizationId , List<String> incidentTypeCodes, String keyword ,
                                                          Boolean whetherEnableStatus , Boolean whetherEnableTime , Long currentTime , Boolean whetherPage,
                                               int page, int size) {

        String headsql = "  select drillPlan  from DrillPlanEntity drillPlan    where drillPlan.valid = 1    ";
        String conditionsql = "";
        if( Strings.isNotBlank( organizationId )){
            conditionsql = conditionsql + " and drillPlan.makeOrganizationId  = :ZZDWID   ";
        }
        if ( null != incidentTypeCodes && incidentTypeCodes.size() > 0  ) {
            conditionsql = conditionsql + " and drillPlan.incidentTypeCode in ( :AJLX ) ";
        }
        if (Strings.isNotBlank(keyword)) {
            conditionsql = conditionsql + " and (  drillPlan.drillPlanName  like  :GJZ  " +
                    " or   drillPlan.drillPlanContent  like  :GJZ   " +
                    " or  drillPlan.makePersonName like  :GJZ  )   ";
        }
        if( whetherEnableStatus ){
            conditionsql = conditionsql + " and drillPlan.whetherEnable  = 1   ";
        }
        if( whetherEnableTime ){
            conditionsql = conditionsql + " and ( drillPlan.invalidTime >= :DQSJ  or   drillPlan.enableTime <= :DQSJ ) ";
        }
        String endsql = "  ";
        String ordersql = " order by  drillPlan.makeTime desc  ";
        String sql = headsql + conditionsql + endsql + ordersql;
        javax.persistence.Query query = entityManager.createQuery(sql);
        if( Strings.isNotBlank( organizationId )){
            query.setParameter("ZZDWID", organizationId);
        }
        if ( null != incidentTypeCodes && incidentTypeCodes.size() > 0 ) {
            query.setParameter("AJLX", incidentTypeCodes);
        }
        if (Strings.isNotBlank(keyword)) {
            query.setParameter("GJZ", "%" + keyword + "%");
        }
        if ( whetherEnableTime ) {
            query.setParameter("DQSJ", currentTime );
        }
        if (whetherPage) {
            query.setFirstResult((page - 1) * size);
            query.setMaxResults(size);
        }
        return query.getResultList();
    }


    @Transactional(readOnly = true)
    public  Integer  findDrillPlanConditionTotal( String organizationId ,  List<String> incidentTypeCodes ,  String keyword ,
                                                  Boolean whetherEnableStatus , Boolean whetherEnableTime , Long currentTime ){

        String headsql = "  select count(1) as  NUM    from DrillPlanEntity drillPlan    where drillPlan.valid = 1    ";
        String conditionsql = "";
        if( Strings.isNotBlank( organizationId )){
            conditionsql = conditionsql + " and drillPlan.makeOrganizationId  = :ZZDWID   ";
        }
        if ( null != incidentTypeCodes && incidentTypeCodes.size() > 0  ) {
            conditionsql = conditionsql + " and drillPlan.incidentTypeCode in ( :AJLX ) ";
        }
        if (Strings.isNotBlank(keyword)) {
            conditionsql = conditionsql + " and (  drillPlan.drillPlanName  like  :GJZ  " +
                    " or   drillPlan.drillPlanContent  like  :GJZ   " +
                    " or  drillPlan.makePersonName like  :GJZ  )   ";
        }
        if( whetherEnableStatus ){
            conditionsql = conditionsql + " and drillPlan.whetherEnable  = 1   ";
        }
        if( whetherEnableTime ){
            conditionsql = conditionsql + " and ( drillPlan.invalidTime >= :DQSJ  or   drillPlan.enableTime <= :DQSJ ) ";
        }
        String endsql = "  ";
        String ordersql = "   ";
        String sql = headsql + conditionsql + endsql + ordersql;
        javax.persistence.Query query = entityManager.createQuery(sql);
        if( Strings.isNotBlank( organizationId )){
            query.setParameter("ZZDWID", organizationId);
        }
        if ( null != incidentTypeCodes && incidentTypeCodes.size() > 0 ) {
            query.setParameter("AJLX", incidentTypeCodes);
        }
        if (Strings.isNotBlank(keyword)) {
            query.setParameter("GJZ", "%" + keyword + "%");
        }
        if ( whetherEnableTime ) {
            query.setParameter("DQSJ", currentTime );
        }
        List<Map<String, Long>> lists = query.unwrap(org.hibernate.Query.class)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
        if (lists != null && !lists.isEmpty()) {
            Long num = lists.get(0).get("NUM");
            return Integer.parseInt(num.toString());
        }
        return 0;
    }

}
