package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.CombatUnitItemEntity;
import com.dscomm.iecs.accept.dal.po.CombatUnitEntity;
import com.dscomm.iecs.accept.dal.repository.CombatUnitRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.CombatUnitInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.CombatUnitQueryInputInfo;
import com.dscomm.iecs.accept.service.CombatUnitService;
import com.dscomm.iecs.accept.service.bean.CombatUnitBean;
import com.dscomm.iecs.accept.service.bean.CombatUnitItemBean;
import com.dscomm.iecs.accept.utils.transform.IncidentTransformUtil;
import com.dscomm.iecs.agent.service.UserService;
import com.dscomm.iecs.base.graphql.typebean.PaginationBean;
import com.dscomm.iecs.basedata.dal.po.EquipmentVehicleEntity;
import com.dscomm.iecs.basedata.graphql.typebean.EquipmentVehicleBean;
import com.dscomm.iecs.basedata.graphql.typebean.OrganizationBean;
import com.dscomm.iecs.basedata.graphql.typebean.SystemConfigurationBean;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.basedata.service.SystemConfigurationService;
import com.dscomm.iecs.basedata.utils.EquipmentTransformUtil;
import org.mx.StringUtils;
import org.mx.dal.Pagination;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

/**
 * 描述:作战单元服务
 * author:YangFuXi
 * Date:2021/6/8 Time:17:04
 **/
@Component
public class CombatUnitServiceImpl implements CombatUnitService {
    private static final Logger logger=LoggerFactory.getLogger(CombatUnitServiceImpl.class);
    private CombatUnitRepository combatUnitRepository;
    private GeneralAccessor accessor;
    private UserService userService;
    private DictionaryService dictionaryService;
    private OrganizationService organizationService;
    private List<String> dics;
    @PersistenceContext
    private EntityManager entityManager;
    private SystemConfigurationService systemConfigurationService;

    @Autowired
    public CombatUnitServiceImpl(CombatUnitRepository combatUnitRepository, @Qualifier("generalAccessor") GeneralAccessor accessor, UserService userService, DictionaryService dictionaryService, OrganizationService organizationService, EntityManager entityManager, SystemConfigurationService systemConfigurationService) {
        this.combatUnitRepository = combatUnitRepository;
        this.accessor = accessor;
        this.userService = userService;
        this.dictionaryService = dictionaryService;
        this.organizationService = organizationService;
        this.entityManager = entityManager;
        this.systemConfigurationService = systemConfigurationService;
        dics = new ArrayList<>(Arrays.asList("WLCLLX", "WLCLDJ", "WLCLZT", "YS", "JLDW", "WLRYZW", "QCLX", "PMLX","WLCLXZ"));
    }

