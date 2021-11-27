package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.ViolationRecordEntity;
import org.apache.logging.log4j.util.Strings;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

/**
 * 描述:违规操作记录 数据库持久层服务
 */
@Repository
public class ViolationRecordRepositoryImpl   {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public  List<ViolationRecordEntity> findViolationCondition(Long startTime, Long endTime ,String searchPath , String seatNumber, String personNumber , String keyword  ,
                                                               List<String> typeCode , String dictionaryTypeCode ,  Boolean whetherPage ,  int page, int size   ) {

        String headsql = "  select t from ViolationRecordEntity t  where  t.valid = 1  ";
        String conditionsql = "";
        if (startTime != null) {
            conditionsql = conditionsql + " and t.violationTime >=:KSSJ ";
        }
        if (endTime != null) {
            conditionsql = conditionsql + " and t.violationTime <:JSSJ ";
        }
        if (Strings.isNotBlank(searchPath)) {
            conditionsql = conditionsql + " and ( 1 != 1   " +
                    " or t.organizationId in (   select t.id   from OrganizationEntity t  where  t.valid = 1 and t.searchPath like :GJGCXM ) " +
                    "  ) ";
        }
        if (Strings.isNotBlank(seatNumber)) {
            conditionsql = conditionsql + " and t.violationSeatNumber = :ZXH ";
        }
        if ( Strings.isNotBlank( personNumber )) {
            conditionsql = conditionsql + " and t.violationPersonNumber = :RYGH   ";
        }
        if ( Strings.isNotBlank( keyword )) {
            conditionsql = conditionsql + " and  (  t.violationPersonName  like :GJZ  or t.violationSeatNumber like :GJZ  or t.violationPersonNumber like :GJZ" +
                    " or t.violationDesc like :GJZ  " +
                    " or t.organizationId  in (   select org.id  from OrganizationEntity org where org.valid = 1 and org.organizationName like :GJZ   )   " +
                    " or t.violationType  in (  select dic.code  from DictionaryEntity dic where dic.valid = 1 and dic.typeCode = :WGLXZD  and dic.name   like :GJZ  )" +
                    "  )  ";
        }
        if (typeCode != null && typeCode.size() > 0) {
            conditionsql = conditionsql + " and t.violationType in (:WGLX )  ";
        }
        String endsql = "  ";
        String ordersql = " order by t.violationTime desc  ";
        String sql = headsql + conditionsql + endsql + ordersql;
        javax.persistence.Query query = entityManager.createQuery(sql);
        if (startTime != null) {
            query.setParameter("KSSJ", startTime);
        }
        if (endTime != null) {
            query.setParameter("JSSJ", endTime);
        }
        if( Strings.isNotBlank( seatNumber ) ){
            query.setParameter("ZXH", seatNumber );
        }
        if (Strings.isNotBlank(personNumber)) {
            query.setParameter("RYGH", personNumber );
        }
        if (Strings.isNotBlank(keyword)) {
            query.setParameter("GJZ", "%" + keyword + "%");
            query.setParameter("WGLXZD", dictionaryTypeCode );
        }
        if (typeCode != null && typeCode.size() > 0) {
            query.setParameter("WGLX", typeCode);
        }
        if (whetherPage) {
            query.setFirstResult((page - 1) * size);
            query.setMaxResults(size);
        }
        return query.getResultList();
    }


