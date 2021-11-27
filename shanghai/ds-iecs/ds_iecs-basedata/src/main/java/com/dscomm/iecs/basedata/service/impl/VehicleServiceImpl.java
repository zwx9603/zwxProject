package com.dscomm.iecs.basedata.service.impl;

import com.dscomm.iecs.base.enums.EnableEnum;
import com.dscomm.iecs.base.websocket.WebsocketCodeEnum;
import com.dscomm.iecs.base.graphql.typebean.PaginationBean;
import com.dscomm.iecs.base.service.NotifyActionService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.base.websocket.WebsocketCodeEnum;
import com.dscomm.iecs.basedata.dal.po.EquipmentVehicleEntity;
import com.dscomm.iecs.basedata.dal.po.OrganizationEntity;
import com.dscomm.iecs.basedata.dal.repository.EquipmentVehicleRepository;
import com.dscomm.iecs.basedata.dal.repository.OrganizationRepository;
import com.dscomm.iecs.basedata.exception.BasedataException;
import com.dscomm.iecs.basedata.graphql.inputbean.EquipmentVehicleExpandInputInfo;
import com.dscomm.iecs.basedata.graphql.inputbean.EquipmentVehicleExpandQueryInputInfo;
import com.dscomm.iecs.basedata.graphql.inputbean.EquipmentVehicleQueryInputInfo;
import com.dscomm.iecs.basedata.graphql.typebean.DictionaryBean;
import com.dscomm.iecs.basedata.graphql.typebean.EquipmentVehicleBean;
import com.dscomm.iecs.basedata.graphql.typebean.EquipmentVehicleLoadBean;
import com.dscomm.iecs.basedata.graphql.typebean.OrganizationBean;
import com.dscomm.iecs.basedata.service.*;
import com.dscomm.iecs.basedata.utils.EquipmentTransformUtil;
import com.dscomm.iecs.ext.organization.naturehead.ORGANIZATION_NATURE_HEAD_DSZD;
import com.dscomm.iecs.ext.organization.naturehead.ORGANIZATION_NATURE_HEAD_SJZD;
import com.dscomm.iecs.ext.organization.naturehead.ORGANIZATION_NATURE_HEAD_XJZD;
import com.dscomm.iecs.ext.organization.naturehead.ORGANIZATION_NATURE_HEAD_XXDD;
import com.dscomm.iecs.ext.vehicle.status.VEHICLE_STATUS_CDFD;
import com.dscomm.iecs.ext.vehicle.status.VEHICLE_STATUS_CDZTFD;
import org.apache.logging.log4j.util.Strings;
import org.mx.StringUtils;
import org.mx.dal.Pagination;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLDecoder;
import java.util.*;

import static java.lang.Math.max;

/**
 * 描述：车辆服务类实现
 */
@Component("vehicleServiceImpl")
public class VehicleServiceImpl implements VehicleService {
    private static final Logger logger = LoggerFactory.getLogger(VehicleServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private Environment env;
    private EquipmentVehicleRepository vehicleRepository;
    private DictionaryService dictionaryService;
    private OrganizationService organizationService;
    private EquipmentService equipmentService;
    private SystemConfigurationService systemConfigurationService;
    private NotifyActionService notifyActionService;
    private OrganizationRepository organizationRepository;
    private VehicleCacheService vehicleCacheService;
    //设置机构数据缓存数据 机构id 与 机构名称  缓存  机构id  与 bean 的缓存
//    private static Map<String, EquipmentVehicleBean> vehicleBeanMap = new HashMap<>();

    private List<String> dics;

    /**
     * 默认的构造函数
     */
    @Autowired
    public VehicleServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor,
                              EquipmentVehicleRepository vehicleRepository, DictionaryService dictionaryService,
                              OrganizationService organizationService, Environment env,
                              EquipmentService equipmentService, SystemConfigurationService systemConfigurationService,
                              NotifyActionService notifyActionService, OrganizationRepository organizationRepository,

                              VehicleCacheService vehicleCacheService) {
        this.logService = logService;
        this.accessor = accessor;
        this.env = env;
        this.vehicleRepository = vehicleRepository;
        this.dictionaryService = dictionaryService;
        this.organizationService = organizationService;
        this.equipmentService = equipmentService;
        this.systemConfigurationService = systemConfigurationService;
        this.notifyActionService = notifyActionService;
        this.organizationRepository = organizationRepository;
        this.vehicleCacheService = vehicleCacheService;

        dics = new ArrayList<>(Arrays.asList("WLCLLX", "WLCLDJ", "WLCLZT", "YS", "JLDW", "WLRYZW", "QCLX", "PMLX","WLCLXZ"));

    }


