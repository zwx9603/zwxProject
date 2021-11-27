package com.dscomm.iecs.ext;

import com.dscomm.iecs.base.enums.BasicEnum;
import org.mx.spring.utils.I18nMessageUtils;

/**
 * 描述 : 车辆状态枚举
 *
 */
public enum VehicleStatusBakEnum implements BasicEnum {


//    VEHICLE_STATUS_TZ("01", "通知"),//车辆状态分类 通知
//    VEHICLE_STATUS_DM("02", "待命"),//车辆状态分类 待命
//    VEHICLE_STATUS_CD("03", "出动"),//车辆状态分类 出动
//    VEHICLE_STATUS_CDTZ("0301", "途中"),//车辆状态分类 途中
//    VEHICLE_STATUS_CDDC("0302", "到场"),//车辆状态分类 到场
//    VEHICLE_STATUS_CDCS("0303", "出水"),//车辆状态分类 出水
//    VEHICLE_STATUS_CDTS("0304", "停水"),//车辆状态分类 停水
//    VEHICLE_STATUS_CDFD("0305", "返队"),//车辆状态分类 返队
//    VEHICLE_STATUS_CDZX("0306", "执勤"),//车辆状态分类 执勤
//    VEHICLE_STATUS_CDZF("0307", "驻防"),//车辆状态分类 驻防
//    VEHICLE_STATUS_CDZTFD("0308", "中返"),//车辆状态分类 中返
//    VEHICLE_STATUS_XL("04", "训练"),//车辆状态分类 训练
//    VEHICLE_STATUS_JY("05", "加油"),//车辆状态分类 加油
//    VEHICLE_STATUS_SC("06", "试车"),//车辆状态分类 试车
//    VEHICLE_STATUS_YS("07", "验收"),//车辆状态分类 验收
//    VEHICLE_STATUS_GW("08", "公务"),//车辆状态分类 公务
//    VEHICLE_STATUS_GZ("09", "故障"),//车辆状态分类 故障
//    VEHICLE_STATUS_GZXL("0901", "修理"),//车辆状态分类 修理
//    VEHICLE_STATUS_GZBY("0902", "保养"),//车辆状态分类 保养
//    VEHICLE_STATUS_BF("10", "报废"),//车辆状态分类 报废
//    VEHICLE_STATUS_WY("98", "未用"),//车辆状态分类 未用
//    VEHICLE_STATUS_QT("99", "其他"),//车辆状态分类 其他

//
//    VEHICLE_STATUS_TZ("0100", "通知"),//车辆状态分类 通知
////    VEHICLE_STATUS_DM("0200", "待命"),//车辆状态分类 待命
//    VEHICLE_STATUS_CD("0300", "出动"),//车辆状态分类 出动
//    VEHICLE_STATUS_CDTZ("0301", "途中"),//车辆状态分类 途中
//    VEHICLE_STATUS_CDDC("0302", "到场"),//车辆状态分类 到场
//    VEHICLE_STATUS_CDCS("0303", "出水"),//车辆状态分类 出水
//    VEHICLE_STATUS_CDTS("0304", "停水"),//车辆状态分类 停水
//    VEHICLE_STATUS_CDFD("0305", "返队"),//车辆状态分类 返队
//    VEHICLE_STATUS_CDZQ("0306", "执勤"),//车辆状态分类 执勤
//    VEHICLE_STATUS_CDZF("0307", "驻防"),//车辆状态分类 驻防
//    VEHICLE_STATUS_CDZTFD("0308", "中返"),//车辆状态分类 中返
//    VEHICLE_STATUS_CDQT("0399", "其他"),//车辆状态分类 出动其他
////    VEHICLE_STATUS_XL("0400", "训练"),//车辆状态分类 训练
////    VEHICLE_STATUS_JY("0500", "加油"),//车辆状态分类 加油
////    VEHICLE_STATUS_SC("0600", "试车"),//车辆状态分类 试车
////    VEHICLE_STATUS_YS("0700", "验收"),//车辆状态分类 验收
////    VEHICLE_STATUS_GW("0800", "公务"),//车辆状态分类 公务
////    VEHICLE_STATUS_GZ("0900", "故障"),//车辆状态分类 故障
////    VEHICLE_STATUS_GZXL("0901", "修理"),//车辆状态分类 修理
////    VEHICLE_STATUS_GZBY("0902", "保养"),//车辆状态分类 保养
////    VEHICLE_STATUS_GZQT("0902", "其他"),//车辆状态分类 保养
////    VEHICLE_STATUS_BF("1000", "报废"),//车辆状态分类 报废
////    VEHICLE_STATUS_YL("1100", "演练"),//车辆状态分类 演练
////    VEHICLE_STATUS_WY("9800", "未用"),//车辆状态分类 未用
////    VEHICLE_STATUS_QT("9900", "其他"),//车辆状态分类 其他

    ;
    private String code;
    private String message;

    VehicleStatusBakEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    VehicleStatusBakEnum(String message) {
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getType() {
        return super.name();
    }

    @Override
    public String getName() {
        return I18nMessageUtils.getI18nMessage(super.name(), message);
    }
}
