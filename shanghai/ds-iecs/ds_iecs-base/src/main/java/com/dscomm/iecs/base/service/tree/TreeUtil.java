package com.dscomm.iecs.base.service.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 树形结构 工具类
 * @param <T>
 */
public class TreeUtil<T extends TreeNode> {

    /**
     * 使用递归方法建树
     *
     * @param treeNodes
     * @return
     */
    public static <T extends TreeNode> List<T> bulidTree(List<T> treeNodes) {
        boolean existRootNode = false;
        List<T> newTree = new ArrayList<T>();//初始化一个新的列表
        for (T treeNode : treeNodes) {
            if (isRootNode(treeNode, treeNodes)) {//选择根节点数据开始找儿子
                newTree.add(findChildren(treeNode, treeNodes));
                existRootNode = true;
            }
        }
        if(!existRootNode){//也可能大家都是根节点
            return treeNodes;
        }
        return newTree;
    }


    /**
     * 使用递归方法建树
     *
     * @param treeNodes
     * @return
     */
    public static <T extends TreeNode> T bulidTree( String rootId , List<T> treeNodes) {
        T rootNode = null  ;
        for (T treeNode : treeNodes) {
            if (  treeNode.getId().equals( rootId )  ) {//选择根节点数据开始找儿子
                rootNode = findChildren(treeNode, treeNodes) ;
            }
        }

        return rootNode ;
    }


    /**
     * 判断节点是否是根节点
     * @param checkNode
     * @param treeNodes
     * @return
     */
    private static <T extends TreeNode> boolean isRootNode(T checkNode, List<T> treeNodes) {
        //判断checkNode是不是为根据  逻辑判断 ： 父节点id 为 null 为根节点  非null 判断是否为根节点
        if( checkNode.getParentId() != null  ) {
            for (TreeNode treeNode : treeNodes) {
                if (checkNode.getParentId().equals(treeNode.getId())) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * 递归查找子节点
     *
     * @param treeNodes
     * @return
     */
    private static <T extends TreeNode> T findChildren(T parentNode, List<T> treeNodes) {

        for (T it : treeNodes) {
            if(parentNode.getId().equals(it.getParentId())) {
                if (parentNode.getChildren() == null) {
                    parentNode.setChildren(new ArrayList<TreeNode>());
                }
                parentNode.getChildren().add(findChildren(it,treeNodes));
            }
        }
        return parentNode ;
    }

}
