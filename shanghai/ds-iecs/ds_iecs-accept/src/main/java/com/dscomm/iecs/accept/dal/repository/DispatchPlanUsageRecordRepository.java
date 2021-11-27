package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.DispatchPlanUsageRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:调派方案使用记录
 * author:YangFuXi
 * Date:2021/6/21 Time:14:40
 **/
@Repository
public interface DispatchPlanUsageRecordRepository extends JpaRepository<DispatchPlanUsageRecordEntity,String> {
    /**
     * 统计方案使用次数
     * @return 返回结果
     */
    @Query("select t.planId, count(t.planId) from DispatchPlanUsageRecordEntity t where t.valid=1 group by t.planId")
    List<Object[]> countDispatchPlanUsageRecord();
}
