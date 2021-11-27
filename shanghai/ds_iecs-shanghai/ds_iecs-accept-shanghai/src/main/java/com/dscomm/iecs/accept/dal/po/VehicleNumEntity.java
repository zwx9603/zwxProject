package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "wl_clxx")
@DynamicInsert(true)
@DynamicUpdate(true)
public class VehicleNumEntity extends BaseEntity {

    @Column(name = "cszt")
    private String cszt;  //传输状态

    @Column(name = "jksjbb")
    private String jksjbb;  //基础数据版本

    @Column(name = "jlzt")
    private String jlzt;  //记录状态

    @Column(name = "sjbb")
    private String sjbb;  //数据库版本

    @Column(name = "sjc")
    private String sjc;  //时间戳

    @Column(name = "ywxtbsid")
    private String ywxtbsid;  //业务系统部署ID

    @Column(name = "jkgn")
    private String jkgn;  //进口装备国内代理商

    @Column(name = "zcbh")
    private String zcbh;  //资产编号

    @Column(name = "pch")
    private String pch;  //批次号

    @Column(name = "zp")
    private Float zp;  //载泡量

    @Column(name = "jcjgzt")
    private String jcjgzt;  //检查结果状态

    @Column(name = "gbdm")
    private String gbdm;  // 国别代码

    @Column(name = "ljyxsj")
    private String ljyxsj;  //累计运输时间

    @Column(name = "ljsycs")
    private String ljsycs;  //累计使用次数

    @Column(name = "yxqz")
    private String yxqz;  //有效期至

    @Column(name = "zbrq")
    private String zbrq;  //装备日期

    @Column(name = "zbbm")
    private String zbbm;  // 装备编码

    @Column(name = "cjh")
    private String cjh;  //车架号

    @Column(name = "gpsbh")
    private String gpsbh;  //GPS编号

    @Column(name = "clxg")
    private String clxg;  //车辆限高

    @Column(name = "ajbh")
    private String ajbh;  //案件编号

    @Column(name = "qdzl")
    private String qdzl;  //起吊重量

    @Column(name = "sdzt")
    private String sdzt;  //锁定状态

    @Column(name = "bcbyrq")
    private Date bcbyrq;  //本次保养日期

    @Column(name = "zdll")
    private String zdll;  //最大流量

    @Column(name = "zdjsgd")
    private Integer zdjsgd;  //最大举升高度

    @Column(name = "zdpyl")
    private String zdpyl;  //最大排烟量

    @Column(name = "jldwdm")
    private String jldwdm;  //计量单位代码

    @Column(name = "zxll")
    private String zxll;  //最小流量

    @Column(name = "zrsl")
    private Integer zrsl;  //载人数量

    @Column(name = "xcbyrq")
    private String xcbyrq;  //下次保养日期

    @Column(name = "ccrq")
    private String ccrq;  //载人数量

    @Column(name = "dthh")
    private String dthh;  //电台呼号

    @Column(name = "bz")
    private String bz;  //备注

    @Column(name = "bfrq")
    private String bfrq;  //报废日期

    @Column(name = "djzbbm")
    private String djzbbm;  //单件装备编码

    @Column(name = "djzbzkdm")
    private String djzbzkdm;  //单件装备状况代码

    @Column(name = "ggxh")
    private String ggxh;  //规格型号

    @Column(name = "sjzbbm")
    private String sjzbbm;  //上级装备编码

    @Column(name = "qyl")
    private String qyl;  //牵引力

    @Column(name = "sb")
    private String sb;  //商标

    @Column(name = "zbmc")
    private String zbmc;  //装备名称 车辆名称

    @Column(name = "cljc")
    private String cljc;  //车辆简称

    @Column(name = "sfzdkqy")
    private String sfzdkqy;  //是否跨总队

    @Column(name = "sfzhdkqy")
    private String sfzhdkqy;  //是否跨支队

    @Column(name = "stb")
    private Float stb;  //手抬泵

    @Column(name = "xfpll")
    private Float xfpll;  //消防炮流量

