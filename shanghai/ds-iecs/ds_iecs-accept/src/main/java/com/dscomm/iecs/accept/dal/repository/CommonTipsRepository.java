package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.CommonTipsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:接警处警提示信息 数据库持久层服务
 */
@Repository
public interface CommonTipsRepository extends JpaRepository<CommonTipsEntity, String> {


    /**
     * 根据案件类型 查询默认值
     * @param type  类型
     * @return 接警/处警提示信息列表
     */
    @Query(" select t from CommonTipsEntity t   where  t.valid = 1   and t.type = ?1 and t.defaultValue = 1 order by t.updatedTime desc  ")
    List<CommonTipsEntity> findCommonTipsType (  String type  );

    /**
     * 根据案件类型 案件处置对象代码 获取接警处警提示信息
     * @param type  类型
     * @param code  代码值
     * @return 接警/处警提示信息列表
     */
    @Query(" select t from CommonTipsEntity t   where  t.valid = 1   and t.type = ?1  and t.code = ?2  order by t.updatedTime desc  ")
    List<CommonTipsEntity> findCommonTipsTypeCode (String type , String code );


    @Query(" select t from CommonTipsEntity t   where  t.valid = 1   and t.id in (?1)    order by t.updatedTime desc  ")
    List<CommonTipsEntity> findByIds(List<String> ids);

    @Query(" select t from CommonTipsEntity t   where  t.valid = 1   and t.id = ?1  order by t.updatedTime desc  ")
    List<CommonTipsEntity> findByIdValid(String id);

    @Query(" select t from CommonTipsEntity t   where  t.valid = 1     order by t.updatedTime desc  ")
    List<CommonTipsEntity> findAllValid();
}
