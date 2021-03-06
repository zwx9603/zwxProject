package com.dscomm.iecs.schedule.service.basedata.keyunit;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.dal.po.PlanDispatchEntity;
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

@Component("planDispatchScheduleServiceImpl")
public class PlanDispatchScheduleServiceImpl implements PlanDispatchScheduleService {

    private static final Logger logger = LoggerFactory.getLogger(PlanDispatchScheduleServiceImpl.class);
    private LogService logService;

    private Environment env;
    private GeneralAccessor accessor;
    private ConnectionsFactory connectionsFactory;
    private static final String id = "yagl_cldj";


    @Autowired
    public PlanDispatchScheduleServiceImpl(LogService logService , Environment env,
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
            logService.infoLog(logger, "service", "PlanDispatch", "service is started...");
            Long logStart = System.currentTimeMillis();

            TransporterEntity entity = accessor.getById(id, TransporterEntity.class);
            if (entity == null) {
                logService.erorLog(logger, "service", "PlanDispatch", "table config not exist ",  new Exception() );
                return ;
            }
            Long lastUpdataTime = entity.getZHTBSJ();
            DateUtils.FieldType fieldType = ScheduleUtil.transFormDateFieldType(entity.getDCTBSCDW() );
            int onceTime = entity.getDCTBSC();
            //??????????????????????????????????????????????????????
            if (lastUpdataTime != null && targetTime != null && lastUpdataTime < targetTime) {
                int i = 0 ; //?????????????????? 7 ???
                while (lastUpdataTime < targetTime && i< 7 ) {
                    Long endTime = new Date().getTime();
//                            DateUtils.add(new Date(lastUpdataTime), fieldType, onceTime).getTime();
                    if (endTime > targetTime) {
                        endTime = targetTime;
                    }
                    //????????????
                    List<PlanDispatchEntity> list = findScheduleTable(lastUpdataTime, endTime);
                    //??????????????? ????????????
                    if (list != null && list.size() > 0) {
                        logService.infoLog(logger, "service", "PlanDispatch",
                                String.format(" find schedule table total : %d ,start time :%s ,end time :%s" , list.size()  , new Timestamp(lastUpdataTime) , new Timestamp(endTime)  )   );

                        accessor.save(list);
                    }else{
                        logService.infoLog(logger, "service", "PlanDispatch",
                                String.format(" find schedule table is null  ,start time :%s ,end time :%s" ,   new Timestamp(lastUpdataTime) , new Timestamp(endTime)  ) );
                    }

                    i++ ;
                }
            }
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "PlanDispatch", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "PlanDispatch", String.format(" transport account task fail  , target time :%s.", new Timestamp(targetTime) ), ex);
            throw new ScheduleException(ScheduleException.ScheduleErrors.SCHEDULE_DATA_FAIL);
        }

    }



    private static String sql = " select " +
            "v_tb_yagl_cldj.id as id  ," +
            "v_tb_yagl_cldj.cjsj   as cjsj ," +
            "v_tb_yagl_cldj.gxsj   as gxsj ," +
            "v_tb_yagl_cldj.yxx  as yxx ," +
            "v_tb_yagl_cldj.yaid as yaid ," +
            "v_tb_yagl_cldj.ssdwid as ssdwid ," +
            "v_tb_yagl_cldj.clid as clid ," +
            "v_tb_yagl_cldj.cdzbdm as cdzbdm ," +
            "v_tb_yagl_cldj.cphm as cphm ," +
            "v_tb_yagl_cldj.cllxdm as cllxdm ," +
            "v_tb_yagl_cldj.cldjdm as cldjdm ," +
            "v_tb_yagl_cldj.zzgndm as zzgndm ," +
            "v_tb_yagl_cldj.cdsx   as cdsx ," +
            "v_tb_yagl_cldj.dcsj as dcsj ," +
            "v_tb_yagl_cldj.czyid as czyid ," +
            "v_tb_yagl_cldj.czyxm as czyxm ," +
            "v_tb_yagl_cldj.jgid as jgid ," +
            "v_tb_yagl_cldj.bz as bz ," +
            "v_tb_yagl_cldj.sjc as sjc ," +
            "v_tb_yagl_cldj.jlzt as jlzt ," +
            "v_tb_yagl_cldj.cszt as cszt ," +
            "v_tb_yagl_cldj.sjbb as sjbb ," +
            "v_tb_yagl_cldj.ywxtbsid as ywxtbsid ," +
            "v_tb_yagl_cldj.jksjbb as jksjbb " +
            "  from  v_tb_yagl_cldj " +
            "  where v_tb_yagl_cldj.sjc >?  and v_tb_yagl_cldj.sjc <= ?  " +
            " ";

    /**
     * ???????????????????????????
     *
     * @param startTime ????????????
     * @param endTime   ????????????
     * @return ??????????????????
     */
    private List<PlanDispatchEntity> findScheduleTable(Long startTime , Long endTime ) {
        PreparedStatement pstat = null;
        ResultSet rs = null;
        List<PlanDispatchEntity> list ;
        try ( Connection conn = connectionsFactory.getConnection( env.getProperty("planDispatch") ) ) {
            if (startTime < endTime) {
                list = new ArrayList<>();
                pstat = conn.prepareStatement( sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                pstat.setTimestamp(1, new Timestamp(startTime));
                pstat.setTimestamp(2, new Timestamp(endTime));
                rs = pstat.executeQuery();

                while (rs.next()) {
                    PlanDispatchEntity entity = new PlanDispatchEntity();
                    //???????????? ????????????
                    entity.setId( rs.getString("id"));
                    entity.setCreatedTime( rs.getLong("cjsj"));
                    entity.setUpdatedTime( rs.getLong("gxsj"));
                    entity.setValid(rs.getInt("yxx"));
                    entity.setOperator( "N/A" );
                    //????????????
                    entity.setDispatchOrderNum(rs.getInt("cdsx"));
                    entity.setDispatchGroup(rs.getString("cdzbdm"));
                    entity.setVehicleLevelCode(rs.getString("cldjdm"));
                    entity.setRemarks(rs.getString("bz"));
                    entity.setCSZT(rs.getInt("cszt"));
                    entity.setVehicleId(rs.getString("clid"));
                    entity.setOperatorId(rs.getString("czyid"));
                    entity.setVehicleTypeCode(rs.getString("cllxdm"));
                    entity.setOperatorName(rs.getString("czyxm"));
                    entity.setAttendanceTime(rs.getLong("dcsj"));
                    entity.setOrganizationId(rs.getString("jgid"));
                    entity.setJKSJBB(rs.getString("jksjbb"));
                    entity.setJLZT(rs.getInt("jlzt"));
                    entity.setPlanId(rs.getString("yaid"));
                    entity.setOperationalFunctionCode(rs.getString("zzgndm"));
                    entity.setSJBB(rs.getString("sjbb"));
                    entity.setSJC(rs.getLong("sjc"));
                    entity.setYWXTBSID(rs.getString("ywxtbsid"));


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
