package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.LocationRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:定位记录
 * author:YangFuXi
 * Date:2021/6/18 Time:10:02
 **/
@Repository
public interface LocationRecordRepository extends JpaRepository<LocationRecordEntity,String> {
    /**
     * 获取定位记录的序列号
     * @param incidentId 案件id
     * @return 返回序列号
     */
    @Query("select count(t.sort)+1 from LocationRecordEntity t where t.incidentId=?1 and t.valid=1 ")
    Integer findNextSort(String incidentId);

    /**
     * 获取案件采用定位记录
     * @param incidentId 案件id
     * @param longitude 经度
     * @param latitude 维度
     * @return 返回定位记录
     */
    @Query("select t from LocationRecordEntity t where t.incidentId=?1 and t.gis_x=?2 and t.gis_y=?3 and t.valid=1 order by t.sort")
    List<LocationRecordEntity> findLocationRecordEntityByIncidentIdAndLocation(String incidentId,Float longitude,Float latitude );
}
