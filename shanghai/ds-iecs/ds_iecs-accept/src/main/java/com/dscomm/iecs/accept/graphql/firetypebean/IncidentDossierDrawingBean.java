package com.dscomm.iecs.accept.graphql.firetypebean;

import com.dscomm.iecs.accept.graphql.typebean.*;
import com.dscomm.iecs.basedata.graphql.typebean.AttachmentBean;
import com.dscomm.iecs.basedata.graphql.typebean.KeyUnitBean;

import java.util.List;

public class IncidentDossierDrawingBean {
    private String incidentId; //事件id

    private IncidentBean incidentBean; //警情详情

    private KeyUnitBean keyUnitBean;    //重点单位信息

    private TelephoneInformationBean telephoneInformationBean ; //电话报警信息

    private List<HandleOrganizationVehicleBean> handleOrganizationVehicleBeanList ;    //根据案件id获得车辆信息

    private List<CommanderBean> commanderBeanList;  //指挥员信息

    private List<SecurityBean> securityHintsBeanList; //特别警示

    private List<AttachmentBean> attachmentBeanList; //附件

    private List<RallyPointBean> rallyPoint ;  //任务部署记录

    private List<InstructionBean> approvals;//批示

    private  List<InstructionBean> askExpertBeanList;   //咨询专家

    private List<InstructionBean> askAndReportBeanList ;//请示汇报

    private List<InstructionBean> instructionBeanList;//下达指令

    private List<InstructionBean> notifiedBeanList;//通报

    private List<InstructionBean> contactBeanList;//联系

    private List<DocumentBean> documentBeanList;//火场文书列表

    public List<RallyPointBean> getRallyPoint() {
        return rallyPoint;
    }

    public void setRallyPoint(List<RallyPointBean> rallyPoint) {
        this.rallyPoint = rallyPoint;
    }

    public List<CommanderBean> getCommanderBeanList() {
        return commanderBeanList;
    }

    public void setCommanderBeanList(List<CommanderBean> commanderBeanList) {
        this.commanderBeanList = commanderBeanList;
    }

    public KeyUnitBean getKeyUnitBean() {
        return keyUnitBean;
    }

    public void setKeyUnitBean(KeyUnitBean keyUnitBean) {
        this.keyUnitBean = keyUnitBean;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public IncidentBean getIncidentBean() {
        return incidentBean;
    }

    public void setIncidentBean(IncidentBean incidentBean) {
        this.incidentBean = incidentBean;
    }

    public TelephoneInformationBean getTelephoneInformationBean() {
        return telephoneInformationBean;
    }

    public void setTelephoneInformationBean(TelephoneInformationBean telephoneInformationBean) {
        this.telephoneInformationBean = telephoneInformationBean;
    }

    public List<HandleOrganizationVehicleBean> getHandleOrganizationVehicleBeanList() {
        return handleOrganizationVehicleBeanList;
    }

    public void setHandleOrganizationVehicleBeanList(List<HandleOrganizationVehicleBean> handleOrganizationVehicleBeanList) {
        this.handleOrganizationVehicleBeanList = handleOrganizationVehicleBeanList;
    }

    public List<SecurityBean> getSecurityHintsBeanList() {
        return securityHintsBeanList;
    }

    public void setSecurityHintsBeanList(List<SecurityBean> securityHintsBeanList) {
        this.securityHintsBeanList = securityHintsBeanList;
    }

    public List<InstructionBean> getApprovals() {
        return approvals;
    }

    public void setApprovals(List<InstructionBean> approvals) {
        this.approvals = approvals;
    }

    public List<InstructionBean> getAskExpertBeanList() {
        return askExpertBeanList;
    }

    public void setAskExpertBeanList(List<InstructionBean> askExpertBeanList) {
        this.askExpertBeanList = askExpertBeanList;
    }

    public List<InstructionBean> getAskAndReportBeanList() {
        return askAndReportBeanList;
    }

    public void setAskAndReportBeanList(List<InstructionBean> askAndReportBeanList) {
        this.askAndReportBeanList = askAndReportBeanList;
    }

    public List<InstructionBean> getInstructionBeanList() {
        return instructionBeanList;
    }

    public void setInstructionBeanList(List<InstructionBean> instructionBeanList) {
        this.instructionBeanList = instructionBeanList;
    }

    public List<InstructionBean> getNotifiedBeanList() {
        return notifiedBeanList;
    }

    public void setNotifiedBeanList(List<InstructionBean> notifiedBeanList) {
        this.notifiedBeanList = notifiedBeanList;
    }

    public List<InstructionBean> getContactBeanList() {
        return contactBeanList;
    }

    public void setContactBeanList(List<InstructionBean> contactBeanList) {
        this.contactBeanList = contactBeanList;
    }


    public List<DocumentBean> getDocumentBeanList() {
        return documentBeanList;
    }

    public void setDocumentBeanList(List<DocumentBean> documentBeanList) {
        this.documentBeanList = documentBeanList;
    }

    public List<AttachmentBean> getAttachmentBeanList() {
        return attachmentBeanList;
    }

    public void setAttachmentBeanList(List<AttachmentBean> attachmentBeanList) {
        this.attachmentBeanList = attachmentBeanList;
    }
}

