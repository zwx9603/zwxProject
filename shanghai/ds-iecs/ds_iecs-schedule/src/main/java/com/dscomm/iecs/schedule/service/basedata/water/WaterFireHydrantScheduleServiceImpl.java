package com.dscomm.iecs.schedule.service.basedata.water;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.dal.po.WaterFireHydrantEntity;
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

@Component("waterFireHydrantScheduleServiceImpl")
public class WaterFireHydrantScheduleServiceImpl implements WaterFireHydrantScheduleService {

    private static final Logger logger = LoggerFactory.getLogger(WaterFireHydrantScheduleServiceImpl.class);
    private LogService logService;

    private Environment env;
    private GeneralAccessor accessor;
    private ConnectionsFactory connectionsFactory;
    private static final String id = "sy_xhssxxx";


    @Autowired
    public WaterFireHydrantScheduleServiceImpl(LogService logService , Environment env,
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
            logService.infoLog(logger, "service", "WaterFireHydrant", "service is started...");
            Long logStart = System.currentTimeMillis();

            TransporterEntity entity = accessor.getById(id, TransporterEntity.class);
            if (entity == null) {
                logService.erorLog(logger, "service", "WaterFireHydrant", "table config not exist ",  new Exception() );
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
                    List<WaterFireHydrantEntity> list = findScheduleTable(lastUpdataTime, endTime);
                    //结果不为空 保存数据
                    if (list != null && list.size() > 0) {
                        logService.infoLog(logger, "service", "WaterFireHydrant",
                                String.format(" find schedule table total : %d ,start time :%s ,end time :%s" , list.size()  , new Timestamp(lastUpdataTime) , new Timestamp(endTime)  )   );

                        accessor.save(list);
                    }else{
                        logService.infoLog(logger, "service", "WaterFireHydrant",
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
            logService.infoLog(logger, "service", "WaterFireHydrant", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "WaterFireHydrant", String.format(" transport account task fail  , target time :%s.", new Timestamp(targetTime) ), ex);
            throw new ScheduleException(ScheduleException.ScheduleErrors.SCHEDULE_DATA_FAIL);
        }

    }



    private static String sql = " select " +
            " v_tb_sy_xhssxxx.id as id  ," +
            "v_tb_sy_xhssxxx.cjsj    as cjsj ," +
            "v_tb_sy_xhssxxx.gxsj    as gxsj ," +
            "v_tb_sy_xhssxxx.yxx as yxx ," +
            "v_tb_sy_xhssxxx.czz as czz ," +
            "v_tb_sy_xhssxxx.ll as ll  ," +
            "v_tb_sy_xhssxxx.xhs_tywysbm as xhs_tywysbm  , " +
            "v_tb_sy_xhssxxx.sjc as sjc " +
            "  from  v_tb_sy_xhssxxx " +
            "  where v_tb_sy_xhssxxx.sjc >?  and v_tb_sy_xhssxxx.sjc <= ?  " +
            " ";


    /**
     * 从原数据库查询数据
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 返回查询结果
     */
    private List<WaterFireHydrantEntity> findScheduleTable(Long startTime , Long endTime ) {
        PreparedStatement pstat = null;
        ResultSet rs = null;
        List<WaterFireHydrantEntity> list ;
        try ( Connection conn = connectionsFactory.getConnection( env.getProperty("waterFireHydrant") ) ) {
            if (startTime < endTime) {
                list = new ArrayList<>();
                pstat = conn.prepareStatement( sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                pstat.setTimestamp(1, new Timestamp(startTime));
                pstat.setTimestamp(2, new Timestamp(endTime));
                rs = pstat.executeQuery();

                while (rs.next()) {
                    WaterFireHydrantEntity entity = new WaterFireHydrantEntity();
                    //处理数据 通用属性
                    entity.setId( rs.getString("id"));
                    entity.setCreatedTime( rs.getLong("cjsj"));
                    entity.setUpdatedTime( rs.getLong("gxsj"));
                    entity.setValid(rs.getInt("yxx"));
                    entity.setOperator( rs.getString("czz") );
                    //一般属性
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
