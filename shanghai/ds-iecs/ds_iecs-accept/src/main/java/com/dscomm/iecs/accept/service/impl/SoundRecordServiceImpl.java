package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.SoundRecordEntity;
import com.dscomm.iecs.accept.dal.po.TelephoneEntity;
import com.dscomm.iecs.accept.dal.repository.SoundRecordRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.typebean.SoundRecordBean;
import com.dscomm.iecs.accept.service.SoundRecordService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.graphql.typebean.SystemConfigurationBean;
import com.dscomm.iecs.basedata.service.SystemConfigurationService;
import com.dscomm.iecs.keydata.service.ServletService;
import org.apache.logging.log4j.util.Strings;
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
 *
 */
@Component("soundRecordServiceImpl")
public class SoundRecordServiceImpl  implements SoundRecordService {

    private static final Logger logger = LoggerFactory.getLogger(SoundRecordServiceImpl.class);
    private GeneralAccessor accessor;
    private Environment environment;
    private LogService logService;
    private ServletService servletService ;
    private SystemConfigurationService systemConfigurationService ;
    private SoundRecordRepository soundRecordRepository ;

    @Autowired
    public SoundRecordServiceImpl(@Qualifier("generalAccessor") GeneralAccessor accessor,
                                  LogService logService , Environment environment,
                                  ServletService servletService  ,
                                  SystemConfigurationService systemConfigurationService  ,
                                  SoundRecordRepository soundRecordRepository  ) {
        this.accessor = accessor;
        this.logService = logService;
        this.environment = environment;
        this.servletService = servletService ;
        this.systemConfigurationService = systemConfigurationService ;
        this.soundRecordRepository = soundRecordRepository ;
    }
    /**
     * 上传录音号
     * @param soundRecord
     * @return
     */
    @Transactional( rollbackFor =  Exception.class )
    @Override
    public  SoundRecordBean  saveSoundRecord(SoundRecordBean soundRecord) {
        if ( soundRecord == null || StringUtils.isBlank( soundRecord .getRecordNo() )   )    {
            logService.infoLog(logger, "service", "saveSoundRecord", "soundRecord is empty.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {

            logService.infoLog(logger, "service", "saveSoundRecord", "service is started...");
            Long logStart = System.currentTimeMillis();

            SoundRecordBean res = null ;

            SoundRecordEntity soundRecordEntity =   transform(soundRecord);

            //录音号播放路径
            SystemConfigurationBean systemConfigurationBean = systemConfigurationService.getSystemConfigByConfigType( "recorder" ) ;
            String recorder = "" ;
            if( systemConfigurationBean != null  ){
                recorder = systemConfigurationBean.getConfigValue() ;
            }
            String recorderUrl  = recorder +  soundRecord .getRecordNo() + "&mdc=1";
            soundRecordEntity.setRecordUrl( recorderUrl );

            if( soundRecordEntity != null ){

                logService.infoLog(logger, "repository", "save( dbSoundRecordEntity )", "repository is started...");
                Long start = System.currentTimeMillis();

                accessor.save( soundRecordEntity );

                Long end = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save( dbSoundRecordEntity )", String.format("repository is finished,execute time is :%sms", end - start));

                res =   transform(  soundRecordEntity  ) ;

            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveSoundRecord", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return  res ;
        }catch (Exception ex){
            logService.erorLog(logger, "service", "saveSoundRecord", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DATA_FAIL);
        }
    }


    /**
     * 根据id 录音信息 更新 案件id
     * @return
     */
    @Transactional( rollbackFor =  Exception.class )
    @Override
    public  SoundRecordBean  updateSoundRecordIncidentId ( String soundRecordId , String  incidentId  ) {
        try {

            logService.infoLog(logger, "service", "saveSoundRecord", "service is started...");
            Long logStart = System.currentTimeMillis();

            SoundRecordBean res = null ;

            if( Strings.isNotBlank( soundRecordId ) ){
                SoundRecordEntity soundRecordEntity = accessor.getById( soundRecordId , SoundRecordEntity.class ) ;

                if( soundRecordEntity != null ){

                    soundRecordEntity.setIncidentId( incidentId );

                    logService.infoLog(logger, "repository", "save( dbSoundRecordEntity )", "repository is started...");
                    Long start = System.currentTimeMillis();

                    accessor.save( soundRecordEntity );

                    Long end = System.currentTimeMillis();
                    logService.infoLog(logger, "repository", "save( dbSoundRecordEntity )", String.format("repository is finished,execute time is :%sms", end - start));

                    res =   transform(  soundRecordEntity  ) ;

                }

            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveSoundRecord", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return  res ;
        }catch (Exception ex){
            logService.erorLog(logger, "service", "saveSoundRecord", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DATA_FAIL);
        }
    }



    /**
     * 根据案件ids 查询录音号
     * @return
     */
    @Transactional( rollbackFor =  Exception.class )
    @Override
    public Map<String, List<SoundRecordBean>> findSoundRecordMapByIncidentIdList( List<String> incidentIdList , List<Integer> typeList  ) {
        if ( incidentIdList == null || incidentIdList.size() < 1 ) {
            logService.infoLog(logger, "service", "findSoundRecordMapByIncidentIdList", "incidentIdList   is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {

            logService.infoLog(logger, "service", "findSoundRecordMapByIncidentIdList", "service is started...");
            Long logStart = System.currentTimeMillis();

            Map<String, List<SoundRecordBean> > res =  new HashMap<>();

            logService.infoLog(logger, "repository", "findSoundRecordMapByIncidentIdList", "repository is started...");
            Long start = System.currentTimeMillis();

            List<SoundRecordEntity> incidentSoundRecordList = new ArrayList<>( );
            if( typeList == null || typeList.size() < 1 ){
                if( incidentIdList != null && incidentIdList.size() > 0 && incidentIdList.size() <= 900 ){
                    incidentSoundRecordList = soundRecordRepository.findSoundRecordMapByIncidentIdList(incidentIdList);
                }else if(  incidentIdList != null && incidentIdList.size()  > 900 ){
                    int page = ( int ) Math.ceil( incidentIdList.size() / 900.0 ) ;
                    for( int i = 0 ; i < page ; i++ ){
                        int startnum = i * 900 ;
                        int endnum = ( i + 1 ) * 900 ;
                        if( endnum > incidentIdList.size() ){
                            endnum = incidentIdList.size() ;
                        }
                        List<String>  batchIds = incidentIdList.subList( startnum , endnum ) ;
                        List<SoundRecordEntity> bathEntityList  = soundRecordRepository.findSoundRecordMapByIncidentIdList( batchIds );
                        if (null != bathEntityList && bathEntityList.size() > 0) {
                            incidentSoundRecordList.addAll( bathEntityList ) ;
                        }
                    }
                }
            }else{
                if( incidentIdList != null && incidentIdList.size() > 0 && incidentIdList.size() <= 900 ){
                    incidentSoundRecordList = soundRecordRepository.findSoundRecordMapByIncidentIdList(incidentIdList , typeList );
                }else if(  incidentIdList != null && incidentIdList.size()  > 900 ){
                    int page = ( int ) Math.ceil( incidentIdList.size() / 900.0 ) ;
                    for( int i = 0 ; i < page ; i++ ){
                        int startnum = i * 900 ;
                        int endnum = ( i + 1 ) * 900 ;
                        if( endnum > incidentIdList.size() ){
                            endnum = incidentIdList.size() ;
                        }
                        List<String>  batchIds = incidentIdList.subList( startnum , endnum ) ;
                        List<SoundRecordEntity> bathEntityList  = soundRecordRepository.findSoundRecordMapByIncidentIdList( batchIds , typeList  );
                        if (null != bathEntityList && bathEntityList.size() > 0) {
                            incidentSoundRecordList.addAll( bathEntityList ) ;
                        }
                    }
                }
            }

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findSoundRecordMapByIncidentIdList", String.format("repository is finished,execute time is :%sms", end - start));

            res = transformSoundRecordMap( incidentSoundRecordList  ) ;

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findSoundRecordMapByIncidentIdList", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res ;

        }catch (Exception ex){
            logService.erorLog(logger, "service", "saveSoundRecord", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DATA_FAIL);
        }
    }

    /**
     * 转换查询结果为map(id : soundRecordList)
     *
     * @param incidentSoundRecordList 查询结果
     * @return map(id : soundRecordList)
     */
    private Map<String, List<SoundRecordBean>> transformSoundRecordMap(List<SoundRecordEntity> incidentSoundRecordList   ) {
        Map<String, List<SoundRecordBean>> resultMap = new HashMap<>();
        if (null != incidentSoundRecordList && incidentSoundRecordList.size() > 0) {
            for ( SoundRecordEntity source  : incidentSoundRecordList) {
                SoundRecordBean target  = transform( source ) ;
                String  incidentId  = target.getIncidentId() ;
                List<SoundRecordBean> soundRecordBeans = resultMap.get( incidentId ) ;
                if( soundRecordBeans == null ){
                    soundRecordBeans = new ArrayList<>( ) ;
                }
                soundRecordBeans.add( target ) ;
                resultMap.put( incidentId , soundRecordBeans ) ;
            }
        }
        return resultMap;
    }


    /**
     * 录音号保存
     *
     **/
    public SoundRecordEntity transform( SoundRecordBean source  ){
        if ( source != null){
            SoundRecordEntity target = new SoundRecordEntity();
            target.setId( source.getId() );
            target.setIncidentId(source.getIncidentId());
            target.setType( source.getType() );
            target.setRecordId( source.getRecordId() );
            target.setRecordNo( source.getRecordNo() );
            target.setFilename( source.getFilename() );
            target.setSeatNumber( source.getSeatNumber() );
            target.setCallerId( source.getCallerId() );
            target.setCalledId( source.getCalledId() );
            target.setNumbers( source.getNumbers() );
            target.setBeginTime( source.getBeginTime() );
            target.setRemark( source.getRemark() );
            return  target ;
        }
        return null ;
    }

    /**
     * 录音号保存
     *
     **/
    public SoundRecordBean   transform(  SoundRecordEntity source  ){
        if ( source != null){
            SoundRecordBean target = new SoundRecordBean();
            target.setId( target.getId() );
            target.setIncidentId(source.getIncidentId());
            target.setType( source.getType() );
            target.setRecordId( source.getRecordId() );
            target.setRecordNo( source.getRecordNo() );
            target.setFilename( source.getFilename() );
            target.setRecordUrl( source.getRecordUrl() );
            target.setSeatNumber( source.getSeatNumber() );
            target.setCalledId( source.getCalledId() );
            target.setCalledId( source.getCalledId() );
            target.setNumbers( source.getNumbers() );
            target.setBeginTime( source.getBeginTime() );
            target.setRemark( source.getRemark() );
            return  target ;
        }
        return null ;
    }

}
