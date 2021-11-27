package com.dscomm.iecs.keydata.service.timeline.bo;

/**
 * 时间轴附件对象
 *
 * @author YangFuxi
 * Date Time 2019/8/15 17:05
 */
public class EsTimelineAttachmentBO {

    /**
     * 文件名
     */
    private String filename;

    /**
     * 文件全路径（不带协议、ip和端口）
     */
    private String fileuri;

    /**
     * 文件后缀名
     */
    private String suffix;

    public String getFilename() {
        return filename;
    }

    public String getFileuri() {
        return fileuri;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setFileuri(String fileuri) {
        this.fileuri = fileuri;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

}
