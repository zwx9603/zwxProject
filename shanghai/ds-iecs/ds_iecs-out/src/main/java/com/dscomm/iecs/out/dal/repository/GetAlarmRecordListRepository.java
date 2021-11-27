package com.dscomm.iecs.out.dal.repository;

import com.dscomm.iecs.accept.dal.po.AcceptanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/4/26 16:49
 * @Describe
 */
@Repository
public interface GetAlarmRecordListRepository extends JpaRepository<AcceptanceEntity, String> {


    @Query(value = "select t from AcceptanceEntity t where t.createdTime>=?1 and t.createdTime<=?2 and " +
            " t.squadronOrganizationId in (?3) or t.brigadeOrganizationId in (?3) or t.registerOrganizationId in (?3)" +
            " and t.valid=1" )
    List<AcceptanceEntity> getAcceptanceEntities(Long st, Long et, List<String> ids);

}
