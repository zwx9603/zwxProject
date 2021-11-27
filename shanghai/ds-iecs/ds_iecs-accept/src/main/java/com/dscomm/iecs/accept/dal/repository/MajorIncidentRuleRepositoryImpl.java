package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.MajorIncidentRuleEntity;
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
public class MajorIncidentRuleRepositoryImpl {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional( readOnly =  true )
    public List<MajorIncidentRuleEntity> findMajorIncidentRule(String describe, String incidentType, int priority, String ruleType, Boolean whetherPage, int page, int size) {
        String headsql = "  select major  from  MajorIncidentRuleEntity major   where   major.valid = 1   ";

        String conditionsql = "";
        if (priority != 0) {
            conditionsql = conditionsql + " and major.priority  = :YXJ  ";
        }
        if (Strings.isNotBlank(describe)) {
            conditionsql = conditionsql + " and major.districtCode = :MS  ";
        }
        if (Strings.isNotBlank(incidentType)) {
            conditionsql = conditionsql + " and major.incidentType = :AJLX  ";
        }
        if (Strings.isNotBlank( ruleType ) ) {
            conditionsql = conditionsql + " and major.ruleType = :XZQH  ";
        }

        String endsql = "  ";
        String ordersql = "  ";
        String sql = headsql + conditionsql + endsql + ordersql;
        Query query = entityManager.createQuery(sql);
        if (priority != 0) {
            query.setParameter("YXJ", priority);
        }

        if (Strings.isNotBlank(describe)) {
            query.setParameter("MS", "%" + describe + "%");
        }
        if (Strings.isNotBlank(incidentType)) {
            query.setParameter("AJLX", incidentType);
        }
        if (Strings.isNotBlank( ruleType ) ) {
            query.setParameter("XZQH", ruleType );
        }

        if (whetherPage) {
            query.setFirstResult((page - 1) * size);
            query.setMaxResults(size);
        }
        return query.getResultList();
    }

    @Transactional( readOnly =  true )
    public Integer findMajorIncidentRuleTotal(String describe, String incidentType, int priority, String ruleType){
        String headsql = "  select count(1) as NUM  from   MajorIncidentRuleEntity major   where   major.valid = 1   ";

        String conditionsql = "";
        if (priority != 0) {
            conditionsql = conditionsql + " and major.priority  = :YXJ  ";
        }
        if (Strings.isNotBlank(describe)) {
            conditionsql = conditionsql + " and major.districtCode = :MS  ";
        }
        if (Strings.isNotBlank(incidentType)) {
            conditionsql = conditionsql + " and major.incidentType = :AJLX  ";
        }
        if (Strings.isNotBlank( ruleType ) ) {
            conditionsql = conditionsql + " and major.ruleType = :XZQH  ";
        }

        String endsql = "  ";
        String ordersql = "   ";
        String sql = headsql + conditionsql + endsql + ordersql;
        Query query = entityManager.createQuery(sql);
        if (priority != 0) {
            query.setParameter("YXJ", priority);
        }
        if (Strings.isNotBlank(describe)) {
            query.setParameter("MS", "%" + describe + "%");
        }
        if (Strings.isNotBlank(incidentType)) {
            query.setParameter("AJLX", incidentType);
        }
        if (Strings.isNotBlank( ruleType ) ) {
            query.setParameter("XZQH", ruleType );
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
