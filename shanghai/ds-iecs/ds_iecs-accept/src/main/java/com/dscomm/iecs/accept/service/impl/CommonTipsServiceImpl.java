package com.dscomm.iecs.accept.service.impl;


import com.dscomm.iecs.accept.dal.po.CommonTipsEntity;
import com.dscomm.iecs.accept.dal.repository.CommonTipsRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.CommonTipsInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.CommonTipsBean;
import com.dscomm.iecs.accept.service.CommonTipsService;
import com.dscomm.iecs.accept.utils.transform.IncidentTransformUtil;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.service.DictionaryService;
import org.apache.logging.log4j.util.Strings;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * 描述：接警处警提示信息 服务类实现
 */
@Component("commonTipsServiceImpl")
public class CommonTipsServiceImpl implements CommonTipsService {
    private static final Logger logger = LoggerFactory.getLogger(CommonTipsServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private Environment env;
    private CommonTipsRepository commonTipsRepository;
    private DictionaryService dictionaryService;

    private List<String> dics;

    /**
     * 默认的构造函数
     */
    @Autowired
    public CommonTipsServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor, Environment env,
                                 CommonTipsRepository commonTipsRepository, DictionaryService dictionaryService
    ) {
        this.accessor = accessor;
        this.logService = logService;
        this.env = env;
        this.commonTipsRepository = commonTipsRepository;
        this.dictionaryService = dictionaryService;

        this.dics = new ArrayList<>(Arrays.asList("AJLX", "AJDJ", "CZDX" ));

    }