    @Column(name = "bll")
    private Float bll;  //泵流量

    @Column(name = "czpm")
    private String czpm;  //车载泡沫

    @Column(name = "czqc")
    private String czqc;  //车载器材

    @Column(name = "xfcl_tywysbm")
    private String xfcl_tywysbm;  //消防车辆_通用唯一识别码

    @Column(name = "clsbdh")
    private String clsbdh;  // 车辆编号车辆识别代号

    @Column(name = "clxnzb_jyqk")
    private String clxnzb_jyqk;  //车辆性能指标_简要情况

    @Column(name = "xfzblxdm")
    private String zblxdm;  // 消防装备器材分类与代码 车辆类型代码

    @Column(name = "xfcldjdm")
    private String cldjdm;  // 消防装备器材分类与代码 车辆等级代码

    @Column(name = "xfjyjg_tywysbm")
    private String xfjyjg_tywysbm;  // 所属消防机构ID 消防救援机构_通用唯一识别码

    @Column(name = "clqwztlbdm")
    private String clztdm_mh;  //车辆状态代码

    @Column(name = "jdchphm")
    private String cphm;  //机动车号牌号码

    @Column(name = "dwsb")
    private String dwsb;  //定位设备id

    @Column(name = "dwsb_tywysbm")
    private String dwbh;  //定位编号 定位设备 通用唯一识别码

    @Column(name = "dwsb_simkkh")
    private String dwsb_simkkh;  //定位设备 SIM卡卡号

    @Column(name = "clwz")
    private String clwz;  //车辆位置

    @Column(name = "sfdycdcl_tywysbm")
    private String sfdycdcl_tywysbm;  //车辆标识 0 头车  可拓展 是否第一出动车辆_判断标识

    @Column(name = "zzs_rj")
    private Float zs;  //载水量 装载水_容积 单位：吨

    @Column(name = "zzalpm_rj")
    private Float zzalpm_rj;  //装载A类泡沫_容积 单位：升

    @Column(name = "zzblpm_rj")
    private Float zzblpm_rj;  //装载B类泡沫_容积 单位：吨

    @Column(name = "sfzp_pdbz")
    private String sfzp;  //是否装配_判断标识

    @Column(name = "sfyyxl")
    private String sfyyxl;  //是否装配_判断标识

    @Column(name = "sfyyxl_pdbz")
    private String sfyyxl_pdbz;  //是否用于训练_判断标识

    @Column(name = "ckj_je")
    private String ckj;  //参考价

    @Column(name = "jdccsyslbdm")
    private String ys;  //颜色

    @Column(name = "sccj_dwid")
    private String sccjid;  //生产厂家代码

    @Column(name = "sccj_dwmc")
    private String sccjmc;  //生产厂家名称

    @Column(name = "shfw_dwmc")
    private String shfw;  //售后服务_单位名称

    @Column(name = "fzr")
    private String fzr;  //责任人

    @Column(name = "fzr_tywysbm")
    private String fzrrid;  //责任人ID 通用唯一识别码

    @Column(name = "fzr_xm")
    private String fzrrxm;  //责任人姓名

    @Column(name = "fzr_lxdh",length = 50)
    private String fzr_lxdh;  //责任人联系电话

    @Column(name = "jdcfdjddjh",length = 100)
    private String fdjbh;  //机动车发动机（电动机）号

    @Column(name = "cfdd",length = 400)
    private String cfdd;  //车辆存放地址

    @Column(name = "px",length = 64)
    private Integer px;  //车辆在机构排序

    @Column(name = "sfxs")
    private Integer sfxs; // (是否显示 0不显示在led大屏）

    @Column(name = "sfbl",length = 32)
    private Integer sfbl; // 是否保留 (是否不优先派遣)

    @Column(name = "sfzb",length = 32)
    private Integer sfzb; // 是否战备

    @Column(name = "zksbid",length = 40)
    private Integer zksbid; // 总控设备id

    @Column(name = "ytgd",length = 10)
    private String ytgd; // 云梯高度

