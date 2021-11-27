package com.dscomm.iecs.basedata.graphql;

import com.dscomm.iecs.base.graphql.typebean.PaginationBean;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.exception.BasedataException;
import com.dscomm.iecs.basedata.graphql.inputbean.*;
import com.dscomm.iecs.basedata.graphql.typebean.*;
import com.dscomm.iecs.basedata.service.*;
import graphql.schema.DataFetchingEnvironment;
import org.apache.logging.log4j.util.Strings;
import org.mx.service.rest.graphql.GraphQLAnnotationExecution;
import org.mx.service.rest.graphql.GraphQLFieldAnnotation;
import org.mx.service.rest.graphql.GraphQLUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 描述:基础数据模块graphql查询操作执行类
 *
 * @author YangFuxi
 * Date Time 2019/7/21 22:18
 */
@Component("basedataQueryExecution")
public class BasedataQueryExecution implements GraphQLAnnotationExecution {
    private static final Logger logger = LoggerFactory.getLogger(BasedataQueryExecution.class);
    private LogService logService;
    private DictionaryService dictionaryService;
    private OrganizationService organizationService;
    private VehicleService vehicleService;
    private EquipmentService equipmentService;
    private ContactsService contactsService;
    private OrganizationOtherService organizationOtherService;
    private SystemConfigurationService systemConfigurationService ;
    private PersonService personService ;
    private VehicleGarageMappingService vehicleGarageMappingService ;
    private PlanService planService ;
    private MiniatureStationService miniatureStationService ;
    private RegionService regionService ;
    private VehiclePersonService vehiclePersonService ;
    private UnitEmergencyService unitEmergencyService;
    private UnitJointServiceService unitJointServiceService;

    @Autowired
    public BasedataQueryExecution(LogService logService, DictionaryService dictionaryService, OrganizationService organizationService,
                                  VehicleService vehicleService, OrganizationOtherService organizationOtherService, EquipmentService equipmentService,
                                  ContactsService contactsService , SystemConfigurationService systemConfigurationService ,
                                  PersonService personService , VehicleGarageMappingService vehicleGarageMappingService ,
                                  PlanService planService ,MiniatureStationService miniatureStationService ,
                                  RegionService regionService ,VehiclePersonService vehiclePersonService,
                                  UnitEmergencyService unitEmergencyService,
                                  UnitJointServiceService unitJointServiceService

    ) {
        this.logService = logService;
        this.dictionaryService = dictionaryService;
        this.organizationService = organizationService;
        this.vehicleService = vehicleService;
        this.equipmentService = equipmentService;
        this.contactsService = contactsService;
        this.organizationOtherService = organizationOtherService;
        this.systemConfigurationService = systemConfigurationService ;
        this.personService = personService ;
        this.vehicleGarageMappingService = vehicleGarageMappingService ;
        this.planService = planService ;
        this.miniatureStationService = miniatureStationService ;
        this.regionService = regionService ;
        this.vehiclePersonService = vehiclePersonService ;
        this.unitEmergencyService = unitEmergencyService ;
        this.unitJointServiceService = unitJointServiceService ;
    }

    @Override
    public String getTypeName() {
        return "Query";
    }

