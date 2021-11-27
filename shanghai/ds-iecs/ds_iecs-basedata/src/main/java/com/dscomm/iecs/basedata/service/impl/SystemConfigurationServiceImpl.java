package com.dscomm.iecs.basedata.service.impl;

import com.dscomm.iecs.base.websocket.WebsocketCodeEnum;
import com.dscomm.iecs.base.service.NotifyActionService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.dal.po.SystemConfigurationEntity;
import com.dscomm.iecs.basedata.dal.repository.SystemConfigurationRepository;
import com.dscomm.iecs.basedata.exception.BasedataException;
import com.dscomm.iecs.basedata.graphql.inputbean.SystemConfigurationSaveInputInfo;
import com.dscomm.iecs.basedata.graphql.inputbean.SystemConfigurationSaveListInputInfo;
import com.dscomm.iecs.basedata.graphql.typebean.SystemConfigurationBean;
import com.dscomm.iecs.basedata.service.DictionaryCacheService;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.basedata.service.SystemConfigurationService;
import com.dscomm.iecs.basedata.utils.SystemTransformUtil;
import org.apache.logging.log4j.util.Strings;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 描述：系统配置 服务类实现
 */
@Component("systemConfigurationServiceImpl")
public class SystemConfigurationServiceImpl implements SystemConfigurationService {

    private static final Logger logger = LoggerFactory.getLogger(SystemConfigurationServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private SystemConfigurationRepository systemConfigurationRepository;
    private NotifyActionService notifyActionService ;
    private OrganizationService organizationService ;
    private DictionaryCacheService dictionaryCacheService;




    //设置配置信息数据缓存数据
//    private static  Map<String,SystemConfigurationBean> systemConfigurationBeanMap = new HashMap<>();

    /**
     * 默认的构造函数
     */
    @Autowired
    public SystemConfigurationServiceImpl(@Qualifier("generalAccessor") GeneralAccessor accessor, LogService logService,
                                          SystemConfigurationRepository systemConfigurationRepository,
                                          NotifyActionService notifyActionService,
                                          OrganizationService organizationService,
                                          DictionaryCacheService dictionaryCacheService) {
        this.accessor = accessor;
        this.logService = logService;
        this.systemConfigurationRepository = systemConfigurationRepository;
        this.notifyActionService = notifyActionService;
        this.organizationService = organizationService ;
        this.dictionaryCacheService = dictionaryCacheService;
    }


    private Long lastTime  = 0l ; //系统默认为0 加载全部

    /**
     *  根据上次数据最新时间  本次数据最新时间
     *  判断是否从数据库更新数据 如果本次数据最新时间 大于 上次数据时间 则需要更新数据
     */
    private void updateCacheSystemConfiguration(){
        logService.infoLog(logger, "service", "UpdateCacheSystemConfiguration", "service is started...");
        Long logStart = System.currentTimeMillis();

        Long latestTime = systemConfigurationRepository.findDataLatestTime( lastTime ) ;
        latestTime = latestTime == null ? lastTime : latestTime ;
        //判断是否需要更新数据
        if( latestTime > lastTime ){

            logService.infoLog(logger, "service", "UpdateCacheSystemConfiguration", " update cache data");

            //此处为增量更新数据
            List<SystemConfigurationEntity>  systemConfigurationEntityList  =
                    systemConfigurationRepository.findDataLatestTime( lastTime , latestTime ) ;
            if (null != systemConfigurationEntityList && systemConfigurationEntityList.size() > 0) {
                for (SystemConfigurationEntity systemConfigurationEntity : systemConfigurationEntityList) {
                    SystemConfigurationBean systemConfigurationBean = SystemTransformUtil.transform(systemConfigurationEntity);
                     //添加数据到缓存信息 中
                    dictionaryCacheService.modifySystemConfigCache( systemConfigurationBean.getConfigType() , systemConfigurationBean ) ;
                }
            }
            lastTime = latestTime ;

        }

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "UpdateCacheSystemConfiguration", String.format("service is finished,execute time is :%sms", logEnd - logStart));


    }

