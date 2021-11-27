package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.NewsCircularEntity;
import com.dscomm.iecs.accept.dal.po.NewsCircularReceiverEntity;
import com.dscomm.iecs.accept.dal.repository.NewsCircularRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.NewsCircularQueryInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.NewsCircularReceiverSaveInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.NewsCircularSaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.NewsCircularBean;
import com.dscomm.iecs.accept.graphql.typebean.NewsCircularReceiverBean;
import com.dscomm.iecs.accept.service.NewsCircularService;
import com.dscomm.iecs.accept.utils.transform.SystemTransformUtil;
import com.dscomm.iecs.agent.service.UserService;
import com.dscomm.iecs.base.enums.EnableEnum;
import com.dscomm.iecs.base.websocket.WebsocketCodeEnum;
import com.dscomm.iecs.base.graphql.typebean.PaginationBean;
import com.dscomm.iecs.base.service.NotifyActionService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.keydata.service.ServletService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.mx.DateUtils;
import org.mx.dal.Pagination;
import org.mx.dal.service.GeneralAccessor;
import org.mx.error.UserInterfaceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLDecoder;
import java.util.*;


/**
 * Description:
 *
 */
@Component("newCircularServiceImpl")
public class NewCircularServiceImpl implements NewsCircularService {
    private static final Logger logger = LoggerFactory.getLogger(NewCircularServiceImpl.class);
    private LogService logService;
    private UserService userService;
    private GeneralAccessor accessor;
    private ServletService servletService ;
    private OrganizationService organizationService ;
    private NotifyActionService notifyActionService ;
    private NewsCircularRepository newsCircularRepository ;


