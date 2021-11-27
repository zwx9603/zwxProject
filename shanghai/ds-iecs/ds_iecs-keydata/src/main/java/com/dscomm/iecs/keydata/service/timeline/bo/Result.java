package com.dscomm.iecs.keydata.service.timeline.bo;

import java.io.Serializable;

/**
 * 返回结果相关BO描述
 *
 * @author YangFuxi
 * Date Time 2019/8/15 17:05
 */
public class Result implements Serializable {

    private static final long serialVersionUID = 1L;
    public String ret = ResultStatus.OK;
    public String msg = "";
    public Object dataStore = null;

    public static Result ok(Object dataStore) {
        Result r = new Result();
        r.setRet(ResultStatus.OK);
        r.setDataStore(dataStore);
        r.setMsg("Success");
        return r;
    }

    public static Result error(String msg) {
        Result r = new Result();
        r.setRet(ResultStatus.ERROR);
        r.setMsg(msg);
        return r;
    }

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getDataStore() {
        return dataStore;
    }

    public void setDataStore(Object dataStore) {
        this.dataStore = dataStore;
    }

}
