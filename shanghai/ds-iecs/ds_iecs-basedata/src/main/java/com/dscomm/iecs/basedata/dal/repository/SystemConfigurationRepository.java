package com.dscomm.iecs.basedata.dal.repository;

import com.dscomm.iecs.basedata.dal.po.SystemConfigurationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述 系统配置 数据底层服务
 */
@Repository
public interface SystemConfigurationRepository extends JpaRepository<SystemConfigurationEntity, String> {


    @Query(" select max (t.updatedTime) from SystemConfigurationEntity t      ")
    Long findDataLatestTime(  Long lastTime ) ;

    @Query(" select t  from SystemConfigurationEntity t  where  t.updatedTime >?1 and t.updatedTime <=?2  ")
    List<SystemConfigurationEntity>   findDataLatestTime( Long lastTime , Long  latestTime ) ;

    @Query("select t from SystemConfigurationEntity t where t.configType=?1 and t.valid = 1 ")
    SystemConfigurationEntity getSystemConfigByConfigType(String configType);


}
