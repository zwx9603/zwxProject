package com.dscomm.iecs.basedata.service.impl;


import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.dal.po.DangerousChemicalsEntity;
import com.dscomm.iecs.basedata.dal.repository.DangerousChemicalsRepository;
import com.dscomm.iecs.basedata.exception.BasedataException;
import com.dscomm.iecs.basedata.graphql.typebean.DangerousChemicalsBean;
import com.dscomm.iecs.basedata.service.DangerousChemicalsService;
import com.dscomm.iecs.basedata.utils.OtherResourceTransformUtil;
import org.apache.logging.log4j.util.Strings;
import org.mx.dal.service.GeneralAccessor;
import org.mx.spring.session.SessionDataStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * @author: zhangsheng
 * @Date: 2020/4/5 16:25
 * @describe: 危化品服务实现
 */
@Component
public class DangerousChemicalsServiceImpl implements DangerousChemicalsService {
    private static final Logger logger = LoggerFactory.getLogger(DangerousChemicalsServiceImpl.class);
    private GeneralAccessor accessor;
    private SessionDataStore store;
    private LogService logService;
    private DangerousChemicalsRepository chemicalsElasticRepository;

    @Autowired
    public DangerousChemicalsServiceImpl(@Qualifier("generalAccessor") GeneralAccessor accessor, SessionDataStore store,
                                         DangerousChemicalsRepository chemicalsElasticRepository, LogService logService) {
        this.accessor = accessor;
        this.store = store;
        this.chemicalsElasticRepository = chemicalsElasticRepository;
        this.logService = logService;
    }

    /**
     * 根据名称查询危化品信息
     *
     * @param keyword 名称关键字
     * @return result
     */
    @Transactional(readOnly = true)
    @Override
    public List<DangerousChemicalsBean> getDangerousChemicalsList(String keyword) {
        try {
            logService.infoLog(logger, "service", "getDangerousChemicalsList", "service is started...");
            Long logStart = System.currentTimeMillis();
            List<DangerousChemicalsBean> beanList = new ArrayList<>();

            List<DangerousChemicalsEntity> entityList = null ;
            logService.infoLog(logger, "service", "getDangerousChemicalsList", "service is started...");
            Long start = System.currentTimeMillis();

            //查询
            if(Strings.isBlank( keyword )){
                entityList = chemicalsElasticRepository.getChemicalsElasticList( );
            }else{
                entityList = chemicalsElasticRepository.getChemicalsElasticList( "%" + keyword + "%" );
            }

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "service", "getDangerousChemicalsList", String.format("service is finished,execute time is :%sms", end - start));

            if (entityList!=null&&entityList.size()>0){
                for (DangerousChemicalsEntity entity:entityList
                     ) {
                    DangerousChemicalsBean bean = OtherResourceTransformUtil.transform(entity);
                    beanList.add(bean);
                }
            }
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "getDangerousChemicalsList", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return beanList;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "getDangerousChemicalsList", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_DATA_FAIL);
        }
    }


}
