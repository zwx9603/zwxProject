package com.dscomm.iecs.basedata.service.impl;

import com.alibaba.fastjson.JSON;
import com.dscomm.iecs.base.service.bean.LocationRangeBean;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.base.utils.DistanceUtils;
import com.dscomm.iecs.basedata.dal.po.KeyUnitEntity;
import com.dscomm.iecs.basedata.dal.repository.BasedataNativeQueryRepository;
import com.dscomm.iecs.basedata.dal.repository.KeyUnitRepository;
import com.dscomm.iecs.basedata.exception.BasedataException;
import com.dscomm.iecs.basedata.graphql.inputbean.KeyUnitQueryInputInfo;
import com.dscomm.iecs.basedata.graphql.inputbean.KeyUnitSaveInputInfo;
import com.dscomm.iecs.basedata.graphql.typebean.KeyUnitBean;
import com.dscomm.iecs.basedata.graphql.typebean.KeyUnitSimpleBean;
import com.dscomm.iecs.basedata.graphql.typebean.SystemConfigurationBean;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.KeyUnitService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.basedata.service.SystemConfigurationService;
import com.dscomm.iecs.basedata.utils.KeyUnitTransformUtil;
import org.apache.logging.log4j.util.Strings;
import org.mx.StringUtils;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 描述：重点单位 服务类实现
 */
@Component("keyUnitServiceImpl")
public class KeyUnitServiceImpl implements KeyUnitService {
    private static final Logger logger = LoggerFactory.getLogger(KeyUnitServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private Environment env;
    private KeyUnitRepository keyUnitRepository;
    private DictionaryService dictionaryService;
    private OrganizationService organizationService;
    private SystemConfigurationService systemConfigurationService;
    private BasedataNativeQueryRepository basedataNativeQueryRepository ;

    //设置重点单位数据缓存数据 单位id 与 单位名称 缓存  单位id与 bean 的缓存
    private static Map<String, String> keyUnitNameMap = new HashMap<>();
    private Map<String, KeyUnitSimpleBean> keyUnitSimpleBeanMap = new HashMap<>();


    private List<String> dics;

    /**
     * 默认的构造函数
     */
    @Autowired
    public KeyUnitServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor,
                              Environment env, KeyUnitRepository keyUnitRepository, DictionaryService dictionaryService,
                              OrganizationService organizationService,SystemConfigurationService systemConfigurationService ,
                              BasedataNativeQueryRepository basedataNativeQueryRepository

    ) {
        this.logService = logService;
        this.accessor = accessor;
        this.env = env;
        this.keyUnitRepository = keyUnitRepository;
        this.dictionaryService = dictionaryService;
        this.organizationService = organizationService;
        this.systemConfigurationService = systemConfigurationService;
        this.basedataNativeQueryRepository = basedataNativeQueryRepository ;

        dics = new ArrayList<>(Arrays.asList( "DWDJ", "ZDDWLB", "ZDDWLX", "DWXZ", "XZQX" ,"JJSYZ" ,"HZWHXFL" ,"ZDDWFL" ) );
    }



    private Long lastTime  = 0l ; //系统默认为0 加载全部

