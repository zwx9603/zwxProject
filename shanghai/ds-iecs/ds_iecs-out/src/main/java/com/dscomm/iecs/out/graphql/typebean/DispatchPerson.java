package com.dscomm.iecs.out.graphql.typebean;

import lombok.Data;
import org.springframework.boot.liquibase.SpringPackageScanClassResolver;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/4/19 21:45
 * @Describe 调派人员
 */
@Data
public class DispatchPerson {
    //todo...
    private String jqcjdpTywysbm;//警情处警调派_通用唯一识别码
    private String jqcjdpdzMc;//人员所属中队_名称
    private String jqcjdpdzTywysbm;//人员所属中队_通用唯一识别码
    private String jqcjdpryDpsj;//警情处警调派人员_调派事件
    private String jqcjdpryGgyj;//调派人员_更改依据
    private String jqcjdpryGgzt;//调派人员_更改状态
    private String jqcjdpryGxfs;//警情处警调派人员_更新方式
    private String jqcjdpryTywysbm;//警情处警调派人员_通用唯一识别码
    private String jqcjdpryXgyybz;//调派人员_修改原因备注
    private String jqcjdpryZtgxbz;//调派人员_状态更新备注
    private String xfjyryTywysbm;//消防救援人员_通用唯一识别码
    private String xfjyryXm;//消防救援人员姓名
    private String uid;
    private String createTime;
    private String deptCode;//单位code

}
