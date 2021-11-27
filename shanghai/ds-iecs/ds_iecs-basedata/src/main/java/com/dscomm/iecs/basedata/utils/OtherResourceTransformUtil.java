package com.dscomm.iecs.basedata.utils;

import com.dscomm.iecs.base.utils.PinYinUtils;
import com.dscomm.iecs.basedata.dal.po.*;
import com.dscomm.iecs.basedata.graphql.inputbean.OrgRelationshipSaveInputInfo;
import com.dscomm.iecs.basedata.graphql.typebean.*;
import org.mx.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述 :系统配置 类数据转换工具
 */
public class OtherResourceTransformUtil {



    /**
     * 危化品entity-bean
     * @return
     */
    public static DangerousChemicalsBean transform(DangerousChemicalsEntity source ){
        if( source == null ){
            DangerousChemicalsBean target =  new DangerousChemicalsBean();
            target.setId(source.getId());
            target.setWhpBhyqy(source.getWhpBhyqy());
            target.setWhpBmjctj(source.getWhpBmjctj());
            target.setWhpBzbz(source.getWhpBzbz());
            target.setWhpBzlx(source.getWhpBzlx());
            target.setWhpBzsx(source.getWhpBzsx());
            target.setWhpBzxx(source.getWhpBzxx());
            target.setWhpCas(source.getWhpCas());
            target.setWhpCyzysx(source.getWhpCyzysx());
            target.setWhpDlxlbtssbj(source.getWhpDlxlbtssbj());
            target.setWhpDlxlglbj(source.getWhpDlxlglbj());
            target.setWhpDlxlyjssbj(source.getWhpDlxlyjssbj());
            target.setWhpDx(source.getWhpDx());
            target.setWhpFd(source.getWhpFd());
            target.setWhpFhf(source.getWhpFhf());
            target.setWhpFzl(source.getWhpFzl());
            target.setWhpFzs(source.getWhpFzs());
            target.setWhpGckz(source.getWhpGckz());
            target.setWhpHxxtfh(source.getWhpHxxtfh());
            target.setWhpImdg(source.getWhpImdg());
            target.setWhpJcxz(source.getWhpJcxz());
            target.setWhpJghyfj(source.getWhpJghyfj());
            target.setWhpJhwh(source.getWhpJhwh());
            target.setWhpJjw(source.getWhpJjw());
            target.setWhpJkwh(source.getWhpJkwh());
            target.setWhpLjwd(source.getWhpLjwd());
            target.setWhpLjyl(source.getWhpLjyl());
            target.setWhpMhff(source.getWhpMhff());
            target.setWhpNm(source.getWhpNm());
            target.setWhppfjcwh(source.getWhpPfjcwh());
            target.setWhpQrtj(source.getWhpQrtj());
            target.setWhpRd(source.getWhpRd());
            target.setWhpRjx(source.getWhpRjx());
            target.setWhpRsfjcw(source.getWhpRsfjcw());
            target.setWhpRsr(source.getWhpRsr());
            target.setWhpRtecs(source.getWhpRtecs());
            target.setWhpSd(source.getWhpSd());
            target.setWhpsfh(source.getWhpSfh());
            target.setWhpSlxlbtssbj(source.getWhpSlxlbtssbj());
            target.setWhpSlxlglbj(source.getWhpSlxlglbj());
            target.setWhpSlxlyjssbj(source.getWhpSlxlyjssbj());
            target.setWhpSrwh(source.getWhpSrwh());
            target.setWhpSsx(source.getWhpSsx());
            target.setWhpUn(source.getWhpUn());
            target.setWhpWdx(source.getWhpWdx());
            target.setWhpWgh(source.getWhpWgh());
            target.setWhpWgxz(source.getWhpWgxz());
            target.setWhpWxtx(source.getWhpWxtx());
            target.setWhpWxxlb(source.getWhpWxxlb());
            target.setWhpXdmdkq(source.getWhpBhyqy());
            target.setWhpXdmds(source.getWhpXdmds());
            target.setWhpXlcz(source.getWhpXlcz());
            target.setWhpxrwh(source.getWhpXrwh());
            target.setWhpYjfh(source.getWhpYjfh());
            target.setWhpYjjcwh(source.getWhpYjjcwh());
            target.setWhpYwmc(source.getWhpYwmc());
            target.setWhpZrwd(source.getWhpZrwd());
            target.setWhpZwmc(source.getWhpZwmc());
            target.setWhpZysx(source.getWhpZysx());
            target.setWhpZyyt(source.getWhpZyyt());

            return target;
        }
        return  null ;

    }




