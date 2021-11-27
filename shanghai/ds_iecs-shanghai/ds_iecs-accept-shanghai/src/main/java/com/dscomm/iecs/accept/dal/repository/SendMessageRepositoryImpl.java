package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.SendMessageEntity;
import org.apache.logging.log4j.util.Strings;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class SendMessageRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<SendMessageEntity> queryByInfo(String ldmc, String ssjgbh){
        String headSql = " select t from SendMessageEntity t where  1 = 1 ";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(headSql);
        if (Strings.isNotBlank(ldmc)){
            stringBuilder.append(" and t.ldmc like :ldmc ");
        }
        if (Strings.isNotBlank(ssjgbh)){
            stringBuilder.append(" and t.ssjgbh = :ssjgbh ");
        }
        Query query = entityManager.createQuery(stringBuilder.toString());
        if (Strings.isNotBlank(ssjgbh)){
            query.setParameter("ssjgbh", ssjgbh );
        }
        if (Strings.isNotBlank(ldmc)){
            query.setParameter("ldmc", "%" + ldmc + "%" );
        }
        return query.getResultList();
    }
}
