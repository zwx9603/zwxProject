package com.dscomm.iecs.out.dal.repository;

import com.dscomm.iecs.accept.dal.po.SoundRecordEntity;
import com.dscomm.iecs.out.dal.po.QueryAuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/4/29 10:09
 * @Describe
 */

@Repository
public interface SoundRecordOutRepository extends JpaRepository<SoundRecordEntity, String> {


    /**
     * 根据录音号获取录音文件信息
     * @param relayRecordNumber
     * @return
     */
    @Query(" select t from SoundRecordEntity t  where  t.valid = 1 and t.recordNo=?1 order by  t.updatedTime desc   ")
    List<SoundRecordEntity> findSoundRecordByRecordNo (String relayRecordNumber  );

    /**
     * 获取录音文件和立案时间
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 返回结果
     */
    @Query("select t,incident.registerIncidentTime from SoundRecordEntity t left join IncidentEntity incident on incident.id=t.incidentId where t.createdTime>=?1 and t.createdTime<?2 and t.valid=1")
    List<Object[]> findSoundRecordEntitiesByTime(Long startTime ,Long endTime);
}
