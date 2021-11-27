package com.dscomm.iecs.out.graphql.typebean;

import com.dscomm.iecs.accept.graphql.typebean.HandleOrganizationEquipmentBean;
import com.dscomm.iecs.accept.graphql.typebean.HandleOrganizationVehicleBean;
import lombok.Data;

import java.util.List;
@Data
public class HandleOrganizationOutBean {
    private String id;

    private Integer valid  ;  //是否启用 0不启用 1启用

    private String incidentId ; //案件ID

    private String handleId;// 处警记录ID

    private String handleBatch;// 处警批次

    private Long handleStartTime;// 处警开始时间

    private Long handleEndTime;// 处警结束时间

    private String organization; //  接收单位

    private String organizationId; //消防机构编号

    private String organizationName; //消防机构名称

    private Long noticeTime;// 通知时间

    private Long systemFeedbackTime;// 系统反馈时间

    private Long personFeedbackPersonTime;// 人工反馈时间

    private String handlePoliceStatus ;// 指令状态 （处警信息状态）  STATUS_GIVEN已通知 STATUS_SIGNED已签收

    private String handlePoliceStatusName ;// 指令状态 （处警信息状态）

    private String feedbackPersonId;// 反馈用户编号

    private String feedbackPersonName;// 反馈人姓名

    private String remarks;//备注

    private String speakToFileUrl ; //  tts播放文件路径

    private String organizationCode;// 机构代码

    private String pinyinHeader; //机构名称拼音头

    private Integer organizationOrderNum ; // 机构排序

    private String organizationParentId; // 上级机构ID

    private String districtCode;// 行政区代码

    private String districtName;// 行政区名称

    private String longitude;// 经度

    private String latitude;// 纬度

    private String height;// 高度

    private String dispatchPhone;// 调度电话

    private String mailCode;// 邮政编码

    private String faxNumber;// 传真号码

    private String email;//电子信箱

    private String contactPerson;// 单位联系人

    private String contactPersonName ;//单位联系人

    private String contactPhone;// 单位联系电话
    private String uid;
    private String createTime;

    private List<HandleOrganizationVehicleBean> handleOrganizationVehicleBean ; // 调派单位车辆信息 （ 作战车辆信息 ）

    private List<HandleOrganizationEquipmentBean> handleOrganizationEquipmentBean ; //调派单位装备信息
}
