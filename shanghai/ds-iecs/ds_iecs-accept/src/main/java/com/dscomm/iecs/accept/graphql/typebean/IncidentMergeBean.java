package com.dscomm.iecs.accept.graphql.typebean;


import java.util.List;


/**
 * 描述:案件合并信息
 *
 */
public class IncidentMergeBean  {

    private IncidentBean mainIncident ; //主案件信息

    private List<IncidentBean> mergeIncidents ; //合并案件信息

    public IncidentBean getMainIncident() {
        return mainIncident;
    }

    public void setMainIncident(IncidentBean mainIncident) {
        this.mainIncident = mainIncident;
    }

    public List<IncidentBean> getMergeIncidents() {
        return mergeIncidents;
    }

    public void setMergeIncidents(List<IncidentBean> mergeIncidents) {
        this.mergeIncidents = mergeIncidents;
    }
}
