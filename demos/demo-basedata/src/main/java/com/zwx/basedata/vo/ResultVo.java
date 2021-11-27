package com.zwx.basedata.vo;

import lombok.Data;

@Data
public class ResultVo<T> {

    private String code;
    private String msg;
    private T data;

    public ResultVo() {
    }

    public ResultVo( T data) {
        this.code = "200";
        this.msg = "success";
        this.data = data;
    }
    public ResultVo(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultVo(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}
