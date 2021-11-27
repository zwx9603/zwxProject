package com.dscomm.iecs.basedata.service.impl;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.dal.po.LeaderEntity;
import com.dscomm.iecs.basedata.dal.repository.LeaderRepository;
import com.dscomm.iecs.basedata.exception.BasedataException;
import com.dscomm.iecs.basedata.graphql.typebean.LeaderBean;
import com.dscomm.iecs.basedata.service.LeaderService;
import com.dscomm.iecs.basedata.utils.PersonTransformUtil;
import org.apache.logging.log4j.util.Strings;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zs
 * @Date: 16:14 2020/7/24
 * desc:
 */
@Component
public class LeaderServiceImpl implements LeaderService {
    private static final Logger logger = LoggerFactory.getLogger(LeaderServiceImpl.class);
    private GeneralAccessor accessor;
    private LogService logService;
    private LeaderRepository leaderRepository;

    @Autowired
    public LeaderServiceImpl(@Qualifier("generalAccessor") GeneralAccessor accessor  , LogService logService, LeaderRepository leaderRepository){
        this.accessor = accessor;
        this.logService = logService;
        this.leaderRepository = leaderRepository;
    }

    /**
     * 领导列表
     * @param type 领导类型
     * @return
     */

    @Transactional(readOnly = true)
    @Override
    public List<LeaderBean> LeaderList(String type) {
        try {
            logService.infoLog(logger, "service", "LeaderList", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<LeaderBean> beans = new ArrayList<>();
            List<LeaderEntity> entities = new ArrayList<>();
            if (Strings.isBlank(type)){
                logService.infoLog(logger, "service", "LeaderList", "service is started...");
                Long start = System.currentTimeMillis();
                //查询
                entities = leaderRepository.getLeaderEntities();
                Long end = System.currentTimeMillis();
                logService.infoLog(logger, "service", "LeaderList", String.format("service is finished,execute time is :%sms", end - start));

            }else {
                logService.infoLog(logger, "service", "LeaderList", "service is started...");
                Long start = System.currentTimeMillis();
                //查询
                entities = leaderRepository.getLeaderEntitiesByType(type);
                Long end = System.currentTimeMillis();
                logService.infoLog(logger, "service", "LeaderList", String.format("service is finished,execute time is :%sms", end - start));

            }
            if (entities!=null&&entities.size()>0){
                for (LeaderEntity entity:entities
                     ) {
                    LeaderBean bean = PersonTransformUtil.transform(entity);
                    beans.add(bean);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "LeaderList", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return beans;
        }catch (Exception ex){
            if (logger.isErrorEnabled()) {
                logger.error("获取领导列表失败", ex);
            }
            throw new BasedataException(BasedataException.BasedataErrors.FIND_DATA_FAIL);
        }
    }
}
