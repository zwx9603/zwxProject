package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.PlanWordEntity;
import com.dscomm.iecs.accept.dal.repository.PlanWordRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.fireinputbean.PlanWordQueryInputInfo;
import com.dscomm.iecs.accept.graphql.fireinputbean.PlanWordSaveInputInfo;
import com.dscomm.iecs.accept.graphql.firetypebean.PlanWordBean;
import com.dscomm.iecs.accept.service.PlanWordService;
import com.dscomm.iecs.accept.utils.transform.FireTransformUtil;
import com.dscomm.iecs.base.graphql.typebean.PaginationBean;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.graphql.inputbean.AttachmentSaveInputInfo;
import com.dscomm.iecs.basedata.graphql.typebean.AttachmentBean;
import com.dscomm.iecs.basedata.service.AttachmentService;
import com.dscomm.iecs.ext.RelationObjectBakEnum;
import org.mx.StringUtils;
import org.mx.dal.Pagination;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component("planWordServiceImpl")
public class PlanWordServiceImpl implements PlanWordService {
    private static final Logger logger = LoggerFactory.getLogger(AcceptanceServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private PlanWordRepository planWordRepository;
    private AttachmentService  attachmentService ;


    /**
     * 默认的构造函数
     */
    @Autowired
    public PlanWordServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor,
                               PlanWordRepository planWordRepository,AttachmentService  attachmentService
    ) {
        this.logService = logService;
        this.accessor = accessor;
        this.planWordRepository=planWordRepository;
        this.attachmentService=attachmentService;

    }

    @Transactional(readOnly = true)
    @Override
    public PaginationBean<PlanWordBean> findPlanWordCondition(PlanWordQueryInputInfo queryBean) {

        if (null==queryBean.getWhetherPage()){
            logService.infoLog(logger, "service", "findPlanWordCondition", "id is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        try {
            logService.infoLog(logger, "service", "savePlanWord", "service is started...");
            Long logStart = System.currentTimeMillis();

            PaginationBean<PlanWordBean> res= new PaginationBean();
            List<PlanWordBean> beans=new ArrayList<>();

            logService.infoLog(logger, "repository", "findPlanWordCondition", "repository is started...");
            Long startHandleBatch = System.currentTimeMillis();

            List<PlanWordEntity> planWordEntityList  = planWordRepository.findPlanWordCondition(queryBean.getWhetherPage(),queryBean.getPagination().getPage(),queryBean.getPagination().getSize());

            Long endHandleBatch = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findPlanWordCondition", String.format("repository is finished,execute time is :%sms", endHandleBatch - startHandleBatch));

            if (planWordEntityList != null && planWordEntityList.size() > 0) {
                List<String> relationIds = new ArrayList<>() ;
                for ( PlanWordEntity entity : planWordEntityList) {
                    relationIds.add( entity.getId() ) ;
                }

                //获得附件信息
                Map<String , List<AttachmentBean> > attachmentMap  =  attachmentService.findAttachmentByRelationIds( relationIds , RelationObjectBakEnum.RELATION_OBJECT_WBYA.getCode() ) ;

                for ( PlanWordEntity planWord : planWordEntityList ) {
                    PlanWordBean bean = FireTransformUtil.transform(planWord);
                    bean.setAttachmentBean( attachmentMap.get( planWord.getId() ));
                    beans.add( bean ) ;
                }

            }

            logService.infoLog(logger, "repository", "findIncidentConditionTotal", "repository is started...");
            Long countStart = System.currentTimeMillis();

            Integer total = planWordRepository.findPlanWordConditionTotal();

            Long countEnd = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findIncidentConditionTotal", String.format("repository is finished,execute time is :%sms", countEnd - countStart));

            Pagination pagination = new Pagination();
            pagination.setPage(queryBean.getPagination().getPage());
            pagination.setSize(queryBean.getPagination().getSize());
            pagination.setTotal(total);

            res.setList(beans);
            res.setPagination(pagination);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "savePlanWord", String.format("service is finished,execute time is :%sms", logEnd - logStart));


            return res ;
        }catch (Exception ex){
            logService.erorLog(logger, "service", "findPlanWordCondition", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_PLANWORD_FALL);
        }





    }

