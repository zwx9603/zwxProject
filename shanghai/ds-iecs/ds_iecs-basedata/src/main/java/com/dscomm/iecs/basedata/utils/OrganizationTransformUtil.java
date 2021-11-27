package com.dscomm.iecs.basedata.utils;

import com.dscomm.iecs.base.utils.PinYinUtils;
import com.dscomm.iecs.basedata.dal.po.*;
import com.dscomm.iecs.basedata.graphql.inputbean.MiniatureStationSaveInputInfo;
import com.dscomm.iecs.basedata.graphql.typebean.*;
import org.apache.logging.log4j.util.Strings;
import org.mx.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述 : 机构、单位等类数据转换工具
 * Date Time : 2020/05/21 15：38
 */
public class OrganizationTransformUtil {


    /**
     * 描述：机构信息 转换
     *
     * @param source  机构实体PO
     * @param dicsMap 字典map
     * @return 机构BO
     */
    public static OrganizationBean transform(OrganizationEntity source, Map<String, Map<String, String>> dicsMap , Map<String, String> organizationIdCodeMap   ) {
        if(organizationIdCodeMap==null){
            organizationIdCodeMap=new HashMap<>();
        }

        OrganizationBean target = new OrganizationBean();
        target.setId(source.getId());
        target.setParentId( source.getOrganizationParentId() );
        target.setSearchPath(source.getSearchPath());
        target.setOrganizationCode(source.getOrganizationCode());

        target.setOrganizationName( source.getOrganizationName  () );
        target.setOrganizationShortName(source.getOrganizationShortName() );

        if(Strings.isNotBlank( target.getOrganizationName() ) ){
            target.setPinyinHeader(  PinYinUtils.getDuoYinHeaderNumberInitialsLower( target.getOrganizationName().replaceAll("\\\\s*","") ) );
        }


        target.setOrganizationDesc(source.getOrganizationDesc());
        target.setOrganizationAddress(source.getOrganizationAddress());
        target.setOrganizationInsideId(source.getOrganizationInsideId());
        target.setOrganizationTree(source.getOrganizationTree());
        target.setDomainName(source.getDomainName());
        target.setLongitude(source.getLongitude());
        target.setLatitude(source.getLatitude());
        target.setHeight(source.getHeight());

        target.setOrganizationParentId(source.getOrganizationParentId());
        if(!StringUtils.isBlank(source.getOrganizationParentId())){
            target.setOrganizationParentCode( organizationIdCodeMap.get( source.getOrganizationParentId() ));
        }



        target.setOrganizationCategoryCode(source.getOrganizationCategoryCode());
        Map<String, String> jglb = dicsMap.get("JGLB");
        if (jglb!=null){
            target.setOrganizationCategoryName(jglb.get(source.getOrganizationCategoryCode()));
        }


        target.setOrganizationTypeCode(source.getOrganizationTypeCode());
        Map<String, String> jglx = dicsMap.get("JGLX");
        if (jglx!=null){
            target.setOrganizationTypeName(jglx.get(source.getOrganizationTypeCode()));
        }

        target.setOrganizationNatureCode(source.getOrganizationNatureCode());
        Map<String, String> jgxz = dicsMap.get("JGXZ");
        if (jgxz!=null){
            target.setOrganizationNatureName(jgxz.get(source.getOrganizationNatureCode()));
        }


        target.setDistrictCode(source.getDistrictCode());
        Map<String, String> xzqx = dicsMap.get("XZQX");
        if (xzqx!=null){
            target.setDistrictName(xzqx.get(source.getDistrictCode()));
        }


        target.setRemarks(source.getRemarks());
        target.setSymbolOfDutyUnit(source.getSymbolOfDutyUnit());
        target.setOrganizationWeight(source.getOrganizationWeight());
        target.setContactPerson(source.getContactPerson());
        target.setContactPersonName( source.getContactPersonName() );
        target.setDispatchPhone(source.getDispatchPhone());

        target.setMailCode(source.getPostalCode());
        target.setFaxNumber(source.getFaxNumber());
        target.setEmail(source.getEmail());
        target.setOrganizationRepealStatus(source.getOrganizationRepealStatus());
        target.setPicture(source.getPicture());
        target.setPrecinctRelevanceId(source.getPrecinctRelevanceId());
        target.setRelationId(source.getRelationId());

        target.setOrganizationRepealTime(source.getOrganizationRepealTime());
        target.setContactPhone(source.getContactPhone());
        target.setPrecinctArea(source.getPrecinctArea());
        target.setPrecinctRange(source.getPrecinctRange());

        target.setOrganizationOrderNum( source.getOrganizationOrderNum() );

        return target;
    }




