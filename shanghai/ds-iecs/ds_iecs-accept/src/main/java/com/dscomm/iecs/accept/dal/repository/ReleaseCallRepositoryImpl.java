package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.ReleaseCallEntity;
import com.dscomm.iecs.accept.graphql.inputbean.ReleaseCallQueryInputInfo;
import org.apache.logging.log4j.util.Strings;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/5/10 14:22
 * @Describe
 */
public class ReleaseCallRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;


    @Transactional(readOnly = true)
    public List<ReleaseCallEntity> findReleaseCallEntities(ReleaseCallQueryInputInfo inputInfo){

        String headSql = " select t from ReleaseCallEntity t where t.valid=1 ";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(headSql);

        if (inputInfo.getStartTime()!=null){
            stringBuilder.append("and t.queuedTime >=").append(inputInfo.getStartTime()) ;
        }
        if (inputInfo.getEndTime()!=null){
            stringBuilder.append(" and t.queuedTime <=").append(inputInfo.getEndTime());
        }
//        if (Strings.isNotBlank(inputInfo.getOrgId())){
//            stringBuilder.append(" and t.orgId ='").append(inputInfo.getOrgId()).append("'");
//        }
        stringBuilder.append(" order by t.createdTime desc ");

        Query query = entityManager.createQuery(stringBuilder.toString());

        return query.getResultList();


    }

    @Transactional(readOnly = true)
    public List<ReleaseCallEntity> findReleaseCallEntitiesByIds(ReleaseCallQueryInputInfo inputInfo,List<String> orgIds){

        String headSql = " select t from ReleaseCallEntity t where t.valid=1 ";

        String conditionSql = "";

        if (inputInfo.getStartTime()!=null) {
            conditionSql = conditionSql + " and t.queuedTime >=:KSSJ ";
        }

        if (inputInfo.getEndTime()!=null) {
            conditionSql = conditionSql + " and t.queuedTime <=:JSSJ  ";
        }

        if (orgIds!=null&&orgIds.size()>0) {
            conditionSql = conditionSql + " and t.orgId in(:IDS)";
        }

        String orderSql = " order by t.createdTime desc  ";
        String sql = headSql + conditionSql + orderSql;
        Query query = entityManager.createQuery(sql);

        if (inputInfo.getStartTime()!=null) {
            query.setParameter("KSSJ", inputInfo.getStartTime());
        }

        if (inputInfo.getEndTime()!=null) {
            query.setParameter("JSSJ", inputInfo.getEndTime());
        }

        if (orgIds!=null&&orgIds.size()>0) {
            query.setParameter("IDS", orgIds);
        }
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(headSql);
//
//        if (inputInfo.getStartTime()!=null){
//            stringBuilder.append("and t.queuedTime >=").append(inputInfo.getStartTime()) ;
//        }
//        if (inputInfo.getEndTime()!=null){
//            stringBuilder.append(" and t.queuedTime <=").append(inputInfo.getEndTime());
//        }
//        if (orgIds!=null&&orgIds.size()>0){
//            stringBuilder.append(" and t.orgId in'").append(orgIds).append("'");
//        }
//        stringBuilder.append(" order by t.createdTime desc ");
//
//        Query query = entityManager.createQuery(stringBuilder.toString());

        return query.getResultList();


    }
}
