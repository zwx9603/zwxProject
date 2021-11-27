package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.ReleaseCallEntity;
import com.dscomm.iecs.accept.graphql.inputbean.ReleaseCallQueryInputInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/5/10 14:19
 * @Describe
 */
@Repository
public interface ReleaseCallRepository extends JpaRepository<ReleaseCallEntity, String> {

    /**
     * 查询排队早释列表
     * @param inputInfo
     * @return
     */
    //机构及下级机构
    List<ReleaseCallEntity> findReleaseCallEntitiesByIds(ReleaseCallQueryInputInfo inputInfo,List<String> orgIds);

    //全部
    List<ReleaseCallEntity> findReleaseCallEntities(ReleaseCallQueryInputInfo inputInfo);

    /**
     * 根据时间和报警电话查询早释记录
     * @param startTime 开始时间
     * @param callNumber 报警电话
     * @return 返回查询结果
     */
    @Query("select t from ReleaseCallEntity t where t.releasedTime>=?1 and t.valid=1 and t.callNumber=?2")
    List<ReleaseCallEntity> findReleaseCallEntitiesByTimeAndPhone(Long startTime,String callNumber);
    /**
     * 根据报警电话查询排队记录
     * @param callNumber 报警电话
     * @return 返回查询结果
     */
    @Query("select t from ReleaseCallEntity t where  t.valid=1 and t.callNumber=?1 and t.releasedTime is null")
    List<ReleaseCallEntity> findQueueCallEntitiesByPhone(String callNumber);

    /**
     * 查询排队记录
     * @param startTime 排队开始时间
     * @return 返回查询结果
     */
    @Query("select t from ReleaseCallEntity t where  t.valid=1 and t.queuedTime>=?1 and t.releasedTime is null")
    List<ReleaseCallEntity> findQueueCallEntitiesByTime(Long startTime);
}