    /**
     * 描述：机构  非消防车辆信息
     *
     * @param source 非消防车辆信息PO
     * @return 非消防车辆信息BO
     */
    public static OrganizationLocalFullTimeUnitBean transform(OrganizationLocalFullTimeUnitEntity source   ) {
        OrganizationLocalFullTimeUnitBean target = new OrganizationLocalFullTimeUnitBean();
        target.setId( source.getId() );
        target.setOrganizationId( source.getOrganizationId() );
        target.setOrganizationName( source.getOrganizationName() );
        target.setEquipmentSortCode( source.getEquipmentSortCode() );
        target.setEquipmentSortName( source.getEquipmentSortName() );
        target.setVehicleLevel( source.getVehicleLevel() );
        target.setSpecificationsNumber( source.getSpecificationsNumber() );
        target.setVehicleNumber( source.getVehicleNumber() );
        target.setWhetherValid( source.getWhetherValid() );
        target.setEquipmentStatus( source.getEquipmentStatus() );
        target.setEquipmentTime( source.getEquipmentTime() );
        target.setSynopsis( source.getSynopsis() );
        target.setRemarks( source.getRemarks() );
        return target;
    }


    /**
     * 描述：消防机构毗邻优先级信息 转换
     *
     * @param source  消防机构毗邻优先级实体PO
     * @return 消防机构毗邻优先级BO
     */
    public static OrganizationAdjacentPriorityBean transform(OrganizationAdjacentPriorityEntity source , Map<String, String>  organizationNameMap  ) {
        if(organizationNameMap==null){
            organizationNameMap = new HashMap<>();
        }
        OrganizationAdjacentPriorityBean target = new OrganizationAdjacentPriorityBean();
        target.setId( source.getId() );
        target.setChargeOrganizationId( source.getChargeOrganizationId() );
        if(!StringUtils.isBlank( source.getChargeOrganizationId() )){
            target.setChargeOrganizationName( organizationNameMap.get( source.getChargeOrganizationId() )   );
        }


        target.setAdjacentOrganizationId( source.getAdjacentOrganizationId() );
        if(!StringUtils.isBlank(source.getAdjacentOrganizationId())){
            target.setAdjacentOrganizationName( organizationNameMap.get( source.getAdjacentOrganizationId() )  );
        }


        target.setPriority( source.getPriority() );
        target.setAdjacentLevel( source.getAdjacentLevel() );
        target.setOrganizationId( source.getOrganizationId() );
        if(!StringUtils.isBlank(source.getOrganizationId())){
            target.setOrganizationName( organizationNameMap.get( source.getOrganizationId() )   );
        }


        target.setRemarks( source.getRemarks() );
        return target;
    }



