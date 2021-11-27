package com.dscomm.iecs.basedata.service.impl;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.dal.po.EquipmentVehicleEntity;
import com.dscomm.iecs.basedata.dal.po.PersonMailEntity;
import com.dscomm.iecs.basedata.dal.po.VehiclePersonsEntity;
import com.dscomm.iecs.basedata.dal.repository.EquipmentVehiclePersonRepository;
import com.dscomm.iecs.basedata.exception.BasedataException;
import com.dscomm.iecs.basedata.graphql.inputbean.VehiclePersonsListSaveInputInfo;
import com.dscomm.iecs.basedata.graphql.inputbean.VehiclePersonsSaveInputInfo;
import com.dscomm.iecs.basedata.graphql.typebean.EquipmentVehicleBean;
import com.dscomm.iecs.basedata.graphql.typebean.VehiclePersonsBean;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.basedata.service.VehiclePersonService;
import com.dscomm.iecs.basedata.service.VehicleService;
import com.dscomm.iecs.basedata.utils.EquipmentTransformUtil;
import org.apache.logging.log4j.util.Strings;
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
 * 描述：车辆人员服务类实现
 */
@Component("vehiclePersonServiceImpl")
public class VehiclePersonServiceImpl implements VehiclePersonService {
    private static final Logger logger = LoggerFactory.getLogger(VehiclePersonServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private EquipmentVehiclePersonRepository equipmentVehiclePersonRepository;
    private DictionaryService dictionaryService;
    private VehicleService vehicleService ;
    private OrganizationService organizationService ;


    private List<String> dics;

    /**
     * 默认的构造函数
     */
    @Autowired
    public VehiclePersonServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor,
                                    EquipmentVehiclePersonRepository equipmentVehiclePersonRepository ,
                                    DictionaryService dictionaryService,
                                    VehicleService vehicleService ,
                                    OrganizationService organizationService

    ) {
        this.logService = logService;
        this.accessor = accessor;
        this.equipmentVehiclePersonRepository = equipmentVehiclePersonRepository ;
        this.dictionaryService = dictionaryService;
        this.vehicleService =  vehicleService ;
        this.organizationService = organizationService ;

        dics = new ArrayList<>(Arrays.asList(  "WLRYZW"  ) );

    }



