package com.dscomm.iecs.accept.service.bean.cad;



import java.util.List;

/**
 * 描述 : 警情卷宗
 *
 * @author : ZhaiYanTao
 * Date Time : 2019/10/19 18:46
 */
public class IncidentDossierVO {
    /**
     * 事件id
     */
    private String incidentId;
    /**
     * 警情详情
     */
    private IncidentInfoBO incidentInfo;
    /**
     * 受理记录信息
     */
    private List<AcceptBO> acceptList;
    /**
     * 警情补充 --- 补充时保存的受理单
     */
    private List<AcceptBO> incidentSupplement;
    /**
     * 关联警情
     */
    private List<IncidentInfoBO> relatedIncidentInfoList;
    /**
     * 处警单{...指令单{...反馈单}}列表
     */
    private List<DispatchBO> dispatchList;

    /**
     * 资源
     */
    private List<String> source;
    /**
     * 复打警情
     */
    private List<IncidentInfoBO> repeatCallIncidentInfoList;

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public IncidentInfoBO getIncidentInfo() {
        return incidentInfo;
    }

    public void setIncidentInfo(IncidentInfoBO incidentInfo) {
        this.incidentInfo = incidentInfo;
    }

    public List<AcceptBO> getAcceptList() {
        return acceptList;
    }

    public void setAcceptList(List<AcceptBO> acceptList) {
        this.acceptList = acceptList;
    }

    public List<AcceptBO> getIncidentSupplement() {
        return incidentSupplement;
    }

    public void setIncidentSupplement(List<AcceptBO> incidentSupplement) {
        this.incidentSupplement = incidentSupplement;
    }

    public List<IncidentInfoBO> getRelatedIncidentInfoList() {
        return relatedIncidentInfoList;
    }

    public void setRelatedIncidentInfoList(List<IncidentInfoBO> relatedIncidentInfoList) {
        this.relatedIncidentInfoList = relatedIncidentInfoList;
    }

    public List<DispatchBO> getDispatchList() {
        return dispatchList;
    }

    public void setDispatchList(List<DispatchBO> dispatchList) {
        this.dispatchList = dispatchList;
    }



    public List<String> getSource() {
        return source;
    }

    public void setSource(List<String> source) {
        this.source = source;
    }

    public List<IncidentInfoBO> getRepeatCallIncidentInfoList() { return repeatCallIncidentInfoList; }

    public void setRepeatCallIncidentInfoList(List<IncidentInfoBO> repeatCallIncidentInfoList) { this.repeatCallIncidentInfoList = repeatCallIncidentInfoList; }
}
