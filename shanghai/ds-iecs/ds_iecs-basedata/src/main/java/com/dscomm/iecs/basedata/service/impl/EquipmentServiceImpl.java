package com.dscomm.iecs.basedata.service.impl;

import com.dscomm.iecs.base.graphql.typebean.PaginationBean;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.dal.po.EquipmentEntity;
import com.dscomm.iecs.basedata.dal.po.EquipmentVehicleLoadEntity;
import com.dscomm.iecs.basedata.dal.po.OrganizationEntity;
import com.dscomm.iecs.basedata.dal.repository.BasedataNativeQueryRepository;
import com.dscomm.iecs.basedata.dal.repository.EquipmentRepository;
import com.dscomm.iecs.basedata.exception.BasedataException;
import com.dscomm.iecs.basedata.graphql.inputbean.EquipmentQueryInputInfo;
import com.dscomm.iecs.basedata.graphql.typebean.EquipmentBean;
import com.dscomm.iecs.basedata.graphql.typebean.EquipmentVehicleLoadBean;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.EquipmentService;
import com.dscomm.iecs.basedata.utils.EquipmentTransformUtil;
import org.apache.logging.log4j.util.Strings;
import org.mx.dal.Pagination;
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
 * 描述：装备（消防设备）服务类实现
 */
@Component("equipmentServiceImpl")
public class EquipmentServiceImpl implements EquipmentService {

    private static final Logger logger = LoggerFactory.getLogger(EquipmentServiceImpl.class);
    private Environment env;
    private LogService logService;
    private GeneralAccessor accessor;
    private DictionaryService dictionaryService;
    private EquipmentRepository equipmentRepository;
    private BasedataNativeQueryRepository basedataNativeQueryRepository ;

    private static Map<String, List<String>> vehicleIdEquipmentLoadsMap = new HashMap<>(); //车辆 与 装载装备id列表 关系
    private static Map<String, Set<String>> equipmentTypeVehicleIdMap = new HashMap<>(); // 装备类型 与 车辆id列表 关系

    private List<String> dics;

    /**
     * 默认的构造函数
     */
    @Autowired
    public EquipmentServiceImpl(@Qualifier("generalAccessor") GeneralAccessor accessor, LogService logService, EquipmentRepository equipmentRepository,
                                Environment env, DictionaryService dictionaryService , BasedataNativeQueryRepository basedataNativeQueryRepository
    ) {
        this.env = env;
        this.accessor = accessor;
        this.logService = logService;
        this.dictionaryService = dictionaryService;
        this.equipmentRepository = equipmentRepository;
        this.basedataNativeQueryRepository = basedataNativeQueryRepository ;

        dics = new ArrayList<>(Arrays.asList("ZBZT", "JLDW", "ZBLX"   ));


    }


    private Long lastTime  = 0l ; //系统默认为0 加载全部

