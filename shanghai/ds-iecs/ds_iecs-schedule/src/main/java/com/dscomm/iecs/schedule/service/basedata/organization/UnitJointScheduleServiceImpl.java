package com.dscomm.iecs.schedule.service.basedata.organization;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.dal.po.UnitJointServiceEntity;
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

@Component("unitJointScheduleServiceImpl")
public class UnitJointScheduleServiceImpl implements UnitJointScheduleService {

    private static final Logger logger = LoggerFactory.getLogger(UnitJointScheduleServiceImpl.class);
    private LogService logService;

    private Environment env;
    private GeneralAccessor accessor;
    private ConnectionsFactory connectionsFactory;
    private static final String id = "jgxx_lqbzdw";


    @Autowired
    public UnitJointScheduleServiceImpl(LogService logService, Environment env,
                                        @Qualifier("generalAccessor") GeneralAccessor accessor,
                                        ConnectionsFactory connectionsFactory) {
        this.logService = logService;
        this.env = env;
        this.accessor = accessor;
        this.connectionsFactory = connectionsFactory;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void transport(Long targetTime) {
        try {
            logService.infoLog(logger, "service", "UnitJoint", "service is started...");
            Long logStart = System.currentTimeMillis();

            TransporterEntity entity = accessor.getById(id, TransporterEntity.class);
            if (entity == null) {
                logService.erorLog(logger, "service", "UnitJoint", "table config not exist ", new Exception());
                return;
            }
            Long lastUpdataTime = entity.getZHTBSJ();
            DateUtils.FieldType fieldType = ScheduleUtil.transFormDateFieldType(entity.getDCTBSCDW());
            int onceTime = entity.getDCTBSC();
            //更新时间晚于目标时间，则进行同步操作
            if (lastUpdataTime != null && targetTime != null && lastUpdataTime < targetTime) {
                int i = 0; //单次最多执行 7 次
                while (lastUpdataTime < targetTime && i < 7) {
                    Long endTime = new Date().getTime();
//                            DateUtils.add(new Date(lastUpdataTime), fieldType, onceTime).getTime();
                    if (endTime > targetTime) {
                        endTime = targetTime;
                    }
                    //单次查询
                    List<UnitJointServiceEntity> list = findScheduleTable(lastUpdataTime, endTime);
                    //结果不为空 保存数据
                    if (list != null && list.size() > 0) {
                        logService.infoLog(logger, "service", "UnitJoint",
                                String.format(" find schedule table total : %d ,start time :%s ,end time :%s", list.size(), new Timestamp(lastUpdataTime), new Timestamp(endTime)));

                        accessor.save(list);
                    } else {
                        logService.infoLog(logger, "service", "UnitJoint",
                                String.format(" find schedule table is null  ,start time :%s ,end time :%s", new Timestamp(lastUpdataTime), new Timestamp(endTime)));
                    }

                    i++;
                }
            }
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "UnitJoint", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "UnitJoint", String.format(" transport account task fail  , target time :%s.", new Timestamp(targetTime)), ex);
            throw new ScheduleException(ScheduleException.ScheduleErrors.SCHEDULE_DATA_FAIL);
        }

    }


    private static String sql = " select " +
            "v_tb_jgxx_lqbzdw.id as id  ," +
            "v_tb_jgxx_lqbzdw.cjsj   as cjsj ," +
            "v_tb_jgxx_lqbzdw.gxsj  as gxsj ," +
            "v_tb_jgxx_lqbzdw.jlzt as yxx ," +
            "v_tb_jgxx_lqbzdw.dwmc as dwmc," +
            "v_tb_jgxx_lqbzdw.bznl as bznl," +
            "v_tb_jgxx_lqbzdw.zybzlbdm as zybzlbdm ," +
            "v_tb_jgxx_lqbzdw.yjzbdh as yjzbdh ," +
            "v_tb_jgxx_lqbzdw.gis_x as gis_x ," +
            "v_tb_jgxx_lqbzdw.gis_y as gis_y ," +
            "v_tb_jgxx_lqbzdw.gis_h as gis_h," +
            "v_tb_jgxx_lqbzdw.glid as glid ," +
            "v_tb_jgxx_lqbzdw.whxfjgid as whxfjgid," +
            "v_tb_jgxx_lqbzdw.sfwnbjg as sfwnbjg ," +
            "v_tb_jgxx_lqbzdw.nbjgid as nbjgid ," +
            "v_tb_jgxx_lqbzdw.wbdwid as wbdwid ," +
            "v_tb_jgxx_lqbzdw.yydjdm as yydjdm, " +
            "v_tb_jgxx_lqbzdw.bz as bz ," +
            "v_tb_jgxx_lqbzdw.sjc as sjc ," +
            "v_tb_jgxx_lqbzdw.jlzt as jlzt ," +
            "v_tb_jgxx_lqbzdw.cszt as cszt ," +
            "v_tb_jgxx_lqbzdw.sjbb as sjbb ," +
            "v_tb_jgxx_lqbzdw.ywxtbsid as ywxtbsid ," +
            "v_tb_jgxx_lqbzdw.jksjbb as jksjbb  ," +
            "v_tb_jgxx_lqbzdw.lqbzdw_tywysbm as lqbzdw_tywysbm  ," +
            "v_tb_jgxx_lqbzdw.dzmc as dzmc  ," +
            "v_tb_jgxx_lqbzdw.czhm as czhm  ," +
            "v_tb_jgxx_lqbzdw.dw_jyqk as dw_jyqk  ," +
            "v_tb_jgxx_lqbzdw.lqbzlbdm as lqbzlbdm ," +
            "v_tb_jgxx_lqbzdw.bznl_jyqk as bznl_jyqk," +
            "v_tb_jgxx_lqbzdw.bzgs_jyqk as bzgs_jyqk," +
            "v_tb_jgxx_lqbzdw.lxr as lxr," +
            "v_tb_jgxx_lqbzdw.lxr_xm as lxr_xm," +
            "v_tb_jgxx_lqbzdw.lxr_lxdh as lxr_lxdh," +
            "v_tb_jgxx_lqbzdw.dqjd as dqjd ," +
            "v_tb_jgxx_lqbzdw.dqwd as dqwd ," +
            "v_tb_jgxx_lqbzdw.dqgd as dqgd ," +
            "v_tb_jgxx_lqbzdw.xfjyjg_tywysbm as xfjyjg_tywysbm," +
            "v_tb_jgxx_lqbzdw.xzqhdm as xzqhdm " +
            "  from  v_tb_jgxx_lqbzdw " +
            "  where v_tb_jgxx_lqbzdw.sjc >?  and v_tb_jgxx_lqbzdw.sjc <= ?  " +
            " ";