    /**
     * {@inheritDoc}
     *
     * @see DictionaryService#forceUpdateCacheDictionary(   )
     */
    @Transactional(readOnly = true)
    @Override
    public  void forceUpdateCacheSystemConfiguration( ) {
        try {

            logService.infoLog(logger, "service", "findSystemConfiguration", "service is started...");
            Long logStart = System.currentTimeMillis();

            logService.infoLog(logger, "repository", "findSystemConfiguration", "repository is started...");
            Long start = System.currentTimeMillis();

            updateCacheSystemConfiguration() ;

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findSystemConfiguration", String.format("service is finished,execute time is :%sms", logEnd - logStart));

        } catch (Exception ex) {
            logService.erorLog(logger, "service", "forceUpdateCacheSystemConfiguration", String.format("force cache Dictionary fail " ), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_SYSTEM_CONFIGURATION_FAIL);
        }
    }





    /**
     * {@inheritDoc}
     *
     * @see SystemConfigurationService#findSystemConfigurationMap( )
     */
    @Transactional(readOnly = true)
    @Override
    public  Map<String, SystemConfigurationBean> findSystemConfigurationMap() {
        try {
            logService.infoLog(logger, "cache", "getSystemConfigCache", "get all systemConfig cache start...");
            Map<String, SystemConfigurationBean> map = dictionaryCacheService.findAllSystemConfigCache();
            if (map==null){
                map=new HashMap<>();
            }
            return map;
        } catch (Exception ex) {
            logService.erorLog(logger, "cache", "getSystemConfigCache", "get all systemConfig cache fail.", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_SYSTEM_CONFIGURATION_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see SystemConfigurationService#findSystemConfiguration( )
     */
    @Transactional(readOnly = true)
    @Override
    public List<SystemConfigurationBean> findSystemConfiguration( ) {
        try {
            logService.infoLog(logger, "service", "findSystemConfiguration", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<SystemConfigurationBean> res = new ArrayList<>() ;
            Map<String, SystemConfigurationBean> systemConfigurationBeanMap = dictionaryCacheService.findAllSystemConfigCache();
            if( systemConfigurationBeanMap != null && systemConfigurationBeanMap.size() > 0 ){
                res = new ArrayList<>( systemConfigurationBeanMap.values() ) ;
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findSystemConfiguration", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findSystemConfiguration", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_SYSTEM_CONFIGURATION_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see #getSystemConfigByConfigType(String)
     */
    @Transactional(readOnly = true)
    @Override
    public SystemConfigurationBean getSystemConfigByConfigType(String configType) {
        try {
            logService.infoLog(logger, "service", "getSystemConfigByConfigName", "service is started...");
            Long logStart = System.currentTimeMillis();
            Map<String, SystemConfigurationBean> systemConfigurationBeanMap = dictionaryCacheService.findAllSystemConfigCache();
            if (systemConfigurationBeanMap==null){
                systemConfigurationBeanMap=new HashMap<>();
            }
            SystemConfigurationBean  res = systemConfigurationBeanMap.get( configType ) ;

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "getSystemConfigByConfigName", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;

        } catch (Exception ex) {
            logService.erorLog(logger, "service", "getSystemConfigByConfigName", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_SYSTEM_CONFIGURATION_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see #getSystemConfigByConfigTypeList(List)
     */
    @Transactional(readOnly = true)
    @Override
    public List<SystemConfigurationBean> getSystemConfigByConfigTypeList(List<String> configTypeList) {
        try {
            logService.infoLog(logger, "service", "getSystemConfigByConfigNameList", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<SystemConfigurationBean> res = new ArrayList<>();
            Map<String, SystemConfigurationBean> systemConfigurationBeanMap = dictionaryCacheService.findAllSystemConfigCache();
            if (systemConfigurationBeanMap==null){
                systemConfigurationBeanMap=new HashMap<>();
            }
            for ( String  configType : configTypeList) {
                SystemConfigurationBean systemConfigBO = systemConfigurationBeanMap.get( configType ) ;
                if (null != systemConfigBO) {
                    res.add(systemConfigBO);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "getSystemConfigByConfigNameList", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "getSystemConfigByConfigNameList", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_SYSTEM_CONFIGURATION_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see SystemConfigurationService#saveSystemConfiguration(SystemConfigurationSaveListInputInfo)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<SystemConfigurationBean> saveSystemConfiguration(SystemConfigurationSaveListInputInfo inputInfo) {
        if (inputInfo == null) {
            logService.infoLog(logger, "service", "saveSystemConfiguration", "SystemConfigurationSaveInputInfo is null.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveSystemConfiguration", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<SystemConfigurationBean> res = new ArrayList<>();

            List<SystemConfigurationEntity> systemConfigurationEntityList = new ArrayList<>();
            if (null != inputInfo.getSystemConfigurationSaveInputInfos() && inputInfo.getSystemConfigurationSaveInputInfos().size() > 0) {
                for (SystemConfigurationSaveInputInfo systemConfigurationSaveInputInfo : inputInfo.getSystemConfigurationSaveInputInfos()) {
                    SystemConfigurationEntity systemConfigurationEntity = SystemTransformUtil.transform(systemConfigurationSaveInputInfo);
                    systemConfigurationEntityList.add(systemConfigurationEntity);
                }
            }

            logService.infoLog(logger, "repository", "save(dbSystemConfigurationEntity)", "repository is started...");
            Long start = System.currentTimeMillis();

            accessor.save(systemConfigurationEntityList);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbSystemConfigurationEntity)", String.format("repository is finished,execute time is :%sms", end - start));

            if (null != systemConfigurationEntityList && systemConfigurationEntityList.size() > 0) {
                for (SystemConfigurationEntity systemConfigurationEntity : systemConfigurationEntityList) {
                    SystemConfigurationBean systemConfigurationBean = SystemTransformUtil.transform(systemConfigurationEntity);
                    //更新缓存数据;
                    dictionaryCacheService.modifySystemConfigCache(systemConfigurationBean.getConfigType() , systemConfigurationBean);
                    res.add(systemConfigurationBean);
                }
            }

            //发送websoket 通知全部在线用户
            //获得 机构信息
            Set<String> orgIds = new HashSet<>();
            Set<String> orgSetList = organizationService.findOrganizationIdSet();
            orgIds.addAll( orgSetList ) ;

            //机构编码
            List<String> orgCodes = organizationService.findOrganizationCodesByIds(new ArrayList<>(orgIds));
            orgIds.addAll(orgCodes);

            notifyActionService.pushMessageToDefaultSystemBusinessOrg( WebsocketCodeEnum.UPDATE_SYSTEM_CONFIGURATION.getCode() ,res ,orgIds );


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveSystemConfiguration", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;

        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveSystemConfiguration", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.SAVE_SYSTEM_CONFIGURATION_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see #enabledSystemConfig(String , Integer)
     */
    @Transactional(readOnly = true)
    @Override
    public SystemConfigurationBean enabledSystemConfig  (String systemConfigId , Integer  valid  ) {
        if(Strings.isBlank( systemConfigId ) || valid == null ){
            logService.infoLog(logger, "service", "enabledSystemConfig", "systemConfigId or valid is null.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "enabledSystemConfig", "service is started...");
            Long logStart = System.currentTimeMillis();

            SystemConfigurationBean res = null ;

            logService.infoLog(logger, "repository", "getById(dbSystemConfigurationEntity)", "repository is started...");
            Long start = System.currentTimeMillis();

            SystemConfigurationEntity systemConfigurationEntity  = accessor.getById(  systemConfigId , SystemConfigurationEntity.class );

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "getById(dbSystemConfigurationEntity", String.format("repository is finished,execute time is :%sms", end - start));

            if (null != systemConfigurationEntity ) {

                systemConfigurationEntity.setValid( valid );

                logService.infoLog(logger, "repository", "save(dbSystemConfigurationEntity)", "repository is started...");
                Long startSave = System.currentTimeMillis();

                accessor.save( systemConfigurationEntity ) ;

                Long endSave = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbSystemConfigurationEntity", String.format("repository is finished,execute time is :%sms", endSave - startSave));

                res = SystemTransformUtil.transform(systemConfigurationEntity);
                //更新缓存数据
                dictionaryCacheService.modifySystemConfigCache( res.getConfigType() , res ) ;

                //发送websoket 通知全部在线用户
                //获得 机构信息
                Set<String> orgIds = new HashSet<>();
                Set<String> orgSetList = organizationService.findOrganizationIdSet();
                orgIds.addAll( orgSetList ) ;

                //机构编码
                List<String> orgCodes = organizationService.findOrganizationCodesByIds(new ArrayList<>(orgIds));
                orgIds.addAll(orgCodes);

                notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.ENABLED_SYSTEM_CONFIGURATION.getCode(),res ,orgIds );

            }else{
                logService.infoLog(logger, "service", "enabledSystemConfig", "systemConfigurationEntity is null data ");
                throw new BasedataException(BasedataException.BasedataErrors.DATA_FAIL_NULL);
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "enabledSystemConfig", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;

        } catch (Exception ex) {
            logService.erorLog(logger, "service", "enabledSystemConfig", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.SAVE_SYSTEM_CONFIGURATION_FAIL);
        }
    }


}
