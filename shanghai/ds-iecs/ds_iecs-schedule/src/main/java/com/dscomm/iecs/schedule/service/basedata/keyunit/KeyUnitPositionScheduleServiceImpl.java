package com.dscomm.iecs.schedule.service.basedata.keyunit;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.dal.po.KeyUnitPositionEntity;
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

@Component("keyUnitPositionScheduleServiceImpl")
public class KeyUnitPositionScheduleServiceImpl implements KeyUnitPositionScheduleService {

    private static final Logger logger = LoggerFactory.getLogger(KeyUnitPositionScheduleServiceImpl.class);
    private LogService logService;

    private Environment env;
    private GeneralAccessor accessor;
    private ConnectionsFactory connectionsFactory;
    private static final String id = "yagl_mhdw_zdbw";


    @Autowired
    public KeyUnitPositionScheduleServiceImpl(LogService logService , Environment env,
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
            logService.infoLog(logger, "service", "KeyUnitPosition", "service is started...");
            Long logStart = System.currentTimeMillis();

            TransporterEntity entity = accessor.getById(id, TransporterEntity.class);
            if (entity == null) {
                logService.erorLog(logger, "service", "KeyUnitPosition", "table config not exist ",  new Exception() );
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
                    List<KeyUnitPositionEntity> list = findScheduleTable(lastUpdataTime, endTime);
                    //结果不为空 保存数据
                    if (list != null && list.size() > 0) {
                        logService.infoLog(logger, "service", "KeyUnitPosition",
                                String.format(" find schedule table total : %d ,start time :%s ,end time :%s" , list.size()  , new Timestamp(lastUpdataTime) , new Timestamp(endTime)  )   );

                        accessor.save(list);
                    }else{
                        logService.infoLog(logger, "service", "KeyUnitPosition",
                                String.format(" find schedule table is null  ,start time :%s ,end time :%s" ,   new Timestamp(lastUpdataTime) , new Timestamp(endTime)  ) );
                    }

                    i++ ;
                }
            }
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "KeyUnitPosition", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "KeyUnitPosition", String.format(" transport account task fail  , target time :%s.", new Timestamp(targetTime) ), ex);
            throw new ScheduleException(ScheduleException.ScheduleErrors.SCHEDULE_DATA_FAIL);
        }

    }






    private static String sql = " select " +
            "v_tb_yagl_mhdw_zdbw.id as  id," +
            "v_tb_yagl_mhdw_zdbw.cjsj as  cjsj," +
            "v_tb_yagl_mhdw_zdbw.gxsj as  gxsj," +
            "v_tb_yagl_mhdw_zdbw.yxx as  yxx," +
            "v_tb_yagl_mhdw_zdbw.jzwsyxzdm as  jzwsyxzdm ," +
            "v_tb_yagl_mhdw_zdbw.jzjglxdm as  jzjglxdm ," +
            "v_tb_yagl_mhdw_zdbw.jzwnhdjdm as  jzwnhdjdm ," +
            "v_tb_yagl_mhdw_zdbw.jz_mj as  jz_mj ," +
            "v_tb_yagl_mhdw_zdbw.wxx_jyqk as  wxx_jyqk ," +
            "v_tb_yagl_mhdw_zdbw.wxy_jyqk as  wxy_jyqk ," +
            "v_tb_yagl_mhdw_zdbw.qlyy_jyqk as  qlyy_jyqk ," +
            "v_tb_yagl_mhdw_zdbw.ssck_sl as  ssck_sl ," +
            "v_tb_yagl_mhdw_zdbw.aqck_sl as  aqck_sl ," +
            "v_tb_yagl_mhdw_zdbw.hz_jyqk as  hz_jyqk ," +
            "v_tb_yagl_mhdw_zdbw.xfdt_sl as  xfdt_sl ," +
            "v_tb_yagl_mhdw_zdbw.mhss_jyqk as  mhss_jyqk ," +
            "v_tb_yagl_mhdw_zdbw.ffbssl_jyqk as  ffbssl_jyqk  ," +
            "v_tb_yagl_mhdw_zdbw.sfaqgl_jyqk as  sfaqgl_jyqk  ," +
            "v_tb_yagl_mhdw_zdbw.zdbw_tywysbm as  zdbw_tywysbm  ," +
            "v_tb_yagl_mhdw_zdbw.dddw_tywysbm as  dddw_tywysbm  ," +
            "v_tb_yagl_mhdw_zdbw.zrr as  zrr ," +
            "v_tb_yagl_mhdw_zdbw.zrr_xm as  zrr_xm ," +
            "v_tb_yagl_mhdw_zdbw.xfjyjg_tywysbm as  xfjyjg_tywysbm ," +
            "v_tb_yagl_mhdw_zdbw.bwsz as  bwsz ," +
            "v_tb_yagl_mhdw_zdbw.ddmc as  ddmc ," +
            "v_tb_yagl_mhdw_zdbw.bwsz_lc as  bwsz_lc ," +
            "v_tb_yagl_mhdw_zdbw.bwsz_gd as  bwsz_gd ," +
            "v_tb_yagl_mhdw_zdbw.mc as  mc ," +
            "v_tb_yagl_mhdw_zdbw.bz as  bz  ," +
            "v_tb_yagl_mhdw_zdbw.sjc as  sjc" +
            "  from  v_tb_yagl_mhdw_zdbw " +
            "  where v_tb_yagl_mhdw_zdbw.sjc >?  and v_tb_yagl_mhdw_zdbw.sjc <= ?  " +
            " ";


    /**
     * 从原数据库查询数据
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 返回查询结果
     */


    private List<KeyUnitPositionEntity> findScheduleTable(Long startTime , Long endTime ) {
        PreparedStatement pstat = null;
        ResultSet rs = null;
        List<KeyUnitPositionEntity> list ;
        try ( Connection conn = connectionsFactory.getConnection( env.getProperty("keyUnitPosition") ) ) {
            if (startTime < endTime) {
                list = new ArrayList<>();
                pstat = conn.prepareStatement( sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                pstat.setTimestamp(1, new Timestamp(startTime));
                pstat.setTimestamp(2, new Timestamp(endTime));
                rs = pstat.executeQuery();

                while (rs.next()) {
                    KeyUnitPositionEntity entity = new KeyUnitPositionEntity();
                    //处理数据 通用属性
                    entity.setId( rs.getString("id"));
                    entity.setCreatedTime( rs.getLong("cjsj"));
                    entity.setUpdatedTime( rs.getLong("gxsj"));
                    entity.setValid(rs.getInt("yxx"));
                    entity.setOperator( "N/A" );
                    //一般属性
                    entity.setExitNum(rs.getInt("aqck_sl"));
                    entity.setPosition(rs.getString("bwsz"));
                    entity.setPositionHeight(rs.getFloat("bwsz_gd"));
                    entity.setPositionFloor(rs.getInt("bwsz_lc"));
                    entity.setRemarks(rs.getString("bz"));
                    entity.setPositionAddress(rs.getString("ddmc"));
                    entity.setFireRrotectionSignsDesc(rs.getString("ffbssl_jyqk"));
                    entity.setFireDesc(rs.getString("hz_jyqk"));
                    entity.setKeyUnitId(rs.getString("dddw_tywysbm"));
                    entity.setBuiltUpArea(rs.getFloat("jz_mj"));
                    entity.setBuildingStructureTypeCode(rs.getString("jzjglxdm"));
                    entity.setBuildingsResistanceFireLevelCode(rs.getString("jzwnhdjdm"));
                    entity.setBuildingStructureNatureCode(rs.getString("jzwsyxzdm"));
                    entity.setPositionName(rs.getString("mc"));
                    entity.setFireSafetyDesc(rs.getString("mhss_jyqk"));
                    entity.setEstablishingDesc(rs.getString("qlyy_jyqk"));
                    entity.setFireSafetyDesc(rs.getString("sfaqgl_jyqk"));
                    entity.setEvacuationNum(rs.getInt("ssck_sl"));
                    entity.setDangerDesc(rs.getString("wxx_jyqk"));
                    entity.setDangerousSourcesDesc(rs.getString("wxy_jyqk"));
                    entity.setFireElevatorNum(rs.getInt("xfdt_sl"));
                    entity.setOrganizationId(rs.getString("xfjyjg_tywysbm"));
                    entity.setIdCode(rs.getString("zdbw_tywysbm"));
                    entity.setLiablePerson(rs.getString("zrr"));
                    entity.setLiablePersonName(rs.getString("zrr_xm"));

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
