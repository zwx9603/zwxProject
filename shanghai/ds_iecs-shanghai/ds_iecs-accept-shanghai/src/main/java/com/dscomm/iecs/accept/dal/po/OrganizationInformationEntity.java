package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "JGXX_XFJG")
@DynamicInsert(true)
@DynamicUpdate(true)
public class OrganizationInformationEntity extends BaseEntity{

    @Column(name = "cszt", length = 32)
    private String cszt;  //传输状态

    @Column(name = "jksjbb", length = 100)
    private String jksjbb;  //基础数据版本

    @Column(name = "jlzt", length = 32)
    private String jlzt;  //记录状态

    @Column(name = "sjbb", length = 100)
    private String sjbb;  //数据库版本

    @Column(name = "sjc", length = 64)
    private String sjc;  //时间戳

    @Column(name = "ywxtbsid", length = 100)
    private String ywxtbsid;  //业务系统部署ID

    @Column(name = "LXR", length = 200)
    private String contactPerson ;// 单位联系人

    @Column(name = "LXDH", length = 50)
    private String contactPhone;// 单位联系电话

    @Column(name = "dddh", length = 50)
    private String dddh ;// 调度电话

    @Column(name = "ssym", length = 200)
    private String ssym;// 所属域名

    @Column(name = "czhm", length = 50)
    private String czhm ;// 传真号码

    @Column(name = "yzbm", length = 50)
    private String yzbm;// 邮政编码

    @Column(name = "jglb", length = 100)
    private String jglb;// 机构类别

    @Column(name = "JGDM", length = 100)
    private String JGDM;// 机构代码

    @Column(name = "jgnbid", length = 100)
    private String jgnbid; // 机构内部id

    @Column(name = "sjjgid", length = 100)
    private String sjjgid;// 上级机构ID

    @Column(name = "jglxdm", length = 100)
    private String jglxdm;// 机构类型代码

    @Column(name = "xqmj", length = 100)
    private String xqmj; // 辖区面积

    @Column(name = "xqfw", length = 100)
    private String xqfw; // 辖区范围

    @Column(name = "xqglid", length = 100)
    private String xqglid; // 辖区管理id

    @Column(name = "glid", length = 100)
    private String glid; // 关联id

    @Column(name = "bz", length = 800)
    private String bz; // 备注

    @Column(name = "cxmlj", length = 100)
    private String cxmlj; // 查询码路径

    @Column(name = "zqdwbz", length = 32)
    private String zqdwbz; // 执勤单位标志

    @Column(name = "xfjyjg_tyw", length = 40)
    private String xfjyjg_tyw; // 消防救援机构_通用唯一识别码

    @Column(name = "dwmc", length = 200)
    private String jgmc; // 机构名称

    @Column(name = "dwjc", length = 200)
    private String jgjc; // 机构简称

    @Column(name = "txdz", length = 400)
    private String jgdz; // 机构地址

    @Column(name = "zhz_jyqk", length = 2000)
    private String zhz_jyqk; // 机构描述职责_简要情况

    @Column(name = "xfjyjgxzdm", length = 100)
    private String xfjyjgxzdm; // 机构性质代码

    @Column(name = "xzqhdm", length = 100)
    private String xzqhdm; // 行政区代码

    @Column(name = "dqjd", length = 50)
    private String dqjd; // 经度

    @Column(name = "dqwd", length = 50)
    private String dqwd; // 纬度

    @Column(name = "dqgd", length = 50)
    private String dqgd; // 纬度

    @Column(name = "dzxx", length = 50)
    private String dzxx; // 电子信箱

    @Column(name = "lxr_xm", length = 200)
    private String lxr_xm; // 单位联系人名称

    @Column(name = "px", length = 64)
    private Integer px; // 机构排序

    @Column(name = "sfxs", length = 32)
    private Integer sfxs; // 是否显示 0不在显示led大屏

    @Column(name = "zzjg", length = 32)
    private Integer zzjg; // 是否具备作战能力

    public String getCszt() {
        return cszt;
    }

    public void setCszt(String cszt) {
        this.cszt = cszt;
    }

    public String getJksjbb() {
        return jksjbb;
    }

    public void setJksjbb(String jksjbb) {
        this.jksjbb = jksjbb;
    }

    public String getJlzt() {
        return jlzt;
    }

    public void setJlzt(String jlzt) {
        this.jlzt = jlzt;
    }

    public String getSjbb() {
        return sjbb;
    }

    public void setSjbb(String sjbb) {
        this.sjbb = sjbb;
    }

    public String getSjc() {
        return sjc;
    }

    public void setSjc(String sjc) {
        this.sjc = sjc;
    }

