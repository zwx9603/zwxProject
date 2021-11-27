package com.dscomm.iecs.basedata.service.impl;

import com.dscomm.iecs.base.graphql.typebean.PaginationBean;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.dal.po.UnitEmergencyEntity;
import com.dscomm.iecs.basedata.dal.repository.UnitEmergencyRepository;
import com.dscomm.iecs.basedata.exception.BasedataException;
import com.dscomm.iecs.basedata.graphql.inputbean.UnitEmergencyQueryInputinfo;
import com.dscomm.iecs.basedata.graphql.typebean.UnitEmergencyBean;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.basedata.service.UnitEmergencyService;
import com.dscomm.iecs.basedata.utils.UnitEmergencyTransformUtil;
import org.apache.logging.log4j.util.Strings;
import org.mx.dal.Pagination;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 描述：应急联动单位 服务类实现
 */
@Component("unitEmergencyServiceImpl")
public class UnitEmergencyServiceImpl implements UnitEmergencyService {
    private static final Logger logger = LoggerFactory.getLogger(UnitJointServiceServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private DictionaryService dictionaryService;
    private OrganizationService organizationService;
    private UnitEmergencyRepository unitEmergencyRepository;

    /**
     * 默认的构造函数
     */
    @Autowired
    public UnitEmergencyServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor,
                                    DictionaryService dictionaryService,
                                    OrganizationService organizationService ,
                                    UnitEmergencyRepository unitEmergencyRepository


    ) {
        this.logService = logService;
        this.accessor = accessor;
        this.unitEmergencyRepository = unitEmergencyRepository;
        this.dictionaryService = dictionaryService;
        this.organizationService = organizationService;

        //dics = new ArrayList<>(Arrays.asList( "WZZT","WZZBLX","WZDPZT","XZQX" ) );
    }
    @Override
    public PaginationBean<UnitEmergencyBean> findUnitEmergencyPage(UnitEmergencyQueryInputinfo inputInfo) {
        if ( inputInfo == null   ) {
            logService.infoLog(logger, "service", "findUnitEmergencyPage", "UnitEmergencyQueryInputinfo is Blank.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findUnitEmergencyPage", "service is started...");
            Long logStart = System.currentTimeMillis();

            PaginationBean<UnitEmergencyBean> res = new PaginationBean() ;

            String squadronOrganizationSearchPath = null ;
            if( Strings.isNotBlank( inputInfo.getOrganizationId() )){
                squadronOrganizationSearchPath = organizationService.findSearchPathById ( inputInfo.getOrganizationId());
            }

            logService.infoLog(logger, "repository", "findUnitEmergencyPage", "repository is started...");
            Long start = System.currentTimeMillis();

            //根据条件查询车辆信息
            List<UnitEmergencyEntity> unitEmergencyEntityList = unitEmergencyRepository.findUnitEmergencyCondition(
                    inputInfo.getKeyword(),  inputInfo.getDistrictCode() , squadronOrganizationSearchPath,
                    inputInfo.getWhetherPage(), inputInfo.getPagination().getPage(),
                    inputInfo.getPagination().getSize());

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findUnitEmergencyPage", String.format("repository is finished,execute time is :%sms", end - start));

            //获取名称-代码对应字典
            Map<String, Map<String, String>> dicsMap = null;//dictionaryService.findDictionaryMap(dics);
            //机构数据
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();

            List<UnitEmergencyBean> beans = new ArrayList<>( ) ;

            if (unitEmergencyEntityList != null && unitEmergencyEntityList.size() > 0) {
                for ( UnitEmergencyEntity unitEmergencyEntity : unitEmergencyEntityList) {
                    UnitEmergencyBean bean = UnitEmergencyTransformUtil.transform( unitEmergencyEntity , dicsMap , organizationNameMap ) ;
                    beans.add(bean);
                }
                res.setList( beans );
            }

            logService.infoLog(logger, "repository", "findUnitEmergencyPage", "repository is started...");
            Long countStart = System.currentTimeMillis();

            //获取分页参数total
            Integer total = unitEmergencyRepository.findUnitEmergencyTotal(
                    inputInfo.getKeyword(),  inputInfo.getDistrictCode() , squadronOrganizationSearchPath ) ;

            Long countEnd = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findUnitEmergencyPage", String.format("repository is finished,execute time is :%sms", countEnd - countStart));

            //装配结果
            Pagination pagination = new Pagination();
            pagination.setPage(inputInfo.getPagination().getPage());
            pagination.setSize(inputInfo.getPagination().getSize());
            pagination.setTotal(total);

            res.setPagination(pagination);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findUnitEmergencyPage", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return  res ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findUnitEmergencyPage", String.format("save Miniature Organization fail "), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_DATA_FAIL);
        }
    }
}
