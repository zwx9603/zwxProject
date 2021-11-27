package com.dscomm.iecs.accept.utils.transform;


import com.dscomm.iecs.accept.dal.po.UnTrafficAlarmEntity;
import com.dscomm.iecs.accept.graphql.inputbean.IncidentSaveInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.outside.OutsideInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.UnTrafficAlarmBean;
import com.dscomm.iecs.accept.restful.vo.UnTrafficAlarmVO;
import com.dscomm.iecs.accept.service.bean.FireTransferBean;
import com.dscomm.iecs.base.enums.EnableEnum;
import com.dscomm.iecs.basedata.dal.po.OrgRelationshipEntity;
import com.dscomm.iecs.basedata.graphql.inputbean.OrgRelationshipSaveInputInfo;
import com.dscomm.iecs.basedata.graphql.typebean.OrgRelationshipBean;
import com.dscomm.iecs.basedata.restful.vo.OrgRelationshipVo;
import com.dscomm.iecs.ext.incident.handle.HANDLE_TYPE_LA;
import org.mx.StringUtils;

import java.util.Map;
import java.util.Objects;

/**
 * 描述 : 非话务警情 转换工具
 */
public class UntrafficAlarmTransformUtil {



    /**
     * 非话务警情转换
     *
     * @param source 非话务警情INPUT
     * @return 立案警情INPUT
     */
    public static IncidentSaveInputInfo transformIncident(UnTrafficAlarmVO  source   ) {
        if (null != source) {
            IncidentSaveInputInfo target = new IncidentSaveInputInfo();

            target.setAlarmModeCode( source.getAlarmModeCode() );
            target.setRegisterModeCode( source.getRegisterModeCode() );
            target.setIncidentSource( source.getIncidentSource() );

            target.setTelephoneId(null);
            target.setAlarmStartTime( source.getAlarmTime() );
            target.setReceiveStartTime( source.getAlarmTime() );
            target.setAlarmEndTime( source.getAlarmTime()  );
            target.setReceiveEndTime( source.getAlarmTime() );

            target.setSquadronOrganizationId( source.getOrganizationId() );
            target.setBrigadeOrganizationId( null );
            target.setDistrictCode( source.getDistrictCode() );
            target.setRegisterIncidentTime( source.getAlarmTime() );
            target.setCrimeAddress( source.getCrimeAddress() );
            target.setLongitude( source.getLongitude() );
            target.setLatitude( source.getLatitude() );
            target.setHeight( source.getHeight() );
            target.setIncidentTypeCode( source.getIncidentTypeCode() );
            target.setIncidentLevelCode( source.getIncidentLevelCode() );
            target.setDisposalObjectCode( source.getDisposalObjectCode() );
            target.setWhetherImportSign(EnableEnum.ENABLE_FALSE.getCode() );
            target.setKeyUnitId( source.getKeyUnitId() );
            target.setSupplementInfo( null );
            target.setAttentions( null );
            target.setIncidentDescribe( source.getIncidentDescribe() );
            target.setRegisterOrganizationId( source.getOrganizationId() );
            target.setRegisterSeatNumber( null );
            target.setAcceptancePersonNumber( null );
            target.setAcceptancePersonName( null );
            target.setHandleType( HANDLE_TYPE_LA.getCode()); //处理类型 2 为立案
            target.setIncidentLabel( null );
            target.setBuildingStructureCode( source.getBuildingStructureCode() );
            target.setSmogSituationCode( source.getSmogSituationCode() );
            target.setTrappedCount( source.getTrappedCount() );
            target.setStoreysOfBuildings( source.getStoreysOfBuildings() );
            target.setBurningFloor( source.getBurningFloor() );
            target.setBurningArea( source.getBurningArea() );
            target.setDisasterSites( source.getDisasterSites() );
            target.setSecurityContactPerson( null  );
            target.setContactPersonPhone( null  );
            target.setWhetherSensitiveArea(EnableEnum.ENABLE_FALSE.getCode());
            target.setWhetherWaterShortageArea ( EnableEnum.ENABLE_FALSE.getCode());
            target.setWhetherSensitiveTime( EnableEnum.ENABLE_FALSE.getCode() );
            target.setRemarks( source.getRemarks() );
            target.setInjuredCount( null );
            target.setDeathCount( null  );
            target.setSecurityStartTime( null );
            target.setSecurityEndTime( null );
            target.setWhetherTestSign( EnableEnum.ENABLE_FALSE.getCode() );
            return target;
        }
        return null;
    }


