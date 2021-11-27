package com.dscomm.iecs.out.graphql.typebean;

import lombok.Data;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/4/20 11:07
 * @Describe 推送车辆信息
 */
@Data
public class VehicleOutBean {

    private Number bedyl;//泵额定压力(MPA
    private String bfrq;//报废日期 string(date-time)
    private String bz;//备注
    private String ccrq;//出厂日期 string(date-time)
    private Number ckjJe;//参考价_金额
    private String clcs;//车辆参数（length:长;width:宽;height:高;load:总重量;size:车辆大小;axis:轴数;）
    private String cllxMc;//车辆类型_名称
    private String cllxTywysbm;//车辆类型_通用唯一识别码
    private String clqwztlbdm;//车辆勤务状态类别代码
    private String clsbdh;//车辆识别代号
    private Number clwzDqjd;//地球经度
    private Number clwzDqwd;//地球纬度
    private String clwzWzcjsj;//位置采集时间string(date-time)
    private String clxhTywysbm;//车辆型号_通用唯一识别码
    private String clxnzbJyqk;//车辆性能指标_简要情况
    private String clxszhm;//行驶证号码
    private String clxszzp;//车辆行驶证照片
    private String clzp;//车辆照片
    private String clztbm;//车辆状态编码
    private String dwsbSimkkh;//SIM卡卡号
    private String dwsbTywysbm;//通用唯一识别码
    private String dwzjhm;//单位证件号码
    private String dwzjlx;//单位证件类型
    private Integer edzrs;//核定载人数
    private String fzrLxdh;//联系电话
    private String fzrTywysbm;//通用唯一识别码
    private String fzrXm;//姓名
    private String ggxh;//规格型号
    private String jdccsyslbdm;//机动车车身颜色类别代码
    private String jdcfdjddjh;//机动车发动机（电动机）号
    private String jdchphm;//机动车号牌号码
    private String jgcs;//举高参数
    private String jsy;//驾驶员
    private String jsylxdh;//驾驶员联系电话
    private String lxfs;//联系方式
    private String pmlx;//泡沫类型
    private Number pmzzl;//泡沫装载量（m³）
    private Integer px;//排序
    private String sccjDwmc;//生产厂家_单位名称
    private Boolean sfdycdclTywysbm;//是否第一出动车辆_判断标识
    private Boolean sfts;//是否特殊
    private Boolean sfygpsdw;//是否有GPS定位
    private Boolean sfyyxlPdbz;//是否用于训练_判断标识
    private Boolean sfzpPdbz;//是否装配_判断标识
    private Boolean sfzx;//是否在线
    private String shfwDwmc;//售后服务_单位名称
    private String ssdwmc;//所属单位名称
    private String sssfmc;//所属省份名称
    private String xfclTywysbm;//消防车辆_通用唯一识别码
    private String xfcldjdm;//消防车辆等级代码
    private String xfclzzgndm;//消防车辆作战功能代码
    private String xfjyjgMc;//消防救援机构_名称
    private String xfjyjgTywysbm;//消防救援机构_通用唯一识别码
    private String xfzblxdm;//消防装备器材分类与代码
    private String xsfx;//行驶方向
    private String xssd;//行驶速度
    private String ytsm;//用途说明
    private Integer yxj;//优先级
    private String zbrq;//装备日期 string(date-time
    private String zdlxr;//中队联系人
    private Number zzalpmRj;//装载A类泡沫_容积
    private Number zzblpmRj;//装载B类泡沫_容积
    private Number zzsRj;//装载水_容积
    private String uid;
    private String createTime;
    private String deptCode;//单位code
}
