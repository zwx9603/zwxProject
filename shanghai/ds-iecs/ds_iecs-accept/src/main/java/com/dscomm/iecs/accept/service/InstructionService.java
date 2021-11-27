package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.inputbean.InstructionQueryInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.InstructionRecordHandleSaveInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.InstructionRecordQueryInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.InstructionSaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.InstructionBean;
import com.dscomm.iecs.accept.graphql.typebean.InstructionRecordBean;

import java.util.List;

public interface InstructionService {


    /**
     * 保存指令 （包含下达特别提示）
     */
    InstructionBean saveInstruction ( InstructionSaveInputInfo queryBean  );


    /**
     * 根据条件 查询调派单(指令单)信息
     */
    List<InstructionBean> findInstructionCondition (InstructionQueryInputInfo queryBean   );


    /**
     * 根据条件 查询 指令记录信息
     */
    List<InstructionRecordBean> findInstructionRecordCondition (InstructionRecordQueryInputInfo queryBean    );



    /**
     * 根据条件 查询 指令记录信息
     */
    Boolean saveInstructionRecordHandle ( InstructionRecordHandleSaveInputInfo queryBean    );



}
