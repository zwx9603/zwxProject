package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.restful.vo.UserSMSLocationVO;
import com.dscomm.iecs.accept.service.bean.LocationBean;

import java.util.List;

/**
 * 描述:定位轨迹服务
 *
 * @author LiLu
 * Date Time 2019/8/14 0014 10:03
 */
public interface LocationService {
    /**
     * 保存定位轨迹
     *
     * @param locationBO 轨迹信息
     * @return 轨迹信息
     */
    LocationBean saveLocation(LocationBean locationBO);

    /**
     * 获取案件/报警人历史位置服务
     *
     * @param incidentId     事件id
     * @param trajectoryType 轨迹类型 1:案件轨迹 2:报警人轨迹
     * @return 轨迹信息列表
     */
    List<LocationBean> findLocationByIncidentIdAndTrajectoryType(String incidentId, Integer trajectoryType);

    /**
     * 保存 定位短信信息
     *
     * @param addressInfo 短信定位结果
     * @return 返回通知结果描述
     */
    Boolean saveSMSLocation(UserSMSLocationVO addressInfo);


    /**
     * 短信定位结果，webSocket发送给前端用户
     *
     * @param addressInfo 短信定位结果
     * @return 返回通知结果描述
     */
    String subSMSLocationResultSendToUser(UserSMSLocationVO addressInfo);
}
