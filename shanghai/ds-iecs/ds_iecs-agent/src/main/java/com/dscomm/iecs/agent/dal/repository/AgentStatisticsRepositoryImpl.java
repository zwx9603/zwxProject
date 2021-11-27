package com.dscomm.iecs.agent.dal.repository;

import com.dscomm.iecs.keydata.enums.OperationTypeEnum;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述： 坐席统计 数据库持久层服务实现类
 */
@Repository
public class AgentStatisticsRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public  List<Long[]>  findSeatAcceptanceTimeTrend(Long startTime, Long endTime, String seatNumber, String personNumber, String searchPath) {
        String headsql = " select count(1)  as  NUM from jcj_sld jcj_sld where jcj_sld.yxx=1 ";
        String conditionsql = " ";
        if (startTime != null) {
            conditionsql = conditionsql + " and jcj_sld.cjsj >=:KSSJ  ";
        }
        if (endTime != null) {
            conditionsql = conditionsql + " and jcj_sld.cjsj <:JSSJ  ";
        }
        if (Strings.isNotBlank(seatNumber)) {
            conditionsql = conditionsql + " and jcj_sld.lajjxh =:ZXBH  ";
        }
        if (Strings.isNotBlank(personNumber)) {
            conditionsql = conditionsql + " and jcj_sld.jjygh =:YGBH  ";
        }
        if (Strings.isNotBlank(searchPath)) {
            conditionsql = conditionsql + "  and jcj_sld.zgjgbh in (   select t.id  from jgxx_xfjg t  where  t.yxx = 1 and t.cxmlj like :JGCXM )  ";
        }
        String endsql = " ";
        String ordersql = " ";
        String sql = headsql + conditionsql + endsql + ordersql;
        Query query = entityManager.createNativeQuery(sql);
        if (startTime != null) {
            query.setParameter("KSSJ", startTime);
        }
        if (endTime != null) {
            query.setParameter("JSSJ", endTime);
        }
        if (Strings.isNotBlank(seatNumber)) {
            query.setParameter("ZXBH", seatNumber);
        }
        if (Strings.isNotBlank(personNumber)) {
            query.setParameter("YGBH", personNumber);
        }
        if (Strings.isNotBlank(searchPath)) {
            query.setParameter("JGCXM", searchPath + "%");
        }
        List<Long[]> list = query.getResultList();
        return list;
    }

    @Transactional(readOnly = true)
    public  List<Long[]>  findSeatIncidentTimeTrend(Long startTime, Long endTime, String seatNumber, String personNumber, String searchPath) {
        String headsql = " select count(1) from jcj_ajxx a where a.yxx=1 ";
        String conditionsql = " ";
        if (startTime != null) {
            conditionsql = conditionsql + " and a.cjsj >=:KSSJ  ";
        }
        if (endTime != null) {
            conditionsql = conditionsql + " and a.cjsj <:JSSJ  ";
        }
        if (Strings.isNotBlank(seatNumber)) {
            conditionsql = conditionsql + " and a.lazxh =:ZXBH  ";
        }
        if (Strings.isNotBlank(personNumber)) {
            conditionsql = conditionsql + " and a.jjygh =:YGBH  ";
        }
        if (Strings.isNotBlank(searchPath)) {
            conditionsql = conditionsql + "  and a.XFJYJG_TYWYSBM in (   select t.id  from jgxx_xfjg t  where  t.yxx = 1 and t.cxmlj like :JGCXM )  ";
        }
        String endsql = " ";
        String ordersql = " ";
        String sql = headsql + conditionsql + endsql + ordersql;
        Query query = entityManager.createNativeQuery(sql);
        if (startTime != null) {
            query.setParameter("KSSJ", startTime);
        }
        if (endTime != null) {
            query.setParameter("JSSJ", endTime);
        }
        if (Strings.isNotBlank(seatNumber)) {
            query.setParameter("ZXBH", seatNumber);
        }
        if (Strings.isNotBlank(personNumber)) {
            query.setParameter("YGBH", personNumber);
        }
        if (Strings.isNotBlank(searchPath)) {
            query.setParameter("JGCXM", searchPath + "%");
        }
        List<Long[]> list = query.getResultList();
        return list;
    }





    @Transactional(readOnly = true)
    public  List<Long[]>  findSeatViolationTrend(Long startTime, Long endTime, String seatNumber, String personNumber, String searchPath) {
        String headsql = " select count(1) from jcj_wgczjl a where a.yxx=1 ";
        String conditionsql = " ";
        if (startTime != null) {
            conditionsql = conditionsql + " and a.cjsj >=:KSSJ  ";
        }
        if (endTime != null) {
            conditionsql = conditionsql + " and a.cjsj <:JSSJ  ";
        }
        if (Strings.isNotBlank(seatNumber)) {
            conditionsql = conditionsql + " and a.wgzxh =:ZXBH  ";
        }
        if (Strings.isNotBlank(personNumber)) {
            conditionsql = conditionsql + " and a.wgrygh =:YGBH  ";
        }
        if (Strings.isNotBlank(searchPath)) {
            conditionsql = conditionsql + "  and a.jgid in (   select t.id  from jgxx_xfjg t  where  t.yxx = 1 and t.cxmlj like :JGCXM )  ";
        }
        String endsql = " ";
        String ordersql = " ";
        String sql = headsql + conditionsql + endsql + ordersql;
        Query query = entityManager.createNativeQuery(sql);
        if (startTime != null) {
            query.setParameter("KSSJ", startTime);
        }
        if (endTime != null) {
            query.setParameter("JSSJ", endTime);
        }
        if (Strings.isNotBlank(seatNumber)) {
            query.setParameter("ZXBH", seatNumber);
        }
        if (Strings.isNotBlank(personNumber)) {
            query.setParameter("YGBH", personNumber);
        }
        if (Strings.isNotBlank(searchPath)) {
            query.setParameter("JGCXM", searchPath + "%");
        }
        List<Long[]> list = query.getResultList();
        return list;
    }



    @Transactional(readOnly = true)
    public  List<Object []> findSeatLogRecord(Long startTime, Long endTime, String seatNumber, String personNumber )   {
        String headsql = " select  jjymc ,  jjygh ,   czsj , czlx  " +
                " from  JCJ_XTDLJL zb where czlx in ( :DLDCZT )   ";
        String conditionsql = " ";
        if (startTime != null) {
            conditionsql = conditionsql + " and zb.czsj >=:KSSJ  ";
        }
        if (endTime != null) {
            conditionsql = conditionsql + " and zb.czsj <:JSSJ  ";
        }
        if (Strings.isNotBlank(seatNumber)) {
            conditionsql = conditionsql + " and zb.czth =:ZXBH  ";
        }
        if (Strings.isNotBlank(personNumber)) {
            conditionsql = conditionsql + " and zb.jjygh =:YGBH  ";
        }
        String endsql = " ";
        String ordersql = "  order  by  czsj  asc  ";
        String sql = headsql + conditionsql + endsql + ordersql;
        Query query = entityManager.createNativeQuery(sql);
        List<String >  dldczt = new ArrayList<>();
        dldczt.add( OperationTypeEnum.OPERATIONTYPE_LOGIN.getCode() + "" );
        dldczt.add( OperationTypeEnum.OPERATIONTYPE_LOGOUT.getCode() + "" ) ;
        dldczt.add( OperationTypeEnum.OPERATIONTYPE_FORCEDEXIT.getCode() + "" );
        dldczt.add( OperationTypeEnum.OPERATIONTYPE_CHERT_HEART_OVERTIME.getCode() + "" );
        query.setParameter("DLDCZT", dldczt);
        if (startTime != null) {
            query.setParameter("KSSJ", startTime);
        }
        if (endTime != null) {
            query.setParameter("JSSJ", endTime);
        }
        if (Strings.isNotBlank(seatNumber)) {
            query.setParameter("ZXBH", seatNumber);
        }
        if (Strings.isNotBlank(personNumber)) {
            query.setParameter("YGBH", personNumber);
        }
        List< Object []> list = query.getResultList();
        return list;
    }





    @Transactional(readOnly = true)
    public   List<Long[]> findViolationCount(Long startTime, Long endTime, String seatNumber, String personNumber) {
        String headsql = " select count(1) from jcj_wgczjl a   where a.yxx=1  ";
        String conditionsql = " ";
        if (startTime != null) {
            conditionsql = conditionsql + " and a.cjsj >=:KSSJ  ";
        }
        if (endTime != null) {
            conditionsql = conditionsql + " and a.cjsj <:JSSJ  ";
        }
        if (Strings.isNotBlank(seatNumber)) {
            conditionsql = conditionsql + " and a.wgzxh =:ZXBH  ";
        }
        if (Strings.isNotBlank(personNumber)) {
            conditionsql = conditionsql + " and a.wgrygh =:YGBH  ";
        }

        String endsql = " ";
        String ordersql = " ";
        String sql = headsql + conditionsql + endsql + ordersql;
        Query query = entityManager.createNativeQuery(sql);
        if (startTime != null) {
            query.setParameter("KSSJ", startTime);
        }
        if (endTime != null) {
            query.setParameter("JSSJ", endTime);
        }
        if (Strings.isNotBlank(seatNumber)) {
            query.setParameter("ZXBH", seatNumber);
        }
        if (Strings.isNotBlank(personNumber)) {
            query.setParameter("YGBH", personNumber);
        }
        List<Long[]> list = query.getResultList();
        return list ;
    }



    @Transactional(readOnly = true)
    public  List<Long[]> findAcceptanceCountAndAvgDuration(Long startTime, Long endTime, String seatNumber, String personNumber) {
        String headsql = " select count(1) , sum(COALESCE(a.jjsc,0)) / COALESCE(count(1),1)  " +
                "          from jcj_sld a where a.yxx=1   ";
        String conditionsql = " ";
        if (startTime != null) {
            conditionsql = conditionsql + " and a.cjsj >=:KSSJ  ";
        }
        if (endTime != null) {
            conditionsql = conditionsql + " and a.cjsj <:JSSJ  ";
        }
        if (Strings.isNotBlank(seatNumber)) {
            conditionsql = conditionsql + " and a.lajjxh =:ZXBH  ";
        }
        if (Strings.isNotBlank(personNumber)) {
            conditionsql = conditionsql + " and a.jjygh =:YGBH  ";
        }

        String endsql = " ";
        String ordersql = " ";
        String sql = headsql + conditionsql + endsql + ordersql;
        Query query = entityManager.createNativeQuery(sql);
        if (startTime != null) {
            query.setParameter("KSSJ", startTime);
        }
        if (endTime != null) {
            query.setParameter("JSSJ", endTime);
        }
        if (Strings.isNotBlank(seatNumber)) {
            query.setParameter("ZXBH", seatNumber);
        }
        if (Strings.isNotBlank(personNumber)) {
            query.setParameter("YGBH", personNumber);
        }
        List<Long[]> list = query.getResultList();
        return list;
    }





    @Transactional(readOnly = true)
    public  List<Long[]> findAvgHandleDuration(Long startTime, Long endTime, String seatNumber, String personNumber) {
        String headsql = " select sum(COALESCE(a.cjsc,0)) / COALESCE(count(1),1) from jcj_cjjl a where a.yxx=1   ";
        String conditionsql = " ";
        if (startTime != null) {
            conditionsql = conditionsql + " and a.cjsj >=:KSSJ  ";
        }
        if (endTime != null) {
            conditionsql = conditionsql + " and a.cjsj <:JSSJ  ";
        }
        if (Strings.isNotBlank(seatNumber)) {
            conditionsql = conditionsql + " and a.cjth =:ZXBH  ";
        }
        if (Strings.isNotBlank(personNumber)) {
            conditionsql = conditionsql + " and a.cjygh =:YGBH  ";
        }
        String endsql = " ";
        String ordersql = " ";
        String sql = headsql + conditionsql + endsql + ordersql;
        Query query = entityManager.createNativeQuery(sql);
        if (startTime != null) {
            query.setParameter("KSSJ", startTime);
        }
        if (endTime != null) {
            query.setParameter("JSSJ", endTime);
        }
        if (Strings.isNotBlank(seatNumber)) {
            query.setParameter("ZXBH", seatNumber);
        }
        if (Strings.isNotBlank(personNumber)) {
            query.setParameter("YGBH", personNumber);
        }
        List<Long[]> list = query.getResultList();
        return list;
    }





}
