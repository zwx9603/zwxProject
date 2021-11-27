package com.dscomm.iecs.basedata.service.impl;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.dal.po.AttachmentEntity;
import com.dscomm.iecs.basedata.dal.repository.AttachmentRepository;
import com.dscomm.iecs.basedata.exception.BasedataException;
import com.dscomm.iecs.basedata.graphql.inputbean.AttachmentSaveInputInfo;
import com.dscomm.iecs.basedata.graphql.typebean.AttachmentBean;
import com.dscomm.iecs.basedata.service.AttachmentService;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.basedata.service.UploadService;
import com.dscomm.iecs.ext.AttachmentTypeBakEnum;
import com.dscomm.iecs.keydata.service.ServletService;
import org.apache.logging.log4j.util.Strings;
import org.mx.StringUtils;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 描述：附件信息 服务类实现
 */
@Component("attachmentServiceImpl")
public class AttachmentServiceImpl implements AttachmentService {
    private static final Logger logger = LoggerFactory.getLogger(AttachmentServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private Environment env;
    private AttachmentRepository attachmentRepository ;
    private OrganizationService organizationService;
    private UploadService uploadService ;
    private ServletService servletService ;

    private DictionaryService dictionaryService ;




    @Value("${fdfsFilePathUrlEnable:false}")
    private String  fdfsFilePathUrlEnable;

    private List<String> dics;

    /**
     * 默认的构造函数
     */
    @Autowired
    public AttachmentServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor, Environment env ,
                                 AttachmentRepository attachmentRepository , OrganizationService organizationService , UploadService uploadService ,

                                 ServletService servletService,DictionaryService dictionaryService


    ) {
        this.logService = logService;
        this.accessor = accessor;
        this.env = env;
        this.attachmentRepository = attachmentRepository ;
        this.organizationService = organizationService ;
        this.uploadService = uploadService ;
        this.servletService = servletService ;

        this.dictionaryService = dictionaryService ;

        dics = new ArrayList<>(Arrays.asList( "FJLX","TPGSLX"   ));

    }

