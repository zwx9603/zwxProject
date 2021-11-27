package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.LocaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:现场信息 数据库持久层服务
 */
@Repository
public interface LocaleRepository extends JpaRepository<LocaleEntity, String> {


    /**
     * 根据 警情id(案件id)获得 现场信息
     *
     * @param incidentId 警情id(案件id)
     * @return  返回结果
     */
    @Query(" select t from LocaleEntity t  where  t.valid = 1 and t.whetherFileFeedback = 0 and t.incidentId = ?1 order by  t.collectionTime desc    ")
    List<LocaleEntity> findLocaleByIncidentId(String incidentId);




    /**
     * 根据警情id  指挥id  指令单id 指令记录id 反馈对象id  关键字 反馈方式集合 查询处警指令信息
     */
    List<LocaleEntity> findLocaleCondition ( String incidentId , String commandId ,String instructId  ,
                                             String instructRecordId  ,  List<Integer> localeType ,
                                             List<Integer> localeSource  , String  feedbackObjectId ,String keyword       ) ;




}
