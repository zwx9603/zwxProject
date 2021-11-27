package com.dscomm.iecs.accept.utils.transform;

import com.dscomm.iecs.accept.dal.po.WeatherEntity;

import java.math.BigInteger;
import java.util.Objects;

public class WeatherTransformUtil {
    public static WeatherEntity transform(Object[] objects) {
        if (objects.length == 0) {
            return null;
        }
        WeatherEntity weatherEntity = new WeatherEntity();
        weatherEntity.setId(Objects.nonNull(objects[0]) ? (String) objects[0] : null);// 主键
        weatherEntity.setCreatedTime(Objects.nonNull(objects[1]) ? ((BigInteger) objects[1]).longValue() : null);//  创建时间
        weatherEntity.setOperator(Objects.nonNull(objects[2]) ? (String) objects[2] : null);//操作者
        weatherEntity.setUpdatedTime(Objects.nonNull(objects[3]) ? ((BigInteger) objects[3]).longValue() : null);//更新时间
        weatherEntity.setValid(Objects.nonNull(objects[4]) ? (Integer) objects[4] : null);//有效性
        weatherEntity.setCSZT(Objects.nonNull(objects[5]) ? (Integer) objects[5] : null);//传输状态
        weatherEntity.setJKSJBB(Objects.nonNull(objects[6]) ? (String) objects[6] : null);//基础数据版本
        weatherEntity.setJLZT(Objects.nonNull(objects[7]) ? (Integer) objects[7] : null);//记录状态
        weatherEntity.setSJBB(Objects.nonNull(objects[8]) ? (String) objects[8] : null);//数据库版本
        weatherEntity.setSJC(Objects.nonNull(objects[9]) ? ((BigInteger) objects[9]).longValue() : null);//时间戳
        weatherEntity.setYWXTBSID(Objects.nonNull(objects[10]) ? (String) objects[10] : null);//业务系统部署ID
        weatherEntity.setDisasterForecast(Objects.nonNull(objects[11]) ? (String) objects[11] : null);//灾害预报
        weatherEntity.setDistrictCode(Objects.nonNull(objects[12]) ? (String) objects[12] : null);// 行政区域
        weatherEntity.setForecastTime(Objects.nonNull(objects[13]) ? ((BigInteger) objects[13]).longValue() : null);// 预报发布时间
        weatherEntity.setForecastStartTime(Objects.nonNull(objects[14]) ? ((BigInteger) objects[14]).longValue() : null);//预报起始时间
        weatherEntity.setForecastEndTime(Objects.nonNull(objects[15]) ? ((BigInteger) objects[15]).longValue(): null);//预报结束时间
        weatherEntity.setMaxRelativeHumidity(Objects.nonNull(objects[16]) ? (String) objects[16] : null);//最高相对湿度
        weatherEntity.setMaxTemperature(Objects.nonNull(objects[17]) ? (Float) objects[17] : null);//最高温度
        weatherEntity.setMaxWindpower(Objects.nonNull(objects[18]) ? (String) objects[18] : null);//最高风力
        weatherEntity.setMinRelativeHumidity(Objects.nonNull(objects[19]) ? (String) objects[19] : null);//最低相对湿度
        weatherEntity.setMinTemperature(Objects.nonNull(objects[20]) ? (Float) objects[20] : null);//最低温度
        weatherEntity.setMinWindpower(Objects.nonNull(objects[21]) ? (String) objects[21] : null);//最低风力
        weatherEntity.setOrganizationId(Objects.nonNull(objects[22]) ? (String) objects[22] : null);//消防机构代码
        weatherEntity.setPrecipitationProbability(Objects.nonNull(objects[23]) ? (String) objects[23] : null);//降水概率
        weatherEntity.setRecordingMode(Objects.nonNull(objects[24]) ? (String) objects[24] : null);//记录方式
        weatherEntity.setRemarks(Objects.nonNull(objects[25]) ? (String) objects[25] : null);//备注
        weatherEntity.setWeatherInformationCode1(Objects.nonNull(objects[26]) ? (String) objects[26] : null);//天气情况代码1
        weatherEntity.setWeatherInformationCode2(Objects.nonNull(objects[27]) ? (String) objects[27] : null);//天气情况代码2
        weatherEntity.setWinddirection1(Objects.nonNull(objects[28]) ? (String) objects[28] : null);//风向1
        weatherEntity.setWinddirection2(Objects.nonNull(objects[29]) ? (String) objects[29] : null);//风向2
        return weatherEntity;
    }
}
