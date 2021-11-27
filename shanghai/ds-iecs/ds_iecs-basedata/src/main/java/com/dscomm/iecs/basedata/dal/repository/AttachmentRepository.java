package com.dscomm.iecs.basedata.dal.repository;


import com.dscomm.iecs.basedata.dal.po.AttachmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 附件信息 底层服务
 */
@Repository
public interface AttachmentRepository extends JpaRepository<AttachmentEntity, String> {


    /**
     * 根据警情id 获得
     *
     * @param incidentId  警情id
     * @return 返回结果
     */
    @Query(" select t from AttachmentEntity t   where  t.valid = 1   and t.incidentId = ?1 order by t.createdTime desc ")
    List<AttachmentEntity> findAttachmentByIncidentId(String incidentId);

    /**
     * 根据 关联对象id 获得
     * @param relationId  关联对象id
     * @return 返回结果
     */
    @Query(" select t from AttachmentEntity t   where  t.valid = 1   and t.relationId = ?1   order by t.createdTime desc  ")
    List<AttachmentEntity> findAttachmentByRelationId(   String relationId );


    /**
     * 根据关联对象ids 获得
     *
     * @param relationIds  关联对象ids
     * @return 返回结果
     */
    @Query(" select t from AttachmentEntity t   where  t.valid = 1   and t.relationId in ( ?1 )  order by t.createdTime desc ")
    List<AttachmentEntity> findAttachmentByRelationIds( List<String> relationIds  );


    /**
     * 根据关联对象id 获得
     *
     * @param relationIds  关联对象id
     * @param relationObject  关联对象
     * @return 返回结果
     */
    @Query(" select t from AttachmentEntity t   where  t.valid = 1   and t.relationId in ( ?1 ) and t.relationObject = ?2  order by t.createdTime desc ")
    List<AttachmentEntity> findAttachmentByRelationIds( List<String> relationIds , String relationObject);




}
