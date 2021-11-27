package com.dscomm.iecs.accept.hangzhou.service;

import com.dscomm.iecs.accept.graphql.typebean.EarlyWarningBean;
import com.dscomm.iecs.accept.graphql.typebean.HandleBean;
import com.dscomm.iecs.accept.graphql.typebean.IncidentBean;

import java.util.List;

/**
 *  接处警 Ttss 播报信息
 */
public interface TTSSpeechService {



    /**
     * 处警信息 ttss 播报 第三方通知
     * */
    Boolean pushIncidentHandleTTS ( IncidentBean incidentBean,  HandleBean  handleBean   );



    /**
     * 处警信息 ttss 播报文件生成
     * */
    String buildSpeakToFile ( IncidentBean incidentBean,  HandleBean  handleBean   );


    /**
     * 预警警信息 ttss 播报文件生成
     * */
    String buildSpeakToFile (EarlyWarningBean earlyWarningBean     );

}
