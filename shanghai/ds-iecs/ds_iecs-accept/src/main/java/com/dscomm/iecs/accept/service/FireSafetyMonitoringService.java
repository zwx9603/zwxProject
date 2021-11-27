package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.inputbean.FireSafetySaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.FireSafetyMonitoringBean;

import java.util.List;
import java.util.Map;


/**
 * 描述：出入火场登记 服务
 */
public interface FireSafetyMonitoringService {


    /**
     * 出入火场登记( 保存火场安全监控 )
     * @param queryBean 保存参数
     * @return 返回结果
     */
    List<FireSafetyMonitoringBean> saveFireSafetyMonitoring(FireSafetySaveInputInfo queryBean );



    /**
     * 根据案件id查询火场出入记录 ( 火场安全监控记录 )
     * @param incidentId 案件id
     * @return 返回结果
     */
    List<FireSafetyMonitoringBean> findIncidentFireSafetyMonitoring( String incidentId  );


    /**
     * 根据案件id 参战人员id 查询最新进场 撤场 记录信息
     * @param incidentId 案件id
     * @return 返回结果
     */
    Map<String,FireSafetyMonitoringBean>   findLastFireSafetyMonitoring(String incidentId , List<String> personIds );
    
    /**
     * 根据车辆id查询火场出入记录 ( 火场安全监控记录 )
     * @param incidentId 案件id
     * @return 返回结果
     */
    List<FireSafetyMonitoringBean> findEnterFireSafetyByVehicle( String vehicleId ,String incidentId );


}
