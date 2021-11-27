package com.dscomm.iecs.basedata.dal.repository;

import com.dscomm.iecs.basedata.dal.po.OrgRelationshipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:机构转警及其他机构关系 数据库持久层服务
 */
@Repository
public interface OrgRelationshipRepository extends JpaRepository<OrgRelationshipEntity, String> {


    /**
     *  获得全部对应关系
     *
     * @return
     */
    @Query(" select t  from OrgRelationshipEntity t where  t.valid = 1 order by  t.fireDeptId asc , t.transferType asc     ")
    List<OrgRelationshipEntity> findOrgRelationshipAll ( );

    /**
     *  获得根据消防机构获得对应关系
     *
     * @return
     */
    @Query(" select t  from OrgRelationshipEntity t where  t.valid = 1 and t.fireDeptId = ?1 order by  t.fireDeptId asc , t.transferType asc     ")
    List<OrgRelationshipEntity> findOrgRelationshipAll ( String fireDeptId  );


    /**
     *  获得根据ids集合获得全部对应关系
     *
     * @return
     */
    @Query(" select t  from OrgRelationshipEntity t where  t.valid = 1 and t.id in ( ?1 )       ")
    List<OrgRelationshipEntity> findOrgRelationship ( List<String> ids );

    /**
     *  获得消防机构id 类型获得对应关系
     *
     * @return
     */
    @Query(" select t  from OrgRelationshipEntity t where  t.valid = 1  and t.fireDeptId = ?1 and t.transferType = ?2      ")
    OrgRelationshipEntity findOrgRelationship  (  String fireDeptId , String transferType );


    /**
     *  获得转警单位编码/id 类型获得 对应关系
     *
     * @return
     */
    @Query(" select t  from OrgRelationshipEntity t where  t.valid = 1  and t.transferDeptCode = ?1 and t.transferType = ?2      ")
    OrgRelationshipEntity findOrgRelationshipTransferDept  (  String transferDeptCode , String transferType );

}
