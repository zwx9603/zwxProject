package com.dscomm.iecs.basedata.service.impl;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.dal.po.*;
import com.dscomm.iecs.basedata.exception.BasedataException;
import com.dscomm.iecs.basedata.graphql.typebean.WaterBaseBean;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.WaterService;
import com.dscomm.iecs.basedata.utils.OtherResourceTransformUtil;
import org.apache.logging.log4j.util.Strings;
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
 * 水源service
 * */
@Component("waterServiceImpl")
public class WaterServiceImpl implements WaterService {
    private static final Logger logger = LoggerFactory.getLogger(WaterServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private DictionaryService dictionaryService ;

    private List<String> dics;

    @Autowired
    public WaterServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor , DictionaryService dictionaryServic){
        this.accessor = accessor;
        this.logService = logService;
        this.dictionaryService = dictionaryServic ;

        dics = new ArrayList<>(Arrays.asList(  "XHSLX", "JKXS", "GWXS" ,"QSXS" , "FZXS", "KYZT", "SYZT" ,"SYLX","TRSYLX"  ));
    }

    @Override
    @Transactional(readOnly = true)
    public WaterBaseBean findFireHydrantBeanById(String hydrantId ) {
        if (Strings.isBlank( hydrantId )) {
            logService.infoLog(logger, "service", "getById", "id is blank.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "getById", "service is started...");
            Long logStart = System.currentTimeMillis();

            WaterBaseBean res = null ;

            WaterBaseInfoEntity waterBaseInfoEntity = accessor.getById(hydrantId,WaterBaseInfoEntity.class);

            if( waterBaseInfoEntity != null && waterBaseInfoEntity.isValid() ){
                Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;
                  res = OtherResourceTransformUtil.transform(waterBaseInfoEntity , dicsMap);
                  //判断
                WaterFireHydrantEntity extenderWater = accessor.getById(hydrantId, WaterFireHydrantEntity.class);
                if( extenderWater != null ){
                    res.setFlowrate( extenderWater.getFlowrate() );
                }

            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "getById", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "getById", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_WATERSOURCE_FAIL);
        }
    }


    @Override
    @Transactional(readOnly = true)
    public WaterBaseBean findWaterPoolDetailsById(String waterPoolId) {
        if (Strings.isBlank( waterPoolId )) {
            logService.infoLog(logger, "service", "getById", "id is blank.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "getById", "service is started...");
            Long logStart = System.currentTimeMillis();

            WaterBaseBean res = null ;

            WaterBaseInfoEntity waterBaseInfoEntity = accessor.getById(waterPoolId,WaterBaseInfoEntity.class);

            if( waterBaseInfoEntity != null && waterBaseInfoEntity.isValid() ){
                Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;
                res = OtherResourceTransformUtil.transform(waterBaseInfoEntity , dicsMap);
                //判断
                WaterPoolEntity extenderWater = accessor.getById(waterPoolId, WaterPoolEntity.class);
                if( extenderWater != null ){
                    res.setFlowrate( extenderWater.getFlowrate() );
                    res.setParkingPosition( extenderWater.getParkingPosition() );
                    res.setElevationHeight( extenderWater.getElevationHeight() );
                    res.setIntakeHeight( extenderWater.getIntakeHeight() );
                    res.setStorage( extenderWater.getStorage() );
                    res.setWaterStorage( extenderWater.getWaterStorage() );
                    res.setParkingNum( extenderWater.getParkingNum() );
                }

            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "getById", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "getById", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_WATERSOURCE_FAIL);
        }
    }



    @Override
    @Transactional(readOnly = true)
    public WaterBaseBean findWaterCraneDetailsById(String waterCraneId){
        if (Strings.isBlank( waterCraneId )) {
            logService.infoLog(logger, "service", "getById", "id is blank.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "getById", "service is started...");
            Long logStart = System.currentTimeMillis();

            WaterBaseBean res = null ;

            WaterBaseInfoEntity waterBaseInfoEntity = accessor.getById(waterCraneId,WaterBaseInfoEntity.class);

            if( waterBaseInfoEntity != null && waterBaseInfoEntity.isValid() ){
                Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;
                res = OtherResourceTransformUtil.transform(waterBaseInfoEntity , dicsMap);
                //判断
                WaterCraneEntity extenderWater = accessor.getById(waterCraneId, WaterCraneEntity.class);
                if( extenderWater != null ){
                    res.setWaterCraneHeight( extenderWater.getWaterCraneHeight() );
                    res.setFlowrate( extenderWater.getFlowrate() );
                    res.setInletDiameter( extenderWater.getInletDiameter() );
                    res.setEffluentDiameter( extenderWater.getEffluentDiameter() );
                    res.setWaterDriveway( extenderWater.getWaterDriveway() );
                }

            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "getById", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "getById", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_WATERSOURCE_FAIL);
        }
    }




    @Override
    @Transactional(readOnly = true)
    public WaterBaseBean findNaturalWaterSourceDetailsById(String naturalWaterSourceId){
        if (Strings.isBlank( naturalWaterSourceId )) {
            logService.infoLog(logger, "service", "getById", "id is blank.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "getById", "service is started...");
            Long logStart = System.currentTimeMillis();

            WaterBaseBean res = null ;

            WaterBaseInfoEntity waterBaseInfoEntity = accessor.getById(naturalWaterSourceId,WaterBaseInfoEntity.class);

            if( waterBaseInfoEntity != null && waterBaseInfoEntity.isValid() ){
                Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;
                res = OtherResourceTransformUtil.transform(waterBaseInfoEntity , dicsMap);
                //判断
                WaterNaturalSourceEntity extenderWater = accessor.getById(naturalWaterSourceId, WaterNaturalSourceEntity.class);
                if( extenderWater != null ){
                    res.setNaturalWaterTypeCode( extenderWater.getNaturalWaterTypeCode() );
                    res.setNaturalWaterTypeName( dicsMap.get("TRSYLX").get(   extenderWater.getNaturalWaterTypeCode() )  );
                    res.setWaterNaturalSourceHeight( extenderWater.getWaterNaturalSourceHeight() );
                    res.setStorage( extenderWater.getStorage() );
                    res.setArea( extenderWater.getArea() );
                    res.setWaterQualityDesc( extenderWater.getWaterQualityDesc() );
                    res.setSeasonalChangesDesc( extenderWater.getSeasonalChangesDesc() );
                    res.setWhetherDrySeason( extenderWater.getWhetherDrySeason() );
                    res.setDrySeasonDesc( extenderWater.getDrySeasonDesc() );
                }

            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "getById", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "getById", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_WATERSOURCE_FAIL);
        }
    }





    @Override
    @Transactional(readOnly = true)
    public WaterBaseBean findWaterIntakeWharfDetailsById(String waterIntakeWharfId){
        if (Strings.isBlank( waterIntakeWharfId )) {
            logService.infoLog(logger, "service", "getById", "id is blank.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "getById", "service is started...");
            Long logStart = System.currentTimeMillis();

            WaterBaseBean res = null ;

            WaterBaseInfoEntity waterBaseInfoEntity = accessor.getById(waterIntakeWharfId,WaterBaseInfoEntity.class);

            if( waterBaseInfoEntity != null && waterBaseInfoEntity.isValid() ){
                Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;
                res = OtherResourceTransformUtil.transform(waterBaseInfoEntity , dicsMap);
                //判断
                WaterIntakeWharfEntity extenderWater = accessor.getById(waterIntakeWharfId, WaterIntakeWharfEntity.class);
                if( extenderWater != null ){
                    res.setBelongingWaterId( extenderWater.getBelongingWaterId() );
                    res.setBelongingWaterName( extenderWater.getBelongingWaterName() );
                    res.setIntakeHeight( extenderWater.getIntakeHeight() );
                    res.setElevationHeight( extenderWater.getElevationHeight() );
                    res.setParkingPosition( extenderWater.getParkingPosition() );
                    res.setParkingNum( extenderWater.getParkingNum() );
                }

            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "getById", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "getById", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_WATERSOURCE_FAIL);
        }
    }









}
