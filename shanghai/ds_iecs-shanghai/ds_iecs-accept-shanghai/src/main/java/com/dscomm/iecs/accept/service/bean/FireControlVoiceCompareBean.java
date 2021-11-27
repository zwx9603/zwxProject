package com.dscomm.iecs.accept.service.bean;

import lombok.Data;

/**警情和电台对应bean，用来比对并绑定警情和录音记录关系*/

@Data
public class FireControlVoiceCompareBean {

    private String incident;

    private String radioCallSign;

    private Long noticeTime;

    private Long returnTime;

}
