package com.dscomm.iecs.basedata.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述： 装备器材定义信息
 *
 */
@Entity
@Table(name = "WL_ZBQCDY")
@DynamicInsert(true)
@DynamicUpdate(true)
public class EquipmentDefinitionEntity extends BaseEntity {

    @Column(name = "ZBQCBM", length = 100)
    private String  equipmentCode;//装备器材编码

    @Column(name = "ZBQCMC", length = 200)
    private String  equipmentName;//装备器材名称

    @Column(name = "ZBQCLXDM", length = 100)
    private String  equipmentTypeCode ;//装备器材类型代码

    @Column(name = "ZBQCLXMC", length = 200)
    private String  equipmentTypeName;//装备器材类型名称

    @Column(name = "CLLXDM", length = 100)
    private String  vehicleTypeCode;//车辆类型代码

    @Column(name = "CLLXMC", length = 200)
    private String  vehicleTypeName;//车辆类型名称

    @Column(name = "SFXHXZB", length = 10)
    private String  whetherConsumptiveEquipment ;//是否消耗性装备

    @Column(name = "GGXH", length = 100)
    private String specificationsNumber;// 规格型号

    @Column(name = "JLDWDM", length = 100)
    private String measurementCode;// 计量单位代码

    @Column(name = "CKJ"  )
    private Float referencePrice ;// 参考价

    @Column(name = "ZL"  )
    private Float weight ;// 重量

    @Column(name = "TJ"  )
    private Float volume ;// 体积

    @Column(name = "BZH", length = 50 )
    private String proportion ;// 比重

    @Column(name = "JCPH", length = 50 )
    private String checkBatchNo ;// 检测批号

    @Column(name = "KSSJ", length = 50 )
    private String resistanceFire ;// 抗烧时间

    @Column(name = "ZYCF", length = 400 )
    private String mainComponent ;// 主要成分

    @Column(name = "SYFW", length = 400 )
    private String applicationRange ;// 适用范围

    @Column(name = "ZRJYLBDM", length = 100 )
    private String admittanceCheckCategoryCode ;// 准入检验类别代码

    @Column(name = "JKZBGNDLS", length = 800 )
    private String agent ;// 进口装备国内代理商

    @Column(name = "SHFWDW", length = 800 )
    private String afterServiceUnit ;// 售后服务单位

    @Column(name = "BYZQ"  )
    private Float maintenanceCycle ;// 保养周期

    @Column(name = "JCZQ"  )
    private Float checkCycle ;// 检查周期

    @Column(name = "BZ", length = 800 )
    private String remarks; // 备注

    @Column(name = "JLZT"   )
    private Integer JLZT;//记录状态

    @Column(name = "CSZT"  )
    private Integer CSZT;//传输状态

    @Column(name = "SJC")
    private Long SJC ;//时间戳

    @Column(name = "SJBB", length = 100)
    private String SJBB ;//数据库版本

    @Column(name = "YWXTBSID", length = 100)
    private String YWXTBSID ;//业务系统部署ID

    @Column(name = "JKSJBB", length = 100)
    private String JKSJBB ;//基础数据版本

    public String getEquipmentCode() {
        return equipmentCode;
    }

    public void setEquipmentCode(String equipmentCode) {
        this.equipmentCode = equipmentCode;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentTypeCode() {
        return equipmentTypeCode;
    }

    public void setEquipmentTypeCode(String equipmentTypeCode) {
        this.equipmentTypeCode = equipmentTypeCode;
    }

    public String getEquipmentTypeName() {
        return equipmentTypeName;
    }

    public void setEquipmentTypeName(String equipmentTypeName) {
        this.equipmentTypeName = equipmentTypeName;
    }

    public String getVehicleTypeCode() {
        return vehicleTypeCode;
    }

    public void setVehicleTypeCode(String vehicleTypeCode) {
        this.vehicleTypeCode = vehicleTypeCode;
    }

    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    public void setVehicleTypeName(String vehicleTypeName) {
        this.vehicleTypeName = vehicleTypeName;
    }

    public String getWhetherConsumptiveEquipment() {
        return whetherConsumptiveEquipment;
    }

    public void setWhetherConsumptiveEquipment(String whetherConsumptiveEquipment) {
        this.whetherConsumptiveEquipment = whetherConsumptiveEquipment;
    }

    public String getSpecificationsNumber() {
        return specificationsNumber;
    }

    public void setSpecificationsNumber(String specificationsNumber) {
        this.specificationsNumber = specificationsNumber;
    }

    public String getMeasurementCode() {
        return measurementCode;
    }

    public void setMeasurementCode(String measurementCode) {
        this.measurementCode = measurementCode;
    }

    public Float getReferencePrice() {
        return referencePrice;
    }

    public void setReferencePrice(Float referencePrice) {
        this.referencePrice = referencePrice;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getVolume() {
        return volume;
    }

    public void setVolume(Float volume) {
        this.volume = volume;
    }

    public String getProportion() {
        return proportion;
    }

    public void setProportion(String proportion) {
        this.proportion = proportion;
    }

    public String getCheckBatchNo() {
        return checkBatchNo;
    }

    public void setCheckBatchNo(String checkBatchNo) {
        this.checkBatchNo = checkBatchNo;
    }

    public String getResistanceFire() {
        return resistanceFire;
    }

    public void setResistanceFire(String resistanceFire) {
        this.resistanceFire = resistanceFire;
    }

    public String getMainComponent() {
        return mainComponent;
    }

    public void setMainComponent(String mainComponent) {
        this.mainComponent = mainComponent;
    }

    public String getApplicationRange() {
        return applicationRange;
    }

    public void setApplicationRange(String applicationRange) {
        this.applicationRange = applicationRange;
    }

    public String getAdmittanceCheckCategoryCode() {
        return admittanceCheckCategoryCode;
    }

    public void setAdmittanceCheckCategoryCode(String admittanceCheckCategoryCode) {
        this.admittanceCheckCategoryCode = admittanceCheckCategoryCode;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getAfterServiceUnit() {
        return afterServiceUnit;
    }

    public void setAfterServiceUnit(String afterServiceUnit) {
        this.afterServiceUnit = afterServiceUnit;
    }

    public Float getMaintenanceCycle() {
        return maintenanceCycle;
    }

    public void setMaintenanceCycle(Float maintenanceCycle) {
        this.maintenanceCycle = maintenanceCycle;
    }

    public Float getCheckCycle() {
        return checkCycle;
    }

    public void setCheckCycle(Float checkCycle) {
        this.checkCycle = checkCycle;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getJLZT() {
        return JLZT;
    }

    public void setJLZT(Integer JLZT) {
        this.JLZT = JLZT;
    }

    public Integer getCSZT() {
        return CSZT;
    }

    public void setCSZT(Integer CSZT) {
        this.CSZT = CSZT;
    }

    public Long getSJC() {
        return SJC;
    }

    public void setSJC(Long SJC) {
        this.SJC = SJC;
    }

    public String getSJBB() {
        return SJBB;
    }

    public void setSJBB(String SJBB) {
        this.SJBB = SJBB;
    }

    public String getYWXTBSID() {
        return YWXTBSID;
    }

    public void setYWXTBSID(String YWXTBSID) {
        this.YWXTBSID = YWXTBSID;
    }

    public String getJKSJBB() {
        return JKSJBB;
    }

    public void setJKSJBB(String JKSJBB) {
        this.JKSJBB = JKSJBB;
    }
}
