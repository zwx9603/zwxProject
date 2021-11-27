package com.dscomm.iecs.accept.graphql.inputbean;

import java.util.List;

/**
 * 描述：ids 集合参数
 *
 */
public class IdsInputInfo {

    private List<String> ids  ; //主键集合

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
}
