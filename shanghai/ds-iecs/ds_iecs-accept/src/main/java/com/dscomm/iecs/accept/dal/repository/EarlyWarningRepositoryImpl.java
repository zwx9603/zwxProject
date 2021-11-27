package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.EarlyWarningEntity;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * 描述:预警信息 数据库持久层服务
 */
@Repository
public class  EarlyWarningRepositoryImpl  {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<EarlyWarningEntity> findEarlyWarning(String incidentId, String earlyWarningType, List<String> receiveOrganizationIds ) {

        String headsql = "  select t from EarlyWarningEntity t   where t.valid = 1  ";
        String conditionsql = "";

        if( Strings.isNotBlank( incidentId ) ){
            conditionsql = conditionsql + " and t.incidentId = :AJID    ";
        }
        if( Strings.isNotBlank( earlyWarningType ) ){
            conditionsql = conditionsql + " and t.earlyWarningType = :YJLX    ";
        }
        if( receiveOrganizationIds != null && receiveOrganizationIds.size() >0  ){
            conditionsql = conditionsql + " and t.receiveOrganizationId in ( :JSDWID )     ";
        }
        String endsql = "  ";
        String ordersql = " order by t.sendTime desc   ";
        String sql = headsql + conditionsql + endsql + ordersql;
        javax.persistence.Query query = entityManager.createQuery(sql);
        if( Strings.isNotBlank( incidentId ) ){
            query.setParameter("AJID", incidentId);
        }
        if( Strings.isNotBlank( earlyWarningType ) ){
            query.setParameter("YJLX", earlyWarningType);
        }
        if(receiveOrganizationIds != null && receiveOrganizationIds.size() >0  ){
            query.setParameter("JSDWID", receiveOrganizationIds );
        }
        return query.getResultList();
    }

}
