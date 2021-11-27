package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.HierarchicalDispatchEntity;
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
public class HierarchicalDispatchRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<HierarchicalDispatchEntity> findHierarchicalDispatchCondition ( String organizationId , String incidentTypeCode, String incidentLevelCode, String disposalObjectCode ,
                      Boolean whetherPage,  int page, int size) {

        String headsql = "  select hierarchicalDispatch  from HierarchicalDispatchEntity hierarchicalDispatch  where hierarchicalDispatch.valid = 1  ";
        String conditionsql = "";

        if (  Strings.isNotBlank(organizationId)  ) {
            conditionsql = conditionsql + " and hierarchicalDispatch.organizationId = :JGID    ";
        }
        if (  Strings.isNotBlank(incidentTypeCode)  ) {
            conditionsql = conditionsql + " and hierarchicalDispatch.incidentTypeCode = :AJLX    ";
        }
        if (  Strings.isNotBlank(incidentLevelCode)  ) {
            conditionsql = conditionsql + " and hierarchicalDispatch.incidentLevelCode = :AJDJ    ";
        }
        if ( Strings.isNotBlank(disposalObjectCode)  ) {
            conditionsql = conditionsql + " and hierarchicalDispatch.disposalObjectCode = :CZDX    ";
        }

        String endsql = "  ";
        String ordersql = " order by hierarchicalDispatch.updatedTime desc  ";
        String sql = headsql + conditionsql + endsql + ordersql;
        Query query = entityManager.createQuery(sql);

        if (  Strings.isNotBlank(organizationId)  ) {
            query.setParameter("JGID", organizationId);
        }
        if ( Strings.isNotBlank(incidentTypeCode) ) {
            query.setParameter("AJLX", incidentTypeCode);
        }
        if (  Strings.isNotBlank(incidentLevelCode) ) {
            query.setParameter("AJDJ", incidentLevelCode );
        }
        if (  Strings.isNotBlank(disposalObjectCode) ) {
            query.setParameter("CZDX", disposalObjectCode);
        }
        if (whetherPage) {
            query.setFirstResult((page - 1) * size);
            query.setMaxResults(size);
        }
        return query.getResultList();
    }


    @Transactional(readOnly = true)
    public Integer findHierarchicalDispatchTotal( String organizationId , String incidentTypeCode, String incidentLevelCode, String disposalObjectCode  ) {

        String headsql = "  select count(1) as  NUM   from HierarchicalDispatchEntity hierarchicalDispatch  where hierarchicalDispatch.valid = 1  ";
        String conditionsql = "";

        if (  Strings.isNotBlank(organizationId)  ) {
            conditionsql = conditionsql + " and hierarchicalDispatch.organizationId = :JGID    ";
        }
        if (  Strings.isNotBlank(incidentTypeCode)  ) {
            conditionsql = conditionsql + " and hierarchicalDispatch.incidentTypeCode = :AJLX    ";
        }
        if (  Strings.isNotBlank(incidentLevelCode)  ) {
            conditionsql = conditionsql + " and hierarchicalDispatch.incidentLevelCode = :AJDJ    ";
        }
        if ( Strings.isNotBlank(disposalObjectCode)  ) {
            conditionsql = conditionsql + " and hierarchicalDispatch.disposalObjectCode = :CZDX    ";
        }

        String endsql = "  ";
        String ordersql = "  ";
        String sql = headsql + conditionsql + endsql + ordersql;
        Query query = entityManager.createQuery(sql);

        if (  Strings.isNotBlank(organizationId)  ) {
            query.setParameter("JGID", organizationId);
        }
        if ( Strings.isNotBlank(incidentTypeCode) ) {
            query.setParameter("AJLX", incidentTypeCode);
        }
        if (  Strings.isNotBlank(incidentLevelCode) ) {
            query.setParameter("AJDJ", incidentLevelCode );
        }
        if (  Strings.isNotBlank(disposalObjectCode) ) {
            query.setParameter("CZDX", disposalObjectCode);
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
