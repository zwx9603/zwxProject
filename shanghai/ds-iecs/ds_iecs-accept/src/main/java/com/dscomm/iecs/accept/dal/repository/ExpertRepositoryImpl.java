package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.basedata.dal.po.ExpertEntity;
import org.mx.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @Author: zs
 * @Date: 15:56 2020/7/23
 * desc:
 */
public class ExpertRepositoryImpl {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 全局模糊查询
     * @param keyword
     * @return
     */
    @Transactional( readOnly =  true )
    public List<ExpertEntity>findExpertList(String keyword){
        String headSql=" select t from ExpertEntity t where t.valid=1 ";
        String conditionalSql = " ";

        if( !StringUtils.isBlank(keyword) ){
            conditionalSql = conditionalSql + "  and t.expertName like:ZJXM  " ;
        }
        if( !StringUtils.isBlank(keyword) ){
            conditionalSql = conditionalSql + "  or t.expertType like:ZJLX  " ;
        }
        if( !StringUtils.isBlank(keyword) ){
            conditionalSql = conditionalSql + "  or t.expertField like:ZJLY  " ;
        }

        String  sql = headSql +conditionalSql ;

        Query query = entityManager.createQuery( sql );
        if( !StringUtils.isBlank(keyword)){
            query.setParameter( "ZJXM" , "%"+keyword+"%" );
        }
        if( !StringUtils.isBlank(keyword) ){
            query.setParameter( "ZJLX","%"+keyword+"%" );
        }
        if( !StringUtils.isBlank(keyword) ){
            query.setParameter( "ZJLY","%"+keyword+"%" );
        }

        List<ExpertEntity>  expertEntities = query.getResultList();
        return expertEntities;

    }

    /**
     * 智能查询
     * @param expertField
     * @param expertType
     * @param expertName
     * @return
     */
    @Transactional( readOnly =  true )
    public List<ExpertEntity> findSmartExpertList(String expertField, String expertType, String expertName){
        String headSql=" select t from ExpertEntity t where t.valid=1 ";
        String conditionalSql = " ";
        if( !StringUtils.isBlank(expertField) ){
            conditionalSql = conditionalSql + "  and t.expertField like:ZJLY" ;
        }
        if( !StringUtils.isBlank(expertType) ){
            conditionalSql = conditionalSql + "   and t.expertType like:ZJLX  " ;
        }
        if( !StringUtils.isBlank(expertName) ){
            conditionalSql = conditionalSql + "  and t.expertName like:ZJXM   " ;
        }

        String  sql = headSql +conditionalSql ;

        Query query = entityManager.createQuery( sql );

        if( !StringUtils.isBlank(expertName)){
            query.setParameter( "ZJXM" , "%"+expertName+"%" );
        }
        if( !StringUtils.isBlank(expertType) ){
            query.setParameter( "ZJLX","%"+expertType+"%" );
        }
        if( !StringUtils.isBlank(expertField) ){
            query.setParameter( "ZJLY","%"+expertField+"%" );
        }
        List<ExpertEntity>  expertEntities = query.getResultList();
        return expertEntities;
    }
}
