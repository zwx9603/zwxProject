/**
 * 
 */
package com.dscomm.ecs.gateway.dsnetcomm.service.xml;

import org.dom4j.Element;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DS21通信协议XML中的源/目标地址定义
 * 
 * @author Josh Peng
 * 
 */
public class CKAlarm implements Serializable, IXmlSupportedBean {
	private static final long serialVersionUID = 872022045177703812L;

	private String SeqNum = "", CKId = "", AlarmTime = "",
		UserName = "", UserAddr = "", UserPhone = "",
		UserAccount = "", SectorId = "", SectorAddr = "",
		AlarmDes = "", UnitPersonName = "", UnitPersonMobile = "",
		PoliceStation = "", PoliceStationPhone = "", OtherPhone = "",
		AreaSign = "", AlarmType = "", MsgContent = "";
	private int PosX = 0;
	private int PosY = 0;

	/**
	 * 默认的构造函数
	 */
	public CKAlarm() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dscomm.dsnetcomm.bean.IXmlSupportedBean#fromXml(org.dom4j.Element)
	 */
	public void fromXml(Element element) {
		setSeqNum(element.attributeValue("SeqNum", ""));
		setCKId(element.attributeValue("CKId", ""));
		setAlarmTime(element.attributeValue("AlarmTime", ""));
		setUserName(element.attributeValue("UserName", ""));
		setUserAddr(element.attributeValue("UserAddr", ""));
		setUserPhone(element.attributeValue("UserPhone", ""));
		setUserAccount(element.attributeValue("UserAccount", ""));
		setSectorId(element.attributeValue("SectorId", ""));
		setSectorAddr(element.attributeValue("SectorAddr", ""));
		setAlarmDes(element.attributeValue("AlarmDes", ""));
		setUnitPersonName(element.attributeValue("UnitPersonName", ""));
		setUnitPersonMobile(element.attributeValue("UnitPersonMobile", ""));
		setPoliceStation(element.attributeValue("PoliceStation", ""));
		setPoliceStationPhone(element.attributeValue("PoliceStationPhone", ""));
		setOtherPhone(element.attributeValue("OtherPhone", ""));
		setAreaSign(element.attributeValue("AreaSign", ""));
		setAlarmType(element.attributeValue("AlarmType", ""));
//		setPosX(element.attributeValue("PosX", 0));
//		setPosY(element.attributeValue("PosY", 0));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dscomm.dsnetcomm.bean.IXmlSupportedBean#toXml(org.dom4j.Element)
	 */
	public Element toXml(Element element) {
		element.addAttribute("SeqNum", getSeqNum());
		element.addAttribute("CKId", getCKId());
		element.addAttribute("AlarmTime", getAlarmTime());
		element.addAttribute("UserName", getUserName());
		element.addAttribute("UserAddr", getUserAddr());
		element.addAttribute("UserPhone", getUserPhone());
		element.addAttribute("UserAccount", getUserAccount());
		element.addAttribute("SectorId", getSectorId());
		element.addAttribute("SectorAddr", getSectorAddr());
		element.addAttribute("AlarmDes", getAlarmDes());
		element.addAttribute("UnitPersonName", getUnitPersonName());
		element.addAttribute("UnitPersonMobile", getUnitPersonMobile());
		element.addAttribute("PoliceStation", getPoliceStation());
		element.addAttribute("PoliceStationPhone", getPoliceStationPhone());
		element.addAttribute("OtherPhone", getOtherPhone());
		element.addAttribute("AreaSign", getAreaSign());
		element.addAttribute("PosX", "0");
		element.addAttribute("PosY", "0");
		element.addAttribute("AlarmType", getAlarmType());
		element.addAttribute("MsgContent", getMsgContent());
		return element;
	}

	public String getSeqNum() {
		return SeqNum;
	}

	public void setSeqNum(String seqNum) {
		SeqNum = seqNum;
	}

	public String getCKId() {
		return CKId;
	}

	public void setCKId(String CKId) {
		this.CKId = CKId;
	}

	public String getAlarmTime() {
		return AlarmTime;
	}

	public void setAlarmTime(String alarmTime) {
		AlarmTime = alarmTime;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getUserAddr() {
		return UserAddr;
	}

	public void setUserAddr(String userAddr) {
		UserAddr = userAddr;
	}

	public String getUserPhone() {
		return UserPhone;
	}

	public void setUserPhone(String userPhone) {
		UserPhone = userPhone;
	}

	public String getUserAccount() {
		return UserAccount;
	}

	public void setUserAccount(String userAccount) {
		UserAccount = userAccount;
	}

	public String getSectorId() {
		return SectorId;
	}

	public void setSectorId(String sectorId) {
		SectorId = sectorId;
	}

	public String getSectorAddr() {
		return SectorAddr;
	}

	public void setSectorAddr(String sectorAddr) {
		SectorAddr = sectorAddr;
	}

	public String getAlarmDes() {
		return AlarmDes;
	}

	public void setAlarmDes(String alarmDes) {
		AlarmDes = alarmDes;
	}

	public String getUnitPersonName() {
		return UnitPersonName;
	}

	public void setUnitPersonName(String unitPersonName) {
		UnitPersonName = unitPersonName;
	}

	public String getUnitPersonMobile() {
		return UnitPersonMobile;
	}

	public void setUnitPersonMobile(String unitPersonMobile) {
		UnitPersonMobile = unitPersonMobile;
	}

	public String getPoliceStation() {
		return PoliceStation;
	}

	public void setPoliceStation(String policeStation) {
		PoliceStation = policeStation;
	}

	public String getPoliceStationPhone() {
		return PoliceStationPhone;
	}

	public void setPoliceStationPhone(String policeStationPhone) {
		PoliceStationPhone = policeStationPhone;
	}

	public String getOtherPhone() {
		return OtherPhone;
	}

	public void setOtherPhone(String otherPhone) {
		OtherPhone = otherPhone;
	}

	public String getAreaSign() {
		return AreaSign;
	}

	public void setAreaSign(String areaSign) {
		AreaSign = areaSign;
	}

	public String getAlarmType() {
		return AlarmType;
	}

	public void setAlarmType(String alarmType) {
		AlarmType = alarmType;
	}

	public String getMsgContent() {
		return MsgContent;
	}

	public void setMsgContent(String msgContent) {
		MsgContent = msgContent;
	}

	public int getPosX() {
		return PosX;
	}

	public void setPosX(int posX) {
		PosX = posX;
	}

	public int getPosY() {
		return PosY;
	}

	public void setPosY(int posY) {
		PosY = posY;
	}
}
