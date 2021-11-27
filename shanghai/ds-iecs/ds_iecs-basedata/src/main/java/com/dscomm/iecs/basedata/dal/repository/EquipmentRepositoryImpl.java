package com.dscomm.iecs.basedata.dal.repository;

import com.dscomm.iecs.base.enums.EnableEnum;
import com.dscomm.iecs.basedata.dal.po.EquipmentEntity;
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
 * 描述： 装备（消防设备） 数据底层服务
 */
@Repository
public class EquipmentRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<EquipmentEntity> findEquipmentCondition(String searchPath, String keyword,
                                                        List<String> organizationIds, List<String> equipmentStatusCodes,
                                                        List<String> equipmentTypeCodes, Boolean whetherConsumptiveEquipment, Boolean whetherPage,
                                                        int page, int size) {

        String headsql = "  select equipment  from  EquipmentEntity equipment  where   equipment.valid = 1   ";

        String conditionsql = "";

        if (equipmentTypeCodes != null && equipmentTypeCodes.size() > 0) {
            conditionsql = conditionsql + " and equipment.equipmentTypeCode in (:ZBLX )  ";
        }
        if (whetherConsumptiveEquipment != null && whetherConsumptiveEquipment) {
            conditionsql = conditionsql + " and equipment.whetherConsumptiveEquipment = :SFXHZB ";
        }
        if (Strings.isNotBlank(searchPath)) {
            conditionsql = conditionsql + " and equipment.organizationId in (   select t.id   from OrganizationEntity t  where  t.valid = 1 and t.searchPath like :GJGCXM )  ";
        }
        if (Strings.isNotBlank(keyword)) {
            conditionsql = conditionsql + " and equipment.equipmentName  like  :GJZ ";
        }
        if (organizationIds != null && organizationIds.size() > 0) {
            conditionsql = conditionsql + " and equipment.organizationId in (:JG )  ";
        }
        if (equipmentStatusCodes != null && equipmentStatusCodes.size() > 0) {
            conditionsql = conditionsql + " and equipment.equipmentStatusCode in (:ZBZT )  ";
        }

        String endsql = "  ";
        String ordersql = " order by equipment.organizationId desc  ";
        String sql = headsql + conditionsql + endsql + ordersql;
        Query query = entityManager.createQuery(sql);
        if (Strings.isNotBlank(searchPath)) {
            query.setParameter("GJGCXM", searchPath + "%");
        }
        if (Strings.isNotBlank(keyword)) {
            query.setParameter("GJZ", "%" + keyword + "%");
        }
        if (organizationIds != null && organizationIds.size() > 0) {
            query.setParameter("JG", organizationIds);
        }
        if (equipmentStatusCodes != null && equipmentStatusCodes.size() > 0) {
            query.setParameter("ZBZT", equipmentStatusCodes);
        }
        if (equipmentTypeCodes != null && equipmentTypeCodes.size() > 0) {
            query.setParameter("ZBLX", equipmentTypeCodes);
        }
        if (whetherConsumptiveEquipment != null && whetherConsumptiveEquipment) {
            query.setParameter("SFXHZB", String.valueOf(EnableEnum.ENABLE_TRUE.getCode()));
        }
        if (whetherPage) {
            query.setFirstResult((page - 1) * size);
            query.setMaxResults(size);
        }

        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public Integer findEquipmentConditionTotal(String searchPath, List<String> organizationIds, String keyword,
                                               List<String> equipmentStatusCodes, List<String> equipmentTypeCodes, Boolean whetherConsumptiveEquipment) {

        String headsql = "  select count(1) as NUM  from  EquipmentEntity equipment  where   equipment.valid = 1   ";

        String conditionsql = "";

        if (equipmentTypeCodes != null && equipmentTypeCodes.size() > 0) {
            conditionsql = conditionsql + " and equipment.equipmentTypeCode in (:ZBLX )  ";
        }
        if (whetherConsumptiveEquipment != null && whetherConsumptiveEquipment) {
            conditionsql = conditionsql + " and equipment.whetherConsumptiveEquipment = :SFXHZB ";
        }
        if (Strings.isNotBlank(searchPath)) {
            conditionsql = conditionsql + " and equipment.organizationId in (   select t.id   from OrganizationEntity t  where  t.valid = 1 and t.searchPath like :GJGCXM )  ";
        }
        if (Strings.isNotBlank(keyword)) {
            conditionsql = conditionsql + " and equipment.equipmentName  like  :GJZ ";
        }
        if (organizationIds != null && organizationIds.size() > 0) {
            conditionsql = conditionsql + " and equipment.organizationId in (:JG )  ";
        }
        if (equipmentStatusCodes != null && equipmentStatusCodes.size() > 0) {
            conditionsql = conditionsql + " and equipment.equipmentStatusCode in (:ZBZT )  ";
        }
        String endsql = "  ";
        String ordersql = "   ";
        String sql = headsql + conditionsql + endsql + ordersql;
        Query query = entityManager.createQuery(sql);
        if (Strings.isNotBlank(searchPath)) {
            query.setParameter("GJGCXM", searchPath + "%");
        }
        if (Strings.isNotBlank(keyword)) {
            query.setParameter("GJZ", "%" + keyword + "%");
        }
        if (organizationIds != null && organizationIds.size() > 0) {
            query.setParameter("JG", organizationIds);
        }
        if (equipmentStatusCodes != null && equipmentStatusCodes.size() > 0) {
            query.setParameter("ZBZT", equipmentStatusCodes);
        }
        if (equipmentTypeCodes != null && equipmentTypeCodes.size() > 0) {
            query.setParameter("ZBLX", equipmentTypeCodes);
        }
        if (whetherConsumptiveEquipment != null && whetherConsumptiveEquipment) {
            query.setParameter("SFXHZB", String.valueOf(EnableEnum.ENABLE_TRUE.getCode()));
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
