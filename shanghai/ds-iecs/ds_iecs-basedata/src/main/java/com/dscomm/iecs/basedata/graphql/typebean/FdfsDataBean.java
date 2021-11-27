package com.dscomm.iecs.basedata.graphql.typebean;

import java.io.Serializable;

public class FdfsDataBean implements Serializable {


    private static final long serialVersionUID = 1L;

    private String id;
    /** fastdfs返回的id */
    private String fileId;
    /** 文件名称 */
    private String fileName;
    /** 文件大小 */
    private String fileSize;

    /** 地址 */
    private String srcIpAddr;

    /** 下载次数 */
    private int fileDownLoadCount;
    /** 上传用户 */
    private String uploadUser;
    // 文件下载全地址
    private String path;
    /** 上传系统 */
    private String uploadSystem;

    private String createTime;
    private String imagepath;
    private String indownloadaddr;
    private String outdownloadaddr;
    private String rotate;
    private String qrpath;
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getSrcIpAddr() {
        return srcIpAddr;
    }

    public void setSrcIpAddr(String srcIpAddr) {
        this.srcIpAddr = srcIpAddr;
    }

    public int getFileDownLoadCount() {
        return fileDownLoadCount;
    }

    public void setFileDownLoadCount(int fileDownLoadCount) {
        this.fileDownLoadCount = fileDownLoadCount;
    }

    public String getUploadUser() {
        return uploadUser;
    }

    public void setUploadUser(String uploadUser) {
        this.uploadUser = uploadUser;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUploadSystem() {
        return uploadSystem;
    }

    public void setUploadSystem(String uploadSystem) {
        this.uploadSystem = uploadSystem;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public String getIndownloadaddr() {
        return indownloadaddr;
    }

    public void setIndownloadaddr(String indownloadaddr) {
        this.indownloadaddr = indownloadaddr;
    }

    public String getOutdownloadaddr() {
        return outdownloadaddr;
    }

    public void setOutdownloadaddr(String outdownloadaddr) {
        this.outdownloadaddr = outdownloadaddr;
    }

    public String getRotate() {
        return rotate;
    }

    public void setRotate(String rotate) {
        this.rotate = rotate;
    }

    public String getQrpath() {
        return qrpath;
    }

    public void setQrpath(String qrpath) {
        this.qrpath = qrpath;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
