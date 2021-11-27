package com.dscomm.iecs.out.dal.repository;

import com.dscomm.iecs.basedata.dal.po.OrganizationEntity;
import com.dscomm.iecs.out.dal.po.QueryAuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/4/26 14:34
 * @Describe
 */
@Repository
public interface OrganizationOutRepository extends JpaRepository<OrganizationEntity, String> {

    /**
     * 根据跟机构id获取下属机构id列表
     * @param rootOrgId
     * @return
     */
    @Query("select t.id from OrganizationEntity t where t.valid=1 and t.searchPath like %?1%")
    List<String>findOrgIdBySearchPath(String rootOrgId);
}
