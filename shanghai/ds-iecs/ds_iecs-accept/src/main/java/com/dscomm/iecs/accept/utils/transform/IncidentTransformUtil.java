package com.dscomm.iecs.accept.utils.transform;


import com.dscomm.iecs.accept.dal.po.*;
import com.dscomm.iecs.accept.graphql.fireinputbean.IncidentImportantRuleSaveInputInfo;
import com.dscomm.iecs.accept.graphql.firetypebean.IncidentImportantRuleBean;
import com.dscomm.iecs.accept.graphql.inputbean.*;
import com.dscomm.iecs.accept.graphql.typebean.*;
import com.dscomm.iecs.accept.service.bean.dahua.alarm.JQDX;
import com.dscomm.iecs.base.enums.EnableEnum;
import org.apache.logging.log4j.util.Strings;
import org.mx.StringUtils;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述 : 电话报警单 受理单 事件单转换工具
 */
public class IncidentTransformUtil {


    public static final com.dscomm.iecs.accept.service.bean.dahua.alarm.JQDX JQDX = new JQDX();



    /**
     * 电话报警记录转换
     *
     * @param source 电话报警记录PO
     * @return 电话报警记录BO
     */
    public static TelephoneBean transform(TelephoneEntity source, Map<String, Map<String, String>> dicsMap) {
        if (null != source) {
            TelephoneBean target = new TelephoneBean();
            target.setId(source.getId());
            target.setTelephoneId(  source.getId() );
            target.setAcceptanceId(source.getAcceptanceId());
            target.setIncidentId(source.getIncidentId());
            target.setCallDirection(source.getCallDirection());
            if(!StringUtils.isBlank(source.getCallDirection())&&dicsMap.get("HJFX")!=null){
                target.setCallDirectionName(dicsMap.get("HJFX").get(source.getCallDirection()));
            }


            target.setSeatNumber(source.getSeatNumber());
            target.setPersonId(source.getPersonId());
            target.setPersonName(source.getPersonName());
            target.setPersonNumber(source.getPersonNumber());
            target.setCallingNumber(source.getCallingNumber());
            target.setCalledNumber(source.getCalledNumber());
            target.setAcdGroupNumber(source.getAcdGroupNumber());
            target.setRingingTime(source.getRingingTime());
            target.setAnswerTime(source.getAnswerTime());
            target.setEndTime(source.getEndTime());
            target.setRingingDuration(source.getRingingDuration());
            target.setConversationDuration(source.getConversationDuration());
            target.setRelayRecordNumber(source.getRelayRecordNumber());
            target.setInstalledUserName(source.getInstalledUserName());
            target.setInstalledAddress(source.getInstalledAddress());
            target.setAlarmPersonName(source.getAlarmPersonName());
            target.setAlarmPersonSex(source.getAlarmPersonSex());
            target.setAlarmPersonPhone(source.getAlarmPersonPhone());
            target.setAlarmAddress(source.getAlarmAddress());
            target.setLongitude(source.getLongitude());
            target.setLatitude(source.getLatitude());
            target.setHeight(source.getHeight());
            target.setRemarks(source.getRemarks());

            target.setWhetherTrappedSign(source.getWhetherTrappedSign());

            return target;
        }
        return null;
    }


    /**
     * 电话库 转换
     *
     * @param source 电话库 PO
     * @return 电话库 BO
     */
    public static PhoneLibraryBean transform(PhoneLibraryEntity source) {
        if (null != source) {
            PhoneLibraryBean target = new PhoneLibraryBean();
            target.setPhoneNumber(source.getPhoneNumber());
            target.setUserName(source.getUserName());
            target.setUserAddress(source.getUserAddress());
            target.setRemarks(source.getRemarks());
            target.setId(source.getId());
            return target;
        }
        return null;
    }

    /**
     * 标签转换
     *
     * @param source 标签INPUT
     * @return 标签BO
     */
    public static void transform(TagLabelSaveInputInfo source, TagLabelEntity target, Long currentSystemTime) {
        if (null != source) {
            target.setBusinessTable(source.getBusinessTable());
            target.setBusinessDataId(source.getBusinessDataId());
            target.setTagType(source.getTagType());
            target.setRemarksTitle(source.getRemarksTitle());
            target.setRemarksContent(source.getRemarksContent());
            target.setAddTime(currentSystemTime);
            target.setRemarks(source.getRemarks());
        }
    }

    /**
     * 标签转换
     *
     * @param source 标签PO
     * @return 标签BO
     */
    public static TagLabelBean transform(TagLabelEntity source, Map<String, Map<String, String>> dicsMap) {
        if (null != source) {
            TagLabelBean target = new TagLabelBean();
            target.setBusinessTable(source.getBusinessTable());
            if(!StringUtils.isBlank(source.getBusinessTable())&&dicsMap.get("BQLX")!=null){
                target.setBusinessTableName(dicsMap.get("BQLX").get(source.getBusinessTable()));
            }


            target.setBusinessDataId(source.getBusinessDataId());
            String tagType = source.getTagType();
            target.setTagType(tagType);
            if (Strings.isNotBlank(tagType) && tagType.indexOf(",") < 0) {
                target.setTagTypeName(dicsMap.get("BQMX").get(tagType));
            } else {
                String[] tagTypesj = source.getTagType().split(",");
                String tagTypeName = "";
                for (String key : tagTypesj) {
                    tagTypeName = tagTypeName + dicsMap.get("BQMX").get(key) + ",";
                }
                target.setTagTypeName(tagTypeName.substring(0, tagTypeName.lastIndexOf(",")));
            }

            target.setRemarksTitle(source.getRemarksTitle());
            target.setRemarksContent(source.getRemarksContent());
            target.setAddTime(source.getAddTime());
            target.setRevokeTime(source.getRevokeTime());
            target.setRemarks(source.getRemarks());
            target.setPersonId(source.getPersonId());
            target.setPersonName(source.getPersonName());
            target.setId(source.getId());
            return target;
        }
        return null;
    }

    /**
     * 接警处警提示信息转换
     *
     * @param source 接/处警提示信息PO
     * @return 接/处警提示信息BO
     */
    public static CommonTipsBean transform(CommonTipsEntity source, Map<String, Map<String, String>> dicsMap) {
        if (null != source) {
            CommonTipsBean target = new CommonTipsBean();
            target.setId(source.getId());
            target.setType(source.getType());
            if(!StringUtils.isBlank(source.getType())&&dicsMap.get("AJLX")!=null){
                target.setTypeName(dicsMap.get("AJLX").get(source.getType()));
            }


            target.setCode(source.getCode());
            if(!StringUtils.isBlank(source.getCode())&&dicsMap.get("CZDX")!=null){
                target.setName(dicsMap.get("CZDX").get(source.getCode()));
            }


            target.setLevel(source.getLevel());
            if(!StringUtils.isBlank(source.getLevel())&&dicsMap.get("AJDJ")!=null){
                target.setLevelName(dicsMap.get("AJDJ").get(source.getLevel()));
            }


            target.setDefaultValue(source.getDefaultValue());
            target.setReceiveTips(source.getReceiveTips());
            target.setHandleTips(source.getHandleTips());
            target.setRemarks(source.getRemarks());
            target.setSafetyTips(source.getSafetyTips());
            return target;
        }
        return null;
    }

    /**
     * 接警处警提示信息转换
     *
     * @param source 接/处警提示信息PO
     * @return 接/处警提示信息BO
     */
    public static CommonTipsEntity transform(CommonTipsInputInfo source) {
        if (null != source) {
            CommonTipsEntity target = new CommonTipsEntity();
            target.setId(source.getId());
            target.setType(source.getType());
            target.setCode(source.getCode());
            target.setLevel(source.getLevel());
            target.setDefaultValue(source.getDefaultValue());
            target.setReceiveTips(source.getReceiveTips());
            target.setHandleTips(source.getHandleTips());
            target.setRemarks(source.getRemarks());
            target.setSafetyTips(source.getSafetyTips());
            return target;
        }
        return null;
    }

    /**
     * 预警信息转换
     *
     * @param source 预警信息INPUT
     * @return 预警信息PO
     */
    public static EarlyWarningEntity transform(EarlyWarningSaveInputInfo source) {
        if (null != source) {
            EarlyWarningEntity target = new EarlyWarningEntity();
            target.setIncidentId(source.getIncidentId());
            target.setReceiveOrganizationId(source.getReceiveOrganizationId());
            target.setEarlyWarningType(source.getEarlyWarningType());
            target.setEarlyWarningStatus(String.valueOf(EnableEnum.ENABLE_FALSE.getCode()));
            target.setOrganizationId(source.getOrganizationId());
            target.setDistrictCode(source.getDistrictCode());
            target.setAlarmTime(source.getAlarmTime());
            target.setCrimeAddress(source.getCrimeAddress());
            target.setLongitude(source.getLongitude());
            target.setLatitude(source.getLatitude());
            target.setHeight(source.getHeight());
            target.setIncidentTypeCode(source.getIncidentTypeCode());
            target.setIncidentTypeSubitemCode(source.getIncidentTypeSubitemCode());
            target.setIncidentLevelCode(source.getIncidentLevelCode());
            target.setAlarmPhone(source.getAlarmPhone());
            target.setSendOrganizationId(source.getSendOrganizationId());
            target.setSendPersonNumber(source.getSendPersonNumber());
            target.setSendSeatNumber(source.getSendSeatNumber());
            target.setRemarks(source.getRemarks());

            target.setWhetherTestSign(source.getWhetherTestSign());
            return target;
        }
        return null;
    }

