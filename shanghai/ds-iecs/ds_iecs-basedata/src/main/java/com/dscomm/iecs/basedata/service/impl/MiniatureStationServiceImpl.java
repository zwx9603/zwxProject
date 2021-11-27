package com.dscomm.iecs.basedata.service.impl;

import com.dscomm.iecs.base.enums.EnableEnum;
import com.dscomm.iecs.base.graphql.typebean.PaginationBean;
import com.dscomm.iecs.base.service.bean.LocationRangeBean;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.base.utils.DistanceUtils;
import com.dscomm.iecs.basedata.dal.po.MiniatureStationEntity;
import com.dscomm.iecs.basedata.dal.po.OrganizationEntity;
import com.dscomm.iecs.basedata.dal.repository.BasedataNativeQueryRepository;
import com.dscomm.iecs.basedata.dal.repository.OrganizationOtherRepository;
import com.dscomm.iecs.basedata.exception.BasedataException;
import com.dscomm.iecs.basedata.graphql.inputbean.MiniatureStationQueryInputInfo;
import com.dscomm.iecs.basedata.graphql.inputbean.MiniatureStationSaveInputInfo;
import com.dscomm.iecs.basedata.graphql.typebean.MiniatureStationBean;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.MiniatureStationService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.basedata.utils.OrganizationTransformUtil;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 描述：微型消防站 服务类 实现
 */
@Component("miniatureStationServiceImpl")
public class MiniatureStationServiceImpl implements MiniatureStationService {
    private static final Logger logger = LoggerFactory.getLogger(MiniatureStationServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private Environment env;
    private DictionaryService dictionaryService;
    private OrganizationService organizationService;
    private OrganizationOtherRepository organizationOtherRepository ;
    private BasedataNativeQueryRepository basedataNativeQueryRepository;

    private List<String> dics;

    /**
     * 默认的构造函数
     */
    @Autowired
    public MiniatureStationServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor,
                                Environment env,   DictionaryService dictionaryService,
                                OrganizationService organizationService , OrganizationOtherRepository organizationOtherRepository ,
                                       BasedataNativeQueryRepository basedataNativeQueryRepository


    ) {
        this.logService = logService;
        this.accessor = accessor;
        this.env = env;
        this.dictionaryService = dictionaryService;
        this.organizationService = organizationService;
        this.organizationOtherRepository =  organizationOtherRepository ;
        this.basedataNativeQueryRepository = basedataNativeQueryRepository ;

        dics = new ArrayList<>(Arrays.asList( "WZZT","WZZBLX","WZDPZT","XZQX" ) );
    }


