package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.ViolationRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:违规操作记录 数据库持久层服务
 */
@Repository
public interface ViolationRecordRepository extends JpaRepository<ViolationRecordEntity, String> {

    /**
     * 根据时间段 坐席号 人员工号 获得违规操作记录
     *
     * @param startTime    开始时间
     * @param endTime      结束时间
     * @param seatNumber   坐席号
     * @param personNumber 人员工号
     * @return 违规操作记录列表
     */

    List<ViolationRecordEntity> findViolationCondition(Long startTime, Long endTime,String searchPath, String seatNumber, String personNumber , String keyword  ,
                                                                  List<String> typeCode , String dictionaryTypeCode , Boolean whetherPage ,  int page, int size   );


    /**
     * 根据时间段 坐席号 人员工号 获得违规操作记录
     *
     * @param startTime    开始时间
     * @param endTime      结束时间
     * @param seatNumber   坐席号
     * @param personNumber 人员工号
     * @return 违规操作记录列表
     */

    Integer findViolationConditionTotal(Long startTime, Long endTime, String searchPath , String seatNumber, String personNumber , String keyword  ,
                                                                  List<String> typeCode , String dictionaryTypeCode );


    /**
     * 根据时间段查询违规类型类型统计信息
     *
     * @return 符合条件的警情数量
     */
    List<Object[]> findStatisticsViolationType(Long startTime, Long endTime,String searchPath , String seatNumber, String personNumber , String keyword  ,
                                              List<String> typeCode , String dictionaryTypeCode );

}
