package com.dscomm.iecs.base.websocket;

import com.dscomm.iecs.base.enums.BasicEnum;
import org.mx.spring.utils.I18nMessageUtils;

/**
 * 描述:websocket推送消息号枚举
 *
 * @author YangFuxi
 * Date Time 2019/9/5 16:59
 */
public enum WebsocketCodeEnum implements BasicEnum {
    // type(code,name)

    UNCALL_ACCEPT_INICIDENT("9000", "非话务警情推送"),
    UNCALL_ACCEPT_LISTLOCK("9536", "非话务警情锁定成功"),
    UNCALL_ACCEPT_LISTDELETE("9040", "非话务警情接收成功"),

    RECEIVE_CALL_INICIDENT("9001", "接收成功话务警情"),
    RECEIVE_UNCALL_INICIDENT("9002", "接收成功话务警情"),


    SAVE_NEW_INCIDENT("9010", "立案"),
    SAVE_MAJOR_INCIDENT("9110", "重大案情"),

    UPDATE_INCIDENT("9100", "警情信息更新"),
    UPDATE_INCIDENT_NATURE("9101", "案件性质变更"),
    UPDATE_INCIDENT_STATUS("9103", "案件状态变更"),

    INCIDENT_MERGE("9200", "警情合并"),
    INCIDENT_SPLIT("9300", "警情拆分"),
    INCIDENT_REMOVE("9400", "警情删除"),

    UPDATE_VEHICLE_EXPAND_INFO("9500", "车辆拓展变更"),
    UPDATE_VEHICLE_STATUS("9501", "车辆状态变更"),
    AUDIT_VEHICLE_STATUS("9502", "车辆状态变更申请"),
    AUDIT_VEHICLE_RESULT("9510","车辆状态变更申请结果"),

    CHECK_VEHICLE_STATUS("9503", "车辆状态变更申请"),
    CHECK_VEHICLE_RESULT("9511","车辆状态变更申请结果"),

    SAVE_EARLY_WARNING("9600", "新增预警"),
    CHANGE_EARLY_WARNING_STATUS("9601", "接收预警"),
    REMOVE_EARLY_WARNING("9602", "取消预警"),


    SAVE_NEW_CIRCULAR("9514", "新增通知"),
    CHANGE_NEW_CIRCULAR_STATUS("9515", "接收通知 "),

    SAVE_REINFORCEMENT_ASK("9700", "请求增援"),
    SAVE_TRANSFER_OUT("9805", "转警"),
    TRANSFER_OUT_RECEIVE("9809", "转警接收"),
    TRANSFER_OUT_REFUSE("9810", "转警拒接"),
    SAVE_DISLOCATION("9806", "错位接警"),
    SAVE_ASSIST("9807", "请求协助"),

    SAVE_HANDLE("9800", "调派车辆"),
    NOTIFY_HANDLE("9525", "通知调派车辆"),
    NOTIFY_HANDLE_STATUS("9526", "调派状态变更"),


    NOTICE_OVER_TIME_HANDLE_TIME("9546", "系统接收处警单超时"),
    OVER_TIME_HANDLE_TIME("9523", "人工接收处警单超时"),

    UPDATE_HANDLE_SYSTEM_TIME("9521", "系统接收处警单"),
    UPDATE_HANDLE_PERSON_TIME("9522", "人工接收处警单"),

    SAVE_INSTRUCTION("9801", "指令下达"),

    GIVE_SECURITY_HINTS("9524", "下达警示"),

    SAVE_ACCIDENT("9802", "结案反馈"),
    SAVE_DOCUMENT("9803", "文书信息"),
    SAVE_LOCALE("9804", "现场信息"),
    SAVE_PARTICIPANT ("9027", "参战人员反馈"),
    SAVE_FIRESAFE_MONITORING ("9028", "火场安全监控"),
    SAVE_PARTICIPANT_BACK ("9029", "有人员未随车归队"),
    WRITE_BACK_ACCIDENT_TO_INCIDENT("9104", " 结案反馈回写警情"),

    BUILD_IMPORTANT_INCIDENT("9103", "关注重要案件"),
    REMOVE_IMPORTANT_INCIDENT("9531", "取消关注重要案件"),

    UPDATE_INCIDENT_END_STATUS("9532", "案件结案"),
    UPDATE_INCIDENT_PALACE_FILE_STATUS("9533", "案件归档"),


    UPDATE_SYSTEM_CONFIGURATION("9534", "系统配置更新 "),
    ENABLED_SYSTEM_CONFIGURATION("9535", "配置启用变更 "),

    WRITE_ACCIDENT("9537", "通知主管机构填写结案反馈"),

    HANDLE_MINIATURE_STATION("9538", "调派微站"),
    HANDLE_MINIATURE_STATION_FEEDBACK("9539", "微站反馈"),


    AGENTSTATECHANGE("9902", "坐席状态变更"),
    USER_INFO_LOGIN("9540", "用户登录"),
    USER_INFO_LOGOUT("9541", "用户登出"),

    POLICE_REINFORCEMENT_ASK("9542", "公安请求协助"),
    DELETEAGENT("9543", "坐席信息删除"),

    COMMANDER_UPDATE("9545", "指挥员信息更新"),

