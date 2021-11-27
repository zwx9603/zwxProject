package com.dscomm.iecs.schedule.service.basedata.organization;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.dal.po.UnitEmergencyEntity;
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

@Component("unitEmergencyScheduleServiceImpl")
public class UnitEmergencyScheduleServiceImpl implements UnitEmergencyScheduleService {

    private static final Logger logger = LoggerFactory.getLogger(UnitEmergencyScheduleServiceImpl.class);
    private LogService logService;

    private Environment env;
    private GeneralAccessor accessor;
    private ConnectionsFactory connectionsFactory;
    private static final String id = "jgxx_yjlddw";


    @Autowired
    public UnitEmergencyScheduleServiceImpl(LogService logService , Environment env,
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
            logService.infoLog(logger, "service", "UnitEmergency", "service is started...");
            Long logStart = System.currentTimeMillis();

            TransporterEntity entity = accessor.getById(id, TransporterEntity.class);
            if (entity == null) {
                logService.erorLog(logger, "service", "UnitEmergency", "table config not exist ",  new Exception() );
                return ;
            }
            Long lastUpdataTime = entity.getZHTBSJ();
            DateUtils.FieldType fieldType = ScheduleUtil.transFormDateFieldType(entity.getDCTBSCDW() );
            int onceTime = entity.getDCTBSC();
            //更新时间晚于目标时间，则进行同步操作
            if (lastUpdataTime != null && targetTime != null && lastUpdataTime < targetTime) {
                int i = 0 ; //单次最多执行 7 次
                while (lastUpdataTime < targetTime && i< 7 ) {
                    Long endTime = new Date().getTime();
//                            DateUtils.add(new Date(lastUpdataTime), fieldType, onceTime).getTime();
                    if (endTime > targetTime) {
                        endTime = targetTime;
                    }
                    //单次查询
                    List<UnitEmergencyEntity> list = findScheduleTable(lastUpdataTime, endTime);
                    //结果不为空 保存数据
                    if (list != null && list.size() > 0) {
                        logService.infoLog(logger, "service", "UnitEmergency",
                                String.format(" find schedule table total : %d ,start time :%s ,end time :%s" , list.size()  , new Timestamp(lastUpdataTime) , new Timestamp(endTime)  )   );

                        accessor.save(list);
                    }else{
                        logService.infoLog(logger, "service", "UnitEmergency",
                                String.format(" find schedule table is null  ,start time :%s ,end time :%s" ,   new Timestamp(lastUpdataTime) , new Timestamp(endTime)  ) );
                    }

                    i++ ;
                }
            }
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "UnitEmergency", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "UnitEmergency", String.format(" transport account task fail  , target time :%s.", new Timestamp(targetTime) ), ex);
            throw new ScheduleException(ScheduleException.ScheduleErrors.SCHEDULE_DATA_FAIL);
        }

    }



    private static String sql = " select " +
            "v_tb_jgxx_yjlddw.id as id  ," +
            "v_tb_jgxx_yjlddw.cjsj  as cjsj ," +
            "v_tb_jgxx_yjlddw.gxsj  as gxsj ," +
            "v_tb_jgxx_yjlddw.yxx as yxx ," +
            "v_tb_jgxx_yjlddw.ssxzqdm as ssxzqdm ," +
            "v_tb_jgxx_yjlddw.dwmc as dwmc," +
            "v_tb_jgxx_yjlddw.dwdz as dwdz ," +
            "v_tb_jgxx_yjlddw.dwlx as dwlx ," +
            "v_tb_jgxx_yjlddw.yjfwnr as yjfwnr ," +
            "v_tb_jgxx_yjlddw.lxr as lxr ," +
            "v_tb_jgxx_yjlddw.lxdh as lxdh ," +
            "v_tb_jgxx_yjlddw.dwcz as dwcz ," +
            "v_tb_jgxx_yjlddw.ssxq as ssxq ," +
            "v_tb_jgxx_yjlddw.gis_x as gis_x," +
            "v_tb_jgxx_yjlddw.gis_y as GIS_Y," +
            "v_tb_jgxx_yjlddw.gis_h as gis_h," +
            "v_tb_jgxx_yjlddw.glid as glid," +
            "v_tb_jgxx_yjlddw.zp as zp ," +
            "v_tb_jgxx_yjlddw.bz as bz ," +
            "v_tb_jgxx_yjlddw.sjc as sjc ," +
            "v_tb_jgxx_yjlddw.jlzt as jlzt ," +
            "v_tb_jgxx_yjlddw.cszt as cszt ," +
            "v_tb_jgxx_yjlddw.sjbb as sjbb ," +
            "v_tb_jgxx_yjlddw.ywxtbsid as ywxtbsid ," +
            "v_tb_jgxx_yjlddw.jksjbb as jksjbb ," +
            "v_tb_jgxx_yjlddw.yjlddw_tywysbm as yjlddw_tywysbm  ," +
            "v_tb_jgxx_yjlddw.dzmc as dzmc  ," +
            "v_tb_jgxx_yjlddw.yjlddwlbdm as yjlddwlbdm  ," +
            "v_tb_jgxx_yjlddw.yjfwnr_jyqk as yjfwnr_jyqk  ," +
            "v_tb_jgxx_yjlddw.dw_jyqk as dw_jyqk  ," +
            "v_tb_jgxx_yjlddw.lxr_xm as lxr_xm  ," +
            "v_tb_jgxx_yjlddw.lxr_lxdh as lxr_lxdh  ," +
            "v_tb_jgxx_yjlddw.dqjd as dqjd," +
            "v_tb_jgxx_yjlddw.dqwd as dqwd," +
            "v_tb_jgxx_yjlddw.dqgd as dqgd," +
            "v_tb_jgxx_yjlddw.xfjyjg_tywysbm as xfjyjg_tywysbm  ," +
            "v_tb_jgxx_yjlddw.xzqhdm as xzqhdm " +
            "  from  v_tb_jgxx_yjlddw " +
            "  where v_tb_jgxx_yjlddw.sjc >?  and v_tb_jgxx_yjlddw.sjc <= ?  " +
            " ";


    /**
     * 从原数据库查询数据
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 返回查询结果
     */
    private List<UnitEmergencyEntity> findScheduleTable(Long startTime , Long endTime ) {
        PreparedStatement pstat = null;
        ResultSet rs = null;
        List<UnitEmergencyEntity> list ;
        try ( Connection conn = connectionsFactory.getConnection( env.getProperty("unitEmergency") ) ) {
            if (startTime < endTime) {
                list = new ArrayList<>();
                pstat = conn.prepareStatement( sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                pstat.setTimestamp(1, new Timestamp(startTime));
                pstat.setTimestamp(2, new Timestamp(endTime));
                rs = pstat.executeQuery();

                while (rs.next()) {
                    UnitEmergencyEntity entity = new UnitEmergencyEntity();
                    //处理数据 通用属性
                    entity.setId( rs.getString("id"));
                    entity.setCreatedTime( rs.getLong("cjsj"));
                    entity.setUpdatedTime( rs.getLong("gxsj"));
                    entity.setValid(rs.getInt("yxx"));
                    entity.setOperator( rs.getString("czz") );
                    //一般属性
                    entity.setRemarks( rs.getString("bz") );
                    entity.setCSZT( rs.getInt("cszt") );
                    entity.setFaxNumber( rs.getString("czhm") );
                    entity.setHeight( rs.getString("dqgd") );
                    entity.setLongitude( rs.getString("dqjd") );
                    entity.setLatitude( rs.getString("dqwd") );
                    entity.setUnitDesc( rs.getString("dw_jyqk") );
                    entity.setUnitName( rs.getString("dwmc") );
                    entity.setUnitAddress( rs.getString("dzmc") );
                    entity.setGisRelationId( rs.getString("glid") );
                    entity.setJKSJBB( rs.getString("jksjbb") );
                    entity.setJLZT( rs.getInt("jlzt") );
                    entity.setContactPhone( rs.getString("lxr_lxdh") );
                    entity.setContactPerson( rs.getString("lxr") );
                    entity.setContactPersonName( rs.getString("lxr_xm") );
                    entity.setSJBB( rs.getString("sjbb") );
                    entity.setSJC( rs.getLong("sjc") );
                    entity.setIdCode( rs.getString("yjlddw_tywysbm") );
                    entity.setDistrictCode( rs.getString("xzqhdm") );
                    entity.setYWXTBSID( rs.getString("ywxtbsid") );
                    entity.setEmergencyContent( rs.getString("yjfwnr_jyqk") );
                    entity.setOrganizationId( rs.getString("xfjyjg_tywysbm") );
                    entity.setPicture( rs.getString("zp") );
                    entity.setUnitType( rs.getString("yjlddwlbdm") );

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
