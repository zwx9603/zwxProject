package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.typebean.SoundRecordBean;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface SoundRecordService {


    /**
     * 保存录音信息
     * @param soundRecord
     * @return
     */
    SoundRecordBean  saveSoundRecord( SoundRecordBean soundRecord ) ;

    /**
     * 根据id 录音信息 更新 案件id
     * @param soundRecordId
     * @return
     */
    SoundRecordBean  updateSoundRecordIncidentId ( String soundRecordId , String  incidentId  ) ;



    /**
     *  根据案件ids 类型
     */
    Map<String, List<SoundRecordBean>>  findSoundRecordMapByIncidentIdList(   List<String> incidentIdList  , List<Integer> typeList  ) ;
}
