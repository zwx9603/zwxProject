package com.dscomm.iecs.agent.dal.po.DistributeStrategy;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**坐席权限表*/

@Entity
@Table(name="jcj_zxqxb")
@DynamicInsert(true)
@DynamicUpdate(true)
public class AgentRoleEntity extends BaseEntity {

    @Column(name = "zxid",length = 50)
    private String agentId;//坐席id

    @Column(name = "qxdm",length = 5)
    private String roleCode;//权限代码

}
