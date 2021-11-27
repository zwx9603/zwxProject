package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.TransferIncidentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 描述:转警记录 数据库持久层服务
 */
@Repository
public interface TransferIncidentRepository extends JpaRepository<TransferIncidentEntity, String> {

    /**
     * 根据 警情id(案件id)获得 转警记录
     *
     * @param incidentId 警情id(案件id)
     * @return 电话报警记录 列表
     */
    @Query(" select t from TransferIncidentEntity t  where  t.valid = 1 and t.incidentId = ?1 and t.receiveTime is  null order by  t.transferTime desc   ")
    TransferIncidentEntity  findTransferIncidentByIncidentId(String incidentId);


}