    /**
     * 预警信息转换
     *
     * @param source 预警信息PO
     * @return 预警信息BO
     */
    public static EarlyWarningBean transform(EarlyWarningEntity source, Map<String, Map<String, String>> dicsMap, Map<String, String> organizationNameMap) {
        if(organizationNameMap==null){
            organizationNameMap=new HashMap<>();
        }

        if (null != source) {
            EarlyWarningBean target = new EarlyWarningBean();
            target.setId(source.getId());
            target.setEarlyWarningType(source.getEarlyWarningType());
            target.setEarlyWarningStatus(source.getEarlyWarningStatus());
            target.setIncidentId(source.getIncidentId());
            target.setOrganizationId(source.getOrganizationId());
            if(!StringUtils.isBlank(source.getOrganizationId())){
                target.setOrganizationName(organizationNameMap.get(source.getOrganizationId()));
            }


            target.setReceiveOrganizationId(source.getReceiveOrganizationId());
            if(!StringUtils.isBlank(source.getReceiveOrganizationId())){
                target.setReceiveOrganizationName(organizationNameMap.get(source.getReceiveOrganizationId()));
            }


            target.setSendOrganizationId(source.getSendOrganizationId());
            if(!StringUtils.isBlank(source.getSendOrganizationId())){
                target.setSendOrganizationName(organizationNameMap.get(source.getSendOrganizationId()));
            }


            target.setIncidentLevelCode(source.getIncidentLevelCode());
            if(!StringUtils.isBlank(source.getIncidentLevelCode())&&dicsMap.get("AJDJ")!=null){
                target.setIncidentLevelName(dicsMap.get("AJDJ").get(source.getIncidentLevelCode()));
            }



            target.setIncidentTypeCode(source.getIncidentTypeCode());
            if(!StringUtils.isBlank(source.getIncidentTypeCode())&&dicsMap.get("AJLX")!=null){
                target.setIncidentTypeName(dicsMap.get("AJLX").get(source.getIncidentTypeCode()));
            }



            target.setIncidentTypeSubitemCode(source.getIncidentTypeSubitemCode());
            if(!StringUtils.isBlank(source.getIncidentTypeSubitemCode())&&dicsMap.get("AJLXZX")!=null){
                target.setIncidentTypeSubitemName(dicsMap.get("AJLXZX").get(source.getIncidentTypeSubitemCode()));
            }



            target.setDistrictCode(source.getDistrictCode());
            if(!StringUtils.isBlank(source.getDistrictCode())&&dicsMap.get("XZQX")!=null){
                target.setDistrictName(dicsMap.get("XZQX").get(source.getDistrictCode()));
            }



            target.setAlarmTime(source.getAlarmTime());
            target.setCrimeAddress(source.getCrimeAddress());
            target.setLongitude(source.getLongitude());
            target.setLatitude(source.getLatitude());
            target.setHeight(source.getHeight());
            target.setAlarmPhone(source.getAlarmPhone());
            target.setSendPersonNumber(source.getSendPersonNumber());
            target.setSendSeatNumber(source.getSendSeatNumber());
            target.setSendTime(source.getSendTime());
            target.setRevokeTime(source.getRevokeTime());
            target.setRemarks(source.getRemarks());

            target.setWhetherTestSign(source.getWhetherTestSign());
            return target;
        }
        return null;
    }


    /**
     * 受理单信息转换entity
     *
     * @param source 案件信息INPUT
     * @return 受理单信息PO
     */
    public static   AcceptanceEntity transform(IncidentSaveInputInfo source) {
        if (null != source) {
            AcceptanceEntity target = new AcceptanceEntity();

            target.setId(source.getAcceptanceId());
            target.setIncidentSource(source.getIncidentSource());
            target.setTelephoneId(source.getTelephoneId());

            /**  start 受理电话信息 */
            target.setAlarmPhone(source.getAlarmPhone());
            target.setAlarmPersonName(source.getAlarmPersonName());
            target.setAlarmPersonSex(source.getAlarmPersonSex());
            target.setAlarmPersonPhone(source.getAlarmPersonPhone());
            target.setAlarmAddress(source.getAlarmAddress());
            target.setRelayRecordNumber(source.getRelayRecordNumber());

            target.setAlarmStartTime(source.getAlarmStartTime());
            target.setReceiveStartTime(source.getReceiveStartTime());
            target.setAlarmEndTime(source.getAlarmEndTime());
            target.setReceiveEndTime(source.getReceiveEndTime());
            target.setWhetherTrappedSign(source.getWhetherTrappedSign());
            /**  end受理电话信息 */


            target.setAlarmModeCode(source.getAlarmModeCode());

            target.setSquadronOrganizationId(source.getSquadronOrganizationId());//主管中队
            target.setBrigadeOrganizationId(source.getBrigadeOrganizationId());//所属大队
            target.setDistrictCode(source.getDistrictCode());
            target.setRegisterModeCode(source.getRegisterModeCode());
            target.setRegisterIncidentTime(source.getRegisterIncidentTime());

            target.setTitle(source.getTitle());

            target.setCrimeAddress(source.getCrimeAddress());
            target.setLongitude(source.getLongitude());
            target.setLatitude(source.getLatitude());
            target.setHeight(source.getHeight());
            target.setIncidentTypeCode(source.getIncidentTypeCode());
            target.setIncidentTypeSubitemCode(source.getIncidentTypeSubitemCode());
            target.setIncidentLevelCode(source.getIncidentLevelCode());
            target.setDisposalObjectCode(source.getDisposalObjectCode());
            target.setIncidentStatusCode(source.getIncidentStatusCode()); //默认立案
            target.setWhetherImportSign(source.getWhetherImportSign());

            target.setKeyUnit(source.getKeyUnitId());
            target.setKeyUnitId(source.getKeyUnitId());


            target.setSupplementInfo(source.getSupplementInfo());
            target.setAttentions(source.getAttentions());
            target.setIncidentDescribe(source.getIncidentDescribe());

            target.setRegisterOrganizationId(source.getRegisterOrganizationId());//立案单位
            target.setAcceptancePerson(source.getAcceptancePerson());
            target.setAcceptancePersonId(source.getAcceptancePersonId());
            target.setRegisterSeatNumber(source.getRegisterSeatNumber());
            target.setAcceptancePersonNumber(source.getAcceptancePersonNumber());
            target.setAcceptancePersonName(source.getAcceptancePersonName());

            target.setHandleType(source.getHandleType());
            if (source.getReceiveStartTime() != null && source.getReceiveStartTime() != 0
                    && source.getReceiveEndTime() != null && source.getReceiveEndTime() != 0 ) {
                target.setReceiveDuration(source.getReceiveEndTime() - source.getReceiveStartTime());
            } else {
                target.setReceiveDuration(0l);
            }
            target.setBuildingStructureCode(source.getBuildingStructureCode());
            target.setSmogSituationCode(source.getSmogSituationCode());
            target.setTrappedCount(source.getTrappedCount());
            target.setStoreysOfBuildings(source.getStoreysOfBuildings());
            target.setBurningFloor(source.getBurningFloor());
            target.setBurningArea(source.getBurningArea());
            target.setDisasterSites(source.getDisasterSites());

            target.setSecurityContactPerson(source.getSecurityContactPerson());
            target.setContactPersonPhone(source.getContactPersonPhone());

            target.setWhetherSensitiveArea(source.getWhetherSensitiveArea());
            target.setWhetherWaterShortageArea(source.getWhetherWaterShortageArea());
            target.setWhetherSensitiveTime(source.getWhetherSensitiveTime());

            target.setRemarks(source.getRemarks());

            target.setInjuredCount(source.getInjuredCount());
            target.setDeathCount(source.getDeathCount());
            target.setSecurityStartTime(source.getSecurityStartTime());
            target.setSecurityEndTime(source.getSecurityEndTime());
            target.setWhetherTestSign(source.getWhetherTestSign());

            return target;
        }
        return null;
    }


    /**
     * 警情信息转换 立案 input 转 entity
     *
     * @param source       警情信息INPUT
     * @param acceptanceId 受理单id
     * @return 警情信息PO
     */
    public static   IncidentEntity transform(IncidentSaveInputInfo source, String acceptanceId) {
        if (null != source) {
            IncidentEntity target = new IncidentEntity();
            target.setId(source.getId());
            target.setIncidentSource(source.getIncidentSource());
            /**  start 警情电话信息 */
            target.setAlarmPhone(source.getAlarmPhone());
            target.setAlarmPersonName(source.getAlarmPersonName());
            target.setAlarmPersonSex(source.getAlarmPersonSex());
            target.setAlarmPersonPhone(source.getAlarmPersonPhone());
            target.setAlarmAddress(source.getAlarmAddress());
            target.setRelayRecordNumber(source.getRelayRecordNumber());

            target.setAlarmStartTime(source.getAlarmStartTime());
            target.setReceiveStartTime(source.getReceiveStartTime());
            target.setAlarmEndTime(source.getAlarmEndTime());
            target.setReceiveEndTime(source.getReceiveEndTime());
            target.setWhetherTrappedSign(source.getWhetherTrappedSign());
            /**  end警情电话信息 */


            target.setIncidentNumber(source.getIncidentNumber());
            target.setAcceptanceId(acceptanceId);
            target.setSquadronOrganizationId(source.getSquadronOrganizationId());
            target.setBrigadeOrganizationId(source.getBrigadeOrganizationId());
            target.setDistrictCode(source.getDistrictCode());
            target.setAlarmModeCode(source.getAlarmModeCode());
            target.setRegisterModeCode(source.getRegisterModeCode());
            target.setRegisterIncidentTime(source.getRegisterIncidentTime());

            target.setTitle(source.getTitle());
            target.setCrimeAddress(source.getCrimeAddress());

            target.setLongitude(source.getLongitude());
            target.setLatitude(source.getLatitude());
            target.setHeight(source.getHeight());
            target.setIncidentTypeCode(source.getIncidentTypeCode());
            target.setIncidentTypeSubitemCode(source.getIncidentTypeSubitemCode());
            target.setIncidentLevelCode(source.getIncidentLevelCode());
            target.setDisposalObjectCode(source.getDisposalObjectCode());
            target.setIncidentStatusCode(source.getIncidentStatusCode());
            target.setIncidentNatureCode(source.getIncidentNatureCode());
            target.setWhetherImportSign(source.getWhetherImportSign());

            target.setKeyUnit(source.getKeyUnitId());
            target.setKeyUnitId(source.getKeyUnitId());

            target.setSupplementInfo(source.getSupplementInfo());
            target.setAttentions(source.getAttentions());
            target.setIncidentDescribe(source.getIncidentDescribe());
            target.setMergeStatus(EnableEnum.ENABLE_FALSE.getCode());
            target.setWhetherMainMerge(EnableEnum.ENABLE_FALSE.getCode());
            target.setRegisterOrganizationId(source.getRegisterOrganizationId());
            target.setRegisterSeatNumber(source.getRegisterSeatNumber());
            target.setAcceptancePersonNumber(source.getAcceptancePersonNumber());
            target.setAcceptancePersonName(source.getAcceptancePersonName());
            target.setHandleType(source.getHandleType());

            target.setIncidentLabel(source.getIncidentLabel());

            target.setBuildingStructureCode(source.getBuildingStructureCode());
            target.setSmogSituationCode(source.getSmogSituationCode());
            target.setTrappedCount(source.getTrappedCount());
            target.setStoreysOfBuildings(source.getStoreysOfBuildings());
            target.setBurningFloor(source.getBurningFloor());
            target.setBurningArea(source.getBurningArea());
            target.setDisasterSites(source.getDisasterSites());

            target.setSecurityContactPerson(source.getSecurityContactPerson());
            target.setContactPersonPhone(source.getContactPersonPhone());

            target.setWhetherSensitiveArea(source.getWhetherSensitiveArea());
            target.setWhetherWaterShortageArea(source.getWhetherWaterShortageArea());
            target.setWhetherSensitiveTime(source.getWhetherSensitiveTime());


            target.setRemarks(source.getRemarks());
            target.setInjuredCount(source.getInjuredCount());
            target.setDeathCount(source.getDeathCount());
            target.setSecurityStartTime(source.getSecurityStartTime());
            target.setSecurityEndTime(source.getSecurityEndTime());
            target.setWhetherTestSign(source.getWhetherTestSign());
            target.setUniformAddressId(source.getUniformAddressId());//统一地址库id



            return target;
        }
        return null;
    }

