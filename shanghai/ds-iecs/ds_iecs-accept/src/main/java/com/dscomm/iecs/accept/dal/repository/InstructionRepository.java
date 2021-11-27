package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.InstructionEntity;
import com.dscomm.iecs.accept.dal.po.InstructionRecordEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * 描述：警情 指令单信息
 *
 */
public interface InstructionRepository extends Repository<InstructionEntity, String> {

    /**
     * 根据警情id  指挥id 指令类型集合 关键字 查询处警指令信息
     */
    public List<InstructionEntity> findInstructionCondition( String incidentId, String commandId, List<String> instructionType,
                                                             List<String> instructionsSource ,  String keyword) ;


    /**
     * 根据调派单ids 查询指令记录信息
     */
    @Query(" select t from InstructionEntity t   where  t.valid = 1  " +
            " and t.id in  ( ?1 )     " )
    public List<InstructionEntity> findInstruction( List<String> instructionIds ) ;

    /**
     * 根据调派单ids 查询指令记录信息
     */
    @Query(" select t from InstructionRecordEntity t   where  t.valid = 1  " +
            " and t.instructionId in  ( ?1 )     order by  t.instructionRecordTime asc  " )
    public List<InstructionRecordEntity> findInstructionRecord( List<String> instructionIds ) ;


    /**
     * 根据警情id  指挥id 指令单id 接口对象id 指令状态集合 查询处警指令信息
     */
    public List<InstructionRecordEntity> findInstructionRecordCondition(String incidentId, String commandId, String instructionId,
                                                                            String receivingObjectId, List<String> instructState ) ;





}
