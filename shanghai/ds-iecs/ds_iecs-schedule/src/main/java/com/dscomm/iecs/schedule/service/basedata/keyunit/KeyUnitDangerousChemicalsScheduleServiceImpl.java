package com.dscomm.iecs.schedule.service.basedata.keyunit;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.dal.po.KeyUnitDangerousChemicalsEntity;
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

@Component("keyUnitDangerousChemicalsScheduleServiceImpl")
public class KeyUnitDangerousChemicalsScheduleServiceImpl implements KeyUnitDangerousChemicalsScheduleService {

    private static final Logger logger = LoggerFactory.getLogger(KeyUnitDangerousChemicalsScheduleServiceImpl.class);
    private LogService logService;

    private Environment env;
    private GeneralAccessor accessor;
    private ConnectionsFactory connectionsFactory;
    private static final String id = "yagl_mhdw_whp";


    @Autowired
    public KeyUnitDangerousChemicalsScheduleServiceImpl(LogService logService , Environment env,
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
            logService.infoLog(logger, "service", "KeyUnitDangerousChemicals", "service is started...");
            Long logStart = System.currentTimeMillis();

            TransporterEntity entity = accessor.getById(id, TransporterEntity.class);
            if (entity == null) {
                logService.erorLog(logger, "service", "KeyUnitDangerousChemicals", "table config not exist ",  new Exception() );
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
                    List<KeyUnitDangerousChemicalsEntity> list = findScheduleTable(lastUpdataTime, endTime);
                    //结果不为空 保存数据
                    if (list != null && list.size() > 0) {
                        logService.infoLog(logger, "service", "KeyUnitDangerousChemicals",
                                String.format(" find schedule table total : %d ,start time :%s ,end time :%s" , list.size()  , new Timestamp(lastUpdataTime) , new Timestamp(endTime)  )   );

                        accessor.save(list);
                    }else{
                        logService.infoLog(logger, "service", "KeyUnitDangerousChemicals",
                                String.format(" find schedule table is null  ,start time :%s ,end time :%s" ,   new Timestamp(lastUpdataTime) , new Timestamp(endTime)  ) );
                    }

                    i++ ;
                }
            }
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "KeyUnitDangerousChemicals", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "KeyUnitDangerousChemicals", String.format(" transport account task fail  , target time :%s.", new Timestamp(targetTime) ), ex);
            throw new ScheduleException(ScheduleException.ScheduleErrors.SCHEDULE_DATA_FAIL);
        }

    }



    private static String sql = " select " +
            "v_tb_yagl_mhdw_whp.id as id ," +
            "v_tb_yagl_mhdw_whp.cjsj as cjsj, " +
            "v_tb_yagl_mhdw_whp.gxsj as gxsj," +
            "v_tb_yagl_mhdw_whp.yxx as yxx," +
            "v_tb_yagl_mhdw_whp.zddwid as zddwid ," +
            "v_tb_yagl_mhdw_whp.wxhxp as wxhxp ," +
            "v_tb_yagl_mhdw_whp.wxhxp_tywysbm as wxhxp_tywysbm," +
            "v_tb_yagl_mhdw_whp.wxhxp_hxpztlbdm as wxhxp_hxpztlbdm," +
            "v_tb_yagl_mhdw_whp.wxhxp_hxpwxxlbdm as wxhxp_hxpwxxlbdm," +
            "v_tb_yagl_mhdw_whp.wxhxp_wxhxpflydm as wxhxp_wxhxpflydm," +
            "v_tb_yagl_mhdw_whp.wxhxp_jyqk as wxhxp_jyqk," +
            "v_tb_yagl_mhdw_whp.wxhxp_sl as wxhxp_sl ," +
            "v_tb_yagl_mhdw_whp.wxhxp_dzmc as wxhxp_dzmc ," +
            "v_tb_yagl_mhdw_whp.sjc as sjc " +
            "  from  v_tb_yagl_mhdw_whp " +
            "  where v_tb_yagl_mhdw_whp.sjc >?  and v_tb_yagl_mhdw_whp.sjc <= ?  " +
            " ";

    /**
     * 从原数据库查询数据
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 返回查询结果
     */
    private List<KeyUnitDangerousChemicalsEntity> findScheduleTable(Long startTime , Long endTime ) {
        PreparedStatement pstat = null;
        ResultSet rs = null;
        List<KeyUnitDangerousChemicalsEntity> list ;
        try ( Connection conn = connectionsFactory.getConnection( env.getProperty("keyUnitDangerousChemicals") ) ) {
            if (startTime < endTime) {
                list = new ArrayList<>();
                pstat = conn.prepareStatement( sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                pstat.setTimestamp(1, new Timestamp(startTime));
                pstat.setTimestamp(2, new Timestamp(endTime));
                rs = pstat.executeQuery();

                while (rs.next()) {
                    KeyUnitDangerousChemicalsEntity entity = new KeyUnitDangerousChemicalsEntity();
                    //处理数据 通用属性
                    entity.setId( rs.getString("id"));
                    entity.setCreatedTime( rs.getLong("cjsj"));
                    entity.setUpdatedTime( rs.getLong("gxsj"));
                    entity.setValid(rs.getInt("yxx"));
                    entity.setOperator( "N/A" );
                    //一般属性
                    entity.setDangerousChemicalsId(rs.getString("wxhxp"));
                    entity.setKeyUnitDangerousChemicalsAddress(rs.getString("wxhxp_dzmc"));
                    entity.setDangerousChemicalsCategoryCode(rs.getString("wxhxp_hxpwxxlbdm"));
                    entity.setDangerousChemicalsStatusCode(rs.getString("wxhxp_hxpztlbdm"));
                    entity.setKeyUnitDangerousChemicalsDesc(rs.getString("wxhxp_jyqk"));
                    entity.setKeyUnitDangerousChemicalsNum(rs.getInt("wxhxp_sl"));
                    entity.setDangerousChemicalsIdCode(rs.getString("wxhxp_tywysbm"));
                    entity.setDangerousChemicalsTypeCode(rs.getString("wxhxp_wxhxpflydm"));
                    entity.setKeyUnitId(rs.getString("zddwid"));

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
