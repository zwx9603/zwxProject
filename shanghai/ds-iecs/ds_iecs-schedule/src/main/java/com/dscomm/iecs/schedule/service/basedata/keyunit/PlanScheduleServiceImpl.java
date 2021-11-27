package com.dscomm.iecs.schedule.service.basedata.keyunit;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.dal.po.PlanEntity;
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

@Component("planScheduleServiceImpl")
public class PlanScheduleServiceImpl implements PlanScheduleService {

    private static final Logger logger = LoggerFactory.getLogger(PlanScheduleServiceImpl.class);
    private LogService logService;

    private Environment env;
    private GeneralAccessor accessor;
    private ConnectionsFactory connectionsFactory;
    private static final String id = "yagl_yajbxx";


    @Autowired
    public PlanScheduleServiceImpl(LogService logService , Environment env,
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
            logService.infoLog(logger, "service", "Plan", "service is started...");
            Long logStart = System.currentTimeMillis();

            TransporterEntity entity = accessor.getById(id, TransporterEntity.class);
            if (entity == null) {
                logService.erorLog(logger, "service", "Plan", "table config not exist ",  new Exception() );
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
                    List<PlanEntity> list = findScheduleTable(lastUpdataTime, endTime);
                    //结果不为空 保存数据
                    if (list != null && list.size() > 0) {
                        logService.infoLog(logger, "service", "Plan",
                                String.format(" find schedule table total : %d ,start time :%s ,end time :%s" , list.size()  , new Timestamp(lastUpdataTime) , new Timestamp(endTime)  )   );

                        accessor.save(list);
                    }else{
                        logService.infoLog(logger, "service", "Plan",
                                String.format(" find schedule table is null  ,start time :%s ,end time :%s" ,   new Timestamp(lastUpdataTime) , new Timestamp(endTime)  ) );
                    }

                    i++ ;
                }
            }
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "Plan", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "Plan", String.format(" transport account task fail  , target time :%s.", new Timestamp(targetTime) ), ex);
            throw new ScheduleException(ScheduleException.ScheduleErrors.SCHEDULE_DATA_FAIL);
        }

    }



    private static String sql = " select " +
            "v_tb_yagl_yajbxx.id as id  ," +
            "v_tb_yagl_yajbxx.cjsj   as cjsj ," +
            "v_tb_yagl_yajbxx.gxsj  as gxsj ," +
            "v_tb_yagl_yajbxx.jlzt as yxx ," +
            "v_tb_yagl_yajbxx.mbid as mbid," +
            "v_tb_yagl_yajbxx.yazl as yazl ," +
            "v_tb_yagl_yajbxx.yalxdm as yalxdm ," +
            "v_tb_yagl_yajbxx.yabh as yabh ," +
            "v_tb_yagl_yajbxx.yamc as yamc ," +
            "v_tb_yagl_yajbxx.yaztdm as yaztdm ," +
            "v_tb_yagl_yajbxx.dxid as dxid ," +
            "v_tb_yagl_yajbxx.dxmc as dxmc ," +
            "v_tb_yagl_yajbxx.dxlxdm as dxlxdm ," +
            "v_tb_yagl_yajbxx.yasxh as yasxh ," +
            "v_tb_yagl_yajbxx.bwid as bwid ," +
            "v_tb_yagl_yajbxx.bwmc as bwmc ," +
            "v_tb_yagl_yajbxx.gxbbh as gxbbh ," +
            "v_tb_yagl_yajbxx.jgid as jgid ," +
            "v_tb_yagl_yajbxx.yabbh as yabbh ," +
            "v_tb_yagl_yajbxx.bmdj as bmdj ," +
            "v_tb_yagl_yajbxx.sfkqy as sfkqy ," +
            "v_tb_yagl_yajbxx.sfmya as sfmya ," +
            "v_tb_yagl_yajbxx.sfwlsj as sfwlsj ," +
            "v_tb_yagl_yajbxx.zzrid as zzrid ," +
            "v_tb_yagl_yajbxx.zzrmc as zzrmc ," +
            "v_tb_yagl_yajbxx.zzdwid as zzdwid ," +
            "v_tb_yagl_yajbxx.zzrq as zzrq ," +
            "v_tb_yagl_yajbxx.czyid as czyid ," +
            "v_tb_yagl_yajbxx.czyxm as czyxm," +
            "v_tb_yagl_yajbxx.czjg as czjg ," +
            "v_tb_yagl_yajbxx.bz as bz ," +
            "v_tb_yagl_yajbxx.sjc as sjc ," +
            "v_tb_yagl_yajbxx.jlzt  as jlzt ," +
            "v_tb_yagl_yajbxx.cszt  as cszt ," +
            "v_tb_yagl_yajbxx.sjbb as sjbb ," +
            "v_tb_yagl_yajbxx.ywxtbsid as ywxtbsid ," +
            "v_tb_yagl_yajbxx.jksjbb as jksjbb ," +
            "v_tb_yagl_yajbxx.yjya_tywysbm as yjya_tywysbm  ," +
            "v_tb_yagl_yajbxx.yazldm as yazldm ," +
            "v_tb_yagl_yajbxx.yjyalxlbdm as yjyalxlbdm ," +
            "v_tb_yagl_yajbxx.mc as mc ," +
            "v_tb_yagl_yajbxx.yanr_jyqk as yanr_jyqk  ," +
            "v_tb_yagl_yajbxx.yags_jyqk as yags_jyqk  ," +
            "v_tb_yagl_yajbxx.yadx as yadx ," +
            "v_tb_yagl_yajbxx.xfjyjg_tywysbm as xfjyjg_tywysbm ," +
            "v_tb_yagl_yajbxx.xzqhdm as xzqhdm ," +
            "v_tb_yagl_yajbxx.yazzr_xm as yazzr_xm ," +
            "v_tb_yagl_yajbxx.yazzdw_dwid as yazzdw_dwid ," +
            "v_tb_yagl_yajbxx.yazzdw_dwmc as yazzdw_dwmc ," +
            "v_tb_yagl_yajbxx.yazz_rqsj  as yazz_rqsj  " +
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
    private List<PlanEntity> findScheduleTable(Long startTime , Long endTime ) {
        PreparedStatement pstat = null;
        ResultSet rs = null;
        List<PlanEntity> list ;
        try ( Connection conn = connectionsFactory.getConnection( env.getProperty("plan") ) ) {
            if (startTime < endTime) {
                list = new ArrayList<>();
                pstat = conn.prepareStatement( sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                pstat.setTimestamp(1, new Timestamp(startTime));
                pstat.setTimestamp(2, new Timestamp(endTime));
                rs = pstat.executeQuery();

                while (rs.next()) {
                    PlanEntity entity = new PlanEntity();
                    //处理数据 通用属性
                    entity.setId( rs.getString("id"));
                    entity.setCreatedTime( rs.getLong("cjsj"));
                    entity.setUpdatedTime( rs.getLong("gxsj"));
                    entity.setValid(rs.getInt("yxx"));
                    entity.setOperator( "N/A" );
                    //一般属性
                    entity.setSecurityClassification(rs.getString("bmdj"));
                    entity.setPositionId(rs.getString("bwid"));
                    entity.setPlanName(rs.getString("bwmc"));
                    entity.setRemarks(rs.getString("bz"));
                    entity.setCSZT(rs.getInt("cszt"));
                    entity.setOperateOrganization(rs.getString("czjg"));
                    entity.setOperatorId(rs.getString("czyid"));
                    entity.setObjectName(rs.getString("czyxm"));
                    entity.setObjectTypeCode(rs.getString("dxlxdm"));
                    entity.setObjectName(rs.getString("dxmc"));
                    entity.setMappingVersionNum(rs.getString("gxbbh"));
                    entity.setJKSJBB(rs.getString("jksjbb"));
                    entity.setJLZT(rs.getInt("jlzt"));
                    entity.setTemplateId(rs.getString("mbid"));
                    entity.setPlanName(rs.getString("mc"));
                    entity.setWhetherCrossRegion(rs.getInt("sfkqy"));
                    entity.setWhetherParentPlan(rs.getInt("sfmya"));
                    entity.setWhetherExternalSystemData(rs.getInt("sfwlsj"));
                    entity.setSJBB(rs.getString("sjbb"));
                    entity.setSJC(rs.getLong("sjc"));
                    entity.setOrganizationId(rs.getString("xfjyjg_tywysbm"));
                    entity.setDistrictCode(rs.getString("xzqhdm"));
                    entity.setVersionNum(rs.getString("yabbh"));
                    entity.setKeyUnitId(rs.getString("yadx"));
                    entity.setPlanBrieflyDesc(rs.getString("yags_jyqk"));
                    entity.setPlanDesc(rs.getString("yanr_jyqk"));
                    entity.setPlanOrderNum(rs.getInt("yasxh"));
                    entity.setPlanCategoryCode(rs.getString("yazldm"));
                    entity.setPlanStatusCode(rs.getString("yaztdm"));
                    entity.setMakeTime(rs.getLong("yazz_rqsj"));
                    entity.setMakeOrganizationId(rs.getString("yazzdw_dwid"));
                    entity.setMakeOrganizationName(rs.getString("yazzdw_dwmc"));
                    entity.setMakePersonName(rs.getString("yazzr_xm"));
                    entity.setIdCode(rs.getString("yjya_tywysbm"));
                    entity.setPlanTypeCode(rs.getString("yjyalxlbdm"));
                    entity.setYWXTBSID(rs.getString("ywxtbsid"));
                    entity.setMakePersonId(rs.getString("zzrid"));


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
