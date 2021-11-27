package com.dscomm.iecs.basedata.dal.repository;

import com.dscomm.iecs.basedata.dal.po.PlanEntity;
import org.apache.logging.log4j.util.Strings;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

/**
 * 描述:预案 数据库持久层服务
 *
 */
@Repository
public class PlanRepositoryImpl  {

    @PersistenceContext
    private EntityManager entityManager;


    @Transactional(readOnly = true)
    public List<PlanEntity> findCondition( List<String> planCategoryCodes , List<String> planTypeCodes ,  String keyword ,  Boolean whetherPage,
                                           int page, int size ) {

        String headsql = "  select plan  from PlanEntity plan    where plan.valid = 1    ";
        String conditionsql = "";
        if ( null != planCategoryCodes && planCategoryCodes.size() > 0  ) {
            conditionsql = conditionsql + " and plan.planCategoryCode in ( :YAZL ) ";
        }
        if ( null != planTypeCodes && planTypeCodes.size() > 0  ) {
            conditionsql = conditionsql + " and plan.planTypeCode in ( :YALX ) ";
        }
        if (Strings.isNotBlank(keyword)) {
            conditionsql = conditionsql + " and (  plan.planName  like  :GJZ  " +
                    " or   plan.planDesc  like  :GJZ   " +
                    " or  plan.objectName like  :GJZ  " +
                    " or  plan.positionName like  :GJZ  )   ";
        }
        String endsql = "  ";
        String ordersql = " order by  plan.planOrderNum asc  ";
        String sql = headsql + conditionsql + endsql + ordersql;
        javax.persistence.Query query = entityManager.createQuery(sql);
        if ( null != planCategoryCodes && planCategoryCodes.size() > 0 ) {
            query.setParameter("YAZL", planCategoryCodes);
        }
        if (  null != planTypeCodes && planTypeCodes.size() > 0  ) {
            query.setParameter("YALX", planTypeCodes);
        }
        if (Strings.isNotBlank(keyword)) {
            query.setParameter("GJZ", "%" + keyword + "%");
        }
        if (whetherPage) {
            query.setFirstResult((page - 1) * size);
            query.setMaxResults(size);
        }
        return query.getResultList();
    }


    @Transactional(readOnly = true)
    public  Integer  findPlanConditionTotal( List<String> planCategoryCodes , List<String> planTypeCodes ,  String keyword  ){

        String headsql = "  select count(1) as  NUM    from PlanEntity plan    where plan.valid = 1    ";
        String conditionsql = "";
        if ( null != planCategoryCodes && planCategoryCodes.size() > 0  ) {
            conditionsql = conditionsql + " and plan.planCategoryCode in ( :YAZL ) ";
        }
        if ( null != planTypeCodes && planTypeCodes.size() > 0  ) {
            conditionsql = conditionsql + " and plan.planTypeCode in ( :YALX ) ";
        }
        if (Strings.isNotBlank(keyword)) {
            conditionsql = conditionsql + " and (  plan.planName  like  :GJZ  " +
                    " or   plan.planDesc  like  :GJZ   " +
                    " or  plan.objectName like  :GJZ  " +
                    " or  plan.positionName like  :GJZ  )   ";
        }
        String endsql = "  ";
        String ordersql = "   ";
        String sql = headsql + conditionsql + endsql + ordersql;
        javax.persistence.Query query = entityManager.createQuery(sql);
        if ( null != planCategoryCodes && planCategoryCodes.size() > 0 ) {
            query.setParameter("YAZL", planCategoryCodes);
        }
        if (  null != planTypeCodes && planTypeCodes.size() > 0  ) {
            query.setParameter("YALX", planTypeCodes);
        }
        if (Strings.isNotBlank(keyword)) {
            query.setParameter("GJZ", "%" + keyword + "%");
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
