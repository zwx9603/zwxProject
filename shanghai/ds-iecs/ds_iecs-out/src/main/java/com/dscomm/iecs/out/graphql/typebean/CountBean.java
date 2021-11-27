package com.dscomm.iecs.out.graphql.typebean;

import lombok.Data;

import java.util.List;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/4/22 16:39
 * @Describe 统计情况返回结果集
 */
@Data
public class CountBean {
    private List<InterfaceCountBean> beans;

}
