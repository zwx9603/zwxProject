package com.dscomm.iecs.basedata.utils;


import com.dscomm.iecs.base.utils.PinYinUtils;
import com.dscomm.iecs.basedata.dal.po.*;
import com.dscomm.iecs.basedata.graphql.inputbean.KeyUnitSaveInputInfo;
import com.dscomm.iecs.basedata.graphql.inputbean.PlanDispatchSaveInputInfo;
import com.dscomm.iecs.basedata.graphql.inputbean.PlanSaveInputInfo;
import com.dscomm.iecs.basedata.graphql.typebean.*;
import org.apache.logging.log4j.util.Strings;
import org.mx.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 描述 : 重点单位 预案 转换工具
 */
public class KeyUnitTransformUtil {

    /**
     *  重点单位
     * saveInputInfo 转 entity*/
    public static KeyUnitEntity transform (KeyUnitSaveInputInfo source){
        if (source != null){
            KeyUnitEntity target = new KeyUnitEntity();
            target.setId(source.getKeyUnitId());
            target.setFireproofUnit(source.getFireproofUnit());

            target.setUnitName(source.getUnitName());
            target.setUnitShortName(PinYinUtils.getDuoYinHeaderNumberInitialsLower(source.getUnitName()));

            target.setUnitDesc(source.getUnitDesc());

            target.setEconomicOwnership(source.getEconomicOwnership());
            target.setUnitFoundingTime(source.getUnitFoundingTime());

            target.setUnitClassCode( source.getUnitClassCode() );
            target.setUnitLevelCode(source.getUnitLevelCode());
            target.setUnitCategoryCode(source.getUnitCategoryCode());
            target.setUnitTypeCode(source.getUnitTypeCode());
            target.setUnitNatureCode(source.getUnitNatureCode());
            target.setDistrictCode(source.getDistrictCode());
            target.setLegalPerson(source.getLegalPerson());
            target.setLegalPersonId(source.getLegalPersonId());
            target.setLegalPersonName(source.getLegalPersonName());
            target.setLegalPersonPhone(source.getLegalPersonPhone());
            target.setSecurityPerson(source.getSecurityPerson());
            target.setSecurityPersonId(source.getSecurityPersonId());
            target.setSecurityPersonName(source.getSecurityPersonName());
            target.setSecurityPersonPhone(source.getSecurityPersonPhone());
            target.setUnitEmail(source.getUnitEmail());
            target.setSecurityManage(source.getSecurityManage());
            target.setSecurityManageId(source.getSecurityManageId());
            target.setSecurityManageName(source.getSecurityManageName());
            target.setSecurityManagePhone(source.getSecurityManagePhone());
            target.setFireManager(source.getFireManager());
            target.setFireManagerId(source.getFireManagerId());
            target.setFireManagerName(source.getFireManagerName());
            target.setFireManagerPhone(source.getFireManagerPhone());
            target.setUnitAddress(source.getUnitAddress());
            target.setUnitPhone(source.getUnitPhone());
            target.setMailCode(source.getMailCode());
            target.setFixedAssets(source.getFixedAssets());
            target.setUnitWorkers(source.getUnitWorkers());
            target.setConstructionArea(source.getConstructionArea());
            target.setBuiltUpArea(source.getBuiltUpArea());
            target.setUnitPrincipalAttribute(source.getUnitPrincipalAttribute());
            target.setUnitAttribute(source.getUnitAttribute());
            target.setFireConDev(source.getFireConDev());
            target.setJurisdictionOrganizationId(source.getJurisdictionOrganizationId());
            target.setLongitude(source.getLongitude());
            target.setLatitude(source.getLatitude());
            target.setGeographicalPosition(source.getGeographicalPosition());
            target.setBuildNum(source.getBuildNum());
            target.setOperatorId(source.getOperatorId());
            target.setOrganizationId(source.getOrganizationId());
            target.setRemarks(source.getRemarks());

            target.setRelationId(source.getRelationId());

            target.setUnitFireTypeCode(source.getUnitFireTypeCode());

            target.setFireEscapeDesc(source.getFireEscapeDesc());
            target.setInternalFireFacilitiesDesc(source.getInternalFireFacilitiesDesc());
            target.setFirePreventionFacilitiesDesc(source.getFirePreventionFacilitiesDesc());
            target.setFireControlRoomDesc(source.getFireControlRoomDesc());





            target.setOtherSystemUrl( source.getOtherSystemUrl() );

            return target;
        }
        return  null ;


    }


