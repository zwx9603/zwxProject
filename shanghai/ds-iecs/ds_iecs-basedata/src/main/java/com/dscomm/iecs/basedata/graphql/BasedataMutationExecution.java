package com.dscomm.iecs.basedata.graphql;


import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.exception.BasedataException;
import com.dscomm.iecs.basedata.graphql.inputbean.*;
import com.dscomm.iecs.basedata.graphql.typebean.*;
import com.dscomm.iecs.basedata.service.*;
import graphql.schema.DataFetchingEnvironment;
import org.apache.logging.log4j.util.Strings;
import org.mx.StringUtils;
import org.mx.service.rest.graphql.GraphQLAnnotationExecution;
import org.mx.service.rest.graphql.GraphQLFieldAnnotation;
import org.mx.service.rest.graphql.GraphQLUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 描述:基础数据模块graphql增删改操作执行类
 *
 * @author YangFuxi
 * Date Time 2019/7/21 23:12
 */
@Component("basedataMutationExecution")
public class BasedataMutationExecution implements GraphQLAnnotationExecution {

    private static final Logger logger = LoggerFactory.getLogger(BasedataMutationExecution.class);
    private LogService logService;
    private VehicleGarageMappingService vehicleGarageMappingService ;

    private SystemConfigurationService systemConfigurationService;
    private PlanService planService;
    private KeyUnitService keyUnitService;
    private MiniatureStationService miniatureStationService ;
    private VehiclePersonService vehiclePersonService ;
    private OnDutyService onDutyService ;

    @Autowired
    public BasedataMutationExecution(LogService logService , SystemConfigurationService systemConfigurationService ,
                                     VehicleGarageMappingService vehicleGarageMappingService,
                                     PlanService planService,KeyUnitService keyUnitService ,
                                     MiniatureStationService miniatureStationService ,
                                     VehiclePersonService vehiclePersonService ,
                                     OnDutyService onDutyService
    ) {
        this.logService = logService;
        this.systemConfigurationService = systemConfigurationService;
        this.vehicleGarageMappingService = vehicleGarageMappingService ;
        this.planService = planService;
        this.keyUnitService = keyUnitService;
        this.miniatureStationService = miniatureStationService ;
        this.vehiclePersonService = vehiclePersonService ;
        this.onDutyService = onDutyService ;


    }


    @Override
    public String getTypeName() {
        return "Mutation";
    }


    /**
     * 保存修改系统配置
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("saveSystemConfiguration")
    public List<SystemConfigurationBean> saveSystemConfiguration(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveSystemConfiguration", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        SystemConfigurationSaveListInputInfo inputInfo = GraphQLUtils.parse(info, SystemConfigurationSaveListInputInfo.class);

        if (inputInfo == null || null == inputInfo.getSystemConfigurationSaveInputInfos() || inputInfo.getSystemConfigurationSaveInputInfos().size() < 1) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        //执行逻辑处理
        List<SystemConfigurationBean> res = systemConfigurationService.saveSystemConfiguration(inputInfo);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveSystemConfiguration", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 根据系统配置id 是否启用 系统配置
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("enabledSystemConfig")
    public SystemConfigurationBean  enabledSystemConfig(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "enabledSystemConfig", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        String systemConfigId = environment.getArgument("systemConfigId");
        Integer valid = environment.getArgument("valid");

        if ( Strings.isBlank( systemConfigId ) || valid == null ) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        //执行逻辑处理
        SystemConfigurationBean  res = systemConfigurationService.enabledSystemConfig(systemConfigId , valid );

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "enabledSystemConfig", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }



    /**
     * 保存中队车辆与车库门对应关系
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("saveVehicleGarageMapping")
    public List<VehicleGarageMappingBean> saveVehicleGarageMapping(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveVehicleGarageMapping", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        VehicleGarageMappingSaveListInputInfo queryBean = GraphQLUtils.parse(info, VehicleGarageMappingSaveListInputInfo.class);
        if (queryBean == null || queryBean.getVehicleGarageMappingSaveInputInfos() == null || queryBean.getVehicleGarageMappingSaveInputInfos().size() < 1 ) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        //执行逻辑处理
        List<VehicleGarageMappingBean> res = vehicleGarageMappingService.saveVehicleGarageMapping(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveVehicleGarageMapping", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 重点单位预案保存修改
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("saveKeyUnitPlan")
    public PlanBean saveKeyUnitPlan(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveKeyUnitPlan", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        PlanSaveInputInfo queryBean = GraphQLUtils.parse(info, PlanSaveInputInfo.class);
        if (queryBean == null || Strings.isBlank( queryBean.getKeyUnitId() ) ||
         queryBean.getPlanDispatchSaveInputInfo() == null || queryBean.getPlanDispatchSaveInputInfo().size() < 1 ) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        //执行逻辑处理
        PlanBean res = planService.saveKeyUnitPlan(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveKeyUnitPlan", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 删除预案
     * */
     @GraphQLFieldAnnotation("deletePlan")
    public Boolean deletePlan(DataFetchingEnvironment environment){
         logService.infoLog(logger, "graphql", "deletePlan", "graphql is started...");
         Long start = System.currentTimeMillis();

         //参数判断
         List<String> ids = environment.getArgument("ids");
         if (ids == null || ids.size()<1){
             logService.infoLog(logger, "service", "deletePlan", "inputInfo is blank.");
         }

         Boolean res = planService.deletePlan(ids);

         Long end = System.currentTimeMillis();
         logService.infoLog(logger, "graphql", "deletePlan", String.format("graphql is finished,execute time is :%sms", end - start));
         return res;
     }

