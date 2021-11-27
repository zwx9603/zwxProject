package com.dscomm.iecs.basedata.dal.repository;

import com.dscomm.iecs.basedata.dal.po.DictionaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 描述： 字典的数据底层服务
 *
 */
public interface DictionaryRepository extends JpaRepository<DictionaryEntity, String>{


	@Query(" select max (t.updatedTime) from DictionaryEntity t     ")
	Long findDataLatestTime( Long lastTime ) ;


	@Query(" select t from DictionaryEntity t  where    t.valid = 1 and t.typeCode = ?1  order by  t.orderNum asc  " )
	List<DictionaryEntity> findDictionaryByType(String type);


}
