package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.AddressEntity;
import com.dscomm.iecs.accept.dal.po.LocationEntity;
import com.dscomm.iecs.accept.dal.po.LocationSMSEntity;
import com.dscomm.iecs.accept.dal.repository.AddressRepository;
import com.dscomm.iecs.accept.dal.repository.LocationRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.IncidentQueryInputInfo;
import com.dscomm.iecs.accept.restful.vo.UserSMSLocationVO;
import com.dscomm.iecs.accept.service.LocationService;
import com.dscomm.iecs.accept.service.bean.LocationBean;
import com.dscomm.iecs.agent.exception.UserInterfaceAgentException;
import com.dscomm.iecs.agent.graphql.typebean.AgentBean;
import com.dscomm.iecs.agent.service.AgentCacheService;
import com.dscomm.iecs.agent.service.UserService;
import com.dscomm.iecs.base.websocket.WebsocketCodeEnum;
import com.dscomm.iecs.base.service.NotifyActionService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;
import com.dscomm.iecs.keydata.service.ServletService;
import org.mx.StringUtils;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述:定位轨迹
 *
 * @author LiLu
 * Date Time 2019/8/14 0014 10:04
 */
@Component
public class LocationServiceImpl implements LocationService {
    private static final Logger logger = LoggerFactory.getLogger(LocationServiceImpl.class);
    private LogService logService;
    private ServletService servletService ;
    private GeneralAccessor accessor;
    private AddressRepository addressRepository;
    private LocationRepository locationRepository;
    private UserService userService ;
    private NotifyActionService notifyActionService;
    private AgentCacheService agentCacheService ;


    @Autowired
    public LocationServiceImpl(
                               LogService logService,ServletService servletService ,@Qualifier("generalAccessor") GeneralAccessor accessor ,
                               AddressRepository addressRepository , LocationRepository locationRepository, NotifyActionService notifyActionService ,
                               UserService userService ,  AgentCacheService agentCacheService
    ) {
        this.logService = logService;
        this.servletService = servletService ;
        this.accessor = accessor ;
        this.addressRepository = addressRepository;
        this.locationRepository = locationRepository;
        this.notifyActionService = notifyActionService;
        this.userService = userService ;
        this.agentCacheService = agentCacheService ;

    }

