package com.dscomm.iecs.garage.restful;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.garage.exception.GarageException;
import com.dscomm.iecs.garage.service.ServletService;
import com.dscomm.iecs.garage.service.StatisticsService;
import com.dscomm.iecs.garage.service.bean.*;
import org.apache.logging.log4j.util.Strings;
import org.mx.service.rest.vo.DataVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * 描述:garage接口类
 *
 */
@Path("iecs/v1.0/garage")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GarageResource {
    private static final Logger logger = LoggerFactory.getLogger(GarageResource.class);
    private StatisticsService statisticsService;
    private ServletService servletService;
    private LogService logService;

    @Autowired
    public GarageResource(StatisticsService statisticsService,ServletService servletService,
                          LogService logService) {
        this.statisticsService = statisticsService;
        this.servletService = servletService;
        this.logService = logService;
    }

    /**
     * 统计车辆状态数
     * @param organizationId 机构id
     * @param whetherOnlySquadron 是否只查询本机构，  true：只查询本机构、false：查询机构id及其辖区
     * @return 返回
     */
    @Path("statistics/vehicle")
    @GET
    public DataVO<DimensionAssembleNestingStatisticsBean> findStatisticsVehicleStatus(
            @QueryParam("organizationId") String organizationId  ,
            @QueryParam("whetherOnlySquadron") Boolean whetherOnlySquadron
    ) {
        try {
            logService.infoLog(logger, "rest", "findStatisticsVehicleStatus", "start login");
            long start = System.currentTimeMillis();

            //参数判断
            if (null == whetherOnlySquadron || Strings.isBlank(organizationId)) {
                return new DataVO<>(new GarageException(GarageException.GarageErrors.DATA_NULL));
            }

            DimensionAssembleNestingStatisticsBean res = statisticsService.findStatisticsVehicleStatus(organizationId, whetherOnlySquadron);

            long end = System.currentTimeMillis();
            logService.infoLog(logger, "rest", "findStatisticsVehicleStatus", String.format("rest is finished,execute time is :%sms", end - start));

            return new DataVO<>(res);
        } catch (GarageException ex) {
            logger.error("visit fail",ex);
            return new DataVO<>(ex);
        }catch (Exception ex){
            logger.error("visit rest fail",ex);
            return new DataVO<>(new GarageException());
        }
    }

    /**
     * 根据机构id获取车辆 详情
     * @param organizationId 机构id
     * @return 返回
     */
    @Path("vehicle")
    @GET
    public DataVO<List<EquipmentVehicleBean>> findVehicle(
            @QueryParam("organizationId") String organizationId
    ) {
        try {
            logService.infoLog(logger, "rest", "findVehicle", "start login");
            long start = System.currentTimeMillis();

            //参数判断
            if (Strings.isBlank(organizationId)) {
                return new DataVO<>(new GarageException(GarageException.GarageErrors.DATA_NULL));
            }

            List<EquipmentVehicleBean> res = statisticsService.findVehicle(organizationId);

            long end = System.currentTimeMillis();
            logService.infoLog(logger, "rest", "findVehicle", String.format("rest is finished,execute time is :%sms", end - start));

            return new DataVO<>(res);
        } catch (GarageException ex) {
            logger.error("visit fail",ex);
            return new DataVO<>(ex);
        }catch (Exception ex){
            logger.error("visit rest fail",ex);
            return new DataVO<>(new GarageException());
        }
    }



    /**
     * 根据 机构id 获得辖区中队 车辆数量信息
     * @param organizationId 机构id
     * @return 返回
     */
    @Path("statistics/num")
    @GET
    public DataVO<List<VehicleNumBean>> findStatisticsVehicleNum(
            @QueryParam("organizationId") String organizationId
    ) {
        try {
            logService.infoLog(logger, "rest", "findStatisticsVehicleNum", "start login");
            long start = System.currentTimeMillis();

            //参数判断
            if (  Strings.isBlank(organizationId)) {
                return new DataVO<>(new GarageException(GarageException.GarageErrors.DATA_NULL));
            }

            List<VehicleNumBean> res = statisticsService.findStatisticsVehicleNum(organizationId );

            long end = System.currentTimeMillis();
            logService.infoLog(logger, "rest", "findStatisticsVehicleNum", String.format("rest is finished,execute time is :%sms", end - start));

            return new DataVO<>(res);
        } catch (GarageException ex) {
            logger.error("visit fail",ex);
            return new DataVO<>(ex);
        }catch (Exception ex){
            logger.error("visit rest fail",ex);
            return new DataVO<>(new GarageException());
        }
    }



    /**
     * 根据 机构id 获得出动车辆数趋势信息
     * @param organizationId 机构id
     * @return 返回
     */
    @Path("statistics/trend")
    @GET
    public DataVO<List<TimeTrendBean>> findStatisticsVehicleOnDutyTrend(
            @QueryParam("organizationId") String organizationId ,@QueryParam("timeType") String timeType ,
            @QueryParam("time") int time
    ) {
        try {
            logService.infoLog(logger, "rest", "findStatisticsVehicleOnDutyTrend", "start login");
            long start = System.currentTimeMillis();

            //参数判断
            if (  Strings.isBlank(organizationId) || Strings.isBlank( timeType )  ) {
                return new DataVO<>(new GarageException(GarageException.GarageErrors.DATA_NULL));
            }

            List<TimeTrendBean> res = statisticsService.findStatisticsVehicleOnDutyTrend(organizationId , timeType , time  );

            long end = System.currentTimeMillis();
            logService.infoLog(logger, "rest", "findStatisticsVehicleOnDutyTrend", String.format("rest is finished,execute time is :%sms", end - start));

            return new DataVO<>(res);
        } catch (GarageException ex) {
            logger.error("visit fail",ex);
            return new DataVO<>(ex);
        }catch (Exception ex){
            logger.error("visit rest fail",ex);
            return new DataVO<>(new GarageException());
        }
    }


    /**
     * 根据 机构id 获得中队平均出动时长
     * @param organizationId 机构id
     * @return 返回
     */
    @Path("statistics/dispatch/avg")
    @GET
    public DataVO<List<VehicleNumBean>> findStatisticsOrganizationGoOutAvgTime(
            @QueryParam("organizationId") String organizationId ,  @QueryParam("type") Integer type
            ,  @QueryParam("top") Integer top
    ) {
        try {
            logService.infoLog(logger, "rest", "findStatisticsVehicleOnDutyTrend", "start login");
            long start = System.currentTimeMillis();

            //参数判断
            if (  Strings.isBlank(organizationId) || type == null  || top == null   ) {
                return new DataVO<>(new GarageException(GarageException.GarageErrors.DATA_NULL));
            }

            List<VehicleNumBean> res = statisticsService.findStatisticsOrganizationGoOutAvgTime(organizationId , type ,top );

            long end = System.currentTimeMillis();
            logService.infoLog(logger, "rest", "findStatisticsVehicleOnDutyTrend", String.format("rest is finished,execute time is :%sms", end - start));

            return new DataVO<>(res);
        } catch (GarageException ex) {
            logger.error("visit fail",ex);
            return new DataVO<>(ex);
        }catch (Exception ex){
            logger.error("visit rest fail",ex);
            return new DataVO<>(new GarageException());
        }
    }


    /**
     * 获取系统时间
     * @return 返回
     */
    @Path("system/time")
    @GET
    public DataVO<Long> getSystemTime() {
        try {
            logService.infoLog(logger, "rest", "getSystemTime", "start login");
            long start = System.currentTimeMillis();

            Long res = servletService.getSystemTime();

            long end = System.currentTimeMillis();
            logService.infoLog(logger, "rest", "getSystemTime", String.format("rest is finished,execute time is :%sms", end - start));

            return new DataVO<>(res);
        } catch (GarageException ex) {
            logger.error("visit fail",ex);
            return new DataVO<>(ex);
        }catch (Exception ex){
            logger.error("visit rest fail",ex);
            return new DataVO<>(new GarageException());
        }
    }

    /**
     * 获取机构详情
     * @return 返回
     */
    @Path("organization")
    @GET
    public DataVO<OrganizationBean> getOrganization(
            @QueryParam("organizationId") String organizationId
    ) {
        try {
            logService.infoLog(logger, "rest", "getOrganization", "start login");
            long start = System.currentTimeMillis();

            OrganizationBean res = statisticsService.findOrganization(organizationId);

            long end = System.currentTimeMillis();
            logService.infoLog(logger, "rest", "getOrganization", String.format("rest is finished,execute time is :%sms", end - start));

            return new DataVO<>(res);
        } catch (GarageException ex) {
            logger.error("visit fail",ex);
            return new DataVO<>(ex);
        }catch (Exception ex){
            logger.error("visit rest fail",ex);
            return new DataVO<>(new GarageException());
        }
    }

    /**
     * 获取最新火灾（警情）
     * @param organizationId 机构id
     * @param limit 前limit条
     * @return 案件详情列表
     */
    @Path("latest/incident")
    @GET
    public DataVO<List<IncidentBean>> getLatestIncident(
            @QueryParam("organizationId") String organizationId,
            @QueryParam("limit") Integer limit
    ) {
        try {
            logService.infoLog(logger, "rest", "getLatestIncident", "start login");
            long start = System.currentTimeMillis();

            List<IncidentBean> res = statisticsService.findLatestIncident(organizationId,limit);

            long end = System.currentTimeMillis();
            logService.infoLog(logger, "rest", "getLatestIncident", String.format("rest is finished,execute time is :%sms", end - start));

            return new DataVO<>(res);
        } catch (GarageException ex) {
            logger.error("visit fail",ex);
            return new DataVO<>(ex);
        }catch (Exception ex){
            logger.error("visit rest fail",ex);
            return new DataVO<>(new GarageException());
        }
    }

    /**
     * 根据 机构id 获得辖区中队 出动车次排行
     * @param organizationId 机构id
     * @param type 排行类别 1 本周数据 2 为本月数据 其他默认本周
     * @param limit 前limit
     * @return 返回
     */
    @Path("statistics/dispatch/vehicle")
    @GET
    public DataVO<List<VehicleNumBean>> getStatisticsDispatchVehicleCount(
            @QueryParam("organizationId") String organizationId,
            @QueryParam("type") Integer type,
            @QueryParam("limit") Integer limit
    ) {
        try {
            logService.infoLog(logger, "rest", "getStatisticsDispatchVehicleCount", "start login");
            long start = System.currentTimeMillis();

            //参数判断
            if (Strings.isBlank(organizationId)) {
                return new DataVO<>(new GarageException(GarageException.GarageErrors.DATA_NULL));
            }

            List<VehicleNumBean> res = statisticsService.findStatisticsDispatchVehicleCount(organizationId, type, limit);

            long end = System.currentTimeMillis();
            logService.infoLog(logger, "rest", "getStatisticsDispatchVehicleCount", String.format("rest is finished,execute time is :%sms", end - start));

            return new DataVO<>(res);
        } catch (GarageException ex) {
            logger.error("visit fail",ex);
            return new DataVO<>(ex);
        }catch (Exception ex){
            logger.error("visit rest fail",ex);
            return new DataVO<>(new GarageException());
        }
    }

}
