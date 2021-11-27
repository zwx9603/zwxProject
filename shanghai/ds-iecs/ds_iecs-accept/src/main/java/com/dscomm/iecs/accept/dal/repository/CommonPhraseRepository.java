package com.dscomm.iecs.accept.dal.repository;


import com.dscomm.iecs.accept.dal.po.CommonPhraseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommonPhraseRepository extends JpaRepository<CommonPhraseEntity, String> {


    /**
     * 总队版 根据警情等级查询常用短语提示模板
     * @param phraseType 常用短语类型
     * @param IncidentType 警情等级
     * @return 返回查询结果
     */
    @Query("SELECT  a FROM CommonPhraseEntity a  WHERE a.phraseType=?1 AND a.incidentType=?2 AND  a.valid=1")
    public List<CommonPhraseEntity> findCommonPhrase(String phraseType, String IncidentType);

    /**
     * 总队版 查询常用短语提示模板
     * @param phraseType 常用短语类型
     * @return 返回查询结果
     */
    @Query("SELECT  a FROM CommonPhraseEntity a  WHERE a.phraseType=?1  AND  a.valid=1")
    public List<CommonPhraseEntity> findCommonPhrase(String phraseType);

}
