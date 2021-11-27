package com.dscomm.iecs.accept.utils.transform;

import com.dscomm.iecs.accept.dal.po.IncidentEntity;
import com.dscomm.iecs.accept.graphql.typebean.*;
import com.dscomm.iecs.accept.restful.vo.IncidentCocVO;
import com.dscomm.iecs.accept.restful.vo.ReportVO.*;
import com.dscomm.iecs.base.utils.DateHandleUtils;
import com.dscomm.iecs.ext.incident.nature.INCIDENT_NATURE_CWJJ;
import com.dscomm.iecs.ext.incident.nature.INCIDENT_NATURE_JJ;
import com.dscomm.iecs.ext.incident.nature.INCIDENT_NATURE_SCJJ;
import com.dscomm.iecs.ext.incident.nature.INCIDENT_NATURE_ZJ;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 一体化上报转换类
 * */
public class ReportTransformUtil {

    /**
     * 警情信息转换
     * */
    public  static CaseInfoDTO transform ( String timeZoneId , IncidentBean source) throws ParseException  {
        if (source != null){
            CaseInfoDTO vo = new CaseInfoDTO();
            vo.setAFDZ(source.getCrimeAddress());
            vo.setAJBH(source.getIncidentNumber());
            vo.setAJDJDM(source.getIncidentLevelCode());
            vo.setAJID(source.getId());
            vo.setAJMS(source.getIncidentDescribe());
            vo.setAJLXDM(source.getIncidentTypeCode());
            vo.setAJZTDM(source.getIncidentStatusCode());
//            vo.setBDSSRS(source.getInjuredCount());
//            vo.setBDSWRS(source.getDeathCount());
            vo.setBJFS(source.getAlarmModeCode());
            vo.setBJRXM(source.getAlarmPersonName());
            if (source.getAlarmStartTime() != null){
                vo.setBJSJ(   DateHandleUtils.getDateyyyyMMddHHmmss( timeZoneId  ,source.getAlarmStartTime() )    );
            }
            vo.setBSXFJGDM(source.getRegisterOrganizationId());
            vo.setBZ(source.getRemarks());
            vo.setCZDXDM(source.getDisposalObjectCode());
            vo.setGIS_H(source.getHeight());
            vo.setGIS_X(source.getLongitude());
            vo.setGIS_Y(source.getLatitude());
            vo.setIFSB("1");
            vo.setJZJGLXDM(source.getBuildingStructureCode());
            if (source.getRegisterIncidentTime() != null){
                vo.setLASJ(   DateHandleUtils.getDateyyyyMMddHHmmss( timeZoneId  ,source.getRegisterIncidentTime() )    );
            }
            vo.setLXDH(source.getAlarmPersonPhone());
            vo.setLXR(source.getAlarmPersonName());
            vo.setRSLC(source.getBurningFloor());
            vo.setRSMJ(source.getBurningArea());
            vo.setRYBGS(source.getTrappedCount());
            vo.setSSRS(source.getInjuredCount());
            vo.setSWRS(source.getDeathCount());
            vo.setXZQYBH(source.getDistrictCode());
            vo.setZDDWID(source.getKeyUnitId());
            vo.setZDDWMC(source.getKeyUnitName());
            vo.setZGJGBH(source.getSquadronOrganizationId());
            vo.setZJHM(source.getAlarmPhone());
//            vo.setZQBS(source.getIncidentNatureCode());
            // 新标准 2 真警  1假警 3 错位接警 4 关联警情 9删除警情
            // 一体化灾情标识(真警:1、假警:0、错位接警:2 删除警情:9)
            if(  INCIDENT_NATURE_ZJ.getCode().equals( source.getIncidentLabel() )  ){
                vo.setZQBS( "1" );
            }else if(  INCIDENT_NATURE_JJ.getCode().equals( source.getIncidentLabel() )  ){
                vo.setZQBS( "0");
            }else if(  INCIDENT_NATURE_CWJJ.getCode().equals( source.getIncidentLabel() ) ){
                vo.setZQBS( "2" );
            }else if(  INCIDENT_NATURE_SCJJ.getCode().equals( source.getIncidentLabel() ) ){
                vo.setZQBS( "9" );
            }else{
                vo.setZQBS( "0");
            }
            vo.setZQBS( source.getIncidentLabel() );
            vo.setZQLYXFJGDM(source.getBrigadeOrganizationId());
            vo.setZJYHM(source.getAlarmPersonName());
            return vo;
        }
        return null;
    }

