package com.dscomm.iecs.garage.dal.repository;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class StatisticsRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<Object[]> findStatisticsVehicleStatus(String scopeSquadronId, List<String>  vehicleStatusCodes, Boolean whetherOnlySquadron)  {

        String leftsql = " select clztdy.ZTBM as code , clztdy.ZTMC as name  from " +
                "  JCJ_CLZTDY clztdy WITH ( NOLOCK ) where  clztdy.ZTBM in ( :CLZT )" ;

        String headsql = " select vehicle.CLZTDM_MH as code , count(1) as num    FROM  WL_CLXX vehicle WITH ( NOLOCK ) " +
                "  where 1=1 and vehicle.JLZT=1 and vehicle.ZBLXDM like '21%'  ";

        String conditionsql = "";
        if (Strings.isNotBlank(scopeSquadronId) && whetherOnlySquadron != null) {
            if (whetherOnlySquadron) {
                conditionsql = conditionsql + " and  vehicle.SSXFJGID = :GJGCXM ";
            } else {
                conditionsql = conditionsql + " and  vehicle.SSXFJGID in (   select t.ID   from JGXX_XFJG t  where (t.JGXZDM like '09%'  ) and t.JGTREE like " +
                        " :GJGCXM ) ";
            }
        }

        String endsql = "  group by  vehicle.CLZTDM_MH     ";
        String ordersql = "  ";

        String rightsql = headsql + conditionsql + endsql + ordersql;

        String sql =  " select lft.code , lft.name , rft.num  from (  " +  leftsql  + " ) as lft  left join    ( "  +   rightsql   + " ) as rft " +
                " on  lft.code = rft.code "   ;
        Query query = entityManager.createNativeQuery(sql);

        if (vehicleStatusCodes != null && vehicleStatusCodes.size() > 0) {
            query.setParameter("CLZT", vehicleStatusCodes);
        }
        if (Strings.isNotBlank(scopeSquadronId) && whetherOnlySquadron != null) {
            if (whetherOnlySquadron) {
                query.setParameter("GJGCXM", scopeSquadronId);
            } else {
                query.setParameter("GJGCXM", scopeSquadronId + "%");
            }
        }
        List<Object[]> list = query.getResultList();

        return list;
    }



}