    /**
     * 重点单位 转换
     *
     * @param source 重点单位PO
     * @return 重点单位BO
     */
    public static KeyUnitSimpleBean transform(KeyUnitEntity source ) {
        if (null != source) {
            KeyUnitSimpleBean target = new KeyUnitSimpleBean();
            target.setId(source.getId());
            target.setKeyUnitId(source.getId());
            target.setFireproofUnit(source.getFireproofUnit());
            target.setUnitName(source.getUnitName());
            if(Strings.isNotBlank( source.getUnitName() ) ){
                target.setPinyinHeader( PinYinUtils.getDuoYinHeaderNumberInitialsLower( source.getUnitName().replaceAll("\\\\s*","") ) );
            }
            target.setUnitShortName(source.getUnitShortName());
            target.setUnitAddress(source.getUnitAddress());
            target.setUnitPhone(source.getUnitPhone());
            target.setMailCode(source.getMailCode());

            target.setUnitClassCode( source.getUnitClassCode() );
            target.setUnitTypeCode( source.getUnitTypeCode()  );

            target.setLongitude(source.getLongitude());
            target.setLatitude(source.getLatitude());
            target.setGeographicalPosition(source.getGeographicalPosition());

            target.setOrganizationId(source.getJurisdictionOrganizationId());

            return target;
        }
        return null;
    }

