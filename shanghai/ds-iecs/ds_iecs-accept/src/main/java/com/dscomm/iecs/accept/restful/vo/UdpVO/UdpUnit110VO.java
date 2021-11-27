package com.dscomm.iecs.accept.restful.vo.UdpVO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.NONE)
public class UdpUnit110VO {

    @XmlElement(name="DWMC")
    private String DWMC;
    @XmlElement(name="DWBH")
    private String DWBH;

    @XmlTransient
    public String getDWMC() {
        return DWMC;
    }

    public void setDWMC(String DWMC) {
        this.DWMC = DWMC;
    }

    @XmlTransient
    public String getDWBH() {
        return DWBH;
    }

    public void setDWBH(String DWBH) {
        this.DWBH = DWBH;
    }

    @Override
    public String toString() {
        return "{" +
                "DWMC='" + DWMC + '\'' +
                ", DWBH='" + DWBH + '\'' +
                '}';
    }
}
