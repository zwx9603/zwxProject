package com.dscomm.iecs.accept.utils.transform;


import com.alibaba.fastjson.JSONArray;
import com.dscomm.iecs.accept.dal.po.*;
import com.dscomm.iecs.accept.graphql.inputbean.*;
import com.dscomm.iecs.accept.graphql.typebean.*;
import com.dscomm.iecs.base.enums.BasicEnumNumberUtils;
import com.dscomm.iecs.base.enums.EnableEnum;
import com.dscomm.iecs.base.enums.EnumBean;
import com.dscomm.iecs.base.utils.BasicEnumUtils;
import com.dscomm.iecs.basedata.graphql.typebean.AttachmentBean;
import com.dscomm.iecs.basedata.graphql.typebean.EquipmentVehicleBean;
import com.dscomm.iecs.basedata.graphql.typebean.KeyUnitSimpleBean;
import com.dscomm.iecs.basedata.graphql.typebean.OrganizationBean;
import com.dscomm.iecs.basedata.service.enums.LocationTypeEnum;
import com.dscomm.iecs.basedata.service.enums.ParticipantPersonStateEnum;
import com.dscomm.iecs.ext.WarnTypeBakEnum;
import com.dscomm.iecs.ext.comm.document.DOCUMENT_TYPE_JQZT;
import com.dscomm.iecs.ext.instructions.STATUS_GIVEN;
import com.dscomm.iecs.ext.vehicle.status.VEHICLE_STATUS_TZ;
import io.jsonwebtoken.lang.Collections;
import org.apache.logging.log4j.util.Strings;
import org.mx.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 描述 : 处警调度、预警信息、结案反馈、调派 转换工具
 */
public class HandleDispatchTransformUtil {


    /**
     * 集合演练方案 转换
     *
     * @param source 集合演练方案PO
     * @return 集合演练方案BO
     */
    public static DrillPlanBean transform(DrillPlanEntity source, Map<String, Map<String, String>> dicsMap, Map<String, String> organizationNameMap) {
        if(organizationNameMap==null){
            organizationNameMap=new HashMap<>();
        }

        if (null != source) {
            DrillPlanBean target = new DrillPlanBean();
            target.setId(source.getId());
            target.setIncidentTypeCode(source.getIncidentTypeCode());
            if(!StringUtils.isBlank(source.getIncidentTypeCode())&&dicsMap.get("AJLX")!=null){
                target.setIncidentTypeName(dicsMap.get("AJLX").get(source.getIncidentTypeCode()));
            }


            target.setDrillPlanName(source.getDrillPlanName());
            target.setDrillPlanContent(source.getDrillPlanContent());
            target.setMakeOrganizationId(source.getMakeOrganizationId());
            if(!StringUtils.isBlank(source.getMakeOrganizationId())){
                target.setMakeOrganizationName(organizationNameMap.get(source.getMakeOrganizationId()));
            }


            target.setMakePersonId(source.getMakePersonId());
            target.setMakePersonName(source.getMakePersonName());
            target.setWhetherEnable(source.getWhetherEnable());
            target.setEnableTime(source.getEnableTime());
            target.setInvalidTime(source.getInvalidTime());
            target.setMakeTime(source.getMakeTime());
            target.setRemarks(source.getRemarks());
            return target;
        }
        return null;
    }

    /**
     * 集合演练方案 转换
     *
     * @param source 集合演练方案保存参数 INPUT
     * @return 集合演练方案PO
     */
    public static DrillPlanEntity transform(DrillPlanSaveInputInfo source) {
        if (null != source) {
            DrillPlanEntity target = new DrillPlanEntity();
            target.setId(source.getId());
            target.setIncidentTypeCode(source.getIncidentTypeCode());
            target.setDrillPlanName(source.getDrillPlanName());
            target.setDrillPlanContent(source.getDrillPlanContent());
            target.setMakeOrganizationId(source.getMakeOrganizationId());
            target.setMakePersonId(source.getMakePersonId());
            target.setMakePersonName(source.getMakePersonName());
            target.setWhetherEnable(source.getWhetherEnable());
            target.setEnableTime(source.getEnableTime());
            target.setInvalidTime(source.getInvalidTime());
            target.setMakeTime(source.getMakeTime());
            target.setRemarks(source.getRemarks());
            return target;
        }
        return null;
    }

    /**
     * 集合演练方案调派 转换
     *
     * @param source 集合演练方案调派 保存参数INPUT
     * @return 集合演练方案调派 PO
     */
    public static List<DrillPlanDispatchEntity> transform(DrillPlanSaveInputInfo source, String drillPlanId) {
        if (null != source) {
            List<DrillPlanDispatchSaveInputInfo> drillPlanDispatchSaveInputInfos = source.getDrillPlanDispatchSaveInputInfos();
            List<DrillPlanDispatchEntity> target = new ArrayList<>();
            if (drillPlanDispatchSaveInputInfos != null && drillPlanDispatchSaveInputInfos.size() > 0) {
                for (DrillPlanDispatchSaveInputInfo inputInfo : drillPlanDispatchSaveInputInfos) {
                    DrillPlanDispatchEntity dispatchEntity = new DrillPlanDispatchEntity();
                    dispatchEntity.setId(inputInfo.getId());
                    dispatchEntity.setDrillPlanId(drillPlanId);
                    dispatchEntity.setOrganizationId(inputInfo.getOrganizationId());
                    dispatchEntity.setVehicleId(inputInfo.getVehicleId());
                    dispatchEntity.setVehicleName(inputInfo.getVehicleName());
                    dispatchEntity.setVehicleNumber(inputInfo.getVehicleNumber());
                    dispatchEntity.setVehicleTypeCode(inputInfo.getVehicleTypeCode());
                    dispatchEntity.setOrderNum(inputInfo.getOrderNum());
                    dispatchEntity.setWhetherValid(inputInfo.getWhetherValid());
                    dispatchEntity.setRemarks(inputInfo.getRemarks());
                    target.add(dispatchEntity);
                }
            }
            return target;
        }
        return null;
    }


    /**
     * 集合演练方案调派 转换
     *
     * @param source 集合演练方案调派PO
     * @return 集合演练方案调派BO
     */
    public static DrillPlanDispatchBean transform(DrillPlanDispatchEntity source, Map<String, Map<String, String>> dicsMap, Map<String, String> organizationNameMap) {
        if(organizationNameMap==null){
            organizationNameMap=new HashMap<>();
        }

        if (null != source) {
            DrillPlanDispatchBean target = new DrillPlanDispatchBean();
            target.setId(source.getId());
            target.setDrillPlanId(source.getDrillPlanId());
            target.setOrganizationId(source.getOrganizationId());
            target.setVehicleId(source.getVehicleId());
            target.setVehicleName(source.getVehicleName());
            target.setVehicleNumber(source.getVehicleNumber());
            if(!StringUtils.isBlank(source.getOrganizationId())){
                target.setOrganizationName(organizationNameMap.get(source.getOrganizationId()));
            }

            target.setVehicleTypeCode(source.getVehicleTypeCode());
            if(!StringUtils.isBlank(source.getVehicleTypeCode())&&dicsMap.get("WLCLLX")!=null){
                target.setVehicleTypeName(dicsMap.get("WLCLLX").get(source.getVehicleTypeCode()));
            }

            target.setOrderNum(source.getOrderNum());
            target.setWhetherValid(source.getWhetherValid());
            target.setRemarks(source.getRemarks());
            return target;
        }
        return null;
    }


    /**
     * 等级调度转换
     *
     * @param source 等级调度PO
     * @return 等级调度BO
     */
    public static HierarchicalDispatchBean transform(HierarchicalDispatchEntity source, Map<String, Map<String, String>> dicsMap, Map<String, String> organizationNameMap) {
        if(organizationNameMap==null){
            organizationNameMap=new HashMap<>();
        }

        if (null != source) {
            HierarchicalDispatchBean target = new HierarchicalDispatchBean();
            target.setId(source.getId());
            target.setTitle(source.getTitle());
            target.setOrganizationId(source.getOrganizationId());
            if(!StringUtils.isBlank(source.getOrganizationId())){
                target.setOrganizationName(organizationNameMap.get(source.getOrganizationId()));
            }

            target.setIncidentTypeCode(source.getIncidentTypeCode());
            if(!StringUtils.isBlank(source.getIncidentTypeCode())&&dicsMap.get("AJLX")!=null){
                target.setIncidentTypeName(dicsMap.get("AJLX").get(source.getIncidentTypeCode()));
            }

            target.setDisposalObjectCode(source.getDisposalObjectCode());
            if(!StringUtils.isBlank(source.getDisposalObjectCode())&&dicsMap.get("CZDX")!=null){
                target.setDisposalObjectName(dicsMap.get("CZDX").get(source.getDisposalObjectCode()));
            }

            target.setIncidentLevelCode(source.getIncidentLevelCode());
            if(!StringUtils.isBlank(source.getIncidentLevelCode())&&dicsMap.get("AJDJ")!=null){
                target.setIncidentLevelName(dicsMap.get("AJDJ").get(source.getIncidentLevelCode()));
            }

            target.setMakePersonId(source.getMakePersonId());
            target.setMakePersonName(source.getMakePersonName());
            target.setMakeTime(source.getMakeTime());
            target.setRemarks(source.getRemarks());

            String vehicleType = source.getVehicleTypeCode();
            String dispatchNum = source.getDispatchNum();
            if (Strings.isNotBlank(vehicleType) && Strings.isNotBlank(dispatchNum)) {
                String[] vehicleTypes = vehicleType.split(",");
                String[] dispatchNums = dispatchNum.split(",");
                List<HierarchicalDispatchVehicleBean> hierarchicalDispatchVehicleBeanList = new ArrayList<>();
                for (int i = 0; i < vehicleTypes.length && i < dispatchNums.length; i++) {
                    String vehicleTypeCode = vehicleTypes[i];
                    String dispatchNumber = dispatchNums[i];
                    if (Strings.isBlank(vehicleTypeCode) || Strings.isBlank(dispatchNum)) {
                        continue;
                    }
                    HierarchicalDispatchVehicleBean hierarchicalDispatchVehicleBean = new HierarchicalDispatchVehicleBean();
                    hierarchicalDispatchVehicleBean.setVehicleTypeCode(vehicleTypeCode);
                    hierarchicalDispatchVehicleBean.setVehicleTypeName(dicsMap.get("WLCLLX").get(vehicleTypeCode));
                    hierarchicalDispatchVehicleBean.setDispatchNum(Integer.parseInt(dispatchNumber));
                    hierarchicalDispatchVehicleBeanList.add(hierarchicalDispatchVehicleBean);
                }
                target.setHierarchicalDispatchVehicleBeanList(hierarchicalDispatchVehicleBeanList);

            }
            return target;
        }
        return null;
    }


