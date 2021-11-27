package com.dscomm.iecs.out.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/4/21 10:48
 * @Describe 数据接口查询 审计表
 */

@Entity
@Table(name = "JCJ_CXSJB")
@DynamicInsert(true)
@DynamicUpdate(true)
@Data
public class QueryAuditEntity extends BaseEntity {

    @Column(name = "CXKSSJ")
    private Long queryStartTime;//查询开始时间
    @Column(name = "JKMC")
    private String interfaceName;//接口名称
    @Column(name = "IP")
    private String ip;//对方ip
    @Column(name = "BMC")
    private String tableName;//查询表名称
    @Column(name = "CXJG")
    private String queryResult;//查询结果  success/fail
    @Column(name = "CXSJL")
    private Integer dataNum;//查询到的数据量
    @Column(name = "CXJSSJ")
    private Long queryEndTime;//查询结束时间
    @Column(name = "HS")
    private Long queryTime;//耗时（毫秒）
    @Column(name = "ZH")
    private String account;//账号(姓名)
    @Column(name = "SSDW")
    private String orgName;//所属单位

}
