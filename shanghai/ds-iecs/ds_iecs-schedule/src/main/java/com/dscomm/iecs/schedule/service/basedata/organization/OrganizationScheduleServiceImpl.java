package com.dscomm.iecs.schedule.service.basedata.organization;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.dal.po.OrganizationEntity;
import com.dscomm.iecs.schedule.dal.po.TransporterEntity;
import com.dscomm.iecs.schedule.exception.ScheduleException;
import com.dscomm.iecs.schedule.factory.ConnectionsFactory;
import com.dscomm.iecs.schedule.utils.ScheduleUtil;
import org.apache.logging.log4j.util.Strings;
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

@Component("organizationScheduleServiceImpl")
public class OrganizationScheduleServiceImpl implements OrganizationScheduleService {

    private static final Logger logger = LoggerFactory.getLogger(OrganizationScheduleServiceImpl.class);
    private LogService logService;

    private Environment env;
    private GeneralAccessor accessor;
    private ConnectionsFactory connectionsFactory;
    private static final String id = "jgxx_xfjg";
    private Integer row   ;


    @Autowired
    public OrganizationScheduleServiceImpl(LogService logService , Environment env,
                                         @Qualifier("generalAccessor") GeneralAccessor accessor,
                                         ConnectionsFactory connectionsFactory   ) {
        this.logService = logService;
        this.env = env;
        this.accessor = accessor;
        this.connectionsFactory = connectionsFactory;
        row = Strings.isNotBlank(env.getProperty("orgCount")) ? Integer.parseInt(env.getProperty("orgCount")):100;
    }

