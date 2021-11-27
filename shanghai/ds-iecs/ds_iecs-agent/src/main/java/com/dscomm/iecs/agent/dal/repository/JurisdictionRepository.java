package com.dscomm.iecs.agent.dal.repository;

import com.dscomm.iecs.agent.dal.po.DistributeStrategy.AccountJurisdictionPO;
import com.dscomm.iecs.agent.dal.po.DistributeStrategy.AgentJurisdictionPO;
import com.dscomm.iecs.agent.dal.po.DistributeStrategy.DistributeCaseNaturePO;
import com.dscomm.iecs.agent.dal.po.DistributeStrategy.JurisdictionPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:辖区服务
 *
 * @author YangFuxi
 * Date Time 2019/9/18 20:16
 */
@Repository
public interface JurisdictionRepository extends JpaRepository<JurisdictionPO, String> {
    /**
     * 根据坐席获取接管单位 id 集合
     *
     * @param agentNum 坐席号
     * @return 返回单位code集合
     */
    @Query("select org.id ,t from AgentJurisdictionPO p, JurisdictionPO t ,OrganizationEntity org " +
            "where p.agentNum=?1 and t.id =p.jurisdictionId and t.valid=1 and t.deleteFlag=0 and" +
            " t.orgid=org.id and org.valid=1  ")
    List<Object[]> findOrgCodesByAgentJurisdictions(Integer agentNum);

    /**
     * 获取全部辖区对应的机构 id 集合
     *
     * @return 返回全部机构code集合
     */
    @Query("select t, org.id from JurisdictionPO t ,OrganizationEntity org where" +
            " t.valid=1 and t.deleteFlag=0  and t.orgid=org.id ")
    List<Object[]> findAllJurisdictionPOAndOrgCode();

    /**
     * 获取所有的acd警种
     *
     * @return 返回所有警种
     */
    @Query("select t.policeType from AcdEntity t ")
    List<String> findAllAcdJz();

    /**
     * 获取处警员账号接管辖区对应的机构代码
     *
     * @param accountNum 处警员账号
     * @return 返回机构代码集合
     */
    @Query("select org.id,t from AccountJurisdictionPO p,JurisdictionPO t ,OrganizationJurisdictionPO o ,OrganizationEntity org " +
            "where p.accountNum=?1 and t.id =p.jurisdictionId and t.valid=1 and t.deleteFlag=0 and" +
            " t.id=o.jurisdictionId and org.id=o.orgId and org.valid=1 ")
    List<Object[]> findAllOrgCodesByAccountNumJurisdictions(String accountNum);

    /**
     * 查询处警台与处警辖区的绑定关系
     *
     * @return 返回绑定关系集合
     */
    @Query("select t from AgentJurisdictionPO t,JurisdictionPO cjxq  where t.jurisdictionId=cjxq.id and cjxq.valid=1 and cjxq.deleteFlag=0")
    List<AgentJurisdictionPO> queryAllDistrictAgent();

    /**
     * 查询处警台与处警辖区的绑定关系
     *
     * @return 返回绑定关系集合
     */
    @Query("select xq.id,xq.name,xq.orgid,xqzx.agentNum from JurisdictionPO xq" +
            " left join AgentJurisdictionPO xqzx on xqzx.jurisdictionId = xq.id" +
            " where  xq.valid = 1 and xq.deleteFlag = 0")
    List<Object[]> findExistJurisdictionAndAgent();

    /**
     * 查询处警台与处警辖区的绑定关系
     *
     * @return 返回绑定关系集合
     */
    @Query("select t from AgentJurisdictionPO t,JurisdictionPO cjxq  where t.jurisdictionId=cjxq.id and cjxq.valid=1 and cjxq.deleteFlag=0 and t.jurisdictionId=?1 order by cjxq.id desc")
    List<AgentJurisdictionPO> queryAllDistrictAgentByJurisdictionNum(String jurisdictionNum);

    /**
     * 查询处警员与处警辖区的绑定关系
     *
     * @return 返回绑定关系集合
     */
    @Query("select t from AccountJurisdictionPO t order by t.jurisdictionId desc")
    List<AccountJurisdictionPO> queryAllDistrictLogin();


    /**
     * 查询处警台与案由的绑定关系
     *
     * @return 返回绑定关系集合
     */
    @Query("select t from DistributeCaseNaturePO t order by t.caseNatureId desc")
    List<DistributeCaseNaturePO> queryAllCaseNature();

    /**
     * 查询所有处警辖区
     *
     * @return 返回处警辖区集合
     */
    @Query("select t from JurisdictionPO t where t.valid=1 and t.deleteFlag=0 order by t.id desc")
    List<JurisdictionPO> queryAllJurisdiction();


    /**
     * 根据坐席获取接管案由列表
     *
     * @param agentNum 坐席号
     * @return 返回案由集合
     */
    @Query("select t.incidentType from AgentIncidentTypePO t where t.agentNum=?1 ")
    List<String> findAyByAgentNum(Integer agentNum);

    /**
     * 根据辖区号获取辖区信息
     *
     * @param jurisdictionNum 辖区编号
     * @return 返回辖区信息
     */
    @Query("select t from JurisdictionPO t where t.id=?1 ")
    JurisdictionPO findByJurisdictionNum(String jurisdictionNum);

    /**
     * 根据辖区编号获取对应的机构 id
     * @param jurisdictionNum 辖区编号
     * @return 机构编码
     */
    @Query("select org.id from JurisdictionPO p ,OrganizationEntity org where p.id=?1 and p.orgid=org.id")
    String findOrgCodeByJuristdictionNum(String jurisdictionNum);
}
