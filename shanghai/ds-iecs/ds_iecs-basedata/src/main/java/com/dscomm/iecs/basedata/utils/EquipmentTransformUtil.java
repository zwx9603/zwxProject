package com.dscomm.iecs.basedata.utils;

import com.dscomm.iecs.base.enums.EnumBean;
import com.dscomm.iecs.base.utils.BasicEnumUtils;
import com.dscomm.iecs.basedata.dal.po.*;
import com.dscomm.iecs.basedata.graphql.inputbean.VehicleGarageMappingSaveInputInfo;
import com.dscomm.iecs.basedata.graphql.typebean.*;
import com.dscomm.iecs.basedata.service.enums.VehicleNatureEnum;
import org.apache.logging.log4j.util.Strings;
import org.mx.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述 : 装备、车辆、器材等类数据转换工具
 * Date Time : 2020/05/21 15：38
 */
public class EquipmentTransformUtil {


    /**
     * 车辆 转换
     *
     * @param source 车辆状态PO
     * @return 车辆状态BO
     */
    public static EquipmentVehicleBean transform(EquipmentVehicleEntity source,
                                                 Map<String, Map<String, String>> dicsMap, Map<String, String> organizationNameMap) {

        if(organizationNameMap==null){
            organizationNameMap=new HashMap<>();
        }
        if (null != source) {
            EquipmentVehicleBean target = new EquipmentVehicleBean();
            target.setId(source.getId());
            target.setVehicleCode(source.getVehicleCode());
            target.setVehicleName(source.getVehicleName());
            target.setVehicleShortName(source.getVehicleShortName());

            target.setVehicleTypeCode(source.getVehicleTypeCode());
            if(!StringUtils.isBlank(source.getVehicleTypeCode())&&dicsMap.get("WLCLLX")!=null){
                target.setVehicleTypeName(dicsMap.get("WLCLLX").get(source.getVehicleTypeCode()));
            }



            target.setVehicleLevelCode(source.getVehicleLevelCode());
            if(!StringUtils.isBlank(source.getVehicleLevelCode())&&dicsMap.get("WLCLDJ")!=null){
                target.setVehicleLevelName(dicsMap.get("WLCLDJ").get(source.getVehicleLevelCode()));
            }



            target.setOrganizationId(source.getOrganizationId());
            if(!StringUtils.isBlank(source.getOrganizationId())){
                target.setOrganizationName(organizationNameMap.get(source.getOrganizationId()));
            }



            target.setVehicleStatusCode(source.getVehicleStatusCode());
            if(!StringUtils.isBlank(source.getVehicleStatusCode())&&dicsMap.get("WLCLZT")!=null){
                target.setVehicleStatusName(dicsMap.get("WLCLZT").get(source.getVehicleStatusCode()));
            }



            target.setVehicleNumber(source.getVehicleNumber());
            target.setGpsNumber(source.getGpsNumber());
            target.setLocationNumber(source.getLocationNumber());
            target.setRadioCallSign(source.getRadioCallSign());
            target.setRemarks(source.getRemarks());
            target.setSingleEquipmentCode(source.getSingleEquipmentCode());
            target.setEquipmentCode(source.getEquipmentCode());
            target.setSuperEquipmentCode(source.getSuperEquipmentCode());
            target.setSingleEquipmentStautsCode(source.getSingleEquipmentStautsCode());

            target.setColor(source.getColor());
            if(!StringUtils.isBlank(source.getColor())&&dicsMap.get("YS")!=null){
                target.setColorName(dicsMap.get("YS").get(source.getColor()));
            }



            target.setTrademark(source.getTrademark());
            target.setContacts(source.getContacts());
            target.setContactsId(source.getContactsId());
            target.setContactsName(source.getContactsName());
            target.setContactsPhone(source.getContactsPhone());
            target.setEffectiveTime(source.getEffectiveTime());
            target.setWhetherAssembling(source.getWhetherAssembling());
            target.setWhetherDetachment(source.getWhetherDetachment());
            target.setWhetherCorps(source.getWhetherCorps());
            target.setIncidentNumber(source.getIncidentNumber());
            target.setSpecificationsNumber(source.getSpecificationsNumber());
            target.setAssetNumber(source.getAssetNumber());
            target.setReferencePrice(source.getReferencePrice());
            target.setCountryCode(source.getCountryCode());
            target.setManufacturerCode(source.getManufacturerCode());
            target.setManufacturerName(source.getManufacturerName());
            target.setAgent(source.getAgent());
            target.setAfterService(source.getAfterService());
            target.setProductionTime(source.getProductionTime());
            target.setEquipTime(source.getEquipTime());
            target.setScrapTime(source.getScrapTime());
            target.setCumulativeTransportTime(source.getCumulativeTransportTime());
            target.setCumulativeUseFrequency(source.getCumulativeUseFrequency());
            target.setMaintenanceTime(source.getMaintenanceTime());
            target.setNextMaintenanceTime(source.getNextMaintenanceTime());
            target.setFrameNo(source.getFrameNo());

            target.setEngineNumber(source.getEngineNumber());
            target.setBatchNo(source.getBatchNo());
            target.setMeasurementCode(source.getMeasurementCode());
            if(!StringUtils.isBlank(source.getMeasurementCode())&&dicsMap.get("JLDW")!=null){
                target.setMeasurementName(dicsMap.get("JLDW").get(source.getMeasurementCode()));
            }


            target.setWhetherTraining(source.getWhetherTraining());
            target.setLockingStatus(source.getLockingStatus());
            target.setCheckResultStatus(source.getCheckResultStatus());

            target.setWaterCarrier(source.getWaterCarrier());
            target.setCarrierBubble(source.getCarrierBubble());
            target.setMaxFlow(source.getMaxFlow());
            target.setMinFlow(source.getMinFlow());
            target.setMaxLiftingHeight(source.getMaxLiftingHeight());
            target.setLiftingWeight(source.getLiftingWeight());
            target.setPassengersNum(source.getPassengersNum());
            target.setTraction(source.getTraction());
            target.setHeightLimit(source.getHeightLimit());
            target.setMaxSmokeDischarge(source.getMaxSmokeDischarge());

            target.setHandLiftPump(source.getHandLiftPump());
            target.setPumpFlow(source.getPumpFlow());
            target.setFireMonitorFlow(source.getFireMonitorFlow());
            target.setPumpFlow(source.getPumpFlow());
            target.setVehicleNature(source.getVehicleNature());
            String nameByCode = BasicEnumUtils.getNameByCode(VehicleNatureEnum.class, source.getVehicleNature());
            target.setVehicleNatureName(nameByCode);
            String foam = source.getFoam();
            target.setFoam(foam);
            if (Strings.isNotBlank(foam)) {
                String[] foams = foam.split(",");
                String foamsName = "";
                for (String key : foams) {
                    String foamName = dicsMap.get("PMLX").get(key);
                    if (Strings.isNotBlank(foamName)) {
                        foamsName = foamsName + foamName;
                    }
                }
                target.setFoamName(foamsName);
            }

            String equipment = source.getEquipment();
            target.setEquipment(equipment);
            if (Strings.isNotBlank(equipment)) {
                String[] equipments = equipment.split(",");
                String equipmentsName = "";
                for (String key : equipments) {
                    String equipmentName = dicsMap.get("QCLX").get(key);
                    if (Strings.isNotBlank(equipmentName)) {
                        equipmentsName = equipmentsName + equipmentName;
                    }
                }
                target.setEquipmentName(equipmentsName);
            }

            target.setVehicleDesc(source.getVehicleDesc());
            target.setVehicleIdentification(source.getVehicleIdentification());
            target.setLocationId(source.getLocationId());
            target.setLocationSIMNumber(source.getLocationSIMNumber());
            target.setCarrierBubbleA(source.getCarrierBubbleA());
            target.setCarrierBubbleB(source.getCarrierBubbleB());

            //根据车辆前两位排序
            target.setVehicleOrderNum(source.getVehicleOrderNum());

            return target;
        }
        return null;
    }

