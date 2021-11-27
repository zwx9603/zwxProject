package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.BlacklistEntity;
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
 * 描述:黑名单 数据库持久层服务
 */
@Repository
public class BlacklistRepositoryImpl {



    @PersistenceContext
    private EntityManager entityManager;

    @Transactional( readOnly =  true )
    public  List<BlacklistEntity> findBlacklistCondition(String keyword , String phoneKeyword , String personNameKeyword ,  Long  startTime  ,  Long  endTime ,  Boolean whetherPage,
                                                 int page, int size ){

       String headsql = "  select  black  from BlacklistEntity black  where 1=1     ";
       String conditionsql = "";
       if (Strings.isNotBlank(keyword)) {
           conditionsql = conditionsql + " and  ( black.phoneNumber like :GJZ or black.personName like :GJZ )   ";
       }
       if (Strings.isNotBlank(phoneKeyword)) {
            conditionsql = conditionsql + " and  ( black.phoneNumber like :DHGJZ  )   ";
       }
       if (Strings.isNotBlank(personNameKeyword)) {
            conditionsql = conditionsql + " and  (   black.personName like :YHMGJZ )   ";
       }
       if( startTime != null ){
           conditionsql = conditionsql + " and black.endTime>=:KSSJ  " ;
       }
       if( endTime != null ){
           conditionsql = conditionsql + " and black.startTime<:JSSJ  " ;
       }
       String endsql = "  ";
       String ordersql = " order by black.startTime desc  ";
       String sql = headsql + conditionsql + endsql + ordersql;
       Query query = entityManager.createQuery(sql);
       if (Strings.isNotBlank(keyword)) {
           query.setParameter("GJZ", "%" + keyword + "%" );
       }
       if (Strings.isNotBlank(phoneKeyword)) {
            query.setParameter("DHGJZ", "%" + phoneKeyword + "%" );
        }
        if (Strings.isNotBlank(personNameKeyword)) {
            query.setParameter("YHMGJZ", "%" + personNameKeyword + "%" );
        }
       if( startTime != null ){
           query.setParameter( "KSSJ" , startTime );
       }
       if( endTime != null ){
           query.setParameter( "JSSJ" , endTime );
       }
       if (whetherPage) {
           query.setFirstResult((page - 1) * size);
           query.setMaxResults(size);
       }
       return query.getResultList();
   }

    @Transactional( readOnly =  true )
    public  Integer findBlacklistConditionTotal(String keyword , String phoneKeyword , String personNameKeyword  ,  Long  startTime  ,  Long  endTime   ){
        String headsql = "  select count(1) as  NUM  from BlacklistEntity black  where 1=1     ";
        String conditionsql = "";
        if (Strings.isNotBlank(keyword)) {
            conditionsql = conditionsql + " and  ( black.phoneNumber like :GJZ or black.personName like :GJZ )   ";
        }
        if (Strings.isNotBlank(phoneKeyword)) {
            conditionsql = conditionsql + " and  ( black.phoneNumber like :DHGJZ  )   ";
        }if (Strings.isNotBlank(personNameKeyword)) {
            conditionsql = conditionsql + " and  (   black.personName like :YHMGJZ )   ";
        }
        if( startTime != null ){
            conditionsql = conditionsql + " and black.endTime>=:KSSJ  " ;
        }
        if( endTime != null ) {
            conditionsql = conditionsql + " and black.startTime<:JSSJ  ";
        }
        String endsql = "  ";
        String ordersql = "  ";
        String sql = headsql + conditionsql + endsql + ordersql;
        Query query = entityManager.createQuery(sql);
        if (Strings.isNotBlank(keyword)) {
            query.setParameter("GJZ", "%" + keyword + "%" );
        }
        if (Strings.isNotBlank(phoneKeyword)) {
            query.setParameter("DHGJZ", "%" + phoneKeyword + "%" );
        }if (Strings.isNotBlank(personNameKeyword)) {
            query.setParameter("YHMGJZ", "%" + personNameKeyword + "%" );
        }
        if( startTime != null ){
            query.setParameter( "KSSJ" , startTime );
        }
        if( endTime != null ){
            query.setParameter( "JSSJ" , endTime );
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
