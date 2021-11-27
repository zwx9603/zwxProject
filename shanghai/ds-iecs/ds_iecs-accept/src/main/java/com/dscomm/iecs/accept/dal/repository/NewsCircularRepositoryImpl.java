package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.NewsCircularEntity;
import com.dscomm.iecs.accept.dal.po.NewsCircularReceiverEntity;
import org.apache.logging.log4j.util.Strings;
import org.hibernate.transform.Transformers;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

public class NewsCircularRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional( readOnly =  true )
    public List<NewsCircularEntity> findNewsCircularCondition(Long   startTime , Long   endTime , List<String> type ,
                                                              List<String> source , String keyword ,String titleKeyword , String contentKeyword  , Boolean whetherPage,
                                                              int page, int size )  {

        String headsql = "  select newsCircular  from NewsCircularEntity newsCircular   where newsCircular.valid = 1  " ;
        String conditionsql = "" ;
        if( startTime != null ){
            conditionsql = conditionsql + " and newsCircular.circularTime>=:KSSJ  " ;
        }
        if( endTime != null ){
            conditionsql = conditionsql + " and newsCircular.circularTime<:JSSJ  " ;
        }
        if(  type != null && type.size() > 0    ){
            conditionsql = conditionsql + " and  newsCircular.type in  (:ZLTL)  " ;
        }
        if(  source != null && source.size() > 0    ){
            conditionsql = conditionsql + " and  newsCircular.source in  (:ZLLY)  " ;
        }
        if( Strings.isNotBlank( keyword ) ){
            conditionsql = conditionsql + " and ( newsCircular.title  like  :GJZ  or newsCircular.content  like  :GJZ  ) " ;
        }
        if( Strings.isNotBlank( titleKeyword ) ){
            conditionsql = conditionsql + " and ( newsCircular.title  like  :BTGJZ   ) " ;
        }
        if( Strings.isNotBlank( contentKeyword ) ){
            conditionsql = conditionsql + " and (   newsCircular.content  like  :NRGJZ  ) " ;
        }
        String endsql = "  " ;
        String ordersql = " order by newsCircular.circularTime desc  " ;
        String  sql = headsql + conditionsql + endsql +ordersql  ;
        Query query = entityManager.createQuery( sql );
        if( startTime != null ){
            query.setParameter( "KSSJ" , startTime );
        }
        if( endTime != null ){
            query.setParameter( "JSSJ" , endTime );
        }
        if(  type != null && type.size() > 0    ){
            query.setParameter( "ZLTL" , type );
        }
        if(  source != null && source.size() > 0    ){
            query.setParameter( "ZLLY" , source );
        }
        if( Strings.isNotBlank( keyword ) ){
            query.setParameter( "GJZ" , "%" + keyword + "%");
        }
        if( Strings.isNotBlank( titleKeyword ) ){
            query.setParameter( "BTGJZ" , "%" + titleKeyword + "%");
        }
        if( Strings.isNotBlank( contentKeyword ) ){
            query.setParameter( "NRGJZ" , "%" + contentKeyword + "%");
        }
        if (whetherPage) {
            query.setFirstResult((page - 1) * size);
            query.setMaxResults(size);
        }
        return query.getResultList();
    }



    @Transactional( readOnly =  true )
    public  Integer findNewsCircularConditionTotal ( Long   startTime , Long   endTime ,  List<String> type ,
                                                     List<String> source , String keyword ,String titleKeyword , String contentKeyword   ){
        String headsql = "  select count(1) as  NUM  from NewsCircularEntity newsCircular  where 1=1     ";
        String conditionsql = "" ;
        if( startTime != null ){
            conditionsql = conditionsql + " and newsCircular.circularTime>=:KSSJ  " ;
        }
        if( endTime != null ){
            conditionsql = conditionsql + " and newsCircular.circularTime<:JSSJ  " ;
        }
        if(  type != null && type.size() > 0    ){
            conditionsql = conditionsql + " and  newsCircular.type in  (:ZLTL)  " ;
        }
        if(  source != null && source.size() > 0    ){
            conditionsql = conditionsql + " and  newsCircular.source in  (:ZLLY)  " ;
        }
        if( Strings.isNotBlank( keyword ) ){
            conditionsql = conditionsql + " and ( newsCircular.title  like  :GJZ  or newsCircular.content  like  :GJZ  ) " ;
        }
        if( Strings.isNotBlank( titleKeyword ) ){
            conditionsql = conditionsql + " and ( newsCircular.title  like  :BTGJZ   ) " ;
        }
        if( Strings.isNotBlank( contentKeyword ) ){
            conditionsql = conditionsql + " and (   newsCircular.content  like  :NRGJZ  ) " ;
        }
        String endsql = "  " ;
        String ordersql = "  ";
        String sql = headsql + conditionsql + endsql + ordersql;
        Query query = entityManager.createQuery(sql);
        if( startTime != null ){
            query.setParameter( "KSSJ" , startTime );
        }
        if( endTime != null ){
            query.setParameter( "JSSJ" , endTime );
        }
        if(  type != null && type.size() > 0    ){
            query.setParameter( "ZLTL" , type );
        }
        if(  source != null && source.size() > 0    ){
            query.setParameter( "ZLLY" , source );
        }
        if( Strings.isNotBlank( keyword ) ){
            query.setParameter( "GJZ" , "%" + keyword + "%");
        }
        if( Strings.isNotBlank( titleKeyword ) ){
            query.setParameter( "BTGJZ" , "%" + titleKeyword + "%");
        }
        if( Strings.isNotBlank( contentKeyword ) ){
            query.setParameter( "NRGJZ" , "%" + contentKeyword + "%");
        }
        List<Map<String, Long>> lists = query.unwrap(org.hibernate.Query.class)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
        if (lists != null && !lists.isEmpty()) {
            Long num = lists.get(0).get("NUM");
            return Integer.parseInt(num.toString());
        }
        return 0;
    }




    @Transactional( readOnly =  true )
    public  List<NewsCircularReceiverEntity> findNewsCircularReceiverCondition(Long   startTime , Long   endTime , List<String> type ,
                                                List<String> source , String keyword , String titleKeyword , String contentKeyword ,
                                                 String receivingObjectId , List<Integer>   newsCircularStatus ,  Boolean whetherPage,
                                                int page, int size )  {

        String headsql = "  select t  from  NewsCircularReceiverEntity t  , NewsCircularEntity newsCircular   " +
                " where newsCircular.valid = 1  and t.valid = 1 and t.newsCircularId = newsCircular.id  " ;
        String conditionsql = "" ;
        if( Strings.isNotBlank( receivingObjectId ) ){
            conditionsql = conditionsql + " and  t.receiverObjectId =:JSDXID  " ;
        }
        if( newsCircularStatus != null && newsCircularStatus.size() > 0  ){
            conditionsql = conditionsql + " and  t.newsCircularStatus in ( :JSXXZT ) " ;
        }
        if( startTime != null ){
            conditionsql = conditionsql + " and newsCircular.circularTime>=:KSSJ  " ;
        }
        if( endTime != null ){
            conditionsql = conditionsql + " and newsCircular.circularTime<:JSSJ  " ;
        }
        if(  type != null && type.size() > 0    ){
            conditionsql = conditionsql + " and  newsCircular.type in  (:ZLTL)  " ;
        }
        if(  source != null && source.size() > 0    ){
            conditionsql = conditionsql + " and  newsCircular.source in  (:ZLLY)  " ;
        }
        if( Strings.isNotBlank( keyword ) ){
            conditionsql = conditionsql + " and ( newsCircular.title  like  :GJZ  or newsCircular.content  like  :GJZ  ) " ;
        }
        if( Strings.isNotBlank( titleKeyword ) ){
            conditionsql = conditionsql + " and ( newsCircular.title  like  :BTGJZ   ) " ;
        }
        if( Strings.isNotBlank( contentKeyword ) ){
            conditionsql = conditionsql + " and (   newsCircular.content  like  :NRGJZ  ) " ;
        }
        String endsql = "  " ;
        String ordersql = " order by newsCircular.circularTime desc  " ;
        String  sql = headsql + conditionsql + endsql +ordersql  ;
        Query query = entityManager.createQuery( sql );
        if( Strings.isNotBlank( receivingObjectId ) ){
            query.setParameter( "JSDXID" , receivingObjectId );
        }
        if( newsCircularStatus != null && newsCircularStatus.size() > 0  ){
            query.setParameter( "JSXXZT" , newsCircularStatus );
        }
        if( startTime != null ){
            query.setParameter( "KSSJ" , startTime );
        }
        if( endTime != null ){
            query.setParameter( "JSSJ" , endTime );
        }
        if(  type != null && type.size() > 0    ){
            query.setParameter( "ZLTL" , type );
        }
        if(  source != null && source.size() > 0    ){
            query.setParameter( "ZLLY" , source );
        }
        if( Strings.isNotBlank( keyword ) ){
            query.setParameter( "GJZ" , "%" + keyword + "%");
        }
        if( Strings.isNotBlank( titleKeyword ) ){
            query.setParameter( "BTGJZ" , "%" + titleKeyword + "%");
        }
        if( Strings.isNotBlank( contentKeyword ) ){
            query.setParameter( "NRGJZ" , "%" + contentKeyword + "%");
        }
        if (whetherPage) {
            query.setFirstResult((page - 1) * size);
            query.setMaxResults(size);
        }
        return query.getResultList();
    }



    @Transactional( readOnly =  true )
    public Integer findNewsCircularReceiverConditionTotal ( Long   startTime , Long   endTime ,  List<String> type ,
                                                            List<String> source , String keyword ,String titleKeyword , String contentKeyword ,
                                                            String receivingObjectId  , List<Integer>   newsCircularStatus  ) {
        String headsql = "  select count(1) as  NUM  from  NewsCircularReceiverEntity t  , NewsCircularEntity newsCircular  " +
                "           where newsCircular.valid = 1  and t.valid = 1 and t.newsCircularId = newsCircular.id    ";
        String conditionsql = "" ;
        if( Strings.isNotBlank( receivingObjectId ) ){
            conditionsql = conditionsql + " and  t.receiverObjectId =:JSDXID  " ;
        }
        if( newsCircularStatus != null && newsCircularStatus.size() > 0  ){
            conditionsql = conditionsql + " and  t.newsCircularStatus in ( :JSXXZT ) " ;
        }
        if( startTime != null ){
            conditionsql = conditionsql + " and newsCircular.circularTime>=:KSSJ  " ;
        }
        if( endTime != null ){
            conditionsql = conditionsql + " and newsCircular.circularTime<:JSSJ  " ;
        }
        if(  type != null && type.size() > 0    ){
            conditionsql = conditionsql + " and  newsCircular.type in  (:ZLTL)  " ;
        }
        if(  source != null && source.size() > 0    ){
            conditionsql = conditionsql + " and  newsCircular.source in  (:ZLLY)  " ;
        }
        if( Strings.isNotBlank( keyword ) ){
            conditionsql = conditionsql + " and ( newsCircular.title  like  :GJZ  or newsCircular.content  like  :GJZ  ) " ;
        }
        if( Strings.isNotBlank( titleKeyword ) ){
            conditionsql = conditionsql + " and ( newsCircular.title  like  :BTGJZ   ) " ;
        }
        if( Strings.isNotBlank( contentKeyword ) ){
            conditionsql = conditionsql + " and (   newsCircular.content  like  :NRGJZ  ) " ;
        }
        String endsql = "  " ;
        String ordersql = "  ";
        String sql = headsql + conditionsql + endsql + ordersql;
        Query query = entityManager.createQuery(sql);
        if( Strings.isNotBlank( receivingObjectId ) ){
            query.setParameter( "JSDXID" , receivingObjectId );
        }
        if( newsCircularStatus != null && newsCircularStatus.size() > 0  ){
            query.setParameter( "JSXXZT" , newsCircularStatus );
        }
        if( startTime != null ){
            query.setParameter( "KSSJ" , startTime );
        }
        if( endTime != null ){
            query.setParameter( "JSSJ" , endTime );
        }
        if(  type != null && type.size() > 0    ){
            query.setParameter( "ZLTL" , type );
        }
        if(  source != null && source.size() > 0    ){
            query.setParameter( "ZLLY" , source );
        }
        if( Strings.isNotBlank( keyword ) ){
            query.setParameter( "GJZ" , "%" + keyword + "%");
        }
        if( Strings.isNotBlank( titleKeyword ) ){
            query.setParameter( "BTGJZ" , "%" + titleKeyword + "%");
        }
        if( Strings.isNotBlank( contentKeyword ) ){
            query.setParameter( "NRGJZ" , "%" + contentKeyword + "%");
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
