package com.dscomm.iecs.hangzhou.restful.vo;

/**
 * 描述： 信息 通知数据对象
 *
 */
public class SmsMessageBackVO {


    private String resultCode ; //响应代码 0表示成功，其他表示错误，详细参见错误代码表

    private String resultMsg ; //提示信息 响应成功失败描述

    private BackMapVO resultMap ;// 返回信息

    class BackMapVO {

        private String batchId ; //批次号 发送批量短信批次唯一标识码

        private String submitWaitSendCount  ; //提交成功数 	发送批量短信批次提交成功数

        private String blackSendList ; //在黑名单中的号码集合 发送批量短信在黑名单中的发送号码

        private String repeatSendList ; //限定重发时间间隔内的号码集合 发送批量短信限定重发时间间隔内的发送号码

        public String getBatchId() {
            return batchId;
        }

        public void setBatchId(String batchId) {
            this.batchId = batchId;
        }

        public String getSubmitWaitSendCount() {
            return submitWaitSendCount;
        }

        public void setSubmitWaitSendCount(String submitWaitSendCount) {
            this.submitWaitSendCount = submitWaitSendCount;
        }

        public String getBlackSendList() {
            return blackSendList;
        }

        public void setBlackSendList(String blackSendList) {
            this.blackSendList = blackSendList;
        }

        public String getRepeatSendList() {
            return repeatSendList;
        }

        public void setRepeatSendList(String repeatSendList) {
            this.repeatSendList = repeatSendList;
        }
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public BackMapVO getResultMap() {
        return resultMap;
    }

    public void setResultMap(BackMapVO resultMap) {
        this.resultMap = resultMap;
    }
}