    /**
     * 等级调度转换
     *
     * @param source 等级调度INPUT
     * @return 等级调度PO
     */
    public static HierarchicalDispatchEntity transform(HierarchicalDispatchSaveInputInfo source) {
        if (null != source) {
            HierarchicalDispatchEntity target = new HierarchicalDispatchEntity();
            target.setId(source.getId());
            target.setTitle(source.getTitle());
            target.setOrganizationId(source.getOrganizationId());
            target.setIncidentTypeCode(source.getIncidentTypeCode());
            target.setDisposalObjectCode(source.getDisposalObjectCode());
            target.setIncidentLevelCode(source.getIncidentLevelCode());
            List<HierarchicalDispatchVehicleSaveInputInfo> hierarchicalDispatchVehicleSaveInputInfoList = source.getHierarchicalDispatchVehicleSaveInputInfoList();
            if (hierarchicalDispatchVehicleSaveInputInfoList != null && hierarchicalDispatchVehicleSaveInputInfoList.size() > 0) {
                String vehicleType = "";
                String dispatchNum = "";
                for (int i = 0; i < hierarchicalDispatchVehicleSaveInputInfoList.size(); i++) {
                    HierarchicalDispatchVehicleSaveInputInfo hierarchicalDispatchVehicleSaveInputInfo = hierarchicalDispatchVehicleSaveInputInfoList.get(i);
                    vehicleType = vehicleType + hierarchicalDispatchVehicleSaveInputInfo.getVehicleTypeCode();
                    dispatchNum = dispatchNum + hierarchicalDispatchVehicleSaveInputInfo.getDispatchNum();
                    if (i < hierarchicalDispatchVehicleSaveInputInfoList.size() - 1) {
                        vehicleType = vehicleType + ",";
                        dispatchNum = dispatchNum + ",";
                    }
                }
                target.setVehicleTypeCode(vehicleType);
                target.setDispatchNum(dispatchNum);
            }

            target.setRemarks(source.getRemarks());
            return target;
        }
        return null;
    }

    /**
     * 调度日志转换
     *
     * @param source 调度日志INPUT
     * @return 调度日志PO
     */
    public static DispatchDailyRecordEntity transform(DispatchDailyRecordSaveInputInfo source) {
        if (null != source) {
            DispatchDailyRecordEntity target = new DispatchDailyRecordEntity();
            target.setIncidentId(source.getIncidentId());
            target.setCallingNumber(source.getCallingNumber());
            target.setCalledNumber(source.getCalledNumber());
            target.setSeatNumber(source.getSeatNumber());
            target.setConversationStartTime(source.getConversationStartTime());
            target.setConversationEndTime(source.getConversationEndTime());
            target.setConversationDuration(source.getConversationDuration());
            target.setRelayRecordNumber(source.getRelayRecordNumber());
            target.setRemarks(source.getRemarks());
            return target;
        }
        return null;
    }

    /**
     * 调度日志转换
     *
     * @param source 调度日志PO
     * @return 调度日志BO
     */
    public static DispatchDailyRecordBean transform(DispatchDailyRecordEntity source) {
        if (null != source) {
            DispatchDailyRecordBean target = new DispatchDailyRecordBean();
            target.setId(source.getId());
            target.setIncidentId(source.getIncidentId());
            target.setCallingNumber(source.getCallingNumber());
            target.setCalledNumber(source.getCalledNumber());
            target.setSeatNumber(source.getSeatNumber());
            target.setConversationStartTime(source.getConversationStartTime());
            target.setConversationEndTime(source.getConversationEndTime());
            target.setConversationDuration(source.getConversationDuration());
            target.setRelayRecordNumber(source.getRelayRecordNumber());
            target.setRemarks(source.getRemarks());
            return target;
        }
        return null;
    }

    /**
     * 短信记录转换
     *
     * @param source 短信记录INPUT
     * @return 短信记录PO
     */
    public static SendSMSEntity transform(SendSMSSaveInputInfo source, Long currentSystemTime) {
        if (null != source) {
            SendSMSEntity target = new SendSMSEntity();
            target.setIncidentId(source.getIncidentId());
            target.setSmsType(source.getSmsType());
            target.setSmsNumber(source.getSmsNumber());
            target.setSmsContent(source.getSmsContent());
            target.setSystemCode(source.getSystemCode());
            target.setPriorityNum(source.getPriorityNum());
            target.setSmsAddTime(currentSystemTime);
            target.setSmsSendTime(currentSystemTime);
            target.setSendOrganizationId(source.getSendOrganizationId());
            target.setSendPersonNumber(source.getSendPersonNumber());
            target.setSendSeatNumber(source.getSendSeatNumber());
            target.setRemarks(source.getRemarks());
            return target;
        }
        return null;
    }

    /**
     * 短信记录转换
     *
     * @param source 短信记录PO
     * @return 短信记录BO
     */
    public static SendSMSBean transform(SendSMSEntity source, Map<String, String> organizationNameMap) {
        if(organizationNameMap==null){
            organizationNameMap=new HashMap<>();
        }

        if (null != source) {
            SendSMSBean target = new SendSMSBean();
            target.setIncidentId(source.getIncidentId());
            target.setSmsType(source.getSmsType());
            target.setSmsNumber(source.getSmsNumber());
            target.setSmsContent(source.getSmsContent());
            target.setSystemCode(source.getSystemCode());
            target.setPriorityNum(source.getPriorityNum());
            target.setSendStatus(source.getSendStatus());
            target.setSmsAddTime(source.getSmsAddTime());
            target.setSmsSendTime(source.getSmsSendTime());
            target.setSmsReceiveTime(source.getSmsReceiveTime());
            target.setSendOrganizationId(source.getSendOrganizationId());
            if(!StringUtils.isBlank(source.getSendOrganizationId())){
                target.setSendOrganizationName(organizationNameMap.get(source.getSendOrganizationId()));
            }

            target.setSendPersonNumber(source.getSendPersonNumber());
            target.setSendSeatNumber(source.getSendSeatNumber());
            target.setRemarks(source.getRemarks());
            target.setId(source.getId());
            return target;
        }
        return null;
    }


    /**
     * 处警记录 转换
     *
     * @param source 处警记录（调派记录）INPUTINFO
     * @return 处警记录（调派记录）PO
     */
    public static HandleEntity transform(HandleSaveInputInfo source, String incidentId) {
        if (null != source) {
            HandleEntity target = new HandleEntity();
            target.setIncidentId(incidentId);
            target.setHandleNumber(source.getHandleNumber());
            target.setOriginalIncidentNumber(incidentId);

            target.setHandleOrganization(source.getHandleOrganizationId());
            target.setHandleOrganizationId(source.getHandleOrganizationId());

            target.setHandleSeatNumber(source.getHandleSeatNumber());
            target.setHandlePersonNumber(source.getHandlePersonNumber());
            target.setHandlePersonName(source.getHandlePersonName());
            target.setWaitHandleDuration(source.getWaitHandleDuration());

            target.setHandleStatus(STATUS_GIVEN.getCode());
            target.setHandleSource(source.getHandleSource());
            target.setRemarks(source.getRemarks());

            target.setHandleStartTime(source.getHandleStartTime());
            target.setHandleEndTime(source.getHandleEndTime());
            if (source.getHandleDuration() == null) {
                if (source.getHandleStartTime() != null && source.getHandleStartTime() != 0 &&
                        source.getHandleEndTime() != null && source.getHandleEndTime() != 0) {
                    target.setHandleDuration(source.getHandleEndTime() - source.getHandleStartTime());
                } else {
                    target.setHandleDuration(0l);
                }
            }

            target.setHandleExpertNum(0);
            target.setHandlePersonNum(0);
            target.setDispatchVehicleNum(0);
            target.setDispatchEquipmentNum(0);

            target.setHandleTypeCode(source.getHandleTypeCode());
            target.setHandleContent(source.getHandleContent());


            return target;
        }
        return null;
    }


    /**
     * 处警信息 转换
     *
     * @param source 处警信息INPUTINFO
     * @return 处处警信息PO
     */
    public static HandleOrganizationEntity transform(HandleOrganizationSaveInputInfo source,
                                                     String incidentId, String handleId,
                                                     Map<String, String> organizationNameMap) {
        if(organizationNameMap==null){
            organizationNameMap=new HashMap<>();
        }

        if (null != source) {
            HandleOrganizationEntity target = new HandleOrganizationEntity();
            target.setIncidentId(incidentId);
            target.setOriginalIncidentNumber(incidentId);
            target.setHandleId(handleId);

            target.setOrganization(source.getOrganizationId());
            target.setOrganizationId(source.getOrganizationId());
            if(!StringUtils.isBlank(source.getOrganizationId())){
                target.setOrganizationName(organizationNameMap.get(source.getOrganizationId()));
            }

            target.setHandlePoliceStatus(STATUS_GIVEN.getCode());
            target.setRemarks(source.getRemarks());
            return target;
        }
        return null;
    }


    /**
     * 处警车辆（作战车辆） 转换
     *
     * @param source 处警车辆（作战车辆）INPUTINFO
     * @return 处警车辆（作战车辆）PO
     */
    public static HandleOrganizationVehicleEntity transform(HandleOrganizationVehicleSaveInputInfo source, String incidentId, String handleId,
                                                            String handlePoliceId, String organizationId, Long currentSystemTime) {
        if (null != source) {
            HandleOrganizationVehicleEntity target = new HandleOrganizationVehicleEntity();
            target.setIncidentId(incidentId);
            target.setOriginalIncidentNumber(incidentId);
            target.setHandleId(handleId);
            target.setHandlePoliceId(handlePoliceId);
            target.setOrganizationId(organizationId);

            target.setVehicle(source.getVehicleId());
            target.setVehicleId(source.getVehicleId());
            target.setVehicleTypeCode(source.getVehicleTypeCode());
            target.setFightFunctionCode(source.getFightFunctionCode());
            target.setVehicleStatusCode(VEHICLE_STATUS_TZ.getCode());
            target.setNoticeTime(currentSystemTime);
            target.setRemarks(source.getRemarks());
            return target;
        }
        return null;
    }

    /**
     * 处警装备  转换
     *
     * @param source 处警装备INPUTINFO
     * @return 处警装备PO
     */
    public static HandleOrganizationEquipmentEntity transform(HandleOrganizationEquipmentSaveInputInfo source,
                                                              String incidentId, String handleId, String handlePoliceId, String organizationId) {
        if (null != source) {
            HandleOrganizationEquipmentEntity target = new HandleOrganizationEquipmentEntity();
            target.setOrganizationId(source.getOrganizationId());
            target.setIncidentId(incidentId);
            target.setHandleId(handleId);
            target.setHandlePoliceId(handlePoliceId);
            target.setOrganizationId(organizationId);

            target.setEquipment(source.getEquipmentId());
            target.setEquipmentId(source.getEquipmentId());
            target.setEquipmentName(source.getEquipmentName());

            target.setEquipmentTypeCode(source.getEquipmentTypeCode());
            target.setDispatchNum(source.getDispatchNum());
            target.setRemarks(source.getRemarks());
            return target;
        }
        return null;
    }


