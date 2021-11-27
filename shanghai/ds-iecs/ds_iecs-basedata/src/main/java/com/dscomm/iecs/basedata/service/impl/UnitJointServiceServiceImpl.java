package com.dscomm.iecs.basedata.service.impl;

import com.dscomm.iecs.base.graphql.typebean.PaginationBean;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.dal.po.UnitJointServiceEntity;
import com.dscomm.iecs.basedata.dal.repository.UnitJointServiceRepository;
import com.dscomm.iecs.basedata.exception.BasedataException;
import com.dscomm.iecs.basedata.graphql.inputbean.UnitJointServiceQueryInputinfo;
import com.dscomm.iecs.basedata.graphql.typebean.UnitJointServiceBean;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.basedata.service.UnitJointServiceService;
import com.dscomm.iecs.basedata.utils.UnitJointServiceTransformUtil;
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
 * 描述：联勤保障单位 服务类实现
 */
@Component("unitJointServiceServiceImpl")
public class UnitJointServiceServiceImpl implements UnitJointServiceService {
    private static final Logger logger = LoggerFactory.getLogger(UnitJointServiceServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private DictionaryService dictionaryService;
    private OrganizationService organizationService;
    private UnitJointServiceRepository nitJointServiceRepository;

    /**
     * 默认的构造函数
     */
    @Autowired
    public UnitJointServiceServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor,
                                       DictionaryService dictionaryService,
                                       OrganizationService organizationService ,
                                       UnitJointServiceRepository nitJointServiceRepository


    ) {
        this.logService = logService;
        this.accessor = accessor;
        this.nitJointServiceRepository = nitJointServiceRepository;
        this.dictionaryService = dictionaryService;
        this.organizationService = organizationService;

        //dics = new ArrayList<>(Arrays.asList( "WZZT","WZZBLX","WZDPZT","XZQX" ) );
    }
    @Override
    public PaginationBean<UnitJointServiceBean> findUnitJointPage(UnitJointServiceQueryInputinfo inputInfo) {
        if ( inputInfo == null   ) {
            logService.infoLog(logger, "service", "findUnitJointPage", "UnitJointServiceQueryInputinfo is Blank.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findUnitJointPage", "service is started...");
            Long logStart = System.currentTimeMillis();

            PaginationBean<UnitJointServiceBean> res = new PaginationBean() ;

            String squadronOrganizationSearchPath = null ;
            if( Strings.isNotBlank( inputInfo.getOrganizationId() )){
                squadronOrganizationSearchPath = organizationService.findSearchPathById ( inputInfo.getOrganizationId());
            }

            logService.infoLog(logger, "repository", "findUnitJointPage", "repository is started...");
            Long start = System.currentTimeMillis();

            //根据条件查询车辆信息
            List<UnitJointServiceEntity> unitJointServiceEntityList = nitJointServiceRepository.findUnitJointServiceCondition(
                    inputInfo.getKeyword(),  inputInfo.getDistrictCode() , squadronOrganizationSearchPath,
                    inputInfo.getWhetherPage(), inputInfo.getPagination().getPage(),
                    inputInfo.getPagination().getSize());

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findUnitJointPage", String.format("repository is finished,execute time is :%sms", end - start));

            //获取名称-代码对应字典
            Map<String, Map<String, String>> dicsMap = null;//dictionaryService.findDictionaryMap(dics);
            //机构数据
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();

            List<UnitJointServiceBean> beans = new ArrayList<>( ) ;

            if (unitJointServiceEntityList != null && unitJointServiceEntityList.size() > 0) {
                for ( UnitJointServiceEntity unitJointServiceEntity : unitJointServiceEntityList) {
                    UnitJointServiceBean bean = UnitJointServiceTransformUtil.transform( unitJointServiceEntity , dicsMap , organizationNameMap ) ;
                    beans.add(bean);
                }
                res.setList( beans );
            }

            logService.infoLog(logger, "repository", "findUnitJointPage", "repository is started...");
            Long countStart = System.currentTimeMillis();

            //获取分页参数total
            Integer total = nitJointServiceRepository.findUnitJointServiceTotal(
                    inputInfo.getKeyword(),  inputInfo.getDistrictCode() , squadronOrganizationSearchPath ) ;

            Long countEnd = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findUnitJointPage", String.format("repository is finished,execute time is :%sms", countEnd - countStart));

            //装配结果
            Pagination pagination = new Pagination();
            pagination.setPage(inputInfo.getPagination().getPage());
            pagination.setSize(inputInfo.getPagination().getSize());
            pagination.setTotal(total);

            res.setPagination(pagination);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findUnitJointPage", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return  res ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findUnitJointPage", String.format("save Miniature Organization fail "), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_DATA_FAIL);
        }
    }
}
