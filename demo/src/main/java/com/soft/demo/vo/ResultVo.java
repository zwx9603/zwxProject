package com.soft.demo.vo;

import lombok.Data;

@Data
public class ResultVo<T> {

    private String code;
    private String message;
    private T data;

    public ResultVo() {
    }

    public ResultVo( T data) {
        this.code = "0";
        this.message = message;
        this.data = data;
    }
    public ResultVo(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultVo(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
