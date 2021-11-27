package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.inputbean.CombatInformationInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.CombatInformationBean;
import com.dscomm.iecs.base.graphql.inputbean.PaginationInputInfo;
import com.dscomm.iecs.base.graphql.typebean.PaginationBean;

public interface CombatInformationService {

    /**
     * 保存作战信息卡
     * @param inputInfo
     * @return 作战信息卡信息
     * */
    CombatInformationBean saveCombatInformation(CombatInformationInputInfo inputInfo);

    /**
     * 删除作战信息卡
     * @param id 作战信息卡id
     *
     * */
    Boolean deleteCombatInformation(String id);

    /**
     * 作战信息卡列表
     * @param organizationId 所属机构id
     * @return 作战信息卡信息分页列表
     * */
    PaginationBean<CombatInformationBean> listCombatInformation(String organizationId, Boolean whetherPage, PaginationInputInfo paginationInputInfo);

    /**
     * 作战信息卡详情
     * @param id
     * @return 作战信息卡信息
     * */
    CombatInformationBean detailCombatInformation (String id);

}
