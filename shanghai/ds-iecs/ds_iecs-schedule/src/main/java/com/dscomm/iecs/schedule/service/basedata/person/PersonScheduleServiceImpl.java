package com.dscomm.iecs.schedule.service.basedata.person;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.dal.po.PersonEntity;
import com.dscomm.iecs.basedata.dal.po.PersonMailEntity;
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

@Component("personScheduleServiceImpl")
public class PersonScheduleServiceImpl implements PersonScheduleService {

    private static final Logger logger = LoggerFactory.getLogger(PersonScheduleServiceImpl.class);
    private LogService logService;

    private Environment env;
    private GeneralAccessor accessor;
    private ConnectionsFactory connectionsFactory;
    private static final String id = "ryxx_ryjbxx";
    private static final String idMail = "ryxx_rytxl";


    @Autowired
    public PersonScheduleServiceImpl(LogService logService , Environment env,
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
            logService.infoLog(logger, "service", "Person", "service is started...");
            Long logStart = System.currentTimeMillis();

            TransporterEntity entity = accessor.getById(id, TransporterEntity.class);
            if (entity == null) {
                logService.erorLog(logger, "service", "Person", "table config not exist ",  new Exception() );
                return ;
            }
            Long lastUpdataTime = entity.getZHTBSJ();
            DateUtils.FieldType fieldType = ScheduleUtil.transFormDateFieldType(entity.getDCTBSCDW() );
            int onceTime = entity.getDCTBSC();
            //更新时间晚于目标时间，则进行同步操作
            if (lastUpdataTime != null && targetTime != null && lastUpdataTime < targetTime) {
                int i = 0 ; //单次最多执行 7 次
                while (lastUpdataTime < targetTime && i< 7 ) {
                    List<PersonMailEntity> personMailEntityList = new ArrayList<>();
                    Long endTime = DateUtils.add(new Date(lastUpdataTime), fieldType, onceTime).getTime();;
                    if (endTime > targetTime) {
                        endTime = targetTime;
                    }
                    //单次查询
                    List<PersonEntity> list = findScheduleTable(lastUpdataTime, endTime);
                    //结果不为空 保存数据
                    if (list != null && list.size() > 0) {
                        logService.infoLog(logger, "service", "Person",
                                String.format(" find schedule table total : %d ,start time :%s ,end time :%s" , list.size()  , new Timestamp(lastUpdataTime) , new Timestamp(endTime)  )   );

                        accessor.save(list);

                        //结果不为空，继续查询人员通讯录
                        for (PersonEntity personEntity:list
                             ) {
                            List<PersonMailEntity> entities = transportMail(lastUpdataTime,endTime,personEntity);
                            if (entities != null && entities.size()>0){
                                personMailEntityList.addAll(entities);
                            }
                        }
                        //结果不为空 保存数据
                        if (personMailEntityList != null && personMailEntityList.size()>0){
                            logService.infoLog(logger, "service", "PersonMail",
                                    String.format(" find schedule table total : %d ,start time :%s ,end time :%s" , list.size()  , new Timestamp(lastUpdataTime) , new Timestamp(endTime)  )   );
                            accessor.save(personMailEntityList);
                        }else {
                            logService.infoLog(logger, "service", "PersonMail",
                                    String.format(" find schedule table is null  ,start time :%s ,end time :%s" ,   new Timestamp(lastUpdataTime) , new Timestamp(endTime)  ) );

                        }


                    }else{
                        logService.infoLog(logger, "service", "Person",
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
            logService.infoLog(logger, "service", "Person", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "Person", String.format(" transport account task fail  , target time :%s.", new Timestamp(targetTime) ), ex);
            throw new ScheduleException(ScheduleException.ScheduleErrors.SCHEDULE_DATA_FAIL);
        }

    }

    private List<PersonMailEntity> transportMail(Long startTime , Long endTime,PersonEntity personEntity){
        logService.infoLog(logger, "service", "PersonMail", "service is started...");
        TransporterEntity entity = accessor.getById(idMail, TransporterEntity.class);
        if (entity == null) {
            logService.erorLog(logger, "service", "PersonMail", "table config not exist ",  new Exception() );
            return null;
        }
        //单次查询
        List<PersonMailEntity> list = findScheduleTable(startTime, endTime,personEntity);
        //结果不为空 保存数据
        if (list != null && list.size() > 0) {

            return list;

        }else{
            logService.infoLog(logger, "service", "PersonMail",
                    String.format(" find schedule table is null  ,start time :%s ,end time :%s" ,   new Timestamp(startTime) , new Timestamp(endTime)  ) );
            return null;
        }
    }



    private static String sqlRyjbxx = " select " +
            " v_tb_ryxx_ryjbxx.id as id ," +
            " v_tb_ryxx_ryjbxx.cjsj as cjsj ," +
            " v_tb_ryxx_ryjbxx.gxsj as gxsj ," +
            " v_tb_ryxx_ryjbxx.sfyx as sfyx ," +
            " v_tb_ryxx_ryjbxx.yxx AS yxx  ," +
            " v_tb_ryxx_ryjbxx.xm as xm ," +
            " v_tb_ryxx_ryjbxx.bm as bm ," +
            " v_tb_ryxx_ryjbxx.sfzh as sfzh ," +
            " v_tb_ryxx_ryjbxx.xbdm as xbdm ," +
            " v_tb_ryxx_ryjbxx.bzjgid   as  bzjgid ," +
            " v_tb_ryxx_ryjbxx.sjjgid as sjjgid ," +
            " v_tb_ryxx_ryjbxx.mzdm as mzdm ," +
            " v_tb_ryxx_ryjbxx.jgdm as jgdm ," +
            " v_tb_ryxx_ryjbxx.zzmmdm as zzmmdm ," +
            " v_tb_ryxx_ryjbxx.dtsj as dtsj ," +
            " v_tb_ryxx_ryjbxx.csrq as csrq," +
            " v_tb_ryxx_ryjbxx.xldm as xldm ," +
            " v_tb_ryxx_ryjbxx.xwdm as xwdm ," +
            " v_tb_ryxx_ryjbxx.zyfldm as zyfldm," +
            " v_tb_ryxx_ryjbxx.hyzkdm as hyzkdm ," +
            " v_tb_ryxx_ryjbxx.txdz as txdz ," +
            " v_tb_ryxx_ryjbxx.yzbm as yzbm ," +
            " v_tb_ryxx_ryjbxx.rylbdm as rylbdm ," +
            " v_tb_ryxx_ryjbxx.zwqkdm as zwqkdm ," +
            " v_tb_ryxx_ryjbxx.gwdm as gwdm ," +
            " v_tb_ryxx_ryjbxx.zwdm as zwdm ," +
            " v_tb_ryxx_ryjbxx.zjlx as zjlx ," +
            " v_tb_ryxx_ryjbxx.zjhm as zjhm , " +
            " v_tb_ryxx_ryjbxx.jxdm as jxdm ," +
            " v_tb_ryxx_ryjbxx.sfzj as sfzj ," +
            " v_tb_ryxx_ryjbxx.zp  as zp ," +
            " v_tb_ryxx_ryjbxx.ryxh  as ryxh ," +
            " v_tb_ryxx_ryjbxx.ryzk as ryzk ," +
            " v_tb_ryxx_ryjbxx.ryzwqk as ryzwqk , " +
            " v_tb_ryxx_ryjbxx.yssjjid  as yssjjgid ," +
            " v_tb_ryxx_ryjbxx.sfxtry as sfxtry ," +
            " v_tb_ryxx_ryjbxx.sfxtryyxx AS  sfxtryyxx ," +
            " v_tb_ryxx_ryjbxx.jh as jh ," +
            " v_tb_ryxx_ryjbxx.pyt as pyt ," +
            " v_tb_ryxx_ryjbxx.gh as gh ," +
            " v_tb_ryxx_ryjbxx.sfzp  as sfzp ," +
            " v_tb_ryxx_ryjbxx.jlzt  as jlzt  ," +
            " v_tb_ryxx_ryjbxx.cszt  as cszt ," +
            " v_tb_ryxx_ryjbxx.sjbb as sjbb ," +
            " v_tb_ryxx_ryjbxx.ywxtbsid as ywxtbsid ," +
            " v_tb_ryxx_ryjbxx.jksjbb as jksjbb ," +
            " v_tb_ryxx_ryjbxx.bz as bz  ," +
            " v_tb_ryxx_ryjbxx.xfjyry_tywysbm as xfjyry_tywysbm ," +
            " v_tb_ryxx_ryjbxx.cyzjlxdm as cyzjlxdm ," +
            " v_tb_ryxx_ryjbxx.bzszjg_tywysbm   as  bzszjg_tywysbm ," +
            " v_tb_ryxx_ryjbxx.sjszjg_tywysbm as sjszjg_tywysbm ," +
            " v_tb_ryxx_ryjbxx.xfzjlylbdm as xfzjlylbdm," +
            " v_tb_ryxx_ryjbxx.xfjyrylbdm as xfjyrylbdm ," +
            " v_tb_ryxx_ryjbxx.xfjyryztdm as xfjyryztdm ," +
            " v_tb_ryxx_ryjbxx.xfjyryzwqkdm as xfjyryzwqkdm , " +
            " v_tb_ryxx_ryjbxx.xfgwflydm as xfgwflydm ," +
            " v_tb_ryxx_ryjbxx.zyjszwlbdm as zyjszwlbdm ," +
            " v_tb_ryxx_ryjbxx.xfjyxjbdm as xfjyxjbdm ," +
            " v_tb_ryxx_ryjbxx.sfzj_pdbz as sfzj_pdbz  ," +
            " v_tb_ryxx_ryjbxx.sjc\n" +
            "  from  v_tb_ryxx_ryjbxx " +
            "  where v_tb_ryxx_ryjbxx.sjc >?  and v_tb_ryxx_ryjbxx.sjc <= ?  " +
            " ";

    private static String sqlRytxl = "select " +
            "v_tb_ryxx_rytxl.ryid as id ," +
            "v_tb_ryxx_rytxl.cjsj as cjsj ," +
            "v_tb_ryxx_rytxl.gxsj as gxsj ," +
            "v_tb_ryxx_rytxl.sfyx as sfyx ," +
            "v_tb_ryxx_rytxl.yxx AS yxx  , " +
            "v_tb_ryxx_rytxl.xm as xm  ," +
            "v_tb_ryxx_rytxl.ryid as ryid , " +
            "v_tb_ryxx_rytxl.yhid  as yhid , " +
            "v_tb_ryxx_rytxl.yddh as yddh , " +
            "v_tb_ryxx_rytxl.jtdh as jtdh ," +
            "v_tb_ryxx_rytxl.bgdh as bgdh ," +
            "v_tb_ryxx_rytxl.yddh2 as yddh2 ," +
            "v_tb_ryxx_rytxl.yddh3 as yddh3 ," +
            "v_tb_ryxx_rytxl.ytwdzyx as ytwdzyx ," +
            "v_tb_ryxx_rytxl.gawyx as gawyx ," +
            "v_tb_ryxx_rytxl.dh as dh  ," +
            "v_tb_ryxx_rytxl.jlzt  as jlzt ," +
            "v_tb_ryxx_rytxl.cszt  as cszt ," +
            "v_tb_ryxx_rytxl.sjbb as sjbb ," +
            "v_tb_ryxx_rytxl.ywxtbsid as ywxtbsid ," +
            "v_tb_ryxx_rytxl.jksjbb as jksjbb  ," +
            "v_tb_ryxx_rytxl.yd_lxdh as yd_lxdh , " +
            "v_tb_ryxx_rytxl.jt_lxdh as jt_lxdh ," +
            "v_tb_ryxx_rytxl.bg_lxdh as bg_lxdh ," +
            "v_tb_ryxx_rytxl.jt_lxdh2 as jt_lxdh2 ," +
            "v_tb_ryxx_rytxl.jt_lxdh3 as jt_lxdh3 ," +
            "v_tb_ryxx_rytxl.hlw_dzxx as hlw_dzxx ," +
            "v_tb_ryxx_rytxl.nw_dzxx as nw_dzxx  from  v_tb_ryxx_rytxl  where  ryid = '%s'";


    /**
     * 从原数据库查询人员基本信息数据
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 返回查询结果
     */
    private List<PersonEntity> findScheduleTable(Long startTime , Long endTime ) {
        PreparedStatement pstat = null;
        ResultSet rs = null;
        List<PersonEntity> list ;
        try ( Connection conn = connectionsFactory.getConnection( env.getProperty("person") ) ) {
            if (startTime < endTime) {
                list = new ArrayList<>();
                pstat = conn.prepareStatement( sqlRyjbxx, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                pstat.setTimestamp(1, new Timestamp(startTime));
                pstat.setTimestamp(2, new Timestamp(endTime));
                rs = pstat.executeQuery();

                while (rs.next()) {
                    PersonEntity entity = new PersonEntity();
                    //处理数据 通用属性
                    entity.setId( rs.getString("id"));
                    entity.setCreatedTime( rs.getLong("cjsj"));
                    entity.setUpdatedTime( rs.getLong("gxsj"));
                    entity.setValid(rs.getInt("yxx"));
                    entity.setOperator( "N/A" );
                    //一般属性
                    entity.setRemarks( rs.getString("bz") );
                    entity.setCSZT( rs.getInt("cszt") );
                    entity.setAuthorizedOrganizationId( rs.getString("bzszjg_tywysbm") );
                    entity.setDateBirth( rs.getLong("csrq") );
                    entity.setCredentialsType( rs.getString("cyzjlxdm") );
                    entity.setPartyTime( rs.getLong("dtsj") );
                    entity.setMaritalStatusCode( rs.getString("hyzkdm") );
                    entity.setNativePlaceCode( rs.getString("jgdm") );
                    entity.setNationCode( rs.getString("mzdm") );
                    entity.setPersonOrderNum( rs.getInt("ryxh") );
                    entity.setIDCard( rs.getString("sfzh") );
                    entity.setJKSJBB( rs.getString("jksjbb") );
                    entity.setJLZT( rs.getInt("jlzt") );
                    entity.setWhetherExpert( rs.getInt("sfzj_pdbz") );
                    entity.setWhetherLoad( rs.getInt("sfzp") );
                    entity.setActualOrganizationId( rs.getString("sjszjg_tywysbm") );
                    entity.setSJBB( rs.getString("sjbb") );
                    entity.setSJC( rs.getDate("sjc").getTime() );
                    entity.setIdCode( rs.getString("xfjyry_tywysbm") );
                    entity.setAddress( rs.getString("txdz") );
                    entity.setYWXTBSID( rs.getString("ywxtbsid") );
                    entity.setSexCode( rs.getString("xbdm") );
                    entity.setQuartersCode( rs.getString("xfgwflydm") );
                    entity.setPersonCategoryCode( rs.getString("xfjyrylbdm") );
                    entity.setPersonStatusCode( rs.getString("xfjyryztdm") );
                    entity.setPersonReignStatusCode(rs.getString("xfjyryzwqkdm"));
                    entity.setProfessionCode(rs.getString("xfzjlylbdm"));
                    entity.setEducationCode(rs.getString("xldm"));
                    entity.setName(rs.getString("xm"));
                    entity.setAcademicDegreeCode(rs.getString("xwdm"));
                    entity.setBaseParentOrganizationId(rs.getString("yssjjgid"));
                    entity.setPostalCode(rs.getString("yzbm"));
                    entity.setCredentialsNumber(rs.getString("zjhm"));
                    entity.setPicture(rs.getString("zp"));
                    entity.setReignStatusCode(rs.getString("zwqkdm"));
                    entity.setDutiesCode(rs.getString("zyjszwlbdm"));
                    entity.setPoliticalStatusCode(rs.getString("zzmmdm"));


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

    private List<PersonMailEntity> findScheduleTable(Long startTime , Long endTime,PersonEntity personEntity){
        PreparedStatement pstat = null;
        ResultSet rs = null;
        List<PersonMailEntity> list ;
        String sql = String.format(sqlRytxl,personEntity.getId());
        try ( Connection conn = connectionsFactory.getConnection( env.getProperty("personMail") ) ) {
            if (startTime < endTime) {
                list = new ArrayList<>();
                pstat = conn.prepareStatement( sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                rs = pstat.executeQuery();

                while (rs.next()) {
                    PersonMailEntity entity = new PersonMailEntity();
                    //处理数据 通用属性
                    entity.setId( rs.getString("id"));
                    entity.setCreatedTime( rs.getLong("cjsj"));
                    entity.setUpdatedTime( rs.getLong("gxsj"));
                    entity.setValid(rs.getInt("yxx"));
                    entity.setOperator( "N/A" );
                    //一般属性
                    entity.setCSZT( rs.getInt("cszt") );
                    entity.setOfficePhone( rs.getString("bg_lxdh") );
                    entity.setInternetEmail( rs.getString("hlw_dzxx") );
                    entity.setHomePhone( rs.getString("jt_lxdh") );
                    entity.setMobilePhoneTwo( rs.getString("jt_lxdh2") );
                    entity.setMobilePhoneThree( rs.getString("jt_lxdh3") );
                    entity.setSecurityEmail( rs.getString("nw_dzxx") );
                    entity.setPersonId( rs.getString("ryid") );
                    entity.setPersonName( rs.getString("xm") );
                    entity.setMobilePhone( rs.getString("yd_lxdh") );
                    entity.setJKSJBB( rs.getString("jksjbb") );
                    entity.setJLZT( rs.getInt("jlzt") );
                    entity.setUserId( rs.getString("yhid") );
                    entity.setSJC( endTime );
                    entity.setShortNumber( rs.getString("dh") );
                    entity.setSJBB( rs.getString("sjbb") );
                    entity.setYWXTBSID( rs.getString("ywxtbsid") );

                    list.add(entity);
                }
                return list;
            }
            return null;
        } catch ( Exception ex) {
            logService.erorLog(logger, "service", "findScheduleTable", String.format(" find schedule table fail  , target time :%s.", new Timestamp( endTime) ), ex);
            return null;
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
            }
        }
    }

}
