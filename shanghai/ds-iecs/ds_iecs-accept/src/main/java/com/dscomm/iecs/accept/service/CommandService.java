package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.fireinputbean.CommandSaveInputInfo;
import com.dscomm.iecs.accept.graphql.firetypebean.CommandBean;

public interface CommandService {

    CommandBean saveCommand(CommandSaveInputInfo queryBean);
}
