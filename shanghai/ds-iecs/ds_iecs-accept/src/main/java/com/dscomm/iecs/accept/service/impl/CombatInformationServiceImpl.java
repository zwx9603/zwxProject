package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.CombatInformationEntity;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.CombatInformationInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.CombatInformationBean;
import com.dscomm.iecs.accept.service.CombatInformationService;
import com.dscomm.iecs.accept.utils.transform.FireTransformUtil;
import com.dscomm.iecs.base.graphql.inputbean.PaginationInputInfo;
import com.dscomm.iecs.base.graphql.typebean.PaginationBean;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import org.mx.StringUtils;
import org.mx.dal.Pagination;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component("combatInformationServiceImpl")
public class CombatInformationServiceImpl implements CombatInformationService {

    private static final Logger logger = LoggerFactory.getLogger(CombatInformationServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private OrganizationService organizationService;

    @Autowired
    public CombatInformationServiceImpl(LogService logService, @Qualifier("generalAccessor")  GeneralAccessor accessor,OrganizationService organizationService) {
        this.logService = logService;
        this.accessor = accessor;
        this.organizationService = organizationService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CombatInformationBean saveCombatInformation(CombatInformationInputInfo inputInfo) {
        if (null == inputInfo || StringUtils.isBlank(inputInfo.getOrganizationId()) || StringUtils.isBlank(inputInfo.getFileURL()) || StringUtils.isBlank(inputInfo.getFileName())){
            logService.infoLog(logger, "service", "saveCombatInformation", "CombatInformationInputInfo    is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveCombatInformation", "service is started...");
            Long logStart = System.currentTimeMillis();

            //获取机构id和名字的map
            Map<String,String> organizationMap = organizationService.findOrganizationNameMap();

            decodeCombatInputInfo(inputInfo);//URLDecoder inputInfo转码

            CombatInformationEntity entity = FireTransformUtil.transform(inputInfo);
            CombatInformationBean res = new CombatInformationBean();

            if (!StringUtils.isBlank(inputInfo.getId())){
                CombatInformationEntity entityFind = accessor.getById(inputInfo.getId(),CombatInformationEntity.class);
                if (null != entityFind && !entityFind.isValid()){
                    return res;
                }
            }

            entity = accessor.save(entity);

            if (null == entity.getUploadTime()){
                entity.setUploadTime(entity.getCreatedTime());
                entity = accessor.save(entity);
            }
            
            res = FireTransformUtil.transform(entity,organizationMap);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveCombatInformation", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return res;
        }catch (Exception e){
            logService.erorLog(logger, "service", "saveCombatInformation", "execution error", e);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_COMBATINFORMATION_FAIL);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteCombatInformation(String id) {
        if (StringUtils.isBlank(id)){
            logService.infoLog(logger, "service", "deleteCombatInformation", "id is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "deleteCombatInformation", "service is started...");
            Long logStart = System.currentTimeMillis();

            CombatInformationEntity entity = accessor.getById(id,CombatInformationEntity.class);
            if ( null != entity && entity.isValid()){
                accessor.remove(entity);
                Long logEnd = System.currentTimeMillis();
                logService.infoLog(logger, "service", "deleteCombatInformation", String.format("service is finished,execute time is :%sms", logEnd - logStart));
                return true;
            }else {
                Long logEnd = System.currentTimeMillis();
                logService.infoLog(logger, "service", "deleteCombatInformation", String.format("service is finished,execute time is :%sms", logEnd - logStart));
                return false;
            }

        }catch (Exception e){
            logService.erorLog(logger, "service", "deleteCombatInformation", "execution error", e);
            throw new AcceptException(AcceptException.AccetpErrors.DELETE_COMBATINFORMATION_FAIL);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public PaginationBean<CombatInformationBean> listCombatInformation(String organizationId, Boolean whetherPage, PaginationInputInfo paginationInputInfo) {
        if (StringUtils.isBlank(organizationId)){
            logService.infoLog(logger, "service", "listCombatInformation", "id is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "listCombatInformation", "service is started...");
            Long logStart = System.currentTimeMillis();
            if(null == whetherPage){
                whetherPage = false;
            }

            //获取机构id和名字的map
            Map<String,String> organizationMap = organizationService.findOrganizationNameMap();
            PaginationBean<CombatInformationBean> combatInformationBeanPaginationBean = new PaginationBean<>();
            List<CombatInformationBean> combatInformationBeans = new ArrayList<>();
            List<CombatInformationEntity> combatInformationEntities = new ArrayList<>();
            GeneralAccessor.ConditionGroup combatInformationConditionGroup = GeneralAccessor.ConditionGroup.and(
                    GeneralAccessor.ConditionTuple.eq("valid",1),                     //有效性条件
                    GeneralAccessor.ConditionTuple.eq("organizationId",organizationId)      //所属机构条件
            );

            if (whetherPage){
                Pagination pagination = new Pagination();
                if (paginationInputInfo != null){
                    pagination.setPage(paginationInputInfo.getPage());
                    pagination.setSize(paginationInputInfo.getSize());
                }
                combatInformationEntities = accessor.find(pagination,combatInformationConditionGroup,null,CombatInformationEntity.class);
                Long count = accessor.count(combatInformationConditionGroup,CombatInformationEntity.class);
                Integer total = Integer.parseInt(count.toString());
                pagination.setTotal(total);
                combatInformationBeanPaginationBean.setPagination(pagination);
            }else {
                combatInformationEntities = accessor.find(combatInformationConditionGroup,CombatInformationEntity.class);
            }

            if (combatInformationEntities != null && combatInformationEntities.size()>0){
                for (CombatInformationEntity entity:combatInformationEntities){
                    combatInformationBeans.add(FireTransformUtil.transform(entity,organizationMap));
                }
            }

            combatInformationBeanPaginationBean.setList(combatInformationBeans);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "listCombatInformation", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return combatInformationBeanPaginationBean;

        }catch (Exception e){
            logService.erorLog(logger, "service", "listCombatInformation", "execution error", e);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_COMBATINFORMATION_FAIL);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public CombatInformationBean detailCombatInformation(String id) {
        if (StringUtils.isBlank(id)){
            logService.infoLog(logger, "service", "detailCombatInformation", "id is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "detailCombatInformation", "service is started...");
            Long logStart = System.currentTimeMillis();
            //获取机构id和名字的map
            Map<String,String> organizationMap = organizationService.findOrganizationNameMap();
            CombatInformationBean bean = new CombatInformationBean();

            CombatInformationEntity entity = accessor.getById(id,CombatInformationEntity.class);
            if ( null != entity && entity.isValid()){
                bean = FireTransformUtil.transform(entity,organizationMap);
            }
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "detailCombatInformation", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return bean;
        }catch (Exception e){
            logService.erorLog(logger, "service", "detailCombatInformation", "execution error", e);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_COMBATINFORMATION_FAIL);
        }
    }

    /**
     * 对IncidentSaveInputInfo中需要前端手动输入的属性进行解码
     * URLDecoder
     */
    private void decodeCombatInputInfo(CombatInformationInputInfo source) {
        if (source != null) {
            try {
                if (!StringUtils.isBlank(source.getFileURL())) {
                    source.setFileURL((URLDecoder.decode(source.getFileURL(), "utf-8")));
                }
                if (!StringUtils.isBlank(source.getFileName())) {
                    source.setFileName((URLDecoder.decode(source.getFileName(), "utf-8")));
                }
                if (!StringUtils.isBlank(source.getUnitAddress())) {
                    source.setUnitAddress(URLDecoder.decode(source.getUnitAddress(), "UTF-8"));
                }
                if (!StringUtils.isBlank(source.getUnitName())) {
                    source.setUnitName((URLDecoder.decode(source.getUnitName(), "utf-8")));
                }
            } catch (Exception e) {
                throw new AcceptException(AcceptException.AccetpErrors.DECODE_FAIL);
            }
        }
    }
}
