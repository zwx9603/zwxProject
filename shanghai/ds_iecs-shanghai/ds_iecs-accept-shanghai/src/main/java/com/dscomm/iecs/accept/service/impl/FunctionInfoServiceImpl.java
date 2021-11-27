package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.FunctionInfoEntity;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.FunctionInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.FunctionInfoBean;
import com.dscomm.iecs.accept.service.FunctionInfoService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.service.DictionaryService;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component("functionInfoServiceImpl")
public class FunctionInfoServiceImpl implements FunctionInfoService {

    private static final Logger logger = LoggerFactory.getLogger(FunctionInfoServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private DictionaryService dictionaryService;
    private List<String> dics;

    @Autowired
    public FunctionInfoServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor,
                                   DictionaryService dictionaryService

    ){
        this.logService = logService;
        this.accessor = accessor;
        this.dictionaryService = dictionaryService;
        dics = new ArrayList<>(Arrays.asList( "WLZZGN" ));
    }

    /*
     * 查询车辆的所有功能
     * */
    @Transactional( readOnly = true )
    @Override
    public List<FunctionInfoBean> queryInfo() {
        try {
            logService.infoLog(logger, "service", "queryInfo", "service is started...");
            Long logStart = System.currentTimeMillis();
            // 从字典中取出所有的车辆功能
            List<FunctionInfoBean> list = new ArrayList<>();
            Map<String, Map<String, String>>  dicsMap = dictionaryService.findDictionaryMap(dics);
            Map<String, String> wlzzgn = dicsMap.get("WLZZGN");
            // 对Map进行遍历
            Set<String> strings = wlzzgn.keySet();
            for (String ele: strings){
                FunctionInfoBean functionInfoBean = new FunctionInfoBean();
                functionInfoBean.setGndm(ele);
                functionInfoBean.setGnmc(wlzzgn.get(ele));
                list.add(functionInfoBean);
            }
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "queryInfo", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return list;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "queryInfo", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_FAIL);
        }
    }

    /*
     * 保存一个车辆的功能信息
     * */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean saveFunctionInfo(FunctionInputInfo info) {
        if (info == null) {
            logService.infoLog(logger, "service", "saveFunctionInfo", "AssistanceInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try{
            logService.infoLog(logger, "service", "saveFunctionInfo", "service is started...");
            Long logStart = System.currentTimeMillis();
            FunctionInfoEntity functionInfoEntity = new FunctionInfoEntity();
            functionInfoEntity.setCldm(info.getCldm());
            functionInfoEntity.setGndj(info.getGndj());
            functionInfoEntity.setGndm(info.getGndm());
            functionInfoEntity.setGnmc(info.getGnmc());
            accessor.save(functionInfoEntity);
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveFunctionInfo", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return true;
        }catch (Exception ex){
            logService.erorLog(logger, "service", "saveFunctionInfo", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DATA_FAIL);
        }
    }
}
