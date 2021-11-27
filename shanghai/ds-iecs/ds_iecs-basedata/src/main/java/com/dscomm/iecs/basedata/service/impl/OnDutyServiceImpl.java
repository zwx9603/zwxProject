package com.dscomm.iecs.basedata.service.impl;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.base.utils.DateHandleUtils;
import com.dscomm.iecs.basedata.dal.po.OnDutySummaryEntity;
import com.dscomm.iecs.basedata.dal.po.OrganizationEntity;
import com.dscomm.iecs.basedata.dal.repository.OnDutyRepository;
import com.dscomm.iecs.basedata.exception.BasedataException;
import com.dscomm.iecs.basedata.graphql.inputbean.OnDutySummarySaveInputInfo;
import com.dscomm.iecs.basedata.graphql.typebean.OnDutySummaryBean;
import com.dscomm.iecs.basedata.graphql.typebean.OnDutySummaryOrganizationBean;
import com.dscomm.iecs.basedata.graphql.typebean.OrganizationBean;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.OnDutyService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.basedata.utils.OtherResourceTransformUtil;
import com.dscomm.iecs.ext.organization.naturehead.ORGANIZATION_NATURE_HEAD_DSZD;
import com.dscomm.iecs.ext.organization.naturehead.ORGANIZATION_NATURE_HEAD_SJZD;
import com.dscomm.iecs.ext.organization.naturehead.ORGANIZATION_NATURE_HEAD_XJZD;
import com.dscomm.iecs.ext.organization.naturehead.ORGANIZATION_NATURE_HEAD_XXDD;
import com.dscomm.iecs.keydata.service.ServletService;
import org.apache.logging.log4j.util.Strings;
import org.mx.DateUtils;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 描述： 值班信息服务实现类
 */
@Component("onDutyServiceImpl")
public class OnDutyServiceImpl implements OnDutyService {
    private static final Logger logger = LoggerFactory.getLogger(OnDutyServiceImpl.class);
    private LogService logService;
    private Environment env;
    private GeneralAccessor accessor;
    private OnDutyRepository onDutyRepository;
    private OrganizationService organizationService;
    private ServletService servletService ;
    private DictionaryService dictionaryService ;

    private List<String> dics;

    @Autowired
    public OnDutyServiceImpl(@Qualifier("generalAccessor") GeneralAccessor accessor, OnDutyRepository onDutyRepository, LogService logService,
                             Environment env ,
                             OrganizationService organizationService , ServletService servletService , DictionaryService dictionaryService

    ) {
        this.accessor = accessor;
        this.logService = logService;
        this.env = env ;
        this.onDutyRepository = onDutyRepository;
        this.organizationService = organizationService;
        this.servletService = servletService ;
        this.dictionaryService = dictionaryService ;

        dics = new ArrayList<>(Arrays.asList("ZBLXXX", "GW", "XZQX"  ));
    }


