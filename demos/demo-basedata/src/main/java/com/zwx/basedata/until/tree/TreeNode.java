package com.zwx.basedata.until.tree;

import java.util.List;

/**
 * 树形结构节点类
 * @param <T>
 */
public class TreeNode<T> {

    private String id;

    private String parentId;

    List<T> children = null ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<T> getChildren() {
        return children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }

    public void add(T node){
        children.add(node);
    }
}