    @Autowired
    public NewCircularServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor,
                                  UserService userService,  ServletService servletService  ,  OrganizationService organizationService ,
                                  NotifyActionService notifyActionService , NewsCircularRepository newsCircularRepository   ) {

        this.logService = logService;
        this.userService = userService;
        this.accessor = accessor;
        this.servletService = servletService;
        this.organizationService = organizationService ;
        this.notifyActionService = notifyActionService ;
        this.newsCircularRepository = newsCircularRepository ;
    }


    /**
     * {@inheritDoc}
     *
     * @see #saveNewsCircular(NewsCircularSaveInputInfo)
     */
    @Transactional( rollbackFor =  Exception.class )
    @Override
    public NewsCircularBean saveNewsCircular (  NewsCircularSaveInputInfo queryBean  )  {
        if ( queryBean == null   ) {
            logService.infoLog(logger, "service", "saveInstruction", "incidentId is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveInstruction", "service is started...");
            Long logStart = System.currentTimeMillis();

            NewsCircularBean res = null ;


            UserInfo userInfo = userService.getUserInfo() ;
            Long currentTime = servletService.getSystemTime()  ;

            decodeNewsCircularSaveInputInfo(queryBean);//URLDecoder inputInfo转码

            //转换通知消息
            NewsCircularEntity newsCircularEntity  = SystemTransformUtil.transform( queryBean )  ;

            List<String> receivingObjectIds = new ArrayList<>( );

            if( newsCircularEntity != null   ){
                newsCircularEntity.setCircularTime( currentTime  );
                newsCircularEntity.setCircularPersonId ( userInfo.getAccount() );
                newsCircularEntity.setCircularPersonName( userInfo.getPersonName() );
                newsCircularEntity.setCircularOrganizationId( userInfo.getOrgId() );

                //通知消息
                logService.infoLog(logger, "repository", "save( dbNewsCircularEntity )", "repository is started...");
                Long startInstruction = System.currentTimeMillis();

                accessor.save( newsCircularEntity );

                Long endInstruction = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save( dbNewsCircularEntity )", String.format("repository is finished,execute time is :%sms", endInstruction - startInstruction ));

                String newsCircularId = newsCircularEntity.getId() ;

                Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();

                res = SystemTransformUtil.transform( newsCircularEntity , organizationNameMap  ) ;

                if( Strings.isBlank( newsCircularId ) ){
                    if (logger.isDebugEnabled()) {
                        logger.debug(" newsCircularId id  is null    ");
                    }
                    throw new AcceptException(AcceptException.AccetpErrors.SAVE_DATA_FAIL );
                }
                //保存接收对象  生成消息结构对象
                List<NewsCircularReceiverSaveInputInfo> newsCircularReceiverSaveInputInfoList  = queryBean.getNewsCircularReceiverSaveInputInfoList() ;
                List< NewsCircularReceiverEntity > newsCircularReceiverEntityList  = new ArrayList<>() ;
                List<NewsCircularReceiverBean> newsCircularReceiverBeans = new ArrayList<>() ;
                if( newsCircularReceiverSaveInputInfoList != null && newsCircularReceiverSaveInputInfoList.size() > 0 ){
                    for ( NewsCircularReceiverSaveInputInfo  newsCircularReceiverSaveInputInfo   : newsCircularReceiverSaveInputInfoList ) {
                        receivingObjectIds.add( newsCircularReceiverSaveInputInfo.getReceiverObjectId() ) ;
                        NewsCircularReceiverEntity newsCircularReceiverEntity  =  SystemTransformUtil.transform ( newsCircularReceiverSaveInputInfo , newsCircularId   ) ;
                        //判断消息接收者是否为空，不为空则执行保存操作
                        if ( newsCircularReceiverEntity != null) {
                            newsCircularReceiverEntity.setCircularTime( currentTime );
                        }
                        newsCircularReceiverEntityList.add( newsCircularReceiverEntity ) ;
                    }
                    // 保存 消息接收者
                    logService.infoLog(logger, "repository", "save( dbNewsCircularReceiverEntityList )", "repository is started...");
                    Long startInstructionRecord = System.currentTimeMillis();

                    accessor.save( newsCircularReceiverEntityList ) ;

                    Long endInstructionRecord = System.currentTimeMillis();
                    logService.infoLog(logger, "repository", "save( dbNewsCircularReceiverEntityList )", String.format("repository is finished,execute time is :%sms", endInstructionRecord - startInstructionRecord ));

                    for (  NewsCircularReceiverEntity newsCircularReceiverEntity   : newsCircularReceiverEntityList ) {
                        NewsCircularReceiverBean newsCircularReceiverBean =  SystemTransformUtil.transform ( newsCircularReceiverEntity   ) ;
                        newsCircularReceiverBeans.add( newsCircularReceiverBean ) ;
                    }

                    res.setNewsCircularReceiverBeanList( newsCircularReceiverBeans );
                }
            }


            Set<String> orgSet = new HashSet<>() ;
            //通知接收单位
            orgSet.addAll( receivingObjectIds ) ;

            List<String> orgCodes =  organizationService.findOrganizationCodesByIds(new ArrayList<>(orgSet));
            orgSet.addAll(orgCodes);

            notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.SAVE_NEW_CIRCULAR.getCode(), res , orgSet );

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveInstruction", String.format("service is finished,execute time is :%sms", logEnd - logStart));


            return  res ;
        } catch (UserInterfaceException ex) {
            throw ex;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveInstruction", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DATA_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see #findNewsCircularCondition( NewsCircularQueryInputInfo )
     */
    @Transactional(  readOnly =  true )
    @Override
    public PaginationBean<NewsCircularBean> findNewsCircularCondition (NewsCircularQueryInputInfo queryBean   ){
        try {

            logService.infoLog(logger, "service", "findNewsCircularCondition", "service is started...");
            Long logStart = System.currentTimeMillis();

            decodeNewsCircularQueryInputInfo(queryBean);//URLDecoder query转码

            PaginationBean<NewsCircularBean> res = new PaginationBean() ;

            Map<String,NewsCircularBean > newsCircularMap = new HashMap<>() ;
            Map<String,List<NewsCircularReceiverBean> > newsCircularReceiverMap = new HashMap<>() ;

            //如果没有开始时间 结束时间 设置默认时间段 最近一周
            if( queryBean.getStartTime() == null && queryBean.getEndTime() == null ){
                int daynum = -7 ;
                Long currentTime = servletService.getSystemTime() ;
                Long endTime  = currentTime ;
                Long startTime  = DateUtils.add(new Date(currentTime), DateUtils.FieldType.DAY, daynum ).getTime();
                queryBean.setStartTime( startTime );
                queryBean.setEndTime( endTime );
            }


            List<NewsCircularBean>  beans = new ArrayList<>() ;

            logService.infoLog(logger, "repository", "findInstructionCondition", "repository is started...");
            Long startInstruction = System.currentTimeMillis();

            List<NewsCircularEntity> newsCircularEntityList =  newsCircularRepository.findNewsCircularCondition( queryBean.getStartTime() , queryBean.getEndTime() ,
                    queryBean.getType() , queryBean.getSource() ,  queryBean.getKeyword() , queryBean.getTitleKeyword() , queryBean.getContentKeyword() ,
                    queryBean.getWhetherPage() , queryBean.getPagination().getPage() , queryBean.getPagination().getSize()  ) ;

            Long endInstruction = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findInstructionCondition", String.format("repository is finished,execute time is :%sms", endInstruction - startInstruction ));



            if( newsCircularEntityList != null && newsCircularEntityList.size() > 0 ){
                Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();
                List<String> newsCircularIds  = new ArrayList<>() ;
                for ( NewsCircularEntity newsCircularEntity   :  newsCircularEntityList) {
                    //转换调派单
                    NewsCircularBean newsCircularBean  = SystemTransformUtil.transform( newsCircularEntity, organizationNameMap );
                    newsCircularIds.add( newsCircularBean.getId() ) ;
                    newsCircularMap.put( newsCircularBean.getId() , newsCircularBean ) ;
                }

                logService.infoLog(logger, "repository", "findNewsCircularReceiver", "repository is started...");
                Long startInstructionRecord = System.currentTimeMillis();

                List<NewsCircularReceiverEntity> newsCircularReceiverEntityList  = newsCircularRepository.findNewsCircularReceiver( newsCircularIds ) ;

                Long endInstructionRecord = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "findNewsCircularReceiver", String.format("repository is finished,execute time is :%sms", endInstructionRecord - startInstructionRecord ));


                for( NewsCircularReceiverEntity newsCircularReceiverEntity :  newsCircularReceiverEntityList ){
                    NewsCircularReceiverBean newsCircularReceiverBean = SystemTransformUtil.transform( newsCircularReceiverEntity   );
                    List<NewsCircularReceiverBean>  newsCircularReceiverBeanList = newsCircularReceiverMap.get( newsCircularReceiverBean.getNewsCircularId() ) ;
                    if( newsCircularReceiverBeanList == null ){
                        newsCircularReceiverBeanList = new ArrayList<>( ) ;
                        newsCircularReceiverBeanList.add( newsCircularReceiverBean ) ;
                    }else{
                        newsCircularReceiverBeanList.add( newsCircularReceiverBean ) ;
                    }
                    newsCircularReceiverMap.put(newsCircularReceiverBean.getNewsCircularId()  , newsCircularReceiverBeanList ) ;
                }

                for( String key :  newsCircularMap.keySet() ){
                    NewsCircularBean newsCircularBean = newsCircularMap.get( key ) ;
                    newsCircularBean.setNewsCircularReceiverBeanList( newsCircularReceiverMap.get( newsCircularBean.getId()  ) );
                    beans.add( newsCircularBean ) ;
                }

            }
            //通知信息
            beans.sort( new Comparator<NewsCircularBean>() {
                @Override
                public int compare(NewsCircularBean o1, NewsCircularBean o2) {
                    Long d1 = o1.getCircularTime();
                    Long d2 = o2.getCircularTime();
                    return  d2.compareTo(d1);
                }
            } );

            logService.infoLog(logger, "repository", "findNewsCircularConditionTotal", "repository is started...");
            Long countStart = System.currentTimeMillis();

            Integer total =  newsCircularRepository.findNewsCircularConditionTotal(  queryBean.getStartTime() , queryBean.getEndTime() ,
                    queryBean.getType() , queryBean.getSource() ,  queryBean.getKeyword() , queryBean.getTitleKeyword() , queryBean.getContentKeyword()   ) ;

            Long countEnd = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findNewsCircularConditionTotal", String.format("repository is finished,execute time is :%sms", countEnd - countStart));

            Pagination pagination = new Pagination();
            pagination.setPage( queryBean.getPagination().getPage());
            pagination.setSize( queryBean.getPagination().getSize());
            pagination.setTotal(total);

            res.setPagination(pagination);
            res.setList(beans);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findNewsCircularCondition", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res ;


        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findNewsCircularCondition", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_FAIL);
        }
    }




    /**
     * {@inheritDoc}
     *
     * @see #findNewsCircularReceiverCondition( NewsCircularQueryInputInfo )
     */
    @Transactional(  readOnly =  true )
    @Override
    public  PaginationBean<NewsCircularReceiverBean> findNewsCircularReceiverCondition ( NewsCircularQueryInputInfo queryBean   ) {
        try {

            logService.infoLog(logger, "service", "findNewsCircularReceiverCondition", "service is started...");
            Long logStart = System.currentTimeMillis();

            decodeNewsCircularQueryInputInfo(queryBean);//URLDecoder query转码

            PaginationBean<NewsCircularReceiverBean> res = new PaginationBean() ;

            //如果没有开始时间 结束时间 设置默认时间段 最近一周
            if( queryBean.getStartTime() == null && queryBean.getEndTime() == null ){
                int daynum = -7 ;
                Long currentTime = servletService.getSystemTime() ;
                Long endTime  = currentTime ;
                Long startTime  = DateUtils.add(new Date(currentTime), DateUtils.FieldType.DAY, daynum ).getTime();
                queryBean.setStartTime( startTime );
                queryBean.setEndTime( endTime );
            }
            if( queryBean.getNewsCircularStatus() == null || queryBean.getNewsCircularStatus().size() < 1 ){
                queryBean.setStartTime( null  );
                queryBean.setEndTime( null );
                List<Integer>   newsCircularStatus = new ArrayList<>() ;
                newsCircularStatus.add( EnableEnum.ENABLE_FALSE.getCode() ) ;
                queryBean.setNewsCircularStatus( newsCircularStatus );
            }


            List<NewsCircularReceiverBean>  beans = new ArrayList<>() ;

            Map<String , NewsCircularBean>  newsCircularBeanMap = new HashMap<>() ;

            List<String> ids  = new ArrayList<>() ;

            logService.infoLog(logger, "repository", "findNewsCircularReceiverCondition", "repository is started...");
            Long startInstruction = System.currentTimeMillis();

            List<NewsCircularReceiverEntity> newsCircularReceiverEntityList   =  newsCircularRepository.findNewsCircularReceiverCondition( queryBean.getStartTime() , queryBean.getEndTime() ,
                    queryBean.getType() , queryBean.getSource() ,  queryBean.getKeyword() , queryBean.getTitleKeyword() , queryBean.getContentKeyword() ,
                    queryBean.getReceivingObjectId() , queryBean.getNewsCircularStatus() ,
                    queryBean.getWhetherPage() , queryBean.getPagination().getPage() , queryBean.getPagination().getSize()  ) ;

            Long endInstruction = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findNewsCircularReceiverCondition", String.format("repository is finished,execute time is :%sms", endInstruction - startInstruction ));



            if( newsCircularReceiverEntityList != null && newsCircularReceiverEntityList.size() > 0 ){

                Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();

                for( NewsCircularReceiverEntity newsCircularReceiverEntity   :  newsCircularReceiverEntityList ){
                    NewsCircularReceiverBean newsCircularReceiverBean  =  SystemTransformUtil.transform( newsCircularReceiverEntity   ) ;
                    ids.add( newsCircularReceiverBean.getNewsCircularId() ) ;
                    beans.add( newsCircularReceiverBean ) ;
                }
                logService.infoLog(logger, "repository", "findNewsCircular", "repository is started...");
                Long start = System.currentTimeMillis();

                List<NewsCircularEntity> newsCircularEntityList   = newsCircularRepository.findNewsCircular( ids ) ;

                Long end = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "findNewsCircular", String.format("repository is finished,execute time is :%sms", end - start ));


                for( NewsCircularEntity newsCircularEntity :  newsCircularEntityList ){
                    NewsCircularBean newsCircularBean  = SystemTransformUtil.transform( newsCircularEntity ,organizationNameMap  );
                    newsCircularBeanMap.put(newsCircularBean.getId()  , newsCircularBean ) ;
                }

                for( NewsCircularReceiverBean key :  beans ){
                    NewsCircularBean newsCircularBean = newsCircularBeanMap.get( key.getNewsCircularId() ) ;
                    key.setNewsCircularBean( newsCircularBean );
                }
            }


            logService.infoLog(logger, "repository", "findNewsCircularReceiverConditionTotal", "repository is started...");
            Long countStart = System.currentTimeMillis();

            Integer total =  newsCircularRepository.findNewsCircularReceiverConditionTotal(  queryBean.getStartTime() , queryBean.getEndTime() ,
                    queryBean.getType() , queryBean.getSource() ,  queryBean.getKeyword() , queryBean.getTitleKeyword() , queryBean.getContentKeyword() ,
                    queryBean.getReceivingObjectId() ,queryBean.getNewsCircularStatus()) ;

            Long countEnd = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findNewsCircularReceiverConditionTotal", String.format("repository is finished,execute time is :%sms", countEnd - countStart));

            Pagination pagination = new Pagination();
            pagination.setPage( queryBean.getPagination().getPage());
            pagination.setSize( queryBean.getPagination().getSize());
            pagination.setTotal(total);

            res.setPagination(pagination);
            res.setList(beans);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findNewsCircularReceiverCondition", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res ;

        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findNewsCircularReceiverCondition", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_FAIL);
        }
    }



    /**
     * {@inheritDoc}
     *
     * @see #receiveNewsCircular(  List  )
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean  receiveNewsCircular ( List<String> newsCircularIds  ) {
        if ( null == newsCircularIds || newsCircularIds.size() < 1) {
            logService.infoLog(logger, "service", "receiveNewsCircular", "newsCircularIds  is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "receiveNewsCircular", "service is started...");
            Long logStart = System.currentTimeMillis();

            logService.infoLog(logger, "repository", "findNewsCircularReceiverByIds(newsCircularIds)", "repository is started...");
            Long startFind = System.currentTimeMillis();

            List<NewsCircularReceiverEntity> entityList  = newsCircularRepository.findNewsCircularReceiverByIds( newsCircularIds  );

            Long endFind = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findNewsCircularReceiverByIds(newsCircularIds)", String.format("repository is finished,execute time is :%sms", endFind - startFind ));

            if (entityList != null) {

                Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();

                Long currentTiem = servletService.getSystemTime() ;
                UserInfo userInfo = userService.getUserInfo() ;

                for (NewsCircularReceiverEntity entity : entityList) {
                    entity.setOperateTime( currentTiem );
                    entity.setNewsCircularStatus(   EnableEnum.ENABLE_TRUE.getCode()   );
                    entity.setOperatePersonId( userInfo.getAccount() );
                    entity.setOperatePersonName( userInfo.getPersonName() );
                }

                logService.infoLog(logger, "repository", "save(dbNewsCircularReceiverEntityList)", "repository is started...");
                Long start = System.currentTimeMillis();

                accessor.save(entityList);

                Long end = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbNewsCircularReceiverEntityList)", String.format("repository is finished,execute time is :%sms", end - start));

                //消息通知发送机构

                Set<String> orgs = new HashSet<>();
                for ( NewsCircularReceiverEntity entity : entityList) {
                    NewsCircularReceiverBean newsCircularReceiverBean  =  SystemTransformUtil.transform( entity   ) ;
                    orgs.clear();
                    orgs.add(newsCircularReceiverBean.getNewsCircularId());

                    List<String> orgCodes =  organizationService.findOrganizationCodesByIds(new ArrayList<>(orgs));
                    orgs.addAll(orgCodes);
                    notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.CHANGE_NEW_CIRCULAR_STATUS.getCode(),newsCircularReceiverBean ,orgs);
                }
            }


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "receiveNewsCircular", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return true;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "receiveNewsCircular", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DATA_FAIL);
        }
    }

    /**通知保存inputInfo转码*/
    private void decodeNewsCircularSaveInputInfo(NewsCircularSaveInputInfo source){
        if (source != null){
            try {
                if (!StringUtils.isBlank(source.getContent())){
                    source.setContent(URLDecoder.decode(source.getContent(),"UTF-8"));
                }
                if (!StringUtils.isBlank(source.getTitle())){
                    source.setTitle(URLDecoder.decode(source.getTitle(),"UTF-8"));
                }
                if (!StringUtils.isBlank(source.getRemarks())){
                    source.setRemarks(URLDecoder.decode(source.getRemarks(),"UTF-8"));
                }
            }catch (Exception e){
                throw new AcceptException(AcceptException.AccetpErrors.DECODE_FAIL );
            }

        }
    }

    /**NewsCircularQueryInputInfo 转码*/
    private void decodeNewsCircularQueryInputInfo(NewsCircularQueryInputInfo source){
        if (source != null){
            try {
                if (!StringUtils.isBlank(source.getKeyword())){
                    source.setKeyword(URLDecoder.decode(source.getKeyword(),"UTF-8"));
                }
                if (!StringUtils.isBlank(source.getTitleKeyword())){
                    source.setTitleKeyword(URLDecoder.decode(source.getTitleKeyword(),"UTF-8"));
                }
                if (!StringUtils.isBlank(source.getContentKeyword())){
                    source.setContentKeyword(URLDecoder.decode(source.getContentKeyword(),"UTF-8"));
                }
            }catch (Exception e){
                throw new AcceptException(AcceptException.AccetpErrors.DECODE_FAIL );
            }
        }
    }

}