    @Column(name = "ssxfjgid",length = 40)
    private String ssxfjgid; // 实际派遣所在消防机构id


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

    public String getShfw() {
        return shfw;
    }

    public void setShfw(String shfw) {
        this.shfw = shfw;
    }

    public String getFzr() {
        return fzr;
    }

    public void setFzr(String fzr) {
        this.fzr = fzr;
    }

    public String getFzrrid() {
        return fzrrid;
    }

    public void setFzrrid(String fzrrid) {
        this.fzrrid = fzrrid;
    }

    public String getFzrrxm() {
        return fzrrxm;
    }

    public void setFzrrxm(String fzrrxm) {
        this.fzrrxm = fzrrxm;
    }

    public String getFzr_lxdh() {
        return fzr_lxdh;
    }

    public void setFzr_lxdh(String fzr_lxdh) {
        this.fzr_lxdh = fzr_lxdh;
    }

    public String getJkgn() {
        return jkgn;
    }

    public void setJkgn(String jkgn) {
        this.jkgn = jkgn;
    }

    public String getZcbh() {
        return zcbh;
    }

    public void setZcbh(String zcbh) {
        this.zcbh = zcbh;
    }

    public String getPch() {
        return pch;
    }

    public void setPch(String pch) {
        this.pch = pch;
    }

    public Float getZp() {
        return zp;
    }

    public void setZp(Float zp) {
        this.zp = zp;
    }

    public String getJcjgzt() {
        return jcjgzt;
    }

    public void setJcjgzt(String jcjgzt) {
        this.jcjgzt = jcjgzt;
    }

    public String getGbdm() {
        return gbdm;
    }

    public void setGbdm(String gbdm) {
        this.gbdm = gbdm;
    }

    public String getLjyxsj() {
        return ljyxsj;
    }

    public void setLjyxsj(String ljyxsj) {
        this.ljyxsj = ljyxsj;
    }

    public String getLjsycs() {
        return ljsycs;
    }

    public void setLjsycs(String ljsycs) {
        this.ljsycs = ljsycs;
    }

    public String getYxqz() {
        return yxqz;
    }

    public void setYxqz(String yxqz) {
        this.yxqz = yxqz;
    }

    public String getFdjbh() {
        return fdjbh;
    }

    public void setFdjbh(String fdjbh) {
        this.fdjbh = fdjbh;
    }

    public String getCfdd() {
        return cfdd;
    }

