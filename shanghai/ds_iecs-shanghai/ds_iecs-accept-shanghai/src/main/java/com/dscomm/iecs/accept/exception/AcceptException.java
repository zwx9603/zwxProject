package com.dscomm.iecs.accept.exception;

import org.mx.error.UserInterfaceError;
import org.mx.error.UserInterfaceException;

/**
 * 描述:接警受理模块异常类
 *
 * @author YangFuxi
 * Date Time 2020/4/9 12:38
 */
public class AcceptException extends UserInterfaceException {
    public AcceptException(int errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    public AcceptException() {
        this(AccetpErrors.OTHER_FAIL);
    }

    public AcceptException(AccetpErrors error) {
        this(error.getErrorCode(), error.getErrorMessage());
    }

    public enum AccetpErrors implements UserInterfaceError {
        DATA_NULL("必填字段为空"),
        FIND_DATA_NULL("数据为空"),
        FIND_DATA_FAIL("数据查询失败"),
        SAVE_DATA_FAIL("数据保存失败"),
        DELETE_DATA_FAIL("数据删除失败"),
        STATISTICS_VEHICLE_STATUS_FAIL("车辆状态统计失败"),
        FIND_DIMENSION_VEHICLE_STATUS_STATISTICS_FAIL("车辆状态分类统计失败"),
        STATISTICS_VEHICLE_TYPE_FAIL("车辆类型统计失败"),
        STATISTICS_VEHICLE_ORGANIZATION_FAIL("车辆所属机构统计失败"),
        FIND_TAG_LABEL_FAIL("标签查询失败"),
        SAVE_TAG_LABEL_FAIL("标签保存失败"),
        REMOVE_TAG_LABEL_FAIL("标签撤销失败"),
        FIND_COMMON_TIPS_FAIL("接/处警提示信息查询失败"),
        SAVE_VIOLATION_FAIL("违规操作保存失败"),
        FIND_VIOLATION_FAIL("违规操作查询失败"),
        FIND_TELEPHONE_FAIL("电话报警记录查询失败"),
        SAVE_TELEPHONE_FAIL("电话报警记录保存失败"),
        FIND_DRILL_PLAN_FAIL("集合演练方案查询失败"),
        SAVE_DRILL_PLAN_FAIL("集合演练方案保存失败"),
        REMOVE_DRILL_PLAN_FAIL("集合演练方案撤销失败"),
        SAVE_EARLY_WARNING_FAIL("预警信息保存失败"),
        CHANGE_EARLY_WARNING_STATUS_FAIL("预警信息状态更改失败"),
        REMOVE_EARLY_WARNING_FAIL("预警信息取消失败"),
        FIND_EARLY_WARNING_ORGANIZATION_FAIL("获取预警中队失败"),
        FIND_WEATHER_FAIL("天气信息查询失败"),
        SAVE_WEATHER_FAIL("天气信息保存失败"),
        SAVE_INCIDENT_FAIL("立案失败"),
        UPDATE_INCIDENT_FAIL("警情信息更新失败"),
        FIND_INCIDENTS_UNFINISHED_FAIL("未完成警情信息查询失败"),
        FIND_INCIDENTS_FAIL("警情信息查询失败"),
        COMPLETE_PROPERTIES_FAIL("补全信息属性失败"),
        FIND_PARTICIPANT_ORGANIZATION_FAIL("警情参与机构查询失败"),

        FIND_HIERARCHICAL_DISPATCH_FAIL("等级调派查询失败"),
        SAVE_HIERARCHICAL_DISPATCH_FAIL("等级调派保存失败"),
        HIERARCHICAL_DISPATCH_HAVING("单位等级调派已存在"),
        FIND_HIERARCHICAL_DISPATCH_VEHICLE_FAIL("等级调派车辆信息失败"),
        FIND_INCIDENT_STATUS_CHANGE_FAIL("警情状态变化查询失败"),
        SAVE_DISPATCH_DAILY_RECORD_FAIL("调度日志保存失败"),
        SAVE_SMS_FAIL("短信记录保存失败"),
        FIND_ACCEPTANCE_FAIL("案件报警信息获取失败"),
        FIND_HANDLE_FAIL("处警记录获取失败"),
        SAVE_HANDLE_FAIL("处警记录保存失败"),
        SIGN_HANDLE_FAIL("处警记录签收失败"),
        FIND_HANDLE_ORGANIZATION_FAIL("调派单位信息获取失败"),
        FIND_INCIDENT_HANDLE_ORGANIZATION_FAIL("出动单位获取失败"),
        FIND_HANDLE_ORGANIZATION_EQUIPMENT_FAIL("调派单位装备信息获取失败"),
        FIND_HANDLE_ORGANIZATION_VEHICLE_FAIL("调派单位车辆信息获取失败"),
        FIND_INCIDENT_HANDLE_VEHICLE_FAIL("出动车辆获取失败"),
        SAVE_INCIDENT_MERGE_FAIL("案件合并失败"),
        CHANGE_ACCEPTANCE_INCIDENT_ID_FAIL("变更受理单关联的案件id失败"),
        CHANGE_TELEPHONE_INCIDENT_ID_FAIL("变更电话报警记录关联的案件id失败"),
        CHANGE_HANDLE_INCIDENT_ID_FAIL("变更处警记录关联的案件id失败"),
        CHANGE_HANDLE_ORGANIZATIONS_INCIDENT_ID_FAIL("变更 调派信息、车辆、装备 关联的案件id失败"),
        SAVE_INCIDENT_SPLIT_FAIL("案件拆分失败"),
        FIND_INCIDENT_MERGE_FAIL("案件合并查询失败"),
        RECOVER_ACCEPTANCE_INCIDENT_ID_FAIL("恢复受理单关联的案件id失败"),
        RECOVER_TELEPHONE_INCIDENT_ID_FAIL("恢复电话报警记录关联的案件id失败"),
        RECOVER_HANDLE_INCIDENT_ID_FAIL("恢复处警记录关联的案件id失败"),
        UPDATE_HANDLE_FILE_FAIL("更新处警tts播报文件失败"),
        SAVE_REINFORCEMENT_ASK_FAIL("增援请求保存失败"),
        FIND_DOCUMENT_FAIL("警情文书查询失败"),
        SAVE_DOCUMENT_FAIL("警情文书保存失败"),

        FIND_VEHICLE_INCIDENT_MAPPING_FAIL("车辆案件状态映射查询失败"),
        UPDATE_INCIDENT_VEHICLE_STATUS_FAIL("更新案件车辆状态失败"),
        FIND_INCIDENT_TIME_TREND_FAIL("警情时间趋势图统计失败"),
        FIND_INCIDENT_TYPE_STATISTICS_FAIL("警情类型信息统计失败"),
        FIND_HANDLE_POWER_STATISTICS_FAIL("出动力量信息统计失败"),
        REMOVE_INCIDENT_FAIL("案件删除失败"),
        UPDATE_INCIDENT_STATUS_FAIL("案件状态更新失败"),
        UPDATE_INCIDENT_NATURE_FAIL("虚实警转换失败"),
        UPDATE_INCIDENT_FOCUS_ON_FAIL("案件关注修改失败"),
        FIND_INCIDENT_TYPE_CONTRAST_STATISTICS_FAIL("案件同环比统计失败"),
        FIND_SQUADRON_FILL_IN_FAIL("中队填报查询失败"),
        SAVE_SQUADRON_FILL_IN_FAIL("中队填报保存失败"),
        SAVE_UNTRAFFIC_ALAR_INCIDENT_FAIL("非话务警情保存失败"),
        FIND_UNTRAFFIC_ALAR_INCIDENT_FAIL("非话务警情查询失败"),
        WRITE_BACK_INCIDENT_ID_FAIL("非话务警情回写案件id失败"),
        REQUEST_OUT_SIDE_FAIL("请求外部接口失败"),
        WRITE_BACK_ACCIDENT_TO_INCIDENT_FAIL("结案反馈回写入警情信息失败"),
        FIND_INCIDENT_DOSSIER_FAIL("警情卷宗查询失败"),
        SAVE_CALL_BACK_FAIL("电话回拨保存失败"),
        SAVE_ACCIDENT_FAIL("结案反馈保存失败"),
        SAVE_SAFETY_FAIL("安全提示保存失败"),
        FIND_ACCIDENT_FAIL("结案反馈查询失败"),
        FIND_BLACKLIST_FAIL("黑名单查询失败"),
        SAVE_BLACKLIST_FAIL("黑名单保存失败"),
        REMOVE_BLACKLIST_FAIL("黑名单移除失败"),
        ATTENTIONTYPE_NOTMATCH_ERROR("关注类型错误/不匹配"),
        SAVE_ATTENTION_FAIL("警情关注失败"),
        DELETE_ATTENTION_FAIL("取消关注失败"),
        SAVE_COMBATREADINESS_FALL("保存战备信息失败"),
        FIND_COMBATREADINESS_FALL("查询战备信息失败"),
        REMOVE_COMBATREADINESS_FALL("删除战备信息失败"),
        UPDATE_COMBATREADINESS_FALL("修改战备信息失败"),
        SAVE_PLANWORD_FALL("保存文本预案失败"),
        FIND_PLANWORD_FALL("查询文本预案失败"),
        REMOVE_PLANWORD_FALL("删除文本预案失败"),
        UPDATE_PLANWORD_FALL("修改文本预案失败"),
        FIND_INCIDENT_IMPORTANT_FAIL("重要警情规则查询失败"),
        SAVE_INCIDENT_IMPORTANT_FAIL("重要警情规则保存/修改失败"),
        REMOVE_INCIDENT_IMPORTANT_FAIL("重要警情规则删除失败"),
        SAVE_INCIDENTCIRCLE_FALL("救援圈设定失败"),
        SAVE_COMMAND_FALL("指揮設定失敗"),
        SAVE_COMMANDER_FALL("指揮員設定失敗"),
        REMOVE_COMMANDER_FALL("指揮員刪除失敗"),
        FIND_COMMANDER_FALL("查詢失敗"),
        RALLYPOINT_ERROR("该指挥下集结点名称存在"),
        RALLYPOINT_STATUS_IS_SENT("该集结点为已下达状态不能删除"),
        RALLYPOINTSTATUS_IS_SENT("已下达的集结点不能更新为已保存"),
        UNABLE_TO_MODIFY_DATA_THAT_DOES_NOT_EXIST("无法更新或修改不存在的数据"),
        COMMAND_FAILE("启动指挥失败"),
        COUNT_KEYUNITLEVEL_FAIL("统计各等级重点单位信息失败"),
        FIND_EARLYWARNINGIMPORTANT_FALL("重大灾害预警信息查詢失敗"),
        REMOVE_EARLYWARNINGIMPORTANT_FALL("重大灾害预警信息删除失敗"),
        SAVE_EARLYWARNINGIMPORTANT_FALL("保存重大灾害预警信息失败"),
        FIND_ACTIONSUMMARY_FALL("战评查询失败"),
        REMOVE_ACTIONSUMMARY_FALL("战评删除失败"),
        SAVE_SECURITY_FAIL("下达特别警示失败"),
        FIND_SECURITY_LIST_FAIL("获取特别警示列表失败"),
        FIND_EXPERT_LIST_FAIL("获取专家列表失败"),
        GET_EXPERT_DETAILS_FAIL("获取专家详情失败"),
        FIND_ORGANIZATION_DISASTER_STATISTICS_FALL("机构警情分类统计失败"),
        COUNT_INCIDENT_FAIL("统计警情失败"),
        FIND_DISPATCHAGENCY_FAIL("获取出动力量信息失败"),
        SAVE_IMPORTANTREMINDER_FAIL("保存要事提醒失败"),
        FIND_IMPORTANTREMINDER_FATL("获取要事提醒失败"),
        DELETE_IMPORTANTREMINDER_FAIL("删除要事提醒失败"),
        GET_LEADER_LIST_FALI("获取领导列表失败"),
        FIND_SUMMARY_FALL("查询警情总览失败"),
        GET_SECURITY_LIST_FAIL("特别警示历史获取失败"),
        FIND_CONSENSUSINFORMATIOM_FALL("涉消舆情查询失败"),
        GET_DANGEROUS_CHEMICAL_LIST_FAIL("获取危化品列表失败"),
        SAVE_CommonPhrase_FAIL("保存常用短语失败"),
        FIND_CommonPhrase_LIST_FAIL("获取常用短语列表失败"),
        GET_CommonPhrase_DETAILS_FAIL("获取常用短语详情失败"),
        FIND_ONDUTYFORCE_FAIL("获取执勤力量失败"),
        FIND_PLAN_DISPATCH_FAIL("预案调派查询失败"),

        INCIDENT_OR_RECEIVER_IS_NULL("警情流转失败，警单信息或接管账号为空."),
        CIRCULATION_FAIL("警情流转失败"),
        CIRCULATION_CONFIRM_FAIL("警情流转确认失败."),
        DISTRIBUTE_FAIL("警情分配出错"),
        FIND_INCIDENT_HANDLE_ORGANIZATION_CLID("当前警情没有出动该车辆"),
        MISS_DISPATCHCHECK_FAIL("分配校验失败"),
        SAVE_UNCALLACCEPT_FAIL("保存非话务记录失败"),
        FIND_UNCALLACCEPT_FAIL("查询非话务记录失败"),
        DELETE_UNCALLACCEPT_FAIL("删除非话务记录失败"),
        LOCK_UNCALLACCEPT_FAIL("锁定非话务记录失败"),
        SAVE_HANDOVERRECORD_FAIL("保存交接班日志失败"),
        FIND_HANDOVERRECORD_FAIL("查询交接班日志失败"),
        DELETE_HANDOVERRECORD_FAIL("删除交接班日志失败"),

        DECODE_FAIL("转码失败"),

        INCIDENTTYPE_DISPOSALOBJECT_EXIST("案件类型编码与处置对象编码已存在"),

        DELETE_ADDRESSBYINCIDENTIDANDTYPE_FAIL("根据事件id和地址类型删除辅助地址失败"),
        SAVE_LOCATION_FAIL("保存定位轨迹失败"),
        GET_LOCATION_FAIL("获取案件/报警人历史位置失败"),
        SAVE_SMSLOCATIONRESULT_FAIL("保存发送短信定位失败"),
        SEND_SMSLOCATIONRESULT_FAIL("发送短信定位结果失败"),

        SAVE_COMBATINFORMATION_FAIL("保存作战信息卡失败"),
        DELETE_COMBATINFORMATION_FAIL("删除作战信息卡失败"),
        FIND_COMBATINFORMATION_FAIL("查询作战信息卡失败"),

        CASE_AUTO_LEVEL_FAIL("案件等级自动升级数据重叠"),

        VEHICLE_STATUS_NO_HANLE("车辆状态不可调派"),

        SAVE_LED_FAIL("保存LED失败"),
        QUERY_LED_FAIL("查询LED列表失败"),
        QUERY_VEHICLE_STATE_CHANGE_FAIL("查询车辆状态变更记录失败"),
        SAVE_RELEASE_CALL_FAIL("保存排队早释记录失败"),
        FIND_RELEASE_CALL_FAIL("获取排队早释记录失败"),

        FIND_800M_FAIL("获取第三方录音记录失败"),

        OTHER_FAIL("未知异常");



        private String errorMessage;
        public static final int BASE_ORDINAL = 1000;

        @Override
        public int getErrorCode() {
            return BASE_ORDINAL + this.ordinal();
        }

        @Override
        public String getErrorMessage() {
            return this.errorMessage;
        }

        AccetpErrors(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }
}
