package com.dscomm.iecs.out.graphql.typebean;

import lombok.Data;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/5/25 11:29
 * @Describe 警情调派推荐 方案依据
 */
@Data
public class DPTJFAYJBean {
    private Boolean whetherKeyUnit;//是否重点单位

    private String incidentTypeCode;// 案件类型代码

    private String incidentTypeName;// 案件类型名称

    private String disposalObjectCode;// 处置对象代码

    private String disposalObjectName;// 处置对象名称

    private String incidentLevelCode;// 案件等级代码

    private String incidentLevelName;// 案件等级名称
}
