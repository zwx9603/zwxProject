package com.dscomm.iecs.agent.dal.repository;

import com.dscomm.iecs.basedata.dal.po.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 描述： 坐席统计 数据库持久层服务
 */
@Repository
public interface AgentStatisticsRepository extends JpaRepository<OrganizationEntity,Integer> {


    /**
     * 根据时间段 辖区机构id 坐席号 人员工号 获得接警量时间趋势
     *
     * @param startTime    开始时间
     * @param endTime      结束时间
     * @param seatNumber   坐席号
     * @param personNumber 人员工号
     * @param searchPath   辖区机构查询码
     * @return 符合条件的警情数量
     */
    List<Long[]>  findSeatAcceptanceTimeTrend(Long startTime, Long endTime, String seatNumber, String personNumber, String searchPath);

    /**
     * 根据时间段  辖区机构id 坐席号 人员工号 获得警情量时间趋势
     *
     * @param startTime    开始时间
     * @param endTime      结束时间
     * @param seatNumber   坐席号
     * @param personNumber 人员工号
     * @param searchPath   辖区机构查询码
     * @return 符合条件的警情数量
     */
    List<Long[]>  findSeatIncidentTimeTrend(Long startTime, Long endTime, String seatNumber, String personNumber, String searchPath);

    /**
     * 根据时间段  辖区机构id 坐席号 人员工号 获得违规量时间趋势
     *
     * @param startTime    开始时间
     * @param endTime      结束时间
     * @param seatNumber   坐席号
     * @param personNumber 人员工号
     * @param searchPath   辖区机构查询码
     * @return 符合条件的警情数量
     */
    List<Long[]>  findSeatViolationTrend(Long startTime, Long endTime, String seatNumber, String personNumber, String searchPath);


    /**
     * 根据时间段  辖区机构id 坐席号 人员工号 获得违规量时间趋势
     *
     * @param startTime    开始时间
     * @param endTime      结束时间
     * @param seatNumber   坐席号
     * @param personNumber 人员工号
     * @return 符合条件的警情数量
     */
    List<Object []> findSeatLogRecord(Long startTime, Long endTime, String seatNumber, String personNumber );




    /**
     * 根据时间段  坐席号 人员工号 获取违规量
     *
     * @param startTime    开始时间
     * @param endTime      结束时间
     * @param seatNumber   坐席号
     * @param personNumber 人员工号
     * @return 符合条件的警情数量
     */
//    @Query(value = " select count(1) from jcj_wgczjl a " +
//            " where a.yxx=1 and a.wgzxh = ?3 and a.wgrygh = ?4 and a.cjsj >= ?1 and a.cjsj < ?2 ", nativeQuery = true)
    List<Long[]> findViolationCount(Long startTime, Long endTime, String seatNumber, String personNumber);

    /**
     * 根据时间段  坐席号 人员工号 获得接警量和平均接警时长
     *
     * @param startTime    开始时间
     * @param endTime      结束时间
     * @param seatNumber   坐席号
     * @param personNumber 人员工号
     * @return 符合条件的警情数量
     */
//    @Query(value = " select count(1) , sum(COALESCE(a.jjsc,0)) / COALESCE(count(1),1) " +
//            " from jcj_sld a where a.yxx=1 and a.jjygh=?4 and a.lajjxh = ?3 and a.cjsj >= ?1 and a.cjsj < ?2 ", nativeQuery = true)
    List<Long[]> findAcceptanceCountAndAvgDuration(Long startTime, Long endTime, String seatNumber, String personNumber);

    /**
     * 根据时间段  坐席号 人员工号 获得平均处警时长
     *
     * @param startTime    开始时间
     * @param endTime      结束时间
     * @param seatNumber   坐席号
     * @param personNumber 人员工号
     * @return 符合条件的警情数量
     */
//    @Query(value = " select sum(COALESCE(a.cjsc,0)) / COALESCE(count(1),1) from jcj_cjjl a " +
//            " where a.yxx=1 and a.cjth = ?3 and a.cjygh = ?4 and a.cjsj >= ?1 and a.cjsj < ?2 ", nativeQuery = true)
    List<Long[]> findAvgHandleDuration(Long startTime, Long endTime, String seatNumber, String personNumber);


}