    /**
     *  根据上次数据最新时间  本次数据最新时间
     *  判断是否从数据库更新数据 如果本次数据最新时间 大于 上次数据时间 则需要更新数据
     */
    private void updateCacheKeyUnit(){
        logService.infoLog(logger, "service", "pdateCacheKeyUnit", "service is started...");
        Long logStart = System.currentTimeMillis();

        Long latestTime = keyUnitRepository.findDataLatestTime( lastTime ) ;
        latestTime = latestTime == null ? lastTime : latestTime ;
        //判断是否需要更新数据
        if(  latestTime > lastTime ){

            logService.infoLog(logger, "service", "pdateCacheKeyUnit", " update cache data");

            //此处为增量更新数据
            List<String>  keyUnitIds     =  keyUnitRepository.findDataLatestTime( lastTime ,  latestTime   ) ;

            if(keyUnitIds != null && keyUnitIds.size() > 0 && keyUnitIds.size() <= 900 ){
                List<KeyUnitEntity> keyUnitEntities = keyUnitRepository.findOrganizationCache( keyUnitIds );
                if (null != keyUnitEntities && keyUnitEntities.size() > 0) {
                    for (KeyUnitEntity keyUnitEntity : keyUnitEntities) {
                        keyUnitNameMap.put(keyUnitEntity.getId(), keyUnitEntity.getUnitName());
                        if( keyUnitEntity.isValid() ){
                            KeyUnitSimpleBean keyUnitSimpleBean = KeyUnitTransformUtil.transform(keyUnitEntity    );
                            keyUnitSimpleBeanMap.put( keyUnitEntity.getId() , keyUnitSimpleBean ) ;
                        }else{
                            keyUnitSimpleBeanMap.remove( keyUnitEntity.getId()   ) ;
                        }
                    }
                }
            }else if(  keyUnitIds != null && keyUnitIds.size()  > 900 ){
                int page = ( int ) Math.ceil( keyUnitIds.size() / 900.0 ) ;
                for( int i = 0 ; i < page ; i++ ){
                    int startnum = i * 900 ;
                    int endnum = ( i + 1 ) * 900 ;
                    if( endnum > keyUnitIds.size() ){
                        endnum = keyUnitIds.size() ;
                    }
                    List<String>  batchIds = keyUnitIds.subList( startnum , endnum ) ;
                    List<KeyUnitEntity> keyUnitEntities = keyUnitRepository.findOrganizationCache( batchIds );
                    if (null != keyUnitEntities && keyUnitEntities.size() > 0) {
                        for (KeyUnitEntity keyUnitEntity : keyUnitEntities) {
                            keyUnitNameMap.put(keyUnitEntity.getId(), keyUnitEntity.getUnitName());
                            if( keyUnitEntity.isValid() ){
                                KeyUnitSimpleBean keyUnitSimpleBean = KeyUnitTransformUtil.transform(keyUnitEntity    );
                                keyUnitSimpleBeanMap.put( keyUnitEntity.getId() , keyUnitSimpleBean ) ;
                            }else{
                                keyUnitSimpleBeanMap.remove( keyUnitEntity.getId()   ) ;
                            }
                        }
                    }
                }
            }

            lastTime = latestTime ;
        }

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "pdateCacheKeyUnit", String.format("service is finished,execute time is :%sms", logEnd - logStart));


    }




    /**
     * {@inheritDoc}
     *
     * @see KeyUnitService#forceUpdateCacheKeyUnit()
     */
    @Transactional(readOnly = true)
    @Override
    public void forceUpdateCacheKeyUnit() {
        try {
            logService.infoLog(logger, "service", "forceUpdateCacheKeyUnit", "service is started...");
            Long logStart = System.currentTimeMillis();

            updateCacheKeyUnit();

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "forceUpdateCacheKeyUnit", String.format("service is finished,execute time is :%sms", logEnd - logStart));

        } catch (Exception ex) {
            logService.erorLog(logger, "service", "forceUpdateCacheKeyUnit", String.format("force cache Dictionary fail "), ex);
            throw new BasedataException(BasedataException.BasedataErrors.UPDATE_CACHE_KEY_UNIT_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see   #findKeyUnitSimple( String )
     */
    @Transactional(readOnly = true)
    @Override
    public KeyUnitSimpleBean findKeyUnitSimple(  String keyUnitId ) {
        try {

            return keyUnitSimpleBeanMap.get( keyUnitId );

        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findKeyUnitSimple", String.format("find cache Dictionary fail "), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_KEY_UNIT_NAME_MAP_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see   #findKeyUnitSimple( String )
     */
    @Transactional(readOnly = true)
    @Override
    public  Map<String,KeyUnitSimpleBean>  findKeyUnitSimpleIds(  List<String> keyUnitIds )  {
        try {

            Map<String,KeyUnitSimpleBean> keyUnitSimpleMap = new HashMap<>();
            if( keyUnitIds != null && keyUnitIds.size() > 0 ){
                for( String keyUnitId : keyUnitIds ){
                    KeyUnitSimpleBean  keyUnitSimpleBean =  keyUnitSimpleBeanMap.get( keyUnitId ) ;
                    if( keyUnitSimpleBean != null ){
                        keyUnitSimpleMap.put( keyUnitId , keyUnitSimpleBean  ) ;
                    }
                }
            }
            return keyUnitSimpleMap ;

        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findKeyUnitSimple", String.format("find cache Dictionary fail "), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_KEY_UNIT_NAME_MAP_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see   #findKeyUnitSimple( String )
     */
    @Transactional(readOnly = true)
    @Override
    public Map<String,String>  findKeyUnitIdNameMap(  List<String> keyUnitIds )  {
        try {

            Map<String,String> keyUnitIdNameMap = new HashMap<>();
            if( keyUnitIds != null && keyUnitIds.size() > 0 ){
                for( String keyUnitId : keyUnitIds ){
                    keyUnitIdNameMap.put( keyUnitId , keyUnitNameMap.get( keyUnitId )  ) ;
                }
            }
            return keyUnitIdNameMap ;

        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findKeyUnitSimple", String.format("find cache Dictionary fail "), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_KEY_UNIT_NAME_MAP_FAIL);
        }
    }




    /**
     * {@inheritDoc}
     *
     * @see KeyUnitService#findKeyUnitByPhone(String)
     */
    @Transactional(readOnly = true)
    @Override
    public KeyUnitBean findKeyUnitByPhone(String unitPhone) {
        if (Strings.isBlank(unitPhone)) {
            logService.infoLog(logger, "service", "findKeyUnitByPhone", "unitPhone is null.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findKeyUnitByPhone", "service is started...");
            Long logStart = System.currentTimeMillis();

            KeyUnitBean res = null ;



            //获得字冠配置信息
            String ziguanhead = "" ;
            SystemConfigurationBean ziguan = systemConfigurationService.getSystemConfigByConfigType("ziguan") ;
            if( ziguan != null ){
                ziguanhead = ziguan.getConfigValue();
            }
            //判断第二个模糊匹配电话
            String unitPhoneLike = unitPhone ;
            if( Strings.isNotBlank( ziguanhead )){
                if( unitPhone.startsWith( ziguanhead ) ){
                    unitPhoneLike = unitPhone.substring( unitPhone.indexOf( ziguanhead) + 1  );
                }else {
                    unitPhoneLike = ziguanhead + unitPhone ;
                }
            }

            Map<String, Map<String, String>> dicsMap  = dictionaryService.findDictionaryMap(dics) ;

            logService.infoLog(logger, "repository", "findKeyUnitByPhone(unitPhone)", "repository is started...");
            Long start = System.currentTimeMillis();

            List<KeyUnitEntity> keyUnitList = keyUnitRepository.findKeyUnitByPhone(    unitPhone  ,   unitPhoneLike );

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findKeyUnitByPhone(unitPhone)", String.format("repository is finished,execute time is :%sms", end - start));

            if (null != keyUnitList && keyUnitList.size() > 0) {
                res = KeyUnitTransformUtil.transform(keyUnitList.get(0), dicsMap, organizationService.findOrganizationNameMap());
            }


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findKeyUnitByPhone", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findKeyUnitByPhone", String.format(" find keyUnit by unitPhone  : %s.", JSON.toJSONString(unitPhone)), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_KEY_UNIT_FAIL);
        }

    }

    /**
     * {@inheritDoc}
     *
     * @see KeyUnitService#findKeyUnitByPhone(String)
     */
    @Transactional(readOnly = true)
    @Override
    public List<KeyUnitSimpleBean> findKeyUnitAuthenticate ( String organizationId ) {
        try {

            List<KeyUnitSimpleBean> res = new ArrayList<>();

            if (!StringUtils.isBlank(organizationId)){
                List<String> orgIds = organizationService.findChildOrganizationId(organizationId); //输入的机构以及下级机构

                for (KeyUnitSimpleBean keyUnitSimpleBean : keyUnitSimpleBeanMap.values()){
                    if( Strings.isBlank( keyUnitSimpleBean.getOrganizationId() ) ){
                        res.add(keyUnitSimpleBean);
                    }else if (   orgIds.contains(keyUnitSimpleBean.getOrganizationId())){
                        res.add(keyUnitSimpleBean);
                    }
                }
            }
            res.sort( ( a , b ) -> a.getId().compareTo( b.getId() ) );

            return  res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findKeyUnitAll", String.format(" find all keyUnit  %s.", ""), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_KEY_UNIT_FAIL);
        }

    }

    /**
     * {@inheritDoc}
     *
     * @see KeyUnitService#findKeyUnitCondition(KeyUnitQueryInputInfo)
     */
    @Transactional(readOnly = true)
    @Override
    public List<KeyUnitBean> findKeyUnitCondition(KeyUnitQueryInputInfo queryBean) {
        if (queryBean == null) {
            logService.infoLog(logger, "service", "findKeyUnitCondition", "unitPhone is null.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findKeyUnitCondition", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<KeyUnitBean> res = new ArrayList<>();

            String searchPath = null;
            Map<String, Map<String, String>> dicsMap  = dictionaryService.findDictionaryMap(dics) ;
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();

            //获取机构查询码
            if (!StringUtils.isBlank(queryBean.getOrganizationId())){
                logService.infoLog(logger, "service", "findSearchPathById", "service is started...");
                Long orgStart = System.currentTimeMillis();

                searchPath = organizationService.findSearchPathById(queryBean.getOrganizationId());

                Long orgEnd = System.currentTimeMillis();
                logService.infoLog(logger, "service", "findSearchPathById", String.format("service is finished,execute time is :%sms", orgEnd - orgStart));

            }

            logService.infoLog(logger, "repository", "findKeyUnitCondition( )", "repository is started...");
            Long start = System.currentTimeMillis();

            List<KeyUnitEntity> keyUnitList = keyUnitRepository.findKeyUnitCondition( queryBean.getUnitAddress(),searchPath) ;

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findKeyUnitCondition( )", String.format("repository is finished,execute time is :%sms", end - start));


            if (null != keyUnitList && keyUnitList.size() > 0) {
                for (KeyUnitEntity keyUnitEntity : keyUnitList) {
                    KeyUnitBean keyUnitBean = KeyUnitTransformUtil.transform(keyUnitEntity, dicsMap, organizationNameMap);
                    res.add(keyUnitBean);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findKeyUnitCondition", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findKeyUnitCondition", String.format(" find keyUnit by condition  fail  : %s.", JSON.toJSONString(queryBean)), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_KEY_UNIT_FAIL);
        }

    }

    /**
     * {@inheritDoc}
     *
     * @see KeyUnitService#findKeyUnitById(String)
     */
    @Transactional(readOnly = true)
    @Override
    public KeyUnitBean findKeyUnitById(String keyUnitId) {
        if (Strings.isBlank(keyUnitId)) {
            logService.infoLog(logger, "service", "findKeyUnitById", "keyUnitId is null.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findKeyUnitById", "service is started...");
            Long logStart = System.currentTimeMillis();

            KeyUnitBean res = null ;

            Map<String, Map<String, String>> dicsMap  = dictionaryService.findDictionaryMap(dics) ;

            KeyUnitEntity keyUnitEntity = accessor.getById(keyUnitId, KeyUnitEntity.class);
            if (null != keyUnitEntity) {
                res = KeyUnitTransformUtil.transform(keyUnitEntity, dicsMap, organizationService.findOrganizationNameMap());
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findKeyUnitById", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findKeyUnitById", String.format(" find keyUnit fail by keyUnitId : %s.", JSON.toJSONString(keyUnitId)), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_KEY_UNIT_FAIL);
        }

    }




    /**
     * {@inheritDoc}
     *
     * @see KeyUnitService#findKeyUnitBeanMap( List)
     */
    @Transactional(readOnly = true)
    @Override
    public  Map<String, KeyUnitBean> findKeyUnitBeanMap( List< String> keyUnitIds) {
        if ( keyUnitIds == null || keyUnitIds.size() < 1 ) {
            logService.infoLog(logger, "service", "findKeyUnitBeanMap", "keyUnitId is null.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findKeyUnitBeanMap", "service is started...");
            Long logStart = System.currentTimeMillis();

            Map<String, KeyUnitBean> keyUnitBeanMap = new HashMap<>();

            logService.infoLog(logger, "repository", "findOrganizationCache", "repository is started...");
            Long start = System.currentTimeMillis();

            List<KeyUnitEntity> keyUnitEntities = keyUnitRepository.findOrganizationByKeyUnitIds(keyUnitIds);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findOrganizationCache", String.format("repository is finished,execute time is :%sms", end - start));

            Map<String, Map<String, String>> dicsMap  = dictionaryService.findDictionaryMap(dics) ;
            Map<String, String> organizationNameMap  =  organizationService.findOrganizationNameMap() ;
            if (null != keyUnitEntities && keyUnitEntities.size() > 0) {
                for (KeyUnitEntity keyUnitEntity : keyUnitEntities) {
                    KeyUnitBean keyUnitBean = KeyUnitTransformUtil.transform( keyUnitEntity , dicsMap,organizationNameMap );
                    keyUnitBeanMap.put( keyUnitBean.getId() , keyUnitBean  ) ;
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findKeyUnitBeanMap", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return keyUnitBeanMap  ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findKeyUnitNameMap", String.format("find cache Dictionary fail "), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_KEY_UNIT_NAME_MAP_FAIL);
        }
    }



    /**
     * {@inheritDoc}
     *
     * @see KeyUnitService#findKeyUnitByPlanId(String)
     */
    @Transactional(readOnly = true)
    @Override
    public KeyUnitBean findKeyUnitByPlanId(String planId) {
        if (Strings.isBlank(planId)) {
            logService.infoLog(logger, "service", "findKeyUnitByPlanId", "planId is null.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findKeyUnitByPlanId", "service is started...");
            Long logStart = System.currentTimeMillis();

            KeyUnitBean res = null ;

            Map<String, Map<String, String>> dicsMap  = dictionaryService.findDictionaryMap(dics) ;

            logService.infoLog(logger, "repository", "findKeyUnitByPlanId( planId )", "repository is started...");
            Long start = System.currentTimeMillis();

            KeyUnitEntity keyUnitEntity = keyUnitRepository.findKeyUnitByPlanId(planId);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findKeyUnitByPlanId( planId )", String.format("repository is finished,execute time is :%sms", end - start));

            if (null != keyUnitEntity) {
                res = KeyUnitTransformUtil.transform(keyUnitEntity, dicsMap, organizationService.findOrganizationNameMap());
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findKeyUnitByPlanId", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findKeyUnitByPlanId", String.format(" find keyUnit fail by planId  : %s.", JSON.toJSONString(planId)), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_KEY_UNIT_FAIL);
        }

    }

    /**
     * {@inheritDoc}
     *
     * @see KeyUnitService#findKeyUnitIncidentRange(String , String )
     */
    @Transactional(readOnly = true)
    @Override
    public List<String> findKeyUnitIncidentRange(  String longitude , String latitude ) {
        if (Strings.isBlank(longitude) || Strings.isBlank( latitude ) ) {
            logService.infoLog(logger, "service", "findKeyUnitIncidentRange", "longitude or latitude is null.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findKeyUnitIncidentRange", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<String> res = new ArrayList<>();

            String radius = "1000";
            SystemConfigurationBean controlCircleConfig = systemConfigurationService.getSystemConfigByConfigType("controlCircle");
            if(controlCircleConfig != null){
                radius = controlCircleConfig.getConfigValue();
            }

            logService.infoLog(logger, "repository", "findKeyUnitIncidentRange(longitude,latitude,radius)", "repository is started...");
            Long start = System.currentTimeMillis();

            LocationRangeBean locationRangeBean = DistanceUtils.buildLocationRange( Double.parseDouble(longitude  ) , Double.parseDouble ( latitude )  , Integer.parseInt( radius ) ) ;


            res = basedataNativeQueryRepository.findKeyUnitIncidentRange( locationRangeBean.getMinLng()    ,   locationRangeBean.getMaxLng()    ,
                    locationRangeBean.getMinLat()   ,  locationRangeBean.getMaxLat()     );

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findKeyUnitIncidentRange(longitude,latitude,radius)", String.format("repository is finished,execute time is :%sms", end - start));


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findKeyUnitIncidentRange", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findKeyUnitIncidentRange", String.format(" find KeyUnit id list in longitude latitude range  fail   ", "" ), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_KEY_UNIT_FAIL);
        }

    }


    /**
     * 保存重点单位
     * @return 重点单位bean
     * */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public KeyUnitBean saveKeyUnit(KeyUnitSaveInputInfo inputInfo) {
        if (inputInfo == null || StringUtils.isBlank(inputInfo.getUnitName()) || StringUtils.isBlank(inputInfo.getJurisdictionOrganizationId())){
            logService.infoLog(logger, "service", "saveKeyUnit", "inputInfo is blank.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveKeyUnit", "service is started...");
            Long logStart = System.currentTimeMillis();

            KeyUnitBean res = null ;

            KeyUnitEntity keyUnitEntity = KeyUnitTransformUtil.transform(inputInfo);
            if( keyUnitEntity != null ){
                //保存
                Map<String, Map<String, String>> dicsMap  = dictionaryService.findDictionaryMap(dics) ;
                Map<String, String> organizationNameMap  =  organizationService.findOrganizationNameMap() ;

                accessor.save(keyUnitEntity);
                keyUnitEntity.setIdCode(keyUnitEntity.getId());
                accessor.save(keyUnitEntity);

                res = KeyUnitTransformUtil.transform( keyUnitEntity ,dicsMap,organizationNameMap);
                KeyUnitSimpleBean keyUnitSimpleBean = KeyUnitTransformUtil.transform(  keyUnitEntity    );
                //更新缓存信息
                if( res != null ){
                    keyUnitSimpleBeanMap.put( keyUnitSimpleBean.getId() , keyUnitSimpleBean );
                    keyUnitNameMap.put( keyUnitSimpleBean.getId() ,  keyUnitSimpleBean.getUnitName()  ) ;
                }

            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveKeyUnit", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveKeyUnit", " saveKeyUnit  fail ", ex);
            throw new BasedataException(BasedataException.BasedataErrors.SAVE_KEYUNIT_FAIL);
        }

    }


    /**删除重点单位*/
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean deleteKeyUnit(List<String> keyUnitIds ) {
        if ( keyUnitIds == null || keyUnitIds.size() < 1 ){
            logService.infoLog(logger, "service", "deleteKeyUnit", "inputInfo is blank.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "deleteKeyUnit", "service is started...");
            Long logStart = System.currentTimeMillis();

            Boolean res = true ;

            logService.infoLog(logger, "repository", "findOrganizationByKeyUnitIds", "repository is started...");
            Long start = System.currentTimeMillis();

            List<KeyUnitEntity> keyUnitList = keyUnitRepository.findOrganizationByKeyUnitIds( keyUnitIds );

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findOrganizationByKeyUnitIds", String.format("repository is finished,execute time is :%sms", end - start));

            if (keyUnitList != null && keyUnitList.size()>0){

                accessor.remove(keyUnitList,true);

                for(String keyUnitId : keyUnitIds){
                    keyUnitSimpleBeanMap.remove( keyUnitId );
                    keyUnitNameMap.remove( keyUnitId ) ;
                }

                res = true;
            }


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "deleteKeyUnit", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "deleteKeyUnit", " saveKeyUnit  fail ", ex);
            throw new BasedataException(BasedataException.BasedataErrors.SAVE_KEYUNIT_FAIL);
        }

    }


}