    /**
     * 重点单位 转换
     *
     * @param source 重点单位PO
     * @return 重点单位BO
     */
    public static KeyUnitBean transform(KeyUnitEntity source, Map<String, Map<String, String>> dicsMap, Map<String, String> organizationNameMap) {
        if (null != source) {
            KeyUnitBean target = new KeyUnitBean();
            target.setId(source.getId());
            target.setKeyUnitId(source.getId());
            target.setFireproofUnit(source.getFireproofUnit());
            target.setUnitName(source.getUnitName());

            if(Strings.isNotBlank( source.getUnitName() ) ){
                target.setPinyinHeader( PinYinUtils.getDuoYinHeaderNumberInitialsLower( source.getUnitName().replaceAll("\\\\s*","") ) );
            }
            target.setUnitShortName(source.getUnitShortName());
            if (!StringUtils.isBlank(source.getEconomicOwnership())){
                target.setEconomicOwnership(source.getEconomicOwnership());
                target.setEconomicOwnershipName(getValue(dicsMap.get("JJSYZ"),source.getEconomicOwnership()));
            }
            target.setUnitFoundingTime(source.getUnitFoundingTime());

            target.setUnitClassCode( source.getUnitClassCode() );


            target.setUnitClassName( getValue(dicsMap.get("ZDDWFL"),target.getUnitClassCode()));

            target.setUnitLevelCode(source.getUnitLevelCode());
            target.setUnitLevelName(getValue(dicsMap.get("DWDJ"),target.getUnitLevelCode()));

            target.setUnitCategoryCode(source.getUnitCategoryCode());
            target.setUnitCategoryName(getValue(dicsMap.get("ZDDWLB"),source.getUnitCategoryCode()));

            target.setUnitTypeCode(source.getUnitTypeCode());
            target.setUnitTypeName(getValue(dicsMap.get("ZDDWLX"),source.getUnitTypeCode()));

            target.setUnitNatureCode(source.getUnitNatureCode());
            target.setUnitNatureName(getValue(dicsMap.get("DWXZ"),source.getUnitNatureCode()));

            target.setDistrictCode(source.getDistrictCode());
            target.setDistrictName(getValue(dicsMap.get("XZQX"),source.getDistrictCode()));

            target.setLegalPerson(source.getLegalPerson());
            target.setLegalPersonId(source.getLegalPersonId());
            target.setLegalPersonName(source.getLegalPersonName());
            target.setLegalPersonPhone(source.getLegalPersonPhone());
            target.setSecurityPerson(source.getSecurityPerson());
            target.setSecurityPersonId(source.getSecurityPersonId());
            target.setSecurityPersonName(source.getSecurityPersonName());
            target.setSecurityPersonPhone(source.getSecurityPersonPhone());
            target.setUnitEmail(source.getUnitEmail());
            target.setSecurityManage(source.getSecurityManage());
            target.setSecurityManageId(source.getSecurityManageId());
            target.setSecurityManageName(source.getSecurityManageName());
            target.setSecurityManagePhone(source.getSecurityManagePhone());
            target.setFireManager(source.getFireManager());
            target.setFireManagerId(source.getFireManagerId());
            target.setFireManagerName(source.getFireManagerName());
            target.setFireManagerPhone(source.getFireManagerPhone());

            target.setUnitAddress(source.getUnitAddress());
            target.setUnitPhone(source.getUnitPhone());
            target.setMailCode(source.getMailCode());
            target.setFixedAssets(source.getFixedAssets());
            target.setUnitWorkers(source.getUnitWorkers());
            target.setConstructionArea(source.getConstructionArea());
            target.setBuiltUpArea(source.getBuiltUpArea());
            target.setUnitPrincipalAttribute(source.getUnitPrincipalAttribute());
            target.setUnitAttribute(source.getUnitAttribute());
            target.setFireConDev(source.getFireConDev());
            target.setJurisdictionOrganizationId(source.getJurisdictionOrganizationId());
            if (!StringUtils.isBlank(source.getOrganizationId())){
                target.setJurisdictionOrganizationName(organizationNameMap.get(source.getOrganizationId()));
            }
            target.setLongitude(source.getLongitude());
            target.setLatitude(source.getLatitude());
            target.setGeographicalPosition(source.getGeographicalPosition());
            target.setBuildNum(source.getBuildNum());
            target.setOperatorId(source.getOperatorId());
            target.setOrganizationId(source.getOrganizationId());
            target.setOrganizationName(getValue(organizationNameMap,source.getOrganizationId()));
            target.setRemarks(source.getRemarks());
            target.setRelationId(source.getRelationId());
            if (!StringUtils.isBlank(source.getUnitFireTypeCode())){
                target.setUnitFireTypeCode(source.getUnitFireTypeCode());
                target.setUnitFireTypeName(getValue(dicsMap.get("HZWHXFL"),source.getUnitFireTypeCode()));
            }

            target.setFireEscapeDesc(source.getFireEscapeDesc());
            target.setInternalFireFacilitiesDesc(source.getInternalFireFacilitiesDesc());
            target.setFirePreventionFacilitiesDesc(source.getFirePreventionFacilitiesDesc());
            target.setFireControlRoomDesc(source.getFireControlRoomDesc());


            target.setOtherSystemUrl( source.getOtherSystemUrl() );

            return target;
        }
        return null;
    }