    /**
     * 处警记录（调派记录）转换
     *
     * @param source 处警记录（调派记录）PO
     * @return 处警记录（调派记录）BO
     */
    public static HandleBean transform(HandleEntity source, Map<String, Map<String, String>> dicsMap, Map<String, String> organizationNameMap) {
        if (null != source) {
            HandleBean target = new HandleBean();
            target.setId(source.getId());
            target.setIncidentId(source.getIncidentId());
            target.setHandleNumber(source.getHandleNumber());
            target.setOriginalIncidentNumber(source.getOriginalIncidentNumber());
            target.setHandleStartTime(source.getHandleStartTime());
            target.setHandleEndTime(source.getHandleEndTime());
            target.setHandleBatch(source.getHandleBatch());
            target.setDispatchOrganization(source.getDispatchOrganization());
            target.setDispatchVehicle(source.getDispatchVehicle());
            target.setHandleOrganization(source.getHandleOrganization());
            target.setHandleOrganizationId(source.getHandleOrganizationId());
            target.setHandleOrganizationName(source.getHandleOrganizationName());
            target.setHandleSeatNumber(source.getHandleSeatNumber());
            target.setHandlePersonNumber(source.getHandlePersonNumber());
            target.setHandlePersonName(source.getHandlePersonName());
            target.setWaitHandleDuration(source.getWaitHandleDuration());
            target.setHandleDuration(source.getHandleDuration());
            target.setHandleStatus(source.getHandleStatus());
            if(!StringUtils.isBlank(source.getHandleStatus())&&dicsMap.get("ZLZT")!=null){
                target.setHandleStatusName(dicsMap.get("ZLZT").get(source.getHandleStatus()));
            }

            target.setHandleSource(source.getHandleSource());
            target.setRemarks(source.getRemarks());


            target.setFeedbackTime(source.getFeedbackTime());
            target.setHandleExpertNum(source.getHandleExpertNum());
            target.setHandlePersonNum(source.getHandlePersonNum());
            target.setDispatchVehicleNum(source.getDispatchVehicleNum());
            target.setDispatchEquipmentNum(source.getDispatchEquipmentNum());

            target.setHandleTypeCode(source.getHandleTypeCode());
            if(!StringUtils.isBlank(source.getHandleTypeCode())&&dicsMap.get("ZLDP")!=null){
                target.setHandleTypeName(dicsMap.get("ZLDP").get(source.getHandleTypeCode()));
            }

            target.setHandleContent(source.getHandleContent());

            return target;
        }
        return null;
    }

    /**
     * 调派信息（调派单位信息）转换
     *
     * @param source 调派信息（调派单位信息）PO
     * @return 调派信息（调派单位信息）BO
     */
    public static HandleOrganizationBean transform(HandleOrganizationEntity source, Map<String, Map<String, String>> dicsMap, Map<String, String> organizationNameMap) {
        if(organizationNameMap==null){
            organizationNameMap=new HashMap<>();
        }

        if (null != source) {
            HandleOrganizationBean target = new HandleOrganizationBean();
            target.setId(source.getId());
            target.setIncidentId(source.getIncidentId());
            target.setHandleId(source.getHandleId());
            target.setOrganization(source.getOrganization());
            target.setOrganizationId(source.getOrganizationId());
            if(!StringUtils.isBlank(source.getOrganizationId())){
                target.setOrganizationName(organizationNameMap.get(source.getOrganizationId()));
            }

            target.setNoticeTime(source.getNoticeTime());
            target.setSystemFeedbackTime(source.getSystemFeedbackTime());
            target.setPersonFeedbackPersonTime(source.getPersonFeedbackPersonTime());
            target.setHandlePoliceStatus(source.getHandlePoliceStatus());
            if(!StringUtils.isBlank(source.getHandlePoliceStatus())&&dicsMap.get("ZLZT")!=null){
                target.setHandlePoliceStatusName(dicsMap.get("ZLZT").get(source.getHandlePoliceStatus()));
            }

            target.setFeedbackPersonId(source.getFeedbackPersonId());
            target.setFeedbackPersonName(source.getFeedbackPersonName());

            target.setSpeakToFileUrl(source.getSpeakToFileUrl());

            target.setRemarks(source.getRemarks());
            return target;
        }
        return null;
    }

    /**
     * 调派信息（调派单位信息）转换
     *
     * @param source 调派信息（调派单位信息）PO
     * @return 调派信息（调派单位信息）BO
     */
    public static void transform(HandleOrganizationBean target, OrganizationBean source) {
        if (null != source) {
            target.setOrganizationCode(source.getOrganizationCode());
            if (!StringUtils.isBlank(source.getOrganizationShortName())){
                target.setOrganizationName(source.getOrganizationShortName());
            }else {
                target.setOrganizationName(source.getOrganizationName());
            }
            target.setPinyinHeader(source.getPinyinHeader());

            target.setLongitude(source.getLongitude());
            target.setLatitude(source.getLatitude());
            target.setHeight(source.getHeight());

            target.setOrganizationParentId(source.getOrganizationParentId());
            target.setOrganizationOrderNum(source.getOrganizationOrderNum());

            target.setDistrictCode(source.getDistrictCode());
            target.setDistrictName(source.getDistrictName());

            target.setContactPerson(source.getContactPerson());
            target.setContactPersonName(source.getContactPersonName());
            target.setDispatchPhone(source.getDispatchPhone());

            target.setMailCode(source.getMailCode());
            target.setFaxNumber(source.getFaxNumber());
            target.setContactPhone(source.getContactPhone());
            target.setEmail(source.getEmail());
            target.setContactPhone(source.getContactPhone());
        }
    }


    /**
     * 调派单位装备信息 转换
     *
     * @param source 调派单位装备信息PO
     * @return 调派单位装备信息BO
     */
    public static HandleOrganizationEquipmentBean transform(HandleOrganizationEquipmentEntity source, Map<String, Map<String, String>> dicsMap, Map<String, String> organizationNameMap) {

        if(organizationNameMap==null){
            organizationNameMap=new HashMap<>();
        }
        if (null != source) {
            HandleOrganizationEquipmentBean target = new HandleOrganizationEquipmentBean();
            target.setId(source.getId());
            target.setIncidentId(source.getIncidentId());
            target.setHandleId(source.getHandleId());
            target.setHandlePoliceId(source.getHandlePoliceId());
            target.setOrganizationId(source.getOrganizationId());
            if(!StringUtils.isBlank(target.getOrganizationId())){
                target.setOrganizationName(organizationNameMap.get(target.getOrganizationId()));
            }

            target.setEquipmentTypeCode(source.getEquipmentTypeCode());
            if(!StringUtils.isBlank(target.getEquipmentTypeCode())&&dicsMap.get("ZBLX")!=null){
                target.setEquipmentTypeName(dicsMap.get("ZBLX").get(target.getEquipmentTypeCode()));
            }

            target.setDispatchNum(source.getDispatchNum());
            target.setRemarks(source.getRemarks());

            target.setEquipment(source.getEquipmentId());
            target.setEquipmentId(source.getEquipmentId());
            target.setEquipmentName(source.getEquipmentName());
            target.setDispatchStartTime(source.getDispatchStartTime());
            target.setDispatchEndTime(source.getDispatchStartTime());
            target.setDispatchEndTime(source.getDispatchEndTime());


            return target;
        }
        return null;
    }

    /**
     * 调派单位车辆信息 （ 作战车辆信息 ） 转换
     *
     * @param source 调派单位车辆信息 （ 作战车辆信息 ）PO
     * @return 调派单位车辆信息 （ 作战车辆信息 ）BO
     */
    public static HandleOrganizationVehicleBean transform(HandleOrganizationVehicleEntity source, Map<String, Map<String, String>> dicsMap,
                                                          Map<String, String> organizationNameMap) {
        if(organizationNameMap==null){
            organizationNameMap=new HashMap<>();
        }

        if (null != source) {
            HandleOrganizationVehicleBean target = new HandleOrganizationVehicleBean();
            target.setId(source.getId());
            target.setIncidentId(source.getIncidentId());
            target.setHandleId(source.getHandleId());
            target.setHandlePoliceId(source.getHandlePoliceId());
            target.setOrganizationId(source.getOrganizationId());
            if(!StringUtils.isBlank(source.getOrganizationId())){
                target.setOrganizationName(organizationNameMap.get(source.getOrganizationId()));
            }


            target.setVehicle(source.getVehicle());
            target.setVehicleId(source.getVehicleId());
            target.setVehicleNumber(source.getVehicleNumber());
            target.setVehicleTypeCode(source.getVehicleTypeCode());
            if(!StringUtils.isBlank(source.getVehicleTypeCode())&&dicsMap.get("WLCLLX")!=null){
                target.setVehicleTypeName(dicsMap.get("WLCLLX").get(source.getVehicleTypeCode()));
            }


            target.setFightFunctionCode(source.getFightFunctionCode());
            if(!StringUtils.isBlank(source.getFightFunctionCode())&&dicsMap.get("WLZZGN")!=null){
                target.setFightFunctionName(dicsMap.get("WLZZGN").get(source.getFightFunctionCode()));
            }


            target.setVehicleStatusCode(source.getVehicleStatusCode());
            if(!StringUtils.isBlank(source.getVehicleStatusCode())&&dicsMap.get("WLCLZT")!=null){
                target.setVehicleStatusName(dicsMap.get("WLCLZT").get(source.getVehicleStatusCode()));
            }


            target.setNoticeTime(source.getNoticeTime());
            target.setSetOutTime(source.getSetOutTime());
            target.setArriveTime(source.getArriveTime());
            target.setSendWaterTime(source.getSendWaterTime());
            target.setStopWaterTime(source.getStopWaterTime());
            target.setReturnLoadTime(source.getReturnLoadTime());
            target.setHalfwayReturnTime(source.getHalfwayReturnTime());
            target.setReturnTime(source.getReturnTime());


            target.setRemarks(source.getRemarks());

            target.setVehicleIdentification(source.getVehicleIdentification());

            return target;
        }
        return null;
    }


    /**
     * 调派单位车辆信息 （ 作战车辆信息 ） 补充信息
     *
     * @param source 调派单位车辆信息 （ 作战车辆信息 ）PO
     * @return 调派单位车辆信息 （ 作战车辆信息 ）BO
     */
    public static void transform(HandleOrganizationVehicleBean target, EquipmentVehicleBean source) {
        if (null != source) {
            target.setVehicleName(source.getVehicleName());
            target.setOrganizationId(source.getOrganizationId());
            target.setOrganizationName(source.getOrganizationName());
            if (Strings.isBlank(source.getVehicleNumber())) {
                target.setVehicleNumber(source.getVehicleNumber());
            }

            target.setGpsNumber(source.getGpsNumber());
            target.setLocationNumber(source.getLocationNumber());
            target.setRadioCallSign(source.getRadioCallSign());
            target.setIncidentNumber(source.getIncidentNumber());
            target.setVehicleOrderNum(source.getVehicleOrderNum());



            target.setMappingGroupId(source.getMappingGroupId());

        }
    }


