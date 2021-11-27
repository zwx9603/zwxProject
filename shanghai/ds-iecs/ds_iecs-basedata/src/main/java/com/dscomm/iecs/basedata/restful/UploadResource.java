package com.dscomm.iecs.basedata.restful;


import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.exception.BasedataException;
import com.dscomm.iecs.basedata.graphql.inputbean.AttachmentSaveInputInfo;
import com.dscomm.iecs.basedata.graphql.typebean.AttachmentBean;
import com.dscomm.iecs.basedata.graphql.typebean.FdfsDataBean;
import com.dscomm.iecs.basedata.service.AttachmentService;
import com.dscomm.iecs.basedata.service.UploadService;
import org.apache.logging.log4j.util.Strings;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.mx.service.rest.vo.DataVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;

@Path("iecs/v1.0/upload")
public class UploadResource {

    private static final Logger logger = LoggerFactory.getLogger(UploadResource.class);

    private LogService logService;
    private UploadService uploadService ;
    private AttachmentService  attachmentService  ;

    @Autowired
    public UploadResource(LogService logService, UploadService uploadService , AttachmentService  attachmentService   ) {
        this.logService = logService ;
        this.uploadService = uploadService ;
        this.attachmentService = attachmentService ;
    }

    /**
     *  上传附件信息
     *
     */
    @Path("fastdfs/upload_file")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public DataVO< AttachmentBean > uploadFile(
            @FormDataParam("file") final InputStream uploadedInputStream,
            @FormDataParam("file") final FormDataContentDisposition fileDetail ,
             @QueryParam("incidentId") String incidentId
             ) {
        try {
            if (Strings.isBlank(incidentId)) {
                throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
            }

            logService.infoLog(logger, "restful", "uploadFile", "restful is started...");
            Long start = System.currentTimeMillis();

            String source = fileDetail.getFileName();
            String fileName = new String(source.getBytes("ISO8859-1"), "UTF-8");


            logService.infoLog(logger, "fdfs", "uploadFile", "restful is started...");
            Long startfdfs = System.currentTimeMillis();

            FdfsDataBean fdfsDataBean = uploadService.uploadFile( uploadedInputStream , fileName ) ;

            Long endfdfs = System.currentTimeMillis();
            logService.infoLog(logger, "fdfs", "uploadFile", String.format("restful is finished,execute time is :%sms", endfdfs - startfdfs));



            //将附件信息  保存
            AttachmentSaveInputInfo queryBean = new AttachmentSaveInputInfo() ;
            queryBean.setId( fdfsDataBean.getId() );
            queryBean.setIncidentId( incidentId );
            queryBean.setAttachmentFileName( fdfsDataBean.getFileName()  );
            queryBean.setAttachmentFileUrl( fdfsDataBean.getPath() ); //
            queryBean.setAttachmentFileType( fdfsDataBean.getType() );
            queryBean.setAttachmentType( fdfsDataBean.getType() );
            queryBean.setAttachmentFileDesc( null );
            queryBean.setRemarks( null );
            AttachmentBean res =  attachmentService.saveAttachment(queryBean) ;

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "restful", "uploadFile", String.format("restful is finished,execute time is :%sms", end - start));

            return new DataVO<>(res);
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "uploadFile", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.UPLOAD_FILE_FAIL);
        }
    }
}
