package com.dscomm.iecs.accept.dal.repository;


import com.dscomm.iecs.accept.dal.po.QueryUnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QueryUnitRepository extends JpaRepository<QueryUnitEntity,String> {
	/**
     * 单位查询
     * @param type
     * @return
     */
	List<Object[]> queryUnitList(String type);
}
