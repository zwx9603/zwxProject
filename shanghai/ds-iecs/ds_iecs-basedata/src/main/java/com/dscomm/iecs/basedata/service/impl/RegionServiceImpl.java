package com.dscomm.iecs.basedata.service.impl;

import com.dscomm.iecs.base.enums.EnableEnum;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.dal.po.RegionEntity;
import com.dscomm.iecs.basedata.dal.repository.OrganizationOtherRepository;
import com.dscomm.iecs.basedata.exception.BasedataException;
import com.dscomm.iecs.basedata.graphql.typebean.RegionBean;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.RegionCacheService;
import com.dscomm.iecs.basedata.service.RegionService;
import com.dscomm.iecs.basedata.utils.OtherResourceTransformUtil;
import org.mx.StringUtils;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：范围服务类实现
 *
 */
@Component("regionServiceImpl")
public class RegionServiceImpl implements RegionService {
    private static final Logger logger = LoggerFactory.getLogger(RegionServiceImpl.class);
    private LogService logService;
    private OrganizationOtherRepository organizationOtherRepository ;
    private RegionCacheService regionCacheService;

    //设置 缓存数据
//    private static  Map<String, RegionBean> regionMap = new HashMap<>();

    /**
     * 默认的构造函数
     *
     */
    @Autowired
    public RegionServiceImpl(LogService logService,OrganizationOtherRepository organizationOtherRepository,

                             RegionCacheService regionCacheService) {
        this.logService = logService ;
        this.organizationOtherRepository = organizationOtherRepository ;

        this.regionCacheService = regionCacheService;
    }


    private Long lastTime  = 0l ; //系统默认为0 加载全部

    /**
     *  根据上次数据最新时间  本次数据最新时间
     *  判断是否从数据库更新数据 如果本次数据最新时间 大于 上次数据时间 则需要更新数据
     */
    private void updateCacheRegion(){
        logService.infoLog(logger, "service", "updateCacheRegion", "service is started...");
        Long logStart = System.currentTimeMillis();

        Long latestTime = organizationOtherRepository.findDataLatestTime( lastTime ) ;
        latestTime = latestTime == null ? lastTime : latestTime ;
        //判断是否需要更新数据
        if( latestTime > lastTime ){

            logService.infoLog(logger, "service", "updateCacheRegion", " update cache data");

            //此处为增量更新数据
            List<RegionEntity>  regionEntityList  =
                    organizationOtherRepository.findDataLatestTime( lastTime , latestTime ) ;
            if( regionEntityList != null && regionEntityList.size() > 0  ){
//                regionMap.clear();
                regionCacheService.cleanRegionCache();
                for ( RegionEntity en : regionEntityList) {
                    RegionBean bean = OtherResourceTransformUtil.transform( en );
                    if( EnableEnum.ENABLE_TRUE.getCode() == bean.getValid() ){
                        regionCacheService.modifyRegionCache( en.getId() ,bean ) ;
                    }
                }
            }

            lastTime = latestTime ;

        }

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "updateCacheRegion", String.format("service is finished,execute time is :%sms", logEnd - logStart));


    }


    /**
     * {@inheritDoc}
     *
     * @see #forceUpdateCacheRegion(   )
     */
    @Transactional(readOnly = true)
    @Override
    public void  forceUpdateCacheRegion (  ) {
        try {
            logService.infoLog(logger, "service", "forceUpdateCacheRegion", "service is started...");
            Long logStart = System.currentTimeMillis();

            updateCacheRegion();

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "forceUpdateCacheRegion", String.format("service is finished,execute time is :%sms", logEnd - logStart));

        } catch (Exception ex) {
            logService.erorLog(logger, "service", "forceUpdateCacheRegion", String.format("force cache Dictionary fail " ), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_DATA_FAIL);
        }
    }



    /**
     * {@inheritDoc}
     *
     * @see DictionaryService#findTreeDictionary(String, Boolean )
     */
    @Transactional(readOnly = true)
    @Override
    public List<RegionBean>  findRegion( List<String> ids ) {
        try {
            logService.infoLog(logger, "service", "findTreeDictionary", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<RegionBean> res =  new ArrayList<>() ;
            Map<String, RegionBean> regionMap = regionCacheService.findAllRegionCache();
            if( ids != null && ids.size() > 0 ){
                for ( String  en : ids) {
                    RegionBean bean = regionMap.get( en );
                    if( bean != null ){
                        res.add(bean);
                    }
                }
            }else{
                res.addAll( regionMap.values() ) ;
            }
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findTreeDictionary", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return  res  ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findTreeDictionary", String.format("tree dictionary fail from type: %s.", ids ), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_DICTIONARY_FAIL);
        }

    }


    /**
     * {@inheritDoc}
     *
     * @see DictionaryService#findTreeDictionary(String, Boolean )
     */
    @Transactional(readOnly = true)
    @Override
    public Map<String,RegionBean>   findRegionMap( List<String> ids ) {
        try {
            logService.infoLog(logger, "service", "findTreeDictionary", "service is started...");
            Long logStart = System.currentTimeMillis();

            Map<String,RegionBean>  res =  new HashMap<>() ;
            Map<String, RegionBean> regionMap = regionCacheService.findAllRegionCache();
            if( ids != null && ids.size() > 0 ){
                for ( String  en : ids) {
                    if (!StringUtils.isBlank(en)){
                        RegionBean bean = regionMap.get( en );
                        if( bean != null ){
                            res.put( bean.getId() , bean );
                        }
                    }
                }
            }else{
                res= regionMap  ;
            }
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findTreeDictionary", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            if (regionMap==null){
                regionMap=new HashMap<>();
            }
            return  res  ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findTreeDictionary", String.format("tree dictionary fail from type: %s.", ids ), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_DICTIONARY_FAIL);
        }

    }

}
