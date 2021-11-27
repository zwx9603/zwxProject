package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.inputbean.CommonPhraseSaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.CommonPhraseBean;

import java.util.List;

/**
 * 常用短语
 * @author Wangjy
 * Date 2018/7/9 10:25
 */
public interface CommonPhraseService {

    /**
     * 根据短语类型和事件类型获取短语
     * @param phraseType 短语类型
     * @param incidentType 事件类型
     * @return 短语列表
     */
    List<CommonPhraseBean> findCommonPhrase(String phraseType, String incidentType   );


    /**
     * 保存常用短语
     * @param inputInfo
     * @return
     */
    CommonPhraseBean saveCommonPhrase( CommonPhraseSaveInputInfo inputInfo);



    /**
     *  删除 常用短语
     * @param commonPhraseId
     * @return
     */
    Boolean  removeCommonPhrase(String commonPhraseId );
}