    /**
     * 车辆 转换 ( 包含车库 )
     *
     * @param source 车辆状态PO
     * @return 车辆状态BO
     */
    public static EquipmentVehicleBean transform(Object[] source, Map<String, Map<String, String>> dicsMap,
                                                 Map<String, String> organizationNameMap, Map<String, OrganizationBean> organizationMap) {
        if (null != source) {
            EquipmentVehicleEntity vehicleEntity = (EquipmentVehicleEntity) source[0];
            VehicleGarageMappingEntity vehicleGarageMappingEntity = (VehicleGarageMappingEntity) source[1];
            return transform(vehicleEntity,vehicleGarageMappingEntity, dicsMap, organizationNameMap, organizationMap);
        }
        return null;
    }

    public static EquipmentVehicleBean transform(EquipmentVehicleEntity vehicleEntity,VehicleGarageMappingEntity vehicleGarageMappingEntity, Map<String, Map<String, String>> dicsMap,
                                                 Map<String, String> organizationNameMap, Map<String, OrganizationBean> organizationMap) {

        if(organizationNameMap==null){
            organizationNameMap=new HashMap<>();
        }

        if (null != vehicleEntity) {
            EquipmentVehicleBean target = new EquipmentVehicleBean();

            target.setId(vehicleEntity.getId());
            target.setValid(vehicleEntity.getValid());
            target.setVehicleCode(vehicleEntity.getVehicleCode());
            target.setVehicleName(vehicleEntity.getVehicleName());
            target.setVehicleShortName(vehicleEntity.getVehicleShortName());

            target.setVehicleTypeCode(vehicleEntity.getVehicleTypeCode());
            if(!StringUtils.isBlank(target.getVehicleTypeCode())&&dicsMap.get("WLCLLX")!=null){
                target.setVehicleTypeName(dicsMap.get("WLCLLX").get(target.getVehicleTypeCode()));
            }



            target.setVehicleLevelCode(vehicleEntity.getVehicleLevelCode());
            if(!StringUtils.isBlank(target.getVehicleLevelCode())&&dicsMap.get("WLCLDJ")!=null){
                target.setVehicleLevelName(dicsMap.get("WLCLDJ").get(target.getVehicleLevelCode()));
            }


            target.setOrganizationId(vehicleEntity.getOrganizationId());
            if(!StringUtils.isBlank(target.getOrganizationId())){
                target.setOrganizationName(organizationNameMap.get(target.getOrganizationId()));
            }



            OrganizationBean organizationBean = organizationMap.get(target.getOrganizationId());
            if (organizationBean != null) {
                String nature = organizationBean.getOrganizationNatureCode();
                //若车辆所属机构性质是大队，则所属大队填自己
                if (Strings.isNotBlank(nature) && nature.startsWith("05")) {
                    target.setBrigadeOrganizationId(vehicleEntity.getOrganizationId());
                    target.setBrigadeOrganizationName(organizationBean.getOrganizationName());
                }
                //若车辆所属机构性质是中队，则所属大队是其父级
                if (Strings.isNotBlank(nature) && nature.startsWith("09")) {
                    target.setBrigadeOrganizationId(organizationBean.getOrganizationParentId());
                    OrganizationBean parentOrg = organizationMap.get(organizationBean.getOrganizationParentId());
                    if (parentOrg != null) {
                        target.setBrigadeOrganizationName(parentOrg.getOrganizationName());
                    }
                }
            }

            target.setVehicleStatusCode(vehicleEntity.getVehicleStatusCode());
            if(!StringUtils.isBlank(target.getVehicleStatusCode())&&dicsMap.get("WLCLZT")!=null){
                target.setVehicleStatusName(dicsMap.get("WLCLZT").get(target.getVehicleStatusCode()));
            }



            target.setVehicleNumber(vehicleEntity.getVehicleNumber());
            target.setGpsNumber(vehicleEntity.getGpsNumber());
            target.setLocationNumber(vehicleEntity.getLocationNumber());
            target.setRadioCallSign(vehicleEntity.getRadioCallSign());
            target.setRemarks(vehicleEntity.getRemarks());
            target.setSingleEquipmentCode(vehicleEntity.getSingleEquipmentCode());
            target.setEquipmentCode(vehicleEntity.getEquipmentCode());
            target.setSuperEquipmentCode(vehicleEntity.getSuperEquipmentCode());
            target.setSingleEquipmentStautsCode(vehicleEntity.getSingleEquipmentStautsCode());

            target.setColor(vehicleEntity.getColor());
            if(!StringUtils.isBlank(vehicleEntity.getColor())&&dicsMap.get("YS")!=null){
                target.setColorName(dicsMap.get("YS").get(vehicleEntity.getColor()));
            }



            target.setTrademark(vehicleEntity.getTrademark());
            target.setContacts(vehicleEntity.getContacts());
            target.setContactsId(vehicleEntity.getContactsId());
            target.setContactsName(vehicleEntity.getContactsName());
            target.setContactsPhone(vehicleEntity.getContactsPhone());
            target.setEffectiveTime(vehicleEntity.getEffectiveTime());
            target.setWhetherAssembling(vehicleEntity.getWhetherAssembling());
            target.setWhetherDetachment(vehicleEntity.getWhetherDetachment());
            target.setWhetherCorps(vehicleEntity.getWhetherCorps());
            target.setIncidentNumber(vehicleEntity.getIncidentNumber());
            target.setSpecificationsNumber(vehicleEntity.getSpecificationsNumber());
            target.setAssetNumber(vehicleEntity.getAssetNumber());
            target.setReferencePrice(vehicleEntity.getReferencePrice());
            target.setCountryCode(vehicleEntity.getCountryCode());
            target.setManufacturerCode(vehicleEntity.getManufacturerCode());
            target.setManufacturerName(vehicleEntity.getManufacturerName());
            target.setAgent(vehicleEntity.getAgent());
            target.setAfterService(vehicleEntity.getAfterService());
            target.setProductionTime(vehicleEntity.getProductionTime());
            target.setEquipTime(vehicleEntity.getEquipTime());
            target.setScrapTime(vehicleEntity.getScrapTime());
            target.setCumulativeTransportTime(vehicleEntity.getCumulativeTransportTime());
            target.setCumulativeUseFrequency(vehicleEntity.getCumulativeUseFrequency());
            target.setMaintenanceTime(vehicleEntity.getMaintenanceTime());
            target.setNextMaintenanceTime(vehicleEntity.getNextMaintenanceTime());
            target.setFrameNo(vehicleEntity.getFrameNo());

            target.setEngineNumber(vehicleEntity.getEngineNumber());
            target.setBatchNo(vehicleEntity.getBatchNo());
            target.setMeasurementCode(vehicleEntity.getMeasurementCode());
            if(!StringUtils.isBlank(target.getMeasurementCode())&&dicsMap.get("JLDW")!=null){
                target.setMeasurementName(dicsMap.get("JLDW").get(target.getMeasurementCode()));
            }


            target.setWhetherTraining(vehicleEntity.getWhetherTraining());
            target.setLockingStatus(vehicleEntity.getLockingStatus());
            target.setCheckResultStatus(vehicleEntity.getCheckResultStatus());

            target.setWaterCarrier(vehicleEntity.getWaterCarrier());
            target.setCarrierBubble(vehicleEntity.getCarrierBubble());
            target.setMaxFlow(vehicleEntity.getMaxFlow());
            target.setMinFlow(vehicleEntity.getMinFlow());
            target.setMaxLiftingHeight(vehicleEntity.getMaxLiftingHeight());
            target.setLiftingWeight(vehicleEntity.getLiftingWeight());
            target.setPassengersNum(vehicleEntity.getPassengersNum());
            target.setTraction(vehicleEntity.getTraction());
            target.setHeightLimit(vehicleEntity.getHeightLimit());
            target.setMaxSmokeDischarge(vehicleEntity.getMaxSmokeDischarge());

            target.setHandLiftPump(vehicleEntity.getHandLiftPump());
            target.setPumpFlow(vehicleEntity.getPumpFlow());
            target.setFireMonitorFlow(vehicleEntity.getFireMonitorFlow());
            target.setPumpFlow(vehicleEntity.getPumpFlow());

            String foam = vehicleEntity.getFoam();
            target.setFoam(foam);
            if (Strings.isNotBlank(foam)) {
                String[] foams = foam.split(",");
                String foamsName = "";
                for (String key : foams) {
                    String foamName = dicsMap.get("PMLX").get(key);
                    if (Strings.isNotBlank(foamName)) {
                        foamsName = foamsName + foamName;
                    }
                }
                target.setFoamName(foamsName);
            }

            String equipment = vehicleEntity.getEquipment();
            target.setEquipment(equipment);
            if (Strings.isNotBlank(equipment)) {
                String[] equipments = equipment.split(",");
                String equipmentsName = "";
                for (String key : equipments) {
                    String equipmentName = dicsMap.get("QCLX").get(key);
                    if (Strings.isNotBlank(equipmentName)) {
                        equipmentsName = equipmentsName + equipmentName;
                    }
                }
                target.setEquipmentName(equipmentsName);
            }

            // 车库门号
            if (vehicleGarageMappingEntity != null) {
                target.setMappingGroupId(vehicleGarageMappingEntity.getMappingGroupId());
            }

            target.setVehicleDesc(vehicleEntity.getVehicleDesc());
            target.setVehicleIdentification(vehicleEntity.getVehicleIdentification());
            target.setLocationId(vehicleEntity.getLocationId());
            target.setLocationSIMNumber(vehicleEntity.getLocationSIMNumber());
            target.setCarrierBubbleA(vehicleEntity.getCarrierBubbleA());
            target.setCarrierBubbleB(vehicleEntity.getCarrierBubbleB());
            EnumBean enumBean = BasicEnumUtils.getEnumBeanByCode(VehicleNatureEnum.class, vehicleEntity.getVehicleNature());
            if (enumBean != null) {
                target.setVehicleNature(enumBean.getCode());
                target.setVehicleNatureName(enumBean.getName());
            }
            //根据车辆前两位排序
            target.setVehicleOrderNum(vehicleEntity.getVehicleOrderNum());

            return target;
        }
        return null;
    }


