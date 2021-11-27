package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.service.CheckHandleService;
import com.dscomm.iecs.base.service.log.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 描述:检查调派力量是否满足预先维护的调派方案，如果不满足，则给接警员推送消息提醒
 * author:YangFuXi
 * Date:2021/6/11 Time:11:08
 **/
@Component
public class CheckHandleServiceImpl implements CheckHandleService {
    private static final Logger logger=LoggerFactory.getLogger(CheckHandleServiceImpl.class);
    private LogService logService;
    @Value("dispatchAlgorithmService:dispatchAlgorithmService")
    private String dispatchAlgorithmService;

    public CheckHandleServiceImpl(LogService logService) {
        this.logService = logService;
    }

    /**
     * {@inheritDoc}
     * @param incidentId 案件id
     */
    @Override
    public void checkHandle(String incidentId) {
        try {

        }catch (Exception ex){
            logService.erorLog(logger,"service","checkHandle",String.format("fail to check handle incident:%s",incidentId),ex);
        }
    }
}
