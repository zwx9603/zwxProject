package com.dscomm.iecs.out.dal.repository;

import com.dscomm.iecs.accept.dal.po.HandleOrganizationEntity;
import com.dscomm.iecs.accept.dal.po.ParticipantFeedbackEntity;
import com.dscomm.iecs.out.dal.po.QueryAuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GetDispatchListByRepository extends JpaRepository<HandleOrganizationEntity, Integer> {

    @Query(" select handleOrganization,handle  from HandleOrganizationEntity handleOrganization " +
            " left join HandleEntity handle on handle.id = handleOrganization.handleId  and handle.valid = 1  " +
            " where handleOrganization.valid = 1   and handleOrganization.incidentId = ?1  and handleOrganization.handleId = ?2 " +
            " order by   handle.handleBatch  asc ,  handle.handleStartTime asc " )
    List<Object[]> findbyincidentId(String incidentId, String handleId);
    @Query(" select distinct  t.organizationId  from HandleOrganizationEntity t  where  t.valid = 1 and t.incidentId = ?1  and t.handleId = ?2 order by t.organizationId desc  ")
    List<String> findOrganizationIdByIncidentId(String incidentId, String handleId);
    @Query(" select handleOrganizationVehicle , handle  from HandleOrganizationVehicleEntity handleOrganizationVehicle " +
            " left join HandleEntity handle on handle.id = handleOrganizationVehicle.handleId  and handle.valid = 1   " +
            " where  handleOrganizationVehicle.valid = 1 and handleOrganizationVehicle.incidentId = ?1 and handleOrganizationVehicle.handleId = ?2 " +
            " order by   handle.handleBatch  asc ,  handle.handleStartTime asc  , " +
            "  handleOrganizationVehicle.organizationId asc ,   handleOrganizationVehicle.vehicleTypeCode asc   ")
    List<Object[]> findHandleOrganizationVehicleByIncidentId(String incidentId, String handleId);
    @Query(" select distinct  t.vehicleId   from HandleOrganizationVehicleEntity t  where  t.valid = 1 and t.incidentId = ?1  and t.handleId = ?2  order by  t.vehicleId desc  ")
    List<String> findVehicleIdByIncidentId(String incidentId, String handleId);
    @Query(" select handleOrganizationVehicle , handle  from HandleOrganizationVehicleEntity handleOrganizationVehicle " +
            " left join HandleEntity handle on handle.id = handleOrganizationVehicle.handleId  and handle.valid = 1   " +
            " where  handleOrganizationVehicle.valid = 1 and handleOrganizationVehicle.incidentId = ?1  and handleOrganizationVehicle.organizationId = ?2 and handleOrganizationVehicle.handleId = ?3 " +
            " order by    handle.handleBatch  asc ,  handle.handleStartTime asc  , " +
            "  handleOrganizationVehicle.organizationId asc ,   handleOrganizationVehicle.vehicleTypeCode asc  ")
    List<Object[]> findHandleOrganizationVehicleByIncidentIdAndOrganizationId(String incidentId, String organizationId, String handleId);

    @Query(" select distinct  t.vehicleId   from HandleOrganizationVehicleEntity t  where  t.valid = 1 and t.incidentId = ?1  and t.organizationId = ?2 and t.handleId = ?3 order by  t.vehicleId desc  ")
    List<String> findVehicleIdByIncidentIdAndOrganizationId(String incidentId, String organizationId, String handleId);

    @Query(" select t from ParticipantFeedbackEntity t  where  t.valid = 1 and t.incidentId = ?1 and t.handleId = ?2  order by  t.checkTime desc , t.feedbackCheckTime desc    ")
    List<ParticipantFeedbackEntity> findParticipantFeedbackByIncidentId(String incidentId, String handleId);
    @Query(" select t from ParticipantFeedbackEntity t  where  t.valid = 1 and t.incidentId = ?1 " +
            " and  t.vehicleId in ( ?2 ) and t.handleId = ?3  " +
            "order by  t.checkTime desc , t.feedbackCheckTime desc    ")
    List<ParticipantFeedbackEntity> findParticipantFeedbackByIncidentId( String incidentId   , List<String> vehicleIds, String handleId );


}
