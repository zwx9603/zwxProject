package com.dscomm.iecs.basedata.service.impl;


import com.dscomm.iecs.base.service.bean.LocationRangeBean;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.base.utils.DistanceUtils;
import com.dscomm.iecs.basedata.dal.po.*;
import com.dscomm.iecs.basedata.dal.repository.BasedataNativeQueryRepository;
import com.dscomm.iecs.basedata.dal.repository.OrganizationOtherRepository;
import com.dscomm.iecs.basedata.exception.BasedataException;
import com.dscomm.iecs.basedata.graphql.typebean.*;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.OrganizationOtherService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.basedata.service.SystemConfigurationService;
import com.dscomm.iecs.basedata.utils.OrganizationTransformUtil;
import com.dscomm.iecs.keydata.service.ServletService;
import org.apache.logging.log4j.util.Strings;
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
 * 描述：机构其他服务类  包含 机构外部单位信息  机构地方专职队/微型消防站 机构间的毗邻关系
 */
@Component("organizationOtherServiceImpl")
public class OrganizationOtherServiceImpl implements OrganizationOtherService {

    private static final Logger logger = LoggerFactory.getLogger(OrganizationOtherServiceImpl.class);
    private Environment env;
    private LogService logService;
    private GeneralAccessor accessor;
    private OrganizationService organizationService ;
    private OrganizationOtherRepository organizationOtherRepository ;
    private DictionaryService dictionaryService ;
    private SystemConfigurationService systemConfigurationService;
    private ServletService servletService;
    private BasedataNativeQueryRepository basedataNativeQueryRepository;
    private static Map<String, List<OrganizationAdjacentPriorityBean>> organizationAdjacentPriorityMap = new HashMap<>(); //车辆 与 装载装备 列表 关系

    private List<String> dics;



    /**
     * 默认的构造函数
     */
    @Autowired
    public OrganizationOtherServiceImpl(Environment env, @Qualifier("generalAccessor") GeneralAccessor accessor, LogService logService ,
                                        OrganizationService organizationService , OrganizationOtherRepository organizationOtherRepository ,
                                        DictionaryService dictionaryService,SystemConfigurationService systemConfigurationService,ServletService servletService,
                                        BasedataNativeQueryRepository basedataNativeQueryRepository

    ) {
        this.accessor = accessor;
        this.env = env;
        this.logService = logService;
        this.organizationService = organizationService ;
        this.organizationOtherRepository = organizationOtherRepository ;
        this.dictionaryService = dictionaryService ;
        this.systemConfigurationService=systemConfigurationService;
        this.servletService=servletService;
        this.basedataNativeQueryRepository=basedataNativeQueryRepository;

        dics = new ArrayList<>(Arrays.asList("LQBZLB", "YYDJ", "XZQX", "YJLDDWLBD","JGLB","JGLX","JGXZ","CLLX"));



    }