     /**保存重点单位*/
    @GraphQLFieldAnnotation("saveKeyUnit")
    public KeyUnitBean saveKeyUnit(DataFetchingEnvironment environment){
        logService.infoLog(logger, "graphql", "saveKeyUnit", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        KeyUnitSaveInputInfo inputInfo = GraphQLUtils.parse(info, KeyUnitSaveInputInfo.class);
        if (inputInfo == null || StringUtils.isBlank(inputInfo.getUnitName()) || StringUtils.isBlank(inputInfo.getJurisdictionOrganizationId())){
            logService.infoLog(logger, "service", "saveKeyUnit", "inputInfo is blank.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }

        KeyUnitBean res = keyUnitService.saveKeyUnit(inputInfo);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveKeyUnit", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**删除重点单位*/
    @GraphQLFieldAnnotation("deleteKeyUnit")
    public Boolean deleteKeyUnit(DataFetchingEnvironment environment){
        logService.infoLog(logger, "graphql", "deleteKeyUnit", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        List<String> ids = environment.getArgument("ids");
        if (ids == null || ids.size()<1){
            logService.infoLog(logger, "service", "deleteKeyUnit", "inputInfo is blank.");
        }

        Boolean res = keyUnitService.deleteKeyUnit(ids);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "deleteKeyUnit", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 保存修改微型消防站信息
     * */
    @GraphQLFieldAnnotation("saveMiniatureStation")
    public MiniatureStationBean saveMiniatureStation(DataFetchingEnvironment environment){
        logService.infoLog(logger, "graphql", "saveMiniatureStation", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        MiniatureStationSaveInputInfo queryBean = GraphQLUtils.parse(info, MiniatureStationSaveInputInfo.class);
        if (queryBean == null || Strings.isBlank( queryBean.getStationName() )) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        MiniatureStationBean res = miniatureStationService.saveMiniatureStation( queryBean ) ;

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveMiniatureStation", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 根据 id 集合移除机构信息
     * */
    @GraphQLFieldAnnotation("removeMiniatureStation")
    public Boolean removeMiniatureStation(DataFetchingEnvironment environment){
        logService.infoLog(logger, "graphql", "removeMiniatureStation", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        List<String> ids = environment.getArgument("ids");
        if (ids == null || ids.size()<1 ){
            logService.infoLog(logger, "service", "removeMiniatureStation", "inputInfo is blank.");
        }

        Boolean res = miniatureStationService.removeMiniatureStation( ids);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "removeMiniatureStation", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 微型消防站 更新经纬度 地址信息
     * */
    @GraphQLFieldAnnotation("saveMiniatureStationLocation")
    public MiniatureStationBean saveMiniatureStationLocation(DataFetchingEnvironment environment){
        logService.infoLog(logger, "graphql", "saveMiniatureStationLocation", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        MiniatureStationSaveInputInfo queryBean = GraphQLUtils.parse(info, MiniatureStationSaveInputInfo.class);
        if (queryBean == null || Strings.isBlank( queryBean.getLongitude() ) || Strings.isBlank( queryBean.getLatitude() )) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        MiniatureStationBean res = miniatureStationService.saveMiniatureStationLocation( queryBean ) ;

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveMiniatureStationLocation", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     *  保存车辆人员信息
     * */
    @GraphQLFieldAnnotation("saveVehiclePersons")
    public Boolean saveVehiclePersons(DataFetchingEnvironment environment){
        logService.infoLog(logger, "graphql", "saveVehiclePersons", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        VehiclePersonsSaveInputInfo inputInfo = GraphQLUtils.parse(info, VehiclePersonsSaveInputInfo.class);

        if (inputInfo == null || inputInfo.getVehiclePersonsList() == null || inputInfo.getVehiclePersonsList().size() < 1  ) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        Boolean res = vehiclePersonService.saveVehiclePersons( inputInfo ) ;

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveVehiclePersons", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }



    /**
     *  更新 值班信息 （ 主要更新 联系人 联系方式 ）
     * */
    @GraphQLFieldAnnotation("updateOnDuty")
    public Boolean updateOnDuty(DataFetchingEnvironment environment){
        logService.infoLog(logger, "graphql", "updateOnDuty", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        OnDutySummarySaveInputInfo inputInfo = GraphQLUtils.parse(info, OnDutySummarySaveInputInfo.class);

        if ( inputInfo == null ||  Strings.isBlank( inputInfo.getId() ) ) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        Boolean res = onDutyService.updateOnDuty( inputInfo ) ;

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "updateOnDuty", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

}