    /**
     * 预案 转换
     *
     * @param source 预案PO
     * @return 预案BO
     */
    public static PlanBean transform(PlanEntity source, Map<String, Map<String, String>> dicsMap, Map<String, String> organizationNameMap) {
        if (null != source) {
            PlanBean target = new PlanBean();
            target.setId(source.getId());
            target.setTemplateId(source.getTemplateId());
            target.setPlanCategoryCode(source.getPlanCategoryCode());
            target.setPlanCategoryName(getValue(dicsMap.get("YAZL"),source.getPlanCategoryCode()));
            target.setPlanTypeCode(source.getPlanTypeCode());
            target.setPlanTypeName(getValue(dicsMap.get("YALX"),source.getPlanTypeCode()));
            target.setPlanCode(source.getPlanCode());
            target.setPlanName(source.getPlanName());
            target.setPlanDesc( source.getPlanDesc() );
            target.setPlanStatusCode(source.getPlanStatusCode());
            target.setPlanStatusName(getValue(dicsMap.get("YAZT"),source.getPlanStatusCode()));
            target.setKeyUnitId(source.getKeyUnitId());
            target.setObjectName(source.getObjectName());
            target.setObjectTypeCode(source.getObjectTypeCode());
            target.setObjectTypeName(getValue(dicsMap.get("YADXLX"),source.getObjectTypeCode()));
            target.setPlanOrderNum(source.getPlanOrderNum());
            target.setPositionId(source.getPositionId());
            target.setPositionName(source.getPositionName());
            target.setMappingVersionNum(source.getMappingVersionNum());
            target.setVersionNum(source.getVersionNum());
            target.setSecurityClassification(source.getSecurityClassification());
            target.setOrganizationId(source.getOrganizationId());
            target.setOrganizationName(organizationNameMap.get(source.getOrganizationId()));
            target.setWhetherCrossRegion(source.getWhetherCrossRegion());
            target.setWhetherParentPlan(source.getWhetherParentPlan());
            target.setWhetherExternalSystemData(source.getWhetherExternalSystemData());
            target.setMakePersonId(source.getMakePersonId());
            target.setMakePersonName(source.getMakePersonName());
            target.setMakeOrganizationId(source.getMakeOrganizationId());
            target.setMakeOrganizationName(organizationNameMap.get(source.getMakeOrganizationId()));
            target.setMakeTime(source.getMakeTime());
            target.setOperatorId(source.getOperatorId());
            target.setOperatorName(source.getOperatorName());
            target.setOperateOrganization(source.getOperateOrganization());
            target.setRemarks(source.getRemarks());
            target.setIdCode(source.getIdCode());
            target.setPlanBrieflyDesc(source.getPlanBrieflyDesc());
            return target;
        }
        return null;
    }

    /**
     * 预案 转换
     *
     * @param source 预案保存参数 INPUT
     * @return 预案PO
     */
    public static PlanEntity transform( PlanSaveInputInfo source,Long time) {
        if (null != source ) {
            PlanEntity target = new PlanEntity();
            target.setId(source.getId());
            target.setTemplateId(source.getTemplateId());
            target.setPlanCategoryCode(source.getPlanType());
            target.setPlanCode(source.getPlanCode());
            target.setPlanOrderNum(source.getPlanOrderNum());
            target.setMappingVersionNum(source.getMappingVersionNum());
            target.setVersionNum(source.getVersionNum());
            target.setSecurityClassification(source.getSecurityClassification());
            target.setPlanTypeCode(source.getPlanTypeCode());
            target.setPlanName(source.getPlanName());
            target.setPlanDesc( source.getPlanDesc() );
            target.setKeyUnitId(source.getKeyUnitId());
            target.setPositionId(source.getPositionId());
            target.setPlanStatusCode(source.getPlanStatusCode());
            target.setObjectTypeCode(source.getObjectTypeCode());
            target.setWhetherCrossRegion(source.getWhetherCrossRegion());
            target.setMakePersonId(source.getMakePersonId());
            target.setMakePersonName(source.getMakePersonName());
            target.setMakeOrganizationId(source.getMakeOrganizationId());
            target.setMakeTime(time);
            target.setOperatorId(source.getOperatorId());
            target.setOperatorName(source.getOperatorName());
            target.setWhetherParentPlan(source.getWhetherParentPlan());
            target.setOrganizationId(source.getOrganizationId());
            target.setObjectName(source.getObjectName());
            target.setPositionName(source.getPositionName());
            target.setWhetherExternalSystemData(source.getWhetherExternalSystemData());
            target.setOperateOrganization(source.getOperateOrganization());
            target.setRemarks(source.getRemarks());
            return target;
        }
        return null;
    }

