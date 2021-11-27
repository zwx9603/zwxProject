package com.dscomm.iecs.keydata.service.impl;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.keydata.dal.po.KeyDataChangeRecordEntity;
import com.dscomm.iecs.keydata.exception.UserInterfaceKeydataException;
import com.dscomm.iecs.keydata.graphql.inputbean.KeyDataChangeRecordSaveInputInfo;
import com.dscomm.iecs.keydata.graphql.typebean.KeyDataChangeRecordBean;
import com.dscomm.iecs.keydata.service.KeyDataChangeRecordService;
import com.dscomm.iecs.keydata.service.ServletService;
import com.dscomm.iecs.keydata.utils.transform.KeyDataTransformUtil;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * 描述:关键数据变更记录 service服务实现
 *
 */
@Component
public class KeyDataChangeRecordServiceImpl implements KeyDataChangeRecordService {
    private static final Logger logger = LoggerFactory.getLogger(KeyDataChangeRecordServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor ;
    private ServletService servletService ;

    @Autowired
    public KeyDataChangeRecordServiceImpl(LogService logService , @Qualifier("generalAccessor") GeneralAccessor accessor , ServletService servletService

    ) {
        this.logService = logService;
        this.accessor = accessor ;
        this.servletService = servletService ;

    }



    /**
     * {@inheritDoc}
     *
     * @see  KeyDataChangeRecordService#saveKeyDataChangeRecord( List )
     */
    @Transactional( rollbackFor =  Exception.class )
    @Override
    public  KeyDataChangeRecordBean  saveKeyDataChangeRecord( KeyDataChangeRecordSaveInputInfo  queryBean) {
        if ( null == queryBean    ) {
            logService.infoLog(logger, "service", "saveKeyDataChangeRecord", "List<KeyDataChangeRecordSaveInputInfo> is null.");
            throw new UserInterfaceKeydataException(UserInterfaceKeydataException.KeydataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveKeyDataChangeRecord", "service is started...");
            Long logStart = System.currentTimeMillis();

            KeyDataChangeRecordBean  res = new KeyDataChangeRecordBean() ;

            KeyDataChangeRecordEntity keyDataChangeRecordEntity = KeyDataTransformUtil.transform(queryBean , servletService.getSystemTime() );

            logService.infoLog(logger, "repository", "save(dbKeyDataChangeRecordEntityList)", "repository is started...");
            Long start = System.currentTimeMillis();

            accessor.save(keyDataChangeRecordEntity);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbKeyDataChangeRecordEntityList)", String.format("repository is finished,execute time is :%sms", end - start));

            res = KeyDataTransformUtil.transform(keyDataChangeRecordEntity);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveKeyDataChangeRecord", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveKeyDataChangeRecord", "save  KeyDataChangeRecord  fail.", ex);
            throw new UserInterfaceKeydataException(UserInterfaceKeydataException.KeydataErrors.SAVE_KEYDATACHANGERECORD_FAIL);
        }
    }




    /**
     * {@inheritDoc}
     *
     * @see  KeyDataChangeRecordService#saveKeyDataChangeRecord( List )
     */
    @Transactional( rollbackFor =  Exception.class )
    @Override
    public List<KeyDataChangeRecordBean> saveKeyDataChangeRecord(List<KeyDataChangeRecordSaveInputInfo> queryBean) {
        if ( null == queryBean && queryBean.size() > 0  ) {
            logService.infoLog(logger, "service", "saveKeyDataChangeRecord", "List<KeyDataChangeRecordSaveInputInfo> is null.");
            throw new UserInterfaceKeydataException(UserInterfaceKeydataException.KeydataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveKeyDataChangeRecord", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<KeyDataChangeRecordBean> res = new ArrayList() ;

            if( null != queryBean  && queryBean.size() > 0 ){
                List<KeyDataChangeRecordEntity> keyDataChangeRecordEntityList = new ArrayList<>( ) ;
                for( KeyDataChangeRecordSaveInputInfo keyDataChangeRecordSaveInputInfo : queryBean ){
                    KeyDataChangeRecordEntity keyDataChangeRecordEntity = KeyDataTransformUtil.transform(keyDataChangeRecordSaveInputInfo , servletService.getSystemTime() );
                    keyDataChangeRecordEntityList.add( keyDataChangeRecordEntity ) ;
                }
                logService.infoLog(logger, "repository", "save(dbKeyDataChangeRecordEntityList)", "repository is started...");
                Long start = System.currentTimeMillis();

                accessor.save(keyDataChangeRecordEntityList);

                Long end = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbKeyDataChangeRecordEntityList)", String.format("repository is finished,execute time is :%sms", end - start));


                for(KeyDataChangeRecordEntity keyDataChangeRecordEntity : keyDataChangeRecordEntityList){
                    KeyDataChangeRecordBean keyDataChangeRecordBean = KeyDataTransformUtil.transform(keyDataChangeRecordEntity);
                    res.add( keyDataChangeRecordBean ) ;
                }
            }
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveKeyDataChangeRecord", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveKeyDataChangeRecord", "save  KeyDataChangeRecord  fail.", ex);
            throw new UserInterfaceKeydataException(UserInterfaceKeydataException.KeydataErrors.SAVE_KEYDATACHANGERECORD_FAIL);
        }
    }



}