    @Transactional(readOnly = true)
    @Override
    public List<MiniatureStationBean> findMiniatureStationRange(String longitude , String latitude , String radius ) {
        if (Strings.isBlank(longitude) || Strings.isBlank( latitude ) ||Strings.isBlank( radius )) {
            logService.infoLog(logger, "service", "findMiniatureStationRange", "longitude or latitude is blank.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findMiniatureStationRange", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<MiniatureStationBean> res = new ArrayList<>();

            //字典数据
            Map<String, Map<String, String>> dicsMap  = dictionaryService.findDictionaryMap(dics) ;
            //机构数据
            Map<String, String> organizationNameMap  =  organizationService.findOrganizationNameMap() ;

            logService.infoLog(logger, "repository", "findMiniatureStationRange", "repository is started...");
            Long start = System.currentTimeMillis();

            //根据坐标查范围内微型消防站
            LocationRangeBean locationRangeBean = DistanceUtils.buildLocationRange( Double.parseDouble(longitude  ) , Double.parseDouble ( latitude )  , Integer.parseInt( radius ) ) ;

            List<String> unitEmergencyIdList = basedataNativeQueryRepository.findMiniatureStationRange(locationRangeBean.getMinLng() ,
                    locationRangeBean.getMaxLng() , locationRangeBean.getMinLat() , locationRangeBean.getMaxLat()  );

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findMiniatureStationRange", String.format("repository is finished,execute time is :%sms", end - start));

            //判断是ids否存在
            List<MiniatureStationEntity> unitEmergency = new ArrayList<>( ) ;
            if( unitEmergencyIdList != null && unitEmergencyIdList.size() > 0 ){
                logService.infoLog(logger, "repository", "findMiniatureStationRange", "repository is started...");
                Long start1 = System.currentTimeMillis();

                if( unitEmergencyIdList != null && unitEmergencyIdList.size() > 0 && unitEmergencyIdList.size() <= 900 ){
                    unitEmergency = organizationOtherRepository.findMiniatureOrganization(unitEmergencyIdList);
                }else if(  unitEmergencyIdList != null && unitEmergencyIdList.size()  > 900 ){
                    int page = ( int ) Math.ceil( unitEmergencyIdList.size() / 900.0 ) ;
                    for( int i = 0 ; i < page ; i++ ){
                        int startnum = i * 900 ;
                        int endnum = ( i + 1 ) * 900 ;
                        if( endnum > unitEmergencyIdList.size() ){
                            endnum = unitEmergencyIdList.size() ;
                        }
                        List<String>  batchIds = unitEmergencyIdList.subList( startnum , endnum ) ;
                        List<MiniatureStationEntity> bathEntityList  = organizationOtherRepository.findMiniatureOrganization(  batchIds);
                        if (null != bathEntityList && bathEntityList.size() > 0) {
                            unitEmergency.addAll( bathEntityList ) ;
                        }
                    }
                }

                Long end1 = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "findMiniatureStationRange", String.format("repository is finished,execute time is :%sms", end1 - start1));
            }

            if( unitEmergency != null && unitEmergency.size() >  0 ){
                for (MiniatureStationEntity unit : unitEmergency){
                    MiniatureStationBean bean = OrganizationTransformUtil.transform( unit ,dicsMap , organizationNameMap) ;
                    res.add(bean);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findMiniatureStationRange", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findMiniatureStationRange", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_DATA_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     */
    @Transactional( rollbackFor =  Exception.class )
    @Override
    public MiniatureStationBean saveMiniatureStation(MiniatureStationSaveInputInfo inputInfo)  {
        if ( inputInfo == null ) {
            logService.infoLog(logger, "service", "saveMiniatureStation", "incidentId is Blank.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveMiniatureStation", "service is started...");
            Long logStart = System.currentTimeMillis();

            MiniatureStationBean res = null ;

            MiniatureStationEntity miniatureStationEntity = OrganizationTransformUtil.transform( inputInfo ) ;

            logService.infoLog(logger, "repository", "save(dbMiniatureStationEntity)", "repository is started...");
            Long start = System.currentTimeMillis();

            accessor.save( miniatureStationEntity) ;
            miniatureStationEntity.setIdCode( miniatureStationEntity.getId() );
            accessor.save( miniatureStationEntity) ;

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbMiniatureStationEntity)", String.format("repository is finished,execute time is :%sms", end - start));


            if (null != miniatureStationEntity ) {
                //字典
                Map<String, Map<String, String>> dicsMap  = dictionaryService.findDictionaryMap(dics) ;
                //机构名称
                Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();
                res = OrganizationTransformUtil.transform( miniatureStationEntity , dicsMap , organizationNameMap ) ;
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveMiniatureStation", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return  res ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveMiniatureStation", String.format("save Miniature Organization fail "), ex);
            throw new BasedataException(BasedataException.BasedataErrors.SAVE_DATA_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     */
    @Transactional( rollbackFor =  Exception.class )
    @Override
    public MiniatureStationBean saveMiniatureStationLocation(MiniatureStationSaveInputInfo inputInfo)  {
        if ( inputInfo == null ||  Strings.isBlank( inputInfo.getId() ) ||
                Strings.isBlank( inputInfo.getLongitude() ) || Strings.isBlank( inputInfo.getLatitude() ) ) {
            logService.infoLog(logger, "service", "saveMiniatureStationLocation", "MiniatureStationId or longitude or latitude is Blank.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveMiniatureStationLocation", "service is started...");
            Long logStart = System.currentTimeMillis();

            MiniatureStationBean res = null ;

            MiniatureStationEntity miniatureStationEntity = accessor.getById( inputInfo.getId() ,MiniatureStationEntity.class  ) ;
            if( miniatureStationEntity == null ){
                logService.infoLog(logger, "service", "saveMiniatureStationLocation", "miniatureStationEntity is Blank.");
                throw new BasedataException(BasedataException.BasedataErrors.DATA_FAIL_NULL);
            }

            miniatureStationEntity.setLongitude( inputInfo.getLongitude() );
            miniatureStationEntity.setLatitude(  inputInfo.getLatitude() );
            if( Strings.isNotBlank( inputInfo.getStationAddress() )){
                miniatureStationEntity.setStationAddress( inputInfo.getStationAddress() );
            }



            logService.infoLog(logger, "repository", "save(dbMiniatureStationEntity)", "repository is started...");
            Long start = System.currentTimeMillis();

            accessor.save( miniatureStationEntity) ;

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbMiniatureStationEntity)", String.format("repository is finished,execute time is :%sms", end - start));

            if (null != miniatureStationEntity ) {
                //字典
                Map<String, Map<String, String>> dicsMap  = dictionaryService.findDictionaryMap(dics) ;
                //机构名称
                Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();
                res = OrganizationTransformUtil.transform( miniatureStationEntity , dicsMap , organizationNameMap ) ;
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveMiniatureStationLocation", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return  res ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveMiniatureStationLocation", String.format("save Miniature Organization fail "), ex);
            throw new BasedataException(BasedataException.BasedataErrors.SAVE_DATA_FAIL);
        }
    }



    /**
     * {@inheritDoc}
     *
     */
    @Transactional( rollbackFor =  Exception.class )
    @Override
    public Boolean removeMiniatureStation(List<String> ids)  {
        if ( ids == null ||   ids.size() < 1 ) {
            logService.infoLog(logger, "service", "removeMiniatureOrganization", "MiniatureStationId or longitude or latitude is Blank.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "removeMiniatureOrganization", "service is started...");
            Long logStart = System.currentTimeMillis();

            Boolean  res = true  ;

            logService.infoLog(logger, "repository", "findMiniatureOrganization", "repository is started...");
            Long start1 = System.currentTimeMillis();

            List<MiniatureStationEntity> miniatureStationEntityList = new ArrayList<>( ) ;
            if( ids != null && ids.size() > 0 && ids.size() <= 900 ){
                  miniatureStationEntityList  = organizationOtherRepository.findMiniatureOrganization(ids);
            }else if(  ids != null && ids.size()  > 900 ){
                int page = ( int ) Math.ceil( ids.size() / 900.0 ) ;
                for( int i = 0 ; i < page ; i++ ){
                    int startnum = i * 900 ;
                    int endnum = ( i + 1 ) * 900 ;
                    if( endnum > ids.size() ){
                        endnum = ids.size() ;
                    }
                    List<String>  batchIds = ids.subList( startnum , endnum ) ;
                    List<MiniatureStationEntity> bathEntityList  =organizationOtherRepository.findMiniatureOrganization(  batchIds);
                    if (null != bathEntityList && bathEntityList.size() > 0) {
                        miniatureStationEntityList.addAll( bathEntityList ) ;
                    }
                }
            }

            Long end1 = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findMiniatureOrganization", String.format("repository is finished,execute time is :%sms", end1 - start1));

            if (null != miniatureStationEntityList && miniatureStationEntityList.size() > 0  ) {
               for(MiniatureStationEntity miniatureStationEntity :  miniatureStationEntityList ){
                   miniatureStationEntity.setValid( EnableEnum.ENABLE_FALSE.getCode() );
               }
            }

            logService.infoLog(logger, "repository", "save(dbMiniatureStationEntity)", "repository is started...");
            Long start = System.currentTimeMillis();

            accessor.save( miniatureStationEntityList ) ;

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbMiniatureStationEntity)", String.format("repository is finished,execute time is :%sms", end - start));

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "removeMiniatureOrganization", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return  res ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "removeMiniatureOrganization", String.format("save Miniature Organization fail "), ex);
            throw new BasedataException(BasedataException.BasedataErrors.SAVE_DATA_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     */
    @Transactional( readOnly =  true )
    @Override
    public PaginationBean<MiniatureStationBean> findMiniatureStationCondition(MiniatureStationQueryInputInfo inputInfo ) {
        if ( inputInfo == null   ) {
            logService.infoLog(logger, "service", "findMiniatureOrganizationCondition", "MiniatureStationQueryInputInfo is Blank.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findMiniatureOrganizationCondition", "service is started...");
            Long logStart = System.currentTimeMillis();

            PaginationBean<MiniatureStationBean>   res = new PaginationBean() ;

            String squadronOrganizationSearchPath = null ;
            if( Strings.isNotBlank( inputInfo.getOrganizationId() )){
                squadronOrganizationSearchPath = organizationService.findSearchPathById ( inputInfo.getOrganizationId());
            }

            logService.infoLog(logger, "repository", "findEquipmentVehicleCondition", "repository is started...");
            Long start = System.currentTimeMillis();

            //根据条件查询车辆信息
            List<MiniatureStationEntity> miniatureStationEntityList = organizationOtherRepository.findMiniatureStationCondition(
                    inputInfo.getKeyword(),  inputInfo.getDistrictCode() , squadronOrganizationSearchPath,
                    inputInfo.getWhetherPage(), inputInfo.getPagination().getPage(),
                    inputInfo.getPagination().getSize());

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findEquipmentVehicleCondition", String.format("repository is finished,execute time is :%sms", end - start));

            //获取名称-代码对应字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
            //机构数据
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();

            List<MiniatureStationBean> beans = new ArrayList<>( ) ;

            if (miniatureStationEntityList != null && miniatureStationEntityList.size() > 0) {
                for ( MiniatureStationEntity miniatureStationEntity : miniatureStationEntityList) {
                    MiniatureStationBean bean = OrganizationTransformUtil.transform( miniatureStationEntity , dicsMap , organizationNameMap ) ;
                    beans.add(bean);
                }
                res.setList( beans );
            }

            logService.infoLog(logger, "repository", "findEquipmentVehicleConditionTotal", "repository is started...");
            Long countStart = System.currentTimeMillis();

            //获取分页参数total
            Integer total = organizationOtherRepository.findMiniatureStationTotal(
                    inputInfo.getKeyword(),  inputInfo.getDistrictCode() , squadronOrganizationSearchPath ) ;

            Long countEnd = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findEquipmentVehicleConditionTotal", String.format("repository is finished,execute time is :%sms", countEnd - countStart));

            //装配结果
            Pagination pagination = new Pagination();
            pagination.setPage(inputInfo.getPagination().getPage());
            pagination.setSize(inputInfo.getPagination().getSize());
            pagination.setTotal(total);

            res.setPagination(pagination);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findMiniatureOrganizationCondition", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return  res ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findMiniatureOrganizationCondition", String.format("save Miniature Organization fail "), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_DATA_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     */
    @Transactional( readOnly =  true )
    @Override
    public  MiniatureStationBean findMiniatureStation( String miniatureStationId ) {
        if (  Strings.isBlank( miniatureStationId )   ) {
            logService.infoLog(logger, "service", "findMiniatureOrganization", "MiniatureStationQueryInputInfo is Blank.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findMiniatureOrganization", "service is started...");
            Long logStart = System.currentTimeMillis();

             MiniatureStationBean    res = new MiniatureStationBean() ;

            logService.infoLog(logger, "repository", "findMiniatureOrganization", "repository is started...");
            Long start = System.currentTimeMillis();

            MiniatureStationEntity miniatureStationEntity = accessor.getById( miniatureStationId , MiniatureStationEntity.class ) ;

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findMiniatureOrganization", String.format("repository is finished,execute time is :%sms", end - start));


            if ( miniatureStationEntity != null  ) {
                //获取名称-代码对应字典
                Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
                //机构数据
                Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();

                 res = OrganizationTransformUtil.transform( miniatureStationEntity , dicsMap , organizationNameMap ) ;
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findMiniatureOrganization", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return  res ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findMiniatureOrganization", String.format("save Miniature Organization fail "), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_DATA_FAIL);
        }
    }

}
