package com.dscomm.iecs.accept.graphql.typebean;


import com.dscomm.iecs.basedata.graphql.typebean.AttachmentBean;

import java.util.List;

/**
 * 描述 : 警情卷宗

 */
public class IncidentDossierBean {

    private String incidentId; //警情id

    private IncidentBean incidentBean; //警情详情

    private List<IncidentBean> relatedIncidentBeanList; //关联警情

    private AcceptanceInformationBean acceptanceInformationBean ; //受理单记录

    private List<HandleBean> handleBeanList; //处警记录

    private List<InstructionBean> sceneInstructionBeanList; //  现场指挥 ( 指令单)

    private AccidentBean accidentBean; //结案信息

    private List<LocaleBean> localeBeanList; //现场信息

    private List<AttachmentBean> attachmentBeanList; //附件

//    private TelephoneInformationBean telephoneInformationBean; //电话报警信息（仅仅包含报警信息）

//    private List<DocumentBean> documentBeanList; //警情文书

//    private List<EarlyWarningBean> earlyWarningBeanList; //预警信息

//    private List<ParticipantFeedbackBean> participantFeedbackBeanList; //参战人员反馈
//
//    private List<FireSafetyMonitoringBean> fireSafetyMonitoringBeanList; //火场出入记录


    public AcceptanceInformationBean getAcceptanceInformationBean() {
        return acceptanceInformationBean;
    }

    public void setAcceptanceInformationBean(AcceptanceInformationBean acceptanceInformationBean) {
        this.acceptanceInformationBean = acceptanceInformationBean;
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

    public List<IncidentBean> getRelatedIncidentBeanList() {
        return relatedIncidentBeanList;
    }

    public void setRelatedIncidentBeanList(List<IncidentBean> relatedIncidentBeanList) {
        this.relatedIncidentBeanList = relatedIncidentBeanList;
    }

    public List<HandleBean> getHandleBeanList() {
        return handleBeanList;
    }

    public void setHandleBeanList(List<HandleBean> handleBeanList) {
        this.handleBeanList = handleBeanList;
    }

    public AccidentBean getAccidentBean() {
        return accidentBean;
    }

    public void setAccidentBean(AccidentBean accidentBean) {
        this.accidentBean = accidentBean;
    }

    public List<LocaleBean> getLocaleBeanList() {
        return localeBeanList;
    }

    public void setLocaleBeanList(List<LocaleBean> localeBeanList) {
        this.localeBeanList = localeBeanList;
    }

    public List<AttachmentBean> getAttachmentBeanList() {
        return attachmentBeanList;
    }

    public void setAttachmentBeanList(List<AttachmentBean> attachmentBeanList) {
        this.attachmentBeanList = attachmentBeanList;
    }

    public List<InstructionBean> getSceneInstructionBeanList() {
        return sceneInstructionBeanList;
    }

    public void setSceneInstructionBeanList(List<InstructionBean> sceneInstructionBeanList) {
        this.sceneInstructionBeanList = sceneInstructionBeanList;
    }

//    public TelephoneInformationBean getTelephoneInformationBean() {
//        return telephoneInformationBean;
//    }
//
//    public void setTelephoneInformationBean(TelephoneInformationBean telephoneInformationBean) {
//        this.telephoneInformationBean = telephoneInformationBean;
//    }
//
//    public List<ParticipantFeedbackBean> getParticipantFeedbackBeanList() {
//        return participantFeedbackBeanList;
//    }
//
//    public void setParticipantFeedbackBeanList(List<ParticipantFeedbackBean> participantFeedbackBeanList) {
//        this.participantFeedbackBeanList = participantFeedbackBeanList;
//    }
//
//    public List<FireSafetyMonitoringBean> getFireSafetyMonitoringBeanList() {
//        return fireSafetyMonitoringBeanList;
//    }
//
//    public void setFireSafetyMonitoringBeanList(List<FireSafetyMonitoringBean> fireSafetyMonitoringBeanList) {
//        this.fireSafetyMonitoringBeanList = fireSafetyMonitoringBeanList;
//    }
//
//    public List<EarlyWarningBean> getEarlyWarningBeanList() {
//        return earlyWarningBeanList;
//    }
//
//    public void setEarlyWarningBeanList(List<EarlyWarningBean> earlyWarningBeanList) {
//        this.earlyWarningBeanList = earlyWarningBeanList;
//    }
//
//    public List<DocumentBean> getDocumentBeanList() {
//        return documentBeanList;
//    }
//
//    public void setDocumentBeanList(List<DocumentBean> documentBeanList) {
//        this.documentBeanList = documentBeanList;
//    }

}
