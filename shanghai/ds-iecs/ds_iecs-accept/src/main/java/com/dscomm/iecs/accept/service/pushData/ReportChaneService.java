package com.dscomm.iecs.accept.service.pushData;

import com.dscomm.iecs.accept.graphql.typebean.*;

import java.util.List;

/**一体化上服务*/
public interface ReportChaneService {

    /**
     * 新增警情上报
     * */
    Boolean newCase(IncidentBean incidentBean);

    /**
     * 修改警情上报
     * */
    Boolean modifyCase(IncidentBean incidentBean);

    /**
     * 警情状态修改上报
     * */
    Boolean caseStat(IncidentStatusChangeBean incidentStatusChangeBean);

    /**
     * 警情合并上上报
     * */
    Boolean caseMerger(IncidentMergeBean incidentMergeBean);

    /**
     * 处警单上报
     * */
    Boolean dispatchVehicle(IncidentBean incidentBean, List<HandleBean> handleBeans);

    /**
     * 录音号上报
     * */
    Boolean record( SoundRecordBean soundRecordBean );

    /**
     * 火场文书上报
     * */
    Boolean fireDocument(DocumentBean documentBean);

    /**
     * 现场信息上报
     * */
    Boolean sceneInfo(LocaleBean localeBean);



}
