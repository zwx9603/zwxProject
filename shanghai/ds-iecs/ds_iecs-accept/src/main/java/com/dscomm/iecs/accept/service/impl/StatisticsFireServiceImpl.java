package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.repository.StatisticsFireRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.typebean.KeyUnitLevelCountBean;
import com.dscomm.iecs.accept.service.StatisticsFireService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.service.DictionaryService;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 统计服务实现类
 */
@Component("StatisticsFireServiceImpl")
public class StatisticsFireServiceImpl implements StatisticsFireService {

    private static final Logger logger = LoggerFactory.getLogger(StatisticsFireServiceImpl.class);
    private Environment env;
    private GeneralAccessor accessor;
    private LogService logService;
    private DictionaryService dictionaryService;
    private StatisticsFireRepository statisticsFireRepository ;


    private List<String> dics;

    @Autowired
    public StatisticsFireServiceImpl(Environment env, @Qualifier("generalAccessor") GeneralAccessor accessor, LogService logService,
                                     DictionaryService dictionaryService, StatisticsFireRepository statisticsFireRepository

    ) {
        this.env = env;
        this.accessor = accessor;
        this.logService = logService;
        this.dictionaryService = dictionaryService;
        this.statisticsFireRepository = statisticsFireRepository ;

        dics = new ArrayList<>(Arrays.asList("DWDJ"   ));
    }

    /**
     * 统计各个等级重点单位数量信息
     * @ return KeyUnitLevelCountBean 集合
     * */
    @Transactional(readOnly = true)
    @Override
    public   List<KeyUnitLevelCountBean> countKeyUnitLevel(){
        try {
            logService.infoLog(logger, "service", "countKeyUnitLevel", "service is started...");
            Long logStart = System.currentTimeMillis();
            List<KeyUnitLevelCountBean> keyUnitLevelCountBeanList = new ArrayList<>();
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;
            logService.infoLog(logger, "service", "countKeyUnitLevel", "repository is started...");
            Long start = System.currentTimeMillis();
            List<Map<String,Object>> keyUnitLevelList = statisticsFireRepository.countKeyUnitLevel();
            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "fcountKeyUnitLevel", String.format("repository is finished,execute time is :%sms", end - start));
            if(keyUnitLevelList != null && keyUnitLevelList.size()>0){
                for (Map<String,Object> map:keyUnitLevelList){
                    KeyUnitLevelCountBean keyUnitLevelCountBean = new KeyUnitLevelCountBean();
                    if (map.get("unitLevelCode") != null && !map.get("unitLevelCode").toString().equals("")){
                        keyUnitLevelCountBean.setUnitLevelCode(map.get("unitLevelCode").toString());
                        keyUnitLevelCountBean.setUnitLevelName(dicsMap.get("DWDJ").get(keyUnitLevelCountBean.getUnitLevelCode()));
                    }else {
                        keyUnitLevelCountBean.setUnitLevelCode("0");
                    }
                    keyUnitLevelCountBean.setKeyUnitCount(Integer.parseInt(map.get("count").toString()));
                    keyUnitLevelCountBeanList.add(keyUnitLevelCountBean);
                }
            }
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "countKeyUnitLevel", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return keyUnitLevelCountBeanList;
        }catch(Exception e){
            throw new AcceptException(AcceptException.AccetpErrors.COUNT_KEYUNITLEVEL_FAIL);

        }

    }

}
