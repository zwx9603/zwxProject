package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述： 警情文书表
 *
 */
@Entity
@Table(name = "JCJ_WSXX")
@DynamicInsert(true)
@DynamicUpdate(true)
public class DocumentEntity extends BaseEntity {

	@Column(name = "JQHCWS_TYWYSBM", length = 40)
	private String idCode; //todo  字段  警情火场文书_通用唯一识别码

//    @Column(name = "AJID", length = 100 )
	@Column(name = "JQ_TYWYSBM", length = 100 )
    private String incidentId;//todo 字段 案件ID（警情_通用唯一识别码）

	@Column(name = "SJLYID", length = 100 )
	private String dateSourceId ; //数据来源id

//	@Column(name = "LX", length = 100 )
	@Column(name = "JQWSZLLBDM", length = 100 )
	private String type;//todo 字段 文书类型 警情文书种类类别代码

	@Column(name = "JQWSZLZXDM", length = 100 )
	private String typeSubitemCode;//todo 字段  警情文书种类状态代码

//	@Column(name = "BT", length = 200 )
	@Column(name = "BT_MC", length = 200 )
	private String title ; //todo  字段 标题

//	@Column(name = "WSNR", length = 4000)
	@Column(name = "WSNR_JYQK", length = 4000)
	private String content;//todo  字段 文书内容（简要概况）

//	@Column(name = "WSSJ")
	@Column(name = "JL_RQSJ")
	private Long recordTime;//todo  字段 记录文书时间 记录_日期时间

	@Column(name = "FK_RQSJ"  )
	private Long feedbackTime;//todo  字段  反馈_日期时间 （页面显示 用户可修改）

//    @Column(name = "FKR", length = 100 )
	@Column(name = "JLR_XM", length = 100 )
    private String feedbackPerson;//todo  字段 反馈人姓名

	@Column(name = "FKJG", length = 100)
	private String feedbackOrganization;//todo  字段 反馈消防机构 反馈机构

//	@Column(name = "FKXFJGBM", length = 100)
	@Column(name = "FKJG_TYWYSBM", length = 100)
	private String feedbackOrganizationId;//todo  字段 反馈消防机构代码 反馈机构 通用唯一识别码

	@Column(name = "FKJG_DWMC", length = 200)
	private String feedbackOrganizationName;//todo  字段 反馈消防机构名称 反馈机构 单位名称

    @Column(name = "PDAID" , length =  100 )
    private String terminalId ;// 终端ID

	@Column(name = "BZ", length = 800)
	private String remarks;//备注

	@Column(name = "SFKXG" )
	private Integer whetherUpdate   ; //是否可以修改 0 不可以修改 1 可以修改 默认不可以修改

	public String getDateSourceId() {
		return dateSourceId;
	}

	public void setDateSourceId(String dateSourceId) {
		this.dateSourceId = dateSourceId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getWhetherUpdate() {
		return whetherUpdate;
	}

	public void setWhetherUpdate(Integer whetherUpdate) {
		this.whetherUpdate = whetherUpdate;
	}

	public String getIncidentId() {
		return incidentId;
	}

	public void setIncidentId(String incidentId) {
		this.incidentId = incidentId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Long recordTime) {
		this.recordTime = recordTime;
	}


	public String getFeedbackPerson() {
		return feedbackPerson;
	}

	public void setFeedbackPerson(String feedbackPerson) {
		this.feedbackPerson = feedbackPerson;
	}

	public String getFeedbackOrganizationId() {
		return feedbackOrganizationId;
	}

	public void setFeedbackOrganizationId(String feedbackOrganizationId) {
		this.feedbackOrganizationId = feedbackOrganizationId;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getIdCode() {
		return idCode;
	}

	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}

	public Long getFeedbackTime() {
		return feedbackTime;
	}

	public void setFeedbackTime(Long feedbackTime) {
		this.feedbackTime = feedbackTime;
	}

	public String getFeedbackOrganization() {
		return feedbackOrganization;
	}

	public void setFeedbackOrganization(String feedbackOrganization) {
		this.feedbackOrganization = feedbackOrganization;
	}

	public String getFeedbackOrganizationName() {
		return feedbackOrganizationName;
	}

	public void setFeedbackOrganizationName(String feedbackOrganizationName) {
		this.feedbackOrganizationName = feedbackOrganizationName;
	}

	public String getTypeSubitemCode() {
		return typeSubitemCode;
	}

	public void setTypeSubitemCode(String typeSubitemCode) {
		this.typeSubitemCode = typeSubitemCode;
	}
}
