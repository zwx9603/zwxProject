package com.dscomm.iecs.basedata.service.impl;


import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.base.service.tree.TreeUtil;
import com.dscomm.iecs.basedata.dal.po.OrganizationEntity;
import com.dscomm.iecs.basedata.dal.repository.OrganizationRepository;
import com.dscomm.iecs.basedata.exception.BasedataException;
import com.dscomm.iecs.basedata.graphql.inputbean.OrganizationQueryInputInfo;
import com.dscomm.iecs.basedata.graphql.typebean.OrganizationBean;
import com.dscomm.iecs.basedata.graphql.typebean.SystemConfigurationBean;
import com.dscomm.iecs.basedata.service.*;
import com.dscomm.iecs.basedata.utils.OrganizationTransformUtil;
import com.dscomm.iecs.ext.organization.naturehead.ORGANIZATION_NATURE_HEAD_DSZD;
import com.dscomm.iecs.ext.organization.naturehead.ORGANIZATION_NATURE_HEAD_SJZD;
import com.dscomm.iecs.ext.organization.naturehead.ORGANIZATION_NATURE_HEAD_XJZD;
import com.dscomm.iecs.ext.organization.naturehead.ORGANIZATION_NATURE_HEAD_XXDD;
import org.apache.logging.log4j.util.Strings;
import org.mx.StringUtils;
import org.mx.dal.service.GeneralAccessor;
import org.mx.error.UserInterfaceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 描述：机构服务类实现
 */
@Component("organizationServiceImpl")
public class OrganizationServiceImpl implements OrganizationService {

    private static final Logger logger = LoggerFactory.getLogger(OrganizationServiceImpl.class);
    private Environment env;
    private LogService logService;
    private GeneralAccessor accessor;
    private DictionaryService dictionaryService;
    private OrganizationRepository organizationRepository;
    private SystemConfigurationService systemConfigurationService;
    private OrganizationOtherService organizationOtherService;
    private OrganizationCacheService organizationCacheService;

//    //设置机构数据缓存数据 机构id 与 机构名称  缓存  机构id  与 bean 的缓存
//    private static Map<String, String> organizationNameMap = Collections.synchronizedMap( new HashMap<>() ) ;
//    private static Map<String, OrganizationBean> organizationBeanMap = new HashMap<>();
//    private static OrganizationBean root = null ; //根机构信息
//    private static Map<String, String> organizationIdCodeMap = new HashMap<>(); //机构id与code Map
//    private static Map<String, String> organizationCodeIdMap = new HashMap<>(); //机构code与id  Map
//    private static List<OrganizationBean> organizationBeanTree = new ArrayList<>();

    private List<String> dics;

    /**
     * 默认的构造函数
     */
    @Autowired
    @Lazy(true)
    public OrganizationServiceImpl(Environment env, @Qualifier("generalAccessor") GeneralAccessor accessor, LogService logService,
                                   DictionaryService dictionaryService, OrganizationRepository organizationRepository,
                                   SystemConfigurationService systemConfigurationService, OrganizationOtherService organizationOtherService,
                                   OrganizationCacheService organizationCacheService) {
        this.accessor = accessor;
        this.env = env;
        this.logService = logService;
        this.dictionaryService = dictionaryService;
        this.organizationRepository = organizationRepository;
        this.systemConfigurationService = systemConfigurationService;
        this.organizationOtherService = organizationOtherService;
        this.organizationCacheService = organizationCacheService;

        dics = new ArrayList<>(Arrays.asList("JGLB", "JGLX", "JGXZ", "XZQX"));
    }


    private Long lastTime = 0l; //系统默认为0 加载全部

