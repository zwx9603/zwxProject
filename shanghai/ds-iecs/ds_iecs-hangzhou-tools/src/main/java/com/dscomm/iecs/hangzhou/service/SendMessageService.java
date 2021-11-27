package com.dscomm.iecs.hangzhou.service;

import com.dscomm.iecs.hangzhou.restful.vo.SmsMessageBackVO;
import com.dscomm.iecs.hangzhou.restful.vo.SmsMessageVO;

/**
 * 描述：  信息推送服务接口定义
 *
 * @author john peng
 * Date time 2018/7/15 上午10:08
 */
public interface SendMessageService {


    /**
     * 发送短信
     */
    SmsMessageBackVO batchSendMessage(SmsMessageVO vo , Boolean  bl );



}
