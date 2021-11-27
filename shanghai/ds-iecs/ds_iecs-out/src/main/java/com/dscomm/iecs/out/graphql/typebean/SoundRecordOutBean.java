package com.dscomm.iecs.out.graphql.typebean;

import lombok.Data;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/4/20 18:59
 * @Describe 录音信息
 */
@Data
public class SoundRecordOutBean {

    private String jqTywysbm;//警情_通用唯一识别码：yyyymmddhhmmssabcd
    private String lasj;//立案时间 string(date-time)
    private String lybh;//录音编号
    private String lydz;//录音地址
    private String uid;
    private String createTime;
    private String deptCode;//单位code
}
