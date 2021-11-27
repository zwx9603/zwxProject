package com.dscomm.iecs.accept.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 描述:案件信息
 *
 */
@Entity
@Table(name = "JCJ_AJXX_119KZ")
@DynamicInsert(true)
@DynamicUpdate(true)
public class IncidentExtend119Entity extends BaseEntity {

    @Column(name = "QTXTAJID1" ,length = 100 )
    private String otherIncidentIdOne ; //其他系统警情id 1

    @Column(name = "QTXTAJID2",length = 100  )
    private String otherIncidentIdTwo ; //其他系统系统id 2

    @Column(name = "QTXTAJID3",length = 100  )
    private String otherIncidentIdThre ; //其他系统系统id 3

    public String getOtherIncidentIdOne() {
        return otherIncidentIdOne;
    }

    public void setOtherIncidentIdOne(String otherIncidentIdOne) {
        this.otherIncidentIdOne = otherIncidentIdOne;
    }

    public String getOtherIncidentIdTwo() {
        return otherIncidentIdTwo;
    }

    public void setOtherIncidentIdTwo(String otherIncidentIdTwo) {
        this.otherIncidentIdTwo = otherIncidentIdTwo;
    }

    public String getOtherIncidentIdThre() {
        return otherIncidentIdThre;
    }

    public void setOtherIncidentIdThre(String otherIncidentIdThre) {
        this.otherIncidentIdThre = otherIncidentIdThre;
    }
}
