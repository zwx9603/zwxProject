package com.dscomm.iecs.schedule.service.basedata.water;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.dal.po.WaterBaseInfoEntity;
import com.dscomm.iecs.schedule.dal.po.TransporterEntity;
import com.dscomm.iecs.schedule.exception.ScheduleException;
import com.dscomm.iecs.schedule.factory.ConnectionsFactory;
import com.dscomm.iecs.schedule.utils.ScheduleUtil;
import org.mx.DateUtils;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component("waterSourceBaseScheduleServiceImpl")
public class WaterSourceBaseScheduleServiceImpl implements WaterSourceBaseScheduleService {


    private static final Logger logger = LoggerFactory.getLogger(WaterSourceBaseScheduleServiceImpl.class);
    private LogService logService;

    private Environment env;
    private GeneralAccessor accessor;
    private ConnectionsFactory connectionsFactory;
    private static final String id = "sy_syjbxx";


    @Autowired
    public WaterSourceBaseScheduleServiceImpl(LogService logService , Environment env,
                                         @Qualifier("generalAccessor") GeneralAccessor accessor,
                                         ConnectionsFactory connectionsFactory   ) {
        this.logService = logService;
        this.env = env;
        this.accessor = accessor;
        this.connectionsFactory = connectionsFactory;
    }