    /**
     *水源基本信息entity转bean
     * */
    public static WaterBaseBean transform(WaterBaseInfoEntity source , Map<String, Map<String, String>> dicsMap ){
        if (source != null){
            WaterBaseBean target = new WaterBaseBean();
            target.setId(source.getId());
            target.setAltitude(source.getAltitude());
            target.setAuditStatus(source.getAuditStatus());

            target.setDistrict(source.getDistrict());
            target.setEastUrl(source.getEastUrl());
            target.setEnableTime(source.getEnableTime());


            target.setLatitude(source.getLatitude());
            target.setLongitude(source.getLongitude());
            target.setMapAltitude(source.getMapAltitude());
            target.setMapLatitude(source.getMapLatitude());
            target.setMapLongitude(source.getMapLongitude());
            target.setNature(source.getNature());
            target.setNearEastUrl(source.getNearEastUrl());
            target.setNearNorthUrl(source.getNearNorthUrl());
            target.setNearSouthUrl(source.getNearSouthUrl());
            target.setNearWestUrl(source.getNearWestUrl());
            target.setNorthUrl(source.getNorthUrl());
            target.setNumber(source.getNumber());
            target.setOrganizationId(source.getOrganizationId());
            target.setOrganizationName(source.getOrganizationName());
            target.setPhotoFileURL(source.getPhotoFileURL());
            target.setPipeDiameter(source.getPipeDiameter());

            target.setPipeNetwork(source.getPipeNetwork());
            target.setPipePressure(source.getPipePressure());

            target.setRecordStatus(source.getRecordStatus());
            target.setRoadSection(source.getRoadSection());
            target.setSetTheForm(source.getSetTheForm());
            target.setSouthUrl(source.getSouthUrl());
            target.setTelephone(source.getTelephone());
            target.setWaterAddr(source.getWaterAddr());
            target.setWaterAttribute(source.getWaterAttribute());
            target.setWaterBuildTime(source.getWaterBuildTime());
            target.setWaterCode(source.getWaterCode());
            target.setWaterHandlersId(source.getWaterHandlersId());

            target.setWaterName(source.getWaterName());

            target.setWaterType(source.getWaterType());
            target.setWaterUnit(source.getWaterUnit());
            target.setWestUrl(source.getWestUrl());

            target.setHydrantType(source.getHydrantType());
            if(!StringUtils.isBlank(source.getHydrantType())&&dicsMap.get("XHSLX")!=null){
                target.setHydrantTypeName(  dicsMap.get("XHSLX").get(   source.getHydrantType() )  );
            }



            target.setInterfaceForm(source.getInterfaceForm());
            if(!StringUtils.isBlank(source.getInterfaceForm())&&dicsMap.get("JKXS")!=null){
                target.setInterfaceFormName(  dicsMap.get("JKXS").get( source.getInterfaceForm()) );
            }



            target.setPipeForm(source.getPipeForm());
            if(!StringUtils.isBlank( source.getPipeForm() )&& dicsMap.get("GWXS")!=null){
                target.setPipeFormName(  dicsMap.get("GWXS").get( source.getPipeForm() ) );
            }




            target.setWaterIntake(source.getWaterIntake());
            if(!StringUtils.isBlank(source.getWaterIntake() )&&  dicsMap.get("QSXS")!=null){
                target.setWaterIntakeName(  dicsMap.get("QSXS").get( source.getWaterIntake() ) );
            }



            target.setPlacementForm(source.getPlacementForm());
            if(!StringUtils.isBlank(source.getPlacementForm() )&&  dicsMap.get("FZXS")!=null){
                target.setPlacementFormName(  dicsMap.get("FZXS").get( source.getPlacementForm() ) );
            }



            target.setAvailableStatus(source.getAvailableStatus());
            if(!StringUtils.isBlank(source.getAvailableStatus() )&&  dicsMap.get("KYZT")!=null){
                target.setAvailableStatusName(  dicsMap.get("KYZT").get( source.getAvailableStatus() ) );
            }



            target.setWaterStatus(source.getWaterStatus());
            target.setWaterStatusName(  dicsMap.get("SYZT").get( source.getWaterStatus() ) );

            target.setPipeNetwork( source.getPipeNetwork() );
            target.setManageUnit( source.getManageUnit() );
            target.setMaintenanceUnit( source.getMaintenanceUnit() );
            target.setContact( source.getContact() );
            target.setContactPersonName( source.getContactPersonName() );
            target.setRoadSectionDesc( source.getRoadSectionDesc() );
            target.setUrl( source.getEastUrl() );
            target.setNearUrl( source.getNearEastUrl() );

            return target;

        }
        return null ;

    }




