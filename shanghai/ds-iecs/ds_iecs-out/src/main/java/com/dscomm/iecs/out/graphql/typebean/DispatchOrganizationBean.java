package com.dscomm.iecs.out.graphql.typebean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/4/19 21:40
 * @Describe 调派中队
 */
@Data
public class DispatchOrganizationBean {

    private List<DispatchVehicleBean> dpclList;//调派车辆列表

    private Integer dpclSl ;//调派车辆数量

    private List<DispatchPerson> dpryList; //调派人员列表

    private Integer dprySl ;//调派人员数量

    private List<DispatchEquipment> dpzbList;//调派装备列表

    private Integer dpzbSl ;//调派装备数量


    private Number dpzdjd;//调派中队经度
    private String dpzdmc;//调派中队名称
    private Number dpzdwd;// 调派中队纬度
    private Number dqjd;//地球经度
    private Number dqwd;//地球纬度
    private String jqcjdpTywysbm;//警情处警调派_通用唯一识别码
    private String jqcjdpdzTywysbm;//警情处警调派队站_通用唯一识别码
    private String sfjbxq;//是否具备辖区
    private Object tmcs;//调派路线
    private String xfjyjgTywysbm;//消防救援机构_通用唯一识别码
    private String xfjyjgjl;//导航距离
    private String xfjyjgjsjj;//中队接收时间
    private String xfjyjgjszt;//中队接收状态
    private Integer xfjyjgtjhldsl;//红绿灯数量
    private String xfjyjgxqfw;//辖区范围
    private Integer xfjyjgyjdcsj;//预计时间(单位：秒)
    private String uid;
    private String createTime;
    private String deptCode;//单位code




}
