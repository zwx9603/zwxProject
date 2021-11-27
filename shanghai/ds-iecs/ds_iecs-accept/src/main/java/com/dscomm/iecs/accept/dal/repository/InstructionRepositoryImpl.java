package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.InstructionEntity;
import com.dscomm.iecs.accept.dal.po.InstructionRecordEntity;
import org.apache.logging.log4j.util.Strings;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class InstructionRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional( readOnly =  true )
    public List<InstructionEntity> findInstructionCondition( String incidentId, String commandId, List<String> instructionType,
                                                             List<String> instructionsSource ,  String keyword )  {

        String headsql = "  select instruction  from InstructionEntity instruction  where instruction.valid = 1  " ;
        String conditionsql = "" ;
        if(  Strings.isNotBlank( incidentId  )  ){
            conditionsql = conditionsql + " and instruction.incidentId = :SJID   " ;
        }
        if(  Strings.isNotBlank( commandId )    ){
            conditionsql = conditionsql + " and instruction.commandId = :ZHID   " ;
        }
        if(  instructionType != null && instructionType.size() > 0    ){
            conditionsql = conditionsql + " and  instruction.instructionsType in  (:ZLTL)  " ;
        }
        if(  instructionsSource != null && instructionsSource.size() > 0    ){
            conditionsql = conditionsql + " and  instruction.instructionsSource in  (:ZLLY)  " ;
        }
        if( Strings.isNotBlank( keyword ) ){
            conditionsql = conditionsql + " and ( instruction.instructionsContent  like  :GJZ  or instruction.instructionsContentExtension  like  :GJZ  ) " ;
        }
        String endsql = "  " ;
        String ordersql = " order by instruction.instructionsTime desc  " ;
        String  sql = headsql + conditionsql + endsql +ordersql  ;
        Query query = entityManager.createQuery( sql );
        if(  Strings.isNotBlank( incidentId  )  ){
            query.setParameter( "SJID" , incidentId );
        }
        if(  Strings.isNotBlank( commandId )    ){
            query.setParameter( "ZHID" , commandId );
        }
        if(  instructionType != null && instructionType.size() > 0    ){
            query.setParameter( "ZLTL" , instructionType );
        }
        if(  instructionsSource != null && instructionsSource.size() > 0    ){
            query.setParameter( "ZLLY" , instructionsSource );
        }
        if( Strings.isNotBlank( keyword ) ){
            query.setParameter( "GJZ" , "%" + keyword + "%");
        }
        return query.getResultList();
    }



    @Transactional( readOnly =  true )
    public List<InstructionRecordEntity> findInstructionRecordCondition (String incidentId , String commandId , String instructionId ,
                                                                         String receivingObjectId  , List<String> dispatchInstructionStatus      )  {

        String headsql = "  select instructionRecord  from InstructionRecordEntity instructionRecord  where instructionRecord.valid = 1  " ;
        String conditionsql = "" ;
        if(  Strings.isNotBlank( incidentId  )  ){
            conditionsql = conditionsql + " and instructionRecord.incidentId = :SJID   " ;
        }
        if(  Strings.isNotBlank( commandId )    ){
            conditionsql = conditionsql + " and instructionRecord.commandId = :ZHID   " ;
        }
        if(  Strings.isNotBlank( instructionId )    ){
            conditionsql = conditionsql + " and instructionRecord.instructionId = :DPDID   " ;
        }
        if(  Strings.isNotBlank( receivingObjectId )    ){
            conditionsql = conditionsql + " and instructionRecord.receivingObjectId = :JSDXID   " ;
        }
        if(  dispatchInstructionStatus != null && dispatchInstructionStatus.size() > 0    ){
            conditionsql = conditionsql + " and  instructionRecord.instructState in  (:ZLZT)  " ;
        }
        String endsql = "  " ;
        String ordersql = " order by instructionRecord.instructionRecordTime desc  " ;
        String  sql = headsql + conditionsql + endsql +ordersql  ;
        Query query = entityManager.createQuery( sql );
        if(  Strings.isNotBlank( incidentId  )  ){
            query.setParameter( "SJID" , incidentId );
        }
        if(  Strings.isNotBlank( commandId )    ){
            query.setParameter( "ZHID" , commandId );
        }
        if(  Strings.isNotBlank( instructionId )    ){
            query.setParameter( "DPDID" , instructionId );
        }
        if(  Strings.isNotBlank( receivingObjectId )    ){
            query.setParameter( "JSDXID" , receivingObjectId );
        }
        if(  dispatchInstructionStatus != null && dispatchInstructionStatus.size() > 0    ){
            query.setParameter( "ZLZT" , dispatchInstructionStatus );
        }
        return query.getResultList();
    }






}
