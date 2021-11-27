package com.dscomm.iecs.basedata.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 化危品实体
 */
@Entity
@Table(name = "T_COC_FIRE_WHPZS")
@DynamicUpdate(true)
@DynamicInsert(true)
public class DangerousChemicalsEntity extends BaseEntity {
	@Column(name = "WHP_NM",length =  100 )
	private String whpNm;// 危险品内码
	@Column(name = "WHP_ZWMC",length =  800)
	private String whpZwmc; // 中文名
	@Column(name = "WHP_YWMC",length =  800)
	private String whpYwmc; // 英文名
	@Column(name = "WHP_FZS",length =  800)
	private String whpFzs; // 分子式
	@Column(name = "WHP_FZL",length =  800)
	private String whpFzl; // 分子量
	@Column(name = "WHP_CAS",length =  800)
	private String whpCas; // CAS号
	@Column(name = "WHP_RTECS",length =  800)
	private String whpRtecs; // RTECS号
	@Column(name = "WHP_UN",length =  800)
	private String whpUn; // UN编号
	@Column(name = "WHP_WGH",length =  800)
	private String whpWgh; // 危险货物编号
	@Column(name = "WHP_IMDG",length =  800 )
	private String whpImdg; // IMDG规则页码
	@Column(name = "WHP_WGXZ",length =  800)
	private String whpWgxz; // 外观与性状
	@Column(name = "WHP_ZYYT",length =  800)
	private String whpZyyt; // 主要用途
	@Column(name = "WHP_RD",length =  800)
	private String whpRd; // 熔点
	@Column(name = "WHP_FD",length =  800)
	private String whpFd; // 沸点
	@Column(name = "WHP_XDMDS",length =  800)
	private String whpXdmds; // 相对密度水
	@Column(name = "WHP_XDMDKQ",length =  800)
	private String whpXdmmkq; // 相对密度空气
	@Column(name = "WHP_BHYQY",length =  800)
	private String whpBhyqy; // 饱和蒸汽压
	@Column(name = "WHP_RJX",length =  800)
	private String whpRjx; // 溶解性
	@Column(name = "WHP_SSX",length =  800)
	private String whpSsx; // 燃烧性
	@Column(name = "WHP_JGHYFJ",length =  800)
	private String whpJghyfj; // 建规火险分级
	@Column(name = "WHP_SD",length =  800)
	private String whpSd; // 闪点
	@Column(name = "WHP_ZRWD",length =  800)
	private String whpZrwd; // 自燃温度
	@Column(name = "WHP_BZSX",length =  800)
	private String whpBzsx; // 爆炸下限
	@Column(name = "WHP_BZXX",length =  800)
	private String whpBzxx; // 爆炸上限
	@Column(name = "WHP_WXTX",length =  800)
	private String whpWxtx; // 危险特性
	@Column(name = "WHP_RSFJCW",length =  800)
	private String whpRsfjcw; // 燃烧分解产物
	@Column(name = "WHP_WDX",length =  800)
	private String whpWdx; // 稳定性
	@Column(name = "WHP_JHWH",length =  800)
	private String whpJhwh; // 聚合危害
	@Column(name = "WHP_JJW",length =  800)
	private String whpJjw; // 禁忌物
	@Column(name = "WHP_MHFF",length =  800)
	private String whpMhff; // 灭火方法
	@Column(name = "WHP_WXXLB",length =  800)
	private String whpWxxlb; // 危险性类别
	@Column(name = "WHP_BZBZ",length =  800)
	private String whpBzbz; // 危险货物包装标志
	@Column(name = "WHP_BZLX",length =  800)
	private String whpBzlx; // 包装类别
	@Column(name = "WHP_CYZYSX",length =  800)
	private String whpCyzysx; // 储运注意事项
	@Column(name = "WHP_JCXZ",length =  800)
	private String whpJcxz; // 接触限值
	@Column(name = "WHP_QRTJ",length =  800)
	private String whpQrtj; // 侵入途径
	@Column(name = "WHP_DX",length =  800)
	private String whpDx; // 毒性
	@Column(name = "WHP_JKWH",length =  800)
	private String whpJkwh; // 健康危害
	@Column(name = "WHP_PFJCWH",length =  800)
	private String whpPfjcwh; // 皮肤接触危害
	@Column(name = "WHP_YJJCWH",length =  800)
	private String whpYjjcwh; // 眼睛接触危害
	@Column(name = "WHP_XRWH",length =  800)
	private String whpXrwh; // 吸入危害
	@Column(name = "WHP_SRWH",length =  800)
	private String whpSrwh; // 食入危害
	@Column(name = "WHP_GCKZ",length =  800)
	private String whpGckz; // 工程控制
	@Column(name = "WHP_HXXTFH",length =  800)
	private String whpHxxtfh; // 呼吸系统防护
	@Column(name = "WHP_YJFH",length =  800)
	private String whpYjfh; // 眼睛防护
	@Column(name = "WHP_FHF",length =  800)
	private String whpFhf; // 防护服
	@Column(name = "WHP_LJWD",length =  800)
	private String whpLjwd; // 临界温度
	@Column(name = "WHP_LJYL",length =  800)
	private String whpLjyl; // 临界压力
	@Column(name = "WHP_RSR",length =  800)
	private String whpRsr; // 燃烧热
	@Column(name = "WHP_BMJCTJ",length =  800)
	private String whpBmjctj; // 避免接触的条件
	@Column(name = "WHP_XLCZ",length =  800)
	private String whpXlcz; // 泄漏处置
	@Column(name = "WHP_ZYSX",length =  800)
	private String whpZysx; // 其他注意事项
	@Column(name = "WHP_SLXLGLBJ",length =  800)
	private String whpSlxlglbj; // 少量泄漏隔离半径
	@Column(name = "WHP_SLXLBTSSBJ",length =  800)
	private String whpSlxlbtssbj; // 少量泄漏白天疏散半径
	@Column(name = "WHP_SLXLYJSSBJ",length =  800)
	private String whpSlxlyjssbj; // 少量泄漏夜间疏散半径
	@Column(name = "WHP_DLXLGLBJ",length =  800)
	private String whpDlxlglbj; // 大量泄漏隔离半径
	@Column(name = "WHP_DLXLBTSSBJ",length =  800)
	private String whpDlxlbtssbj; // 大量泄漏白天疏散半径
	@Column(name = "WHP_DLXLYJSSBJ",length =  800)
	private String whpDlxlyjssbj; // 大量泄漏夜间疏散半径

