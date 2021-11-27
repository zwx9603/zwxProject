package com.dscomm.iecs.out.dal.repository;

import com.dscomm.iecs.out.dal.po.QueryAuditEntity;
import com.dscomm.iecs.out.graphql.inputinfo.QueryInputInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/4/22 16:59
 * @Describe
 */

@Repository
public interface AuditLogOutRepository extends JpaRepository<QueryAuditEntity, Integer> {

    /**
     * 条件查询
     * @param info
     * @return
     */
    List<QueryAuditEntity> getList(QueryInputInfo info);


    List<Object[]> countByParams(QueryInputInfo info);
}
