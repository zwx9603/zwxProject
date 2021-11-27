package com.dscomm.iecs.basedata.dal.repository;


import com.dscomm.iecs.basedata.dal.po.DangerousChemicalsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author: zhangsheng
 * @Date: 2020/4/5 14:50
 * @describe: 危化品服务持久层
 */
public interface DangerousChemicalsRepository extends JpaRepository<DangerousChemicalsEntity,String> {

    /**
     * 根据名称获取危化品详情
     * @param keyword 关键字  名称、类别、禁忌
     * @return
     */
    @Query("select t from DangerousChemicalsEntity t where t.valid = 1 and ( t.whpZwmc like ?1 or  t.whpBzlx like ?1 or  t.whpJjw like ?1 ) ")
    List<DangerousChemicalsEntity> getChemicalsElasticList(String keyword);

    /**
     * 根据名称获取危化品详情
     * @return
     */
    @Query("select t from DangerousChemicalsEntity t where t.valid = 1   ")
    List<DangerousChemicalsEntity> getChemicalsElasticList( );
}