    /**
     * 警情状态改变转换
     * */
    public  static CaseStatDTO transform (  String timeZoneId , IncidentStatusChangeBean source) throws ParseException  {
        if (source != null){
            CaseStatDTO vo = new CaseStatDTO();
            vo.setAJID(source.getIncidentId());
            vo.setAJZT(source.getIncidentStatusCode());
            if (source.getChangeTime() != null){
                vo.setBDSJ(  DateHandleUtils.getDateyyyyMMddHHmmss( timeZoneId  ,source.getChangeTime() ) ) ;
            }
            return vo;
        }
        return null;

    }

    /**
     * 警情合并转换
     * */
    public static CaseMergeDTO transform(  IncidentMergeBean source)    {
        if (source != null){
            CaseMergeDTO vo = new CaseMergeDTO();
            List<ChildCaseIdDTO> caseIdVOS = new ArrayList<>();
            if (source.getMergeIncidents() != null && source.getMergeIncidents().size() > 0){
                for (IncidentBean bean:source.getMergeIncidents()){
                    ChildCaseIdDTO childCaseIdDTO = new ChildCaseIdDTO();
                    childCaseIdDTO.setChildid(bean.getId());
                    caseIdVOS.add(childCaseIdDTO);
                }
            }
            vo.setCaseid(source.getMainIncident().getId());
            vo.setChildCase(caseIdVOS);
            return vo;
        }
        return null;
    }

    /**
     * 火场文书转换
     * */
    public static FireDocumentDTO transform(  String timeZoneId , DocumentBean source) throws ParseException  {
        if (source != null){
            FireDocumentDTO vo = new FireDocumentDTO();
            vo.setAJID(source.getIncidentId());
            vo.setBZ(source.getRemarks());
            vo.setFKR(source.getFeedbackPerson());
            vo.setFKXFJG(source.getFeedbackOrganization());
            vo.setPDAID(source.getTerminalId());
            vo.setWSID(source.getId());
            vo.setWSNR(source.getContent());
            if (source.getRecordTime() != null){
                vo.setWSSJ(     DateHandleUtils.getDateyyyyMMddHHmmss( timeZoneId  ,source.getRecordTime() )    );
            }
            return vo;
        }
        return null;
    }

    /**
     * 录音信息转换
     * */
    public static RecordDTO transform(  String timeZoneId , SoundRecordBean source )throws ParseException  {
        if (source != null){
            RecordDTO vo = new RecordDTO();
            vo.setAJID(source.getIncidentId()  );
            vo.setLYBFDZ(source.getRecordUrl() );
            vo.setLYBH(source.getRecordNo());
            if( 2 == source.getType() ){ // 接警回拨电话
                vo.setTHHM(source.getCalledId() );
            }else{
                vo.setTHHM(source.getCallerId());
            }

            if (source.getBeginTime() != null){
                vo.setLYSJ(    DateHandleUtils.getDateyyyyMMddHHmmss( timeZoneId  ,source.getBeginTime() )    );
            }
            return vo;
        }
        return null;
    }

    /**
     * 现场信息转换
     * */
    public static SceneInfoDTO transform( String timeZoneId , LocaleBean source)throws ParseException  {
        if (source != null){
            SceneInfoDTO vo = new SceneInfoDTO();
            vo.setID(source.getId());
            vo.setAJID(source.getIncidentId());
            vo.setBDSSRS(source.getTroopsDeathNum());
            vo.setBDSWRS(source.getTroopsInjuredNum());
            vo.setBKRS(source.getTrappedPersonNum());
            vo.setBZ(source.getRemarks());
            if (source.getCollectionTime() != null){
                vo.setCJSJ(    DateHandleUtils.getDateyyyyMMddHHmmss( timeZoneId  ,source.getCollectionTime() )    );
            }
            vo.setDCRS(source.getAttendanceNum());
            vo.setFLDJ(source.getWindGrade());
            vo.setFX(source.getWindDirection());
            vo.setHCWD(source.getFireSitesTemperature());
            vo.setQW(source.getWeatherTemperature());
            vo.setQZSSRS(source.getMassInjuredNum());
            vo.setQZSWRS(source.getMassDeathNum());
            vo.setRSMJ(source.getBurningArea());
            vo.setTQDM(source.getWeatherInformationCode());
            vo.setXCMS(source.getLocaleDesc());
            vo.setXDSD(source.getRelativeHumidity());
            vo.setYWQKDM(source.getSmogSituationCode());
            vo.setZHDJDM(source.getDisasterGradeCode());
            return vo;
        }
        return null;
    }

