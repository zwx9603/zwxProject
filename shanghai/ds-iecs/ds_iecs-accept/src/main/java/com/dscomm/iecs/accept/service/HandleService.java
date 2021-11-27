package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.inputbean.HandleSaveInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.IncidentVehicleStatusUpdateInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.*;
import com.dscomm.iecs.basedata.graphql.typebean.OnDutySummaryBean;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;

import java.util.List;

/**
 * 描述：处警记录（调派记录） 服务
 */
public interface HandleService {


    /**
     * 根据调派车辆保存参数  保存处警
     *
     * @param queryBean 保存处警参数
     * @return 处警记录
     */
    HandleBean saveHandle(HandleSaveInputInfo queryBean , UserInfo userInfo );



    /**
     * 处警单位 接收确认接口
     *
     * @handleWay 确认方式 0 系统 1 人员确认
     * @return 处警记录
     */
    HandleBean updateHandleStatus(   String incidentId, String handleId , String organizationId , Integer handleWay  );


    /**
     *  构建单位签收时间
     */
    void   buildHandleOrganizationStatus(    String  handleId ,   String organizationId , Integer handleWay );


    /**
     * 根据警情id(案件id)获取案件 处警信息
     *
     * @param incidentId 警情id(案件id)
     * @param organizationId 机构id
     * @return 处警记录 列表
     */
    List<HandleBean> findHandle(String incidentId , String organizationId);



    /**
     * 根据警情id(案件id)获取案件 处警信息 分组
     *
     * @param incidentId 警情id(案件id)
     * @param organizationId 机构id
     * @return 处警记录 列表
     */
    List<HandleGroupBean> findHandleGroup(String incidentId , String organizationId);


    /**
     * 根据 处警信息id  处警信息
     *
     * @param id 处警信息id

     * @return 处警记录 列表
     */
    HandleBean  findHandleByHandleId ( String id  );


    /**
     * 根据案件id获取 调派信息 （ 调派单位信息 ）
     *
     * @param incidentId 案件id
     * @return 调派信息 列表
     */
    List<HandleOrganizationBean> findHandleOrganization(String incidentId, String organizationId  );


    /**
     * 根据案件id获取 调派单位车辆信息 （ 作战车辆信息 ）
     *
     * @param incidentId 案件id
     * @return 调派单位车辆信息 （ 作战车辆信息 ） 列表
     */
    List<HandleOrganizationVehicleBean> findHandleOrganizationVehicle(String incidentId, String organizationId , Boolean whetherVehicleLoad );

    /**
     * 根据案件id获取 调派单位车辆信息 （ 作战车辆信息 ）
     *
     * @param incidentId 案件id
     * @return 调派单位车辆信息 （ 作战车辆信息 ） 列表
     */
    List<HandleOrganizationVehicleBean> findHandleOrganizationVehicleGroup(String incidentId, String organizationId , Boolean whetherVehicleLoad );


    /**
     * 根据案件id获取 作战车辆id 获得 最新作战车辆信息
     *
     * @param incidentId 案件id
     * @return 调派单位车辆信息 （ 作战车辆信息 ）
     */
    HandleOrganizationVehicleBean  findHandleOrganizationVehicle(String incidentId, String vehicleId  );

    /**
     * 根据出动车辆记录id获取 获得  作战车辆信息
     *
     * @param handleOrganizationVehicleId 出动车辆记录id
     */
    HandleOrganizationVehicleBean  findHandleOrganizationVehicleById( String handleOrganizationVehicleId  );


    /**
     * 根据案件id获取 调派单位装备信息
     *
     * @param incidentId 案件id
     * @return 调派单位装备信息 列表
     */
    List<HandleOrganizationEquipmentBean> findHandleOrganizationEquipment(String incidentId, String organizationId);

    /**
     * 变更处警记录关联的案件id
     *
     * @param sourceIncidentId 原案件id
     * @param targetIncidentId 目标案件id
     */
    void changeHandleIncidentId(String sourceIncidentId, String targetIncidentId);


    /**
     * 根据处警记录id
     * 变更
     * 调派信息、调派单位装备信息、作战车辆信息 车辆状态变更
     * 关联的案件id
     *
     * @param handleId 处警记录id
     * @param targetIncidentId 目标案件id
     */
    void changeHandleOrganizationsIncidentId(String handleId, String targetIncidentId);

    /**
     * 恢复处警记录关联的案件id
     *
     * @param incidentId 案件id
     */
    void recoverHandleIncidentId(String incidentId);


    /**
     * 根据案件id查询 出动单位id
     *
     * @param incidentId 案件id
     * @return 单位 列表
     */
    List<String> findIncidentHandleOrganization(String incidentId);

    /**
     * 根据案件id查询 出动车辆id
     *
     * @param incidentId 案件id
     * @return 单位 列表
     */
    List<String> findIncidentHandleOrganizationVehicleId(String incidentId);


    /**
     * 根据案件id查询 出动车辆ids 正在作战的车辆ids
     *
     * @param incidentId 案件id
     * @return 单位 列表
     */
    List<String> findIncidentFightVehicleIds(String incidentId , List<String> vehicleIds );



    /**
     * 根据案件id 获得值班信息
     *
     * @param incidentId 案件id
     * @return 单位 列表
     */
    List<OnDutySummaryBean> findIncidentOrganizationOnDuty( String incidentId   );



    /**
     * 根据案件id 车辆id 车辆类型 设置作战车辆标识 设置头车（尾车）
     * @param incidentId 案件id
     * @param vehicleId 车辆id
     * @param vehicleIdentification 车辆类型
     * @return
     */
    Boolean saveHandleVehicleIdentification(String incidentId,String vehicleId,String vehicleIdentification);


    /**
     * 更新案件多车辆状态
     *
     * @param  inputInfo 更新状态信息
     * @return 车辆信息 列表
     */
    void  updateIncidentVehicleStatus(IncidentVehicleStatusUpdateInputInfo inputInfo);

    /**
     * 更新案件单车辆状态
     * @return 车辆信息 列表
     */
    void  buildIncidentVehicleStatus( String incidentId ,  String  handleId   ,    String  vehicleId  ,
                                      String newVehicleStatus  , String changeSource   );


    /**
     * 根据 案件id 车辆id  更新作战车辆状态 返回作战车辆id
     *
     * @param incidentId 案件id
     */
    String  updateHandleOrganizationVehicleStatus( String incidentId , String  handleId   ,  String vehicleId , String newVehicleStatus   ) ;


    /**
     * 更新出动单位 tts播放路径
     */
    void updateHandleOrganizationSpeakFile();



}
