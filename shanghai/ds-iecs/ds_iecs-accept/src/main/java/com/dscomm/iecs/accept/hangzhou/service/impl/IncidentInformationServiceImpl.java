package com.dscomm.iecs.accept.hangzhou.service.impl;

import com.dscomm.iecs.accept.dal.po.IncidentEntity;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.hangzhou.bean.IncidentInformationBean;
import com.dscomm.iecs.accept.hangzhou.bean.IncidentShowBean;
import com.dscomm.iecs.accept.hangzhou.service.IncidentInformationService;
import com.dscomm.iecs.base.graphql.inputbean.PaginationInputInfo;
import com.dscomm.iecs.base.graphql.typebean.PaginationBean;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.base.utils.DateHandleUtils;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.ext.incident.type.INCIDENT_TYPE_HZPJ;
import com.dscomm.iecs.ext.incident.type.INCIDENT_TYPE_QXJY;
import com.dscomm.iecs.keydata.service.ServletService;
import org.mx.DateUtils;
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

@Component("incidentInformationServiceImpl")
public class IncidentInformationServiceImpl implements IncidentInformationService {

    private static final Logger logger = LoggerFactory.getLogger(IncidentInformationServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private ServletService servletService ;
    private List<String> dics;
    private DictionaryService dictionaryService;
    private Environment env;

    @Autowired
    public IncidentInformationServiceImpl(LogService logService,
                                          @Qualifier("generalAccessor") GeneralAccessor accessor,
                                          ServletService servletService,DictionaryService dictionaryService ,
                                          Environment env ) {
        this.logService = logService;
        this.accessor = accessor;
        this.servletService = servletService;
        this.dictionaryService = dictionaryService;
        this.env = env ;
        dics = new ArrayList<>(Arrays.asList("AJLX"));
    }

    /**获取当日火情、抢险数量及最新警情信息*/
    @Transactional(  readOnly =  true )
    @Override
    public IncidentInformationBean showIncidentInformation() {
        try {
            logService.infoLog(logger, "service", "showIncidentInformation", "service is started...");
            Long logStart = System.currentTimeMillis();

            Long time = servletService.getSystemTime();
            //设置时间段参数
            Calendar cal = DateHandleUtils.buildCalendar(  env.getProperty("timeZone") ) ;
            Date timeDate = new Date(time);
            cal.setTime(timeDate);
            cal.set(Calendar.HOUR_OF_DAY,   0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            //今天数据统计时间段参数（minTodayTime - maxTodayTime）
            Long minTodayTime = cal.getTimeInMillis();
            Long maxTodayTime = DateUtils.add(new Date(minTodayTime), DateUtils.FieldType.DAY, 1).getTime();

            IncidentInformationBean res = new IncidentInformationBean();
            IncidentShowBean incidentShowBean = new IncidentShowBean();

            // 查询出所有需要用到的字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);

            GeneralAccessor.ConditionGroup incidentConditionGroup = GeneralAccessor.ConditionGroup.and(
                    GeneralAccessor.ConditionTuple.eq("valid",1),
                    GeneralAccessor.ConditionTuple.gte("registerIncidentTime",minTodayTime),
                    GeneralAccessor.ConditionTuple.lte("registerIncidentTime",maxTodayTime),
                    GeneralAccessor.ConditionTuple.in("incidentTypeCode",INCIDENT_TYPE_HZPJ.getCode() ,INCIDENT_TYPE_QXJY.getCode() )
            );

            Pagination pagination = new Pagination(0,1,1);

            GeneralAccessor.RecordOrderGroup incidentOrderGroup = GeneralAccessor.RecordOrderGroup.group(
                   GeneralAccessor.RecordOrder.desc("registerIncidentTime")
            );

            //获取最新警情
            List<IncidentEntity> incidentEntities = accessor.find(pagination,incidentConditionGroup,incidentOrderGroup,IncidentEntity.class);
            if (incidentEntities != null && incidentEntities.size()>0){
                incidentShowBean.setIncidentAddress(incidentEntities.get(0).getCrimeAddress());
                incidentShowBean.setInctdentTime(new Date(incidentEntities.get(0).getRegisterIncidentTime()));
                incidentShowBean.setIncidentType(dicsMap.get("AJLX").get(incidentEntities.get(0).getIncidentTypeCode()));
                res.setLastIncident(incidentShowBean);
            }

            //获取火灾数量
            GeneralAccessor.ConditionGroup fireConditionGroup = GeneralAccessor.ConditionGroup.and(
                    GeneralAccessor.ConditionTuple.eq("valid",1),
                    GeneralAccessor.ConditionTuple.gte("registerIncidentTime",minTodayTime),
                    GeneralAccessor.ConditionTuple.lte("registerIncidentTime",maxTodayTime),
                    GeneralAccessor.ConditionTuple.eq("incidentTypeCode", INCIDENT_TYPE_HZPJ.getCode() )
            );

            Long countFire = accessor.count(fireConditionGroup,IncidentEntity.class);
            res.setFireCount(Integer.parseInt(countFire.toString()));

            //获取抢险数量
            GeneralAccessor.ConditionGroup rescueConditionGroup = GeneralAccessor.ConditionGroup.and(
                    GeneralAccessor.ConditionTuple.eq("valid",1),
                    GeneralAccessor.ConditionTuple.gte("registerIncidentTime",minTodayTime),
                    GeneralAccessor.ConditionTuple.lte("registerIncidentTime",maxTodayTime),
                    GeneralAccessor.ConditionTuple.eq("incidentTypeCode", INCIDENT_TYPE_QXJY.getCode() )
            );

            Long countRescue = accessor.count(rescueConditionGroup,IncidentEntity.class);
            res.setRescueCount(Integer.parseInt(countRescue.toString()));

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "showIncidentInformation", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        }catch (Exception e){
            logService.erorLog(logger, "service", "showIncidentInformation", "execution error", e);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_INCIDENTS_FAIL);
        }

    }

    /**获取当日警情信息*/
    @Transactional(  readOnly =  true )
    @Override
    public PaginationBean<IncidentShowBean> twentyFourHoursIncident(PaginationInputInfo paginationInputInfo) {
        try {
            logService.infoLog(logger, "service", "twentyFourHoursIncident", "service is started...");
            Long logStart = System.currentTimeMillis();

            PaginationBean res = new PaginationBean();
            List<IncidentShowBean> beans  = new ArrayList<>();

            Long time = servletService.getSystemTime();
            //设置时间段参数
            Calendar cal = DateHandleUtils.buildCalendar(  env.getProperty("timeZone") ) ;
            Date timeDate = new Date(time);
            cal.setTime(timeDate);
            cal.set(Calendar.HOUR_OF_DAY,   0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            //今天数据统计时间段参数（minTodayTime - maxTodayTime）
            Long minTodayTime = cal.getTimeInMillis();
            Long maxTodayTime = DateUtils.add(new Date(minTodayTime), DateUtils.FieldType.DAY, 1).getTime();

            // 查询出所有需要用到的字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);

            GeneralAccessor.ConditionGroup incidentConditionGroup = GeneralAccessor.ConditionGroup.and(
                    GeneralAccessor.ConditionTuple.eq("valid",1),
                    GeneralAccessor.ConditionTuple.gte("registerIncidentTime",minTodayTime),
                    GeneralAccessor.ConditionTuple.lte("registerIncidentTime",maxTodayTime),
                    GeneralAccessor.ConditionTuple.in("incidentTypeCode",INCIDENT_TYPE_HZPJ.getCode() ,INCIDENT_TYPE_QXJY.getCode() )
            );

            //获取警情数量
            GeneralAccessor.ConditionGroup fireConditionGroup = GeneralAccessor.ConditionGroup.and(
                    GeneralAccessor.ConditionTuple.eq("valid",1),
                    GeneralAccessor.ConditionTuple.gte("registerIncidentTime",minTodayTime),
                    GeneralAccessor.ConditionTuple.lte("registerIncidentTime",maxTodayTime),
                    GeneralAccessor.ConditionTuple.in("incidentTypeCode",INCIDENT_TYPE_HZPJ.getCode() ,INCIDENT_TYPE_QXJY.getCode() )
            );

            Long countFire = accessor.count(fireConditionGroup,IncidentEntity.class);

            Pagination pagination = new Pagination();

            if (paginationInputInfo != null){
                pagination.setPage(paginationInputInfo.getPage());
                pagination.setSize(paginationInputInfo.getSize());
            }
            pagination.setTotal(  Integer.parseInt( String.valueOf( countFire ) ) );

            if( countFire > 0 ){
                GeneralAccessor.RecordOrderGroup incidentOrderGroup = GeneralAccessor.RecordOrderGroup.group(
                        GeneralAccessor.RecordOrder.desc("registerIncidentTime")
                );
                //获取24小时警情
                List<IncidentEntity> incidentEntities = accessor.find(pagination,incidentConditionGroup,incidentOrderGroup,IncidentEntity.class);
                if (incidentEntities != null && incidentEntities.size()>0){
                    for (IncidentEntity incidentEntity:incidentEntities
                    ) {
                        IncidentShowBean incidentShowBean = new IncidentShowBean();
                        incidentShowBean.setIncidentAddress(incidentEntity.getCrimeAddress());
                        incidentShowBean.setInctdentTime(new Date(incidentEntity.getRegisterIncidentTime()));
                        incidentShowBean.setIncidentType(dicsMap.get("AJLX").get(incidentEntity.getIncidentTypeCode()));
                        beans.add(incidentShowBean);
                    }
                }
            }

            res.setPagination( pagination );
            res.setList( beans );

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "twentyFourHoursIncident", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        }catch (Exception e){
            logService.erorLog(logger, "service", "twentyFourHoursIncident", "execution error", e);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_INCIDENTS_FAIL);
        }
    }
}
