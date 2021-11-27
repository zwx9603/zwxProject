package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.ConsensusInformationEntiy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsensusInformationRepository extends JpaRepository<ConsensusInformationEntiy, String> {

    /**
     * 通过当前时间查询舆情
     * @param currentTimeStart currentTimeEnd
     * @return
     */
    @Query("select t from ConsensusInformationEntiy t where t.valid=1 and t.publishedTime>=?1 and t.publishedTime<?2")
    List<ConsensusInformationEntiy> findConsensusInformations(Long currentTimeStart, Long currentTimeEnd );

    /**
     * 通过id查询舆情
     * @param  id
     * @return
     */
    @Query("select t from ConsensusInformationEntiy t where t.valid=1 and t.id=?1")
    ConsensusInformationEntiy findConsensusInformationsById(String id );
}