    /**
     * 根据上次数据最新时间  本次数据最新时间
     * 判断是否从数据库更新数据 如果本次数据最新时间 大于 上次数据时间 则需要更新数据
     */
    private void updateCacheOrganization() {
        logService.infoLog(logger, "service", "UpdateCacheDictionary", "service is started...");
        Long logStart = System.currentTimeMillis();

        Long latestTime = organizationRepository.findDataLatestTime(lastTime);
        latestTime = latestTime == null ? lastTime : latestTime;
        //判断是否需要更新数据
        if (latestTime > lastTime) {

            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);

            logService.infoLog(logger, "service", "UpdateCacheDictionary", " update cache data");
            //此处为增量更新数据
            logService.infoLog(logger, "repository", "findOrganizationCondition", "repository is started...");
            Long start = System.currentTimeMillis();

            List<OrganizationEntity> organizationCache = organizationRepository.findDataLatestTime();

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findOrganizationCondition", String.format("repository is finished,execute time is :%sms", end - start));

            if (organizationCache != null && organizationCache.size() > 0) {
                organizationCache.forEach(org -> {
                    if (org != null) {
                        //缓存机构ID-code对应关系
                        organizationCacheService.modifyOrgIdCodeCache(org.getId(), org.getOrganizationCode());
                        //缓存机构code-id对应关系
                        organizationCacheService.modifyOrgCodeIdCache(org.getOrganizationCode(), org.getId());
                        //缓存机构ID-name对应关系
                        organizationCacheService.modifyOrgIdNameCache(org.getId(), org.getOrganizationName());
                        //清除无效的机构缓存
                        if (!org.isValid()){
                            organizationCacheService.modifyOrgBeanCache("remove", org.getId(), null);
                        }
                    }
                });
                Map<String, String> organizationIdCodeMap = organizationCacheService.findOrgIdCodeCache();
                Map<String, String> organizationCodeIdMap = organizationCacheService.findOrgCodeIdCache();
                if (organizationIdCodeMap == null) {
                    organizationIdCodeMap = new HashMap<>();
                }
                if (organizationCodeIdMap==null){
                    organizationCodeIdMap=new HashMap<>();
                }
                //缓存机构信息
                for (OrganizationEntity organizationEntity : organizationCache) {
                    OrganizationBean bean = OrganizationTransformUtil.transform(organizationEntity, dicsMap, organizationIdCodeMap);
                    organizationCacheService.modifyOrgBeanCache("put",bean.getId(),bean);
                }
                //生成树状机构信息
//                List<String> nowOrgIds = new ArrayList<>();
                List<OrganizationBean> organizationBeanList = new ArrayList<>();
                List<OrganizationBean> organizationBeanList2 = new ArrayList<>();
                for (OrganizationEntity entity : organizationCache) {
                    if (entity != null && entity.isValid()) {
                        OrganizationBean bean = OrganizationTransformUtil.transform(entity, dicsMap, organizationIdCodeMap);
                        organizationBeanList.add(bean);
                        OrganizationBean bean2 = OrganizationTransformUtil.transform(entity, dicsMap, organizationIdCodeMap);
                        organizationBeanList2.add(bean2);

//                        nowOrgIds.add(bean.getId());
                    }

                }
//                Map<String, OrganizationBean> organizationBeanMap = organizationCacheService.findOrgBeanCache();
//                if (organizationBeanMap == null) {
//                    organizationBeanMap = new HashMap<>();
//                }
                //清除无效历史数据
//                if (organizationBeanMap.keySet() != null && organizationBeanMap.keySet().size() > 0) {
//                    for (String key : organizationBeanMap.keySet()) {
//                        if (!nowOrgIds.contains(key)) {
//                            organizationCacheService.modifyOrgBeanCache("remove", key, null);
//                        }
//                    }
//                }


                //更新树状机构信息
                List<OrganizationBean> organizationBeanTree = TreeUtil.bulidTree(organizationBeanList);
                organizationCacheService.modifyOrgTreeCache(organizationBeanTree);
                //构建机构树形结构
                if (organizationBeanList2 != null && organizationBeanList2.size() > 0) {
                    OrganizationBean root = null;
                    SystemConfigurationBean systemConfigurationBean = systemConfigurationService.getSystemConfigByConfigType("XFJG_ID");
                    if (systemConfigurationBean != null && Strings.isNotBlank(systemConfigurationBean.getConfigValue())) {
                        root = TreeUtil.bulidTree(systemConfigurationBean.getConfigValue(), organizationBeanList2);
                    }
                    if (root == null) {
                        root = organizationBeanTree.get(0);
                    }
                    organizationCacheService.modifyRootOrg(root);
                }
            }
////            //缓存机构信息
//            if (null != organizationCache && organizationCache.size() > 0) {
//
//                transform(organizationCache, organizationBeanMap, organizationIdCodeMap, organizationCodeIdMap, dicsMap);
//            }
//            //更新机构 id 与 名称关系
//            List<Object[]> organizationIdNameCache = organizationRepository.findOrganizationIdNameCache(lastTime, latestTime);
//            if (organizationIdNameCache != null && organizationIdNameCache.size() > 0) {
//                for (Object[] entity : organizationIdNameCache) {
//                    if (entity != null) {
//                        Object orgId = entity[0];
//                        String organizationId = (orgId == null) ? "" : orgId.toString();
//                        Object orgName = entity[1];
//                        String organizationName = (orgName == null) ? "" : orgName.toString();
//                        organizationNameMap.put(organizationId, organizationName);
//                    }
//
//                }
//            }

            lastTime = latestTime;
        }

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "UpdateCacheSystemConfiguration", String.format("service is finished,execute time is :%sms", logEnd - logStart));

    }


    /**
     * {@inheritDoc}
     *
     * @see OrganizationService#forceUpdateCacheOrganization()
     */
    @Transactional(readOnly = true)
    @Override
    public void forceUpdateCacheOrganization() {
        try {
            logService.infoLog(logger, "service", "forceUpdateCacheOrganization", "service is started...");
            Long logStart = System.currentTimeMillis();

            updateCacheOrganization();

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "forceUpdateCacheOrganization", String.format("service is finished,execute time is :%sms", logEnd - logStart));


        } catch (Exception ex) {
            logService.erorLog(logger, "service", "forceUpdateCacheOrganization", String.format("force cache Organization Map id name fail "), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_ORGANIZATION_FAIL);
        }
    }

