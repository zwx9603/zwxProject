package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.CaseAutoUpdateWarnEntity;
import com.dscomm.iecs.accept.dal.repository.CaseAutoUpdateWarnRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.CaseAutoUpdateWarnInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.CaseAutoUpdateWarnQueryInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.CaseAutoUpdateWarnBean;
import com.dscomm.iecs.accept.service.CaseAutoUpdateWarnSerivce;
import com.dscomm.iecs.accept.utils.transform.HandleDispatchTransformUtil;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.graphql.typebean.KeyUnitSimpleBean;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.KeyUnitService;
import com.dscomm.iecs.ext.WarnTypeBakEnum;
import com.dscomm.iecs.ext.incident.autoUpdate.*;
import io.jsonwebtoken.lang.Collections;
import org.apache.logging.log4j.util.Strings;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.*;

@Component("caseAutoUpdateWarnSerivceImpl")
public class CaseAutoUpdateWarnSerivceImpl implements CaseAutoUpdateWarnSerivce {
    private static final Logger logger = LoggerFactory.getLogger(AcceptanceServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private CaseAutoUpdateWarnRepository caseAutoUpdateWarnRepository;
    private DictionaryService dictionaryService;
    private List<String> dics;
    private KeyUnitService keyUnitService;
    private Environment env;
    public CaseAutoUpdateWarnSerivceImpl(LogService logService,
                                         @Qualifier("generalAccessor")GeneralAccessor accessor,
                                         CaseAutoUpdateWarnRepository caseAutoUpdateWarnRepository,
                                         DictionaryService dictionaryService,KeyUnitService keyUnitService ,
                                         Environment env ) {
        this.logService = logService;
        this.accessor = accessor;
        this.caseAutoUpdateWarnRepository = caseAutoUpdateWarnRepository;
        this.dictionaryService = dictionaryService;
        this.keyUnitService = keyUnitService;
        this.env = env ;
        dics = new ArrayList<>(Arrays.asList("AJLX", "CZDX", "AJDJ" ,"WLCLLX"  ));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CaseAutoUpdateWarnBean saveCaseAutoUpdateWarn(@NotNull CaseAutoUpdateWarnInputInfo inputInfo) {
        //逻辑判断 是否可以进行保存
        //获得提醒类型
        String  warnType = inputInfo.getWarnType() ;
        Boolean judgeBl = judgeHavingSave( warnType , inputInfo );
        if( !judgeBl){
            logService.infoLog(logger, "service", "saveCaseAutoUpdateWarn", "data  is null.");
            throw new AcceptException(AcceptException.AccetpErrors.CASE_AUTO_LEVEL_FAIL);
        }

        try {
            logService.infoLog(logger, "service", "saveCaseAutoUpdateWarn", "service is started...");
            Long startHandleBatch = System.currentTimeMillis();

            CaseAutoUpdateWarnEntity transform  = buildSaveEntity( warnType , inputInfo ) ;

            CaseAutoUpdateWarnEntity caseAutoUpdateWarnEntity = accessor.save(transform);

            Long endHandleBatch = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveCaseAutoUpdateWarn", String.format("service is finished,execute time is :%sms", endHandleBatch - startHandleBatch));

            return HandleDispatchTransformUtil.transform(caseAutoUpdateWarnEntity);
        } catch (Exception e) {
            logService.erorLog(logger, "service", "saveCaseAutoUpdateWarn", "execution error", e);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DATA_FAIL);
        }
    }


    private Boolean   judgeHavingSave(  String  warnType , CaseAutoUpdateWarnInputInfo inputInfo  )  {
        //被困人数 燃烧面积  最小值 最大值 判断 以及是否以及存在交集
        // 燃烧对象 火灾场所 重点单位
        // 夜间警情
        //被困人数
        if(TXLX_TYPE_BKRS.getCode().equals( warnType ) ){
            String min = inputInfo.getMinimum() ;
            String max = inputInfo.getMaximum() ;
            // 最小值 最大值比较
            if( Integer.parseInt( min ) > Integer.parseInt( max ) ){
                return  false ;
            }
            //判断是否为新增
            if(Strings.isNotBlank( inputInfo.getId() )){
                //获得 案件类型  提醒类型 其他等级是否 存在交集
                List<CaseAutoUpdateWarnEntity> other =   caseAutoUpdateWarnRepository.findCaseAutoUpdateWarnByCaseTypeNoId(
                        inputInfo.getCaseType() ,  warnType , inputInfo.getId() );
                if( other != null && other.size() > 0 ){
                    for(CaseAutoUpdateWarnEntity autoUpdateWarn : other  ){
                        String amin = autoUpdateWarn.getMinimum() ;
                        String bmax = autoUpdateWarn.getMaximum() ;
                        if( Math.max( Integer.parseInt(min ), Integer.parseInt(amin ) ) <=  Math.min( Integer.parseInt(max ) , Integer.parseInt( bmax ) ) ){
                            return  false ;
                        }
                    }
                }
            }else{
                //获得 案件类型  提醒类型 其他等级是否 存在交集
                List<CaseAutoUpdateWarnEntity> other =   caseAutoUpdateWarnRepository.findCaseAutoUpdateWarnByCaseTypeNoLevel(
                        inputInfo.getCaseType() ,  warnType ,  inputInfo.getIncidentLevelCode()  );
                if( other != null && other.size() > 0 ){
                    for(CaseAutoUpdateWarnEntity autoUpdateWarn : other  ){
                        String amin = autoUpdateWarn.getMinimum() ;
                        String bmax = autoUpdateWarn.getMaximum() ;
                        if( Math.max( Integer.parseInt(min ), Integer.parseInt(amin ) ) <=  Math.min( Integer.parseInt(max ) , Integer.parseInt( bmax ) ) ){
                            return  false ;
                        }
                    }
                }
            }

            //燃烧面积
        }else if( TXLX_TYPE_RSMJ.getCode().equals( warnType )  ){
            String min = inputInfo.getMinimum() ;
            String max = inputInfo.getMaximum() ;
            // 最小值 最大值比较
            if( Integer.parseInt( min ) > Integer.parseInt( max ) ){
                return  false ;
            }
            //判断是否为新增
            if( Strings.isNotBlank( inputInfo.getId() )){
                //获得 案件类型  提醒类型 其他等级是否 存在交集
                List<CaseAutoUpdateWarnEntity> other =   caseAutoUpdateWarnRepository.findCaseAutoUpdateWarnByCaseTypeNoId(
                        inputInfo.getCaseType() ,  warnType , inputInfo.getId()  );
                if( other != null && other.size() > 0 ){
                    for(CaseAutoUpdateWarnEntity autoUpdateWarn : other  ){
                        String amin = autoUpdateWarn.getMinimum() ;
                        String bmax = autoUpdateWarn.getMaximum() ;
                        if( Math.max( Integer.parseInt(min ), Integer.parseInt(amin ) ) <=  Math.min( Integer.parseInt(max ) , Integer.parseInt( bmax ) ) ){
                            return  false ;
                        }
                    }
                }
            }else{
                //获得 案件类型  提醒类型 其他等级是否 存在交集
                List<CaseAutoUpdateWarnEntity> other =   caseAutoUpdateWarnRepository.findCaseAutoUpdateWarnByCaseTypeNoLevel(
                        inputInfo.getCaseType() ,  warnType ,  inputInfo.getIncidentLevelCode()  );
                if( other != null && other.size() > 0 ){
                    for(CaseAutoUpdateWarnEntity autoUpdateWarn : other  ){
                        String amin = autoUpdateWarn.getMinimum() ;
                        String bmax = autoUpdateWarn.getMaximum() ;
                        if( Math.max( Integer.parseInt(min ), Integer.parseInt(amin ) ) <=  Math.min( Integer.parseInt(max ) , Integer.parseInt( bmax ) ) ){
                            return  false ;
                        }
                    }
                }
            }
        }

        return  true ;
    }

    private CaseAutoUpdateWarnEntity   buildSaveEntity(  String  warnType , CaseAutoUpdateWarnInputInfo inputInfo  )  {
        //构建保存entity
        CaseAutoUpdateWarnEntity transform = HandleDispatchTransformUtil.transform(inputInfo);
        //判断是否为修改
        if( Strings.isNotBlank( inputInfo.getId() ) ){
            transform.setId( inputInfo.getId() );
        }else {
            //被困人数
            if (TXLX_TYPE_BKRS.getCode().equals(warnType)) {

                CaseAutoUpdateWarnEntity caseAutoUpdateWarnEntity =
                        caseAutoUpdateWarnRepository.findCaseAutoUpdateWarnByCaseTypeLevel(inputInfo.getCaseType(), warnType, inputInfo.getIncidentLevelCode());
                if (caseAutoUpdateWarnEntity != null) {
                    transform.setId(caseAutoUpdateWarnEntity.getId());
                    transform.setCreatedTime(caseAutoUpdateWarnEntity.getCreatedTime());
                }
                //燃烧面积
            } else if (TXLX_TYPE_RSMJ.getCode().equals(warnType)) {
                CaseAutoUpdateWarnEntity caseAutoUpdateWarnEntity =
                        caseAutoUpdateWarnRepository.findCaseAutoUpdateWarnByCaseTypeLevel(inputInfo.getCaseType(), warnType, inputInfo.getIncidentLevelCode());
                if (caseAutoUpdateWarnEntity != null) {
                    transform.setId(caseAutoUpdateWarnEntity.getId());
                    transform.setCreatedTime(caseAutoUpdateWarnEntity.getCreatedTime());
                }
                // 燃烧对象
            } else if (TXLX_TYPE_RSDX.getCode().equals(warnType)) {
                CaseAutoUpdateWarnEntity caseAutoUpdateWarnEntity =
                        caseAutoUpdateWarnRepository.findCaseAutoUpdateWarnByCaseTypeDisposalObject(inputInfo.getCaseType(), warnType, inputInfo.getDisposalObjectCode());
                if (caseAutoUpdateWarnEntity != null) {
                    transform.setId(caseAutoUpdateWarnEntity.getId());
                    transform.setCreatedTime(caseAutoUpdateWarnEntity.getCreatedTime());
                }
                //火灾场所
            } else if (TXLX_TYPE_ZHCS.getCode().equals(warnType)) {
                CaseAutoUpdateWarnEntity caseAutoUpdateWarnEntity =
                        caseAutoUpdateWarnRepository.findCaseAutoUpdateWarnByCaseTypeDisposalObject(inputInfo.getCaseType(), warnType, inputInfo.getDisposalObjectCode());
                if (caseAutoUpdateWarnEntity != null) {
                    transform.setId(caseAutoUpdateWarnEntity.getId());
                    transform.setCreatedTime(caseAutoUpdateWarnEntity.getCreatedTime());
                }
                //重点单位
            } else if (TXLX_TYPE_ZDDW.getCode().equals(warnType)) {
                CaseAutoUpdateWarnEntity caseAutoUpdateWarnEntity =
                        caseAutoUpdateWarnRepository.findCaseAutoUpdateWarnByCaseTypeDisposalObject(inputInfo.getCaseType(), warnType, inputInfo.getDisposalObjectCode());
                if (caseAutoUpdateWarnEntity != null) {
                    transform.setId(caseAutoUpdateWarnEntity.getId());
                    transform.setCreatedTime(caseAutoUpdateWarnEntity.getCreatedTime());
                }
                //夜间时间
            } else if (TXLX_TYPE_ZDDW.getCode().equals(warnType)) {
                List<CaseAutoUpdateWarnEntity> caseAutoUpdateWarnEntityList =
                        caseAutoUpdateWarnRepository.findCaseAutoUpdateWarnByCaseType(inputInfo.getCaseType(), warnType);
                if (caseAutoUpdateWarnEntityList != null && caseAutoUpdateWarnEntityList.size() > 0) {
                    CaseAutoUpdateWarnEntity caseAutoUpdateWarnEntity = caseAutoUpdateWarnEntityList.get(0);
                    transform.setId(caseAutoUpdateWarnEntity.getId());
                    transform.setCreatedTime(caseAutoUpdateWarnEntity.getCreatedTime());
                }
            }
        }
        return  transform ;

    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean removeCaseAutoUpdateWarn(@NotNull String id) {
        try {
            logService.infoLog(logger, "service", "removeCaseAutoUpdateWarn", "service is started...");
            Long startHandleBatch = System.currentTimeMillis();

            CaseAutoUpdateWarnEntity delete = accessor.getById(id, CaseAutoUpdateWarnEntity.class);
            if (Objects.nonNull(delete)){
                delete.setValid(Boolean.FALSE);
                accessor.save(delete);
            }

            Long endHandleBatch = System.currentTimeMillis();
            logService.infoLog(logger, "service", "removeCaseAutoUpdateWarn", String.format("service is finished,execute time is :%sms", endHandleBatch - startHandleBatch));

            return Boolean.TRUE;
        } catch (Exception e) {
            logService.erorLog(logger, "service", "removeCaseAutoUpdateWarn", "execution error", e);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DATA_FAIL);
        }
    }

    @Transactional(readOnly =  true)
    @Override
    public CaseAutoUpdateWarnBean findCaseAutoUpdateWarnById(@NotNull String id) {
        try {
            logService.infoLog(logger, "service", "findCaseAutoUpdateWarnById", "service is started...");
            Long startHandleBatch = System.currentTimeMillis();
            List<CaseAutoUpdateWarnEntity>  caseAutoUpdateWarnEntity = caseAutoUpdateWarnRepository.getById(id);
            //获取案件类型名称map
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;

            List<String> keyUnitIds = new ArrayList<>( ) ;
            if( caseAutoUpdateWarnEntity != null && caseAutoUpdateWarnEntity.size() > 0 ){
                for( CaseAutoUpdateWarnEntity caseAutoUpdateWarn  : caseAutoUpdateWarnEntity ){
                    if (  caseAutoUpdateWarn.getWarnType().equals(WarnTypeBakEnum.WARN_TYPE_ZDDW.getCode()  )){
                        keyUnitIds.add(  caseAutoUpdateWarn.getDisposalObjectCode() ) ;
                    }
                }
            }
            //获得全部重点单位信息
            Map<String,String> keyUnitIdNameMap  = keyUnitService.findKeyUnitIdNameMap(keyUnitIds );
            List<CaseAutoUpdateWarnBean> transform = HandleDispatchTransformUtil.transform(caseAutoUpdateWarnEntity , dicsMap , keyUnitIdNameMap  );
            Long endHandleBatch = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findCaseAutoUpdateWarnById", String.format("service is finished,execute time is :%sms", endHandleBatch - startHandleBatch));
            if (Collections.isEmpty(transform)){
                return null;
            }
            return transform.get(0);
        } catch (Exception e) {
            logService.erorLog(logger, "service", "findCaseAutoUpdateWarnById", "execution error", e);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_FAIL);
        }
    }

