package com.dscomm.iecs.accept.service.bean.cad;


/**
 * 描述:122受理扩展表对应BO
 *
 * @author ZhaiYanTao
 * Date Time 2019/7/26 16:13
 */
public class AcceptExtend122BO {
    /**
     * 受理单编号
     */
    private String id;
    /**
     * 受伤人数
     */
    private Integer injuriesNumber=0;
    /**
     * 死亡人数
     */
    private Integer deathNumber=0;
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
     * 时间戳
     */
    private Long lastedhandleTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getInjuriesNumber() {
        return injuriesNumber;
    }

    public void setInjuriesNumber(Integer injuriesNumber) {
        this.injuriesNumber = injuriesNumber;
    }

    public Integer getDeathNumber() {
        return deathNumber;
    }

    public void setDeathNumber(Integer deathNumber) {
        this.deathNumber = deathNumber;
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

    public Long getLastedhandleTime() {
        return lastedhandleTime;
    }

    public void setLastedhandleTime(Long lastedhandleTime) {
        this.lastedhandleTime = lastedhandleTime;
    }
}
