package com.dscomm.iecs.basedata.dal.repository;

import com.dscomm.iecs.basedata.dal.po.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述 机构数据底层服务
 */
@Repository
public interface OrganizationRepository extends JpaRepository<OrganizationEntity, String> {

    @Query(" select max (t.updatedTime) from OrganizationEntity t      ")
    Long findDataLatestTime( Long lastTime ) ;


    @Query(" select t  from OrganizationEntity t  where  t.valid = 1  " +
            "  order by  t.organizationOrderNum asc    ")
    List<OrganizationEntity>  findDataLatestTime( ) ;

    /**
     * 缓存全部机构信息  id 与 名称对应关系（ 有效 无效 ）
     */
    @Query(" select t.id , t.organizationName  from OrganizationEntity t  where t.updatedTime >?1 and t.updatedTime <=?2 " +
            "   order by  t.organizationOrderNum asc   ")
    List<Object[]> findOrganizationIdNameCache( Long lastTime , Long  latestTime );

    /**
     *  某类机构信息
     */
    @Query(" select t from OrganizationEntity t where t.valid = 1  and t.searchPath like ?1    order by  t.organizationOrderNum asc   ")
    List<OrganizationEntity> findOrganizationNatureAll( String searchPath  ) ;


    /**
     * 某类机构信息
     */
    List<OrganizationEntity> findOrganizationNatureAll( String searchPath  , String nature  ) ;

    /**
     * 根据辖区查询码、关键字 模糊匹配机构名称 机构地址 获得机构信息
     */
    List<OrganizationEntity> findOrganizationCondition(String searchPath, String keyword, Boolean whetherOnlySquadron);

    /**
     * 根据父机构id 获得下级机构信息
     *
     * @param parentId 父级机构id
     * @return 下级机构列表
     */
    @Query(" select t  from OrganizationEntity t where  t.valid = 1 and t.organizationParentId = ?1  order by  t.organizationOrderNum asc  ")
    List<OrganizationEntity> findChildrenOrganizationByParentId(String parentId);


    /**
     * 根据id列表 获得查询码
     *
     * @param organizationId id
     * @return 查询码列表
     */
    @Query(" select t.searchPath  from OrganizationEntity t where  t.valid = 1 and t.id = ?1   ")
     String  findSearchPathById ( String  organizationId );

    /**
     * 根据id列表 获得查询码列表
     *
     * @param organizationIds id列表
     * @return 查询码列表
     */
    @Query(" select t.searchPath  from OrganizationEntity t where  t.valid = 1 and t.id in (?1)   ")
    List<String> findSearchPathByIds(List<String> organizationIds);

    /**
     * 根据机构ids获得 机构列表
     *
     * @param organizationsByIds id列表
     * @return 机构列表
     */
    @Query(" select t  from OrganizationEntity t where  t.valid = 1 and t.id in (?1)   order by  t.organizationOrderNum asc ")
    List<OrganizationEntity> findOrganizationsByIds(List<String> organizationsByIds );


    /**
     * 根据根机构id，机构名称，机构类型查询机构id
     * */
    @Query("select t.id from OrganizationEntity t where t.valid = 1 and t.organizationName like ?1 and t.searchPath like ?2 and t.organizationNatureCode like ?3")
    List<String> findOrgIdByNameAndRootIdAndNature(String name,String rootId,String natureCode);


    @Query(value = "SELECT t.id " +
            "FROM jgxx_xfjg t WHERE t.yxx = 1 " +
            "ORDER BY coalesce (( SELECT b.px FROM jgxx_xfjg b WHERE b.yxx = 1 AND b.ID = t.sjjgid AND b.xfjyjgxzdm LIKE'%05%%' ),t.px ) ASC," +
            "t.px ASC",nativeQuery = true)
    List<String> findOrgOrder();



}
