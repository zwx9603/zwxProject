package com.dscomm.iecs.accept.service.bean;

public class FireTransferVO {
    public FireTransferVO(FireTransferBean fireTransferBO) {
        this.fireTransferBO = fireTransferBO;
    }

    private FireTransferBean fireTransferBO;

    public FireTransferBean getFireTransferBO() {
        return fireTransferBO;
    }

    public void setFireTransferBO(FireTransferBean fireTransferBO) {
        this.fireTransferBO = fireTransferBO;
    }
}