    /**
     * 预案调派 转换
     *
     * @param source 预案PO
     * @return 预案BO
     */
    public static PlanDispatchBean transform(PlanDispatchEntity source, Map<String, Map<String, String>> dicsMap, Map<String, String> organizationNameMap , EquipmentVehicleBean vehicleBean ) {
        if (null != source) {
            PlanDispatchBean target = new PlanDispatchBean();
            target.setId(source.getId());
            target.setPlanId(source.getPlanId());
            target.setSubordinateUnitId(source.getSubordinateUnitId());
            target.setSubordinateUnitName(getValue(organizationNameMap,source.getSubordinateUnitId()));
            target.setOrganizationId(source.getOrganizationId());
            target.setOrganizationName(getValue(organizationNameMap,source.getOrganizationId()));
            target.setVehicleId(source.getVehicleId());
            target.setDispatchGroup(source.getDispatchGroup());
            if( vehicleBean != null ){
                target.setVehicleName( vehicleBean.getVehicleName() );
                target.setVehicleNumber(vehicleBean.getVehicleNumber());
                target.setVehicleTypeCode(vehicleBean.getVehicleTypeCode());
                target.setVehicleTypeName(vehicleBean.getVehicleTypeName() );
                target.setVehicleLevelCode(vehicleBean.getVehicleLevelCode() );
                target.setVehicleLevelName(vehicleBean.getVehicleLevelName() );
            }
            target.setOperationalFunctionCode(source.getOperationalFunctionCode());
            target.setOperationalFunctionName(getValue(dicsMap.get("WLZZGN"),source.getOperationalFunctionCode()));
            target.setDispatchOrderNum(source.getDispatchOrderNum());
            target.setAttendanceTime(source.getAttendanceTime());
            target.setRemarks(source.getRemarks());
            return target;
        }
        return null;
    }

    /**
     * 预案调派 转换
     *
     * @param source 预案保存参数INPUT
     * @return 预案调派PO
     */
    public static List<PlanDispatchEntity> transform(PlanSaveInputInfo source , String planId ) {
        if (null != source) {
            List<PlanDispatchSaveInputInfo> planDispatchSaveInputInfoList = source.getPlanDispatchSaveInputInfo();
            List<PlanDispatchEntity> target = new ArrayList<>();
            if (planDispatchSaveInputInfoList != null && planDispatchSaveInputInfoList.size() > 0) {
                for (PlanDispatchSaveInputInfo inputInfo : planDispatchSaveInputInfoList) {
                    PlanDispatchEntity planDispatchEntity = new PlanDispatchEntity();
                    planDispatchEntity.setId(inputInfo.getId());
                    planDispatchEntity.setPlanId( planId );
                    planDispatchEntity.setSubordinateUnitId(inputInfo.getSubordinateUnitId());
                    planDispatchEntity.setVehicleId(inputInfo.getVehicleId());
                    planDispatchEntity.setDispatchGroup(inputInfo.getDispatchGroup());
                    planDispatchEntity.setVehicleNumber(inputInfo.getVehicleNumber());
                    planDispatchEntity.setVehicleTypeCode(inputInfo.getVehicleTypeCode());
                    planDispatchEntity.setVehicleLevelCode(inputInfo.getVehicleLevelCode());
                    planDispatchEntity.setOperationalFunctionCode(inputInfo.getOperationalFunctionCode());
                    planDispatchEntity.setDispatchOrderNum(inputInfo.getDispatchOrderNum());
                    planDispatchEntity.setAttendanceTime(inputInfo.getAttendanceTime());
                    planDispatchEntity.setOperatorId(source.getOperatorId());
                    planDispatchEntity.setOperatorName(source.getOperatorName());
                    planDispatchEntity.setOrganizationId(inputInfo.getOrganizationId());
                    planDispatchEntity.setRemarks(inputInfo.getRemarks());

                    target.add(planDispatchEntity);
                }
            }
            return target;
        }
        return null;
    }


