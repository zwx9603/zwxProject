package com.dscomm.iecs.accept.service.impl;


import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.typebean.DocumentBean;
import com.dscomm.iecs.accept.service.DocumentService;
import com.dscomm.iecs.accept.service.IncidentGuidanceService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.graphql.typebean.AttachmentBean;
import com.dscomm.iecs.basedata.service.AttachmentService;
import org.apache.logging.log4j.util.Strings;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author xushaohuang
 * @date 2021-05-20 13:34
 * @description
 */
@Component("IncidentGuidanceServiceImpl")
public class IncidentGuidanceServiceImpl implements IncidentGuidanceService {

    private static final Logger logger = LoggerFactory.getLogger(NewCircularServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private DocumentService documentService;
    private AttachmentService attachmentService;

    @Autowired
    public IncidentGuidanceServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor,
                                       DocumentService documentService, AttachmentService attachmentService) {
        this.logService = logService;
        this.accessor = accessor;
        this.documentService = documentService;
        this.attachmentService = attachmentService;
    }

    /**
     * 根据案件id获取文书和附件信息
     *
     * @param incidentId
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public List<DocumentBean> findIncidentGuidanceInfo(String incidentId) {
        if (Strings.isBlank(incidentId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveInstruction", "service is started...");
            Long logStart = System.currentTimeMillis();

            logService.infoLog(logger, "repository", "save( minimumUnitEntity )", "repository is started...");
            Long startInstruction = System.currentTimeMillis();

            List<DocumentBean> documents = documentService.findDocument(incidentId, null, "300");

            Long endInstruction = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save( minimumUnitEntity )", String.format("repository is finished,execute time is :%sms", endInstruction - startInstruction));

            if (documents.size() > 0 && documents != null) {
                for (DocumentBean document : documents) {
                    List<AttachmentBean> attachment = attachmentService.findAttachment(document.getIncidentId(), document.getId());
                    document.setAttachmentBeans(attachment);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveInstruction", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return documents;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveInstruction", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DATA_FAIL);
        }
    }
}
