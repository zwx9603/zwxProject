package com.dscomm.iecs.basedata.graphql.typebean;

import java.io.Serializable;
import java.util.List;

public class FdfsResponseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<FdfsDataBean> dataStore;

    private String msg;

    private String ret;

    public List<FdfsDataBean> getDataStore() {
        return dataStore;
    }

    public void setDataStore(List<FdfsDataBean> dataStore) {
        this.dataStore = dataStore;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }
}
