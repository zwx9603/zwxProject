package com.dscomm.iecs.out.graphql.typebean;

import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/4/19 21:12
 * @Describe 案件数据集合
 */
@Data
public class ResultVo<T> {

    private AccessInfoVo accessInfoVo;//数据接入信息
    private String interfaceType;//固定值(枚举)
    private String queryParam;//查询参数（默认为空）
    private List<T> dataList;
}
