package com.dscomm.iecs.out.dal.repository;

import com.dscomm.iecs.out.dal.po.QueryAuditEntity;
import com.dscomm.iecs.out.graphql.inputinfo.QueryInputInfo;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/4/22 17:02
 * @Describe
 */
@Repository
public class AuditLogOutRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<QueryAuditEntity> getList(QueryInputInfo info){

        String headSql = "select t from QueryAuditEntity t where t.valid=1 ";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(headSql);

        if (info.getQueryStartTime()!=null){
            stringBuilder.append("and t.queryStartTime >=").append(info.getQueryStartTime()) ;
        }
        if (info.getQueryEndTime()!=null){
            stringBuilder.append(" and t.queryEndTime <=").append(info.getQueryEndTime());
        }
        if (Strings.isNotBlank(info.getAccount())){
            stringBuilder.append(" and t.account like '").append("%").append(info.getAccount()).append("%'");
        }
        if (Strings.isNotBlank(info.getInterfaceName())){
            stringBuilder.append(" and t.interfaceName ='").append(info.getInterfaceName()).append("'");
        }
        if (Strings.isNotBlank(info.getQueryResult())){
            stringBuilder.append(" and t.queryResult ='").append(info.getQueryResult()).append("'");
        }

        Query query = entityManager.createQuery(stringBuilder.toString());

        return query.getResultList();

    }



    @Transactional(readOnly = true)
    public List<Object[]> countByParams(QueryInputInfo info){

        //todo
        String headSql = "select t.interfaceName,count(1) as num,sum(t.dataNum) from QueryAuditEntity t where t.valid=1 ";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(headSql);

        if (info.getQueryStartTime()!=null){
            stringBuilder.append("and t.queryStartTime >=").append(info.getQueryStartTime()) ;
        }
        if (info.getQueryEndTime()!=null){
            stringBuilder.append(" and t.queryEndTime <=").append(info.getQueryEndTime());
        }
        if (Strings.isNotBlank(info.getAccount())){
            stringBuilder.append(" and t.account like '").append("%").append(info.getAccount()).append("%'");
        }
        if (Strings.isNotBlank(info.getInterfaceName())){
            stringBuilder.append(" and t.interfaceName = '").append(info.getInterfaceName()).append("'");
        }
        if (Strings.isNotBlank(info.getQueryResult())){
            stringBuilder.append(" and t.queryResult ='").append(info.getQueryResult()).append("'");
        }
        stringBuilder.append(" group by t.interfaceName ");

        Query query = entityManager.createQuery(stringBuilder.toString());

        return query.getResultList();
    }
}
