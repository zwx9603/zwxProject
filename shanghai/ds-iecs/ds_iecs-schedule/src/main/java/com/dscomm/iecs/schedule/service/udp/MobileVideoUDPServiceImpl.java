package com.dscomm.iecs.schedule.service.udp;

import com.dscomm.iecs.base.service.log.LogService;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 描述：移动视频同步服务 UDP监控方式
 *
 * @author YangFuXi Date Time 2018/11/29 17:18
 */
@Component
public class MobileVideoUDPServiceImpl implements MobileVideoUDPService {

    private static final Logger logger = LoggerFactory.getLogger(MobileVideoUDPServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;

    @Autowired
    public MobileVideoUDPServiceImpl( LogService logService , @Qualifier("generalAccessor") GeneralAccessor accessor) {
        this.logService = logService ;
        this.accessor = accessor;
    }

    /**
     * {@inheritDoc}
     *
     * @see #transport(String)
     */
    @Transactional( rollbackFor =  Exception.class )
    @Override
    public void transport(String message) {
        try {
            logService.infoLog(logger, "service", "transport", "service is started...");
            Long logStart = System.currentTimeMillis();

            if (logger.isInfoEnabled()) {
                logger.info("收到一条移动设备UDP消息：" + message);
            }


            //todo 项目 业务逻辑处理


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "transport", String.format("service is finished,execute time is :%sms", logEnd - logStart));


        } catch (Exception ex) {
            logService.erorLog(logger, "service", "transport", "UDP monitor fail error", ex);
        }
    }




}
