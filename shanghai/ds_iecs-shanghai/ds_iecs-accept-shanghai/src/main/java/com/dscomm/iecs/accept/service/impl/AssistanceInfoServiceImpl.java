package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.AssistanceEntity;
import com.dscomm.iecs.accept.dal.po.GradeEntity;
import com.dscomm.iecs.accept.dal.repository.AssistanceRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.AssistanceInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.AssistanceBean;
import com.dscomm.iecs.accept.graphql.typebean.GradeBean;
import com.dscomm.iecs.accept.service.AssistanceInfoService;
import com.dscomm.iecs.accept.utils.transform.AssistanceTransformUtil;
import com.dscomm.iecs.base.service.log.LogService;
import org.apache.logging.log4j.util.Strings;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component("assistanceInfoServiceImpl")
public class AssistanceInfoServiceImpl implements AssistanceInfoService {

    private static final Logger logger = LoggerFactory.getLogger(AssistanceInfoServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private AssistanceRepository assistanceRepository;
    @Autowired
    public AssistanceInfoServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor,
                                     AssistanceRepository assistanceRepository

    ){
        this.logService = logService;
        this.accessor = accessor;
        this.assistanceRepository = assistanceRepository;
    }

    /*
    * 保存友邻援助信息
    * */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean saveAssistanceInfo(AssistanceInputInfo info) {
        if (info == null) {
            logService.infoLog(logger, "service", "saveAssistanceInfo", "AssistanceInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try{
            //先判断添加的信息是否存在
            String zgdwdm = info.getZgdwdm();
            String pldwdm = info.getPldwdm();
            String xfjgdm = info.getXfjgdm();
            String xcjl = info.getXcjl();
            AssistanceEntity result = assistanceRepository.queryBack(zgdwdm, pldwdm,xfjgdm);
            if (result != null) {
                //判断插入的信息已存在，就修改信息
                String id = result.getId();
                assistanceRepository.updateInfo(xcjl,zgdwdm,pldwdm,xfjgdm);
                return true;
            }else {
                //判断插入的信息不存在，插入信息
                logService.infoLog(logger, "service", "saveAssistanceInfo", "service is started...");
                Long logStart = System.currentTimeMillis();
                AssistanceEntity ass = new AssistanceEntity();
                ass.setXcjl(info.getXcjl());
                ass.setPldwdm(info.getPldwdm());
                ass.setXfjgdm(info.getXfjgdm());
                ass.setZgdwdm(info.getZgdwdm());
                accessor.save(ass);
                Long logEnd = System.currentTimeMillis();
                logService.infoLog(logger, "service", "saveAssistanceInfo", String.format("service is finished,execute time is :%sms", logEnd - logStart));
                return true;
            }
        }catch (Exception ex){
            logService.erorLog(logger, "service", "saveAssistanceInfo", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DATA_FAIL);
        }
    }

    /**
     * 根据id查询友邻援助的信息
     * @param zgdwdmId
     * @return
     */
    @Transactional( readOnly = true )
    @Override
    public AssistanceBean queryInfo(String zgdwdmId) {
        if (Strings.isBlank(zgdwdmId)) {
            logService.infoLog(logger, "service", "queryInfo", "zgdwdmId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "queryInfo", "service is started...");
            Long logStart = System.currentTimeMillis();

            logService.infoLog(logger, "repository", "queryInfo", "repository is started...");
            Long start = System.currentTimeMillis();

            AssistanceEntity result = assistanceRepository.queryInfo(zgdwdmId);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "queryInfo", String.format("repository is finished,execute time is :%sms", end - start));

            AssistanceTransformUtil assistanceTransformUtil = new AssistanceTransformUtil();
            AssistanceBean ass = assistanceTransformUtil.transform(result);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "queryInfo", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return ass;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "queryInfo", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_FAIL);
        }
    }
}
