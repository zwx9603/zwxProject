package com.dscomm.iecs.integrate.restful;

import com.alibaba.fastjson.JSON;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.integrate.exception.IntegrateException;
import com.dscomm.iecs.integrate.restful.vo.*;
import com.dscomm.iecs.integrate.service.HandleReportService;
import com.dscomm.iecs.integrate.service.IncidentReportService;
import org.apache.logging.log4j.util.Strings;
import org.mx.StringUtils;
import org.mx.service.rest.vo.DataVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**一体化上报接口*/
@Path("DataReport")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReportResource {

    private static final Logger logger = LoggerFactory.getLogger(UdpResource.class);
    private LogService logService;

    private IncidentReportService incidentReportService;
    private HandleReportService handleReportService;

    @Autowired
    public ReportResource(IncidentReportService incidentReportService, LogService logService , HandleReportService handleReportService) {
        this.logService = logService ;
        this.incidentReportService = incidentReportService;
        this.handleReportService = handleReportService;
    }

    /**
     * 新增警情上报
     * */
    @Path("NewCase")
    @POST
    public DataVO<Boolean> newCase (CaseInfoDTO caseInfoDTO){
        if (  null == caseInfoDTO || Strings.isBlank( caseInfoDTO.getAJID() )) {
            throw new IntegrateException(IntegrateException.IntegrateErrors.DATA_NULL);
        }
        logService.infoLog(logger, "restful", "NewCase", "NewCase :" + JSON.toJSONString( caseInfoDTO ) );

        Boolean res = incidentReportService.newCase(caseInfoDTO);
        return new DataVO<>(res);
    }

    /**
     * 修改警情上报
     * */
    @Path("ModifyCase")
    @POST
    public DataVO<Boolean> modifyCase (CaseInfoDTO caseInfoDTO){
        if (  null == caseInfoDTO || Strings.isBlank( caseInfoDTO.getAJID() )) {
            throw new IntegrateException(IntegrateException.IntegrateErrors.DATA_NULL);
        }
        logService.infoLog(logger, "restful", "ModifyCase", "ModifyCase:" + JSON.toJSONString( caseInfoDTO ) );

        Boolean res = incidentReportService.modifyCase(caseInfoDTO);
        return new DataVO<>(res);
    }

    /**
     * 案件状态修改上报
     * */
    @Path("CaseStat")
    @POST
    public DataVO<Boolean> caseStat (CaseStatDTO caseStatDTO){
        if (  null == caseStatDTO || Strings.isBlank( caseStatDTO.getAJID() )) {
            throw new IntegrateException(IntegrateException.IntegrateErrors.DATA_NULL);
        }
        logService.infoLog(logger, "restful", "CaseStat", "CaseStat:" + JSON.toJSONString( caseStatDTO ) );

        Boolean res = incidentReportService.caseStat(caseStatDTO);
        return new DataVO<>(res);
    }

    /**
     * 案件合并上报
     * */
    @Path("CaseMerger")
    @POST
    public DataVO<Boolean> caseMerger (CaseMergeDTO caseMergeDTO){
        if (  null == caseMergeDTO || Strings.isBlank( caseMergeDTO.getCaseid() )) {
            throw new IntegrateException(IntegrateException.IntegrateErrors.DATA_NULL);
        }
        logService.infoLog(logger, "restful", "CaseMerge", "CaseMerger:" + JSON.toJSONString( caseMergeDTO ) );

        Boolean res = incidentReportService.caseMerger(caseMergeDTO);
        return new DataVO<>(res);
    }

    /**
     * 处警单上报
     * */
    @Path("DispatchVehicle")
    @POST
    public DataVO<Boolean> dispatchVehicle (DispatchDTO dispatchDTO){
        if (  null == dispatchDTO) {
            throw new IntegrateException(IntegrateException.IntegrateErrors.DATA_NULL);
        }
        logService.infoLog(logger, "restful", "DispatchVehicle", "DispatchVehicle:" + JSON.toJSONString( dispatchDTO ) );

        Boolean res = handleReportService.dispatchVehicle(dispatchDTO);
        return new DataVO<>(res);
    }

    /**
     * 录音号上报
     * */
    @Path("Record")
    @POST
    public DataVO<Boolean> record (RecordDTO recordDTO){
        if (  null == recordDTO || StringUtils.isBlank(recordDTO.getLYBH())) {
            throw new IntegrateException(IntegrateException.IntegrateErrors.DATA_NULL);
        }
        logService.infoLog(logger, "restful", "Record", "Record:" + JSON.toJSONString( recordDTO ) );

        Boolean res = handleReportService.record(recordDTO);
        return new DataVO<>(res);
    }

    /**
     * 现场信息上报
     * */
    @Path("SceneInfo")
    @POST
    public DataVO<Boolean> sceneInfo (SceneInfoDTO sceneInfoDTO){
        if (  null == sceneInfoDTO || StringUtils.isBlank(sceneInfoDTO.getID())) {
            throw new IntegrateException(IntegrateException.IntegrateErrors.DATA_NULL);
        }
        logService.infoLog(logger, "restful", "SceneInfo", "SceneInfo:" + JSON.toJSONString( sceneInfoDTO ) );

        Boolean res = handleReportService.sceneInfo(sceneInfoDTO);
        return new DataVO<>(res);
    }

    /**
     * 火场文书上报
     * */
    @Path("FireDocument")
    @POST
    public DataVO<Boolean> fireDocument (FireDocumentDTO fireDocumentDTO){
        if (  null == fireDocumentDTO || StringUtils.isBlank(fireDocumentDTO.getWSID())) {
            throw new IntegrateException(IntegrateException.IntegrateErrors.DATA_NULL);
        }
        logService.infoLog(logger, "restful", "FireDocument", "FireDocument:" + JSON.toJSONString( fireDocumentDTO ) );

        Boolean res = handleReportService.fireDocument(fireDocumentDTO);
        return new DataVO<>(res);
    }


}
