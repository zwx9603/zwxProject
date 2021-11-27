package com.dscomm.iecs.out.service;

import com.dscomm.iecs.out.graphql.typebean.LocaleOutBean;

import java.util.List;

public interface LocaleOutService {
    List<LocaleOutBean> getAlarmJqxcListByTime(Long st, Long et, String username);
}