    /**
     * 受理单信息转换bean
     *
     * @param source 案件信息PO
     * @return 受理单信息BO
     */
    public static AcceptanceBean transform(AcceptanceEntity source, Map<String, Map<String, String>> dicsMap, Map<String, String> organizationNameMap) {
        if(organizationNameMap==null){
            organizationNameMap=new HashMap<>();
        }

        if (null != source) {
            AcceptanceBean target = new AcceptanceBean();
            target.setId(source.getId());
            target.setIdCode(source.getIdCode());
            target.setIncidentSource(source.getIncidentSource());

            target.setTelephoneId(source.getTelephoneId());
            target.setAcceptanceTime(source.getAcceptanceTime());

            /**  start 受理电话信息 */
            target.setAlarmPhone(source.getAlarmPhone());
            target.setAlarmPersonName(source.getAlarmPersonName());
            target.setAlarmPersonSex(source.getAlarmPersonSex());
            if(!StringUtils.isBlank(source.getAlarmPersonSex())&&dicsMap.get("XB")!=null){
                target.setAlarmPersonSexName(dicsMap.get("XB").get(source.getAlarmPersonSex()));
            }


            target.setAlarmPersonPhone(source.getAlarmPersonPhone());
            target.setAlarmAddress(source.getAlarmAddress());
            target.setRelayRecordNumber(source.getRelayRecordNumber());

            target.setAlarmStartTime(source.getAlarmStartTime());
            target.setReceiveStartTime(source.getReceiveStartTime());
            target.setAlarmEndTime(source.getAlarmEndTime());
            target.setReceiveEndTime(source.getReceiveEndTime());
            target.setWhetherTrappedSign(source.getWhetherTrappedSign());
            /**  end受理电话信息 */


            target.setIncidentId(source.getIncidentId());
            target.setOriginalIncidentNumber(source.getOriginalIncidentNumber());
            target.setAlarmModeCode(source.getAlarmModeCode());
            if(!StringUtils.isBlank(source.getAlarmModeCode())&&dicsMap.get("BJFS")!=null){
                target.setAlarmModeName(dicsMap.get("BJFS").get(source.getAlarmModeCode()));
            }


            target.setOrganizationId(source.getSquadronOrganizationId());
            if(!StringUtils.isBlank(source.getSquadronOrganizationId())){
                target.setOrganizationName(organizationNameMap.get(source.getSquadronOrganizationId()));
            }


            target.setBrigadeOrganizationId(source.getBrigadeOrganizationId());
            if(!StringUtils.isBlank(source.getBrigadeOrganizationId())){
                target.setBrigadeOrganizationName(organizationNameMap.get(source.getBrigadeOrganizationId()));
            }


            target.setDistrictCode(source.getDistrictCode());
            if(!StringUtils.isBlank(source.getDistrictCode())&&dicsMap.get("XZQX")!=null){
                target.setDistrictName(dicsMap.get("XZQX").get(source.getDistrictCode()));
            }


            target.setRegisterModeCode(source.getRegisterModeCode());
            if(!StringUtils.isBlank(source.getRegisterModeCode())&&dicsMap.get("LAFS")!=null){
                target.setRegisterModeName(dicsMap.get("LAFS").get(source.getRegisterModeCode()));
            }


            target.setRegisterIncidentTime(source.getRegisterIncidentTime());

            target.setTitle(source.getTitle());
            target.setCrimeAddress(source.getCrimeAddress());

            target.setLongitude(source.getLongitude());
            target.setLatitude(source.getLatitude());
            target.setHeight(source.getHeight());
            target.setIncidentTypeCode(source.getIncidentTypeCode());
            if(!StringUtils.isBlank(source.getIncidentTypeCode())&&dicsMap.get("AJLX")!=null){
                target.setIncidentTypeName(dicsMap.get("AJLX").get(source.getIncidentTypeCode()));
            }


            target.setIncidentTypeSubitemCode(source.getIncidentTypeSubitemCode());
            if(!StringUtils.isBlank(source.getIncidentTypeSubitemCode())&&dicsMap.get("AJLXZX")!=null){
                target.setIncidentTypeSubitemName(dicsMap.get("AJLXZX").get(source.getIncidentTypeSubitemCode()));
            }


            target.setIncidentLevelCode(source.getIncidentLevelCode());
            if(!StringUtils.isBlank(source.getIncidentLevelCode())&&dicsMap.get("AJDJ")!=null){
                target.setIncidentLevelName(dicsMap.get("AJDJ").get(source.getIncidentLevelCode()));
            }


            target.setDisposalObjectCode(source.getDisposalObjectCode());
            if(!StringUtils.isBlank(source.getDisposalObjectCode())&&dicsMap.get("CZDX")!=null){
                target.setDisposalObjectName(dicsMap.get("CZDX").get(source.getDisposalObjectCode()));
            }


            target.setIncidentStatusCode(source.getIncidentStatusCode());
            if(!StringUtils.isBlank(source.getIncidentStatusCode())&&dicsMap.get("AJZT")!=null){
                target.setIncidentStatusName(dicsMap.get("AJZT").get(source.getIncidentStatusCode()));
            }


            target.setWhetherImportSign(source.getWhetherImportSign());

            target.setKeyUnit(source.getKeyUnit());
            target.setKeyUnitId(source.getKeyUnitId());
            target.setKeyUnitTypeCode(source.getKeyUnitTypeCode());
            if(!StringUtils.isBlank(source.getKeyUnitTypeCode())&&dicsMap.get("JQDX")!=null){
                target.setKeyUnitTypeName(dicsMap.get("JQDX").get(source.getKeyUnitTypeCode()));
            }



            target.setSupplementInfo(source.getSupplementInfo());
            target.setAttentions(source.getAttentions());
            target.setIncidentDescribe(source.getIncidentDescribe());
            target.setRegisterOrganizationId(source.getRegisterOrganizationId());
            if(!StringUtils.isBlank(source.getRegisterOrganizationId())){
                target.setRegisterOrganizationName(organizationNameMap.get(source.getRegisterOrganizationId()));
            }


            target.setRegisterSeatNumber(source.getRegisterSeatNumber());
            target.setAcceptancePerson(source.getAcceptancePerson());
            target.setAcceptancePersonId(source.getAcceptancePersonId());
            target.setAcceptancePersonNumber(source.getAcceptancePersonNumber());
            target.setAcceptancePersonName(source.getAcceptancePersonName());
            target.setHandleType(source.getHandleType());
            target.setReceiveDuration(source.getReceiveDuration());
            target.setBuildingStructureCode(source.getBuildingStructureCode());
            if(!StringUtils.isBlank(source.getBuildingStructureCode())&&dicsMap.get("JZJG")!=null){
                target.setBuildingStructureName(dicsMap.get("JZJG").get(source.getBuildingStructureCode()));
            }


            target.setSmogSituationCode(source.getSmogSituationCode());
            if(!StringUtils.isBlank(source.getSmogSituationCode())&&dicsMap.get("YWQK")!=null){
                target.setSmogSituationName(dicsMap.get("YWQK").get(source.getSmogSituationCode()));
            }


            target.setTrappedCount(source.getTrappedCount());
            target.setStoreysOfBuildings(source.getStoreysOfBuildings());
            target.setBurningFloor(source.getBurningFloor());
            target.setBurningArea(source.getBurningArea());

            target.setDisasterSites(source.getDisasterSites());
            if(!StringUtils.isBlank(source.getDisasterSites())&&dicsMap.get("ZHCS")!=null){
                target.setDisasterSitesName(dicsMap.get("ZHCS").get(source.getDisasterSites()));
            }



            target.setSecurityContactPerson(source.getSecurityContactPerson());
            target.setContactPersonPhone(source.getContactPersonPhone());
            target.setWhetherSensitiveArea(source.getWhetherSensitiveArea());
            target.setWhetherWaterShortageArea(source.getWhetherWaterShortageArea());
            target.setWhetherSensitiveTime(source.getWhetherSensitiveTime());
            target.setRemarks(source.getRemarks());

            target.setInjuredCount(source.getInjuredCount());
            target.setDeathCount(source.getDeathCount());
            target.setSecurityStartTime(source.getSecurityStartTime());
            target.setSecurityEndTime(source.getSecurityEndTime());
            target.setWhetherTestSign(source.getWhetherTestSign());

            return target;
        }
        return null;
    }





