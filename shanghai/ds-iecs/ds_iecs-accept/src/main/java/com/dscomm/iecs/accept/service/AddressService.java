package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.service.bean.AddressBean;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 描述:辅助地址服务
 *
 * @author XuHao
 * Date Time 2020/2/21 15:03
 */
public interface AddressService {
    /**
     * 根据事件id和地址类型删除辅助地址
     *
     * @param incidentId 事件单编号
     * @param addressType 地址类型 1:主地址 2:辅助地址2 3:辅助地址3(默认为1)
     * @return
     */
    Boolean deleteAddressByIncidentIdAndAddressType(String incidentId, Integer addressType);

    /**
     * 保存辅助地址
     *
     * @param addressBO 辅助地址信息
     * @return 辅助地址信息
     */
    AddressBean saveAddress(AddressBean addressBO);

    /**
     * 根据事件id获取辅助地址列表
     *
     * @param incidentId 事件单编号
     * @return 辅助地址列表
     */
    List<AddressBean> findAddressByIncidentId(@Param("incidentId") String incidentId);
}
