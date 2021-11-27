package com.dscomm.iecs.out.graphql.typebean;

import lombok.Data;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/4/19 21:47
 * @Describe 调派装备
 */
@Data
public class DispatchEquipment {
    //todo...
    private String jqcjdpTywysbm;//警情处警调派_通用唯一识别码
    private String  jqcjdpdzTywysbm;//装备所属队站_通用唯一识别码
    private String jqcjdpdzmc;//装备所属队站_名称
    private String jqcjdpzbDpsj;//警情处警调派装备_调派事件
    private String jqcjdpzbGgyj;//调派装备_更改依据
    private String jqcjdpzbGgzt;//更改状态
    private String jqcjdpzbGxfs;//警情处警调派装备_更新方式
    private String jqcjdpzbTywysbm;//警情处警调派装备_通用唯一识别码
    private String jqcjdpzbXgyybz;//调派装备_修改原因备注
    private String jqcjdpzbZtgxbz;//调派装备_状态更新备注
    private String jqcjdpzbztlbdm;//警情处警调派装备状态类别代码
    private String xfjyzbMc;//消防救援装备_名称
    private String  xfjyzbTywysbm;//消防救援装备_通用唯一识别码
    private String uid;
    private String createTime;
    private String deptCode;//单位code
}
