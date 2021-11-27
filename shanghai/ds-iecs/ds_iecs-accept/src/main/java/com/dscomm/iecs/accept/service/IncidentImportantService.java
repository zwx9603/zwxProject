package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.fireinputbean.IncidentImportantRuleSaveInputInfo;
import com.dscomm.iecs.accept.graphql.firetypebean.IncidentImportantRuleBean;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述：重要警情规则 服务层
 *
 */
@Service
public interface IncidentImportantService {


    /**
     * 重要警情规则信息修改
     *
     * @param incidentImportantSaveInputInfo 重大警情规则
     * @return  重要警情规则
     */
    IncidentImportantRuleBean saveIncidentImportant(IncidentImportantRuleSaveInputInfo incidentImportantSaveInputInfo);

    /**
     * 删除重要警情规则
     *
     * @param incidentImportantRuleId 重要案件规则 id
     * @return  是否删除成功
     */
    Boolean removeIncidentImportantRule(String incidentImportantRuleId);


    /**
     * 启用 重要警情规则
     */
    Boolean  enabledIncidentImportantRule ( String  incidentImportantRuleId     ) ;


    /**
     * 查询 重要警情规则
     */
    List<IncidentImportantRuleBean> findIncidentImportantRuleCondition (   ) ;


    /**
     * 根据 警情id 判断 警情是否为 重要警情 并通知
     */
    Boolean judgeIncidentImportant( String incidentId ) ;

}
