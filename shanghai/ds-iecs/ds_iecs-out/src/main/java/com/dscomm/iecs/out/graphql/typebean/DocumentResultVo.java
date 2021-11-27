package com.dscomm.iecs.out.graphql.typebean;

import lombok.Data;

import java.util.List;
@Data
public class DocumentResultVo {
    private AccessInfoVo accessInfoVo;//数据接入信息
    private String interfaceType;//固定值(枚举)
    private String queryParam;//查询参数（默认为空）
    private List<DocumentOutBean> dataList;
}
