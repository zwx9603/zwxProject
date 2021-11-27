package com.zwx.basedata.exception;

public class TestException extends RuntimeException{
    private Integer code;
    private String message;

    public TestException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    public TestException( String message) {
        this.message = message;
    }
    public TestException() {
        this(TestErrors.DATA_NULL);
    }
    public TestException(TestErrors errors) {
        this(errors.getMsg());
    }

    public enum TestErrors{
        DATA_NULL("必填字段为空");

       private String msg;

        TestErrors(String msg) {
            this.msg = msg;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