    /**
     *   强制更新全部字典信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("forceCacheDictionary")
    public Boolean forceCacheDictionary(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "forceCacheDictionary", "graphql is started...");
        Long start = System.currentTimeMillis();

        dictionaryService.forceUpdateCacheDictionary();
        ;
        Boolean res = true;

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "forceCacheDictionary", String.format("graphql is finished,execute time is :%sms", end - start));

        return res;
    }


    /**
     *  根据字典定义编码获得列表字典项信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findDictionaryByGrid")
    public List<DictionaryBean> findDictionaryByGrid(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findDictionaryByGrid", "graphql is started...");
        Long start = System.currentTimeMillis();

        String type = environment.getArgument("type");
        //参数判断
        if (Strings.isBlank(type)) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        List<DictionaryBean> res = dictionaryService.findGridDictionary(type, false);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findDictionaryByGrid", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /*  根据字典定义编码获得树形字典项信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findDictionaryByTree")
    public List<DictionaryBean> findDictionaryByTree(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findDictionaryByTree", "graphql is started...");
        Long start = System.currentTimeMillis();

        String type = environment.getArgument("type");
        //参数判断
        if (Strings.isBlank(type)) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        List<DictionaryBean> res = dictionaryService.findTreeDictionary(type, false);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findDictionaryByTree", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /*  根据字典定义编码获得树形字典项信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findDictionaryByType")
    public List<DictionaryKeyBean> findDictionaryType (DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findDictionaryType", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        List<String> dicsType = environment.getArgument("dicsType");

        List<DictionaryKeyBean> res = dictionaryService.findDictionaryType( dicsType );

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findDictionaryType", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /*  获得全部字典信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findDictionaryByAll")
    public List<DictionaryKeyBean> findDictionaryByAll (DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findDictionaryByAll", "graphql is started...");
        Long start = System.currentTimeMillis();

        List<DictionaryKeyBean> res = dictionaryService.findAllDictionary( );

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findDictionaryByAll", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }





    /* 1.3 根据条件获得车辆信息(分页)
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findEquipmentVehicleCondition")
    public PaginationBean<EquipmentVehicleBean> findEquipmentVehicleCondition(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findEquipmentVehicleCondition", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        EquipmentVehicleQueryInputInfo queryBean = GraphQLUtils.parse(input, EquipmentVehicleQueryInputInfo.class);
        //参数判断
        if (queryBean == null || Strings.isBlank(queryBean.getScopeSquadronId())) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        //执行逻辑处理
        PaginationBean<EquipmentVehicleBean> res = vehicleService.findEquipmentVehicleCondition(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findEquipmentVehicleCondition", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /* 1.3 根据机构id获得 车辆信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findEquipmentVehicleByOrganizationId")
    public List <EquipmentVehicleBean> findEquipmentVehicleByOrganizationId(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findEquipmentVehicleByOrganizationId", "graphql is started...");
        Long start = System.currentTimeMillis();

        String organizationId = environment.getArgument("organizationId");
        //参数判断
        if (  Strings.isBlank( organizationId ) ) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        //执行逻辑处理
        List<EquipmentVehicleBean> res = vehicleService.findEquipmentVehicleByOrganizationId(organizationId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findEquipmentVehicleByOrganizationId", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }



    /* 1.4 根据车辆ID查询车辆信息
     *
     * @param vehicleId 参数
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findVehicle")
    public EquipmentVehicleBean findVehicle(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findVehicle", "graphql is started...");
        Long start = System.currentTimeMillis();

        String vehicleId = environment.getArgument("vehicleId");
        //参数判断
        if (Strings.isBlank(vehicleId)) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        //执行逻辑处理
        EquipmentVehicleBean res = vehicleService.findVehicle(vehicleId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findVehicle", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /* 1.4 根据车辆车牌号查询车辆信息
     *
     * @param vehicleId 参数
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findVehicleNumber")
    public EquipmentVehicleBean findVehicleNumber(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findVehicleNumber", "graphql is started...");
        Long start = System.currentTimeMillis();

        String vehicleNumber = environment.getArgument("vehicleNumber");
        //参数判断
        if (Strings.isBlank(vehicleNumber)) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        //执行逻辑处理
        EquipmentVehicleBean res = vehicleService.findVehicleNumber( vehicleNumber);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findVehicleNumber", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 获得全部机构信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findOrganizationAll")
    public List<OrganizationBean> findOrganizationAll(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findOrganizationAll", "graphql is started...");
        Long start = System.currentTimeMillis();

        List<OrganizationBean> res = new ArrayList<>( );

        //执行逻辑处理
        Map<String,  OrganizationBean > resMap = organizationService.findOrganizationAll( );

       if( resMap != null && resMap.size() > 0 ){
           res.addAll( resMap.values() ) ;
       }
        res.sort((a, b) -> a.getOrganizationOrderNum().compareTo(b.getOrganizationOrderNum()));

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findOrganizationAll", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 获得全部机构信息-树状
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findTreeOrganization")
    public List<OrganizationBean> findTreeOrganization(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findTreeOrganization", "graphql is started...");
        Long start = System.currentTimeMillis();

        //执行逻辑处理
        List<OrganizationBean> res = organizationService.findTreeOrganization( );

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findTreeOrganization", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 获得某类机构全部信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findOrganizationNatureAll")
    public List<OrganizationBean> findOrganizationNatureAll(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findOrganizationNatureAll", "graphql is started...");
        Long start = System.currentTimeMillis();

        String squadronId = environment.getArgument("squadronId");
        Integer nature = environment.getArgument("nature");
        //参数判断
        if( null == nature  ){
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        //执行逻辑处理
        List<OrganizationBean> res  = organizationService.findOrganizationNatureAll(squadronId ,  nature );

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findOrganizationNatureAll", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 1.5 根据条件查询机构信息，返回结构分为列表结构信息以及树形结构信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findOrganizationCondition")
    public List<OrganizationBean> findOrganizationCondition(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findOrganizationCondition", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        OrganizationQueryInputInfo queryBean = GraphQLUtils.parse(input, OrganizationQueryInputInfo.class);
        //参数判断
        if (queryBean == null) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        //执行逻辑处理
        List<OrganizationBean> res = organizationService.findOrganizationCondition(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findOrganizationCondition", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 1.6 根据机构id获得机构详情
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findOrganization")
    public OrganizationBean findOrganizationByOrganizationId(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findOrganizationByOrganizationId", "graphql is started...");
        Long start = System.currentTimeMillis();

        String organizationId = environment.getArgument("organizationId");
        //参数判断
        if (Strings.isBlank(organizationId)) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }

        //执行逻辑处理
        OrganizationBean res = organizationService.findOrganizationByOrganizationId(organizationId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findOrganizationByOrganizationId", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }




    /**
     * 1.7 根据机构id获得下级机构信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findOrganizationChildren")
    public List<OrganizationBean> findOrganizationChildren(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findOrganizationChildren", "graphql is started...");
        Long start = System.currentTimeMillis();

        String organizationId = environment.getArgument("organizationId");
        //参数判断
        if (Strings.isBlank(organizationId)) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        //执行逻辑处理
        List<OrganizationBean> res = organizationService.findOrganizationChildren(organizationId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findOrganizationChildren", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 1.8 根据机构id获得平级机构信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findOrganizationSamelevel")
    public List<OrganizationBean> findOrganizationSamelevel(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findOrganizationSamelevel", "graphql is started...");
        Long start = System.currentTimeMillis();

        String organizationId = environment.getArgument("organizationId");
        //参数判断
        if (Strings.isBlank(organizationId)) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        //执行逻辑处理
        List<OrganizationBean> res = organizationService.findOrganizationSamelevel(organizationId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findOrganizationSamelevel", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 1.9 根据机构id获得上级机构信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findOrganizationHigherlevel")
    public OrganizationBean findOrganizationHigherlevel(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findOrganizationHigherlevel", "graphql is started...");
        Long start = System.currentTimeMillis();

        String organizationId = environment.getArgument("organizationId");
        //参数判断
        if (Strings.isBlank(organizationId)) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        //执行逻辑处理
        OrganizationBean res = organizationService.findOrganizationHigherlevel(organizationId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findOrganizationHigherlevel", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     *   根据机构id 获得 非消防机构车辆
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findOrganizationLocalFullTimeUnitByOrganizationId")
    public List<OrganizationLocalFullTimeUnitBean> findOrganizationLocalFullTimeUnitByOrganizationId(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findOrganizationLocalFullTimeUnitByOrganizationId", "graphql is started...");
        Long start = System.currentTimeMillis();

        String organizationId = environment.getArgument("organizationId");
        //参数判断
        if (Strings.isBlank(organizationId)) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }

        //执行逻辑处理
        List<OrganizationLocalFullTimeUnitBean> res = organizationOtherService.findOrganizationLocalFullTimeUnitByOrganizationId(organizationId);


        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findOrganizationLocalFullTimeUnitByOrganizationId", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 2.0 根据机构微站/企业专职队id获得机构微站/企业专职队详情
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findOrganizationLocalFullTimeUnit")
    public OrganizationLocalFullTimeUnitBean findOrganizationLocalFullTimeUnit(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findOrganizationLocalFullTimeUnit", "graphql is started...");
        Long start = System.currentTimeMillis();

        String organizationLocalFullTimeUnitId = environment.getArgument("organizationLocalFullTimeUnitId");
        //参数判断
        if (Strings.isBlank(organizationLocalFullTimeUnitId)) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }

        //执行逻辑处理
        OrganizationLocalFullTimeUnitBean res = organizationOtherService.findOrganizationLocalFullTimeUnitById(organizationLocalFullTimeUnitId);


        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findOrganizationLocalFullTimeUnit", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 2.1 根据机构id获得毗邻机构信息 (获取消防单位友缘度)
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findAdjacentOrganization")
    public List<OrganizationAdjacentPriorityBean> findAdjacentOrganization(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findAdjacentOrganization", "graphql is started...");
        Long start = System.currentTimeMillis();

        String organizationId = environment.getArgument("organizationId");
        //参数判断
        if (Strings.isBlank(organizationId)) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        //执行逻辑处理
        List<OrganizationAdjacentPriorityBean> res = organizationOtherService.findAdjacentOrganizationByChargeOrganizationId(organizationId ) ;

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findAdjacentOrganization", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 2.2 根据条件获得消防装备(分页)
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findEquipmentCondition")
    public PaginationBean<EquipmentBean> findEquipmentCondition(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findEquipmentCondition", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        EquipmentQueryInputInfo queryBean = GraphQLUtils.parse(input, EquipmentQueryInputInfo.class);
        //参数判断
        if (queryBean == null) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        //执行逻辑处理
        PaginationBean<EquipmentBean> res = equipmentService.findEquipmentCondition(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findEquipmentCondition", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }



    /**
     * 根据车辆id获得 车辆装载 设备信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findEquipmentVehicleLoadByVehicleId")
    public List<EquipmentVehicleLoadBean> findEquipmentVehicleLoadByVehicleId(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findEquipmentVehicleLoadByVehicleId", "graphql is started...");
        Long start = System.currentTimeMillis();

        String vehicleId = environment.getArgument("vehicleId");
        //参数判断
        if (Strings.isBlank(vehicleId)  ) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        //执行逻辑处理
        List<EquipmentVehicleLoadBean> res = equipmentService.findEquipmentVehicleLoadByVehicleId(  vehicleId );

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findEquipmentVehicleLoadByVehicleId", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 2.3 根据 机构 人员名称模糊匹配 获得人员信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findPersonCondition")
    public List<PersonBean> findPersonCondition(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findPersonCondition", "graphql is started...");
        Long start = System.currentTimeMillis();

        String organizationId = environment.getArgument("organizationId");
        String keyword = environment.getArgument("keyword");
        //参数判断
        if (Strings.isBlank(organizationId) ) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }

        //执行逻辑处理
        List<PersonBean> res = personService.findPersonCondition( organizationId , keyword   ) ;

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findPersonCondition", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 2.3 根据单位或人员编号获取单位或人员的联系方式(获取联系信息)
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findContactNumber")
    public ContactNumberBean findContactNumber(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findContactNumber", "graphql is started...");
        Long start = System.currentTimeMillis();

        String contactObjectId = environment.getArgument("contactObjectId");
        String contactType = environment.getArgument("contactType");
        //参数判断
        if (Strings.isBlank(contactObjectId) || Strings.isBlank(contactType)) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }

        //执行逻辑处理
        ContactNumberBean res = contactsService.findContactNumber(contactObjectId, contactType);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findContactNumber", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     *  根据配置项 获得系统配置信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findSystemConfiguration")
    public  List<SystemConfigurationBean> findSystemConfiguration(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findSystemConfiguration", "graphql is started...");
        Long start = System.currentTimeMillis();

        String configType = environment.getArgument("configType");
        //参数判断

        //执行逻辑处理
        List<SystemConfigurationBean> res = new ArrayList<>() ;
        if( Strings.isBlank( configType )){
            res = systemConfigurationService.findSystemConfiguration(  );
        }else{
            SystemConfigurationBean systemConfigurationBean = systemConfigurationService.getSystemConfigByConfigType( configType );
            if( systemConfigurationBean != null ){
                res.add( systemConfigurationBean) ;
            }
        }

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findSystemConfiguration", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 根据装备类型 获得 装载该装备的车辆id列表
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findVehicleIdsByEquipmentTypeCode")
    public Set<String> findVehicleIdsByEquipmentTypeCode(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findVehicleIdsByEquipmentTypeCode", "graphql is started...");
        Long start = System.currentTimeMillis();


        String equipmentTypeCode =   environment.getArgument("equipmentTypeCode");
        //参数判断
        if (Strings.isBlank( equipmentTypeCode )) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        String [] equipmentTypeCodeSj = equipmentTypeCode.split(",");
        List<String> equipmentTypeCodes = Arrays.asList( equipmentTypeCodeSj ) ;

        //执行逻辑处理
        Set<String> res = equipmentService.findVehicleIdsByEquipmentTypeCode(  equipmentTypeCodes );

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findVehicleIdsByEquipmentTypeCode", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }



    /**
     * 根据装备关键 获得 装载该装备的车辆id列表
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findVehicleIdsByEquipmentKeyWord")
    public Set<String> findVehicleIdsByEquipmentKeyWord(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findVehicleIdsByEquipmentTypeCode", "graphql is started...");
        Long start = System.currentTimeMillis();

        String keyword = environment.getArgument("keyword");
        //参数判断
        if (Strings.isBlank(keyword)  ) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        //执行逻辑处理
        Set<String> res = equipmentService.findVehicleIdsByEquipmentKeyWord(  keyword );

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findVehicleIdsByEquipmentKeyWord", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }



    /**
     * 根据车辆拓展字典 与 泡沫类型 查询车辆信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findVehicleIdsByExpandCondition")
    public Set<String> findVehicleIdsByExpandCondition(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findVehicleIdsByExpandCondition", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        EquipmentVehicleExpandQueryInputInfo queryBean = GraphQLUtils.parse(input, EquipmentVehicleExpandQueryInputInfo.class);
        //参数判断

        //执行逻辑处理
        Set<String> res = vehicleService.findVehicleIdsByExpandCondition(  queryBean );

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findVehicleIdsByExpandCondition", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 获取机构下可用车辆列表
     */
    @GraphQLFieldAnnotation("getUsableVehicle")
    public PaginationBean<EquipmentVehicleBean>getUsableVehicle(DataFetchingEnvironment environment){
        logService.infoLog(logger, "graphql", "getUsableVehicle", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        EquipmentVehicleQueryInputInfo queryBean = GraphQLUtils.parse(input, EquipmentVehicleQueryInputInfo.class);
        //参数判断
        if (queryBean == null || Strings.isBlank(queryBean.getScopeSquadronId())) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        //执行逻辑处理
        PaginationBean<EquipmentVehicleBean> res = vehicleService.findEquipmentVehicleCondition(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "getUsableVehicle", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;

    }


    /**
     * 7.4 根据机构id集合 关系类型集合 车辆id集合  车库id集合 获得车辆与车库关系
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findVehicleGarageMapping")
    public List<VehicleGarageMappingBean> findVehicleGarageMapping(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findVehicleGarageMapping", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        VehicleGarageMappingQueryInputInfo queryBean = GraphQLUtils.parse(info, VehicleGarageMappingQueryInputInfo.class);
        if (queryBean == null) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        //执行逻辑处理
        List<VehicleGarageMappingBean> res = vehicleGarageMappingService.findVehicleGarageMappingCondition(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findVehicleGarageMapping", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }



    /**
     * 7.4 根据联勤保障单位id 获得 联勤保障单位信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findUnitJointService")
    public UnitJointServiceBean findUnitJointService(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findUnitJointService", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        String unitJointServiceId = environment.getArgument("unitJointServiceId");
        if ( Strings.isBlank( unitJointServiceId  )) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        //执行逻辑处理
        UnitJointServiceBean  res = organizationOtherService.findUnitJointService(unitJointServiceId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findUnitJointService", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }



    /**
     * 7.4 根据应急联动单位id  获得应急联动详情
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findUnitEmergency")
    public UnitEmergencyBean findUnitEmergency(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findUnitEmergency", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        String unitEmergencyId = environment.getArgument("unitEmergencyId");
        if ( Strings.isBlank( unitEmergencyId  )) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        //执行逻辑处理
        UnitEmergencyBean res = organizationOtherService.findUnitEmergency(unitEmergencyId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findUnitEmergency", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 5.1 根据预案编号查询预案信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findPlan")
    public PlanBean findPlan(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findPlan", "graphql is started...");
        Long start = System.currentTimeMillis();

        String planId = environment.getArgument("planId");
        //参数判断
        if (Strings.isBlank(planId)) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }

        //执行逻辑处理
        PlanBean res = planService.findPlanById(planId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findPlan", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 5.2 根据重点单位编号查询该重点单位所有预案
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findPlanKeyUnitId")
    public List<PlanBean> findPlanKeyUnitId(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findPlanKeyUnitId", "graphql is started...");
        Long start = System.currentTimeMillis();

        String keyUnitId = environment.getArgument("keyUnitId");
        //参数判断
        if (Strings.isBlank(keyUnitId)) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }

        //执行逻辑处理
        List<PlanBean> res = planService.findPlanByKeyUnitId(keyUnitId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findPlanKeyUnitId", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }



    /**
     * 根据坐标查范围内联动单位
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findUnitEmergencyRange")
    public List<UnitEmergencyBean> findUnitEmergencyRange(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findUnitEmergencyRange", "graphql is started...");
        Long start = System.currentTimeMillis();

        String longitude = environment.getArgument("longitude");
        String latitude = environment.getArgument("latitude");
        String radius = environment.getArgument("radius");
        //参数判断
        if (  Strings.isBlank(longitude) || Strings.isBlank( latitude )|| Strings.isBlank( radius )) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }

        List<UnitEmergencyBean> res = organizationOtherService.findUnitEmergencyRange(longitude, latitude, radius);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findUnitEmergencyRange", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 根据坐标查范围内联勤保障单位
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findUnitJointRange")
    public List<UnitJointServiceBean> findUnitJointRange(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findUnitJointRange", "graphql is started...");
        Long start = System.currentTimeMillis();

        String longitude = environment.getArgument("longitude");
        String latitude = environment.getArgument("latitude");
        String radius = environment.getArgument("radius");
        //参数判断
        if (  Strings.isBlank(longitude) || Strings.isBlank( latitude )|| Strings.isBlank( radius )) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }

        List<UnitJointServiceBean> res = organizationOtherService.findUnitJointRange(longitude, latitude, radius);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findUnitJointRange", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }



    /**
     * 根据坐标查范围内的消防机构
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findOrganizationRange")
    public List<OrganizationBean> findOrganizationRange(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findOrganizationRange", "graphql is started...");
        Long start = System.currentTimeMillis();

        String longitude = environment.getArgument("longitude");
        String latitude = environment.getArgument("latitude");
        String radius = environment.getArgument("radius");
        //参数判断
        if (  Strings.isBlank(longitude) || Strings.isBlank( latitude )|| Strings.isBlank( radius )) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }

        List<OrganizationBean> res = organizationOtherService.findOrganizationRange(longitude, latitude, radius);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findOrganizationRange", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 根据坐标查范围内的微型消防站
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findMiniatureStationRange")
    public List<MiniatureStationBean> findMiniatureStationRange(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findMiniatureOrganizationRange", "graphql is started...");
        Long start = System.currentTimeMillis();

        String longitude = environment.getArgument("longitude");
        String latitude = environment.getArgument("latitude");
        String radius = environment.getArgument("radius");
        //参数判断
        if (  Strings.isBlank(longitude) || Strings.isBlank( latitude )|| Strings.isBlank( radius )) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }

        List<MiniatureStationBean> res = miniatureStationService.findMiniatureStationRange(longitude, latitude, radius);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findMiniatureOrganizationRange", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 根据条件 查询微站信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findMiniatureStationCondition")
    public PaginationBean<MiniatureStationBean> findMiniatureStationCondition(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findMiniatureStationCondition", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        MiniatureStationQueryInputInfo queryBean = GraphQLUtils.parse(info, MiniatureStationQueryInputInfo.class);
        if (queryBean == null ) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }

        PaginationBean<MiniatureStationBean> res = miniatureStationService.findMiniatureStationCondition( queryBean ) ;

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findMiniatureStationCondition", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }



    /**
     * 根据id 查询微站信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findMiniatureStation")
    public  MiniatureStationBean findMiniatureStation(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findMiniatureStation", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        String miniatureStationId = environment.getArgument("miniatureStationId");
        if ( Strings.isBlank( miniatureStationId ) ) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }

         MiniatureStationBean  res = miniatureStationService.findMiniatureStation( miniatureStationId ) ;

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findMiniatureStation", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     *  查询 辖区范围信息
     *
     * @param env 上下文环境变量
     * @return 返回操作结果
     */
    @GraphQLFieldAnnotation("findRegion")
    public List<RegionBean>  findRegion( DataFetchingEnvironment env) {

        logService.infoLog(logger, "graphql", "findRegion", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        List<String> ids = env.getArgument("ids");

        List<RegionBean>  res = regionService.findRegion( ids ) ;

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findRegion", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;

    }


    /**
     *  查询车辆人员信息
     *
     * @param env 上下文环境变量
     * @return 返回操作结果
     */
    @GraphQLFieldAnnotation("findVehiclePersonsByOrganizationId")
    public List<VehiclePersonsBean>  findVehiclePersonsByOrganizationId( DataFetchingEnvironment env) {

        logService.infoLog(logger, "graphql", "findVehiclePersonsByOrganizationId", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
         String  organizationId = env.getArgument("organizationId");
        if ( Strings.isBlank( organizationId ) ) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        List<VehiclePersonsBean>  res = vehiclePersonService.findVehiclePersonsByOrganizationId( organizationId   ) ;

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findVehiclePersonsByOrganizationId", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;

    }



    /**
     *  获得可接警机构信息
     *
     * @param env 上下文环境变量
     * @return 返回操作结果
     */
    @GraphQLFieldAnnotation("findReceiveOrganization")
    public List<OrganizationBean>  findReceiveOrganization( DataFetchingEnvironment env) {

        logService.infoLog(logger, "graphql", "findReceiveOrganization", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        List<OrganizationBean>  res = organizationOtherService.findReceiveOrganization(   ) ;

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findReceiveOrganization", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;

    }



    /**
     *  根据 机构 人员名称模糊匹配 获得人员通信录
     *
     * @param environment 上下文环境变量
     * @return 返回操作结果
     */
    @GraphQLFieldAnnotation("findPersonMailCondition")
    public List<PersonMailBean>  findPersonMailCondition( DataFetchingEnvironment environment) {

        logService.infoLog(logger, "graphql", "findPersonMailCondition", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        String organizationId = environment.getArgument("organizationId");
        String keyword = environment.getArgument("keyword");
        //参数判断
        if (Strings.isBlank(organizationId) ) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }

        List<PersonMailBean>  res = personService.findPersonMailCondition( organizationId , keyword   ) ;

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findPersonMailCondition", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;

    }
    /**
     * 根据条件 查询联勤保障单位
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findUnitJointPage")
    public PaginationBean<UnitJointServiceBean> findUnitJointPage(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findMiniatureStationCondition", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        UnitJointServiceQueryInputinfo queryBean = GraphQLUtils.parse(info, UnitJointServiceQueryInputinfo.class);
        if (queryBean == null ) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }

        PaginationBean<UnitJointServiceBean> res = unitJointServiceService.findUnitJointPage( queryBean ) ;

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findMiniatureStationCondition", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }
    /**
     * 根据条件 查询应急联动单位
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findUnitEmergencyPage")
    public PaginationBean<UnitEmergencyBean> findUnitEmergencyPage(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findMiniatureStationCondition", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        UnitEmergencyQueryInputinfo queryBean = GraphQLUtils.parse(info, UnitEmergencyQueryInputinfo.class);
        if (queryBean == null ) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }

        PaginationBean<UnitEmergencyBean> res = unitEmergencyService.findUnitEmergencyPage( queryBean ) ;

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findMiniatureStationCondition", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

}
