package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.fireinputbean.CommanderListSaveInputInfo;
import com.dscomm.iecs.accept.graphql.fireinputbean.CommanderSaveInputInfo;
import com.dscomm.iecs.accept.graphql.firetypebean.CommanderBean;

import java.util.List;

/**
 *  现场指挥员服务 service
 */
public interface CommanderService  {

    /**
     *  保存指挥员 信息
     * @param queryBean
     * @return
     */
    CommanderBean saveCommander (CommanderSaveInputInfo queryBean);

    /**
     *  保存指挥员 信息
     * @param queryBean
     * @return
     */
    Boolean  saveCommanderList (CommanderListSaveInputInfo queryBean);

    /**
     * 移除 指挥员信息
     * @param id
     * @return
     */
    Boolean removeCommander ( String id );


    /**
     *  根据案件id 获得 指挥员信息
     * @param incidentId
     * @return
     */
    List<CommanderBean>  findCommander(String incidentId);

}
