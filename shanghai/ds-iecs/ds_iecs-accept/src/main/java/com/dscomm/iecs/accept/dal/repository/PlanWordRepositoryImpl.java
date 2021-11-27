package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.PlanWordEntity;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Repository
public class PlanWordRepositoryImpl {


    @PersistenceContext
    private EntityManager entityManager;


    @Transactional(readOnly = true)
    public List<PlanWordEntity> findPlanWordCondition(Boolean whetherPage, int page, int size ) {

        String headsql = "  select  plan  from PlanWordEntity plan  where plan.valid = 1      ";
        String conditionsql = "";

        String endsql = "  ";

        String ordersql = " ";
        String sql = headsql + conditionsql + endsql + ordersql;
        Query query = entityManager.createQuery(sql);

        if (whetherPage) {
            query.setFirstResult((page - 1) * size);
            query.setMaxResults(size);
        }
        return query.getResultList();
    }


    @Transactional(readOnly = true)
    public Integer findPlanWordConditionTotal(){
        String headsql = "  select count(1) as  NUM  from PlanWordEntity plan  where plan.valid = 1  ";
        String conditionsql = "";

        String endsql = "  ";

        String ordersql = " ";
        String sql = headsql + conditionsql + endsql + ordersql;
        Query query = entityManager.createQuery(sql);

        List<Map<String, Long>> lists = query.unwrap(org.hibernate.Query.class)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();

        if (lists != null && !lists.isEmpty()) {
            Long num = lists.get(0).get("NUM");
            return Integer.parseInt(num.toString());
        }
        return 0;
    }
}
