package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.AssistanceEntity;
import com.dscomm.iecs.accept.dal.po.IncidentStatesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidentStatesRepository extends JpaRepository<IncidentStatesEntity,String> {
    /*
    * 查询案件的状态
    * */
    @Query("select t from IncidentStatesEntity t")
    List<IncidentStatesEntity> queryInfo();

    /*
     * 根据案件id查询案件的局部状态
     * */
    @Query("select t from IncidentStatesEntity t where  t.ajid = ?1")
    List<IncidentStatesEntity> queryInfoByInfo(String ajid);

    /*
    *根据案件id和案件状态代码判断，状态带吗是否存在
    * */
    /*@Modifying
    @Query("update IncidentStatesEntity  t set t.flg = '1' where t.ztbm1 = ?1 and t.ztbm2 = ?2 and t.hzlx = ?3")
    void updateInfo(String ajid,String ajztdm);*/
}
