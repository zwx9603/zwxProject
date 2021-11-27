package com.dscomm.iecs.out.graphql.typebean;

import lombok.Data;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/4/22 16:40
 * @Describe 接口统计情况公用类
 */
@Data
public class InterfaceCountBean {
    private String interfaceName;//接口名称
    private Integer postNum;//请求次数
    private Integer dataNum;//推送数据量
}