    /**
     * {@inheritDoc}
     *
     * @see VehicleService#findEquipmentVehicleCondition(EquipmentVehicleQueryInputInfo)
     */
    @Transactional(readOnly = true)
    @Override
    public PaginationBean<EquipmentVehicleBean> findEquipmentVehicleCondition(EquipmentVehicleQueryInputInfo inputInfo) {
        if (inputInfo == null) {
            logService.infoLog(logger, "service", "findEquipmentVehicleCondition", "equipmentVehicleQueryInputInfo is null.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findEquipmentVehicleCondition", "service is started...");
            Long logStart = System.currentTimeMillis();

            PaginationBean<EquipmentVehicleBean> res = new PaginationBean<>();
            List<EquipmentVehicleBean> beans = new ArrayList<>();
            Boolean cascade=false;
            //scop 1 级联查询下级单位 0只查询该单位数据
            String searchPath = inputInfo.getScopeSquadronId(); //机构id
            if (1 == inputInfo.getScopeType()) {
                OrganizationEntity organization = accessor.getById(inputInfo.getScopeSquadronId(), OrganizationEntity.class);
                if (organization != null && organization.isValid()) {
                    searchPath = organization.getSearchPath();
                    cascade=true;
                }
            } else {
                searchPath = null;
            }


            //  -1 0 总队 1 支队 2 大队 3救援站（中队） -1查询全部  其他查询全部
            String natureLike = "";
            Integer nature = inputInfo.getNature();
            if (null == nature) {
                natureLike = "";
            } else if (0 == nature) {
                natureLike = ORGANIZATION_NATURE_HEAD_SJZD.getCode(); //0 总队
            } else if (1 == nature) {
                natureLike = ORGANIZATION_NATURE_HEAD_DSZD.getCode() ; //1 支队
            } else if (2 == nature) {
                natureLike = ORGANIZATION_NATURE_HEAD_XXDD.getCode() ; //大队
            } else if (3 == nature) {
                natureLike = ORGANIZATION_NATURE_HEAD_XJZD.getCode(); //救援站（中队）
            }

            logService.infoLog(logger, "repository", "findEquipmentVehicleCondition", "repository is started...");
            Long start = System.currentTimeMillis();
            //查询总数
            logService.infoLog(logger, "repository", "findEquipmentVehicleConditionTotal", "repository is started...");
            Long countStart = System.currentTimeMillis();

            //获取分页参数total
            Integer total = vehicleRepository.findEquipmentVehicleConditionTotal(inputInfo.getScopeSquadronId(),searchPath,cascade, inputInfo.getKeyword(), inputInfo.getVehicleTypeCodes(),
                    inputInfo.getVehicleStatusCodes(),
                    null, null, null,  natureLike);

            Long countEnd = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findEquipmentVehicleConditionTotal", String.format("repository is finished,execute time is :%sms", countEnd - countStart));
            //根据条件查询车辆信息
            List<Object[]> vehicleEntityList =null;
            if(inputInfo.getWhetherPage()!=null&&inputInfo.getWhetherPage()){
                vehicleEntityList = vehicleRepository.findEquipmentVehicleCondition(inputInfo.getScopeSquadronId(),searchPath,cascade,
                        inputInfo.getKeyword(), inputInfo.getVehicleTypeCodes(),inputInfo.getVehicleStatusCodes(), null, null, null, natureLike,
                        inputInfo.getPagination().getPage(),
                        inputInfo.getPagination().getSize());
            }else {
                vehicleEntityList =new ArrayList<>();
                int size=2000;
                int page=1;
                while ((page-1)*size<total){
                    List<Object[]> ret = vehicleRepository.findEquipmentVehicleCondition(inputInfo.getScopeSquadronId(),searchPath,cascade,
                            inputInfo.getKeyword(), inputInfo.getVehicleTypeCodes(),inputInfo.getVehicleStatusCodes(), null, null, null, natureLike,
                            page,size);
                    if (ret!=null&&!ret.isEmpty()){
                       vehicleEntityList.addAll(ret);
                    }
                    page++;
                }
            }
            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findEquipmentVehicleCondition", String.format("repository is finished,execute time is :%sms", end - start));


            //获取名称-代码对应字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
            //机构数据
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();
            Map<String, OrganizationBean> organizationMap = organizationService.findOrganizationAll();

            if (vehicleEntityList != null && vehicleEntityList.size() > 0) {
                for (Object[] vehicleEntity : vehicleEntityList) {
                    EquipmentVehicleBean bean = EquipmentTransformUtil.transform(vehicleEntity, dicsMap, organizationNameMap, organizationMap);
                    beans.add(bean);
                }
            }
            //按照机构排序
            List<EquipmentVehicleBean> beansSorted = new ArrayList<>();
            List<String>orgSort = organizationRepository.findOrgOrder();
            if (orgSort!=null&&orgSort.size()>0){
                for (String orgId:orgSort
                     ) {
                    //建立一个空beanList
                    List<EquipmentVehicleBean> nullBeans = new ArrayList<>();
                    for (EquipmentVehicleBean b:beans
                         ) {
                        if (orgId.equals(b.getOrganizationId())){
                            nullBeans.add(b);
                        }
                    }

                    //排序
                    nullBeans.sort( new Comparator<EquipmentVehicleBean>() {

                        @Override
                        public int compare(EquipmentVehicleBean o1, EquipmentVehicleBean o2) {
                            Integer d1 = o1.getVehicleOrderNum();
                            Integer d2 = o2.getVehicleOrderNum();
                            return  d1.compareTo(d2);
                        }
                    } );

                    beansSorted.addAll(nullBeans);
                }
            }


            //装配结果
            Pagination pagination = new Pagination();
            pagination.setPage(inputInfo.getPagination().getPage());
            pagination.setSize(inputInfo.getPagination().getSize());
            pagination.setTotal(total);

            res.setPagination(pagination);

//            //车辆排序
//            beans.sort( new Comparator<EquipmentVehicleBean>() {
//                @Override
//                public int compare(EquipmentVehicleBean o1, EquipmentVehicleBean o2) {
//                    Integer d1 = o1.getVehicleOrderNum();
//                    Integer d2 = o2.getVehicleOrderNum();
//                    return  d1.compareTo(d2);
//                }
//            } );


            res.setList(beansSorted);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findEquipmentVehicleCondition", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;

        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findEquipmentVehicleCondition", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_VEHICLE_FAIL);
        }

    }


