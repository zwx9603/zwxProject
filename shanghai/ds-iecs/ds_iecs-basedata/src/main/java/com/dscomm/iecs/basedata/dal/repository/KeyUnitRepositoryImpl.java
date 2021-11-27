package com.dscomm.iecs.basedata.dal.repository;

import com.dscomm.iecs.basedata.dal.po.KeyUnitEntity;
import org.mx.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class KeyUnitRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<KeyUnitEntity> findKeyUnitCondition(String keyword , String searchPath){
        String headSql = "select t from KeyUnitEntity t where t.valid = 1 ";

        String conditionsql = "";

        if (!StringUtils.isBlank(keyword)){
            conditionsql += " and t.unitAddress like :DWDZ";
        }
        if (!StringUtils.isBlank(searchPath)){
            conditionsql += " and (   t.jurisdictionOrganizationId is null or  t.jurisdictionOrganizationId = '' or t.jurisdictionOrganizationId in ( select o.id from OrganizationEntity o where o.searchPath like :CXMLJ ) )";
        }

        String endsql = "  ";
        String ordersql = " order by t.unitName asc  ";
        String sql = headSql + conditionsql + endsql + ordersql;
        Query query = entityManager.createQuery(sql);

        if (!StringUtils.isBlank(keyword)){
            query.setParameter("DWDZ","%"+keyword+"%");
        }
        if (!StringUtils.isBlank(searchPath)){
            query.setParameter("CXMLJ",searchPath+"%");
        }

        List<KeyUnitEntity> res = query.getResultList();
        return res;
    }
}
