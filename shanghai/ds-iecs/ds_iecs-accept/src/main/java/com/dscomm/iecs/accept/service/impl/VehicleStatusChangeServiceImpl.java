package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.VehicleStatusChangeEntity;
import com.dscomm.iecs.accept.dal.repository.VehicleStatusChangeRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.VehicleStateChangeQueryInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.VehicleStatusChangeBean;
import com.dscomm.iecs.accept.service.VehicleStatusChangeService;
import com.dscomm.iecs.accept.service.pushData.PushDataService;
import com.dscomm.iecs.agent.service.UserService;
import com.dscomm.iecs.base.graphql.typebean.PaginationBean;
import com.dscomm.iecs.base.service.NotifyActionService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.base.websocket.WebsocketCodeEnum;
import com.dscomm.iecs.basedata.dal.po.EquipmentVehicleEntity;
import com.dscomm.iecs.basedata.graphql.typebean.EquipmentVehicleBean;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.basedata.service.VehicleService;
import com.dscomm.iecs.keydata.service.ServletService;
import org.apache.logging.log4j.util.Strings;
import org.mx.dal.Pagination;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 描述：车辆状态变动 服务类实现
 *
 */
@Component("vehicleStatusChangeServiceImpl")
public class VehicleStatusChangeServiceImpl implements VehicleStatusChangeService {
    private static final Logger logger = LoggerFactory.getLogger(VehicleStatusChangeServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor  ;
    private Environment env;
    private ServletService servletService ;
    private UserService userService ;
    private VehicleService vehicleService ;
    private DictionaryService dictionaryService;
    private OrganizationService organizationService;
    private NotifyActionService notifyActionService ;

    private PushDataService pushDataService ;
    private VehicleStatusChangeRepository vehicleStatusChangeRepository;


    private List<String> dics;
    private List<String> nonCombat;//非战斗状态

    /**
     * 默认的构造函数
     *
     */
    @Autowired
    public VehicleStatusChangeServiceImpl(LogService logService , @Qualifier("generalAccessor") GeneralAccessor accessor ,
                                          Environment env , ServletService servletService, UserService userService ,
                                          VehicleService vehicleService , DictionaryService dictionaryService, OrganizationService organizationService,
                                          NotifyActionService notifyActionService ,
                                          PushDataService pushDataService, VehicleStatusChangeRepository vehicleStatusChangeRepository
    ) {
        this.logService = logService ;
        this.accessor = accessor ;
        this.env = env ;
        this.servletService = servletService ;
        this.userService = userService ;
        this.vehicleService = vehicleService ;
        this.dictionaryService = dictionaryService;
        this.organizationService = organizationService;
        this.notifyActionService = notifyActionService;
        this.pushDataService = pushDataService ;
        this.vehicleStatusChangeRepository = vehicleStatusChangeRepository;


        dics = new ArrayList<>(Arrays.asList("WLCLLX", "WLCLDJ", "WLCLZT", "YS", "JLDW" , "QCLX","PMLX"  ));

        //主要记录非战时状态申请 0306 0307 0308 0399 0400 0500 0600 0700 0800 0900 0901 0902 0999 1000 1100 9900 9991
        nonCombat = new ArrayList<>(Arrays.asList("0306", "0307" , "0399", "0400", "0500", "0600", "0700", "0800", "0900",
                "0901", "0902","0999","1000", "1100", "9900", "9991"));
    }



    /**
     * {@inheritDoc}
     *
     * @see  #updateVehicleStatus(String, String , List  ,  List , String, String   )
     */
    @Transactional( rollbackFor =  Exception.class    )
    @Override
    public Boolean updateVehicleStatus( String  incidentId , String handleId ,     List<String> vehicleIds   ,
                                        List<String> handleOrganizationVehicleIds   ,  String vehicleStatusCode, String changeSource ) {
        if (vehicleIds == null || vehicleIds.size() < 1  || Strings.isBlank(vehicleStatusCode)) {
            logService.infoLog(logger, "service", "updateVehicleStatus", "vehicleId or vehicleStatusCode is blank.");
        }
        try {
            logService.infoLog(logger, "service", "updateVehicleStatus", "service is started...");
            Long logStart = System.currentTimeMillis();

             if( handleOrganizationVehicleIds == null || handleOrganizationVehicleIds.size() < 1 ){
                 for( String vehicleId : vehicleIds  ){
                     updateVehicleStatus( incidentId , handleId , vehicleId , null  , vehicleStatusCode , changeSource ) ;
                 }
             }else{
                 for( int i = 0 ; i < vehicleIds.size() ; i++ ){
                     updateVehicleStatus( incidentId , handleId , vehicleIds.get(i) , handleOrganizationVehicleIds.get(i) ,
                             vehicleStatusCode , changeSource ) ;
                 }
             }


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "updateVehicleStatus", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return true;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "updateVehicleStatus", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DATA_FAIL);
        }

    }