    /**
     * 车辆调度转换
     * */
    public static DispatchVehicleDTO transform (HandleOrganizationVehicleBean source)  {
        if (source != null){
            DispatchVehicleDTO vo = new DispatchVehicleDTO();
            vo.setBZ(source.getRemarks());
            vo.setCDDID(source.getHandleId());
            vo.setCLID(source.getVehicleId());
            vo.setCLLXDM(source.getVehicleTypeCode());
            vo.setCLMC(source.getVehicleName());
            vo.setCPHM(source.getVehicleNumber());
            vo.setID(source.getId());
            vo.setXFJGDM(source.getOrganizationId());
            return  vo;
        }
        return  null;
    }

    /**
     * 装备调派转换
     * */
    public static DispatchEquipmentDTO transform(HandleOrganizationEquipmentBean source)   {
        if (source != null){
            DispatchEquipmentDTO vo = new DispatchEquipmentDTO();
            vo.setBZ(source.getRemarks());
            vo.setCDDID(source.getHandleId());
            vo.setDPSL(source.getDispatchNum().toString());
            vo.setQCLXDM(source.getEquipmentTypeCode());
            vo.setXFJGDM(source.getOrganizationId());
            return vo;
        }
        return null;
    }

    /**
     * 调派记录转换
     * */
    public static DispatchRecordDTO transform ( String timeZoneId , HandleBean source)throws ParseException  {
        if (source != null){
            DispatchRecordDTO vo = new DispatchRecordDTO();
            vo.setAJID(source.getIncidentId());
            vo.setBZ(source.getRemarks());
            vo.setDPFAID(source.getId());
            vo.setFSDWDM(source.getHandleOrganization());
            if (source.getHandleStartTime() != null){
                vo.setFSSJ( DateHandleUtils.getDateyyyyMMddHHmmss( timeZoneId  ,source.getHandleStartTime() )    );
            }
            if (source.getHandleEndTime() != null){
                vo.setJSSJ( DateHandleUtils.getDateyyyyMMddHHmmss( timeZoneId  ,source.getHandleEndTime() )    );
            }
            return vo;
        }
        return  null;
    }

    /**
     * 调派单转换
     * */
    public static List<DispatchInfoDTO> transformDispatchInfo(  String timeZoneId , HandleBean source)throws ParseException  {
        if (source != null){
            List<DispatchInfoDTO> dispatchInfoDTOS = new ArrayList<>();
            if (source.getHandleOrganizationBean() != null && source.getHandleOrganizationBean().size()>0){
                for (HandleOrganizationBean organizationBean : source.getHandleOrganizationBean()){
                    DispatchInfoDTO vo = new DispatchInfoDTO();
                    vo.setAJID(source.getIncidentId());
                    vo.setBZ(source.getRemarks());
                    vo.setCDDBM(source.getHandleNumber());
                    vo.setCDDID(source.getId());
                    vo.setCDRS(source.getHandlePersonNum().toString());
                    vo.setDPFAID(source.getId());
                    vo.setFSDWDM(source.getHandleOrganization());
                    if (source.getHandleStartTime() != null){
                        vo.setFSSJ(   DateHandleUtils.getDateyyyyMMddHHmmss( timeZoneId  ,source.getHandleStartTime() )    );
                    }
                    vo.setJSDWDM(organizationBean.getId());
                    dispatchInfoDTOS.add(vo);
                }
            }else {
                DispatchInfoDTO vo = new DispatchInfoDTO();
                vo.setAJID(source.getIncidentId());
                vo.setBZ(source.getRemarks());
                vo.setCDDBM(source.getHandleNumber());
                vo.setCDDID(source.getId());
                vo.setCDRS(source.getHandlePersonNum().toString());
                vo.setDPFAID(source.getId());
                vo.setFSDWDM(source.getHandleOrganization());
                if (source.getHandleStartTime() != null){
                    vo.setFSSJ(   DateHandleUtils.getDateyyyyMMddHHmmss( timeZoneId  ,source.getHandleStartTime() )    );
                }
                dispatchInfoDTOS.add(vo);
            }
            return dispatchInfoDTOS;
        }
        return null;
    }