    /**
     * 参战人员反馈  转换
     *
     * @param source 参战人员反馈INPUTINFO
     * @return 参战人员反馈PO
     */
    public static List<ParticipantFeedbackEntity> transform(ParticipantSaveInputInfo source) {
        if (null != source && source.getParticipantFeedbackSaveInputInfo() != null) {
            List<ParticipantFeedbackEntity> target = new ArrayList<>();
            List<ParticipantFeedbackSaveInputInfo> participantFeedbackSources = source.getParticipantFeedbackSaveInputInfo();
            for (ParticipantFeedbackSaveInputInfo participantFeedbackSource : participantFeedbackSources) {
                ParticipantFeedbackEntity participantFeedbackTarget = new ParticipantFeedbackEntity();
                participantFeedbackTarget.setIncidentId(source.getIncidentId());
                participantFeedbackTarget.setHandleId(source.getHandleId());
                participantFeedbackTarget.setOriginalIncidentNumber(source.getIncidentId());
                participantFeedbackTarget.setVehicleId(source.getVehicleId());
                participantFeedbackTarget.setPersonId(participantFeedbackSource.getPersonId());
                participantFeedbackTarget.setSorter(participantFeedbackSource.getSorter());
                participantFeedbackTarget.setCheckTime(participantFeedbackSource.getCheckTime());
                participantFeedbackTarget.setWhetherFeedback(EnableEnum.ENABLE_FALSE.getCode());
                participantFeedbackTarget.setFeedbackCheckTime(participantFeedbackSource.getFeedbackCheckTime());
                participantFeedbackTarget.setCheckPersonId(source.getCheckPersonId());
                participantFeedbackTarget.setCheckPersonName(source.getCheckPersonName());
                participantFeedbackTarget.setRemarks(participantFeedbackSource.getRemarks());
                target.add(participantFeedbackTarget);
            }
            return target;
        }
        return null;
    }


    /**
     * 参战人员反馈  转换
     *
     * @param source 参战人员反馈PO
     * @return 参战人员反馈BO
     */

    public static ParticipantFeedbackBean transform(ParticipantFeedbackEntity source, Map<String, Map<String, String>> dicsMap, Map<String, String> organizationNameMap) {
        if(organizationNameMap==null){
            organizationNameMap=new HashMap<>();
        }

        if (null != source) {
            ParticipantFeedbackBean target = new ParticipantFeedbackBean();
            target.setId(source.getId());
            target.setIncidentId(source.getIncidentId());
            target.setHandleId(source.getHandleId());
            target.setOrganizationId(source.getOrganizationId());
            if(!StringUtils.isBlank(source.getOrganizationId())){
                target.setOrganizationName(organizationNameMap.get(source.getOrganizationId()));
            }


            target.setVehicleId(source.getVehicleId());
            target.setPersonId(source.getPersonId());
            target.setPersonName(source.getPersonName());
            target.setPersonPhone(source.getPersonPhone());
            target.setPersonRole(source.getPersonRole());
            if(!StringUtils.isBlank(source.getPersonRole())&&dicsMap.get("WLRYZW")!=null){
                target.setPersonRoleName(dicsMap.get("WLRYZW").get(source.getPersonRole()));
            }


            target.setSorter(source.getSorter());
            target.setCheckTime(source.getCheckTime());
            target.setWhetherFeedback(source.getWhetherFeedback());
            target.setFeedbackCheckTime(source.getFeedbackCheckTime());
            target.setCheckPersonId(source.getCheckPersonId());
            target.setCheckPersonName(source.getCheckPersonName());
            target.setRemarks(source.getRemarks());
            target.setEnterFireTime(source.getEntryTime());
            target.setWithdrawFireTime(source.getExitTime());
            target.setStateCode(source.getState());
            String nameByCode = BasicEnumNumberUtils.getNameByCode(ParticipantPersonStateEnum.class, source.getState());
            target.setStateName(nameByCode);
            return target;
        }
        return null;
    }


    /**
     * 出入火场  转换
     *
     * @param source 出入火场INPUTINFO
     * @return 出入火场PO
     */
    public static List<FireSafetyMonitoringEntity> transform(FireSafetySaveInputInfo source, String incidentId, Long currentTime, Long fireSceneOverTime) {
        if (null != source && source.getFireSafetyMonitoringSaveInputInfo() != null) {
            List<FireSafetyMonitoringEntity> target = new ArrayList<>();
            List<FireSafetyMonitoringSaveInputInfo> fireSafetyMonitoringSources = source.getFireSafetyMonitoringSaveInputInfo();
            for (FireSafetyMonitoringSaveInputInfo fireSafetyMonitoringSource : fireSafetyMonitoringSources) {
                FireSafetyMonitoringEntity fireSafetyMonitoringTarget = new FireSafetyMonitoringEntity();
                fireSafetyMonitoringTarget.setIncidentId(incidentId);
                fireSafetyMonitoringTarget.setOrganizationId(fireSafetyMonitoringSource.getOrganizationId());
                fireSafetyMonitoringTarget.setOrganizationName(fireSafetyMonitoringSource.getOrganizationName());
                fireSafetyMonitoringTarget.setPersonId(fireSafetyMonitoringSource.getPersonId());
                fireSafetyMonitoringTarget.setPersonName(fireSafetyMonitoringSource.getPersonName());
                fireSafetyMonitoringTarget.setEnterFireTime(currentTime);
                fireSafetyMonitoringTarget.setWithdrawFireTime(null);
                fireSafetyMonitoringTarget.setPlanWithdrawFireTime(fireSceneOverTime);
                fireSafetyMonitoringTarget.setRemarks(fireSafetyMonitoringSource.getRemarks());
                target.add(fireSafetyMonitoringTarget);
            }
            return target;
        }
        return null;
    }


    /**
     * 出入火场  转换
     *
     * @param source 出入火场PO
     * @return 出入火场BO
     */
    public static FireSafetyMonitoringBean transform(FireSafetyMonitoringEntity source, Map<String, String> organizationNameMap) {
        if (null != source) {
            FireSafetyMonitoringBean target = new FireSafetyMonitoringBean();
            target.setId(source.getId());
            target.setIncidentId(source.getIncidentId());
            target.setOrganizationId(source.getOrganizationId());
            target.setOrganizationName(source.getOrganizationName());
            target.setPersonId(source.getPersonId());
            target.setPersonName(source.getPersonName());
            target.setEnterFireTime(source.getEnterFireTime());
            target.setWithdrawFireTime(source.getWithdrawFireTime());
            target.setPlanWithdrawFireTime(source.getPlanWithdrawFireTime());
            target.setRemarks(source.getRemarks());
            return target;
        }
        return null;
    }


    /**
     * 请求增援 转换
     *
     * @param source 请求增援保存参数INPUT
     * @return 请求增援PO
     */
    public static ReinforcementAskEntity transform(ReinforcementAskSaveInputInfo source) {
        if (null != source) {
            ReinforcementAskEntity target = new ReinforcementAskEntity();
            target.setIncidentId(source.getIncidentId());
            target.setType(source.getType());
            if (source.getReinforcementAskDetailInputInfo() != null) {
                String detail = JSONArray.toJSONString(source.getReinforcementAskDetailInputInfo());
                target.setReinforcementAskContent(detail);
            }
            target.setContent(source.getContent());
            target.setAskOrganizationId(source.getAskOrganizationId());
            target.setReceiveOrganizationId(source.getReceiveOrganizationId());
            target.setReinforcementTypeSign(source.getReinforcementTypeSign());
            target.setHandleSign(null);
            target.setMeetAddress(source.getMeetAddress());
            target.setMeetTime(source.getMeetTime());
            target.setMeetContactPerson(source.getMeetContactPerson());
            target.setMeetContactPersonPhone(source.getMeetContactPersonPhone());
            target.setRemarks(source.getRemarks());
            return target;
        }
        return null;
    }

    /**
     * 请求增援 转换
     *
     * @param source 请求增援PO
     * @return 请求增援BO
     */
    public static ReinforcementAskBean transform(ReinforcementAskEntity source, Map<String, String> organizationNameMap, Map<String, Map<String, String>> dicsMap) {
        if(organizationNameMap==null){
            organizationNameMap=new HashMap<>();
        }

        if (null != source) {
            ReinforcementAskBean target = new ReinforcementAskBean();
            target.setId(source.getId());
            target.setIncidentId(source.getIncidentId());
            target.setType(source.getType());
            target.setContent(source.getContent());
            target.setAskOrganizationId(source.getAskOrganizationId());
            if(!StringUtils.isBlank(source.getAskOrganizationId())){
                target.setAskOrganizationName(organizationNameMap.get(source.getAskOrganizationId()));
            }


            target.setReceiveOrganizationId(source.getReceiveOrganizationId());
            if(!StringUtils.isBlank(source.getReceiveOrganizationId())){
                target.setReceiveOrganizationName(organizationNameMap.get(source.getReceiveOrganizationId()));
            }


            target.setReinforcementTypeSign(source.getReinforcementTypeSign());
            String reinforcementAskContent = source.getReinforcementAskContent();
            List<ReinforcementAskDetailBean> reinforcementAskDetailBean = JSONArray.parseArray(reinforcementAskContent, ReinforcementAskDetailBean.class);
            if (reinforcementAskDetailBean != null && reinforcementAskDetailBean.size() > 0) {
                for (ReinforcementAskDetailBean reinforcementAskDetail : reinforcementAskDetailBean) {
                    reinforcementAskDetail.setVehicleTypeName(dicsMap.get("WLCLLX").get(reinforcementAskDetail.getVehicleTypeCode()));
                    reinforcementAskDetail.setEquipmentTypeName(dicsMap.get("ZBLX").get(reinforcementAskDetail.getEquipmentTypeCode()));
                }
            }
            target.setReinforcementAskDetailBean(reinforcementAskDetailBean);
            target.setHandleSign(source.getHandleSign());
            target.setMeetAddress(source.getMeetAddress());
            target.setMeetTime(source.getMeetTime());
            target.setMeetContactPerson(source.getMeetContactPerson());
            target.setMeetContactPersonPhone(source.getMeetContactPersonPhone());
            target.setRemarks(source.getRemarks());
            return target;
        }
        return null;
    }


