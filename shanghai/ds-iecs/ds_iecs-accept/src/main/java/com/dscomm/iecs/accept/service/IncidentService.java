package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.inputbean.*;
import com.dscomm.iecs.accept.graphql.typebean.*;
import com.dscomm.iecs.base.graphql.typebean.PaginationBean;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;

import java.util.List;


/**
 * 描述：案件（警情信息） 服务
 */
public interface IncidentService {

    /**
     * 全部警情状态为暂存警情信息
     *
     * @return 警情（案件）列表
     */
    PaginationBean<IncidentBrieflyBean> findIncidentTemp( IncidentQueryInputInfo queryBean );


    /**
     * 根据接收对象id、警情地址模糊匹配、警情类型、警情等级查询未结案警情信息
     *
     * @param queryBean 警情（案件）参数
     * @return 警情（案件）列表
     */
    PaginationBean<IncidentBrieflyBean> findIncidentsReceiverObjectIdUnfinished(IncidentQueryInputInfo queryBean);


    /**
     * 根据辖区ID集合、警情地址模糊匹配、警情类型、警情等级查询 未结案警情
     *
     * @param queryBean 警情（案件）参数
     * @return 警情（案件）列表
     */
    PaginationBean<IncidentBrieflyBean> findIncidentsUnfinished(IncidentQueryInputInfo queryBean);

    /**
     * 根据辖区ID集合、警情地址模糊匹配、警情类型、警情等级查询 过滤警情ids 未结案警情
     *
     * @param queryBean 警情（案件）参数
     * @return 警情（案件）列表
     */
    PaginationBean<IncidentBrieflyBean> findIncidentsUnfinishedFilterIncident (IncidentQueryInputInfo queryBean);


    /**
     * 根据辖区ID集合、警情地址模糊匹配、警情类型、警情等级、警情状态、警情时间段 查询警情(分页)
     *
     * @param queryBean 警情参数
     * @return 警情（案件）查询结果
     */
    PaginationBean<IncidentBrieflyBean> findIncidentsCondition(IncidentQueryInputInfo queryBean);


    /**
     * 根据辖区ID集合、警情地址模糊匹配、警情类型、警情等级、警情状态、警情时间段 查询关注警情(分页)
     *
     * @param queryBean 警情参数
     * @return 警情（案件）查询结果
     */
    PaginationBean<IncidentBrieflyBean> findIncidentAttentionCondition(IncidentQueryInputInfo queryBean);


    /**
     * 根据辖区ID集合、警情地址模糊匹配、警情类型、警情等级、警情状态、警情时间段 查询重要警情(分页)
     *
     * @param queryBean 警情参数
     * @return 警情（案件）查询结果
     */
    PaginationBean<IncidentBrieflyBean> findIncidentImportantCondition(IncidentQueryInputInfo queryBean);


    /**
     * 设置警情简要信息属性
     *
     * @param incidentBeanList 警情信息列表
     * @return 警情信息列表
     */
    List<IncidentBrieflyBean> setIncidentBrieflyProperties( List<IncidentBrieflyBean> incidentBeanList );




    /**
     * 根据警情id 获得警情详情
     *
     * @param incidentId 警情id参数
     * @return 警情保存结果
     */
    IncidentBean findIncident(String incidentId ,Boolean whetherProperties);

    /**
     * 设置警情详细信息属性
     *
     * @param incidentBeanList 警情信息列表
     * @return 警情信息列表
     */
    List<IncidentBean> setIncidentProperties( List<IncidentBean> incidentBeanList );






    /**
     * 立案(保存警情）
     *
     * @param queryBean 警情保存参数
     * @param  distribute   是否坐席分配
     * @param  acceptAndDispatch 是否接处合一
     * @return 警情保存结果
     */
    IncidentBean saveIncident(IncidentSaveInputInfo queryBean , UserInfo userInfo, Boolean distribute ,Boolean acceptAndDispatch );


    /**
     * 保存警情与调度
     *
     * @param queryBean 保存处警参数
     * @return 处警记录
     */
    HandleIncidentBean saveIncidentHandle(IncidentHandleSaveInputInfo queryBean , UserInfo userInfo );


    /**
     * 修改警情信息
     *
     * @param queryBean 警情修改参数
     * @return 警情保存结果
     */
    IncidentBean updateIncident(IncidentSaveInputInfo queryBean);



    /**
     * 案件合并
     *
     * @param inputInfo 案件合并参数
     * @return 合并结果
     */
    Boolean saveIncidentMerge(IncidentMergeSaveInputInfo inputInfo);





    /**
     * 警情拆分 ( 拆分过程 记录火场文书 )
     *
     * @param inputInfo 案件拆分参数
     * @return 拆分结果
     */
    Boolean saveIncidentSplit(IncidentSplitSaveInputInfo inputInfo);


    /**
     * 根据主案件id 获得 主案件信息  合并案件信息
     *
     * @param incidentId 主案件信息
     * @return 拆分结果
     */
    IncidentMergeBean findIncidentMerge(String incidentId  );

    /**
     * 警情删除
     *
     * @param incidentId 案件id
     * @return 删除结果
     */
    Boolean removeIncident(String incidentId);

    /**
     * 警情状态更新
     *
     * @param incidentId 案件id
     * @return 更新结果
     */
    Boolean updateIncidentStatus(String incidentId, String statusCode ,
                                 String handleId   );

    /**
     * 虚实警转换
     *
     * @param incidentId 案件id
     * @param incidentNature 案件性质 1 实警；0 虚警
     * @return 转换结果
     */
    Boolean updateIncidentNature(String incidentId, String incidentNature);


    /**
     * 回写结案反馈信息到警情
     *
     * @param accidentBean 结案反馈
     */
    void writeBackAccidentToIncident(AccidentBean accidentBean);


    /**
     * 获取经纬度周边范围内历史案件
     * @param radius 范围半径 米
     * @return 警情（案件）列表
     */
    List<IncidentBean> findScopeIncident( String longitude , String latitude , String radius );


    /**
     * 修改警情经纬度 地址
     *
     * @param queryBean 警情修改参数
     * @return 警情保存结果
     */
    IncidentBean updateIncidentAddress(IncidentSaveInputInfo queryBean);

    /**
     * 根据案件id获取 参与单位id （ 主管中队以及全部上级单位 预警参与单位 指令参与单位 ）
     *
     * @param incidentId 警情id参数
     * @return 案件扩展信息
     */
    List<String > findIncidentParticipantOrganizationId (String incidentId);

    /**
     * 根据辖区ID集合、警情地址模糊匹配、警情类型、警情等级查询 未结案警情
     * 新添加部分获取的信息，移动端专用
     * @param queryBean 警情（案件）参数
     * @return 警情（案件）列表
     */
    PaginationBean<IncidentBean> findZJIncidents(IncidentQueryInputInfo queryBean);


}
