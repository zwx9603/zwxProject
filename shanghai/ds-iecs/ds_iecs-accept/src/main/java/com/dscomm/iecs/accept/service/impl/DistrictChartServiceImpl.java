package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.repository.DistrictChartRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.typebean.DistrictChartBean;
import com.dscomm.iecs.accept.graphql.typebean.DistrictChartOnDutyBean;
import com.dscomm.iecs.accept.graphql.typebean.DutyPowerBean;
import com.dscomm.iecs.accept.service.DistrictChartService;
import com.dscomm.iecs.agent.service.UserService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.dal.po.EquipmentVehicleEntity;
import com.dscomm.iecs.basedata.graphql.inputbean.EquipmentVehicleQueryInputInfo;
import com.dscomm.iecs.basedata.graphql.typebean.EquipmentVehicleBean;
import com.dscomm.iecs.basedata.graphql.typebean.OrganizationBean;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.basedata.service.VehicleService;
import com.dscomm.iecs.basedata.utils.EquipmentTransformUtil;
import com.dscomm.iecs.ext.vehicle.status.VEHICLE_STATUS_CDZQ;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class DistrictChartServiceImpl implements DistrictChartService {

    private static final Logger logger = LoggerFactory.getLogger(DistrictChartServiceImpl.class);
    private LogService logService;
    private OrganizationService organizationService;
    private GeneralAccessor accessor;
    private Environment env;
    private DistrictChartRepository districtChartRepository;
    private VehicleService vehicleService;
    private DictionaryService dictionaryService;
    private UserService userService ;

    private List<String> dics;

    @Autowired
    public DistrictChartServiceImpl(@Qualifier("generalAccessorJpa") GeneralAccessor accessor,
                                    DistrictChartRepository districtChartRepository,
                                    Environment env, OrganizationService organizationService, LogService logService,VehicleService vehicleService,
                                    DictionaryService dictionaryService , UserService userService) {
        this.accessor = accessor;
        this.districtChartRepository = districtChartRepository;
        this.env = env;
        this.organizationService = organizationService;
        this.logService = logService;
        this.vehicleService = vehicleService;
        this.dictionaryService = dictionaryService;
        this.userService = userService ;

        dics = new ArrayList<>(Arrays.asList("WLCLLX", "WLCLDJ", "WLCLZT", "YS", "JLDW" , "QCLX","PMLX"  ));
    }

    @Override
    @Transactional(readOnly = true)
    public DistrictChartOnDutyBean findDistrictChartOnDutyBean() {
        try {
            logService.infoLog(logger, "service", "findDistrictChartOnDutyBean", "service is started...");
            Long logStart = System.currentTimeMillis();

            UserInfo userInfo = userService.getUserInfo() ;

            DistrictChartOnDutyBean bean = new DistrictChartOnDutyBean();
            List<DistrictChartBean> districtChartBeans = new ArrayList<>();
            Integer vehicleNum = 0;// 车辆数
            Integer fullTimeNum = 0;// 企业专职队数 FIXME:专职队暂时归零
            Integer orgNum = 0;//执勤中队数
            Integer fireStationNum = 0;// 微型消防站数量  FIXME:微站没表，暂时归零
            List<OrganizationBean> organizationBeans = organizationService.findOrganizationNatureAll(userInfo.getOrgId() ,2);//获取全部大队
            if (organizationBeans != null&& organizationBeans.size()>0){
                for (OrganizationBean organization:organizationBeans){
                    DistrictChartBean districtChartBean = findDistrictChartBean(organization.getId());
                    if (districtChartBean != null && !StringUtils.isBlank(districtChartBean.getOrganizationId())){
                        districtChartBeans.add(districtChartBean);
                        orgNum += districtChartBean.getOrganizationNum();
                        vehicleNum += districtChartBean.getVechileNum();
                        fullTimeNum += districtChartBean.getFullTimeNum();
                        fireStationNum += districtChartBean.getFireStationNum();
                    }
                }

            }
            bean.setDistrictChartBean(districtChartBeans);
            bean.setOrganizationNum(orgNum);
            bean.setVehicleNum(vehicleNum);
            bean.setFullTimeNum(fullTimeNum);
            bean.setFireStationNum(fireStationNum);
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findDistrictChartOnDutyBean", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return bean;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findDistrictChartOnDutyBean", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_ACCEPTANCE_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public DistrictChartBean findDistrictChartBean(String organizationId) {
        if (Strings.isBlank(organizationId)) {
            logService.infoLog(logger, "service", "findDistrictChartBean", "orgId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findDistrictChartBean", "service is started...");
            Long logStart = System.currentTimeMillis();
            DistrictChartBean bean = new DistrictChartBean();
            List<OrganizationBean> orgBeanList = organizationService.findOrganizationChildren(organizationId);//查询大队下全部的中队
            OrganizationBean organizationBean = organizationService.findOrganizationByOrganizationId(organizationId);
            List<DutyPowerBean> dutyPowerBeans = new ArrayList<>();
            // 统计各 大队 中队 专职队的车辆数  机构数
            Integer vehicleNum = 0;//车辆数
            Integer fullTimeNum = 0;//专属队数 FIXME:专职队暂时归零
            Integer orgNum = 0;//机构数
            Integer fireStationNum = 0;//微站数 FIXME:微站没表，暂时归零
            if (orgBeanList != null && orgBeanList.size() > 0) {
                orgNum = orgBeanList.size();
                for (OrganizationBean organization : orgBeanList) {//只查大队下的中队，不查大队本身
                    if (!organizationId.equals(organization.getId())) {
                        DutyPowerBean dutyPowerBean = findDutyPower(organization.getId());
                        if (dutyPowerBean != null){
                            dutyPowerBeans.add(dutyPowerBean);
                            vehicleNum += dutyPowerBean.getVechileNum();
                        }
                    }
                }

            }
            bean.setOrganizationId(organizationBean.getId());
            bean.setOrganizationName(organizationBean.getOrganizationName());
            bean.setDistrictCode(organizationBean.getDistrictCode());
            bean.setDistrictName(organizationBean.getDistrictName());
            bean.setVechileNum(vehicleNum);
            bean.setFullTimeNum(fullTimeNum);
            bean.setOrganizationNum(orgNum);
            bean.setFireStationNum(fireStationNum);
            bean.setDutyPowerBean(dutyPowerBeans);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findDistrictChartBean", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return bean;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findDistrictChartBean", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_ACCEPTANCE_FAIL);
        }
    }

    /**
     * 值勤力量
     *
     * @param organizationId 机构id
     * @return 值勤车辆中队微站专属队统计
     */
    private DutyPowerBean findDutyPower(String organizationId) {

        EquipmentVehicleQueryInputInfo inputInfo = new EquipmentVehicleQueryInputInfo();
        inputInfo.setScopeSquadronId(organizationId);
        inputInfo.setScopeType(1);
        inputInfo.setWhetherPage(false);
        List<String> codeList  = new ArrayList<>();
        codeList.add( VEHICLE_STATUS_CDZQ.getCode());
        inputInfo.setVehicleStatusCodes(codeList);

        GeneralAccessor.ConditionGroup carCondition = GeneralAccessor.ConditionGroup.and(
                GeneralAccessor.ConditionTuple.eq("valid",1),
                GeneralAccessor.ConditionTuple.eq("organizationId",organizationId),
                GeneralAccessor.ConditionTuple.eq("vehicleStatusCode", VEHICLE_STATUS_CDZQ.getCode())
        );
        //获取名称-代码对应字典
        Map<String, Map<String, String>> dicsMap  = dictionaryService.findDictionaryMap(dics) ;
        //机构数据
        Map<String, String> organizationNameMap =  organizationService.findOrganizationNameMap();
        List<EquipmentVehicleEntity> entities = accessor.find(carCondition,EquipmentVehicleEntity.class);
        List<EquipmentVehicleBean> vehicleBean  = new ArrayList<>();
        if (entities!=null&&entities.size()>0){
            for (EquipmentVehicleEntity entity:entities
            ) {
                EquipmentVehicleBean bean = EquipmentTransformUtil.transform(entity, dicsMap, organizationNameMap );
                vehicleBean.add(bean);
            }
        }

		OrganizationBean organizationBean = organizationService.findOrganizationByOrganizationId(organizationId);

        DutyPowerBean dutyPowerBean = new DutyPowerBean();
        dutyPowerBean.setVehicleBean(vehicleBean);
        dutyPowerBean.setVechileNum(vehicleBean!= null?vehicleBean.size():0);
        dutyPowerBean.setOrganizationName(organizationBean.getOrganizationName());
        return dutyPowerBean;
    }


}