    /**
     * 现场信息 转换
     *
     * @param source 现场信息保存参数INPUT
     * @return 现场信息PO
     */
    public static LocaleEntity transform(LocaleSaveInputInfo source, Long currentSystemTime) {
        if (null != source) {
            LocaleEntity target = new LocaleEntity();
            target.setId(source.getId());
            target.setIncidentId(source.getIncidentId());

            target.setCommandId(source.getCommandId());
            target.setInstructId(source.getInstructId());
            target.setInstructRecordId(source.getInstructRecordId());
            target.setLocaleType(source.getLocaleType());
            target.setLocaleSource(source.getLocaleSource());


            target.setCollectionTime(currentSystemTime);
            target.setLocaleDesc(source.getLocaleDesc());

            target.setLocaleExtension(source.getLocaleExtension());


            target.setFireSitesTemperature(source.getFireSitesTemperature());
            target.setWeatherInformationCode(source.getWeatherInformationCode());
            target.setWeatherTemperature(source.getWeatherTemperature());
            target.setWindDirection(source.getWindDirection());
            target.setWindGrade(source.getWindGrade());
            target.setRelativeHumidity(source.getRelativeHumidity());
            target.setDisasterGradeCode(source.getDisasterGradeCode());
            target.setBurningArea(source.getBurningArea());
            target.setSmogSituationCode(source.getSmogSituationCode());


            target.setWhetherSmogSituation(source.getWhetherSmogSituation());
            target.setWhetherFire(source.getWhetherFire());

            target.setTempRallyPointAddress(source.getTempRallyPointAddress());
            target.setLongitude(source.getLongitude());
            target.setLatitude(source.getLatitude());


            target.setCrimeAddress(source.getCrimeAddress());
            target.setIncidentTypeCode(source.getIncidentTypeCode());
            target.setIncidentTypeSubitemCode(source.getIncidentTypeSubitemCode());

            target.setFeedbackObjectId(source.getFeedbackObjectId());
            target.setFeedbackObjectName(source.getFeedbackObjectName());
            target.setFeedbackOrganizationId(source.getFeedbackOrganizationId());
            target.setFeedbackOrganizationName(source.getFeedbackOrganizationName());

            target.setOperatorPerson(source.getOperatorPerson());
            target.setRemarks(source.getRemarks());

            target.setWhetherFileFeedback(source.getWhetherFileFeedback());

            target.setAttendanceNum(source.getAttendanceNum());
            target.setTrappedPersonNum(source.getTrappedPersonNum());

            target.setRescueNum(source.getRescueNum());
            target.setMassRescueNum(source.getMassRescueNum());
            target.setTroopsRescueNum(source.getTroopsRescueNum());

            target.setInjuredNum(source.getInjuredNum());
            target.setMassInjuredNum(source.getMassInjuredNum());
            target.setTroopsInjuredNum(source.getTroopsInjuredNum());

            target.setDeathNum(source.getDeathNum());
            target.setMassDeathNum(source.getMassDeathNum());
            target.setTroopsDeathNum(source.getTroopsDeathNum());

            target.setOutContactNum(source.getOutContactNum());
            target.setMassOutContactNum(source.getMassOutContactNum());
            target.setTroopsOutContactNum(source.getTroopsOutContactNum());

            target.setFireSitesDesc(source.getFireSitesDesc());
            target.setBurnMaterialCode(source.getBurnMaterialCode());
            target.setBurnMaterialName(source.getBurnMaterialName());

            target.setWindSpeed(source.getWindSpeed());
            target.setPressure(source.getPressure());
            target.setSmogSituationDesc(source.getSmogSituationDesc());
            target.setLocaleCommander(source.getLocaleCommander());


            target.setBuildingUpLayerNumber(source.getBuildingUpLayerNumber());
            target.setBuildingDownLayerNumber(source.getBuildingDownLayerNumber());
            target.setBuildingStructure(source.getBuildingStructure());
            target.setBurningArea(source.getBurningArea());
            target.setBurningFloor(source.getBurningFloor());

            return target;
        }
        return null;
    }


    /**
     * 现场信息 转换
     *
     * @param source 现场信息保存参数INPUT
     * @return 现场信息PO
     */
    public static LocaleBean transform(LocaleEntity source, Map<String, Map<String, String>> dicsMap, List<AttachmentBean> attachmentBeans) {
        if (null != source) {
            LocaleBean target = new LocaleBean();
            target.setId(source.getId());
            target.setIncidentId(source.getIncidentId());

            target.setCommandId(source.getCommandId());
            target.setInstructId(source.getInstructId());
            target.setInstructRecordId(source.getInstructRecordId());
            target.setLocaleType(source.getLocaleType());
            target.setLocaleSource(source.getLocaleSource());

            target.setCollectionTime(source.getCollectionTime());
            target.setLocaleDesc(source.getLocaleDesc());
            target.setLocaleExtension(source.getLocaleExtension());

            target.setAttachmentBeans(attachmentBeans);
            target.setFireSitesTemperature(source.getFireSitesTemperature());
            target.setWeatherInformationCode(source.getWeatherInformationCode());
            if(!StringUtils.isBlank(source.getWeatherInformationCode())&&dicsMap.get("TQQX")!=null){
                target.setWeatherInformationName(dicsMap.get("TQQX").get(source.getWeatherInformationCode()));
            }


            target.setWeatherTemperature(source.getWeatherTemperature());
            target.setWindDirection(source.getWindDirection());
            if(!StringUtils.isBlank(source.getWindDirection())&&dicsMap.get("FX")!=null){
                target.setWindDirectionName(dicsMap.get("FX").get(source.getWindDirection()));
            }


            target.setWindGrade(source.getWindGrade());
            if(!StringUtils.isBlank(source.getWindGrade())&&dicsMap.get("FLDJ")!=null){
                target.setWindGradeName(dicsMap.get("FLDJ").get(source.getWindGrade()));
            }


            target.setRelativeHumidity(source.getRelativeHumidity());
            target.setDisasterGradeCode(source.getDisasterGradeCode());
            if(!StringUtils.isBlank(source.getDisasterGradeCode())&&dicsMap.get("AJDJ")!=null){
                target.setDisasterGradeName(dicsMap.get("AJDJ").get(source.getDisasterGradeCode()));
            }


            target.setBurningArea(source.getBurningArea());
            target.setSmogSituationCode(source.getSmogSituationCode());
            if(!StringUtils.isBlank(source.getSmogSituationCode())&&dicsMap.get("YWQK")!=null){
                target.setSmogSituationName(dicsMap.get("YWQK").get(source.getSmogSituationCode()));
            }

            target.setWhetherSmogSituation(source.getWhetherSmogSituation());
            target.setWhetherFire(source.getWhetherFire());

            target.setTempRallyPointAddress(source.getTempRallyPointAddress());
            target.setLongitude(source.getLongitude());
            target.setLatitude(source.getLatitude());


            target.setCrimeAddress(source.getCrimeAddress());
            target.setIncidentTypeCode(source.getIncidentTypeCode());
            if(!StringUtils.isBlank(source.getIncidentTypeCode())&&dicsMap.get("AJLX")!=null){
                target.setIncidentTypeName(dicsMap.get("AJLX").get(source.getIncidentTypeCode()));
            }


            target.setIncidentTypeSubitemCode(source.getIncidentTypeSubitemCode());
            if(!StringUtils.isBlank(source.getIncidentTypeSubitemCode())&&dicsMap.get("AJLXZX")!=null){
                target.setIncidentTypeSubitemName(dicsMap.get("AJLXZX").get(source.getIncidentTypeSubitemCode()));
            }



            target.setFeedbackObjectId(source.getFeedbackObjectId());
            target.setFeedbackObjectName(source.getFeedbackObjectName());
            target.setFeedbackOrganizationId(source.getFeedbackOrganizationId());
            target.setFeedbackOrganizationName(source.getFeedbackOrganizationName());

            target.setOperatorPerson(source.getOperatorPerson());
            target.setRemarks(source.getRemarks());

            target.setWhetherFileFeedback(source.getWhetherFileFeedback());

            target.setAttendanceNum(source.getAttendanceNum());
            target.setTrappedPersonNum(source.getTrappedPersonNum());

            target.setRescueNum(source.getRescueNum());
            target.setMassRescueNum(source.getMassRescueNum());
            target.setTroopsRescueNum(source.getTroopsRescueNum());

            target.setInjuredNum(source.getInjuredNum());
            target.setMassInjuredNum(source.getMassInjuredNum());
            target.setTroopsInjuredNum(source.getTroopsInjuredNum());

            target.setDeathNum(source.getDeathNum());
            target.setMassDeathNum(source.getMassDeathNum());
            target.setTroopsDeathNum(source.getTroopsDeathNum());

            target.setOutContactNum(source.getOutContactNum());
            target.setMassOutContactNum(source.getMassOutContactNum());
            target.setTroopsOutContactNum(source.getTroopsOutContactNum());

            target.setFireSitesDesc(source.getFireSitesDesc());
            target.setBurnMaterialCode(source.getBurnMaterialCode());
            target.setBurnMaterialName(source.getBurnMaterialName());

            target.setWindSpeed(source.getWindSpeed());
            target.setPressure(source.getPressure());
            target.setSmogSituationDesc(source.getSmogSituationDesc());
            target.setLocaleCommander(source.getLocaleCommander());

            target.setBuildingUpLayerNumber(source.getBuildingUpLayerNumber());
            target.setBuildingDownLayerNumber(source.getBuildingDownLayerNumber());
            target.setBuildingStructure(source.getBuildingStructure());
            if(!StringUtils.isBlank(source.getBuildingStructure())&&dicsMap.get("JZJG")!=null){
                target.setBuildingStructureName(dicsMap.get("JZJG").get(source.getBuildingStructure()));
            }


            target.setBurningArea(source.getBurningArea());
            target.setBurningFloor(source.getBurningFloor());

            return target;
        }
        return null;
    }


