package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.LocationEntity;
import com.dscomm.iecs.accept.dal.po.LocationSMSEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 描述:定位轨迹
 *
 * @author LiLu
 * Date Time 2019/8/14 0014 10:00
 */
public interface LocationRepository extends JpaRepository<LocationEntity, String> {

    /**
     * 根据事件id和轨迹类型查找定位轨迹
     *
     * @param incidentId     事件id
     * @param trajectoryType 轨迹类型
     * @return 定位轨迹列表
     */
    @Query("select l from LocationEntity l where l.incidentId = :incidentId and l.trajectoryType = :trajectoryType order by l.locationTime asc")
    List<LocationEntity> findLocationByIncidentIdAndTrajectoryType(@Param("incidentId") String incidentId, @Param("trajectoryType") Integer trajectoryType);



    /**
     * 根据电话号码 账号 当前时间 未返回结果 获得 发送短信记录
     *
     *
     * @return 定位轨迹列表
     */
    @Query("select t from LocationSMSEntity t where  t.valid = 1   " +
            "   and t.userAccount = ?1 and t.phoneNumber = ?2 " +
            "   and t.smsSendTime < ?3 and t.smsReceiveTime is null " +
            " order by t.smsSendTime desc ")
    List<LocationSMSEntity> findLocationSMS(String userAccount, String phoneNumber , Long smsSendTime );

}