    /**
     * 非话务警情转换
     *
     * @param source 非话务警情INPUT
     * @return 非话务警情PO
     */
    public static UnTrafficAlarmEntity transform(UnTrafficAlarmVO source   ) {
        if (null != source) {
            UnTrafficAlarmEntity target = new UnTrafficAlarmEntity();

            target.setAlarmModeCode(source.getAlarmModeCode());
            target.setRegisterModeCode(source.getRegisterModeCode());

            /**  start 受理电话信息 */
            target.setAlarmPhone( source.getAlarmPhone() );
            target.setAlarmPersonName( source.getAlarmPersonName() );
            target.setAlarmPersonSex( source.getAlarmPersonSex() );
            target.setAlarmPersonPhone( source.getAlarmPersonPhone() );
            target.setAlarmAddress( source.getAlarmAddress() );
            target.setRelayRecordNumber( source.getRelayRecordNumber() );

            target.setAlarmStartTime(source.getAlarmStartTime());
            target.setReceiveStartTime(source.getReceiveStartTime());
            target.setAlarmEndTime(source.getAlarmEndTime());
            target.setReceiveEndTime(source.getReceiveEndTime());
            target.setWhetherTrappedSign( source.getWhetherTrappedSign() );
            /**  end受理电话信息 */

            target.setIncidentSource( source.getIncidentSource() );
            target.setOrganizationId( source.getOrganizationId() );
            target.setDistrictCode( source.getDistrictCode() );
            target.setAlarmTime( source.getAlarmTime() );
            target.setCrimeAddress( source.getCrimeAddress() );
            target.setLongitude( source.getLongitude() );
            target.setLatitude( source.getLatitude() );
            target.setHeight( source.getHeight() );
            target.setSmogSituationCode( source.getSmogSituationCode() );
            target.setBuildingStructureCode( source.getBuildingStructureCode() );
            target.setIncidentTypeCode( source.getIncidentTypeCode() );
            target.setIncidentLevelCode( source.getIncidentLevelCode() );
            target.setDisposalObjectCode( source.getDisposalObjectCode() );
            target.setKeyUnitId( source.getKeyUnitId() );
            target.setTrappedCount( source.getTrappedCount() );

            target.setInjuredCount( source.getInjuredCount() );
            target.setDeathCount( source.getDeathCount() );

            target.setIncidentDescribe( source.getIncidentDescribe() );
            target.setStoreysOfBuildings( source.getStoreysOfBuildings() );
            target.setBurningFloor ( source.getBurningFloor() );
            target.setBurningArea( source.getBurningArea() );
            target.setDisasterSites( source.getDisasterSites() );
            target.setRemarks(source.getRemarks());
            target.setOriginalIncidentNumber( source.getIncidentId() );

            target.setSendOrgCode(source.getSendOrgCode());
            return target;
        }
        return null;
    }


