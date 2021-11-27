package com.dscomm.iecs.hangzhou.restful.vo;

/**
 * 描述： 信息 通知数据对象
 *
 */
public class SmsMessageVO {


    private String applicationId ; //应用账号(AppKey)

    private String password ; //应用密码(AppSecret)

    private String requestTime ; //请求时间 与服务器时间差不能超过5分钟，格式如：20191213141516

    private String sign ; //接口校验令牌

    private String funCode = "1002"; //服务代码 群发短信必须填写为1002

    private String sendTime ; //发送时间

    private String [] mobiles ; // 手机号码

    private String extendCode ; //扩展号

    private String content ; //短信内容

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getFunCode() {
        return funCode;
    }

    public void setFunCode(String funCode) {
        this.funCode = funCode;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String[] getMobiles() {
        return mobiles;
    }

    public void setMobiles(String[] mobiles) {
        this.mobiles = mobiles;
    }

    public String getExtendCode() {
        return extendCode;
    }

    public void setExtendCode(String extendCode) {
        this.extendCode = extendCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
