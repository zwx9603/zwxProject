package com.dscomm.iecs.out.graphql.typebean;

import lombok.Data;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/4/23 16:45
 * @Describe
 */
@Data
public class TokenBean {
    private String code ;
    private String msg;
    private TokenDate data;
}
