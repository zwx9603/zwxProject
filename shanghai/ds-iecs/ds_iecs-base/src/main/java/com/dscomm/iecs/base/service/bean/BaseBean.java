package com.dscomm.iecs.base.service.bean;

public class BaseBean {

    private String id;

    private Integer valid  ;  //是否启用 0不启用 1启用

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }
}