    /**
     * {@inheritDoc}
     *
     * @see  #updateVehicleStatus(String, String , String , String , String, String   )
     */
    @Transactional( rollbackFor =  Exception.class    )
    @Override
    public Boolean updateVehicleStatus( String  incidentId , String handleId ,   String vehicleId ,  String handleOrganizationVehicleId   ,  String vehicleStatusCode, String changeSource ) {
        if (Strings.isBlank(vehicleId) || Strings.isBlank(vehicleStatusCode)) {
            logService.infoLog(logger, "service", "updateVehicleStatus", "vehicleId or vehicleStatusCode is blank.");
        }
        try {
            logService.infoLog(logger, "service", "updateVehicleStatus", "service is started...");
            Long logStart = System.currentTimeMillis();

            EquipmentVehicleEntity equipmentVehicleEntity = accessor.getById(vehicleId, EquipmentVehicleEntity.class);

            if (equipmentVehicleEntity == null) {
                logService.infoLog(logger, "service", "updateVehicleStatus", String.format("can not find vehicle by id[%s].", vehicleId));
                throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_NULL);
            }
            if( equipmentVehicleEntity.getVehicleStatusCode().equals( vehicleStatusCode ) ){
                return  true ;
            }

            //变更车辆状态
            vehicleService.updateVehicleStatus( vehicleId , vehicleStatusCode ) ;

            //保存车辆状态变动记录
            saveVehicleStatusChange( incidentId , handleId ,  vehicleId , handleOrganizationVehicleId ,  vehicleStatusCode , changeSource ) ;

            //发送消息通知
//            //获取名称-代码对应字典
//            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;
            //消息通知所属机构 上级机构
            Set<String> orgs = new HashSet<>();
            EquipmentVehicleBean equipmentVehicleBean =   vehicleService.findVehicle( vehicleId ) ;

            List<String> orgIds = new ArrayList<>();
            orgIds.add(equipmentVehicleBean.getOrganizationId());

            List<String> parentOrganizationId = organizationService.findParentOrganizationIds( orgIds );
            orgs.addAll(parentOrganizationId ) ;

            List<String> orgCodes =  organizationService.findOrganizationCodesByIds(new ArrayList<>(parentOrganizationId));

            orgs.addAll(orgCodes);
            notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.UPDATE_VEHICLE_STATUS.getCode(),equipmentVehicleBean,orgs);