    @Transactional(readOnly = true)
    public Integer findViolationConditionTotal(Long startTime, Long endTime ,String searchPath , String seatNumber, String personNumber , String keyword  ,
                                               List<String> typeCode , String dictionaryTypeCode ) {

        String headsql = "  select count(t.id) as  NUM   from ViolationRecordEntity t  where  t.valid = 1  ";
        String conditionsql = "";
        if (startTime != null) {
            conditionsql = conditionsql + " and t.violationTime >=:KSSJ ";
        }
        if (endTime != null) {
            conditionsql = conditionsql + " and t.violationTime <:JSSJ ";
        }
        if (Strings.isNotBlank(searchPath)) {
            conditionsql = conditionsql + " and ( 1 != 1   " +
                    " or t.organizationId in (   select t.id   from OrganizationEntity t  where  t.valid = 1 and t.searchPath like :GJGCXM ) " +
                    "  ) ";
        }
        if (Strings.isNotBlank(seatNumber)) {
            conditionsql = conditionsql + " and t.violationSeatNumber = :ZXH ";
        }
        if ( Strings.isNotBlank( personNumber )) {
            conditionsql = conditionsql + " and t.violationPersonNumber = :RYGH   ";
        }
        if ( Strings.isNotBlank( keyword )) {
            conditionsql = conditionsql + " and  (  t.violationPersonName  like :GJZ  or t.violationSeatNumber like :GJZ or t.violationPersonNumber like :GJZ " +
                    " or t.violationPersonNumber like :GJZ or t.violationDesc like :GJZ    " +
                    " or t.organizationId  in (   select org.id  from OrganizationEntity org where org.valid = 1 and org.organizationName like :GJZ   )   " +
                    " or t.violationType  in (  select dic.code  from DictionaryEntity dic where dic.valid = 1 and dic.typeCode = :WGLXZD  and dic.name   like :GJZ  ) " +
                    " )  ";
        }
        if (typeCode != null && typeCode.size() > 0) {
            conditionsql = conditionsql + " and t.violationType in (:WGLX )  ";
        }
        String endsql = "  ";
        String ordersql = "   ";
        String sql = headsql + conditionsql + endsql + ordersql;
        javax.persistence.Query query = entityManager.createQuery(sql);
        if (startTime != null) {
            query.setParameter("KSSJ", startTime);
        }
        if (endTime != null) {
            query.setParameter("JSSJ", endTime);
        }
        if( Strings.isNotBlank( seatNumber ) ){
            query.setParameter("ZXH", seatNumber );
        }
        if (Strings.isNotBlank(personNumber)) {
            query.setParameter("RYGH", personNumber );
        }
        if (Strings.isNotBlank(keyword)) {
            query.setParameter("GJZ", "%" + keyword + "%");
            query.setParameter("WGLXZD", dictionaryTypeCode );
        }
        if (typeCode != null && typeCode.size() > 0) {
            query.setParameter("WGLX", typeCode);
        }
        List<Map<String, Long>> lists = query.unwrap(org.hibernate.Query.class)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
        if (lists != null && !lists.isEmpty()) {
            Long num = lists.get(0).get("NUM");
            return Integer.parseInt(num.toString());
        }
        return 0;
    }



    @Transactional(readOnly = true)
    public List<Object[]> findStatisticsViolationType(Long startTime, Long endTime ,String searchPath , String seatNumber, String personNumber , String keyword  ,
                                                      List<String> typeCode , String dictionaryTypeCode ) {

        String headsql = "  select  t.violationType as typeCode , count(1) as typeNum  from ViolationRecordEntity t  where  t.valid = 1  ";
        String conditionsql = "";
        if (startTime != null) {
            conditionsql = conditionsql + " and t.violationTime >=:KSSJ ";
        }
        if (endTime != null) {
            conditionsql = conditionsql + " and t.violationTime <:JSSJ ";
        }
        if (Strings.isNotBlank(searchPath)) {
            conditionsql = conditionsql + " and ( 1 != 1   " +
                    " or t.organizationId in (   select t.id   from OrganizationEntity t  where  t.valid = 1 and t.searchPath like :GJGCXM ) " +
                    "  ) ";
        }
        if (Strings.isNotBlank(seatNumber)) {
            conditionsql = conditionsql + " and t.violationSeatNumber = :ZXH ";
        }
        if ( Strings.isNotBlank( personNumber )) {
            conditionsql = conditionsql + " and t.violationPersonNumber = :RYGH   ";
        }
        if ( Strings.isNotBlank( keyword )) {
            conditionsql = conditionsql + " and  (  t.violationPersonName  like :GJZ  or t.violationSeatNumber like :GJZ  " +
                    " or t.violationPersonNumber like :GJZ or t.violationDesc like :GJZ    " +
                    " or t.organizationId  in (   select org.id  from OrganizationEntity org where org.valid = 1 and org.organizationName like :GJZ   )   " +
                    " or t.violationType  in (  select dic.code  from DictionaryEntity dic where dic.valid = 1 and dic.typeCode = :WGLXZD  and dic.name   like :GJZ  ) " +
                    " )  ";
        }
        if (typeCode != null && typeCode.size() > 0) {
            conditionsql = conditionsql + " and t.violationType in (:WGLX )  ";
        }
        String endsql = "  group by  t.violationType     ";
        String ordersql = " order by typeCode  asc       ";
        String sql = headsql + conditionsql + endsql + ordersql;
        Query query = entityManager.createQuery(sql);
        if (startTime != null) {
            query.setParameter("KSSJ", startTime);
        }
        if (endTime != null) {
            query.setParameter("JSSJ", endTime);
        }
        if( Strings.isNotBlank( seatNumber ) ){
            query.setParameter("ZXH", seatNumber );
        }
        if (Strings.isNotBlank(personNumber)) {
            query.setParameter("RYGH", personNumber );
        }
        if (Strings.isNotBlank(keyword)) {
            query.setParameter("GJZ", "%" + keyword + "%");
            query.setParameter("WGLXZD", dictionaryTypeCode );
        }
        if (typeCode != null && typeCode.size() > 0) {
            query.setParameter("WGLX", typeCode);
        }
        List<Object[]> list = query.getResultList();
        return list;
    }



}