    @Transactional
    @Override
    public CombatUnitBean saveOrUpdateCombatUnit(CombatUnitInputInfo inputInfo) {
        CombatUnitBean combatUnitBean=null;
        try {
            //获取用户信息
            UserInfo userInfo = userService.getUserInfo();
            if (inputInfo!=null){
                if (userInfo!=null){
                    if (StringUtils.isBlank(inputInfo.getOrgId())){
                        inputInfo.setOrgId(userInfo.getOrgId());
                    }
                    if (StringUtils.isBlank(inputInfo.getOrgName())){
                        inputInfo.setOrgName(userInfo.getOrgName());
                    }
                }
                CombatUnitEntity combatUnitEntity=null;
                List<CombatUnitItemEntity> items=new ArrayList<>();
                List<String> vehicleIds = inputInfo.getVehicleIds();
                if (vehicleIds==null){
                    vehicleIds=new ArrayList<>();
                }
                List<CombatUnitItemBean> itemBeans=new ArrayList<>();
                //新增
                if (StringUtils.isBlank(inputInfo.getId())){
                    combatUnitEntity=new CombatUnitEntity();
                    transform(inputInfo, userInfo, combatUnitEntity);
                    combatUnitEntity.setValid(1);
                    accessor.save(combatUnitEntity);
                }else {
                 //删除
                    combatUnitEntity = combatUnitRepository.findById(inputInfo.getId()).orElse(null);
                    if (combatUnitEntity!=null){
                        List<CombatUnitItemEntity> ret = combatUnitRepository.findCombatUnitDetailsEntities(combatUnitEntity.getId());
                        if (inputInfo.getValid()==0){
                            //删除
                            combatUnitEntity.setValid(0);
                            accessor.save(combatUnitEntity);
                            //删除明细

                            if (ret!=null&&!ret.isEmpty()){
                                for (CombatUnitItemEntity entity : ret) {
                                    entity.setValid(0);
                                }
                                items.addAll(ret);
                            }
                            vehicleIds.clear();
                        }else {
                            //更新
                            transform(inputInfo, userInfo, combatUnitEntity);
                            if (ret!=null&&!ret.isEmpty()){
                                for (CombatUnitItemEntity entity : ret) {
                                    if (!vehicleIds.contains(entity.getId())) {
                                        entity.setValid(0);
                                        items.add(entity);
                                    }else {
                                        vehicleIds.remove(entity.getId());
                                    }
                                }

                            }
                        }

                    }else {
                     vehicleIds.clear();
                    }


                }
                if (!vehicleIds.isEmpty()){
                    for (String vehicleId : vehicleIds) {
                        CombatUnitItemEntity entity=new CombatUnitItemEntity();
                        entity.setValid(1);
                        entity.setCombatUnitId(combatUnitEntity.getId());
                        entity.setVehicleId(vehicleId);
                        items.add(entity);
                    }
                }
                if (items!=null&&!items.isEmpty()){
                    accessor.save(items);
                }
                if (combatUnitEntity!=null){
                    combatUnitBean = transform(combatUnitEntity);
                    //组装明细
                    Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
                    //机构数据
                    Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();
                    List<Object[]> res = combatUnitRepository.findCombatUnitItemEntityByCombatUnitId(combatUnitBean.getId());
                    Set<String> orgIdSet=new HashSet<>();;
                    if (res!=null&&!res.isEmpty()){
                        //获取车辆可用状态
                        List<String> status=new ArrayList<>();
                        SystemConfigurationBean configValue = systemConfigurationService.getSystemConfigByConfigType("vehicleDispatchStatus");
                        if (configValue!=null&&!StringUtils.isBlank(configValue.getConfigValue())){
                            String[] split = configValue.getConfigValue().split(",");
                            if (split!=null&&split.length>0){
                                status=Arrays.asList(split);
                            }
                        }

                        for (Object[] objects : res) {
                            CombatUnitItemBean bean = transformCombatUnitItemBean(dicsMap, organizationNameMap, status, objects);
                            itemBeans.add(bean);
                            if (bean.getVehicleBean()!=null){
                                orgIdSet.add(bean.getVehicleBean().getOrganizationId());
                            }
                        }

                    }
                    combatUnitBean.setVehicleNum(itemBeans.size());
                    combatUnitBean.setOrgNum(orgIdSet.size());
                    combatUnitBean.setItemBeans(itemBeans);
                }
            }
            return combatUnitBean;
        }catch (Exception ex){
            logger.error("fail to saveOrUpdateCombatUnit ",ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_BATUNIT_FAIL);
        }
    }

    private CombatUnitItemBean transformCombatUnitItemBean(Map<String, Map<String, String>> dicsMap, Map<String, String> organizationNameMap, List<String> status, Object[] objects) {
        CombatUnitItemEntity item= (CombatUnitItemEntity) objects[0];
        EquipmentVehicleEntity vehicle= (EquipmentVehicleEntity) objects[1];
        CombatUnitItemBean bean=new CombatUnitItemBean();
        bean.setCombatUnitId(item.getCombatUnitId());
        bean.setId(item.getId());
        bean.setValid(item.getValid());
        bean.setVehicleId(item.getVehicleId());
        if (vehicle!=null){
            EquipmentVehicleBean vehicleBean = EquipmentTransformUtil.transform(vehicle, dicsMap, organizationNameMap);
            bean.setVehicleBean(vehicleBean);
            if (!StringUtils.isBlank(vehicleBean.getVehicleStatusCode())&&status.contains(vehicleBean.getVehicleStatusCode())){
                bean.setAvailable(1);
            }
        }
        return bean;
    }


