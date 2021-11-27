package com.dscomm.iecs.accept.graphql.typebean;

public class FunctionInfoBean {

    private String id;

    private String cldm; // 车辆代码

    private String gndm; // 功能代码

    private String gndj; // 功能等级

    private String gnmc; // 功能名称

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCldm() {
        return cldm;
    }

    public void setCldm(String cldm) {
        this.cldm = cldm;
    }

    public String getGndm() {
        return gndm;
    }

    public void setGndm(String gndm) {
        this.gndm = gndm;
    }

    public String getGndj() {
        return gndj;
    }

    public void setGndj(String gndj) {
        this.gndj = gndj;
    }

    public String getGnmc() {
        return gnmc;
    }

    public void setGnmc(String gnmc) {
        this.gnmc = gnmc;
    }
}
