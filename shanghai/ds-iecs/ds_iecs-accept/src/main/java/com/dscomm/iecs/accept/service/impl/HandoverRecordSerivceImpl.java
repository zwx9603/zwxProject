package com.dscomm.iecs.accept.service.impl;


import com.dscomm.iecs.accept.dal.po.HandoverRecordEntity;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.HandoverRecordQueryInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.HandoverRecordSaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.HandoverRecordBean;
import com.dscomm.iecs.accept.service.HandoverRecordSerivce;
import com.dscomm.iecs.accept.utils.transform.HandoverRecordTransfromUtil;
import com.dscomm.iecs.agent.service.UserService;
import com.dscomm.iecs.base.enums.EnableEnum;
import com.dscomm.iecs.base.graphql.typebean.PaginationBean;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;
import org.mx.StringUtils;
import org.mx.dal.Pagination;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@Component("handoverRecordSerivceImpl")
public class HandoverRecordSerivceImpl implements HandoverRecordSerivce {

    private static final Logger logger = LoggerFactory.getLogger(HandoverRecordSerivceImpl.class);
    private LogService logService;
    private UserService userService;
    private GeneralAccessor accessor;

    @Autowired
    public HandoverRecordSerivceImpl(LogService logService, UserService userService,@Qualifier("generalAccessor") GeneralAccessor accessor) {
        this.logService = logService;
        this.userService = userService;
        this.accessor = accessor;
    }

