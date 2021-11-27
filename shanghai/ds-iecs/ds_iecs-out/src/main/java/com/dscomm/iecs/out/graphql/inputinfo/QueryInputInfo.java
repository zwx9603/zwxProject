package com.dscomm.iecs.out.graphql.inputinfo;

import lombok.Data;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/4/22 16:29
 * @Describe
 */
@Data
public class QueryInputInfo {
    private Long queryStartTime;//查询开始时间
    private String interfaceName;//接口名称
    private Long queryEndTime;//查询结束时间
    private String account;//账号(姓名)
    private String queryResult;//查询结果  success/fail
}
