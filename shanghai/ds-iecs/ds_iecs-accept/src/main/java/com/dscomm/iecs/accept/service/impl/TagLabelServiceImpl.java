package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.TagLabelEntity;
import com.dscomm.iecs.accept.dal.repository.TagLabelRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.TagLabelQueryInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.TagLabelSaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.TagLabelBean;
import com.dscomm.iecs.accept.service.TagLabelService;
import com.dscomm.iecs.accept.utils.transform.IncidentTransformUtil;
import com.dscomm.iecs.agent.service.UserService;
import com.dscomm.iecs.base.graphql.typebean.PaginationBean;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.keydata.service.ServletService;
import org.apache.logging.log4j.util.Strings;
import org.mx.dal.Pagination;
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

/**
 * 描述：标签 服务类实现
 */
@Component("tagLabelServiceImpl")
public class TagLabelServiceImpl implements TagLabelService {
    private static final Logger logger = LoggerFactory.getLogger(TagLabelServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private TagLabelRepository tagLabelRepository;
    private ServletService servletService ;
    private DictionaryService dictionaryService ;
    private UserService userService ;

    private List<String> dics;
    /**
     * 默认的构造函数
     */
    @Autowired
    public TagLabelServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor,
                               TagLabelRepository tagLabelRepository , ServletService servletService ,
                               DictionaryService dictionaryService ,  UserService userService
    ) {
        this.accessor = accessor;
        this.logService = logService;
        this.tagLabelRepository = tagLabelRepository;
        this.servletService = servletService ;
        this.dictionaryService = dictionaryService ;
        this.userService = userService ;

        dics = new ArrayList<>(Arrays.asList("BQLX", "BQMX"    ));
    }


