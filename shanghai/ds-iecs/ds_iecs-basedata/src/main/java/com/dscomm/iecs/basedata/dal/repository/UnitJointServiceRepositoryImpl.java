package com.dscomm.iecs.basedata.dal.repository;

import com.dscomm.iecs.basedata.dal.po.UnitJointServiceEntity;
import org.apache.logging.log4j.util.Strings;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

/**
 * 描述:联勤保障单位 数据库持久层服务
 *
 */
@Repository
public class UnitJointServiceRepositoryImpl {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<UnitJointServiceEntity> findUnitJointServiceCondition(String keyword, String districtCode,
                                                                      String squadronOrganizationSearchPath,
                                                                      Boolean whetherPage, int page, int size){
        String headsql = "  select station  from  UnitJointServiceEntity station   where   station.valid = 1   ";

        String conditionsql = "";

        if (Strings.isNotBlank(keyword)) {
            conditionsql = conditionsql + " and ( station.stationName  like  :GJZ  or station.stationAddress  like  :GJZ  ) ";
        }
        if (Strings.isNotBlank(districtCode)) {
            conditionsql = conditionsql + " and station.districtCode = :XZQH )  ";
        }
        if (Strings.isNotBlank( squadronOrganizationSearchPath ) ) {
            conditionsql = conditionsql + " and station.organizationId in (   select t.id   from OrganizationEntity t  where  t.valid = 1 and t.searchPath like :GJGCXM )  ";
        }

        String endsql = "  ";
        String ordersql = " order by station.organizationId desc  ";
        String sql = headsql + conditionsql + endsql + ordersql;
        Query query = entityManager.createQuery(sql);
        if (Strings.isNotBlank(keyword)) {
            query.setParameter("GJZ", "%" + keyword + "%");
        }
        if (Strings.isNotBlank(districtCode)) {
            query.setParameter("XZQH", districtCode);
        }
        if (Strings.isNotBlank( squadronOrganizationSearchPath ) ) {
            query.setParameter("GJGCXM", squadronOrganizationSearchPath + "%");
        }

        if (whetherPage) {
            query.setFirstResult((page - 1) * size);
            query.setMaxResults(size);
        }
        return query.getResultList();
    }
    @Transactional(readOnly = true)
    public Integer findUnitJointServiceTotal(String keyword, String districtCode, String squadronOrganizationSearchPath){
        String headsql = "  select count(1) as NUM  from   UnitJointServiceEntity station   where   station.valid = 1   ";

        String conditionsql = "";

        if (Strings.isNotBlank(keyword)) {
            conditionsql = conditionsql + " and ( station.stationName  like  :GJZ  or station.stationAddress  like  :GJZ  ) ";
        }
        if (Strings.isNotBlank(districtCode)) {
            conditionsql = conditionsql + " and station.districtCode = :XZQH )  ";
        }
        if (Strings.isNotBlank( squadronOrganizationSearchPath ) ) {
            conditionsql = conditionsql + " and station.organizationId in (   select t.id   from OrganizationEntity t  where  t.valid = 1 and t.searchPath like :GJGCXM )  ";
        }

        String endsql = "  ";
        String ordersql = "   ";
        String sql = headsql + conditionsql + endsql + ordersql;
        Query query = entityManager.createQuery(sql);
        if (Strings.isNotBlank(keyword)) {
            query.setParameter("GJZ", "%" + keyword + "%");
        }
        if (Strings.isNotBlank(districtCode)) {
            query.setParameter("XZQH", districtCode);
        }
        if (Strings.isNotBlank( squadronOrganizationSearchPath ) ) {
            query.setParameter("GJGCXM", squadronOrganizationSearchPath + "%");
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
