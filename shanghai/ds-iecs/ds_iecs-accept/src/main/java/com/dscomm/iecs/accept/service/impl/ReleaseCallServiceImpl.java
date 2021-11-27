package com.dscomm.iecs.accept.service.impl;

import com.alibaba.fastjson.JSON;
import com.dscomm.iecs.accept.dal.po.ReleaseCallEntity;
import com.dscomm.iecs.accept.dal.repository.ReleaseCallRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.ReleaseCallQueryInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.ReleaseCallSaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.ReleaseCallBean;
import com.dscomm.iecs.accept.service.ReleaseCallService;
import com.dscomm.iecs.agent.service.UserService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.keydata.service.ServletService;
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

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/5/10 13:58
 * @Describe 排队早释 服务实现
 */
@Component
public class ReleaseCallServiceImpl implements ReleaseCallService {

    private static final Logger logger = LoggerFactory.getLogger(ReleaseCallServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private UserService userService;
    private ReleaseCallRepository releaseCallRepository;
    private OrganizationService organizationService;
    private ServletService servletService;

    @Autowired
    public ReleaseCallServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor,
                                  UserService userService,
                                  ReleaseCallRepository releaseCallRepository,
                                  OrganizationService organizationService,
                                  ServletService servletService){
        this.logService = logService;
        this.accessor = accessor;
        this.userService = userService;
        this.releaseCallRepository = releaseCallRepository;
        this.organizationService = organizationService;
        this.servletService = servletService;
    }


    /**
     * 保存排队早释记录
     * @param info
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean saveReleaseCall(ReleaseCallSaveInputInfo info) {
        if (info == null || Strings.isBlank(info.getCallNumber()) ||info.getReleasedTime()==null || info.getQueuedTime()==null) {
            logService.infoLog(logger, "service", "saveReleaseCall", "ReleaseCallSaveInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try{

            logService.infoLog(logger, "service", "saveReleaseCall", "service is started...");
            Long logStart = System.currentTimeMillis();
            Long systemTime = servletService.getSystemTime();
            UserInfo userInfo = userService.getUserInfo();
//            //3秒钟之内的早释记录认为是同一个，防止所有坐席都调用此接口造成重复数据
//            List<ReleaseCallEntity> res = releaseCallRepository.findReleaseCallEntitiesByTimeAndPhone(systemTime - 3 * 1000l, info.getCallNumber());
//            if (res==null||res.isEmpty()){
                ReleaseCallEntity entity = new ReleaseCallEntity();
                entity.setAgentNumber(userInfo.getAgentNum());//坐席号
                entity.setOrgId(userInfo.getOrgId());//排队单位id
                entity.setOrgName(userInfo.getOrgName());//排队单位名称
                entity.setPersonNumber(userInfo.getAccount());//工号
                entity.setCallNumber(info.getCallNumber());//主叫号码
                entity.setQueuedTime(info.getQueuedTime());//排队时间
                entity.setReleasedTime(info.getReleasedTime());//早释时间
                if (entity.getReleasedTime()==null){
                    entity.setReleasedTime(systemTime);
                }
                accessor.save(entity);

//            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveReleaseCall", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return true;
        }catch (Exception ex){
            logService.erorLog(logger, "service", "saveReleaseCall", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_RELEASE_CALL_FAIL);
        }

    }


    /**
     * 查询列表
     * @param queryInputInfo
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public List<ReleaseCallEntity> findReleaseCalls(ReleaseCallQueryInputInfo queryInputInfo) {
        try{
            logService.infoLog(logger, "service", "findReleaseCalls", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<ReleaseCallEntity> res = new ArrayList<>();
            //如果机构id为空则查询全部
            res = releaseCallRepository.findReleaseCallEntities(queryInputInfo);
            //如果机构id 不为空  获取下级机构；
            if (Strings.isNotBlank(queryInputInfo.getOrgId())) {
                List<String> orgIds = organizationService.findChildOrganizationId(queryInputInfo.getOrgId()); //输入的机构以及下级机构
                res = releaseCallRepository.findReleaseCallEntitiesByIds(queryInputInfo,orgIds);
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findReleaseCalls", String.format("service is finished,execute time is :%sms", logEnd - logStart));


            return res;

        }catch (Exception ex){
            logService.erorLog(logger, "service", "findReleaseCalls", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_RELEASE_CALL_FAIL);
        }

    }
    @Transactional
    @Override
    public Boolean saveQueueCalls(ReleaseCallSaveInputInfo param) {
        try {
            List<ReleaseCallEntity> list = releaseCallRepository.findQueueCallEntitiesByPhone(param.getCallNumber());
            if (list==null||list.isEmpty()){
                ReleaseCallEntity entity=new ReleaseCallEntity();
                entity.setAgentNumber(param.getAgentNumber());
                entity.setCallNumber(param.getCallNumber());
                entity.setOrgId(param.getOrgId());
                entity.setOrgName(param.getOrgName());
                entity.setPersonNumber(param.getPersonNumber());
                entity.setQueuedTime(param.getQueuedTime());
                if (entity.getQueuedTime()==null){
                    entity.setQueuedTime(servletService.getSystemTime());
                }
                accessor.save(entity);
            }
            return true;
        }catch (Exception ex){
            logService.erorLog(logger,"service","saveQueueCalls",String.format("fail to save queue call,data:%s",JSON.toJSONString(param)),ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DATA_FAIL);
        }
    }

    @Transactional
    @Override
    public Boolean removeQuenceCall(String callNum) {
        try {
            List<ReleaseCallEntity> list = releaseCallRepository.findQueueCallEntitiesByPhone(callNum);
            if (list!=null&&list.isEmpty()){
                Long systemTime = servletService.getSystemTime();
                list.forEach(entity->{
                    entity.setValid(0);
                    entity.setUpdatedTime(systemTime);
                });
                releaseCallRepository.saveAll(list);
            }
            return true;
        }catch (Exception ex){
            logService.erorLog(logger, "service", "findReleaseCalls", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DATA_FAIL);
        }
    }

    @Override
    public List<ReleaseCallBean> findQueueList() {
        try {
            List<ReleaseCallBean> list=new ArrayList<>();
            //默认获取正在排队的电话（为过滤掉无效的异常数据，取2个小时内还在排队的电话）
            Long systemTime = servletService.getSystemTime();
            long startTime=systemTime-2*60*60*1000l;
            List<ReleaseCallEntity> res = releaseCallRepository.findQueueCallEntitiesByTime(startTime);
            if (res!=null&&!res.isEmpty()){
                res.forEach(entity->{
                    ReleaseCallBean bean=new ReleaseCallBean();
                    bean.setAgentNumber(entity.getAgentNumber());
                    bean.setCallNumber(entity.getCallNumber());
                    bean.setOrgId(entity.getOrgId());
                    bean.setOrgName(entity.getOrgName());
                    bean.setPersonNumber(entity.getPersonNumber());
                    bean.setQueuedTime(entity.getQueuedTime());
                    list.add(bean);
                });
            }
            return list;
        }catch (Exception ex){
            logService.erorLog(logger,"service","findQueueList","fail to find queue list",ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_FAIL);
        }
    }
}