    /**
     * 描述：联勤保障单位 转换
     *
     * @param source  联勤保障单位PO
     * @return 联勤保障单位BO
     */
    public static UnitJointServiceBean transform(UnitJointServiceEntity source , Map<String, Map<String, String>> dicsMap , Map<String, String>  organizationNameMap   ) {
        if(organizationNameMap==null){
            organizationNameMap=new HashMap<>();
        }

        UnitJointServiceBean target = new UnitJointServiceBean();
        target.setId( source.getId() );
        target.setUnitName( source.getUnitName() );
        target.setSupportability( source.getSupportability() );
        target.setMainSupportCategoryCode( source.getMainSupportCategoryCode() );
        if(!StringUtils.isBlank(source.getMainSupportCategoryCode())&&dicsMap.get("LQBZLB")!=null){
            target.setMainSupportCategoryName(dicsMap.get("LQBZLB").get(  source.getMainSupportCategoryCode() )  );
        }


        target.setDutyPhone( source.getDutyPhone() );
        target.setLongitude( source.getLongitude() );
        target.setLatitude( source.getLatitude() );
        target.setHeight( source.getHeight() );
        target.setRelationId( source.getRelationId() );
        target.setOrganizationId( source.getOrganizationId() );
        if(!StringUtils.isBlank( source.getOrganizationId() )){
            target.setOrganizationName( organizationNameMap.get( source.getOrganizationId()  ) );
        }


        target.setWhetherInnerOrganization( source.getWhetherInnerOrganization() );
        target.setInnerOrganizationId( source.getInnerOrganizationId() );
        target.setOuterOrganizationId( source.getOuterOrganizationId() );
        target.setHospitalLevelCode( source.getHospitalLevelCode() );
        if(!StringUtils.isBlank(source.getHospitalLevelCode())&&dicsMap.get("YYDJ")!=null){
            target.setHospitalLevelName( dicsMap.get("YYDJ").get(  source.getHospitalLevelCode() ) );
        }


        target.setRemarks( source.getRemarks() );

        target.setUnitAddress( source.getUnitAddress() );
        target.setFaxNumber( source.getFaxNumber() );
        target.setUnitDesc( source.getUnitDesc() );

        target.setContactPerson( source.getContactPerson() );
        target.setContactPersonName( source.getContactPersonName() );
        target.setContactPhone( source.getContactPhone() );
        target.setDistrictCode( source.getDistrictCode() );
        if(!StringUtils.isBlank( source.getDistrictCode())&&dicsMap.get("XZQX")!=null){
            target.setDistrictName( dicsMap.get("XZQX").get(  source.getDistrictCode()  )  );
        }




        return target;
    }


    /**
     * 描述：应急联动单位 转换
     *
     * @param source  应急联动单位PO
     * @return 应急联动单位BO
     */
    public static UnitEmergencyBean transform(UnitEmergencyEntity source , Map<String, Map<String, String>> dicsMap , Map<String, String>  organizationNameMap   ) {
        if(organizationNameMap==null){
            organizationNameMap=new HashMap<>();
        }

        UnitEmergencyBean target = new UnitEmergencyBean();
        target.setId( source.getId() );
        target.setDistrictCode( source.getDistrictCode() );
        if(!StringUtils.isBlank( source.getDistrictCode())&&dicsMap.get( "XZQX" )!=null){
            target.setDistrictName( dicsMap.get( "XZQX" ).get( source.getDistrictCode() )  );
        }


        target.setUnitName( source.getUnitName() );
        target.setUnitAddress( source.getUnitAddress() );
        target.setUnitType( source.getUnitType() );
        if(!StringUtils.isBlank( source.getUnitType())&& dicsMap.get( "YJLDDWLBD" )!=null){
            target.setUnitTypeName( dicsMap.get( "YJLDDWLBD" ).get( source.getUnitType() )  );
        }



        target.setEmergencyContent( source.getEmergencyContent() );
        target.setContactPhone( source.getContactPhone() );
        target.setFaxNumber( source.getFaxNumber() );
        target.setOrganizationId( source.getOrganizationId() );
        if(!StringUtils.isBlank( source.getOrganizationId())){
            target.setOrganizationName(  organizationNameMap.get(   source.getOrganizationId() ) );
        }


        target.setLongitude( source.getLongitude() );
        target.setLatitude( source.getLatitude() );
        target.setHeight( source.getHeight() );
        target.setGisRelationId( source.getGisRelationId() );
        target.setPicture( source.getPicture() );
        target.setRemarks( source.getRemarks() );

        target.setUnitDesc( source.getUnitDesc() );
        target.setContactPerson( source.getContactPerson() );
        target.setContactPersonName( source.getContactPersonName() );

        return target;
    }



