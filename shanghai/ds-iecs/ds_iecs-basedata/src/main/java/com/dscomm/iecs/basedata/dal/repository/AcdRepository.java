package com.dscomm.iecs.basedata.dal.repository;

import com.dscomm.iecs.basedata.dal.po.AcdEntity;
import com.dscomm.iecs.basedata.dal.po.AgentAcdEntity;
import com.dscomm.iecs.basedata.dal.po.UserAcdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 描述:acd 数据服务层
 *
 * @author YangFuxi
 * Date Time 2019/8/31 11:23
 */
public interface AcdRepository extends JpaRepository<AcdEntity,String> {


    /**
     * 获取ADC组
     *
     * @return 数据列表
     */
    @Query("select t from AcdEntity t where t.valid = 1  ")
    List<AcdEntity> findAllAcdPO();

    /**
     * 根据工号查询用户acd
     *
     * @param userId 工号
     * @return 用户acd列表
     */
    @Query("select u from UserAcdEntity u where u.valid = 1 and  u.userId = :userId")
    List<UserAcdEntity> findUserAcdPOByUserId(@Param("userId") String userId);


    /**
     * 查询全部用户acd
     *
     * @return 全部用户acd列表
     */
    @Query("select u from UserAcdEntity u where u.valid = 1  ")
    List<UserAcdEntity> findAllUserAcdPO();



    /**
     * 查询全部坐席acd
     *
     * @return 全部坐席acd列表
     */
    @Query("select a from AgentAcdEntity a where a.valid = 1 ")
    List<AgentAcdEntity> findAllAgentAcdPO();

    /**
     * 根据坐席台号查询坐席acd
     *
     * @param agentNumber 坐席
     * @return 坐席acd列表
     */
    @Query("select a from AgentAcdEntity a where a.valid = 1 and a.agentNumber = :agentNumber")
    List<AgentAcdEntity> findAgentAcdPOByAgentNumber(@Param("agentNumber") String  agentNumber);


}
