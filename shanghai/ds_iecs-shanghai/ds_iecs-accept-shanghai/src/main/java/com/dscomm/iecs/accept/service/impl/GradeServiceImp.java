package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.GradeEntity;
import com.dscomm.iecs.accept.dal.repository.GradeRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.typebean.GradeBean;
import com.dscomm.iecs.accept.service.GradeService;
import com.dscomm.iecs.accept.utils.transform.GradeTransformUtil;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/*
 * @author Zhao Wenxue
 * */
@Component("gradeServiceImp")
public class GradeServiceImp implements GradeService {

    private static final Logger logger = LoggerFactory.getLogger(GradeServiceImp.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private GradeRepository gradeRepository;
    private DictionaryService dictionaryService;
    private List<String> dics;
    @Autowired
    public GradeServiceImp(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor,
                           GradeRepository gradeRepository,DictionaryService dictionaryService

    ){
        this.logService = logService;
        this.accessor = accessor;
        this.gradeRepository = gradeRepository;
        this.dictionaryService = dictionaryService;
        dics = new ArrayList<>(Arrays.asList( "WLCLLX" ));
    }

    /**
     * 根据参数案件类型、案件等级 、处置对象查询
     * @param czdx
     * @param ajdj
     * @param ajlx
     * @return
     */
    @Transactional( readOnly = true )
    @Override
    public List<GradeBean> queryInfo(String czdx, String ajdj, String ajlx) {
        if (Strings.isBlank(czdx)) {
            logService.infoLog(logger, "service", "queryInfo", "czdx is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        } else if (Strings.isBlank(ajdj)){
            logService.infoLog(logger, "service", "queryInfo", "ajdj is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        } else if (Strings.isBlank(ajlx)){
            logService.infoLog(logger, "service", "queryInfo", "ajlx is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "queryInfo", "service is started...");
            Long logStart = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "queryInfo", "repository is started...");
            Long start = System.currentTimeMillis();
            // 执行业务逻辑
            GradeEntity back = gradeRepository.queryInfo(czdx, ajdj, ajlx);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "queryInfo", String.format("repository is finished,execute time is :%sms", end - start));
            GradeTransformUtil gradeTransformUtil = new GradeTransformUtil();
            Map<String, Map<String, String>>  dicsMap = dictionaryService.findDictionaryMap(dics) ;
            List<GradeBean> transform = GradeTransformUtil.transform(back, dicsMap);
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "queryInfo", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return transform;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "queryInfo", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_FAIL);
        }
    }
}