            // 推送
            Map<String, String > otherParams  = new HashMap<>() ;
            pushDataService.pushVehicleStatusChange(   incidentId ,   handleId ,      vehicleId  , vehicleStatusCode , otherParams   ) ;


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "updateVehicleStatus", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return true;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "updateVehicleStatus", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DATA_FAIL);
        }

    }





    /**
     * {@inheritDoc}
     *
     * @see VehicleStatusChangeService#saveVehicleStatusChange( String , String , String ,  String  , String , String  )
     */
    @Transactional( rollbackFor =  Exception.class )
    @Override
    public Boolean saveVehicleStatusChange(String  incidentId , String handleId ,   String vehicleId  ,  String handleOrganizationVehicleId  , String vehicleStatusCode , String changeSource ){
        if (Strings.isBlank(vehicleId) || Strings.isBlank(vehicleStatusCode)) {
            logService.infoLog(logger, "service", "saveVehicleStatusChange", "vehicleId or vehicleStatusCode is blank.");
        }
        try {
            logService.infoLog(logger, "service", "saveVehicleStatusChange", "service is started...");
            Long logStart = System.currentTimeMillis();


            UserInfo userInfo = userService.getUserInfo();
            if( userInfo == null ){
                userInfo = new UserInfo() ;
            }

            VehicleStatusChangeEntity vehicleStatusChangeEntity = new VehicleStatusChangeEntity();
            vehicleStatusChangeEntity.setIncidentId( incidentId );
            vehicleStatusChangeEntity.setHandleId( handleId );
            vehicleStatusChangeEntity.setVehicleId( vehicleId );
            vehicleStatusChangeEntity.setHandleOrganizationVehicleId( handleOrganizationVehicleId );
            vehicleStatusChangeEntity.setVehicleStatusCode( vehicleStatusCode );
            vehicleStatusChangeEntity.setChangeTime(  servletService.getSystemTime() );
            vehicleStatusChangeEntity.setChangeSource( changeSource);
            vehicleStatusChangeEntity.setOrganizationId( userInfo.getOrgId() );
            vehicleStatusChangeEntity.setSeatNumber( userInfo.getAgentNum() );
            vehicleStatusChangeEntity.setPersonId( userInfo.getAccount() );
            vehicleStatusChangeEntity.setRemarks( null );


            if ( null !=vehicleStatusChangeEntity  ) {
                logService.infoLog(logger, "repository", "save(dbVehicleStatusChangeEntity)", "repository is started...");
                Long start = System.currentTimeMillis();

                accessor.save(vehicleStatusChangeEntity);

                Long end = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbVehicleStatusChangeEntity)", String.format("repository is finished,execute time is :%sms", end - start));
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveVehicleStatusChange", String.format("service is finished,execute time is :%sms", logEnd - logStart));


            return  true ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveVehicleStatusChange", String.format(" save Vehicle Status Change  fail ","" ), ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DATA_FAIL);
        }
    }


    /**
     * 查询车辆状态
     * @param queryInputInfo
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public PaginationBean<VehicleStatusChangeBean> findVehicleStateChangeRecord(VehicleStateChangeQueryInputInfo queryInputInfo) {
        if (queryInputInfo==null) {
            logService.infoLog(logger, "service", "findVehicleStateChangeRecord", "queryInputInfo is blank.");
        }
        try {

            logService.infoLog(logger, "service", "findVehicleStateChangeRecord", "service is started...");
            Long logStart = System.currentTimeMillis();

            PaginationBean<VehicleStatusChangeBean> res = new PaginationBean<>();
            List<VehicleStatusChangeBean> beans = new ArrayList<>();

            logService.infoLog(logger, "repository", "findVehicleStateChangeRecord", "repository is started...");
            Long start = System.currentTimeMillis();

            List<Object[]> objects = vehicleStatusChangeRepository.findVehicleChangeList(nonCombat,
                    queryInputInfo.getBrigadeOrganizationId(), queryInputInfo.getOrganizationId(),
                    queryInputInfo.getVehicleState(), queryInputInfo.getWhetherPage(),
                    queryInputInfo.getPagination().getPage(),queryInputInfo.getPagination().getSize());

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findVehicleStateChangeRecord", String.format("repository is finished,execute time is :%sms", end - start));

            if (objects != null && objects.size() > 0) {
                //获得机构名称map
                Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();
                //获得警情类型字典信息
                Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;


                for (Object[] obj : objects) {
                    VehicleStatusChangeBean bean = new VehicleStatusChangeBean();
                    if (obj[0]!=null) {
                        bean.setBrigadeOrganizationName(organizationNameMap.get(obj[0].toString()));
                    }
                    bean.setOrganizationName(organizationNameMap.get(obj[1].toString()));
                    bean.setVehicleName(obj[2].toString());
                    bean.setVehicleNumber(obj[3].toString());
                    bean.setVehicleStatusName(dicsMap.get("WLCLZT").get(obj[4].toString()));
                    bean.setChangeTime(Long.parseLong(obj[5].toString()));
                    if (obj[6]!=null) {
                        bean.setSeatNumber(obj[6].toString());
                    }
                    beans.add(bean);
                }
            }

            logService.infoLog(logger, "repository", "findVehicleStateChangeRecordTotal", "repository is started...");
            Long startTotal = System.currentTimeMillis();

            Integer  total = vehicleStatusChangeRepository.findVehicleChangeTotal(nonCombat,
                    queryInputInfo.getBrigadeOrganizationId(), queryInputInfo.getOrganizationId(),
                    queryInputInfo.getVehicleState());

            Long endTotal = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findVehicleStateChangeRecordTotal", String.format("repository is finished,execute time is :%sms", endTotal - startTotal));

            Pagination pagination = new Pagination();
            pagination.setPage(queryInputInfo.getPagination().getPage());
            pagination.setSize(queryInputInfo.getPagination().getSize());
            pagination.setTotal(total);

            res.setPagination(pagination);
            res.setList(beans);


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findVehicleStateChangeRecord", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        }catch (Exception ex){
            logService.erorLog(logger, "service", "findVehicleStateChangeRecord", " findVehicleStateChangeRecord  fail ",ex);
            throw new AcceptException(AcceptException.AccetpErrors.QUERY_VEHICLE_STATE_CHANGE_FAIL);
        }

    }
}
