package com.dscomm.iecs.integrate.service;

import com.dscomm.iecs.integrate.restful.vo.IncidentCocVO;

public interface CocService {

    /**新增警情通知coc*/
    Boolean newCase(IncidentCocVO vo);

    /**修改警情通知coc*/
    Boolean modifyCase(IncidentCocVO vo);


}
