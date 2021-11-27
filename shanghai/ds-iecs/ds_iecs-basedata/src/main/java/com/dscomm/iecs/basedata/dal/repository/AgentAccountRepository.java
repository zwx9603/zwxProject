package com.dscomm.iecs.basedata.dal.repository;

import com.dscomm.iecs.basedata.dal.po.AcdEntity;
import com.dscomm.iecs.basedata.dal.po.AgentAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/5/19 19:02
 * @Describe
 */
public interface AgentAccountRepository extends JpaRepository<AgentAccountEntity,String> {

    @Query("select t from AgentAccountEntity t where t.valid = 1 and t.account=?1 order by t.latesttime desc")
    List<AgentAccountEntity> findAllAgentAccount(String account);
}
