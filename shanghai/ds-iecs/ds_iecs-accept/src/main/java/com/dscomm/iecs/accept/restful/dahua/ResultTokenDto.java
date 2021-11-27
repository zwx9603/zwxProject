package com.dscomm.iecs.accept.restful.dahua;

import java.util.List;

public class ResultTokenDto {
    private List<TokenDto> data;

    private String ret_msg;

    private String ret_code;

    public List<TokenDto> getData() {
        return data;
    }

    public void setData(List<TokenDto> data) {
        this.data = data;
    }

    public String getRet_msg() {
        return ret_msg;
    }

    public void setRet_msg(String ret_msg) {
        this.ret_msg = ret_msg;
    }

    public String getRet_code() {
        return ret_code;
    }

    public void setRet_code(String ret_code) {
        this.ret_code = ret_code;
    }
}