    /**
     * {@inheritDoc}
     *
     * @see #findEquipmentVehicleByOrganizationId(String)
     */
    @Transactional(readOnly = true)
    @Override
    public List<EquipmentVehicleBean> findEquipmentVehicleByOrganizationId(String organizationId) {
        if (Strings.isBlank(organizationId)) {
            logService.infoLog(logger, "service", "findEquipmentVehicleByOrganizationId", "organizationId is null.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findEquipmentVehicleByOrganizationId", "service is started...");
            Long logStart = System.currentTimeMillis();


            List<EquipmentVehicleBean> beans = new ArrayList<>();


            logService.infoLog(logger, "repository", "findEquipmentVehicleCondition", "repository is started...");
            Long start = System.currentTimeMillis();
            //获取分页参数total
            Integer total = vehicleRepository.findEquipmentVehicleConditionTotal(organizationId,null,false, null, null,
                    null,null, null, null,  null);
            //根据条件查询车辆信息
            List<Object[]> vehicleEntityList = vehicleRepository.findEquipmentVehicleCondition(organizationId,
                    null, false, null, null,
                    null, null, null, null, null,
                    1, total);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findEquipmentVehicleCondition", String.format("repository is finished,execute time is :%sms", end - start));

            //获取名称-代码对应字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
            //机构数据
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();
            Map<String, OrganizationBean> organizationMap = organizationService.findOrganizationAll();

            if (vehicleEntityList != null && vehicleEntityList.size() > 0) {
                for (Object[] vehicleEntity : vehicleEntityList) {
                    EquipmentVehicleBean bean = EquipmentTransformUtil.transform(vehicleEntity, dicsMap, organizationNameMap, organizationMap);
                    beans.add(bean);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findEquipmentVehicleByOrganizationId", String.format("service is finished,execute time is :%sms", logEnd - logStart));

//            //车辆排序
//            beans.sort( new Comparator<EquipmentVehicleBean>() {
//                @Override
//                public int compare(EquipmentVehicleBean o1, EquipmentVehicleBean o2) {
//                    Integer d1 = o1.getVehicleOrderNum();
//                    Integer d2 = o2.getVehicleOrderNum();
//                    return  d1.compareTo(d2);
//                }
//            } );

            return beans;

        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findEquipmentVehicleByOrganizationId", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_VEHICLE_FAIL);
        }

    }


    /**
     * {@inheritDoc}
     *
     * @see VehicleService#findVehicle(String)
     */
    @Override
    @Transactional(readOnly = true)
    public EquipmentVehicleBean findVehicle(String vehicleId) {
        if (Strings.isBlank(vehicleId)) {
            logService.infoLog(logger, "service", "findVehicle", "vehicleId is blank.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findVehicle", "service is started...");
            Long logStart = System.currentTimeMillis();

            EquipmentVehicleBean res = new EquipmentVehicleBean();

            logService.infoLog(logger, "repository", "findEquipmentVehicleCondition(    )", "repository is started...");
            Long start = System.currentTimeMillis();


            //根据条件查询车辆信息
            List<Object[]> vehicleEntityList = vehicleRepository.findEquipmentVehicleCondition(null,
                    null, null, null, null,
                    null,  vehicleId, null, null,
                    null,1, 1);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findEquipmentVehicleCondition(    )", String.format("repository is finished,execute time is :%sms", end - start));


            if (vehicleEntityList != null && vehicleEntityList.size() > 0) {
                //获取名称-代码对应字典
                Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
                //机构数据
                Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();
                Map<String, OrganizationBean> organizationMap = organizationService.findOrganizationAll();
                Object[] vehicleEntity = vehicleEntityList.get(0);
                res = EquipmentTransformUtil.transform(vehicleEntity, dicsMap, organizationNameMap, organizationMap);
            }


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findVehicle", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findVehicle", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_VEHICLE_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see VehicleService#findVehicle(String)
     */
    @Override
    @Transactional(readOnly = true)
    public List<EquipmentVehicleBean> findVehicleByIds(List<String> vehicleIds) {
        if (vehicleIds == null || vehicleIds.size() < 1) {
            logService.infoLog(logger, "service", "findVehicleByIds", "vehicleId is blank.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findVehicleByIds", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<EquipmentVehicleBean> res = new ArrayList<>();

            logService.infoLog(logger, "repository", "findEquipmentVehicleCondition(    )", "repository is started...");
            Long start = System.currentTimeMillis();


            //根据条件查询车辆信息
            List<Object[]> vehicleEntityList = vehicleRepository.findEquipmentVehicleCondition(null,
                    null, null, null, null,
                    null, null,  vehicleIds, null, null,
                    1, vehicleIds.size());

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findEquipmentVehicleCondition(    )", String.format("repository is finished,execute time is :%sms", end - start));


            if (vehicleEntityList != null && vehicleEntityList.size() > 0) {
                //获取名称-代码对应字典
                Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
                //机构数据
                Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();
                Map<String, OrganizationBean> organizationMap = organizationService.findOrganizationAll();
                for (Object[] vehicleEntity : vehicleEntityList) {
                    EquipmentVehicleBean vehicleBean = EquipmentTransformUtil.transform(vehicleEntity, dicsMap, organizationNameMap, organizationMap);
                    //添加车辆信息
                    res.add(vehicleBean);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findVehicle", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findVehicle", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_VEHICLE_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<EquipmentVehicleBean> findVehicleByIdsAndVehicleStatus(List<String> vehicleIds, List<String> vehicleStatusList) {
        if (vehicleIds == null || vehicleIds.size() < 1) {
            logService.infoLog(logger, "service", "findVehicleByIdsAndVehicleStatus", "vehicleId is blank.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findVehicleByIdsAndVehicleStatus", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<EquipmentVehicleBean> res = new ArrayList<>();

            logService.infoLog(logger, "repository", "findVehicleByIdsAndVehicleStatus(    )", "repository is started...");
            Long start = System.currentTimeMillis();


            //根据条件查询车辆信息
            List<EquipmentVehicleEntity> vehicleEntityList = vehicleRepository.findVehicleByIdsAndVehicleStatus(vehicleIds, vehicleStatusList);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findVehicleByIdsAndVehicleStatus(    )", String.format("repository is finished,execute time is :%sms", end - start));


            if (vehicleEntityList != null && vehicleEntityList.size() > 0) {
                //获取名称-代码对应字典
                Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
                //机构数据
                Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();
                Map<String, OrganizationBean> organizationMap = organizationService.findOrganizationAll();
                for (EquipmentVehicleEntity vehicleEntity : vehicleEntityList) {
                    EquipmentVehicleBean vehicleBean = EquipmentTransformUtil.transform(vehicleEntity, dicsMap, organizationNameMap);
                    //添加车辆信息
                    res.add(vehicleBean);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findVehicle", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findVehicle", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_VEHICLE_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see #findVehicleNumber(String)
     */
    @Override
    @Transactional(readOnly = true)
    public EquipmentVehicleBean findVehicleNumber(String vehicleNumber) {
        if (Strings.isBlank(vehicleNumber)) {
            logService.infoLog(logger, "service", "findVehicleNumber", "vehicleNumber is blank.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findVehicleNumber", "service is started...");
            Long logStart = System.currentTimeMillis();
            EquipmentVehicleBean res = new EquipmentVehicleBean();

            logService.infoLog(logger, "repository", "findEquipmentVehicleByVehicleNumber( vehicleNumber  )", "repository is started...");
            Long start = System.currentTimeMillis();

            //根据条件查询车辆信息
            List<Object[]> vehicleEntityList = vehicleRepository.findEquipmentVehicleCondition(null,
                    null, null, null, null,
                    null, null, null,  vehicleNumber, null,  1, 1);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findEquipmentVehicleByVehicleNumber( vehicleNumber  )", String.format("repository is finished,execute time is :%sms", end - start));


            if (vehicleEntityList != null && vehicleEntityList.size() > 0) {
                //获取名称-代码对应字典
                Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
                //机构数据
                Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();
                Map<String, OrganizationBean> organizationMap = organizationService.findOrganizationAll();
                Object[] vehicleEntity = vehicleEntityList.get(0);
                res = EquipmentTransformUtil.transform(vehicleEntity, dicsMap, organizationNameMap, organizationMap);
                // 设置车载装备信息
                List<EquipmentVehicleLoadBean> loadBeans = equipmentService.findEquipmentVehicleLoadByVehicleId(res.getId());
                res.setEquipmentVehicleLoadBeanList(loadBeans);
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findVehicleNumber", String.format("service is finished,execute time is :%sms", logEnd - logStart));


            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findVehicleNumber", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_VEHICLE_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see VehicleService#updateVehicleStatus(String, String)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean updateVehicleStatus(String vehicleId, String vehicleStatusCode) {
        if (Strings.isBlank(vehicleId) || Strings.isBlank(vehicleStatusCode)) {
            logService.infoLog(logger, "service", "updateVehicleStatus", "vehicleId or vehicleStatusCode is blank.");
        }
        try {
            logService.infoLog(logger, "service", "updateVehicleStatus", "service is started...");
            Long logStart = System.currentTimeMillis();

            EquipmentVehicleEntity equipmentVehicleEntity = accessor.getById(vehicleId, EquipmentVehicleEntity.class);

            if (equipmentVehicleEntity == null) {
                logService.infoLog(logger, "service", "updateVehicleStatus", String.format("can not find vehicle by id[%s].", vehicleId));
                throw new BasedataException(BasedataException.BasedataErrors.FIND_VEHICLE_FAIL);
            }

            if (equipmentVehicleEntity.getVehicleStatusCode().equals(vehicleStatusCode)) {
                return true;
            }

            // 获得车辆状态字典
            List<DictionaryBean> dictionaryWLCLZTBeans = dictionaryService.findGridDictionary("WLCLZT", false);

            if (null != dictionaryWLCLZTBeans) {
                //获得车辆作战编码
                String vehicleOnDutyStatus = systemConfigurationService.getSystemConfigByConfigType("vehicleOnDutyStatus").getConfigValue();
                List<String> ondutyStatusList = new ArrayList<>();
                if (Strings.isNotBlank(vehicleOnDutyStatus)) {
                    String[] ondutyStatus = vehicleOnDutyStatus.split(",");
                    ondutyStatusList = Arrays.asList(ondutyStatus);
                }

                for (DictionaryBean dictionaryBean : dictionaryWLCLZTBeans) {
                    if (dictionaryBean.getCode().equals(vehicleStatusCode)) {
                        //判断车辆状态是否为归类战斗  , 如不归类战斗  去掉 车辆 与 案件的关联关系
                        if (!(ondutyStatusList.contains(vehicleStatusCode))
                                || VEHICLE_STATUS_CDFD.getCode().equals(vehicleStatusCode)
                                || VEHICLE_STATUS_CDZTFD.getCode().equals(vehicleStatusCode)) {
                            equipmentVehicleEntity.setIncidentNumber(null);
                        }
                        break;
                    }
                }
            }
            equipmentVehicleEntity.setVehicleStatusCode(vehicleStatusCode);

            logService.infoLog(logger, "repository", "save(dbEquipmentVehicleEntity)", "repository is started...");
            Long start = System.currentTimeMillis();
            //保存车辆状态
            accessor.save(equipmentVehicleEntity);


            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbEquipmentVehicleEntity)", String.format("repository is finished,execute time is :%sms", end - start));

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "updateVehicleStatus", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return true;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "updateVehicleStatus", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.UPDATE_VEHICLE_STATUS_FAIL);
        }

    }


    /**
     * {@inheritDoc}
     *
     * @see VehicleService#updateVehicleExpandInfo(EquipmentVehicleExpandInputInfo)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public EquipmentVehicleBean updateVehicleExpandInfo(EquipmentVehicleExpandInputInfo inputInfo) {
        if (inputInfo == null || Strings.isBlank(inputInfo.getId())) {
            logService.infoLog(logger, "service", "updateVehicleExpandInfo", "EquipmentVehicleExpandInputInfo is null or id is blank.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "updateVehicleExpandInfo", "service is started...");
            Long logStart = System.currentTimeMillis();

            decodeEquipmentVehicleExpandInputInfo(inputInfo);

            //获取车辆
            EquipmentVehicleEntity vehicleEntity = accessor.getById(inputInfo.getId(), EquipmentVehicleEntity.class);
            //修改拓展信息
            vehicleEntity.setWaterCarrier(inputInfo.getWaterCarrier());
            vehicleEntity.setCarrierBubble(inputInfo.getCarrierBubble());
            vehicleEntity.setMaxLiftingHeight(inputInfo.getMaxLiftingHeight());
            vehicleEntity.setTraction(inputInfo.getTraction());
            vehicleEntity.setLiftingWeight(inputInfo.getLiftingWeight());
            vehicleEntity.setPassengersNum(inputInfo.getPassengersNum());
            vehicleEntity.setHandLiftPump(inputInfo.getHandLiftPump());
            vehicleEntity.setPumpFlow(inputInfo.getPumpFlow());
            vehicleEntity.setFireMonitorFlow(inputInfo.getFireMonitorFlow());

            vehicleEntity.setVehicleShortName(inputInfo.getVehicleShortName());
            vehicleEntity.setFoam(inputInfo.getFoam());
            vehicleEntity.setEquipment(inputInfo.getEquipment());
            vehicleEntity.setVehicleNature(inputInfo.getVehicleNature());

            //保存更改
            logService.infoLog(logger, "repository", "save(dbVehicleEntity)", "repository is started...");
            Long start = System.currentTimeMillis();

            accessor.save(vehicleEntity);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbVehicleEntity)", String.format("repository is finished,execute time is :%sms", end - start));

            //获取名称-代码对应字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
            //机构数据
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();

            EquipmentVehicleBean res = EquipmentTransformUtil.transform(vehicleEntity, dicsMap, organizationNameMap);
            //消息通知所属机构
            Set<String> orgs = new HashSet<>();

            List<String> orgIds = new ArrayList<>();
            orgIds.add(res.getOrganizationId());
            List<String> parentOrganizationId = organizationService.findParentOrganizationIds(orgIds);

            List<String> orgCodes = organizationService.findOrganizationCodesByIds(parentOrganizationId);

            orgs.addAll(orgCodes);
            notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.UPDATE_VEHICLE_EXPAND_INFO.getCode(), res, orgs);


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "updateVehicleExpandInfo", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "updateVehicleExpandInfo", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.UPDATE_VEHICLE_EXPAND_INFO_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see #findVehicleIdsByExpandCondition(EquipmentVehicleExpandQueryInputInfo)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Set<String> findVehicleIdsByExpandCondition(EquipmentVehicleExpandQueryInputInfo inputInfo) {
        try {
            logService.infoLog(logger, "service", "findVehicleIdsByExpandCondition", "service is started...");
            Long logStart = System.currentTimeMillis();


            Set<String> res = new HashSet<>();
            //根据车辆拓展属性查询车辆信息
            logService.infoLog(logger, "repository", "save(dbVehicleEntity)", "repository is started...");
            Long start = System.currentTimeMillis();

            List<String> expandVehicleIds = vehicleRepository.findVehicleIdByExpandCondition(
                    inputInfo.getKeyword(),
                    inputInfo.getMaxNum1(), inputInfo.getMinNum1(),
                    inputInfo.getMaxNum2(), inputInfo.getMinNum2(),
                    inputInfo.getMaxNum3(), inputInfo.getMinNum3(),
                    inputInfo.getMaxNum4(), inputInfo.getMinNum4(),
                    inputInfo.getMaxNum5(), inputInfo.getMinNum5(),
                    inputInfo.getMaxNum6(), inputInfo.getMinNum6()
            );

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbVehicleEntity)", String.format("repository is finished,execute time is :%sms", end - start));

            if (expandVehicleIds != null && expandVehicleIds.size() > 0) {
                res.addAll(expandVehicleIds);
            }


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findVehicleIdsByExpandCondition", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findVehicleIdsByExpandCondition", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_DATA_FAIL);
        }
    }

    /**
     * IncidentQueryInputInfo转码
     */
    private void decodeEquipmentVehicleExpandInputInfo(EquipmentVehicleExpandInputInfo source) {
        if (source != null) {
            try {
                if (!StringUtils.isBlank(source.getVehicleName())) {
                    source.setVehicleName(URLDecoder.decode(source.getVehicleName(), "UTF-8"));
                }
                if (!StringUtils.isBlank(source.getEquipment())) {
                    source.setEquipment(URLDecoder.decode(source.getEquipment(), "UTF-8"));
                }
                if (!StringUtils.isBlank(source.getFoam())) {
                    source.setFoam(URLDecoder.decode(source.getFoam(), "UTF-8"));
                }
            } catch (Exception e) {
                throw new BasedataException(BasedataException.BasedataErrors.DECODE_FAIL);
            }
        }
    }


    private Long lastTime = 0l; //系统默认为0 加载全部

    /**
     * 根据上次数据最新时间  本次数据最新时间
     * 判断是否从数据库更新数据 如果本次数据最新时间 大于 上次数据时间 则需要更新数据
     */
    private void updateCacheVehicle() {
        logService.infoLog(logger, "service", "updateCacheVehicle", "service is started...");
        Long logStart = System.currentTimeMillis();

        Long latestTime = vehicleRepository.findDataLatestTime(lastTime);
        Long latestTime1 = vehicleRepository.findDataLatestTime1(lastTime);

        latestTime = latestTime == null ? lastTime : latestTime;
        latestTime1 = latestTime1 == null ? lastTime : latestTime1;

        //判断是否需要更新数据
        if (max(latestTime, latestTime1) > lastTime) {

            logService.infoLog(logger, "service", "updateCacheVehicle", " update cache data");

            //此处为增量更新数据
            List<String> vehicleIds =
                    vehicleRepository.findDataLatestTime(lastTime, max(latestTime, latestTime1));
            List<String> vehicleIds1 =
                    vehicleRepository.findDataLatestTime1(lastTime, max(latestTime, latestTime1));
            // 将两个车辆id 组合
            Set<String> allVehicleIdSet = new HashSet<>();
            allVehicleIdSet.addAll(vehicleIds);
            allVehicleIdSet.addAll(vehicleIds1);

            List<String> allVehicleIdList = new ArrayList<>(allVehicleIdSet);

            //获取名称-代码对应字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
            //机构数据
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();

            if (allVehicleIdList != null && allVehicleIdList.size() > 0 && allVehicleIdList.size() <= 900) {
                //根据条件查询车辆信息
                List<EquipmentVehicleEntity> vehicleCache = vehicleRepository.findEquipmentVehicleByIds(allVehicleIdList);
                if (vehicleCache != null && vehicleCache.size() > 0) {
                    for (EquipmentVehicleEntity vehicleEntity : vehicleCache) {
                        if (vehicleEntity != null) {
                            EquipmentVehicleBean equipmentVehicleBean = EquipmentTransformUtil.transform(vehicleEntity, dicsMap, organizationNameMap);
                            ;
                            vehicleCacheService.modifyVehicleCache(equipmentVehicleBean.getId(), equipmentVehicleBean);
                        }

                    }
                }
            } else if (allVehicleIdList != null && allVehicleIdList.size() > 900) {
                int page = (int) Math.ceil(allVehicleIdList.size() / 900.0);
                for (int i = 0; i < page; i++) {
                    int startnum = i * 900;
                    int endnum = (i + 1) * 900;
                    if (endnum > allVehicleIdList.size()) {
                        endnum = allVehicleIdList.size();
                    }
                    List<String> batchVehcileIds = allVehicleIdList.subList(startnum, endnum);
                    List<EquipmentVehicleEntity> vehicleCache = vehicleRepository.findEquipmentVehicleByIds(batchVehcileIds);
                    if (vehicleCache != null && vehicleCache.size() > 0) {
                        for (EquipmentVehicleEntity vehicleEntity : vehicleCache) {
                            if (vehicleEntity != null) {
                                EquipmentVehicleBean equipmentVehicleBean = EquipmentTransformUtil.transform(vehicleEntity, dicsMap, organizationNameMap);
                                ;
                                vehicleCacheService.modifyVehicleCache(equipmentVehicleBean.getId(), equipmentVehicleBean);
                            }

                        }
                    }

                }
            }

            lastTime = latestTime;

        }

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "updateCacheVehicle", String.format("service is finished,execute time is :%sms", logEnd - logStart));


    }

    /**
     * {@inheritDoc}
     *
     * @see #forceUpdateCacheVehicle()
     */
    @Override
    @Transactional(readOnly = true)
    public void forceUpdateCacheVehicle() {
        try {
            logService.infoLog(logger, "service", "forceUpdateCacheVehicle", "service is started...");
            Long logStart = System.currentTimeMillis();

            updateCacheVehicle();

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "forceUpdateCacheVehicle", String.format("service is finished,execute time is :%sms", logEnd - logStart));

        } catch (Exception ex) {
            logService.erorLog(logger, "service", "forceUpdateCacheVehicle", String.format("force cache Organization Map id name fail "), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_ORGANIZATION_FAIL);
        }

    }

    /**
     * {@inheritDoc}
     *
     * @see #findVehicleCache(String)
     */
    @Override
    @Transactional(readOnly = true)
    public EquipmentVehicleBean findVehicleCache(String vehicleId) {
        if (Strings.isBlank(vehicleId)) {
            logService.infoLog(logger, "service", "findVehicleOne", "vehicleId is null or empty.");
            return new EquipmentVehicleBean();
        }
        try {
            logService.infoLog(logger, "service", "findVehicleOne", "service is started...");
            Long logStart = System.currentTimeMillis();

            EquipmentVehicleBean res = vehicleCacheService.findVehicleCache(vehicleId);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findVehicleOne", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;

        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findVehicleOne", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_VEHICLE_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see VehicleService#findVehicleCacheList(List<String>)
     */
    @Override
    @Transactional(readOnly = true)
    public List<EquipmentVehicleBean> findVehicleCacheList(List<String> vehicleIds) {
        if (vehicleIds == null || vehicleIds.isEmpty()) {
            logService.infoLog(logger, "service", "findVehicleList", "vehicleIds is null or empty.");
            return new ArrayList<>();
        }
        try {
            logService.infoLog(logger, "service", "findVehicleList", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<EquipmentVehicleBean> res = new ArrayList<>();

            for (String vehicleId : vehicleIds) {
                EquipmentVehicleBean equipmentVehicleBean = vehicleCacheService.findVehicleCache(vehicleId);
                if (equipmentVehicleBean != null) {
                    res.add(equipmentVehicleBean);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findVehicleList", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;

        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findVehicleList", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_VEHICLE_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see VehicleService#findVehicleNumberCacheByIds(List<String>)
     */
    @Override
    @Transactional(readOnly = true)
    public List<String> findVehicleNumberCacheByIds(List<String> vehicleIds) {
        if (vehicleIds == null || vehicleIds.isEmpty()) {
            logService.infoLog(logger, "service", "findVehicleNumberByIds", "vehicleIds is null or empty.");
            return new ArrayList<>();
        }
        try {
            logService.infoLog(logger, "service", "findVehicleNumberByIds", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<String> res = new ArrayList<>();

            for (String vehicleId : vehicleIds) {
                EquipmentVehicleBean equipmentVehicleBean = vehicleCacheService.findVehicleCache(vehicleId);
                if (equipmentVehicleBean != null && Strings.isNotBlank(equipmentVehicleBean.getVehicleNumber())) {
                    res.add(equipmentVehicleBean.getVehicleNumber());
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findVehicleNumberByIds", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findVehicleNumberByIds", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_VEHICLE_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see VehicleService#findVehicleNumberCacheByIds(List<String>)
     */
    @Override
    @Transactional(readOnly = true)
    public List<String> findframeNoCacheByIds(List<String> vehicleIds) {
        if (vehicleIds == null || vehicleIds.isEmpty()) {
            logService.infoLog(logger, "service", "findframeNoCacheByIds", "vehicleIds is null or empty.");
            return new ArrayList<>();
        }
        try {
            logService.infoLog(logger, "service", "findframeNoCacheByIds", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<String> res = new ArrayList<>();

            for (String vehicleId : vehicleIds) {
                EquipmentVehicleBean equipmentVehicleBean = vehicleCacheService.findVehicleCache(vehicleId);
                if (equipmentVehicleBean != null && Strings.isNotBlank(equipmentVehicleBean.getFrameNo())) {
                    res.add(equipmentVehicleBean.getFrameNo());
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findVehicleNumberByIds", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findVehicleNumberByIds", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_VEHICLE_FAIL);
        }
    }


}
