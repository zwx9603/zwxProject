package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.DispatchDailyRecordEntity;
import com.dscomm.iecs.accept.dal.po.SendSMSEntity;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.DispatchDailyRecordSaveInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.SendSMSSaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.DispatchDailyRecordBean;
import com.dscomm.iecs.accept.graphql.typebean.SendSMSBean;
import com.dscomm.iecs.accept.graphql.typebean.SoundRecordBean;
import com.dscomm.iecs.accept.service.DispatchDailyRecordService;
import com.dscomm.iecs.accept.service.SoundRecordService;
import com.dscomm.iecs.accept.utils.transform.HandleDispatchTransformUtil;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.keydata.service.ServletService;
import org.apache.logging.log4j.util.Strings;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 描述：调度日志 服务类实现
 */
@Component("dispatchDailyRecordServiceImpl")
public class DispatchDailyRecordServiceImpl implements DispatchDailyRecordService {
    private static final Logger logger = LoggerFactory.getLogger(DispatchDailyRecordServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private OrganizationService organizationService;
    private ServletService servletService ;
    private SoundRecordService soundRecordService ;

    /**
     * 默认的构造函数
     */
    @Autowired
    public DispatchDailyRecordServiceImpl(@Qualifier("generalAccessor") GeneralAccessor accessor, LogService logService,
                                          OrganizationService organizationService ,
                                          ServletService servletService ,
                                          SoundRecordService soundRecordService
    ) {
        this.accessor = accessor;
        this.logService = logService;
        this.organizationService = organizationService;
        this.servletService = servletService ;
        this.soundRecordService = soundRecordService ;
    }

    /**
     * {@inheritDoc}
     *
     * @see DispatchDailyRecordService#saveDispatchDailyRecord(DispatchDailyRecordSaveInputInfo)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public DispatchDailyRecordBean saveDispatchDailyRecord(DispatchDailyRecordSaveInputInfo inputInfo) {
        if (inputInfo == null) {
            logService.infoLog(logger, "service", "saveDispatchDailyRecord", "DispatchDailyRecordSaveInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveDispatchDailyRecord", "service is started...");
            Long logStart = System.currentTimeMillis();

            DispatchDailyRecordEntity dispatchDailyRecordEntity = HandleDispatchTransformUtil.transform(inputInfo);

            if (null != dispatchDailyRecordEntity) {

                logService.infoLog(logger, "repository", "save(dbDispatchDailyRecordEntity)", "repository is started...");
                Long start = System.currentTimeMillis();

                accessor.save(dispatchDailyRecordEntity);

                Long end = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbDispatchDailyRecordEntity)", String.format("repository is finished,execute time is :%sms", end - start));

            }

            DispatchDailyRecordBean res = HandleDispatchTransformUtil.transform(dispatchDailyRecordEntity);

            if( res != null && Strings.isNotBlank( res.getRelayRecordNumber() ) ){
                //保存录音记录
                SoundRecordBean soundRecord = new SoundRecordBean() ;
                soundRecord.setId( res.getId() );
                soundRecord.setIncidentId( res.getIncidentId());
                soundRecord.setType( 3 ); //处警录音
                soundRecord.setRecordId( res.getRelayRecordNumber() );
                soundRecord.setRecordNo( res.getRelayRecordNumber() );
                soundRecord.setFilename( null  );
                soundRecord.setSeatNumber( res.getSeatNumber() );
                soundRecord.setCallerId( res.getCallingNumber() );
                soundRecord.setCalledId( res.getCalledNumber() );
                soundRecord.setNumbers( null  );
                soundRecord.setBeginTime( servletService.getSystemTime() );
                soundRecord.setRemark( res.getRemarks() );
                SoundRecordBean soundRecordBean  =  soundRecordService.saveSoundRecord( soundRecord ) ;
            }


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveDispatchDailyRecord", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveDispatchDailyRecord", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DISPATCH_DAILY_RECORD_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see DispatchDailyRecordService#saveSMS(SendSMSSaveInputInfo)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SendSMSBean saveSMS(SendSMSSaveInputInfo inputInfo) {
        if (inputInfo == null) {
            logService.infoLog(logger, "service", "saveSMS", "SendSMSSaveInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveSMS", "service is started...");
            Long logStart = System.currentTimeMillis();

            SendSMSEntity sendSMSEntity = HandleDispatchTransformUtil.transform(inputInfo ,  servletService.getSystemTime() );

            if (null != sendSMSEntity) {

                logService.infoLog(logger, "repository", "save(dbSendSMSEntity)", "repository is started...");
                Long start = System.currentTimeMillis();

                accessor.save(sendSMSEntity);

                Long end = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbSendSMSEntity)", String.format("repository is finished,execute time is :%sms", end - start));

            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveSMS", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            SendSMSBean res = HandleDispatchTransformUtil.transform(sendSMSEntity, organizationService.findOrganizationNameMap());

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveSMS", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_SMS_FAIL);
        }
    }
}