    /**
     * 案件基本信息转换 立案 / 更新警情构建临时bean entity 转 bean
     *
     * @param source 案件基本信息信息PO
     * @return 案件基本信息信息BO
     */
    public static IncidentBean transform(IncidentEntity source, Map<String, Map<String, String>> dicsMap, Map<String, String> organizationNameMap) {
        if(organizationNameMap==null){
            organizationNameMap=new HashMap<>();
        }

        if (null != source) {
            IncidentBean target = new IncidentBean();
            target.setId(source.getId());

            /**  start 警情电话信息 */
            target.setAlarmPhone(source.getAlarmPhone());
            target.setAlarmPersonName(source.getAlarmPersonName());
            target.setAlarmPersonSex(source.getAlarmPersonSex());
            if(!StringUtils.isBlank(source.getAlarmPersonSex())&&dicsMap.get("XB")!=null){
                target.setAlarmPersonSexName(dicsMap.get("XB").get(source.getAlarmPersonSex()));
            }


            target.setAlarmPersonPhone(source.getAlarmPersonPhone());
            target.setAlarmAddress(source.getAlarmAddress());
            target.setRelayRecordNumber(source.getRelayRecordNumber());

            target.setAlarmStartTime(source.getAlarmStartTime());
            target.setReceiveStartTime(source.getReceiveStartTime());
            target.setAlarmEndTime(source.getAlarmEndTime());
            target.setReceiveEndTime(source.getReceiveEndTime());
            target.setWhetherTrappedSign(source.getWhetherTrappedSign());
            /**  end警情电话信息 */

            target.setIncidentNumber(source.getIncidentNumber());
            target.setAcceptanceId(source.getAcceptanceId());
            target.setSquadronOrganizationId(source.getSquadronOrganizationId());
            if(!StringUtils.isBlank(source.getSquadronOrganizationId())){
                target.setSquadronOrganizationName(organizationNameMap.get(source.getSquadronOrganizationId()));
            }


            target.setBrigadeOrganizationId(source.getBrigadeOrganizationId());
            if(!StringUtils.isBlank(source.getBrigadeOrganizationId())){
                target.setBrigadeOrganizationName(organizationNameMap.get(source.getBrigadeOrganizationId()));
            }



            target.setDistrictCode(source.getDistrictCode());
            if(!StringUtils.isBlank(source.getDistrictCode())&&dicsMap.get("XZQX")!=null){
                target.setDistrictName(dicsMap.get("XZQX").get(source.getDistrictCode()));
            }


            target.setAlarmModeCode(source.getAlarmModeCode());
            if(!StringUtils.isBlank(source.getAlarmModeCode())&&dicsMap.get("BJFS")!=null){
                target.setAlarmModeName(dicsMap.get("BJFS").get(source.getAlarmModeCode()));
            }



            target.setRegisterModeCode(source.getRegisterModeCode());
            if(!StringUtils.isBlank(source.getRegisterModeCode())&&dicsMap.get("LAFS")!=null){
                target.setRegisterModeName(dicsMap.get("LAFS").get(source.getRegisterModeCode()));
            }



            target.setRegisterIncidentTime(source.getRegisterIncidentTime());
            target.setTitle(source.getTitle());
            target.setCrimeAddress(source.getCrimeAddress());
            target.setLongitude(source.getLongitude());
            target.setLatitude(source.getLatitude());
            target.setHeight(source.getHeight());

            target.setIncidentTypeCode(source.getIncidentTypeCode());
            if(!StringUtils.isBlank(source.getIncidentTypeCode())&&dicsMap.get("AJLX")!=null){
                target.setIncidentTypeName(dicsMap.get("AJLX").get(source.getIncidentTypeCode()));
            }



            target.setIncidentTypeSubitemCode(source.getIncidentTypeSubitemCode());
            if(!StringUtils.isBlank(source.getIncidentTypeSubitemCode())&&dicsMap.get("AJLXZX")!=null){
                target.setIncidentTypeSubitemName(dicsMap.get("AJLXZX").get(source.getIncidentTypeSubitemCode()));
            }




            target.setIncidentLevelCode(source.getIncidentLevelCode());
            if(!StringUtils.isBlank(source.getIncidentLevelCode())&&dicsMap.get("AJDJ")!=null){
                target.setIncidentLevelName(dicsMap.get("AJDJ").get(source.getIncidentLevelCode()));
            }


            target.setIncidentStatusCode(source.getIncidentStatusCode());
            if(!StringUtils.isBlank(source.getIncidentStatusCode())&&dicsMap.get("AJZT")!=null){
                target.setIncidentStatusName(dicsMap.get("AJZT").get(source.getIncidentStatusCode()));
            }


            target.setIncidentNatureCode(source.getIncidentNatureCode());
            if(!StringUtils.isBlank(source.getIncidentNatureCode())&&dicsMap.get("AJXZ")!=null){
                target.setIncidentNatureName(dicsMap.get("AJXZ").get(source.getIncidentNatureCode()));
            }


            target.setDisposalObjectCode(source.getDisposalObjectCode());
            if(!StringUtils.isBlank(source.getDisposalObjectCode())&&dicsMap.get("CZDX")!=null){
                target.setDisposalObjectName(dicsMap.get("CZDX").get(source.getDisposalObjectCode()));
            }



            target.setWhetherImportSign(source.getWhetherImportSign());
            target.setKeyUnit(source.getKeyUnit());
            target.setKeyUnitId(source.getKeyUnitId());
            target.setKeyUnitTypeCode(source.getKeyUnitTypeCode());
            if(!StringUtils.isBlank(source.getKeyUnitTypeCode())&&dicsMap.get("JQDX")!=null){
                target.setKeyUnitTypeName(dicsMap.get("JQDX").get(source.getKeyUnitTypeCode()));
            }



            target.setSupplementInfo(source.getSupplementInfo());
            target.setAttentions(source.getAttentions());
            target.setIncidentDescribe(source.getIncidentDescribe());

            target.setMergeStatus(source.getMergeStatus());
            target.setWhetherMainMerge(source.getWhetherMainMerge());

            target.setRegisterOrganizationId(source.getRegisterOrganizationId());
            if(!StringUtils.isBlank(source.getRegisterOrganizationId())){
                target.setRegisterOrganizationName(organizationNameMap.get(source.getRegisterOrganizationId()));
            }


            target.setRegisterSeatNumber(source.getRegisterSeatNumber());
            target.setAcceptancePersonNumber(source.getAcceptancePersonNumber());
            target.setAcceptancePersonName(source.getAcceptancePersonName());

            target.setDispatchOrganization(source.getDispatchOrganization());
            target.setDispatchVehicle(source.getDispatchVehicle());

            target.setHandleType(source.getHandleType());
            target.setIncidentLabel(source.getIncidentLabel());
            if(!StringUtils.isBlank(source.getIncidentLabel())&&dicsMap.get("JQBQ")!=null){
                target.setIncidentLabelName(dicsMap.get("JQBQ").get(source.getIncidentLabel()));
            }




            target.setBuildingStructureCode(source.getBuildingStructureCode());
            if(!StringUtils.isBlank(source.getBuildingStructureCode())&&dicsMap.get("JZJG")!=null){
                target.setBuildingStructureName(dicsMap.get("JZJG").get(source.getBuildingStructureCode()));
            }


            target.setSmogSituationCode(source.getSmogSituationCode());
            if(!StringUtils.isBlank(source.getSmogSituationCode())&&dicsMap.get("YWQK")!=null){
                target.setSmogSituationName(dicsMap.get("YWQK").get(source.getSmogSituationCode()));
            }


            target.setTrappedCount(source.getTrappedCount());
            target.setStoreysOfBuildings(source.getStoreysOfBuildings());
            target.setBurningFloor(source.getBurningFloor());
            target.setBurningArea(source.getBurningArea());
            target.setDisasterSites(source.getDisasterSites());
            if(!StringUtils.isBlank(source.getDisasterSites())&&dicsMap.get("ZHCS")!=null){
                target.setDisasterSitesName(dicsMap.get("ZHCS").get(source.getDisasterSites()));
            }



            target.setSecurityContactPerson(source.getSecurityContactPerson());
            target.setContactPersonPhone(source.getContactPersonPhone());

            target.setWhetherSensitiveArea(source.getWhetherSensitiveArea());
            target.setWhetherWaterShortageArea(source.getWhetherWaterShortageArea());
            target.setWhetherSensitiveTime(source.getWhetherSensitiveTime());


            target.setTransmitTime(source.getTransmitTime());
            target.setReceiveTime(source.getReceiveTime());
            target.setDispatchTime(source.getDispatchTime());
            target.setArriveTime(source.getArriveTime());
            target.setFightTime(source.getFightTime());
            target.setFightStartTime(source.getFightStartTime());
            target.setFireControlTime(source.getFireControlTime());
            target.setExtinguishTime(source.getExtinguishTime());
            target.setFightEdnTime(source.getFightEdnTime());
            target.setReturnTime(source.getReturnTime());
            target.setHalfwayReturnTime(source.getHalfwayReturnTime());

            target.setRemarks(source.getRemarks());

            target.setInjuredCount(source.getInjuredCount());
            target.setDeathCount(source.getDeathCount());
            target.setSecurityStartTime(source.getSecurityStartTime());
            target.setSecurityEndTime(source.getSecurityEndTime());
            target.setWhetherTestSign(source.getWhetherTestSign());

            return target;
        }
        return null;
    }


