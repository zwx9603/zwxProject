package com.dscomm.iecs.schedule.service.basedata.water;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.dal.po.WaterNaturalSourceEntity;
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

@Component("waterNaturalSourceScheduleServiceImpl")
public class WaterNaturalSourceScheduleServiceImpl implements WaterNaturalSourceScheduleService {

    private static final Logger logger = LoggerFactory.getLogger(WaterNaturalSourceScheduleServiceImpl.class);
    private LogService logService;

    private Environment env;
    private GeneralAccessor accessor;
    private ConnectionsFactory connectionsFactory;
    private static final String id = "sy_trsyxx";


    @Autowired
    public WaterNaturalSourceScheduleServiceImpl(LogService logService , Environment env,
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
            logService.infoLog(logger, "service", "WaterNaturalSource", "service is started...");
            Long logStart = System.currentTimeMillis();

            TransporterEntity entity = accessor.getById(id, TransporterEntity.class);
            if (entity == null) {
                logService.erorLog(logger, "service", "WaterNaturalSource", "table config not exist ",  new Exception() );
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
                    List<WaterNaturalSourceEntity> list = findScheduleTable(lastUpdataTime, endTime);
                    //结果不为空 保存数据
                    if (list != null && list.size() > 0) {
                        logService.infoLog(logger, "service", "WaterNaturalSource",
                                String.format(" find schedule table total : %d ,start time :%s ,end time :%s" , list.size()  , new Timestamp(lastUpdataTime) , new Timestamp(endTime)  )   );

                        accessor.save(list);
                    }else{
                        logService.infoLog(logger, "service", "WaterNaturalSource",
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
            logService.infoLog(logger, "service", "WaterNaturalSource", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "WaterNaturalSource", String.format(" transport account task fail  , target time :%s.", new Timestamp(targetTime) ), ex);
            throw new ScheduleException(ScheduleException.ScheduleErrors.SCHEDULE_DATA_FAIL);
        }

    }



    private static String sql = " select " +
            "v_tb_sy_trsyxx.id as id  ," +
            "v_tb_sy_trsyxx.cjsj     as cjsj ," +
            "v_tb_sy_trsyxx.gxsj      as gxsj ," +
            "v_tb_sy_trsyxx.yxx  as yxx ," +
            "v_tb_sy_trsyxx.czz  as czz ," +
            "v_tb_sy_trsyxx.mj  as  mj  ," +
            "v_tb_sy_trsyxx.ksqkd_jyqk  as  ksqkd_jyqk  ," +
            "v_tb_sy_trsyxx.trsy_tywysbm  as  trsy_tywysbm  ," +
            "v_tb_sy_trsyxx.sylxdm  as  sylxdm  ," +
            "v_tb_sy_trsyxx.sjbh_jyqk  as  sjbh_jyqk  ," +
            "v_tb_sy_trsyxx.rl  as  rl  ," +
            "v_tb_sy_trsyxx.gd  as  gd  ," +
            "v_tb_sy_trsyxx.szqk_jyqk  as  szqk_jyqk  ," +
            "v_tb_sy_trsyxx.ywksq_pdbz  as  ywksq_pdbz  ," +
            "v_tb_sy_trsyxx.sjc  as    sjc  " +
            "  from  v_tb_sy_trsyxx " +
            "  where v_tb_sy_trsyxx.sjc >?  and v_tb_sy_trsyxx.sjc <= ?  " +
            " ";


    /**
     * 从原数据库查询数据
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 返回查询结果
     */
    private List<WaterNaturalSourceEntity> findScheduleTable(Long startTime , Long endTime ) {
        PreparedStatement pstat = null;
        ResultSet rs = null;
        List<WaterNaturalSourceEntity> list ;
        try ( Connection conn = connectionsFactory.getConnection( env.getProperty("waterNaturalSource") ) ) {
            if (startTime < endTime) {
                list = new ArrayList<>();
                pstat = conn.prepareStatement( sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                pstat.setTimestamp(1, new Timestamp(startTime));
                pstat.setTimestamp(2, new Timestamp(endTime));
                rs = pstat.executeQuery();

                while (rs.next()) {
                    WaterNaturalSourceEntity entity = new WaterNaturalSourceEntity();
                    //处理数据 通用属性
                    entity.setId( rs.getString("id"));
                    entity.setCreatedTime( rs.getLong("cjsj"));
                    entity.setUpdatedTime( rs.getLong("gxsj"));
                    entity.setValid(rs.getInt("yxx"));
                    entity.setOperator( rs.getString("czz") );
                    //一般属性
                    entity.setWaterNaturalSourceHeight( rs.getString("gd") );
                    entity.setDrySeasonDesc( rs.getString("ksqkd_jyqk") );
                    entity.setArea( rs.getString("mj") );
                    entity.setStorage( rs.getString("rl") );
                    entity.setSeasonalChangesDesc( rs.getString("sjbh_jyqk") );
                    entity.setNaturalWaterTypeCode( rs.getString("sylxdm") );
                    entity.setWaterQualityDesc( rs.getString("szqk_jyqk") );
                    entity.setIdCode( rs.getString("trsy_tywysbm") );
                    entity.setWhetherDrySeason( rs.getInt("ywksq_pdbz") );
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