    /**
     * {@inheritDoc}
     *
     * @see OrganizationOtherService#findOrganizationLocalFullTimeUnitByOrganizationId( String )
     */
    @Transactional(readOnly = true)
    @Override
    public List<OrganizationLocalFullTimeUnitBean> findOrganizationLocalFullTimeUnitByOrganizationId (String organizationId) {
        if ( Strings.isBlank( organizationId ) ) {
            logService.infoLog(logger, "service", "findOrganizationLocalFullTimeUnitByOrganizationId", "organizationId is null.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }

        try {
            logService.infoLog(logger, "service", "findOrganizationLocalFullTimeUnitByOrganizationId", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<OrganizationLocalFullTimeUnitBean> res = new ArrayList() ;

            logService.infoLog(logger, "repository", "findOrganizationLocalFullTimeUnitByOrganizationId(organizationId)", "repository is started...");
            Long start = System.currentTimeMillis();

            List<OrganizationLocalFullTimeUnitEntity>   organizationLocalFullTimeUnitEntityList  = organizationOtherRepository.findOrganizationLocalFullTimeUnitByOrganizationId( organizationId ) ;

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findOrganizationLocalFullTimeUnitByOrganizationId(organizationId)", String.format("repository is finished,execute time is :%sms", end - start));

            if( null != organizationLocalFullTimeUnitEntityList && organizationLocalFullTimeUnitEntityList.size() > 0 ){
                for( OrganizationLocalFullTimeUnitEntity organizationLocalFullTimeUnitEntity :  organizationLocalFullTimeUnitEntityList ){
                    OrganizationLocalFullTimeUnitBean  organizationLocalFullTimeUnitBean  = OrganizationTransformUtil.transform( organizationLocalFullTimeUnitEntity );
                    res.add( organizationLocalFullTimeUnitBean );
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findOrganizationLocalFullTimeUnitByOrganizationId", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return  res ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findOrganizationLocalFullTimeUnitByOrganizationId", String.format("find  OrganizationLocalFullTimeUnit  fail by id : %s.", organizationId ), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_LOCAL_FULLTIME_ORGANIZATION_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see OrganizationOtherService#findOrganizationLocalFullTimeUnitById( String )
     */
    @Transactional(readOnly = true)
    @Override
    public OrganizationLocalFullTimeUnitBean findOrganizationLocalFullTimeUnitById (String organizationFullTimeUnitId) {
        if ( Strings.isBlank( organizationFullTimeUnitId ) ) {
            logService.infoLog(logger, "service", "findOrganizationLocalFullTimeUnitById", "organizationFullTimeUnitId is null.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }

        try {
            logService.infoLog(logger, "service", "findOrganizationLocalFullTimeUnitById", "service is started...");
            Long logStart = System.currentTimeMillis();

            OrganizationLocalFullTimeUnitBean organizationLocalFullTimeUnitBean = new OrganizationLocalFullTimeUnitBean() ;

            OrganizationLocalFullTimeUnitEntity organizationLocalFullTimeUnitEntity = accessor.getById( organizationFullTimeUnitId ,OrganizationLocalFullTimeUnitEntity.class ) ;

            if ( null != organizationLocalFullTimeUnitEntity ) {
                organizationLocalFullTimeUnitBean  = OrganizationTransformUtil.transform( organizationLocalFullTimeUnitEntity );
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findOrganizationLocalFullTimeUnitById", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return  organizationLocalFullTimeUnitBean ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findOrganizationLocalFullTimeUnitById", String.format("find  OrganizationLocalFullTimeUnit  fail by id : %s.", organizationFullTimeUnitId ), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_LOCAL_FULLTIME_ORGANIZATION_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see #buildAdjacentOrganizationCache(     )
     */
    @Transactional(  rollbackFor =  Exception.class    )
    @Override
    public   void buildAdjacentOrganizationCache  (     ) {
        try {
            logService.infoLog(logger, "service", "buildAdjacentOrganization", "service is started...");
            Long logStart = System.currentTimeMillis();

            buildAdjacentOrganization  (   ) ;

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "buildAdjacentOrganization", String.format("service is finished,execute time is :%sms", logEnd - logStart));

        } catch (Exception ex) {
            logService.erorLog(logger, "service", "buildAdjacentOrganization", String.format("execution error", "" ), ex);
            throw new BasedataException(BasedataException.BasedataErrors.BUILD_ORGANIZATION_ADJACENT_PRIORITY_FAIL);
        }
    }


    private Long lastTime  = 0l ; //系统默认为0 加载全部
    /**
     *  根据机构信息  保存毗邻机构信息
     */
    @Transactional(  rollbackFor =  Exception.class    )
    @Override
    public    void  buildAdjacentOrganization  (   ){
        logService.infoLog(logger, "service", "UpdateCacheDictionary", "service is started...");
        Long logStart = System.currentTimeMillis();

        Long latestTime = organizationOtherRepository.findDataLatestTimeOrg( lastTime ) ;
        latestTime = latestTime == null ? lastTime : latestTime ;
        //判断是否需要更新数据
        if( latestTime > lastTime ){

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "UpdateCacheSystemConfiguration", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            //判断是否需要更新数据
            //删除历史毗邻机构数据
            accessor.clear( OrganizationAdjacentPriorityEntity.class );

            //全部有效机构数据
            Map<String, OrganizationBean> organizationBeanMap = organizationService.findOrganizationAll();
            List<OrganizationBean> organizationBeanList = new ArrayList<>( organizationBeanMap.values() ) ;

            List<OrganizationAdjacentPriorityEntity>  organizationAdjacentPriorityEntityList = new ArrayList<>() ;

            for ( int i = 0 ; i < organizationBeanList.size() ; i ++){
                OrganizationBean charge = organizationBeanList.get(i) ;
                double longitude1 = Strings.isBlank( charge.getLongitude() ) ? 0.0d : Double.parseDouble( charge.getLongitude() ) ;
                double latitude1  = Strings.isBlank( charge.getLatitude() ) ? 0.0d : Double.parseDouble( charge.getLatitude() ) ;


                for( int j = 0 ; j < organizationBeanList.size() ; j ++ ){
                    if( j == i ){
                        continue ;
                    }

                    OrganizationBean adjacent = organizationBeanList.get(j) ;

                    // 根据机构的经纬度 信息 计算距离
                    OrganizationAdjacentPriorityEntity organizationAdjacentPriorityEntity = new OrganizationAdjacentPriorityEntity() ;

                    organizationAdjacentPriorityEntity.setChargeOrganizationId( charge.getId() );
                    organizationAdjacentPriorityEntity.setAdjacentOrganizationId( adjacent.getId() );

                    double longitude2 = Strings.isBlank( adjacent.getLongitude() ) ? 0.0d : Double.parseDouble( adjacent.getLongitude() ) ;
                    double latitude2  = Strings.isBlank( adjacent.getLatitude() ) ? 0.0d : Double.parseDouble( adjacent.getLatitude() ) ;
                    double distance = DistanceUtils.getDistance( longitude1 , latitude1 , longitude2 , latitude2 ) ;
                    organizationAdjacentPriorityEntity.setPriority( distance );
                    organizationAdjacentPriorityEntity.setAdjacentLevel( adjacent.getOrganizationCategoryCode() );
                    organizationAdjacentPriorityEntity.setOrganizationId( charge.getId() );
                    organizationAdjacentPriorityEntity.setRemarks( null );


                    organizationAdjacentPriorityEntityList.add( organizationAdjacentPriorityEntity ) ;
                }
            }
            //保存
            accessor.save(organizationAdjacentPriorityEntityList);


            List<OrganizationAdjacentPriorityBean> organizationAdjacentPriorityBeanList = new ArrayList<>() ;

            if( organizationAdjacentPriorityEntityList != null && organizationAdjacentPriorityEntityList.size() > 0 ){

                Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();
                for(  OrganizationAdjacentPriorityEntity organizationAdjacentPriorityEntity :  organizationAdjacentPriorityEntityList ){
                    OrganizationAdjacentPriorityBean bean = OrganizationTransformUtil.transform( organizationAdjacentPriorityEntity , organizationNameMap ) ;
                    organizationAdjacentPriorityBeanList.add( bean ) ;
                }
            }

            if( organizationAdjacentPriorityBeanList != null && organizationAdjacentPriorityBeanList.size() > 0 ){
                //清除缓存数据
                organizationAdjacentPriorityMap.clear();

                Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();
                for(  OrganizationAdjacentPriorityBean bean  :  organizationAdjacentPriorityBeanList ){
                    List<OrganizationAdjacentPriorityBean>  organizationAdjacentPriorityBeans = organizationAdjacentPriorityMap.get( bean.getChargeOrganizationId()  );
                    if( organizationAdjacentPriorityBeans == null ){
                        organizationAdjacentPriorityBeans = new ArrayList<>() ;
                    }
                    organizationAdjacentPriorityBeans.add( bean ) ;
                    organizationAdjacentPriorityMap.put( bean.getChargeOrganizationId() , organizationAdjacentPriorityBeans);
                }
            }

            for( String  key : organizationAdjacentPriorityMap.keySet() ){
                List<OrganizationAdjacentPriorityBean> source = organizationAdjacentPriorityMap.get( key ) ;
                //装配机构id - 毗邻机构列表 map缓存
                source.sort(new Comparator<OrganizationAdjacentPriorityBean>() {
                    @Override
                    public int compare(OrganizationAdjacentPriorityBean o1, OrganizationAdjacentPriorityBean o2) {
                        Double d1 = o1.getPriority();
                        Double d2 = o2.getPriority();
                        return d1.compareTo(d2);
                    }
                });

            }

            lastTime = latestTime ;
        }

    }






    private String build ( String id ){
        StringBuffer buffer = new StringBuffer();
        int n = id.length();
        for(int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                buffer.append(id.charAt(i));
            }
        }
        return buffer.toString();
    }


    /**
     * {@inheritDoc}
     *
     * @see OrganizationOtherService#findAdjacentOrganizationByChargeOrganizationId( String )
     */
    @Transactional(readOnly = true)
    @Override
    public List<OrganizationAdjacentPriorityBean> findAdjacentOrganizationByChargeOrganizationId (String chargeOrganizationId){
        if ( Strings.isBlank( chargeOrganizationId ) ) {
            logService.infoLog(logger, "service", "findAdjacentOrganizationByChargeOrganizationId", "chargeOrganizationId is null.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findAdjacentOrganizationByChargeOrganizationId", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<OrganizationAdjacentPriorityBean> res = organizationAdjacentPriorityMap.get(chargeOrganizationId);
            if (res == null){
                res = new ArrayList<>();
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findAdjacentOrganizationByChargeOrganizationId", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return  res ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findAdjacentOrganizationByChargeOrganizationId", String.format("find  OrganizationAdjacentPriority  fail by id : %s.", chargeOrganizationId ), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_ORGANIZATION_ADJACENT_PRIORITY_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see #findUnitJointService( String )
     */
    @Transactional(readOnly = true)
    @Override
    public UnitJointServiceBean findUnitJointService (String  unitJointServiceId ){
        if ( Strings.isBlank( unitJointServiceId ) ) {
            logService.infoLog(logger, "service", "findUnitJointService", "chargeOrganizationId is null.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findUnitJointService", "service is started...");
            Long logStart = System.currentTimeMillis();

            UnitJointServiceBean res = null ;

            logService.infoLog(logger, "repository", "getById(dbUnitJointServiceEntity)", "repository is started...");
            Long startBuild = System.currentTimeMillis();

            UnitJointServiceEntity unitJointServiceEntity = accessor.getById( unitJointServiceId , UnitJointServiceEntity.class ) ;

            Long endBuild = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "getById(dbUnitJointServiceEntity)", String.format("repository is finished,execute time is :%sms", endBuild - startBuild));

            if( unitJointServiceEntity != null && unitJointServiceEntity.isValid() ){
                //字典数据
                Map<String, Map<String, String>> dicsMap  = dictionaryService.findDictionaryMap(dics) ;
                //机构数据
                Map<String, String> organizationNameMap =  organizationService.findOrganizationNameMap() ;

                res = OrganizationTransformUtil.transform( unitJointServiceEntity ,dicsMap , organizationNameMap ) ;
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findUnitJointService", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return  res ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findUnitJointService", String.format("find   UnitJointService  fail by id : %s.", unitJointServiceId ), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_DATA_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see #findUnitEmergency( String )
     */
    @Transactional(readOnly = true)
    @Override
    public UnitEmergencyBean findUnitEmergency(String  unitEmergencyId  ){
        if ( Strings.isBlank( unitEmergencyId ) ) {
            logService.infoLog(logger, "service", "findUnitEmergency", "unitEmergencyId is null.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findUnitEmergency", "service is started...");
            Long logStart = System.currentTimeMillis();

            UnitEmergencyBean res = null ;

            logService.infoLog(logger, "repository", "getById(dbUnitEmergencyEntity)", "repository is started...");
            Long startBuild = System.currentTimeMillis();

            UnitEmergencyEntity unitEmergencyEntity = accessor.getById( unitEmergencyId , UnitEmergencyEntity.class ) ;

            Long endBuild = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "getById(dbUnitEmergencyEntity)", String.format("repository is finished,execute time is :%sms", endBuild - startBuild));

            if( unitEmergencyEntity != null && unitEmergencyEntity.isValid() ){
                //字典数据
                Map<String, Map<String, String>> dicsMap  = dictionaryService.findDictionaryMap(dics) ;
                //机构数据
                Map<String, String> organizationNameMap  =  organizationService.findOrganizationNameMap() ;
                res = OrganizationTransformUtil.transform( unitEmergencyEntity ,dicsMap , organizationNameMap ) ;
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findUnitEmergency", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return  res ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findUnitEmergency", String.format("find   unitEmergency  fail by id : %s.", unitEmergencyId ), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_DATA_FAIL);
        }
    }


    @Transactional(readOnly = true)
    @Override
    public List<UnitEmergencyBean> findUnitEmergencyRange(String longitude, String latitude, String radius) {
        if (Strings.isBlank(longitude) || Strings.isBlank( latitude ) ) {
            logService.infoLog(logger, "service", "findUnitEmergencyRange", "longitude or latitude is blank.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findUnitEmergencyRange", "service is started...");

            Long logStart = System.currentTimeMillis();

            List<UnitEmergencyBean> res = new ArrayList<>();

            //字典数据
            Map<String, Map<String, String>> dicsMap  = dictionaryService.findDictionaryMap(dics) ;
            //机构数据
            Map<String, String> organizationNameMap  =  organizationService.findOrganizationNameMap() ;



            logService.infoLog(logger, "repository", "findUnitEmergencyRange", "repository is started...");

            Long start = System.currentTimeMillis();

            //根据坐标查范围内应急联动单位
            LocationRangeBean locationRangeBean = DistanceUtils.buildLocationRange( Double.parseDouble(longitude  ) , Double.parseDouble ( latitude )  , Integer.parseInt( radius ) ) ;

            List<String> unitEmergencyIdList = basedataNativeQueryRepository.findUnitEmergencyRange(
                    locationRangeBean.getMinLng() , locationRangeBean.getMaxLng() , locationRangeBean.getMinLat() , locationRangeBean.getMaxLat()  );

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findUnitEmergencyRange", String.format("repository is finished,execute time is :%sms", end - start));

            List<UnitEmergencyEntity> unitEmergency = new ArrayList<>( ) ;
            if( unitEmergencyIdList != null && unitEmergencyIdList.size() > 0 && unitEmergencyIdList.size() <= 900 ){
                unitEmergency = organizationOtherRepository.findUnitEmergency(unitEmergencyIdList);
            }else if(  unitEmergencyIdList != null && unitEmergencyIdList.size()  > 900 ){
                int page = ( int ) Math.ceil( unitEmergencyIdList.size() / 900.0 ) ;
                for( int i = 0 ; i < page ; i++ ){
                    int startnum = i * 900 ;
                    int endnum = ( i + 1 ) * 900 ;
                    if( endnum > unitEmergencyIdList.size() ){
                        endnum = unitEmergencyIdList.size() ;
                    }
                    List<String>  batchIds = unitEmergencyIdList.subList( startnum , endnum ) ;
                    List<UnitEmergencyEntity> bathEntityList  = organizationOtherRepository.findUnitEmergency(  batchIds);
                    if (null != bathEntityList && bathEntityList.size() > 0) {
                        unitEmergency.addAll( bathEntityList ) ;
                    }
                }
            }

            if( unitEmergency != null && unitEmergency.size() > 0 ){
                for (UnitEmergencyEntity unit : unitEmergency){
                    UnitEmergencyBean bean = OrganizationTransformUtil.transform( unit ,dicsMap , organizationNameMap ) ;
                    res.add(bean);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findUnitEmergencyRange", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
           logService.erorLog(logger, "service", "findUnitEmergencyRange", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_DATA_FAIL);
        }
    }


    @Transactional(readOnly = true)
    @Override
    public List<UnitJointServiceBean> findUnitJointRange(String longitude, String latitude, String radius) {
        if (Strings.isBlank(longitude) || Strings.isBlank( latitude ) ) {
            logService.infoLog(logger, "service", "findUnitJointRange", "longitude or latitude is blank.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findUnitJointRange", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<UnitJointServiceBean> res = new ArrayList<>();



            logService.infoLog(logger, "repository", "findUnitJointRange", "repository is started...");
            Long start = System.currentTimeMillis();

            //根据坐标查范围内联勤保障单位
            LocationRangeBean locationRangeBean = DistanceUtils.buildLocationRange( Double.parseDouble(longitude  ) , Double.parseDouble ( latitude )  , Integer.parseInt( radius ) ) ;

            List<String> unitEmergencyIdList = basedataNativeQueryRepository.findUnitJointRange(
                    locationRangeBean.getMinLng() , locationRangeBean.getMaxLng() , locationRangeBean.getMinLat() , locationRangeBean.getMaxLat()  );

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findUnitJointRange", String.format("repository is finished,execute time is :%sms", end - start));

            List<UnitJointServiceEntity> unitEmergency = new ArrayList<>( ) ;
            if( unitEmergencyIdList != null && unitEmergencyIdList.size() > 0 && unitEmergencyIdList.size() <= 900 ){
                unitEmergency = organizationOtherRepository.findUnitJoint(unitEmergencyIdList);
            }else if(  unitEmergencyIdList != null && unitEmergencyIdList.size()  > 900 ){
                int page = ( int ) Math.ceil( unitEmergencyIdList.size() / 900.0 ) ;
                for( int i = 0 ; i < page ; i++ ){
                    int startnum = i * 900 ;
                    int endnum = ( i + 1 ) * 900 ;
                    if( endnum > unitEmergencyIdList.size() ){
                        endnum = unitEmergencyIdList.size() ;
                    }
                    List<String>  batchIds = unitEmergencyIdList.subList( startnum , endnum ) ;
                    List<UnitJointServiceEntity> bathEntityList  = organizationOtherRepository.findUnitJoint(  batchIds);
                    if (null != bathEntityList && bathEntityList.size() > 0) {
                        unitEmergency.addAll( bathEntityList ) ;
                    }
                }
            }

            if( unitEmergency != null && unitEmergency.size() > 0 ){
                //字典数据
                Map<String, Map<String, String>> dicsMap  = dictionaryService.findDictionaryMap(dics) ;
                //机构数据
                Map<String, String> organizationNameMap =  organizationService.findOrganizationNameMap() ;
                for (UnitJointServiceEntity unit : unitEmergency){
                    UnitJointServiceBean bean = OrganizationTransformUtil.transform( unit ,dicsMap , organizationNameMap  ) ;
                    res.add(bean);
                }
            }



            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findUnitJointRange", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findUnitJointRange", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_DATA_FAIL);
        }

    }



    @Transactional(readOnly = true)
    @Override
    public List<OrganizationBean> findOrganizationRange(String longitude, String latitude, String radius) {
        if (Strings.isBlank(longitude) || Strings.isBlank( latitude ) ||Strings.isBlank( radius )) {
            logService.infoLog(logger, "service", "findOrganizationRange", "longitude or latitude is blank.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findOrganizationRange", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<OrganizationBean> res = new ArrayList<>();

            //字典数据
            Map<String, Map<String, String>> dicsMap  = dictionaryService.findDictionaryMap(dics) ;
            //机构数据
            Map<String, String> organizationNameMap  =  organizationService.findOrganizationNameMap() ;

            logService.infoLog(logger, "repository", "findOrganizationRange", "repository is started...");
            Long start = System.currentTimeMillis();

            //根据坐标查范围内消防机构
            LocationRangeBean locationRangeBean = DistanceUtils.buildLocationRange( Double.parseDouble(longitude  ) , Double.parseDouble ( latitude )  , Integer.parseInt( radius ) ) ;

            List<String> unitEmergencyIdList = basedataNativeQueryRepository.findOrganizationRange(locationRangeBean.getMinLng() ,
                    locationRangeBean.getMaxLng() , locationRangeBean.getMinLat() , locationRangeBean.getMaxLat()  );

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findOrganizationRange", String.format("repository is finished,execute time is :%sms", end - start));

            List<OrganizationEntity> unitEmergency = new ArrayList<>( ) ;
            if( unitEmergencyIdList != null && unitEmergencyIdList.size() > 0 && unitEmergencyIdList.size() <= 900 ){
                unitEmergency = organizationOtherRepository.findOrganization(unitEmergencyIdList);
            }else if(  unitEmergencyIdList != null && unitEmergencyIdList.size()  > 900 ){
                int page = ( int ) Math.ceil( unitEmergencyIdList.size() / 900.0 ) ;
                for( int i = 0 ; i < page ; i++ ){
                    int startnum = i * 900 ;
                    int endnum = ( i + 1 ) * 900 ;
                    if( endnum > unitEmergencyIdList.size() ){
                        endnum = unitEmergencyIdList.size() ;
                    }
                    List<String>  batchIds = unitEmergencyIdList.subList( startnum , endnum ) ;
                    List<OrganizationEntity> bathEntityList  = organizationOtherRepository.findOrganization(  batchIds);
                    if (null != bathEntityList && bathEntityList.size() > 0) {
                        unitEmergency.addAll( bathEntityList ) ;
                    }
                }
            }
            //判断是ids否存在
            if( unitEmergency != null && unitEmergency.size() > 0 ){
                for (OrganizationEntity unit : unitEmergency){
                    OrganizationBean bean = OrganizationTransformUtil.transform( unit ,dicsMap , organizationNameMap) ;
                    res.add(bean);
                }
            }


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findOrganizationRange", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findOrganizationRange", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_DATA_FAIL);
        }
    }



    @Transactional(readOnly = true)
    @Override
    public List<OrganizationBean> findReceiveOrganization (  ) {

        try {
            logService.infoLog(logger, "service", "findReceiveOrganization", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<OrganizationBean> res = new ArrayList<>();


            List<String> organizationList = new ArrayList<>( ) ;
            //获得 根机构信息
            SystemConfigurationBean rootSystem = systemConfigurationService.getSystemConfigByConfigType("XFJG_ID");
            if( rootSystem != null && Strings.isNotBlank( rootSystem.getConfigValue() ) ){
                organizationList.add( rootSystem.getConfigValue() ) ;
            }
            //获得可接警大队信息
            SystemConfigurationBean receiveSystem = systemConfigurationService.getSystemConfigByConfigType("whetherReceive") ;
            if( receiveSystem != null && Strings.isNotBlank( receiveSystem.getConfigValue() ) ){
                 String[] receive = receiveSystem.getConfigValue().split(",") ;
                 List<String>  receiveIds = organizationService.findOrganizationIdsByCodes( Arrays.asList( receive ) );
                 if( receiveIds != null && receiveIds.size() > 0 ){
                     organizationList.addAll( receiveIds  ) ;
                 }
            }
            res = organizationService.findOrganizationsByIds( organizationList );

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findReceiveOrganization", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findReceiveOrganization", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_DATA_FAIL);
        }
    }


}