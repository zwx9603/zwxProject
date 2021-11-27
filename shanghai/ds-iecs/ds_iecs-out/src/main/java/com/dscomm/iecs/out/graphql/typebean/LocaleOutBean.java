package com.dscomm.iecs.out.graphql.typebean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

@Data
public class LocaleOutBean {
    @JSONField(name = "jqxcTywysbm")
    private String idCode; //警情现场_通用唯一识别码

    @JSONField(name = "jqTywysbm")
    private String incidentId;//警情通用唯一识别码

    @JSONField(name = "jlRqsj")
    private String collectionTime;//记录日期时间

    @JSONField(name = "xcJyqk")
    private String localeDesc;//现场简要情况

    @JSONField(name = "hcWd")
    private Number fireSitesTemperature;//火场温度

    @JSONField(name = "tqzklbdm")
    private String weatherInformationCode;//天气状况类别代码

    @JSONField(name = "kqWd")
    private Number weatherTemperature;//空气温度

    @JSONField(name = "fxlbdm")
    private String windDirection;//风向类别代码

    @JSONField(name = "fldjdm")
    private String windGrade;//风力等级代码

    @JSONField(name = "xdsd")
    private Number relativeHumidity;//相对湿度

    @JSONField(name = "rsMj")
    private Number burningArea;//燃烧面积

    @JSONField(name = "ywqkdm")
    private String smogSituationCode;//烟雾情况代码

    @JSONField(name = "fkjgDwmc")
    private String feedbackOrganizationName ;// 反馈机构单位名称

    @JSONField(name = "fkrXm")
    private String  operatorPerson ; //反馈人_姓名

    @JSONField(name = "jlrXm")
    private String  recordPerson ; //记录人姓名

    @JSONField(name = "dcRs")
    private Integer attendanceNum;// 到场人数

    @JSONField(name = "bkRs")
    private Integer trappedPersonNum;// 被困_人数

    @JSONField(name = "qzssRs")
    private Integer massInjuredNum;// 群众受伤人数

    @JSONField(name = "dwssRs")
    private Integer troopsInjuredNum;// 队伍受伤人数

    @JSONField(name = "qzswRs")
    private Integer massDeathNum;// 群众死亡人数

    @JSONField(name = "dwswRs")
    private Integer troopsDeathNum;// 队伍死亡人数

    @JSONField(name = "qzslRs")
    private Integer massOutContactNum  ; //  群众失联人员

    @JSONField(name = "dwslRs")
    private Integer troopsOutContactNum  ; //  队伍失联人员

    @JSONField(name = "hsJyqk")
    private String fireSitesDesc;// 火势_简要情况

    @JSONField(name = "rswzMc")
    private String burnMaterialName;// 燃烧物质_名称

    @JSONField(name = "fsd")
    private Number windSpeed;// 风_速度

    @JSONField(name = "dqYl")
    private Integer pressure;//  大气压力

    @JSONField(name = "yawJyqk")
    private String smogSituationDesc;//  烟雾_简要情况

    @JSONField(name = "delFlag")
    private String delFlag;//  禁用标志

    @JSONField(name = "zhrXm")
    private String localeCommander;//  现场指挥人_姓名

    @JSONField(name = "createTime")
    private String createTime;//  创建时间

    @JSONField(name = "swryList")
    private List<SwryBean> swryList;//  现场伤亡人员列表

    @JSONField(name = "uid")
    private String uid;
    @JSONField(name = "deptCode")
    private String deptCode;//单位code
}
