package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.BlacklistEntity;
import org.apache.logging.log4j.util.Strings;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

/**
 * 描述:标签 数据库持久层服务
 */
@Repository
public class TagLabelRepositoryImpl {



    @PersistenceContext
    private EntityManager entityManager;

    public   List<BlacklistEntity> findTagLabelCondition(String keyword , String phoneKeyword , String personNameKeyword , List<String> tagType ,  String businessTable ,  Long  startTime  ,  Long  endTime , Boolean whetherPage,
                                                         int page, int size ) {

        String headsql = "  select  taglale  from  TagLabelEntity  taglale  where taglale.valid=1     ";
        String conditionsql = "";
        if (Strings.isNotBlank(keyword)) {
            if( tagType != null && tagType.size() > 0 ){
                conditionsql = conditionsql + " and  ( taglale.businessDataId like :GJZ  or  taglale.personName like :GJZ or  " ;
                for( int i = 0  ; i< tagType.size()  ; i ++ ){
                    conditionsql = conditionsql + " taglale.tagType like :BJLX" +  i + "  or " ;
                }
                conditionsql = conditionsql + " 1=2  ) " ;

            }else{
                conditionsql = conditionsql + " and    ( taglale.businessDataId like :GJZ or  taglale.personName like :GJZ  )     ";
            }
        }
        if (Strings.isNotBlank(phoneKeyword)) {
            conditionsql = conditionsql + " and  ( taglale.businessDataId like :DHGJZ  )   ";
        }
        if (Strings.isNotBlank(personNameKeyword)) {
            conditionsql = conditionsql + " and  (   taglale.personName like :YHMGJZ )   ";
        }
        if( Strings.isNotBlank( businessTable) ){
            conditionsql = conditionsql + " and  taglale.businessTable = :YWBM  "  ;
        }
        if( startTime != null ){
            conditionsql = conditionsql + " and taglale.addTime>=:KSSJ  " ;
        }
        if( endTime != null ) {
            conditionsql = conditionsql + " and taglale.addTime<:JSSJ  ";
        }
        String endsql = "  ";
        String ordersql = " order by taglale.addTime desc  ";
        String sql = headsql + conditionsql + endsql + ordersql;
        javax.persistence.Query query = entityManager.createQuery(sql);
        if (Strings.isNotBlank(keyword)) {
            if( tagType != null && tagType.size() > 0 ){
                query.setParameter("GJZ", "%" + keyword + "%" );
                for( int i = 0  ; i< tagType.size()  ; i ++ ){
                    String code = tagType.get(i) ;
                    query.setParameter("BJLX" + i , "%" +  code + "%" );
                }
            }else{
                query.setParameter("GJZ", "%" + keyword + "%" );
            }
        }
        if (Strings.isNotBlank(phoneKeyword)) {
            query.setParameter("DHGJZ", "%" + phoneKeyword + "%" );
        }
        if (Strings.isNotBlank(personNameKeyword)) {
            query.setParameter("YHMGJZ", "%" + personNameKeyword + "%" );
        }
        if( Strings.isNotBlank( businessTable) ){
            query.setParameter("YWBM", businessTable );
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


    public  Integer findTagLabelConditionTotal ( String keyword  , String phoneKeyword , String personNameKeyword , List<String> tagType ,   String businessTable , Long  startTime  ,  Long  endTime   ){
        String headsql = "  select count(1) as  NUM  from TagLabelEntity taglale  where taglale.valid=1     ";
        String conditionsql = "";
        if (Strings.isNotBlank(keyword)) {
            if( tagType != null && tagType.size() > 0 ){
                conditionsql = conditionsql + " and  (  taglale.businessDataId like :GJZ or  taglale.personName like :GJZ or " ;
                for( int i = 0  ; i< tagType.size()  ; i ++ ){
                    conditionsql = conditionsql + " taglale.tagType like :BJLX" +  i + "  or " ;
                }
                conditionsql = conditionsql + " 1=2  )  " ;
            }else{
                conditionsql = conditionsql + " and    (  taglale.businessDataId like :GJZ or  taglale.personName like :GJZ )    ";
            }
        }
        if (Strings.isNotBlank(phoneKeyword)) {
            conditionsql = conditionsql + " and  ( taglale.businessDataId like :DHGJZ  )   ";
        }
        if (Strings.isNotBlank(personNameKeyword)) {
            conditionsql = conditionsql + " and  (   taglale.personName like :YHMGJZ )   ";
        }
        if( Strings.isNotBlank( businessTable) ){
            conditionsql = conditionsql + " and  taglale.businessTable = :YWBM  "  ;
        }
        if( startTime != null ){
            conditionsql = conditionsql + " and taglale.addTime>=:KSSJ  " ;
        }
        if( endTime != null ) {
            conditionsql = conditionsql + " and taglale.addTime<:JSSJ  ";
        }
        String endsql = "  ";
        String ordersql = "  ";
        String sql = headsql + conditionsql + endsql + ordersql;
        Query query = entityManager.createQuery(sql);
        if (Strings.isNotBlank(keyword)) {
            if( tagType != null && tagType.size() > 0 ){
                query.setParameter("GJZ", "%" + keyword + "%" );
                for( int i = 0  ; i< tagType.size()  ; i ++ ){
                    String code = tagType.get(i) ;
                    query.setParameter("BJLX" + i , "%" +  code + "%" );
                }
            }else{
                query.setParameter("GJZ", "%" + keyword + "%" );
            }
        }
        if (Strings.isNotBlank(phoneKeyword)) {
            query.setParameter("DHGJZ", "%" + phoneKeyword + "%" );
        }
        if (Strings.isNotBlank(personNameKeyword)) {
            query.setParameter("YHMGJZ", "%" + personNameKeyword + "%" );
        }
        if( Strings.isNotBlank( businessTable) ){
            query.setParameter("YWBM", businessTable );
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
