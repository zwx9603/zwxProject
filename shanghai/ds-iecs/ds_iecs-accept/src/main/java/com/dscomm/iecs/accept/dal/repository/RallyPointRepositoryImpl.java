package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.RallyItemFeedbackEntity;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class RallyPointRepositoryImpl  {

    @PersistenceContext
    private EntityManager entityManager;



    public List<RallyItemFeedbackEntity> findRallyItemFeedbackCondition (String incidentId  , String commandId , String rallyPointId ,
                                                                         String rallyItemId , String feedbackObjectId , String keyword ) {
        String headsql = "  select rallyItemFeedback  from RallyItemFeedbackEntity rallyItemFeedback  where rallyItemFeedback.valid = 1  " ;
        String conditionsql = "" ;

        if( Strings.isNotBlank( incidentId )  ){
            conditionsql = conditionsql + " and  rallyItemFeedback.incidentId = :SJID    " ;
        }
        if( Strings.isNotBlank( commandId ) ){
            conditionsql = conditionsql + " and  rallyItemFeedback.commandId = :ZHID  " ;
        }
        if( Strings.isNotBlank( rallyPointId )  ){
            conditionsql = conditionsql + " and  rallyItemFeedback.rallyPointId = :JJDID  " ;
        }
        if( Strings.isNotBlank( rallyItemId ) ){
            conditionsql = conditionsql + " and  rallyItemFeedback.rallyItemId = :JJXID  " ;
        }
        if( Strings.isNotBlank( feedbackObjectId )  ){
            conditionsql = conditionsql + " and  rallyItemFeedback.feedbackObjectId = :FKDXID  " ;
        }
        if( Strings.isNotBlank( keyword ) ){
            conditionsql = conditionsql + " and  rallyItemFeedback.feedbackContent  like  :GJZ     " ;
        }

        String endsql = "  " ;
        String ordersql = " order by rallyItemFeedback.feedbackTime asc  " ;
        String sql = headsql + conditionsql + endsql +  ordersql  ;
        Query query = entityManager.createQuery( sql );

        if( Strings.isNotBlank( incidentId )  ){
            query.setParameter( "SJID" , incidentId );
        }
        if( Strings.isNotBlank( commandId ) ){
            query.setParameter( "ZHID" , commandId );
        }
        if( Strings.isNotBlank( rallyPointId )  ){
            query.setParameter( "JJDID" , rallyPointId );
        }
        if( Strings.isNotBlank( rallyItemId ) ){
            query.setParameter( "JJXID" , rallyItemId );
        }
        if( Strings.isNotBlank( feedbackObjectId )  ){
            query.setParameter( "FKDXID" , feedbackObjectId );
        }
        if(Strings.isNotBlank( keyword ) ){
            query.setParameter( "GJZ" , "%" + keyword + "%");
        }
        return query.getResultList();
    }


}