	@Column(name = "WHP_SFH",length =  800)
	private String whpSfh; //手防护

	public String getWhpSfh() {
		return whpSfh;
	}

	public void setWhpSfh(String whpSfh) {
		this.whpSfh = whpSfh;
	}

	public String getWhpNm() {
		return whpNm;
	}

	public void setWhpNm(String whpNm) {
		this.whpNm = whpNm;
	}

	public String getWhpZwmc() {
		return whpZwmc;
	}
	public void setWhpZwmc(String whpZwmc) {
		this.whpZwmc = whpZwmc;
	}
	public String getWhpYwmc() {
		return whpYwmc;
	}
	public void setWhpYwmc(String whpYwmc) {
		this.whpYwmc = whpYwmc;
	}
	public String getWhpFzs() {
		return whpFzs;
	}
	public void setWhpFzs(String whpFzs) {
		this.whpFzs = whpFzs;
	}
	public String getWhpFzl() {
		return whpFzl;
	}
	public void setWhpFzl(String whpFzl) {
		this.whpFzl = whpFzl;
	}
	public String getWhpCas() {
		return whpCas;
	}
	public void setWhpCas(String whpCas) {
		this.whpCas = whpCas;
	}
	public String getWhpRtecs() {
		return whpRtecs;
	}
	public void setWhpRtecs(String whpRtecs) {
		this.whpRtecs = whpRtecs;
	}
	public String getWhpUn() {
		return whpUn;
	}
	public void setWhpUn(String whpUn) {
		this.whpUn = whpUn;
	}
	public String getWhpWgh() {
		return whpWgh;
	}
	public void setWhpWgh(String whpWgh) {
		this.whpWgh = whpWgh;
	}
	public String getWhpImdg() {
		return whpImdg;
	}
	public void setWhpImdg(String whpImdg) {
		this.whpImdg = whpImdg;
	}
	public String getWhpWgxz() {
		return whpWgxz;
	}
	public void setWhpWgxz(String whpWgxz) {
		this.whpWgxz = whpWgxz;
	}
	public String getWhpZyyt() {
		return whpZyyt;
	}
	public void setWhpZyyt(String whpZyyt) {
		this.whpZyyt = whpZyyt;
	}
	public String getWhpRd() {
		return whpRd;
	}
	public void setWhpRd(String whpRd) {
		this.whpRd = whpRd;
	}
	public String getWhpFd() {
		return whpFd;
	}
	public void setWhpFd(String whpFd) {
		this.whpFd = whpFd;
	}
	public String getWhpXdmds() {
		return whpXdmds;
	}
	public void setWhpXdmds(String whpXdmds) {
		this.whpXdmds = whpXdmds;
	}
	public String getWhpXdmmkq() {
		return whpXdmmkq;
	}
	public void setWhpXdmmkq(String whpXdmmkq) {
		this.whpXdmmkq = whpXdmmkq;
	}
	public String getWhpBhyqy() {
		return whpBhyqy;
	}
	public void setWhpBhyqy(String whpBhyqy) {
		this.whpBhyqy = whpBhyqy;
	}
	public String getWhpRjx() {
		return whpRjx;
	}
	public void setWhpRjx(String whpRjx) {
		this.whpRjx = whpRjx;
	}
	public String getWhpSsx() {
		return whpSsx;
	}
	public void setWhpSsx(String whpSsx) {
		this.whpSsx = whpSsx;
	}
	public String getWhpJghyfj() {
		return whpJghyfj;
	}
	public void setWhpJghyfj(String whpJghyfj) {
		this.whpJghyfj = whpJghyfj;
	}
	public String getWhpSd() {
		return whpSd;
	}
	public void setWhpSd(String whpSd) {
		this.whpSd = whpSd;
	}
	public String getWhpZrwd() {
		return whpZrwd;
	}
	public void setWhpZrwd(String whpZrwd) {
		this.whpZrwd = whpZrwd;
	}
	public String getWhpBzsx() {
		return whpBzsx;
	}
	public void setWhpBzsx(String whpBzsx) {
		this.whpBzsx = whpBzsx;
	}
	public String getWhpBzxx() {
		return whpBzxx;
	}
	public void setWhpBzxx(String whpBzxx) {
		this.whpBzxx = whpBzxx;
	}
	public String getWhpWxtx() {
		return whpWxtx;
	}
	public void setWhpWxtx(String whpWxtx) {
		this.whpWxtx = whpWxtx;
	}
	public String getWhpRsfjcw() {
		return whpRsfjcw;
	}
	public void setWhpRsfjcw(String whpRsfjcw) {
		this.whpRsfjcw = whpRsfjcw;
	}
	public String getWhpWdx() {
		return whpWdx;
	}
	public void setWhpWdx(String whpWdx) {
		this.whpWdx = whpWdx;
	}
	public String getWhpJhwh() {
		return whpJhwh;
	}
	public void setWhpJhwh(String whpJhwh) {
		this.whpJhwh = whpJhwh;
	}
	public String getWhpJjw() {
		return whpJjw;
	}
	public void setWhpJjw(String whpJjw) {
		this.whpJjw = whpJjw;
	}
	public String getWhpMhff() {
		return whpMhff;
	}
	public void setWhpMhff(String whpMhff) {
		this.whpMhff = whpMhff;
	}
	public String getWhpWxxlb() {
		return whpWxxlb;
	}
	public void setWhpWxxlb(String whpWxxlb) {
		this.whpWxxlb = whpWxxlb;
	}
	public String getWhpBzbz() {
		return whpBzbz;
	}
	public void setWhpBzbz(String whpBzbz) {
		this.whpBzbz = whpBzbz;
	}
	public String getWhpBzlx() {
		return whpBzlx;
	}
	public void setWhpBzlx(String whpBzlx) {
		this.whpBzlx = whpBzlx;
	}
	public String getWhpCyzysx() {
		return whpCyzysx;
	}
	public void setWhpCyzysx(String whpCyzysx) {
		this.whpCyzysx = whpCyzysx;
	}
	public String getWhpJcxz() {
		return whpJcxz;
	}
	public void setWhpJcxz(String whpJcxz) {
		this.whpJcxz = whpJcxz;
	}
	public String getWhpQrtj() {
		return whpQrtj;
	}
	public void setWhpQrtj(String whpQrtj) {
		this.whpQrtj = whpQrtj;
	}
	public String getWhpDx() {
		return whpDx;
	}
	public void setWhpDx(String whpDx) {
		this.whpDx = whpDx;
	}
	public String getWhpJkwh() {
		return whpJkwh;
	}
	public void setWhpJkwh(String whpJkwh) {
		this.whpJkwh = whpJkwh;
	}
	public String getWhpPfjcwh() {
		return whpPfjcwh;
	}
	public void setWhpPfjcwh(String whpPfjcwh) {
		this.whpPfjcwh = whpPfjcwh;
	}
	public String getWhpYjjcwh() {
		return whpYjjcwh;
	}
	public void setWhpYjjcwh(String whpYjjcwh) {
		this.whpYjjcwh = whpYjjcwh;
	}
	public String getWhpXrwh() {
		return whpXrwh;
	}
	public void setWhpXrwh(String whpXrwh) {
		this.whpXrwh = whpXrwh;
	}
	public String getWhpSrwh() {
		return whpSrwh;
	}
	public void setWhpSrwh(String whpSrwh) {
		this.whpSrwh = whpSrwh;
	}
	public String getWhpGckz() {
		return whpGckz;
	}
	public void setWhpGckz(String whpGckz) {
		this.whpGckz = whpGckz;
	}
	public String getWhpHxxtfh() {
		return whpHxxtfh;
	}
	public void setWhpHxxtfh(String whpHxxtfh) {
		this.whpHxxtfh = whpHxxtfh;
	}
	public String getWhpYjfh() {
		return whpYjfh;
	}
	public void setWhpYjfh(String whpYjfh) {
		this.whpYjfh = whpYjfh;
	}
	public String getWhpFhf() {
		return whpFhf;
	}
	public void setWhpFhf(String whpFhf) {
		this.whpFhf = whpFhf;
	}
	public String getWhpLjwd() {
		return whpLjwd;
	}
	public void setWhpLjwd(String whpLjwd) {
		this.whpLjwd = whpLjwd;
	}
	public String getWhpLjyl() {
		return whpLjyl;
	}
	public void setWhpLjyl(String whpLjyl) {
		this.whpLjyl = whpLjyl;
	}
	public String getWhpRsr() {
		return whpRsr;
	}
	public void setWhpRsr(String whpRsr) {
		this.whpRsr = whpRsr;
	}
	public String getWhpBmjctj() {
		return whpBmjctj;
	}
	public void setWhpBmjctj(String whpBmjctj) {
		this.whpBmjctj = whpBmjctj;
	}
	public String getWhpXlcz() {
		return whpXlcz;
	}
	public void setWhpXlcz(String whpXlcz) {
		this.whpXlcz = whpXlcz;
	}
	public String getWhpZysx() {
		return whpZysx;
	}
	public void setWhpZysx(String whpZysx) {
		this.whpZysx = whpZysx;
	}
	public String getWhpSlxlglbj() {
		return whpSlxlglbj;
	}
	public void setWhpSlxlglbj(String whpSlxlglbj) {
		this.whpSlxlglbj = whpSlxlglbj;
	}
	public String getWhpSlxlbtssbj() {
		return whpSlxlbtssbj;
	}
	public void setWhpSlxlbtssbj(String whpSlxlbtssbj) {
		this.whpSlxlbtssbj = whpSlxlbtssbj;
	}
	public String getWhpSlxlyjssbj() {
		return whpSlxlyjssbj;
	}
	public void setWhpSlxlyjssbj(String whpSlxlyjssbj) {
		this.whpSlxlyjssbj = whpSlxlyjssbj;
	}
	public String getWhpDlxlglbj() {
		return whpDlxlglbj;
	}
	public void setWhpDlxlglbj(String whpDlxlglbj) {
		this.whpDlxlglbj = whpDlxlglbj;
	}
	public String getWhpDlxlbtssbj() {
		return whpDlxlbtssbj;
	}
	public void setWhpDlxlbtssbj(String whpDlxlbtssbj) {
		this.whpDlxlbtssbj = whpDlxlbtssbj;
	}
	public String getWhpDlxlyjssbj() {
		return whpDlxlyjssbj;
	}
	public void setWhpDlxlyjssbj(String whpDlxlyjssbj) {
		this.whpDlxlyjssbj = whpDlxlyjssbj;
	}
}
