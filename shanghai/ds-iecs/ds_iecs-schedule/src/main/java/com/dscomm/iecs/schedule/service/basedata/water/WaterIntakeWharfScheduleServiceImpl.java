package com.dscomm.iecs.schedule.service.basedata.water;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.dal.po.WaterIntakeWharfEntity;
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

@Component("waterIntakeWharfScheduleServiceImpl")
public class WaterIntakeWharfScheduleServiceImpl implements WaterIntakeWharfScheduleService {

    private static final Logger logger = LoggerFactory.getLogger(WaterIntakeWharfScheduleServiceImpl.class);
    private LogService logService;

    private Environment env;
    private GeneralAccessor accessor;
    private ConnectionsFactory connectionsFactory;
    private static final String id = "sy_xfmtsxxx";


    @Autowired
    public WaterIntakeWharfScheduleServiceImpl(LogService logService , Environment env,
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
            logService.infoLog(logger, "service", "WaterIntakeWharf", "service is started...");
            Long logStart = System.currentTimeMillis();

            TransporterEntity entity = accessor.getById(id, TransporterEntity.class);
            if (entity == null) {
                logService.erorLog(logger, "service", "WaterIntakeWharf", "table config not exist ",  new Exception() );
                return ;
            }
            Long lastUpdataTime = entity.getZHTBSJ();
            DateUtils.FieldType fieldType = ScheduleUtil.transFormDateFieldType(entity.getDCTBSCDW() );
            int onceTime = entity.getDCTBSC();
            //??????????????????????????????????????????????????????
            if (lastUpdataTime != null && targetTime != null && lastUpdataTime < targetTime) {
                int i = 0 ; //?????????????????? 7 ???
                while (lastUpdataTime < targetTime && i< 7 ) {
                    Long endTime = DateUtils.add(new Date(lastUpdataTime), fieldType, onceTime).getTime();
                    if (endTime > targetTime) {
                        endTime = targetTime;
                    }
                    //????????????
                    List<WaterIntakeWharfEntity> list = findScheduleTable(lastUpdataTime, endTime);
                    //??????????????? ????????????
                    if (list != null && list.size() > 0) {
                        logService.infoLog(logger, "service", "WaterIntakeWharf",
                                String.format(" find schedule table total : %d ,start time :%s ,end time :%s" , list.size()  , new Timestamp(lastUpdataTime) , new Timestamp(endTime)  )   );

                        accessor.save(list);
                    }else{
                        logService.infoLog(logger, "service", "WaterIntakeWharf",
                                String.format(" find schedule table is null  ,start time :%s ,end time :%s" ,   new Timestamp(lastUpdataTime) , new Timestamp(endTime)  ) );
                    }
                    //??????????????????
                    lastUpdataTime = endTime;
                    //??????????????????????????????
                    entity.setZHTBSJ(lastUpdataTime);
                    accessor.save(entity);
                    i++ ;
                }
            }
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "WaterIntakeWharf", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "WaterIntakeWharf", String.format(" transport account task fail  , target time :%s.", new Timestamp(targetTime) ), ex);
            throw new ScheduleException(ScheduleException.ScheduleErrors.SCHEDULE_DATA_FAIL);
        }

    }



    private static String sql = " select " +
            " v_tb_sy_xfmtsxxx.id as id  ," +
            "v_tb_sy_xfmtsxxx.cjsj  as cjsj ," +
            "v_tb_sy_xfmtsxxx.gxsj  as gxsj ," +
            "v_tb_sy_xfmtsxxx.yxx  as yxx ," +
            "v_tb_sy_xfmtsxxx.czz  as czz ," +
            "v_tb_sy_xfmtsxxx.qsmt_tywysbm  as qsmt_tywysbm  ," +
            "v_tb_sy_xfmtsxxx.szsy_id  as  szsy_id ," +
            "v_tb_sy_xfmtsxxx.szsy_mc  as szsy_mc  ," +
            "v_tb_sy_xfmtsxxx.qs_gd  as qs_gd  ," +
            "v_tb_sy_xfmtsxxx.sybgc_gd  as   sybgc_gd  ," +
            "v_tb_sy_xfmtsxxx.tcwz_ddmc  as   tcwz_ddmc  ," +
            "v_tb_sy_xfmtsxxx.tc_sl  as  tc_sl  ," +
            "v_tb_sy_xfmtsxxx.sjc   as sjc  " +
            "  from  v_tb_sy_xfmtsxxx " +
            "  where v_tb_sy_xfmtsxxx.sjc >?  and v_tb_sy_xfmtsxxx.sjc <= ?  " +
            " ";


    /**
     * ???????????????????????????
     *
     * @param startTime ????????????
     * @param endTime   ????????????
     * @return ??????????????????
     */
    private List<WaterIntakeWharfEntity> findScheduleTable(Long startTime , Long endTime ) {
        PreparedStatement pstat = null;
        ResultSet rs = null;
        List<WaterIntakeWharfEntity> list ;
        try ( Connection conn = connectionsFactory.getConnection( env.getProperty("waterIntakeWharf") ) ) {
            if (startTime < endTime) {
                list = new ArrayList<>();
                pstat = conn.prepareStatement( sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                pstat.setTimestamp(1, new Timestamp(startTime));
                pstat.setTimestamp(2, new Timestamp(endTime));
                rs = pstat.executeQuery();

                while (rs.next()) {
                    WaterIntakeWharfEntity entity = new WaterIntakeWharfEntity();
                    //???????????? ????????????
                    entity.setId( rs.getString("id"));
                    entity.setCreatedTime( rs.getLong("cjsj"));
                    entity.setUpdatedTime( rs.getLong("gxsj"));
                    entity.setValid(rs.getInt("yxx"));
                    entity.setOperator( rs.getString("czz") );
                    //????????????
                    entity.setIntakeHeight( rs.getString("qs_gd") );
                    entity.setIdCode( rs.getString("qsmt_tywysbm") );
                    entity.setElevationHeight( rs.getString("sybgc_gd") );
                    entity.setBelongingWaterId( rs.getString("szsy_id") );
                    entity.setBelongingWaterName( rs.getString("szsy_mc") );
                    entity.setParkingNum( rs.getInt("tc_sl") );
                    entity.setParkingPosition( rs.getString("tcwz_ddmc") );
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
