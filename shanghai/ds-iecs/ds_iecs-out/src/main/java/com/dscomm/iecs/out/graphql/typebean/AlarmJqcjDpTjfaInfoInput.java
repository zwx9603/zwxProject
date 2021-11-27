package com.dscomm.iecs.out.graphql.typebean;

import lombok.Data;

import java.util.List;

@Data
public class AlarmJqcjDpTjfaInfoInput {
    private String xfjyjgTywysbm;//:"消防救援机构通用唯一识别码",
    private String xfjyjgjl;//":"导航距离",
    private String xfjyjgtjhldsl;//":"红绿灯数量",
    private String xfjyjgyjdcsj;//":"预计时间(单位：秒)"
    private List<DpryBean> dpryList;
}
