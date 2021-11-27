package com.dscomm.iecs.base.graphql.typebean;

import org.mx.dal.Pagination;

import java.util.List;

/**
 * 描述： 分页bean
 */
public class PaginationBean<T> {
    private Pagination pagination;
    private List<T> list;

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