    public void setCfdd(String cfdd) {
        this.cfdd = cfdd;
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

    public Integer getSfbl() {
        return sfbl;
    }

    public void setSfbl(Integer sfbl) {
        this.sfbl = sfbl;
    }

    public Integer getSfzb() {
        return sfzb;
    }

    public void setSfzb(Integer sfzb) {
        this.sfzb = sfzb;
    }

    public Integer getZksbid() {
        return zksbid;
    }

    public void setZksbid(Integer zksbid) {
        this.zksbid = zksbid;
    }

    public String getYtgd() {
        return ytgd;
    }

    public void setYtgd(String ytgd) {
        this.ytgd = ytgd;
    }

    public String getZbrq() {
        return zbrq;
    }

    public void setZbrq(String zbrq) {
        this.zbrq = zbrq;
    }

    public String getZbbm() {
        return zbbm;
    }

    public void setZbbm(String zbbm) {
        this.zbbm = zbbm;
    }

    public String getCjh() {
        return cjh;
    }

    public void setCjh(String cjh) {
        this.cjh = cjh;
    }

    public String getGpsbh() {
        return gpsbh;
    }

    public void setGpsbh(String gpsbh) {
        this.gpsbh = gpsbh;
    }

    public String getClxg() {
        return clxg;
    }

    public void setClxg(String clxg) {
        this.clxg = clxg;
    }

    public String getAjbh() {
        return ajbh;
    }

    public void setAjbh(String ajbh) {
        this.ajbh = ajbh;
    }

    public String getQdzl() {
        return qdzl;
    }

    public void setQdzl(String qdzl) {
        this.qdzl = qdzl;
    }

    public String getSdzt() {
        return sdzt;
    }

    public void setSdzt(String sdzt) {
        this.sdzt = sdzt;
    }

    public Date getBcbyrq() {
        return bcbyrq;
    }

    public void setBcbyrq(Date bcbyrq) {
        this.bcbyrq = bcbyrq;
    }

    public String getZdll() {
        return zdll;
    }

    public void setZdll(String zdll) {
        this.zdll = zdll;
    }

    public Integer getZdjsgd() {
        return zdjsgd;
    }

    public void setZdjsgd(Integer zdjsgd) {
        this.zdjsgd = zdjsgd;
    }

    public String getZdpyl() {
        return zdpyl;
    }

    public void setZdpyl(String zdpyl) {
        this.zdpyl = zdpyl;
    }

    public String getJldwdm() {
        return jldwdm;
    }

    public void setJldwdm(String jldwdm) {
        this.jldwdm = jldwdm;
    }

    public String getZxll() {
        return zxll;
    }

    public void setZxll(String zxll) {
        this.zxll = zxll;
    }

    public Integer getZrsl() {
        return zrsl;
    }

    public void setZrsl(Integer zrsl) {
        this.zrsl = zrsl;
    }

    public String getXcbyrq() {
        return xcbyrq;
    }

    public void setXcbyrq(String xcbyrq) {
        this.xcbyrq = xcbyrq;
    }

    public String getSsxfjgid() {
        return ssxfjgid;
    }

    public void setSsxfjgid(String ssxfjgid) {
        this.ssxfjgid = ssxfjgid;
    }

    public String getClztdm_mh() {
        return clztdm_mh;
    }

    public void setClztdm_mh(String clztdm_mh) {
        this.clztdm_mh = clztdm_mh;
    }

    public String getCphm() {
        return cphm;
    }

    public void setCphm(String cphm) {
        this.cphm = cphm;
    }

    public String getDwsb() {
        return dwsb;
    }

    public void setDwsb(String dwsb) {
        this.dwsb = dwsb;
    }

    public String getDwbh() {
        return dwbh;
    }

    public void setDwbh(String dwbh) {
        this.dwbh = dwbh;
    }

    public String getDwsb_simkkh() {
        return dwsb_simkkh;
    }

    public void setDwsb_simkkh(String dwsb_simkkh) {
        this.dwsb_simkkh = dwsb_simkkh;
    }

    public String getClwz() {
        return clwz;
    }

    public void setClwz(String clwz) {
        this.clwz = clwz;
    }

    public String getSfdycdcl_tywysbm() {
        return sfdycdcl_tywysbm;
    }

    public void setSfdycdcl_tywysbm(String sfdycdcl_tywysbm) {
        this.sfdycdcl_tywysbm = sfdycdcl_tywysbm;
    }

    public Float getZs() {
        return zs;
    }

    public void setZs(Float zs) {
        this.zs = zs;
    }

    public Float getZzalpm_rj() {
        return zzalpm_rj;
    }

    public void setZzalpm_rj(Float zzalpm_rj) {
        this.zzalpm_rj = zzalpm_rj;
    }

    public Float getZzblpm_rj() {
        return zzblpm_rj;
    }

    public void setZzblpm_rj(Float zzblpm_rj) {
        this.zzblpm_rj = zzblpm_rj;
    }

    public String getSfzp() {
        return sfzp;
    }

    public void setSfzp(String sfzp) {
        this.sfzp = sfzp;
    }

    public String getSfyyxl() {
        return sfyyxl;
    }

    public void setSfyyxl(String sfyyxl) {
        this.sfyyxl = sfyyxl;
    }

    public String getSfyyxl_pdbz() {
        return sfyyxl_pdbz;
    }

    public void setSfyyxl_pdbz(String sfyyxl_pdbz) {
        this.sfyyxl_pdbz = sfyyxl_pdbz;
    }

    public String getCkj() {
        return ckj;
    }

    public void setCkj(String ckj) {
        this.ckj = ckj;
    }

    public String getYs() {
        return ys;
    }

    public void setYs(String ys) {
        this.ys = ys;
    }

    public String getSccjid() {
        return sccjid;
    }

    public void setSccjid(String sccjid) {
        this.sccjid = sccjid;
    }

    public String getSccjmc() {
        return sccjmc;
    }

    public void setSccjmc(String sccjmc) {
        this.sccjmc = sccjmc;
    }

    public String getCcrq() {
        return ccrq;
    }

    public void setCcrq(String ccrq) {
        this.ccrq = ccrq;
    }

    public String getDthh() {
        return dthh;
    }

    public void setDthh(String dthh) {
        this.dthh = dthh;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getBfrq() {
        return bfrq;
    }

    public void setBfrq(String bfrq) {
        this.bfrq = bfrq;
    }

    public String getDjzbbm() {
        return djzbbm;
    }

    public void setDjzbbm(String djzbbm) {
        this.djzbbm = djzbbm;
    }

    public String getDjzbzkdm() {
        return djzbzkdm;
    }

    public void setDjzbzkdm(String djzbzkdm) {
        this.djzbzkdm = djzbzkdm;
    }

    public String getGgxh() {
        return ggxh;
    }

    public void setGgxh(String ggxh) {
        this.ggxh = ggxh;
    }

    public String getSjzbbm() {
        return sjzbbm;
    }

    public void setSjzbbm(String sjzbbm) {
        this.sjzbbm = sjzbbm;
    }

    public String getQyl() {
        return qyl;
    }

    public void setQyl(String qyl) {
        this.qyl = qyl;
    }

    public String getSb() {
        return sb;
    }

    public void setSb(String sb) {
        this.sb = sb;
    }

    public String getZbmc() {
        return zbmc;
    }

    public void setZbmc(String zbmc) {
        this.zbmc = zbmc;
    }

    public String getCljc() {
        return cljc;
    }

    public void setCljc(String cljc) {
        this.cljc = cljc;
    }

    public String getSfzdkqy() {
        return sfzdkqy;
    }

    public void setSfzdkqy(String sfzdkqy) {
        this.sfzdkqy = sfzdkqy;
    }

    public String getSfzhdkqy() {
        return sfzhdkqy;
    }

    public void setSfzhdkqy(String sfzhdkqy) {
        this.sfzhdkqy = sfzhdkqy;
    }

    public Float getStb() {
        return stb;
    }

    public void setStb(Float stb) {
        this.stb = stb;
    }

    public Float getXfpll() {
        return xfpll;
    }

    public void setXfpll(Float xfpll) {
        this.xfpll = xfpll;
    }

    public Float getBll() {
        return bll;
    }

    public void setBll(Float bll) {
        this.bll = bll;
    }

    public String getCzpm() {
        return czpm;
    }

    public void setCzpm(String czpm) {
        this.czpm = czpm;
    }

    public String getCzqc() {
        return czqc;
    }

    public void setCzqc(String czqc) {
        this.czqc = czqc;
    }

    public String getXfcl_tywysbm() {
        return xfcl_tywysbm;
    }

    public void setXfcl_tywysbm(String xfcl_tywysbm) {
        this.xfcl_tywysbm = xfcl_tywysbm;
    }

    public String getClsbdh() {
        return clsbdh;
    }

    public void setClsbdh(String clsbdh) {
        this.clsbdh = clsbdh;
    }

    public String getClxnzb_jyqk() {
        return clxnzb_jyqk;
    }

    public void setClxnzb_jyqk(String clxnzb_jyqk) {
        this.clxnzb_jyqk = clxnzb_jyqk;
    }

    public String getZblxdm() {
        return zblxdm;
    }

    public void setZblxdm(String zblxdm) {
        this.zblxdm = zblxdm;
    }

    public String getCldjdm() {
        return cldjdm;
    }

    public void setCldjdm(String cldjdm) {
        this.cldjdm = cldjdm;
    }
}
