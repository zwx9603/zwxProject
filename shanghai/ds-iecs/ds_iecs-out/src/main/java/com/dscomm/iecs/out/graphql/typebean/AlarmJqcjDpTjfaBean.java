package com.dscomm.iecs.out.graphql.typebean;

import lombok.Data;

@Data
public class AlarmJqcjDpTjfaBean {

    private String uid;
    private String createTime;
    private String jq_tywysbm;//警情_通用唯一识别码
    private String jqcjdp_tjfayj;//调派推荐方案依据JSON
    private String jqcjdptjfa;//调派推荐方案
    private String jqcjdptjfa_tywysbm;//警情处警调派推荐方案_通用唯一识别码
    private String deptCode;//单位code
}
