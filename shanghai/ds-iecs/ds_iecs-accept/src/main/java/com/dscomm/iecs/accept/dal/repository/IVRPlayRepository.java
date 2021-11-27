package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.IVRPlayRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 描述:播放录音记录（表名不能改） 数据库持久层服务
 */
@Repository
public interface IVRPlayRepository extends JpaRepository<IVRPlayRecordEntity, String> {


}
