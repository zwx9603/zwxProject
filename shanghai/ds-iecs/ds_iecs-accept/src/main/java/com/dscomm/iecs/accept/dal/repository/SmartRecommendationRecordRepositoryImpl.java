package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.SmartRecommendationRecordEntity;
import com.dscomm.iecs.accept.graphql.inputbean.FindSmartRecommendationInfo;
import com.dscomm.iecs.accept.graphql.typebean.FindSmartRecommendationBean;
import com.dscomm.iecs.base.graphql.typebean.PaginationBean;
import org.apache.logging.log4j.util.Strings;
import org.mx.StringUtils;
import org.mx.dal.Pagination;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class SmartRecommendationRecordRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 动态查询智能辅助记录
     * @param info
     * @return
     */
    @Transactional(readOnly = true)
    public PaginationBean<FindSmartRecommendationBean> findSmartRecommendationRecordCondition(FindSmartRecommendationInfo info){

        String headSql = " select t from SmartRecommendationRecordEntity t where t.valid=1 and t.state=1";
        String headSql2 = " select count(1) from SmartRecommendationRecordEntity t where t.valid=1 and t.state=1";

        String conditionalSql = " ";
        if (info.getStartTime() != null) {
            conditionalSql = conditionalSql + " and t.callTime >=:DYSJ0 ";
        }
        if (info.getEndTime() != null) {
            conditionalSql = conditionalSql + " and t.callTime <:DYSJ1";
        }

        if (!StringUtils.isBlank(info.getCallManualNumber())) {
            conditionalSql = conditionalSql + " and t.callingNumber =:DYRGH";
        }
        if (!StringUtils.isBlank(info.getCallUnitId())) {
            conditionalSql = conditionalSql + " and t.callUnitId =:DYDWID";
        }
        if (!StringUtils.isBlank(info.getIncidentId())) {
            conditionalSql = conditionalSql + " and t.incidentId =:AJID";
        }
        if (!StringUtils.isBlank(info.getType())) {
            conditionalSql = conditionalSql + " and t.type =:LX";
        }
        String endSsl = " order by callTime desc";
        String sql = headSql + conditionalSql+endSsl;
        String sql2 = headSql2 + conditionalSql;
        Query query = entityManager.createQuery(sql);
        Query query2 = entityManager.createQuery(sql2);

        if (info.getStartTime() != null) {
            query.setParameter("DYSJ0", info.getStartTime());
            query2.setParameter("DYSJ0", info.getStartTime());
        }
        if (info.getEndTime() != null) {
            query.setParameter("DYSJ1", info.getEndTime());
            query2.setParameter("DYSJ1", info.getEndTime());
        }

        if (!StringUtils.isBlank(info.getCallManualNumber())) {
            query.setParameter("DYRGH",  info.getCallManualNumber());
            query2.setParameter("DYRGH",  info.getCallManualNumber());
        }

        if (!StringUtils.isBlank(info.getCallUnitId())) {
            query.setParameter("DYDWID",  info.getCallUnitId());
            query2.setParameter("DYDWID",  info.getCallUnitId());
        }

        if (!StringUtils.isBlank(info.getIncidentId())) {
            query.setParameter("AJID",  info.getIncidentId());
            query2.setParameter("AJID",  info.getIncidentId());
        }

        if (!StringUtils.isBlank(info.getType())) {
            query.setParameter("LX",  info.getType());
            query2.setParameter("LX",  info.getType());
        }
        //分页 默认就要分页
        List<FindSmartRecommendationBean> beanList = new ArrayList<>();
        int size = info.getPagination().getSize();
        int page = info.getPagination().getPage();
        query.setFirstResult((page-1)*size);
        query.setMaxResults(size);

        List<SmartRecommendationRecordEntity> lists = query.getResultList();
        int total = Integer.valueOf(String.valueOf(query2.getResultList().get(0)));

        for(SmartRecommendationRecordEntity obj:lists){
            FindSmartRecommendationBean bean = transform(obj);
            if(bean!=null){
                beanList.add(bean);

            }
        }
        PaginationBean pageBean = new PaginationBean();
        pageBean.setList(beanList);
        pageBean.setPagination(new Pagination(total,page,size));

        return pageBean;
    }

    private FindSmartRecommendationBean transform(SmartRecommendationRecordEntity entity){

        if(entity==null){
            return null;
        }
        FindSmartRecommendationBean bean = new FindSmartRecommendationBean();

        bean.setId(entity.getId());
        bean.setIncidentId(entity.getIncidentId());
        bean.setRequestData(entity.getRequestData());
        bean.setResult(entity.getResult());
        bean.setType(entity.getType());
        bean.setCallManualNumber(entity.getCallManualNumber());
        bean.setCallManualName(entity.getCallManualName());
        bean.setCallUnitId(entity.getCallUnitId());
        bean.setCallUnitName(entity.getCallUnitName());
        bean.setCallTime(entity.getCallTime());
        bean.setSjc(entity.getSjc());
        bean.setCreatedTime(entity.getCreatedTime());
        bean.setUpdatedTime(entity.getUpdatedTime());
        bean.setValid(entity.getValid());
        bean.setOperator(entity.getOperator());
        bean.setActualResults(entity.getActualResults());
        bean.setCauseOfDifference(entity.getCauseOfDifference());
        bean.setWasteTime(entity.getWasteTime());
        bean.setState(entity.getState());

        return bean;
    }

}
