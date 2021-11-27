package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**800M录音记录表*/

@Data
@Entity
@Table(name = "JCJ_DTLYJL ")
@DynamicInsert(true)
@DynamicUpdate(true)
public class FireControlVoiceEntity extends BaseEntity {

    private String name;

    private String href;

    private String size;

    private Long time;

    private String voiceclass;

    private String content;

    private String remark;

    private String brief;

    private String channel;

    private String terrace;

    @Column(name = "voicenum")
    private String voiceNum;

    private String voiceclassname;

    @Column(name = "channelname")
    private String channelName;

}