    /**
     * 案件基本信息转换 更新警情 input 更新 entity
     *
     * @param source 警情信息 更新信息INPUT
     * @param target 警情信息PO
     */
    public static void transform(IncidentSaveInputInfo source, IncidentEntity target) {

        /**  start 警情电话信息 */
        target.setAlarmPersonName(source.getAlarmPersonName());
        target.setAlarmPersonSex(source.getAlarmPersonSex());
        target.setAlarmPhone(source.getAlarmPhone());
        target.setAlarmPersonPhone(source.getAlarmPersonPhone());
        target.setAlarmAddress(source.getAlarmAddress());
        target.setWhetherTrappedSign(source.getWhetherTrappedSign());
        /**  end警情电话信息 */
        target.setBrigadeOrganizationId(source.getBrigadeOrganizationId());
        target.setSquadronOrganizationId(source.getSquadronOrganizationId());
        target.setDistrictCode(source.getDistrictCode());

        target.setTitle(source.getTitle());
        target.setCrimeAddress(source.getCrimeAddress());

        target.setLongitude(source.getLongitude());
        target.setLatitude(source.getLatitude());
        target.setHeight(source.getHeight());
        target.setIncidentTypeCode(source.getIncidentTypeCode());
        target.setIncidentTypeSubitemCode(source.getIncidentTypeSubitemCode());
        target.setIncidentLevelCode(source.getIncidentLevelCode());
        target.setDisposalObjectCode(source.getDisposalObjectCode());
        target.setWhetherImportSign(source.getWhetherImportSign());

        target.setKeyUnit(source.getKeyUnitId());
        target.setKeyUnitId(source.getKeyUnitId());

        target.setSupplementInfo(source.getSupplementInfo());
        target.setAttentions(source.getAttentions());
        target.setIncidentDescribe(source.getIncidentDescribe());

        target.setBuildingStructureCode(source.getBuildingStructureCode());
        target.setSmogSituationCode(source.getSmogSituationCode());
        target.setTrappedCount(source.getTrappedCount());
        target.setStoreysOfBuildings(source.getStoreysOfBuildings());
        target.setBurningFloor(source.getBurningFloor());
        target.setBurningArea(source.getBurningArea());
        target.setDisasterSites(source.getDisasterSites());
        target.setSecurityContactPerson(source.getSecurityContactPerson());
        target.setContactPersonPhone(source.getContactPersonPhone());
        target.setWhetherSensitiveArea(source.getWhetherSensitiveArea());
        target.setWhetherWaterShortageArea(source.getWhetherWaterShortageArea());
        target.setWhetherSensitiveTime(source.getWhetherSensitiveTime());
        target.setRemarks(source.getRemarks());

        target.setInjuredCount(source.getInjuredCount());
        target.setDeathCount(source.getDeathCount());
        target.setSecurityStartTime(source.getSecurityStartTime());
        target.setSecurityEndTime(source.getSecurityEndTime());
        target.setWhetherTestSign(source.getWhetherTestSign());


    }


    /**
     * 案件基本信息转换 更新警情警情 entity 更新 bean
     *
     * @param source 案件基本信息信息PO
     * @return 案件基本信息信息BO
     */
    public static void transform(IncidentBean target, IncidentEntity source,
                                 Map<String, Map<String, String>> dicsMap,
                                 Map<String, String> organizationNameMap

    ) {

        if(organizationNameMap==null){
            organizationNameMap=new HashMap<>();
        }


        target.setId(source.getId());

        /**  start 警情电话信息 */
        target.setAlarmPhone(source.getAlarmPhone());
        target.setAlarmPersonName(source.getAlarmPersonName());
        target.setAlarmPersonSex(source.getAlarmPersonSex());
        if(!StringUtils.isBlank(source.getAlarmPersonSex())&&dicsMap.get("XB")!=null){
            target.setAlarmPersonSexName(dicsMap.get("XB").get(source.getAlarmPersonSex()));
        }


        target.setAlarmPersonPhone(source.getAlarmPersonPhone());
        target.setAlarmAddress(source.getAlarmAddress());
        target.setRelayRecordNumber(source.getRelayRecordNumber());

        target.setAlarmStartTime(source.getAlarmStartTime());
        target.setReceiveStartTime(source.getReceiveStartTime());
        target.setAlarmEndTime(source.getAlarmEndTime());
        target.setReceiveEndTime(source.getReceiveEndTime());
        target.setWhetherTrappedSign(source.getWhetherTrappedSign());
        /**  end警情电话信息 */

        target.setIncidentNumber(source.getIncidentNumber());
        target.setAcceptanceId(source.getAcceptanceId());
        target.setSquadronOrganizationId(source.getSquadronOrganizationId());
        if(!StringUtils.isBlank(source.getSquadronOrganizationId())){
            target.setSquadronOrganizationName(organizationNameMap.get(source.getSquadronOrganizationId()));
        }


        target.setBrigadeOrganizationId(source.getBrigadeOrganizationId());
        if(!StringUtils.isBlank(source.getBrigadeOrganizationId())){
            target.setBrigadeOrganizationName(organizationNameMap.get(source.getBrigadeOrganizationId()));
        }



        target.setDistrictCode(source.getDistrictCode());
        if(!StringUtils.isBlank(source.getDistrictCode())&&dicsMap.get("XZQX")!=null){
            target.setDistrictName(dicsMap.get("XZQX").get(source.getDistrictCode()));
        }


        target.setAlarmModeCode(source.getAlarmModeCode());
        if(!StringUtils.isBlank(source.getAlarmModeCode())&&dicsMap.get("BJFS")!=null){
            target.setAlarmModeName(dicsMap.get("BJFS").get(source.getAlarmModeCode()));
        }



        target.setRegisterModeCode(source.getRegisterModeCode());
        if(!StringUtils.isBlank(source.getRegisterModeCode())&&dicsMap.get("LAFS")!=null){
            target.setRegisterModeName(dicsMap.get("LAFS").get(source.getRegisterModeCode()));
        }



        target.setRegisterIncidentTime(source.getRegisterIncidentTime());

        target.setTitle(source.getTitle());
        target.setCrimeAddress(source.getCrimeAddress());
        target.setLongitude(source.getLongitude());
        target.setLatitude(source.getLatitude());
        target.setHeight(source.getHeight());

        target.setIncidentTypeCode(source.getIncidentTypeCode());
        if(!StringUtils.isBlank(source.getIncidentTypeCode())&&dicsMap.get("AJLX")!=null){
            target.setIncidentTypeName(dicsMap.get("AJLX").get(source.getIncidentTypeCode()));
        }



        target.setIncidentTypeSubitemCode(source.getIncidentTypeSubitemCode());
        if(!StringUtils.isBlank(source.getIncidentTypeSubitemCode())&&dicsMap.get("AJLXZX")!=null){
            target.setIncidentTypeSubitemName(dicsMap.get("AJLXZX").get(source.getIncidentTypeSubitemCode()));
        }



        target.setIncidentLevelCode(source.getIncidentLevelCode());
        if(!StringUtils.isBlank(source.getIncidentLevelCode())&&dicsMap.get("AJDJ")!=null){
            target.setIncidentLevelName(dicsMap.get("AJDJ").get(source.getIncidentLevelCode()));
        }


        target.setIncidentStatusCode(source.getIncidentStatusCode());
        if(!StringUtils.isBlank(source.getIncidentStatusCode())&&dicsMap.get("AJZT")!=null){
            target.setIncidentStatusName(dicsMap.get("AJZT").get(source.getIncidentStatusCode()));
        }


        target.setIncidentNatureCode(source.getIncidentNatureCode());
        if(!StringUtils.isBlank(source.getIncidentNatureCode())&&dicsMap.get("AJXZ")!=null){
            target.setIncidentNatureName(dicsMap.get("AJXZ").get(source.getIncidentNatureCode()));
        }


        target.setDisposalObjectCode(source.getDisposalObjectCode());
        if(!StringUtils.isBlank(source.getDisposalObjectCode())&&dicsMap.get("CZDX")!=null){
            target.setDisposalObjectName(dicsMap.get("CZDX").get(source.getDisposalObjectCode()));
        }



        target.setWhetherImportSign(source.getWhetherImportSign());
        target.setKeyUnit(source.getKeyUnit());
        target.setKeyUnitId(source.getKeyUnitId());
        target.setKeyUnitTypeCode(source.getKeyUnitTypeCode());
        if(!StringUtils.isBlank(source.getKeyUnitTypeCode())&&dicsMap.get("JQDX")!=null){
            target.setKeyUnitTypeName(dicsMap.get("JQDX").get(source.getKeyUnitTypeCode()));
        }




        target.setSupplementInfo(source.getSupplementInfo());
        target.setAttentions(source.getAttentions());
        target.setIncidentDescribe(source.getIncidentDescribe());

        target.setMergeStatus(source.getMergeStatus());
        target.setWhetherMainMerge(source.getWhetherMainMerge());

        target.setRegisterOrganizationId(source.getRegisterOrganizationId());
        if(!StringUtils.isBlank(source.getRegisterOrganizationId())){
            target.setRegisterOrganizationName(organizationNameMap.get(source.getRegisterOrganizationId()));
        }


        target.setRegisterSeatNumber(source.getRegisterSeatNumber());
        target.setAcceptancePersonNumber(source.getAcceptancePersonNumber());
        target.setAcceptancePersonName(source.getAcceptancePersonName());

        target.setDispatchOrganization(source.getDispatchOrganization());
        target.setDispatchVehicle(source.getDispatchVehicle());

        target.setHandleType(source.getHandleType());
        target.setIncidentLabel(source.getIncidentLabel());
        if(!StringUtils.isBlank(source.getIncidentLabel())&&dicsMap.get("JQBQ")!=null){
            target.setIncidentLabelName(dicsMap.get("JQBQ").get(source.getIncidentLabel()));
        }



        target.setBuildingStructureCode(source.getBuildingStructureCode());
        if(!StringUtils.isBlank(source.getBuildingStructureCode())&&dicsMap.get("JZJG")!=null){
            target.setBuildingStructureName(dicsMap.get("JZJG").get(source.getBuildingStructureCode()));
        }


        target.setSmogSituationCode(source.getSmogSituationCode());
        if(!StringUtils.isBlank(source.getSmogSituationCode())&&dicsMap.get("YWQK")!=null){
            target.setSmogSituationName(dicsMap.get("YWQK").get(source.getSmogSituationCode()));
        }


        target.setTrappedCount(source.getTrappedCount());
        target.setStoreysOfBuildings(source.getStoreysOfBuildings());
        target.setBurningFloor(source.getBurningFloor());
        target.setBurningArea(source.getBurningArea());
        target.setDisasterSites(source.getDisasterSites());
        if(!StringUtils.isBlank(target.getDisasterSites())&&dicsMap.get("ZHCS")!=null){
            target.setDisasterSitesName(dicsMap.get("ZHCS").get(target.getDisasterSites()));
        }



        target.setSecurityContactPerson(source.getSecurityContactPerson());
        target.setContactPersonPhone(source.getContactPersonPhone());

        target.setWhetherSensitiveArea(source.getWhetherSensitiveArea());
        target.setWhetherWaterShortageArea(source.getWhetherWaterShortageArea());
        target.setWhetherSensitiveTime(source.getWhetherSensitiveTime());


        target.setTransmitTime(source.getTransmitTime());
        target.setReceiveTime(source.getReceiveTime());
        target.setDispatchTime(source.getDispatchTime());
        target.setArriveTime(source.getArriveTime());
        target.setFightTime(source.getFightTime());
        target.setFightStartTime(source.getFightStartTime());
        target.setFireControlTime(source.getFireControlTime());
        target.setExtinguishTime(source.getExtinguishTime());
        target.setFightEdnTime(source.getFightEdnTime());
        target.setReturnTime(source.getReturnTime());
        target.setHalfwayReturnTime(source.getHalfwayReturnTime());

        target.setRemarks(source.getRemarks());

        target.setInjuredCount(source.getInjuredCount());
        target.setDeathCount(source.getDeathCount());
        target.setSecurityStartTime(source.getSecurityStartTime());
        target.setSecurityEndTime(source.getSecurityEndTime());
        target.setWhetherTestSign(source.getWhetherTestSign());

    }


