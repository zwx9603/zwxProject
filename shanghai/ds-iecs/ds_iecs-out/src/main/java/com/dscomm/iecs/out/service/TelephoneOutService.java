package com.dscomm.iecs.out.service;

import com.dscomm.iecs.out.graphql.typebean.TelephoneOutBean;

import java.util.List;

public interface TelephoneOutService  {
    List<TelephoneOutBean> getAlarmThjlListByTime(Long st, Long et, String username);
}
