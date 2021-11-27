package com.dscomm.iecs.accept.utils.transform;

import com.dscomm.iecs.accept.dal.po.DocumentEntity;
import com.dscomm.iecs.accept.graphql.inputbean.DocumentSaveInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.DocumentSaveInputInfoSH;
import com.dscomm.iecs.accept.graphql.typebean.DocumentBean;
import com.dscomm.iecs.accept.graphql.typebean.DocumentBeanSH;
import com.dscomm.iecs.ext.comm.document.DOCUMENT_TYPE_JQZT;

import java.util.Map;

public class DocumentSHTransformUtil {

    /**
     * 警情文书 转换
     *
     * @param source 警情文书PO
     * @return 警情文书BO
     */
    public static DocumentBeanSH transform(DocumentEntity source, Map<String, String> organizationNameMap, Map<String, Map<String, String>> dicsMap) {
        if (null != source) {
            DocumentBeanSH target = new DocumentBeanSH();
            target.setId(source.getId());
            target.setIncidentId(source.getIncidentId());
            target.setDateSourceId( source.getDateSourceId() );
            target.setType(source.getType());
            target.setTypeName(dicsMap.get("WSLX").get(target.getType()));
            if ( DOCUMENT_TYPE_JQZT.getCode().equals(source.getType())) {
                target.setTypeSubitemCode(source.getTypeSubitemCode());
                target.setTypeSubitemName(dicsMap.get("AJZT").get(target.getTypeSubitemCode()));
            }
            target.setTitle(source.getTitle());
            target.setContent(source.getContent());
            target.setRecordTime(source.getRecordTime());
            target.setFeedbackTime(source.getFeedbackTime());
            target.setFeedbackPerson(source.getFeedbackPerson());
            target.setFeedbackOrganization(source.getFeedbackOrganization());
            target.setFeedbackOrganizationId(source.getFeedbackOrganizationId());
            target.setFeedbackOrganizationName(source.getFeedbackOrganizationName());
            target.setTerminalId(source.getTerminalId());
            target.setRemarks(source.getRemarks());

            target.setWhetherUpdate(source.getWhetherUpdate());
            return target;
        }
        return null;
    }

    /**
     * 警情文书 转换
     *
     * @param source 警情文书保存参数INPUT
     * @return 警情文书PO
     */
    public static DocumentBean transform(DocumentBeanSH source) {
        if (null != source) {
            DocumentBean target = new DocumentBean();
            target.setId(source.getId());
            target.setIncidentId(source.getIncidentId());
            target.setDateSourceId( source.getDateSourceId() );
            target.setType(source.getType());
            target.setTypeName(source.getTypeName());
            target.setTypeSubitemCode(source.getTypeSubitemCode());
            target.setTypeSubitemName(source.getTypeSubitemName());
            target.setTitle(source.getTitle());
            target.setContent(source.getContent());
            target.setFeedbackTime(source.getFeedbackTime());
            target.setFeedbackPerson(source.getFeedbackPerson());
            target.setFeedbackOrganization(source.getFeedbackOrganizationId());
            target.setFeedbackOrganizationId(source.getFeedbackOrganizationId());
            target.setFeedbackOrganizationName(source.getFeedbackOrganizationName());
            target.setTerminalId(source.getTerminalId());
            target.setRemarks(source.getRemarks());
            target.setWhetherUpdate(source.getWhetherUpdate());
            target.setRecordTime(source.getRecordTime());
            return target;
        }
        return null;
    }

    /**
     * 警情文书 新类与原始类转换
     *
     * @param source 警情文书保存参数INPUT
     * @return 警情文书PO
     */
    public static DocumentEntity transform(DocumentSaveInputInfoSH source) {
        if (null != source) {
            DocumentEntity target = new DocumentEntity();
            target.setId(source.getId());
            target.setIncidentId(source.getIncidentId());
            target.setDateSourceId( source.getDateSourceId() );
            target.setType(source.getType());
            target.setTypeSubitemCode(source.getTypeSubitemCode());
            target.setTitle(source.getTitle());
            target.setContent(source.getContent());
            target.setFeedbackTime(source.getFeedbackTime());
            target.setFeedbackPerson(source.getFeedbackPerson());
            target.setFeedbackOrganization(source.getFeedbackOrganizationId());
            target.setFeedbackOrganizationId(source.getFeedbackOrganizationId());
            target.setTerminalId(source.getTerminalId());
            target.setRemarks(source.getRemarks());
            target.setWhetherUpdate(source.getWhetherUpdate());
            return target;
        }
        return null;
    }

}