    /**
     * 从原数据库查询数据
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 返回查询结果
     */
    private List<UnitJointServiceEntity> findScheduleTable(Long startTime, Long endTime) {
        PreparedStatement pstat = null;
        ResultSet rs = null;
        List<UnitJointServiceEntity> list;
        try (Connection conn = connectionsFactory.getConnection(env.getProperty("unitJoint"))) {
            if (startTime < endTime) {
                list = new ArrayList<>();
                pstat = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                pstat.setTimestamp(1, new Timestamp(startTime));
                pstat.setTimestamp(2, new Timestamp(endTime));
                rs = pstat.executeQuery();

                while (rs.next()) {
                    UnitJointServiceEntity entity = new UnitJointServiceEntity();
                    //处理数据 通用属性
                    entity.setId(rs.getString("id"));
                    entity.setCreatedTime(rs.getLong("cjsj"));
                    entity.setUpdatedTime(rs.getLong("gxsj"));
                    entity.setValid(rs.getInt("yxx"));
                    entity.setOperator(rs.getString("czz"));
                    //一般属性
                    entity.setRemarks(rs.getString("bz"));
                    entity.setCSZT(rs.getInt("cszt"));
                    entity.setFaxNumber(rs.getString("czhm"));
                    entity.setHeight(rs.getString("dqgd"));
                    entity.setLongitude(rs.getString("dqjd"));
                    entity.setLatitude(rs.getString("dqwd"));
                    entity.setUnitDesc(rs.getString("dw_jyqk"));
                    entity.setUnitName(rs.getString("dwmc"));
                    entity.setUnitAddress(rs.getString("dzmc"));
                    entity.setSupportDesc(rs.getString("bzgs_jyqk"));
                    entity.setSupportability(rs.getString("bznl_jyqk"));
                    entity.setJKSJBB(rs.getString("jksjbb"));
                    entity.setJLZT(rs.getInt("jlzt"));
                    entity.setContactPhone(rs.getString("lxr_lxdh"));
                    entity.setContactPerson(rs.getString("lxr"));
                    entity.setContactPersonName(rs.getString("lxr_xm"));
                    entity.setSJBB(rs.getString("sjbb"));
                    entity.setSJC(rs.getLong("sjc"));
                    entity.setIdCode(rs.getString("lqbzdw_tywysbm"));
                    entity.setDistrictCode(rs.getString("xzqhdm"));
                    entity.setYWXTBSID(rs.getString("ywxtbsid"));
                    entity.setMainSupportCategoryCode(rs.getString("lqbzlbdm"));
                    entity.setOrganizationId(rs.getString("xfjyjg_tywysbm"));
                    entity.setHospitalLevelCode(rs.getString("yydjdm"));
                    entity.setDutyPhone(rs.getString("yjzbdh"));
                    entity.setInnerOrganizationId(rs.getString("nbjgid"));
                    entity.setWhetherInnerOrganization(rs.getString("sfwnbjg"));
                    entity.setOuterOrganizationId(rs.getString("wbdwid"));


                    list.add(entity);
                }
                return list;
            }
            return null;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findScheduleTable", String.format(" find schedule table fail  , target time :%s.", new Timestamp(endTime)), ex);
            throw new ScheduleException(ScheduleException.ScheduleErrors.SCHEDULE_DATA_FAIL);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstat != null) {
                    pstat.close();
                }
            } catch (Exception ex) {
                logService.erorLog(logger, "service", "findScheduleTable", String.format(" find schedule table  fail  , target time :%s.", new Timestamp(System.currentTimeMillis())), ex);
                throw new ScheduleException(ScheduleException.ScheduleErrors.SCHEDULE_DATA_FAIL);
            }
        }
    }

}