    /**
     * {@inheritDoc}
     *
     * @see #findTagLabelCondition(TagLabelQueryInputInfo)
     */
    @Transactional(readOnly = true)
    @Override
    public  PaginationBean<TagLabelBean> findTagLabelCondition(TagLabelQueryInputInfo queryBean ) {
        if( Strings.isBlank( queryBean.getBusinessTable() )){
            logService.infoLog(logger, "service", "findTagLabelCondition", "businessTable is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findBlacklistPhoneCondition", "service is started...");
            Long logStart = System.currentTimeMillis();

            PaginationBean  res  = new PaginationBean() ;
            List<TagLabelBean> beans = new ArrayList<>();
            //字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;
            List<String> tagType = new ArrayList<>() ;
            if( Strings.isNotBlank( queryBean.getKeyword() ) ){
                Map<String, String> bqmx = dicsMap.get("BQMX") ;
                for( String key :  bqmx.keySet() ){
                    if( bqmx.get( key).contains( queryBean.getKeyword() ) ){
                        tagType.add( key ) ;
                    }
                }
            }

            logService.infoLog(logger, "repository", "findTagLabelCondition", "repository is started...");
            Long start = System.currentTimeMillis();

            List<TagLabelEntity> tagLabelEntityList = tagLabelRepository.findTagLabelCondition( queryBean.getKeyword(),  queryBean.getPhoneKeyword() , queryBean.getPersonNameKeyword() ,
                    tagType ,  queryBean.getBusinessTable() ,
                    queryBean.getStartTime() , queryBean.getEndTime() , queryBean.getWhetherPage() ,
                    queryBean.getPagination().getPage() , queryBean.getPagination().getSize() );


            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findTagLabelCondition", String.format("repository is finished,execute time is :%sms", end - start));

            if (tagLabelEntityList != null && tagLabelEntityList.size() > 0) {

                for(TagLabelEntity tagLabelEntity : tagLabelEntityList){
                    TagLabelBean tagLabelBean  = IncidentTransformUtil.transform(tagLabelEntity , dicsMap );
                    beans.add( tagLabelBean ) ;

                }
            }

            logService.infoLog(logger, "repository", "findTagLabelConditionTotal", "repository is started...");
            Long countStart = System.currentTimeMillis();

            Integer total =  tagLabelRepository.findTagLabelConditionTotal(   queryBean.getKeyword(),  queryBean.getPhoneKeyword() , queryBean.getPersonNameKeyword() ,
                     tagType , queryBean.getBusinessTable() , queryBean.getStartTime() , queryBean.getEndTime()   ) ;

            Long countEnd = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findTagLabelConditionTotal", String.format("repository is finished,execute time is :%sms", countEnd - countStart));

            Pagination pagination = new Pagination();
            pagination.setPage( queryBean.getPagination().getPage());
            pagination.setSize( queryBean.getPagination().getSize());
            pagination.setTotal(total);

            res.setPagination(pagination);
            res.setList(beans);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findTagLabelCondition", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findTagLabelCondition", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_TAG_LABEL_FAIL);
        }
    }



    /**
     * {@inheritDoc}
     *
     * @see TagLabelService#findTagLabelsBusinessDataId(String , String )
     */
    @Transactional(readOnly = true)
    @Override
    public  TagLabelBean  findTagLabelsBusinessDataId(String businessDataId , String businessTable ) {
        if (Strings.isBlank(businessDataId) || Strings.isBlank( businessTable ) ) {
            logService.infoLog(logger, "service", "findTagLabelsBusinessDataId", "businessDataId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findTagLabelsBusinessDataId", "service is started...");
            Long logStart = System.currentTimeMillis();

            //字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;

            TagLabelBean  res = null ;

            logService.infoLog(logger, "repository", "findTagLabelsBusinessDataId(businessDataId)", "repository is started...");
            Long start = System.currentTimeMillis();

            List<TagLabelEntity> tagLabelEntities = tagLabelRepository.findTagLabelsBusinessDataId(businessDataId , businessTable );

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findTagLabelsBusinessDataId(businessDataId)", String.format("repository is finished,execute time is :%sms", end - start));

            if (tagLabelEntities != null && tagLabelEntities.size() > 0) {
                TagLabelEntity tagLabelEntity = tagLabelEntities.get(0) ;
                res = IncidentTransformUtil.transform(tagLabelEntity , dicsMap );
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findTagLabelsBusinessDataId", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findTagLabelsBusinessDataId", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_TAG_LABEL_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see TagLabelService#saveTagLabel(TagLabelSaveInputInfo)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public TagLabelBean saveTagLabel(TagLabelSaveInputInfo inputInfo) {
        if (inputInfo == null) {
            logService.infoLog(logger, "service", "saveTagLabel", "TagLabelSaveInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveTagLabel", "service is started...");
            Long logStart = System.currentTimeMillis();

            //字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;

            //判断这个号码是否打过标签
            List<TagLabelEntity> tagLabelEntities = tagLabelRepository.findTagLabelsBusinessDataId( inputInfo.getBusinessDataId() , inputInfo.getBusinessTable()  );
            //如果打过标签 更新标签
            TagLabelEntity tagLabelEntity = new  TagLabelEntity() ;
            if( tagLabelEntities != null && tagLabelEntities.size() > 0  ){
                 tagLabelEntity = tagLabelEntities.get(0) ;
            }

            IncidentTransformUtil.transform( inputInfo , tagLabelEntity , servletService.getSystemTime()   );
            UserInfo userInfo = userService.getUserInfo() ;
            tagLabelEntity.setPersonId(  userInfo.getAccount() );
            tagLabelEntity.setPersonName( userInfo.getPersonName() );

            if (null != tagLabelEntity) {
                logService.infoLog(logger, "repository", "save(dbTagLabelEntity)", "repository is started...");
                Long start = System.currentTimeMillis();

                accessor.save(tagLabelEntity);

                Long end = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbTagLabelEntity)", String.format("repository is finished,execute time is :%sms", end - start));

            }
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveTagLabel", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            TagLabelBean res = IncidentTransformUtil.transform(tagLabelEntity , dicsMap );

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveTagLabel", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_TAG_LABEL_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see TagLabelService#removeTagLabel(String)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean removeTagLabel(String tagLabelId) {
        if (Strings.isBlank(tagLabelId)) {
            logService.infoLog(logger, "service", "removeTagLabel", "tagLabelId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "removeTagLabel", "service is started...");
            Long logStart = System.currentTimeMillis();

            TagLabelEntity entity = accessor.getById(tagLabelId, TagLabelEntity.class);

            if (entity != null) {

                logService.infoLog(logger, "repository", "remove(tagLabelId)", "repository is started...");
                Long start = System.currentTimeMillis();

                entity.setRevokeTime(System.currentTimeMillis());
                entity.setValid( false );

                accessor.save(entity);

                Long end = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "remove(tagLabelId)", String.format("repository is finished,execute time is :%sms", end - start));

            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "removeTagLabel", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return true;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "removeTagLabel", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.REMOVE_TAG_LABEL_FAIL);
        }
    }
}
