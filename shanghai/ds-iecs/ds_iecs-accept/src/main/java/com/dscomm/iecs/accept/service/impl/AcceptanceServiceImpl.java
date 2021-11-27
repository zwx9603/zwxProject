package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.AcceptanceEntity;
import com.dscomm.iecs.accept.dal.repository.AcceptanceRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.typebean.AcceptanceBean;
import com.dscomm.iecs.accept.graphql.typebean.AcceptanceInformationBean;
import com.dscomm.iecs.accept.service.AcceptanceService;
import com.dscomm.iecs.accept.utils.transform.IncidentTransformUtil;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.graphql.typebean.KeyUnitSimpleBean;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.KeyUnitService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import org.apache.logging.log4j.util.Strings;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 描述：受理单 服务类实现
 */
@Component("acceptanceServiceImpl")
public class AcceptanceServiceImpl implements AcceptanceService {
    private static final Logger logger = LoggerFactory.getLogger(AcceptanceServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private Environment env;
    private AcceptanceRepository acceptanceRepository;
    private DictionaryService dictionaryService;
    private OrganizationService organizationService;
    private KeyUnitService keyUnitService;

    private List<String> dics;

    /**
     * 默认的构造函数
     */
    @Autowired
    public AcceptanceServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor, Environment env,
                                 DictionaryService dictionaryService, OrganizationService organizationService,
                                 KeyUnitService keyUnitService, AcceptanceRepository acceptanceRepository
    ) {
        this.logService = logService;
        this.accessor = accessor;
        this.env = env;
        this.acceptanceRepository = acceptanceRepository;
        this.dictionaryService = dictionaryService;
        this.organizationService = organizationService;
        this.keyUnitService = keyUnitService;
        dics = new ArrayList<>(Arrays.asList("XB","XZQX", "BJFS", "LAFS", "AJLX", "AJLXZX", "AJDJ", "AJZT", "AJXZ", "CZDX", "ZDDWLX" ,"JZJG" ,"YWQK", "ZHCS" ,"JQDX"   ));


    }