    @Transactional(readOnly = true)
    @Override
    public PaginationBean<CombatUnitBean> findCombatUnitList(CombatUnitQueryInputInfo inputInfo) {
        try {
            PaginationBean<CombatUnitBean> result=new PaginationBean<>();
            String sql="select t ";
            StringBuilder condition=new StringBuilder("from CombatUnitEntity t where t.valid=1 ");
            Map<String, OrganizationBean> organizationMap = organizationService.findOrganizationAll();
            if (inputInfo!=null){
                if (!StringUtils.isBlank(inputInfo.getKeyWord())){
                    condition.append(" and (t.unitName like '%").append(inputInfo.getKeyWord()).append("%' or t.content like '%s").append(inputInfo.getKeyWord()).append("%') ");
                }
                if (!StringUtils.isBlank(inputInfo.getOrgId())){
                    OrganizationBean org = organizationMap.get(inputInfo.getOrgId());
                    if (org!=null){
                        if (inputInfo.getScope()!=null&&inputInfo.getScope()==1){
                            condition.append(" and t.orgId in (select org.id from OrganizationEntity org where org.searchPath like '").append(org.getSearchPath()).append("%') ");
                        }else {
                            condition.append(" and t.orgId='").append(inputInfo.getOrgId()).append("' ");
                        }
                    }
                }
                if (!StringUtils.isBlank(inputInfo.getUnitPersonName())){
                    condition.append(" and t.unitPersonName='").append(inputInfo.getUnitPersonName()).append("' ");
                }
            }
            Query totalQuery = entityManager.createQuery("select count(t.id) " + condition.toString());
            int total = Integer.valueOf(IncidentTransformUtil.toString(totalQuery.getSingleResult()));
            Pagination pagination=new Pagination();
            pagination.setTotal(total);
            condition.append(" order by t.updatedTime desc ");
            List<CombatUnitEntity> list=null;
            sql=sql+condition.toString();
            if (inputInfo.getPagination()!=null){
                Query query = entityManager.createQuery(sql );
                query.setFirstResult((inputInfo.getPagination().getPage()-1)*inputInfo.getPagination().getPage());
                query.setMaxResults(inputInfo.getPagination().getSize());
                list=query.getResultList();
                pagination.setPage(inputInfo.getPagination().getPage());
                pagination.setSize(inputInfo.getPagination().getSize());
            }else {
                if (total<=1000){
                    Query query = entityManager.createQuery(sql );
                    list=query.getResultList();
                }else {
                    int i=0;
                    list=new ArrayList<>();
                    while (i<total){
                        Query query = entityManager.createQuery(sql );
                        query.setFirstResult(i);
                        query.setMaxResults(1000);
                        List<CombatUnitEntity> temp=query.getResultList();
                        if (temp!=null&&temp.size()>0){
                            list.addAll(temp);
                        }
                        i=i+1000;
                    }
                }
            }
            //查询明细
            if (list!=null&&list.size()>0){
                List<String> ids=new ArrayList<>();
                List<CombatUnitBean> combatUnitBeans=new ArrayList<>();
                for (CombatUnitEntity combatUnitEntity : list) {
                    CombatUnitBean combatUnitBean = transform(combatUnitEntity);
                    combatUnitBeans.add(combatUnitBean);
                    ids.add(combatUnitBean.getId());
                }
                List<Object[]> itemResult=null;
                if (ids.size()>1000){
                    itemResult=new ArrayList<>();
                    int i=0,j=0;
                    while (i<ids.size()){
                        j=i+1000;
                        if (j>ids.size()){
                            j=ids.size();
                        }
                        List<Object[]> ret = combatUnitRepository.findCombatUnitItemEntityByCombatUnitIds(ids.subList(i, j));
                        if (ret!=null&&!ret.isEmpty()){
                            itemResult.addAll(ret);
                        }
                        i=j;
                    }
                }else {
                    itemResult=combatUnitRepository.findCombatUnitItemEntityByCombatUnitIds(ids);
                }
                if (itemResult!=null&&itemResult.size()>0){
                    Map<String,List<CombatUnitItemBean>> map=new HashMap<>();
                    Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
                    //机构数据
                    Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();
                    //获取车辆可用状态
                    List<String> status=new ArrayList<>();
                    SystemConfigurationBean configValue = systemConfigurationService.getSystemConfigByConfigType("vehicleDispatchStatus");
                    if (configValue!=null&&!StringUtils.isBlank(configValue.getConfigValue())){
                        String[] split = configValue.getConfigValue().split(",");
                        if (split!=null&&split.length>0){
                            status=Arrays.asList(split);
                        }
                    }
                    for (Object[] objects : itemResult) {
                        CombatUnitItemBean bean = transformCombatUnitItemBean(dicsMap, organizationNameMap, status, objects);
                        List<CombatUnitItemBean> itemBeans = map.get(bean.getCombatUnitId());
                        if (itemBeans==null){
                            itemBeans=new ArrayList<>();
                        }
                        itemBeans.add(bean);
                        map.put(bean.getCombatUnitId(),itemBeans);
                    }
                    Set<String> orgIdSet=new HashSet<>();
                    for (CombatUnitBean combatUnitBean : combatUnitBeans) {
                        List<CombatUnitItemBean> itemBeans = map.get(combatUnitBean.getId());
                        orgIdSet.clear();
                        if (itemBeans!=null){
                            for (CombatUnitItemBean itemBean : itemBeans) {
                                if (itemBean.getVehicleBean()!=null){
                                    orgIdSet.add(itemBean.getVehicleBean().getOrganizationId());
                                }
                            }
                        }else {
                            itemBeans=new ArrayList<>();
                        }
                        combatUnitBean.setItemBeans(itemBeans);
                        combatUnitBean.setOrgNum(orgIdSet.size());
                        combatUnitBean.setVehicleNum(itemBeans.size());
                    }
                    itemResult.clear();
                    list.clear();
                }
                result.setList(combatUnitBeans);
            }
            result.setPagination(pagination);
            return result;
        }catch (Exception ex){
            logger.error("find combatUnit fail",ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_BATUNIT_FAIL);
        }
    }

