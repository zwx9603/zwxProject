package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.inputbean.CombatUnitInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.CombatUnitQueryInputInfo;
import com.dscomm.iecs.accept.service.bean.CombatUnitBean;
import com.dscomm.iecs.base.graphql.typebean.PaginationBean;

/**
 * 描述:作战单元服务
 * author:YangFuXi
 * Date:2021/6/8 Time:17:04
 **/
public interface CombatUnitService {
    /**
     * 保存或修改、删除作战单元
     * @param inputInfo 参数
     * @return 返回结果
     */
    CombatUnitBean saveOrUpdateCombatUnit(CombatUnitInputInfo inputInfo);

    /**
     * 获取作战单元列表
     * @param inputInfo 条件参数
     * @return 返回结果
     */
    PaginationBean<CombatUnitBean> findCombatUnitList(CombatUnitQueryInputInfo inputInfo);

}
