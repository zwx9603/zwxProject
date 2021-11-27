package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.inputbean.TagLabelQueryInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.TagLabelSaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.TagLabelBean;
import com.dscomm.iecs.base.graphql.typebean.PaginationBean;

/**
 * 描述：标签 服务
 */
public interface TagLabelService {


    /**
     * 根据条件 查询标签
     *
     * @param queryBean 查询条件
     * @return 黑名单
     */
    PaginationBean<TagLabelBean> findTagLabelCondition(TagLabelQueryInputInfo queryBean );

    /**
     * 根据单据ID(变更数据id)查询标签
     *
     * @param businessDataId 单据ID(变更数据id)
     * @return 标签列表
     */
    TagLabelBean  findTagLabelsBusinessDataId(String businessDataId , String businessTable  );

    /**
     * 保存标签
     *
     * @param inputInfo 标签参数
     * @return 保存结果
     */
    TagLabelBean saveTagLabel(TagLabelSaveInputInfo inputInfo);

    /**
     * 撤销标签
     *
     * @param tagLabelId 标签id
     * @return 撤销结果
     */
    Boolean removeTagLabel(String tagLabelId);




}