//    private void transform(List<OrganizationEntity> organizations, Map<String, OrganizationBean> map, Map<String, String> idCodeMap,
//                           Map<String, String> codeIdMap,
//            /*Map<String, List<OrganizationBean>> temp ,*/ Map<String, Map<String, String>> dicsMap) {
//        if (organizations != null && organizations.size() > 0) {
//            Map<String, OrganizationEntity> pos = new HashMap<>();
//            organizations.forEach(po -> pos.put(po.getId(), po));
//            organizations.forEach(organization -> {
//                idCodeMap.put(organization.getId(), organization.getOrganizationCode());
//                codeIdMap.put(organization.getOrganizationCode(), organization.getId());
//                organizationNameMap.put(organization.getId(), organization.getOrganizationName()); // 缓存机构id 与 机构名称
//                if (organization != null) {
//                    if (organization.isValid()) {
//                        OrganizationBean bean = OrganizationTransformUtil.transform(organization, dicsMap, organizationIdCodeMap);
//                        if (null != bean) {
//                            if (!StringUtils.isBlank(bean.getParentId())) {
//                                OrganizationEntity organizationPO = pos.get(bean.getParentId());
//                                if (organizationPO != null) {
//                                    bean.setOrganizationParentCode(organizationPO.getOrganizationCode());
//                                }
//                            }
//                            map.put(bean.getId(), bean);
//                        }
//                    } else {
//                        map.remove(organization.getId());
//                    }
//                }
//            });
//
//        }
//    }


    /**
     * 递归组装业务单位组织机构树
     *
     * @param map                业务单位组织机构集合
     * @param parentOrganization 根节点
     */
    private void recursive(Map<String, List<OrganizationBean>> map, OrganizationBean parentOrganization) {
        if (map != null && parentOrganization != null) {
            List<OrganizationBean> children = map.get(parentOrganization.getId());
            parentOrganization.setChildren(children);
            if (children != null && children.size() > 0) {
                for (OrganizationBean child : children) {
                    recursive(map, child);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see OrganizationService#forceUpdateCacheOrganization()
     */
    @Transactional(readOnly = true)
    @Override
    public Map<String, String> findOrganizationNameMap() {
        try {

//            return organizationNameMap;
            Map<String, String> orgIdNameCache = organizationCacheService.findOrgIdNameCache();
            if (orgIdNameCache==null){
                orgIdNameCache=new HashMap<>();
            }
            return  orgIdNameCache ;

        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findOrganizationNameMap", String.format("find Organization id Name Map fail "), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_ORGANIZATION_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see OrganizationService#findOrganizationAll()
     */
    @Transactional(readOnly = true)
    @Override
    public Map<String, OrganizationBean> findOrganizationAll() {
        try {
//            return organizationBeanMap;
            Map<String, OrganizationBean> orgBeanCache = organizationCacheService.findOrgBeanCache();
            if (orgBeanCache==null){
                orgBeanCache=new HashMap<>();
            }
            return orgBeanCache;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findOrganizationAll", String.format("find all Organization  fail "), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_ORGANIZATION_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see OrganizationService#findTreeOrganization()
     */
    @Transactional(readOnly = true)
    @Override
    public List<OrganizationBean> findTreeOrganization() {
        try {

//            return organizationBeanTree;
            List<OrganizationBean> orgTreeCache = organizationCacheService.findOrgTreeCache();
            if (orgTreeCache==null){
                orgTreeCache=new ArrayList<>();
            }
            return orgTreeCache;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findOrganizationAll", String.format("find all Organization  fail "), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_ORGANIZATION_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see OrganizationService#findOrganizationAll()
     */
    @Transactional(readOnly = true)
    @Override
    public List<OrganizationBean> findOrganizationNatureAll(String organizationId, Integer nature) {
        try {
            logService.infoLog(logger, "service", "findOrganizationNatureAll", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<OrganizationBean> res = new ArrayList<>();

            //  -1 0 总队 1 支队 2 大队 3救援站（中队） -1查询全部  其他查询全部
            String natureLike = "";
            if (null == nature) {
                natureLike = "";
            } else if (0 == nature) {
                natureLike = ORGANIZATION_NATURE_HEAD_SJZD.getCode() + "%"; //0 总队
            } else if (1 == nature) {
                natureLike = ORGANIZATION_NATURE_HEAD_DSZD.getCode() + "%"; //1 支队
            } else if (2 == nature) {
                natureLike = ORGANIZATION_NATURE_HEAD_XXDD.getCode() + "%"; //大队
            } else if (3 == nature) {
                natureLike = ORGANIZATION_NATURE_HEAD_XJZD.getCode() + "%"; //救援站（中队）
            }

            List<OrganizationEntity> organizationList = null;

            String searchPath = "%%";
            if (Strings.isNotBlank(organizationId)) {
                OrganizationEntity organization = accessor.getById(organizationId, OrganizationEntity.class);
                if (organization != null && organization.isValid() && Strings.isNotBlank(organization.getSearchPath())) {
                    searchPath = organization.getSearchPath() + "%";
                }
            }


            logService.infoLog(logger, "repository", "findOrganizationNatureAll(nature)", "repository is started...");
            Long start = System.currentTimeMillis();

            if (Strings.isNotBlank(natureLike)) {
                organizationList = organizationRepository.findOrganizationNatureAll(searchPath, natureLike);
            } else {
                organizationList = organizationRepository.findOrganizationNatureAll(searchPath);
            }

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findOrganizationNatureAll(nature)", String.format("repository is finished,execute time is :%sms", end - start));

            if (organizationList != null && organizationList.size() > 0) {
                // 获取字典表中机构类型名称的信息
                Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
                Map<String, String> organizationIdCodeMap = organizationCacheService.findOrgIdCodeCache();
                if (organizationIdCodeMap==null){
                    organizationIdCodeMap=new HashMap<>();
                }
                for (OrganizationEntity organizationEntity : organizationList) {
                    OrganizationBean bean = OrganizationTransformUtil.transform(organizationEntity, dicsMap, organizationIdCodeMap);
                    res.add(bean);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findOrganizationNatureAll", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findOrganizationNatureAll", String.format("find all Organization  fail "), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_ORGANIZATION_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see OrganizationService#findOrganizationAllAuthorization(String, List)
     */
    @Transactional(readOnly = true)
    @Override
    public Map<String, OrganizationBean> findOrganizationAllAuthorization(String organizationId, List<String> categoryCodes) {
        try {
            logService.infoLog(logger, "service", "findOrganizationAllAuthorization", "service is started...");
            Long logStart = System.currentTimeMillis();

            Map<String, OrganizationBean> allOrganization = findOrganizationAll();
            Map<String, OrganizationBean> res = new HashMap<>();

            if (allOrganization != null && allOrganization.size() > 0) {
                List<String> orgIds = null;
                if (!StringUtils.isBlank(organizationId)) {
                    orgIds = findChildOrganizationId(organizationId);
                }
                for (String key : allOrganization.keySet()) {
                    OrganizationBean bean = allOrganization.get(key);
                    if (orgIds != null && orgIds.size() > 0) {
                        if (orgIds.contains(bean.getId())) {
                            if (categoryCodes != null && categoryCodes.size() > 0) {
                                if (categoryCodes.contains(bean.getOrganizationCategoryCode())) {
                                    res.put(bean.getId(), bean);
                                }
                            } else {
                                res.put(bean.getId(), bean);
                            }
                        }
                    }
                }

            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findOrganizationAllAuthorization", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findOrganizationAllAuthorization", String.format("find all Organization  fail "), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_ORGANIZATION_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see OrganizationService#findOrganizationByOrganizationId(String)
     */
    @Transactional(readOnly = true)
    @Override
    public OrganizationBean findOrganizationByOrganizationId(String organizationId) {
        if (Strings.isBlank(organizationId)) {
            logService.infoLog(logger, "service", "findOrganization", "organizationId is blank.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            OrganizationBean bean =null;
            logService.infoLog(logger, "service", "findOrganization", "service is started...");
            Long logStart = System.currentTimeMillis();
            Map<String, OrganizationBean> organizationBeanMap = organizationCacheService.findOrgBeanCache();
            if (organizationBeanMap!=null){
                bean = organizationBeanMap.get(organizationId);
            }
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findOrganization", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return bean;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findOrganization", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_ORGANIZATION_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see OrganizationService#findOrganizationCondition(OrganizationQueryInputInfo)
     */
    @Transactional(readOnly = true)
    @Override
    public List<OrganizationBean> findOrganizationCondition(OrganizationQueryInputInfo inputInfo) {
        if (inputInfo == null) {
            logService.infoLog(logger, "service", "findOrganizationCondition", "OrganizationQueryInputInfo is null.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findOrganizationCondition", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<OrganizationBean> beanList = new ArrayList<>();
            // 获取字典表中机构类型名称的信息
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
            List<OrganizationEntity> organizationList = new ArrayList<>();

            OrganizationEntity organization = accessor.getById(inputInfo.getSquadronId(), OrganizationEntity.class);

            logService.infoLog(logger, "repository", "findOrganizationCondition", "repository is started...");
            Long start = System.currentTimeMillis();

            if (organization != null && organization.isValid()) {
                String searchPath = organization.getSearchPath() + "%";
                organizationList = organizationRepository.findOrganizationCondition(searchPath, inputInfo.getKeyword(), inputInfo.getWhetherOnlySquadron());
            }


            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findOrganizationCondition", String.format("repository is finished,execute time is :%sms", end - start));
            Map<String, String> organizationIdCodeMap = organizationCacheService.findOrgIdCodeCache();
            if (organizationIdCodeMap==null){
                organizationIdCodeMap=new HashMap<>();
            }
            if (organizationList != null && organizationList.size() > 0) {
                for (OrganizationEntity organizationEntity : organizationList) {
                    OrganizationBean bean = OrganizationTransformUtil.transform(organizationEntity, dicsMap, organizationIdCodeMap);
                    beanList.add(bean);
                }
            }

            List<OrganizationBean> returnBeans = beanList;
            //判断是否为树形结构
            if (inputInfo.getReturnType() == 1) {
                returnBeans = TreeUtil.bulidTree(beanList);
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findOrganizationCondition", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return returnBeans;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findOrganizationCondition", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_ORGANIZATION_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see OrganizationService#findOrganizationChildren(String)
     */
    @Transactional(readOnly = true)
    @Override
    public List<OrganizationBean> findOrganizationChildren(String organizationId) {

        if (Strings.isBlank(organizationId)) {
            logService.infoLog(logger, "service", "findOrganizationChildren", "organizationId is blank.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }

        try {
            logService.infoLog(logger, "service", "findOrganizationChildren", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<OrganizationBean> beanList = new ArrayList<>();
            // 获取字典表中机构类型名称的信息
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
            List<OrganizationEntity> organizationList = new ArrayList<>();
            OrganizationEntity organization = accessor.getById(organizationId, OrganizationEntity.class);
            if (organization != null && organization.isValid()) {


                logService.infoLog(logger, "repository", "findChildrenOrganizationByParentId(organizationId)", "repository is started...");
                Long start = System.currentTimeMillis();

                organizationList = organizationRepository.findChildrenOrganizationByParentId(organizationId);


                Long end = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "findChildrenOrganizationByParentId(organizationId)", String.format("repository is finished,execute time is :%sms", end - start));

            }
            Map<String, String> organizationIdCodeMap = organizationCacheService.findOrgIdCodeCache();
            if (organizationIdCodeMap==null){
                organizationIdCodeMap=new HashMap<>();
            }
            if (organizationList != null && organizationList.size() > 0) {
                for (OrganizationEntity organizationEntity : organizationList) {
                    OrganizationBean bean = OrganizationTransformUtil.transform(organizationEntity, dicsMap, organizationIdCodeMap);
                    beanList.add(bean);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findOrganizationChildren", String.format("service is finished,execute time is :%sms", logEnd - logStart));


            return beanList;
        } catch (UserInterfaceException ex) {
            throw ex;
        } catch (Exception ex) {
            if (logger.isErrorEnabled()) {
                logger.error(String.format("根据机构ID为:%s 查询子机构信息出错.", organizationId), ex);
            }
            throw new BasedataException(BasedataException.BasedataErrors.FIND_ORGANIZATION_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see OrganizationService#findOrganizationSamelevel(String)
     */
    @Transactional(readOnly = true)
    @Override
    public List<OrganizationBean> findOrganizationSamelevel(String organizationId) {
        if (Strings.isBlank(organizationId)) {
            logService.infoLog(logger, "service", "findOrganizationSamelevel", "organizationId is blank.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findOrganizationSamelevel", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<OrganizationBean> beanList = new ArrayList<>();
            // 获取字典表中机构类型名称的信息
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
            OrganizationEntity organization = accessor.getById(organizationId, OrganizationEntity.class);
            if (organization != null && organization.isValid() && Strings.isNotBlank(organization.getOrganizationParentId())) {


                logService.infoLog(logger, "repository", "findChildrenOrganizationByParentId(organizationId)", "repository is started...");
                Long start = System.currentTimeMillis();

                List<OrganizationEntity> organizationList = organizationRepository.findChildrenOrganizationByParentId(organization.getOrganizationParentId());


                Long end = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "findChildrenOrganizationByParentId(organizationId)", String.format("repository is finished,execute time is :%sms", end - start));

                if (organizationList != null && organizationList.size() > 0) {
                    Map<String, String> organizationIdCodeMap = organizationCacheService.findOrgIdCodeCache();
                    if (organizationIdCodeMap==null){
                        organizationIdCodeMap=new HashMap<>();
                    }
                    for (OrganizationEntity organizationEntity : organizationList) {
                        //排除本机构
                        if (!organizationId.equals(organizationEntity.getId())) {
                            OrganizationBean bean = OrganizationTransformUtil.transform(organizationEntity, dicsMap, organizationIdCodeMap);
                            beanList.add(bean);
                        }
                    }
                    ;
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findOrganizationSamelevel", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return beanList;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findOrganizationSamelevel", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_ORGANIZATION_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see OrganizationService#findOrganizationHigherlevel(String)
     */
    @Transactional(readOnly = true)
    @Override
    public OrganizationBean findOrganizationHigherlevel(String organizationId) {
        if (Strings.isBlank(organizationId)) {
            logService.infoLog(logger, "service", "findOrganizationHigherlevel", "organizationId is blank.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findOrganizationHigherlevel", "service is started...");
            Long logStart = System.currentTimeMillis();

            OrganizationBean bean = new OrganizationBean();
            // 获取字典表中机构类型名称的信息
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
            OrganizationEntity organization = accessor.getById(organizationId, OrganizationEntity.class);
            if (organization != null && organization.isValid() && Strings.isNotBlank(organization.getOrganizationParentId())) {
                Map<String, String> organizationIdCodeMap = organizationCacheService.findOrgIdCodeCache();
                if (organizationIdCodeMap==null){
                    organizationIdCodeMap=new HashMap<>();
                }
                OrganizationEntity parentEntity = accessor.getById(organization.getOrganizationParentId(), OrganizationEntity.class);
                bean = OrganizationTransformUtil.transform(parentEntity, dicsMap, organizationIdCodeMap);
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findOrganizationHigherlevel", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return bean;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findOrganizationHigherlevel", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_ORGANIZATION_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see OrganizationService#findSearchPathByIds(List)
     */
    @Transactional(readOnly = true)
    @Override
    public String findSearchPathById(String organizationId) {
        if (Strings.isBlank(organizationId)) {
            logService.infoLog(logger, "service", "findSearchPathById", "organizationId is null or empty");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findSearchPathById", "service is started...");
            Long logStart = System.currentTimeMillis();

            String res = organizationRepository.findSearchPathById(organizationId);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findSearchPathById", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findSearchPathById", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_ORGANIZATION_SEARCH_PATH_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see OrganizationService#findSearchPathByIds(List)
     */
    @Transactional(readOnly = true)
    @Override
    public List<String> findSearchPathByIds(List<String> organizationIds) {
        if (organizationIds == null || organizationIds.size() < 1) {
            logService.infoLog(logger, "service", "findSearchPathByIds", "organizationIds is null or empty");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findSearchPathByIds", "service is started...");
            Long logStart = System.currentTimeMillis();

            organizationIds.remove(null);

            List<String> res = new ArrayList<>();
            if (organizationIds != null && organizationIds.size() > 0 && organizationIds.size() <= 900) {
                res = organizationRepository.findSearchPathByIds(organizationIds);
            } else if (organizationIds != null && organizationIds.size() > 900) {
                int page = (int) Math.ceil(organizationIds.size() / 900.0);
                for (int i = 0; i < page; i++) {
                    int startnum = i * 900;
                    int endnum = (i + 1) * 900;
                    if (endnum > organizationIds.size()) {
                        endnum = organizationIds.size();
                    }
                    List<String> batchIds = organizationIds.subList(startnum, endnum);
                    List<String> bathEntityList = organizationRepository.findSearchPathByIds(batchIds);
                    if (null != bathEntityList && bathEntityList.size() > 0) {
                        res.addAll(bathEntityList);
                    }
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findSearchPathByIds", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findSearchPathByIds", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_ORGANIZATION_SEARCH_PATH_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see OrganizationService#findOrganizationsByIds(List<String>)
     */
    @Transactional(readOnly = true)
    @Override
    public List<OrganizationBean> findOrganizationsByIds(List<String> organizationIds) {
        if (organizationIds == null || organizationIds.isEmpty()) {
            logService.infoLog(logger, "service", "findOrganizationsByIds", "findOrganizationsByIds is null or empty");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findOrganizationsByIds", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<OrganizationBean> res = new ArrayList<>();

            organizationIds.remove(null);

            List<OrganizationEntity> entityList = new ArrayList<>();
            if (organizationIds != null && organizationIds.size() > 0 && organizationIds.size() <= 900) {
                entityList = organizationRepository.findOrganizationsByIds(organizationIds);
            } else if (organizationIds != null && organizationIds.size() > 900) {
                int page = (int) Math.ceil(organizationIds.size() / 900.0);
                for (int i = 0; i < page; i++) {
                    int startnum = i * 900;
                    int endnum = (i + 1) * 900;
                    if (endnum > organizationIds.size()) {
                        endnum = organizationIds.size();
                    }
                    List<String> batchIds = organizationIds.subList(startnum, endnum);
                    List<OrganizationEntity> bathEntityList = organizationRepository.findOrganizationsByIds(batchIds);
                    if (null != bathEntityList && bathEntityList.size() > 0) {
                        entityList.addAll(bathEntityList);
                    }
                }
            }

            if (entityList != null && entityList.size() > 0) {
                Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
                Map<String, String> organizationIdCodeMap = organizationCacheService.findOrgIdCodeCache();
                if (organizationIdCodeMap==null){
                    organizationIdCodeMap=new HashMap<>();
                }
                for (OrganizationEntity entity : entityList) {
                    OrganizationBean bean = OrganizationTransformUtil.transform(entity, dicsMap, organizationIdCodeMap);
                    res.add(bean);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findOrganizationsByIds", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findOrganizationsByIds", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_ORGANIZATION_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see OrganizationService#findOrganizationIdSet()
     */
    @Transactional(readOnly = true)
    @Override
    public Set<String> findOrganizationIdSet() {

        Set<String> codeSet = new HashSet<>();
        Map<String, OrganizationBean> organizationBeanMap = organizationCacheService.findOrgBeanCache();
        if (null != organizationBeanMap && organizationBeanMap.size() > 0) {
            codeSet = organizationBeanMap.keySet();
        }
        return codeSet;
    }


    /**
     * {@inheritDoc}
     *
     * @see OrganizationService#findParentOrganizationId(String)
     */
    @Transactional(readOnly = true)
    @Override
    public List<String> findParentOrganizationId(String organizationId) {
        try {
            List<String> list = new ArrayList<>();
            Map<String, OrganizationBean> organizationIdMap = organizationCacheService.findOrgBeanCache();
//            Map<String, OrganizationBean> organizationIdMap = organizationBeanMap;
            if (organizationIdMap != null && organizationIdMap.size() > 0) {
                traverseFindParentId(organizationId, organizationIdMap, list);
            }
            return list;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findParentOrganizationId", String.format("find organization code fail,org:%s", organizationId), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_ORGANIZATION_FAIL);
        }
    }

    /**
     * 递归上级单位id
     *
     * @param organizationId    单位id
     * @param organizationIdMap 单位信息
     * @param list              上级单位code集合
     */
    private void traverseFindParentId(String organizationId, Map<String, OrganizationBean> organizationIdMap, List<String> list) {
        if (!StringUtils.isBlank(organizationId)) {
            OrganizationBean organization = organizationIdMap.get(organizationId);
            if (organization != null && organizationId.equals(organization.getId())) {
                list.add(organizationId);
                String parentId = organization.getOrganizationParentId();
                if (!StringUtils.isBlank(parentId)) {
                    traverseFindParentId(parentId, organizationIdMap, list);
                }
            }
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see OrganizationService#findParentOrganizationIds(List)
     */
    @Override
    public List<String> findParentOrganizationIds(List<String> organizationIds) {
        try {
            long start = System.currentTimeMillis();

            Set<String> setIds = new HashSet<>();
            if (organizationIds != null && organizationIds.size() > 0) {

                organizationIds.remove(null);

                organizationIds.forEach(organizationId -> {
                    List<String> parentOrganizationIds = findParentOrganizationId(organizationId);
                    if (parentOrganizationIds != null) {
                        setIds.addAll(parentOrganizationIds);
                    }
                });
            }
            long end = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findParentOrganizationIds", String.format("total time:%sms", end - start));
            return new ArrayList<>(setIds);
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findParentOrganizationIds", String.format("find org codes fail by orgCodes:%s", organizationIds.toArray()), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_ORGANIZATION_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see OrganizationService#findChildOrganizationId(String)
     */
    @Transactional(readOnly = true)
    @Override
    public List<String> findChildOrganizationId(String organizationId) {
        try {
            List<String> childs = new ArrayList<>();
            OrganizationBean root = organizationCacheService.findRootOrg();
            if (root != null) {
                OrganizationBean bo = recursiveOrgById(root, organizationId);
                if (bo != null) {
                    traverseFindChildId(bo, childs);
                }
            }
            return childs;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findChildOrganizationId", String.format("find org codes fail,organizationCode:%s", organizationId), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_ORGANIZATION_FAIL);
        }
    }


    /**
     * 递归获取机构
     *
     * @param root           根机构
     * @param organizationId 机构id
     * @return 返回机构信息
     */
    private OrganizationBean recursiveOrgById(OrganizationBean root, String organizationId) {
        if (root.getId().equals(organizationId)) {
            return root;
        } else {
            List<OrganizationBean> children = root.getChildren();
            if (children != null && children.size() > 0) {
                for (OrganizationBean child : children) {
                    OrganizationBean bo = recursiveOrgById(child, organizationId);
                    if (bo != null) {
                        return bo;
                    }
                }
            }
            return null;
        }
    }

    /**
     * 递归子单位id
     *
     * @param bo     单位BO
     * @param childs 子单位id集合
     */
    private void traverseFindChildId(OrganizationBean bo, List<String> childs) {
        if (bo != null) {
            childs.add(bo.getId());
            List<OrganizationBean> children = bo.getChildren();
            if (children != null && children.size() > 0) {
                children.forEach(child -> traverseFindChildId(child, childs));
            }
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see OrganizationService#findChildOrganizationIds(List)
     */
    @Override
    public List<String> findChildOrganizationIds(List<String> organizationIds) {
        try {
            long start = System.currentTimeMillis();
            Set<String> setCodes = new HashSet<>();
            if (organizationIds != null && organizationIds.size() > 0) {

                organizationIds.remove(null);

                organizationIds.forEach(organizationId -> {
                    List<String> childId = findChildOrganizationId(organizationId);
                    if (childId != null) {
                        setCodes.addAll(childId);
                    }
                });
            }
            long end = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findChildOrganizationIds", String.format("total time:%sms", end - start));
            return new ArrayList<>(setCodes);
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findChildOrganizationIds", String.format("find org codes fail by orgCodes:%s", organizationIds.toArray()), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_ORGANIZATION_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see OrganizationService#findOrganizationCodesById(String)
     */
    @Override
    public String findOrganizationCodesById(String organizationId) {
        try {
            logService.infoLog(logger, "service", "findOrganizationCodesById", "service is started...");
            long logStart = System.currentTimeMillis();

            String res = null;
            Map<String, String> organizationIdCodeMap = organizationCacheService.findOrgIdCodeCache();
            if (organizationIdCodeMap==null){
                organizationIdCodeMap=new HashMap<>();
            }
            String organizationCode = organizationIdCodeMap.get(organizationId);
            if (Strings.isNotBlank(organizationCode)) {
                res = organizationCode;
            }

            long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findOrganizationCodesById", String.format("total time:%sms", logEnd - logStart));
            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findOrganizationCodesById", String.format("find org codes fail by id :%s", organizationId), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_ORGANIZATION_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see OrganizationService#findOrganizationCodesByIds(List)
     */
    @Override
    public List<String> findOrganizationCodesByIds(List<String> organizationIds) {
        try {
            logService.infoLog(logger, "service", "findOrganizationCodesByIds", "service is started...");
            long logStart = System.currentTimeMillis();

            List<String> res = new ArrayList<>();
            Map<String, String> organizationIdCodeMap = organizationCacheService.findOrgIdCodeCache();
            if (organizationIdCodeMap==null){
                organizationIdCodeMap=new HashMap<>();
            }
            for (String organizationId : organizationIds) {
                String organizationCode = organizationIdCodeMap.get(organizationId);
                if (Strings.isNotBlank(organizationCode)) {
                    res.add(organizationCode);
                }
            }

            long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findOrganizationCodesByIds", String.format("total time:%sms", logEnd - logStart));
            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findOrganizationCodesByIds", String.format("find org codes fail by orgCodes:%s", organizationIds.toArray()), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_ORGANIZATION_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see OrganizationService#findOrganizationIdsByCode(String)
     */
    @Override
    public String findOrganizationIdsByCode(String organizationCode) {
        try {
            logService.infoLog(logger, "service", "findOrganizationIdsByCode", "service is started...");
            long logStart = System.currentTimeMillis();

            String res = null;
            Map<String, String> organizationCodeIdMap = organizationCacheService.findOrgCodeIdCache();
            if (organizationCodeIdMap!=null){
                res =organizationCodeIdMap.get(organizationCode);
            }

            long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findOrganizationIdsByCode", String.format("total time:%sms", logEnd - logStart));
            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findOrganizationIdsByCode", String.format("find org codes fail by id :%s", organizationCode), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_ORGANIZATION_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see OrganizationService#findOrganizationCodesByIds(List)
     */
    @Override
    public List<String> findOrganizationIdsByCodes(List<String> organizationCodes) {
        try {
            logService.infoLog(logger, "service", "findOrganizationIdsByCodes", "service is started...");
            long logStart = System.currentTimeMillis();

            List<String> res = new ArrayList<>();
            Map<String, String> organizationCodeIdMap = organizationCacheService.findOrgCodeIdCache();
            if (organizationCodeIdMap==null){
                organizationCodeIdMap=new HashMap<>();
            }
            for (String organizationCode : organizationCodes) {
                String organizationId = organizationCodeIdMap.get(organizationCode);
                if (Strings.isNotBlank(organizationId)) {
                    res.add(organizationId);
                }
            }

            long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findOrganizationIdsByCodes", String.format("total time:%sms", logEnd - logStart));
            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findOrganizationIdsByCodes", String.format("find org codes fail by orgCodes:%s", organizationCodes.toArray()), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_ORGANIZATION_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see OrganizationService#getRootOrg()
     */
    @Override
    public OrganizationBean getRootOrg() {
        try {

            OrganizationBean rootOrg = organizationCacheService.findRootOrg();

            return rootOrg;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "getRootOrg", "find root org fail", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_ORGANIZATION_FAIL);
        }

    }

    /**
     * {@inheritDoc}
     *
     * @see OrganizationService#getRootOrg()
     */
    @Override
    public String getRootOrgId() {
        try {

            OrganizationBean rootOrg = organizationCacheService.findRootOrg();
            if (rootOrg != null) {
                return rootOrg.getId();
            }
            return "";
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "getRootOrg", "find root org fail", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_ORGANIZATION_FAIL);
        }

    }

}