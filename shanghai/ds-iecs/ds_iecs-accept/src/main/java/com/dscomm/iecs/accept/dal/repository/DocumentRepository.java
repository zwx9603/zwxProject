package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:警情文书 数据库持久层服务
 */
@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, String> {

    /**
     * 根据案件id(警情id)查询警情文书列表
     *
     * @param incidentId 案件id(警情id)
     * @return 警情文书列表
     */
    @Query(" select t from DocumentEntity t   where  t.valid = 1   and t.incidentId = ?1  order by  t.feedbackTime asc    ")
    List<DocumentEntity> findDocumentByIncidentId(String incidentId );


    /**
     * 根据案件id(警情id)查询警情文书列表
     *
     * @param incidentId 案件id(警情id)
     * @return 警情文书列表
     */
    @Query(" select t from DocumentEntity t   where  t.valid = 1   and t.incidentId = ?1 and t.typeSubitemCode = ?2  order by  t.feedbackTime asc    ")
    List<DocumentEntity> findDocumentByIncidentIdAndType(String incidentId , String type );

    /**
     * 根据案件id(警情id)查询警情文书列表
     *
     * @param incidentId 案件id(警情id)
     * @return 警情文书列表
     */
    @Query(" select t from DocumentEntity t   where  t.valid = 1   and t.incidentId = ?1 and t.feedbackOrganizationId = ?2  order by  t.feedbackTime asc    ")
    List<DocumentEntity> findDocumentByIncidentIdAndOrganizationId(String incidentId , String organizationId );



    /**
     * 根据案件id(警情id)查询警情文书列表
     *
     * @param incidentId 案件id(警情id)
     * @return 警情文书列表
     */
    @Query(" select t from DocumentEntity t   where  t.valid = 1   and t.incidentId = ?1 and t.feedbackOrganizationId = ?2  and t.typeSubitemCode = ?3 order by  t.feedbackTime asc    ")
    List<DocumentEntity> findDocumentByIncidentIdAndOrganizationIdAndType(String incidentId , String organizationId , String type );


    /**
     * 根据案件id(警情id)文书参与单位Id
     *
     * @param incidentId 案件id(警情id)
     * @return 警情文书列表
     */
    @Query(" select distinct  t.feedbackOrganizationId from DocumentEntity t   where  t.valid = 1   and t.incidentId = ?1    ")
    List<String> findDocumentOrganizationIdByIncidentId(String incidentId   );
}
