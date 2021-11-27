package com.dscomm.iecs.accept.dal.repository;

import org.apache.logging.log4j.util.Strings;
import org.hibernate.transform.Transformers;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

public class LedRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<Object[]> findLedVehicleList(String organizationCategoryCode, Integer isDisPlay, String name, String oldName, Boolean whetherPage, int page, int size) {
        String headSql = " select t.id,t.name,t.oldName,t.order,t.isDisPlay from LedVehicleEntity t left join EquipmentVehicleEntity v on v.id=t.id and t.valid=1 left join OrganizationEntity o on o.id=v.organizationId and o.valid=1 where t.valid=1 ";
        String conditionalSql = "  ";

        if (Strings.isNotBlank(organizationCategoryCode)){
            conditionalSql = conditionalSql + " and o.searchPath like :CXMLJ  ";
        }

        if (isDisPlay!=null){
            conditionalSql = conditionalSql + " and t.isDisPlay  =:SFXS  ";
        }

        if (Strings.isNotBlank(name)){
            conditionalSql = conditionalSql + " and t.name like :NAME  ";
        }

        if (Strings.isNotBlank(oldName)){
            conditionalSql = conditionalSql + " and t.oldName like :OLDNAME  ";
        }

        String ordersql = " order by cast(t.order as int) asc  ";

        String sql = headSql + conditionalSql+ordersql;
        Query query = entityManager.createQuery(sql);

        if (Strings.isNotBlank(organizationCategoryCode)){
            query.setParameter("CXMLJ","%"+organizationCategoryCode +"%");
        }

        if (isDisPlay!=null){
            query.setParameter("SFXS",isDisPlay );
        }

        if (Strings.isNotBlank(name)){
            query.setParameter("NAME","%"+name +"%");
        }

        if (Strings.isNotBlank(oldName)){
            query.setParameter("OLDNAME","%"+oldName +"%");
        }

        if (whetherPage) {
            query.setFirstResult((page - 1) * size);
            query.setMaxResults(size);
        }


        return query.getResultList();

    }

    @Transactional(rollbackFor = Exception.class)
    public int findLedVehicleCount(String organizationCategoryCode,Integer isDisPlay, String name, String oldName){

        String headSql = " select count(1) as NUM from LedVehicleEntity t left join EquipmentVehicleEntity v on v.id=t.id and t.valid=1 left join OrganizationEntity o on o.id=v.organizationId and o.valid=1 where t.valid=1 ";
        String conditionalSql = "  ";
        if (Strings.isNotBlank(organizationCategoryCode)){
            conditionalSql = conditionalSql + " and o.searchPath like :CXMLJ ";
        }

        if (isDisPlay!=null){
            conditionalSql = conditionalSql + " and t.isDisPlay  =:SFXS  ";
        }

        if (Strings.isNotBlank(name)){
            conditionalSql = conditionalSql + " and t.name like :NAME  ";
        }

        if (Strings.isNotBlank(oldName)){
            conditionalSql = conditionalSql + " and t.oldName like :OLDNAME  ";
        }

        String sql = headSql + conditionalSql;
        Query query = entityManager.createQuery(sql);

        if (Strings.isNotBlank(organizationCategoryCode)){
            query.setParameter("CXMLJ","%"+organizationCategoryCode +"%");
        }

        if (isDisPlay!=null){
            query.setParameter("SFXS",isDisPlay );
        }

        if (Strings.isNotBlank(name)){
            query.setParameter("NAME","%"+name +"%");
        }

        if (Strings.isNotBlank(oldName)){
            query.setParameter("OLDNAME","%"+oldName +"%");
        }

        List<Map<String, Long>> lists = query.unwrap(org.hibernate.Query.class)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
        if (lists != null && !lists.isEmpty()) {
            Long num = lists.get(0).get("NUM");
            return Integer.parseInt(num.toString());
        }
        return 0;

    }


    @Transactional
    public  List<Object[]> findLedOrganizationList(String organizationCategoryCode,Integer isDisPlay, String name, String oldName, Boolean whetherPage, int page, int size) {
        String headSql = " select  t.id,t.name,t.oldName,t.order,t.isDisPlay,t.organizationParentId,t.isImportantCity from LedOrganizationEntity t  left join OrganizationEntity o on o.id=t.id and o.valid=1 where t.valid=1 ";
        String conditionalSql = "  ";

        if (Strings.isNotBlank(organizationCategoryCode)){
            conditionalSql = conditionalSql + " and o.searchPath like :CXMLJ  ";
        }

        if (isDisPlay!=null){
            conditionalSql = conditionalSql + " and t.isDisPlay  =:SFXS  ";
        }

        if (Strings.isNotBlank(name)){
            conditionalSql = conditionalSql + " and t.name like :NAME  ";
        }

        if (Strings.isNotBlank(oldName)){
            conditionalSql = conditionalSql + " and t.oldName like :OLDNAME  ";
        }

        String ordersql = " order by cast(t.order as int) asc  ";

        String sql = headSql + conditionalSql+ordersql;
        Query query = entityManager.createQuery(sql);

        if (Strings.isNotBlank(organizationCategoryCode)){
            query.setParameter("CXMLJ","%"+organizationCategoryCode +"%");
        }

        if (isDisPlay!=null){
            query.setParameter("SFXS",isDisPlay );
        }

        if (Strings.isNotBlank(name)){
            query.setParameter("NAME","%"+name +"%");
        }

        if (Strings.isNotBlank(oldName)){
            query.setParameter("OLDNAME","%"+oldName +"%");
        }


        if (whetherPage) {
            query.setFirstResult((page - 1) * size);
            query.setMaxResults(size);
        }


        return query.getResultList();

    }

    @Transactional(rollbackFor = Exception.class)
    public int findLedOrganizationCount(String organizationCategoryCode,Integer isDisPlay, String name, String oldName){

        String headSql = " select count(1) as NUM from LedOrganizationEntity t  left join OrganizationEntity o on o.id=t.id and o.valid=1 where t.valid=1 ";
        String conditionalSql = "  ";
        if (Strings.isNotBlank(organizationCategoryCode)){
            conditionalSql = conditionalSql + " and o.searchPath like :CXMLJ ";
        }

        if (isDisPlay!=null){
            conditionalSql = conditionalSql + " and t.isDisPlay  =:SFXS  ";
        }

        if (Strings.isNotBlank(name)){
            conditionalSql = conditionalSql + " and t.name like :NAME  ";
        }

        if (Strings.isNotBlank(oldName)){
            conditionalSql = conditionalSql + " and t.oldName like :OLDNAME  ";
        }

        String sql = headSql + conditionalSql;
        Query query = entityManager.createQuery(sql);

        if (Strings.isNotBlank(organizationCategoryCode)){
            query.setParameter("CXMLJ","%"+organizationCategoryCode +"%");
        }

        if (isDisPlay!=null){
            query.setParameter("SFXS",isDisPlay );
        }

        if (Strings.isNotBlank(name)){
            query.setParameter("NAME","%"+name +"%");
        }

        if (Strings.isNotBlank(oldName)){
            query.setParameter("OLDNAME","%"+oldName +"%");
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