    /**
     * 事故情况 转换
     *
     * @param source 事故情况保存参数INPUT
     * @return vPO
     */
    public static AccidentEntity transform(AccidentSaveInputInfo source) {
        if (null != source) {
            AccidentEntity target = new AccidentEntity();
            target.setId(source.getId());
            target.setIncidentId(source.getIncidentId());
            target.setActualCrimeAddress(source.getActualCrimeAddress());
            target.setAccidentUnitName(source.getAccidentUnitName());
            target.setAccidentUnitNature(source.getAccidentUnitNature());
            target.setResponsiblePersonName(source.getResponsiblePersonName());
            target.setResponsiblePersonAge(source.getResponsiblePersonAge());
            target.setResponsiblePersonCareer(source.getResponsiblePersonCareer());
            target.setResponsiblePersonNativePlace(source.getResponsiblePersonNativePlace());
            target.setAccidentReason(source.getAccidentReason());
            target.setBurningMaterial(source.getBurningMaterial());
            target.setBurningArea(source.getBurningArea());
            target.setBurningFloor(source.getBurningFloor());
            target.setProtectArea(source.getProtectArea());
            target.setEconomicLoss(source.getEconomicLoss());
            target.setProtectEconomic(source.getProtectEconomic());
            target.setAccidentDescribe(source.getAccidentDescribe());
            target.setRescueMeasures(source.getRescueMeasures());
            target.setSmogSituation(source.getSmogSituation());
            target.setSpreadSituation(source.getSpreadSituation());
            target.setWaterSituation(source.getWaterSituation());
            target.setPeripherySituation(source.getPeripherySituation());
            target.setBuildingDownLayerNumber(source.getBuildingDownLayerNumber());
            target.setBuildingUpLayerNumber(source.getBuildingUpLayerNumber());
            target.setBuildingStructure(source.getBuildingStructure());
            target.setBuildingArea(source.getBuildingArea());
            target.setLeakageMaterial(source.getLeakageMaterial());

            target.setIncidentLevelCode(source.getIncidentLevelCode());

            target.setDispatchTrainNum(source.getDispatchTrainNum());
            target.setDispatchVehicleNum(source.getDispatchVehicleNum());
            target.setDispatchWaterGunNum(source.getDispatchWaterGunNum());
            target.setDispatchPersonNum(source.getDispatchPersonNum());

            target.setAttendanceNum(source.getAttendanceNum());
            target.setTrappedPersonNum(source.getTrappedPersonNum());

            target.setRescueNum(source.getRescueNum());
            target.setMassRescueNum(source.getMassRescueNum());
            target.setTroopsRescueNum(source.getTroopsRescueNum());

            target.setInjuredNum(source.getInjuredNum());
            target.setMassInjuredNum(source.getMassInjuredNum());
            target.setTroopsInjuredNum(source.getTroopsInjuredNum());

            target.setDeathNum(source.getDeathNum());
            target.setMassDeathNum(source.getMassDeathNum());
            target.setTroopsDeathNum(source.getTroopsDeathNum());

            target.setOutContactNum(source.getOutContactNum());
            target.setMassOutContactNum(source.getMassOutContactNum());
            target.setTroopsOutContactNum(source.getTroopsOutContactNum());

            target.setRemarks(source.getRemarks());
            return target;
        }
        return null;
    }


    /**
     * 事故情况 转换
     * 包含作战状况
     *
     * @param source               事故情况PO
     * @param fightSituationEntity 作战状况PO
     * @return 事故情况PO
     */
    public static AccidentBean transform(AccidentEntity source, FightSituationEntity fightSituationEntity, Map<String, Map<String, String>> dicsMap) {
        if (null != source) {
            AccidentBean target = new AccidentBean();
            target.setId(source.getId());
            target.setIdCode(source.getIdCode());
            target.setIncidentId(source.getIncidentId());
            target.setActualCrimeAddress(source.getActualCrimeAddress());
            target.setAccidentUnitName(source.getAccidentUnitName());
            target.setAccidentUnitNature(source.getAccidentUnitNature());
            if(!StringUtils.isBlank(source.getAccidentUnitNature())&&dicsMap.get("DWXZ")!=null){
                target.setAccidentUnitNatureName(dicsMap.get("DWXZ").get(source.getAccidentUnitNature()));
            }


            target.setResponsiblePersonName(source.getResponsiblePersonName());
            target.setResponsiblePersonAge(source.getResponsiblePersonAge());
            target.setResponsiblePersonCareer(source.getResponsiblePersonCareer());
            target.setResponsiblePersonNativePlace(source.getResponsiblePersonNativePlace());
            target.setAccidentReason(source.getAccidentReason());
            target.setBurningMaterial(source.getBurningMaterial());
            target.setBurningArea(source.getBurningArea());
            target.setBurningFloor(source.getBurningFloor());
            target.setProtectArea(source.getProtectArea());
            target.setEconomicLoss(source.getEconomicLoss());
            target.setProtectEconomic(source.getProtectEconomic());
            target.setAccidentDescribe(source.getAccidentDescribe());
            target.setRescueMeasures(source.getRescueMeasures());
            target.setSmogSituation(source.getSmogSituation());
            if(!StringUtils.isBlank(source.getSmogSituation())&&dicsMap.get("YWQK")!=null){
                target.setSmogSituationName(dicsMap.get("YWQK").get(source.getSmogSituation()));
            }


            target.setSpreadSituation(source.getSpreadSituation());
            target.setWaterSituation(source.getWaterSituation());
            target.setPeripherySituation(source.getPeripherySituation());
            target.setBuildingDownLayerNumber(source.getBuildingDownLayerNumber());
            target.setBuildingUpLayerNumber(source.getBuildingUpLayerNumber());
            target.setBuildingStructure(source.getBuildingStructure());
            if(!StringUtils.isBlank(source.getBuildingStructure())&&dicsMap.get("JZJG")!=null){
                target.setBuildingStructureName(dicsMap.get("JZJG").get(source.getBuildingStructure()));
            }


            target.setBuildingArea(source.getBuildingArea());
            target.setLeakageMaterial(source.getLeakageMaterial());

            target.setAccidentTime(source.getAccidentTime());

            target.setIncidentLevelCode(source.getIncidentLevelCode());
            if(!StringUtils.isBlank(source.getIncidentLevelCode())&&dicsMap.get("AJDJ")!=null){
                target.setIncidentLevelName(dicsMap.get("AJDJ").get(source.getIncidentLevelCode()));
            }



            target.setDispatchTrainNum(source.getDispatchTrainNum());
            target.setDispatchVehicleNum(source.getDispatchVehicleNum());
            target.setDispatchWaterGunNum(source.getDispatchWaterGunNum());
            target.setDispatchPersonNum(source.getDispatchPersonNum());

            target.setAttendanceNum(source.getAttendanceNum());
            target.setTrappedPersonNum(source.getTrappedPersonNum());

            target.setRescueNum(source.getRescueNum());
            target.setMassRescueNum(source.getMassRescueNum());
            target.setTroopsRescueNum(source.getTroopsRescueNum());

            target.setInjuredNum(source.getInjuredNum());
            target.setMassInjuredNum(source.getMassInjuredNum());
            target.setTroopsInjuredNum(source.getTroopsInjuredNum());

            target.setDeathNum(source.getDeathNum());
            target.setMassDeathNum(source.getMassDeathNum());
            target.setTroopsDeathNum(source.getTroopsDeathNum());

            target.setOutContactNum(source.getOutContactNum());
            target.setMassOutContactNum(source.getMassOutContactNum());
            target.setTroopsOutContactNum(source.getTroopsOutContactNum());


            target.setRemarks(source.getRemarks());

            //作战情况相关字段
            if (fightSituationEntity != null) {
                target.setCombatSituation(fightSituationEntity.getCombatSituation());
                target.setLocaleCommander(fightSituationEntity.getLocaleCommander());
                target.setLocaleDeputyCommander(fightSituationEntity.getLocaleDeputyCommander());
                target.setDispatcher(fightSituationEntity.getDispatcher());
                target.setLocaleCorrespondent(fightSituationEntity.getLocaleCorrespondent());
                target.setDetachmentCorrespondent(fightSituationEntity.getDetachmentCorrespondent());
                target.setSquadronCommander(fightSituationEntity.getSquadronCommander());
                target.setSquadronCorrespondent(fightSituationEntity.getSquadronCorrespondent());
                target.setVideographer(fightSituationEntity.getVideographer());
                target.setCombatProcessDesc(fightSituationEntity.getCombatProcessDesc());
            }

            return target;
        }
        return null;
    }


    /**
     * 警情文书 转换
     *
     * @param source 警情文书PO
     * @return 警情文书BO
     */
    public static DocumentBean transform(DocumentEntity source, Map<String, String> organizationNameMap, Map<String, Map<String, String>> dicsMap) {
        if (null != source) {
            DocumentBean target = new DocumentBean();
            target.setId(source.getId());
            target.setIncidentId(source.getIncidentId());
            target.setDateSourceId(source.getDateSourceId());
            target.setType(source.getType());
            if(!StringUtils.isBlank(target.getType())&&dicsMap.get("WSLX")!=null){
                target.setTypeName(dicsMap.get("WSLX").get(target.getType()));
            }


            if (DOCUMENT_TYPE_JQZT.getCode().equals(source.getType())) {
                target.setTypeSubitemCode(source.getTypeSubitemCode());
                if(!StringUtils.isBlank(target.getTypeSubitemCode())&&dicsMap.get("AJZT")!=null){
                    target.setTypeSubitemName(dicsMap.get("AJZT").get(target.getTypeSubitemCode()));
                }


            }
            target.setTitle(source.getTitle());
            target.setContent(source.getContent());
            target.setRecordTime(source.getRecordTime());
            target.setFeedbackTime(source.getFeedbackTime());
            target.setFeedbackPerson(source.getFeedbackPerson());
            target.setFeedbackOrganization(source.getFeedbackOrganization());
            target.setFeedbackOrganizationId(source.getFeedbackOrganizationId());
            target.setFeedbackOrganizationName(source.getFeedbackOrganizationName());
            target.setTerminalId(source.getTerminalId());
            target.setRemarks(source.getRemarks());

            target.setWhetherUpdate(source.getWhetherUpdate());
            return target;
        }
        return null;
    }

    /**
     * 警情文书 转换
     *
     * @param source 警情文书保存参数INPUT
     * @return 警情文书PO
     */
    public static DocumentEntity transform(DocumentSaveInputInfo source) {
        if (null != source) {
            DocumentEntity target = new DocumentEntity();
            target.setId(source.getId());
            target.setIncidentId(source.getIncidentId());
            target.setDateSourceId(source.getDateSourceId());
            target.setType(source.getType());
            target.setTypeSubitemCode(source.getTypeSubitemCode());
            target.setTitle(source.getTitle());
            target.setContent(source.getContent());
            target.setFeedbackTime(source.getFeedbackTime());
            target.setFeedbackPerson(source.getFeedbackPerson());
            target.setFeedbackOrganization(source.getFeedbackOrganizationId());
            target.setFeedbackOrganizationId(source.getFeedbackOrganizationId());
            target.setTerminalId(source.getTerminalId());
            target.setRemarks(source.getRemarks());
            target.setWhetherUpdate(source.getWhetherUpdate());
            return target;
        }
        return null;
    }


    /**
     * 车辆状态与案件状态影响 转换
     *
     * @param source 车辆状态与案件状态影响PO
     * @return 车辆状态与案件状态影响BO
     */
    public static VehicleIncidentStatusMappingBean transform(VehicleIncidentStatusMappingEntity source, Map<String, Map<String, String>> dicsMap) {
        if (null != source) {
            VehicleIncidentStatusMappingBean target = new VehicleIncidentStatusMappingBean();
            target.setId(source.getId());
            target.setIncidentTypeCode(source.getIncidentTypeCode());
            if(!StringUtils.isBlank(source.getIncidentTypeCode())&&dicsMap.get("AJLX")!=null){
                target.setIncidentTypeName(dicsMap.get("AJLX").get(source.getIncidentTypeCode()));
            }


            target.setVehicleStatusCode(source.getVehicleStatusCode());
            if(!StringUtils.isBlank(source.getVehicleStatusCode())&&dicsMap.get("WLCLZT")!=null){
                target.setVehicleStatusName(dicsMap.get("WLCLZT").get(source.getVehicleStatusCode()));
            }


            target.setIncidentStatusCode(source.getIncidentStatusCode());
            if(!StringUtils.isBlank(source.getIncidentStatusCode())&&dicsMap.get("AJZT")!=null){
                target.setIncidentStatusName(dicsMap.get("AJZT").get(source.getIncidentStatusCode()));
            }


            target.setInfluenceMode(source.getInfluenceMode());
            target.setRemarks(source.getRemarks());
            return target;
        }
        return null;
    }