    @Override
    @Transactional( rollbackFor = Exception.class )
    public void transport(Long targetTime) {
        try {
            logService.infoLog(logger, "service", "Organization", "service is started...");
            Long logStart = System.currentTimeMillis();

            TransporterEntity entity = accessor.getById(id, TransporterEntity.class);
            if (entity == null) {
                logService.erorLog(logger, "service", "Organization", "table config not exist ",  new Exception() );
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
                    List<OrganizationEntity> list = findScheduleTable(lastUpdataTime, endTime);
                    //结果不为空 保存数据
                    if (list != null && list.size() > 0) {
                        logService.infoLog(logger, "service", "Organization",
                                String.format(" find schedule table total : %d ,start time :%s ,end time :%s" , list.size()  , new Timestamp(lastUpdataTime) , new Timestamp(endTime)  )   );

                        accessor.save(list);
                    }else{
                        logService.infoLog(logger, "service", "Organization",
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
            logService.infoLog(logger, "service", "Organization", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "Organization", String.format(" transport account task fail  , target time :%s.", new Timestamp(targetTime) ), ex);
            throw new ScheduleException(ScheduleException.ScheduleErrors.SCHEDULE_DATA_FAIL);
        }

    }

    private static String sqlId = " select " +
            "v_tb_jgxx_xfjg.id as id  " +
            "  from  v_tb_jgxx_xfjg ";

    private static String sql = " select " +
            "v_tb_jgxx_xfjg.id as id ," +
            "v_tb_jgxx_xfjg.cjsj as cjsj ," +
            "v_tb_jgxx_xfjg.gxsj as gxsj ," +
            "v_tb_jgxx_xfjg.sfyx as sfyx , " +
            "v_tb_jgxx_xfjg.yxxnum  AS yxxnum  ," +
            "v_tb_jgxx_xfjg.yxx as yxx ," +
            "v_tb_jgxx_xfjg.jgdm as jgdm ,  " +
            "v_tb_jgxx_xfjg.jgjc as jgjc , " +
            "v_tb_jgxx_xfjg.jgmc  as jgmc , " +
            "v_tb_jgxx_xfjg.jgdz as jgdz , " +
            "v_tb_jgxx_xfjg.jgms   as jgms ," +
            "v_tb_jgxx_xfjg.sjjgid as sjjgid ," +
            "v_tb_jgxx_xfjg.bmsjjgid as bmsjjgid ," +
            "v_tb_jgxx_xfjg.jglb as jglb ," +
            "v_tb_jgxx_xfjg.jglxdm as jglxdm ," +
            "v_tb_jgxx_xfjg.jgxzdm as jgxzdm ," +
            "v_tb_jgxx_xfjg.xzqdm as  xzqdm , " +
            "v_tb_jgxx_xfjg.gis_x  as gis_x ," +
            "v_tb_jgxx_xfjg.gis_y as gis_y ," +
            "v_tb_jgxx_xfjg.gis_h as gis_h , " +
            "v_tb_jgxx_xfjg.dddh as dddh , " +
            "v_tb_jgxx_xfjg.yzbm  as yzbm ," +
            "v_tb_jgxx_xfjg.czhm as czhm ," +
            "v_tb_jgxx_xfjg.lxdh as lxdh ," +
            "v_tb_jgxx_xfjg.lxrid as lxrid ," +
            "v_tb_jgxx_xfjg.lxr  as lxr ," +
            "v_tb_jgxx_xfjg.jgqz as jgqz ," +
            "v_tb_jgxx_xfjg.jgnbid as jgnbid ," +
            "v_tb_jgxx_xfjg.jgtree as jgtree ," +
            "v_tb_jgxx_xfjg.cxmlj as cxmlj ," +
            "v_tb_jgxx_xfjg.glid as glid , " +
            "v_tb_jgxx_xfjg.xqglid as xqglid ," +
            "v_tb_jgxx_xfjg.ssym as ssym ," +
            "v_tb_jgxx_xfjg.zp as zp ," +
            "v_tb_jgxx_xfjg.xqmj as xqmj ," +
            "v_tb_jgxx_xfjg.xqfw as xqfw ," +
            "v_tb_jgxx_xfjg.jgcxzt as jgcxzt ," +
            "v_tb_jgxx_xfjg.jgcxsj  as jgcxsj ," +
            "v_tb_jgxx_xfjg.zqdwbz  as zqdwbz  ," +
            "v_tb_jgxx_xfjg.jlzt as jlzt ," +
            "v_tb_jgxx_xfjg.cszt  as cszt ," +
            "v_tb_jgxx_xfjg.sjbb as sjbb ," +
            "v_tb_jgxx_xfjg.ywxtbsid as ywxtbsid ," +
            "v_tb_jgxx_xfjg.jksjbb as jksjbb ," +
            "v_tb_jgxx_xfjg.jz as jz ," +
            "v_tb_jgxx_xfjg.bz as bz  ," +
            "v_tb_jgxx_xfjg.xfjyjg_tywysbm as xfjyjg_tywysbm ," +
            "v_tb_jgxx_xfjg.dwmc as dwmc , " +
            "v_tb_jgxx_xfjg.dwjc  as dwjc , " +
            "v_tb_jgxx_xfjg.txdz as txdz , " +
            "v_tb_jgxx_xfjg.zhz_jyqk   as zhz_jyqk ," +
            "v_tb_jgxx_xfjg.xfjyjgxzdm as xfjyjgxzdm ," +
            "v_tb_jgxx_xfjg.xzqhdm as xzqhdm , " +
            "v_tb_jgxx_xfjg.dqjd  as dqjd ," +
            "v_tb_jgxx_xfjg.dqwd as dqwd ," +
            "v_tb_jgxx_xfjg.dqgd as dqgd , " +
            "v_tb_jgxx_xfjg.dzxx as dzxx ," +
            "v_tb_jgxx_xfjg.lxr_xm  as lxr_xm ," +
            "v_tb_jgxx_xfjg.sjc   AS sjc    " +
            "  from  v_tb_jgxx_xfjg where id = '%s'";

    private static String sqlCount = "SELECT count(1) as total from v_tb_jgxx_xfjg";

    /**
     * 从原数据库查询数据
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 返回查询结果
     */
    private List<OrganizationEntity> findScheduleTable(Long startTime , Long endTime ) {
        PreparedStatement pstatCount = null;
        ResultSet rsCount = null;
        Integer total = 0;
        List<OrganizationEntity> list ;
        try ( Connection conn = connectionsFactory.getConnection( env.getProperty("organization") ) ) {
            if (startTime < endTime) {

                pstatCount = conn.prepareStatement( sqlId, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                rsCount = pstatCount.executeQuery();
                list = new ArrayList<>();
                while (rsCount.next()){
//                    total = rsCount.getInt("total");
                    String orgid = rsCount.getString("id");
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            PreparedStatement pstat = null;
                            ResultSet rs = null;
                            String sqlOrg  = String.format(sql,orgid);
                            try {

                                logService.infoLog(logger, "thread", "run", "start");
                                pstat = conn.prepareStatement(sqlOrg, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//                                pstat.setMaxRows(row);
                                rs = pstat.executeQuery();
//                                rs.absolute(page * row);

                                while (rs.next()) {
                                    OrganizationEntity entity = new OrganizationEntity();
                                    //处理数据 通用属性
                                    entity.setId(rs.getString("id"));
                                    entity.setCreatedTime(rs.getLong("cjsj"));
                                    entity.setUpdatedTime(rs.getLong("gxsj"));
                                    entity.setValid(rs.getInt("yxx"));
                                    entity.setOperator("N/A");
                                    //一般属性
                                    entity.setRemarks(rs.getString("bz"));
                                    entity.setCSZT(rs.getInt("cszt"));
                                    entity.setSearchPath(rs.getString("cxmlj"));
                                    entity.setFaxNumber(rs.getString("czhm"));
                                    entity.setDispatchPhone(rs.getString("dddh"));
                                    entity.setHeight(rs.getString("dqgd"));
                                    entity.setLongitude(rs.getString("dqjd"));
                                    entity.setLatitude(rs.getString("dqwd"));
                                    entity.setOrganizationShortName(rs.getString("dwjc"));
                                    entity.setOrganizationName(rs.getString("dwmc"));
                                    entity.setEmail(rs.getString("dzxx"));
                                    entity.setRelationId(rs.getString("glid"));
                                    entity.setOrganizationRepealTime(rs.getLong("jgcxsj"));
                                    entity.setOrganizationRepealStatus(rs.getString("jgcxzt"));
                                    entity.setOrganizationCode(rs.getString("jgdm"));
                                    entity.setOrganizationCategoryCode(rs.getString("jglb"));
                                    entity.setOrganizationTypeCode(rs.getString("jglxdm"));
                                    entity.setOrganizationInsideId(rs.getString("jgnbid"));
                                    entity.setOrganizationWeight(rs.getInt("jgqz"));
                                    entity.setOrganizationTree(rs.getString("jgtree"));
                                    entity.setJKSJBB(rs.getString("jksjbb"));
                                    entity.setJLZT(rs.getInt("jlzt"));
                                    entity.setContactPhone(rs.getString("lxdh"));
                                    entity.setContactPerson(rs.getString("lxr"));
                                    entity.setContactPersonName(rs.getString("lxr_xm"));
                                    entity.setSJBB(rs.getString("sjbb"));
                                    entity.setSJC(rs.getDate("sjc").getTime());
                                    entity.setOrganizationParentId(rs.getString("sjjgid"));
                                    entity.setDomainName(rs.getString("ssym"));
                                    entity.setOrganizationAddress(rs.getString("txdz"));
                                    entity.setIdCode(rs.getString("xfjyjg_tywysbm"));
                                    entity.setOrganizationNatureCode(rs.getString("xfjyjgxzdm"));
                                    entity.setPrecinctRange(rs.getString("xqfw"));
                                    entity.setPrecinctRelevanceId(rs.getString("xqglid"));
                                    entity.setPrecinctArea(rs.getString("xqmj"));
                                    entity.setDistrictCode(rs.getString("xzqhdm"));
                                    entity.setYWXTBSID(rs.getString("ywxtbsid"));
                                    entity.setPostalCode(rs.getString("yzbm"));
                                    entity.setOrganizationDesc(rs.getString("zhz_jyqk"));
                                    entity.setPicture(rs.getString("zp"));
                                    entity.setSymbolOfDutyUnit(rs.getInt("zqdwbz"));

                                    list.add(entity);
                                }
                                logService.infoLog(logger, "thread", "run", "end");
                            }catch (Exception e){
                                logService.erorLog(logger, "thread", "run", String.format(" find entity fail  , target time :%s.", new Timestamp( endTime) ), e);
                            }finally {
                                try {
                                    if (rs != null) {
                                        rs.close();
                                    }
                                    if (pstat != null) {
                                        pstat.close();
                                    }
                                }catch (Exception e) {
                                    logService.erorLog(logger, "thread", "close", String.format(" close jdbc fail  , target time :%s.", new Timestamp( endTime) ), e);
                                }
                            }
                        }
                    });
                    thread.start();

                }


//                for (int i = 1 ; i<= total/row +1 ; i++) {
//                    int page = i;
//
//                }

                return list;
            }
            return null;
        } catch ( Exception ex) {
            logService.erorLog(logger, "service", "findScheduleTable", String.format(" find schedule table fail  , target time :%s.", new Timestamp( endTime) ), ex);
            throw new ScheduleException(ScheduleException.ScheduleErrors.SCHEDULE_DATA_FAIL);
        }finally {
            try {
                if (rsCount != null) {
                    rsCount.close();
                }
                if (pstatCount != null) {
                    pstatCount.close();
                }
            }catch ( Exception ex ){
                logService.erorLog(logger, "service", "findScheduleTable", String.format(" find schedule table  fail  , target time :%s.", new Timestamp( System.currentTimeMillis() ) ), ex);
                throw new ScheduleException(ScheduleException.ScheduleErrors.SCHEDULE_DATA_FAIL);
            }
        }
    }

}