    /**
     * {@inheritDoc}
     *
     * @see CommonTipsService#findCommonTips(String, String)
     */
    @Transactional(readOnly = true)
    @Override
    public CommonTipsBean findCommonTips(String incidentTypeCode, String disposalObjectCode) {
        try {
            logService.infoLog(logger, "service", "findCommonTipsIncidentTypeLevel", "service is started...");
            Long logStart = System.currentTimeMillis();
            CommonTipsBean res = new CommonTipsBean();

            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);

            logService.infoLog(logger, "repository", "findCommonTipsCodeLevel(incidentTypeCode,incidentLevelCode)", "repository is started...");
            Long start = System.currentTimeMillis();

            List<CommonTipsEntity> commonTipsEntities = null;
            if (Strings.isNotBlank(disposalObjectCode)) {
                commonTipsEntities = commonTipsRepository.findCommonTipsTypeCode(incidentTypeCode, disposalObjectCode);
            }
            if (commonTipsEntities == null || commonTipsEntities.size() < 1) {
                commonTipsEntities = commonTipsRepository.findCommonTipsType(incidentTypeCode);
            }


            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findCommonTipsCodeLevel(incidentTypeCode,incidentLevelCode)", String.format("repository is finished,execute time is :%sms", end - start));

            if (commonTipsEntities != null && commonTipsEntities.size() > 0) {
                res = IncidentTransformUtil.transform(commonTipsEntities.get(0), dicsMap);
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findCommonTipsIncidentTypeLevel", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findCommonTipsIncidentTypeLevel", "execution error", ex);
            return new CommonTipsBean() ;
        }
    }


    @Override
    public CommonTipsBean saveCommonTips(CommonTipsInputInfo inputInfo) {
        logService.infoLog(logger, "service", "saveSafetyWarn", "service is started...");
        Long logStart = System.currentTimeMillis();
        try {
            if (Objects.isNull(inputInfo) && Objects.isNull(inputInfo.getType()) || Objects.isNull(inputInfo.getCode())) {
                logService.infoLog(logger, "service", "SafetyWarnInputInfo", "SafetyWarnInputInfo or incidentTypeCode or disposalObjectCode is null.");
                throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
            }
            logService.infoLog(logger, "repository", "findSafetyWarn", "service is started...");
            Long st = System.currentTimeMillis();

            List<CommonTipsEntity> CommonTipsEntityList = commonTipsRepository.findCommonTipsTypeCode(inputInfo.getType(), inputInfo.getCode());

            Long ed = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findSafetyWarn", String.format("service is finished,execute time is :%sms", ed - st));
            if (!CollectionUtils.isEmpty(CommonTipsEntityList)) {
                // 新增时校验重复
                if (Objects.isNull(inputInfo.getId())) {
                    logService.infoLog(logger, "service", "SafetyWarnInputInfo", "  incidentTypeCode and disposalObjectCode already exist.");
                    throw new AcceptException(AcceptException.AccetpErrors.INCIDENTTYPE_DISPOSALOBJECT_EXIST);
                }
                // 修改时校验重复
                long count = CommonTipsEntityList.stream().filter(o -> !o.getId().equals(inputInfo.getId())).count();
                if (count > 0l) {
                    logService.infoLog(logger, "service", "SafetyWarnInputInfo", "  incidentTypeCode and disposalObjectCode already exist.");
                    throw new AcceptException(AcceptException.AccetpErrors.INCIDENTTYPE_DISPOSALOBJECT_EXIST);
                }

            }
        } catch (AcceptException acceptException) {
            throw acceptException;
        }

        try {
            // 查询出所有需要用到的字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);


            logService.infoLog(logger, "repository", "save(CommonTipsEntity)", "repository is started...");
            Long start = System.currentTimeMillis();

            CommonTipsEntity entity = IncidentTransformUtil.transform(inputInfo);
            entity.setName( dicsMap.get("CZDX").get( entity.getCode() ));

            CommonTipsEntity save = accessor.save(entity);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(CommonTipsEntity)", String.format("repository is finished,execute time is :%sms", end - start));

            CommonTipsBean transform = IncidentTransformUtil.transform(save, dicsMap);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveSafetyWarn", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return transform;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveSafetyWarn", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_SAFETY_FAIL);
        }

    }

    @Override
    public Boolean removeCommonTips(List<String> ids) {
        logService.infoLog(logger, "service", "removeSafetyWarn", "service is started...");
        Long logStart = System.currentTimeMillis();
        try {
            if (CollectionUtils.isEmpty(ids)) {
                logService.infoLog(logger, "service", "removeSafetyWarn", "id is null.");
                throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
            }
        } catch (AcceptException acceptException) {
            throw acceptException;
        }
        try {
            logService.infoLog(logger, "repository", "save(CommonTipsEntity)", "repository is started...");
            Long start = System.currentTimeMillis();
            List<CommonTipsEntity> CommonTipsEntityList = commonTipsRepository.findByIds(ids);
            if (Objects.isNull(CommonTipsEntityList)) {
                logService.infoLog(logger, "repository", "findById(id)", "id is invincible.");
                throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_NULL);
            }
            for (CommonTipsEntity warnEntity : CommonTipsEntityList) {
                warnEntity.setValid(Boolean.FALSE);
                accessor.save(warnEntity);
            }

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(CommonTipsEntity)", String.format("repository is finished,execute time is :%sms", end - start));


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "removeSafetyWarn", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return Boolean.TRUE;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "removeSafetyWarn", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_ACCIDENT_FAIL);
        }
    }

    @Override
    public CommonTipsBean findById(String id) {
        if (Objects.isNull(id)) {
            logService.infoLog(logger, "service", "findById", "id is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        try {
            logService.infoLog(logger, "service", "findById", "service is started...");
            Long logStart = System.currentTimeMillis();

            logService.infoLog(logger, "repository", "findById(id)", "repository is started...");
            Long start = System.currentTimeMillis();

            List<CommonTipsEntity> CommonTipsEntityList = commonTipsRepository.findByIdValid(id);
            if (CollectionUtils.isEmpty(CommonTipsEntityList)) {
                logService.infoLog(logger, "repository", "findById(id)", "id is invincible.");
                throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_NULL);
            }

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findById(ID)", String.format("repository is finished,execute time is :%sms", end - start));

            // 查询出所有需要用到的字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
            CommonTipsBean transform = IncidentTransformUtil.transform(CommonTipsEntityList.get(0), dicsMap);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findById", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return transform;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findById", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_FAIL);
        }
    }

    @Override
    public List<CommonTipsBean> findAll() {
        try {
            logService.infoLog(logger, "service", "findAll", "service is started...");
            Long logStart = System.currentTimeMillis();

            logService.infoLog(logger, "repository", "findAllValid", "repository is started...");
            Long start = System.currentTimeMillis();

            List<CommonTipsEntity> list = commonTipsRepository.findAllValid();


            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findAllValid", String.format("repository is finished,execute time is :%sms", end - start));

            // 查询出所有需要用到的字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
            List<CommonTipsBean> res = new ArrayList<>();
            for (CommonTipsEntity commonTipsEntity : list) {
                CommonTipsBean transform = IncidentTransformUtil.transform(commonTipsEntity, dicsMap);
                res.add(transform);
            }


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findById", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findById", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_FAIL);
        }
    }
}
