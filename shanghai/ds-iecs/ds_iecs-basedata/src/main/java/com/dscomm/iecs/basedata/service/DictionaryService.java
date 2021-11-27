package com.dscomm.iecs.basedata.service;



import com.dscomm.iecs.basedata.graphql.typebean.DictionaryBean;
import com.dscomm.iecs.basedata.graphql.typebean.DictionaryKeyBean;

import java.util.List;
import java.util.Map;

/**
 * 描述：字典服务
 *
 */
public interface DictionaryService {


	/** 强制更新全部字典缓存
	 * @return 返回结果
	 */
	void forceUpdateCacheDictionary( );


	/**根据字典类型查询字典
	 * @param type 参数 字典类型
	 * @param whetherSearch 参数 是否从数据获得最新数据
	 * @return 返回结果
	 */

	List<DictionaryBean> findGridDictionary(String type , Boolean whetherSearch );

	/**返回树状字典实体
	 * @param type 参数，字典类型
	 * @param whetherSearch 参数 是否从数据获得最新数据
	 * @return 返回查询结果
	 */
	List<DictionaryBean>  findTreeDictionary(String type , Boolean whetherSearch );



	/**返回 前端 全部字典
	 * @return 返回查询结果
	 */
	List<DictionaryKeyBean>  findAllDictionary(  );


	/**返回 前端 全部字典
	 * @return 返回查询结果
	 */
	List<DictionaryKeyBean>  findDictionaryType( List<String> dicsType    );

	/**
	 * 返回 根据type获得字典 code name Map集合
	 * @return 返回字典集合
	 */
	Map<String, Map<String, String>>   findDictionaryMap( List<String> dicsType  );

	Map<String, String> findDictionary(String type);

}