    /**
     * 消防装备 转换
     *
     * @param source 消防装备PO
     * @return 消防装备BO
     */
    public static EquipmentBean transform(EquipmentEntity source, Map<String, Map<String, String>> dicsMap) {
        if (null != source) {
            EquipmentBean target = new EquipmentBean();
            target.setId(source.getId());
            target.setWarehouseId(source.getWarehouseId());
            target.setWarehouseName(source.getWarehouseName());
            target.setLocationId(source.getLocationId());
            target.setLocationName(source.getLocationName());
            target.setEquipmentCode(source.getEquipmentCode());
            target.setEquipmentName(source.getEquipmentName());
            target.setSpecificationsNumber(source.getSpecificationsNumber());
            target.setBatchNo(source.getBatchNo());
            target.setEffectiveTime(source.getEffectiveTime());
            target.setSingleEquipmentId(source.getSingleEquipmentId());
            target.setSingleEquipmentCode(source.getSingleEquipmentCode());
            target.setVehicleNumber(source.getVehicleNumber());
            target.setEquipmentStatusCode(source.getEquipmentStatusCode());
            if(!StringUtils.isBlank(source.getEquipmentStatusCode())&&dicsMap.get("ZBZT")!=null){
                target.setEquipmentStatusName(dicsMap.get("ZBZT").get(source.getEquipmentStatusCode()));
            }


            target.setWhetherFight(source.getWhetherFight());
            target.setWhetherInCar(source.getWhetherInCar());
            target.setStockNum(source.getStockNum());
            target.setCollectingNum(source.getCollectingNum());
            target.setLoadNum(source.getLoadNum());
            target.setRepairNum(source.getRepairNum());
            target.setOnWayNum(source.getOnWayNum());
            target.setMeasurementCode(source.getMeasurementCode());
            if(!StringUtils.isBlank(source.getMeasurementCode())&&dicsMap.get("JLDW")!=null){
                target.setMeasurementName(dicsMap.get("JLDW").get(source.getMeasurementCode()));
            }


            target.setUnitPrice(source.getUnitPrice());
            target.setSumMoney(source.getSumMoney());
            target.setOriginalSubLedgerId(source.getOriginalSubLedgerId());
            target.setStandingBookId(source.getStandingBookId());
            target.setLoadSingleEquipmentId(source.getLoadSingleEquipmentId());
            target.setLoadSingleEquipmentCode(source.getLoadSingleEquipmentCode());
            target.setMaintenanceTime(source.getMaintenanceTime());
            target.setCheckTime(source.getCheckTime());
            target.setBuildAccountTime(source.getBuildAccountTime());
            target.setOrganizationId(source.getOrganizationId());
            target.setOrganizationName(source.getOrganizationName());
            target.setRemarks(source.getRemarks());

            target.setEquipmentTypeCode(source.getEquipmentTypeCode());
            if(!StringUtils.isBlank(target.getMeasurementCode())&&dicsMap.get("ZBLX")!=null){
                target.setEquipmentTypeName(dicsMap.get("ZBLX").get(target.getMeasurementCode()));
            }


            target.setWhetherConsumptiveEquipment(source.getWhetherConsumptiveEquipment());

            target.setEquipNum(source.getEquipNum());
            target.setEquipmentDesc(source.getEquipmentDesc());
            target.setAfterService(source.getAfterService());
            target.setWhetherAssembling(source.getWhetherAssembling());
            target.setWhetherTraining(source.getWhetherTraining());
            target.setWeight(source.getWeight());
            target.setVolume(source.getVolume());
            target.setMainComponents(source.getMainComponents());
            target.setScopeApplication(source.getScopeApplication());
            target.setEquipmentPerformance(source.getEquipmentPerformance());
            target.setBrandName(source.getBrandName());
            target.setManufacturerName(source.getManufacturerName());

            target.setChargePerson(source.getChargePerson());
            target.setChargePersonName(source.getChargePersonName());
            target.setChargePersonPhone(source.getChargePersonPhone());
            target.setWarehousingTime(source.getWarehousingTime());
            return target;
        }
        return null;
    }


