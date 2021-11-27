package com.dscomm.iecs.basedata.graphql.inputbean;


/**
 * 车辆拓展信息查询参数
 */
public class EquipmentVehicleExpandQueryInputInfo {

    private String  keyword ; //泡沫类型关键字（ 泡沫名称 模糊）

    private Float maxNum1 ;  //载液量 高值
    private Float minNum1 ;  //载液量 低值

    private Float maxNum2 ;  //泵流量 高值
    private Float minNum2 ;  //泵流量 低值

    private Float maxNum3 ;  //举升高度 高值
    private Float minNum3 ;  //举升高度 低值

    private Float maxNum4 ;  //消防炮流量 高值
    private Float minNum4 ;  //消防炮流量 低值

    private Float maxNum5 ;  //牵引力 高值
    private Float minNum5 ;  //牵引力 低值

    private Float maxNum6 ;  //吊机 高值
    private Float minNum6 ;  //吊机 低值

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Float getMaxNum1() {
        return maxNum1;
    }

    public void setMaxNum1(Float maxNum1) {
        this.maxNum1 = maxNum1;
    }

    public Float getMinNum1() {
        return minNum1;
    }

    public void setMinNum1(Float minNum1) {
        this.minNum1 = minNum1;
    }

    public Float getMaxNum2() {
        return maxNum2;
    }

    public void setMaxNum2(Float maxNum2) {
        this.maxNum2 = maxNum2;
    }

    public Float getMinNum2() {
        return minNum2;
    }

    public void setMinNum2(Float minNum2) {
        this.minNum2 = minNum2;
    }

    public Float getMaxNum3() {
        return maxNum3;
    }

    public void setMaxNum3(Float maxNum3) {
        this.maxNum3 = maxNum3;
    }

    public Float getMinNum3() {
        return minNum3;
    }

    public void setMinNum3(Float minNum3) {
        this.minNum3 = minNum3;
    }

    public Float getMaxNum4() {
        return maxNum4;
    }

    public void setMaxNum4(Float maxNum4) {
        this.maxNum4 = maxNum4;
    }

    public Float getMinNum4() {
        return minNum4;
    }

    public void setMinNum4(Float minNum4) {
        this.minNum4 = minNum4;
    }

    public Float getMaxNum5() {
        return maxNum5;
    }

    public void setMaxNum5(Float maxNum5) {
        this.maxNum5 = maxNum5;
    }

    public Float getMinNum5() {
        return minNum5;
    }

    public void setMinNum5(Float minNum5) {
        this.minNum5 = minNum5;
    }

    public Float getMaxNum6() {
        return maxNum6;
    }

    public void setMaxNum6(Float maxNum6) {
        this.maxNum6 = maxNum6;
    }

    public Float getMinNum6() {
        return minNum6;
    }

    public void setMinNum6(Float minNum6) {
        this.minNum6 = minNum6;
    }
}
