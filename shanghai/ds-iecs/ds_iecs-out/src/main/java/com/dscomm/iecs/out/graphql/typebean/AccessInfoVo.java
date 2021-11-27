package com.dscomm.iecs.out.graphql.typebean;

import lombok.Data;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/4/19 13:17
 * @Describe 数据接入信息
 */
@Data
public class AccessInfoVo {

    private String accessOrg;//接入支队名称
    private String accessVersion;//接入版本
    private String accessPerson;//接入人员
    private String accessPhone;//人员电话
    private String deptCode;//支队单位code

}
