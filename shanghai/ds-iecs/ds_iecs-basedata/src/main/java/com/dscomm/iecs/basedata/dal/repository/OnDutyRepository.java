package com.dscomm.iecs.basedata.dal.repository;

import com.dscomm.iecs.basedata.dal.po.OnDutySummaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 值班数据底层服务
 */
@Repository
public interface OnDutyRepository extends JpaRepository<OnDutySummaryEntity, String> {


    @Query(" select t  from OnDutySummaryEntity t  , OrganizationEntity o  " +
            " where t.valid=1 and o.valid = 1 and t.organizationId  = o.id " +
            " and t.onDutyTime>=?1 and t.onDutyTime<?2 and   o.searchPath like ?3      " +
            " order by  t.onDutyTime desc , o.organizationTree asc , t.showNumber asc  ")
    List<OnDutySummaryEntity> findAllOrganizationOnDuty(Long startTime, Long endTime, String searchPath );

    @Query(" select t  from OnDutySummaryEntity t , OrganizationEntity o  " +
            " where t.valid=1 and t.organizationId  = o.id " +
            " and t.onDutyTime>=?1 and t.onDutyTime<?2 and  t.organizationId =?3" +
            " order by coalesce ( ( SELECT b.organizationOrderNum FROM OrganizationEntity b WHERE b.valid = 1 AND b.id = o.organizationParentId AND b.organizationNatureCode LIKE'%05%%' ), o.organizationOrderNum ) ASC," +
            "                o.organizationOrderNum ASC," +
            "t.onDutyTime desc , o.organizationTree asc , t.showNumber asc  ")
    List<OnDutySummaryEntity> findOrganizationOnDuty(Long startTime, Long endTime, String organizationId);

    @Query(" select t  from OnDutySummaryEntity t , OrganizationEntity o  " +
            " where t.valid=1 and t.organizationId  = o.id" +
            "  and t.onDutyTime>=?1 and t.onDutyTime<?2 and  t.organizationId in ( ?3 ) " +
            " order by  t.onDutyTime desc , o.organizationTree asc , t.showNumber asc ")
    List<OnDutySummaryEntity> findOrganizationOnDuty(Long startTime, Long endTime, List<String> organizationIds );

    @Query(" select t  from OnDutySummaryEntity t  , OrganizationEntity o  " +
            " where t.valid=1 and o.valid = 1 and t.organizationId  = o.id " +
            " and t.onDutyTime>=?1 and t.onDutyTime<?2 and   o.searchPath like ?3  and o.organizationNatureCode like  ?4   " +
            " order by  coalesce ( ( SELECT b.organizationOrderNum FROM OrganizationEntity b WHERE b.valid = 1 AND b.id = o.organizationParentId AND b.organizationNatureCode LIKE'%05%%' ), o.organizationOrderNum ) ASC, " +
            "        o.organizationOrderNum ASC, " +
            " t.onDutyTime desc , o.organizationTree asc , t.showNumber asc   "
            )
    List<OnDutySummaryEntity> findSquadronOrganizationOnDuty(Long startTime, Long endTime, String searchPath , String  natureLike);

}





