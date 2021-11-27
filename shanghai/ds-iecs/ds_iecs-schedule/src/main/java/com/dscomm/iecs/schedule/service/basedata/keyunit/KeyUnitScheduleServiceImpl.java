package com.dscomm.iecs.schedule.service.basedata.keyunit;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.dal.po.KeyUnitEntity;
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

@Component("keyUnitScheduleServiceImpl")
public class KeyUnitScheduleServiceImpl implements KeyUnitScheduleService {

    private static final Logger logger = LoggerFactory.getLogger(KeyUnitScheduleServiceImpl.class);
    private LogService logService;

    private Environment env;
    private GeneralAccessor accessor;
    private ConnectionsFactory connectionsFactory;
    private static final String id = "yagl_mhdw";


    @Autowired
    public KeyUnitScheduleServiceImpl(LogService logService , Environment env,
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
            logService.infoLog(logger, "service", "KeyUnit", "service is started...");
            Long logStart = System.currentTimeMillis();

            TransporterEntity entity = accessor.getById(id, TransporterEntity.class);
            if (entity == null) {
                logService.erorLog(logger, "service", "KeyUnit", "table config not exist ",  new Exception() );
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
                    List<KeyUnitEntity> list = findScheduleTable(lastUpdataTime, endTime);
                    //结果不为空 保存数据
                    if (list != null && list.size() > 0) {
                        logService.infoLog(logger, "service", "KeyUnit",
                                String.format(" find schedule table total : %d ,start time :%s ,end time :%s" , list.size()  , new Timestamp(lastUpdataTime) , new Timestamp(endTime)  )   );

                        accessor.save(list);
                    }else{
                        logService.infoLog(logger, "service", "KeyUnit",
                                String.format(" find schedule table is null  ,start time :%s ,end time :%s" ,   new Timestamp(lastUpdataTime) , new Timestamp(endTime)  ) );
                    }

                    i++ ;
                }
            }
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "KeyUnit", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "KeyUnit", String.format(" transport account task fail  , target time :%s.", new Timestamp(targetTime) ), ex);
            throw new ScheduleException(ScheduleException.ScheduleErrors.SCHEDULE_DATA_FAIL);
        }

    }



    private static String sql = " select " +
            "\n" +
            "v_tb_yagl_mhdw.id as id  ," +
            "v_tb_yagl_mhdw.cjsj  as cjsj ," +
            "v_tb_yagl_mhdw.gxsj   as gxsj ," +
            "v_tb_yagl_mhdw.yxx as yxx ," +
            "v_tb_yagl_mhdw.fhdwid as fhdwid," +
            "v_tb_yagl_mhdw.dwmc as dwmc," +
            "v_tb_yagl_mhdw.dwpyjc as dwpyjc," +
            "v_tb_yagl_mhdw.jjsyz as jjsyz," +
            "v_tb_yagl_mhdw.dwclsj as dwclsj ," +
            "v_tb_yagl_mhdw.dwdj as dwdj," +
            "v_tb_yagl_mhdw.dwxz as dwxz ," +
            "v_tb_yagl_mhdw.xzqy as xzqy ," +
            "v_tb_yagl_mhdw.frdb as frdb ," +
            "v_tb_yagl_mhdw.frdbsfz as frdbsfz ," +
            "v_tb_yagl_mhdw.frdbdh as frdbdh ," +
            "v_tb_yagl_mhdw.aqzrr as aqzrr ," +
            "v_tb_yagl_mhdw.aqzrrsfz as aqzrrsfz ," +
            "v_tb_yagl_mhdw.aqzrrdh as aqzrrdh ," +
            "v_tb_yagl_mhdw.dwdzyx as dwdzyx ," +
            "v_tb_yagl_mhdw.aqglr as aqglr ," +
            "v_tb_yagl_mhdw.aqglrsfz as aqglrsfz ," +
            "v_tb_yagl_mhdw.aqglrdh as aqglrdh ," +
            "v_tb_yagl_mhdw.zjzxfglr as zjzxfglr ," +
            "v_tb_yagl_mhdw.zjzxfglrsfz as zjzxfglrsfz ," +
            "v_tb_yagl_mhdw.zjzxfglrdh as zjzxfglrdh ," +
            "v_tb_yagl_mhdw.dwdz as dwdz ," +
            "v_tb_yagl_mhdw.dwdh as dwdh ," +
            "v_tb_yagl_mhdw.yzbm as yzbm ," +
            "v_tb_yagl_mhdw.gdzc  as gdzc ," +
            "v_tb_yagl_mhdw.zgrs   as zgrs ," +
            "v_tb_yagl_mhdw.zdmj  as zdmj ," +
            "v_tb_yagl_mhdw.jzmj   as jzmj ," +
            "v_tb_yagl_mhdw.dwzsx as dwzsx ," +
            "v_tb_yagl_mhdw.dwcsx as dwcsx ," +
            "v_tb_yagl_mhdw.zdxfss  as zdxfss ," +
            "v_tb_yagl_mhdw.xfgxjgid as xfgxjgid ," +
            "v_tb_yagl_mhdw.gis_x as gis_x ," +
            "v_tb_yagl_mhdw.gis_y as gis_y ," +
            "v_tb_yagl_mhdw.dlwz as dlwz ," +
            "v_tb_yagl_mhdw.jzsl  as jzsl ," +
            "v_tb_yagl_mhdw.czyid as czyid ," +
            "v_tb_yagl_mhdw.jgid as jgid ," +
            "v_tb_yagl_mhdw.glid as glid ," +
            "v_tb_yagl_mhdw.bz as bz," +
            "v_tb_yagl_mhdw.sjc as sjc ," +
            "v_tb_yagl_mhdw.cszt   as cszt ," +
            "v_tb_yagl_mhdw.jlzt   as jlzt ," +
            "v_tb_yagl_mhdw.sjbb as sjbb ," +
            "v_tb_yagl_mhdw.ywxtbsid as ywxtbsid ," +
            "v_tb_yagl_mhdw.jksjbb as jksjbb ," +
            "v_tb_yagl_mhdw.zddw_tywysbm as zddw_tywysbm  ," +
            "v_tb_yagl_mhdw.jjsyzlxdm as jjsyzlxdm," +
            "v_tb_yagl_mhdw.cl_rq as cl_rq ," +
            "v_tb_yagl_mhdw.dwhzwhxflydm as dwhzwhxflydm  ," +
            "v_tb_yagl_mhdw.dwzrxzdm as dwzrxzdm ," +
            "v_tb_yagl_mhdw.xzqhdm as xzqhdm ," +
            "v_tb_yagl_mhdw.dw_jyqk as dw_jyqk ," +
            "v_tb_yagl_mhdw.sftdhsstd_jyqk as sftdhsstd_jyqk ," +
            "v_tb_yagl_mhdw.nbxfss_jyqk as nbxfss_jyqk ," +
            "v_tb_yagl_mhdw.xfkzs_jyqk as xfkzs_jyqk ," +
            "v_tb_yagl_mhdw.frdb_xm as frdb_xm ," +
            "v_tb_yagl_mhdw.frdb_gmsfzh as frdb_gmsfzh ," +
            "v_tb_yagl_mhdw.frdb_lxdh as frdb_lxdh ," +
            "v_tb_yagl_mhdw.xfaqzrr as xfaqzrr ," +
            "v_tb_yagl_mhdw.xfaqzrr_xm as xfaqzrr_xm ," +
            "v_tb_yagl_mhdw.xfaqzrr_gmsfhm as xfaqzrr_gmsfhm ," +
            "v_tb_yagl_mhdw.xfaqzrr_lxdh as xfaqzrr_lxdh ," +
            "v_tb_yagl_mhdw.dzxx as dzxx ," +
            "v_tb_yagl_mhdw.xfaqglr as xfaqglr ," +
            "v_tb_yagl_mhdw.xfaqglr_xm as xfaqglr_xm ," +
            "v_tb_yagl_mhdw.xfaqglr_gmsfhm as xfaqglr_gmsfhm ," +
            "v_tb_yagl_mhdw.xfaqglr_lxdh as xfaqglr_lxdh ," +
            "v_tb_yagl_mhdw.zjzxfglr_xm as zjzxfglr_xm ," +
            "v_tb_yagl_mhdw.zjzxfglr_gmsfhm as zjzxfglr_gmsfhm ," +
            "v_tb_yagl_mhdw.zjzxfglr_lxdh as zjzxfglr_lxdh ," +
            "v_tb_yagl_mhdw.dzmc as dzmc ," +
            "v_tb_yagl_mhdw.lxdh as lxdh ," +
            "v_tb_yagl_mhdw.rs as rs ," +
            "v_tb_yagl_mhdw.zd_mj  as zd_mj ," +
            "v_tb_yagl_mhdw.jz_mj  as jz_mj ," +
            "v_tb_yagl_mhdw.xfjyjg_tywysbm as xfjyjg_tywysbm ," +
            "v_tb_yagl_mhdw.dqjd as dqjd ," +
            "v_tb_yagl_mhdw.dqwd as dqwd ," +
            "v_tb_yagl_mhdw.jz_sl  as jz_sl " +
            "  from  v_tb_yagl_mhdw " +
            "  where v_tb_yagl_mhdw.sjc >?  and v_tb_yagl_mhdw.sjc <= ?  " +
            " ";


    /**
     * 从原数据库查询数据
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 返回查询结果
     */
    private List<KeyUnitEntity> findScheduleTable(Long startTime , Long endTime ) {
        PreparedStatement pstat = null;
        ResultSet rs = null;
        List<KeyUnitEntity> list ;
        try ( Connection conn = connectionsFactory.getConnection( env.getProperty("unitEmergency") ) ) {
            if (startTime < endTime) {
                list = new ArrayList<>();
                pstat = conn.prepareStatement( sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                pstat.setTimestamp(1, new Timestamp(startTime));
                pstat.setTimestamp(2, new Timestamp(endTime));
                rs = pstat.executeQuery();

                while (rs.next()) {
                    KeyUnitEntity entity = new KeyUnitEntity();
                    //处理数据 通用属性
                    entity.setId( rs.getString("id"));
                    entity.setCreatedTime( rs.getLong("cjsj"));
                    entity.setUpdatedTime( rs.getLong("gxsj"));
                    entity.setValid(rs.getInt("yxx"));
                    entity.setOperator( rs.getString("czz") );
                    //一般属性
                    entity.setUnitCategoryCode(rs.getString("dwlb"));
                    entity.setUnitTypeCode(rs.getString("dwlx"));
                    entity.setFireproofUnit(rs.getString("fhdwid"));
                    entity.setUnitName(rs.getString("dwmc"));
                    entity.setUnitShortName(rs.getString("dwpyjc"));
                    entity.setEconomicOwnership(rs.getString("jjsyzlxdm"));
                    entity.setUnitFoundingTime(rs.getLong("cl_rq"));
                    entity.setUnitLevelCode(rs.getString("dwdj"));
                    entity.setUnitNatureCode(rs.getString("dwzrxzdm"));
                    entity.setLegalPerson(rs.getString("frdb"));
                    entity.setLegalPersonId(rs.getString("frdb_gmsfzh"));
                    entity.setLegalPersonPhone(rs.getString("frdb_lxdh"));
                    entity.setSecurityPerson(rs.getString("xfaqzrr"));
                    entity.setSecurityPersonId(rs.getString("xfaqzrr_gmsfhm"));
                    entity.setSecurityPersonPhone(rs.getString("xfaqzrr_lxdh"));
                    entity.setUnitEmail(rs.getString("dzxx"));
                    entity.setSecurityManage(rs.getString("xfaqglr"));
                    entity.setSecurityManageId(rs.getString("xfaqglr_gmsfhm"));
                    entity.setSecurityManagePhone(rs.getString("xfaqglr_lxdh"));
                    entity.setFireManager(rs.getString("zjzxfglr"));
                    entity.setFireManagerId(rs.getString("zjzxfglr_gmsfhm"));
                    entity.setFireManagerPhone(rs.getString("zjzxfglr_lxdh"));
                    entity.setUnitAddress(rs.getString("dzmc"));
                    entity.setUnitPhone(rs.getString("lxdh"));
                    entity.setMailCode(rs.getString("yzbm"));
                    entity.setFixedAssets(rs.getFloat("gdzc"));
                    entity.setUnitWorkers(rs.getInt("rs"));
                    entity.setConstructionArea(rs.getFloat("zd_mj"));
                    entity.setBuiltUpArea(rs.getFloat("jz_mj"));
                    entity.setUnitPrincipalAttribute(rs.getString("dwzsx"));
                    entity.setUnitAttribute(rs.getString("dwcsx"));
                    entity.setJurisdictionOrganizationId(rs.getString("xfjyjg_tywysbm"));
                    entity.setLongitude(rs.getString("dqjd"));
                    entity.setLatitude(rs.getString("dqwd"));
                    entity.setBuildNum(rs.getInt("jz_sl"));
                    entity.setOperatorId(rs.getString("czyid"));
                    entity.setOrganizationId(rs.getString("jgid"));
                    entity.setCSZT(rs.getInt("cszt"));
                    entity.setJLZT(rs.getInt("jlzt"));
                    entity.setSJC(rs.getLong("sjc"));
                    entity.setRemarks(rs.getString("bz"));
                    entity.setSJBB(rs.getString("sjbb"));
                    entity.setRelationId(rs.getString("glid"));
                    entity.setVersionStamp(rs.getLong("vercol"));
                    entity.setJKSJBB(rs.getString("jksjbb"));
                    entity.setIdCode(rs.getString("zddw_tywysbm"));
                    entity.setUnitFireTypeCode(rs.getString("dwhzwhxflydm"));
                    entity.setUnitDesc(rs.getString("dw_jyqk"));
                    entity.setFireEscapeDesc(rs.getString("sftdhsstd_jyqk"));
                    entity.setInternalFireFacilitiesDesc(rs.getString("nbxfss_jyqk"));
                    entity.setFireControlRoomDesc(rs.getString("xfkzs_jyqk"));
                    entity.setLegalPersonName(rs.getString("frdb_xm"));
                    entity.setSecurityPersonName(rs.getString("xfaqzrr_xm"));
                    entity.setSecurityManageName(rs.getString("xfaqglr_xm"));
                    entity.setFireManagerName(rs.getString("zjzxfglr_xm"));

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
