package com.dscomm.iecs.basedata.exception;

import org.mx.error.UserInterfaceError;
import org.mx.error.UserInterfaceException;

/**
 * 描述:基础数据模块异常类
 *
 */
public class BasedataException extends UserInterfaceException {
    public BasedataException(int errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    public BasedataException() {
        this(BasedataErrors.OTHER_FAIL);
    }

    public BasedataException(BasedataErrors error) {
        this(error.getErrorCode(), error.getErrorMessage());
    }

    public enum BasedataErrors implements UserInterfaceError {
        DATA_NULL("必填字段为空"),
        FIND_DATA_FAIL("数据查询失败"),
        DATA_FAIL_NULL("数据为空"),
        SAVE_DATA_FAIL("数据保存失败"),
        FIND_ACD_FAIL("获取acd失败"),
        FIND_USERACD_FAIL("获取用户acd失败"),
        FIND_AGENTACD_FAIL("获取坐席acd失败"),
        FIND_DICTIONARY_FAIL("字典查询失败"),
        FIND_VEHICLE_FAIL("车辆查询失败"),
        FIND_ORGANIZATION_FAIL("机构查询失败"),
        FIND_ORGANIZATION_SEARCH_PATH_FAIL("机构查询码获取失败"),
        UPDATE_VEHICLE_STATUS_FAIL("修改车辆状态信息失败"),
        UPDATE_VEHICLE_STATUS_DUTY("中队禁止修改处警中车辆状态"),
        UPDATE_VEHICLE_EXPAND_INFO_FAIL("修改车辆拓展信息失败"),
        SAVE_ACCOUNT_OPERATION_FAIL("保存账号操作信息失败"),
        FIND_LOCAL_FULLTIME_ORGANIZATION_FAIL("地方专职队信息查询失败"),
        FIND_ORGANIZATION_ADJACENT_PRIORITY_FAIL("机构毗邻信息查询失败"),
        BUILD_ORGANIZATION_ADJACENT_PRIORITY_FAIL("机构毗邻信息构建失败"),
        FIND_EQUIPMENT_FAIL("装备查询失败"),
        UPDATE_CACHE_FAIL("刷新缓存失败"),
        FIND_PERSON_MAIL_FAIL("人员通讯录查询失败"),
        FIND_PERSON_FAIL("人员查询失败"),
        FIND_VEHICLE_GARAGE_MAPPING_FAIL("查询车辆与车库关系失败"),
        SAVE_VEHICLE_GARAGE_MAPPING_FAIL("保存车辆与车库关系失败"),
        FIND_CONTACT_NUMBER_FAIL("获取联系信息失败"),
        SAVE_SYSTEM_CONFIGURATION_FAIL("保存/修改系统配置失败"),
        FIND_SYSTEM_CONFIGURATION_FAIL("查询系统配置失败"),
        UPLOAD_FDFS_FILE_FAIL("fdfs上传文件失败"),
        FIND_FILE_PATH_FAIL("fdfs上传文件失败"),
        UPDATE_CACHE_KEY_UNIT_FAIL("全部重点单位缓存更新失败"),
        FIND_KEY_UNIT_NAME_MAP_FAIL("重点单位名称缓存查询失败"),
        FIND_KEY_UNIT_FAIL("重点单位查询失败"),
        FIND_PLAN_FAIL("预案查询失败"),
        SAVE_PLAN_FAIL("重点单位预案保存失败"),
        FIND_WATERSOURCE_FAIL("查询水源信息失败"),
        CHECK_SYSTEMCONFIG_FAIL("系统配置检测失败"),
        OPERATE_SYSTEMCONFIGCAHCE_FAIL("操作系统配置缓存失败"),
        FIND_ORGANIZATION_ON_DUTY_FAIL("值班信息查询失败"),
        SAVE_KEYUNIT_FAIL("保存重点单位失败"),
        DELETE_KEYUNIT_FAIL("删除重点单位失败"),
        DELETE_PLAN_FAIL("删除预案失败"),
        FIND_DATA_NULL("数据为空"),
        DELETE_DATA_FAIL("数据删除失败"),
        UPLOAD_FILE_FAIL("上传文件失败"),
        DECODE_FAIL("转码失败"),
        UPDATE_STANDARD_FAIL("保存或修改提示信息失败"),

        OTHER_FAIL("未知异常") ;


        private String errorMessage;
        public static final int BASE_ORDINAL = 9000;

        @Override
        public int getErrorCode() {
            return BASE_ORDINAL + this.ordinal();
        }

        @Override
        public String getErrorMessage() {
            return this.errorMessage;
        }

        BasedataErrors(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }
}
