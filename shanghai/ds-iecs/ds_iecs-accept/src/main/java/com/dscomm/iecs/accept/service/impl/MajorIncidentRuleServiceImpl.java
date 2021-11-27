package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.MajorIncidentRuleEntity;
import com.dscomm.iecs.accept.dal.repository.MajorIncidentRuleRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.fireinputbean.MajorIncidentRuleInputInfo;
import com.dscomm.iecs.accept.graphql.fireinputbean.MajorIncidentRuleQueryInputInfo;
import com.dscomm.iecs.accept.graphql.firetypebean.MajorIncidentRuleBean;
import com.dscomm.iecs.accept.service.MajorIncidentRuleService;
import com.dscomm.iecs.accept.utils.transform.MajorIncidentRuleTransformUtil;
import com.dscomm.iecs.agent.exception.UserInterfaceAgentException;
import com.dscomm.iecs.base.graphql.typebean.PaginationBean;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.exception.BasedataException;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.keydata.service.ServletService;
import org.mx.StringUtils;
import org.mx.dal.Pagination;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component("majorIncidentRuleServiceImpl")
public class MajorIncidentRuleServiceImpl implements MajorIncidentRuleService {
    private static final Logger logger = LoggerFactory.getLogger(MajorIncidentRuleServiceImpl.class);
    private LogService logService;
    private ServletService servletService ;
    private GeneralAccessor accessor;
    private MajorIncidentRuleRepository majorIncidentRuleRepository;
    private DictionaryService dictionaryService;
    private OrganizationService organizationService;
    private List<String> dics;