    /**
     * 描述：微站信息 转换
     *
     * @param source   PO
     * @return  BO
     */
    public static MiniatureStationEntity transform(MiniatureStationSaveInputInfo source    ) {
        MiniatureStationEntity target = new MiniatureStationEntity();
        target.setId(source.getId());
        target.setStationName( source.getStationName() );
        target.setStationAddress( source.getStationAddress() );
        target.setDistrictCode( source.getDistrictCode() );
        target.setSquadronOrganizationId( source.getSquadronOrganizationId() );
        target.setBrigadeOrganizationId( source.getBrigadeOrganizationId() );
        target.setStationStatus( source.getStationStatus() );
        target.setStationDutyType( source.getStationDutyType() );
        target.setStationDispatchStatus( source.getStationDispatchStatus() );
        target.setRoadSection( source.getRoadSection() );
        target.setStationType( source.getStationType() );
        target.setPersonNum( source.getPersonNum() );
//        target.setStationEquipmentDesc( source.getStationEquipmentDesc() );
        target.setDutyPhone( source.getDutyPhone() );
//        target.setContactPerson( source.getContactPerson() );
        target.setContactPersonName( source.getContactPersonName() );
        target.setContactPersonPhone( source.getContactPersonPhone() );
        target.setLongitude( source.getLongitude() );
        target.setLatitude( source.getLatitude() );

        return target;
    }

    /**
     * 描述：微站信息 转换
     *
     * @param source  PO
     * @return BO
     */
    public static MiniatureStationBean transform(MiniatureStationEntity source, Map<String, Map<String, String>> dicsMap , Map<String, String> organizationNameMap   ) {

        if(organizationNameMap==null){
            organizationNameMap=new HashMap<>();
        }
        MiniatureStationBean target = new MiniatureStationBean();
        target.setId(source.getId());
        target.setStationName( source.getStationName() );
        if(Strings.isNotBlank( target.getStationName() ) ){
            target.setPinyinHeader(  PinYinUtils.getDuoYinHeaderNumberInitialsLower( target.getStationName().replaceAll("\\\\s*","") ) );
        }
        target.setStationAddress( source.getStationAddress() );
        target.setDistrictCode( source.getDistrictCode() );
        if(!StringUtils.isBlank( source.getDistrictCode())&& dicsMap.get("XZQX")!=null){
            target.setDistrictName( dicsMap.get("XZQX").get(source.getDistrictCode()) );
        }


        target.setSquadronOrganizationId( source.getSquadronOrganizationId() );
        if(!StringUtils.isBlank( source.getSquadronOrganizationId())){
            target.setSquadronOrganizationName( organizationNameMap.get( source.getSquadronOrganizationId() ));
        }


        target.setBrigadeOrganizationId( source.getBrigadeOrganizationId() );
        if(!StringUtils.isBlank( source.getBrigadeOrganizationId())){
            target.setBrigadeOrganizationName( organizationNameMap.get( source.getBrigadeOrganizationId() ));
        }


        target.setStationStatus( source.getStationStatus() );
        if(!StringUtils.isBlank( source.getStationStatus())&&dicsMap.get("WZZT")!=null){
            target.setStationStatusName(dicsMap.get("WZZT").get(source.getStationStatus()));
        }


        target.setStationDutyType( source.getStationDutyType() );
        if(!StringUtils.isBlank( source.getStationDutyType())&&dicsMap.get("WZZBLX")!=null){
            target.setStationDutyTypeName(dicsMap.get("WZZBLX").get(source.getStationDutyType()));
        }


        target.setStationDispatchStatus( source.getStationDispatchStatus() );
        if(!StringUtils.isBlank( source.getStationDispatchStatus())&&dicsMap.get("WZDPZT")!=null){
            target.setStationDispatchStatusName( dicsMap.get("WZDPZT").get(source.getStationDispatchStatus()));
        }


        target.setRoadSection( source.getRoadSection() );
        target.setStationType( source.getStationType() );
        target.setPersonNum( source.getPersonNum() );
//        target.setStationEquipmentDesc( source.getStationEquipmentDesc() );
        target.setDutyPhone( source.getDutyPhone() );
//        target.setContactPerson( source.getContactPerson() );
        target.setContactPersonName( source.getContactPersonName() );
        target.setContactPersonPhone( source.getContactPersonPhone() );
        target.setLongitude( source.getLongitude() );
        target.setLatitude( source.getLatitude() );

        return target;
    }



}
