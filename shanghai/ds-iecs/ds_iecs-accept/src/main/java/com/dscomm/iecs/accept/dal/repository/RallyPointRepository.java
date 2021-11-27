package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.RallyItemEntity;
import com.dscomm.iecs.accept.dal.po.RallyItemFeedbackEntity;
import com.dscomm.iecs.accept.dal.po.RallyPointEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * [描述信息]:集结点Repository
 */
public interface RallyPointRepository extends Repository<RallyPointEntity, String> {
    /**
     * 描述：查询是否存在相同的集结点
     *
     * @param commandId      指挥ID
     * @param rallyPointName 集结点名称
     * @return 集结点ID
     */
    @Query("select rallyPoint.id from RallyPointEntity as rallyPoint where rallyPoint.commandId=?1 and rallyPoint.rallyPointName=?2 and rallyPoint.valid=1")
    String findRallyPoint(String commandId, String rallyPointName);


    /**
     * 描述: 查询同一个指挥下是否存在同名不同ID的集结点
     *
     * @param id        集结点ID
     * @param name      集结点名称
     * @param commandId 指挥ID
     * @return 集结点ID
     */
    @Query("select rallyPoint.id from RallyPointEntity rallyPoint where rallyPoint.id<>?1 and rallyPoint.rallyPointName=?2 and rallyPoint.commandId=?3 " +
            "and rallyPoint.valid=1")
    String findRallyPoint(String id, String name, String commandId);


    /**
     * 描述：查询所有集结项
     * @param rallyPointId 集结点ID
     */
    @Query("select item from RallyItemEntity as item where item.valid=1 and item.rallyPointId=?1")
    List<RallyItemEntity> findRallyItem (String rallyPointId);


    /**
     *  根据警情id 获取集结点列表
     *
     */
    @Query("select rallyPoint from RallyPointEntity as rallyPoint where rallyPoint.incidentId=?1 and rallyPoint.valid=1")
    List<RallyPointEntity> findRallyPoint (String incidentId);

    /**
     * 描述：查询所有集结项
     */
    @Query("select item from RallyItemEntity as item where item.valid=1 and item.incidentId=?1 and item.rallyPowerId = ?2 ")
    List<RallyItemEntity> findRallyItem (String incidentId ,String rallyPowerId);



    /**
     *  根据条件查询 集结项(集结力量反馈)
     *
     */
    List<RallyItemFeedbackEntity> findRallyItemFeedbackCondition (String incidentId, String commandId, String rallyPointId,
                                                                  String rallyItemId, String feedbackObjectId, String keyword);


}