    /**
     * 调派转换
     * */
    public static DispatchDTO transform( String timeZoneId , IncidentBean incidentBean, List<HandleBean> handleBeans)throws ParseException  {

        DispatchDTO dispatchDTO = new DispatchDTO();
        List<DispatchRecordDTO> dispatchRecordDTOS = new ArrayList<>();
        List<DispatchInfoDTO> dispatchInfoDTOS = new ArrayList<>();
        Map<String,HandleOrganizationVehicleBean> vehicleBeanMap = new HashMap<>();
        List<DispatchVehicleDTO> dispatchVehicleDTOS = new ArrayList<>();
        Map<String,HandleOrganizationEquipmentBean> equipmentBeanMap = new HashMap<>();
        List<DispatchEquipmentDTO> equipmentVOS = new ArrayList<>();

        /**转换警情*/
        CaseInfoDTO caseInfoDTO = transform( timeZoneId , incidentBean);

        if (handleBeans != null && handleBeans.size()>0){
            /**转换调派单，调派记录*/
            for (HandleBean handleBean:handleBeans){
                DispatchRecordDTO dispatchRecordDTO = transform( timeZoneId, handleBean);
                List<DispatchInfoDTO> infoVOS = transformDispatchInfo( timeZoneId, handleBean);
                if (dispatchRecordDTO != null){
                    dispatchRecordDTOS.add(dispatchRecordDTO);
                }
                if (infoVOS != null && infoVOS.size()>0){
                    dispatchInfoDTOS.addAll(infoVOS);
                }
                /**取出调派单中的派车单与派装备单*/
                if (handleBean.getHandleOrganizationBean() != null && handleBean.getHandleOrganizationBean().size()>0){
                    for (HandleOrganizationBean organizationBean:handleBean.getHandleOrganizationBean()){
                        if (organizationBean.getHandleOrganizationVehicleBean() != null && organizationBean.getHandleOrganizationVehicleBean().size()>0){
                            for (HandleOrganizationVehicleBean vehicleBean:organizationBean.getHandleOrganizationVehicleBean()){
                                vehicleBeanMap.put(vehicleBean.getId(),vehicleBean);
                            }
                        }
                        if (organizationBean.getHandleOrganizationEquipmentBean() != null && organizationBean.getHandleOrganizationEquipmentBean().size()>0){
                            for (HandleOrganizationEquipmentBean equipmentBean:organizationBean.getHandleOrganizationEquipmentBean()){
                                equipmentBeanMap.put(equipmentBean.getId(),equipmentBean);
                            }
                        }
                    }
                }
            }
        }

        /**转换派车*/
        if (incidentBean != null && incidentBean.getHandleOrganizationVehicleList() != null ){
            for (HandleOrganizationVehicleBean vehicleBean:incidentBean.getHandleOrganizationVehicleList()){
                vehicleBeanMap.put(vehicleBean.getId(),vehicleBean);
            }
        }

        if (!vehicleBeanMap.isEmpty()){
            for (String key:vehicleBeanMap.keySet()){
                DispatchVehicleDTO vehicleVO = transform(vehicleBeanMap.get(key));
                if (vehicleVO != null){
                    dispatchVehicleDTOS.add(vehicleVO);
                }
            }
        }
        /**转换装备*/
        if (!equipmentBeanMap.isEmpty()){
            for (String key:equipmentBeanMap.keySet()){
                DispatchEquipmentDTO equipmentVO = transform(equipmentBeanMap.get(key));
                if (equipmentVO != null){
                    equipmentVOS.add(equipmentVO);
                }
            }
        }

        /**setVO*/
        if (caseInfoDTO != null){
            dispatchDTO.setCaseinfo(caseInfoDTO);
        }
        if (dispatchRecordDTOS != null && dispatchInfoDTOS.size()>0){
            dispatchDTO.setRecordDTOs(dispatchRecordDTOS);
        }
        if (dispatchInfoDTOS != null && dispatchInfoDTOS.size()>0){
            dispatchDTO.setInfoDTOs(dispatchInfoDTOS);
        }
        if (dispatchVehicleDTOS != null && dispatchVehicleDTOS.size()>0){
            dispatchDTO.setVehicleDTOs(dispatchVehicleDTOS);
        }
        if (equipmentVOS != null && equipmentVOS.size()>0){
            dispatchDTO.setEquipmentDTOs(equipmentVOS);
        }
        return dispatchDTO;
    }



    public static IncidentCocVO transformCOC (IncidentBean bean){
        IncidentCocVO vo = new IncidentCocVO();
        if (null != bean ){
            vo.setIncidentId(bean.getId());
            vo.setIncidentAddress(bean.getCrimeAddress());
            vo.setLatitude(bean.getLatitude());
            vo.setLongitude(bean.getLongitude());
            vo.setIncidentType(bean.getIncidentTypeCode());
        }
        return vo;
    }

    public static IncidentCocVO transformCOC(IncidentEntity entity){
        IncidentCocVO vo = new IncidentCocVO();
        if (null != entity){
            vo.setIncidentType(entity.getIncidentNatureCode());
            vo.setIncidentId(entity.getId());
            vo.setIncidentAddress(entity.getCrimeAddress());
            vo.setLatitude(entity.getLatitude());
            vo.setLongitude(entity.getLongitude());
            vo.setIncidentType(entity.getIncidentTypeCode());
        }
        return vo;
    }


}
