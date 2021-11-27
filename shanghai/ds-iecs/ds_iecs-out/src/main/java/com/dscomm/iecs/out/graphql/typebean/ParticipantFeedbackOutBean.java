package com.dscomm.iecs.out.graphql.typebean;

import lombok.Data;

@Data
public class ParticipantFeedbackOutBean {
    private String id;

    private Integer valid  ;  //是否启用 0不启用 1启用
    private String incidentId ; //案件id

    private String handleId;// 处警记录ID

    private String organizationId; // 消防机构编号

    private String organizationName; // 消防机构名称

    private String vehicleId; // 车辆编号

    private String personId; // 人员编号

    private String personName; // 人员姓名

    private String personPhone; // 人员联系电话

    private String personRole; //人员角色

    private String personRoleName; //人员角色名称

    private Integer sorter;//排序

    private Long checkTime; //清点时间

    private Integer whetherFeedback; //是否返回 0 非返回 1 返回

    private Long feedbackCheckTime; //返回清点时间

    private String checkPersonId; //清点人员编号

    private String checkPersonName; //清点人员姓名

    private String remarks;//备注

    //额外字段
    private Integer whetherEnterFireSafety = 0  ; //是否进入火场 1 进火场 0 不在火场 默认0 不在火场

    private Long enterFireTime ; //  人员进入火场时间

    private Long  withdrawFireTime ; //人员撤出火场时间
    private String uid;
    private String createTime;
}
