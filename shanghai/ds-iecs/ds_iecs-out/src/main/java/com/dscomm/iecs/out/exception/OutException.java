package com.dscomm.iecs.out.exception;

import org.mx.error.UserInterfaceError;
import org.mx.error.UserInterfaceException;

/**
 * 描述:接警受理模块异常类
 *
 * @author YangFuxi
 * Date Time 2020/4/9 12:38
 */
public class OutException extends UserInterfaceException {
    public OutException(int errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    public OutException() {
        this(AccetpErrors.OTHER_FAIL);
    }

    public OutException(AccetpErrors error) {
        this(error.getErrorCode(), error.getErrorMessage());
    }

    public enum AccetpErrors implements UserInterfaceError {
        DATA_NULL("必填字段为空"),
        FIND_ALARM_RECORD_FAIL("查询报警记录数据失败"),
        FIND_INCIDENT_LIST_FAIL("获取警情列表失败"),
        FIND_DISPATCH_LIST_FAIL("获取警情调派信息列表失败"),
        FIND_VEHICLE_LIST_FAIL("获取车辆列表失败"),
        FIND_DOC_RECORD_FAIL("获取警情文书信息列表失败"),
        FIND_LOC_RECORD_FAIL("获取警情现场信息列表失败"),
        FIND_SOUND_RECORD_LIST_FAIL("获取录音列表失败"),
        FIND_TEL_RECORD_FAIL("获取通话记录列表失败"),
        LOGIN_FAIL("用户不存在或者密码错误"),
        ORG_NULL("机构不存在"),
        TOKEN_FAIL("token 无效或已过期"),
        QUERY_FAIL("获取统计结果失败"),
        COUNT_FAIL("分类统计失败"),
        FIND_DATA_NULL("数据为空"),
        FIND_DATA_FAIL("数据查询失败"),
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
