package com.dscomm.iecs.out.service.impl;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.out.dal.po.QueryAuditEntity;
import com.dscomm.iecs.out.dal.repository.AuditLogOutRepository;
import com.dscomm.iecs.out.enums.InterfaceTypeEnum;
import com.dscomm.iecs.out.exception.OutException;
import com.dscomm.iecs.out.graphql.inputinfo.QueryInputInfo;
import com.dscomm.iecs.out.graphql.typebean.CountBean;
import com.dscomm.iecs.out.graphql.typebean.InterfaceCountBean;
import com.dscomm.iecs.out.service.AuditLogOutService;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/4/22 16:52
 * @Describe
 */
@Component
public class AuditLogOutServiceImpl implements AuditLogOutService {
    private static final Logger logger = LoggerFactory.getLogger(AuditLogOutServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private AuditLogOutRepository auditLogRepository;

    @Autowired
    public AuditLogOutServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor,
                                  AuditLogOutRepository auditLogRepository) {
        this.logService = logService;
        this.accessor = accessor;
        this.auditLogRepository = auditLogRepository;

    }

    /**
     * 统计列表
     *
     * @param inputInfo
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public List<QueryAuditEntity> findInterfaceAuditByParams(QueryInputInfo inputInfo) {
        try {
            logService.infoLog(logger, "service", "findInterfaceAuditByParams", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<QueryAuditEntity> res = auditLogRepository.getList(inputInfo);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findInterfaceAuditByParams", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findInterfaceAuditByParams", "execution error", ex);
            throw new OutException(OutException.AccetpErrors.QUERY_FAIL);
        }

    }


    /**
     * 统计数
     *
     * @param inputInfo
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public CountBean countPostByParams(QueryInputInfo inputInfo) {
        try {
            logService.infoLog(logger, "service", "countPostByParams", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<Object[]> objects = auditLogRepository.countByParams(inputInfo);
            CountBean res = new CountBean();
            res.setBeans(transform(objects));

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "countPostByParams", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "countPostByParams", "execution error", ex);
            throw new OutException(OutException.AccetpErrors.COUNT_FAIL);
        }
    }

    public static List<InterfaceCountBean> transform(List<Object[]> objects){
        List<InterfaceCountBean> beans = new ArrayList<>();

        for (InterfaceTypeEnum interfaceTypeEnum : InterfaceTypeEnum.values()) {
            Integer postNum = 0;
            Integer dataNum = 0;
            InterfaceCountBean interfaceCountBean = new InterfaceCountBean();
            interfaceCountBean.setInterfaceName(interfaceTypeEnum.getName());
            if (objects!=null&&objects.size()>0){
                for (Object[] o:objects
                ) {
                    String interfaceName = o[0].toString();
                    if (interfaceTypeEnum.getCode().equals(interfaceName)) {
                        postNum = Integer.valueOf(o[1].toString());
                        dataNum = Integer.valueOf(o[2].toString());
                    }
                }
            }
            interfaceCountBean.setDataNum(dataNum);
            interfaceCountBean.setPostNum(postNum);
            beans.add(interfaceCountBean);
        }


        return beans;
    }


}