    /**
     * {@inheritDoc}
     *
     * @see #saveLocation(LocationBean)
     */
    @Transactional
    @Override
    public LocationBean saveLocation(LocationBean locationBO) {
        if (null == locationBO) {
            logService.infoLog(logger, "service", "saveLocation", "locationBO is null.");
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveLocation", "service is started...");
            Long logStart = System.currentTimeMillis();

            Long time = servletService.getSystemTime();
            locationBO.setLocationTime(time);
            LocationEntity locationPO = transform(locationBO);

            logService.infoLog(logger, "repository", "save(dbLocationEntity)", "repository is started...");
            Long start = System.currentTimeMillis();

            accessor.save(locationPO);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbLocationEntity)", String.format("repository is finished,execute time is :%sms", end - start));

            List<AddressEntity> list = addressRepository.findAddressByIncidentIdAndAddressType(locationBO.getIncidentId(),locationBO.getAddressType());
            if(list == null || list.isEmpty()) {
                AddressEntity addressPO = new AddressEntity();
                addressPO.setIncidentId(locationBO.getIncidentId());
                addressPO.setAddress(locationBO.getAddress());
                addressPO.setLongitude(locationBO.getLongitude());
                addressPO.setLatitude(locationBO.getLatitude());
                addressPO.setAddressType(locationBO.getAddressType());
                Long saveTime = servletService.getSystemTime();
                addressPO.setSaveTime(saveTime);
                addressPO.setSjc(saveTime);

                logService.infoLog(logger, "repository", "save(po)", "repository is started...");
                Long saveStart = System.currentTimeMillis();
                addressRepository.save(addressPO);
                Long saveEnd = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(po)", String.format("repository is finished,execute time is :%sms", saveEnd - saveStart));
            }else {
                Long saveTime = servletService.getSystemTime();
                addressRepository.updateAddress(locationBO.getAddress(),locationBO.getLongitude(),locationBO.getLatitude(),saveTime,locationBO.getIncidentId(),locationBO.getAddressType());
            }
            locationBO = transform(locationPO);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveLocation", String.format("service is finished,execute time is :%s ms", logEnd - logStart));

            return locationBO;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveLocation", "save location fail.", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_LOCATION_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see #findLocationByIncidentIdAndTrajectoryType(String, Integer)
     */
    @Transactional(readOnly = true)
    @Override
    public List<LocationBean> findLocationByIncidentIdAndTrajectoryType(String incidentId, Integer trajectoryType) {
        if (StringUtils.isBlank(incidentId) || null == trajectoryType) {
            logService.infoLog(logger, "service", "findLocationByIncidentIdAndTrajectoryType", "incidentId or trajectoryType is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findLocationByIncidentIdAndTrajectoryType", "service is started...");
            Long logStart = System.currentTimeMillis();

            logService.infoLog(logger, "repository", "findLocationByIncidentIdAndTrajectoryType(incidentId, trajectoryType)", "repository is started...");
            Long start = System.currentTimeMillis();

            List<LocationEntity> locationPOList = locationRepository.findLocationByIncidentIdAndTrajectoryType(incidentId, trajectoryType);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findLocationByIncidentIdAndTrajectoryType(incidentId, trajectoryType)", String.format("repository is finished,execute time is :%sms", end - start));

            List<LocationBean> locationBOList = new ArrayList<>();
            if (null != locationPOList && locationPOList.size() > 0) {
                for (LocationEntity locationPO : locationPOList) {
                    LocationBean locationBO = transform(locationPO);
                    if (null != locationBO) {
                        locationBOList.add(locationBO);
                    }
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findLocationByIncidentIdAndTrajectoryType", String.format("service is finished,execute time is :%s ms", logEnd - logStart));

            return locationBOList;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findLocationByIncidentIdAndTrajectoryType", String.format("find location by incidentId[%s] and trajectoryType[%s] fail.", incidentId, trajectoryType), ex);
            throw new AcceptException(AcceptException.AccetpErrors.GET_LOCATION_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see #subSMSLocationResultSendToUser(UserSMSLocationVO)
     */
    @Override
    public  Boolean saveSMSLocation(UserSMSLocationVO addressInfo){
        if (null == addressInfo) {
            logService.infoLog(logger, "service", "saveSMSLocation", "addressInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveSMSLocation", "service is started...");
            Long logStart = System.currentTimeMillis();

            decodeUserSMSLocationVO( addressInfo ) ; //URLDecoder Query转码

            Boolean  res = false ;

            LocationSMSEntity  locationSMSEntity = new LocationSMSEntity();
            locationSMSEntity.setIncidentId( addressInfo.getIncidentId() );
            locationSMSEntity.setPhoneNumber( addressInfo.getPhoneNumber() );
            locationSMSEntity.setUserAccount( addressInfo.getUserAccount() );
            locationSMSEntity.setSmsContent( addressInfo.getSmsContent() );
            Long time = servletService.getSystemTime();
            locationSMSEntity.setSmsSendTime( time );

            UserInfo userInfo =  userService.getUserInfo() ;

            locationSMSEntity.setSendOrganizationId( userInfo.getOrgId() );
            locationSMSEntity.setSendSeatNumber( userInfo.getAgentNum() );
            locationSMSEntity.setSendPersonNumber( userInfo.getAccount() );

            logService.infoLog(logger, "repository", "save(dbLocationSMSEntity)", "repository is started...");
            Long startAcceptance = System.currentTimeMillis();

            accessor.save(locationSMSEntity);

            Long endAcceptance = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbLocationSMSEntity)", String.format("repository is finished,execute time is :%sms", endAcceptance - startAcceptance));


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveSMSLocation", String.format("service is finished,execute time is :%s ms", logEnd - logStart));

            res = true ;

            return res ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveSMSLocation", "send subSMSLocationResult fail.", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_SMSLOCATIONRESULT_FAIL);
        }
    }



    /**
     * {@inheritDoc}
     *
     * @see #subSMSLocationResultSendToUser(UserSMSLocationVO)
     */
    @Override
    public String subSMSLocationResultSendToUser(UserSMSLocationVO addressInfo) {
        if (null == addressInfo) {
            logService.infoLog(logger, "service", "subSMSLocationResultSendToUser", "addressInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "subSMSLocationResultSendToUser", "service is started...");
            Long logStart = System.currentTimeMillis();


            LocationSMSEntity locationSMSEntity = null ;
            //根据用户 电话号码 获得短信发送记录
            Long time = servletService.getSystemTime();
            List<LocationSMSEntity>  locationSMSEntityList = locationRepository.findLocationSMS( addressInfo.getUserAccount() , addressInfo.getPhoneNumber() ,
                    time ) ;
            if( locationSMSEntityList != null &&  locationSMSEntityList.size() > 0 ){
                locationSMSEntity = locationSMSEntityList.get(0) ;
            }else {
                locationSMSEntity = new LocationSMSEntity() ;
            }
            locationSMSEntity.setSmsReceiveTime( time );
            locationSMSEntity.setLongitude( addressInfo.getLongitude() );
            locationSMSEntity.setLatitude(  addressInfo.getLatitude() );
            locationSMSEntity.setAddress( addressInfo.getAddress() );

            logService.infoLog(logger, "repository", "save(dbLocationSMSEntity)", "repository is started...");
            Long startAcceptance = System.currentTimeMillis();

            accessor.save(locationSMSEntity);

            Long endAcceptance = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbLocationSMSEntity)", String.format("repository is finished,execute time is :%sms", endAcceptance - startAcceptance));


            List<String> toUsers = new ArrayList<>();
            AgentBean agentBean = agentCacheService.findAgentByAgentNumber( addressInfo.getUserAccount() ) ;
            if( agentBean != null && agentBean.getPersonBean() != null  ){
                toUsers.add(agentBean.getPersonBean().getAccount() );
            }else{
                toUsers.add(addressInfo.getUserAccount());
            }

            notifyActionService.pushMessageToUsers(WebsocketCodeEnum.SUBSMSLOCATIONRESULT.getCode(), addressInfo, toUsers);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "subSMSLocationResultSendToUser", String.format("service is finished,execute time is :%s ms", logEnd - logStart));

            return "true";
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "subSMSLocationResultSendToUser", "send subSMSLocationResult fail.", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SEND_SMSLOCATIONRESULT_FAIL);
        }
    }

    /**
     * 转化方法 定位轨迹po 转 定位轨迹bo
     *
     * @param source 定位轨迹po
     * @return 定位轨迹bo
     */
    private LocationBean transform(LocationEntity source) {
        if (null != source) {
            LocationBean target = new LocationBean();

            target.setId(source.getId());
            target.setIncidentId(source.getIncidentId());
            target.setAcceptId(source.getAcceptId());
            target.setLocationType(source.getLocationType());
            target.setLocationTypeName("");
            target.setCoordinateType(source.getCoordinateType());
            target.setCoordinateTypeName("");
            target.setLongitude(source.getLongitude());
            target.setLatitude(source.getLatitude());
            target.setLocationTime(source.getLocationTime());
            target.setLocationContent(source.getLocationContent());
            target.setOrder(source.getOrder());
            target.setAddress(source.getAddress());
            target.setTrajectoryType(source.getTrajectoryType());
            if (null != source.getTrajectoryType()) {
                if (1 == source.getTrajectoryType()) {
                    target.setTrajectoryTypeName("案件轨迹");
                }
                if (2 == source.getTrajectoryType()) {
                    target.setTrajectoryTypeName("报警人轨迹");
                }
            }
            target.setFeedbackId(source.getFeedbackId());
            target.setAddressType(source.getAddressType());
            return target;
        }
        return null;
    }

    /**
     * 转化方法 定位轨迹bo 转 定位轨迹po
     *
     * @param source 定位轨迹bo
     * @return 定位轨迹po
     */
    private LocationEntity transform(LocationBean source) {
        if (null != source) {
            LocationEntity target = new LocationEntity();

            target.setId(source.getId());
            target.setIncidentId(source.getIncidentId());
            target.setAcceptId(source.getAcceptId());
            target.setLocationType(source.getLocationType());
            target.setCoordinateType(source.getCoordinateType());
            target.setLongitude(source.getLongitude());
            target.setLatitude(source.getLatitude());
            target.setLocationTime(source.getLocationTime());
            target.setLocationContent(source.getLocationContent());
            target.setOrder(source.getOrder());
            target.setAddress(source.getAddress());
            target.setTrajectoryType(source.getTrajectoryType());
            target.setFeedbackId(source.getFeedbackId());
            target.setAddressType(source.getAddressType());

            return target;
        }
        return null;
    }

    /**
     * IncidentQueryInputInfo转码
     */
    private void decodeUserSMSLocationVO( UserSMSLocationVO   source) {
        if (source != null) {
            try {
                if (!StringUtils.isBlank(source.getSmsContent())) {
                    source.setSmsContent(URLDecoder.decode(source.getSmsContent(), "UTF-8"));
                }
                if (!StringUtils.isBlank(source.getAddress())) {
                    source.setAddress(URLDecoder.decode(source.getAddress(), "UTF-8"));
                }
            } catch (Exception e) {
                throw new AcceptException(AcceptException.AccetpErrors.DECODE_FAIL);
            }
        }
    }
}
