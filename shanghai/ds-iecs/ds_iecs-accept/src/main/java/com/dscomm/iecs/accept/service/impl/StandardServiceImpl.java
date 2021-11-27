package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.StandardEntity;
import com.dscomm.iecs.accept.dal.repository.StandardRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.StandardQueryInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.UpdateStandardInfo;
import com.dscomm.iecs.accept.graphql.typebean.StandardBean;
import com.dscomm.iecs.accept.graphql.typebean.UpdateStandardBean;
import com.dscomm.iecs.accept.service.StandardService;
import com.dscomm.iecs.accept.utils.StandardTransformUtil;
import com.dscomm.iecs.accept.utils.transform.IncidentTransformUtil;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.exception.BasedataException;
import org.mx.StringUtils;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 描述：规范用语 服务类实现
 */
@Component("standardServiceImpl")
public class StandardServiceImpl implements StandardService {
    private static final Logger logger = LoggerFactory.getLogger(StandardServiceImpl.class);
    private Environment env;
    private GeneralAccessor accessor;
    private LogService logService;
    private StandardRepository standardRepository ;


    private List<String> dics;

    @Autowired
    public StandardServiceImpl(Environment env, @Qualifier("generalAccessor") GeneralAccessor accessor, LogService logService,
                               StandardRepository standardRepository

    ) {
        this.env = env;
        this.accessor = accessor;
        this.logService = logService;
        this.standardRepository = standardRepository ;

        dics = new ArrayList<>(Arrays.asList("DWDJ"   ));
    }
    @Override
    public List<StandardBean> findStandard(StandardQueryInputInfo inputInfo) {

        if ( inputInfo == null   ) {
            logService.infoLog(logger, "service", "findUnitJointPage", "UnitJointServiceQueryInputinfo is Blank.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findUnitJointPage", "service is started...");
            Long logStart = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findUnitJointPage", "repository is started...");
            Long start = System.currentTimeMillis();

            //根据条件查询提示用语
            List<StandardEntity> standardEntityList = standardRepository.findStandard(
                    inputInfo.getKeyword(),  inputInfo.getIncidentBigType() , inputInfo.getIncidentLevel(),
                    inputInfo.getIncidentType(), inputInfo.getPlaceType(),inputInfo.getTipsContent(),
                    inputInfo.getTipsKeyword(),inputInfo.getTipsType(),inputInfo.getStandardType());

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findUnitJointPage", String.format("repository is finished,execute time is :%sms", end - start));


            List<StandardBean> beans = new ArrayList<StandardBean>( ) ;
            //List<StandardBean> res = new ArrayList<StandardBean>() ;
            if (standardEntityList != null && standardEntityList.size() > 0) {
                for ( StandardEntity standardEntity : standardEntityList) {
                    StandardBean bean = StandardTransformUtil.transform(standardEntity ) ;
                    beans.add(bean);
                }
            }
            logService.infoLog(logger, "repository", "findUnitJointPage", "repository is started...");
            Long countStart = System.currentTimeMillis();
            Long countEnd = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findUnitJointPage", String.format("repository is finished,execute time is :%sms", countEnd - countStart));
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findUnitJointPage", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return  beans ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findUnitJointPage", String.format("save Miniature Organization fail "), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_DATA_FAIL);
        }
    }

    /**
     * 新增，修改，删除警情提示
     * @param queryBean
     * @return 返回值描述
     */
    @Override
    public UpdateStandardBean saveOrUpdateStandard(UpdateStandardInfo queryBean){

        try {
            if (queryBean == null) {
                logService.infoLog(logger, "service", "UpdateStandard", "queryBean is null.");
                throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
            }
            logService.infoLog(logger, "service", "UpdateStandard", "service is started...");
            Long logStart = System.currentTimeMillis();

            StandardEntity entity = IncidentTransformUtil.transform(queryBean);


            logService.infoLog(logger, "repository",
                    "save", "repository is started...");
            Long start = System.currentTimeMillis();

            StandardEntity result = accessor.save(entity);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save",
                    String.format("repository is finished,execute time is :%sms", end - start));

            //数据处理
            UpdateStandardBean bean = IncidentTransformUtil.transform(result);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "UpdateStandard", String.format("service is finished,execute time is :%sms", logEnd - logStart));



            return bean;

        } catch (Exception ex) {
            logService.erorLog(logger, "service", "UpdateStandard", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.UPDATE_STANDARD_FAIL);
        }
    }
}
