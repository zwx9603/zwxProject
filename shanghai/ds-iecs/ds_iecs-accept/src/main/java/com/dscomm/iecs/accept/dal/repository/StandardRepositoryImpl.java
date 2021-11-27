package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.StandardEntity;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class StandardRepositoryImpl {
    @PersistenceContext
    private EntityManager entityManager;

    public List<StandardEntity> findStandard(String keyword, String incidentBigType,
                                      String incidentLevel, String incidentType,
                                      String placeType, String tipsContent,
                                      String tipsKeyword, String tipsType,String standardType){
        String headsql = "  select standard  from StandardEntity standard  where standard.valid = 1  ";
        String conditionsql = "";

        if (Strings.isNotBlank(incidentBigType)) {
            conditionsql = conditionsql + " and standard.incidentBigType like :AJDL    ";
        }
        if (Strings.isNotBlank(incidentLevel)) {
            conditionsql = conditionsql + " and standard.incidentLevel like :AJDJ    ";
        }
        if (Strings.isNotBlank(incidentType)) {
            conditionsql = conditionsql + " and standard.incidentType like :AJLX    ";
        }
        if (Strings.isNotBlank(placeType)) {
            conditionsql = conditionsql + " and standard.placeType like :CDLX    ";
        }
        if (Strings.isNotBlank(tipsContent)) {
            conditionsql = conditionsql + " and standard.tipsContent like :TSNR    ";
        }
        if (Strings.isNotBlank(tipsKeyword)) {
            conditionsql = conditionsql + " and standard.tipsKeyword like :TSGJZ    ";
        }
        if (Strings.isNotBlank(tipsType)) {
            conditionsql = conditionsql + " and standard.tipsType like :TSLX    ";
        }
        if (Strings.isNotBlank(standardType)) {
            conditionsql = conditionsql + " and standard.standardType like :GFLX    ";
        }
        String endsql = "  ";
        //String ordersql = " order by organization.organizationOrderNum asc  ";
        String ordersql = " ";
        String sql = headsql + conditionsql + endsql + ordersql;
        Query query = entityManager.createQuery(sql);
        if (Strings.isNotBlank(incidentBigType)) {
            query.setParameter("AJDL", "%" + incidentBigType + "%");
        }
        if (Strings.isNotBlank(incidentLevel)) {
            query.setParameter("AJDJ", "%" + incidentLevel + "%");
        }
        if (Strings.isNotBlank(incidentType)) {
            query.setParameter("AJLX", "%" + incidentType + "%");
        }
        if (Strings.isNotBlank(placeType)) {
            query.setParameter("CDLX", "%" + placeType + "%");
        }
        if (Strings.isNotBlank(tipsContent)) {
            query.setParameter("TSNR", "%" + tipsContent + "%");
        }
        if (Strings.isNotBlank(tipsKeyword)) {
            query.setParameter("TSGJZ", "%" + tipsKeyword + "%");
            conditionsql = conditionsql + " and standard.tipsKeyword like :TSGJZ    ";
        }
        if (Strings.isNotBlank(tipsType)) {
            query.setParameter("TSLX", "%" + tipsType + "%");
        }
        if (Strings.isNotBlank(standardType)) {
            query.setParameter("GFLX", "%" + standardType + "%");
        }
        return query.getResultList();

    }
}
