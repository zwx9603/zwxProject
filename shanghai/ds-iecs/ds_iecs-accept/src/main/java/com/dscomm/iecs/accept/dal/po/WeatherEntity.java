package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述:天气信息
 * <p>
 * *
 */
@Entity
@Table(name = "ZBZB_QXXX")
@DynamicInsert(true)
@DynamicUpdate(true)
public class WeatherEntity extends BaseEntity {

    @Column(name = "YBFBSJ")
    private Long forecastTime; //预报发布时间

    @Column(name = "YBQSSJ")
    private Long forecastStartTime; //预报起始时间

    @Column(name = "YBJSSJ")
    private Long forecastEndTime; //预报结束时间

    @Column(name = "XFJGDM", length = 100)
    private String organizationId; //消防机构代码

    @Column(name = "XZQY", length = 100)
    private String districtCode; //行政区域

    @Column(name = "ZDWD")
    private Float minTemperature; //最低温度

    @Column(name = "ZGWD")
    private Float maxTemperature; //最高温度

    @Column(name = "TQDM1", length = 100)
    private String weatherInformationCode1; //天气情况代码1

    @Column(name = "TQDM2", length = 100)
    private String weatherInformationCode2; //天气情况代码2

    @Column(name = "ZDFL", length = 100)
    private String minWindpower; //最低风力

    @Column(name = "ZGFL", length = 100)
    private String maxWindpower; //最高风力

    @Column(name = "FX1", length = 100)
    private String winddirection1; //风向1

    @Column(name = "FX2", length = 100)
    private String winddirection2; //风向2

    @Column(name = "JSGL", length = 100)
    private String precipitationProbability; //降水概率

    @Column(name = "ZDXDSD", length = 100)
    private String minRelativeHumidity; //最低相对湿度

    @Column(name = "ZGXDSD", length = 100)
    private String maxRelativeHumidity; //最高相对湿度

    @Column(name = "ZHYB", length = 1000)
    private String disasterForecast; //灾害预报

    @Column(name = "JLFS", length = 100 )
    private String recordingMode; //记录方式

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

    public Long getForecastTime() {
        return forecastTime;
    }

    public void setForecastTime(Long forecastTime) {
        this.forecastTime = forecastTime;
    }

    public Long getForecastStartTime() {
        return forecastStartTime;
    }

    public void setForecastStartTime(Long forecastStartTime) {
        this.forecastStartTime = forecastStartTime;
    }

    public Long getForecastEndTime() {
        return forecastEndTime;
    }

    public void setForecastEndTime(Long forecastEndTime) {
        this.forecastEndTime = forecastEndTime;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public Float getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(Float minTemperature) {
        this.minTemperature = minTemperature;
    }

    public Float getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(Float maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public String getWeatherInformationCode1() {
        return weatherInformationCode1;
    }

    public void setWeatherInformationCode1(String weatherInformationCode1) {
        this.weatherInformationCode1 = weatherInformationCode1;
    }

    public String getWeatherInformationCode2() {
        return weatherInformationCode2;
    }

    public void setWeatherInformationCode2(String weatherInformationCode2) {
        this.weatherInformationCode2 = weatherInformationCode2;
    }

    public String getMinWindpower() {
        return minWindpower;
    }

    public void setMinWindpower(String minWindpower) {
        this.minWindpower = minWindpower;
    }

    public String getMaxWindpower() {
        return maxWindpower;
    }

    public void setMaxWindpower(String maxWindpower) {
        this.maxWindpower = maxWindpower;
    }

    public String getWinddirection1() {
        return winddirection1;
    }

    public void setWinddirection1(String winddirection1) {
        this.winddirection1 = winddirection1;
    }

    public String getWinddirection2() {
        return winddirection2;
    }

    public void setWinddirection2(String winddirection2) {
        this.winddirection2 = winddirection2;
    }

    public String getPrecipitationProbability() {
        return precipitationProbability;
    }

    public void setPrecipitationProbability(String precipitationProbability) {
        this.precipitationProbability = precipitationProbability;
    }

    public String getMinRelativeHumidity() {
        return minRelativeHumidity;
    }

    public void setMinRelativeHumidity(String minRelativeHumidity) {
        this.minRelativeHumidity = minRelativeHumidity;
    }

    public String getMaxRelativeHumidity() {
        return maxRelativeHumidity;
    }

    public void setMaxRelativeHumidity(String maxRelativeHumidity) {
        this.maxRelativeHumidity = maxRelativeHumidity;
    }

    public String getDisasterForecast() {
        return disasterForecast;
    }

    public void setDisasterForecast(String disasterForecast) {
        this.disasterForecast = disasterForecast;
    }

    public String getRecordingMode() {
        return recordingMode;
    }

    public void setRecordingMode(String recordingMode) {
        this.recordingMode = recordingMode;
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