    /**
     * 案件基本信息转换 警情列表 entity  转 brieflyBean
     *
     * @param source 案件基本信息信息PO
     * @return 案件基本信息信息BO
     */
    public static IncidentBrieflyBean transformBriefly(Object[] source, Map<String, Map<String, String>> dicsMap, Map<String, String> organizationNameMap) {
        if(organizationNameMap==null){
            organizationNameMap=new HashMap<>();
        }

        if (null != source) {
            IncidentBrieflyBean target = new IncidentBrieflyBean();

            target.setId(toString(source[0]));
            target.setIncidentNumber(toString(source[1]));
            target.setSquadronOrganizationId(toString(source[2]));

            if(!StringUtils.isBlank(toString(source[2]))){
                target.setSquadronOrganizationName(organizationNameMap.get(toString(source[2])));
            }


            target.setBrigadeOrganizationId(toString(source[3]));
            if(!StringUtils.isBlank(toString(source[3]))){
                target.setBrigadeOrganizationName(organizationNameMap.get(toString(source[3])));
            }


            target.setDistrictCode(toString(source[4]));

            if(!StringUtils.isBlank(toString(source[4]))&&dicsMap.get("XZQX")!=null){
                target.setDistrictName(dicsMap.get("XZQX").get(toString(source[4])));
            }




            target.setRegisterModeCode(toString(source[5]));
            if(!StringUtils.isBlank(toString(source[5]))&&dicsMap.get("LAFS")!=null){
                target.setRegisterModeName(dicsMap.get("LAFS").get(toString(source[5])));
            }


            target.setRegisterIncidentTime(toLong(source[6]));
            target.setCrimeAddress(toString(source[7]));
            target.setLongitude(toString(source[8]));
            target.setLatitude(toString(source[9]));


            target.setHeight(toString(source[10]));
            target.setIncidentTypeCode(toString(source[11]));
            if(!StringUtils.isBlank(toString(source[11]))&&dicsMap.get("AJLX")!=null){
                target.setIncidentTypeName(dicsMap.get("AJLX").get(toString(source[11])));
            }


            target.setIncidentTypeSubitemCode(toString(source[12]));
            if(!StringUtils.isBlank(toString(source[12]))&&dicsMap.get("AJLXZX")!=null){
                target.setIncidentTypeSubitemName(dicsMap.get("AJLXZX").get(toString(source[12])));
            }


            target.setIncidentLevelCode(toString(source[13]));
            if(!StringUtils.isBlank(toString(source[13]))&&dicsMap.get("AJDJ")!=null){
                target.setIncidentLevelName(dicsMap.get("AJDJ").get(toString(source[13])));
            }


            target.setDisposalObjectCode(toString(source[14]));
            if(!StringUtils.isBlank(toString(source[14]))&&dicsMap.get("CZDX")!=null){
                target.setDisposalObjectName(dicsMap.get("CZDX").get(toString(source[14])));
            }




            target.setIncidentStatusCode(toString(source[15]));
            if(!StringUtils.isBlank(toString(source[15]))&&dicsMap.get("AJZT")!=null){
                target.setIncidentStatusName(dicsMap.get("AJZT").get(toString(source[15])));
            }



            target.setIncidentNatureCode(toString(source[16]));
            if(!StringUtils.isBlank(toString(source[16]))&&dicsMap.get("AJXZ")!=null){
                target.setIncidentNatureName(dicsMap.get("AJXZ").get(toString(source[16])));
            }


            target.setWhetherImportSign(toInteger(source[17]));
            target.setMergeStatus(toInteger(source[18]));
            target.setWhetherMainMerge(toInteger(source[19]));


            target.setRegisterSeatNumber(toString(source[20]));
            target.setAcceptancePersonNumber(toString(source[21]));
            target.setAcceptancePersonName(toString(source[22]));
            target.setDispatchOrganization(toString(source[23]));
            target.setDispatchVehicle(toString(source[24]));


            target.setIncidentGroupId(toString(source[25]));
            String attention = toString(source[26]);
            if ("0".equals(attention)) {
                target.setWhetherFocusOn(0); //不关注
            } else {
                target.setWhetherFocusOn(1); //关注
            }
            String attentionImportant = toString(source[27]);
            if ("0".equals(attentionImportant)) {
                target.setWhetherImportant(0);
                ; //非重要警情标识
            } else {
                target.setWhetherImportant(1); //重要警情标识
            }
            target.setAttachmentNum(Integer.parseInt(toLong(source[28]) + ""));
            target.setAlarmPhone(toString(source[29]));


            target.setRelayRecordNumber(toString(source[30]));
            target.setAlarmModeCode(toString(source[31]));
            if(!StringUtils.isBlank(toString(source[31]))&&dicsMap.get("BJFS")!=null){
                target.setAlarmModeName(dicsMap.get("BJFS").get(toString(source[31])));
            }



            target.setAlarmPersonPhone(toString(source[32]));
            target.setAlarmPersonName( toString(source[33]) );

            return target;
        }
        return null;
    }


