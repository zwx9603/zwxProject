package com.dscomm.iecs.agent.dal.po.DistributeStrategy;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 描述:分配工号(账号)辖区表
 *
 * @author YangFuxi
 * Date Time 2019/9/19 15:25
 */
@Table(name = "t_fp_cjyxq")
@Entity
public class AccountJurisdictionPO implements Serializable {
    @Id
    @GenericGenerator(name = "generuuid", strategy = "uuid")
    @GeneratedValue(generator = "generuuid")
    @Column(name = "lsh", length = 40)
    private String id;
    @Column(name = "cjygh", length = 60)
    private String accountNum;
    @Column(name = "cjxqbh", length = 40)
    private String jurisdictionId;
    @Column(name = "sjc", length = 20)
    private Long sjc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getJurisdictionId() {
        return jurisdictionId;
    }

    public void setJurisdictionId(String jurisdictionId) {
        this.jurisdictionId = jurisdictionId;
    }

    public Long getSjc() {
        return sjc;
    }

    public void setSjc(Long sjc) {
        this.sjc = sjc;
    }
}
