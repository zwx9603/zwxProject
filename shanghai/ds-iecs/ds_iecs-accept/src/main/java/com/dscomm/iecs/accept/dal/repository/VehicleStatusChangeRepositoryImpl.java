package com.dscomm.iecs.accept.dal.repository;

import org.apache.logging.log4j.util.Strings;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/5/1 13:55
 * @Describe
 */

@Repository
public class VehicleStatusChangeRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;


    /**
     * 列表
     *
     * @param states
     * @param brigadeOrganizationId
     * @param organizationId
     * @param vehicleState
     * @param whetherPage
     * @param page
     * @param size
     * @return
     */
    @Transactional(readOnly = true)
    public List<Object[]> findVehicleChangeList(List<String> states, String brigadeOrganizationId,
                                                String organizationId, String vehicleState, Boolean whetherPage, int page, int size) {


        String headSql = " select org.organizationParentId,wl.organizationId,wl.vehicleName,wl.vehicleNumber,bd" +
                ".vehicleStatusCode,bd.changeTime,bd.seatNumber from VehicleStatusChangeEntity bd," +
                "EquipmentVehicleEntity wl ,OrganizationEntity org where org.valid=1 and wl.valid=1 and bd.valid=1 " +
                "and bd.vehicleId=wl.id and org.id=bd.organizationId and bd.vehicleStatusCode in(:ZTS)";
        String conditionSql = "";

        if (Strings.isNotBlank(brigadeOrganizationId)) {
            conditionSql = conditionSql + " and org.organizationParentId =:SJJGID";
        }
        if (Strings.isNotBlank(organizationId)) {
            conditionSql = conditionSql + " and bd.organizationId =:JGID";
        }
        if (Strings.isNotBlank(vehicleState)) {
            conditionSql = conditionSql + " and bd.vehicleStatusCode =:ZT";
        }


        String ordersql = " order by bd.changeTime desc  ";
        String sql = headSql + conditionSql + ordersql;
        Query query = entityManager.createQuery(sql);

        query.setParameter("ZTS", states);
        if (Strings.isNotBlank(brigadeOrganizationId)) {
            query.setParameter("SJJGID", brigadeOrganizationId);
        }
        if (Strings.isNotBlank(organizationId)) {
            query.setParameter("JGID", organizationId);
        }
        if (Strings.isNotBlank(vehicleState)) {
            query.setParameter("ZT", vehicleState);
        }

        if (whetherPage) {
            query.setFirstResult((page - 1) * size);
            query.setMaxResults(size);
        }
        return query.getResultList();
    }


    @Transactional(readOnly = true)
    public Integer findVehicleChangeTotal(List<String> states, String brigadeOrganizationId, String organizationId,
                                          String vehicleState) {


        String headSql = " select count(bd.id) as NUM from VehicleStatusChangeEntity bd," +
                "EquipmentVehicleEntity wl ,OrganizationEntity org where org.valid=1 and wl.valid=1 and bd.valid=1 " +
                "and bd.vehicleId=wl.id and org.id=bd.organizationId and bd.vehicleStatusCode in(:ZTS)";
        String conditionSql = "";

        if (Strings.isNotBlank(brigadeOrganizationId)) {
            conditionSql = conditionSql + " and org.organizationParentId =:SJJGID";
        }
        if (Strings.isNotBlank(organizationId)) {
            conditionSql = conditionSql + " and bd.organizationId =:JGID";
        }
        if (Strings.isNotBlank(vehicleState)) {
            conditionSql = conditionSql + " and bd.vehicleStatusCode =:ZT";
        }


        String sql = headSql + conditionSql;
        Query query = entityManager.createQuery(sql);

        query.setParameter("ZTS", states);
        if (Strings.isNotBlank(brigadeOrganizationId)) {
            query.setParameter("SJJGID", brigadeOrganizationId);
        }
        if (Strings.isNotBlank(organizationId)) {
            query.setParameter("JGID", organizationId);
        }
        if (Strings.isNotBlank(vehicleState)) {
            query.setParameter("ZT", vehicleState);
        }

        List<Map<String, Long>> lists = query.unwrap(org.hibernate.Query.class)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
        if (lists != null && !lists.isEmpty()) {
            Long num = lists.get(0).get("NUM");
            return Integer.parseInt(num.toString());
        }
        return 0;
    }
}
