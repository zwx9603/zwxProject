package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.firetypebean.FireControlVoiceReturnBean;

public interface FireControlVoiceService {

     /**获取800M录音记录并保存至接处警数据库*/
     FireControlVoiceReturnBean getFireControlVoice(String token);

}
