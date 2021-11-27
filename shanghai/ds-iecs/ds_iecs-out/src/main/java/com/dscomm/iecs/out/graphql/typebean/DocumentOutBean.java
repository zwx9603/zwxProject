package com.dscomm.iecs.out.graphql.typebean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.persistence.Column;
import java.util.List;
@Data
public class DocumentOutBean {
    @JSONField(name = "jqhcwsTywysbm")
    private String idCode;//警情火场文书_通用唯一识别码
    @JSONField(name = "jqTywysbm")
    private String incidentId;//警情_通用唯一识别码
    @JSONField(name = "jqwszllbdm")
    private String type;//警情文书种类类别代码
    @JSONField(name = "btMc")
    private String title ; //标题_名称
    @JSONField(name = "jlrXm")
    private String recordPerson;//记录人姓名
    @JSONField(name = "wsnrJyqk")
    private String content;//文书内容_简要情况
    @JSONField(name = "jlRqsj")
    private String recordTime;//记录日期时间
    @JSONField(name = "fkRqsj")
    private String feedbackTime;//  反馈_日期时间
    @JSONField(name = "fkrXm")
    private String feedbackPerson;//反馈人姓名
    @JSONField(name = "fkjgTywysbm")
    private String feedbackOrganizationId;//通用唯一识别码
    @JSONField(name = "fkjgDwmc")
    private String feedbackOrganizationName;//单位名称
    @JSONField(name = "fjTywysbm")
    private String id;//通用唯一识别码
    @JSONField(name = "fjDzwjlxdm")
    private String  attachmentFileType;   //电子文件类型代码
    @JSONField(name = "fjMc")
    private String  attachmentFileName;   // 名称
    @JSONField(name = "fjDzwjwz")
    private String  attachmentFileUrl;   // 电子文件位置
    @JSONField(name = "uid")
    private String uid;
    @JSONField(name = "createTime")
    private String createTime;
    @JSONField(name = "deptCode")
    private String deptCode;//单位code
}
