package com.dscomm.iecs.accept.restful.vo.ReportVO;

/**
 * 录音号VO
 * */
public class RecordDTO {

    private String AJID ;//案件id
    private String THHM ;//通话号码
    private String LYBH ;//录音编号
    private String LYBFDZ ;//录音播放地址
    private String LYSJ ;//录音时间

    public String getAJID() {
        return AJID;
    }

    public void setAJID(String AJID) {
        this.AJID = AJID;
    }

    public String getTHHM() {
        return THHM;
    }

    public void setTHHM(String THHM) {
        this.THHM = THHM;
    }

    public String getLYBH() {
        return LYBH;
    }

    public void setLYBH(String LYBH) {
        this.LYBH = LYBH;
    }

    public String getLYBFDZ() {
        return LYBFDZ;
    }

    public void setLYBFDZ(String LYBFDZ) {
        this.LYBFDZ = LYBFDZ;
    }

    public String getLYSJ() {
        return LYSJ;
    }

    public void setLYSJ(String LYSJ) {
        this.LYSJ = LYSJ;
    }
}
