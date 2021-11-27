package com.dscomm.iecs.basedata.restful;

import com.dscomm.iecs.base.graphql.typebean.PaginationBean;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.base.utils.ObtainIPUtils;
import com.dscomm.iecs.basedata.exception.BasedataException;
import com.dscomm.iecs.basedata.graphql.inputbean.EquipmentVehicleQueryInputInfo;
import com.dscomm.iecs.basedata.graphql.inputbean.OrganizationQueryInputInfo;
import com.dscomm.iecs.basedata.graphql.typebean.*;
import com.dscomm.iecs.basedata.service.*;
import org.apache.logging.log4j.util.Strings;
import org.mx.StringUtils;
import org.mx.service.rest.vo.DataVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  basedata restful query  接口
 */
@Path("iecs/v1.0/query")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BaseDataQueryResource {

    private static final Logger logger = LoggerFactory.getLogger(BaseDataQueryResource.class);
    private LogService logService;
    private DictionaryService dictionaryService;
    private OrganizationService organizationService;
    private VehicleService vehicleService;
    private EquipmentService equipmentService;
    private OrganizationOtherService organizationOtherService;
    private SystemConfigurationService systemConfigurationService ;

    private BaseDateCacheService baseDateCacheService;
    private PersonService personService ;
    private MiniatureStationService miniatureStationService ;

    @Autowired
    public BaseDataQueryResource(LogService logService, DictionaryService dictionaryService, OrganizationService organizationService,
                                 VehicleService vehicleService, OrganizationOtherService organizationOtherService, EquipmentService equipmentService,
                                 SystemConfigurationService systemConfigurationService , BaseDateCacheService baseDateCacheService ,
                                 PersonService personService ,MiniatureStationService miniatureStationService

    ) {
        this.logService = logService;
        this.dictionaryService = dictionaryService;
        this.organizationService = organizationService;
        this.vehicleService = vehicleService;
        this.equipmentService = equipmentService;
        this.organizationOtherService = organizationOtherService;
        this.systemConfigurationService = systemConfigurationService ;
        this.baseDateCacheService = baseDateCacheService ;
        this.personService = personService ;
        this.miniatureStationService = miniatureStationService ;
    }



    /**
     * 基础数据 更新缓存
     *
     * @return 返回结果
     */
    @Path("baseDateCache")
    @GET
    public DataVO<Boolean> notifyAgentCache( ) {
        //更新服务IP port 缓存信息
        ObtainIPUtils.localTomcatIpAndPort();

        baseDateCacheService.updateBaseDateCache(   );

        baseDateCacheService.updateBaseDateNoKeyCache(   );

        return new DataVO<>(true);
    }



    /**
     *  根据字典定义编码获得列表字典项信息
     *
     * @param type
     * @return 返回结果
     */
    @Path("findDictionaryByGrid")
    @POST
    public DataVO<List<DictionaryBean>> findDictionaryByGrid(@Context HttpHeaders headers, @QueryParam("type") String type) {
        logService.infoLog(logger, "restful", "findDictionaryByGrid", "restful is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        if (StringUtils.isBlank(type)) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        List<DictionaryBean> res = dictionaryService.findGridDictionary(type, false);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "findDictionaryByGrid", String.format("restful is finished,execute time is :%sms", end - start));
        return new DataVO<>(res);
    }


    /**
     *  根据字典定义编码获得树形字典项信息
     *
     * @param type
     * @return 返回结果
     */
    @Path("findDictionaryByTree")
    @POST
    public DataVO<List<DictionaryBean>> findDictionaryByTree(@Context HttpHeaders headers, @QueryParam("type") String type) {
        logService.infoLog(logger, "restful", "findDictionaryByTree", "restful is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        if (Strings.isBlank(type)) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        List<DictionaryBean> res = dictionaryService.findTreeDictionary(type, false);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "findDictionaryByTree", String.format("restful is finished,execute time is :%sms", end - start));
        return new DataVO<>(res);
    }

    /**
     *  获得全部字典信息
     *
     * @return 返回结果
     */
    @Path("findDictionaryByAll")
    @POST
    public DataVO<List<DictionaryKeyBean>> findDictionaryByAll (HttpHeaders headers) {
        logService.infoLog(logger, "restful", "findDictionaryByAll", "restful is started...");
        Long start = System.currentTimeMillis();

        List<DictionaryKeyBean> res = dictionaryService.findAllDictionary( );

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "findDictionaryByAll", String.format("restful is finished,execute time is :%sms", end - start));
        return new DataVO<>(res);
    }


    /* 1.3 根据条件获得车辆信息(分页)
     *
     * @param queryBean EquipmentVehicleQueryInputInfo
     * @return 返回结果
     */
    @Path("findEquipmentVehicleCondition")
    @POST
    public DataVO<PaginationBean<EquipmentVehicleBean>> findEquipmentVehicleCondition(@Context HttpHeaders headers, EquipmentVehicleQueryInputInfo queryBean) {
        logService.infoLog(logger, "restful", "findEquipmentVehicleCondition", "restful is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        if (queryBean == null || Strings.isBlank(queryBean.getScopeSquadronId())) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        //执行逻辑处理
        PaginationBean<EquipmentVehicleBean> res = vehicleService.findEquipmentVehicleCondition(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "findEquipmentVehicleCondition", String.format("restful is finished,execute time is :%sms", end - start));
        return new DataVO<>(res);
    }


    /* 1.3 根据机构id获得 车辆信息
     *
     * @param organizationId
     * @return 返回结果
     */
    @Path("findEquipmentVehicleByOrganizationId")
    @POST
    public DataVO<List <EquipmentVehicleBean>> findEquipmentVehicleByOrganizationId(@Context HttpHeaders headers, @QueryParam("organizationId") String organizationId) {
        logService.infoLog(logger, "restful", "findEquipmentVehicleByOrganizationId", "restful is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        if (  Strings.isBlank( organizationId ) ) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        //执行逻辑处理
        List<EquipmentVehicleBean> res = vehicleService.findEquipmentVehicleByOrganizationId(organizationId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "findEquipmentVehicleByOrganizationId", String.format("restful is finished,execute time is :%sms", end - start));
        return new DataVO<>(res) ;
    }

    /* 1.4 根据车辆ID查询车辆信息
     *
     * @param vehicleId 参数
     * @return 返回结果
     */
    @Path("findVehicle")
    @POST
    public DataVO<EquipmentVehicleBean> findVehicle(@Context HttpHeaders headers, @QueryParam("vehicleId") String vehicleId) {
        logService.infoLog(logger, "restful", "findVehicle", "restful is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        if (Strings.isBlank(vehicleId)) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        //执行逻辑处理
        EquipmentVehicleBean res = vehicleService.findVehicle(vehicleId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "findVehicle", String.format("restful is finished,execute time is :%sms", end - start));
        return new DataVO<>(res) ;
    }

    /* 1.4 根据车辆车牌号查询车辆信息
     *
     * @param vehicleId 参数
     * @return 返回结果
     */
    @Path("findVehicleNumber")
    @POST
    public DataVO<EquipmentVehicleBean> findVehicleNumber(@Context HttpHeaders headers, @QueryParam("vehicleNumber") String vehicleNumber) {
        logService.infoLog(logger, "restful", "findVehicleNumber", "restful is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        if (Strings.isBlank(vehicleNumber)) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        //执行逻辑处理
        EquipmentVehicleBean res = vehicleService.findVehicleNumber( vehicleNumber);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "findVehicleNumber", String.format("restful is finished,execute time is :%sms", end - start));
        return new DataVO<>(res) ;
    }

    /**
     * 获得全部机构信息
     *
     * @return 返回结果
     */
    @Path("getRootOrg")
    @GET
    public DataVO<Object> getRootOrg(HttpHeaders headers) {
        logService.infoLog(logger, "restful", "findOrganizationAll", "restful is started...");
        Long start = System.currentTimeMillis();

        //执行逻辑处理
        Map<String,Object> orgMap = new HashMap<>();
        orgMap.put( "getRootOrg" , organizationService.getRootOrg( ) ) ;
        orgMap.put( "findTreeOrganization" , organizationService.findTreeOrganization( ) ) ;

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "findOrganizationAll", String.format("restful is finished,execute time is :%sms", end - start));
        return new DataVO<>(orgMap) ;
    }



    /**
     * 获得全部机构信息
     *
     * @return 返回结果
     */
    @Path("findOrganizationAll")
    @POST
    public DataVO<List<OrganizationBean>> findOrganizationAll(HttpHeaders headers) {
        logService.infoLog(logger, "restful", "findOrganizationAll", "restful is started...");
        Long start = System.currentTimeMillis();

        List<OrganizationBean> res = new ArrayList<>( );

        //执行逻辑处理
        Map<String,  OrganizationBean > resMap = organizationService.findOrganizationAll( );

        if( resMap != null && resMap.size() > 0 ){
            res.addAll( resMap.values() ) ;
        }

        res.sort((a, b) -> a.getOrganizationOrderNum().compareTo(b.getOrganizationOrderNum()));

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "findOrganizationAll", String.format("restful is finished,execute time is :%sms", end - start));
        return new DataVO<>(res) ;
    }

    /**
     * 获得全部机构信息-树状
     *
     * @return 返回结果
     */
    @Path("findTreeOrganization")
    @POST
    public DataVO<List<OrganizationBean>> findTreeOrganization(HttpHeaders headers) {
        logService.infoLog(logger, "restful", "findTreeOrganization", "restful is started...");
        Long start = System.currentTimeMillis();

        //执行逻辑处理
        List<OrganizationBean> res = organizationService.findTreeOrganization( );

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "findTreeOrganization", String.format("restful is finished,execute time is :%sms", end - start));
        return new DataVO<>(res) ;
    }

    /**
     * 获得某类机构全部信息
     *
     * @param nature 0总队1支队2大队3中队
     * @return 返回结果
     */
    @Path("findOrganizationNatureAll")
    @POST
    public DataVO<List<OrganizationBean>> findOrganizationNatureAll(@Context HttpHeaders headers , @QueryParam("squadronId") String squadronId ,
                                                                    @QueryParam("nature") Integer nature) {
        logService.infoLog(logger, "restful", "findOrganizationNatureAll", "restful is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        if(    null == nature ){
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        //执行逻辑处理
        List<OrganizationBean> res  = organizationService.findOrganizationNatureAll( squadronId , nature );

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "findOrganizationNatureAll", String.format("restful is finished,execute time is :%sms", end - start));
        return new DataVO<>(res) ;
    }

    /**
     * 1.5 根据条件查询机构信息，返回结构分为列表结构信息以及树形结构信息
     *
     * @param queryBean OrganizationQueryInputInfo
     * @return 返回结果
     */
    @Path("findOrganizationCondition")
    @POST
    public DataVO<List<OrganizationBean>> findOrganizationCondition(@Context HttpHeaders headers,OrganizationQueryInputInfo queryBean ) {
        logService.infoLog(logger, "restful", "findOrganizationCondition", "restful is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        if (queryBean == null) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        //执行逻辑处理
        List<OrganizationBean> res = organizationService.findOrganizationCondition(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "findOrganizationCondition", String.format("restful is finished,execute time is :%sms", end - start));
        return new DataVO<>(res) ;
    }

    /**
     * 1.6 根据机构id获得机构详情
     *
     * @param organizationId
     * @return 返回结果
     */
    @Path("findOrganization")
    @POST
    public DataVO<OrganizationBean> findOrganizationByOrganizationId(@Context HttpHeaders headers,@QueryParam("organizationId") String organizationId) {
        logService.infoLog(logger, "restful", "findOrganizationByOrganizationId", "restful is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        if (Strings.isBlank(organizationId)) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }

        //执行逻辑处理
        OrganizationBean res = organizationService.findOrganizationByOrganizationId(organizationId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "findOrganizationByOrganizationId", String.format("restful is finished,execute time is :%sms", end - start));
        return new DataVO<>(res) ;
    }

    /**
     * 1.7 根据机构id获得下级机构信息
     *
     * @param organizationId
     * @return 返回结果
     */
    @Path("findOrganizationChildren")
    @POST
    public DataVO<List<OrganizationBean>> findOrganizationChildren(@Context HttpHeaders headers,@QueryParam("organizationId") String organizationId) {
        logService.infoLog(logger, "restful", "findOrganizationChildren", "restful is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        if (Strings.isBlank(organizationId)) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        //执行逻辑处理
        List<OrganizationBean> res = organizationService.findOrganizationChildren(organizationId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "findOrganizationChildren", String.format("restful is finished,execute time is :%sms", end - start));
        return new DataVO<>(res) ;
    }

    /**
     * 1.8 根据机构id获得平级机构信息
     *
     * @param organizationId
     * @return 返回结果
     */
    @Path("findOrganizationSamelevel")
    @POST
    public DataVO<List<OrganizationBean>> findOrganizationSamelevel(@Context HttpHeaders headers,@QueryParam("organizationId") String organizationId) {
        logService.infoLog(logger, "restful", "findOrganizationSamelevel", "restful is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        if (Strings.isBlank(organizationId)) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        //执行逻辑处理
        List<OrganizationBean> res = organizationService.findOrganizationSamelevel(organizationId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "findOrganizationSamelevel", String.format("restful is finished,execute time is :%sms", end - start));
        return new DataVO<>(res) ;
    }

    /**
     * 1.9 根据机构id获得上级机构信息
     *
     * @param organizationId
     * @return 返回结果
     */
    @Path("findOrganizationHigherlevel")
    @POST
    public DataVO<OrganizationBean> findOrganizationHigherlevel(@Context HttpHeaders headers,@QueryParam("organizationId") String organizationId) {
        logService.infoLog(logger, "restful", "findOrganizationHigherlevel", "restful is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        if (Strings.isBlank(organizationId)) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        //执行逻辑处理
        OrganizationBean res = organizationService.findOrganizationHigherlevel(organizationId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "findOrganizationHigherlevel", String.format("restful is finished,execute time is :%sms", end - start));
        return new DataVO<>(res) ;
    }



    /**
     * 2.1 根据机构id获得毗邻机构信息 (获取消防单位友缘度)
     *
     * @param organizationId
     * @return 返回结果
     */
    @Path("findAdjacentOrganization")
    @POST
    public DataVO<List<OrganizationAdjacentPriorityBean>> findAdjacentOrganization(@Context HttpHeaders headers,@QueryParam("organizationId") String organizationId) {
        logService.infoLog(logger, "restful", "findAdjacentOrganization", "restful is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        if (Strings.isBlank(organizationId)) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        //执行逻辑处理
        List<OrganizationAdjacentPriorityBean> res = organizationOtherService.findAdjacentOrganizationByChargeOrganizationId(organizationId ) ;

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "findAdjacentOrganization", String.format("restful is finished,execute time is :%sms", end - start));
        return new DataVO<>(res) ;
    }

    /**
     * 1.9 根据机构id获得人员信息
     *
     * @param organizationId
     * @return 返回结果
     */
    @Path("findPersonCondition")
    @POST
    public DataVO<List<PersonBean>> findPersonCondition(@Context HttpHeaders headers,@QueryParam("organizationId") String organizationId
            ,@QueryParam("keyword") String keyword ) {

        logService.infoLog(logger, "restful", "findPersonCondition", "restful is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        if (Strings.isBlank(organizationId)) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        //执行逻辑处理
        List<PersonBean> res = personService.findPersonCondition( organizationId , keyword   ) ;

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "findPersonCondition", String.format("restful is finished,execute time is :%sms", end - start));
        return new DataVO<>(res) ;
    }


    /**
     * 根据车辆id获得 车辆装载 设备信息
     *
     * @param vehicleId
     * @return 返回结果
     */
    @Path("findEquipmentVehicleLoadByVehicleId")
    @POST
    public DataVO<List<EquipmentVehicleLoadBean>> findEquipmentVehicleLoadByVehicleId(@Context HttpHeaders headers,@QueryParam("vehicleId") String vehicleId) {
        logService.infoLog(logger, "restful", "findEquipmentVehicleLoadByVehicleId", "restful is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        if (Strings.isBlank(vehicleId)  ) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        //执行逻辑处理
        List<EquipmentVehicleLoadBean> res = equipmentService.findEquipmentVehicleLoadByVehicleId(  vehicleId );

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "findEquipmentVehicleLoadByVehicleId", String.format("restful is finished,execute time is :%sms", end - start));
        return new DataVO<>(res) ;
    }

    /**
     * 根据坐标查范围内应急单位
     *
     * @param longitude
     * @return 返回结果
     */
    @Path("findUnitEmergencyRange")
    @POST
    public DataVO<List<UnitEmergencyBean>> findUnitEmergencyRange(@Context HttpHeaders headers,
                              @QueryParam("longitude") String longitude,
                              @QueryParam("latitude") String latitude,
                              @QueryParam("radius") String radius) {
        logService.infoLog(logger, "restful", "findUnitEmergencyRange", "restful is started...");
        Long start = System.currentTimeMillis();



        //参数判断
        if (  Strings.isBlank(longitude) || Strings.isBlank( latitude )|| Strings.isBlank( radius )) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        //执行逻辑处理
        List<UnitEmergencyBean> res = organizationOtherService.findUnitEmergencyRange(longitude, latitude, radius);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "findUnitEmergencyRange", String.format("restful is finished,execute time is :%sms", end - start));
        return new DataVO<>(res) ;
    }



    /**
     * 根据坐标查范围内联勤保障单位
     *
     * @param longitude
     * @return 返回结果
     */
    @Path("findUnitJointRange")
    @POST
    public DataVO<List<UnitJointServiceBean>> findUnitJointRange(@Context HttpHeaders headers,
                                                                                  @QueryParam("longitude") String longitude,
                                                                                  @QueryParam("latitude") String latitude,
                                                                                  @QueryParam("radius") String radius) {
        logService.infoLog(logger, "restful", "findUnitJointRange", "restful is started...");
        Long start = System.currentTimeMillis();


        //参数判断
        if (  Strings.isBlank(longitude) || Strings.isBlank( latitude )|| Strings.isBlank( radius )) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        //执行逻辑处理
        List<UnitJointServiceBean> res = organizationOtherService.findUnitJointRange(longitude, latitude, radius);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "findUnitJointRange", String.format("restful is finished,execute time is :%sms", end - start));
        return new DataVO<>(res) ;
    }



    /**
     * 根据坐标查范围内消防机构
     *
     * @param longitude
     * @return 返回结果
     */
    @Path("findOrganizationRange")
    @POST
    public DataVO<List<OrganizationBean>> findOrganizationRange(@Context HttpHeaders headers,
                                                                                 @QueryParam("longitude") String longitude,
                                                                                 @QueryParam("latitude") String latitude,
                                                                                 @QueryParam("radius") String radius) {
        logService.infoLog(logger, "restful", "findOrganizationRange", "restful is started...");
        Long start = System.currentTimeMillis();


        //参数判断
        if (  Strings.isBlank(longitude) || Strings.isBlank( latitude )|| Strings.isBlank( radius )) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        //执行逻辑处理
        List<OrganizationBean> res = organizationOtherService.findOrganizationRange(longitude, latitude, radius);


        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "findOrganizationRange", String.format("restful is finished,execute time is :%sms", end - start));
        return new DataVO<>(res) ;
    }


    /**
     * 根据坐标查范围内微站
     *
     * @param longitude
     * @return 返回结果
     */
    @Path("findMiniatureStationRange")
    @POST
    public DataVO<List<MiniatureStationBean>> findMiniatureStationRange(@Context HttpHeaders headers,
                                                                @QueryParam("longitude") String longitude,
                                                                @QueryParam("latitude") String latitude,
                                                                @QueryParam("radius") String radius) {
        logService.infoLog(logger, "restful", "findMiniatureOrganizationRange", "restful is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        if (  Strings.isBlank(longitude) || Strings.isBlank( latitude )|| Strings.isBlank( radius )) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        //执行逻辑处理
        List<MiniatureStationBean> res = miniatureStationService.findMiniatureStationRange(longitude, latitude, radius);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "findMiniatureOrganizationRange", String.format("restful is finished,execute time is :%sms", end - start));
        return new DataVO<>(res) ;
    }


    /**
     *  根据 系统配置项 编号 查询 系统配置信息
     *
     * @param configType
     * @return 返回结果
     */
    @Path("findSystemConfiguration")
    @POST
    public DataVO<SystemConfigurationBean> findSystemConfiguration(@Context HttpHeaders headers,
                                                                @QueryParam("configType") String configType ) {
        logService.infoLog(logger, "restful", "findSystemConfiguration", "restful is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        if (  Strings.isBlank(configType)  ) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        //执行逻辑处理
        SystemConfigurationBean res = systemConfigurationService.getSystemConfigByConfigType(  configType  );

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "findSystemConfiguration", String.format("restful is finished,execute time is :%sms", end - start));
        return new DataVO<>(res) ;
    }




}
