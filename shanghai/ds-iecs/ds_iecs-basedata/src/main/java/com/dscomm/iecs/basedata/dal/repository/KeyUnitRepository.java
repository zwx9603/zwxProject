package com.dscomm.iecs.basedata.dal.repository;

import com.dscomm.iecs.basedata.dal.po.KeyUnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:重点单位 数据库持久层服务
 *
 */
@Repository
public interface KeyUnitRepository extends JpaRepository<KeyUnitEntity, String> {

    @Query(" select max (t.updatedTime) from KeyUnitEntity t        ")
    Long findDataLatestTime(  Long lastTime ) ;

    @Query(" select t.id    from KeyUnitEntity t   where t.updatedTime >?1 and  t.updatedTime <=?2  ")
    List<String>   findDataLatestTime(Long lastTime , Long  latestTime ) ;


    /**
     * 缓存全部重点单位信息
     */
    @Query(" select t from KeyUnitEntity t  where   t.id in ( ?1 )   ")
    List<KeyUnitEntity> findOrganizationCache( List< String> keyUnitIds );


    /**
     * 缓存全部重点单位信息
     */
    @Query(" select t from KeyUnitEntity t  where t.valid = 1  and  t.id in ( ?1 )  ")
    List<KeyUnitEntity> findOrganizationByKeyUnitIds( List< String> keyUnitIds);

    /**
     * 根据重点单位电话 模糊 获得重点单位信息
     * @return
     */
    @Query(" select t from KeyUnitEntity t   where  t.valid = 1   and t.unitPhone = ?1 or  t.unitPhone = ?2  order by  t.unitFoundingTime asc  " )
    List<KeyUnitEntity> findKeyUnitByPhone(String unitPhone , String unitPhoneLike ) ;


    /**
     *  获得全部重点单位信息
     * @return
     */
    @Query(" select t from KeyUnitEntity t   where  t.valid = 1    order by  t.unitFoundingTime asc  " )
    List<KeyUnitEntity> findKeyUnitAll(  ) ;

    /**
     * 根据条件获得重点单位信息
     * @return
     */
    @Query(" select t from KeyUnitEntity t   where  t.valid = 1    order by  t.unitFoundingTime asc  " )
    List<KeyUnitEntity> findKeyUnitCondition(  ) ;

    /**
     * 根据条件获得重点单位信息
     * @return
     */
    List<KeyUnitEntity> findKeyUnitCondition(  String keyword ,String searchPath) ;

    /**
     * 根据预案id 获得重点单位信息
     * @return
     */
    @Query(" select t from KeyUnitEntity t , PlanEntity p where t.id = p.keyUnitId and  t.valid = 1  and p.valid = 1 and p.id = ?1    " )
    KeyUnitEntity findKeyUnitByPlanId(  String planId ) ;


    /**
     * 缓存全部重点单位信息
     */
    @Query(" select t from KeyUnitEntity t where t.jurisdictionOrganizationId in ( select o.id from OrganizationEntity o where o.searchPath like ?1) ")
    List<KeyUnitEntity> findOrganizationCache(String searchPath);





}

