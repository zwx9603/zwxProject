package com.dscomm.iecs.accept.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

import java.util.List;

/**
 * 描述:天气信息
 * <p>
 * *
 */
public class WeatherBean extends BaseBean {

    private Long forecastTime; //预报发布时间

    private Long forecastStartTime; //预报起始时间

    private Long forecastEndTime; //预报结束时间

    private String organizationId; //消防机构代码

    private String organizationName; //消防机构名称

    private String districtCode; //行政区域

    private String districtName; //行政区域名称

    private String longitude;// 经度

    private String latitude;// 纬度

    private String regionRange; // 范围信息

    private Float minTemperature; //最低温度

    private Float maxTemperature; //最高温度

    private String weatherInformationCode1; //天气情况代码1

    private String weatherInformationName1; //天气情况名称1

    private String weatherInformationCode2; //天气情况代码2

    private String weatherInformationName2; //天气情况名称2

    private String minWindpower; //最低风力

    private String minWindpowerName; //最低风力

    private String maxWindpower; //最高风力

    private String maxWindpowerName; //最高风力

    private String winddirection1; //风向1

    private String winddirection1Name; //风向1

    private String winddirection2; //风向2

    private String winddirection2Name; //风向2

    private String precipitationProbability; //降水概率

    private String minRelativeHumidity; //最低相对湿度

    private String maxRelativeHumidity; //最高相对湿度

    private String disasterForecast; //灾害预报

    private String recordingMode; //记录方式

    private String remarks; // 备注

    private List<String> windPowerLevelInfo; //风力等级信息

    private List<String> windDirectionInfo; //风向等级信息

    public List<String> getWindDirectionInfo() {
        return windDirectionInfo;
    }

    public void setWindDirectionInfo(List<String> windDirectionInfo) {
        this.windDirectionInfo = windDirectionInfo;
    }

    public List<String> getWindPowerLevelInfo() {
        return windPowerLevelInfo;
    }

    public void setWindPowerLevelInfo(List<String> windPowerLevelInfo) {
        this.windPowerLevelInfo = windPowerLevelInfo;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getWeatherInformationName1() {
        return weatherInformationName1;
    }

    public void setWeatherInformationName1(String weatherInformationName1) {
        this.weatherInformationName1 = weatherInformationName1;
    }

    public String getWeatherInformationName2() {
        return weatherInformationName2;
    }

    public void setWeatherInformationName2(String weatherInformationName2) {
        this.weatherInformationName2 = weatherInformationName2;
    }

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

    public String getMinWindpowerName() {
        return minWindpowerName;
    }

    public void setMinWindpowerName(String minWindpowerName) {
        this.minWindpowerName = minWindpowerName;
    }

    public String getMaxWindpower() {
        return maxWindpower;
    }

    public void setMaxWindpower(String maxWindpower) {
        this.maxWindpower = maxWindpower;
    }

    public String getMaxWindpowerName() {
        return maxWindpowerName;
    }

    public void setMaxWindpowerName(String maxWindpowerName) {
        this.maxWindpowerName = maxWindpowerName;
    }

    public String getWinddirection1Name() {
        return winddirection1Name;
    }

    public void setWinddirection1Name(String winddirection1Name) {
        this.winddirection1Name = winddirection1Name;
    }

    public String getWinddirection2Name() {
        return winddirection2Name;
    }

    public void setWinddirection2Name(String winddirection2Name) {
        this.winddirection2Name = winddirection2Name;
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


    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getRegionRange() {
        return regionRange;
    }

    public void setRegionRange(String regionRange) {
        this.regionRange = regionRange;
    }
}