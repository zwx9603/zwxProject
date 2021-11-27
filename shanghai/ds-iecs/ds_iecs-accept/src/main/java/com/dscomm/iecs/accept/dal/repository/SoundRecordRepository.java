package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.BlacklistEntity;
import com.dscomm.iecs.accept.dal.po.SoundRecordEntity;
import com.dscomm.iecs.accept.dal.po.TelephoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:黑名单 数据库持久层服务
 */
@Repository
public interface SoundRecordRepository extends JpaRepository<SoundRecordEntity, String> {


    /**
     * 根据 录音id   录音信息
     *
     * @param soundRecordIdList 警情ids(案件ids)
     * @return 电话报警记录 列表
     */
    @Query(" select t from SoundRecordEntity t  where  t.valid = 1 and t.id in ( ?1 ) order by  t.updatedTime desc   ")
    List<SoundRecordEntity> findSoundRecordMapByIdList ( List<String> soundRecordIdList  );

    /**
     * 根据 警情id(案件ids)获得   录音信息
     *
     * @param incidentIdList 警情ids(案件ids)
     * @return 电话报警记录 列表
     */
    @Query(" select t from SoundRecordEntity t  where  t.valid = 1 and t.incidentId in ( ?1 ) order by  t.updatedTime desc   ")
    List<SoundRecordEntity> findSoundRecordMapByIncidentIdList ( List<String> incidentIdList  );

    /**
     * 根据 警情id(案件ids)获得 类型  录音信息
     *
     * @param incidentIdList 警情ids(案件ids)
     * @return 电话报警记录 列表
     */
    @Query(" select t from SoundRecordEntity t  where  t.valid = 1 and t.incidentId in ( ?1 ) and t.type in (  ?2 ) order by  t.updatedTime desc   ")
    List<SoundRecordEntity> findSoundRecordMapByIncidentIdList ( List<String> incidentIdList , List<Integer> typeList   );
}