    /**
     * 消防转警机构关系配置
     *
     * @param source 消防转警机构关系配置INPUT
     * @return 消防转警机构关系配置PO
     */
    public static OrgRelationshipBean transform( OrgRelationshipEntity source , Map<String, Map<String, String>> dicsMap  , Map<String, String> organizationNameMap ) {
        if(organizationNameMap==null){
            organizationNameMap=new HashMap<>();
        }

        if (null != source) {
            OrgRelationshipBean target = new OrgRelationshipBean();
            target.setId( source.getId() );
            target.setFireDeptId(source.getFireDeptId());

            if(!StringUtils.isBlank(source.getFireDeptId() )){
                target.setFireDeptName( organizationNameMap.get( source.getFireDeptId() ));
            }

            target.setTransferType(source.getTransferType());

            if(!StringUtils.isBlank(source.getTransferType() )&&  dicsMap.get("QQXZLX")!=null){
                target.setTransferTypeName( dicsMap.get("QQXZLX").get( source.getTransferType() ));
            }

            target.setTransferDeptCode(source.getTransferDeptCode());
            target.setTransferDeptName( source.getTransferDeptName() );
            target.setIsAudit(source.getIsAudit());
            target.setTransferDeptName( source.getTransferDeptName() );
            target.setTransferDeptName( source.getTransferDeptName() );
            target.setTransferDeptName( source.getTransferDeptName() );
            return target;
        }
        return null;
    }

    /**
     * 消防转警机构关系配置
     *
     * @param source 消防转警机构关系配置INPUT
     * @return 消防转警机构关系配置PO
     */
    public static OrgRelationshipEntity transform( OrgRelationshipSaveInputInfo  source ) {
        if (null != source) {
            OrgRelationshipEntity target = new OrgRelationshipEntity();
            target.setFireDeptId(source.getFireDeptId());
            target.setTransferType(source.getTransferType());
            target.setTransferDeptCode(source.getTransferDeptCode());
            target.setTransferDeptName( source.getTransferDeptName() );
            target.setIsAudit(source.getIsAudit());
            target.setTransferDeptName( source.getTransferDeptName() );
            target.setTransferDeptName( source.getTransferDeptName() );
            target.setTransferDeptName( source.getTransferDeptName() );
            return target;
        }
        return null;
    }


    /**
     * 值班信息转换
     *
     * @param source 值班信息PO
     * @return 值班信息BO
     */
    public static OnDutySummaryBean transform(OnDutySummaryEntity source, Map<String, Map<String, String>> dicsMap) {
        if (null != source) {
            OnDutySummaryBean target = new OnDutySummaryBean();
            target.setId(source.getId());
            target.setIdCode(source.getIdCode());
            target.setOrganizationId(source.getOrganizationId());
            target.setOrganizationName(source.getOrganizationName());
            target.setOnDutyTime(source.getOnDutyTime());
            target.setOrganizationIntegrated(source.getOrganizationIntegrated());
            target.setOnDutyType(source.getOnDutyType());
            target.setOnDutyTypeName(  source.getOnDutyTypeName() );
            target.setOnDutyCode(source.getOnDutyCode());
            if(!StringUtils.isBlank(source.getOnDutyCode() )&& dicsMap.get("GW")!=null){
                target.setOnDutyName(dicsMap.get("GW").get(source.getOnDutyCode()));
            }


            target.setResponsibilities(source.getResponsibilities());
            target.setOnDutyPersonId(source.getOnDutyPersonId());
            target.setOnDutyPersonNumber(source.getOnDutyPersonNumber());
            target.setOnDutyPersonName(source.getOnDutyPersonName());
            target.setContactNumber(source.getContactNumber());
            target.setOnDutyNumber(source.getOnDutyNumber());
            target.setShowNumber(source.getShowNumber());
            target.setDistrictCode(source.getDistrictCode());
            if(!StringUtils.isBlank(source.getDistrictCode() )&& dicsMap.get("XZQX")!=null){
                target.setDistrictName(dicsMap.get("XZQX").get(source.getDistrictCode()));
            }


            target.setRemarks(source.getRemarks());
            target.setOnDutyPersonUnitName(source.getOnDutyPersonUnitName());
            target.setWhetherDeputy(source.getWhetherDeputy());
            target.setPinyinHeader( PinYinUtils.getDuoYinHeaderNumberInitialsLower(source.getOrganizationName())
                    + "," + PinYinUtils.getDuoYinHeaderNumberInitialsLower(source.getOnDutyPersonName()) );

            return target;
        }
        return null;
    }



    /**
     *  范围信息 entity-bean
     * @return
     */
    public static RegionBean transform(RegionEntity source ){
        if( source != null ){
            RegionBean target =  new RegionBean();
            target.setId(source.getId());
            target.setValid( source.getValid() );
            target.setRegionName( source.getRegionName() );
            target.setLongitude( source.getLongitude() );
            target.setLatitude( source.getLatitude() );
            target.setRegionRange(  source.getRegionRange() );
            return target;
        }
        return  null ;

    }


}
