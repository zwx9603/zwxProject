package com.dscomm.iecs.ext;


import com.dscomm.iecs.base.enums.BaseEnum;

/**
 * 描述：集结点类型
 *
 */
public enum RallyPointTypeBakEnum implements BaseEnum {


    SCENE_HEADQUARTERS("现场指挥部"),
    OPERATIONAL_TASK("作战任务点"),
    VEHICLE_AGGREGATION("车辆集结点"),
    EQUIPMENT_AGGREGATION("器材集结点"),
    LOGISTIC_SERVICE("后勤保障点");
    private String title;

    RallyPointTypeBakEnum(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }



}