    /**
     * 车辆 车载 装备 转换
     *
     * @param source 车辆 车载 装备PO
     * @return 车辆 车载 装备BO
     */
    public static EquipmentVehicleLoadBean transform(EquipmentVehicleLoadEntity source, Map<String, Map<String, String>> dicsMap) {
        if (null != source) {
            EquipmentVehicleLoadBean target = new EquipmentVehicleLoadBean();
            target.setId(source.getId());
            target.setOrganizationId(source.getOrganizationId());
            target.setOrganizationName(source.getOrganizationName());
            target.setVehicleId(source.getVehicleId());
            target.setSingleEquipmentCode(source.getSingleEquipmentCode());
            target.setEquipmentTypeCode(source.getEquipmentTypeCode());
            if(!StringUtils.isBlank(target.getEquipmentTypeCode())&&dicsMap.get("ZBLX")!=null){
                target.setEquipmentTypeName(dicsMap.get("ZBLX").get(target.getEquipmentTypeCode()));
            }


            target.setEquipmentCode(source.getEquipmentCode());
            target.setEquipmentName(source.getEquipmentName());
            target.setSpecificationsNumber(source.getSpecificationsNumber());
            target.setLoadNum(source.getLoadNum() == null ? "0" : String.valueOf(source.getLoadNum()));
            target.setMeasurementCode(source.getMeasurementCode());
            if(!StringUtils.isBlank(target.getMeasurementCode())&&dicsMap.get("JLDW")!=null){
                target.setMeasurementName(dicsMap.get("JLDW").get(target.getMeasurementCode()));
            }


            target.setWhetherConsumptiveEquipment(source.getWhetherConsumptiveEquipment());
            return target;
        }
        return null;
    }

