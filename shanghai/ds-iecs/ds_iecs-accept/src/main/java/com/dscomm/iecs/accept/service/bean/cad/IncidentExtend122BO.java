package com.dscomm.iecs.accept.service.bean.cad;


import java.io.Serializable;

/**
 * 描述:122事件扩展表对应BO
 *
 * @author ZhaiYanTao
 * Date Time 2019/7/26 16:13
 */
public class IncidentExtend122BO implements Serializable {
    /**
     * 事件单编号
     */
    private String id;
    /**
     * 肇事车牌类型
     */
    private String carCategory;
    /**
     * 肇事车牌类型name
     */
    private String carCategoryName;
    /**
     * 肇事车牌号
     */
    private String carNumber;
    /**
     * 是否有化学危险品
     */
    private Integer chemistryDangerous;
    /**
     * 是否有化学危险品name
     */
    private String chemistryDangerousName;
    /**
     * 出动车次
     */
    private Integer dispatchTrainNumber;
    /**
     * 出动人次
     */
    private Integer dispatchedPersonNumber;
    /**
     * 轻伤人数
     */
    private Integer minorInjuriesNumber;
    /**
     * 重伤人数
     */
    private Integer seriousInjuryNumber;
    /**
     * 死亡人数
     */
    private Integer deathNumber;
    /**
     * 时间戳
     */
    private Long lastedhandleTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCarCategory() {
        return carCategory;
    }

    public void setCarCategory(String carCategory) {
        this.carCategory = carCategory;
    }

    public String getCarCategoryName() {
        return carCategoryName;
    }

    public void setCarCategoryName(String carCategoryName) {
        this.carCategoryName = carCategoryName;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public Integer getChemistryDangerous() {
        return chemistryDangerous;
    }

    public void setChemistryDangerous(Integer chemistryDangerous) {
        this.chemistryDangerous = chemistryDangerous;
    }

    public String getChemistryDangerousName() {
        return chemistryDangerousName;
    }

    public void setChemistryDangerousName(String chemistryDangerousName) {
        this.chemistryDangerousName = chemistryDangerousName;
    }

    public Integer getDispatchTrainNumber() {
        return dispatchTrainNumber;
    }

    public void setDispatchTrainNumber(Integer dispatchTrainNumber) {
        this.dispatchTrainNumber = dispatchTrainNumber;
    }

    public Integer getDispatchedPersonNumber() {
        return dispatchedPersonNumber;
    }

    public void setDispatchedPersonNumber(Integer dispatchedPersonNumber) {
        this.dispatchedPersonNumber = dispatchedPersonNumber;
    }

    public Integer getMinorInjuriesNumber() {
        return minorInjuriesNumber;
    }

    public void setMinorInjuriesNumber(Integer minorInjuriesNumber) {
        this.minorInjuriesNumber = minorInjuriesNumber;
    }

    public Integer getSeriousInjuryNumber() {
        return seriousInjuryNumber;
    }

    public void setSeriousInjuryNumber(Integer seriousInjuryNumber) {
        this.seriousInjuryNumber = seriousInjuryNumber;
    }

    public Integer getDeathNumber() {
        return deathNumber;
    }

    public void setDeathNumber(Integer deathNumber) {
        this.deathNumber = deathNumber;
    }

    public Long getLastedhandleTime() {
        return lastedhandleTime;
    }

    public void setLastedhandleTime(Long lastedhandleTime) {
        this.lastedhandleTime = lastedhandleTime;
    }
}
