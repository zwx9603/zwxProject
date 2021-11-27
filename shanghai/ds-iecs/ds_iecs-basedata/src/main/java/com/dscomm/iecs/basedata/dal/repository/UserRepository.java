package com.dscomm.iecs.basedata.dal.repository;

import com.dscomm.iecs.basedata.dal.po.SystemUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 描述:用户信息
 *
 * @author YangFuxi
 * Date Time 2019/8/30 15:44
 */
public interface UserRepository extends JpaRepository<SystemUserEntity, String> {
    /**
     * 根据账号获取用户信息
     *
     * @param systemName 系统账号
     * @return 返回用户信息
     */
    @Query("select" +
            "  t.id,t.userCode,t.name,p.id, '' ," +
            "  p.id , p.name,org.id,org.organizationCode,org.organizationTypeCode, " +
            "  org.organizationNatureCode, org.searchPath,org.organizationParentId, org.districtCode, org.organizationName ," +
            "  org.organizationShortName ,t.password , org.organizationNatureCode , org.organizationOrderNum , parent.organizationName " +
            " from SystemUserEntity t " +
            " left join PersonEntity p on p.id=t.personId " +
            " left join OrganizationEntity org ON org.id=t.organizationCode " +
            " left join OrganizationEntity parent ON parent.id=org.organizationParentId " +
            " left join UserRoleEntity role on role.userId=t.id where t.userCode = ?1 ")
    List<Object[]> findUserInfoBySystemName(String systemName);



    /**
     * 获取用户角色
     *
     * @param systemName 登录账号
     * @return 返回用户角色集合
     */
    @Query("select role.id,role.roleCode,role.name from SystemUserEntity t " +
            " left join UserRoleEntity s on s.userId = t.id " +
            " left join SystemRoleEntity role on s.roleId = role.roleCode " +
            " where t.userCode = ?1")
    List<Object[]> getUserRoles(String systemName);







    /**
     * 根据角色ids 集合获得 某类角色用户账号
     *
     * @param roleIds 角色ids
     * @return 返回用户信息
     */
    @Query("select" +
            "   t.userCode " +
            " from SystemUserEntity t " +
            " left join UserRoleEntity role on role.userId=t.id where role.roleId in ( ?1   ) ")
    List<String> findAccountByRoleIds ( List<String> roleIds );





}
