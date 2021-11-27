package com.dscomm.iecs.accept.dal.repository;


import com.dscomm.iecs.accept.dal.po.AcceptanceEntity;
import com.dscomm.iecs.accept.dal.po.SmartRecommendationRecordEntity;
import com.dscomm.iecs.accept.graphql.inputbean.FindSmartRecommendationInfo;
import com.dscomm.iecs.accept.graphql.typebean.FindSmartRecommendationBean;
import com.dscomm.iecs.base.graphql.typebean.PaginationBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:智能辅助调用记录
 */
@Repository
public interface SmartRecommendationRecordRepository extends JpaRepository<SmartRecommendationRecordEntity, String> {


    PaginationBean<FindSmartRecommendationBean> findSmartRecommendationRecordCondition(FindSmartRecommendationInfo info);

}
