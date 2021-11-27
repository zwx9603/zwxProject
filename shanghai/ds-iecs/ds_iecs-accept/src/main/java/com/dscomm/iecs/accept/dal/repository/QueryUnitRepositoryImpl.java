package com.dscomm.iecs.accept.dal.repository;
/**
 * 单位查询
 * @param type
 * @return
 */


import com.dscomm.iecs.accept.dal.po.QueryUnitEntity;
import org.mx.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class QueryUnitRepositoryImpl {

	@PersistenceContext
	private EntityManager entityManager;
	public List<QueryUnitEntity> queryUnitList(String type) {
		String headsql="select t.id,t.unitName,t.unitAddr,t.contacts,t.mobilePhone,t.unitType,t.context,t.supportability,t.longitude,t.latitude from QueryUnitEntity t where 1=1";
		String conditionsql="";
		if (!StringUtils.isBlank(type)) {
			conditionsql=conditionsql+ " and t.unitName like:UNITNAME ";
		}
		if (!StringUtils.isBlank(type)) {
			conditionsql=conditionsql+ " or t.context like:CONTEXT ";
		}
		if (!StringUtils.isBlank(type)) {
			conditionsql=conditionsql+ " or t.unitType like:DWLX ";
		}
		String sql=headsql+conditionsql+" order by t.unitName desc ";
		Query query=entityManager.createQuery(sql);
		if (!StringUtils.isBlank(type)) {
			query.setParameter("UNITNAME", "%"+type+"%");
		}
		if (!StringUtils.isBlank(type)) {
			query.setParameter("CONTEXT", "%"+type+"%");
		}
		if (!StringUtils.isBlank(type)) {
			query.setParameter("DWLX", "%"+type+"%");
		}
		List<QueryUnitEntity> list=query.getResultList();
		return list;
		
	}
}
