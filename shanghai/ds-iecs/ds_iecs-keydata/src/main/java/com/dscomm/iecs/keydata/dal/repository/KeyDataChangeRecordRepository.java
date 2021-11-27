package com.dscomm.iecs.keydata.dal.repository;

import com.dscomm.iecs.keydata.dal.po.KeyDataChangeRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 描述:系统操作日志 数据库持久层服务
 *
 */
@Repository
public interface KeyDataChangeRecordRepository extends JpaRepository<KeyDataChangeRecordEntity, String> {

}
