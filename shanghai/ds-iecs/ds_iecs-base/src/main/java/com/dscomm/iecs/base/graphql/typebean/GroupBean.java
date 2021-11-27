package com.dscomm.iecs.base.graphql.typebean;

import java.util.List;

// 分组维度信息
public class GroupBean<T> {

    private String  groupId ; //分组维度Id

    private String  groupName ;//分组维度名称

    private Integer  groupNum  ;//分组维度排序

    private List<T> list;//分组维度数据

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(Integer groupNum) {
        this.groupNum = groupNum;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
