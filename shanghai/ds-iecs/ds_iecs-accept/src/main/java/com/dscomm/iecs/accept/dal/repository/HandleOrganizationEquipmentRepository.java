package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.HandleOrganizationEquipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:调派单位装备信息 数据库持久层服务
 */
@Repository
public interface HandleOrganizationEquipmentRepository extends JpaRepository<HandleOrganizationEquipmentEntity, String> {

    /**
     * 根据案件ID获取 调派单位装备信息
     *
     * @param incidentId 案件ID
     * @return 调派单位装备信息 列表
     */
    @Query(" select handleOrganizationEquipment , handle  from HandleOrganizationEquipmentEntity handleOrganizationEquipment " +
            " left join HandleEntity handle on handle.id = handleOrganizationEquipment.handleId  and handle.valid = 1   " +
            " where  handleOrganizationEquipment.valid = 1 and handleOrganizationEquipment.incidentId = ?1 " +
            " order by   handle.handleBatch  asc ,  handle.handleStartTime asc   ")
    List<Object []> findHandleOrganizationEquipmentByIncidentId(String incidentId);

    /**
     * 根据案件ID获取 调派单位装备信息
     *
     * @param incidentId 案件ID
     * @return 调派单位装备信息 列表
     */
    @Query(" select handleOrganizationEquipment , handle  from HandleOrganizationEquipmentEntity handleOrganizationEquipment " +
            " left join HandleEntity handle on handle.id = handleOrganizationEquipment.handleId  and handle.valid = 1   " +
            " where  handleOrganizationEquipment.valid = 1 and handleOrganizationEquipment.incidentId = ?1 and handleOrganizationEquipment.organizationId = ?2 " +
            " order by   handle.handleBatch  asc ,  handle.handleStartTime asc   ")
    List<Object []> findHandleOrganizationEquipmentByIncidentId(String incidentId ,   String organizationId);



    /**
     * 根据处警记录ID获取 调派单位装备信息
     *
     * @param handleId 处警记录ID
     * @return 调派单位装备信息 列表
     */
    @Query(" select t from HandleOrganizationEquipmentEntity t  where  t.valid = 1 and t.handleId = ?1 ")
    List<HandleOrganizationEquipmentEntity> findHandleOrganizationEquipmentByHandleId(String handleId);
}
