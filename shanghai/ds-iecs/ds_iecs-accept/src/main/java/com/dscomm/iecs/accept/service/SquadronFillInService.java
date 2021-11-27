package com.dscomm.iecs.accept.service;


import com.dscomm.iecs.accept.graphql.inputbean.SquadronFillInSaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.SquadronFillInBean;

import java.util.List;

/**
 * 描述：中队填报 服务
 */
public interface SquadronFillInService {

    /**
     * 根据案件id 机构id 获得中队填报信息
     * @param incidentId 案件id
     * @return 值班信息列表
     */
    List<SquadronFillInBean> findSquadronFillIn (String incidentId  , String organizationId );


    /**
     * 保存 中队填报信息
     * @param   inputInfo 保存信息
     * @return 值班信息列表
     */
    SquadronFillInBean  saveSquadronFillIn( SquadronFillInSaveInputInfo inputInfo );


}
