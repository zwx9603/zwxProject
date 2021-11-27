package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.SendMessageEntity;
import com.dscomm.iecs.accept.dal.repository.SendMessageRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.SendMessageInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.SendMessageBean;
import com.dscomm.iecs.accept.service.SendMessageService;
import com.dscomm.iecs.accept.utils.transform.SendMessageTransformUtil;
import com.dscomm.iecs.base.service.log.LogService;
import org.apache.logging.log4j.util.Strings;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component("sendMessageServiceImpl")
public class SendMessageServiceImpl implements SendMessageService {

    private static final Logger logger = LoggerFactory.getLogger(SendMessageServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private SendMessageRepository sendMessageRepository;
    @Autowired
    public SendMessageServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor,
                                  SendMessageRepository sendMessageRepository

    ){
        this.logService = logService;
        this.accessor = accessor;
        this.sendMessageRepository = sendMessageRepository;
    }

    /*
     *做全查询操作
     * */
    @Transactional(readOnly = true)
    @Override
    public List<SendMessageBean> queryAll() {
        try {
            logService.infoLog(logger, "service", "queryAll", "service is started...");
            Long logStart = System.currentTimeMillis();

            logService.infoLog(logger, "repository", "queryAll", "repository is started...");
            Long start = System.currentTimeMillis();

            List<SendMessageEntity> sendMessageEntities = sendMessageRepository.queryAll();
            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "queryAll", String.format("repository is finished,execute time is :%sms", end - start));
            List<SendMessageBean> transform = SendMessageTransformUtil.transform(sendMessageEntities);
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "queryAll", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return transform;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "queryAll", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_FAIL);
        }
    }

    /**
     * 根据被发送人姓名或机构名称做条件查询
     * @param ldmc
     * @param ssjgbh
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public List<SendMessageBean> queryByInfo(String ldmc, String ssjgbh) {
        try {
            logService.infoLog(logger, "service", "queryByInfo", "service is started...");
            Long logStart = System.currentTimeMillis();

            logService.infoLog(logger, "repository", "queryByInfo", "repository is started...");
            Long start = System.currentTimeMillis();
            List<SendMessageEntity> sendMessageEntities = sendMessageRepository.queryByInfo(ldmc, ssjgbh);
            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "queryByInfo", String.format("repository is finished,execute time is :%sms", end - start));
            List<SendMessageBean> transform = SendMessageTransformUtil.transform(sendMessageEntities);
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "queryByInfo", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return transform;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "queryByInfo", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_FAIL);
        }
    }

    /*
     * 添加信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean addInfo(SendMessageInputInfo info) {
        if (info == null) {
            logService.infoLog(logger, "service", "addInfo", "info is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "addInfo", "service is started...");
            Long logStart = System.currentTimeMillis();
            SendMessageEntity sendMessageEntity = new SendMessageEntity();
            sendMessageEntity.setDhhm(info.getDhhm());
            sendMessageEntity.setIssend(info.getIssend());
            sendMessageEntity.setLdmc(info.getLdmc());
            sendMessageEntity.setSsjgbh(info.getSsjgbh());
            sendMessageEntity.setId(info.getId());
            sendMessageEntity.setRyid(info.getRyid());
            accessor.save(sendMessageEntity);
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "addInfo", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return true;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "addInfo", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DATA_FAIL);
        }
    }

    /**
     * 根据人员id做批量删除
     * @param
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean deleteByInfo(List<String> list) {
        if (list == null) {
            logService.infoLog(logger, "service", "deleteByInfo", "list is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "deleteByInfo", "service is started...");
            Long logStart = System.currentTimeMillis();
            Boolean aBoolean = sendMessageRepository.deleteByInfo(list);
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "deleteByInfo", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return true;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "deleteByInfo", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.DELETE_DATA_FAIL);
        }
    }

    /**
     * 根据人员id和人员所属机构批量修改手机号、是否发送信息
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean updateInfo(SendMessageInputInfo info) {
        String dhhm = info.getDhhm();
        String ssjgbh = info.getSsjgbh();
        String ryid = info.getRyid();
        if (Strings.isBlank(dhhm)) {
            logService.infoLog(logger, "service", "updateInfo", "dhhm is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_NULL);
        } else if (Strings.isBlank(ryid)){
            logService.infoLog(logger, "service", "updateInfo", "ryid is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_NULL);
        } else if (Strings.isBlank(ssjgbh)){
            logService.infoLog(logger, "service", "updateInfo", "ssjgbh is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "updateInfo", "service is started...");
            Long logStart = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "updateInfo", "repository is started...");
            Long start = System.currentTimeMillis();
            sendMessageRepository.updateInfo(dhhm, ryid, ssjgbh);
            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "updateInfo", String.format("repository is finished,execute time is :%sms", end - start));
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "updateInfo", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return true;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "updateInfo", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DATA_FAIL);
        }
    }
}
