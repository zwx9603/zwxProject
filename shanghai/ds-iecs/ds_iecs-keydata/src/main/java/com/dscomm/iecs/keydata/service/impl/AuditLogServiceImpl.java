package com.dscomm.iecs.keydata.service.impl;

import com.alibaba.fastjson.JSON;
import com.dscomm.iecs.base.graphql.typebean.Pagination;
import com.dscomm.iecs.base.graphql.typebean.PaginationBean;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.keydata.dal.po.AuditLogEntity;
import com.dscomm.iecs.keydata.exception.UserInterfaceKeydataException;
import com.dscomm.iecs.keydata.graphql.inputbean.AuditLogParam;
import com.dscomm.iecs.keydata.graphql.inputbean.AuditLogSaveInputInfo;
import com.dscomm.iecs.keydata.graphql.typebean.AuditLogBean;
import com.dscomm.iecs.keydata.service.AuditLogService;
import com.dscomm.iecs.keydata.service.ServletService;
import com.dscomm.iecs.keydata.utils.transform.KeyDataTransformUtil;
import org.mx.DateUtils;
import org.mx.StringUtils;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 描述：系统操作日志 审计日志 服务类实现
 *
 */
@Component("auditLogServiceImpl")
public class AuditLogServiceImpl implements AuditLogService {
    private static final Logger logger = LoggerFactory.getLogger(AuditLogServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor ;
    private ServletService servletService ;
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 默认的构造函数
     *
     */
    @Autowired
    public AuditLogServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor, ServletService servletService,

                               EntityManager entityManager) {
        this.logService = logService ;
        this.accessor = accessor ;
        this.servletService = servletService ;
        this.entityManager = entityManager;
    }



    /**
     * {@inheritDoc}
     *
     * @see  AuditLogService#saveAuditLog(AuditLogSaveInputInfo)
     */
    @Transactional( rollbackFor =  Exception.class )
    @Override
    public AuditLogBean saveAuditLog(AuditLogSaveInputInfo queryBean ) {
        if ( null == queryBean) {
            logService.infoLog(logger, "service", "saveAuditLog", "AuditLogSaveInputInfo is null.");
        }
        AuditLogBean res = new AuditLogBean() ;

        try {
            logService.infoLog(logger, "service", "saveAuditLog", "service is started...");
            Long logStart = System.currentTimeMillis();



            AuditLogEntity auditLogEntity = KeyDataTransformUtil.transform(queryBean , servletService.getSystemTime() );

            if ( null != auditLogEntity ) {
                logService.infoLog(logger, "repository", "save(dbAuditLogEntity)", "repository is started...");
                Long start = System.currentTimeMillis();

                accessor.save(auditLogEntity);

                Long end = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbAuditLogEntity)", String.format("repository is finished,execute time is :%sms", end - start));
            }

            res = KeyDataTransformUtil.transform( auditLogEntity );

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveAuditLog", String.format("service is finished,execute time is :%sms", logEnd - logStart));

        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveAuditLog", String.format(" save Audit Log fail by seat number: %s.", JSON.toJSONString( queryBean ) ), ex);
        }
        return  res ;
    }


    @Override
    public PaginationBean<AuditLogBean> findAuditLogByCondition(AuditLogParam param, org.mx.dal.Pagination pagination) {
        try {
            PaginationBean<AuditLogBean> result=new PaginationBean<>();
            List<AuditLogBean> list=new ArrayList<>();
            if (pagination==null){
                pagination= new org.mx.dal.Pagination();
            }
            if (param==null){
                param=new AuditLogParam();
            }
            if (param.getStartTime()==null){
                param.setStartTime(DateUtils.add(new Date(),DateUtils.FieldType.DAY,-1).getTime());
            }
            StringBuilder condition=new StringBuilder("from AuditLogEntity t where t.operateTime>=").append(param.getStartTime());
            if (param.getEndTime()!=null){
                condition.append(" and t.operateTime<").append(param.getEndTime());
            }
            if (!StringUtils.isBlank(param.getDocumentNumber())){
                condition.append(" and t.documentNumber='").append(param.getDocumentNumber()).append("'");
            }
            if (param.getOperateTypes()!=null&&!param.getOperateTypes().isEmpty()){
                condition.append(" and t.operateType in ('").append(org.apache.commons.lang3.StringUtils.join(param.getOperateTypes(),"','")).append("')");
            }
            Query countQuery = entityManager.createQuery(String.format("select count(t.id) %s", condition.toString()));
            Integer total = Integer.valueOf(String.valueOf(countQuery.getSingleResult()));
            if (total>0){
                Query query = entityManager.createQuery(String.format("select t %s order by t.operateTime desc", condition.toString()));
                query.setFirstResult((pagination.getPage()-1)*pagination.getSize());
                query.setMaxResults(pagination.getSize());
                List<AuditLogEntity> resultList = query.getResultList();
                if (resultList!=null&&!resultList.isEmpty()){
                    for (AuditLogEntity auditLogEntity : resultList) {
                        AuditLogBean bean = KeyDataTransformUtil.transform(auditLogEntity);
                        list.add(bean);
                    }
                }
            }
            pagination.setTotal(total);
            result.setPagination(pagination);
            result.setList(list);
            return result;
        }catch (Exception ex){
            logger.error("find AuditLog By Condition fail",ex);
            throw new UserInterfaceKeydataException(UserInterfaceKeydataException.KeydataErrors.FIND_AUDITLOG_FAIL);
        }
    }
}
