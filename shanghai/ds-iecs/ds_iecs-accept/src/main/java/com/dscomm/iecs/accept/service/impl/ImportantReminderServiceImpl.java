package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.ImportantReminderEntity;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.ImportantReminderInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.ImportantReminderBean;
import com.dscomm.iecs.accept.service.ImportantReminderService;
import com.dscomm.iecs.accept.utils.transform.ImportantReminderTransformUtil;
import com.dscomm.iecs.base.websocket.WebsocketCodeEnum;
import com.dscomm.iecs.base.graphql.typebean.PaginationBean;
import com.dscomm.iecs.base.service.NotifyActionService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import org.apache.logging.log4j.util.Strings;
import org.mx.StringUtils;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 要事提醒服务类
 * */
@Component("importantReminderServiceImpl")
public class ImportantReminderServiceImpl implements ImportantReminderService {
    private static final Logger logger = LoggerFactory.getLogger(HandleServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private Environment env;
    private NotifyActionService notifyActionService ;
    private DictionaryService dictionaryService;
    private OrganizationService organizationService;

    @Autowired
    public ImportantReminderServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor, Environment env,
                             OrganizationService organizationService, DictionaryService dictionaryService,
                                        NotifyActionService notifyActionService
    ) {

        this.accessor = accessor;
        this.logService = logService;
        this.env = env;
        this.organizationService = organizationService;
        this.dictionaryService = dictionaryService;
        this.notifyActionService = notifyActionService;
    }


