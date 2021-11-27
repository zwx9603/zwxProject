package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.TelephoneEntity;
import com.dscomm.iecs.ext.comm.call.CALL_DIRECTION_HC;
import com.dscomm.iecs.ext.comm.call.CALL_DIRECTION_HR;
import org.apache.logging.log4j.util.Strings;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Repository
public class TelephoneRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public  List<TelephoneEntity> findTelephoneCondition (Long   startTime , Long   endTime  , String seatNumber , String personNumber ,
                                                          String  phoneKeyword ,String  nameKeyword  ,
                                                          Boolean whetherPage, int page, int size ) {

        String headsql = "  select  telephone    from TelephoneEntity telephone   where  1 = 1  and telephone.valid = 1  ";
        String conditionsql = "";
        if (Strings.isNotBlank( seatNumber ) ) {
            conditionsql = conditionsql + " and telephone.seatNumber  =  :ZXH ";
        }
        if (Strings.isNotBlank( personNumber ) ) {
            conditionsql = conditionsql + " and telephone.personNumber  =  :RYGH ";
        }
        if (startTime != null) {
            conditionsql = conditionsql + " and telephone.ringingTime >=:KSSJ ";
        }
        if (endTime != null) {
            conditionsql = conditionsql + " and telephone.ringingTime < :JSSJ ";
        }
        if (Strings.isNotBlank( phoneKeyword ) ) {
            conditionsql = conditionsql + " and  (" +
                    " (  telephone.callDirection  = '" + CALL_DIRECTION_HR.getCode() + "' and telephone.callingNumber like :DHMH ) " +
                    "or " +
                    " ( telephone.callDirection  = '" + CALL_DIRECTION_HC.getCode() + "' and telephone.calledNumber like :DHMH  ) " +
                    " or telephone.alarmPersonPhone like :DHMH " +
                    ") ";
        }
        if (Strings.isNotBlank( nameKeyword ) ) {
            conditionsql = conditionsql + " and ( telephone.alarmPersonName  like  :MCMH or telephone.personName  like  :MCMH      )  ";
        }
        String endsql = "  ";
        String ordersql = " order by  telephone.ringingTime desc    ";
        String sql = headsql + conditionsql + endsql + ordersql;
        Query query = entityManager.createQuery(sql);
        if (Strings.isNotBlank(seatNumber)) {
            query.setParameter("ZXH", seatNumber );
        }
        if (Strings.isNotBlank(personNumber)) {
            query.setParameter("RYGH", personNumber);
        }
        if (startTime != null) {
            query.setParameter("KSSJ", startTime);
        }
        if (endTime != null) {
            query.setParameter("JSSJ", endTime);
        }
        if (Strings.isNotBlank( phoneKeyword ) ) {
            query.setParameter("DHMH", "%" + phoneKeyword + "%");
        }
        if (Strings.isNotBlank( nameKeyword ) ) {
            query.setParameter("MCMH", "%" + nameKeyword + "%");
        }
        if (whetherPage) {
            query.setFirstResult((page - 1) * size);
            query.setMaxResults(size);
        }
        return query.getResultList();
    }


    @Transactional(readOnly = true)
    public Integer findTelephoneConditionTotal ( Long   startTime , Long   endTime  ,  String seatNumber ,  String personNumber ,
                                                 String  phoneKeyword ,String  nameKeyword   ) {

        String headsql = "  select count( telephone.id) as  NUM       from TelephoneEntity telephone   where  1 = 1  and telephone.valid = 1  ";
        String conditionsql = "";
        if (Strings.isNotBlank( seatNumber ) ) {
            conditionsql = conditionsql + " and telephone.seatNumber  =  :ZXH ";
        }
        if (Strings.isNotBlank( personNumber ) ) {
            conditionsql = conditionsql + " and telephone.personNumber  =  :RYGH ";
        }
        if (startTime != null) {
            conditionsql = conditionsql + " and telephone.ringingTime >=:KSSJ ";
        }
        if (endTime != null) {
            conditionsql = conditionsql + " and telephone.ringingTime < :JSSJ ";
        }
        if (Strings.isNotBlank( phoneKeyword ) ) {
            conditionsql = conditionsql + " and  (" +
                    " (  telephone.callDirection  = '" + CALL_DIRECTION_HR.getCode() + "' and telephone.callingNumber like :DHMH ) " +
                    "or " +
                    " ( telephone.callDirection  = '" + CALL_DIRECTION_HC.getCode() + "' and telephone.calledNumber like :DHMH  ) " +
                    " or telephone.alarmPersonPhone like :DHMH " +
                    ") ";
        }
        if (Strings.isNotBlank( nameKeyword ) ) {
            conditionsql = conditionsql + " and ( telephone.alarmPersonName  like  :MCMH or telephone.personName  like  :MCMH      )  ";
        }
        String endsql = "  ";
        String ordersql = "  ";
        String sql = headsql + conditionsql + endsql + ordersql;
        Query query = entityManager.createQuery(sql);
        if (Strings.isNotBlank(seatNumber)) {
            query.setParameter("ZXH", seatNumber );
        }
        if (Strings.isNotBlank(personNumber)) {
            query.setParameter("RYGH", personNumber);
        }
        if (startTime != null) {
            query.setParameter("KSSJ", startTime);
        }
        if (endTime != null) {
            query.setParameter("JSSJ", endTime);
        }
        if (Strings.isNotBlank( phoneKeyword ) ) {
            query.setParameter("DHMH", "%" + phoneKeyword + "%");
        }
        if (Strings.isNotBlank( nameKeyword ) ) {
            query.setParameter("MCMH", "%" + nameKeyword + "%");
        }
        List<Map<String, Long>> lists = query.unwrap(org.hibernate.Query.class)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
        if (lists != null && !lists.isEmpty()) {
            Long num = lists.get(0).get("NUM");
            return Integer.parseInt(num.toString());
        }
        return 0;
    }








}