    /**
     * 中队填报 转换
     *
     * @param source 中队填报保存参数INPUT
     * @return 中队填报PO
     */
    public static SquadronFillInBean transform(SquadronFillInEntity source, Map<String, String> organizationNameMap) {
        if (null != source) {
            SquadronFillInBean target = new SquadronFillInBean();
            target.setId(source.getId());
            target.setIncidentId(source.getIncidentId());
            target.setWriteOrganizationId(source.getWriteOrganizationId());
            if(!StringUtils.isBlank(source.getWriteOrganizationId())&&organizationNameMap.get(source.getWriteOrganizationId())!=null){
                target.setWriteOrganizationName(organizationNameMap.get(source.getWriteOrganizationId()));
            }


            target.setDispatchWaterGunNumber(source.getDispatchWaterGunNumber());
            target.setDeathNumber(source.getDeathNumber());
            target.setInjuredNumber(source.getInjuredNumber());
            target.setRescueNumber(source.getRescueNumber());
            target.setWriteTime(source.getWriteTime());
            target.setRemarks(source.getRemarks());
            return target;
        }
        return null;
    }


    /**
     * 中队填报 转换
     *
     * @param source 中队填报PO
     * @return 中队填报BO
     */
    public static SquadronFillInEntity transform(SquadronFillInSaveInputInfo source) {
        if (null != source) {
            SquadronFillInEntity target = new SquadronFillInEntity();
            target.setId(source.getId());
            target.setIncidentId(source.getIncidentId());
            target.setWriteOrganizationId(source.getWriteOrganizationId());
            target.setDispatchWaterGunNumber(source.getDispatchWaterGunNumber());
            target.setDeathNumber(source.getDeathNumber());
            target.setInjuredNumber(source.getInjuredNumber());
            target.setRescueNumber(source.getRescueNumber());
            target.setWriteTime(source.getWriteTime());
            target.setRemarks(source.getRemarks());
            return target;
        }
        return null;
    }


    /**
     * 转换工具，把指令单Bean转换成指令单Entity
     *
     * @param source 参数，指令单bean
     * @return 返回转换后的结果
     */
    public static InstructionEntity transform(InstructionSaveInputInfo source) {
        if (null != source) {
            InstructionEntity target = new InstructionEntity();
            target.setIncidentId(source.getIncidentId());
            target.setCommandId(source.getCommandId());
            target.setSuperiorInstructionId(source.getSuperiorInstructionId());
            target.setInstructionsType(source.getInstructionsType());
            target.setInstructionsOrganizationId(source.getInstructionsOrganizationId());
            target.setInstructionsOrganizationName(source.getInstructionsOrganizationName());
            target.setInstructionsPersonId(source.getInstructionsPersonId());
            target.setInstructionsPersonName(source.getInstructionsPersonName());
            target.setInstructionsPersonRank(source.getInstructionsPersonRank());
            target.setInstructionsPersonRankName(source.getInstructionsPersonRankName());
            target.setInstructionsPersonArriveTime(source.getInstructionsPersonArriveTime());
            target.setInstructionsPersonBackTime(source.getInstructionsPersonBackTime());
            target.setInstructionsSource(source.getInstructionsSource());
            target.setInstructionsMode(source.getInstructionsMode());
            target.setInstructionTitle(source.getInstructionTitle());
            target.setInstructionsContent(source.getInstructionsContent());
            target.setInstructionsContentExtension(source.getInstructionsContentExtension());
            target.setRemarks(source.getRemarks());

            target.setInstructionTypeCode(source.getInstructionTypeCode());
            target.setInstructionCategoryCode(source.getInstructionCategoryCode());

            return target;
        }
        return null;
    }


    /**
     * 转换工具，把指令单Bean转换成指令单Entity
     *
     * @param source 参数，指令单bean
     * @return 返回转换后的结果
     */
    public static InstructionBean transform(InstructionEntity source, Map<String, Map<String, String>> dicsMap) {
        if (null != source) {
            InstructionBean target = new InstructionBean();
            target.setId(source.getId());
            target.setIncidentId(source.getIncidentId());
            target.setCommandId(source.getCommandId());
            target.setSuperiorInstructionId(source.getSuperiorInstructionId());
            target.setInstructionsType(source.getInstructionsType());
            target.setInstructionsOrganizationId(source.getInstructionsOrganizationId());
            target.setInstructionsOrganizationName(source.getInstructionsOrganizationName());
            target.setInstructionsPersonId(source.getInstructionsPersonId());
            target.setInstructionsPersonName(source.getInstructionsPersonName());
            target.setInstructionsPersonRank(source.getInstructionsPersonRank());
            target.setInstructionsPersonRankName(source.getInstructionsPersonRankName());
            target.setInstructionsPersonArriveTime(source.getInstructionsPersonArriveTime());
            target.setInstructionsPersonBackTime(source.getInstructionsPersonBackTime());
            target.setInstructionsSource(source.getInstructionsSource());
            target.setInstructionsTime(source.getInstructionsTime());
            target.setInstructionsMode(source.getInstructionsMode());
            target.setInstructionTitle(source.getInstructionTitle());
            target.setInstructionsContent(source.getInstructionsContent());
            target.setInstructionsContentExtension(source.getInstructionsContentExtension());
            target.setRemarks(source.getRemarks());

            target.setInstructionTypeCode(source.getInstructionTypeCode());
            if(!StringUtils.isBlank(source.getInstructionTypeCode())&&dicsMap.get("ZLLX")!=null){
                target.setInstructionTypeName(dicsMap.get("ZLLX").get(source.getInstructionTypeCode()));
            }


            target.setInstructionCategoryCode(source.getInstructionCategoryCode());
            if(!StringUtils.isBlank(source.getInstructionCategoryCode())&&dicsMap.get("ZLLB")!=null){
                target.setInstructionCategoryName(dicsMap.get("ZLLB").get(source.getInstructionCategoryCode()));
            }




            return target;
        }
        return null;
    }


    /**
     * 转换工具 指令记录表bean转entity
     *
     * @return 返回指令记录entity
     */
    public static InstructionRecordEntity transform(InstructionRecordSaveInputInfo source, String incidentId, String commandId, String instructionId) {
        if (null != source) {
            InstructionRecordEntity target = new InstructionRecordEntity();
            target.setIncidentId(incidentId);
            target.setCommandId(commandId);
            target.setInstructionId(instructionId);
            target.setReceivingObjectId(source.getReceivingObjectId());
            target.setReceivingObjectName(source.getReceivingObjectName());
            target.setReceivingObjectType(source.getReceivingObjectType());
            target.setInstructState(STATUS_GIVEN.getCode());
            target.setTimeout(source.getTimeout());
            target.setRemarks(source.getRemarks());

            target.setReceivingObjectOrganizationId(source.getReceivingObjectOrganizationId());
            target.setReceivingObjectOrganizationName(source.getReceivingObjectOrganizationName());
            target.setHandleSign(EnableEnum.ENABLE_FALSE.getCode());

            return target;
        }
        return null;


    }

    /**
     * 转换工具 指令记录表bean转entity
     *
     * @return 返回指令记录entity
     */
    public static InstructionRecordBean transform(InstructionRecordEntity source, Map<String, Map<String, String>> dicsMap) {
        if (null != source) {
            InstructionRecordBean target = new InstructionRecordBean();
            target.setId(source.getId());
            target.setIncidentId(source.getIncidentId());
            target.setCommandId(source.getCommandId());
            target.setInstructionId(source.getInstructionId());
            target.setReceivingObjectId(source.getReceivingObjectId());
            target.setReceivingObjectName(source.getReceivingObjectName());
            target.setReceivingObjectType(source.getReceivingObjectType());
            target.setInstructState(source.getInstructState());
            target.setInstructionRecordTime(source.getInstructionRecordTime());
            target.setReceivingTime(source.getReceivingTime());
            target.setTimeout(source.getTimeout());
            target.setRemarks(source.getRemarks());

            target.setReceivingObjectOrganizationId(source.getReceivingObjectOrganizationId());
            target.setReceivingObjectOrganizationName(source.getReceivingObjectOrganizationName());

            target.setHandleSign(source.getHandleSign());
            target.setHandleResultDesc(source.getHandleResultDesc());
            target.setHandlePersonName(source.getHandlePersonName());
            target.setHandleTime(source.getHandleTime());

            return target;
        }
        return null;


    }


    public static CaseAutoUpdateWarnEntity transform(CaseAutoUpdateWarnInputInfo source) {
        if (null != source) {
            CaseAutoUpdateWarnEntity target = new CaseAutoUpdateWarnEntity();
            target.setCaseType(source.getCaseType());
            target.setWarnType(source.getWarnType());
            target.setMinimum(source.getMinimum());
            target.setMaximum(source.getMaximum());
            target.setDisposalObjectCode(source.getDisposalObjectCode());
            target.setIncidentLevelCode(source.getIncidentLevelCode());
            target.setId(source.getId());
            target.setDescribe(source.getDescribe());
            return target;
        }
        return null;
    }

    public static CaseAutoUpdateWarnBean transform(CaseAutoUpdateWarnEntity source) {
        if (null != source) {
            CaseAutoUpdateWarnBean target = new CaseAutoUpdateWarnBean();
            target.setCaseType(source.getCaseType());
            target.setWarnType(source.getWarnType());
            target.setMinimum(source.getMinimum());
            target.setMaximum(source.getMaximum());
            target.setDisposalObjectCode(source.getDisposalObjectCode());
            target.setIncidentLevelCode(source.getIncidentLevelCode());
            target.setId(source.getId());
            target.setDescribe(source.getDescribe());
            return target;
        }
        return null;
    }

