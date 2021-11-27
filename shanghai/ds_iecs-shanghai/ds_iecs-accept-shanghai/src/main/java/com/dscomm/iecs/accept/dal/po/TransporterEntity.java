package com.dscomm.iecs.accept.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述： DI服务传输配置实体
 *
 * @author AIguibin Date time 2018/7/11 12:46
 */
@Entity
@Table(name = "DS_COC_DI_CONFIG")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TransporterEntity extends BaseEntity {
    private Long ZHTBSJ;  //最后同步时间
    private String CSSM;   //传输机说明
    private int DCTBSC=1;//单次同步时长，默认为1
    private int DCTBSCDW=3;//单次同步时长单位，0代表年，1代表月，2代表日，3代表时，4代表分，5代表秒，默认3 取小时

    //每次同步时都要向前推5分钟，以免时间上的差异导致同步失败。
    public Long getZHTBSJ() {
        return ZHTBSJ-1*60*1000;
    }

    public void setZHTBSJ(Long ZHTBSJ) {
        this.ZHTBSJ = ZHTBSJ;
    }

    public String getCSSM() {
        return CSSM;
    }

    public void setCSSM(String CSSM) {
        this.CSSM = CSSM;
    }

    public int getDCTBSC() {
        return DCTBSC;
    }

    public void setDCTBSC(int DCTBSC) {
        this.DCTBSC = DCTBSC;
    }

    public int getDCTBSCDW() {
        return DCTBSCDW;
    }

    public void setDCTBSCDW(int DCTBSCDW) {
        this.DCTBSCDW = DCTBSCDW;
    }
}
