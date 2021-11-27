package com.dscomm.iecs.out.graphql.typebean;

import com.dscomm.iecs.basedata.graphql.typebean.EquipmentVehicleLoadBean;
import lombok.Data;

import java.util.List;
@Data
public class EquipmentVehicleLoadOutBean {
    private String id;

    private Integer valid  ;  //是否启用 0不启用 1启用
    private String organizationId;// 所属消防机构ID

    private String organizationName;// 所属消防机构名称

    private String vehicleId;// 车辆id

    private String  singleEquipmentCode;// 单件装备编码

    private String equipmentTypeCode;// 装备类型编码

    private String equipmentTypeName;// 装备类型名称

    private String equipmentCode;// 装备编码

    private String equipmentName;// 装备名称

    private String specificationsNumber  ;// 规格型号

    private String  loadNum;//装载数量

    private String measurementCode;// 计量单位代码

    private String measurementName;// 计量单位名称

    private Integer  whetherConsumptiveEquipment;//是否属于消耗器材 0 非消耗器材  1消耗器材


    private String uid;
    private String createTime;
}