    @Override
    @Transactional( rollbackFor = Exception.class )
    public void transport(Long targetTime) {
        try {
            logService.infoLog(logger, "service", "WaterSourceBase", "service is started...");
            Long logStart = System.currentTimeMillis();

            TransporterEntity entity = accessor.getById(id, TransporterEntity.class);
            if (entity == null) {
                logService.erorLog(logger, "service", "WaterSourceBase", "table config not exist ",  new Exception() );
                return ;
            }
            Long lastUpdataTime = entity.getZHTBSJ();
            DateUtils.FieldType fieldType = ScheduleUtil.transFormDateFieldType(entity.getDCTBSCDW() );
            int onceTime = entity.getDCTBSC();
            //更新时间晚于目标时间，则进行同步操作
            if (lastUpdataTime != null && targetTime != null && lastUpdataTime < targetTime) {
                int i = 0 ; //单次最多执行 7 次
                while (lastUpdataTime < targetTime && i< 7 ) {
                    Long endTime = DateUtils.add(new Date(lastUpdataTime), fieldType, onceTime).getTime();
                    if (endTime > targetTime) {
                        endTime = targetTime;
                    }
                    //单次查询
                    List<WaterBaseInfoEntity> list = findScheduleTable(lastUpdataTime, endTime);
                    //结果不为空 保存数据
                    if (list != null && list.size() > 0) {
                        logService.infoLog(logger, "service", "WaterSourceBase",
                                String.format(" find schedule table total : %d ,start time :%s ,end time :%s" , list.size()  , new Timestamp(lastUpdataTime) , new Timestamp(endTime)  )   );

                        accessor.save(list);
                    }else{
                        logService.infoLog(logger, "service", "WaterSourceBase",
                                String.format(" find schedule table is null  ,start time :%s ,end time :%s" ,   new Timestamp(lastUpdataTime) , new Timestamp(endTime)  ) );
                    }
                    //修改更新时间
                    lastUpdataTime = endTime;
                    //更新数据库的更新时间
                    entity.setZHTBSJ(lastUpdataTime);
                    accessor.save(entity);
                    i++ ;
                }
            }
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "WaterSourceBase", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "WaterSourceBase", String.format(" transport account task fail  , target time :%s.", new Timestamp(targetTime) ), ex);
            throw new ScheduleException(ScheduleException.ScheduleErrors.SCHEDULE_DATA_FAIL);
        }

    }



    private static String sql = " select " +
            " v_tb_sy_syjbxx.id as id  ," +
            " v_tb_sy_syjbxx.cjsj    as cjsj ," +
            " v_tb_sy_syjbxx.gxsj    as gxsj ," +
            " v_tb_sy_syjbxx.yxx as yxx ," +
            " v_tb_sy_syjbxx.czz as czz," +
            " v_tb_sy_syjbxx.gis_h as gis_h," +
            " v_tb_sy_syjbxx.shzt as shzt," +
            " v_tb_sy_syjbxx.kyzt  as kyzt," +
            " v_tb_sy_syjbxx.ssxq as ssxq ," +
            " v_tb_sy_syjbxx.fwtd as fwtd," +
            " v_tb_sy_syjbxx.qysj  as qysj ," +
            " v_tb_sy_syjbxx.xfslx  as xfslx ," +
            " v_tb_sy_syjbxx.jkxs as jkxs ," +
            " v_tb_sy_syjbxx.gis_x  as gis_x ," +
            " v_tb_sy_syjbxx.gis_y  as gis_y ," +
            " v_tb_sy_syjbxx.gis_x_map  as gis_x_map ," +
            " v_tb_sy_syjbxx.gis_y_map  as gis_y_map ," +
            " v_tb_sy_syjbxx.gis_h_map as gis_h_map ," +
            " v_tb_sy_syjbxx.xz as xz ," +
            " v_tb_sy_syjbxx.blgxd as blgxd ," +
            " v_tb_sy_syjbxx.blgxb as blgxb ," +
            " v_tb_sy_syjbxx.blgxn as blgxn ," +
            " v_tb_sy_syjbxx.blgxx as blgxx ," +
            " v_tb_sy_syjbxx.fwtb as fwtb ," +
            " v_tb_sy_syjbxx.bm as bm ," +
            " v_tb_sy_syjbxx.jgid as jgid ," +
            " v_tb_sy_syjbxx.jgmc as jgmc ," +
            " v_tb_sy_syjbxx.zpwjurl as zpwjurl ," +
            " v_tb_sy_syjbxx.gwzj as gwzj ," +
            " v_tb_sy_syjbxx.gwxs as gwxs ," +
            " v_tb_sy_syjbxx.gwid as gwid ," +
            " v_tb_sy_syjbxx.gwid as gwmc ," +
            " v_tb_sy_syjbxx.gwyl as gwyl ," +
            " v_tb_sy_syjbxx.fzxs as fzxs ," +
            " v_tb_sy_syjbxx.ssld as ssld ," +
            " v_tb_sy_syjbxx.szxs as szxs ," +
            " v_tb_sy_syjbxx.fwtn as fwtn ," +
            " v_tb_sy_syjbxx.lxfs as lxfs ," +
            " v_tb_sy_syjbxx.sydz as sydz ," +
            " v_tb_sy_syjbxx.sysxxxid as sysxxxid ," +
            " v_tb_sy_syjbxx.jzsj    as jzsj ," +
            " v_tb_sy_syjbxx.sybh as sybh ," +
            " v_tb_sy_syjbxx.czyid as czyid ," +
            " v_tb_sy_syjbxx.qsxs as qsxs," +
            " v_tb_sy_syjbxx.symc as symc ," +
            " v_tb_sy_syjbxx.sylx as sylx ," +
            " v_tb_sy_syjbxx.gsdw as gsdw ," +
            " v_tb_sy_syjbxx.fwtx as fwtx  ," +
            " v_tb_sy_syjbxx.jlzt    as jlzt  ," +
            " v_tb_sy_syjbxx.sjc   as sjc ," +
            " v_tb_sy_syjbxx.syzt  as syzt," +
            " v_tb_sy_syjbxx.xfsszkflydm  as xfsszkflydm," +
            " v_tb_sy_syjbxx.mc as mc ," +
            " v_tb_sy_syjbxx.dzmc as dzmc ," +
            " v_tb_sy_syjbxx.xfjyjg_tywysbm as xfjyjg_tywysbm ," +
            " v_tb_sy_syjbxx.xfjyjg_tywysmc as xfjyjg_tywysmc ," +
            " v_tb_sy_syjbxx.xzqhdm as xzqhdm ," +
            " v_tb_sy_syjbxx.qsxs_jyqk as qsxs_jyqk," +
            " v_tb_sy_syjbxx.xhsfzxslbdm as xhsfzxslbdm ," +
            " v_tb_sy_syjbxx.sykyztlbdm  as sykyztlbdm," +
            " v_tb_sy_syjbxx.ssgw_mc as ssgw_mc ," +
            " v_tb_sy_syjbxx.gs_dwmc as gs_dwmc ," +
            " v_tb_sy_syjbxx.gl_dwmc as gl_dwmc ," +
            " v_tb_sy_syjbxx.wb_dwmc as wb_dwmc ," +
            " v_tb_sy_syjbxx.lxr as lxr ," +
            " v_tb_sy_syjbxx.lxr_xm as lxr_xm ," +
            " v_tb_sy_syjbxx.lxr_lxdh as lxr_lxdh , " +
            " v_tb_sy_syjbxx.xfjsgwxslxdm as xfjsgwxslxdm ," +
            " v_tb_sy_syjbxx.gwzj_kd as gwzj_kd ," +
            " v_tb_sy_syjbxx.gw_yl as gw_yl ," +
            " v_tb_sy_syjbxx.xfsdjklxdm as xfsdjklxdm ," +
            " v_tb_sy_syjbxx.ssld_mc as ssld_mc ," +
            " v_tb_sy_syjbxx.dllkld_jyqk as dllkld_jyqk ," +
            " v_tb_sy_syjbxx.xhslbdm as xhslbdm ," +
            " v_tb_sy_syjbxx.dqjd  as dqjd ," +
            " v_tb_sy_syjbxx.dqwd  as dqwd ," +
            " v_tb_sy_syjbxx.dqgd as dqgd," +
            " v_tb_sy_syjbxx.fwt AS fwt," +
            " v_tb_sy_syjbxx.sjt AS sjt," +
            " v_tb_sy_syjbxx.jiz_rq   as jiz_rq ," +
            " v_tb_sy_syjbxx.qy_rq as qy_rq " +
            "  from  v_tb_sy_syjbxx " +
            "  where v_tb_sy_syjbxx.sjc >?  and v_tb_sy_syjbxx.sjc <= ?  " +
            " ";


    /**
     * 从原数据库查询数据
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 返回查询结果
     */
    private List<WaterBaseInfoEntity> findScheduleTable(Long startTime , Long endTime ) {
        PreparedStatement pstat = null;
        ResultSet rs = null;
        List<WaterBaseInfoEntity> list ;
        try ( Connection conn = connectionsFactory.getConnection( env.getProperty("waterSourceBase") ) ) {
            if (startTime < endTime) {
                list = new ArrayList<>();
                pstat = conn.prepareStatement( sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                pstat.setTimestamp(1, new Timestamp(startTime));
                pstat.setTimestamp(2, new Timestamp(endTime));
                rs = pstat.executeQuery();

                while (rs.next()) {
                    WaterBaseInfoEntity entity = new WaterBaseInfoEntity();
                    //处理数据 通用属性
                    entity.setId( rs.getString("id"));
                    entity.setCreatedTime( rs.getLong("cjsj"));
                    entity.setUpdatedTime( rs.getLong("gxsj"));
                    entity.setValid(rs.getInt("yxx"));
                    entity.setOperator( rs.getString("czz") );
                    //一般属性
                    entity.setNearNorthUrl( rs.getString("blgxb") );
                    entity.setNearEastUrl( rs.getString("blgxd") );
                    entity.setNearSouthUrl( rs.getString("blgxn") );
                    entity.setNearWestUrl( rs.getString("blgxx") );
                    entity.setNumber( rs.getString("bm") );
                    entity.setWaterHandlersId( rs.getString("czyid") );
                    entity.setAltitude( rs.getString("dqgd") );
                    entity.setLongitude( rs.getString("dqjd") );
                    entity.setLatitude( rs.getString("dqwd") );
                    entity.setWaterAddr( rs.getString("dzmc") );
                    entity.setUrl( rs.getString("fwt") );
                    entity.setNorthUrl( rs.getString("fwtb") );
                    entity.setEastUrl( rs.getString("fwtd") );
                    entity.setSouthUrl( rs.getString("fwtn") );
                    entity.setWestUrl(rs.getString("fwtx"));
                    entity.setMapAltitude( rs.getString("gis_h_map") );
                    entity.setMapLongitude( rs.getString("gis_x_map") );
                    entity.setMapLatitude( rs.getString("gis_y_map") );
                    entity.setManageUnit( rs.getString("gl_dwmc") );
                    entity.setWaterUnit( rs.getString("gs_dwmc") );
                    entity.setPipePressure( rs.getString("gw_yl") );
                    entity.setPipeNetwork( rs.getString("gwid") );
                    entity.setPipeDiameter( rs.getString("gwzj_kd") );
                    entity.setWaterBuildTime( rs.getLong("jiz_rq") );
                    entity.setRecordStatus( rs.getInt("jlzt") );
                    entity.setContact( rs.getString("lxr") );
                    entity.setTelephone( rs.getString("lxr_lxdh") );
                    entity.setContactPersonName( rs.getString("lxr_xm") );
                    entity.setWaterName( rs.getString("mc") );
                    entity.setWaterIntake( rs.getString("qsxs_jyqk") );
                    entity.setEnableTime( rs.getLong("qy_rq") );
                    entity.setAuditStatus( rs.getString("shzt") );
                    entity.setNearUrl( rs.getString("sjt") );
                    entity.setPipeNetworkName( rs.getString("ssgw_mc") );
                    entity.setRoadSection( rs.getString("ssld_mc") );
                    entity.setWaterCode( rs.getString("sybh") );
                    entity.setAvailableStatus( rs.getString("sykyztlbdm") );
                    entity.setWaterType( rs.getString("sylx") );
                    entity.setWaterAttribute( rs.getString("sysxxxid") );
                    entity.setSetTheForm( rs.getString("szxs") );
                    entity.setMaintenanceUnit( rs.getString("wb_dwmc") );
                    entity.setPipeForm( rs.getString("xfjsgwxslxdm") );
                    entity.setOrganizationId( rs.getString("xfjyjg_tywysbm") );
                    entity.setOrganizationName( rs.getString("xfjyjg_tywysmc") );
                    entity.setInterfaceForm( rs.getString("xfsdjklxdm") );
                    entity.setWaterStatus( rs.getString("xfsszkflydm") );
                    entity.setPlacementForm( rs.getString("xhsfzxslbdm") );
                    entity.setHydrantType( rs.getString("xhslbdm") );
                    entity.setNature( rs.getString("xz") );
                    entity.setDistrict( rs.getString("xzqhdm") );
                    entity.setPhotoFileURL( rs.getString("zpwjurl") );

                    list.add(entity);
                }
                return list;
            }
            return null;
        } catch ( Exception ex) {
            logService.erorLog(logger, "service", "findScheduleTable", String.format(" find schedule table fail  , target time :%s.", new Timestamp( endTime) ), ex);
            throw new ScheduleException(ScheduleException.ScheduleErrors.SCHEDULE_DATA_FAIL);
        }finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstat != null) {
                    pstat.close();
                }
            }catch ( Exception ex ){
                logService.erorLog(logger, "service", "findScheduleTable", String.format(" find schedule table  fail  , target time :%s.", new Timestamp( System.currentTimeMillis() ) ), ex);
                throw new ScheduleException(ScheduleException.ScheduleErrors.SCHEDULE_DATA_FAIL);
            }
        }
    }
}
