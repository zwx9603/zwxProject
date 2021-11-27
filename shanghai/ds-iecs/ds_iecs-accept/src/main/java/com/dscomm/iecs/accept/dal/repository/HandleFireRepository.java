package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.HandleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 描述:处警记录（调派记录） 数据库持久层服务
 */
@Repository
public interface HandleFireRepository extends JpaRepository<HandleEntity, String> {









}
