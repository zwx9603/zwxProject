package com.dscomm.iecs.accept.service.bean;

/**
 * 描述:智能推荐服务参数
 *
 * @author YangFuxi
 * Date Time 2021/8/3 16:34
 */
public class SmartRecommendParam {
    private Object msg;//参数

    public SmartRecommendParam() {
    }

    public SmartRecommendParam(Object msg) {
        this.msg = msg;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }
}
