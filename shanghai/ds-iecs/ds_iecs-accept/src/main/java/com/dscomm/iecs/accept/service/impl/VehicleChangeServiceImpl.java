package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.service.VehicleChangeService;
import com.dscomm.iecs.accept.service.VehicleStatusChangeCheckService;
import com.dscomm.iecs.accept.service.VehicleStatusChangeService;
import com.dscomm.iecs.agent.service.UserService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.exception.BasedataException;
import com.dscomm.iecs.basedata.graphql.inputbean.EquipmentVehicleExpandInputInfo;
import com.dscomm.iecs.basedata.graphql.typebean.EquipmentVehicleBean;
import com.dscomm.iecs.basedata.graphql.typebean.OrganizationBean;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.basedata.service.VehicleService;
import com.dscomm.iecs.ext.organization.naturehead.ORGANIZATION_NATURE_HEAD_XJZD;
import com.dscomm.iecs.ext.vehicle.status.VEHICLE_STATUS_CDZQ;
import com.dscomm.iecs.ext.vehicle.status.VEHICLE_STATUS_DM;
import org.apache.logging.log4j.util.Strings;
import org.mx.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 描述：车辆变动 服务类实现
 */
@Component("vehicleChangeServiceImpl")
public class VehicleChangeServiceImpl implements VehicleChangeService {
    private static final Logger logger = LoggerFactory.getLogger(VehicleChangeServiceImpl.class);
    private LogService logService;
    private Environment env;
    private VehicleService vehicleService ;
    private VehicleStatusChangeService vehicleStatusChangeService ;
    private OrganizationService organizationService;
    private VehicleStatusChangeCheckService vehicleStatusChangeCheckService;
    private UserService userService ;

    /**
     * 默认的构造函数
     */
    @Autowired
    public VehicleChangeServiceImpl(LogService logService, Environment env,
//                                    @Qualifier("generalAccessor") GeneralAccessor accessor,
                                    VehicleService vehicleService  , VehicleStatusChangeService vehicleStatusChangeService ,
                                     OrganizationService organizationService,

                                    VehicleStatusChangeCheckService vehicleStatusChangeCheckService,UserService userService
    ) {
        this.logService = logService;
        this.env = env;
        this.vehicleService = vehicleService ;
        this.vehicleStatusChangeService = vehicleStatusChangeService ;

        this.organizationService = organizationService;
        this.vehicleStatusChangeCheckService = vehicleStatusChangeCheckService;
        this.userService = userService;

    }


    /**
     * {@inheritDoc}
     *
     * @see #updateVehicleExpandInfo(EquipmentVehicleExpandInputInfo)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public EquipmentVehicleBean updateVehicleExpandInfo(EquipmentVehicleExpandInputInfo inputInfo) {
        if (inputInfo == null || Strings.isBlank(inputInfo.getId())) {
            logService.infoLog(logger, "service", "updateVehicleExpandInfo", "EquipmentVehicleExpandInputInfo is null or id is blank.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
         
        try {
            logService.infoLog(logger, "service", "updateVehicleExpandInfo", "service is started...");
            Long logStart = System.currentTimeMillis();
            String organizationId = organizationService.findOrganizationIdsByCode(userService.getUserInfo().getOrgCode());
            inputInfo.setOrganizationId(organizationId);

            EquipmentVehicleBean vehicleBean = vehicleService.findVehicle(inputInfo.getId() );
            // 配置是否审核
            boolean isVerifyVehicle = Boolean.parseBoolean(env.getProperty("isVerifyVehicle"));

            OrganizationBean organizationBean = organizationService.findOrganizationByOrganizationId(inputInfo.getOrganizationId());
            String natureCode = organizationBean.getOrganizationNatureCode();
            logService.infoLog(logger, "service", "updateVehicleExpandInfo", "isVerifyVehicle{}"+isVerifyVehicle+"[] natureCode:" +natureCode+"[]oldStatus:"+vehicleBean.getVehicleStatusCode()
            +"[]newStatus:"+inputInfo.getVehicleStatusCode());
            // 其中特殊车辆状态 待命0200  执勤0306 不需要审核
            if ( isVerifyVehicle
                    && !VEHICLE_STATUS_DM.getCode().equals( inputInfo.getVehicleStatusCode()  )
                    && !VEHICLE_STATUS_CDZQ.getCode().equals( inputInfo.getVehicleStatusCode()  )
                    && null != vehicleBean && !vehicleBean.getVehicleStatusCode().equals(inputInfo.getVehicleStatusCode())
                    && ORGANIZATION_NATURE_HEAD_XJZD.getCode().equals(natureCode.substring(0, 2))
                        ) {
                // 中队申请
                // 排除 车辆处警中包含状态
//                SystemConfigurationBean vehicleOnDutyStatus = systemConfigurationService.getSystemConfigByConfigType("vehicleOnDutyStatus");
//                if (Objects.nonNull(vehicleOnDutyStatus) && Objects.nonNull(vehicleOnDutyStatus.getConfigValue())){
//                    String[] values = vehicleOnDutyStatus.getConfigValue().split(",");
//                    boolean contains = Arrays.asList(values).contains(inputInfo.getVehicleStatusCode());
//                    try {
//                        if (contains){
//                            logService.infoLog(logger, "service", "updateVehicleExpandInfo", "vehicleStatusCode is vehicleOnDutyStatus.");
//                            throw new BasedataException(BasedataException.BasedataErrors.UPDATE_VEHICLE_STATUS_DUTY);
//                        }
//                    }catch (BasedataException b){
//                        throw new BasedataException(BasedataException.BasedataErrors.UPDATE_VEHICLE_STATUS_DUTY);
//                    }
//                }
                //  中队变更车辆状态时需要向支队发送申请（驻防、加油、训练、试车、验收、公务、故障、修理、保养和报废），支队审批完成后车辆状态才可变更。
                vehicleStatusChangeCheckService.auditVehicleExpand(inputInfo);
                Long logEnd = System.currentTimeMillis();
                logService.infoLog(logger, "service", "updateVehicleExpandInfo", String.format("service is finished,execute time is :%sms", logEnd - logStart));
                return  vehicleService.findVehicle( inputInfo.getId() );
            } else {
                // 大队或支队审批或直接修改
                // todo  审批
                if (!StringUtils.isBlank(inputInfo.getAuditId())){
                    Boolean aBoolean = vehicleStatusChangeCheckService.auditVehicleStatusChangeCheck(inputInfo);
                    if (!aBoolean){
                        // 审批 不通过
                        Long logEnd = System.currentTimeMillis();
                        logService.infoLog(logger, "service", "updateVehicleExpandInfo", String.format("service is finished,execute time is :%sms", logEnd - logStart));
                        return vehicleService.findVehicle( inputInfo.getId() );
                    }

                }
                // 修改车辆状态
                vehicleStatusChangeService.updateVehicleStatus( null , null , inputInfo.getId(),null,
                        inputInfo.getVehicleStatusCode() , "PC");

                EquipmentVehicleBean res = vehicleService.updateVehicleExpandInfo( inputInfo );

                Long logEnd = System.currentTimeMillis();
                logService.infoLog(logger, "service", "updateVehicleExpandInfo", String.format("service is finished,execute time is :%sms", logEnd - logStart));
                return res;
            }
        }  catch (Exception ex) {
            logService.erorLog(logger, "service", "updateVehicleExpandInfo", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.UPDATE_VEHICLE_EXPAND_INFO_FAIL);
        }
    }








}
