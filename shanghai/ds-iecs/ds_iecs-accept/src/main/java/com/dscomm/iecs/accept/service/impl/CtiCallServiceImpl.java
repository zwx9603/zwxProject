package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.CtiCallEntity;
import com.dscomm.iecs.accept.dal.repository.CtiCallRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.CtiCallQueryInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.CtiCallBean;
import com.dscomm.iecs.accept.service.CtiCallService;
import com.dscomm.iecs.agent.service.UserService;
import com.dscomm.iecs.base.graphql.inputbean.PaginationInputInfo;
import com.dscomm.iecs.base.graphql.typebean.PaginationBean;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;
import com.dscomm.iecs.basedata.service.OrganizationService;
import org.mx.dal.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/5/25 14:29
 * @Describe
 */
@Component
public class CtiCallServiceImpl implements CtiCallService {
    private static final Logger logger = LoggerFactory.getLogger(CtiCallServiceImpl.class);
    private LogService logService;
    private CtiCallRepository ctiCallRepository;
    private UserService userService;
    private OrganizationService organizationService;

    @Autowired
    public CtiCallServiceImpl(LogService logService, CtiCallRepository ctiCallRepository,
                              UserService userService,
                              OrganizationService organizationService

    ) {
        this.logService = logService;
        this.ctiCallRepository = ctiCallRepository;
        this.userService = userService;
        this.organizationService = organizationService;

    }


    @Transactional(readOnly = true)
    @Override
    public PaginationBean<CtiCallBean> findCtiCallCondition(CtiCallQueryInputInfo queryBean) {

        try {
            logService.infoLog(logger, "service", "findCtiCallCondition", "service is started...");
            Long logStart = System.currentTimeMillis();

            PaginationBean res = new PaginationBean();
            List<CtiCallBean> beans = new ArrayList<>();
            UserInfo userInfo = userService.getUserInfo();
            String orgId = userInfo.getOrgId();
            List<String> ids = organizationService.findChildOrganizationId(orgId);
            if (queryBean==null){
                queryBean=new CtiCallQueryInputInfo();
            }
            if (queryBean.getPagination()==null){
                queryBean.setPagination(new PaginationInputInfo());
            }
            logService.infoLog(logger, "repository", "findCtiCallCondition", "repository is started...");
            Long start = System.currentTimeMillis();
            //时间参数转换
            Timestamp st = null;
            Timestamp et = null;
            if (queryBean.getStartTime() != null) {
                st = new Timestamp(queryBean.getStartTime());
            }
            if (queryBean.getEndTime() != null) {
                et = new Timestamp(queryBean.getEndTime());
            }
            List<Object[]> objects = ctiCallRepository.findCtiCallCondition(queryBean.getPhoneNumber(),
                    queryBean.getPersonName(), st, et, ids, queryBean.getWhetherPage(),
                    queryBean.getPagination().getPage(), queryBean.getPagination().getSize());

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findCtiCallCondition", String.format("repository is finished,execute time is :%sms", end - start));

            if (objects != null && objects.size() > 0) {
                for (Object[] obj : objects
                ) {
                    CtiCallBean bean = transform(obj);
                    beans.add(bean);
                }
            }
            logService.infoLog(logger, "repository", "findCtiCallConditionTotal", "repository is started...");
            Long countStart = System.currentTimeMillis();

            Integer total = ctiCallRepository.findCtiCallConditionTotal(queryBean.getPhoneNumber(),
                    queryBean.getPersonName(), st, et, ids);

            Long countEnd = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findCtiCallConditionTotal", String.format("repository is finished,execute time is :%sms", countEnd - countStart));

            Pagination pagination = new Pagination();
            pagination.setPage(queryBean.getPagination().getPage());
            pagination.setSize(queryBean.getPagination().getSize());
            pagination.setTotal(total);

            res.setPagination(pagination);
            res.setList(beans);
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findCtiCallConditionTotal", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;


        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findCtiCallCondition", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_CTI_CALL_FAIL);
        }

    }

    public static CtiCallBean transform(Object[] source) {
        if (null != source) {
            CtiCallEntity entity = (CtiCallEntity) source[0];
            CtiCallBean target = new CtiCallBean();
            target.setAcd(entity.getAcd());
            target.setAgentNumber(entity.getAgentNumber());
            target.setCalledNumber(entity.getCalledNumber());
            target.setCallNumber(entity.getCallNumber());
            target.setDuration(entity.getDuration());
            target.setEndTime(entity.getEndTime().getTime());
            target.setId(entity.getId());
            target.setPersonId(entity.getPersonId());
            target.setStartTime(entity.getStartTime().getTime());
            target.setName(source[1].toString());
            target.setOrgName(source[2].toString());
            return target;
        }
        return null;
    }
}