    /**
     * 非话务警情转换
     *
     * @param source 非话务警情PO
     * @return 非话务警情BO
     */
    public static UnTrafficAlarmBean transform(UnTrafficAlarmEntity source , Map<String,Map<String,String>> dicsMap ,  Map<String, String> organizationNameMap  ) {
        if (null != source) {
            UnTrafficAlarmBean target = new UnTrafficAlarmBean();
            target.setId( source.getId() );
            target.setUnTrafficAlarmId( source.getId() );

            target.setAlarmModeCode(source.getAlarmModeCode());
            if(!StringUtils.isBlank(source.getAlarmModeCode())&&dicsMap.get("BJFS")!=null){
                target.setAlarmModeName(dicsMap.get("BJFS").get(source.getAlarmModeCode()));
            }



            target.setRegisterModeCode(source.getRegisterModeCode());
            if(!StringUtils.isBlank(source.getRegisterModeCode())&&dicsMap.get("LAFS")!=null){
                target.setRegisterModeName(dicsMap.get("LAFS").get(source.getRegisterModeCode()));
            }




            /**  start 受理电话信息 */
            target.setAlarmPhone( source.getAlarmPhone() );
            target.setAlarmPersonName( source.getAlarmPersonName() );
            target.setAlarmPersonSex( source.getAlarmPersonSex() );
            target.setAlarmPersonPhone( source.getAlarmPersonPhone() );
            target.setAlarmAddress( source.getAlarmAddress() );
            target.setRelayRecordNumber( source.getRelayRecordNumber() );

            target.setAlarmStartTime(source.getAlarmStartTime());
            target.setReceiveStartTime(source.getReceiveStartTime());
            target.setAlarmEndTime(source.getAlarmEndTime());
            target.setReceiveEndTime(source.getReceiveEndTime());
            target.setWhetherTrappedSign( source.getWhetherTrappedSign() );
            /**  end受理电话信息 */

            target.setIncidentId( source.getIncidentId() );
            target.setIncidentSource( source.getIncidentSource() );
            target.setOrganizationId( source.getOrganizationId() );
            if (!StringUtils.isBlank(source.getOrganizationId())&&organizationNameMap!=null){
                target.setOrganizationName( organizationNameMap.get( source.getOrganizationId() ) ) ;
            }
            target.setDistrictCode(source.getDistrictCode());
            if (!StringUtils.isBlank(source.getDistrictCode())&&dicsMap.get("XZQX")!=null){
                target.setDistrictName(dicsMap.get("XZQX").get(source.getDistrictCode()));
            }
            target.setAlarmTime( source.getAlarmTime() );
            target.setCrimeAddress( source.getCrimeAddress() );
            target.setLongitude( source.getLongitude() );
            target.setLatitude( source.getLatitude() );
            target.setHeight( source.getHeight() );

            target.setBuildingStructureCode(source.getBuildingStructureCode());
            if (!StringUtils.isBlank(source.getBuildingStructureCode())&&dicsMap.get("JZJG")!=null){
                target.setBuildingStructureName(dicsMap.get("JZJG").get(source.getBuildingStructureCode()));
            }
            target.setSmogSituationCode(source.getSmogSituationCode());
            if (!StringUtils.isBlank(source.getSmogSituationCode())&&dicsMap.get("YWQK")!=null){
                target.setSmogSituationName(dicsMap.get("YWQK").get(source.getSmogSituationCode()));
            }

            target.setIncidentTypeCode(source.getIncidentTypeCode());
            if (!StringUtils.isBlank(source.getIncidentTypeCode())&&dicsMap.get("AJLX")!=null){
                target.setIncidentTypeName(dicsMap.get("AJLX").get(source.getIncidentTypeCode()));
            }

            target.setIncidentTypeSubitemCode(source.getIncidentTypeSubitemCode());
            if (StringUtils.isBlank(source.getIncidentTypeSubitemCode())&&dicsMap.get("AJLXZX")!=null){
                target.setIncidentTypeSubitemName(dicsMap.get("AJLXZX").get(source.getIncidentTypeSubitemCode()));
            }

            target.setIncidentLevelCode(source.getIncidentLevelCode());
            if (!StringUtils.isBlank(source.getIncidentLevelCode())&&dicsMap.get("AJDJ")!=null){
                target.setIncidentLevelName(dicsMap.get("AJDJ").get(source.getIncidentLevelCode()));
            }


            target.setDisposalObjectCode(source.getDisposalObjectCode());
            if (!StringUtils.isBlank(source.getDisposalObjectCode())&&dicsMap.get("CZDX")!=null){
                target.setDisposalObjectName(dicsMap.get("CZDX").get(source.getDisposalObjectCode()));
            }

            target.setKeyUnitId( source.getKeyUnitId() );
            target.setTrappedCount( source.getTrappedCount() );
            target.setInjuredCount( source.getInjuredCount() );
            target.setDeathCount( source.getDeathCount() );
            target.setIncidentDescribe( source.getIncidentDescribe() );
            target.setStoreysOfBuildings( source.getStoreysOfBuildings() );
            target.setBurningFloor ( source.getBurningFloor() );
            target.setBurningArea( source.getBurningArea() );
            target.setDisasterSites(source.getDisasterSites());
            if (!StringUtils.isBlank(source.getDisasterSites())&&dicsMap.get("ZHCS")!=null){
                target.setDisasterSitesName(dicsMap.get("ZHCS").get(source.getDisasterSites()) );
            }

            target.setRemarks(source.getRemarks());
            target.setOriginalIncidentNumber( source.getOriginalIncidentNumber() );
            return target;
        }
        return null;
    }

    public static FireTransferBean transform(OutsideInputInfo source){
       
        if (Objects.nonNull(source)){
            FireTransferBean target = new FireTransferBean();
            target.setAlarmPersonContact(source.getIncident().getAlarmPersonPhone());
            target.setAlarmPersonAddress(source.getIncident().getAlarmAddress());
            target.setContent(source.getIncident().getIncidentDescribe());
            target.setAddress(source.getIncident().getCrimeAddress());
            target.setLongitude(source.getIncident().getLongitude());
            target.setLatitude(source.getIncident().getLatitude());
            target.setAlarmPersonSex(source.getIncident().getAlarmPersonSex());
            target.setAlarmPersonName(source.getIncident().getAlarmPersonName());
            target.setAlarmWay(source.getIncident().getAlarmModeCode());
            target.setRecord(source.getIncident().getRelayRecordNumber());
            target.setAlarmNumber(source.getIncidentId());
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
    public static OrgRelationshipEntity transform(OrgRelationshipSaveInputInfo source ) {
        if (null != source) {
            OrgRelationshipEntity target = new OrgRelationshipEntity();
            target.setTransferType(source.getTransferType());
            target.setTransferDeptCode(source.getTransferDeptCode());
            target.setIsAudit(source.getIsAudit());
            target.setFireDeptId(source.getFireDeptId());
            return target;
        }
        return null;
    }
    public static OrgRelationshipEntity transform(OrgRelationshipVo source ) {
        if (null != source) {
            OrgRelationshipEntity target = new OrgRelationshipEntity();
            target.setTransferType(source.getTransferType());
            target.setTransferDeptCode(source.getTransferDeptCode());
            target.setIsAudit(source.getIsAudit());
            target.setFireDeptId(source.getFireDeptId());
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
    public static OrgRelationshipBean transform(OrgRelationshipEntity source ) {
        if (null != source) {
            OrgRelationshipBean target = new OrgRelationshipBean();
            target.setTransferType(source.getTransferType());
            target.setTransferDeptCode(source.getTransferDeptCode());
            target.setIsAudit(source.getIsAudit());
            target.setFireDeptId(source.getFireDeptId());
            target.setId(source.getId());
            return target;
        }
        return null;
    }


}
