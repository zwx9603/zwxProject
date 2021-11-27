package com.zwx.basedata.entity;

import lombok.Data;

@Data
public class FunctionEntity implements Comparable<FunctionEntity>{
    private String code;
    private String name;
    private Integer dispatNum=0; // 等级调派
    private Integer smartNum = 0; // 智能调派
    private Integer czNum=0;
    private Integer flag = 0;

    public FunctionEntity(){}

    public FunctionEntity(String code, String name, Integer dispatNum) {
        this.code = code;
        this.name = name;
        this.dispatNum = dispatNum;
    }

    @Override
    public int compareTo(FunctionEntity o1) {
        if(this.code.equals(o1.getCode())){

        }
        return 0;
    }
}
