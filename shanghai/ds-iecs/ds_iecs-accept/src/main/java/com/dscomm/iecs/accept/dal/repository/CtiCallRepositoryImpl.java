package com.dscomm.iecs.accept.dal.repository;

import org.apache.logging.log4j.util.Strings;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/5/25 13:55
 * @Describe
 */
@Repository
public class CtiCallRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<Object[]> findCtiCallCondition(String phoneNumber, String personNameKeyword,
                                               Timestamp startTime, Timestamp endTime, List<String> ids, Boolean whetherPage,
                                               int page, int size) {
        String headSql = "select cti,yh.name,org.organizationName from CtiCallEntity cti,OrganizationEntity org," +
                "SystemUserEntity yh where cti.personId=yh.userCode and yh.organizationCode=org.id ";

        String conditionSql = "";
        if (page<1){
            page=1;
        }
        if (size<1){
            size=20;
        }
        if (Strings.isNotBlank(phoneNumber)) {
            conditionSql = conditionSql + " and  ( cti.callNumber like :DH or cti.calledNumber like :DH )";
        }
        if (Strings.isNotBlank(personNameKeyword)) {
            conditionSql = conditionSql + " and  ( yh.name like :XM  ) ";
        }
        if (ids != null && ids.size() > 0) {
            conditionSql = conditionSql + " and cti.personId in (select userCode from SystemUserEntity yh where yh" +
                    ".valid=1 and yh.organizationCode in(:IDS))";
        }
        if (startTime != null) {
            conditionSql = conditionSql + " and cti.startTime>=:KSSJ ";
        }
        if (endTime != null) {
            conditionSql = conditionSql + " and cti.endTime<=:JSSJ  ";
        }

        String ordersql = " order by cti.startTime desc  ";
        String sql = headSql + conditionSql + ordersql;
        Query query = entityManager.createQuery(sql);

        if (Strings.isNotBlank(phoneNumber)) {
            query.setParameter("DH", "%" + phoneNumber + "%");
        }
        if (Strings.isNotBlank(personNameKeyword)) {
            query.setParameter("XM", "%" + personNameKeyword + "%");
        }
        if (ids != null && ids.size() > 0) {
            query.setParameter("IDS", ids);
        }
        if (startTime != null) {
            query.setParameter("KSSJ", startTime);
        }
        if (endTime != null) {
            query.setParameter("JSSJ", endTime);
        }
        //分页
            query.setFirstResult((page - 1) * size);
            query.setMaxResults(page*size);

        return query.getResultList();


    }

    @Transactional(readOnly = true)
    public Integer findCtiCallConditionTotal(String phoneNumber, String personNameKeyword, Timestamp startTime,
                                             Timestamp endTime, List<String> ids) {

        String headSql = "select count(1) as  NUM  from CtiCallEntity cti,OrganizationEntity org," +
                "SystemUserEntity yh where cti.personId=yh.userCode and yh.organizationCode=org.id ";

        String conditionSql = "";

        if (Strings.isNotBlank(phoneNumber)) {
            conditionSql = conditionSql + " and  ( cti.callNumber like :DH or cti.calledNumber like :DH )";
        }
        if (Strings.isNotBlank(personNameKeyword)) {
            conditionSql = conditionSql + " and  ( yh.name like :XM  ) ";
        }
        if (ids != null && ids.size() > 0) {
            conditionSql = conditionSql + " and cti.personId in (select userCode from SystemUserEntity yh where yh" +
                    ".valid=1 and yh.organizationCode in(:IDS))";
        }
        if (startTime != null) {
            conditionSql = conditionSql + " and cti.startTime>=:KSSJ ";
        }
        if (endTime != null) {
            conditionSql = conditionSql + " and cti.endTime<=:JSSJ  ";
        }

        String sql = headSql + conditionSql;
        Query query = entityManager.createQuery(sql);

        if (Strings.isNotBlank(phoneNumber)) {
            query.setParameter("DH", "%" + phoneNumber + "%");
        }
        if (Strings.isNotBlank(personNameKeyword)) {
            query.setParameter("XM", "%" + personNameKeyword + "%");
        }
        if (ids != null && ids.size() > 0) {
            query.setParameter("IDS", ids);
        }
        if (startTime != null) {
            query.setParameter("KSSJ", startTime);
        }
        if (endTime != null) {
            query.setParameter("JSSJ", endTime);
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
