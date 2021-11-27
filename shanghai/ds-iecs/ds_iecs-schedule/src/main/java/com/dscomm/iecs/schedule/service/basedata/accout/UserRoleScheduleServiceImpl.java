package com.dscomm.iecs.schedule.service.basedata.accout;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.dal.po.UserRoleEntity;
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
@Component("userRoleScheduleServiceImpl")
public class UserRoleScheduleServiceImpl implements UserRoleScheduleService {


    private static final Logger logger = LoggerFactory.getLogger(UserRoleScheduleServiceImpl.class);
    private LogService logService;

    private Environment env;
    private GeneralAccessor accessor;
    private ConnectionsFactory connectionsFactory;


    @Autowired
    public UserRoleScheduleServiceImpl(LogService logService , Environment env,
                                       @Qualifier("generalAccessor") GeneralAccessor accessor,
                                       ConnectionsFactory connectionsFactory) {
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
            logService.infoLog(logger, "service", "userRole", "service is started...");
            Long logStart = System.currentTimeMillis();

            TransporterEntity entity = accessor.getById("jcj_bd_yhjs", TransporterEntity.class);
            if (entity == null) {
                logService.erorLog(logger, "service", "userRole", "table config not exist ",  new Exception() );
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
                    List<UserRoleEntity> list = findScheduleTable(lastUpdataTime, endTime);
                    //结果不为空 保存数据
                    if (list != null && list.size() > 0) {
                        logService.infoLog(logger, "service", "systemRole",
                                String.format(" find schedule table total : %d ,start time :%s ,end time :%s" , list.size()  , new Timestamp(lastUpdataTime) , new Timestamp(endTime)  )   );

                        accessor.save(list);
                    }else{
                        logService.infoLog(logger, "service", "systemRole",
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
            logService.infoLog(logger, "service", "userRole", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "userRole", String.format(" transport table task fail  , target time :%s.", new Timestamp(targetTime) ), ex);
            throw new ScheduleException(ScheduleException.ScheduleErrors.SCHEDULE_DATA_FAIL);
        }

    }


    private static String sql = " select " +
            " id  ," +
            " cjsj , " +
            " gxsj , " +
            " 'NA' as czz ," +
            " yxx ," +
            " yhid as yh  ," +
            " bm as js ," +
            " '' as  bz  " +
            "  from  v_td_jcj_bd_yhjs schedule " +
//            " where schedule.sjc >?  and schedule.sjc <= ? " +
            "  ";

    /**
     * 从原数据库查询数据
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 返回查询结果
     */
    private List<UserRoleEntity> findScheduleTable( Long startTime , Long endTime ) {
        PreparedStatement pstat = null;
        ResultSet rs = null;
        List<UserRoleEntity> list ;
        try ( Connection conn = connectionsFactory.getConnection( env.getProperty("userRole") ) ) {
            if (startTime < endTime) {
                list = new ArrayList<>();
                pstat = conn.prepareStatement( sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//                pstat.setTimestamp(1, new Timestamp(startTime));
//                pstat.setTimestamp(2, new Timestamp(endTime));
                rs = pstat.executeQuery();

                while (rs.next()) {
                    UserRoleEntity entity = new UserRoleEntity();
                    //处理数据 通用属性
                    entity.setId( rs.getString("id"));
                    entity.setCreatedTime( rs.getLong("cjsj"));
                    entity.setUpdatedTime( rs.getLong("gxsj"));
                    entity.setValid(rs.getInt("yxx"));
                    entity.setOperator( rs.getString("czz") );
                    //一般属性
                    entity.setUserId( rs.getString("yh") );
                    entity.setRoleId( rs.getString("js") );
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
                logService.erorLog(logger, "service", "findScheduleTableByDelete", String.format(" find schedule table   fail  , target time :%s.", new Timestamp( System.currentTimeMillis() ) ), ex);
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
            List<UserRoleEntity> removeList = new ArrayList<>() ;
            //获得全部业务角色
            List<UserRoleEntity> allUserRoles = accessor.list( UserRoleEntity.class ) ;
            //获得原数据库 全部业务角色 唯一标识
            List<String> ids = findScheduleTableByDelete() ;
            if( ids == null || ids.size() < 1 ){
                removeList.addAll( allUserRoles ) ;
            }else {

                if( allUserRoles != null && allUserRoles.size() > 0   ){
                    for( UserRoleEntity userRole  :  allUserRoles ){
                        String bid = userRole.getId() ;
                        if( !ids.contains( bid )){
                            removeList.add( userRole ) ;
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
            "  from  v_td_jcj_bd_yhjs schedule  ";

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