    public String getYwxtbsid() {
        return ywxtbsid;
    }

    public void setYwxtbsid(String ywxtbsid) {
        this.ywxtbsid = ywxtbsid;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getDddh() {
        return dddh;
    }

    public void setDddh(String dddh) {
        this.dddh = dddh;
    }

    public String getSsym() {
        return ssym;
    }

    public void setSsym(String ssym) {
        this.ssym = ssym;
    }

    public String getCzhm() {
        return czhm;
    }

    public void setCzhm(String czhm) {
        this.czhm = czhm;
    }

    public String getYzbm() {
        return yzbm;
    }

    public void setYzbm(String yzbm) {
        this.yzbm = yzbm;
    }

    public String getJglb() {
        return jglb;
    }

    public void setJglb(String jglb) {
        this.jglb = jglb;
    }

    public String getJGDM() {
        return JGDM;
    }

    public void setJGDM(String JGDM) {
        this.JGDM = JGDM;
    }

    public String getJgnbid() {
        return jgnbid;
    }

    public void setJgnbid(String jgnbid) {
        this.jgnbid = jgnbid;
    }

    public String getSjjgid() {
        return sjjgid;
    }

    public void setSjjgid(String sjjgid) {
        this.sjjgid = sjjgid;
    }

    public String getJglxdm() {
        return jglxdm;
    }

    public void setJglxdm(String jglxdm) {
        this.jglxdm = jglxdm;
    }

    public String getXqmj() {
        return xqmj;
    }

    public void setXqmj(String xqmj) {
        this.xqmj = xqmj;
    }

    public String getXqfw() {
        return xqfw;
    }

    public void setXqfw(String xqfw) {
        this.xqfw = xqfw;
    }

    public String getXqglid() {
        return xqglid;
    }

    public void setXqglid(String xqglid) {
        this.xqglid = xqglid;
    }

    public String getGlid() {
        return glid;
    }

    public void setGlid(String glid) {
        this.glid = glid;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getCxmlj() {
        return cxmlj;
    }

    public void setCxmlj(String cxmlj) {
        this.cxmlj = cxmlj;
    }

    public String getZqdwbz() {
        return zqdwbz;
    }

    public void setZqdwbz(String zqdwbz) {
        this.zqdwbz = zqdwbz;
    }

    public String getXfjyjg_tyw() {
        return xfjyjg_tyw;
    }

    public void setXfjyjg_tyw(String xfjyjg_tyw) {
        this.xfjyjg_tyw = xfjyjg_tyw;
    }

    public String getJgmc() {
        return jgmc;
    }

    public void setJgmc(String jgmc) {
        this.jgmc = jgmc;
    }

    public String getJgjc() {
        return jgjc;
    }

    public void setJgjc(String jgjc) {
        this.jgjc = jgjc;
    }

    public String getJgdz() {
        return jgdz;
    }

    public void setJgdz(String jgdz) {
        this.jgdz = jgdz;
    }

    public String getZhz_jyqk() {
        return zhz_jyqk;
    }

    public void setZhz_jyqk(String zhz_jyqk) {
        this.zhz_jyqk = zhz_jyqk;
    }

    public String getXfjyjgxzdm() {
        return xfjyjgxzdm;
    }

    public void setXfjyjgxzdm(String xfjyjgxzdm) {
        this.xfjyjgxzdm = xfjyjgxzdm;
    }

    public String getXzqhdm() {
        return xzqhdm;
    }

    public void setXzqhdm(String xzqhdm) {
        this.xzqhdm = xzqhdm;
    }

    public String getDqjd() {
        return dqjd;
    }

    public void setDqjd(String dqjd) {
        this.dqjd = dqjd;
    }

    public String getDqwd() {
        return dqwd;
    }

    public void setDqwd(String dqwd) {
        this.dqwd = dqwd;
    }

    public String getDqgd() {
        return dqgd;
    }

    public void setDqgd(String dqgd) {
        this.dqgd = dqgd;
    }

    public String getDzxx() {
        return dzxx;
    }

    public void setDzxx(String dzxx) {
        this.dzxx = dzxx;
    }

    public String getLxr_xm() {
        return lxr_xm;
    }

    public void setLxr_xm(String lxr_xm) {
        this.lxr_xm = lxr_xm;
    }

    public Integer getPx() {
        return px;
    }

    public void setPx(Integer px) {
        this.px = px;
    }

    public Integer getSfxs() {
        return sfxs;
    }

    public void setSfxs(Integer sfxs) {
        this.sfxs = sfxs;
    }

    public Integer getZzjg() {
        return zzjg;
    }

    public void setZzjg(Integer zzjg) {
        this.zzjg = zzjg;
    }
}
