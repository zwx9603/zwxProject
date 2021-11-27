package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.AddressEntity;
import com.dscomm.iecs.accept.dal.repository.AddressRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.service.AddressService;
import com.dscomm.iecs.accept.service.bean.AddressBean;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.keydata.service.ServletService;
import org.mx.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * 描述:辅助地址
 *
 * @author XuHao
 * Date Time 2020/2/21 15:07
 */
@Component("addressServiceImpl")
public class AddressServiceImpl implements AddressService {
    private static final Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);
    private LogService logService;
    private AddressRepository addressRepository;
    private ServletService servletService ;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository,
                              LogService logService, ServletService servletService ){
        this.addressRepository = addressRepository;
        this.logService = logService;
        this.servletService = servletService;
    }

    /**
     * {@inheritDoc}
     *
     * @see #deleteAddressByIncidentIdAndAddressType(String, Integer)
     */
    @Transactional( rollbackFor =  Exception.class )
    @Override
    public Boolean deleteAddressByIncidentIdAndAddressType(String incidentId, Integer addressType) {
        try {
            logService.infoLog(logger, "service", "deleteAddressByIncidentIdAndAddressType", "start excute sql to delete address");
            Long start = System.currentTimeMillis();

            addressRepository.deleteAddressByIncidentIdAndAddressType(incidentId, addressType);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "service", "deleteAddressByIncidentIdAndAddressType", String.format("finish, execution total time is:%s ms", end - start));
            return true;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "deleteTagCorrespondByIncidentId", String.format("delete address fail，the incidentId is:%s", incidentId), ex);
            throw new AcceptException(AcceptException.AccetpErrors.DELETE_ADDRESSBYINCIDENTIDANDTYPE_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see #saveAddress(AddressBean)
     */
    @Transactional( rollbackFor =  Exception.class )
    @Override
    public AddressBean saveAddress(AddressBean addressBO) {
        if (null == addressBO) {
            logService.infoLog(logger, "service", "saveAddress", "addressBO is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveAddress", "service is started...");
            Long logStart = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(po)", "repository is started...");
            Long start = System.currentTimeMillis();

            List<AddressEntity> list = addressRepository.findAddressByIncidentIdAndAddressType(addressBO.getIncidentId(),addressBO.getAddressType());
            if(list == null || list.isEmpty()) {
                AddressEntity addressPO = transform(addressBO);
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
                addressRepository.updateAddress(addressBO.getAddress(),addressBO.getLongitude(),addressBO.getLatitude(),saveTime,addressBO.getIncidentId(),addressBO.getAddressType());
            }

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(po)", String.format("repository is finished,execute time is :%sms", end - start));

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveAddress", String.format("service is finished,execute time is :%s ms", logEnd - logStart));

            return addressBO;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveAddress", "save location fail.", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_LOCATION_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see #findAddressByIncidentId(String)
     */
    @Transactional( readOnly =  true )
    @Override
    public List<AddressBean> findAddressByIncidentId(String incidentId) {
        if (StringUtils.isBlank(incidentId)) {
            logService.infoLog(logger, "service", "findAddressByIncidentId", "incidentId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findAddressByIncidentId", "service is started...");
            Long logStart = System.currentTimeMillis();

            logService.infoLog(logger, "repository", "findAddressByIncidentId(incidentId)", "repository is started...");
            Long start = System.currentTimeMillis();
            List<AddressEntity> addressPOList = addressRepository.findAddressByIncidentId(incidentId);
            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findAddressByIncidentId(incidentId)", String.format("repository is finished,execute time is :%s ms", end - start));

            List<AddressBean> addressBOS = new ArrayList<>();
            if (addressPOList != null && addressPOList.size() > 0) {
                for (AddressEntity addressPO : addressPOList) {
                    AddressBean addressBO = transform(addressPO);
                    addressBOS.add(addressBO);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findAddressByIncidentId", String.format("service is finished,execute time is :%s ms", logEnd - logStart));

            return addressBOS;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findAddressByIncidentId", "get tag list by incidentId fail.", ex);
            throw new AcceptException(AcceptException.AccetpErrors.GET_LOCATION_FAIL);
        }
    }


    /**
     * 辅助地址转换
     *
     * @param source 辅助地址PO
     * @return 辅助地址BO
     */
    public static AddressBean transform(AddressEntity source) {
        if (null != source) {
            AddressBean target = new AddressBean();

            target.setId(source.getId());
            target.setIncidentId(source.getIncidentId());
            target.setAddress(source.getAddress());
            target.setLongitude(source.getLongitude());
            target.setLatitude(source.getLatitude());
            target.setAddressType(source.getAddressType());
            target.setSaveTime(source.getSaveTime());
            target.setSjc(source.getSjc());

            return target;
        }
        return null;
    }

    /**
     * 辅助地址转换
     *
     * @param source 辅助地址BO
     * @return 辅助地址PO
     */
    public static AddressEntity transform(AddressBean source) {
        if (null != source) {
            AddressEntity target = new AddressEntity();

            target.setId(source.getId());
            target.setIncidentId(source.getIncidentId());
            target.setAddress(source.getAddress());
            target.setLongitude(source.getLongitude());
            target.setLatitude(source.getLatitude());
            target.setAddressType(source.getAddressType());
            target.setSaveTime(source.getSaveTime());
            target.setSjc(source.getSjc());

            return target;
        }
        return null;
    }



}
