package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.HierarchicalDispatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 描述:等级调派 数据库持久层服务
 */
@Repository
public interface HierarchicalDispatchRepository extends JpaRepository<HierarchicalDispatchEntity, String> {

    /**
     * 根据条件查询等级调派
     *
     * @param incidentTypeCode   案件类型
     * @param incidentLevelCode  案件等级
     * @param disposalObjectCode 处置对象
     * @return 等级调派列表
     */
    List<HierarchicalDispatchEntity> findHierarchicalDispatchCondition( String organizationId ,  String incidentTypeCode, String incidentLevelCode, String disposalObjectCode  ,
             Boolean whetherPage,  int page, int size );


    /**
     * 根据条件查询等级调派数量
     *
     * @param incidentTypeCode   案件类型
     * @param incidentLevelCode  案件等级
     * @param disposalObjectCode 处置对象
     * @return 等级调派列表
     */
    Integer findHierarchicalDispatchTotal(  String organizationId , String incidentTypeCode, String incidentLevelCode, String disposalObjectCode  );



    /**
     * 判断是否存在警情
     * @return 处警记录（调派记录）列表
     */
    @Query(" select count( t.id ) from HierarchicalDispatchEntity t   where  t.valid = 1  " +
            " and  t.id <> ?1 and t.organizationId =   ?2 and  t.incidentTypeCode = ?2  " +
            "and  t.incidentLevelCode = ?3 and t.disposalObjectCode = ?4  ")
    Integer whetherHierarchicalDispatchTotal( String  hierarchicalDispatchId , String organizationId ,
                               String incidentTypeCode, String incidentLevelCode, String disposalObjectCode );


}
