package com.dscomm.iecs.out.graphql.typebean;

import lombok.Data;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/4/19 13:33
 * @Describe 报警记录（对应受理单）
 */
@Data
public class AlarmRecordBean {
    private String bjTywysbm;//报警_通用唯一识别码
    private String bjdh;//报警电话
    private String bjfslbdm;//报警方式类别代码
    private String bz;//备注
    private String createTime;//创建时间（string类型的datetime）
    private String deptCode;
    private Number dqjd;//地球经度
    private Number dqwd;//地球纬度
    private String dzmc;//地址名称
    private String rqsj;//日期时间（string类型的datetime）
    private String thjlTywysbm;//通话记录_通用唯一识别码
    private String updateTime;// string类型的datetime）
    private String xfryTywysbm;//消防人员 通用唯一识别码
    private String xfryXm;//消防人员姓名
    private String xzqhdm;//行政区划代码
    private String uid;
   // private String bjfslbdm;//报警方式类别代码

}