    /**
     * {@inheritDoc}
     *
     * @see AcceptanceService#findAcceptance(String)
     */
    @Transactional(readOnly = true)
    @Override
    public AcceptanceInformationBean findAcceptance(String incidentId) {
        if (Strings.isBlank(incidentId)) {
            logService.infoLog(logger, "service", "findAcceptance", "incidentId is Blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findAcceptance", "service is started...");
            Long logStart = System.currentTimeMillis();

            AcceptanceInformationBean res = new AcceptanceInformationBean();

            AcceptanceBean mainAcceptance = null ;
            List<AcceptanceBean> acceptanceList = new ArrayList<>();


            // 查询出所有需要用到的字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;
            // 查询机构id-名称缓存map
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();

            logService.infoLog(logger, "repository", "findAcceptanceByIncidentId", "repository is started...");
            Long start = System.currentTimeMillis();

            List<AcceptanceEntity> acceptanceEntityList = acceptanceRepository.findAcceptanceByIncidentId(incidentId);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findAcceptanceByIncidentId", String.format("repository is finished,execute time is :%sms", end - start));

            if (acceptanceEntityList != null && acceptanceEntityList.size() > 0) {
                for (AcceptanceEntity entity : acceptanceEntityList) {
                    if(incidentId.equals(entity.getOriginalIncidentNumber())){
                        mainAcceptance = IncidentTransformUtil.transform(entity, dicsMap, organizationNameMap  );
                        if( Strings.isNotBlank( mainAcceptance.getKeyUnitId( )  ) ) {
                            KeyUnitSimpleBean keyUnitSimple =keyUnitService.findKeyUnitSimple( mainAcceptance.getKeyUnitId() ) ;
                            if( keyUnitSimple != null ){
                                mainAcceptance.setKeyUnitName(keyUnitSimple.getUnitName() );
                            }
                        }
                    }
                    AcceptanceBean bean = IncidentTransformUtil.transform(entity, dicsMap, organizationNameMap  );
                    if( Strings.isNotBlank( bean.getKeyUnitId( )  ) ) {
                        KeyUnitSimpleBean keyUnitSimple =keyUnitService.findKeyUnitSimple( bean.getKeyUnitId() ) ;
                        if( keyUnitSimple != null ){
                            bean.setKeyUnitName(keyUnitSimple.getUnitName() );
                        }
                    }
                    acceptanceList.add(bean);
                }
                acceptanceEntityList.clear();
                acceptanceEntityList=null;
            }

            res.setMainAcceptance(mainAcceptance);
            res.setAcceptanceList(acceptanceList);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findAcceptance", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findAcceptance", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_ACCEPTANCE_FAIL);
        }

    }

    /**
     * {@inheritDoc}
     *
     * @see AcceptanceService#changeAcceptanceIncidentId(String, String)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void changeAcceptanceIncidentId(String sourceIncidentId, String targetIncidentId) {
        if (Strings.isBlank(sourceIncidentId) || Strings.isBlank(targetIncidentId)) {
            logService.infoLog(logger, "service", "changeAcceptanceIncidentId", "source or target IncidentId is Blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findAcceptanceByIncidentId", "service is started...");
            Long logStart = System.currentTimeMillis();
            //根据原案件id查找受理单
            logService.infoLog(logger, "repository", "findAcceptanceByIncidentId", "repository is started...");
            Long start = System.currentTimeMillis();

            List<AcceptanceEntity> acceptanceEntityList = acceptanceRepository.findAcceptanceByIncidentId(sourceIncidentId);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findAcceptanceByIncidentId", String.format("repository is finished,execute time is :%sms", end - start));

            //将受理单关联的案件id置为目标案件id
            if (acceptanceEntityList != null && acceptanceEntityList.size() > 0) {
                for (AcceptanceEntity entity : acceptanceEntityList) {
                    entity.setIncidentId(targetIncidentId);
                }
                //保存更改
                logService.infoLog(logger, "repository", "save(dbAcceptanceEntityList)", "repository is started...");
                Long saveStart = System.currentTimeMillis();

                accessor.save(acceptanceEntityList);

                Long saveEnd = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbAcceptanceEntityList)", String.format("repository is finished,execute time is :%sms", saveEnd - saveStart));
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "changeAcceptanceIncidentId", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "changeAcceptanceIncidentId", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.CHANGE_ACCEPTANCE_INCIDENT_ID_FAIL);
        }

    }

    /**
     * {@inheritDoc}
     *
     * @see AcceptanceService#recoverAcceptanceIncidentId(String)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void recoverAcceptanceIncidentId(String incidentId) {
        if (Strings.isBlank(incidentId)) {
            logService.infoLog(logger, "service", "recoverAcceptanceIncidentId", "incidentId is Blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "recoverAcceptanceIncidentId", "service is started...");
            Long logStart = System.currentTimeMillis();
            //根据原案件id查找受理单
            logService.infoLog(logger, "repository", "findAcceptanceByOriginalIncidentId", "repository is started...");
            Long start = System.currentTimeMillis();

            List<AcceptanceEntity> acceptanceEntityList = acceptanceRepository.findAcceptanceByOriginalIncidentId(incidentId);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findAcceptanceByOriginalIncidentId", String.format("repository is finished,execute time is :%sms", end - start));

            //将受理单关联的案件id置为原始案件id
            if (acceptanceEntityList != null && acceptanceEntityList.size() > 0) {
                for (AcceptanceEntity entity : acceptanceEntityList) {
                    entity.setIncidentId(entity.getOriginalIncidentNumber());
                }
                //保存更改
                logService.infoLog(logger, "repository", "save(dbAcceptanceEntityList)", "repository is started...");
                Long saveStart = System.currentTimeMillis();

                accessor.save(acceptanceEntityList);

                Long saveEnd = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbAcceptanceEntityList)", String.format("repository is finished,execute time is :%sms", saveEnd - saveStart));

            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "recoverAcceptanceIncidentId", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "recoverAcceptanceIncidentId", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.RECOVER_ACCEPTANCE_INCIDENT_ID_FAIL);
        }

    }
}
