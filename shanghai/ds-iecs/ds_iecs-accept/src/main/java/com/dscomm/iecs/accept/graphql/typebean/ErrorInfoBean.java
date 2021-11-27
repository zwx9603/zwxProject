package com.dscomm.iecs.accept.graphql.typebean;

/**
 * 描述：
 *
 * @author YangFuXi Date Time 2018/7/6 9:21
 */
public class ErrorInfoBean {
    //异常类型
    private String errorType;
    //异常信息
    private String errorDescribe;

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getErrorDescribe() {
        return errorDescribe;
    }

    public void setErrorDescribe(String errorDescribe) {
        this.errorDescribe = errorDescribe;
    }
}
