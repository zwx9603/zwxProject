package com.dscomm.iecs.out.graphql.typebean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;
@Data
public class TelephoneOutBean {
    @JSONField(name = "thjlTywysbm")
    private String idCode; //通话记录_通用唯一识别码

    @JSONField(name = "dhhjlxdm")
    private String callDirection   ; //电话呼叫类型代码

    @JSONField(name = "xfryTywysbm")
    private String personId; //消防人员_通用唯一识别码

    @JSONField(name = "zjhmLxdh")
    private String callingNumber;//主叫号码_联系电话

    @JSONField(name = "bjhmLxdh")
    private String calledNumber;//被叫号码_联系电话

    @JSONField(name = "zlRqsj")
    private String ringingTime;  //振铃日期时间

    @JSONField(name = "jitRqsj")
    private String answerTime;  //接听日期时间

    @JSONField(name = "gjRqsj")
    private String endTime;  //挂机日期时间

    @JSONField(name = "lywjMc")
    private String relayRecordName; //名称

    @JSONField(name = "lywjDzwjwz")
    private String relayRecordUrl; //电子文件位置

    @JSONField(name = "dzwjlxdm")
    private String relayRecorCode; //电子文件类型代码

    @JSONField(name = "yyzynrJyqk")
    private String relayRecorDesc; //语音转译内容_简要情况
    @JSONField(name = "uid")
    private String uid;
    @JSONField(name = "createTime")
    private String createTime;
    @JSONField(name = "deptCode")
    private String deptCode;//单位code
}
