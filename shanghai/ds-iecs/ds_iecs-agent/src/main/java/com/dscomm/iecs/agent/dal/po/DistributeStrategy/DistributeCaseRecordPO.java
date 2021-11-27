package com.dscomm.iecs.agent.dal.po.DistributeStrategy;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Zhuqihong
 * 分配警情记录ORM
 * Date Time 2019/8/31
 */
@Entity
@Table(name = "t_fp_jqjl")
public class DistributeCaseRecordPO implements Serializable {
    /**
     * 流水号
     */
    @Id
    @Column(name = "lsh", length = 40, nullable = false)
    @GenericGenerator(name = "generuuid", strategy = "uuid")
    @GeneratedValue(generator = "generuuid")
    private String serialNumber;
    /**
     * 单号
     */
    @Column(name = "dh")
    private String incidentId;
    /**
     * 分配ACD组
     */
    @Column(name = "acd")
    private Integer acd;
    /**
     * 发起分配时间
     */
    @Column(name = "fqfpsj")
    private Long startDistributeTime;
    /**
     * 发起分配台号
     */
    @Column(name = "fqfpth")
    private Integer stratDistributeStationId;
    /**
     * 分配申请接收时间
     */
    @Column(name = "fpsqjssj")
    private Long acceptDistributionTime;
    /**
     * 分配到达时间
     */
    @Column(name = "fpddsj")
    private Long distributionArrivalTime;
    /**
     * 分配目标台号
     */
    @Column(name = "fpmbth")
    private Integer distributeTargetStationId;
    /**
     * 分配状态
     */
    @Column(name = "fpzt")
    private Integer distributeStatus;
    /**
     * 时间戳
     */
    @Column(name = "sjc")
    private Long lastedhandleTime;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public Integer getAcd() {
        return acd;
    }

    public void setAcd(Integer acd) {
        this.acd = acd;
    }

    public Long getStartDistributeTime() {
        return startDistributeTime;
    }

    public void setStartDistributeTime(Long startDistributeTime) {
        this.startDistributeTime = startDistributeTime;
    }

    public Integer getStratDistributeStationId() {
        return stratDistributeStationId;
    }

    public void setStratDistributeStationId(Integer stratDistributeStationId) {
        this.stratDistributeStationId = stratDistributeStationId;
    }

    public Long getAcceptDistributionTime() {
        return acceptDistributionTime;
    }

    public void setAcceptDistributionTime(Long acceptDistributionTime) {
        this.acceptDistributionTime = acceptDistributionTime;
    }

    public Long getDistributionArrivalTime() {
        return distributionArrivalTime;
    }

    public void setDistributionArrivalTime(Long distributionArrivalTime) {
        this.distributionArrivalTime = distributionArrivalTime;
    }

    public Integer getDistributeTargetStationId() {
        return distributeTargetStationId;
    }

    public void setDistributeTargetStationId(Integer distributeTargetStationId) {
        this.distributeTargetStationId = distributeTargetStationId;
    }

    public Integer getDistributeStatus() {
        return distributeStatus;
    }

    public void setDistributeStatus(Integer distributeStatus) {
        this.distributeStatus = distributeStatus;
    }

    public Long getLastedhandleTime() {
        return lastedhandleTime;
    }

    public void setLastedhandleTime(Long lastedhandleTime) {
        this.lastedhandleTime = lastedhandleTime;
    }
}
