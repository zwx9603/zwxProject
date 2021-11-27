package com.dscomm.iecs.integrate.service;

import com.dscomm.iecs.integrate.restful.vo.TTSSpeechDTO;

/**
 *  接处警 Ttss 播报信息
 */
public interface TTSSpeechService {



    /**
     * 处警信息 ttss 播报
     * */
    Boolean pushIncidentHandleTTS( TTSSpeechDTO ttsSpeechDTO );


}
