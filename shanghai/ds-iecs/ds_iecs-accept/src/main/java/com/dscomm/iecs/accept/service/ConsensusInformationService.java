package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.firetypebean.ConsensusInformationBean;

import java.util.List;

public interface ConsensusInformationService {

    //舆情列表
    List<ConsensusInformationBean> findConsensusInformation( Long currentTimeStart, Long currentTimeEnd );

    //根据id查询舆情
    ConsensusInformationBean findConsensusInformationById(String id);
}
