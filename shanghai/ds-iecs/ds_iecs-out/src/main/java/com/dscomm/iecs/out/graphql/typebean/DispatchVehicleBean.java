package com.dscomm.iecs.out.graphql.typebean;

import lombok.Data;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/4/19 21:43
 * @Describe 调派车辆
 */
@Data
public class DispatchVehicleBean {
    //todo...
    private String cllxMc;//车辆类型名称
    private String cllxTywysbm;//车辆类型代码
    private Integer edzrs;//核定载人数
    private String jdchphm;//机动车号牌号码
    private String jgcs;//举高参数
    private String jqcjdpTywysbm;//警情处警调派_通用唯一识别码
    private String jqcjdpclDpsj;//警情处警调派车辆_调派事件
    private String jqcjdpclGgyj;//调派车辆_更改依据
    private String jqcjdpclGgzt;//更改状态
    private String jqcjdpclGxfs;//警情处警调派车辆_更新方式
    private String jqcjdpclTywysbm;//警情处警调派车辆_通用唯一识别码
    private String jqcjdpclXgyybz;//调派车辆_修改原因备注
    private String jqcjdpclZtgxbz;//调派车辆_状态更新备注
    private String jqcjdpclztlbdm;//警情处警调派车辆状态类别代码
    private String jqcjdpdzMc;//警情处警调派中队_名称
    private String jqcjdpdzTywysbm;//警情处警调派中队_通用唯一识别码
    private Number pmzzl;//泡沫装载量
    private String updateTime;//更新时间
    private String xfjyclTywysbm;//消防救援车辆_通用唯一识别码
    private Number zzsrj;//装载水_容积
    private String uid;
    private String createTime;
    private String deptCode;//单位code
}
