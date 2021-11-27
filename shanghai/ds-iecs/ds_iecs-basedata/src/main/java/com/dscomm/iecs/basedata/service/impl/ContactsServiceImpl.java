package com.dscomm.iecs.basedata.service.impl;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.exception.BasedataException;
import com.dscomm.iecs.basedata.graphql.typebean.ContactNumberBean;
import com.dscomm.iecs.basedata.graphql.typebean.OrganizationBean;
import com.dscomm.iecs.basedata.graphql.typebean.PersonMailBean;
import com.dscomm.iecs.basedata.service.ContactsService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.basedata.service.PersonService;
import com.dscomm.iecs.basedata.service.enums.ContactTypeEnum;
import org.apache.logging.log4j.util.Strings;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 描述：通讯录 服务类实现
 */
@Component("contactsServiceImpl")
public class ContactsServiceImpl implements ContactsService {
    private static final Logger logger = LoggerFactory.getLogger(ContactsServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private OrganizationService organizationService;
    private PersonService personService;

    /**
     * 默认的构造函数
     */
    @Autowired
    public ContactsServiceImpl(@Qualifier("generalAccessor") GeneralAccessor accessor, LogService logService,
                               OrganizationService organizationService, PersonService personService
    ) {
        this.accessor = accessor;
        this.logService = logService;
        this.personService = personService;
        this.organizationService = organizationService;
    }

    /**
     * {@inheritDoc}
     *
     * @see ContactsService#findContactNumber(String, String)
     */
    @Transactional(readOnly = true)
    @Override
    public ContactNumberBean findContactNumber(String contactObjectId, String contactType) {
        if (Strings.isBlank(contactObjectId) || Strings.isBlank(contactType)) {
            logService.infoLog(logger, "service", "findContactNumber", "contactObjectId or contactType is blank.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findContactNumber", "service is started...");
            Long logStart = System.currentTimeMillis();

            ContactNumberBean res = new ContactNumberBean();

            //根据通讯信息类型，判断查询单位 或 人员 的通讯信息
            if (contactType.equals(String.valueOf(ContactTypeEnum.ORGANIZATION.getCode()))) {
                OrganizationBean organizationBean = organizationService.findOrganizationByOrganizationId(contactObjectId);
                if (organizationBean != null) {
                    res.setContactObjectId(organizationBean.getId());
                    res.setContactObjectName(organizationBean.getOrganizationName());
                    res.setDispatchPhone(organizationBean.getDispatchPhone());
                    res.setMailCode(organizationBean.getMailCode());
                    res.setFaxNumber(organizationBean.getFaxNumber());
                    res.setContactPerson(organizationBean.getContactPerson());
                    res.setContactPhone(organizationBean.getContactPhone());
                }
            } else if (contactType.equals(String.valueOf(ContactTypeEnum.PERSON.getCode()))) {
                PersonMailBean personMailBean = personService.findPersonMail(contactObjectId);
                if (personMailBean != null) {
                    res.setContactObjectId(personMailBean.getPersonId());
                    res.setContactObjectName(personMailBean.getPersonName());
                    res.setMobilePhone(personMailBean.getMobilePhone());
                    res.setHomePhone(personMailBean.getHomePhone());
                    res.setOfficePhone(personMailBean.getOfficePhone());
                    res.setMobilePhoneTwo(personMailBean.getMobilePhoneTwo());
                    res.setMobilePhoneThree(personMailBean.getMobilePhoneThree());
                    res.setInternetEmail(personMailBean.getInternetEmail());
                    res.setSecurityEmail(personMailBean.getSecurityEmail());
                    res.setShortNumber(personMailBean.getShortNumber());
                }
            } else {
                logService.infoLog(logger, "service", "findContactNumber", "contactType is not available.");
                throw new BasedataException(BasedataException.BasedataErrors.DATA_FAIL_NULL);
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findContactNumber", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;

        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findContactNumber", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_CONTACT_NUMBER_FAIL);
        }
    }

}
