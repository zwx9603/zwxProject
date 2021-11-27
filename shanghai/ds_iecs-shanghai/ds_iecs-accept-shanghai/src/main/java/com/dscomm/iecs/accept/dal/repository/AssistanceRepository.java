package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.AssistanceEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
* @author Zhao Wenxue
* */
@Repository
public interface AssistanceRepository extends JpaRepository<AssistanceEntity,String> {
    /*
    * 根据id查询数据
    * */
    @Query("select t from AssistanceEntity t where t.id = ?1")
    AssistanceEntity queryInfo (String zgdwdmId);

    /*
    * 通过传来的字段判断添加的信息是否存在
    * */
    @Query("select t from AssistanceEntity t where t.zgdwdm = ?1 and t.pldwdm = ?2 and t.xfjgdm = ?3")
    AssistanceEntity queryBack(String zgdwdm,String pldwdm,String xfjgdm);

    /*
    * 对已存在的信息进行修改
    * */
    @Modifying(clearAutomatically = true)
    @Query("update AssistanceEntity t set t.xcjl = ?1 where t.zgdwdm =?2 and t.pldwdm =?3 and t.xfjgdm =?4")
    void updateInfo(String xcjl,String zgdwdm,String pldwdm,String xfjgdm);

    /*
    * 搜索离主管中队最近的10个中队的距离，并进行升序
    * */
    @Query(value ="select t from AssistanceEntity t  order by t.xcjl asc , limit 10 where t.zgdwdm = ?1",nativeQuery= true)
    List<AssistanceEntity> queryBlXfJl(String zgdwdm);
}
