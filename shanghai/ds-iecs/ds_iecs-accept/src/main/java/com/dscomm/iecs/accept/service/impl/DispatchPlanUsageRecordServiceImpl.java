package com.dscomm.iecs.accept.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dscomm.iecs.accept.dal.po.DispatchPlanUsageRecordEntity;
import com.dscomm.iecs.accept.dal.repository.DispatchPlanUsageRecordRepository;
import com.dscomm.iecs.accept.dal.repository.HandleRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.DispachPlanUsageRecordInputInfo;
import com.dscomm.iecs.accept.service.DispatchPlanUsageRecordService;
import com.dscomm.iecs.accept.utils.transform.IncidentTransformUtil;
import com.dscomm.iecs.agent.service.UserService;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;
import org.mx.StringUtils;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述:调派方案使用记录
 * author:YangFuXi
 * Date:2021/6/21 Time:15:13
 **/
@Component
public class DispatchPlanUsageRecordServiceImpl implements DispatchPlanUsageRecordService {
    private static final Logger logger=LoggerFactory.getLogger(DispatchPlanUsageRecordServiceImpl.class);
    private UserService userService;
    private GeneralAccessor accessor;
    private DispatchPlanUsageRecordRepository dispatchPlanUsageRecordRepository;
    private HandleRepository handleRepository;

    @Autowired
    public DispatchPlanUsageRecordServiceImpl(UserService userService, @Qualifier("generalAccessor") GeneralAccessor accessor, DispatchPlanUsageRecordRepository dispatchPlanUsageRecordRepository, HandleRepository handleRepository) {
        this.userService = userService;
        this.accessor = accessor;
        this.dispatchPlanUsageRecordRepository = dispatchPlanUsageRecordRepository;
        this.handleRepository = handleRepository;
    }

    @Transactional
    @Override
    public Boolean saveDispatchPlanUsageRecord(DispachPlanUsageRecordInputInfo inputInfo) {
        if (inputInfo==null||StringUtils.isBlank(inputInfo.getIncidentId())||StringUtils.isBlank(inputInfo.getPlanId())){
            logger.error(String.format("save dispatchPlanUsageRecord fail,the param is null,param:&s",JSONObject.toJSONString(inputInfo)));
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            UserInfo userInfo = userService.getUserInfo();
            DispatchPlanUsageRecordEntity entity=new DispatchPlanUsageRecordEntity();
            entity.setIncidentId(inputInfo.getIncidentId());
            entity.setPlanId(inputInfo.getPlanId());
            entity.setOrgId(userInfo.getOrgId());
            entity.setPersonId(userInfo.getPersonId());
            entity.setPersonName(userInfo.getPersonName());
            entity.setPlanType(inputInfo.getPlanType());
            entity.setRemark(inputInfo.getRemark());
            if(StringUtils.isBlank(inputInfo.getHandleId())){
                List<String> ids = handleRepository.findHandleIdIdByIncidentId(inputInfo.getIncidentId());
                if(ids!=null&&ids.size()>0){
                    entity.setHandleId(ids.get(0));
                }
            }else {
                entity.setHandleId(inputInfo.getHandleId());
            }
            accessor.save(entity);
            return true;
        }catch (Exception ex){
            logger.error("save dispatchPlanUsageRecord fail",ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DISPATCHPLANRECORD_FAIL);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Map<String, Integer> countDispatchPlanUsageRecord() {
        Map<String, Integer> map=new HashMap<>();
        try {

            List<Object[]> res = dispatchPlanUsageRecordRepository.countDispatchPlanUsageRecord();
            if (res!=null&&!res.isEmpty()){
                for (Object[] objects : res) {
                    String id=IncidentTransformUtil.toString(objects[0]);
                    Integer total=IncidentTransformUtil.toInteger2(objects[1]);
                    map.put(id,total);
                }
            }

        }catch (Exception ex){
            logger.error("countDispatchPlanUsageRecord fail",ex);
        }
        return map;
    }
}


