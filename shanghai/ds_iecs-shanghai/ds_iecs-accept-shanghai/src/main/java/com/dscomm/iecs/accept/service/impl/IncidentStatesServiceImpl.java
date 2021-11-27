package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.IncidentStatesEntity;
import com.dscomm.iecs.accept.dal.repository.IncidentStatesRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.IncidentStatesInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.FunctionInfoBean;
import com.dscomm.iecs.accept.graphql.typebean.IncidentStatesBean;
import com.dscomm.iecs.accept.service.IncidentStatesService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.service.DictionaryService;
import org.apache.logging.log4j.util.Strings;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component("incidentStatesServiceImpl")
public class IncidentStatesServiceImpl implements IncidentStatesService {

    private static final Logger logger = LoggerFactory.getLogger(IncidentStatesServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private IncidentStatesRepository incidentStatesRepository;
    private DictionaryService dictionaryService;
    private List<String> dics;

    @Autowired
    public IncidentStatesServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor,
                                     DictionaryService dictionaryService,IncidentStatesRepository incidentStatesRepository

    ){
        this.logService = logService;
        this.accessor = accessor;
        this.dictionaryService = dictionaryService;
        this.incidentStatesRepository = incidentStatesRepository;
        dics = new ArrayList<>(Arrays.asList( "AJZT" ));
    }

    /*
     * 查询案件的所有状态
     * */
    @Override
    public List<IncidentStatesBean> queryInfo() {
        try {
            logService.infoLog(logger, "service", "queryInfo", "service is started...");
            Long logStart = System.currentTimeMillis();
             // 从字典中去除所有的案件状态
            List<IncidentStatesBean> list = new ArrayList<>();
            Map<String, Map<String, String>>  dicsMap = dictionaryService.findDictionaryMap(dics);

            Map<String, String> ajzt = dicsMap.get("AJZT");
            // 对Map进行遍历
            Set<String> strings = ajzt.keySet();
            for (String ele: strings){
                IncidentStatesBean incidentStatesBean = new IncidentStatesBean();
                incidentStatesBean.setAjztdm(ele); // 案件状态代码
                incidentStatesBean.setAjztdmmc(ajzt.get(ele)); // 案件状态的名称
                list.add(incidentStatesBean);
            }
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "queryInfo", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return list;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "queryAll", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_FAIL);
        }
    }

    /*
     * 根据案件id查询案件的局部状态
     * */
    @Override
    public List<IncidentStatesBean> queryInfoByInfo(String ajid) {
        if (Strings.isBlank(ajid)) {
            logService.infoLog(logger, "service", "queryInfoByInfo", "ajid is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "queryInfoByInfo", "service is started...");
            Long logStart = System.currentTimeMillis();

            logService.infoLog(logger, "repository", "queryInfoByInfo", "repository is started...");
            Long start = System.currentTimeMillis();
            List<IncidentStatesEntity> incidentStatesEntities = incidentStatesRepository.queryInfoByInfo(ajid);
            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "queryInfoByInfo", String.format("repository is finished,execute time is :%sms", end - start));
            List<IncidentStatesBean> list = new ArrayList<>();
            Map<String, Map<String, String>>  dicsMap = dictionaryService.findDictionaryMap(dics);
            for (IncidentStatesEntity ele: incidentStatesEntities) {
                IncidentStatesBean incidentStatesBean = new IncidentStatesBean();
                incidentStatesBean.setAjztdm(ele.getAjztdm()); // 案件状态代码
                incidentStatesBean.setAjztdmmc(dicsMap.get("AJZT").get(ele.getAjztdm())); // 案件状态代码对应的名称
                list.add(incidentStatesBean);
            }
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "queryInfoByInfo", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return list;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "queryInfoByInfo", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_FAIL);
        }
    }

    /*
     *根据案件状态1,修改案件状态2中有哪些状态可以显示
     * */
    /*@Transactional(readOnly = true)
    @Override
    public Boolean updateInfo(IncidentStatesInputInfo info) {
        if (info == null) {
            logService.infoLog(logger, "service", "updateInfo", "info is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "updateInfo", "service is started...");
            Long logStart = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "updateInfo", "repository is started...");
            Long start = System.currentTimeMillis();
            String ajid = info.getAjid(); // 案件id
            String ajztdm = info.getAjztdm(); // 案件状态代码
//            incidentStatesRepository.updateInfo(ajid,ajztdm);
            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "updateInfo", String.format("repository is finished,execute time is :%sms", end - start));
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "updateInfo", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return true;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "updateInfo", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DATA_FAIL);
        }
    }*/
}
