package com.dscomm.iecs.agent.exception;

import org.mx.error.UserInterfaceError;
import org.mx.error.UserInterfaceException;
import org.mx.spring.utils.I18nMessageUtils;

/**
 * 描述:坐席管理模块异常类定义
 *
 * @author YangFuxi
 * Date Time 2019/7/21 14:50
 */
public class UserInterfaceAgentException extends UserInterfaceException {

    public UserInterfaceAgentException(AgentErrors error) {
        super(error.getErrorCode(), error.getErrorMessage());
    }

    public UserInterfaceAgentException( int errorCode , String errorMessage ) {
        super( errorCode  , errorMessage );
    }

    public UserInterfaceAgentException() {
        this(AgentErrors.OTHER_FAIL);
    }

    public enum AgentErrors implements UserInterfaceError {
        OTHER_FAIL,
        FIND_DATA_NULL("数据为空"),
        FIND_DATA_FAIL("数据查询失败"),
        SAVE_DATA_FAIL("数据保存失败"),
        AGENT_CACHE_OPERATE_FAIL("坐席缓存操作失败"),
        AGENT_CACHE_GET_FAIL("获取坐席缓存失败."),
        AGENT_CACHE_CLEAR_FAIL("清空坐席缓存失败."),
        DATA_NULL("必填字段为空"),
        FIND_AGENT_FAIL("获取座席信息失败"),
        FIND_USER_FAIL("获取用户信息失败"),
        SAVE_AGENT_FAIL("更改座席信息失败"),
        AGENT_LOGIN_FAIL("坐席登陆失败"),
        AGENT_LOGOUT_FAIL("坐席注销失败"),
        CHANGE_AGENTSTATE_FAIL("修改坐席状态失败"),
        SAVE_CONFIG_FAIL("保存或更改系统配置信息失败"),
        FIND_CONFIG_FAIL("获取系统配置信息失败"),
        GET_USERINFO_FAIL("获取用户信息失败,请重新登录"),
        GET_IMEI_FAIL("获取imei信息失败,请重新登录"),
        USER_AGENT_NOTEXIT("用户或坐席不存在"),
        LOGIN_FAIL_ALREADY_LOGIN("用户已登录"),
        AUTHENTICATE_FAIL("身份认证失败"),
        AUTHENTICATE_FAIL_TOKEN_NULL("身份认证失败,token为空"),
        LOGIN_FAIL_AGENT_LOGIN_("坐席已有用户登录"),
        AUTHENTICATE_FAIL_TOKEN_FORMAT_FAIL("身份认证失败,token格式错误"),
        LOGIN_INIT_FAIL("登陆初始化失败."),
        DESTROY_USER_FAIL("注销用户失败."),
        GET_KEY_FAIL("获取加密key失败."),
        GET_SILENTCALLRECORD_FAIL("获取无声电话记录失败"),
        SAVE_RELEASECALLRECORD_FAIL("保存早释电话记录失败"),
        GET_RELEASECALLRECORD_FAIL("获取早释电话记录失败"),
        CONFIRM_SILENTRELEASECALLRECORD_FAIL("无声/早释电话回拨确认失败"),
        DATA_NOTUNIQUE_ERROR("db数据不唯一"),
        DATA_NOTEXIST_ERROR("db数据不存在"),
        ENUMCODE_AGAINSTRULE_ERROR("枚举code不符合规则错误"),
        SAVE_AUDITLOG_ERROR("保存审计日志错误"),
        SEND_WEBSOCKET_ERROR("发送websocket消息错误"),
        GET_DISTRIBUTE_CONFIG_FAIL("获取警情分配配置项失败."),
        DISTRIBUTE_INCIDENT_FAIL("分配警情失败."),
        GET_DISTRIBUTE_INFO_FAIL("获取警情分配模式信息失败"),
        QUERY_ALLDISTRICTAGENT_FAIL("获取处警台与处警辖区绑定关系集合失败"),
        QUERY_ALLDISTRICTACCOUNT_FAIL("获取处警员与处警辖区绑定关系集合失败"),
        FIND_LEVELONECASENATURE_FAIL("获取一级案由集合失败"),
        QUERY_ALLCASENATURE_FAIL("获取处警员与案由绑定关系集合失败"),
        QUERY_ALLJURISDICTION_FAIL("查询所有处警辖区失败"),
        QUERY_ALLJURISDICTIONFORAGENT_FAIL("查询所有辖区与接管其的坐席集合失败"),
        FIND_ORGCODESBYAGENTJURISDICTIONS_FAIL("根据坐席号获取处警台接管的单位编号失败"),
        FIND_ORGCODESBYACCOUNTNUMJURISDICTIONS_FAIL("根据处警员账号获取处警台接管的单位编号失败"),
        QUERY_AGENTBOSFORJURISDICTION_FAIL("根据辖区号获取可绑定坐席列表"),
        GET_ALL_ACDJZ_FAIL("获取所有acd警种失败"),
        INIT_AGENTCACHE_FAIL("初始化坐席缓存失败"),
        DELETE_JURISDICTIONFORAGENT_FAIL("删除坐席与辖区绑定关系失败"),
        ADD_JURISDICTIONFORAGENT_FAIL("增加坐席与辖区绑定关系失败"),
        GET_USER_LANGUAGE_FAIL("获取用户语言失败"),
        APPLAY_SLEEP_FAIL("申请休眠失败."),
        AUDIT_SLEEP_FAIL("审核休眠失败"),
        AUDIT_RESULT_ILLEGAL("审核结果数据非法"),
        PARAM_ILLEGAL("参数非法"),
        FORCE_QUIT_FAIL("坐席强制退出失败"),
        FIND_CAUSE_AGENT_FAIL("获取案由坐席信息失败"),
        ADD_CAUSE_TO_AGENT_FAIL("绑定接管案由到坐席失败"),
        REMOVE_CAUSE_TO_AGENT_FAIL("解除接管案由和坐席绑定关系失败"),
        REMOVE_CAUSE_TO_AGENT_FAIL_NO_AGENT_TAKEOVER("解除接管案由和坐席绑定关系失败,解绑之后无座席接管"),
        GET_ONLINEPERSONACCOUNT_FAIL("获取在线人员账号"),
        QUERY_AND_STATISTICS_FAIL("查询统计失败"),
        STATISTICS_AGENT_ACCEPTINFO_FAIL("统计坐席接警情况失败"),
        HEART_FAIL("心跳服务失败"),
        CHECK_SYSTEMCONFIG_FAIL("系统配置检测失败"),
        OPERATE_SYSTEMCONFIGCAHCE_FAIL("操作系统配置缓存失败"),
        STATISTICS_INVALIDCALLINDAY_FAIL("统计24小时内的报警电话的无效警数量失败"),
        STATISTICS_INVALIDCALLINMONTH_FAIL("统计30天内的报警电话的无效警数量失败"),

        SEND_SMSLOCATIONRESULT_FAIL("发送短信定位结果失败"),

        ;
        private String errorMessage;
        public static final int BASE_ORDINAL = 3000;

        @Override
        public int getErrorCode() {
            return BASE_ORDINAL + this.ordinal();
        }

        @Override
        public String getErrorMessage() {
            return I18nMessageUtils.getI18nMessage(super.name(), this.errorMessage);
        }

        AgentErrors() {
        }

        AgentErrors(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }
}
