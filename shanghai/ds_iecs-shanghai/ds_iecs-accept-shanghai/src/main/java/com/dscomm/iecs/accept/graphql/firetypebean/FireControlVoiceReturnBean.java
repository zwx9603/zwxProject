package com.dscomm.iecs.accept.graphql.firetypebean;

import lombok.Data;

import java.util.List;

@Data
public class FireControlVoiceReturnBean {
    private String token;
    private List<FireControlVoiceBean> data;
}
