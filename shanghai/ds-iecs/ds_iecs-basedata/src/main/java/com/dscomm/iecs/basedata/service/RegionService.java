package com.dscomm.iecs.basedata.service;


import com.dscomm.iecs.basedata.graphql.typebean.RegionBean;

import java.util.List;
import java.util.Map;

/**
 * 描述：范围服务
 *
 */
public interface RegionService {


	/** 强制更新 范围缓存
	 * @return 返回结果
	 */
	void forceUpdateCacheRegion();


	/**返回  范围
	 * @return 返回查询结果
	 */
	List<RegionBean>  findRegion( List<String> ids );

	/**返回  范围
	 * @return 返回查询结果
	 */
	Map<String,RegionBean>  findRegionMap( List<String> ids );


}
