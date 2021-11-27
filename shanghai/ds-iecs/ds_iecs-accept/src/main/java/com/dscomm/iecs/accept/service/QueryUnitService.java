package com.dscomm.iecs.accept.service;
/**
 * 描述：联勤联动单位查询
 */



import com.dscomm.iecs.accept.graphql.inputbean.OrgUnitInfo;
import com.dscomm.iecs.accept.graphql.typebean.QueryUnitBean;

import java.util.List;

public interface QueryUnitService {

	List<QueryUnitBean> queryUnitList(OrgUnitInfo info);
}