    /**
     * {@inheritDoc}
     *
     * @see OnDutyService#findChildrenOrganizationOnDuty(  String)
     */
    @Transactional( rollbackFor =   Exception.class )
    @Override
    public  Boolean  updateOnDuty( OnDutySummarySaveInputInfo inputInfo ) {

        try {
            logService.infoLog(logger, "service", "updateOnDuty", "service is started...");
            Long logStart = System.currentTimeMillis();

            OnDutySummaryEntity onDutySummaryEntity =  accessor.getById( inputInfo.getId() , OnDutySummaryEntity.class ) ;
            if( onDutySummaryEntity != null ){

                onDutySummaryEntity.setOnDutyPersonId( inputInfo.getOnDutyPersonId() );
                onDutySummaryEntity.setOnDutyPersonNumber( inputInfo.getOnDutyPersonId() );
                onDutySummaryEntity.setOnDutyPersonName( inputInfo.getOnDutyPersonName() );
                onDutySummaryEntity.setContactNumber( inputInfo.getContactNumber() );

                accessor.save( onDutySummaryEntity ) ;

            }else{
                return  false  ;
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "updateOnDuty", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return  true  ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "updateOnDuty", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_ORGANIZATION_ON_DUTY_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see OnDutyService#findChildrenOrganizationOnDuty(  String)
     */
    @Transactional(readOnly = true)
    @Override
    public List<OnDutySummaryOrganizationBean> findAllOrganizationOnDuty(String organizationId ) {
        if (  Strings.isBlank(organizationId)) {
            logService.infoLog(logger, "service", "findAllOrganizationOnDuty", " organizationId is blank.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findAllOrganizationOnDuty", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<OnDutySummaryBean> res = new ArrayList<>();


            //今天 设置时间段参数
            Calendar cal = DateHandleUtils.buildCalendar(  env.getProperty("timeZone") ) ;
            Date timeDate = new Date( servletService.getSystemTime() );
            cal.setTime(timeDate);
//            cal.set(Calendar.HOUR_OF_DAY,   0);
//            cal.set(Calendar.MINUTE, 0);
//            cal.set(Calendar.SECOND, 0);
//            cal.set(Calendar.MILLISECOND, 0);
//            //今天数据统计时间段参数（minTodayTime - maxTodayTime）
//            Long startTime = cal.getTimeInMillis();
//            Long endTime = DateUtils.add(new Date(startTime), DateUtils.FieldType.DAY, 1).getTime();
            //今天数据统计时间段参数（minTodayTime - maxTodayTime）

            Long endTime = cal.getTimeInMillis();
            Long startTime  = DateUtils.add(new Date(endTime), DateUtils.FieldType.DAY, -1).getTime();


            //获得机构查询码
            OrganizationBean organizationBean = organizationService.findOrganizationByOrganizationId( organizationId );
            if( organizationBean != null ){

                List<OnDutySummaryEntity> ondutySummarys = new ArrayList<>() ;

                //根据id获取机构
                OrganizationEntity organizationEntity = accessor.getById(organizationId,OrganizationEntity.class);
                if(organizationEntity != null){
                    //判断机构性质是否为大队
                    String nature = organizationEntity.getOrganizationNatureCode();
                    if(Strings.isNotBlank(nature) && nature.startsWith(  ORGANIZATION_NATURE_HEAD_XXDD.getCode() )){
                        //若机构性质是大队，则一并获取其上级支队机构的值班信息
                        List<OnDutySummaryEntity> parentOnDuty = onDutyRepository.findOrganizationOnDuty(startTime, endTime, organizationEntity.getOrganizationParentId());
                        if (parentOnDuty != null  && parentOnDuty.size() > 0){
                            if(ondutySummarys == null){
                                ondutySummarys = new ArrayList<>();
                            }
                            ondutySummarys.addAll(parentOnDuty);
                        }
                    }
                }

                String searchPath = organizationBean.getSearchPath() ;



                logService.infoLog(logger, "repository", "findAllOrganizationOnDuty", "repository is started...");
                Long start = System.currentTimeMillis();

                List<OnDutySummaryEntity> organizationSummarys = onDutyRepository.findAllOrganizationOnDuty(startTime, endTime, searchPath + "%");

                Long end = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "findAllOrganizationOnDuty", String.format("repository is finished,execute time is :%sms", end - start));

                if( organizationSummarys != null && organizationSummarys.size() > 0 ){
                    ondutySummarys.addAll( organizationSummarys );
                }


                if (ondutySummarys != null && ondutySummarys.size() > 0) {
                    Map<String, Map<String, String>>  dicsMap = dictionaryService.findDictionaryMap(dics) ;
                    for (OnDutySummaryEntity ondutySummary : ondutySummarys) {
                        OnDutySummaryBean ondutySummaryBean = OtherResourceTransformUtil.transform(ondutySummary , dicsMap );
                        res.add(ondutySummaryBean);
                    }
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findAllOrganizationOnDuty", String.format("service is finished,execute time is :%sms", logEnd - logStart));




            return buildOnDutySummaryOrganization(  res )  ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findAllOrganizationOnDuty", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_ORGANIZATION_ON_DUTY_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see OnDutyService#findOrganizationOnDuty(  String)
     */
    @Transactional(readOnly = true)
    @Override
    public List<OnDutySummaryOrganizationBean> findOrganizationOnDuty(  String organizationId) {
        if (  Strings.isBlank(organizationId)) {
            logService.infoLog(logger, "service", "findOrganizationOnDuty", " organizationId is blank.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findOrganizationOnDuty", "service is started...");
            Long logStart = System.currentTimeMillis();

            //今天 设置时间段参数
            Calendar cal = DateHandleUtils.buildCalendar(  env.getProperty("timeZone") ) ;
            Date timeDate = new Date( servletService.getSystemTime() );
            cal.setTime(timeDate);
//            cal.set(Calendar.HOUR_OF_DAY,   0);
//            cal.set(Calendar.MINUTE, 0);
//            cal.set(Calendar.SECOND, 0);
//            cal.set(Calendar.MILLISECOND, 0);
//            //今天数据统计时间段参数（minTodayTime - maxTodayTime）
//            Long startTime = cal.getTimeInMillis();
//            Long endTime = DateUtils.add(new Date(startTime), DateUtils.FieldType.DAY, 1).getTime();
            //今天数据统计时间段参数（minTodayTime - maxTodayTime）

            Long endTime = cal.getTimeInMillis();
            Long startTime  = DateUtils.add(new Date(endTime), DateUtils.FieldType.DAY, -1).getTime();

            List<OnDutySummaryEntity> ondutySummarys = new ArrayList<>() ;

            //根据id获取机构
            OrganizationBean organizationEntity = organizationService.findOrganizationByOrganizationId( organizationId );
            if(organizationEntity != null){
                //判断机构性质是否为大队
                String nature = organizationEntity.getOrganizationNatureCode();
                if(Strings.isNotBlank(nature) && nature.startsWith(  ORGANIZATION_NATURE_HEAD_XXDD.getCode() )){
                    //若机构性质是大队，则一并获取其上级支队机构的值班信息
                    List<OnDutySummaryEntity> parentOnDuty = onDutyRepository.findOrganizationOnDuty(startTime, endTime, organizationEntity.getOrganizationParentId());
                    if (parentOnDuty != null  && parentOnDuty.size() > 0){
                        ondutySummarys.addAll(parentOnDuty);
                    }
                }
                //判断机构性质是否为总队
                else if(Strings.isNotBlank(nature) && nature.startsWith(  ORGANIZATION_NATURE_HEAD_SJZD.getCode() )){

                    String searchPath = organizationEntity.getSearchPath() ;

                    String natureLike =  ORGANIZATION_NATURE_HEAD_DSZD.getCode() + "%"; // 支队
                    ondutySummarys = onDutyRepository.findSquadronOrganizationOnDuty(startTime, endTime, searchPath + "%" , natureLike );

                } //支队本身
                else {
                    ondutySummarys = onDutyRepository.findOrganizationOnDuty(startTime, endTime, organizationEntity.getId()  );
                }
            }

            List<OnDutySummaryBean> res = new ArrayList<>();
            if (ondutySummarys != null && ondutySummarys.size() > 0) {
                Map<String, Map<String, String>>  dicsMap = dictionaryService.findDictionaryMap(dics) ;
                for (OnDutySummaryEntity ondutySummary : ondutySummarys) {
                    OnDutySummaryBean ondutySummaryBean = OtherResourceTransformUtil.transform(ondutySummary , dicsMap);
                    res.add(ondutySummaryBean);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findOrganizationOnDuty", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return buildOnDutySummaryOrganization(  res )  ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findOrganizationOnDuty", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_ORGANIZATION_ON_DUTY_FAIL);
        }
    }



    /**
     * {@inheritDoc}
     *
     * @see OnDutyService#findChildrenOrganizationOnDuty(  String)
     */
    @Transactional(readOnly = true)
    @Override
    public List<OnDutySummaryOrganizationBean> findChildrenOrganizationOnDuty(  String organizationId) {
        if ( Strings.isBlank(organizationId)) {
            logService.infoLog(logger, "service", "findChildrenOrganizationOnDuty", "startTime is null or endTime is null or organizationId is blank.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findChildrenOrganizationOnDuty", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<OnDutySummaryBean> res = new ArrayList<>();

            //今天 设置时间段参数
            Calendar cal = DateHandleUtils.buildCalendar(  env.getProperty("timeZone") ) ;
            Date timeDate = new Date( servletService.getSystemTime() );
            cal.setTime(timeDate);
//            cal.set(Calendar.HOUR_OF_DAY,   0);
//            cal.set(Calendar.MINUTE, 0);
//            cal.set(Calendar.SECOND, 0);
//            cal.set(Calendar.MILLISECOND, 0);
//            //今天数据统计时间段参数（minTodayTime - maxTodayTime）
//            Long startTime = cal.getTimeInMillis();
//            Long endTime = DateUtils.add(new Date(startTime), DateUtils.FieldType.DAY, 1).getTime();
            //今天数据统计时间段参数（minTodayTime - maxTodayTime）

            Long endTime = cal.getTimeInMillis();
            Long startTime  = DateUtils.add(new Date(endTime), DateUtils.FieldType.DAY, -1).getTime();



            List<OnDutySummaryEntity> ondutySummarys =new ArrayList<>( ) ;


            //根据id获取机构
            OrganizationBean organizationEntity = organizationService.findOrganizationByOrganizationId( organizationId );
            if(organizationEntity != null){
                //判断机构性质是否为中队
                String nature = organizationEntity.getOrganizationNatureCode();
                if(Strings.isNotBlank(nature) && nature.startsWith(  ORGANIZATION_NATURE_HEAD_XJZD.getCode() )){
                    //若机构性质是中队，则一并获取其上级大队机构的值班信息
                    List<OnDutySummaryEntity> parentOnDuty = onDutyRepository.findOrganizationOnDuty(startTime, endTime, organizationEntity.getOrganizationParentId());
                    if (parentOnDuty != null  && parentOnDuty.size() > 0){
                        ondutySummarys.addAll(parentOnDuty);
                    }
                }
                //其他
                else{
                    String searchPath = organizationEntity.getSearchPath() ;

                    String natureLike =  ORGANIZATION_NATURE_HEAD_XXDD.getCode() + "%"; // 大队
                    ondutySummarys = onDutyRepository.findSquadronOrganizationOnDuty(startTime, endTime, searchPath + "%" , natureLike );
                }
            }

            if (ondutySummarys != null && ondutySummarys.size() > 0) {
                Map<String, Map<String, String>>  dicsMap = dictionaryService.findDictionaryMap(dics) ;
                for (OnDutySummaryEntity ondutySummary : ondutySummarys) {
                    OnDutySummaryBean ondutySummaryBean = OtherResourceTransformUtil.transform(ondutySummary , dicsMap);
                    res.add(ondutySummaryBean);
                }
            }



            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findChildrenOrganizationOnDuty", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return buildOnDutySummaryOrganization(  res )  ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findChildrenOrganizationOnDuty", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_ORGANIZATION_ON_DUTY_FAIL);
        }
    }



    /**
     * {@inheritDoc}
     *
     * @see OnDutyService#findChildrenOrganizationOnDuty(  String)
     */
    @Transactional(readOnly = true)
    @Override
    public List<OnDutySummaryOrganizationBean> findSquadronOrganizationOnDuty(   String organizationId ) {
        if (  Strings.isBlank(organizationId)) {
            logService.infoLog(logger, "service", "findSquadronOrganizationOnDuty", "startTime is null or endTime is null or organizationId is blank.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findSquadronOrganizationOnDuty", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<OnDutySummaryBean> res = new ArrayList<>();

            //获得机构查询码
            OrganizationBean organizationBean = organizationService.findOrganizationByOrganizationId( organizationId );
            if( organizationBean != null ){
                String searchPath = organizationBean.getSearchPath() ;

                //今天 设置时间段参数
                Calendar cal = DateHandleUtils.buildCalendar(  env.getProperty("timeZone") ) ;
                Date timeDate = new Date( servletService.getSystemTime() );
                cal.setTime(timeDate);
//            cal.set(Calendar.HOUR_OF_DAY,   0);
//            cal.set(Calendar.MINUTE, 0);
//            cal.set(Calendar.SECOND, 0);
//            cal.set(Calendar.MILLISECOND, 0);
//            //今天数据统计时间段参数（minTodayTime - maxTodayTime）
//            Long startTime = cal.getTimeInMillis();
//            Long endTime = DateUtils.add(new Date(startTime), DateUtils.FieldType.DAY, 1).getTime();
                //今天数据统计时间段参数（minTodayTime - maxTodayTime）

                Long endTime = cal.getTimeInMillis();
                Long startTime  = DateUtils.add(new Date(endTime), DateUtils.FieldType.DAY, -1).getTime();

                logService.infoLog(logger, "repository", "findSquadronOrganizationOnDuty", "repository is started...");
                Long start = System.currentTimeMillis();

                String natureLike =  ORGANIZATION_NATURE_HEAD_XJZD.getCode() + "%"; //救援站（中队）
                List<OnDutySummaryEntity> ondutySummarys = onDutyRepository.findSquadronOrganizationOnDuty(startTime, endTime, searchPath + "%" , natureLike );

                Long end = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "findSquadronOrganizationOnDuty", String.format("repository is finished,execute time is :%sms", end - start));


                if (ondutySummarys != null && ondutySummarys.size() > 0) {
                    Map<String, Map<String, String>>  dicsMap = dictionaryService.findDictionaryMap(dics) ;
                    for (OnDutySummaryEntity ondutySummary : ondutySummarys) {
                        OnDutySummaryBean ondutySummaryBean = OtherResourceTransformUtil.transform(ondutySummary , dicsMap );
                        res.add(ondutySummaryBean);
                    }
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findSquadronOrganizationOnDuty", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return buildOnDutySummaryOrganization(  res )  ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findSquadronOrganizationOnDuty", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_ORGANIZATION_ON_DUTY_FAIL);
        }
    }




    /**
     * {@inheritDoc}
     *
     * @see OnDutyService#findChildrenOrganizationOnDuty(  String)
     */
    @Transactional(readOnly = true)
    @Override
    public List<OnDutySummaryBean> findOnDutyByOrganizationIds(   List<String> organizationIds ) {
        if (  organizationIds == null || organizationIds.size() < 1  ) {
            logService.infoLog(logger, "service", "findOnDutyByOrganizationIds", "organizationIds is blank.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findOnDutyByOrganizationIds", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<OnDutySummaryBean> res = new ArrayList<>();

            //今天 设置时间段参数
            Calendar cal = DateHandleUtils.buildCalendar(  env.getProperty("timeZone") ) ;
            Date timeDate = new Date( servletService.getSystemTime() );
            cal.setTime(timeDate);
//            cal.set(Calendar.HOUR_OF_DAY,   0);
//            cal.set(Calendar.MINUTE, 0);
//            cal.set(Calendar.SECOND, 0);
//            cal.set(Calendar.MILLISECOND, 0);
//            //今天数据统计时间段参数（minTodayTime - maxTodayTime）
//            Long startTime = cal.getTimeInMillis();
//            Long endTime = DateUtils.add(new Date(startTime), DateUtils.FieldType.DAY, 1).getTime();
            //今天数据统计时间段参数（minTodayTime - maxTodayTime）

            Long endTime = cal.getTimeInMillis();
            Long startTime  = DateUtils.add(new Date(endTime), DateUtils.FieldType.DAY, -1).getTime();

            logService.infoLog(logger, "repository", "findSquadronOrganizationOnDuty", "repository is started...");
            Long start = System.currentTimeMillis();

            List<OnDutySummaryEntity> ondutySummarys = new ArrayList<>( );

            if( organizationIds != null && organizationIds.size() > 0 && organizationIds.size() <= 900 ){
                ondutySummarys = onDutyRepository.findOrganizationOnDuty(startTime, endTime, organizationIds);
            }else if(  organizationIds != null && organizationIds.size()  > 900 ){
                int page = ( int ) Math.ceil( organizationIds.size() / 900.0 ) ;
                for( int i = 0 ; i < page ; i++ ){
                    int startnum = i * 900 ;
                    int endnum = ( i + 1 ) * 900 ;
                    if( endnum > organizationIds.size() ){
                        endnum = organizationIds.size() ;
                    }
                    List<String>  batchIds = organizationIds.subList( startnum , endnum ) ;
                    List<OnDutySummaryEntity> bathEntityList  = onDutyRepository.findOrganizationOnDuty(startTime, endTime, batchIds);
                    if (null != bathEntityList && bathEntityList.size() > 0) {
                        ondutySummarys.addAll( bathEntityList ) ;
                    }
                }
            }

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findSquadronOrganizationOnDuty", String.format("repository is finished,execute time is :%sms", end - start));


            if (ondutySummarys != null && ondutySummarys.size() > 0) {
                Map<String, Map<String, String>>  dicsMap = dictionaryService.findDictionaryMap(dics) ;
                for (OnDutySummaryEntity ondutySummary : ondutySummarys) {
                    OnDutySummaryBean ondutySummaryBean = OtherResourceTransformUtil.transform(ondutySummary , dicsMap);
                    res.add(ondutySummaryBean);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findSquadronOrganizationOnDuty", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return     res  ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findSquadronOrganizationOnDuty", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_ORGANIZATION_ON_DUTY_FAIL);
        }
    }




    /**
     * 构建 单位值班信息
     * @param onDutySummaryOrganizationBeans
     * @return
     */
    private List<OnDutySummaryOrganizationBean>  buildOnDutySummaryOrganization( List<OnDutySummaryBean> onDutySummaryOrganizationBeans ){
        List<OnDutySummaryOrganizationBean> res = new ArrayList<>() ;

        if( onDutySummaryOrganizationBeans != null && onDutySummaryOrganizationBeans.size() > 0 ){
            List<String> organizationIds = new ArrayList<>() ;
            Map< String ,  OnDutySummaryOrganizationBean >  onDutySummaryOrganizationMap = new HashMap<>() ;
            for( OnDutySummaryBean onDutySummaryBean :  onDutySummaryOrganizationBeans   ){
                OnDutySummaryOrganizationBean onDutySummaryOrganizationBean = onDutySummaryOrganizationMap.get( onDutySummaryBean.getOrganizationId() ) ;
                if( onDutySummaryOrganizationBean == null ){
                    onDutySummaryOrganizationBean = new OnDutySummaryOrganizationBean() ;
                    onDutySummaryOrganizationBean.setOrganizationId( onDutySummaryBean.getOrganizationId() );
                    onDutySummaryOrganizationBean.setOrganizationName( onDutySummaryBean.getOrganizationName() );
                    onDutySummaryOrganizationBean.setOnDutySummaryOrganizations( new ArrayList<OnDutySummaryBean>() );

                    organizationIds.add( onDutySummaryBean.getOrganizationId()  ) ;
                }
                onDutySummaryOrganizationBean.getOnDutySummaryOrganizations().add( onDutySummaryBean ) ;

                onDutySummaryOrganizationMap.put(  onDutySummaryBean.getOrganizationId() ,onDutySummaryOrganizationBean  ) ;

            }
            for( String organizationId :  organizationIds){
                    res.add( onDutySummaryOrganizationMap.get( organizationId ) ) ;
            }
        }
        return  res ;
    }


}
