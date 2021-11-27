package com.dscomm.iecs.basedata.dal.repository;

import com.dscomm.iecs.basedata.dal.po.VehicleGarageMappingEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class VehicleGarageMappingRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<VehicleGarageMappingEntity> findVehicleGarageMappingCondition(List<String> organizationIds, List<String> mappingTypes,
                                                                              List<String> mappingObjectIds, List<String> mappingGroupIds) {

        String headsql = "  select vehicleGarageMapping  from VehicleGarageMappingEntity vehicleGarageMapping  where vehicleGarageMapping.valid = 1  ";
        String conditionsql = "";

        if (organizationIds != null && organizationIds.size() > 0) {
            conditionsql = conditionsql + " and vehicleGarageMapping.organizationId in (:JGID)    ";
        }
        if (mappingTypes != null && mappingTypes.size() > 0) {
            conditionsql = conditionsql + " and vehicleGarageMapping.mappingType in (:GXLX)    ";
        }
        if (mappingObjectIds != null && mappingObjectIds.size() > 0) {
            conditionsql = conditionsql + " and vehicleGarageMapping.mappingObjectId in (:CLBH)    ";
        }
        if (mappingGroupIds != null && mappingGroupIds.size() > 0) {
            conditionsql = conditionsql + " and vehicleGarageMapping.mappingGroupId in (:CKBH)    ";
        }

        String endsql = "  ";
        String ordersql = " order by vehicleGarageMapping.updatedTime desc  ";
        String sql = headsql + conditionsql + endsql + ordersql;
        Query query = entityManager.createQuery(sql);

        if (organizationIds != null && organizationIds.size() > 0) {
            query.setParameter("JGID", organizationIds);
        }
        if (mappingTypes != null && mappingTypes.size() > 0) {
            query.setParameter("GXLX", mappingTypes);
        }
        if (mappingObjectIds != null && mappingObjectIds.size() > 0) {
            query.setParameter("CLBH", mappingObjectIds);
        }
        if (mappingGroupIds != null && mappingGroupIds.size() > 0) {
            query.setParameter("CKBH", mappingGroupIds);
        }

        return query.getResultList();
    }

}
