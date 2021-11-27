package com.dscomm.iecs.basedata.dal.repository;


import com.dscomm.iecs.basedata.dal.po.LeaderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LeaderRepository extends JpaRepository<LeaderEntity,String> {

	/**
     * 领导查询
     * @param type 领导类型
     * @return
     */
	@Query("select t from LeaderEntity t where t.valid=1 and t.type=?1")
	List<LeaderEntity> getLeaderEntitiesByType(String type);

	/**
	 * 领导列表
	 * @return
	 */
	@Query("select t from LeaderEntity t where t.valid=1 ")
	List<LeaderEntity> getLeaderEntities();
}
