package com.dscomm.iecs.out.service;

import com.dscomm.iecs.out.graphql.typebean.AlarmJqcjDpTjfaBean;

import java.util.List;

public interface AlarmJqcjDpTjfaService {
    List<AlarmJqcjDpTjfaBean> getAlarmJqcjDpTjfaListByTime(Long st, Long et, String username);
}
