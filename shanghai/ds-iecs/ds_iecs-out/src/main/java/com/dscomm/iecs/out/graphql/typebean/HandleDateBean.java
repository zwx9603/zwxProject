package com.dscomm.iecs.out.graphql.typebean;

import lombok.Data;

import java.util.List;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/4/19 21:35
 * @Describe 处警数据集合
 */
@Data
public class HandleDateBean {
    private String czrMc;//操作员_姓名
    private String czrTywysbm;//操作员_通用唯一识别码
    private Integer dpdzSl;//警情处警调派_队站数量
    private List<DispatchOrganizationBean> dzList;//调派中队集合

    private String jqTywysbm;//警情_通用唯一识别码
    private String jqcjdpDplx;//警情处警调派_调派类型
    private String jqcjdpDpsj;//警情处警调派_调派事件
    private String jqcjdpDpyj;//警情处警调派_调派依据JSON
    private String jqcjdpTywysbm;//警情处警调派_通用唯一识别码
    private String jqcjdpXgyy;//警情处警调派_修改原因
    private String jqcjdpclGgyj;//车辆更改依据
    private Integer jqcjdpztlbdm;//警情处警调派_状态
    private String jqdjTywysbm;//是否警情升级
    private String lasj;//立案时间
    private String uid;
    private String createTime;
    private String deptCode;//单位code


}
