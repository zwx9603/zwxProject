package com.dscomm.iecs.accept.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * 战备信息录入表
 */
@Entity
@Table(name = "T_COC_FIRE_XXLR")
@DynamicInsert(true)
@DynamicUpdate(true)
public class CombatReadinessEntity extends BaseEntity {

    @Column(name = "SXLX" , length =  100 )
    private String type; // 事项类型 1 会议通知 2 待办事项 3领导批示 4工作状态 5 值班文件

    @Column(name = "SXBT" , length = 400)
    private String title ; // 事项标题

    @Column(name = "SXNR" , length = 4000)
    private String content; //事项内容

    @Column(name = "XSKSSJ"  )
    private Long showStartTime ;  //事项显示开始时间 如未传如时间 默认保存开始时间

    @Column(name = "XSJSSJ"  )
    private long showEndTime ; // 事项显示开始时间 如未传如时间 默认保存当天结束时间

    @Column(name = "KZZD1" , length = 800 )
    private String expand1 ;  //拓展根据事项类型自行决定

    @Column(name = "KZZD2" , length = 800 )
    private String expand2 ;  //拓展根据事项类型自行决定

    @Column(name = "KZZD3" , length = 800 )
    private String expand3 ;  //拓展根据事项类型自行决定


    @Column(name = "BZ", length = 800)
    private String remarks;   //备注


    public String getExpand1() {
        return expand1;
    }

    public void setExpand1(String expand1) {
        this.expand1 = expand1;
    }

    public String getExpand2() {
        return expand2;
    }

    public void setExpand2(String expand2) {
        this.expand2 = expand2;
    }

    public String getExpand3() {
        return expand3;
    }

    public void setExpand3(String expand3) {
        this.expand3 = expand3;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getShowStartTime() {
        return showStartTime;
    }

    public void setShowStartTime(Long showStartTime) {
        this.showStartTime = showStartTime;
    }

    public long getShowEndTime() {
        return showEndTime;
    }

    public void setShowEndTime(long showEndTime) {
        this.showEndTime = showEndTime;
    }
}
