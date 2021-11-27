package com.dscomm.iecs.integrate.service;

import com.dscomm.iecs.integrate.restful.vo.OutsideVO;
import com.dscomm.iecs.integrate.restful.vo.ReceiveUnTrafficAlarmVO;


/**
 * 描述：外部接口 服务
 */
public interface OutsideService {

    /**
     * 转警
     * @param queryBean 参数
     * @return 返回结果
     */
    Boolean transferOutIncident(OutsideVO queryBean);


    /**
     * 错位接警
     * @param queryBean 参数
     * @return 返回结果
     */
    Boolean dislocationIncident(OutsideVO queryBean);

    /**
     * 请求协助
     * @param queryBean 参数
     * @return 返回结果
     */
    Boolean assistIncident(OutsideVO queryBean);


    /**
     * 非话务报警
     * @param queryBean 参数
     * @return 返回结果
     */
    String unTrafficAlarm ( ReceiveUnTrafficAlarmVO queryBean );

}
