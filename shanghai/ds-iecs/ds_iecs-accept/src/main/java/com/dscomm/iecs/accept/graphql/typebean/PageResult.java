package com.dscomm.iecs.accept.graphql.typebean;

import org.mx.dal.Pagination;

import java.util.List;

/**
 * 描述：分页查询结果
 *
 * @author YangFuXi Date Time 2018/12/15 17:58
 */
public class PageResult<T> {
    private Pagination pagination;
    private List<T> data;

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
