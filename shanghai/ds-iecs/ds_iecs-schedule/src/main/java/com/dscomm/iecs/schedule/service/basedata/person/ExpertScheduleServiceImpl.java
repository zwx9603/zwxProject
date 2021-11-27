package com.dscomm.iecs.schedule.service.basedata.person;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.dal.po.ExpertEntity;
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

@Component("expertScheduleServiceImpl")
public class ExpertScheduleServiceImpl implements ExpertScheduleService {

    private static final Logger logger = LoggerFactory.getLogger(ExpertScheduleServiceImpl.class);
    private LogService logService;

    private Environment env;
    private GeneralAccessor accessor;
    private ConnectionsFactory connectionsFactory;
    private static final String id = "t_coc_fire_ld";


    @Autowired
    public ExpertScheduleServiceImpl(LogService logService , Environment env,
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
            logService.infoLog(logger, "service", "Leader", "service is started...");
            Long logStart = System.currentTimeMillis();

            TransporterEntity entity = accessor.getById(id, TransporterEntity.class);
            if (entity == null) {
                logService.erorLog(logger, "service", "Leader", "table config not exist ",  new Exception() );
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
                    List<ExpertEntity> list = findScheduleTable(lastUpdataTime, endTime);
                    //结果不为空 保存数据
                    if (list != null && list.size() > 0) {
                        logService.infoLog(logger, "service", "Leader",
                                String.format(" find schedule table total : %d ,start time :%s ,end time :%s" , list.size()  , new Timestamp(lastUpdataTime) , new Timestamp(endTime)  )   );

                        accessor.save(list);
                    }else{
                        logService.infoLog(logger, "service", "Leader",
                                String.format(" find schedule table is null  ,start time :%s ,end time :%s" ,   new Timestamp(lastUpdataTime) , new Timestamp(endTime)  ) );
                    }
                    //修改更新时间
                    lastUpdataTime = endTime;
                    //更新数据库的更新时间
                    entity.setZHTBSJ(lastUpdataTime);
//                    accessor.save(entity);
                    i++ ;
                }
            }
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "Leader", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "Leader", String.format(" transport account task fail  , target time :%s.", new Timestamp(targetTime) ), ex);
            throw new ScheduleException(ScheduleException.ScheduleErrors.SCHEDULE_DATA_FAIL);
        }

    }



    private static String sql = " select " +
            " id, " +
            " cjsj, " +
            " gxsj, " +
            " czz, " +
            " xm, " +
            " xb, " +
            " bgdh, " +
            " zwmc, " +
            " zjly, " +
            " txdz, " +
            " yddh, " +
            " xl, " +
            " sjjgid, " +
            " yxx, " +
            " zjlx, " +
            " jtdh, " +
            " csrq, " +
            " jg, " +
            " zp, " +
            " yzbm, " +
            " mz, " +
            " ryzk, " +
            " rylb, " +
            " ryzwqk, " +
            " zzmm, " +
            " bz, " +
            " gw, " +
            " mhjyzj_tywysbm, " +
            " gmsfhm, " +
            " xbdm, " +
            " xldm, " +
            " xfgwlbdm, " +
            " jgdm, " +
            " cs_rq, " +
            " jzh_dzmc, " +
            " yd_lxdh, " +
            " jt_lxdh, " +
            " bg_lxdh, " +
            " dwmc, " +
            " xfzjlylbdm, " +
            " sfdwnbzj_pdbz, " +
            " xfjyjg_tywysbm, " +
            " xzqhdm, " +
            " sjc " +
            "  from  v_td_t_coc_fire_zjxx schedule" +
            "  where schedule.sjc >?  and schedule.sjc <= ?  " +
            " ";


    /**
     * 从原数据库查询数据
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 返回查询结果
     */
    private List<ExpertEntity> findScheduleTable(Long startTime , Long endTime ) {
        PreparedStatement pstat = null;
        ResultSet rs = null;
        List<ExpertEntity> list ;
        try ( Connection conn = connectionsFactory.getConnection( env.getProperty("expert") ) ) {
            if (startTime < endTime) {
                list = new ArrayList<>();
                pstat = conn.prepareStatement( sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                pstat.setTimestamp(1, new Timestamp(startTime));
                pstat.setTimestamp(2, new Timestamp(endTime));
                rs = pstat.executeQuery();

                while (rs.next()) {
                    ExpertEntity entity = new ExpertEntity();
                    //处理数据 通用属性
                    entity.setId( rs.getString("id"));
                    entity.setCreatedTime( rs.getLong("cjsj"));
                    entity.setUpdatedTime( rs.getLong("gxsj"));
                    entity.setValid(rs.getInt("yxx"));
                    entity.setOperator( rs.getString("czz") );
                    //一般属性
                    entity.setOfficePhone( rs.getString("bg_lxdh") );
                    entity.setUnitName( rs.getString("dwmc") );
                    entity.setRemarks( rs.getString("bz") );
                    entity.setDateBirth( rs.getLong("cs_rq") );
                    entity.setIDCard( rs.getString("gmsfhm") );
                    entity.setNativePlace( rs.getString("jgdm") );
                    entity.setHomePhone( rs.getString("jt_lxdh") );
                    entity.setAddressName( rs.getString("jzh_dzmc") );
                    entity.setIdCode( rs.getString("mhjyzj_tywysbm") );
                    entity.setNationCode( rs.getString("mz") );
                    entity.setPersonCategory( rs.getString("rylb") );
                    entity.setPerson( rs.getString("ryzk") );
                    entity.setPersonReign( rs.getString("ryzwqk") );
                    entity.setWhetherInnerExpert( rs.getInt("sfdwnbzj_pdbz") );
                    entity.setAddress( rs.getString("txdz") );
                    entity.setExpertSex( rs.getString("xbdm") );
                    entity.setQuarters( rs.getString("xfgwlbdm") );
                    entity.setOrganizationId( rs.getString("xfjyjg_tywysbm") );
                    entity.setExpertField( rs.getString("xfzjlylbdm") );
                    entity.setEducation( rs.getString("xldm") );
                    entity.setExpertName( rs.getString("xm") );
                    entity.setDistrictCode( rs.getString("xzqhdm") );
                    entity.setMobilePhone( rs.getString("yd_lxdh") );
                    entity.setPostalCode( rs.getString("yzbm") );
                    entity.setExpertType( rs.getString("zjlx") );
                    entity.setPicture( rs.getString("zp") );
                    entity.setDuties( rs.getString("zwmc") );
                    entity.setPolitical( rs.getString("zzmm") );

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