    /**
     * 中队车辆与车库门对应关系 转换
     *
     * @param source 中队车辆与车库门对应关系保存INPUT
     * @return 值班信息PO
     */
    public static VehicleGarageMappingEntity transform(VehicleGarageMappingSaveInputInfo source) {
        if (null != source) {
            VehicleGarageMappingEntity target = new VehicleGarageMappingEntity();
            target.setId(source.getMappingObjectId()); //设置车库门对应关系 为 车辆编号/班组号
            target.setOrganizationId(source.getOrganizationId());
            target.setMappingType(source.getMappingType());
            target.setMappingObjectId(source.getMappingObjectId());
            target.setMappingGroupId(source.getMappingGroupId());
            target.setRemarks(source.getRemarks());
            return target;
        }
        return null;
    }

    /**
     * 中队车辆与车库门对应关系 转换
     *
     * @param source 中队车辆与车库门对应关系保存INPUT
     * @return 值班信息PO
     */
    public static VehicleGarageMappingBean transform(VehicleGarageMappingEntity source, Map<String, Map<String, String>> dicsMap,
                                                     Map<String, String> organizationNameMap) {
        if(organizationNameMap==null){
            organizationNameMap=new HashMap<>();
        }

        if (null != source) {
            VehicleGarageMappingBean target = new VehicleGarageMappingBean();
            target.setId(source.getId());
            target.setOrganizationId(source.getOrganizationId());
            if(!StringUtils.isBlank(source.getOrganizationId())){
                target.setOrganizationName(organizationNameMap.get(source.getOrganizationId()));
            }


            target.setMappingType(source.getMappingType());
            if(!StringUtils.isBlank(source.getMappingType())&&dicsMap.get("CLCKGXLX")!=null){
                target.setMappingTypeName(dicsMap.get("CLCKGXLX").get(source.getMappingType()));
            }


            target.setMappingObjectId(source.getMappingObjectId());
            target.setMappingGroupId(source.getMappingGroupId());
            if(!StringUtils.isBlank(source.getMappingGroupId())&&dicsMap.get("CKMH")!=null){
                target.setMappingGroupName(dicsMap.get("CKMH").get(source.getMappingGroupId()));
            }


            target.setRemarks(source.getRemarks());
            return target;
        }
        return null;
    }


