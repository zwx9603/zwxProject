package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.fireinputbean.IncidentCircleSaveInputInfo;
import com.dscomm.iecs.accept.graphql.firetypebean.IncidentCircleBean;

public interface IncidentCircleService {


    IncidentCircleBean saveIncidentCircle(IncidentCircleSaveInputInfo queryBean);


}
