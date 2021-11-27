package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.CtiCallEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/5/25 13:28
 * @Describe cti
 */
@Repository
public interface CtiCallRepository extends JpaRepository<CtiCallEntity, String> {


    List<Object[]> findCtiCallCondition(String phoneNumber, String personNameKeyword,
                                        Timestamp startTime, Timestamp endTime, List<String> ids, Boolean whetherPage,
                                        int page, int size);

    Integer findCtiCallConditionTotal(String phoneNumber, String personNameKeyword, Timestamp startTime,
                                      Timestamp endTime, List<String> ids);
}
