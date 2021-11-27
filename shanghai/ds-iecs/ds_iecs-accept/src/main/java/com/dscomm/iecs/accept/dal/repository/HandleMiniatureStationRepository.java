package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.HandleMiniatureStationEntity;
import com.dscomm.iecs.accept.dal.po.HandleMiniatureStationFeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:受理单（报警记录） 数据库持久层服务
 */
@Repository
public interface HandleMiniatureStationRepository extends JpaRepository<HandleMiniatureStationEntity, String> {

    /**
     * 根据 警情id(案件id)获得 微站调派信息
     *
     * @param incidentId 警情id(案件id)
     * @return 微站调派信息
     */
    @Query(" select handleMiniature ,handle  from HandleMiniatureStationEntity handleMiniature " +
            " left join HandleEntity handle on handle.id = handleMiniature.handleId  and handle.valid = 1 " +
            "where  handleMiniature.valid = 1 and handleMiniature.incidentId = ?1 order by  handleMiniature.handleTime asc   ")
    List<Object[]> findHandleMiniatureStation (String incidentId);

    /**
     * 根据 警情id(案件id)获得 微站调派信息
     *
     * @param incidentId 警情id(案件id)
     * @return 微站调派信息
     */
    @Query(" select handleMiniature ,handle  from HandleMiniatureStationEntity handleMiniature " +
            " left join HandleEntity handle on handle.id = handleMiniature.handleId  and handle.valid = 1 " +
            " where  handleMiniature.valid = 1 and handleMiniature.incidentId = ?1 and handleMiniature.handleId = ?2    order by  handleMiniature.handleTime asc   ")
    List<Object[]> findHandleMiniatureStation (String incidentId , String handleId );


    /**
     * 根据 警情id(案件id)获得 微站调派信息
     *
     * @param incidentId 警情id(案件id)
     * @return 微站调派信息
     */
    @Query("  select handleMiniature ,handle  from HandleMiniatureStationEntity handleMiniature " +
            " left join HandleEntity handle on handle.id = handleMiniature.handleId  and handle.valid = 1 " +
            " where  handleMiniature.valid = 1 and handleMiniature.incidentId = ?1 and handleMiniature.handleId in ( ?2 ) " +
            " order by  handleMiniature.handleTime asc   ")
    List<Object[]> findHandleMiniatureStation (String incidentId , List<String> handleIds );



    /**
     * 根据 警情id(案件id)获得 微站调派反馈信息
     *
     * @param incidentId 警情id(案件id)
     * @return 微站调派信息
     */
    @Query(" select t  from HandleMiniatureStationFeedbackEntity t " +
            "where  t.valid = 1 and t.incidentId = ?1 and t.handleMiniatureStationId = ?2  order by  t.feedbackTime desc   ")
    List<HandleMiniatureStationFeedbackEntity> findHandleMiniatureStationFeedback ( String incidentId , String handleMiniatureStationId );


    /**
     * 根据处警记录ID 获取 微站调派信息
     *
     * @param handleId 处警记录ID
     * @return 调派单位装备信息 列表
     */
    @Query(" select t from HandleMiniatureStationEntity t  where  t.valid = 1 and t.handleId = ?1    order by t.updatedTime desc  ")
    List<HandleMiniatureStationEntity> findHandleMiniatureStationByHandleId  (String handleId);

}
