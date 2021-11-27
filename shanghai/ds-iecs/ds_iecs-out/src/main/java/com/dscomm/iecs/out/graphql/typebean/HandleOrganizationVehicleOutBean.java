package com.dscomm.iecs.out.graphql.typebean;

import com.dscomm.iecs.accept.service.bean.DeviceLocationBean;
import lombok.Data;

import java.util.List;
@Data
public class HandleOrganizationVehicleOutBean {
    private String id;

    private Integer valid  ;  //是否启用 0不启用 1启用
    private String incidentId ; //案件id

    private String handleId; // 处警记录ID

    private String handleBatch;// 处警批次

    private Long handleStartTime;// 处警开始时间

    private Long handleEndTime;// 处警结束时间

    private String handlePoliceId; // 处警信息ID

    private String organizationId; // 消防机构编号

    private String organizationName; // 消防机构名称

    private String vehicle; //   车辆编号

    private String vehicleId; // 车辆编号

    private String vehicleNumber; //   车牌编号  机动车号牌号码

    private String vehicleTypeCode; // 装备类型代码 （车辆类型代码）

    private String vehicleTypeName; // 装备类型名称

    private String fightFunctionCode; // 作战功能代码

    private String fightFunctionName; // 作战功能名称

    private String vehicleStatusCode;// 车辆状态代码 ( 车辆状态代码_灭火 )

    private String vehicleStatusName;// 车辆状态名称 ( 车辆状态代码_灭火 )

    private String  vehicleCode;//车辆编号

    private String vehicleName;// 装备名称 车辆名称

    private String gpsNumber;// GPS编号

    private String locationNumber;// 定位编号

    private String radioCallSign;// 电台呼号

    private Integer vehicleOrderNum = 99 ; // 车辆顺序 默认99

    private String incidentNumber;// 案件编号

    private Long noticeTime;// 通知时间

    private Long setOutTime;// 出动时间

    private Long arriveTime;// 到达时间

    private Long sendWaterTime;// 出水时间

    private Long stopWaterTime;// 停水时间

    private Long returnLoadTime;//todo 字段 返回时间

    private Long halfwayReturnTime;//中返时间

    private Long returnTime;// 归队时间

    private String remarks;//备注

    public Long getReturnLoadTime() {
        return returnLoadTime;
    }

    public void setReturnLoadTime(Long returnLoadTime) {
        this.returnLoadTime = returnLoadTime;
    }

    private List<EquipmentVehicleLoadOutBean> equipmentVehicleLoadBeanList; //车载装备列表

    private List<ParticipantFeedbackOutBean> participantFeedbackBeanList; //车载人员列表

    //设置车辆指挥员
    private String vehicleCommanderId ; // 车辆指挥员id
    private String vehicleCommander ; // 车辆指挥员
    private String vehicleCommanderPhone; ; // 车辆指挥员

    private String  mappingGroupId  ; //车库号

    //车辆位置信息
    private DeviceLocationBean deviceLocationBean ;

    private  String vehicleIdentification;  //车辆标识 0 头车 1指挥车 2尾车  可拓展
    private String uid;
    private String createTime;
}
