package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.LedOrganizationEntity;
import com.dscomm.iecs.accept.dal.po.LedVehicleEntity;
import com.dscomm.iecs.accept.dal.repository.LedRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.fireinputbean.LedQueryInputInfo;
import com.dscomm.iecs.accept.graphql.fireinputbean.LedSaveInputInfo;
import com.dscomm.iecs.accept.graphql.fireinputbean.ledInputInfo;
import com.dscomm.iecs.accept.service.LedService;
import com.dscomm.iecs.accept.service.bean.LedBean;
import com.dscomm.iecs.accept.utils.transform.FireTransformUtil;
import com.dscomm.iecs.base.graphql.typebean.PaginationBean;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.dal.repository.OrganizationRepository;
import com.dscomm.iecs.basedata.graphql.typebean.OrganizationBean;
import com.dscomm.iecs.basedata.graphql.typebean.SystemConfigurationBean;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.basedata.service.SystemConfigurationService;
import com.dscomm.iecs.keydata.service.ServletService;
import org.apache.logging.log4j.util.Strings;
import org.mx.dal.Pagination;
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
 * led大屏维护  服务层
 */
@Component("ledServiceImpl")
public class LedServiceImpl implements LedService {
    private static final Logger logger = LoggerFactory.getLogger(LedServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private ServletService servletService ;
    private LedRepository ledRepository;
    private OrganizationService organizationService;
    private OrganizationRepository organizationRepository;
    private SystemConfigurationService systemConfigurationService ;
    private DictionaryService dictionaryService;
    private List<String> dics;
    private Environment env;


    @Autowired
    public LedServiceImpl(LogService logService,@Qualifier("generalAccessor") GeneralAccessor accessor, ServletService servletService,LedRepository ledRepository,OrganizationService organizationService,OrganizationRepository organizationRepository,SystemConfigurationService systemConfigurationService,DictionaryService dictionaryService,Environment env) {
        this.logService = logService;
        this.accessor = accessor;
        this.servletService=servletService;
        this.ledRepository=ledRepository;
        this.organizationService=organizationService;
        this.organizationRepository=organizationRepository;
        this.systemConfigurationService=systemConfigurationService;
        this.dictionaryService=dictionaryService;
        dics = new ArrayList<>(Arrays.asList("JGLB", "JGLX", "JGXZ", "XZQX"));
        this.env=env;
    }



    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveLed(LedSaveInputInfo info) {
        try{

            logService.infoLog(logger, "service", "saveLed", "service is started...");
            Long start = System.currentTimeMillis();

            if (Strings.isBlank(info.getType()) ) {
                logService.infoLog(logger, "service", "saveLed", "ledInputInfo is null.");
                throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
            }

            Long currentTime = servletService.getSystemTime() ;

            List<ledInputInfo> inputInfos=info.getInputInfos();

            String type=info.getType();

            if (type.equals("organization")) {
                for (ledInputInfo infos : inputInfos
                ) {
                    LedOrganizationEntity entity = FireTransformUtil.transform(infos, currentTime);

                    if (entity != null) {
                        accessor.save(entity);
                    }
                }
            }else {
                for (ledInputInfo infos: inputInfos
                ) {
                    LedVehicleEntity ledVehicleEntity = FireTransformUtil.transforms(infos,currentTime);

                    accessor.save(ledVehicleEntity);

                }
            }

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "serivce", "saveLed", String.format("repository is finished,execute time is :%sms", end - start));

            return true;
        }catch (Exception e) {
            logService.erorLog(logger, "service", "saveLed", "execution error", e);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_LED_FAIL);
        }
    }


    @Transactional( readOnly = true )
    @Override
    public PaginationBean<LedBean> findLedList(LedQueryInputInfo queryBean) {
        if ( Strings.isBlank(queryBean.getType())){
            logService.infoLog(logger, "service", "findLedList", "queryBean is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            PaginationBean<LedBean> res= new PaginationBean<>();

            List<LedBean> beans=new ArrayList<>();

            logService.infoLog(logger, "service", "findLedList", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<Object[]> objects=null;

            logService.infoLog(logger, "repository", "findLedList", "repository is started...");
            Long startincidentCircleSave = System.currentTimeMillis();

            Map<String, OrganizationBean> organizationBeanMap=organizationService.findOrganizationAll();

            Map<String,String> organization_name=organizationService.findOrganizationNameMap();

            SystemConfigurationBean systemConfigurationBean = systemConfigurationService.getSystemConfigByConfigType("XFJG_ID") ;

            String orgid=null;

            if (Strings.isBlank(queryBean.getOrgId())){
                orgid=systemConfigurationBean.getConfigValue();
            }else{
                orgid=queryBean.getOrgId();
            }

            String organizationCategoryCode=organizationBeanMap.get(orgid).getOrganizationCode();

            int total=0;

            switch (queryBean.getType()){
                 case "vehicle":
                    objects=ledRepository.findLedVehicleList(organizationCategoryCode,queryBean.getIsDisPlay(),queryBean.getName(),queryBean.getOldName(), queryBean.getWhetherPage(), queryBean.getPagination().getPage(), queryBean.getPagination().getSize());
                     total=ledRepository.findLedVehicleCount(organizationCategoryCode,queryBean.getIsDisPlay(),queryBean.getName(),queryBean.getOldName());
                break;
                default:
                    objects=ledRepository.findLedOrganizationList(organizationCategoryCode,queryBean.getIsDisPlay(),queryBean.getName(),queryBean.getOldName(), queryBean.getWhetherPage(), queryBean.getPagination().getPage(), queryBean.getPagination().getSize());
                    total=ledRepository.findLedOrganizationCount(organizationCategoryCode,queryBean.getIsDisPlay(),queryBean.getName(),queryBean.getOldName());
                    break;
            }

            Long endincidentCircleSave = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findLedList", String.format("repository is finished,execute time is :%sms", endincidentCircleSave - startincidentCircleSave));

            if (objects!=null && objects.size()>0){
                for (Object[] obj: objects) {
                    LedBean bean=FireTransformUtil.transforms(obj,queryBean.getType(),organization_name);
                    beans.add(bean);
                }
            }

            Pagination pagination = new Pagination();
            pagination.setPage(queryBean.getPagination().getPage());
            pagination.setSize(queryBean.getPagination().getSize());
            pagination.setTotal(total);

            res.setPagination(pagination);
            res.setList(beans);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findLedList", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        }catch (Exception ex){
            logService.erorLog(logger, "service", "findLedList", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.QUERY_LED_FAIL);
        }
    }

}
