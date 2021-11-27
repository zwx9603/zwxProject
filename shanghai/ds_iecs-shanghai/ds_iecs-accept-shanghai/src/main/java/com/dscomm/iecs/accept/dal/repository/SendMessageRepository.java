package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.SendMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: zhao wenxue
 * desc: 发送短信配置列表进行查询、添加、删除、修改
 */
@Repository
public interface SendMessageRepository extends JpaRepository<SendMessageEntity,String> {

    /*
    *做全查询操作
    * */
    @Query("select t from SendMessageEntity t ")
    List<SendMessageEntity> queryAll();

    /**
     * 根据被发送人姓名或机构名称做条件查询
     * @param ldmc
     * @param ssjgbh
     * @return
     */
    List<SendMessageEntity> queryByInfo(String ldmc,String ssjgbh);

    /**
     * 根据人员id做批量删除
     * @param
     * @param
     * @return
     */
    @Query("delete  from SendMessageEntity t where t.ryid in (?1) ")
    Boolean deleteByInfo(List<String> list);

    /**
     * 根据人员id和人员所属机构批量修改手机号、是否发送信息
     * @param dhhm
     * @param ryid
     * @param ssjgbh
     * @return
     */
    @Modifying(clearAutomatically = true)
    @Query("update SendMessageEntity t set t.dhhm = ?1 where t.ryid = ?2 and t.ssjgbh = ?3")
    void updateInfo(String dhhm,String ryid,String ssjgbh);
}