    public static List<CaseAutoUpdateWarnBean> transform(
            List<CaseAutoUpdateWarnEntity> source, Map<String,
            Map<String, String>> dicsMap,
            Map<String, String> keyUnitIdNameMap
    ) {
        if (!Collections.isEmpty(source)) {
            List<CaseAutoUpdateWarnBean> targets = new ArrayList<>();
            CaseAutoUpdateWarnBean target;
            for (CaseAutoUpdateWarnEntity entity : source) {
                target = new CaseAutoUpdateWarnBean();
                target.setCaseType(entity.getCaseType());
                if(!StringUtils.isBlank(entity.getCaseType())&&dicsMap.get("AJLX")!=null){
                    target.setCaseTypeName(dicsMap.get("AJLX").get(entity.getCaseType()));
                }

                if(!StringUtils.isBlank(entity.getIncidentLevelCode())&&dicsMap.get("AJDJ")!=null){
                    target.setIncidentLevelCodeName(dicsMap.get("AJDJ").get(entity.getIncidentLevelCode()));
                }


                if (entity.getWarnType().equals(WarnTypeBakEnum.WARN_TYPE_ZDDW.getCode())) {

                    if(!StringUtils.isBlank(entity.getDisposalObjectCode())&&keyUnitIdNameMap.get(entity.getDisposalObjectCode())!=null){
                        target.setDisposalObjectCodeName(keyUnitIdNameMap.get(entity.getDisposalObjectCode()));
                    }


                } else {

                    if(!StringUtils.isBlank(entity.getDisposalObjectCode())&&dicsMap.get("CZDX")!=null){
                        target.setDisposalObjectCodeName(dicsMap.get("CZDX").get(entity.getDisposalObjectCode()));
                    }


                }
                target.setWarnTypeName(WarnTypeBakEnum.getEnumName(entity.getWarnType()));
                target.setWarnType(entity.getWarnType());
                target.setMinimum(entity.getMinimum());
                target.setMaximum(entity.getMaximum());
                target.setDisposalObjectCode(entity.getDisposalObjectCode());
                target.setIncidentLevelCode(entity.getIncidentLevelCode());
                target.setId(entity.getId());
                target.setCreatedTime(entity.getCreatedTime());
                target.setUpdatedTime(entity.getUpdatedTime());
                target.setDescribe(entity.getDescribe());
                targets.add(target);

            }
            return targets;
        }
        return new ArrayList<>();
    }


    public static CaseAutoUpdateWarnBean transform(CaseAutoUpdateWarnEntity source, Map<String,
            Map<String, String>> dicsMap, KeyUnitSimpleBean keyUnit) {
        if (source != null) {
            CaseAutoUpdateWarnBean target = new CaseAutoUpdateWarnBean();
            target = new CaseAutoUpdateWarnBean();
            target.setCaseType(source.getCaseType());
            if(!StringUtils.isBlank(source.getCaseType())&&dicsMap.get("AJLX")!=null){
                target.setCaseTypeName(dicsMap.get("AJLX").get(source.getCaseType()));
            }

            if(!StringUtils.isBlank(source.getIncidentLevelCode())&&dicsMap.get("AJDJ")!=null){
                target.setIncidentLevelCodeName(dicsMap.get("AJDJ").get(source.getIncidentLevelCode()));
            }


            if (source.getWarnType().equals(WarnTypeBakEnum.WARN_TYPE_ZDDW.getCode())) {
                //重点单位对象
                if (keyUnit != null) {
                    target.setDisposalObjectCodeName(keyUnit.getUnitName());
                }

            } else {
                if(!StringUtils.isBlank(source.getDisposalObjectCode())&&dicsMap.get("CZDX")!=null){
                    target.setDisposalObjectCodeName(dicsMap.get("CZDX").get(source.getDisposalObjectCode()));
                }


            }
            target.setWarnTypeName(WarnTypeBakEnum.getEnumName(source.getWarnType()));
            target.setWarnType(source.getWarnType());
            target.setMinimum(source.getMinimum());
            target.setMaximum(source.getMaximum());
            target.setDisposalObjectCode(source.getDisposalObjectCode());
            target.setIncidentLevelCode(source.getIncidentLevelCode());
            target.setId(source.getId());
            target.setCreatedTime(source.getCreatedTime());
            target.setUpdatedTime(source.getUpdatedTime());
            target.setDescribe(source.getDescribe());
        }
        return null;
    }


    /**
     * 微站调派 转换
     *
     * @param source 处警信息INPUTINFO
     * @return 处处警信息PO
     */
    public static HandleMiniatureStationEntity transform(HandleMiniatureStationSaveInputInfo source, String incidentId, String handleId) {
        if (null != source) {
            HandleMiniatureStationEntity target = new HandleMiniatureStationEntity();
            target.setIncidentId(incidentId);
            target.setOriginalIncidentNumber(incidentId);
            target.setHandleId(handleId);
            target.setHandleType(source.getHandleType());
            target.setHandleWay(source.getHandleWay());
            target.setHandleObjectId(source.getHandleObjectId());
            target.setHandleObjectName(source.getHandleObjectName());
            target.setDistrictCode(source.getDistrictCode());
            target.setOrganizationId(source.getOrganizationId());
            target.setRelayRecordNumber(source.getRelayRecordNumber());
            target.setHandleContent(source.getHandleContent());
            target.setHandleStatus(STATUS_GIVEN.getCode());


            return target;
        }
        return null;
    }


    /**
     * 微站调派 转换
     *
     * @param source 处警信息INPUTINFO
     * @return 处处警信息PO
     */
    public static HandleMiniatureStationBean transform(HandleMiniatureStationEntity source, Map<String,
            Map<String, String>> dicsMap, Map<String, String> organizationNameMap) {
        if(organizationNameMap==null){
            organizationNameMap=new HashMap<>();
        }

        if (null != source) {
            HandleMiniatureStationBean target = new HandleMiniatureStationBean();
            target.setId(source.getId());
            target.setIncidentId(source.getIncidentId());
            target.setHandleId(source.getHandleId());
            target.setHandleType(source.getHandleType());
            if(!StringUtils.isBlank(source.getHandleType())&&dicsMap.get("DPDXLX")!=null){
                target.setHandleTypeName(dicsMap.get("DPDXLX").get(source.getHandleType()));
            }


            target.setHandleWay(source.getHandleWay());
            if(!StringUtils.isBlank(source.getHandleWay())&&dicsMap.get("DPFS")!=null){
                target.setHandleWayName(dicsMap.get("DPFS").get(source.getHandleWay()));
            }


            target.setHandleObjectId(source.getHandleObjectId());
            target.setHandleObjectName(source.getHandleObjectName());
            target.setDistrictCode(source.getDistrictCode());
            if(!StringUtils.isBlank(source.getDistrictCode())&&dicsMap.get("XZQX")!=null){
                target.setDistrictCode(dicsMap.get("XZQX").get(source.getDistrictCode()));
            }


            target.setOrganizationId(source.getOrganizationId());
            if(!StringUtils.isBlank(source.getOrganizationId())){
                target.setOrganizationName(organizationNameMap.get(source.getOrganizationId()));
            }


            target.setRelayRecordNumber(source.getRelayRecordNumber());
            target.setHandleContent(source.getHandleContent());
            target.setHandleStatus(source.getHandleStatus());
            if(!StringUtils.isBlank(source.getHandleStatus())&&dicsMap.get("ZLZT")!=null){
                target.setHandleStatusName(dicsMap.get("ZLZT").get(source.getHandleStatus()));
            }


            target.setHandleTime(source.getHandleTime());

            target.setHandleOrganizationId(source.getHandleOrganizationId());
            if(!StringUtils.isBlank(source.getHandleOrganizationId())){
                target.setHandleOrganizationName(organizationNameMap.get(source.getHandleOrganizationId()));
            }


            target.setHandlePersonNumber(source.getHandlePersonNumber());
            target.setHandleSeatNumber(source.getHandleSeatNumber());

            target.setLatestFeedbackTime(source.getLatestFeedbackTime());

            return target;
        }
        return null;
    }


    /**
     * 微站调派 反馈  转换
     *
     * @param source INPUTINFO
     * @return PO
     */
    public static HandleMiniatureStationFeedbackEntity transform(HandleMiniatureStationFeedbackSaveInputInfo source) {
        if (null != source) {
            HandleMiniatureStationFeedbackEntity target = new HandleMiniatureStationFeedbackEntity();
            target.setIncidentId(source.getIncidentId());
            target.setHandleId(source.getHandleId());
            target.setHandleMiniatureStationId(source.getHandleMiniatureStationId());
            target.setFeedbackTime(source.getFeedbackTime());
            target.setLocaleDesc(source.getLocaleDesc());
            return target;
        }
        return null;
    }


    /**
     * 微站调派 反馈 转换
     *
     * @param source INPUTINFO
     * @return PO
     */
    public static HandleMiniatureStationFeedbackBean transform(HandleMiniatureStationFeedbackEntity source) {
        if (null != source) {
            HandleMiniatureStationFeedbackBean target = new HandleMiniatureStationFeedbackBean();
            target.setId(source.getId());
            target.setIncidentId(source.getIncidentId());
            target.setHandleId(source.getHandleId());
            target.setHandleMiniatureStationId(source.getHandleMiniatureStationId());
            target.setFeedbackTime(source.getFeedbackTime());
            target.setLocaleDesc(source.getLocaleDesc());

            return target;
        }
        return null;
    }

    /**
     * 定位记录转换
     */
    public static LocationRecordEntity transform(LocationRecordInputInfo inputInfo) {
        LocationRecordEntity locationRecordEntity = new LocationRecordEntity();
        if (null != inputInfo) {
            locationRecordEntity.setAddress(inputInfo.getAddress());
            locationRecordEntity.setGis_x(inputInfo.getGis_x());
            locationRecordEntity.setGis_y(inputInfo.getGis_y());
            locationRecordEntity.setIncidentId(inputInfo.getIncidentId());
            locationRecordEntity.setLocationTime(inputInfo.getLocationTime());
            locationRecordEntity.setLocationType(inputInfo.getLocationType());
            locationRecordEntity.setRemark(inputInfo.getRemark());
            locationRecordEntity.setSort(inputInfo.getSort());
            locationRecordEntity.setAcquisitionFlag(inputInfo.getAcquisitionFlag());
            locationRecordEntity.setQuickPicture(inputInfo.getQuickPicture());
        }
        return locationRecordEntity;
    }

    /**
     * 定位记录转换
     */
    public static LocationRecordBean transform(LocationRecordEntity entity) {
        LocationRecordBean locationRecordBean = new LocationRecordBean();
        if (null != entity) {
            locationRecordBean.setId(entity.getId());
            locationRecordBean.setAddress(entity.getAddress());
            locationRecordBean.setGis_x(entity.getGis_x());
            locationRecordBean.setGis_y(entity.getGis_y());
            locationRecordBean.setIncidentId(entity.getIncidentId());
            locationRecordBean.setLocationTime(entity.getLocationTime());
            locationRecordBean.setLocationType(entity.getLocationType());
            locationRecordBean.setRemark(entity.getRemark());
            locationRecordBean.setSort(entity.getSort());
            locationRecordBean.setCreatedTime(entity.getCreatedTime());
            locationRecordBean.setUpdatedTime(entity.getUpdatedTime());
            locationRecordBean.setAcquisitionFlag(entity.getAcquisitionFlag());
            EnumBean enumBean = BasicEnumUtils.getEnumBeanByCode(LocationTypeEnum.class, entity.getLocationType());
            if (enumBean != null) {
                locationRecordBean.setLocationTypeName(enumBean.getName());
            }
            locationRecordBean.setQuickPicture(entity.getQuickPicture());

        }
        return locationRecordBean;
    }


}
