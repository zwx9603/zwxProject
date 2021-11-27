package com.dscomm.iecs.basedata.dal.repository;


import com.dscomm.iecs.basedata.dal.po.OrganizationEntity;
import com.dscomm.iecs.basedata.graphql.typebean.OrganizationBean;
import com.dscomm.iecs.ext.organization.naturehead.ORGANIZATION_NATURE_HEAD_XJZD;
import com.dscomm.iecs.ext.organization.naturehead.ORGANIZATION_NATURE_HEAD_XXDD;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * 描述： 机构数据底层服务
 */
@Repository
public class OrganizationRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<OrganizationEntity> findOrganizationCondition(String searchPath, String keyword, Boolean whetherOnlySquadron) {
        String headsql = "  select organization  from OrganizationEntity organization  where organization.valid = 1  ";
        String conditionsql = "";

        if (Strings.isNotBlank(searchPath)) {
            conditionsql = conditionsql + " and organization.searchPath like :GJGCXM    ";
        }

        if (Strings.isNotBlank(keyword)) {
            conditionsql = conditionsql + " and (  organization.organizationName  like  :GJZ " +
                    " or organization.organizationAddress  like  :GJZ )  ";
        }

        if (whetherOnlySquadron != null && whetherOnlySquadron) {
            conditionsql = conditionsql + " and organization.organizationNatureCode like :ZDJGXZ ";
        }

        String endsql = "  ";
        String ordersql = " order by     COALESCE(  ( select px.organizationOrderNum from OrganizationEntity px where " +
                "  px.id = organization.organizationParentId  and  px.organizationNatureCode like '" +
                ORGANIZATION_NATURE_HEAD_XXDD.getCode()  + "%' )  , organization.organizationOrderNum )  asc  ,     organization.organizationOrderNum asc  ";
        String sql = headsql + conditionsql + endsql + ordersql;
        Query query = entityManager.createQuery(sql);
        if (Strings.isNotBlank(searchPath)) {
            query.setParameter("GJGCXM", searchPath + "%");
        }
        if (Strings.isNotBlank(keyword)) {
            query.setParameter("GJZ", "%" + keyword + "%");
        }
        // 若只查询中队
        if (whetherOnlySquadron != null && whetherOnlySquadron) {
            String natureLike = ORGANIZATION_NATURE_HEAD_XJZD.getCode() + "%"; //救援站（中队）
            query.setParameter("ZDJGXZ", natureLike );
        }
        return query.getResultList();
    }



    @Transactional(readOnly = true)
    public List<OrganizationEntity> findOrganizationNatureAll( String searchPath  , String nature  )  {
        String headsql = "  select organization  from OrganizationEntity organization  where organization.valid = 1  ";
        String conditionsql = "";

        if (Strings.isNotBlank(searchPath)) {
            conditionsql = conditionsql + " and organization.searchPath like :GJGCXM    ";
        }

        if ( Strings.isNotBlank( nature ) ) {
            conditionsql = conditionsql + " and organization.organizationNatureCode like :JGXZ ";
        }

        String endsql = "  ";
        String ordersql = " order by     COALESCE(  ( select px.organizationOrderNum from OrganizationEntity px where " +
                "  px.id = organization.organizationParentId  and  px.organizationNatureCode like '" +
                ORGANIZATION_NATURE_HEAD_XXDD.getCode()  + "%' )  , organization.organizationOrderNum )  asc  ,     organization.organizationOrderNum asc  ";
        String sql = headsql + conditionsql + endsql + ordersql;
        Query query = entityManager.createQuery(sql);
        if (Strings.isNotBlank(searchPath)) {
            query.setParameter("GJGCXM", searchPath + "%");
        }
        if ( Strings.isNotBlank( nature ) ) {
            query.setParameter("JGXZ", nature + "%" );
        }
        return query.getResultList();
    }


}