    /**保存交接班日志*/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public HandoverRecordBean saveHandoverRecord(HandoverRecordSaveInputInfo inputInfo) {
        if (inputInfo == null || StringUtils.isBlank(inputInfo.getHandoverPersonId()) || StringUtils.isBlank(inputInfo.getHandoverPersonName()) || inputInfo.getHandoverTime() == null){
            logService.infoLog(logger, "service", "saveHandoverRecord", "inputInfo or personId or personName or time is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveHandoverRecord", "service is started...");
            Long logStart = System.currentTimeMillis();

            decodeHandoverRecordSaveInputInfo(inputInfo);//URLDecoder 转码

            UserInfo userInfo  = userService.getUserInfo();
            HandoverRecordEntity entity = HandoverRecordTransfromUtil.transfrom(inputInfo,userInfo);
            entity.setDeleted(EnableEnum.ENABLE_FALSE.getCode());
            entity = accessor.save(entity);

            HandoverRecordBean res = HandoverRecordTransfromUtil.transform(entity);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveHandoverRecord", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return res;
        }catch (Exception e){
            logService.erorLog(logger, "service", "saveHandoverRecord", "execution error", e);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_HANDOVERRECORD_FAIL);
        }
    }

    /**查询交接班日志*/
    @Override
    @Transactional(readOnly = true)
    public PaginationBean<HandoverRecordBean> findHandoverRecordList(HandoverRecordQueryInputInfo queryBean) {
        if (queryBean == null){
            logService.infoLog(logger, "service", "findHandoverRecordList", "queryBean is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findHandoverRecordList", "service is started...");
            Long logStart = System.currentTimeMillis();

            decodeHandoverRecordQueryInputInfo(queryBean); //URLDecoder 转码

            PaginationBean<HandoverRecordBean> res = new PaginationBean<>();
            List<HandoverRecordBean> beans = new ArrayList<>();
            List<HandoverRecordEntity> entities = new ArrayList<>();
            UserInfo userInfo = userService.getUserInfo();
            Long startTime ;
            Long endTime;

            List<GeneralAccessor.ConditionGroup> handoverRecordCondtiton  = new ArrayList<>();
            handoverRecordCondtiton.add(GeneralAccessor.ConditionTuple.eq("handoverSeatNumber",userInfo.getAgentNum()));
            handoverRecordCondtiton.add(GeneralAccessor.ConditionTuple.eq("valid",1));

            if (!StringUtils.isBlank(queryBean.getKeyWord())){
                handoverRecordCondtiton.add(GeneralAccessor.ConditionGroup.or(
                        GeneralAccessor.ConditionTuple.fuzzy("handoverPersonName",queryBean.getKeyWord()),
                        GeneralAccessor.ConditionTuple.fuzzy("handoverContent",queryBean.getKeyWord())
                ));
            }

            if (queryBean.getEndTime() != null){//结束时间，默认为当前时间
                endTime = queryBean.getEndTime();
            }else {
                endTime = System.currentTimeMillis();
            }

            if (queryBean.getStartTime() != null && queryBean.getStartTime()<endTime){//开始时间，默认一周
                startTime = queryBean.getStartTime();
            }else {
                startTime = endTime - 7*86400000;
            }

            handoverRecordCondtiton.add(GeneralAccessor.ConditionTuple.gt("handoverTime",startTime));
            handoverRecordCondtiton.add(GeneralAccessor.ConditionTuple.lt("handoverTime",endTime));

            GeneralAccessor.RecordOrderGroup handoverRecordOrder = GeneralAccessor.RecordOrderGroup.group(
                    GeneralAccessor.RecordOrder.desc("handoverTime")
            );

            GeneralAccessor.ConditionGroup handoverRecordCondtitonGroup = GeneralAccessor.ConditionGroup.and(handoverRecordCondtiton);
            if (queryBean.getWhetherPage()){
                entities = accessor.find(queryBean.getPagination(), handoverRecordCondtitonGroup,handoverRecordOrder,HandoverRecordEntity.class);
                Long count = accessor.count(handoverRecordCondtitonGroup,HandoverRecordEntity.class);
                Pagination pagination = new Pagination();
                pagination.setPage(queryBean.getPagination().getPage());
                pagination.setSize(queryBean.getPagination().getSize());
                pagination.setTotal(Integer.parseInt(count.toString()));
                res.setPagination(pagination);
            }else {
                entities = accessor.find(null, handoverRecordCondtitonGroup,handoverRecordOrder,HandoverRecordEntity.class);
            }

            if (entities != null && entities.size()>0){
                for (HandoverRecordEntity entity :entities){
                    HandoverRecordBean bean = HandoverRecordTransfromUtil.transform(entity);
                    beans.add(bean);
                }
            }

            res.setList(beans);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findHandoverRecordList", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return res;
        }catch (Exception e){
            logService.erorLog(logger, "service", "findHandoverRecordList", "execution error", e);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_HANDOVERRECORD_FAIL);
        }
    }

    /**删除交接班日志*/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteHandoverRecord(List<String> ids) {
        if (ids == null || ids.size()<1){
            logService.infoLog(logger, "service", "deleteHandoverRecord", "ids is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "deleteHandoverRecord", "service is started...");
            Long logStart = System.currentTimeMillis();

            GeneralAccessor.ConditionGroup handoverRecordCondition = GeneralAccessor.ConditionGroup.and(
                    GeneralAccessor.ConditionTuple.eq("valid",1),
                    GeneralAccessor.ConditionTuple.in("id",ids.toArray(new String[ids.size()]  ) )
            );

            List<HandoverRecordEntity> entities = accessor.find(handoverRecordCondition,HandoverRecordEntity.class);
            if (entities != null && entities.size()>0){
                List<HandoverRecordEntity> removeList = new ArrayList<>() ;
                for (HandoverRecordEntity entity:entities){
                        entity.setDeleted(EnableEnum.ENABLE_TRUE.getCode());
                        entity.setValid( EnableEnum.ENABLE_FALSE.getCode());
                        removeList.add( entity);
                }
                if( removeList != null && removeList.size() > 0  ){
                    accessor.save( removeList ) ;
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "deleteHandoverRecord", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return true;
        }catch (Exception e){
            logService.erorLog(logger, "service", "deleteHandoverRecord", "execution error", e);
            throw new AcceptException(AcceptException.AccetpErrors.DELETE_HANDOVERRECORD_FAIL);
        }
    }
    /**
     * 查询交接班日志(restful接口)
     * @param queryBean
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public PaginationBean<HandoverRecordBean> findHandoverRecordRestList(HandoverRecordQueryInputInfo queryBean) {
        if (queryBean == null){
            logService.infoLog(logger, "service", "findHandoverRecordList", "queryBean is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findHandoverRecordList", "service is started...");
            Long logStart = System.currentTimeMillis();

            decodeHandoverRecordQueryInputInfo(queryBean); //URLDecoder 转码

            PaginationBean<HandoverRecordBean> res = new PaginationBean<>();
            List<HandoverRecordBean> beans = new ArrayList<>();
            List<HandoverRecordEntity> entities = new ArrayList<>();
            //UserInfo userInfo = userService.getUserInfo();
            Long startTime ;
            Long endTime;

            List<GeneralAccessor.ConditionGroup> handoverRecordCondtiton  = new ArrayList<>();
            //handoverRecordCondtiton.add(GeneralAccessor.ConditionTuple.eq("handoverSeatNumber",userInfo.getAgentNum()));
            handoverRecordCondtiton.add(GeneralAccessor.ConditionTuple.eq("valid",1));

            if (!StringUtils.isBlank(queryBean.getKeyWord())){
                handoverRecordCondtiton.add(GeneralAccessor.ConditionGroup.or(
                        GeneralAccessor.ConditionTuple.fuzzy("handoverPersonName",queryBean.getKeyWord()),
                        GeneralAccessor.ConditionTuple.fuzzy("handoverContent",queryBean.getKeyWord())
                ));
            }

            if (queryBean.getEndTime() != null){//结束时间，默认为当前时间
                endTime = queryBean.getEndTime();
            }else {
                endTime = System.currentTimeMillis();
            }

            if (queryBean.getStartTime() != null && queryBean.getStartTime()<endTime){//开始时间，默认一周
                startTime = queryBean.getStartTime();
            }else {
                startTime = endTime - 7*86400000;
            }

            handoverRecordCondtiton.add(GeneralAccessor.ConditionTuple.gt("handoverTime",startTime));
            handoverRecordCondtiton.add(GeneralAccessor.ConditionTuple.lt("handoverTime",endTime));

            GeneralAccessor.RecordOrderGroup handoverRecordOrder = GeneralAccessor.RecordOrderGroup.group(
                    GeneralAccessor.RecordOrder.desc("handoverTime")
            );

            GeneralAccessor.ConditionGroup handoverRecordCondtitonGroup = GeneralAccessor.ConditionGroup.and(handoverRecordCondtiton);
            if (queryBean.getWhetherPage()){
                entities = accessor.find(queryBean.getPagination(), handoverRecordCondtitonGroup,handoverRecordOrder,HandoverRecordEntity.class);
                Long count = accessor.count(handoverRecordCondtitonGroup,HandoverRecordEntity.class);
                Pagination pagination = new Pagination();
                pagination.setPage(queryBean.getPagination().getPage());
                pagination.setSize(queryBean.getPagination().getSize());
                pagination.setTotal(Integer.parseInt(count.toString()));
                res.setPagination(pagination);
            }else {
                entities = accessor.find(null, handoverRecordCondtitonGroup,handoverRecordOrder,HandoverRecordEntity.class);
            }

            if (entities != null && entities.size()>0){
                for (HandoverRecordEntity entity :entities){
                    HandoverRecordBean bean = HandoverRecordTransfromUtil.transform(entity);
                    beans.add(bean);
                }
            }

            res.setList(beans);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findHandoverRecordList", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return res;
        }catch (Exception e){
            logService.erorLog(logger, "service", "findHandoverRecordList", "execution error", e);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_HANDOVERRECORD_FAIL);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HandoverRecordBean saveHandoverRecordRest(HandoverRecordSaveInputInfo inputInfo) {
        if (inputInfo == null || StringUtils.isBlank(inputInfo.getHandoverPersonId()) || StringUtils.isBlank(inputInfo.getHandoverPersonName()) || inputInfo.getHandoverTime() == null){
            logService.infoLog(logger, "service", "saveHandoverRecord", "inputInfo or personId or personName or time is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveHandoverRecord", "service is started...");
            Long logStart = System.currentTimeMillis();

            decodeHandoverRecordSaveInputInfo(inputInfo);//URLDecoder 转码

            //UserInfo userInfo  = userService.getUserInfo();
            HandoverRecordEntity entity = new  HandoverRecordEntity();//HandoverRecordTransfromUtil.transfrom(inputInfo,userInfo);
            BeanUtils.copyProperties(inputInfo,entity);
            entity.setDeleted(EnableEnum.ENABLE_FALSE.getCode());
            entity = accessor.save(entity);

            HandoverRecordBean res = HandoverRecordTransfromUtil.transform(entity);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveHandoverRecord", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return res;
        }catch (Exception e){
            logService.erorLog(logger, "service", "saveHandoverRecord", "execution error", e);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_HANDOVERRECORD_FAIL);
        }
    }

    /**saveinputInfo转码*/
    private void decodeHandoverRecordSaveInputInfo(HandoverRecordSaveInputInfo source){
        if (source != null){
            try {
                if (!StringUtils.isBlank(source.getHandoverContent())){
                    source.setHandoverContent(URLDecoder.decode(source.getHandoverContent(),"UTF-8"));
                }
            }catch (Exception e){
                throw new AcceptException(AcceptException.AccetpErrors.DECODE_FAIL );
            }

        }
    }

    /**queryInputInfo 转码*/
    private void decodeHandoverRecordQueryInputInfo(HandoverRecordQueryInputInfo source){
        if (source != null){
            try {
                if (!StringUtils.isBlank(source.getKeyWord())){
                    source.setKeyWord(URLDecoder.decode(source.getKeyWord(),"UTF-8"));
                }
            }catch (Exception e){
                throw new AcceptException(AcceptException.AccetpErrors.DECODE_FAIL );
            }
        }
    }
}