    private CombatUnitBean transform(CombatUnitEntity combatUnitEntity) {
        CombatUnitBean combatUnitBean=new CombatUnitBean();
        combatUnitBean.setAuthorAccount(combatUnitEntity.getAuthorAccount());
        combatUnitBean.setAuthorName(combatUnitEntity.getAuthorName());
        combatUnitBean.setContent(combatUnitEntity.getContent());
        combatUnitBean.setId(combatUnitEntity.getId());
        combatUnitBean.setOrgId(combatUnitEntity.getOrgId());
        combatUnitBean.setOrgName(combatUnitEntity.getOrgName());
        combatUnitBean.setUnitName(combatUnitEntity.getUnitName());
        combatUnitBean.setUnitPersonId(combatUnitEntity.getUnitPersonId());
        combatUnitBean.setUnitPersonName(combatUnitEntity.getUnitPersonName());
        combatUnitBean.setUnitPersonPhone(combatUnitEntity.getUnitPersonPhone());
        combatUnitBean.setValid(combatUnitEntity.getValid());
        return combatUnitBean;
    }

    private void transform(CombatUnitInputInfo inputInfo, UserInfo userInfo, CombatUnitEntity combatUnitEntity) {
        if (userInfo!=null){
            combatUnitEntity.setAuthorAccount(userInfo.getAccount());
            combatUnitEntity.setAuthorName(userInfo.getUserName());
        }
        combatUnitEntity.setContent(inputInfo.getContent());
        combatUnitEntity.setOrgId(inputInfo.getOrgId());
        combatUnitEntity.setOrgName(inputInfo.getOrgName());
        combatUnitEntity.setUnitName(inputInfo.getUnitName());
        combatUnitEntity.setUnitPersonId(inputInfo.getUnitPersonId());
        combatUnitEntity.setUnitPersonName(inputInfo.getUnitPersonName());
        combatUnitEntity.setUnitPersonPhone(inputInfo.getUnitPersonPhone());
    }
}
