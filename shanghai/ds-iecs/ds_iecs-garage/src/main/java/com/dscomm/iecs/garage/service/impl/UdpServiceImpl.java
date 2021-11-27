package com.dscomm.iecs.garage.service.impl;

import com.dscomm.iecs.base.service.NotifyClientService;
import com.dscomm.iecs.garage.service.UdpService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 描述： UDP监控方式
 *
 * @author YangFuXi Date Time 2018/11/29 17:18
 */
@Component("udpServiceImpl")
public class UdpServiceImpl implements UdpService {
    private Log logger = LogFactory.getLog(UdpServiceImpl.class);
    private NotifyClientService notifyClientService ;

    @Autowired
    public UdpServiceImpl( NotifyClientService notifyClientService ) {
        this.notifyClientService = notifyClientService ;
    }

    /**
     * {@inheritDoc}
     *
     * @see #transport(String)
     */
    @Transactional(readOnly = false)
    @Override
    public void transport(String message) {
        try {
            if (logger.isInfoEnabled()) {
                logger.info("收到一条移动设备UDP消息：" + message);
            }
            // 逻辑处理  推送消息
            //测试
//            JSONObject data = new JSONObject();
//            data.put("content", "策划似乎");
//            data.put("dispatchId", 222);
//            notifyClientService.pushNotifyMessage( "test" , data.toJSONString()  );
        } catch (Exception ex) {
            if (logger.isErrorEnabled()) {
                logger.error(String.format("UDP监听失败"), ex);
            }
        }
    }


}