    /**
     * 车辆 车载人员信息
     *
     * @param source
     * @return
     */
    public static VehiclePersonsBean transform(VehiclePersonsEntity source, EquipmentVehicleBean vehicleBean,
                                               Map<String, Map<String, String>> dicsMap) {
        if (null != source) {
            VehiclePersonsBean target = new VehiclePersonsBean();
            target.setId(source.getId());
            target.setVehiclePersonId(source.getId());
            target.setVehicleId(source.getVehicleId());
            if (vehicleBean != null) {
                target.setVehicleName(vehicleBean.getVehicleName());
                target.setVehicleNumber(vehicleBean.getVehicleNumber());
                target.setOrganizationId(vehicleBean.getOrganizationId());
                target.setOrganizationName(vehicleBean.getOrganizationName());
            }
            target.setPersonNum(source.getPersonNum());
            target.setPersonId(source.getPersonId());
            target.setPersonName(source.getPersonName());
            target.setPersonType(source.getPersonType());
            if(!StringUtils.isBlank(source.getPersonType())&&dicsMap.get("WLRYZW")!=null){
                target.setPersonTypeName(dicsMap.get("WLRYZW").get(source.getPersonType()));
            }


//            target.setWhetherCommander( source.getWhetherCommander() );

            target.setDriver(source.getDriver());
            target.setCorrespondent(source.getCorrespondent());

            target.setSorter(source.getSorter());
            return target;
        }
        return null;
    }


}