    @Autowired
    public MajorIncidentRuleServiceImpl(
            LogService logService, ServletService servletService , @Qualifier("generalAccessor") GeneralAccessor accessor,
            DictionaryService dictionaryService,
            OrganizationService organizationService ,
            MajorIncidentRuleRepository majorIncidentRuleRepository
    ) {
        this.logService = logService;
        this.servletService = servletService ;
        this.accessor = accessor ;
        this.dictionaryService = dictionaryService;
        this.organizationService = organizationService;
        this.majorIncidentRuleRepository = majorIncidentRuleRepository;
        dics = new ArrayList<>(Arrays.asList("XB", "XZQX", "BJFS", "LAFS", "AJLX", "AJLXZX", "AJDJ", "AJZT", "AJXZ", "CZDX", "ZDDWLX", "JZJG", "YWQK", "ZHCS", "CLLX", "JQBQ", "JQDX"));

    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public MajorIncidentRuleBean saveMajorIncidentRule(MajorIncidentRuleInputInfo inputInfo) {
        if (null == inputInfo) {
            logService.infoLog(logger, "service", "saveMajorIncidentRule", "locationBO is null.");
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveMajorIncidentRule", "service is started...");
            Long logStart = System.currentTimeMillis();
            Long time = servletService.getSystemTime();
            logService.infoLog(logger, "repository", "saveMajorIncidentRule", "repository is started...");
            Long start = System.currentTimeMillis();
            MajorIncidentRuleEntity majorIncidentRuleEntity = new  MajorIncidentRuleEntity();
            BeanUtils.copyProperties(inputInfo,majorIncidentRuleEntity);
            MajorIncidentRuleEntity majorIncidentRuleEntityNew = accessor.save(majorIncidentRuleEntity);
            MajorIncidentRuleBean majorIncidentRuleBean = new MajorIncidentRuleBean();
            BeanUtils.copyProperties(majorIncidentRuleEntityNew,majorIncidentRuleBean);
            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "saveMajorIncidentRule", String.format("repository is finished,execute time is :%sms", end - start));


            return majorIncidentRuleBean;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveLocation", "save location fail.", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_LOCATION_FAIL);
        }
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public MajorIncidentRuleBean updateMajorIncidentRule(MajorIncidentRuleInputInfo inputInfo) {
        if (null == inputInfo) {
            logService.infoLog(logger, "service", "updateMajorIncidentRule", "locationBO is null.");
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "updateMajorIncidentRule", "service is started...");
            Long logStart = System.currentTimeMillis();
            Long time = servletService.getSystemTime();
            logService.infoLog(logger, "repository", "updateMajorIncidentRule", "repository is started...");
            Long start = System.currentTimeMillis();
            if (StringUtils.isBlank(inputInfo.getId())) {
                logService.infoLog(logger, "service", "updateMajorIncidentRule", "locationBO is null.");
                throw new Exception("修改时id不能为空");
            }
            MajorIncidentRuleEntity majorIncidentRuleEntity = accessor.getById(inputInfo.getId(),MajorIncidentRuleEntity.class);
            if (null == majorIncidentRuleEntity) {
                logService.infoLog(logger, "service", "updateMajorIncidentRule", "locationBO is null.");
                throw new Exception("规则不存在");
            }
            //majorIncidentRuleEntity.setUpdatedTime(time);
            majorIncidentRuleEntity.setDescribe(inputInfo.getDescribe());
            majorIncidentRuleEntity.setIncidentType(inputInfo.getIncidentType());
            majorIncidentRuleEntity.setJudgmentValue(inputInfo.getJudgmentValue());
            majorIncidentRuleEntity.setPriority(inputInfo.getPriority());
            majorIncidentRuleEntity.setRuleType(inputInfo.getRuleType());
            MajorIncidentRuleEntity majorIncidentRuleEntityNew = accessor.save(majorIncidentRuleEntity);
            MajorIncidentRuleBean majorIncidentRuleBean = new MajorIncidentRuleBean();
            BeanUtils.copyProperties(majorIncidentRuleEntityNew,majorIncidentRuleBean);
            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "updateMajorIncidentRule", String.format("repository is finished,execute time is :%sms", end - start));


            return majorIncidentRuleBean;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "updateMajorIncidentRule", "save location fail.", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_LOCATION_FAIL);
        }
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean deleteMajorIncidentRule(List<String> ids) {
        majorIncidentRuleRepository.deleteMajorIncidentRule(ids);
        return true;
    }

    @Override
    public PaginationBean<MajorIncidentRuleBean> findMajorIncidentRule(MajorIncidentRuleQueryInputInfo inputInfo) {
        if ( inputInfo == null   ) {
            logService.infoLog(logger, "service", "findMajorIncidentRule", "UnitEmergencyQueryInputinfo is Blank.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findMajorIncidentRule", "service is started...");
            Long logStart = System.currentTimeMillis();

            PaginationBean<MajorIncidentRuleBean> res = new PaginationBean() ;
            logService.infoLog(logger, "repository", "findMajorIncidentRule", "repository is started...");
            Long start = System.currentTimeMillis();

            //根据条件查询重大案件判定规则
            List<MajorIncidentRuleEntity> majorIncidentRuleEntityList = majorIncidentRuleRepository.findMajorIncidentRule(
                    inputInfo.getDescribe(),  inputInfo.getIncidentType() ,
                    inputInfo.getPriority(),  inputInfo.getRuleType(),
                    inputInfo.getWhetherPage(), inputInfo.getPagination().getPage(),
                    inputInfo.getPagination().getSize());

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findMajorIncidentRule", String.format("repository is finished,execute time is :%sms", end - start));

            //获取名称-代码对应字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
            //机构数据
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();

            List<MajorIncidentRuleBean> beans = new ArrayList<>( ) ;

            if (majorIncidentRuleEntityList != null && majorIncidentRuleEntityList.size() > 0) {
                for ( MajorIncidentRuleEntity majorIncidentRuleEntity : majorIncidentRuleEntityList) {
                    MajorIncidentRuleBean bean = MajorIncidentRuleTransformUtil.transform( majorIncidentRuleEntity , dicsMap , organizationNameMap ) ;
                    beans.add(bean);
                }
                res.setList( beans );
            }

            logService.infoLog(logger, "repository", "findMajorIncidentRule", "repository is started...");
            Long countStart = System.currentTimeMillis();

            //获取分页参数total
            Integer total = majorIncidentRuleRepository.findMajorIncidentRuleTotal(
                    inputInfo.getDescribe(),  inputInfo.getIncidentType() ,
                    inputInfo.getPriority(),  inputInfo.getRuleType()) ;

            Long countEnd = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findMajorIncidentRule", String.format("repository is finished,execute time is :%sms", countEnd - countStart));

            //装配结果
            Pagination pagination = new Pagination();
            pagination.setPage(inputInfo.getPagination().getPage());
            pagination.setSize(inputInfo.getPagination().getSize());
            pagination.setTotal(total);

            res.setPagination(pagination);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findMajorIncidentRule", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return  res ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findMajorIncidentRule", String.format("save Miniature Organization fail "), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_DATA_FAIL);
        }
    }
}