    /**
     *  根据上次数据最新时间  本次数据最新时间
     *  判断是否从数据库更新数据 如果本次数据最新时间 大于 上次数据时间 则需要更新数据
     */
    private void updateCacheEquipmentVehicleLoad(){
        logService.infoLog(logger, "service", "uppdateCacheEquipmentVehicleLoad", "service is started...");
        Long logStart = System.currentTimeMillis();

        Long latestTime = equipmentRepository.findDataLatestTime( lastTime ) ;
        latestTime = latestTime == null ? lastTime : latestTime ;
        //判断是否需要更新数据
        if(  latestTime > lastTime ){

            logService.infoLog(logger, "service", "uppdateCacheEquipmentVehicleLoad", " update cache data");

            //此处为增量更新数据
            List<Object[]>  equipmentVehicleLoadList    =  equipmentRepository.findDataLatestTime( lastTime ,  latestTime   ) ;

            if( equipmentVehicleLoadList != null && equipmentVehicleLoadList.size() > 0 ){

                    for( Object[] equipmentVehicleLoadEntity  :  equipmentVehicleLoadList){
                        String equipmentVehicleLoadId = ( String ) equipmentVehicleLoadEntity[0] ;
                        String vehicleId = ( String ) equipmentVehicleLoadEntity[1] ;
                        String equipmentTypeCode = ( String ) equipmentVehicleLoadEntity[2] ;
                        //缓存 车辆id - 车载装备列表 map
                        if(Strings.isNotBlank(vehicleId)){
                            List<String> mapBeans = vehicleIdEquipmentLoadsMap.get(vehicleId);
                            if(mapBeans == null){
                                mapBeans = new ArrayList<>();
                            }
                            mapBeans.add( equipmentVehicleLoadId );
                            vehicleIdEquipmentLoadsMap.put(vehicleId,mapBeans);
                        }
                        //缓存 装备类型 - 车辆id列表 map
                        if(Strings.isNotBlank(equipmentTypeCode)){
                            Set<String> vehicleIds = equipmentTypeVehicleIdMap.get(equipmentTypeCode);
                            if(vehicleIds == null){
                                vehicleIds = new HashSet<>();
                            }
                            vehicleIds.add(vehicleId);
                            equipmentTypeVehicleIdMap.put(equipmentTypeCode,vehicleIds);
                        }
                    }
            }
            lastTime = latestTime ;

        }

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "uppdateCacheEquipmentVehicleLoad", String.format("service is finished,execute time is :%sms", logEnd - logStart));


    }



    /**
     * {@inheritDoc}
     *
     * @see  #forceUpdateCacheEquipmentVehicleLoadCache( )
     */
    @Transactional(readOnly = true)
    @Override
    public void forceUpdateCacheEquipmentVehicleLoadCache( ){
        try {
            logService.infoLog(logger, "service", "updateCacheVehicleIdEquipmentLoads", "service is started...");
            Long logStart = System.currentTimeMillis();

            updateCacheEquipmentVehicleLoad();

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "updateCacheVehicleIdEquipmentLoads", String.format("service is finished,execute time is :%sms", logEnd - logStart));

        } catch (Exception ex) {
            logService.erorLog(logger, "service", "updateCacheVehicleIdEquipmentLoads", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.UPDATE_CACHE_FAIL);
        }
    }




    /**
     * {@inheritDoc}
     *
     * @see  #findEquipmentVehicleLoadByVehicleId( String )
     */
    @Override
    public List<EquipmentVehicleLoadBean> findEquipmentVehicleLoadByVehicleId(  String vehicleId  ){
        if (Strings.isBlank( vehicleId )) {
            logService.infoLog(logger, "service", "findEquipmentVehicleLoadByVehicleId", "vehicleId is null.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findEquipmentVehicleLoadByVehicleId", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<EquipmentVehicleLoadBean> res = new ArrayList<>() ;

            //从缓存中获取车辆id关联的车载装备
            List<String>  vehicleIdEquipmentLoadIds = vehicleIdEquipmentLoadsMap.get(vehicleId);

            if(vehicleIdEquipmentLoadIds != null && vehicleIdEquipmentLoadIds.size() > 0 && vehicleIdEquipmentLoadIds.size() <= 900 ){
                Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;

                List<EquipmentVehicleLoadEntity> equipmentEntityList = equipmentRepository.findEquipmentVehicleLoadByIds( vehicleIdEquipmentLoadIds );
                if( equipmentEntityList != null && equipmentEntityList.size() > 0 ){
                    for ( EquipmentVehicleLoadEntity equipmentEntity : equipmentEntityList) {
                        EquipmentVehicleLoadBean bean = EquipmentTransformUtil.transform(equipmentEntity, dicsMap);
                        res.add( bean );
                    }
                }
            }else if(  vehicleIdEquipmentLoadIds != null && vehicleIdEquipmentLoadIds.size()  > 900 ){
                Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;
                int page = ( int ) Math.ceil( vehicleIdEquipmentLoadIds.size() / 900.0 ) ;
                for( int i = 0 ; i < page ; i++ ){
                    int startnum = i * 900 ;
                    int endnum = ( i + 1 ) * 900 ;
                    if( endnum > vehicleIdEquipmentLoadIds.size() ){
                        endnum = vehicleIdEquipmentLoadIds.size() ;
                    }
                    List<String>  batchVehcileIds = vehicleIdEquipmentLoadIds.subList( startnum , endnum ) ;
                    List<EquipmentVehicleLoadEntity> bathEquipmentEntityList = equipmentRepository.findEquipmentVehicleLoadByIds( batchVehcileIds );
                    if( bathEquipmentEntityList != null && bathEquipmentEntityList.size() > 0 ){
                        for ( EquipmentVehicleLoadEntity equipmentEntity : bathEquipmentEntityList) {
                            EquipmentVehicleLoadBean bean = EquipmentTransformUtil.transform(equipmentEntity, dicsMap);
                            res.add( bean );
                        }
                    }
                }
            }
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findEquipmentVehicleLoadByVehicleId", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;

        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findEquipmentVehicleLoadByVehicleId", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_EQUIPMENT_FAIL);
        }
    }



    /**
     * {@inheritDoc}
     *
     * @see EquipmentService#findEquipmentCondition(EquipmentQueryInputInfo)
     */
    @Transactional(readOnly = true)
    @Override
    public PaginationBean<EquipmentBean> findEquipmentCondition(EquipmentQueryInputInfo inputInfo) {
        if (inputInfo == null) {
            logService.infoLog(logger, "service", "findEquipmentCondition", "EquipmentQueryInputInfo is null.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findEquipmentCondition", "service is started...");
            Long logStart = System.currentTimeMillis();

            PaginationBean<EquipmentBean> res = new PaginationBean<>();
            List<EquipmentBean> beans = new ArrayList<>();

            String searchPath = inputInfo.getScopeSquadronId() ; //机构查询码 总队类型直接查询全部数据  支队类型查询辖区Id部分数据
            if (1 == inputInfo.getScopeType()) {
                OrganizationEntity organization = accessor.getById(inputInfo.getScopeSquadronId(), OrganizationEntity.class);
                if (organization != null && organization.isValid()) {
                    searchPath = organization.getSearchPath();
                }
            }else{
                searchPath = null ;
            }

            logService.infoLog(logger, "repository", "findEquipmentCondition", "repository is started...");
            Long start = System.currentTimeMillis();

            //根据条件查询装备信息
            List<EquipmentEntity> equipmentEntityList = equipmentRepository.findEquipmentCondition(searchPath, inputInfo.getKeyword(),
                    inputInfo.getOrganizationIds(), inputInfo.getEquipmentStatusCodes(), inputInfo.getEquipmentTypeCodes(), inputInfo.getWhetherConsumptiveEquipment(),
                    inputInfo.getWhetherPage(), inputInfo.getPagination().getPage(),
                    inputInfo.getPagination().getSize());

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findEquipmentCondition", String.format("repository is finished,execute time is :%sms", end - start));

            //获取名称-代码对应字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;
            if (equipmentEntityList != null && equipmentEntityList.size() > 0) {
                for (EquipmentEntity equipmentEntity : equipmentEntityList) {
                    EquipmentBean bean = EquipmentTransformUtil.transform(equipmentEntity, dicsMap);
                    beans.add(bean);
                }
            }

            logService.infoLog(logger, "repository", "findEquipmentConditionTotal", "repository is started...");
            Long countStart = System.currentTimeMillis();

            //获取分页参数total
            Integer total = equipmentRepository.findEquipmentConditionTotal(searchPath, inputInfo.getOrganizationIds(),
                    inputInfo.getKeyword(), inputInfo.getEquipmentStatusCodes(), inputInfo.getEquipmentTypeCodes(), inputInfo.getWhetherConsumptiveEquipment());

            Long countEnd = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findEquipmentConditionTotal", String.format("repository is finished,execute time is :%sms", countEnd - countStart));

            //装配结果
            Pagination pagination = new Pagination();
            pagination.setPage(inputInfo.getPagination().getPage());
            pagination.setSize(inputInfo.getPagination().getSize());
            pagination.setTotal(total);

            res.setPagination(pagination);
            res.setList(beans);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findEquipmentCondition", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;

        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findEquipmentCondition", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_EQUIPMENT_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see  #findVehicleIdsByEquipmentKeyWord(String )
     */
    @Override
    public  Set<String> findVehicleIdsByEquipmentKeyWord (  String  keyword   ){

        try {
            logService.infoLog(logger, "service", "findVehicleIdsByEquipmentTypeCode", "service is started...");
            Long logStart = System.currentTimeMillis();

            Set<String> res = new HashSet<>( ) ;

            logService.infoLog(logger, "repository", "findVehicleByKeyword", "repository is started...");
            Long countStart = System.currentTimeMillis();

            List<String> vehicleIds  = basedataNativeQueryRepository.findVehicleByKeyword( "%" + keyword + "%",  env.getProperty("ZBLX") );

            Long countEnd = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findVehicleByKeyword", String.format("repository is finished,execute time is :%sms", countEnd - countStart));

            if( vehicleIds != null && vehicleIds.size() > 0  ){
                res.addAll( vehicleIds ) ;
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findVehicleIdsByEquipmentTypeCode", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;

        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findVehicleIdsByEquipmentTypeCode", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_VEHICLE_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see  #findVehicleIdsByEquipmentTypeCode(List )
     */
    @Override
    public Set<String> findVehicleIdsByEquipmentTypeCode( List<String> equipmentTypeCodes ){

        try {
            logService.infoLog(logger, "service", "findVehicleIdsByEquipmentTypeCode", "service is started...");
            Long logStart = System.currentTimeMillis();

            //从缓存中获取 车载装备 关联的车辆id列表
            Set<String> res =  new HashSet<>( );

            for( String equipmentTypeCode : equipmentTypeCodes){
                Set<String>  vehicleIds = equipmentTypeVehicleIdMap.get(equipmentTypeCode);
                if( vehicleIds != null ){
                    res.addAll( vehicleIds ) ;
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findVehicleIdsByEquipmentTypeCode", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;

        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findVehicleIdsByEquipmentTypeCode", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_VEHICLE_FAIL);
        }
    }



    /**
     * {@inheritDoc}
     *
     * @see  #findEquipmentVehicleByVehicleIds(List<String> )
     */
    @Transactional(readOnly = true)
    @Override
    public List<EquipmentVehicleLoadBean> findEquipmentVehicleByVehicleIds(List<String> vehicleIds ){
        if (vehicleIds == null || vehicleIds.isEmpty()) {
            logService.infoLog(logger, "service", "findEquipmentCondition", "vehicleIds is null or empty.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findEquipmentVehicleByVehicleIds", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<EquipmentVehicleLoadBean> res = new ArrayList<>();

            //获取名称-代码对应字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;

            logService.infoLog(logger, "repository", "findEquipmentVehicleLoadByVehicleIds", "repository is started...");
            Long start = System.currentTimeMillis();

            List<EquipmentVehicleLoadEntity>  equipmentVehicleLoadEntityList = new ArrayList<>() ;
            if(  vehicleIds != null && vehicleIds.size() > 0 && vehicleIds.size() <= 900 ){
                equipmentVehicleLoadEntityList = equipmentRepository.findEquipmentVehicleLoadByVehicleIds(vehicleIds) ;
            }else if(  vehicleIds != null && vehicleIds.size()  > 900 ){
                int page = ( int ) Math.ceil( vehicleIds.size() / 900.0 ) ;
                for( int i = 0 ; i < page ; i++ ){
                    int startnum = i * 900 ;
                    int endnum = ( i + 1 ) * 900 ;
                    if( endnum > vehicleIds.size() ){
                        endnum = vehicleIds.size() ;
                    }
                    List<String>  batchVehcileIds = vehicleIds.subList( startnum , endnum ) ;
                    List<EquipmentVehicleLoadEntity>  bathEquipmentVehicleLoadEntityList = equipmentRepository.findEquipmentVehicleLoadByVehicleIds(batchVehcileIds) ;
                    if( bathEquipmentVehicleLoadEntityList != null && bathEquipmentVehicleLoadEntityList.size() > 0 ){
                        equipmentVehicleLoadEntityList.addAll( bathEquipmentVehicleLoadEntityList ) ;
                    }
                }
            }

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findEquipmentVehicleLoadByVehicleIds", String.format("repository is finished,execute time is :%sms", end - start));

            if( equipmentVehicleLoadEntityList != null && equipmentVehicleLoadEntityList.size() > 0 ){
                for( EquipmentVehicleLoadEntity equipmentVehicleLoadEntity  :  equipmentVehicleLoadEntityList){
                    EquipmentVehicleLoadBean equipmentVehicleLoadBean = EquipmentTransformUtil.transform( equipmentVehicleLoadEntity , dicsMap ) ;
                    res.add(equipmentVehicleLoadBean);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findEquipmentVehicleByVehicleIds", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findEquipmentVehicleByVehicleIds", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_EQUIPMENT_FAIL);
        }
    }





}