    /**
     * {@inheritDoc}
     *
     * @see #findVehiclePersons(List  )
     */
    @Transactional( readOnly =  true )
    @Override
    public   List<VehiclePersonsBean>  findVehiclePersonsSplit (  List<String> vehicleIds   ){
        if (vehicleIds == null || vehicleIds.size() < 1) {
            logService.infoLog(logger, "service", "findVehiclePersons", "vehicleIds is null  is blank.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findVehiclePersons", "service is started...");
            Long logStart = System.currentTimeMillis();


            List<VehiclePersonsBean> res = new ArrayList<>();

            logService.infoLog(logger, "repository", "findVehiclePersonsByVehicleIds", "repository is started...");
            Long start = System.currentTimeMillis();

            List<Object[]>  vehiclePersonsEntityList = equipmentVehiclePersonRepository.findVehiclePersonsMailByVehicleIds(vehicleIds);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findVehiclePersonsByVehicleIds", String.format("repository is finished,execute time is :%sms", end - start));

            if (vehiclePersonsEntityList != null && vehiclePersonsEntityList.size() > 0) {
                //获取名称-代码对应字典
                Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
                for (Object[] source : vehiclePersonsEntityList) {
                    VehiclePersonsEntity vehiclePersonsEntity = (VehiclePersonsEntity) source[0];
                    EquipmentVehicleBean vehicleBean = vehicleService.findVehicleCache( vehiclePersonsEntity.getVehicleId() ) ;
                    VehiclePersonsBean bean = EquipmentTransformUtil.transform(vehiclePersonsEntity ,vehicleBean , dicsMap);
                    PersonMailEntity personMailEntity = (PersonMailEntity) source[1];
                    if (personMailEntity != null) {
                        bean.setPersonPhone(personMailEntity.getMobilePhone());
                    }
                    res.add(bean);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findVehiclePersons", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findVehiclePersons", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_DATA_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see #findVehiclePersons(List  )
     */
    @Transactional( readOnly =  true )
    @Override
    public List<VehiclePersonsBean>  findVehiclePersons( List<String> vehicleIds  ){
        if (vehicleIds == null || vehicleIds.size() < 1) {
            logService.infoLog(logger, "service", "findVehiclePersons", "vehicleIds is null  is blank.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findVehiclePersons", "service is started...");
            Long logStart = System.currentTimeMillis();


            List<VehiclePersonsBean> res = new ArrayList<>();

            logService.infoLog(logger, "repository", "findVehiclePersonsByVehicleIds", "repository is started...");
            Long start = System.currentTimeMillis();

            List<Object[]>  vehiclePersonsEntityList = equipmentVehiclePersonRepository.findVehiclePersonsByVehicleIds(vehicleIds);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findVehiclePersonsByVehicleIds", String.format("repository is finished,execute time is :%sms", end - start));

            if (vehiclePersonsEntityList != null && vehiclePersonsEntityList.size() > 0) {
                //获取名称-代码对应字典
                Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
                //机构名称 map
                Map<String, String> organizationNameMap  = organizationService.findOrganizationNameMap() ;
                for (Object[] source : vehiclePersonsEntityList) {

                    EquipmentVehicleEntity vehicle = (EquipmentVehicleEntity) source[0] ;
                    VehiclePersonsEntity vehiclePerson  = ( VehiclePersonsEntity ) source[1] ;

                    VehiclePersonsBean bean = new VehiclePersonsBean() ;
                    bean.setVehicleId( vehicle.getId()  );
                    bean.setVehicleNumber( vehicle.getVehicleNumber() );
                    bean.setVehicleName( vehicle.getVehicleName());
                    bean.setOrganizationId( vehicle.getOrganizationId() ) ;
                    bean.setOrganizationName( organizationNameMap.get( vehicle.getOrganizationId()  ) );
                    if( vehiclePerson != null ){
                        bean.setId( vehiclePerson.getId() );
                        bean.setVehiclePersonId( vehiclePerson.getId()  );
                        bean.setPersonId( vehiclePerson.getPersonId()  );
                        bean.setPersonName( vehiclePerson.getPersonName()  );
                        bean.setPersonType( vehiclePerson.getPersonType()  );
                        bean.setPersonTypeName( dicsMap.get("WLRYZW").get(  vehiclePerson.getPersonType()  ) );

                        bean.setDriver( vehiclePerson.getDriver() );
                        bean.setCorrespondent( vehiclePerson.getCorrespondent() );

                        bean.setPersonNum( vehicle.getPassengersNum() == null ? 0 : (int)  Math.floor( vehicle.getPassengersNum() )  );

                        bean.setSorter( vehiclePerson.getSorter()   );
                    }else{
                        bean.setId( "" );
                        bean.setVehiclePersonId( "" );
                        bean.setPersonNum(  vehicle.getPassengersNum() == null ? 0 : (int)  Math.floor( vehicle.getPassengersNum() )   );
                    }
                    res.add(bean);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findVehiclePersons", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findVehiclePersons", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_DATA_FAIL);
        }
    }




    /**
     * {@inheritDoc}
     *
     * @see #findVehiclePersonsByOrganizationId(String   )
     */
    @Transactional( readOnly =  true )
    @Override
    public List<VehiclePersonsBean>  findVehiclePersonsByOrganizationId( String  organizationId    ) {
        if ( Strings.isBlank( organizationId ) ) {
            logService.infoLog(logger, "service", "findVehiclePersonsByOrganizationId", "organizationId is null  is blank.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findVehiclePersonsByOrganizationId", "service is started...");
            Long logStart = System.currentTimeMillis();


            List<VehiclePersonsBean> res = new ArrayList<>();

            logService.infoLog(logger, "repository", "findVehiclePersonsByOrganizationId", "repository is started...");
            Long start = System.currentTimeMillis();

            List<Object[]>  vehiclePersonsEntityList = equipmentVehiclePersonRepository.findVehiclePersonsByOrganizationId( organizationId );


            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findVehiclePersonsByOrganizationId", String.format("repository is finished,execute time is :%sms", end - start));

            if (vehiclePersonsEntityList != null && vehiclePersonsEntityList.size() > 0) {
                //获取名称-代码对应字典
                Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
                //机构名称 map
                Map<String, String> organizationNameMap  = organizationService.findOrganizationNameMap() ;
                for (Object[] source : vehiclePersonsEntityList) {

                    EquipmentVehicleEntity vehicle = (EquipmentVehicleEntity) source[0] ;
                    VehiclePersonsEntity vehiclePerson  = ( VehiclePersonsEntity ) source[1] ;

                    VehiclePersonsBean bean = new VehiclePersonsBean() ;
                    bean.setVehicleId( vehicle.getId()  );
                    bean.setVehicleNumber( vehicle.getVehicleNumber() );
                    bean.setVehicleName( vehicle.getVehicleName());
                    bean.setOrganizationId( vehicle.getOrganizationId() ) ;
                    bean.setOrganizationName( organizationNameMap.get( vehicle.getOrganizationId()  ) );
                    if( vehiclePerson != null ){
                        bean.setId( vehiclePerson.getId() );
                        bean.setVehiclePersonId( vehiclePerson.getId()  );
                        bean.setPersonId( vehiclePerson.getPersonId()  );
                        bean.setPersonName( vehiclePerson.getPersonName()  );
                        bean.setPersonType( vehiclePerson.getPersonType()  );
                        bean.setPersonTypeName( dicsMap.get("WLRYZW").get(  vehiclePerson.getPersonType()  ) );

                        bean.setDriver( vehiclePerson.getDriver() );
                        bean.setCorrespondent( vehiclePerson.getCorrespondent() );

                        bean.setPersonNum( vehicle.getPassengersNum() == null ? 0 : (int)  Math.floor( vehicle.getPassengersNum() )  );

                        bean.setSorter( vehiclePerson.getSorter()   );
                    }else{
                        bean.setId( "" );
                        bean.setVehiclePersonId( "" );
                        bean.setPersonNum(  vehicle.getPassengersNum() == null ? 0 : (int)  Math.floor( vehicle.getPassengersNum() )   );
                    }
                    res.add(bean);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findVehiclePersonsByOrganizationId", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findVehiclePersonsByOrganizationId", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_DATA_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see #saveVehiclePersons( VehiclePersonsSaveInputInfo )
     */
    @Transactional(  rollbackFor = Exception.class )
    @Override
    public  Boolean  saveVehiclePersons(  VehiclePersonsSaveInputInfo  inputInfo ) {
        if ( inputInfo == null || inputInfo.getVehiclePersonsList() == null || inputInfo.getVehiclePersonsList().size() < 1.  ) {
            logService.infoLog(logger, "service", "saveVehiclePersons", "List<VehiclePersonsSaveInputInfo> is null  is blank.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveVehiclePersons", "service is started...");
            Long logStart = System.currentTimeMillis();

            Boolean res = false ;

            if( inputInfo != null   ){

                List<String> vehicleIds = new ArrayList<>();

                List<VehiclePersonsEntity> vehiclePersonList = new ArrayList<>() ;
                for( VehiclePersonsListSaveInputInfo vehiclePersonsSaveInputInfo :  inputInfo.getVehiclePersonsList() ){
                    VehiclePersonsEntity vehiclePersonsEntity = new VehiclePersonsEntity() ;
                    vehiclePersonsEntity.setId(   vehiclePersonsSaveInputInfo.getVehiclePersonId() );
                    vehiclePersonsEntity.setVehicleId( vehiclePersonsSaveInputInfo.getVehicleId() );
                    vehiclePersonsEntity.setPersonId( vehiclePersonsSaveInputInfo.getPersonId() );
                    vehiclePersonsEntity.setPersonName( vehiclePersonsSaveInputInfo.getPersonName() );
                    if( Strings.isBlank( vehiclePersonsEntity.getPersonId() )  ){
                        vehiclePersonsEntity.setPersonId( vehiclePersonsEntity.getPersonName() );
                    }

                    vehiclePersonsEntity.setPersonType(  vehiclePersonsSaveInputInfo.getPersonType() );

                    vehiclePersonsEntity.setDriver( vehiclePersonsSaveInputInfo.getDriver() );
                    vehiclePersonsEntity.setCorrespondent( vehiclePersonsSaveInputInfo.getCorrespondent() );

                    vehiclePersonsEntity.setPersonNum( vehiclePersonsSaveInputInfo.getPersonNum()  );

                    vehiclePersonsEntity.setSorter( vehiclePersonsSaveInputInfo.getSorter() );
                    vehiclePersonList.add( vehiclePersonsEntity ) ;

                    vehicleIds.add( vehiclePersonsSaveInputInfo.getVehicleId() ) ;

                }

                logService.infoLog(logger, "repository", "saveVehiclePersons", "repository is started...");
                Long startincidentCircleSave = System.currentTimeMillis();

                //删除历史指挥员
                List<VehiclePersonsEntity>  vehiclePersonsList = equipmentVehiclePersonRepository.findVehiclePersonsByVehicleIdList(vehicleIds) ;

                accessor.remove( vehiclePersonsList ,true );
                accessor.save( vehiclePersonList ) ;

                Long endincidentCircleSave = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "saveVehiclePersons", String.format("repository is finished,execute time is :%sms", endincidentCircleSave - startincidentCircleSave));

            }

            res = true ;

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveVehiclePersons", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveVehiclePersons", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.SAVE_DATA_FAIL);
        }
    }

}
