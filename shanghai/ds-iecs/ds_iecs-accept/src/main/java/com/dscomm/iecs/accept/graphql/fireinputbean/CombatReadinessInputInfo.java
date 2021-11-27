package com.dscomm.iecs.accept.graphql.fireinputbean;

public class CombatReadinessInputInfo {

    private String id;//主键

    private String type; // 事项类型 1 会议通知 2 待办事项 3领导批示 4工作状态 5 值班文件

    private String title ; // 事项标题

    private String content; //事项内容

    private Long showStartTime ;  //事项显示开始时间 如未传如时间 默认保存开始时间

    private Long showEndTime ; // 事项显示开始时间 如未传如时间 默认保存当天结束时间

    private String expand1 ;  //拓展根据事项类型自行决定

    private String expand2 ;  //拓展根据事项类型自行决定

    private String expand3 ;  //拓展根据事项类型自行决定

    private String remarks;//备注

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


}