    //TODO:历史枚举值，待功能确认后再删改
    INCIDENTCIRCULATE("9901", "警情接管"),

    INCIDENTCIRCULATECONFIRM("9903", "警情流转成功确认"),
    CASECLOSURERESULT("9904", "案件关闭结果"),
    CASEMODIFIED("9905", "案件更新"),// 消息发送IncidentInfoBO对象
    CASECLOSUREREQUEST("9906", "案件关闭申请"),
    DISTRIBUTEINCIDENTTODISPATCH("9007", "分配待处警警情"),
    MISSDISPATCHCHECKRESULT("9008", "接处分开分配策略漏管检查结果"),
    MISSDISPATCHCHECKFORMONITORRESULT("9009", "接处分开分配策略漏管检查结果给班长台"),
    REDLISINFO("9011", "红名单信息"),
    BLACKLISTRECORD("9012", "黑名单记录"),
    HARASSTELRECORDS("9013", "骚扰电话记录"),
    CALLRECORDSANDINCIDENT("9014", "报警人呼入记录，包含对应警情地址信息"),
    PERSONHISTORYALARM("9015", "人员历史报警"),
    CASECLOSE("9016", "案件关闭"),
    CASEREOPEN("9017", "案件重新打开"),
    JURISDICTIONFORAGENTCHANGE("9018", "坐席与辖区分配关系变更"),
    ORDER_SIGN_TIMEOUT("9019", "指令单签收超时"),
    FEEDBACK_TIMEOUT("9020", "反馈超时"),
    MONITORINSERT("9021", "班长主动接管"),
    MONITORINSERT_PUSHCASEINFO("9022", "接警席收到主动接管推送警情"),
    MONITORINSERT_PUSHCASEINFO_RESULT("9023", "成功收到接警席主动接管推送的警情"),
    ADDMAJORINCIDENT("9024", "新增重大警"),
    CANCELMAJORINCIDENT("9025", "取消重大警"),

//    REQUESTCHANGEINCIDENTLEVEL("9027", "申请修改警情级别"),
//    ACCEPTCHANGEINCIDENTLEVEL("9028", "同意修改警情级别"),
    REFUSECHANGEINCIDENTLEVEL("9029", "拒绝修改警情级别"),
    REQUESTADDBLACKLIST("9030", "申请添加黑名单"),
    AUDITADDBLACKLIST("9031", "审核添加黑名单"),
    APPLY_SLEEP("9032", "申请休眠"),
    AUDIT_SLEEP("9033", "审批休眠"),
    UNCALLBACKSILENTCALL("9034", "未回拨无声电话"),
    UNCALLBACKRELEASECALL("9035", "未回拨早释电话"),
    APPLY_TAKEOVER("9036", "请求接管"),
    AUDIT_TAKEOVER("9037", "审核接管"),
    PHONEHARASSEXCESS("9038", "电话骚扰次数过量"),
    CONFIRMCALLBACKSILENTRELEASECALL("9039", "无声/早释回拨确认"),

    ARRIVE_TIMEOUT("9041", "到场超时"),
    DISPATCH_TIMEOUT("9042", "处警超时"),
    MONITOR_FORCE_QUIT("9043", "班长强制退出"),
    REQUEST_RETURN_ORDER("9044", "申请退单"),
    AUDIT_RETURN_ORDER("9045", "审核退单"),
    CHANGEORDERSTATE("9046", "指令单指令状态变更"),
    CHANGEUNDISPATCHCADSTATE("9047", "修改待处警列表处警状态"),
    DELETEREPEATINCIDENTINFO("9048", "重复警情关联"),
    COUNTALLAGENTACCEPTS("9049", "坐席接警和违规统计"),
    CONFIRMFEEDBACKRECEIVED("9050", "反馈已接收确认"),
    DELETEMONITORCONTROLTIMEOUT("9051", "删除班长监控警单列表"),
    SUBSMSLOCATIONRESULT("9052", "短信定位结果"),
    PREATTENTCASEINFOCHANGED("9054", "预先介入警情变更"),
    AGENTDORMANCYTIMERESULT("9053", "坐席剩余休眠时间结果"),
    REMOVE_FROM_CURRENT("9054","真警转虚警"),
    AGENTDORMANCYTIMEOUTRESULT("9055","休眠超时"),
    PREATTENTCASEINFO("9056","预先介入警情"),
    PREATTENTCASECANCEL("9057","拒绝预先介入警情"),
    PREATTENTCASEACCEPT("9058","同意预先介入警情"),
    PREATTENTCASEHASACCEPTED("9059","该单位已经有人同意预先介入警情"),
    ORDER_UNSIGNED("9060", "规定时间内指令未签收通知"),
    MONITORBARGE("9061", "班长进行插话"),
    NEW_INFORM("9062", "新的通知"),
    NEW_NOTICE("9063", "新的公告"),
    IMPROTANT_REMINDER("9064","要事提醒"),

    //调度台专属code
    ADDIMPGROUP("20001", "新增重点群组"),
    DELETEIMPGROUP("20002", "删除重点群组"),
    INCIDENTGUIDANCE("9881", "警情引导");
    private String code;
    private String message;



    WebsocketCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    WebsocketCodeEnum(String message) {
        this.message = message;
    }


    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
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
