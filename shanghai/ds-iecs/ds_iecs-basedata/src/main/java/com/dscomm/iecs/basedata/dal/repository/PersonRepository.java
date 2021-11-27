package com.dscomm.iecs.basedata.dal.repository;

import com.dscomm.iecs.basedata.dal.po.PersonEntity;
import com.dscomm.iecs.basedata.dal.po.PersonMailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 描述 人员通讯录 数据底层服务
 */
@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, String> {

    /**
     * 根据人员id 获取人员通讯录
     *
     * @param organizationId 机构id
     * @return 人员
     */
    @Query(" select t  from PersonEntity t where  t.valid = 1 and t.actualOrganizationId = ?1  ")
    List<PersonEntity> findPersonByOrganizationId(String organizationId);

    /**
     * 根据人员id 获取人员通讯录
     *
     * @param organizationId 机构id
     * @return 人员
     */
    @Query(" select t  from PersonEntity t where  t.valid = 1 and t.actualOrganizationId = ?1  and t.name like ?2  ")
    List<PersonEntity> findPersonByOrganizationIdAndKeyword(String organizationId , String keyword );

    /**
     * 根据人员id 获取人员通讯录
     *
     * @param personId 人员id
     * @return 人员通讯录
     */
    @Query(" select t  from PersonMailEntity t where  t.valid = 1 and t.personId = ?1  ")
    PersonMailEntity findPersonMailByPersonId(String personId);


    /**
     * 根据 机构  匹配 获得人员通信录
     *
     * @return 人员通讯录
     */
    @Query(" select t  from PersonMailEntity t , PersonEntity person where t.personId = person.id   " +
            "  and person.valid = 1 and person.actualOrganizationId = ?1    ")
    List<PersonMailEntity> findPersonMailByOrganizationId (String organizationId  );

    /**
     * 根据 机构 人员名称模糊匹配 获得人员通信录
     *
     * @return 人员通讯录
     */
    @Query(" select t  from PersonMailEntity t , PersonEntity person where t.personId = person.id  " +
            "  and t.valid = 1 and person.actualOrganizationId = ?1  and person.name like ?2  ")
    List<PersonMailEntity> findPersonMailByOrganizationIdAndKeyword(String organizationId , String keyword );

}
