package com.dscomm.iecs.out.graphql.typebean;

import lombok.Data;

import java.util.List;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/4/19 13:20
 * @Describe 报警记录信息 返回数据结果
 */
@Data
public class AlarmRecordResultVo {

    private AccessInfoVo accessInfoVo;//数据接入信息
    private String interfaceType;//固定值(枚举)
    private String queryParam;//查询参数（默认为空）
    private List<AlarmRecordBean> dataList;//数据集合


}
