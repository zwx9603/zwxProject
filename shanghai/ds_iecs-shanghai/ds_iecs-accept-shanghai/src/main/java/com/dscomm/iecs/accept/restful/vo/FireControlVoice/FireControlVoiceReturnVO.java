package com.dscomm.iecs.accept.restful.vo.FireControlVoice;

import lombok.Data;

import java.util.List;

@Data
public class FireControlVoiceReturnVO {

    private String code;

    private String rtnInfo;

    private List<FireControlVoiceVO> data;

}
