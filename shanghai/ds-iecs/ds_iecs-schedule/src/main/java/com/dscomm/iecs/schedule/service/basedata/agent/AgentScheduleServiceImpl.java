package com.dscomm.iecs.schedule.service.basedata.agent;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.dal.po.AgentEntity;
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

/**
 * 描述： 角色信息 同步服务
 *
 */
@Component("agentScheduleServiceImpl")
public class AgentScheduleServiceImpl implements AgentScheduleService {


    private static final Logger logger = LoggerFactory.getLogger(AgentScheduleServiceImpl.class);
    private LogService logService;

    private Environment env;
    private GeneralAccessor accessor;
    private ConnectionsFactory connectionsFactory;


    @Autowired
    public AgentScheduleServiceImpl(LogService logService , Environment env,
                                    @Qualifier("generalAccessor") GeneralAccessor accessor,
                                    ConnectionsFactory connectionsFactory   ) {
        this.logService = logService;
        this.env = env;
        this.accessor = accessor;
        this.connectionsFactory = connectionsFactory;
    }

    /**
     * {@inheritDoc}
     *
     * @see #transport(Long)
     */
    @Override
    @Transactional( rollbackFor = Exception.class )
    public void transport(Long targetTime) {
        try {
            logService.infoLog(logger, "service", "agent", "service is started...");
            Long logStart = System.currentTimeMillis();

            TransporterEntity entity = accessor.getById("jcj_bd_zx", TransporterEntity.class);
            if (entity == null) {
                logService.erorLog(logger, "service", "agent", "table config not exist ",  new Exception() );
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
                    List<AgentEntity> list = findScheduleTable(lastUpdataTime, endTime);
                    //结果不为空 保存数据
                    if (list != null && list.size() > 0) {
                        logService.infoLog(logger, "service", "agent",
                                String.format(" find schedule table total : %d ,start time :%s ,end time :%s" , list.size()  , new Timestamp(lastUpdataTime) , new Timestamp(endTime)  )   );

                        accessor.save(list);
                    }else{
                        logService.infoLog(logger, "service", "agent",
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
            logService.infoLog(logger, "service", "agent", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "agent", String.format(" transport account task fail  , target time :%s.", new Timestamp(targetTime) ), ex);
            throw new ScheduleException(ScheduleException.ScheduleErrors.SCHEDULE_DATA_FAIL);
        }

    }



    private static String sql = " select " +
            " id  ," +
            " cjsj , " +
            " gxsj , " +
            " 'NA' as czz ," +
            " yxx ," +
            " th  ," +
            " fjh ," +
            " yjdhhm , " +
            " ssdw , " +
            " ip , " +
            " jdlx21 , " +
            " dlacd , " +
            " ftms , " +
            " zxlx , " +
            " jnjb , " +
            " room , " +
            " dlzt , " +
            " dlgh , " +
            " dccode , " +
            " dcpassword , " +
            " latesttime , " +
            " icpip , " +
            " speakerid , " +
            " wluserid , " +
            " wluserpassword , " +
            " wlserverinfo , " +
            " xh , " +
            " talkinggroup , " +
            " wldeviceid , " +
            " wlconferenceid , " +
            " elteproxymode , " +
            " wlserverid , " +
            " '' as  bz  " +
            "  from  v_td_jcj_bd_zx schedule" +
//            "  where schedule.sjc >?  and schedule.sjc <= ?  " +
            " ";


    /**
     * 从原数据库查询数据
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 返回查询结果
     */
    private List<AgentEntity> findScheduleTable( Long startTime , Long endTime ) {
        PreparedStatement pstat = null;
        ResultSet rs = null;
        List<AgentEntity> list ;
        try ( Connection conn = connectionsFactory.getConnection( env.getProperty("agent") ) ) {
            if (startTime < endTime) {
                list = new ArrayList<>();
                pstat = conn.prepareStatement( sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//                pstat.setTimestamp(1, new Timestamp(startTime));
//                pstat.setTimestamp(2, new Timestamp(endTime));
                rs = pstat.executeQuery();

                while (rs.next()) {
                    AgentEntity entity = new AgentEntity();
                    //处理数据 通用属性
                    entity.setId( rs.getString("id"));
                    entity.setCreatedTime( rs.getLong("cjsj"));
                    entity.setUpdatedTime( rs.getLong("gxsj"));
                    entity.setValid(rs.getInt("yxx"));
                    entity.setOperator( rs.getString("czz") );
                    //一般属性
                    entity.setAgentNumber(  rs.getString("th") );
                    entity.setExtensionNumber(  rs.getString("fjh") );
                    entity.setEmerPhone( rs.getString("yjdhhm")   );
                    entity.setOrganizationCode(  rs.getString("ssdw")   );
                    entity.setIp(  rs.getString("ip")    );
                    entity.setLoginAcd(   rs.getString("dlacd")     );
                    entity.setExtensionType(  rs.getString("ftms")  );
                    entity.setAgentType(  rs.getString("zxlx")  );
                    entity.setSkillLevel(  rs.getString("jnjb")  );
                    entity.setLoginState(  rs.getInt("dlzt")  );
                    entity.setLoginNum( rs.getString("dlgh") );
                    entity.setOrder( rs.getString("xh")  );
                    entity.setJdlx21(  rs.getInt("jdlx21")  );
                    entity.setRoom(  rs.getString("room")  );
                    entity.setDccode(  rs.getString("dccode")  );
                    entity.setDcpassword( rs.getString("dcpassword") );
                    entity.setLatesttime(  rs.getLong("latesttime")  );
                    entity.setIcpip(  rs.getString("icpip")  );
                    entity.setSpeakerid(  rs.getString("speakerid")  );
                    entity.setWluserid(  rs.getString("wluserid")  );
                    entity.setWluserpassword(  rs.getString("wluserpassword")  );
                    entity.setWlserverinfo(  rs.getString("wlserverinfo")  );
                    entity.setTalkinggroup(  rs.getString("talkinggroup")  );
                    entity.setWldeviceid(  rs.getString("wldeviceid")  );
                    entity.setWlconferenceid(  rs.getString("wlconferenceid")  );
                    entity.setElteproxymode(   rs.getInt("elteproxymode")  );
                    entity.setRemarks( rs.getString("bz") );

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
                logService.erorLog(logger, "service", "findScheduleTableByDelete", String.format(" find schedule table  fail  , target time :%s.", new Timestamp( System.currentTimeMillis() ) ), ex);
                throw new ScheduleException(ScheduleException.ScheduleErrors.SCHEDULE_DATA_FAIL);
            }
        }
    }





    /**
     * 用户角色信息 删除服务
     */
    @Override
    @Transactional( rollbackFor = Exception.class )
    public  void  transportDelete() {
        try{
            //移除数据集合
            List<AgentEntity> removeList = new ArrayList<>() ;
            //获得全部业务角色
            List<AgentEntity> allAgents = accessor.list( AgentEntity.class ) ;
            //获得原数据库 全部业务角色 唯一标识
            List<String> ids = findScheduleTableByDelete() ;
            if( ids == null || ids.size() < 1 ){
                removeList.addAll( allAgents ) ;
            }else {

                if( allAgents != null && allAgents.size() > 0   ){
                    for( AgentEntity agentEntity  :  allAgents ){
                        String bid = agentEntity.getId() ;
                        if( !ids.contains( bid )){
                            removeList.add( agentEntity ) ;
                        }
                    }
                }
            }
            if( removeList.size() > 0 ){
                logService.infoLog(logger, "service", "transportDelete",
                        String.format(" find schedule delete table total : %d " , removeList.size()       )   );
                accessor.remove( removeList , false ) ;
            }
        }catch ( Exception ex ){
            logService.erorLog(logger, "service", "transportDelete", String.format(" find schedule table delete fail  , target time :%s.", new Timestamp( System.currentTimeMillis() ) ), ex);
            throw new ScheduleException(ScheduleException.ScheduleErrors.SCHEDULE_DATA_FAIL);
        }

    }


    private static String sqldelete = " select " +
            " id   " +
            "  from  v_td_jcj_bd_zx schedule  ";

    /**
     * 从原数据库查询删除数据
     *
     * @return 返回查询结果
     */
    private List<String> findScheduleTableByDelete(   ) {
        PreparedStatement pstat = null;
        ResultSet rs = null;
        List<String> list ;
        try ( Connection conn = connectionsFactory.getConnection( env.getProperty("systemRole") ) ) {
            list = new ArrayList<>();
            pstat = conn.prepareStatement( sqldelete, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = pstat.executeQuery();

            while (rs.next()) {
                String id = rs.getString("id")  ;
                list.add( id );
            }
            return list;
        } catch ( Exception ex) {
            logService.erorLog(logger, "service", "findScheduleTableByDelete", String.format(" find schedule table delete fail  , target time :%s.", new Timestamp( System.currentTimeMillis() ) ), ex);
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
                logService.erorLog(logger, "service", "findScheduleTableByDelete", String.format(" find schedule table delete fail  , target time :%s.", new Timestamp( System.currentTimeMillis() ) ), ex);
                throw new ScheduleException(ScheduleException.ScheduleErrors.SCHEDULE_DATA_FAIL);
            }
        }



    }


}