    /**
     * 案件基本信息转换 警情详情 entity  转 bean
     *
     * @param source 案件基本信息信息PO
     * @return 案件基本信息信息BO
     */
    public static IncidentBean transform(Object[] source,
                                         Map<String, Map<String, String>> dicsMap,
                                         Map<String, String> organizationNameMap ,
                                         String handlePersonNumType

    ) {
        if(organizationNameMap==null){
            organizationNameMap=new HashMap<>();
        }

        if (null != source) {
            IncidentBean target = new IncidentBean();

            IncidentEntity incidentEntity = (IncidentEntity) source[0];
            target.setId(incidentEntity.getId());
            target.setIdCode(incidentEntity.getIdCode());
            target.setIncidentSource(incidentEntity.getIncidentSource());
            /**  start 警情电话信息 */
            target.setAlarmPhone(incidentEntity.getAlarmPhone());
            target.setAlarmPersonName(incidentEntity.getAlarmPersonName());
            target.setAlarmPersonSex(incidentEntity.getAlarmPersonSex());
            if(!StringUtils.isBlank(incidentEntity.getAlarmPersonSex())&&dicsMap.get("XB")!=null){
                target.setAlarmPersonSexName(dicsMap.get("XB").get(incidentEntity.getAlarmPersonSex()));
            }


            target.setAlarmPersonPhone(incidentEntity.getAlarmPersonPhone());
            target.setAlarmAddress(incidentEntity.getAlarmAddress());
            target.setRelayRecordNumber(incidentEntity.getRelayRecordNumber());

            target.setAlarmStartTime(incidentEntity.getAlarmStartTime());
            target.setReceiveStartTime(incidentEntity.getReceiveStartTime());
            target.setAlarmEndTime(incidentEntity.getAlarmEndTime());
            target.setReceiveEndTime(incidentEntity.getReceiveEndTime());
            target.setWhetherTrappedSign(incidentEntity.getWhetherTrappedSign());
            /**  end警情电话信息 */

            target.setIncidentNumber(incidentEntity.getIncidentNumber());
            target.setAcceptanceId(incidentEntity.getAcceptanceId());
            target.setSquadronOrganizationId(incidentEntity.getSquadronOrganizationId());
            if(!StringUtils.isBlank(incidentEntity.getSquadronOrganizationId())){
                target.setSquadronOrganizationName(organizationNameMap.get(incidentEntity.getSquadronOrganizationId()));
            }


            target.setBrigadeOrganizationId(incidentEntity.getBrigadeOrganizationId());
            if(!StringUtils.isBlank(incidentEntity.getBrigadeOrganizationId())){
                target.setBrigadeOrganizationName(organizationNameMap.get(incidentEntity.getBrigadeOrganizationId()));
            }


            target.setDistrictCode(incidentEntity.getDistrictCode());
            if(!StringUtils.isBlank(incidentEntity.getDistrictCode())&&dicsMap.get("XZQX")!=null){
                target.setDistrictName(dicsMap.get("XZQX").get(incidentEntity.getDistrictCode()));
            }


            target.setAlarmModeCode(incidentEntity.getAlarmModeCode());
            if(!StringUtils.isBlank(incidentEntity.getAlarmModeCode())&&dicsMap.get("BJFS")!=null){
                target.setAlarmModeName(dicsMap.get("BJFS").get(incidentEntity.getAlarmModeCode()));
            }


            target.setRegisterIncidentTime(incidentEntity.getRegisterIncidentTime());
            target.setTitle(incidentEntity.getTitle());
            target.setCrimeAddress(incidentEntity.getCrimeAddress());
            target.setLongitude(incidentEntity.getLongitude());
            target.setLatitude(incidentEntity.getLatitude());
            target.setHeight(incidentEntity.getHeight());

            target.setIncidentTypeCode(incidentEntity.getIncidentTypeCode());
            if(!StringUtils.isBlank(incidentEntity.getIncidentTypeCode())&&dicsMap.get("AJLX")!=null){
                target.setIncidentTypeName(dicsMap.get("AJLX").get(incidentEntity.getIncidentTypeCode()));
            }



            target.setIncidentTypeSubitemCode(incidentEntity.getIncidentTypeSubitemCode());
            if(!StringUtils.isBlank(incidentEntity.getIncidentTypeSubitemCode())&&dicsMap.get("AJLXZX")!=null){
                target.setIncidentTypeSubitemName(dicsMap.get("AJLXZX").get(incidentEntity.getIncidentTypeSubitemCode()));
            }




            target.setIncidentLevelCode(incidentEntity.getIncidentLevelCode());
            if(!StringUtils.isBlank(incidentEntity.getIncidentLevelCode())&&dicsMap.get("AJDJ")!=null){
                target.setIncidentLevelName(dicsMap.get("AJDJ").get(incidentEntity.getIncidentLevelCode()));
            }


            target.setDisposalObjectCode(incidentEntity.getDisposalObjectCode());
            if(!StringUtils.isBlank(incidentEntity.getDisposalObjectCode())&&dicsMap.get("CZDX")!=null){
                target.setDisposalObjectName(dicsMap.get("CZDX").get(incidentEntity.getDisposalObjectCode()));
            }


            target.setIncidentStatusCode(incidentEntity.getIncidentStatusCode());
            if(!StringUtils.isBlank(incidentEntity.getIncidentStatusCode())&&dicsMap.get("AJZT")!=null){
                target.setIncidentStatusName(dicsMap.get("AJZT").get(incidentEntity.getIncidentStatusCode()));
            }


            target.setIncidentNatureCode(incidentEntity.getIncidentNatureCode());
            if(!StringUtils.isBlank(incidentEntity.getIncidentNatureCode())&&dicsMap.get("AJXZ")!=null){
                target.setIncidentNatureName(dicsMap.get("AJXZ").get(incidentEntity.getIncidentNatureCode()));
            }


            target.setWhetherImportSign(incidentEntity.getWhetherImportSign());

            target.setKeyUnit(incidentEntity.getKeyUnit());
            target.setKeyUnitId(incidentEntity.getKeyUnitId());
            target.setKeyUnitTypeCode(incidentEntity.getKeyUnitTypeCode());
            if(!StringUtils.isBlank(incidentEntity.getKeyUnitTypeCode())&&dicsMap.get("JQDX")!=null){
                target.setKeyUnitTypeName(dicsMap.get("JQDX").get(incidentEntity.getKeyUnitTypeCode()));
            }

            target.setSupplementInfo(incidentEntity.getSupplementInfo());
            target.setAttentions(incidentEntity.getAttentions());
            target.setIncidentDescribe(incidentEntity.getIncidentDescribe());
            target.setMergeStatus(incidentEntity.getMergeStatus());
            target.setWhetherMainMerge(incidentEntity.getWhetherMainMerge());

            target.setRegisterOrganizationId(incidentEntity.getRegisterOrganizationId());
            if(!StringUtils.isBlank(incidentEntity.getRegisterOrganizationId())){
                target.setRegisterOrganizationName(organizationNameMap.get(incidentEntity.getRegisterOrganizationId()));
            }


            target.setRegisterSeatNumber(incidentEntity.getRegisterSeatNumber());
            target.setAcceptancePersonNumber(incidentEntity.getAcceptancePersonNumber());
            target.setAcceptancePersonName(incidentEntity.getAcceptancePersonName());
            target.setHandleType(incidentEntity.getHandleType());
            if(!StringUtils.isBlank(incidentEntity.getHandleType())&&dicsMap.get("CLLX")!=null){
                target.setHandleTypeName(dicsMap.get("CLLX").get(incidentEntity.getHandleType()));
            }


            target.setDispatchOrganization(incidentEntity.getDispatchOrganization());
            target.setDispatchVehicle(incidentEntity.getDispatchVehicle());
            target.setIncidentLabel(incidentEntity.getIncidentLabel());
            if(!StringUtils.isBlank(incidentEntity.getIncidentLabel())&&dicsMap.get("JQBQ")!=null){
                target.setIncidentLabelName(dicsMap.get("JQBQ").get(incidentEntity.getIncidentLabel()));
            }



            target.setBuildingStructureCode(incidentEntity.getBuildingStructureCode());
            if(!StringUtils.isBlank(incidentEntity.getBuildingStructureCode())&&dicsMap.get("JZJG")!=null){
                target.setBuildingStructureName(dicsMap.get("JZJG").get(incidentEntity.getBuildingStructureCode()));
            }


            target.setSmogSituationCode(incidentEntity.getSmogSituationCode());
            if(!StringUtils.isBlank(incidentEntity.getSmogSituationCode())&&dicsMap.get("YWQK")!=null){
                target.setSmogSituationName(dicsMap.get("YWQK").get(incidentEntity.getSmogSituationCode()));
            }


            target.setTrappedCount(incidentEntity.getTrappedCount());
            target.setStoreysOfBuildings(incidentEntity.getStoreysOfBuildings());
            target.setBurningFloor(incidentEntity.getBurningFloor());
            target.setBurningArea(incidentEntity.getBurningArea());
            target.setDisasterSites(incidentEntity.getDisasterSites());
            if(!StringUtils.isBlank(incidentEntity.getDisasterSites())&&dicsMap.get("ZHCS")!=null){
                target.setDisasterSitesName(dicsMap.get("ZHCS").get(incidentEntity.getDisasterSites()));
            }

            target.setSecurityContactPerson(incidentEntity.getSecurityContactPerson());
            target.setContactPersonPhone(incidentEntity.getContactPersonPhone());
            target.setWhetherSensitiveArea(incidentEntity.getWhetherSensitiveArea());
            target.setWhetherWaterShortageArea(incidentEntity.getWhetherWaterShortageArea());
            target.setWhetherSensitiveTime(incidentEntity.getWhetherSensitiveTime());

            target.setTransmitTime(incidentEntity.getTransmitTime());
            target.setReceiveTime(incidentEntity.getReceiveTime());
            target.setDispatchTime(incidentEntity.getDispatchTime());
            target.setArriveTime(incidentEntity.getArriveTime());
            target.setFightTime(incidentEntity.getFightTime());
            target.setFightStartTime(incidentEntity.getFightStartTime());
            target.setFireControlTime(incidentEntity.getFireControlTime());
            target.setExtinguishTime(incidentEntity.getExtinguishTime());
            target.setFightEdnTime(incidentEntity.getFightEdnTime());
            target.setReturnTime(incidentEntity.getReturnTime());
            target.setHalfwayReturnTime(incidentEntity.getHalfwayReturnTime());


            target.setRemarks(incidentEntity.getRemarks());
            target.setInjuredCount(incidentEntity.getInjuredCount());
            target.setDeathCount(incidentEntity.getDeathCount());
            target.setSecurityStartTime(incidentEntity.getSecurityStartTime());
            target.setSecurityEndTime(incidentEntity.getSecurityEndTime());
            target.setWhetherTestSign(incidentEntity.getWhetherTestSign());
            target.setRegisterModeCode(incidentEntity.getRegisterModeCode());
            if(!StringUtils.isBlank(incidentEntity.getRegisterModeCode())&&dicsMap.get("LAFS")!=null){
                target.setRegisterModeName(dicsMap.get("LAFS").get(incidentEntity.getRegisterModeCode()));
            }


            target.setPlaceFileIncidentTime(incidentEntity.getPlaceFileIncidentTime());
            target.setIncidentEndTime(incidentEntity.getIncidentEndTime());
            target.setIncidentGroupId(incidentEntity.getIncidentGroupId());
            target.setUniformAddressId(incidentEntity.getUniformAddressId());//统一地址库id


            String attention = toString(source[1]);
            if ("0".equals(attention)) {
                target.setWhetherFocusOn(0); //不关注
            } else {
                target.setWhetherFocusOn(1); //关注
            }
            String attentionImportant = toString(source[2]);
            if ("0".equals(attentionImportant)) {
                target.setWhetherImportant(0);
                ; //非重要警情标识
            } else {
                target.setWhetherImportant(1); //重要警情标识
            }
            target.setHandleBatchNum(toString(source[3]));
            target.setHandleOrganizationNum(toString(source[4]));
            target.setHandleVehicleNum(toString(source[5]));


            target.setOriginalIncidentNumber(toString(source[7]));

            String handlePersonNum =  toString(source[6]) ;
            String vehicleCommandPersonNum = toString(source[8])  ;
            String vehiclePersonNum = toString(source[9]) ;
            String vehiclePassengersNum = toString(source[10]) ;
            String vehiclePersonCountNum=toString(source[11]);

            //1参战人员 2杭州 案件车辆人员 3车辆人员 4车辆载人数 5车辆人员信息表记录条数  其他默认选择4
            if( "1".equals( handlePersonNumType )  ){
                target.setHandlePersonNum( handlePersonNum );
            }else if( "2".equals( handlePersonNumType ) ){
                target.setHandlePersonNum( vehicleCommandPersonNum );
            }else if( "3".equals( handlePersonNumType ) ){
                target.setHandlePersonNum( vehiclePersonNum );
            }else if( "4".equals( handlePersonNumType ) ){
                target.setHandlePersonNum( vehiclePassengersNum );
            }else if ("5".equals(handlePersonNumType)){
                target.setHandlePersonNum(vehiclePersonCountNum);
            }else  {
                target.setHandlePersonNum( vehiclePassengersNum );
            }

            return target;
        }
        return null;
    }