    @Transactional(  readOnly =  true )
    @Override
    public PlanWordBean findPlanWord(String id) {
        if(StringUtils.isBlank(id)){
            logService.infoLog(logger, "service", "findPlanWord", "id is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "savePlanWord", "service is started...");
            Long logStart = System.currentTimeMillis();

            PlanWordBean res = null ;

            logService.infoLog(logger, "repository", "find(findPlanWord)", "repository is started...");
            Long startHandleBatch = System.currentTimeMillis();

            PlanWordEntity planWordEntity = accessor.getById( id , PlanWordEntity.class );

            Long endHandleBatch = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "find(findPlanWord)", String.format("repository is finished,execute time is :%sms", endHandleBatch - startHandleBatch));

            if( planWordEntity  != null && planWordEntity.isValid() ){
                res= FireTransformUtil.transform(planWordEntity);

                List<AttachmentBean> attachmentBeans =  attachmentService.findAttachment( null ,id ) ;
                res.setAttachmentBean(attachmentBeans);
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findPlanWord", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;

        }catch (Exception ex){
            logService.erorLog(logger, "service", "findPlanWord", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_PLANWORD_FALL);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public PlanWordBean savePlanWord(PlanWordSaveInputInfo queryBean){
        if ( null == queryBean ) {
            logService.infoLog(logger, "service", "savePlanWord", "CombatReadinessInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "savePlanWord", "service is started...");
            Long logStart = System.currentTimeMillis();


            PlanWordBean res = null ;

            PlanWordEntity planWordEntity= FireTransformUtil.transform(queryBean);

            logService.infoLog(logger, "repository", "save(PlanWordEntity)", "repository is started...");
            Long startPlan = System.currentTimeMillis();

            accessor.save(planWordEntity);

            Long endPlan = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(PlanWordEntity)", String.format("repository is finished,execute time is :%sms", endPlan - startPlan));

            String  relationId = planWordEntity.getId() ;

            //保存文本预案附件信息
            List<AttachmentBean> attachmentBeans = new ArrayList<>() ;


            if(null != queryBean.getAttachmentSaveInputInfo()){
                List< AttachmentSaveInputInfo > attachmentSaveInputInfoList = queryBean.getAttachmentSaveInputInfo();
                for (AttachmentSaveInputInfo attachmentSaveInputInfo : attachmentSaveInputInfoList ){
                    attachmentSaveInputInfo.setRelationId( relationId );
                    attachmentSaveInputInfo.setRelationObject( RelationObjectBakEnum.RELATION_OBJECT_WBYA.getCode()  );
                }
                attachmentBeans = attachmentService.saveAttachmentList( attachmentSaveInputInfoList ) ;
            }

            res=FireTransformUtil.transform(planWordEntity);
            res.setAttachmentBean(attachmentBeans);


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "savePlanWord", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;

        }catch (Exception ex){
            logService.erorLog(logger, "service", "saveCombatReadiness", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_PLANWORD_FALL);
        }
    }



    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean removePlanWord(String id) {
        if(StringUtils.isBlank(id)){
            logService.infoLog(logger, "service", "removePlanWord", "id is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "savePlanWord", "service is started...");
            Long logStart = System.currentTimeMillis();

            Boolean res = true ;

            logService.infoLog(logger, "repository", "remove(PlanWordEntity)", "repository is started...");
            Long startHandleBatch = System.currentTimeMillis();

            PlanWordEntity remove =   accessor.getById(id ,  PlanWordEntity.class);
            if( remove != null ){
                remove.setValid( false );
                accessor.save( remove ) ;
            }

            Long endHandleBatch = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "find(findPlanWord)", String.format("repository is finished,execute time is :%sms", endHandleBatch - startHandleBatch));


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "savePlanWord", String.format("service is finished,execute time is :%sms", logEnd - logStart));


            return res;
        }catch (Exception ex){
            logService.erorLog(logger, "service", "removePlanWord", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.REMOVE_PLANWORD_FALL);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<PlanWordBean>  findPlanWordByKeyUnitId(String keyUnitId) {
        if(StringUtils.isBlank(keyUnitId)){
            logService.infoLog(logger, "service", "findPlanWordByKeyUnitId", "keyUnitId is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "savePlanWord", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<PlanWordBean>  planWordBeanList = new ArrayList<>() ;

            logService.infoLog(logger, "repository", "remove(PlanWordEntity)", "repository is started...");
            Long startHandleBatch = System.currentTimeMillis();

            List<PlanWordEntity> planWordEntityList = planWordRepository.findPlanWordByKeyUnitId(keyUnitId);

            Long endHandleBatch = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "find(findPlanWord)", String.format("repository is finished,execute time is :%sms", endHandleBatch - startHandleBatch));

            if (planWordEntityList != null && planWordEntityList.size() > 0) {

                List<String> relationIds = new ArrayList<>() ;
                for ( PlanWordEntity entity : planWordEntityList) {
                    relationIds.add( entity.getId() ) ;
                }

                //获得附件信息
                Map<String , List<AttachmentBean> > attachmentMap  =  attachmentService.findAttachmentByRelationIds( relationIds , RelationObjectBakEnum.RELATION_OBJECT_WBYA.getCode() ) ;

                for ( PlanWordEntity planWord : planWordEntityList ) {
                    PlanWordBean bean = FireTransformUtil.transform(planWord);
                    bean.setAttachmentBean( attachmentMap.get( planWord.getId() ));
                    planWordBeanList.add( bean ) ;
                }

            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "savePlanWord", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return planWordBeanList;


        }catch (Exception ex){
            logService.erorLog(logger, "service", "findPlanWord", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_PLANWORD_FALL);
        }
    }
}
