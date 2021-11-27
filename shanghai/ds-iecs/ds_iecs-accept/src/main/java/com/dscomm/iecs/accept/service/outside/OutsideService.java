package com.dscomm.iecs.accept.service.outside;

import com.dscomm.iecs.accept.graphql.inputbean.outside.OutsideInputInfo;
import com.dscomm.iecs.accept.restful.vo.ReceiveUnTrafficAlarmVO;
import com.dscomm.iecs.accept.service.bean.cad.IncidentDossierVO;


/**
 * 描述：外部接口 服务
 */
public interface OutsideService {

    /**
     * 转警
     * @param queryBean 参数
     * @return 返回结果
     */
    Boolean transferOutIncident(  OutsideInputInfo queryBean );

    /**
     *  消防转警警情回执操作
     * @return 返回结果
     */
    Boolean receiveTransferOutIncident(  ReceiveUnTrafficAlarmVO inputInfo  );


    /**
     * 获取警情卷宗110
     *
     * @param incidentId 事件id
     * @return 警情卷宗
     */
    IncidentDossierVO findIncidentDossier110(String incidentId);


    /**
     * 错位接警
     * @param queryBean 参数
     * @return 返回结果
     */
    Boolean dislocationIncident(OutsideInputInfo queryBean );

    /**
     * 错位接警 关联错位接警录音号
     * @return 返回结果
     */
    Boolean  dislocationRelayRecordNumber( String dislocationId , String relayRecordNumber  );

    /**
     * 请求协助
     * @param queryBean 参数
     * @return 返回结果
     */
    Boolean assistIncident(  OutsideInputInfo queryBean );

}
