package com.dscomm.iecs.schedule.service.basedata.water;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.dal.po.WaterCraneEntity;
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

@Component("waterCraneScheduleServiceImpl")
public class WaterCraneScheduleServiceImpl implements WaterCraneScheduleService {

    private static final Logger logger = LoggerFactory.getLogger(WaterCraneScheduleServiceImpl.class);
    private LogService logService;

    private Environment env;
    private GeneralAccessor accessor;
    private ConnectionsFactory connectionsFactory;
    private static final String id = "sy_xfshsxxx";


    @Autowired
    public WaterCraneScheduleServiceImpl(LogService logService , Environment env,
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
            logService.infoLog(logger, "service", "WaterCrane", "service is started...");
            Long logStart = System.currentTimeMillis();

            TransporterEntity entity = accessor.getById(id, TransporterEntity.class);
            if (entity == null) {
                logService.erorLog(logger, "service", "WaterCrane", "table config not exist ",  new Exception() );
                return ;
            }
            Long lastUpdataTime = entity.getZHTBSJ();
            DateUtils.FieldType fieldType = ScheduleUtil.transFormDateFieldType(entity.getDCTBSCDW() );
            int onceTime = entity.getDCTBSC();
            //??????????????????????????????????????????????????????
            if (lastUpdataTime != null && targetTime != null && lastUpdataTime < targetTime) {
                int i = 0 ; //?????????????????? 7 ???
                while (lastUpdataTime < targetTime && i< 7 ) {
                    Long endTime =  DateUtils.add(new Date(lastUpdataTime), fieldType, onceTime).getTime();
                    if (endTime > targetTime) {
                        endTime = targetTime;
                    }
                    //????????????
                    List<WaterCraneEntity> list = findScheduleTable(lastUpdataTime, endTime);
                    //??????????????? ????????????
                    if (list != null && list.size() > 0) {
                        logService.infoLog(logger, "service", "WaterCrane",
                                String.format(" find schedule table total : %d ,start time :%s ,end time :%s" , list.size()  , new Timestamp(lastUpdataTime) , new Timestamp(endTime)  )   );

                        accessor.save(list);
                    }else{
                        logService.infoLog(logger, "service", "WaterCrane",
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
            logService.infoLog(logger, "service", "WaterCrane", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "WaterCrane", String.format(" transport account task fail  , target time :%s.", new Timestamp(targetTime) ), ex);
            throw new ScheduleException(ScheduleException.ScheduleErrors.SCHEDULE_DATA_FAIL);
        }

    }



    private static String sql = " select " +
            " v_tb_sy_xfshsxxx.id as id  ," +
            "v_tb_sy_xfshsxxx.cjsj    as cjsj ," +
            "v_tb_sy_xfshsxxx.gxsj    as gxsj ," +
            "v_tb_sy_xfshsxxx.yxx as yxx ," +
            "v_tb_sy_xfshsxxx.czz as czz ," +
            "v_tb_sy_xfshsxxx.csgzj_kd as  csgzj_kd ," +
            "v_tb_sy_xfshsxxx.ll as ll  ," +
            "v_tb_sy_xfshsxxx.xfsh_tywysbm as xfsh_tywysbm  ," +
            "v_tb_sy_xfshsxxx.jsgzj_kd as jsgzj_kd  ," +
            "v_tb_sy_xfshsxxx.gd as   gd  ," +
            "v_tb_sy_xfshsxxx.jscds_sl as  jscds_sl ," +
            "v_tb_sy_xfshsxxx.sjc as sjc  " +
            "  from  v_tb_sy_xfshsxxx " +
            "  where v_tb_sy_xfshsxxx.sjc >?  and v_tb_sy_xfshsxxx.sjc <= ?  " +
            " ";


    /**
     * ???????????????????????????
     *
     * @param startTime ????????????
     * @param endTime   ????????????
     * @return ??????????????????
     */
    private List<WaterCraneEntity> findScheduleTable(Long startTime , Long endTime ) {
        PreparedStatement pstat = null;
        ResultSet rs = null;
        List<WaterCraneEntity> list ;
        try ( Connection conn = connectionsFactory.getConnection( env.getProperty("waterCrane") ) ) {
            if (startTime < endTime) {
                list = new ArrayList<>();
                pstat = conn.prepareStatement( sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                pstat.setTimestamp(1, new Timestamp(startTime));
                pstat.setTimestamp(2, new Timestamp(endTime));
                rs = pstat.executeQuery();

                while (rs.next()) {
                    WaterCraneEntity entity = new WaterCraneEntity();
                    //???????????? ????????????
                    entity.setId( rs.getString("id"));
                    entity.setCreatedTime( rs.getLong("cjsj"));
                    entity.setUpdatedTime( rs.getLong("gxsj"));
                    entity.setValid(rs.getInt("yxx"));
                    entity.setOperator( rs.getString("czz") );
                    //????????????
                    entity.setEffluentDiameter( rs.getString("csgzj_kd") );
                    entity.setWaterCraneHeight( rs.getString("gd") );
                    entity.setWaterDriveway( rs.getInt("jscds_sl") );
                    entity.setInletDiameter( rs.getString("jsgzj_kd") );
                    entity.setFlowrate( rs.getString("ll") );
                    entity.setIdCode( rs.getString("xfsh_tywysbm") );
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
