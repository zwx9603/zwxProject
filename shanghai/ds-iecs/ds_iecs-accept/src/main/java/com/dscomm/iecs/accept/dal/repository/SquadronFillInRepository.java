package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.SquadronFillInEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:中队填报 数据库持久层服务
 */
@Repository
public interface SquadronFillInRepository extends JpaRepository<SquadronFillInEntity, String> {

    /**
     * 根据 警情id(案件id)获得 中队填报记录
     *
     * @param incidentId 警情id(案件id)
     * @return 受理单（报警记录）列表
     */
    @Query(" select t from SquadronFillInEntity t  where  t.valid = 1 and t.incidentId = ?1 order by  t.writeTime desc   ")
    List<SquadronFillInEntity> findSquadronFillIn (String incidentId);


    /**
     * 根据 警情id(案件id)获得 机构id  中队填报记录
     *
     * @param incidentId 警情id(案件id)
     * @return 受理单（报警记录）列表
     */
    @Query(value = " select t.* from JCJ_ZDTB t  where  t.yxx = 1 " +
            " and t.AJID = ?1 " +
            " and t.TXJGBH in ( select id  from JGXX_XFJG where CXMLJ like ?2 ) " +
            " order by  t.WSSJ desc   " , nativeQuery =  true )
    List<SquadronFillInEntity> findSquadronFillIn (String incidentId  , String organizationId );


}
