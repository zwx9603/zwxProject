package com.dscomm.iecs.accept.dal.repository;


import com.dscomm.iecs.accept.dal.po.StandardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StandardRepository extends JpaRepository<StandardEntity, String> {
    List<StandardEntity> findStandard(String keyword, String incidentBigType, String incidentLevel, String incidentType, String placeType, String tipsContent, String tipsKeyword, String tipsType,String standardType);
}
