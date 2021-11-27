package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.basedata.dal.po.ExpertEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: zs
 * @Date: 15:48 2020/7/23
 * desc: 专家服务
 */
public interface ExpertRepository extends JpaRepository<ExpertEntity,String> {

    /**
     * 全局查询 专家列表
     * @param keyword  expertField专家领域 expertType专家类型 主要分内部专家 社会专家两大类 expertName专家姓名
     * @return
     */
    List<ExpertEntity> findExpertList(String keyword);


    /**
     * 智能检索
     * @param expertField 专家领域
     * @param expertType 专家类型 主要分内部专家 社会专家两大类
     * @param expertName 专家姓名
     * @return
     */
    List<ExpertEntity> findSmartExpertList(String expertField, String expertType, String expertName);
}
