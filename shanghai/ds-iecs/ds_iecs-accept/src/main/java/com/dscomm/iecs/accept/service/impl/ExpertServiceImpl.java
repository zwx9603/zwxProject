package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.repository.ExpertRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.service.ExpertService;
import com.dscomm.iecs.accept.utils.transform.FireTransformUtil;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.dal.po.ExpertEntity;
import com.dscomm.iecs.basedata.graphql.typebean.ExpertBean;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Author: zs
 * @Date: 16:15 2020/7/23
 * desc:
 */
@Component
public class ExpertServiceImpl implements ExpertService {
    private static final Logger logger = LoggerFactory.getLogger(ExpertServiceImpl.class);
    private GeneralAccessor accessor;
    private LogService logService;
    private ExpertRepository expertRepository;
    private DictionaryService dictionaryService ;
    private OrganizationService organizationService ;

    private List<String> dics;

    @Autowired
    public ExpertServiceImpl(@Qualifier("generalAccessor") GeneralAccessor accessor  , LogService logService,ExpertRepository expertRepository ,
                             DictionaryService dictionaryService  , OrganizationService organizationService ){
        this.accessor = accessor;
        this.logService = logService;
        this.expertRepository = expertRepository;
        this.dictionaryService = dictionaryService ;
        this.organizationService = organizationService ;

        dics = new ArrayList<>(Arrays.asList("XB", "MZ", "JG", "ZZMM", "XL", "XW", "ZJLY", "HYZK", "RYLB",
                "ZWQK", "GW", "ZYJSZW", "ZW", "ZJLX", "JX", "RYZWQK", "RYZK" ));
    }

    /**
     * ??????????????????
     * @param expertField ??????
     * @param expertType ??????
     * @param expertName ??????
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public List<ExpertBean> findSmartExpertList(String expertField, String expertType, String expertName) {
        try{
            logService.infoLog(logger, "service", "findSmartExpertList", "service is started...");
            Long logStart = System.currentTimeMillis();
            List<ExpertBean>beanList = new ArrayList<>();
            logService.infoLog(logger, "service", "findSmartExpertList", "service is started...");
            Long start = System.currentTimeMillis();
            //??????
            List<ExpertEntity>expertEntities = expertRepository.findSmartExpertList(expertField,expertType,expertName);
            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findSmartExpertList", String.format("service is finished,execute time is :%sms", end - start));

            if (expertEntities!=null&&expertEntities.size()>0){
                // ????????????????????????????????????
                Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;
                // ????????????id-????????????map
                Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();

                beanList = FireTransformUtil.transformList(expertEntities , dicsMap , organizationNameMap );
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findSmartExpertList", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return beanList;
        }catch (Exception ex){
            if (logger.isErrorEnabled()) {
                logger.error("????????????????????????", ex);
            }
            throw new AcceptException(AcceptException.AccetpErrors.FIND_EXPERT_LIST_FAIL);
        }
    }

    /**
     * ????????????
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public ExpertBean findExpertDetailById(String id) {
        try {
            logService.infoLog(logger, "service", "findExpertDetailById", "service is started...");
            Long logStart = System.currentTimeMillis();

            ExpertBean bean = new ExpertBean();
            //????????????
            if (id==null){
                throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
            }
            logService.infoLog(logger, "service", "findExpertDetailById", "service is started...");
            Long start = System.currentTimeMillis();
            //??????
            ExpertEntity expertEntity = accessor.getById(id,ExpertEntity.class);
            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findExpertDetailById", String.format("service is finished,execute time is :%sms", end - start));

            if (expertEntity!=null){
                // ????????????????????????????????????
                Map<String, Map<String, String>> dicsMap  = dictionaryService.findDictionaryMap(dics) ;
                // ????????????id-????????????map
                Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();

                bean = FireTransformUtil.transform(expertEntity , dicsMap , organizationNameMap );
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findExpertDetailById", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return bean;
        }catch (Exception ex){
            if (logger.isErrorEnabled()) {
                logger.error("????????????????????????", ex);
            }
            throw new AcceptException(AcceptException.AccetpErrors.GET_EXPERT_DETAILS_FAIL);
        }

    }

    @Transactional(readOnly = true)
    @Override
    public List<ExpertBean> findSmartExpertList(String keyword) {
        try{
            logService.infoLog(logger, "service", "findSmartExpertList", "service is started...");
            Long logStart = System.currentTimeMillis();
            List<ExpertBean>beanList = new ArrayList<>();
            logService.infoLog(logger, "service", "findSmartExpertList", "service is started...");
            Long start = System.currentTimeMillis();
            //??????
            List<ExpertEntity>expertEntities = expertRepository.findExpertList(keyword);
            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findSmartExpertList", String.format("service is finished,execute time is :%sms", end - start));

            if (expertEntities!=null&&expertEntities.size()>0){
                // ????????????????????????????????????
                Map<String, Map<String, String>> dicsMap  = dictionaryService.findDictionaryMap(dics) ;
                // ????????????id-????????????map
                Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();

                beanList = FireTransformUtil.transformList(expertEntities , dicsMap , organizationNameMap );
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findSmartExpertList", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return beanList;
        }catch (Exception ex){
            if (logger.isErrorEnabled()) {
                logger.error("????????????????????????", ex);
            }
            throw new AcceptException(AcceptException.AccetpErrors.FIND_EXPERT_LIST_FAIL);
        }
    }
}