    /**
     * 灭火单位 重点部位转换
     * @param source
     * @return KeyUnitPositionBean
     * */
    public static KeyUnitPositionBean transform (KeyUnitPositionEntity source ,Map<String, Map<String, String>> dicsMap, Map<String, String> organizationNameMap){

        if (source != null){
            KeyUnitPositionBean target = new KeyUnitPositionBean();
            target.setId(source.getId());
            if (!StringUtils.isBlank(source.getBuildingsResistanceFireLevelCode())){
                target.setBuildingsResistanceFireLevelCode(source.getBuildingsResistanceFireLevelCode());
                target.setBuildingsResistanceFireLevelName(getValue(dicsMap.get("JZWNHDJ"),source.getBuildingsResistanceFireLevelCode()));
            }
            if (!StringUtils.isBlank(source.getBuildingStructureNatureCode())){
                target.setBuildingStructureNatureCode(source.getBuildingStructureNatureCode());
                target.setBuildingStructureNatureName(getValue(dicsMap.get("JZWSYXZ"),source.getBuildingsResistanceFireLevelCode()));
            }
            if (!StringUtils.isBlank(source.getBuildingStructureTypeCode())){
                target.setBuildingStructureTypeCode(source.getBuildingStructureTypeCode());
                target.setBuildingStructureTypeName(getValue(dicsMap.get("JZJGLX"),source.getBuildingsResistanceFireLevelCode()));
            }
            target.setBuiltUpArea(source.getBuiltUpArea());
            target.setDangerDesc(source.getDangerDesc());
            target.setDangerousSourcesDesc(source.getDangerousSourcesDesc());
            target.setEstablishingDesc(source.getEstablishingDesc());
            target.setEvacuationNum(source.getEvacuationNum());
            target.setExitNum(source.getExitNum());
            target.setFireDesc(source.getFireDesc());
            target.setFireElevatorNum(source.getFireElevatorNum());
            target.setFireFacilitiesDesc(source.getFireFacilitiesDesc());
            target.setFireRrotectionSignsDesc(source.getFireRrotectionSignsDesc());
            target.setFireSafetyDesc(source.getFireSafetyDesc());
            target.setIdCode(source.getIdCode());
            target.setKeyUnitId(source.getKeyUnitId());
            target.setLiablePerson(source.getLiablePerson());
            target.setLiablePersonName(source.getLiablePersonName());
            if (!StringUtils.isBlank(source.getOrganizationId())){
                target.setOrganizationId(source.getOrganizationId());
                target.setOrganizationName(organizationNameMap.get(source.getOrganizationId()));
            }
            target.setPosition(source.getPosition());
            target.setPositionAddress(source.getPositionAddress());
            target.setPositionFloor(source.getPositionFloor());
            target.setPositionHeight(source.getPositionHeight());
            target.setPositionName(source.getPositionName());
            target.setRemarks(source.getRemarks());

            return target;
        }
       return  null ;
    }

    /**
     * 重点单位危化品 转换
     * */
    public KeyUnitDangerousChemicalsBean transform (KeyUnitDangerousChemicalsEntity source ,Map<String, Map<String, String>> dicsMap){

        if (null != source){
            KeyUnitDangerousChemicalsBean target = new KeyUnitDangerousChemicalsBean();
            target.setId(source.getId());

            if (!StringUtils.isBlank(source.getDangerousChemicalsCategoryCode())){
                target.setDangerousChemicalsCategoryCode(source.getDangerousChemicalsCategoryCode());
                target.setDangerousChemicalsCategoryName(getValue(dicsMap.get("HXPWXXLB"),source.getDangerousChemicalsCategoryCode()));
            }
            target.setDangerousChemicalsId(source.getDangerousChemicalsId());
            target.setDangerousChemicalsIdCode(source.getDangerousChemicalsIdCode());
            if (!StringUtils.isBlank(source.getDangerousChemicalsStatusCode())){
                target.setDangerousChemicalsStatusCode(source.getDangerousChemicalsStatusCode());
                target.setDangerousChemicalsStatusName(getValue(dicsMap.get("HXPZTLB"),source.getDangerousChemicalsStatusCode()));
            }
            if (!StringUtils.isBlank(source.getDangerousChemicalsTypeCode())){
                target.setDangerousChemicalsTypeCode(source.getDangerousChemicalsTypeCode());
                target.setDangerousChemicalsTypeName(getValue(dicsMap.get("WXHXPFL"),source.getDangerousChemicalsTypeCode()));
            }
            target.setKeyUnitDangerousChemicalsAddress(source.getKeyUnitDangerousChemicalsAddress());
            target.setKeyUnitDangerousChemicalsNum(source.getKeyUnitDangerousChemicalsNum());
            target.setKeyUnitDangerousChemicalsDesc(source.getKeyUnitDangerousChemicalsDesc());
            target.setKeyUnitId(source.getKeyUnitId());
            return target;
        }
       return  null ;
    }


    public static String getValue(Map<String,String> map,String key){
        if (StringUtils.isBlank(key)||map==null){
            return null;
        }else {
            return map.get(key);
        }
    }
}
