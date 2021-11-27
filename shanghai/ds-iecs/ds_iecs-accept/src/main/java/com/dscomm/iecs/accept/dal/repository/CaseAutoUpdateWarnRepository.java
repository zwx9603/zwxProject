package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.CaseAutoUpdateWarnEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaseAutoUpdateWarnRepository extends JpaRepository<CaseAutoUpdateWarnEntity, String> {


    @Query(" select t from CaseAutoUpdateWarnEntity t  where    t.valid = 1 " +
            "  order by t.caseType asc , t.warnType asc , t.incidentLevelCode asc , t.updatedTime desc   " )
    List<CaseAutoUpdateWarnEntity> findAllBy();


    List<CaseAutoUpdateWarnEntity> findAllByCondition( String caseType , String warnType , String incidentLevelCode , String keyword , String czdxlx , String zhcslx  );


    @Query(" select t from CaseAutoUpdateWarnEntity t  where    t.valid = 1  and t.id =  ?1   " )
    List<CaseAutoUpdateWarnEntity>  getById(String id);


    @Query(" select t from CaseAutoUpdateWarnEntity t " +
            " where    t.valid = 1  and t.caseType =  ?1  and t.warnType = ?2 " +
            "  order by t.caseType asc , t.warnType asc , t.incidentLevelCode asc , t.updatedTime desc   " )
    List<CaseAutoUpdateWarnEntity>  findCaseAutoUpdateWarnByCaseType(String caseType  , String  warnType  );


    @Query(" select t from CaseAutoUpdateWarnEntity t  " +
            " where    t.valid = 1  and t.caseType =  ?1  and t.warnType = ?2  and t.id <> ?3 " +
            "  order by t.caseType asc , t.warnType asc , t.incidentLevelCode asc , t.updatedTime desc   " )
    List<CaseAutoUpdateWarnEntity>  findCaseAutoUpdateWarnByCaseTypeNoId(String caseType  , String  warnType , String caseAutoUpdateWarnId  );


    @Query(" select t from CaseAutoUpdateWarnEntity t  " +
            " where    t.valid = 1  and t.caseType =  ?1  and t.warnType = ?2  and t.incidentLevelCode <> ?3 " +
            "  order by t.caseType asc , t.warnType asc , t.incidentLevelCode asc , t.updatedTime desc   " )
    List<CaseAutoUpdateWarnEntity>  findCaseAutoUpdateWarnByCaseTypeNoLevel(String caseType  , String  warnType , String levelCode  );

    @Query(" select t from CaseAutoUpdateWarnEntity t  " +
            " where    t.valid = 1  and t.caseType =  ?1  and t.warnType = ?2  and t.incidentLevelCode = ?3 " +
            "  order by t.caseType asc , t.warnType asc , t.incidentLevelCode asc , t.updatedTime desc   " )
    CaseAutoUpdateWarnEntity   findCaseAutoUpdateWarnByCaseTypeLevel(String caseType  , String  warnType , String levelCode  );

    @Query(" select t from CaseAutoUpdateWarnEntity t  " +
            " where    t.valid = 1  and t.caseType =  ?1  and t.warnType = ?2  and t.disposalObjectCode = ?3 " +
            "  order by t.caseType asc , t.warnType asc , t.incidentLevelCode asc , t.updatedTime desc   " )
    CaseAutoUpdateWarnEntity   findCaseAutoUpdateWarnByCaseTypeDisposalObject(String caseType  , String  warnType , String disposalObjectCode  );

}
