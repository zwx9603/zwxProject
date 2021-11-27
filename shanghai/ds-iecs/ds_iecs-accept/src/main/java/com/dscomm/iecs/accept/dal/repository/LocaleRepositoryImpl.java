package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.LocaleEntity;
import org.apache.logging.log4j.util.Strings;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * 描述:现场信息 数据库持久层服务
 */
public class LocaleRepositoryImpl   {


    @PersistenceContext
    private EntityManager entityManager;


    @Transactional( readOnly =  true )
    public   List<LocaleEntity> findLocaleCondition (String incidentId , String commandId , String instructId  ,
                                                     String instructRecordId  , List<Integer> localeType,
                                                     List<Integer> localeSource  , String  feedbackObjectId , String keyword      ) {
        String headsql = "  select feedback  from LocaleEntity feedback  where feedback.valid = 1 and feedback.whetherFileFeedback = 0   " ;
        String conditionsql = "" ;
        if(  Strings.isNotBlank( incidentId  )  ){
            conditionsql = conditionsql + " and feedback.incidentId = :SJID   " ;
        }
        if(  Strings.isNotBlank( commandId )    ){
            conditionsql = conditionsql + " and feedback.commandId = :ZHID   " ;
        }
        if(  Strings.isNotBlank( instructId )    ){
            conditionsql = conditionsql + " and feedback.instructId = :DPDID   " ;
        }
        if(  Strings.isNotBlank( instructRecordId )    ){
            conditionsql = conditionsql + " and feedback.instructRecordId = :DPZLID   " ;
        }
        if(  localeType != null && localeType.size() > 0    ){
            conditionsql = conditionsql + " and  feedback.localeType in  (:ZLLX)  " ;
        }
        if(  localeSource != null && localeSource.size() > 0    ){
            conditionsql = conditionsql + " and  feedback.localeSource in  (:ZLLY)  " ;
        }
        if(  Strings.isNotBlank( feedbackObjectId  )    ){
            conditionsql = conditionsql + " and feedback.feedbackObjectId = :JSDXID   " ;
        }
        if(  Strings.isNotBlank( keyword )    ){
            conditionsql = conditionsql + " and  ( feedback.localeDesc like :GZJ  or feedback.localeExtension like :GZJ  )  " ;
        }
        String endsql = "  " ;
        String ordersql = " order by feedback.collectionTime desc  " ;
        String  sql = headsql + conditionsql + endsql +ordersql  ;
        Query query = entityManager.createQuery( sql );
        if(  Strings.isNotBlank( incidentId  )  ){
            query.setParameter( "SJID" , incidentId );
        }
        if(  Strings.isNotBlank( commandId )    ){
            query.setParameter( "ZHID" , commandId );
        }
        if(  Strings.isNotBlank( instructId )    ){
            query.setParameter( "DPDID" , instructId );
        }
        if(  Strings.isNotBlank( instructRecordId )    ){
            query.setParameter( "DPZLID" , instructRecordId );
        }
        if(  localeType != null && localeType.size() > 0    ){
            query.setParameter( "ZLLX" , localeType );
        }
        if(  localeSource != null && localeSource.size() > 0    ){
            query.setParameter( "ZLLY" , localeSource );
        }
        if(  Strings.isNotBlank( feedbackObjectId )    ){
            query.setParameter( "JSDXID" , feedbackObjectId );
        }
        if(  Strings.isNotBlank( keyword )    ){
            query.setParameter( "GZJ" , "%"+keyword+"%" );
        }
        return query.getResultList();
    }




}
