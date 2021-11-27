package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.TagLabelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:标签 数据库持久层服务
 */
@Repository
public interface TagLabelRepository extends JpaRepository<TagLabelEntity, String> {



    List<TagLabelEntity> findTagLabelCondition(String keyword , String phoneKeyword , String personNameKeyword , List<String> tagType , String businessTable , Long  startTime  ,  Long  endTime , Boolean whetherPage,
                                                 int page, int size );


    Integer findTagLabelConditionTotal ( String keyword  , String phoneKeyword , String personNameKeyword , List<String> tagType ,  String businessTable , Long  startTime  ,  Long  endTime  );

    /**
     * 根据单据ID(变更数据id)查询标签列表
     *
     * @param businessDataId 单据ID(变更数据id)
     * @return 标签列表
     */
    @Query(" select t from TagLabelEntity t   where  t.valid = 1   and t.businessDataId = ?1  and  t.businessTable = ?2  order by  t.updatedTime desc    " )
    List<TagLabelEntity> findTagLabelsBusinessDataId (String businessDataId , String businessTable );

}
