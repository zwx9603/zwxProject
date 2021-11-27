package com.dscomm.iecs.base.bo;

import java.io.Serializable;

/**
 * 描述:基础状态Bean
 *
 * @author YangFuxi
 * Date Time 2019/8/14 11:23
 */
public class BaseStatusBean implements Serializable {

    private String code;
    private String name;

    public BaseStatusBean( String code , String name ){
        this.code = code ;
        this.name = name  ;

    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
