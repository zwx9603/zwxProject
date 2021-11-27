package com.dscomm.iecs.accept.service.bean;

/**
 * 描述:推荐结果项
 *
 * @author YangFuxi
 * Date Time 2021/8/7 15:13
 */
public class RecommendItemBean {
    private String code;//车辆类型代码
    private int num;//数量
    private String score;//分数

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