    /**
     * 保存要事提醒
     * @param importantReminderInputInfo 要事详情输入信息
     * @return boolean
     * */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveImportantReminder(ImportantReminderInputInfo importantReminderInputInfo) {
        if (importantReminderInputInfo == null){
            logService.infoLog(logger, "service", "saveImportantReminder", " inputInfo is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveImportantReminder", "repository is started...");
            Long logStart = System.currentTimeMillis();
            ImportantReminderEntity importantReminderEntity = ImportantReminderTransformUtil.transformEntity(importantReminderInputInfo);
            ImportantReminderEntity saveEntity = accessor.save(importantReminderEntity);//保存要事提醒，同时获取保存后的entity用来发布websocket

            if (Strings.isNotBlank(saveEntity.getPersonUnitId())){//取出填写人单位，发布websocket信息

                ImportantReminderBean importantReminderBean = ImportantReminderTransformUtil.transformBean(saveEntity);
                List<String> orgsCode = organizationService.findChildOrganizationId(saveEntity.getPersonUnitId());//获取填写人单位及子单位的code
                Set<String> orgs = new HashSet<>();

                orgs.addAll(orgsCode);

                notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.IMPROTANT_REMINDER.getCode(), importantReminderBean, orgs);

            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "savemportantReminder", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return true;

        }catch (Exception e){
            logService.erorLog(logger, "service", "savemportantReminder", "execution error", e);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_HANDLE_FAIL);
        }

    }

    /**
     * 要事提醒详情
     * @param id 要事提醒id
     * @return 要事提醒信息
     * */
    @Override
    @Transactional(readOnly = true)
    public ImportantReminderBean importantReminderDetail(String id) {
        if (Strings.isBlank(id)){
            logService.infoLog(logger, "service", "importantReminderDetail", "id is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "importantReminderDetail", "repository is started...");
            Long logStart = System.currentTimeMillis();
            ImportantReminderBean importantReminderBean = new ImportantReminderBean();
            ImportantReminderEntity importantReminderEntity = accessor.getById(id,ImportantReminderEntity.class);
            if (importantReminderEntity != null){
                importantReminderBean = ImportantReminderTransformUtil.transformBean(importantReminderEntity);
            }
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "importantReminderDetail", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return importantReminderBean;
        }catch (Exception e){
            logService.erorLog(logger, "service", "importantReminderDetail", "execution error", e);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_IMPORTANTREMINDER_FATL);
        }
    }

    /**
     * 删除要事提醒
     * @param id 要事提醒id
     * @return Boolean
     * */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteImportantReminder(String id) {
        if (Strings.isBlank(id)){
            logService.infoLog(logger, "service", "deleteImportantReminder", "id is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "savemportantReminder", "repository is started...");
            Long logStart = System.currentTimeMillis();
            ImportantReminderEntity importantReminderEntity = accessor.getById(id,ImportantReminderEntity.class);
            if (importantReminderEntity != null){
                accessor.remove(importantReminderEntity);

            }
            return  true;

        }catch (Exception e){
            logService.erorLog(logger, "service", "savemportantReminder", "execution error", e);
            throw new AcceptException(AcceptException.AccetpErrors.DELETE_IMPORTANTREMINDER_FAIL);
        }
    }

    /**
     *要事提醒列表
     * @param importantReminderInputInfo 要事查询输入信息
     * @param pageSize 分页信息
     * @param pageNum
     * @return ImportantReminderBean列表
     * */
    @Override
    public PaginationBean<ImportantReminderBean> listImportantReminder(ImportantReminderInputInfo importantReminderInputInfo, Integer pageSize, Integer pageNum) {
        try {
            logService.infoLog(logger, "service", "listImportantReminder", "repository is started...");
            Long logStart = System.currentTimeMillis();
            Pagination pagination = new Pagination();
            if (pageSize!= null && pageNum!= null && pageSize>0 && pageNum>0){
                pagination.setSize(pageSize);
                pagination.setPage(pageNum);
            }else {
                pagination = null;
            }
            List<GeneralAccessor.ConditionGroup> importantReminderConditionList = new ArrayList<>();
            importantReminderConditionList.add(GeneralAccessor.ConditionTuple.eq("valid",1));
            if (importantReminderInputInfo!= null){
                if (!StringUtils.isBlank(importantReminderInputInfo.getContext())){//要事提醒内容
                    importantReminderConditionList.add(GeneralAccessor.ConditionTuple.contain("context",importantReminderInputInfo.getContext()));
                }
                if (!StringUtils.isBlank(importantReminderInputInfo.getTitle())){//要事提醒标题
                    importantReminderConditionList.add(GeneralAccessor.ConditionTuple.contain("title",importantReminderInputInfo.getTitle()));
                }
                if (!StringUtils.isBlank(importantReminderInputInfo.getPersonId())){//要事提醒填写人
                    importantReminderConditionList.add(GeneralAccessor.ConditionTuple.eq("personId",importantReminderInputInfo.getPersonId()));
                }
                if (!StringUtils.isBlank(importantReminderInputInfo.getPersonUnitId())){//要事填写单位
                    importantReminderConditionList.add(GeneralAccessor.ConditionTuple.eq("personUnitId",importantReminderInputInfo.getPersonUnitId()));
                }
                if (importantReminderInputInfo.getBeginTime() != null){//开始时间
                    importantReminderConditionList.add(GeneralAccessor.ConditionTuple.gt("beginTime",importantReminderInputInfo.getBeginTime()));
                }
                if (importantReminderInputInfo.getEndTime() != null){//结束时间
                    importantReminderConditionList.add(GeneralAccessor.ConditionTuple.lt("beginTime",importantReminderInputInfo.getEndTime()));
                }
            }
            GeneralAccessor.RecordOrderGroup importantRemiderOrder = GeneralAccessor.RecordOrderGroup.group(
                    GeneralAccessor.RecordOrder.desc("releaseTime")
            );
            GeneralAccessor.ConditionGroup importantCondition = GeneralAccessor.ConditionGroup.and(importantReminderConditionList);
            List<ImportantReminderEntity> importantReminderEntityList = accessor.find(pagination,importantCondition,importantRemiderOrder,ImportantReminderEntity.class);
            Long count = accessor.count(importantCondition,ImportantReminderEntity.class);
            if (pagination != null){
                pagination.setPage(Integer.parseInt(count.toString()));
            }
            List<ImportantReminderBean> beanList = new ArrayList<>( );
            if( importantReminderEntityList != null && importantReminderEntityList.size() > 0 ){
                for( ImportantReminderEntity importantReminderEntity : importantReminderEntityList){
                    ImportantReminderBean importantReminderBean = ImportantReminderTransformUtil.transformBean( importantReminderEntity ) ;
                    beanList.add( importantReminderBean ) ;
                }
            }

            PaginationBean<ImportantReminderBean> importantReminderBeanPaginationBean = new PaginationBean<>();
            importantReminderBeanPaginationBean.setPagination(pagination);
            importantReminderBeanPaginationBean.setList(beanList);
            Long logEnd = System.currentTimeMillis();

            logService.infoLog(logger, "service", "listImportantReminder", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return importantReminderBeanPaginationBean;
        }catch (Exception e){
            logService.erorLog(logger, "service", "listImportantReminder", "execution error", e);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_IMPORTANTREMINDER_FATL);
        }

    }




}
