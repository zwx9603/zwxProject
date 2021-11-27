package com.dscomm.iecs.base.service.impl;

import com.dscomm.iecs.base.service.SubAuditService;
import com.dscomm.iecs.base.service.log.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 描述:第三方日志服务
 *
 */
@Component
public class SubAuditServiceImpl implements SubAuditService {
    private static final Logger logger = LoggerFactory.getLogger(SubAuditServiceImpl.class);
    private LogService logService;

    @Value("${printSubAuditLog}")
    private String printSubAuditLog;

    @Autowired
    public SubAuditServiceImpl( LogService logService ) {
        this.logService = logService;
    }

    @Override
    public void buildSubAuditLog( String  account , String userName , String organizationCode , String  organizationName  ,
                                  String operateType, String operateResult, String operateContent) {
        try {
            if ("true".equalsIgnoreCase(printSubAuditLog)) {
                operateContent=operateContent.replace("["," ");
                operateContent=operateContent.replace("]"," ");
                String logContent = String.format("[%s][%s][%s][%s][%s][%s]%s", account ,
                        userName ,organizationCode ,  organizationName , operateType, operateResult, operateContent);
                logger.info(logContent);
            }
        } catch (Exception ex) {
           logService.erorLog(logger, "log", "buildSubAuditLog", String.format(" build sub  Audit Log fail "  ), ex);
        }

    }
}