    @Transactional(readOnly =  true)
    @Override
    public List<CaseAutoUpdateWarnBean> findCaseAutoUpdateWarn() {
        try {
            logService.infoLog(logger, "service", "findCaseAutoUpdateWarn", "service is started...");
            Long startHandleBatch = System.currentTimeMillis();


            List<CaseAutoUpdateWarnEntity> all = caseAutoUpdateWarnRepository.findAllBy();
            //获取案件类型名称map
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;
            //获得全部重点单位信息
            List<String> keyUnitIds = new ArrayList<>( ) ;
            if( all != null && all.size() > 0 ){
                for( CaseAutoUpdateWarnEntity caseAutoUpdateWarn  : all ){
                    if (  caseAutoUpdateWarn.getWarnType().equals(WarnTypeBakEnum.WARN_TYPE_ZDDW.getCode()  )){
                        keyUnitIds.add(  caseAutoUpdateWarn.getDisposalObjectCode() ) ;
                    }
                }
            }
            Map<String,String> keyUnitIdNameMap  = keyUnitService.findKeyUnitIdNameMap(keyUnitIds );

            List<CaseAutoUpdateWarnBean> res  = HandleDispatchTransformUtil.transform(all, dicsMap, keyUnitIdNameMap );

            Long endHandleBatch = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findCaseAutoUpdateWarn", String.format("service is finished,execute time is :%sms", endHandleBatch - startHandleBatch));

            return res;
        } catch (Exception e) {
            logService.erorLog(logger, "service", "findCaseAutoUpdateWarn", "execution error", e);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_FAIL);
        }
    }

    @Transactional(readOnly =  true)
    @Override
    public List<CaseAutoUpdateWarnBean> findCaseAutoUpdateWarnCondition(  CaseAutoUpdateWarnQueryInputInfo queryBean ) {
        try {
            logService.infoLog(logger, "service", "findCaseAutoUpdateWarnCondition", "service is started...");
            Long startHandleBatch = System.currentTimeMillis();


            List<CaseAutoUpdateWarnEntity> all = caseAutoUpdateWarnRepository.findAllByCondition( queryBean.getCaseType() , queryBean.getWarnType() ,
                    queryBean.getIncidentLevelCode() , queryBean.getKeyword() ,  env.getProperty("CZDX", "") , env.getProperty("ZHCS", "") );
            //获取案件类型名称map
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;
            //获得全部重点单位信息
            List<String> keyUnitIds = new ArrayList<>( ) ;
            if( all != null && all.size() > 0 ){
                for( CaseAutoUpdateWarnEntity caseAutoUpdateWarn  : all ){
                    if (  caseAutoUpdateWarn.getWarnType().equals(WarnTypeBakEnum.WARN_TYPE_ZDDW.getCode()  )){
                        keyUnitIds.add(  caseAutoUpdateWarn.getDisposalObjectCode() ) ;
                    }
                }
            }
            Map<String,String> keyUnitIdNameMap  = keyUnitService.findKeyUnitIdNameMap(keyUnitIds );
            List<CaseAutoUpdateWarnBean> res   = HandleDispatchTransformUtil.transform(all, dicsMap, keyUnitIdNameMap );

            Long endHandleBatch = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findCaseAutoUpdateWarnCondition", String.format("service is finished,execute time is :%sms", endHandleBatch - startHandleBatch));

            return res;
        } catch (Exception e) {
            logService.erorLog(logger, "service", "findCaseAutoUpdateWarnCondition", "execution error", e);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_FAIL);
        }
    }



    @Transactional(readOnly =  true)
    @Override
    public CaseAutoUpdateWarnBean findCaseAutoUpdateWarnByCaseType( String  caseType ,  String warnType ) {
        try {
            logService.infoLog(logger, "service", "findCaseAutoUpdateWarnByCaseType", "service is started...");
            Long startHandleBatch = System.currentTimeMillis();

            CaseAutoUpdateWarnBean res = null ;

            logService.infoLog(logger, "repository", "findCaseAutoUpdateWarnByCaseType( caseType , warnType  )", "repository is started...");
            Long start = System.currentTimeMillis();

            List<CaseAutoUpdateWarnEntity> caseAutoUpdateWarnEntityList  = caseAutoUpdateWarnRepository.findCaseAutoUpdateWarnByCaseType( caseType , warnType  );

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findCaseAutoUpdateWarnByCaseType( caseType , warnType  )", String.format("repository is finished,execute time is :%sms", end - start));


            if( caseAutoUpdateWarnEntityList != null && caseAutoUpdateWarnEntityList.size() > 0  ){
                CaseAutoUpdateWarnEntity caseAutoUpdateWarnEntity = caseAutoUpdateWarnEntityList.get(0) ;
                //获取案件类型名称map
                Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;
                KeyUnitSimpleBean  keyUnit = keyUnitService.findKeyUnitSimple( caseAutoUpdateWarnEntity.getDisposalObjectCode());
                res = HandleDispatchTransformUtil.transform( caseAutoUpdateWarnEntity , dicsMap , keyUnit ) ;

            }

            Long endHandleBatch = System.currentTimeMillis();
            logService.infoLog(logger, "service", "v", String.format("service is finished,execute time is :%sms", endHandleBatch - startHandleBatch));

            return res;
        } catch (Exception e) {
            logService.erorLog(logger, "service", "findCaseAutoUpdateWarnByCaseType", "execution error", e);
            return  null ;
        }
    }




}
