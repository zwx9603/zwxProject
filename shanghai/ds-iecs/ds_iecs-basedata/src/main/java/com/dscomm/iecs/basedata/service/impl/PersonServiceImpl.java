package com.dscomm.iecs.basedata.service.impl;


import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.dal.po.PersonEntity;
import com.dscomm.iecs.basedata.dal.po.PersonMailEntity;
import com.dscomm.iecs.basedata.dal.repository.PersonRepository;
import com.dscomm.iecs.basedata.exception.BasedataException;
import com.dscomm.iecs.basedata.graphql.typebean.PersonBean;
import com.dscomm.iecs.basedata.graphql.typebean.PersonMailBean;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.basedata.service.PersonService;
import com.dscomm.iecs.basedata.utils.PersonTransformUtil;
import org.apache.logging.log4j.util.Strings;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 描述：人员 服务类实现
 */
@Component("personServiceImpl")
public class PersonServiceImpl implements PersonService {

    private static final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);
    private Environment env;
    private LogService logService;
    private GeneralAccessor accessor;
    private PersonRepository personRepository;
    private DictionaryService dictionaryService ;
    private OrganizationService organizationService ;

    private List<String> dics;


    /**
     * 默认的构造函数
     */
    @Autowired
    public PersonServiceImpl(Environment env, @Qualifier("generalAccessor") GeneralAccessor accessor,
                             LogService logService, PersonRepository personRepository , DictionaryService dictionaryService ,
                             OrganizationService organizationService

    ) {
        this.accessor = accessor;
        this.env = env;
        this.logService = logService;
        this.personRepository = personRepository;
        this.dictionaryService = dictionaryService ;
        this.organizationService = organizationService ;

        dics = new ArrayList<>(Arrays.asList("XB", "MZ", "JG", "ZZMM", "XL", "XW", "ZJLY", "HYZK", "RYLB",
                "ZWQK", "GW", "ZYJSZW", "ZW", "ZJLX", "JX", "RYZWQK", "RYZK" ));
    }


    /**
     * {@inheritDoc}
     *
     * @see PersonService#findPersonMail(String)
     */
    @Transactional(readOnly = true)
    @Override
    public PersonMailBean findPersonMail(String personId) {
        if (Strings.isBlank(personId)) {
            logService.infoLog(logger, "service", "findPersonMail", "personId is blank");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findPersonMail", "service is started...");
            Long logStart = System.currentTimeMillis();
            PersonMailBean res = new PersonMailBean();
            logService.infoLog(logger, "repository", "findPersonMailByPersonId", "repository is started...");
            Long start = System.currentTimeMillis();

            PersonMailEntity personMailEntity = personRepository.findPersonMailByPersonId(personId);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findPersonMailByPersonId", String.format("repository is finished,execute time is :%sms", end - start));

            if (null != personMailEntity) {
                res = PersonTransformUtil.transform(personMailEntity);
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findPersonMailByPersonId", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findPersonMailByPersonId", String.format("force cache Dictionary fail "), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_PERSON_MAIL_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     */
    @Transactional(readOnly = true)
    @Override
    public  List<PersonMailBean> findPersonMailCondition (String organizationId , String keyword ){
        if (Strings.isBlank(organizationId)) {
            logService.infoLog(logger, "service", "findPersonMailCondition", "organizationId is blank");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findPersonMailCondition", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<PersonMailBean>  res  = new ArrayList<>() ;

            List<PersonMailEntity>  personMailEntityList = null ;

            logService.infoLog(logger, "repository", "findPersonMailCondition", "repository is started...");
            Long start = System.currentTimeMillis();

            if( Strings.isNotBlank( keyword ) ){
                personMailEntityList = personRepository.findPersonMailByOrganizationIdAndKeyword( organizationId , "%" + keyword + "%") ;
            }else{
                personMailEntityList = personRepository.findPersonMailByOrganizationId( organizationId ) ;
            }

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findPersonMailCondition", String.format("repository is finished,execute time is :%sms", end - start));

            if( personMailEntityList != null && personMailEntityList.size() > 0 ){
                for (PersonMailEntity personMailEntity :   personMailEntityList ){
                    PersonMailBean personMailBean = PersonTransformUtil.transform(personMailEntity);
                    res.add( personMailBean ) ;
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findPersonMailCondition", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findPersonMailCondition", String.format("finPersonMail Condition fail "), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_PERSON_MAIL_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see #findPersonCondition(String , String)
     */
    @Transactional(readOnly = true)
    @Override
    public List<PersonBean> findPersonCondition (String organizationId , String keyword ) {
        if (Strings.isBlank(organizationId)) {
            logService.infoLog(logger, "service", "findPersonCondition", "organizationId is blank");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findPersonCondition", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<PersonBean>  personBeanList = new ArrayList<>() ;

            List<PersonEntity>  personEntityList = null ;

            logService.infoLog(logger, "repository", "findPerson", "repository is started...");
            Long start = System.currentTimeMillis();

            if( Strings.isNotBlank( keyword ) ){
                personEntityList = personRepository.findPersonByOrganizationIdAndKeyword( organizationId , "%" + keyword + "%") ;
            }else{
                personEntityList = personRepository.findPersonByOrganizationId( organizationId ) ;
            }

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findPerson", String.format("repository is finished,execute time is :%sms", end - start));

            if( personEntityList != null && personEntityList.size() > 0 ){
                // 获取字典表中机构类型名称的信息
                Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;
                Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();
                for (PersonEntity personEntity :   personEntityList ){
                    PersonBean personBean = PersonTransformUtil.transform( personEntity , dicsMap , organizationNameMap ) ;
                    personBeanList.add( personBean ) ;
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findPersonCondition", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return personBeanList;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findPersonCondition", String.format("force cache Dictionary fail "), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_PERSON_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see #findPerson(String )
     */
    @Transactional(readOnly = true)
    @Override
    public PersonBean findPerson(String personId) {
        if ( Strings.isBlank(personId) ) {
            logService.infoLog(logger, "service", "findPerson", "personId is blank");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findPerson", "service is started...");
            Long logStart = System.currentTimeMillis();

            PersonBean   res = null  ;

            List<PersonEntity>  personEntityList = null ;

            logService.infoLog(logger, "repository", "getById(dbPersonEntity)", "repository is started...");
            Long start = System.currentTimeMillis();

            PersonEntity personEntity = accessor.getById( personId , PersonEntity.class ) ;

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "getById(dbPersonEntity)", String.format("repository is finished,execute time is :%sms", end - start));

            if( personEntity != null && personEntity.isValid() ){
                // 获取字典表中机构类型名称的信息
                Map<String, Map<String, String>> dicsMap  = dictionaryService.findDictionaryMap(dics) ;
                Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();
                res = PersonTransformUtil.transform( personEntity , dicsMap , organizationNameMap ) ;

            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findPersonCondition", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findPersonCondition", String.format("force cache Dictionary fail "), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_PERSON_FAIL);
        }
    }


}