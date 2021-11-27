package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.GradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<GradeEntity,String> {

    /*
    * 根据参数案件类型、案件等级 、处置对象查询
    * @author Zhao Wenxue
    * */
    @Query("select t from GradeEntity t where t.czdx =?1 and t.ajdj = ?2 and t.ajlx = ?3")
    GradeEntity queryInfo(String czdx,String ajdj,String ajlx);

}
