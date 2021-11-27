package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.inputbean.EarlyWarningListSaveInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.EarlyWarningQueryInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.EarlyWarningBean;

import java.util.List;

/**
 * 描述：预警信息 服务
 */
public interface EarlyWarningService {

    /**
     * 保存预警信息
     *
     * @param inputInfo 预警参数
     * @return 保存结果
     */
    List<EarlyWarningBean> saveEarlyWarning(EarlyWarningListSaveInputInfo inputInfo);


    /**
     * 根据预警id 变更预警状态为 已接收
     *
     * @param earlyWarningIds 预警参数
     * @return 保存结果
     */
    Boolean changeEarlyWarningStatus ( List<String> earlyWarningIds );

    /**
     * 根据预警条件 查询预警信息
     *
     * @param inputInfo 案件id
     * @return 返回结果
     */
    List<EarlyWarningBean> findEarlyWarning(EarlyWarningQueryInputInfo inputInfo ) ;

    /**
     * 根据案件id 预警类型 取消预警信息
     *
     * @param incidentId 案件id
     * @param organizationIds 案件id
     * @return 撤销结果
     */
    Boolean removeEarlyWarning(String incidentId  , String earlyWarningType , List<String>   organizationIds   ) ;



    /**
     * 根据案件编号获取接警时已经下达过预警机构id
     *
     * @param incidentId 案件编号
     * @return 预警中队列表
     */
    List<String> findEarlyWarningOrganizationIds (String incidentId);

    /**
     * 根据案件id 查询预警信息
     *
     * @param incidentId 案件id
     * @return 返回结果
     */
    List<EarlyWarningBean> findEarlyWarningByIncidentId(String incidentId ) ;


    /**
     * 根据案件id 机构id查询预警信息
     *
     * @param incidentId 案件id
     * @param organizationId 机构id
     * @return 返回结果
     */
    List<EarlyWarningBean>   findEarlyWarningByIncidentIdAndOrganizationId (String incidentId , String organizationId ) ;


    /**
     * 更新预警信息 tts播放路径
     */
    void updateEarlyWarningSpeakFile();

}
