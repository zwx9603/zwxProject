package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.InteractiveRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Description:交互记录服务
 *
 * @author: ZhangSheng
 * Date: created in 16:46 2019/9/3
 */
@Repository
public interface InteractiveRecordRepository extends JpaRepository<InteractiveRecordEntity,String> {


    /**
     * 交互记录 关联案件id
     */
    @Modifying(clearAutomatically = true)
    @Query("  update  InteractiveRecordEntity t  set t.incidentId = ?2 where  t.valid = 1 and " +
            "  t.alarmId = ?1     " )
    void   saveInteractiveRecordToIncident ( String alarmNumber , String incidentId   );

}
