package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.AttentionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Description:警情关注服务
 *
 */
@Repository
public interface AttentionRepository extends JpaRepository<AttentionEntity, String> {

    /**
     * 根据事件id查 全量关注
     *
     * @param incidentId 事件id
     * @return 警情关注po
     */
    @Query("select t from AttentionEntity t where t.incidentId =:incidentId  and t.type = :typeCode  and t.attentionType = 2")
    AttentionEntity getByIncidentId(@Param("incidentId") String incidentId , @Param("typeCode") Integer type );

    /**
     * 根据事件id和关注人查个人关注
     *
     * @param incidentId        事件id
     * @param attentionPersonId 关注人账号
     * @return 警情关注po
     */
    @Query("select t from AttentionEntity t where t.incidentId =:incidentId and t.attentionPersonId = :attentionPersonId  and t.type = :typeCode   and t.attentionType = 1")
    AttentionEntity getByIncidentIdAndAttentionPersonId(@Param("incidentId") String incidentId, @Param("attentionPersonId") String attentionPersonId , @Param("typeCode") Integer type );

    /**
     * 根据事件id 是否存在重要警情 关注信息
     *
     * @param incidentId        事件id
     * @return 警情关注po
     */
    @Query("select t from AttentionEntity t where t.incidentId =:incidentId  and t.type = 2   ")
    List<AttentionEntity> getImportantByIncidentId(@Param("incidentId") String incidentId );

//
//
//
//    /**
//     * 取消关注 普通关注删除数据库记录
//     *
//     * @param incidentId        事件单编号
//     * @param attentionPersonId 关注人账号
//     * @param type              类型
//     * @return 返回删除结果
//     */
//    @Modifying
//    @Query("delete from AttentionEntity t where t.incidentId=?1 and t.attentionPersonId=?2 and t.type=?3")
//    Integer deleteByType1(String incidentId, String attentionPersonId, Integer type);
//
//    /**
//     * 取消关注 班长关注删除数据库记录
//     *
//     * @param incidentId 事件单编号
//     * @param type       类型
//     * @return 返回删除结果
//     */
//    @Modifying
//    @Query("delete from AttentionEntity t where t.incidentId=?1 and t.type=?2")
//    Integer deleteByType2(String incidentId, Integer type);
//
//    /**
//     * 根据事件单id列表查询警情关注列表
//     *
//     * @param incidentIdList 事件单id列表
//     * @return 警情关注列表
//     */
//    @Query("select a from AttentionEntity a where a.incidentId in ?1")
//    List<AttentionEntity> findAttentionEntityListByIncidentIdList(List<String> incidentIdList);
//
//    /**
//     * 根据事件单id和关注人工号列表查询警情关注列表
//     *
//     * @param incidentId 事件单编号
//     * @param attentionPersonId 关注人工号
//     * @return 警情关注列表
//     */
//    @Query("select a from AttentionEntity a where a.incidentId=?1 and a.attentionPersonId=?2")
//    List<AttentionEntity> findAttentionEntityListByIncidentIdAndAttentionPersonIdList(String incidentId, String attentionPersonId);
//
//    /**
//     * 根据事件单id和关注类型列表查询自动关注
//     *
//     * @param incidentId 事件单编号
//     * @param type 关注类型
//     * @return 警情关注
//     */
//    @Query("select a from AttentionEntity a where a.incidentId=?1 and a.type=?2 and a.attentionWay=2")
//    AttentionEntity findAutoAttentionEntityByIncidentIdAndAttentionType(String incidentId, Integer type);
//
//    /**
//     * 根据事件单id和关注人工号列表查询手动关注
//     *
//     * @param incidentId 事件单编号
//     * @param attentionPersonId 关注人工号
//     * @return 警情关注
//     */
//    @Query("select a from AttentionEntity a where a.incidentId=?1 and a.attentionPersonId=?2 and a.attentionWay=1")
//    AttentionEntity findManualAttentionEntityByIncidentIdAndAttentionPersonId(String incidentId, String attentionPersonId);
//
//    /**
//     * 根据事件单id和类型查询警情关注列表
//     *
//     * @param incidentId 事件单id
//     * @param type 类型
//     * @return 警情关注列表
//     */
//    @Query("select a from AttentionEntity a where a.incidentId=?1 and a.type=?2")
//    List<AttentionEntity> findAttentionEntityListByIncidentIdAndType(String incidentId, Integer type);
//
//    /**
//     * 根据事件单id查询接警员警情关注列表
//     *
//     * @param incidentId 事件单id
//     * @return 警情关注列表
//     */
//    @Query("select a from AttentionEntity a where a.incidentId=?1 and a.type in ('1','2')")
//    List<AttentionEntity> findCalltakingAttentionEntityListByIncidentId(String incidentId);
//
//    /**
//     * 根据事件单id查询接警班长警情关注列表
//     *
//     * @param incidentId 事件单id
//     * @return 警情关注列表
//     */
//    @Query("select a from AttentionEntity a where a.incidentId=?1 and a.type in ('1','3')")
//    List<AttentionEntity> findCalltakingSupervisiorAttentionEntityListByIncidentId(String incidentId);
//
//    /**
//     * 根据事件单id查询处警员警情关注列表
//     *
//     * @param incidentId 事件单id
//     * @return 警情关注列表
//     */
//    @Query("select a from AttentionEntity a where a.incidentId=?1 and a.type in ('1','4')")
//    List<AttentionEntity> findDispatcherAttentionEntityListByIncidentId(String incidentId);
//
//    /**
//     * 根据事件单id查询处警班长警情关注列表
//     *
//     * @param incidentId 事件单id
//     * @return 警情关注列表
//     */
//    @Query("select a from AttentionEntity a where a.incidentId=?1 and a.type in ('1','5')")
//    List<AttentionEntity> findDispatcherSupervisiorAttentionEntityListByIncidentId(String incidentId);
}