    /**
     * 警情状态变更记录 转换
     *
     * @param source 警情状态变更PO
     * @return 警情状态变更信息BO
     */
    public static IncidentStatusChangeBean transform(IncidentStatusChangeEntity source, Map<String, Map<String, String>> dicsMap) {
        if (null != source) {
            IncidentStatusChangeBean target = new IncidentStatusChangeBean();
            target.setId(source.getId());
            target.setIncidentId(source.getIncidentId());
            target.setIncidentStatusCode(source.getIncidentStatusCode());
            if(!StringUtils.isBlank(source.getIncidentStatusCode())&&dicsMap.get("AJZT")!=null){
                target.setIncidentStatusName(dicsMap.get("AJZT").get(source.getIncidentStatusCode()));
            }


            target.setChangeTime(source.getChangeTime());
            target.setRemarks(source.getRemarks());
            return target;
        }
        return null;

    }


    /**
     * 案件合并记录 转换
     *
     * @param source 案件合并参数INPUT
     * @return 案件合并记录PO
     */
    public static IncidentMergeEntity transform(IncidentMergeSaveInputInfo source, Long currentSystemTime) {
        if (null != source) {
            IncidentMergeEntity target = new IncidentMergeEntity();
            target.setMainIncidentId(source.getMainIncidentId());
            target.setMergeIncidentId(source.getMergeIncidentId());
            target.setMergeTime(currentSystemTime);
            target.setMergeSeatNumber(source.getMergeSeatNumber());
            target.setAcceptancePersonNumber(source.getAcceptancePersonNumber());
            target.setRemarks(source.getRemarks());
            return target;
        }
        return null;

    }


    /**
     * 黑名单转换
     *
     * @param source 黑名单INPUT
     * @return 黑名单PO
     */
    public static BlacklistEntity transform(BlacklistSaveInputInfo source) {
        if (null != source) {
            BlacklistEntity target = new BlacklistEntity();
            target.setPhoneNumber(source.getPhoneNumber());
            target.setAlarmNumber(source.getAlarmNumber());
            return target;
        }
        return null;

    }

    /**
     * 黑名单转换
     *
     * @param source 黑名单PO
     * @return 黑名单BO
     */
    public static BlacklistBean transform(BlacklistEntity source) {
        if (null != source) {
            BlacklistBean target = new BlacklistBean();
            target.setAlarmNumber(source.getAlarmNumber());
            target.setPhoneNumber(source.getPhoneNumber());
            target.setStartTime(source.getStartTime());
            target.setEndTime(source.getEndTime());
            target.setPersonId(source.getPersonId());
            target.setPersonName(source.getPersonName());
            return target;
        }
        return null;
    }


    /**
     * 警情关注转换
     *
     * @param source 警情关注PO
     * @return 警情关注BO
     */
    public static AttentionBean transform(AttentionEntity source) {
        if (null != source) {
            AttentionBean target = new AttentionBean();
            target.setId(source.getId());
            target.setType(source.getType());
            target.setIncidentId(source.getIncidentId());
            target.setAttentionPersonId(source.getAttentionPersonId());
            target.setAttentionType(source.getAttentionType());
            target.setAttentionWay(source.getAttentionWay());
            target.setAttentionTime(source.getAttentionTime());
            return target;
        }
        return null;
    }


    /**
     * 重要警情规则 转换
     *
     * @param source 重要警情规则PO
     * @return 重要警情规则BO
     */
    public static IncidentImportantRuleEntity transform(IncidentImportantRuleSaveInputInfo source) {
        if (source != null) {
            IncidentImportantRuleEntity target = new IncidentImportantRuleEntity();
            target.setId(source.getId());
            target.setWhetherEnable(EnableEnum.ENABLE_FALSE.getCode());
            target.setIncidentTypeCode(source.getIncidentTypeCode());
            target.setIncidentLevelCode(source.getIncidentLevelCode());
            target.setTrappedEnable(source.getTrappedEnable());
            target.setTrappedNumber(source.getTrappedNumber());
            target.setInjuredEnable(source.getInjuredEnable());
            target.setInjuredNumber(source.getInjuredNumber());
            target.setDeathsEnable(source.getDeathsEnable());
            target.setDeathsNumber(source.getDeathsNumber());
            target.setCombatVehicleNum(source.getCombatVehicleNum());
            target.setKeyword(source.getKeyword());
            target.setOvertime(source.getOvertime());
            return target;
        }
        return null;
    }

    /**
     * 重要警情规则 转换
     *
     * @param source 重要警情规则PO
     * @return 重要警情规则BO
     */
    public static IncidentImportantRuleBean transform(IncidentImportantRuleEntity source) {
        if (source != null) {
            IncidentImportantRuleBean target = new IncidentImportantRuleBean();
            target.setId(source.getId());
            target.setWhetherEnable(source.getWhetherEnable());
            target.setIncidentTypeCode(source.getIncidentTypeCode());
            target.setIncidentLevelCode(source.getIncidentLevelCode());
            target.setTrappedEnable(source.getTrappedEnable());
            target.setTrappedNumber(source.getTrappedNumber());
            target.setInjuredEnable(source.getInjuredEnable());
            target.setInjuredNumber(source.getInjuredNumber());
            target.setDeathsEnable(source.getDeathsEnable());
            target.setDeathsNumber(source.getDeathsNumber());
            target.setCombatVehicleNum(source.getCombatVehicleNum());
            target.setKeyword(source.getKeyword());
            target.setOvertime(source.getOvertime());
            return target;
        }
        return null;

    }


    /**
     * 电话报警记录转换
     *
     * @param source 电话报警记录PO
     * @return 电话报警记录BO
     */
    public static VoiceTranscribeEntity transform(VoiceTranscribeSaveInputInfo source) {
        if (null != source) {
            VoiceTranscribeEntity target = new VoiceTranscribeEntity();
            target.setId(source.getId());
            target.setTelephoneId(source.getTelephoneId());
            target.setIncidentId(source.getIncidentId());
            target.setTranscribeContent(source.getTranscribeContent());
            target.setKeyword(source.getKeyword());
            target.setAbstractContent(source.getAbstractContent());
            target.setAttachment(source.getAttachment());
            target.setAttachmentName(source.getAttachmentName());
            target.setAttachmentType(source.getAttachmentType());
            target.setAttachmentUrl(source.getAttachmentUrl());
            target.setRelayRecordNumber(source.getRelayRecordNumber());
            target.setRemarks(source.getRemarks());
            return target;
        }
        return null;
    }


    /**
     * 电话报警记录转换
     *
     * @param source 电话报警记录PO
     * @return 电话报警记录BO
     */
    public static VoiceTranscribeBean transform(VoiceTranscribeEntity source, Map<String, Map<String, String>> dicsMap) {
        if (null != source) {
            VoiceTranscribeBean target = new VoiceTranscribeBean();
            target.setId(source.getId());
            target.setTelephoneId(source.getTelephoneId());
            target.setIncidentId(source.getIncidentId());
            target.setTranscribeContent(source.getTranscribeContent());
            target.setKeyword(source.getKeyword());
            target.setAbstractContent(source.getAbstractContent());
            target.setAttachment(source.getAttachment());
            target.setAttachmentName(source.getAttachmentName());
            target.setAttachmentType(source.getAttachmentType());
            if(!StringUtils.isBlank(source.getAttachmentType())&&dicsMap.get("FJLX")!=null){
                target.setAttachmentTypeName(dicsMap.get("FJLX").get(source.getAttachmentType()));
            }


            target.setAttachmentUrl(source.getAttachmentUrl());
            target.setRelayRecordNumber(source.getRelayRecordNumber());
            target.setRemarks(source.getRemarks());
            target.setSeatNumber(source.getSeatNumber());
            target.setAcceptancePerson(source.getAcceptancePerson());
            target.setAcceptancePersonName(source.getAcceptancePersonName());
            target.setAcceptancePersonNumber(source.getAcceptancePersonNumber());

            return target;
        }
        return null;
    }


    /**
     * 转换为string类
     *
     * @param obj 参数
     * @return 返回字符串
     */
    public static String toString(Object obj) {
        return (obj == null) ? null : obj.toString();
    }


    /**
     * 转换为BigInteger类
     *
     * @param obj 参数
     * @return 返回字符串
     */
    public static Integer toInteger(Object obj) {
        if (obj == null) {
            return null;
        } else {
            Integer sl = (Integer) obj;
            return sl.intValue();
        }
    }

    /**
     * 转换为string类
     *
     * @param obj 参数
     * @return 返回字符串
     */
    public static Long toLong(Object obj) {
        if (obj != null) {
            Long count = 0l;
            try {
                count = (Long) obj;
            } catch (ClassCastException e) {
                BigInteger bigInteger = (BigInteger) obj;
                count = bigInteger.longValue();
            }
            return count.longValue();
        }
        return 0l;
    }

    public static Integer toInteger2(Object obj) {
        if (obj == null) {
            return 0;
        } else {
            return Integer.valueOf(String.valueOf(obj));
        }
    }

    public static  StandardEntity  transform(UpdateStandardInfo info){
        StandardEntity entity = new StandardEntity();
        entity.setIncidentBigType(info.getIncidentBigType());
        entity.setIncidentLevel(info.getIncidentLevel());
        entity.setIncidentType(info.getIncidentType());
        entity.setPlaceType(info.getPlaceType());
        entity.setStandardType(info.getStandardType());
        entity.setTipsContent(info.getTipsContent());
        entity.setTipsKeyword(info.getTipsKeyword());
        entity.setTipsType(info.getTipsType());
        entity.setId(info.getId());
        entity.setValid(info.getValid());
        return entity;

    }

    public static  UpdateStandardBean  transform(StandardEntity info){
        UpdateStandardBean bean = new UpdateStandardBean();
        bean.setIncidentBigType(info.getIncidentBigType());
        bean.setIncidentLevel(info.getIncidentLevel());
        bean.setIncidentType(info.getIncidentType());
        bean.setPlaceType(info.getPlaceType());
        bean.setStandardType(info.getStandardType());
        bean.setTipsContent(info.getTipsContent());
        bean.setTipsKeyword(info.getTipsKeyword());
        bean.setTipsType(info.getTipsType());
        bean.setId(info.getId());
        bean.setValid(info.getValid());

        return bean;
    }

}
