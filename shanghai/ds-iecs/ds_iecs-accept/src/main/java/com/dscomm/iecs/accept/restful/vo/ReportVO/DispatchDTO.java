package com.dscomm.iecs.accept.restful.vo.ReportVO;

import java.util.List;

/**
 * 调派单上报VO
 * */
public class DispatchDTO {

    private CaseInfoDTO caseinfo ; //警情信息

    private List<DispatchRecordDTO> recordDTOs ; //调派记录

    private List<DispatchInfoDTO>  infoDTOs ;//调派信息

    private List<DispatchVehicleDTO> vehicleDTOs ;//调派车辆

    private List<DispatchEquipmentDTO> equipmentDTOs ;//调派装备

    public CaseInfoDTO getCaseinfo() {
        return caseinfo;
    }

    public void setCaseinfo(CaseInfoDTO caseinfo) {
        this.caseinfo = caseinfo;
    }

    public List<DispatchRecordDTO> getRecordDTOs() {
        return recordDTOs;
    }

    public void setRecordDTOs(List<DispatchRecordDTO> recordDTOs) {
        this.recordDTOs = recordDTOs;
    }

    public List<DispatchInfoDTO> getInfoDTOs() {
        return infoDTOs;
    }

    public void setInfoDTOs(List<DispatchInfoDTO> infoDTOs) {
        this.infoDTOs = infoDTOs;
    }

    public List<DispatchVehicleDTO> getVehicleDTOs() {
        return vehicleDTOs;
    }

    public void setVehicleDTOs(List<DispatchVehicleDTO> vehicleDTOs) {
        this.vehicleDTOs = vehicleDTOs;
    }

    public List<DispatchEquipmentDTO> getEquipmentDTOs() {
        return equipmentDTOs;
    }

    public void setEquipmentDTOs(List<DispatchEquipmentDTO> equipmentDTOs) {
        this.equipmentDTOs = equipmentDTOs;
    }
}
