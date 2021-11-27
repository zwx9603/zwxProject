package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.EarlyWarningImportantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EarlyWarningImportantRepository extends JpaRepository<EarlyWarningImportantEntity, String> {


   @Query("select t from EarlyWarningImportantEntity t where t.valid=1 and t.effectiveTime>=?1")
   List<EarlyWarningImportantEntity> findEarlyWarningImportant(String currentTime);


   @Query("select t from EarlyWarningImportantEntity t where t.valid=1 and t.id=?1")
   EarlyWarningImportantEntity findEarlyWarningImportantById( String id );
}
