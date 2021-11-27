package com.dscomm.iecs.integrate.service;

import com.dscomm.iecs.integrate.restful.vo.DispatchDTO;
import com.dscomm.iecs.integrate.restful.vo.FireDocumentDTO;
import com.dscomm.iecs.integrate.restful.vo.RecordDTO;
import com.dscomm.iecs.integrate.restful.vo.SceneInfoDTO;

public interface HandleReportService {

    /**
     * 调派单上报
     * */
    Boolean dispatchVehicle(DispatchDTO dispatchDTO);

    /**
     * 录音号上报
     *
     * @return*/
    Boolean record(RecordDTO recordDTO);

    /**
     * 现场信息上报
     * */
    Boolean sceneInfo(SceneInfoDTO sceneInfoDTO);


    /**
     * 火场文书上报
     * */
    Boolean fireDocument(FireDocumentDTO fireDocumentDTO);

}
