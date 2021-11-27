package com.dscomm.iecs.out.graphql.typebean;

import lombok.Data;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/4/19 19:23
 * @Describe 警情数据bean
 */
@Data
public class IncidentOutBean {
    private String bjTywysbm;//报警_通用唯一识别码
    private String bjdh;//报警电话
    private String bjfslbdm;//报警方式类别代码
    private String bjsj;//报警时间string(date-time)
    private String cdsj;//出动时间
    private String cssj;//出水时间
    private String dcsj;//到场时间
    private String ddmc;//地点名称
    private Number dqjd;//地球经度
    private Number dqwd;//地球纬度
    private String fwdtqk;//房屋倒塌情况
    private String gdsj;//归队时间
    private String hskzsj;//火势控制时间
    private String jasj;//结案时间
    private String jbpmsj;//基本扑灭时间
    private String jqJyqk;//警情简要情况
    private String jqSsqydm;//所属区域代码
    private String jqTywysbm;//警情_通用唯一识别码
    private String jqXlh;//警情_序列号
    private String jqXqzd;//辖区中队
    private String jqXqzdTywysbm;//辖区中队_通用唯一识别码
    private String jqbslbdm;//警情标识类别代码
    private String jqdj;//警情等级
    private String jqdjdm;//警情等级代码
    private String jqdxJqdxlbdm;//警情对象类别代码
    private String jqdxJyqk;//警情对象简要情况
    private String jqdxMc;//警情对象名称
    private String jqdxTywysbm;//警情对象通用唯一识别码
    private String jqflydm;//警情分类与代码
    private String jqlb;//警情类别
    private String jqlbdm;//警情类别代码
    private String jqlx;//警情类型
    private String jqlxdm;//警情类型代码
    private String jqztLbdm;//警情状态类别代码
    private String jqztRqsj;//日期时间
    private String jsmlsj;//接受命令时间
    private String jtssqk;//交通设施情况
    private String jzjglx;//建筑结构类型
    private String jzjglxdm;//建筑结构类型代码
    private String lasj;//立案时间
    private Integer lc;//integer(int32)楼层
    private String lccs;//楼层层数
    private String mc;//名称
    private Integer rscs;//燃烧层数
    private String rsdx;//燃烧对象
    private String rybkqk;//人员被困情况
    private String ryslqk;//人员失联情况
    private String ryswqk;//人员伤亡情况
    private String tssj;//停水时间
    private String txssqk;//通讯设施情况
    private String uid;//警情_唯一ID：yyyymmddhhmmssabcd
    private String xczhrLxdh;//联系电话
    private String xczhrRqsj;//日期时间
    private String xczhrXfgwlbdm;//消防岗位类别代码
    private String xczhrXm;//姓名
    private String xfjyjgTywysbm;//消防救援机构_通用唯一识别码
    private String xzqhdm;//行政区划代码
    private String ywqk;//烟雾情况
    private String ywqkdm;//烟雾情况代码
    private String zdzksj;//战斗展开时间
    private String zqcs;//灾情场所
    private String ztfhsj;//中途返回时间
    private String createTime;
    private String deptCode;//单位code
    private String jqbh;//警情编号

    //新增
    private String jqlxdmcjxq;//警情类型代码层级详情

}
