package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.CaseAutoUpdateWarnEntity;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CaseAutoUpdateWarnRepositoryImpl   {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional( readOnly =  true )
    public   List<CaseAutoUpdateWarnEntity> findAllByCondition( String caseType, String warnType, String incidentLevelCode, String keyword  , String czdxlx , String zhcslx) {

        String headsql = " select black from CaseAutoUpdateWarnEntity black  where    black.valid = 1    ";
        String conditionsql = "";
        if (Strings.isNotBlank(caseType)) {
            conditionsql = conditionsql + " and    black.caseType = :AJLX    ";
        }
        if (Strings.isNotBlank(warnType)) {
            conditionsql = conditionsql + " and   black.warnType = :TXLX      ";
        }
        if (Strings.isNotBlank(incidentLevelCode)) {
            conditionsql = conditionsql + " and     black.incidentLevelCode = :AJDJ    ";
        }
        if(Strings.isNotBlank(keyword)) {
            conditionsql = conditionsql + " and  (  " +
                    "  black.minimum like :GJZ or  black.maximum like :GJZ " +
                    "  or  black.describe like :GJZ " +
                    "  or black.disposalObjectCode in (   " +
                    "     select keyUnit.id from KeyUnitEntity keyUnit where keyUnit.valid = 1 and  keyUnit.id = black.disposalObjectCode and keyUnit.unitName like  :GJZ  ) " +
                    "  or black.disposalObjectCode in (   " +
                    "    select dic1.code from   DictionaryEntity dic1 where  dic1.valid = 1 and  dic1.typeCode = :CZDXLX and dic1.name like  :GJZ ) " +
                    "  or black.disposalObjectCode in (      " +
                    "  select dic2.code from   DictionaryEntity dic2 where  dic2.valid = 1 and  dic2.typeCode = :ZHCSLX  and dic2.name like  :GJZ   )   " +
                    "  )  " ;
        }

        String endsql = "  ";
        String ordersql = "    order by black.caseType asc , black.warnType asc , black.incidentLevelCode asc , black.updatedTime desc   "  ;
        String sql = headsql + conditionsql + endsql + ordersql;
        Query query = entityManager.createQuery(sql);
        if (Strings.isNotBlank(caseType)) {
            query.setParameter( "AJLX" , caseType );
        }
        if (Strings.isNotBlank(warnType)) {
            query.setParameter( "TXLX" , warnType );
        }
        if (Strings.isNotBlank(incidentLevelCode)) {
            query.setParameter( "AJDJ" , incidentLevelCode );
        }
        if (Strings.isNotBlank(keyword)) {
            query.setParameter("GJZ", "%" + keyword + "%" );
            query.setParameter("CZDXLX", czdxlx );
            query.setParameter("ZHCSLX", zhcslx );
        }


        return query.getResultList();
    }

}
