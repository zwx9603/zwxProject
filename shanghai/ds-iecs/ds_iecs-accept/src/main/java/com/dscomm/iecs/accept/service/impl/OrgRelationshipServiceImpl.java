package com.dscomm.iecs.accept.service.impl;


import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.service.OrgRelationshipService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.dal.po.OrgRelationshipEntity;
import com.dscomm.iecs.basedata.dal.repository.OrgRelationshipRepository;
import com.dscomm.iecs.basedata.graphql.inputbean.OrgRelationshipSaveInputInfo;
import com.dscomm.iecs.basedata.graphql.typebean.OrgRelationshipBean;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.basedata.utils.OtherResourceTransformUtil;
import org.apache.logging.log4j.util.Strings;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 消防转警机构关系  服务层
 */
@Component("orgRelationshipServiceImpl")
public class OrgRelationshipServiceImpl implements OrgRelationshipService {
    private static final Logger logger = LoggerFactory.getLogger(OrgRelationshipServiceImpl.class);
    private OrgRelationshipRepository orgRelationshipRepository;
    private LogService logService;
    private GeneralAccessor accessor;

    private DictionaryService dictionaryService ;
    private OrganizationService organizationService ;

    private List<String> dics;

    @Autowired
    public OrgRelationshipServiceImpl(OrgRelationshipRepository orgRelationshipRepository,
                                      LogService logService,DictionaryService dictionaryService ,  OrganizationService organizationService ,
                                      @Qualifier("generalAccessor") GeneralAccessor accessor) {
        this.orgRelationshipRepository = orgRelationshipRepository;
        this.logService = logService;
        this.accessor = accessor;
        this.dictionaryService = dictionaryService ;
        this.organizationService = organizationService ;

        dics = new ArrayList<>(Arrays.asList(  "QQXZLX"   ));
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public OrgRelationshipBean saveOrgRelationship(  OrgRelationshipSaveInputInfo inputInfo) {
        try {
            logService.infoLog(logger, "service", "saveOrgRelationship", "service is started...");
            Long start = System.currentTimeMillis();

            OrgRelationshipBean res = null ;

            OrgRelationshipEntity saveEntity  = OtherResourceTransformUtil.transform( inputInfo ) ;

            if( saveEntity != null ){


                logService.infoLog(logger, "repository", "findOrgRelationship", "repository is started...");
                Long startDao = System.currentTimeMillis();

                OrgRelationshipEntity tempEntity = orgRelationshipRepository.findOrgRelationship( inputInfo.getFireDeptId() , inputInfo.getTransferType() ) ;

                Long endDao = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "findOrgRelationship", String.format("repository is finished,execute time is :%sms", endDao - startDao));


                if( tempEntity != null ){
                    saveEntity.setId( tempEntity.getId() );
                }

                logService.infoLog(logger, "repository", "save(dbOrgRelationshipEntity)", "repository is started...");
                Long start2 = System.currentTimeMillis();

                accessor.save(tempEntity);

                Long end2 = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbOrgRelationshipEntity)", String.format("repository is finished,execute time is :%sms", end2 - start2));


                Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap() ;
                Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap( dics ) ;

                res = OtherResourceTransformUtil.transform(saveEntity , dicsMap , organizationNameMap ) ;
            }


            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "serivce", "save(OrgRelationshipSaveInputInfo)", String.format("repository is finished,execute time is :%sms", end - start));
            return res ;
        } catch (Exception e) {
            logService.erorLog(logger, "service", "saveOrgRelationship", "execution error", e);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DATA_FAIL);
        }
    }



    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteOrgRelationship(@NotNull List<String> ids) {
        try {
            logService.infoLog(logger, "service", "deleteOrgRelationship", "service is started...");
            Long start = System.currentTimeMillis();

            List<OrgRelationshipEntity> orgRelationshipEntityList = new ArrayList<>( );

            if(ids != null && ids.size() > 0 && ids.size() <= 900 ){
                orgRelationshipEntityList = orgRelationshipRepository.findOrgRelationship(ids);
            }else if(  ids != null && ids.size()  > 900 ){
                int page = ( int ) Math.ceil( ids.size() / 900.0 ) ;
                for( int i = 0 ; i < page ; i++ ){
                    int startnum = i * 900 ;
                    int endnum = ( i + 1 ) * 900 ;
                    if( endnum > ids.size() ){
                        endnum = ids.size() ;
                    }
                    List<String>  batchIds = ids.subList( startnum , endnum ) ;
                    List<OrgRelationshipEntity> bathOrgRelationshipEntityList = orgRelationshipRepository.findOrgRelationship(batchIds);
                    if( bathOrgRelationshipEntityList != null && bathOrgRelationshipEntityList.size() > 0 ){
                        orgRelationshipEntityList.addAll( bathOrgRelationshipEntityList ) ;
                    }
                }
            }

            if( orgRelationshipEntityList != null && orgRelationshipEntityList.size() > 0 ){
                for ( OrgRelationshipEntity orgRelationshipEntity : orgRelationshipEntityList ){
                    orgRelationshipEntity.setValid( false );
                }
                accessor.save(orgRelationshipEntityList);
            }

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "serivce", "deleteOrgRelationship", String.format("repository is finished,execute time is :%sms", end - start));

            return true;
        } catch (Exception e) {
            logService.erorLog(logger, "service", "deleteOrgRelationship", "execution error", e);
            throw new AcceptException(AcceptException.AccetpErrors.DELETE_DATA_FAIL);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<OrgRelationshipBean> findOrgRelationshipList( String fireDeptId ) {
        logService.infoLog(logger, "service", "findOrgRelationshipList", "service is started...");
        Long start = System.currentTimeMillis();
        List<OrgRelationshipBean> list = null;
        try {
            List<OrgRelationshipEntity> orgRelationshipEntityList = null ;
            if( Strings.isBlank( fireDeptId ) ){
                orgRelationshipEntityList = orgRelationshipRepository.findOrgRelationshipAll();
            }else{
                orgRelationshipEntityList = orgRelationshipRepository.findOrgRelationshipAll(fireDeptId);
            }

            list = new ArrayList<>();
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap() ;
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics ) ;
            if( orgRelationshipEntityList != null && orgRelationshipEntityList.size() > 0 ){
                for (OrgRelationshipEntity entity : orgRelationshipEntityList) {
                    list.add(OtherResourceTransformUtil.transform(entity , dicsMap , organizationNameMap ));
                }
            }

        } catch (Exception e) {
            logService.erorLog(logger, "service", "findOrgRelationshipList", "execution error", e);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_FAIL);
        }
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "serivce", "findOrgRelationshipList", String.format("service is finished,execute time is :%sms", end - start));
        return list;
    }


    @Transactional(readOnly = true)
    @Override
    public  OrgRelationshipBean  findOrgRelationship(  String fireDeptId  , String   transferType ) {
        try {
            logService.infoLog(logger, "service", "findOrgRelationship", "service is started...");
            Long start = System.currentTimeMillis();

            OrgRelationshipBean res = new OrgRelationshipBean() ;

            OrgRelationshipEntity  orgRelationshipEntity  = orgRelationshipRepository.findOrgRelationship( fireDeptId , transferType ) ;

            if( orgRelationshipEntity != null ){
                Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap() ;
                Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics ) ;
                res  = OtherResourceTransformUtil.transform(orgRelationshipEntity , dicsMap , organizationNameMap ) ;
            }

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "serivce", "findOrgRelationship", String.format("service is finished,execute time is :%sms", end - start));

            return  res ;
        } catch (Exception e) {
            logService.erorLog(logger, "service", "findOrgRelationship", "execution error", e);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_FAIL);
        }

    }




}