    /**
     * {@inheritDoc}
     *
     * @see AttachmentService#findAttachment(String , String )
     */
    @Transactional(readOnly = true)
    @Override
    public  List<AttachmentBean> findAttachment(String incidentId , String relationId ) {
        if ( Strings.isBlank( incidentId) && Strings.isBlank( relationId)  ) {
            logService.infoLog(logger, "service", "findAttachment", "incidentId and relationId  is null.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findAttachment", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<AttachmentBean> res = new ArrayList<>();
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap() ;
            Map<String, Map<String, String>> dicsMap  = dictionaryService.findDictionaryMap(dics) ;

            List<AttachmentEntity> attachmentEntityList = null ;

            logService.infoLog(logger, "repository", "findAttachment( incidentId , relationId  )", "repository is started...");
            Long start = System.currentTimeMillis();
            if( Strings.isNotBlank ( relationId )){
                attachmentEntityList = attachmentRepository.findAttachmentByRelationId  ( relationId ) ;
            }else{
                attachmentEntityList = attachmentRepository.findAttachmentByIncidentId ( incidentId  ) ;
            }

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findAttachment( incidentId , relationId  )", String.format("repository is finished,execute time is :%sms", end - start));

            if( null != attachmentEntityList && attachmentEntityList.size() > 0  ){

                for( AttachmentEntity attachmentEntity : attachmentEntityList ){

                    AttachmentBean attachmentBean = transform( attachmentEntity ,dicsMap, organizationNameMap )  ;


                    res.add( attachmentBean ) ;
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findAttachment", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findAttachment", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_DATA_FAIL);
        }

    }



    /**
     * {@inheritDoc}
     *
     * @see #findAttachmentByRelationIds(List , String )
     */
    @Transactional(readOnly = true)
    @Override
    public   Map<String , List<AttachmentBean> > findAttachmentByRelationIds(List<String> relationIds , String relationObject  ){
        if (  relationIds == null ||  relationIds.size() < 1 ) {
            logService.infoLog(logger, "service", "findAttachmentByRelationIds", "relationIds is null.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findAttachmentByRelationIds", "service is started...");
            Long logStart = System.currentTimeMillis();

            Map<String , List<AttachmentBean> >  res = new HashMap<>( );


            Map<String, Map<String, String>> dicsMap  = dictionaryService.findDictionaryMap(dics) ;
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap() ;

            List<AttachmentEntity> attachmentEntityList = null ;

            logService.infoLog(logger, "repository", "findAttachment( relationIds , relationObject  )", "repository is started...");
            Long start = System.currentTimeMillis();
            if( Strings.isBlank( relationObject )){
                attachmentEntityList = attachmentRepository.findAttachmentByRelationIds( relationIds ) ;
            }else{
                attachmentEntityList = attachmentRepository.findAttachmentByRelationIds( relationIds , relationObject  ) ;
            }

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findAttachment( relationIds , relationObject  )", String.format("repository is finished,execute time is :%sms", end - start));

            if( null != attachmentEntityList && attachmentEntityList.size() > 0  ){

                for( AttachmentEntity attachmentEntity : attachmentEntityList ){

                    AttachmentBean attachmentBean = transform( attachmentEntity ,dicsMap, organizationNameMap )  ;

                    List<AttachmentBean> beans = res.get( attachmentBean.getRelationId() ) ;
                    if( beans == null ){
                        beans = new ArrayList<>() ;
                    }
                    beans.add( attachmentBean ) ;
                    res.put( attachmentBean.getRelationId() , beans  ) ;
                }

            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findAttachmentByRelationIds", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findAttachmentByRelationIds", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_DATA_FAIL);
        }

    }


    /**
     * {@inheritDoc}
     *
     */
    @Transactional( rollbackFor =  Exception.class )
    @Override
    public  AttachmentBean saveAttachment( AttachmentSaveInputInfo queryBean ) {
        if ( null == queryBean || Strings.isBlank( queryBean.getIncidentId() ) ) {
            logService.infoLog(logger, "service", "saveAttachment", "queryBean or relationId is null.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveAttachment", "service is started...");
            Long logStart = System.currentTimeMillis();

            AttachmentBean res = null  ;

            Map<String, Map<String, String>> dicsMap  = dictionaryService.findDictionaryMap(dics) ;
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap() ;

            Map<String, String>  tpgsMap = dicsMap.get( "TPGSLX") ;
            Map<String, String>  tpgsNameCodeMap  =  new HashMap<>()  ;
            if( tpgsMap != null && tpgsMap.size() > 0 ){
                for( String key :  tpgsMap.keySet() ){
                    String name = tpgsMap.get(key) ;
                    if( Strings.isNotBlank( name )){
                        tpgsNameCodeMap.put( name.toLowerCase() , key ) ;
                    }else{
                        tpgsNameCodeMap.put( name  , key ) ;
                    }
                }
            }
            AttachmentEntity attachmentEntity  =  transform( queryBean , tpgsNameCodeMap  ) ;
            attachmentEntity.setUploadTime( servletService.getSystemTime() );

            logService.infoLog(logger, "repository", "save(dbAttachmentEntity)", "repository is started...");
            Long start = System.currentTimeMillis();

            accessor.save( attachmentEntity ) ;

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbAttachmentEntity)", String.format("repository is finished,execute time is :%sms", end - start));



            res = transform( attachmentEntity ,dicsMap, organizationNameMap )  ;



            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveAttachment", String.format("service is finished,execute time is :%sms", logEnd - logStart));


            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveAttachment", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.SAVE_DATA_FAIL);
        }

    }



    /**
     * {@inheritDoc}
     *
     * @see #saveAttachmentList(List)
     */
    @Transactional( rollbackFor =  Exception.class )
    @Override
    public List<AttachmentBean> saveAttachmentList(List<AttachmentSaveInputInfo> sourceList ) {
        if ( null == sourceList || sourceList.size() < 1  ) {
            logService.infoLog(logger, "service", "saveAttachmentList", "sourceList is null");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveAttachmentList", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<AttachmentBean> res = new ArrayList<>();

            List<AttachmentEntity> attachmentEntityList = new ArrayList<>() ;

            // 查询出所有需要用到的字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;
            Map<String, String>  tpgsMap = dicsMap.get( "TPGSLX") ;
            Map<String, String>  tpgsNameCodeMap  =  new HashMap<>()  ;
            if( tpgsMap != null && tpgsMap.size() > 0 ){
                for( String key :  tpgsMap.keySet() ){
                    String name = tpgsMap.get(key) ;
                    if( Strings.isNotBlank( name )){
                        tpgsNameCodeMap.put( name.toLowerCase() , key ) ;
                    }else{
                        tpgsNameCodeMap.put( name  , key ) ;
                    }
                }
            }

            for (AttachmentSaveInputInfo queryBean : sourceList) {

                AttachmentEntity attachmentEntity  = transform( queryBean , tpgsNameCodeMap   ) ;
                if (null != attachmentEntity) {
                    attachmentEntity.setUploadTime( servletService.getSystemTime() );
                    attachmentEntityList.add ( attachmentEntity );
                }
            }

            if( attachmentEntityList != null && attachmentEntityList.size() > 0 ){

                logService.infoLog(logger, "repository", "save(dbAttachmentEntityList)", "repository is started...");
                Long start = System.currentTimeMillis();

                accessor.save(attachmentEntityList);

                Long end = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbAttachmentEntityList)", String.format("repository is finished,execute time is :%s ms", end - start));

                Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap() ;


                for( AttachmentEntity attachmentEntity : attachmentEntityList ){

                    AttachmentBean attachmentBean = transform( attachmentEntity ,dicsMap, organizationNameMap )  ;

                    res.add( attachmentBean ) ;
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveAttachmentList", String.format("service is finished,execute time is :%s ms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveAttachmentList", "save attachment list fail.", ex);
            throw new BasedataException(BasedataException.BasedataErrors.SAVE_DATA_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see #deleteAttachmentById(String)
     */
    @Transactional( rollbackFor =  Exception.class )
    @Override
    public AttachmentBean deleteAttachmentById(String id) {
        if (StringUtils.isBlank(id)) {
            logService.infoLog(logger, "service", "deleteAttachmentById", "id is blank.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "deleteAttachmentById", "service is started...");
            Long logStart = System.currentTimeMillis();


            AttachmentBean res = null  ;

            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap() ;
            Map<String, Map<String, String>> dicsMap  = dictionaryService.findDictionaryMap(dics) ;

            logService.infoLog(logger, "repository", "findById(id)", "repository is started...");
            Long start = System.currentTimeMillis();

            AttachmentEntity attachmentEntity = accessor.getById( id , AttachmentEntity.class ) ;

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findById(id)", String.format("repository is finished,execute time is :%s ms", end - start));

            if ( attachmentEntity == null ) {
                logService.infoLog(logger, "service", "deleteAttachmentById", String.format("can not find attachment from db by id[%s].", id));
                throw new BasedataException(BasedataException.BasedataErrors.FIND_DATA_NULL);
            }

            res = transform( attachmentEntity ,dicsMap, organizationNameMap )  ;


            logService.infoLog(logger, "repository", "deleteById(id)", "repository is started...");
            start = System.currentTimeMillis();

            attachmentEntity.setValid( false );
            accessor.save( attachmentEntity ) ;

            end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "deleteById(id)", String.format("repository is finished,execute time is :%s ms", end - start));

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "deleteAttachmentById", String.format("service is finished,execute time is :%s ms", logEnd - logStart));

            return res ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "deleteAttachmentById", String.format("delete attachment by id[%s] fail.", id), ex);
            throw new BasedataException(BasedataException.BasedataErrors.DELETE_DATA_FAIL);
        }
    }


    /**
     *  附件信息 转换
     * @param source 附件信息INPUT
     * @return 附件信息PO
     */
    public  AttachmentEntity transform(AttachmentSaveInputInfo source ,    Map<String, String>  tpgsNameCodeMap   ) {
        if(null!=source){
            AttachmentEntity target=new AttachmentEntity();
            target.setId( source.getId() );
            target.setIncidentId(source.getIncidentId());
            target.setRelationId( source.getRelationId() );
            target.setRelationObject( source.getRelationObject()  );
            target.setAttachmentFileName(source.getAttachmentFileName());
            target.setAttachmentFileUrl(source.getAttachmentFileUrl());
            target.setAttachmentFileDesc(source.getAttachmentFileDesc());
            target.setOrganizationId(source.getOrganizationId());
            target.setAcceptancePersonNumber(source.getAcceptancePersonNumber());
            target.setSeatNumber( source.getSeatNumber() );
            target.setRemarks(source.getRemarks());
            target.setAttachmentSuffix( source.getAttachmentType() );

            //处理
            buildAttachment( target , source.getAttachmentType() , tpgsNameCodeMap ) ;

            return target;
        }
        return null;
    }


    //根据附件后缀判断是否附件类 电子图片
    private  void  buildAttachment(  AttachmentEntity target , String attachmentSuffix  ,  Map<String, String>  tpgsNameCodeMap ){
        attachmentSuffix = attachmentSuffix.toLowerCase() ;
        //视频后缀
        List<String> videoList = new ArrayList<>(Arrays.asList( "avi","flv","mpg","mpeg","mpe","m1v","m2v","mpv2","mp2v","dat","ts","tp","tpr","pva","pss","mp4","m4v",
                "m4p","m4b","3gp","3gpp","3g2","3gp2","ogg","mov","qt","amr","rm","ram","rmvb","rpm"  ));
        //图片后缀
        List<String> pictureList = new ArrayList<>(Arrays.asList( "webp","bmp","pcx","tif","gif","jpeg","jpg","tga","exif","fpx","svg","psd","cdr","pcd","dxf","ufo","eps","ai",
                "png", "hdri","wmf","flic","emf","ico" ));
        //文件后缀
        List<String> wordList = new ArrayList<>(Arrays.asList( "xls","docx","doc","xlsx"  ));

        if( videoList.contains( attachmentSuffix )){
            target.setAttachmentType(  AttachmentTypeBakEnum.ATTACHMENT_TYPE_SP.getCode() );
        }else if( pictureList.contains( attachmentSuffix ) ){
            target.setAttachmentType(  AttachmentTypeBakEnum.ATTACHMENT_TYPE_TP.getCode() );
            target.setAttachmentFileType( tpgsNameCodeMap.get( attachmentSuffix )  );
        }else if(wordList.contains( attachmentSuffix ) ){
            target.setAttachmentType(  AttachmentTypeBakEnum.ATTACHMENT_TYPE_WD.getCode() );
        }else{
            target.setAttachmentType(  AttachmentTypeBakEnum.ATTACHMENT_TYPE_QT.getCode()  );
        }

    }




    /**
     * 附件信息 转换
     *
     * @param source 附件信息PO
     * @return 附件信息BO
     */

    private  AttachmentBean transform(AttachmentEntity source, Map<String, Map<String, String>> dicsMap ,  Map<String, String> organizationNameMap) {

        if (null != source) {
            AttachmentBean target = new AttachmentBean();
            target.setId(source.getId());
            target.setIncidentId(source.getIncidentId());
            target.setRelationObject(source.getRelationObject());
            target.setRelationId(source.getRelationId());

            target.setAttachmentType(source.getAttachmentType());
            target.setAttachmentTypeName(  dicsMap.get("FJLX").get(  source.getAttachmentType()  ) );

            target.setAttachmentFileType(source.getAttachmentFileType());
            target.setAttachmentFileTypeName(  dicsMap.get("TPGSLX").get(  source.getAttachmentFileType()  )   );

            target.setAttachmentFileName(source.getAttachmentFileName());
            target.setAttachmentFileUrl(source.getAttachmentFileUrl());
            target.setAttachmentFileDesc(source.getAttachmentFileDesc());
            target.setOrganizationId(source.getOrganizationId());
            target.setOrganizationName(organizationNameMap.get(target.getOrganizationId()));
            target.setSeatNumber(source.getSeatNumber());
            target.setAcceptancePersonNumber(source.getAcceptancePersonNumber());
            target.setRemarks(source.getRemarks());

            target.setAttachmentSuffix( source.getAttachmentSuffix() );

            target.setUploadTime( source.getUploadTime() );

            //是否需要 根据附件id获得路径
            if( "true".equals( fdfsFilePathUrlEnable ) ){
                String path  =  uploadService.getFilePath( target.getId() ) ;
                target.setAttachmentFileUrl( path );
            }

            return target;
        }
        return null;

    }


}
